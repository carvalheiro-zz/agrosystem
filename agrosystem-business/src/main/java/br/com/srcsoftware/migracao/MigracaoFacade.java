package br.com.srcsoftware.migracao;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;

public class MigracaoFacade implements MigracaoServiceInterface{

	private final MigracaoServiceImpl service;

	private MigracaoFacade(){
		this.service = new MigracaoServiceImpl();
	}

	public static MigracaoFacade getInstance() {
		return new MigracaoFacade();
	}

	@Override
	public void executar() throws ApplicationException {
		service.executar();
	}

}
