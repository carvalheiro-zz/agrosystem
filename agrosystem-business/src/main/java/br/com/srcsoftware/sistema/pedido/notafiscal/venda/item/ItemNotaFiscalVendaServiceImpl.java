package br.com.srcsoftware.sistema.pedido.notafiscal.venda.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.srcsoftware.factory.ModelFactory;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.exceptions.ModelFactoryException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.utilidades.DateUtil;
import br.com.srcsoftware.sistema.pedido.notafiscal.venda.ItemNotaFiscalVendaDTO;
import br.com.srcsoftware.sistema.pedido.notafiscal.venda.ItemNotaFiscalVendaPO;

public class ItemNotaFiscalVendaServiceImpl implements ItemNotaFiscalVendaServiceInterface{

	private transient Logger logger = Logger.getLogger( this.getClass().getName() );

	private ItemNotaFiscalVendaDAOInterface daoInterface;

	private ModelFactory< ItemNotaFiscalVendaPO, ItemNotaFiscalVendaDTO > modelFactory = new ModelFactory<>();

	public ItemNotaFiscalVendaServiceImpl(){
		this.daoInterface = new ItemNotaFiscalVendaDAOImpl();
	}

	/**
	 * Polimorfico
	 * 
	 * @throws ApplicationException
	 * 
	 * @see br.com.src.masterpersistence.interfaces.BasicServiceInterface#filtrar(br.com.src.dto.requisicao_retorno.RequisicaoServico)
	 */
	@Override
	public List< ItemNotaFiscalVendaDTO > filtrarPorDataNotaFiscalVendaBetween( String dataInicial, String dataFinal ) throws ApplicationException {
		try {

			List< ItemNotaFiscalVendaPO > dadosEncontrados = this.daoInterface.filtrarPorDataNotaFiscalVendaBetween( DateUtil.parseLocalDate( dataInicial ), DateUtil.parseLocalDate( dataFinal ) );

			ArrayList< ItemNotaFiscalVendaDTO > dtosRetorno = new ArrayList<>();

			for ( ItemNotaFiscalVendaPO poCorrente : dadosEncontrados ) {
				dtosRetorno.add( this.modelFactory.getDTO( ItemNotaFiscalVendaDTO.class, poCorrente ) );
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
	public List< ItemNotaFiscalVendaDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, ItemNotaFiscalVendaDTO dto ) throws ApplicationException {

		try {
			ItemNotaFiscalVendaPO poFilter = this.modelFactory.getPO( ItemNotaFiscalVendaPO.class, dto );

			List< ItemNotaFiscalVendaPO > encontrados = this.daoInterface.filtrar( paginacao, camposOrders, poFilter );

			ArrayList< ItemNotaFiscalVendaDTO > dtosRetorno = new ArrayList<>();

			for ( ItemNotaFiscalVendaPO poCorrente : encontrados ) {
				dtosRetorno.add( this.modelFactory.getDTO( ItemNotaFiscalVendaDTO.class, poCorrente ) );
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
