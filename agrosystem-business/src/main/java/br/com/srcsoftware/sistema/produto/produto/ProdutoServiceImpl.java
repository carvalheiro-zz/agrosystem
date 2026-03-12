package br.com.srcsoftware.sistema.produto.produto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.factory.ModelFactory;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.exceptions.ModelFactoryException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.estoque.estoque.EstoqueDAOImpl;
import br.com.srcsoftware.sistema.estoque.estoque.EstoqueDAOInterface;

public class ProdutoServiceImpl implements ProdutoServiceInterface{

	private transient Logger logger = Logger.getLogger( this.getClass().getName() );

	private ProdutoDAOInterface daoInterface;
	private EstoqueDAOInterface daoEstoqueInterface;

	private ModelFactory< ProdutoPO, ProdutoDTO > modelFactory = new ModelFactory<>();

	public ProdutoServiceImpl(){
		this.daoInterface = new ProdutoDAOImpl();
		this.daoEstoqueInterface = new EstoqueDAOImpl();
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, ProdutoDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			dto.montarNomeCompleto();
			dto.ajustarObjetosParaRelacionamentos();

			ProdutoPO po = this.modelFactory.getPO( ProdutoPO.class, dto );
			po.setarDadosAuditoria( usuarioSessaoPOJO.getUsuario().getLogin(), LocalDateTime.now() );

			/*po.setUnidadeMedida( new UnidadeMedidaPO() );
			po.getUnidadeMedida().setId( Long.valueOf( dto.getUnidadeMedida().getId() ) );
			
			po.setMarca( new MarcaPO() );
			po.getMarca().setId( Long.valueOf( dto.getMarca().getId() ) );
			
			po.setTipo( new TipoPO() );
			po.getTipo().setId( Long.valueOf( dto.getTipo().getId() ) );*/

			//gerenciarMarca( hibernate, po );
			//gerenciarUnidadeMedida( hibernate, po );

			dto.setId( this.daoInterface.inserir( hibernate, po ).getId().toString() );

			//this.atualizarEstoque( hibernate, po );

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

	/*private void atualizarEstoque( HibernateConnection hibernateConnection, ProdutoPO produto ) throws ApplicationException, ModelFactoryException {
	
		ArrayList< EstoqueDTO > produtosEstoque = new ArrayList<>();
	
		ModelFactory< EstoquePO, EstoqueDTO > modelFactoryEstoque = new ModelFactory<>();
	
		BigDecimal quantidade = BigDecimal.ZERO;
		BigDecimal custoTotal = BigDecimal.ZERO;
	
		EstoquePO estoque = new EstoquePO();
		estoque.setCustoTotal( custoTotal );
		estoque.setProduto( produto );
		estoque.setQuantidade( quantidade );
	
		produtosEstoque.add( modelFactoryEstoque.getDTO( EstoqueDTO.class, estoque ) );
	
		EstoqueFacade.getInstance().atualizarEstoque( hibernateConnection, produtosEstoque, EstoqueDTO.TIPO_MOVIMENTO_ENTRADA );
	}*/

	@Override
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, ProdutoDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			dto.montarNomeCompleto();
			dto.ajustarObjetosParaRelacionamentos();

			ProdutoPO po = this.modelFactory.getPO( ProdutoPO.class, dto );
			po.setarDadosAuditoria( usuarioSessaoPOJO.getUsuario().getLogin(), LocalDateTime.now() );

			//gerenciarMarca( hibernate, po );
			//gerenciarUnidadeMedida( hibernate, po );

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

	@Override
	public void excluir( ProdutoDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			ProdutoPO po = this.modelFactory.getPO( ProdutoPO.class, dto );

			//EstoqueDTO estoque = EstoqueFacade.getInstance().filtrarPorProduto( po.getId().toString() );

			/** Feito isso para que ao excluir um produto que esteja ZERADO no Estoque, seu registro no estoque seja excluido tambem */
			//if ( !Utilidades.isNuloOuVazio( estoque ) && Utilidades.parseBigDecimal( estoque.getQuantidade() ).longValue() == 0L ) {
			//	daoEstoqueInterface.excluir( hibernate, new ModelFactory< EstoquePO, EstoqueDTO >().getPO( EstoquePO.class, estoque ) );
			//}

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

	@Override
	public ArrayList< ProdutoDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, ProdutoDTO dto ) throws ApplicationException {

		try {
			ProdutoPO poFilter = this.modelFactory.getPO( ProdutoPO.class, dto );

			List< ProdutoPO > encontrados = this.daoInterface.filtrar( paginacao, camposOrders, poFilter );

			ArrayList< ProdutoDTO > dtosRetorno = new ArrayList<>();

			for ( ProdutoPO poCorrente : encontrados ) {
				dtosRetorno.add( this.modelFactory.getDTO( ProdutoDTO.class, poCorrente ) );
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
	public ProdutoDTO filtrarPorId( String id ) throws ApplicationException {

		try {

			if ( Utilidades.isNuloOuVazio( id ) ) {
				throw new ApplicationException( "Id não informado!" );
			}

			ProdutoPO encontrado = this.daoInterface.filtrarPorId( Long.valueOf( id ) );

			return this.modelFactory.getDTO( ProdutoDTO.class, encontrado );
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

	/** usado para quando há Composição */
	/*private void gerenciarMarca( HibernateConnection hibernateConnection, ProdutoPO produto ) throws ApplicationException {
		if ( !Utilidades.isNuloOuVazio( produto.getMarca().getId() ) ) {
			produto.setMarca( (MarcaPO) new HibernateConnection().filtrarPorId( MarcaPO.class, produto.getMarca().getId() ) );
		}
	}*/

	/** usado para quando há Composição */
	/*private void gerenciarUnidadeMedida( HibernateConnection hibernateConnection, ProdutoPO produto ) throws ApplicationException {
		if ( !Utilidades.isNuloOuVazio( produto.getUnidadeMedida().getId() ) ) {
			produto.setUnidadeMedida( (UnidadeMedidaPO) new HibernateConnection().filtrarPorId( UnidadeMedidaPO.class, produto.getUnidadeMedida().getId() ) );
		} else {
			String[ ] unidadeSplit = Utilidades.normalizeRemoverEspacosDuplosOuMais( ( produto.getUnidadeMedida().getNome().replace( "-", " " ).replace( "/", " " ).replace( ".", " " ) ) ).split( " " );
	
			String nomeUnidadeMedida = unidadeSplit[ 0 ];
			produto.getUnidadeMedida().setNome( nomeUnidadeMedida.trim() );
	
			if ( unidadeSplit.length > 1 ) {
				String siglaUnidadeMedida = unidadeSplit[ 1 ];
				produto.getUnidadeMedida().setSigla( siglaUnidadeMedida.trim() );
			} else {
				produto.getUnidadeMedida().setSigla( "N/A" );
			}
		}
	}*/
}
