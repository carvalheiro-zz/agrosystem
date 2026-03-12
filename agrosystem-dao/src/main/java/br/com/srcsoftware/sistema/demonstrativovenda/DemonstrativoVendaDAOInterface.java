package br.com.srcsoftware.sistema.demonstrativovenda;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.sistema.silo.contratovenda.InformacoesRestanteEntregarPOJO;

public interface DemonstrativoVendaDAOInterface{

	List< InformacoesRestanteEntregarPOJO > filtrarDemonstrativoVenda( StringBuffer SQL, HashMap< String, Object > parametros ) throws ApplicationException;

}
