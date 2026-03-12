package br.com.srcsoftware.sistema.pedido.notafiscal.simplesremessa.item;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.sistema.pedido.notafiscal.simplesremessa.ItemNotaFiscalSimplesRemessaPO;

public interface ItemNotaFiscalSimplesRemessaDAOInterface{

	ArrayList< ItemNotaFiscalSimplesRemessaPO > filtrarPorDataNotaFiscalSimplesRemessaBetween( LocalDate dataInicial, LocalDate dataFinal ) throws ApplicationException;

	List< ItemNotaFiscalSimplesRemessaPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, ItemNotaFiscalSimplesRemessaPO poFilter ) throws ApplicationException;
}
