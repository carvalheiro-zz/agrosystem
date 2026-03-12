package br.com.srcsoftware.sistema.pedido.itempedido;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.srcsoftware.factory.ModelFactory;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.exceptions.ModelFactoryException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.utilidades.DateUtil;

public class ItemPedidoServiceImpl implements ItemPedidoServiceInterface{

	private transient Logger logger = Logger.getLogger( this.getClass().getName() );

	private ItemPedidoDAOInterface daoInterface;

	private ModelFactory< ItemPedidoPO, ItemPedidoDTO > modelFactory = new ModelFactory< ItemPedidoPO, ItemPedidoDTO >();

	public ItemPedidoServiceImpl(){
		this.daoInterface = new ItemPedidoDAOImpl();
	}

	/**
	 * Polimorfico
	 * 
	 * @throws ApplicationException
	 * 
	 * @see br.com.src.masterpersistence.interfaces.BasicServiceInterface#filtrar(br.com.src.dto.requisicao_retorno.RequisicaoServico)
	 */
	@Override
	public List< ItemPedidoDTO > filtrarPorDataPedidoBetween( String dataInicial, String dataFinal ) throws ApplicationException {

		try {
			List< ItemPedidoPO > dadosEncontrados = this.daoInterface.filtrarPorDataPedidoBetween( DateUtil.parseLocalDate( dataInicial ), DateUtil.parseLocalDate( dataFinal ) );

			ArrayList< ItemPedidoDTO > dtosRetorno = new ArrayList<>();

			for ( ItemPedidoPO poCorrente : dadosEncontrados ) {
				dtosRetorno.add( this.modelFactory.getDTO( ItemPedidoDTO.class, poCorrente ) );
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
	public List< ItemPedidoDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, ItemPedidoDTO dto ) throws ApplicationException {

		try {
			ItemPedidoPO poFilter = this.modelFactory.getPO( ItemPedidoPO.class, dto );

			List< ItemPedidoPO > encontrados = this.daoInterface.filtrar( paginacao, camposOrders, poFilter );

			ArrayList< ItemPedidoDTO > dtosRetorno = new ArrayList<>();

			for ( ItemPedidoPO poCorrente : encontrados ) {
				dtosRetorno.add( this.modelFactory.getDTO( ItemPedidoDTO.class, poCorrente ) );
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
}
