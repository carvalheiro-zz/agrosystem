package br.com.srcsoftware.sistema.pedido.itempedido;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;

public interface ItemPedidoServiceInterface{

	List< ItemPedidoDTO > filtrarPorDataPedidoBetween( String dataInicial, String dataFinal ) throws ApplicationException;

	List< ItemPedidoDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, ItemPedidoDTO dto ) throws ApplicationException;

}
