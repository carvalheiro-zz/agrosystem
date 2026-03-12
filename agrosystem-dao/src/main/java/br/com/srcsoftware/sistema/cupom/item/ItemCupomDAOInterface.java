package br.com.srcsoftware.sistema.cupom.item;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;

public interface ItemCupomDAOInterface{

	ArrayList< ItemCupomPO > filtrarPorDataCupomBetween( LocalDate dataInicial, LocalDate dataFinal ) throws ApplicationException;

	List< ItemCupomPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, HashMap< String, ArrayList< Object > > camposBetween, ItemCupomPO poFilter ) throws ApplicationException;
}
