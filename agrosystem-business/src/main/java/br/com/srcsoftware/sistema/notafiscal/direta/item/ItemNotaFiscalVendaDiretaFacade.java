package br.com.srcsoftware.sistema.notafiscal.direta.item;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;

public class ItemNotaFiscalVendaDiretaFacade implements ItemNotaFiscalVendaDiretaServiceInterface{

	private final ItemNotaFiscalVendaDiretaServiceImpl service;

	private ItemNotaFiscalVendaDiretaFacade(){
		this.service = new ItemNotaFiscalVendaDiretaServiceImpl();
	}

	public static ItemNotaFiscalVendaDiretaFacade getInstance() {
		return new ItemNotaFiscalVendaDiretaFacade();
	}

	@Override
	public List< ItemNotaFiscalVendaDiretaDTO > filtrarPorDataNotaFiscalVendaDiretaBetween( String dataInicial, String dataFinal ) throws ApplicationException {
		return service.filtrarPorDataNotaFiscalVendaDiretaBetween( dataInicial, dataFinal );
	}

	@Override
	public List< ItemNotaFiscalVendaDiretaDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, ItemNotaFiscalVendaDiretaDTO dto ) throws ApplicationException {
		return service.filtrar( paginacao, camposOrders, dto );
	}

}
