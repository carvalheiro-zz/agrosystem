package br.com.srcsoftware.sistema.safra.relatorios;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;

public interface RelacaoSafraSetorCulturaVariedadeDAOInterface{

	List< RelacaoSafraSetorCulturaVariedadePOJO > filtrarRelacao( StringBuffer SQL, HashMap< String, Object > parametros ) throws ApplicationException;

}
