package br.com.srcsoftware.sistema.pedido.notafiscal.simplesremessa.item;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.sistema.pedido.notafiscal.simplesremessa.ItemNotaFiscalSimplesRemessaDTO;

public class ItemNotaFiscalSimplesRemessaFacade implements ItemNotaFiscalSimplesRemessaServiceInterface{

	private final ItemNotaFiscalSimplesRemessaServiceImpl service;

	private ItemNotaFiscalSimplesRemessaFacade(){
		this.service = new ItemNotaFiscalSimplesRemessaServiceImpl();
	}

	public static ItemNotaFiscalSimplesRemessaFacade getInstance() {
		return new ItemNotaFiscalSimplesRemessaFacade();
	}

	@Override
	public List< ItemNotaFiscalSimplesRemessaDTO > filtrarPorDataNotaFiscalSimplesRemessaBetween( String dataInicial, String dataFinal ) throws ApplicationException {
		return service.filtrarPorDataNotaFiscalSimplesRemessaBetween( dataInicial, dataFinal );
	}

	@Override
	public List< ItemNotaFiscalSimplesRemessaDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, ItemNotaFiscalSimplesRemessaDTO dto ) throws ApplicationException {
		return service.filtrar( paginacao, camposOrders, dto );
	}

}
