package br.com.srcsoftware.sistema.cupom.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;

public class ItemCupomFacade implements ItemCupomServiceInterface{

	private final ItemCupomServiceImpl service;

	private ItemCupomFacade(){
		this.service = new ItemCupomServiceImpl();
	}

	public static ItemCupomFacade getInstance() {
		return new ItemCupomFacade();
	}

	@Override
	public ArrayList< ItemCupomDTO > filtrarPorDataCupomBetween( String dataInicial, String dataFinal ) throws ApplicationException {
		return service.filtrarPorDataCupomBetween( dataInicial, dataFinal );
	}

	@Override
	public List< ItemCupomDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, ItemCupomDTO dto, String precoCustoInicialParam, String precoCustoFinalParam ) throws ApplicationException {
		return service.filtrar( paginacao, camposOrders, dto, precoCustoInicialParam, precoCustoFinalParam );
	}

}
