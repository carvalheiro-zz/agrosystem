package br.com.srcsoftware.sistema.demonstrativovenda;

import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.sistema.silo.contratovenda.InformacoesRestanteEntregarPOJO;

public class DemonstrativoVendaFacade implements DemonstrativoVendaServiceInterface{

	private final DemonstrativoVendaServiceImpl service;

	private DemonstrativoVendaFacade(){
		this.service = new DemonstrativoVendaServiceImpl();
	}

	public static DemonstrativoVendaFacade getInstance() {
		return new DemonstrativoVendaFacade();
	}

	@Override
	public List< InformacoesRestanteEntregarPOJO > filtrarDemonstrativoVenda( String idCultura ) throws ApplicationException {
		return service.filtrarDemonstrativoVenda( idCultura );
	}

}
