package br.com.srcsoftware.sistema.pedido.notafiscal.simplesremessa.item;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.sistema.pedido.notafiscal.simplesremessa.ItemNotaFiscalSimplesRemessaDTO;

public interface ItemNotaFiscalSimplesRemessaServiceInterface{

	List< ItemNotaFiscalSimplesRemessaDTO > filtrarPorDataNotaFiscalSimplesRemessaBetween( String dataInicial, String dataFinal ) throws ApplicationException;

	List< ItemNotaFiscalSimplesRemessaDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, ItemNotaFiscalSimplesRemessaDTO dto ) throws ApplicationException;

}
