package br.com.srcsoftware.sistema.notafiscal.direta.item;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;

public interface ItemNotaFiscalVendaDiretaServiceInterface{

	List< ItemNotaFiscalVendaDiretaDTO > filtrarPorDataNotaFiscalVendaDiretaBetween( String dataInicial, String dataFinal ) throws ApplicationException;

	List< ItemNotaFiscalVendaDiretaDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, ItemNotaFiscalVendaDiretaDTO dto ) throws ApplicationException;

}
