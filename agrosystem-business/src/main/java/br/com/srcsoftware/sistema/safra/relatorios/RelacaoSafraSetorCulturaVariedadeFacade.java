package br.com.srcsoftware.sistema.safra.relatorios;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;

public class RelacaoSafraSetorCulturaVariedadeFacade implements RelacaoSafraSetorCulturaVariedadeInterface{

	private final RelacaoSafraSetorCulturaVariedadeServiceImpl service;

	private RelacaoSafraSetorCulturaVariedadeFacade(){
		this.service = new RelacaoSafraSetorCulturaVariedadeServiceImpl();
	}

	public static RelacaoSafraSetorCulturaVariedadeFacade getInstance() {
		return new RelacaoSafraSetorCulturaVariedadeFacade();
	}

	@Override
	public List< RelacaoSafraSetorCulturaVariedadePOJO > filtrarRelacao( String idSafra, String idCultura ) throws ApplicationException {
		return service.filtrarRelacao( idSafra, idCultura );
	}

	@Override
	public void gerarPDF( HttpServletResponse response, String idEmpresa, List< RelacaoSafraSetorCulturaVariedadePOJO > encontrados, String safra, String cultura ) throws ApplicationException {
		service.gerarPDF( response, idEmpresa, encontrados, safra, cultura );
	}

	@Override
	public void gerarXLS( HttpServletResponse response, String idEmpresa, List< RelacaoSafraSetorCulturaVariedadePOJO > encontrados, String safra, String cultura ) throws ApplicationException {
		service.gerarXLS( response, idEmpresa, encontrados, safra, cultura );
	}

	@Override
	public void exportarXLS( ArrayList< RelacaoSafraSetorCulturaVariedadePOJO > encontrados, HttpServletResponse response, String nomeArquivo ) throws ApplicationException {
		service.exportarXLS( encontrados, response, nomeArquivo );
	}

}
