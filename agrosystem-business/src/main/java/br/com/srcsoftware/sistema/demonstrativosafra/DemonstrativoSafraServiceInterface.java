package br.com.srcsoftware.sistema.demonstrativosafra;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import br.com.srcsoftware.modular.manager.empresa.EmpresaDTO;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.sistema.demonstrativosafra.custodireto.insumo.CustoDiretoInsumoPOJO;
import br.com.srcsoftware.sistema.demonstrativosafra.custodireto.insumo.sintetico.CustoDiretoInsumoSinteticoPOJO;

public interface DemonstrativoSafraServiceInterface{

	List< CustoDiretoInsumoPOJO > filtrarDemonstrativoSafraCustosDiretos( String idSafra, String idSetor, String idCultura, String classificacao ) throws ApplicationException;

	void gerarPdfSintetico( HttpServletResponse response,
	        EmpresaDTO empresa,
	        List< CustoDiretoInsumoSinteticoPOJO > sinteticos,
	        String cabecalhoInformativo,
	        String totalArea,
	        String totalCustoPorAlqueire,
	        String totalCustoArea ) throws ApplicationException;

}
