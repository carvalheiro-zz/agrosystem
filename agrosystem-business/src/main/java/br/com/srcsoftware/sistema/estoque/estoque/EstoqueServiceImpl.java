package br.com.srcsoftware.sistema.estoque.estoque;

import java.util.List;

import org.apache.log4j.Logger;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.estoque.estoque.pojo.EstoquePOJO;
import br.com.srcsoftware.sistema.pedido.pedido.PedidoFacade;

public class EstoqueServiceImpl implements EstoqueServiceInterface{

	private transient Logger logger = Logger.getLogger( this.getClass().getName() );

	private EstoqueDAOInterface daoInterface;

	public EstoqueServiceImpl(){
		this.daoInterface = new EstoqueDAOImpl();
	}

	@Override
	public List< EstoquePOJO > filtrar( Paginacao paginacao, String idProduto, String nomeProduto, String localizacaoProduto, String idMarca, String idTipo, String negativos ) throws ApplicationException {

		try {

			Long idProdutoLong = null;

			if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
				idProdutoLong = Long.valueOf( idProduto );
			}

			Long idMarcaLong = null;

			if ( !Utilidades.isNuloOuVazio( idMarca ) ) {
				idMarcaLong = Long.valueOf( idMarca );
			}

			Long idTipoLong = null;

			if ( !Utilidades.isNuloOuVazio( idTipo ) ) {
				idTipoLong = Long.valueOf( idTipo );
			}

			List< EstoquePOJO > encontrados = this.daoInterface.filtrar( paginacao, idProdutoLong, nomeProduto, localizacaoProduto, idMarcaLong, idTipoLong, negativos );

			for ( EstoquePOJO poCorrente : encontrados ) {
				poCorrente.setRestantes( PedidoFacade.getInstance().calcularValoresRestantesEntregarEstoque( poCorrente.getIdPoduto() ) );
			}

			return encontrados;
		} catch ( ApplicationException e ) {
			this.logger.error( e.getMessage(), e );
			throw e;
		} catch ( Exception e ) {
			this.logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public EstoquePOJO filtrarSaldoPorProduto( String idProduto ) throws ApplicationException {

		try {

			Long idProdutoLong = null;

			if ( Utilidades.isNuloOuVazio( idProduto ) ) {
				throw new ApplicationException( "Produto não informado!" );
			}

			idProdutoLong = Long.valueOf( idProduto );

			List< EstoquePOJO > encontrados = this.daoInterface.filtrar( Paginacao.NAO, idProdutoLong, null, null, null, null, null );

			if ( !Utilidades.isNuloOuVazio( encontrados ) ) {
				return encontrados.iterator().next();
			}

			throw new ApplicationException( "Produto não encontrado!" );
		} catch ( ApplicationException e ) {
			this.logger.error( e.getMessage(), e );
			throw e;
		} catch ( Exception e ) {
			this.logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

}
