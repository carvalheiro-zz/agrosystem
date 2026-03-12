package br.com.srcsoftware.sistema.pedido.notafiscal.venda.item;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.sistema.pedido.notafiscal.venda.ItemNotaFiscalVendaDTO;

public interface ItemNotaFiscalVendaServiceInterface{

	List< ItemNotaFiscalVendaDTO > filtrarPorDataNotaFiscalVendaBetween( String dataInicial, String dataFinal ) throws ApplicationException;

	List< ItemNotaFiscalVendaDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, ItemNotaFiscalVendaDTO dto ) throws ApplicationException;

}
