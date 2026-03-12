package br.com.srcsoftware.sistema.demonstrativoproducao;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.sistema.silo.entrada.InformacoesProducaoSafraPOJO;

public interface DemonstrativoProducaoServiceInterface{

	List< InformacoesProducaoSafraPOJO > filtrarSaldoProducao( String idSafra, String idSetor, String idCultura, String idVariedade ) throws ApplicationException;

	void gerarPDF( HttpServletResponse response, String idEmpresa, List< InformacoesProducaoSafraPOJO > encontrados, String safra, String setor, String cultura, String variedade ) throws ApplicationException;

}
