package br.com.srcsoftware.sistema.cupom.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;

public interface ItemCupomServiceInterface{

	ArrayList< ItemCupomDTO > filtrarPorDataCupomBetween( String dataInicial, String dataFinal ) throws ApplicationException;

	List< ItemCupomDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, ItemCupomDTO dto, String precoCustoInicialParam, String precoCustoFinalParam ) throws ApplicationException;

}
