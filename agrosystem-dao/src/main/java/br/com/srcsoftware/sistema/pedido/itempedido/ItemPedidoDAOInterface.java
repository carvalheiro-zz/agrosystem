package br.com.srcsoftware.sistema.pedido.itempedido;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;

public interface ItemPedidoDAOInterface{

	ArrayList< ItemPedidoPO > filtrarPorDataPedidoBetween( LocalDate dataInicial, LocalDate dataFinal ) throws ApplicationException;

	List< ItemPedidoPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, ItemPedidoPO poFilter ) throws ApplicationException;

}
