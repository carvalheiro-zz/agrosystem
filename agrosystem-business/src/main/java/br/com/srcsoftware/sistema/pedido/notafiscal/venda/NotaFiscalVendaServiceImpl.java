package br.com.srcsoftware.sistema.pedido.notafiscal.venda;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.factory.ModelFactory;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.exceptions.ModelFactoryException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;
import br.com.srcsoftware.modular.manager.utilidades.DateUtil;
import br.com.srcsoftware.modular.manager.utilidades.MensagensResourcesApplication;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.pedido.itempedido.ItemPedidoDTO;
import br.com.srcsoftware.sistema.pedido.itempedido.ItemPedidoPO;
import br.com.srcsoftware.sistema.pedido.notafiscal.simplesremessa.NotaFiscalSimplesRemessaFacade;
import br.com.srcsoftware.sistema.pedido.pedido.PedidoDTO;
import br.com.srcsoftware.sistema.pedido.pedido.PedidoFacade;
import br.com.srcsoftware.sistema.pedido.pedido.PedidoPO;
import br.com.srcsoftware.sistema.pedido.pedido.PedidoServiceImpl;

public final class NotaFiscalVendaServiceImpl implements NotaFiscalVendaServiceInterface{

	private transient Logger logger = Logger.getLogger( this.getClass().getName() );

	private NotaFiscalVendaDAOInterface daoInterface;

	private ModelFactory< NotaFiscalVendaPO, NotaFiscalVendaDTO > modelFactory = new ModelFactory<>();

	public NotaFiscalVendaServiceImpl(){
		this.daoInterface = new NotaFiscalVendaDAOImpl();
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, NotaFiscalVendaDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {

			if ( Utilidades.isNuloOuVazio( dto.getItens() ) ) {
				throw new ApplicationException( "Por favor informe os itens do NotaFiscalVenda!" );
			}

			hibernate.iniciarTransacao();

			NotaFiscalVendaPO po = this.modelFactory.getPO( NotaFiscalVendaPO.class, dto );
			po.setarDadosAuditoria( usuarioSessaoPOJO.getUsuario().getLogin(), LocalDateTime.now() );

			if ( NotaFiscalVendaDTO.TIPO_VENDA.equals( po.getTipo() ) ) {
				po.setStatus( PedidoDTO.STATUS_FINALIZADO );
			} else if ( NotaFiscalVendaDTO.TIPO_FUTURA.equals( po.getTipo() ) ) {
				po.setStatus( PedidoDTO.STATUS_ABERTO );
			}

			if ( po.getData().isBefore( po.getPedido().getData() ) ) {
				throw new ApplicationException( "A data da Nota Fiscal deve ser Maior ou Igual a data do Pedido" );
			}

			/** Controlando o acesso concorrente do Item do Pedido de maneira a bloquear caso mais de um usuario queira lançar entrada do item e suas somas exceda a quantidade Pedida. */
			this.gerenciarAcessoConcorrenteEntreguaItensPedidos( po, po.getItens() );

			dto.setId( this.daoInterface.inserir( hibernate, po ).getId().toString() );

			if ( NotaFiscalVendaDTO.TIPO_VENDA.equals( po.getTipo() ) ) {
				this.gerenciarPedido( hibernate, usuarioSessaoPOJO.getUsuario().getLogin(), po.getPedido(), po.getItens() );
			}

			hibernate.commitTransacao();
		} catch ( ApplicationException e ) {
			hibernate.rollbackTransacao();

			this.logger.error( e.getMessage(), e );
			throw e;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();

			this.logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, NotaFiscalVendaDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			if ( Utilidades.isNuloOuVazio( dto.getItens() ) ) {
				throw new ApplicationException( "Por favor informe os itens do NotaFiscalVenda!" );
			}

			hibernate.iniciarTransacao();

			NotaFiscalVendaPO po = this.modelFactory.getPO( NotaFiscalVendaPO.class, dto );
			po.setarDadosAuditoria( usuarioSessaoPOJO.getUsuario().getLogin(), LocalDateTime.now() );

			if ( po.getData().isBefore( po.getPedido().getData() ) ) {
				throw new ApplicationException( "A data da Nota Fiscal deve ser Maior ou Igual a data do Pedido" );
			}

			/** Controlando o acesso concorrente do Item do Pedido de maneira a bloquear caso mais de um usuario queira lançar entrada do item e suas somas exceda a quantidade Pedida. */
			this.gerenciarAcessoConcorrenteEntreguaItensPedidos( po, po.getItens() );

			if ( NotaFiscalVendaDTO.TIPO_VENDA.equals( po.getTipo() ) ) {
				this.gerenciarPedido( hibernate, usuarioSessaoPOJO.getUsuario().getLogin(), po.getPedido(), po.getItens() );
			}

			/** Deve-se Obrigatoriamente atualizar o Estoque antes de alterar a Nota de Entrega */
			this.daoInterface.alterar( hibernate, po );

			hibernate.commitTransacao();
		} catch ( ApplicationException e ) {
			hibernate.rollbackTransacao();

			this.logger.error( e.getMessage(), e );
			throw e;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();

			this.logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	private ArrayList< ItemNotaFiscalVendaPO > getItensEntregaRemovidos( NotaFiscalVendaPO compraProduto ) throws ApplicationException {
		NotaFiscalVendaPO notaEncontrada = this.daoInterface.filtrarPorId( compraProduto.getId() );
		ArrayList< ItemNotaFiscalVendaPO > itensRemovidos = new ArrayList<>();

		for ( ItemNotaFiscalVendaPO itemEntregaCorrente : notaEncontrada.getItens() ) {
			boolean achei = false;
			for ( ItemNotaFiscalVendaPO itemCorrente : compraProduto.getItens() ) {
				if ( itemEntregaCorrente.getId().equals( itemCorrente.getId() ) ) {
					achei = true;
					break;
				}
			}

			if ( !achei ) {
				ItemNotaFiscalVendaPO itemRemovido = new ItemNotaFiscalVendaPO();
				itemRemovido.setId( itemEntregaCorrente.getId() );
				itemRemovido.setItemPedido( itemEntregaCorrente.getItemPedido() );
				itemRemovido.setPrecoCusto( itemEntregaCorrente.getPrecoCusto() );
				itemRemovido.setQuantidade( itemEntregaCorrente.getQuantidade() );

				itensRemovidos.add( itemRemovido );
			}
		}

		return itensRemovidos;
	}

	@Override
	public void excluir( NotaFiscalVendaDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			NotaFiscalVendaPO po = this.modelFactory.getPO( NotaFiscalVendaPO.class, dto );

			this.daoInterface.excluir( hibernate, po );
			if ( NotaFiscalVendaDTO.TIPO_VENDA.equals( po.getTipo() ) ) {
				this.gerenciarPedidoExcluir( hibernate, po );
			}

			hibernate.commitTransacao();
		} catch ( ApplicationException e ) {
			hibernate.rollbackTransacao();

			this.logger.error( e.getMessage(), e );
			throw e;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();

			this.logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public ArrayList< NotaFiscalVendaDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, NotaFiscalVendaDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException {

		try {
			NotaFiscalVendaPO poFilter = this.modelFactory.getPO( NotaFiscalVendaPO.class, dto );

			HashMap< String, ArrayList< Object > > camposBetween = new HashMap<>();

			if ( !Utilidades.isNuloOuVazio( dataInicialParam ) && !Utilidades.isNuloOuVazio( dataFinalParam ) ) {
				camposBetween.put( "data", new ArrayList< Object >( Arrays.asList( LocalDate.parse( dataInicialParam, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ), LocalDate.parse( dataFinalParam, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ) ) ) );
			}

			List< NotaFiscalVendaPO > encontrados = this.daoInterface.filtrar( paginacao, camposOrders, camposBetween, poFilter );

			ArrayList< NotaFiscalVendaDTO > dtosRetorno = new ArrayList<>();

			for ( NotaFiscalVendaPO poCorrente : encontrados ) {
				NotaFiscalVendaDTO dtoCorrente = this.modelFactory.getDTO( NotaFiscalVendaDTO.class, poCorrente );
				dtoCorrente.setQuantidadeRestante( PedidoFacade.getInstance().calcularValoresRestantesEntregarNFSimplesRemessa( null, dtoCorrente.getId() ) );

				dtosRetorno.add( dtoCorrente );
			}

			return dtosRetorno;
		} catch ( ApplicationException e ) {

			this.logger.error( e.getMessage(), e );
			throw e;
		} catch ( ModelFactoryException e ) {
			this.logger.error( "Erro no ModelFactory" + System.lineSeparator() + e.getMessage().trim(), e );
			throw new ApplicationException( "Erro no ModelFactory" + System.lineSeparator() + e.getMessage().trim(), e );
		} catch ( Exception e ) {

			this.logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public NotaFiscalVendaDTO filtrarPorId( String id ) throws ApplicationException {

		try {

			if ( Utilidades.isNuloOuVazio( id ) ) {
				throw new ApplicationException( "Id não informado!" );
			}

			NotaFiscalVendaPO encontrado = this.daoInterface.filtrarPorId( Long.valueOf( id ) );

			NotaFiscalVendaDTO dtoRetorno = this.modelFactory.getDTO( NotaFiscalVendaDTO.class, encontrado );

			for ( ItemNotaFiscalVendaDTO itemCorrente : dtoRetorno.getItens() ) {
				String quantidadeRestanteEncontrada = PedidoFacade.getInstance().calcularValoresRestantesEntregarNFSimplesRemessa( itemCorrente.getItemPedido().getProduto().getId(), dtoRetorno.getId() );
				itemCorrente.setQuantidadeRestante( quantidadeRestanteEncontrada );
			}

			return dtoRetorno;
		} catch ( ApplicationException e ) {

			this.logger.error( e.getMessage(), e );
			throw e;
		} catch ( ModelFactoryException e ) {
			this.logger.error( "Erro no ModelFactory" + System.lineSeparator() + e.getMessage().trim(), e );
			throw new ApplicationException( "Erro no ModelFactory" + System.lineSeparator() + e.getMessage().trim(), e );
		} catch ( Exception e ) {

			this.logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	private void gerenciarPedido( HibernateConnection hibernateConnection, String usuario, PedidoPO pedido, Set< ItemNotaFiscalVendaPO > itensVenda ) throws ApplicationException, ParseException {

		PedidoPO pedidoBD = (PedidoPO) new HibernateConnection().filtrarPorId( PedidoPO.class, pedido.getId() );

		PedidoServiceImpl service = new PedidoServiceImpl();

		String quantidadeItensRestantes = service.calcularValoresRestantesEntregarPedido( pedidoBD.getId().toString() );

		BigDecimal quantidadeItensRestantesAux = Utilidades.parseBigDecimal( quantidadeItensRestantes );

		BigDecimal quantidadeItensEntregues = BigDecimal.ZERO;
		for ( ItemNotaFiscalVendaPO itemEntregueCorrente : itensVenda ) {

			ItemNotaFiscalVendaPO itemEntregueBD = null;
			if ( !Utilidades.isNuloOuVazio( itemEntregueCorrente.getId() ) ) {
				itemEntregueBD = (ItemNotaFiscalVendaPO) new HibernateConnection().filtrarPorId( ItemNotaFiscalVendaPO.class, itemEntregueCorrente.getId() );
			}

			if ( !Utilidades.isNuloOuVazio( itemEntregueBD ) && ( itemEntregueBD.getQuantidade().compareTo( itemEntregueCorrente.getQuantidade() ) == 0 ) ) {
				continue;
			}

			quantidadeItensEntregues = quantidadeItensEntregues.add( itemEntregueCorrente.getQuantidade() );

			if ( !Utilidades.isNuloOuVazio( itemEntregueBD ) ) {
				quantidadeItensRestantesAux = quantidadeItensRestantesAux.add( itemEntregueBD.getQuantidade() );
			}
		}

		quantidadeItensRestantesAux = quantidadeItensRestantesAux.subtract( quantidadeItensEntregues );

		String statusAnterior = pedidoBD.getStatus();
		String statusAtual = "";

		LocalDateTime dataAlteracaoPedido = LocalDateTime.now();

		if ( Utilidades.isNuloOuVazio( quantidadeItensRestantesAux ) ) {
			pedidoBD.setStatus( PedidoDTO.STATUS_ABERTO );
			statusAtual = PedidoDTO.STATUS_ABERTO;
		} else if ( quantidadeItensRestantesAux.compareTo( BigDecimal.ZERO ) == 0 ) {
			pedidoBD.setStatus( PedidoDTO.STATUS_FINALIZADO );
			statusAtual = PedidoDTO.STATUS_FINALIZADO;
		} else {
			pedidoBD.setStatus( PedidoDTO.STATUS_ANDAMENTO );
			statusAtual = PedidoDTO.STATUS_ANDAMENTO;
		}

		if ( !statusAnterior.equalsIgnoreCase( statusAtual ) ) {
			pedidoBD.setObservacao( ( pedidoBD.getObservacao() == null ? "" : pedidoBD.getObservacao() ) + " | " + "Pedido " + statusAtual + " pelo usuário: [" + usuario + "] em " + DateUtil.parseLocalDateTime( dataAlteracaoPedido ) );
			pedidoBD.setarDadosAuditoria( usuario, dataAlteracaoPedido );
			hibernateConnection.alterar( pedidoBD );
		}
	}

	private void gerenciarPedidoExcluir( HibernateConnection hibernateConnection, NotaFiscalVendaPO notaVenda ) throws ApplicationException, ParseException {

		PedidoPO pedidoBD = (PedidoPO) new HibernateConnection().filtrarPorId( PedidoPO.class, notaVenda.getPedido().getId() );

		PedidoServiceImpl service = new PedidoServiceImpl();

		// Pegando a quantidade total de itens do Pedido
		BigDecimal quantidadeTotalPedido = BigDecimal.ZERO;
		for ( ItemPedidoPO itemPedidoCorrente : pedidoBD.getItens() ) {
			quantidadeTotalPedido = quantidadeTotalPedido.add( itemPedidoCorrente.getQuantidade() );
		}

		// Pegando a quantidade total de itens da notaFiscalVenda
		BigDecimal quantidadeTotalNFVenda = BigDecimal.ZERO;
		for ( ItemNotaFiscalVendaPO itemNFVendaCorrente : notaVenda.getItens() ) {
			quantidadeTotalNFVenda = quantidadeTotalNFVenda.add( itemNFVendaCorrente.getQuantidade() );
		}

		String quantidadeItensRestantes = service.calcularValoresRestantesEntregarPedido( null, pedidoBD.getId().toString() );

		String statusAnterior = pedidoBD.getStatus();
		String statusAtual = "";

		LocalDateTime dataAlteracaoPedido = LocalDateTime.now();

		if ( Utilidades.parseBigDecimal( quantidadeItensRestantes ).compareTo( quantidadeTotalPedido.subtract( quantidadeTotalNFVenda ) ) == 0 ) {
			pedidoBD.setStatus( PedidoDTO.STATUS_ABERTO );
			statusAtual = PedidoDTO.STATUS_ABERTO;
		} else {
			pedidoBD.setStatus( PedidoDTO.STATUS_ANDAMENTO );
			statusAtual = PedidoDTO.STATUS_ANDAMENTO;
		}

		if ( !statusAnterior.equalsIgnoreCase( statusAtual ) ) {
			pedidoBD.setObservacao( ( pedidoBD.getObservacao() == null ? "" : pedidoBD.getObservacao() ) + " | Exclusão da Nota Fiscal Venda nº " + notaVenda.getNumero() + ", Pedido com status atual [" + statusAtual + "] pelo usuário: [" + pedidoBD.getNomeUsuarioAlteracao() + "] em " + DateUtil.parseLocalDateTime( dataAlteracaoPedido ) );
			pedidoBD.setarDadosAuditoria( pedidoBD.getNomeUsuarioAlteracao(), dataAlteracaoPedido );
			hibernateConnection.alterar( pedidoBD );
		}
	}

	/**
	 * 
	 * Método responsável por Controlar o acesso concorrente do Item do Pedido de maneira a bloquear caso mais de um usuario queira lançar entrada do item e suas somas exceda a quantidade Pedida.
	 *
	 * @param hibernateConnection
	 * @param idPedido
	 * @param itensPedidos
	 * @param itensNotaVenda
	 * @throws ApplicationException
	 *
	 * @author Gabriel Damiani Carvalheiro <gabriel.carvalheiro@gmail.com.br>
	 * @since 28 de mar de 2017 11:29:14
	 * @version 1.0
	 */
	private void gerenciarAcessoConcorrenteEntreguaItensPedidos( NotaFiscalVendaPO notaVenda, Set< ItemNotaFiscalVendaPO > itensNotaVenda ) throws ApplicationException {

		PedidoServiceImpl service = new PedidoServiceImpl();

		for ( ItemNotaFiscalVendaPO itemEntregueNF : itensNotaVenda ) {

			ItemNotaFiscalVendaPO itemEntregueBD = null;
			if ( !Utilidades.isNuloOuVazio( itemEntregueNF.getId() ) ) {
				itemEntregueBD = (ItemNotaFiscalVendaPO) new HibernateConnection().filtrarPorId( ItemNotaFiscalVendaPO.class, itemEntregueNF.getId() );
			}

			if ( !Utilidades.isNuloOuVazio( itemEntregueBD ) && ( itemEntregueBD.getQuantidade().compareTo( itemEntregueNF.getQuantidade() ) == 0 ) ) {
				continue;
			}

			BigDecimal quantidadeJaEntregue = BigDecimal.ZERO;
			if ( !Utilidades.isNuloOuVazio( itemEntregueBD ) ) {
				quantidadeJaEntregue = itemEntregueBD.getQuantidade();
			}

			String quantidadeItensRestantes = service.calcularValoresRestantesEntregarNFVendaNFFutura( itemEntregueNF.getItemPedido().getProduto().getId().toString(), notaVenda.getPedido().getId().toString() );

			BigDecimal quantidadeItensRestantesAux = Utilidades.parseBigDecimal( quantidadeItensRestantes ).add( quantidadeJaEntregue ).subtract( itemEntregueNF.getQuantidade() );

			if ( quantidadeItensRestantesAux.compareTo( BigDecimal.ZERO ) < 0 ) {
				throw new ApplicationException( "O Item " + itemEntregueNF.getItemPedido().getProduto().getNome() + " possui uma quantidade de entrega maior que a pedida/restante." );
			}
		}

	}

	@Override
	public String getTotalFaltaEntregar( String idProduto, String idPedido ) throws ApplicationException {
		try {
			PedidoServiceImpl service = new PedidoServiceImpl();

			return service.calcularValoresRestantesEntregarNFVendaNFFutura( idProduto, idPedido );
		} catch ( ApplicationException e ) {
			this.logger.error( MensagensResourcesApplication.getString( "mensagem", new String[ ] { e.getMessage() } ) );
			throw e;
		} catch ( Exception e ) {
			this.logger.error( MensagensResourcesApplication.getString( "mensagem", new String[ ] { e.getMessage() } ) );
			throw new ApplicationException( "Erro ao ocorrido: " + e.getMessage(), e );
		}
	}

	@Override
	public List< NotaFiscalVendaDTO > filtrarAbertosAndamentos( String numeroNF, String tipoNF, HashMap< String, String > camposOrders ) throws ApplicationException {

		List< NotaFiscalVendaDTO > encontradosDTO = new ArrayList<>();

		try {

			List< NotaFiscalVendaPO > encontrados = this.daoInterface.filtrarAbertosAndamentos( numeroNF, tipoNF, camposOrders );

			for ( NotaFiscalVendaPO poCorrente : encontrados ) {
				NotaFiscalVendaDTO encontrado = this.modelFactory.getDTO( NotaFiscalVendaDTO.class, poCorrente );
				encontradosDTO.add( encontrado );
			}

			return encontradosDTO;
		} catch ( ApplicationException e ) {
			this.logger.error( MensagensResourcesApplication.getString( "mensagem", new String[ ] { e.getMessage() } ) );
			throw e;
		} catch ( Exception e ) {

			this.logger.error( MensagensResourcesApplication.getString( "mensagem", new String[ ] { e.getMessage() } ) );
			throw ( new ApplicationException( "Erro ao ocorrido: " + e.getMessage(), e ) );
		}

	}

	@Override
	public void adicionarItemRecebido( NotaFiscalVendaDTO notaFiscalVenda, ArrayList< ItemPedidoDTO > itensPedidosRestantes, String idItemPedidoReceber, String quantidadeItensAdicionar ) throws ApplicationException {

		try {

			/** Pegando o item de pedido que foi selecionado como recebido */
			ItemPedidoDTO itemPedidoEntregar = new ItemPedidoDTO();
			for ( ItemPedidoDTO itemPedidoCorrente : itensPedidosRestantes ) {
				if ( idItemPedidoReceber.equals( itemPedidoCorrente.getId() ) ) {
					itemPedidoEntregar = itemPedidoCorrente;
					break;
				}
			}

			boolean jaAdicionado = false;
			if ( !Utilidades.isNuloOuVazio( notaFiscalVenda.getItens() ) ) {
				for ( ItemNotaFiscalVendaDTO itemNotaFiscalEntregueCorrente : notaFiscalVenda.getItens() ) {
					if ( idItemPedidoReceber.equals( itemNotaFiscalEntregueCorrente.getItemPedido().getId() ) ) {

						BigDecimal quantidadeRestante = BigDecimal.ZERO;
						quantidadeRestante = Utilidades.parseBigDecimal( itemPedidoEntregar.getQuantidadeRestante() );
						quantidadeRestante = quantidadeRestante.subtract( Utilidades.parseBigDecimal( quantidadeItensAdicionar ) );
						if ( quantidadeRestante.compareTo( BigDecimal.ZERO ) <= 0 ) {
							itensPedidosRestantes.remove( itemPedidoEntregar );

							/** Seta como Quantidade total de itens entregues como sendo a quantidade de itens do Pedido */
							BigDecimal quantidadeTotalEntregue = Utilidades.parseBigDecimal( itemPedidoEntregar.getQuantidadeRestante() ).add( Utilidades.parseBigDecimal( itemNotaFiscalEntregueCorrente.getQuantidade() ) );
							itemNotaFiscalEntregueCorrente.setQuantidade( Utilidades.parseBigDecimal( quantidadeTotalEntregue ) );

						} else {
							BigDecimal quantidadeTotalEntregue = Utilidades.parseBigDecimal( itemNotaFiscalEntregueCorrente.getQuantidade() ).add( Utilidades.parseBigDecimal( quantidadeItensAdicionar ) );
							itemNotaFiscalEntregueCorrente.setQuantidade( Utilidades.parseBigDecimal( quantidadeTotalEntregue ) );

							itemPedidoEntregar.setQuantidadeRestante( Utilidades.parseBigDecimal( quantidadeRestante ) );
						}

						jaAdicionado = true;
						break;
					}
				}
			}

			if ( !jaAdicionado ) {
				ItemNotaFiscalVendaDTO itemNotaFiscalEntregue = new ItemNotaFiscalVendaDTO();
				itemNotaFiscalEntregue.setItemPedido( itemPedidoEntregar );
				itemNotaFiscalEntregue.setQuantidade( quantidadeItensAdicionar );
				itemNotaFiscalEntregue.setPrecoCusto( itemPedidoEntregar.getPrecoCusto() );

				BigDecimal quantidadeRestante = BigDecimal.ZERO;
				quantidadeRestante = Utilidades.parseBigDecimal( itemPedidoEntregar.getQuantidadeRestante() );
				quantidadeRestante = quantidadeRestante.subtract( Utilidades.parseBigDecimal( quantidadeItensAdicionar ) );

				/** Caso A quantidade a ser adicionada seja igual ou maior que a quantidade de itens do pedido */
				if ( quantidadeRestante.compareTo( BigDecimal.ZERO ) <= 0 ) {

					/** Remove da lista de Itens de pedidos */
					itensPedidosRestantes.remove( itemPedidoEntregar );

					/** Seta como Quantidade total de itens entregues como sendo a quantidade de itens do Pedido */
					itemNotaFiscalEntregue.setQuantidade( itemPedidoEntregar.getQuantidadeRestante() );
				} else {
					itemNotaFiscalEntregue.setQuantidade( quantidadeItensAdicionar );

					itemPedidoEntregar.setQuantidadeRestante( Utilidades.parseBigDecimal( quantidadeRestante ) );
				}

				notaFiscalVenda.getItens().add( itemNotaFiscalEntregue );
			}
		} catch ( Exception e ) {
			this.logger.error( MensagensResourcesApplication.getString( "mensagem", new String[ ] { e.getMessage() } ) );
			throw new ApplicationException( "Erro ao ocorrido: " + e.getMessage(), e );
		}
	}

	@Override
	public void removerItemRecebido( String tipoNF, NotaFiscalVendaDTO notaFiscalVenda, ArrayList< ItemPedidoDTO > itensPedidosRestantes, String idItemPedidoRetornar, String quantidadeItensRetornar ) throws ApplicationException {
		try {

			/** Pegando o item de pedido que foi selecionado como recebido */
			ItemNotaFiscalVendaDTO itemNotaFiscalEntregueSelecionado = new ItemNotaFiscalVendaDTO();
			for ( ItemNotaFiscalVendaDTO itemEntregueCorrente : notaFiscalVenda.getItens() ) {
				if ( idItemPedidoRetornar.equals( itemEntregueCorrente.getItemPedido().getId() ) ) {
					itemNotaFiscalEntregueSelecionado = itemEntregueCorrente;
					break;
				}
			}

			if ( NotaFiscalVendaDTO.TIPO_FUTURA.equals( tipoNF ) ) {
				if ( !Utilidades.isNuloOuVazio( notaFiscalVenda.getId() ) ) {
					String quantidadeFaltaEntregarAux = NotaFiscalSimplesRemessaFacade.getInstance().getTotalFaltaEntregar( itemNotaFiscalEntregueSelecionado.getItemPedido().getProduto().getId(), notaFiscalVenda.getId() );
					BigDecimal quantidadeFaltaEntregar = Utilidades.parseBigDecimal( quantidadeFaltaEntregarAux );

					System.out.println( quantidadeFaltaEntregar );
					BigDecimal quantidadeDevolvida = Utilidades.parseBigDecimal( quantidadeItensRetornar );
					if ( ( BigDecimal.ZERO.compareTo( quantidadeFaltaEntregar ) == 0 ) || ( quantidadeDevolvida.compareTo( quantidadeFaltaEntregar ) > 0 ) ) {
						throw new ApplicationException( "Não é possível remover este item, pois este item possui entregas em Notas Fiscais de Simples Remessa!<br/> Quantidade ainda não entregue: " + quantidadeFaltaEntregar + "<br/>Quantidade que deseja remover da FUTURA: " + quantidadeDevolvida );
					}
				}
			}

			boolean jaAdicionado = false;
			if ( !Utilidades.isNuloOuVazio( itensPedidosRestantes ) ) {
				for ( ItemPedidoDTO itemPedidoEntregarCorrente : itensPedidosRestantes ) {
					if ( itemNotaFiscalEntregueSelecionado.getItemPedido().getId().equals( itemPedidoEntregarCorrente.getId() ) ) {

						BigDecimal quantidadeEntregue = BigDecimal.ZERO;
						quantidadeEntregue = Utilidades.parseBigDecimal( itemNotaFiscalEntregueSelecionado.getQuantidade() );
						quantidadeEntregue = quantidadeEntregue.subtract( Utilidades.parseBigDecimal( quantidadeItensRetornar ) );
						if ( quantidadeEntregue.compareTo( BigDecimal.ZERO ) <= 0 ) {
							notaFiscalVenda.getItens().remove( itemNotaFiscalEntregueSelecionado );

							BigDecimal quantidadeTotalDevolvido = Utilidades.parseBigDecimal( itemPedidoEntregarCorrente.getQuantidadeRestante() ).add( Utilidades.parseBigDecimal( itemNotaFiscalEntregueSelecionado.getQuantidade() ) );
							itemPedidoEntregarCorrente.setQuantidade( Utilidades.parseBigDecimal( quantidadeTotalDevolvido ) );
							itemPedidoEntregarCorrente.setQuantidadeRestante( itemPedidoEntregarCorrente.getQuantidade() );

						} else {

							BigDecimal quantidadeTotalDevolvido = Utilidades.parseBigDecimal( itemPedidoEntregarCorrente.getQuantidadeRestante() ).add( Utilidades.parseBigDecimal( quantidadeItensRetornar ) );
							itemPedidoEntregarCorrente.setQuantidadeRestante( Utilidades.parseBigDecimal( quantidadeTotalDevolvido ) );

							itemNotaFiscalEntregueSelecionado.setQuantidade( Utilidades.parseBigDecimal( quantidadeEntregue ) );
						}

						jaAdicionado = true;
						break;
					}
				}
			}

			if ( !jaAdicionado ) {
				ItemPedidoDTO itemPedidoEntregar = new ItemPedidoDTO();
				itemPedidoEntregar.setId( itemNotaFiscalEntregueSelecionado.getItemPedido().getId() );
				itemPedidoEntregar.setProduto( itemNotaFiscalEntregueSelecionado.getItemPedido().getProduto() );
				itemPedidoEntregar.setQuantidade( itemNotaFiscalEntregueSelecionado.getQuantidade() );
				itemPedidoEntregar.setPrecoCusto( itemNotaFiscalEntregueSelecionado.getPrecoCusto() );

				BigDecimal quantidadeEntregue = BigDecimal.ZERO;
				quantidadeEntregue = Utilidades.parseBigDecimal( itemNotaFiscalEntregueSelecionado.getQuantidade() );
				quantidadeEntregue = quantidadeEntregue.subtract( Utilidades.parseBigDecimal( quantidadeItensRetornar ) );
				if ( quantidadeEntregue.compareTo( BigDecimal.ZERO ) <= 0 ) {
					notaFiscalVenda.getItens().remove( itemNotaFiscalEntregueSelecionado );

					itemPedidoEntregar.setQuantidade( itemNotaFiscalEntregueSelecionado.getQuantidade() );
					itemPedidoEntregar.setQuantidadeRestante( itemPedidoEntregar.getQuantidade() );
				} else {
					BigDecimal quantidadeTotalDevolvido = Utilidades.parseBigDecimal( quantidadeItensRetornar );//Utilidades.parseBigDecimal( itemEntregar.getQuantidadeRestante() ).add( Utilidades.parseBigDecimal( quantidadeItensRetornar ) );
					itemPedidoEntregar.setQuantidadeRestante( Utilidades.parseBigDecimal( quantidadeTotalDevolvido ) );
					itemNotaFiscalEntregueSelecionado.setQuantidade( Utilidades.parseBigDecimal( quantidadeEntregue ) );
				}

				itensPedidosRestantes.add( itemPedidoEntregar );
			}

		} catch ( Exception e ) {
			this.logger.error( MensagensResourcesApplication.getString( "mensagem", new String[ ] { e.getMessage() } ) );
			throw new ApplicationException( "Erro ao ocorrido: " + e.getMessage(), e );
		}
	}
}
