package br.com.srcsoftware.sistema.demonstrativosafra;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import br.com.srcsoftware.modular.manager.empresa.EmpresaDTO;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.sistema.demonstrativosafra.custodireto.insumo.CustoDiretoInsumoPOJO;
import br.com.srcsoftware.sistema.demonstrativosafra.custodireto.insumo.sintetico.CustoDiretoInsumoSinteticoPOJO;

public class DemonstrativoSafraFacade implements DemonstrativoSafraServiceInterface{

	private final DemonstrativoSafraServiceImpl service;

	private DemonstrativoSafraFacade(){
		this.service = new DemonstrativoSafraServiceImpl();
	}

	public static DemonstrativoSafraFacade getInstance() {
		return new DemonstrativoSafraFacade();
	}

	@Override
	public List< CustoDiretoInsumoPOJO > filtrarDemonstrativoSafraCustosDiretos( String idSafra, String idSetor, String idCultura, String classificacao ) throws ApplicationException {
		return service.filtrarDemonstrativoSafraCustosDiretos( idSafra, idSetor, idCultura, classificacao );
	}

	@Override
	public void gerarPdfSintetico( HttpServletResponse response,
	        EmpresaDTO empresa,
	        List< CustoDiretoInsumoSinteticoPOJO > sinteticos,
	        String cabecalhoInformativo,
	        String totalArea,
	        String totalCustoPorAlqueire,
	        String totalCustoArea ) throws ApplicationException {
		service.gerarPdfSintetico( response, empresa, sinteticos, cabecalhoInformativo, totalArea, totalCustoPorAlqueire, totalCustoArea );
	}

}
