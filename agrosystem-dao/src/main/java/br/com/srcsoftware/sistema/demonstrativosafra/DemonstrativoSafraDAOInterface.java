package br.com.srcsoftware.sistema.demonstrativosafra;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.sistema.demonstrativosafra.custodireto.insumo.CustoDiretoInsumoPOJO;

public interface DemonstrativoSafraDAOInterface{

	List< CustoDiretoInsumoPOJO > filtrarDemonstrativoSafraCustosDiretos( StringBuffer SQL, HashMap< String, Object > parametros ) throws ApplicationException;

}
