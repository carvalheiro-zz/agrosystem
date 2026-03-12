package br.com.srcsoftware.sistema.notafiscal.direta.item;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;

public interface ItemNotaFiscalVendaDiretaDAOInterface{

	List< ItemNotaFiscalVendaDiretaPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, ItemNotaFiscalVendaDiretaPO poFilter ) throws ApplicationException;

	ArrayList< ItemNotaFiscalVendaDiretaPO > filtrarPorDataNotaFiscalVendaDiretaBetween( LocalDate dataInicial, LocalDate dataFinal ) throws ApplicationException;

}
