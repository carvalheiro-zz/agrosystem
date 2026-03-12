package br.com.srcsoftware.sistema.pedido.notafiscal.simplesremessa.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.srcsoftware.factory.ModelFactory;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.exceptions.ModelFactoryException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.utilidades.DateUtil;
import br.com.srcsoftware.sistema.pedido.notafiscal.simplesremessa.ItemNotaFiscalSimplesRemessaDTO;
import br.com.srcsoftware.sistema.pedido.notafiscal.simplesremessa.ItemNotaFiscalSimplesRemessaPO;

public class ItemNotaFiscalSimplesRemessaServiceImpl implements ItemNotaFiscalSimplesRemessaServiceInterface{

	private transient Logger logger = Logger.getLogger( this.getClass().getName() );

	private ItemNotaFiscalSimplesRemessaDAOInterface daoInterface;

	private ModelFactory< ItemNotaFiscalSimplesRemessaPO, ItemNotaFiscalSimplesRemessaDTO > modelFactory = new ModelFactory<>();

	public ItemNotaFiscalSimplesRemessaServiceImpl(){
		this.daoInterface = new ItemNotaFiscalSimplesRemessaDAOImpl();
	}

	@Override
	public List< ItemNotaFiscalSimplesRemessaDTO > filtrarPorDataNotaFiscalSimplesRemessaBetween( String dataInicial, String dataFinal ) throws ApplicationException {
		try {

			List< ItemNotaFiscalSimplesRemessaPO > dadosEncontrados = this.daoInterface.filtrarPorDataNotaFiscalSimplesRemessaBetween( DateUtil.parseLocalDate( dataInicial ), DateUtil.parseLocalDate( dataFinal ) );

			ArrayList< ItemNotaFiscalSimplesRemessaDTO > dtosRetorno = new ArrayList<>();

			for ( ItemNotaFiscalSimplesRemessaPO poCorrente : dadosEncontrados ) {
				dtosRetorno.add( this.modelFactory.getDTO( ItemNotaFiscalSimplesRemessaDTO.class, poCorrente ) );
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
	public List< ItemNotaFiscalSimplesRemessaDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, ItemNotaFiscalSimplesRemessaDTO dto ) throws ApplicationException {

		try {
			ItemNotaFiscalSimplesRemessaPO poFilter = this.modelFactory.getPO( ItemNotaFiscalSimplesRemessaPO.class, dto );

			List< ItemNotaFiscalSimplesRemessaPO > encontrados = this.daoInterface.filtrar( paginacao, camposOrders, poFilter );

			ArrayList< ItemNotaFiscalSimplesRemessaDTO > dtosRetorno = new ArrayList<>();

			for ( ItemNotaFiscalSimplesRemessaPO poCorrente : encontrados ) {
				dtosRetorno.add( this.modelFactory.getDTO( ItemNotaFiscalSimplesRemessaDTO.class, poCorrente ) );
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
