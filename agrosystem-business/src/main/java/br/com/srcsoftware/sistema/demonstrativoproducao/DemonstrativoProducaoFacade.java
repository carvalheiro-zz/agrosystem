package br.com.srcsoftware.sistema.demonstrativoproducao;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.sistema.silo.entrada.InformacoesProducaoSafraPOJO;

public class DemonstrativoProducaoFacade implements DemonstrativoProducaoServiceInterface{

	private final DemonstrativoProducaoServiceImpl service;

	private DemonstrativoProducaoFacade(){
		this.service = new DemonstrativoProducaoServiceImpl();
	}

	public static DemonstrativoProducaoFacade getInstance() {
		return new DemonstrativoProducaoFacade();
	}

	@Override
	public List< InformacoesProducaoSafraPOJO > filtrarSaldoProducao( String idSafra, String idSetor, String idCultura, String idVariedade ) throws ApplicationException {
		return service.filtrarSaldoProducao( idSafra, idSetor, idCultura, idVariedade );
	}

	@Override
	public void gerarPDF( HttpServletResponse response, String idEmpresa, List< InformacoesProducaoSafraPOJO > encontrados, String safra, String setor, String cultura, String variedade ) throws ApplicationException {
		service.gerarPDF( response, idEmpresa, encontrados, safra, setor, cultura, variedade );
	}

}
