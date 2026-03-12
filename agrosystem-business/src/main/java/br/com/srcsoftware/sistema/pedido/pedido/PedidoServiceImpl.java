package br.com.srcsoftware.sistema.pedido.pedido;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.factory.ModelFactory;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.exceptions.ModelFactoryException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;
import br.com.srcsoftware.modular.manager.utilidades.MensagensResourcesApplication;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.estoque.estoque.EstoqueDAOInterface;
import br.com.srcsoftware.sistema.pedido.itempedido.ItemPedidoDTO;
import br.com.srcsoftware.sistema.pedido.itempedido.ItemPedidoPO;
import br.com.srcsoftware.sistema.pessoa.fornecedor.juridico.FornecedorFacade;
import br.com.srcsoftware.sistema.pessoa.fornecedor.juridico.FornecedorJuridicoDTO;
import br.com.srcsoftware.sistema.pessoa.fornecedor.juridico.FornecedorJuridicoPO;
import br.com.srcsoftware.sistema.produto.produto.ProdutoDTO;
import br.com.srcsoftware.sistema.produto.produto.ProdutoFacade;

public final class PedidoServiceImpl implements PedidoServiceInterface{

	private transient Logger logger = Logger.getLogger( this.getClass().getName() );

	private PedidoDAOInterface daoInterface;

	private ModelFactory< PedidoPO, PedidoDTO > modelFactory = new ModelFactory<>();

	public PedidoServiceImpl(){
		this.daoInterface = new PedidoDAOImpl();
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, PedidoDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			PedidoPO po = this.modelFactory.getPO( PedidoPO.class, dto );
			po.setarDadosAuditoria( usuarioSessaoPOJO.getUsuario().getLogin(), LocalDateTime.now() );

			if ( Utilidades.isNuloOuVazio( dto.getItens() ) ) {
				throw new ApplicationException( "Por favor informe os itens do NotaFiscalVendaDireta!" );
			}

			this.gerenciarFornecedor( hibernate, po );

			/*for ( ItemPedidoPO itemCorrente : po.getItens() ) {
				this.gerenciarProdutos( hibernate, requisicao, itemCorrente, requisicao.getUsuario() );
			}*/

			dto.setId( this.daoInterface.inserir( hibernate, po ).getId().toString() );

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
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, PedidoDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			PedidoPO po = this.modelFactory.getPO( PedidoPO.class, dto );
			po.setarDadosAuditoria( usuarioSessaoPOJO.getUsuario().getLogin(), LocalDateTime.now() );

			if ( Utilidades.isNuloOuVazio( dto.getItens() ) ) {
				throw new ApplicationException( "Por favor informe os itens do NotaFiscalVendaDireta!" );
			}

			this.gerenciarStatusPedido( ( dto ) );

			/*for ( ItemPedidoPO itemCorrente : po.getItens() ) {
				this.gerenciarProdutos( hibernateConnection, requisicao, itemCorrente, requisicao.getUsuario() );
			}*/

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

	/*@SuppressWarnings( "unchecked" )
	private void gerenciarProdutos( HibernateConnection hibernateConnection,ItemPedidoPO itemCorrente, String usuario ) throws ApplicationException {
	
		List< ProdutoPO > produtoEncontrado = new HibernateConnection().filtrar( Paginacao.NAO, ProdutoPO.class, HibernateConnection.CAMPOS_TRAZER_TODOS, HibernateConnectionDAO.SEM_ORDENACAO, requisicao.getCamposBetween(), itemCorrente.getProduto() );
		if ( !Utilidades.isNuloOuVazio( produtoEncontrado ) ) {
			itemCorrente.getProduto().setId( produtoEncontrado.get( 0 ).getId() );
			itemCorrente.getProduto().setNome( null );
			itemCorrente.setProduto( produtoEncontrado.get( 0 ) );// fiz agora
		} else {
			if ( !Utilidades.isNuloOuVazio( itemCorrente.getProduto().getMarca().getId() ) ) {
				itemCorrente.getProduto().setMarca( (MarcaPO) new HibernateConnectionDAO().filtrarPorId( MarcaPO.class, itemCorrente.getProduto().getMarca().getId() ) );
			}
	
			if ( !Utilidades.isNuloOuVazio( itemCorrente.getProduto().getUnidadeMedida().getId() ) ) {
				itemCorrente.getProduto().setUnidadeMedida( (UnidadeMedidaPO) new HibernateConnectionDAO().filtrarPorId( UnidadeMedidaPO.class, itemCorrente.getProduto().getUnidadeMedida().getId() ) );
			}
			itemCorrente.getProduto().setarDadosAuditoria( usuario, new Date() );
			itemCorrente.setProduto( (ProdutoPO) hibernateConnection.inserir( itemCorrente.getProduto() ) );
		}
	}*/

	/**
	 * Método responsável Definir o Status do Pedido baseado nos Item Pedidos em relação aos Itens Entregues
	 *
	 * @param pedidoDTO
	 *
	 * @author Gabriel Damiani Carvalheiro <gabriel.carvalheiro@gmail.com.br>
	 * @since 27 de fev de 2017 18:27:05
	 * @version 1.0
	 * @throws ApplicationException
	 * @throws NumberFormatException
	 */
	private void gerenciarStatusPedido( PedidoDTO pedidoDTO ) throws NumberFormatException, ApplicationException {
		/*for ( ItemPedidoDTO itemCorrente : pedidoDTO.getItens() ) {
			BigDecimal totalRestante = Utilidades.parseBigDecimal( itemCorrente.getQuantidadeRestante() );
			BigDecimal quantidade = Utilidades.parseBigDecimal( itemCorrente.getQuantidade() );
		
			
			if ( !Utilidades.isNuloOuVazio( totalRestante ) ) {
				if ( ( totalRestante.compareTo( BigDecimal.ZERO ) != 0 ) && ( totalRestante.compareTo( quantidade ) != 0 ) ) {
					pedidoDTO.setStatus( PedidoDTO.STATUS_ANDAMENTO );
				}
			}
		}*/

		for ( ItemPedidoDTO itemCorrente : pedidoDTO.getItens() ) {
			if ( Utilidades.isNuloOuVazio( itemCorrente.getId() ) ) {
				pedidoDTO.setStatus( PedidoDTO.STATUS_ANDAMENTO );
				continue;
			}

			ItemPedidoPO itemPedidoBD = (ItemPedidoPO) new HibernateConnection().filtrarPorId( ItemPedidoPO.class, Long.valueOf( itemCorrente.getId() ) );

			if ( ( Utilidades.isNuloOuVazio( itemPedidoBD ) || ( itemPedidoBD.getQuantidade().compareTo( Utilidades.parseBigDecimal( itemCorrente.getQuantidade() ) ) != 0 ) ) ) {
				pedidoDTO.setStatus( PedidoDTO.STATUS_ANDAMENTO );
			}
		}
	}

	/*private void limparIdsTemporarios( ArrayList< ItemPedidoDTO > itens ) {
		for ( ItemPedidoDTO itemCorrente : itens ) {
			if ( Utilidades.isNuloOuVazio( itemCorrente.getId() ) ) {
				continue;
			}
			if ( itemCorrente.getId().contains( "temp" ) ) {
				itemCorrente.setId( null );
			}
		}
	}*/

	/*private void atualizarEstoque( HibernateConnectionDAO hibernateConnection, String idUsuario, String loginUsuario, PedidoPO pedido, String tipoMovimento ) throws DadosInvalidosException, ApplicationException {
	
		ArrayList< EstoqueDTO > produtosEstoque = new ArrayList< EstoqueDTO >();
	
		ModelFactory< EstoquePO, EstoqueDTO > modelFactoryEstoque = new ModelFactory< EstoquePO, EstoqueDTO >();
	
		for ( ItemPedidoPO itemCorrente : pedido.getItens() ) {
	
			EstoquePO estoque = new EstoquePO();
			estoque.setCustoTotal( itemCorrente.getPrecoCusto().multiply( itemCorrente.getQuantidade() ).setScale( 2, BigDecimal.ROUND_HALF_EVEN ) );
			estoque.setProduto( itemCorrente.getProduto() );
			estoque.setQuantidade( itemCorrente.getQuantidade() );
	
			produtosEstoque.add( modelFactoryEstoque.getDTO( EstoqueDTO.class, estoque ) );
	
		}
	
		RetornoServico retorno = EstoqueFacade.getInstance().atualizarEstoque( hibernateConnection, produtosEstoque, tipoMovimento );
	
		// Verificando se deu erro.
		if ( !Utilidades.isNuloOuVazio( retorno.erro ) ) {
			throw retorno.erro;
		}
	}*/

	@Override
	public void excluir( PedidoDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			PedidoPO po = this.modelFactory.getPO( PedidoPO.class, dto );

			this.daoInterface.excluir( hibernate, po );

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

	@SuppressWarnings( "unchecked" )
	private void gerenciarFornecedor( HibernateConnection hibernateConnection, PedidoPO pedido ) throws ApplicationException, ModelFactoryException {
		if ( Utilidades.isNuloOuVazio( pedido.getFornecedor().getNome() ) ) {

			ModelFactory< FornecedorJuridicoPO, FornecedorJuridicoDTO > modelFactoryFornecedor = new ModelFactory<>();

			FornecedorJuridicoDTO fornecedorFilter = new FornecedorJuridicoDTO();
			fornecedorFilter.setNome( "Fornecedor padrão" );

			List< FornecedorJuridicoDTO > encontrados = FornecedorFacade.getInstance().filtrar( Paginacao.NAO, HibernateConnection.SEM_ORDENACAO, fornecedorFilter );

			List< FornecedorJuridicoPO > fornecedoresEncontrado = new ArrayList<>();

			for ( FornecedorJuridicoDTO poCorrente : encontrados ) {
				FornecedorJuridicoPO encontrado = modelFactoryFornecedor.getPO( FornecedorJuridicoPO.class, poCorrente );

				fornecedoresEncontrado.add( encontrado );
			}

			if ( Utilidades.isNuloOuVazio( fornecedoresEncontrado ) ) {
				FornecedorJuridicoPO fornecedorInserir = new FornecedorJuridicoPO();
				fornecedorInserir.setNome( "Fornecedor padrão" );
				/*fornecedorInserir.setCelular( "(00)0000-00000" );
				fornecedorInserir.setFixo( "(00)0000-00000" );*/
				fornecedorInserir.setTelefone( "(00)0000-00000" );
				fornecedorInserir.setEndereco( "Endereço padrão" );
				pedido.setFornecedor( (FornecedorJuridicoPO) hibernateConnection.inserir( fornecedorInserir ) );
			} else {
				pedido.setFornecedor( fornecedoresEncontrado.get( 0 ) );
			}
		}
	}

	@Override
	public ArrayList< PedidoDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, PedidoDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException {

		try {
			PedidoPO poFilter = this.modelFactory.getPO( PedidoPO.class, dto );

			HashMap< String, ArrayList< Object > > camposBetween = new HashMap<>();

			if ( !Utilidades.isNuloOuVazio( dataInicialParam ) && !Utilidades.isNuloOuVazio( dataFinalParam ) ) {
				camposBetween.put( "data", new ArrayList< Object >( Arrays.asList( LocalDate.parse( dataInicialParam, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ), LocalDate.parse( dataFinalParam, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ) ) ) );
			}

			List< PedidoPO > encontrados = this.daoInterface.filtrar( paginacao, camposOrders, camposBetween, poFilter );

			ArrayList< PedidoDTO > dtosRetorno = new ArrayList<>();

			for ( PedidoPO poCorrente : encontrados ) {

				PedidoDTO dtoRetorno = this.modelFactory.getDTO( PedidoDTO.class, poCorrente );

				dtoRetorno.setQuantidadeRestante( this.calcularValoresRestantesEntregarPedido( null, dtoRetorno.getId() ) );

				dtosRetorno.add( dtoRetorno );
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
	public PedidoDTO filtrarPorId( String id ) throws ApplicationException {

		try {

			if ( Utilidades.isNuloOuVazio( id ) ) {
				throw new ApplicationException( "Id não informado!" );
			}

			PedidoPO encontrado = this.daoInterface.filtrarPorId( Long.valueOf( id ) );

			PedidoDTO dtoRetorno = this.modelFactory.getDTO( PedidoDTO.class, encontrado );

			// Valida a Permissão de se emitir NF
			dtoRetorno.setQuantidadeRestante( this.calcularValoresRestantesEntregarPedido( null, dtoRetorno.getId() ) );
			if ( BigDecimal.ZERO.compareTo( Utilidades.parseBigDecimal( dtoRetorno.getQuantidadeRestante() ) ) != 0 ) {
				dtoRetorno.setPermiteEmitirNota( true );
			} else {
				dtoRetorno.setPermiteEmitirNota( false );
			}

			// Calculando a quantidade Restante de cada Item do Pedido
			for ( ItemPedidoDTO itemCorrente : dtoRetorno.getItens() ) {
				itemCorrente.setQuantidadeRestante( this.calcularValoresRestantesEntregarPedido( itemCorrente.getProduto().getId(), dtoRetorno.getId() ) );
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

	@Override
	public List< PedidoDTO > filtrarAbertosAndamentos( String numeroPedido, HashMap< String, String > camposOrders ) throws ApplicationException {

		List< PedidoDTO > encontradosDTO = new ArrayList<>();

		try {

			List< PedidoPO > encontrados = this.daoInterface.filtrarAbertosAndamentos( numeroPedido, camposOrders );

			for ( PedidoPO poCorrente : encontrados ) {
				PedidoDTO encontrado = this.modelFactory.getDTO( PedidoDTO.class, poCorrente );
				encontrado.setQuantidadeRestante( this.calcularValoresRestantesEntregarEstoque( null, encontrado.getId() ) );

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
	public void adicionarItem( ItemPedidoDTO item, PedidoDTO pedido ) throws ApplicationException {

		try {

			if ( Utilidades.isNuloOuVazio( item.getQuantidade() ) ) {
				item.setQuantidade( "1" );
			}

			ProdutoDTO produtoAdicionar = ProdutoFacade.getInstance().filtrarPorId( item.getProduto().getId() );
			item.setProduto( produtoAdicionar );

			pedido.getItens().add( item );
		} catch ( ApplicationException e ) {
			this.logger.error( MensagensResourcesApplication.getString( "mensagem", new String[ ] { e.getMessage() } ) );
			throw new ApplicationException( "Erro ao ocorrido: " + e.getMessage(), e );
		}
	}

	@Override
	public void removerItem( PedidoDTO pedido, String idItem ) throws ApplicationException {
		try {

			for ( ItemPedidoDTO itemPedidoCorrente : pedido.getItens() ) {
				if ( idItem.equals( itemPedidoCorrente.getIdTemp() ) ) {
					pedido.getItens().remove( itemPedidoCorrente );
					break;
				}
			}

		} catch ( Exception e ) {
			this.logger.error( MensagensResourcesApplication.getString( "mensagem", new String[ ] { e.getMessage() } ) );
			throw new ApplicationException( "Erro ao ocorrido: " + e.getMessage(), e );
		}
	}

	/**
	 * Usado na tela de pedidos e nos itens do pedido.
	 * 
	 * Polimorfico
	 * 
	 * @see br.com.srcsoftware.gestao.sistema.estoque.estoque.EstoqueServiceInterface#calcularValoresRestantesEntregarPedido(java.lang.Long, java.lang.Long)
	 */
	@Override
	public String calcularValoresRestantesEntregarPedido( String idProduto, String idPedido ) throws ApplicationException {
		return this.calcularValoresRestantesEntregarEstoque( idProduto, idPedido );
	}

	/**
	 * Polimorfico
	 * 
	 * @see br.com.srcsoftware.gestao.sistema.estoque.estoque.EstoqueServiceInterface#calcularValoresRestantesEntregarPedido(java.lang.Long)
	 */
	@Override
	public String calcularValoresRestantesEntregarPedido( String idPedido ) throws ApplicationException {
		return this.calcularValoresRestantesEntregar( null, idPedido, null, EstoqueDAOInterface.CONSIDERAR_FUTURA_SIM );
	}

	@Override
	public String calcularValoresRestantesEntregarNFVendaNFFutura( String idProduto, String idPedido ) throws ApplicationException {
		return this.calcularValoresRestantesEntregar( idProduto, idPedido, null, EstoqueDAOInterface.CONSIDERAR_FUTURA_SIM );
	}

	@Override
	public String calcularValoresRestantesEntregarNFSimplesRemessa( String idProduto, String idNotaFiscalVenda ) throws ApplicationException {
		return this.calcularValoresRestantesEntregarSimplesRemessa( idProduto, idNotaFiscalVenda );
	}

	@Override
	public String calcularValoresRestantesEntregarPedido( String idPedido, boolean considerarFutura ) throws ApplicationException {
		return this.calcularValoresRestantesEntregar( null, idPedido, null, considerarFutura );
	}

	@Override
	public String calcularValoresRestantesEntregarEstoque( String idProduto ) throws ApplicationException {
		return this.calcularValoresRestantesEntregarEstoque( idProduto, null );
	}

	private String calcularValoresRestantesEntregarEstoque( String idProduto, String idPedido ) throws ApplicationException {
		try {

			Long idProdutoLong = null;
			Long idPedidoLong = null;

			if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
				idProdutoLong = Long.valueOf( idProduto );
			}
			if ( !Utilidades.isNuloOuVazio( idPedido ) ) {
				idPedidoLong = Long.valueOf( idPedido );
			}

			BigDecimal somatoriaValor = this.daoInterface.calcularValoresRestantesEntregar( idProdutoLong, idPedidoLong );

			return Utilidades.parseBigDecimal( somatoriaValor );
		} catch ( ApplicationException e ) {
			this.logger.error( e.getMessage(), e );
			throw e;
		} catch ( Exception e ) {
			this.logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}

	}

	private String calcularValoresRestantesEntregar( String idProduto, String idPedido, String idNotaFiscalVenda, boolean considerarFutura ) throws ApplicationException {
		try {

			Long idProdutoLong = null;
			Long idPedidoLong = null;
			Long idNotaFiscalVendaLong = null;

			if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
				idProdutoLong = Long.valueOf( idProduto );
			}
			if ( !Utilidades.isNuloOuVazio( idPedido ) ) {
				idPedidoLong = Long.valueOf( idPedido );
			}
			if ( !Utilidades.isNuloOuVazio( idNotaFiscalVenda ) ) {
				idNotaFiscalVendaLong = Long.valueOf( idNotaFiscalVenda );
			}

			BigDecimal somatoriaValor = this.daoInterface.calcularValoresRestantesEntregar( idProdutoLong, idPedidoLong, idNotaFiscalVendaLong, considerarFutura );

			return Utilidades.parseBigDecimal( somatoriaValor );
		} catch ( ApplicationException e ) {
			this.logger.error( e.getMessage(), e );
			throw e;
		} catch ( Exception e ) {
			this.logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}

	}

	private String calcularValoresRestantesEntregarSimplesRemessa( String idProduto, String idNotaFiscalVenda ) throws ApplicationException {
		try {

			Long idProdutoLong = null;

			Long idNotaFiscalVendaLong = null;

			if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
				idProdutoLong = Long.valueOf( idProduto );
			}

			if ( !Utilidades.isNuloOuVazio( idNotaFiscalVenda ) ) {
				idNotaFiscalVendaLong = Long.valueOf( idNotaFiscalVenda );
			}

			BigDecimal somatoriaValor = this.daoInterface.calcularValoresRestantesEntregarSimplesRemessa( idProdutoLong, idNotaFiscalVendaLong );

			return Utilidades.parseBigDecimal( somatoriaValor );
		} catch ( ApplicationException e ) {
			this.logger.error( e.getMessage(), e );
			throw e;
		} catch ( Exception e ) {
			this.logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}

	}

}
