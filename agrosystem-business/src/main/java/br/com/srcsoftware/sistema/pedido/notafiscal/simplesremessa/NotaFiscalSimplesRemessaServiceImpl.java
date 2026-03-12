package br.com.srcsoftware.sistema.pedido.notafiscal.simplesremessa;

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
import br.com.srcsoftware.sistema.pedido.itempedido.ItemPedidoPO;
import br.com.srcsoftware.sistema.pedido.notafiscal.venda.ItemNotaFiscalVendaDTO;
import br.com.srcsoftware.sistema.pedido.notafiscal.venda.ItemNotaFiscalVendaPO;
import br.com.srcsoftware.sistema.pedido.notafiscal.venda.NotaFiscalVendaPO;
import br.com.srcsoftware.sistema.pedido.pedido.PedidoDTO;
import br.com.srcsoftware.sistema.pedido.pedido.PedidoPO;
import br.com.srcsoftware.sistema.pedido.pedido.PedidoServiceImpl;

public final class NotaFiscalSimplesRemessaServiceImpl implements NotaFiscalSimplesRemessaServiceInterface{

	private transient Logger logger = Logger.getLogger( this.getClass().getName() );

	private NotaFiscalSimplesRemessaDAOInterface daoInterface;

	private ModelFactory< NotaFiscalSimplesRemessaPO, NotaFiscalSimplesRemessaDTO > modelFactory = new ModelFactory<>();

	public NotaFiscalSimplesRemessaServiceImpl(){
		this.daoInterface = new NotaFiscalSimplesRemessaDAOImpl();
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, NotaFiscalSimplesRemessaDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			String usuarioAlteracao = usuarioSessaoPOJO.getUsuario().getLogin();
			LocalDateTime dataAlteracao = LocalDateTime.now();

			hibernate.iniciarTransacao();

			NotaFiscalSimplesRemessaPO po = this.modelFactory.getPO( NotaFiscalSimplesRemessaPO.class, dto );
			po.setarDadosAuditoria( usuarioAlteracao, dataAlteracao );

			if ( po.getData().isBefore( po.getNotaFiscalVendaFutura().getData() ) ) {
				throw new ApplicationException( "A data da Nota Fiscal de Simples Remessa deve ser Maior ou Igual a data da Nota Fiscal Futura" );
			}

			/** Controlando o acesso concorrente do Item da Nota Futura de maneira a bloquear caso mais de um usuario queira lançar entrada do item e suas somas exceda a quantidade da Nota. */
			this.gerenciarAcessoConcorrenteEntreguaItensPedidos( po, po.getItens() );

			dto.setId( this.daoInterface.inserir( hibernate, po ).getId().toString() );

			this.gerenciarNotaFiscalVenda( hibernate, po.getNotaFiscalVendaFutura(), po.getItens() );

			this.gerenciarPedido( hibernate, po.getNotaFiscalVendaFutura().getPedido(), po.getItens() );

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
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, NotaFiscalSimplesRemessaDTO dto ) throws ApplicationException {

		HibernateConnection hibernate = new HibernateConnection();

		try {
			String usuarioAlteracao = usuarioSessaoPOJO.getUsuario().getLogin();
			LocalDateTime dataAlteracao = LocalDateTime.now();

			hibernate.iniciarTransacao();

			NotaFiscalSimplesRemessaPO po = this.modelFactory.getPO( NotaFiscalSimplesRemessaPO.class, dto );
			po.setarDadosAuditoria( usuarioAlteracao, dataAlteracao );

			if ( po.getData().isBefore( po.getNotaFiscalVendaFutura().getData() ) ) {
				throw new ApplicationException( "A data da Nota Fiscal de Simples Remessa deve ser Maior ou Igual a data da Nota Fiscal Futura" );
			}

			/** Controlando o acesso concorrente do Item da Nota Futura de maneira a bloquear caso mais de um usuario queira lançar entrada do item e suas somas exceda a quantidade da Nota. */
			this.gerenciarAcessoConcorrenteEntreguaItensPedidos( po, po.getItens() );

			/** Deve-se Obrigatoriamente atualizar o Estoque antes de alterar a Nota de Entrega */
			this.daoInterface.alterar( hibernate, po );

			this.gerenciarNotaFiscalVenda( hibernate, po.getNotaFiscalVendaFutura(), po.getItens() );

			this.gerenciarPedido( hibernate, po.getNotaFiscalVendaFutura().getPedido(), po.getItens() );

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
	public void excluir( NotaFiscalSimplesRemessaDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {

			/** Pegando o PO passado na Requisicao */
			NotaFiscalSimplesRemessaPO po = this.modelFactory.getPO( NotaFiscalSimplesRemessaPO.class, dto );

			hibernate.iniciarTransacao();

			hibernate.excluir( po );

			this.gerenciarNotaFiscalVendaExcluir( hibernate, po );

			this.gerenciarPedidoExcluir( hibernate, po );
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
	public ArrayList< NotaFiscalSimplesRemessaDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, NotaFiscalSimplesRemessaDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException {

		try {
			NotaFiscalSimplesRemessaPO poFilter = this.modelFactory.getPO( NotaFiscalSimplesRemessaPO.class, dto );

			HashMap< String, ArrayList< Object > > camposBetween = new HashMap<>();

			if ( !Utilidades.isNuloOuVazio( dataInicialParam ) && !Utilidades.isNuloOuVazio( dataFinalParam ) ) {
				camposBetween.put( "data", new ArrayList< Object >( Arrays.asList( LocalDate.parse( dataInicialParam, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ), LocalDate.parse( dataFinalParam, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ) ) ) );
			}

			List< NotaFiscalSimplesRemessaPO > encontrados = this.daoInterface.filtrar( paginacao, camposOrders, camposBetween, poFilter );

			ArrayList< NotaFiscalSimplesRemessaDTO > dtosRetorno = new ArrayList<>();

			for ( NotaFiscalSimplesRemessaPO poCorrente : encontrados ) {
				dtosRetorno.add( this.modelFactory.getDTO( NotaFiscalSimplesRemessaDTO.class, poCorrente ) );
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
	public NotaFiscalSimplesRemessaDTO filtrarPorId( String id ) throws ApplicationException {

		try {

			if ( Utilidades.isNuloOuVazio( id ) ) {
				throw new ApplicationException( "Id não informado!" );
			}

			NotaFiscalSimplesRemessaPO encontrado = this.daoInterface.filtrarPorId( Long.valueOf( id ) );

			return this.modelFactory.getDTO( NotaFiscalSimplesRemessaDTO.class, encontrado );
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

	private void gerenciarPedido( HibernateConnection hibernate, PedidoPO pedido, Set< ItemNotaFiscalSimplesRemessaPO > itensVenda ) throws ApplicationException, ParseException {

		PedidoPO pedidoBD = (PedidoPO) new HibernateConnection().filtrarPorId( PedidoPO.class, pedido.getId() );

		PedidoServiceImpl service = new PedidoServiceImpl();

		String quantidadeItensRestantesAux = service.calcularValoresRestantesEntregarPedido( null, pedidoBD.getId().toString() );

		BigDecimal quantidadeItensRestantes = Utilidades.parseBigDecimal( quantidadeItensRestantesAux );

		BigDecimal quantidadeItensEntregues = BigDecimal.ZERO;
		for ( ItemNotaFiscalSimplesRemessaPO itemEntregueCorrente : itensVenda ) {

			ItemNotaFiscalSimplesRemessaPO itemEntregueBD = null;
			if ( !Utilidades.isNuloOuVazio( itemEntregueCorrente.getId() ) ) {
				itemEntregueBD = (ItemNotaFiscalSimplesRemessaPO) new HibernateConnection().filtrarPorId( ItemNotaFiscalSimplesRemessaPO.class, itemEntregueCorrente.getId() );
			}

			if ( !Utilidades.isNuloOuVazio( itemEntregueBD ) && ( itemEntregueBD.getQuantidade().compareTo( itemEntregueCorrente.getQuantidade() ) == 0 ) ) {
				continue;
			}

			quantidadeItensEntregues = quantidadeItensEntregues.add( itemEntregueCorrente.getQuantidade() );

			if ( !Utilidades.isNuloOuVazio( itemEntregueBD ) ) {
				quantidadeItensRestantes = quantidadeItensRestantes.add( itemEntregueBD.getQuantidade() );
			}
		}

		quantidadeItensRestantes = quantidadeItensRestantes.subtract( quantidadeItensEntregues );

		String statusAnterior = pedidoBD.getStatus();
		String statusAtual = "";

		LocalDateTime dataAlteracaoPedido = LocalDateTime.now();

		if ( Utilidades.isNuloOuVazio( quantidadeItensRestantes ) ) {
			pedidoBD.setStatus( PedidoDTO.STATUS_ABERTO );
			statusAtual = PedidoDTO.STATUS_ABERTO;
		} else if ( quantidadeItensRestantes.compareTo( BigDecimal.ZERO ) == 0 ) {
			pedidoBD.setStatus( PedidoDTO.STATUS_FINALIZADO );
			statusAtual = PedidoDTO.STATUS_FINALIZADO;
		} else {
			pedidoBD.setStatus( PedidoDTO.STATUS_ANDAMENTO );
			statusAtual = PedidoDTO.STATUS_ANDAMENTO;
		}

		if ( !statusAnterior.equalsIgnoreCase( statusAtual ) ) {
			pedidoBD.setObservacao( ( pedidoBD.getObservacao() == null ? "" : pedidoBD.getObservacao() ) + " | " + "Pedido " + statusAtual + " pelo usuário: [" + "" + "] em " + DateUtil.parseLocalDateTime( dataAlteracaoPedido ) );
			pedidoBD.setarDadosAuditoria( "", dataAlteracaoPedido );
			hibernate.alterar( pedidoBD );
		}

	}

	private void gerenciarPedidoExcluir( HibernateConnection hibernate, NotaFiscalSimplesRemessaPO notaFiscalSimplesRemessa ) throws ApplicationException, ParseException {

		PedidoPO pedidoBD = (PedidoPO) new HibernateConnection().filtrarPorId( PedidoPO.class, notaFiscalSimplesRemessa.getNotaFiscalVendaFutura().getPedido().getId() );

		PedidoServiceImpl service = new PedidoServiceImpl();

		// Pegando a quantidade total de itens do Pedido
		BigDecimal quantidadeTotalPedido = BigDecimal.ZERO;
		for ( ItemPedidoPO itemPedidoCorrente : pedidoBD.getItens() ) {
			quantidadeTotalPedido = quantidadeTotalPedido.add( itemPedidoCorrente.getQuantidade() );
		}

		// Pegando a quantidade total de itens da notaFiscalVenda
		BigDecimal quantidadeTotalNFVenda = BigDecimal.ZERO;
		for ( ItemNotaFiscalSimplesRemessaPO itemNFVendaCorrente : notaFiscalSimplesRemessa.getItens() ) {
			quantidadeTotalNFVenda = quantidadeTotalNFVenda.add( itemNFVendaCorrente.getQuantidade() );
		}

		String quantidadeItensRestantesAux = service.calcularValoresRestantesEntregarPedido( null, pedidoBD.getId().toString() );
		BigDecimal quantidadeItensRestantes = Utilidades.parseBigDecimal( quantidadeItensRestantesAux );

		String statusAnterior = pedidoBD.getStatus();
		String statusAtual = "";

		LocalDateTime dataAlteracaoPedido = LocalDateTime.now();

		if ( quantidadeItensRestantes.compareTo( quantidadeTotalPedido.subtract( quantidadeTotalNFVenda ) ) == 0 ) {
			pedidoBD.setStatus( PedidoDTO.STATUS_ABERTO );
			statusAtual = PedidoDTO.STATUS_ABERTO;
		} else {
			pedidoBD.setStatus( PedidoDTO.STATUS_ANDAMENTO );
			statusAtual = PedidoDTO.STATUS_ANDAMENTO;
		}

		if ( !statusAnterior.equalsIgnoreCase( statusAtual ) ) {
			pedidoBD.setObservacao( ( pedidoBD.getObservacao() == null ? "" : pedidoBD.getObservacao() ) + " | Exclusão da Nota Fiscal de Simples Remessa nº " + notaFiscalSimplesRemessa.getNumero() + ", Pedido com status atual [" + statusAtual + "] pelo usuário: [" + "" + "] em " + DateUtil.parseLocalDateTime( dataAlteracaoPedido ) );
			pedidoBD.setarDadosAuditoria( "", dataAlteracaoPedido );
			hibernate.alterar( pedidoBD );
		}
	}

	private void gerenciarNotaFiscalVenda( HibernateConnection hibernate, NotaFiscalVendaPO notaFiscalVendaFutura, Set< ItemNotaFiscalSimplesRemessaPO > itensVenda ) throws ApplicationException, ParseException {
		NotaFiscalVendaPO notaFiscalVendaFuturaBD = (NotaFiscalVendaPO) new HibernateConnection().filtrarPorId( NotaFiscalVendaPO.class, notaFiscalVendaFutura.getId() );

		PedidoServiceImpl serviceEstoque = new PedidoServiceImpl();

		String quantidadeItensRestantesAux = serviceEstoque.calcularValoresRestantesEntregarNFSimplesRemessa( null, notaFiscalVendaFuturaBD.getId().toString() );
		BigDecimal quantidadeItensRestantes = Utilidades.parseBigDecimal( quantidadeItensRestantesAux );

		BigDecimal quantidadeItensEntregues = BigDecimal.ZERO;
		for ( ItemNotaFiscalSimplesRemessaPO itemEntregueCorrente : itensVenda ) {

			ItemNotaFiscalSimplesRemessaPO itemEntregueBD = null;
			if ( !Utilidades.isNuloOuVazio( itemEntregueCorrente.getId() ) ) {
				itemEntregueBD = (ItemNotaFiscalSimplesRemessaPO) new HibernateConnection().filtrarPorId( ItemNotaFiscalSimplesRemessaPO.class, itemEntregueCorrente.getId() );
			}

			if ( !Utilidades.isNuloOuVazio( itemEntregueBD ) && ( itemEntregueBD.getQuantidade().compareTo( itemEntregueCorrente.getQuantidade() ) == 0 ) ) {
				continue;
			}

			quantidadeItensEntregues = quantidadeItensEntregues.add( itemEntregueCorrente.getQuantidade() );

			if ( !Utilidades.isNuloOuVazio( itemEntregueBD ) ) {
				quantidadeItensRestantes = quantidadeItensRestantes.add( itemEntregueBD.getQuantidade() );
			}
		}

		quantidadeItensRestantes = quantidadeItensRestantes.subtract( quantidadeItensEntregues );

		String statusAnterior = notaFiscalVendaFuturaBD.getStatus();
		String statusAtual = "";

		LocalDateTime dataAlteracaoNotaFiscalVenda = LocalDateTime.now();

		if ( Utilidades.isNuloOuVazio( quantidadeItensRestantes ) ) {
			notaFiscalVendaFuturaBD.setStatus( PedidoDTO.STATUS_ABERTO );
			statusAtual = PedidoDTO.STATUS_ABERTO;
		} else if ( quantidadeItensRestantes.compareTo( BigDecimal.ZERO ) == 0 ) {
			notaFiscalVendaFuturaBD.setStatus( PedidoDTO.STATUS_FINALIZADO );
			statusAtual = PedidoDTO.STATUS_FINALIZADO;
		} else {
			notaFiscalVendaFuturaBD.setStatus( PedidoDTO.STATUS_ANDAMENTO );
			statusAtual = PedidoDTO.STATUS_ANDAMENTO;
		}

		if ( !statusAnterior.equalsIgnoreCase( statusAtual ) ) {
			notaFiscalVendaFuturaBD.setObservacao( ( notaFiscalVendaFuturaBD.getObservacao() == null ? "" : notaFiscalVendaFuturaBD.getObservacao() ) + " | " + "NotaFiscalVenda " + statusAtual + " pelo usuário: [" + "" + "] em " + DateUtil.parseLocalDateTime( dataAlteracaoNotaFiscalVenda ) );
			notaFiscalVendaFuturaBD.setarDadosAuditoria( "", dataAlteracaoNotaFiscalVenda );
			hibernate.alterar( notaFiscalVendaFuturaBD );
		}
	}

	private void gerenciarNotaFiscalVendaExcluir( HibernateConnection hibernate, NotaFiscalSimplesRemessaPO notaFiscalSimplesRemessa ) throws ApplicationException, ParseException {

		NotaFiscalVendaPO notaFiscalVendaFuturaBD = (NotaFiscalVendaPO) new HibernateConnection().filtrarPorId( NotaFiscalVendaPO.class, notaFiscalSimplesRemessa.getNotaFiscalVendaFutura().getId() );

		PedidoServiceImpl serviceEstoque = new PedidoServiceImpl();

		// Pegando a quantidade total de itens do NotaFiscalVenda
		BigDecimal quantidadeTotalNotaFiscalVenda = BigDecimal.ZERO;
		for ( ItemNotaFiscalVendaPO itemNotaFiscalVendaCorrente : notaFiscalVendaFuturaBD.getItens() ) {
			quantidadeTotalNotaFiscalVenda = quantidadeTotalNotaFiscalVenda.add( itemNotaFiscalVendaCorrente.getQuantidade() );
		}

		// Pegando a quantidade total de itens da notaFiscalVenda
		BigDecimal quantidadeTotalNFSimplesRemessa = BigDecimal.ZERO;
		for ( ItemNotaFiscalSimplesRemessaPO itemNFSimpleRemessaCorrente : notaFiscalSimplesRemessa.getItens() ) {
			quantidadeTotalNFSimplesRemessa = quantidadeTotalNFSimplesRemessa.add( itemNFSimpleRemessaCorrente.getQuantidade() );
		}

		String quantidadeItensRestantesAux = serviceEstoque.calcularValoresRestantesEntregarNFSimplesRemessa( null, notaFiscalVendaFuturaBD.getId().toString() );
		BigDecimal quantidadeItensRestantes = Utilidades.parseBigDecimal( quantidadeItensRestantesAux );

		String statusAnterior = notaFiscalVendaFuturaBD.getStatus();
		String statusAtual = "";

		LocalDateTime dataAlteracaoNotaFiscalVenda = LocalDateTime.now();

		if ( quantidadeItensRestantes.compareTo( quantidadeTotalNotaFiscalVenda.subtract( quantidadeTotalNFSimplesRemessa ) ) == 0 ) {
			notaFiscalVendaFuturaBD.setStatus( PedidoDTO.STATUS_ABERTO );
			statusAtual = PedidoDTO.STATUS_ABERTO;
		} else {
			notaFiscalVendaFuturaBD.setStatus( PedidoDTO.STATUS_ANDAMENTO );
			statusAtual = PedidoDTO.STATUS_ANDAMENTO;
		}

		if ( !statusAnterior.equalsIgnoreCase( statusAtual ) ) {
			notaFiscalVendaFuturaBD.setObservacao( ( notaFiscalVendaFuturaBD.getObservacao() == null ? "" : notaFiscalVendaFuturaBD.getObservacao() ) + " | Exclusão da Nota Fiscal de Simples Remessa nº " + notaFiscalSimplesRemessa.getNumero() + ", NotaFiscalVenda com status atual [" + statusAtual + "] pelo usuário: [" + "" + "] em " + DateUtil.parseLocalDateTime( dataAlteracaoNotaFiscalVenda ) );
			notaFiscalVendaFuturaBD.setarDadosAuditoria( "", dataAlteracaoNotaFiscalVenda );
			hibernate.alterar( notaFiscalVendaFuturaBD );
		}
	}

	/**
	 * 
	 * Método responsável por Controlar o acesso concorrente do Item da Nota Futura de maneira a bloquear caso mais de um usuario queira lançar entrada do item e suas somas exceda a quantidade da
	 * Nota.
	 *
	 * 
	 * @author Gabriel Damiani Carvalheiro <gabriel.carvalheiro@gmail.com.br>
	 * @since 28 de mar de 2017 11:29:14
	 * @version 1.0
	 */
	private void gerenciarAcessoConcorrenteEntreguaItensPedidos( NotaFiscalSimplesRemessaPO notaSimplesRemessa, Set< ItemNotaFiscalSimplesRemessaPO > itensNotaSimplesRemessa ) throws ApplicationException {

		PedidoServiceImpl service = new PedidoServiceImpl();

		for ( ItemNotaFiscalSimplesRemessaPO itemEntregueNF : itensNotaSimplesRemessa ) {

			ItemNotaFiscalSimplesRemessaPO itemEntregueBD = null;
			if ( !Utilidades.isNuloOuVazio( itemEntregueNF.getId() ) ) {
				itemEntregueBD = (ItemNotaFiscalSimplesRemessaPO) new HibernateConnection().filtrarPorId( ItemNotaFiscalSimplesRemessaPO.class, itemEntregueNF.getId() );
			}

			if ( !Utilidades.isNuloOuVazio( itemEntregueBD ) && ( itemEntregueBD.getQuantidade().compareTo( itemEntregueNF.getQuantidade() ) == 0 ) ) {
				continue;
			}

			BigDecimal quantidadeJaEntregue = BigDecimal.ZERO;
			if ( !Utilidades.isNuloOuVazio( itemEntregueBD ) ) {
				quantidadeJaEntregue = itemEntregueBD.getQuantidade();
			}

			String quantidadeItensRestantesAux = service.calcularValoresRestantesEntregarNFSimplesRemessa( itemEntregueNF.getItemNotaFiscalVendaFutura().getItemPedido().getProduto().getId().toString(), notaSimplesRemessa.getNotaFiscalVendaFutura().getId().toString() );
			BigDecimal quantidadeItensRestantes = Utilidades.parseBigDecimal( quantidadeItensRestantesAux );

			//quantidadeItensRestantes = quantidadeItensRestantes.subtract( itemEntregueNF.getQuantidade() );
			quantidadeItensRestantes = quantidadeItensRestantes.add( quantidadeJaEntregue ).subtract( itemEntregueNF.getQuantidade() );

			if ( quantidadeItensRestantes.compareTo( BigDecimal.ZERO ) < 0 ) {
				throw new ApplicationException( "O Item " + itemEntregueNF.getItemNotaFiscalVendaFutura().getItemPedido().getProduto().getNome() + " possui uma quantidade de entrega maior que a pedida/restante." );
			}
		}
	}

	@Override
	public String getTotalFaltaEntregar( String idProduto, String idNotaFutura ) throws ApplicationException {
		try {
			PedidoServiceImpl serviceEstoque = new PedidoServiceImpl();

			String quantidadeItensRestantes = serviceEstoque.calcularValoresRestantesEntregarNFSimplesRemessa( idProduto, idNotaFutura );

			return quantidadeItensRestantes;
		} catch ( ApplicationException e ) {
			this.logger.error( MensagensResourcesApplication.getString( "mensagem", new String[ ] { e.getMessage() } ) );
			throw e;
		} catch ( Exception e ) {
			this.logger.error( MensagensResourcesApplication.getString( "mensagem", new String[ ] { e.getMessage() } ) );
			throw new ApplicationException( "Erro ao ocorrido: " + e.getMessage(), e );
		}
	}

	@Override
	public void adicionarItemRecebido( NotaFiscalSimplesRemessaDTO notaFiscalSimplesRemessa, ArrayList< ItemNotaFiscalVendaDTO > itensNotaFiscalRestantes, String idItemNotaFiscalReceber, String quantidadeItensAdicionar ) throws ApplicationException {

		try {

			/** Pegando o item de pedido que foi selecionado como recebido */
			ItemNotaFiscalVendaDTO itemNotaFiscalEntregar = new ItemNotaFiscalVendaDTO();
			for ( ItemNotaFiscalVendaDTO itemNotaFiscalCorrente : itensNotaFiscalRestantes ) {
				if ( idItemNotaFiscalReceber.equals( itemNotaFiscalCorrente.getId() ) ) {
					itemNotaFiscalEntregar = itemNotaFiscalCorrente;
					break;
				}
			}

			boolean jaAdicionado = false;
			if ( !Utilidades.isNuloOuVazio( notaFiscalSimplesRemessa.getItens() ) ) {
				for ( ItemNotaFiscalSimplesRemessaDTO itemSimplesRemessaEntregueCorrente : notaFiscalSimplesRemessa.getItens() ) {
					if ( idItemNotaFiscalReceber.equals( itemSimplesRemessaEntregueCorrente.getItemNotaFiscalVendaFutura().getId() ) ) {

						BigDecimal quantidadeRestante = BigDecimal.ZERO;
						quantidadeRestante = Utilidades.parseBigDecimal( itemNotaFiscalEntregar.getQuantidadeRestante() );
						quantidadeRestante = quantidadeRestante.subtract( Utilidades.parseBigDecimal( quantidadeItensAdicionar ) );
						if ( quantidadeRestante.compareTo( BigDecimal.ZERO ) <= 0 ) {
							itensNotaFiscalRestantes.remove( itemNotaFiscalEntregar );

							/** Seta como Quantidade total de itens entregues como sendo a quantidade de itens do Pedido */
							BigDecimal quantidadeTotalEntregue = Utilidades.parseBigDecimal( itemNotaFiscalEntregar.getQuantidadeRestante() ).add( Utilidades.parseBigDecimal( itemSimplesRemessaEntregueCorrente.getQuantidade() ) );
							itemSimplesRemessaEntregueCorrente.setQuantidade( Utilidades.parseBigDecimal( quantidadeTotalEntregue ) );

						} else {
							BigDecimal quantidadeTotalEntregue = Utilidades.parseBigDecimal( itemSimplesRemessaEntregueCorrente.getQuantidade() ).add( Utilidades.parseBigDecimal( quantidadeItensAdicionar ) );
							itemSimplesRemessaEntregueCorrente.setQuantidade( Utilidades.parseBigDecimal( quantidadeTotalEntregue ) );

							itemNotaFiscalEntregar.setQuantidadeRestante( Utilidades.parseBigDecimal( quantidadeRestante ) );
						}

						jaAdicionado = true;
						break;
					}
				}
			}

			if ( !jaAdicionado ) {
				ItemNotaFiscalSimplesRemessaDTO itemSimplesRemessaEntregue = new ItemNotaFiscalSimplesRemessaDTO();
				itemSimplesRemessaEntregue.setItemNotaFiscalVendaFutura( itemNotaFiscalEntregar );
				itemSimplesRemessaEntregue.setQuantidade( quantidadeItensAdicionar );
				itemSimplesRemessaEntregue.setPrecoCusto( itemNotaFiscalEntregar.getPrecoCusto() );

				BigDecimal quantidadeRestante = BigDecimal.ZERO;
				quantidadeRestante = Utilidades.parseBigDecimal( itemNotaFiscalEntregar.getQuantidadeRestante() );
				quantidadeRestante = quantidadeRestante.subtract( Utilidades.parseBigDecimal( quantidadeItensAdicionar ) );

				/** Caso A quantidade a ser adicionada seja igual ou maior que a quantidade de itens do pedido */
				if ( quantidadeRestante.compareTo( BigDecimal.ZERO ) <= 0 ) {

					/** Remove da lista de Itens de pedidos */
					itensNotaFiscalRestantes.remove( itemNotaFiscalEntregar );

					/** Seta como Quantidade total de itens entregues como sendo a quantidade de itens do Pedido */
					itemSimplesRemessaEntregue.setQuantidade( itemNotaFiscalEntregar.getQuantidadeRestante() );
				} else {
					itemSimplesRemessaEntregue.setQuantidade( quantidadeItensAdicionar );

					itemNotaFiscalEntregar.setQuantidadeRestante( Utilidades.parseBigDecimal( quantidadeRestante ) );
				}

				notaFiscalSimplesRemessa.getItens().add( itemSimplesRemessaEntregue );
			}
		} catch ( Exception e ) {
			this.logger.error( MensagensResourcesApplication.getString( "mensagem", new String[ ] { e.getMessage() } ) );
			throw new ApplicationException( "Erro ao ocorrido: " + e.getMessage(), e );
		}
	}

	@Override
	public void removerItemRecebido( NotaFiscalSimplesRemessaDTO notaFiscalSimplesRemessa, ArrayList< ItemNotaFiscalVendaDTO > itensNotaFiscalRestantes, String idItemNotaFiscalRetornar, String quantidadeItensRetornar ) throws ApplicationException {
		try {

			/** Pegando o item de pedido que foi selecionado como recebido */
			ItemNotaFiscalSimplesRemessaDTO itemSimplesRemessaEntregueSelecionado = new ItemNotaFiscalSimplesRemessaDTO();
			for ( ItemNotaFiscalSimplesRemessaDTO itemSimplesRemessaEntregueCorrente : notaFiscalSimplesRemessa.getItens() ) {
				if ( idItemNotaFiscalRetornar.equals( itemSimplesRemessaEntregueCorrente.getItemNotaFiscalVendaFutura().getId() ) ) {
					itemSimplesRemessaEntregueSelecionado = itemSimplesRemessaEntregueCorrente;
					break;
				}
			}

			boolean jaAdicionado = false;
			if ( !Utilidades.isNuloOuVazio( itensNotaFiscalRestantes ) ) {
				for ( ItemNotaFiscalVendaDTO itemNotaFiscalEntregarCorrente : itensNotaFiscalRestantes ) {
					if ( itemSimplesRemessaEntregueSelecionado.getItemNotaFiscalVendaFutura().getId().equals( itemNotaFiscalEntregarCorrente.getId() ) ) {

						BigDecimal quantidadeEntregue = BigDecimal.ZERO;
						quantidadeEntregue = Utilidades.parseBigDecimal( itemSimplesRemessaEntregueSelecionado.getQuantidade() );
						quantidadeEntregue = quantidadeEntregue.subtract( Utilidades.parseBigDecimal( quantidadeItensRetornar ) );
						if ( quantidadeEntregue.compareTo( BigDecimal.ZERO ) <= 0 ) {
							notaFiscalSimplesRemessa.getItens().remove( itemSimplesRemessaEntregueSelecionado );

							BigDecimal quantidadeTotalDevolvido = Utilidades.parseBigDecimal( itemNotaFiscalEntregarCorrente.getQuantidadeRestante() ).add( Utilidades.parseBigDecimal( itemSimplesRemessaEntregueSelecionado.getQuantidade() ) );
							itemNotaFiscalEntregarCorrente.setQuantidade( Utilidades.parseBigDecimal( quantidadeTotalDevolvido ) );
							itemNotaFiscalEntregarCorrente.setQuantidadeRestante( itemNotaFiscalEntregarCorrente.getQuantidade() );

						} else {

							BigDecimal quantidadeTotalDevolvido = Utilidades.parseBigDecimal( itemNotaFiscalEntregarCorrente.getQuantidadeRestante() ).add( Utilidades.parseBigDecimal( quantidadeItensRetornar ) );
							itemNotaFiscalEntregarCorrente.setQuantidadeRestante( Utilidades.parseBigDecimal( quantidadeTotalDevolvido ) );

							itemSimplesRemessaEntregueSelecionado.setQuantidade( Utilidades.parseBigDecimal( quantidadeEntregue ) );
						}

						jaAdicionado = true;
						break;
					}
				}
			}

			if ( !jaAdicionado ) {
				ItemNotaFiscalVendaDTO itemNotaFiscalEntregar = new ItemNotaFiscalVendaDTO();
				itemNotaFiscalEntregar.setId( itemSimplesRemessaEntregueSelecionado.getItemNotaFiscalVendaFutura().getId() );
				itemNotaFiscalEntregar.setItemPedido( itemSimplesRemessaEntregueSelecionado.getItemNotaFiscalVendaFutura().getItemPedido() );
				itemNotaFiscalEntregar.setQuantidade( itemSimplesRemessaEntregueSelecionado.getQuantidade() );
				itemNotaFiscalEntregar.setPrecoCusto( itemSimplesRemessaEntregueSelecionado.getPrecoCusto() );

				BigDecimal quantidadeEntregue = BigDecimal.ZERO;
				quantidadeEntregue = Utilidades.parseBigDecimal( itemSimplesRemessaEntregueSelecionado.getQuantidade() );
				quantidadeEntregue = quantidadeEntregue.subtract( Utilidades.parseBigDecimal( quantidadeItensRetornar ) );
				if ( quantidadeEntregue.compareTo( BigDecimal.ZERO ) <= 0 ) {
					notaFiscalSimplesRemessa.getItens().remove( itemSimplesRemessaEntregueSelecionado );

					itemNotaFiscalEntregar.setQuantidade( itemSimplesRemessaEntregueSelecionado.getQuantidade() );
					itemNotaFiscalEntregar.setQuantidadeRestante( itemNotaFiscalEntregar.getQuantidade() );
				} else {
					BigDecimal quantidadeTotalDevolvido = Utilidades.parseBigDecimal( quantidadeItensRetornar );//Utilidades.parseBigDecimal( itemEntregar.getQuantidadeRestante() ).add( Utilidades.parseBigDecimal( quantidadeItensRetornar ) );
					itemNotaFiscalEntregar.setQuantidadeRestante( Utilidades.parseBigDecimal( quantidadeTotalDevolvido ) );
					itemSimplesRemessaEntregueSelecionado.setQuantidade( Utilidades.parseBigDecimal( quantidadeEntregue ) );
				}

				itensNotaFiscalRestantes.add( itemNotaFiscalEntregar );
			}

		} catch ( Exception e ) {
			this.logger.error( MensagensResourcesApplication.getString( "mensagem", new String[ ] { e.getMessage() } ) );
			throw new ApplicationException( "Erro ao ocorrido: " + e.getMessage(), e );
		}
	}
}
