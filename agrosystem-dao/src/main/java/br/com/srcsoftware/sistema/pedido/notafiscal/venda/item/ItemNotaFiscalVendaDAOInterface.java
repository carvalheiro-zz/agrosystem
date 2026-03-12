package br.com.srcsoftware.sistema.pedido.notafiscal.venda.item;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.sistema.pedido.notafiscal.venda.ItemNotaFiscalVendaPO;

public interface ItemNotaFiscalVendaDAOInterface{

	ArrayList< ItemNotaFiscalVendaPO > filtrarPorDataNotaFiscalVendaBetween( LocalDate dataInicial, LocalDate dataFinal ) throws ApplicationException;

	List< ItemNotaFiscalVendaPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, ItemNotaFiscalVendaPO poFilter ) throws ApplicationException;
}
