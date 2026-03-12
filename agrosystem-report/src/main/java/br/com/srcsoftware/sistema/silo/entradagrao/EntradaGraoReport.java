package br.com.srcsoftware.sistema.silo.entradagrao;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import br.com.srcsoftware.manager.util.Utilidades;
import br.com.srcsoftware.modular.manager.empresa.EmpresaDTO;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.report.abstracts.MasterReport;
import br.com.srcsoftware.modular.manager.utilidades.Parametrizacao;
import br.com.srcsoftware.sistema.silo.entrada.EntradaGraoDTO;

public final class EntradaGraoReport extends MasterReport{

	/**
	 * M�todo construtor respons�vel por configurar os dados necess�rios para
	 * a geração do relat�rio.
	 * 
	 * @param HttpServletRequest request - Request usado para a aquisição
	 *        dos caminhos das pasta do projeto.
	 * @param String nomePastaOrigemRelatorios - Nome da pasta criada dentro da pasta webapp/relatorios
	 *        na qual seus arquivos (.jrxm e .jasper) foram armazenados.
	 * @param EmpresaDTO empresaLogada - Empresa logada no sistema, usado a fim de se pegar o nome da imagem do
	 *        Logo para exibição no relat�rio.
	 * @param String camposFitragem - Contem as informaçães dos nomes dos campos e valores a eles atribuido no qual
	 *        o relatorio foi gerado.
	 *        EX: Consultei todos os cliente com idade = 18. No relatorio ser� exibido
	 *        "Idade: 18" em Refer�ncia de Filtragem.
	 * @param String versaoSistemaPMEControle - Vers�o atual do sistema PMEControle;
	 * @param String sitePMEControle - Site do sistema PMEControle.
	 * @throws ApplicationException
	 */
	public EntradaGraoReport( String nomePastaOrigemRelatorios, String camposFitragem, String versaoSistema ) throws ApplicationException{
		super( nomePastaOrigemRelatorios, camposFitragem, versaoSistema );
	}

	/**
	 * M�todo respons�vel por gerar e exibir o relatorio.
	 * 
	 * @param HttpServletResponse response - Response usado para "escrever" o relatorio na tela.
	 * @param ArrayList< ContratanteHCDTO > lista = Lista contendo todos os objetos cujos dados ser�o
	 *        exibidos no relat�rio.
	 * @throws ApplicationException
	 * @throws ReportException
	 * @throws IOException
	 */
	public void gerarRelatorio( HttpServletResponse response, ArrayList< EntradaGraoDTO > entradas, String camposPesquisa ) throws ApplicationException, IOException {

		EmpresaDTO empresa = entradas.get( 0 ).getEstoquista().getEmpresa();

		calcularTotais( entradas );

		String pathDestinoServidor = Parametrizacao.parametros.get( Parametrizacao.URL_CONTEXT_PATH ) + File.separator + "temp" + File.separator + empresa.getPrefixoImagem();
		this.parametros.put( "LOGO_PATH", String.join( File.separator, pathDestinoServidor, empresa.getImagem() ) );
		this.parametros.put( "CAMPOS_PESQUISA", camposPesquisa );

		byte[ ] reportByte = this.facade.gerarRelatorioPDF( "entradaGraos", entradas, this.reportPath, this.parametros );

		// Chamando o m�todo respons�vel por exibir na tela o relat�rio gerado.
		Utilidades.writeHtmlToResponse( response, reportByte );// N�o muda nunca
	}

	public void calcularTotais( List< EntradaGraoDTO > encontradosParaTotais ) {

		BigDecimal totalPesoBruto = BigDecimal.ZERO;
		BigDecimal totalPesoLiquido = BigDecimal.ZERO;
		BigDecimal totalSacas = BigDecimal.ZERO;

		for ( EntradaGraoDTO corrente : encontradosParaTotais ) {

			BigDecimal pesoBruto = br.com.srcsoftware.modular.manager.utilidades.Utilidades.parseBigDecimal( corrente.getPesoBruto() );
			totalPesoBruto = totalPesoBruto.add( pesoBruto );

			BigDecimal pesoLiquido = br.com.srcsoftware.modular.manager.utilidades.Utilidades.parseBigDecimal( corrente.getPesoLiquido() );
			totalPesoLiquido = totalPesoLiquido.add( pesoLiquido );

			BigDecimal sacas = br.com.srcsoftware.modular.manager.utilidades.Utilidades.parseBigDecimal( corrente.getEmSacas() );
			totalSacas = totalSacas.add( sacas );
		}

		this.parametros.put( "TOTAL_PESO_BRUTO", br.com.srcsoftware.modular.manager.utilidades.Utilidades.parseBigDecimal( totalPesoBruto ) );
		this.parametros.put( "TOTAL_PESO_LIQUIDO", br.com.srcsoftware.modular.manager.utilidades.Utilidades.parseBigDecimal( totalPesoLiquido ) );
		this.parametros.put( "TOTAL_EM_SACAS", br.com.srcsoftware.modular.manager.utilidades.Utilidades.parseBigDecimal( totalSacas ) );
	}
}