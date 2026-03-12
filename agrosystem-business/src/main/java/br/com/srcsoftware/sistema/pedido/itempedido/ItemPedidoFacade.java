package br.com.srcsoftware.sistema.pedido.itempedido;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;

public class ItemPedidoFacade implements ItemPedidoServiceInterface{

	private final ItemPedidoServiceImpl service;

	private ItemPedidoFacade(){
		this.service = new ItemPedidoServiceImpl();
	}

	public static ItemPedidoFacade getInstance() {
		return new ItemPedidoFacade();
	}

	@Override
	public List< ItemPedidoDTO > filtrarPorDataPedidoBetween( String dataInicial, String dataFinal ) throws ApplicationException {
		return service.filtrarPorDataPedidoBetween( dataInicial, dataFinal );
	}

	@Override
	public List< ItemPedidoDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, ItemPedidoDTO dto ) throws ApplicationException {
		return service.filtrar( paginacao, camposOrders, dto );
	}

}
