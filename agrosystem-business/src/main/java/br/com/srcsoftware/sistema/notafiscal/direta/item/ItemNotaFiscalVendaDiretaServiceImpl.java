package br.com.srcsoftware.sistema.notafiscal.direta.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.srcsoftware.factory.ModelFactory;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.exceptions.ModelFactoryException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.utilidades.DateUtil;

public class ItemNotaFiscalVendaDiretaServiceImpl implements ItemNotaFiscalVendaDiretaServiceInterface{

	private transient Logger logger = Logger.getLogger( this.getClass().getName() );

	private ItemNotaFiscalVendaDiretaDAOInterface daoInterface;

	private ModelFactory< ItemNotaFiscalVendaDiretaPO, ItemNotaFiscalVendaDiretaDTO > modelFactory = new ModelFactory< ItemNotaFiscalVendaDiretaPO, ItemNotaFiscalVendaDiretaDTO >();

	public ItemNotaFiscalVendaDiretaServiceImpl(){
		this.daoInterface = new ItemNotaFiscalVendaDiretaDAOImpl();
	}

	/**
	 * Polimorfico
	 * 
	 * @throws ApplicationException
	 * 
	 * @see br.com.src.masterpersistence.interfaces.BasicServiceInterface#filtrar(br.com.src.dto.requisicao_retorno.RequisicaoServico)
	 */
	@Override
	public List< ItemNotaFiscalVendaDiretaDTO > filtrarPorDataNotaFiscalVendaDiretaBetween( String dataInicial, String dataFinal ) throws ApplicationException {
		try {
			List< ItemNotaFiscalVendaDiretaPO > encontrados = this.daoInterface.filtrarPorDataNotaFiscalVendaDiretaBetween( DateUtil.parseLocalDate( dataInicial ), DateUtil.parseLocalDate( dataFinal ) );

			ArrayList< ItemNotaFiscalVendaDiretaDTO > dtosRetorno = new ArrayList<>();

			for ( ItemNotaFiscalVendaDiretaPO poCorrente : encontrados ) {
				dtosRetorno.add( this.modelFactory.getDTO( ItemNotaFiscalVendaDiretaDTO.class, poCorrente ) );
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
	public List< ItemNotaFiscalVendaDiretaDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, ItemNotaFiscalVendaDiretaDTO dto ) throws ApplicationException {

		try {
			ItemNotaFiscalVendaDiretaPO poFilter = this.modelFactory.getPO( ItemNotaFiscalVendaDiretaPO.class, dto );

			List< ItemNotaFiscalVendaDiretaPO > encontrados = this.daoInterface.filtrar( paginacao, camposOrders, poFilter );

			ArrayList< ItemNotaFiscalVendaDiretaDTO > dtosRetorno = new ArrayList<>();

			for ( ItemNotaFiscalVendaDiretaPO poCorrente : encontrados ) {
				dtosRetorno.add( this.modelFactory.getDTO( ItemNotaFiscalVendaDiretaDTO.class, poCorrente ) );
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
