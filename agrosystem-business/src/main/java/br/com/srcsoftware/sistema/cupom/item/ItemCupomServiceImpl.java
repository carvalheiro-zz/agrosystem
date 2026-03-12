package br.com.srcsoftware.sistema.cupom.item;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.srcsoftware.factory.ModelFactory;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.exceptions.ModelFactoryException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;

public class ItemCupomServiceImpl implements ItemCupomServiceInterface{

	private transient Logger logger = Logger.getLogger( this.getClass().getName() );

	private ItemCupomDAOInterface daoInterface;

	private ModelFactory< ItemCupomPO, ItemCupomDTO > modelFactory = new ModelFactory< ItemCupomPO, ItemCupomDTO >();

	public ItemCupomServiceImpl(){
		this.daoInterface = new ItemCupomDAOImpl();
	}

	/**
	 * Polimorfico
	 * 
	 * @throws ApplicationException
	 * 
	 * @see br.com.src.masterpersistence.interfaces.BasicServiceInterface#filtrar(br.com.src.dto.requisicao_retorno.RequisicaoServico)
	 */
	@Override
	public ArrayList< ItemCupomDTO > filtrarPorDataCupomBetween( String dataInicial, String dataFinal ) throws ApplicationException {

		try {
			/** Pegando o PO passado na Requisicao */

			List< ItemCupomPO > encontrados = this.daoInterface.filtrarPorDataCupomBetween( LocalDate.parse( dataInicial, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ), LocalDate.parse( dataFinal, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ) );

			ArrayList< ItemCupomDTO > dtosRetorno = new ArrayList<>();

			for ( ItemCupomPO poCorrente : encontrados ) {
				dtosRetorno.add( this.modelFactory.getDTO( ItemCupomDTO.class, poCorrente ) );
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
	public ArrayList< ItemCupomDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, ItemCupomDTO dto, String precoCustoInicialParam, String precoCustoFinalParam ) throws ApplicationException {

		try {
			ItemCupomPO poFilter = this.modelFactory.getPO( ItemCupomPO.class, dto );

			HashMap< String, ArrayList< Object > > camposBetween = new HashMap<>();

			if ( !Utilidades.isNuloOuVazio( precoCustoInicialParam ) && !Utilidades.isNuloOuVazio( precoCustoFinalParam ) ) {
				camposBetween.put( "precoCusto", new ArrayList< Object >( Arrays.asList( Utilidades.parseBigDecimal( precoCustoInicialParam ), Utilidades.parseBigDecimal( precoCustoFinalParam ) ) ) );
			}

			List< ItemCupomPO > encontrados = this.daoInterface.filtrar( paginacao, camposOrders, camposBetween, poFilter );

			ArrayList< ItemCupomDTO > dtosRetorno = new ArrayList<>();

			for ( ItemCupomPO poCorrente : encontrados ) {
				dtosRetorno.add( this.modelFactory.getDTO( ItemCupomDTO.class, poCorrente ) );
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
