package br.com.srcsoftware.sistema.demonstrativovenda;

import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.sistema.silo.contratovenda.InformacoesRestanteEntregarPOJO;

public interface DemonstrativoVendaServiceInterface{

	List< InformacoesRestanteEntregarPOJO > filtrarDemonstrativoVenda( String idCultura ) throws ApplicationException;

}
