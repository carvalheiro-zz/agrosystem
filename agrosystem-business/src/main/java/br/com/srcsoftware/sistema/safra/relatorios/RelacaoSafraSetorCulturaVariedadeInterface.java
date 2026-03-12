package br.com.srcsoftware.sistema.safra.relatorios;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;

public interface RelacaoSafraSetorCulturaVariedadeInterface{

	List< RelacaoSafraSetorCulturaVariedadePOJO > filtrarRelacao( String idSafra, String idCultura ) throws ApplicationException;

	void gerarPDF( HttpServletResponse response, String idEmpresa, List< RelacaoSafraSetorCulturaVariedadePOJO > encontrados, String safra, String cultura ) throws ApplicationException;

	void gerarXLS( HttpServletResponse response, String idEmpresa, List< RelacaoSafraSetorCulturaVariedadePOJO > encontrados, String safra, String cultura ) throws ApplicationException;

	void exportarXLS( ArrayList< RelacaoSafraSetorCulturaVariedadePOJO > encontrados, HttpServletResponse response, String nomeArquivo ) throws ApplicationException;
}
