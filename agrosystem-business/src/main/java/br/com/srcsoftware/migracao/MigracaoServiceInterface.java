package br.com.srcsoftware.migracao;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;

public interface MigracaoServiceInterface{

	void executar() throws ApplicationException;

}
