package br.com.srcsoftware.sistema.demonstrativosafra.custodireto.insumo.sintetico;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import br.com.srcsoftware.manager.util.Utilidades;
import br.com.srcsoftware.modular.manager.empresa.EmpresaDTO;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.report.abstracts.MasterReport;
import br.com.srcsoftware.modular.manager.utilidades.Parametrizacao;

public final class DemonstrativoSafraSinteticoReport extends MasterReport{

	public DemonstrativoSafraSinteticoReport( String nomePastaOrigemRelatorios, String camposFitragem, String versaoSistema ) throws ApplicationException{
		super( nomePastaOrigemRelatorios, camposFitragem, versaoSistema );
	}

	public void gerarRelatorio( HttpServletResponse response,
	        EmpresaDTO empresa,
	        List< CustoDiretoInsumoSinteticoPOJO > sinteticos,
	        String cabecalhoInformativo,
	        String totalArea,
	        String totalCustoPorAlqueire,
	        String totalCustoArea ) throws ApplicationException, IOException {

		String pathDestinoServidor = Parametrizacao.parametros.get( Parametrizacao.URL_CONTEXT_PATH ) + File.separator + "temp" + File.separator + empresa.getPrefixoImagem();
		this.parametros.put( "LOGO_PATH", String.join( File.separator, pathDestinoServidor, empresa.getImagem() ) );
		this.parametros.put( "CABECALHO_INFORMATIVO", cabecalhoInformativo );
		this.parametros.put( "TOTAL_AREA", totalArea );
		this.parametros.put( "TOTAL_CUSTO_POR_ALQUEIRE", totalCustoPorAlqueire );
		this.parametros.put( "TOTAL_CUSTO_AREA", totalCustoArea );

		byte[ ] reportByte = this.facade.gerarRelatorioPDF( "demonstrativoSafraSintetico", sinteticos, this.reportPath, this.parametros );

		// Chamando o método responsável por exibir na tela o relatório gerado.
		Utilidades.writeHtmlToResponse( response, reportByte );// Não muda nunca
	}

}