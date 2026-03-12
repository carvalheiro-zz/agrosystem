package br.com.srcsoftware.sistema.relatorios;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import br.com.srcsoftware.manager.util.Utilidades;
import br.com.srcsoftware.modular.manager.empresa.EmpresaDTO;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.report.abstracts.MasterReport;
import br.com.srcsoftware.modular.manager.utilidades.Parametrizacao;
import br.com.srcsoftware.sistema.safra.relatorios.RelacaoSafraSetorCulturaVariedadePOJO;

public final class RelacaoSafraSetorCulturaVariedadeReport extends MasterReport{

	public RelacaoSafraSetorCulturaVariedadeReport( String nomePastaOrigemRelatorios, String camposFitragem, String versaoSistema ) throws ApplicationException{
		super( nomePastaOrigemRelatorios, camposFitragem, versaoSistema );
		this.parametros.put( "CAMPOS_PESQUISA", camposFitragem );
	}

	@SuppressWarnings( "unchecked" )
	public void gerarRelatorioPDF( HttpServletResponse response, EmpresaDTO empresa, List< RelacaoSafraSetorCulturaVariedadePOJO > pojos, String areaAlqueire ) throws ApplicationException, IOException {

		String pathDestinoServidor = Parametrizacao.parametros.get( Parametrizacao.URL_CONTEXT_PATH ) + File.separator + "temp" + File.separator + empresa.getPrefixoImagem();
		this.parametros.put( "LOGO_PATH", String.join( File.separator, pathDestinoServidor, empresa.getImagem() ) );
		this.parametros.put( "AREA_ALQUEIRE", areaAlqueire );

		byte[ ] reportByte = this.facade.gerarRelatorioPDF( "relacaoSafraSetorCulturaVariedade", pojos, this.reportPath, this.parametros );

		// Chamando o método responsável por exibir na tela o relatório gerado.
		Utilidades.writeHtmlToResponse( response, reportByte );// Não muda nunca
	}

	@SuppressWarnings( "unchecked" )
	public void gerarRelatorioXLS( HttpServletResponse response, EmpresaDTO empresa, List< RelacaoSafraSetorCulturaVariedadePOJO > pojos, String areaAlqueire ) throws ApplicationException, IOException {

		String pathDestinoServidor = Parametrizacao.parametros.get( Parametrizacao.URL_CONTEXT_PATH ) + File.separator + "temp" + File.separator + empresa.getPrefixoImagem();
		this.parametros.put( "LOGO_PATH", String.join( File.separator, pathDestinoServidor, empresa.getImagem() ) );
		this.parametros.put( "AREA_ALQUEIRE", areaAlqueire );

		byte[ ] reportByte = this.facade.gerarRelatorioXLS( "relacaoSafraSetorCulturaVariedade", pojos, this.reportPath, this.parametros );

		// Chamando o método responsável por exibir na tela o relatório gerado.
		Utilidades.writeHtmlToXLS2000Response( response, reportByte, "" );
	}

}