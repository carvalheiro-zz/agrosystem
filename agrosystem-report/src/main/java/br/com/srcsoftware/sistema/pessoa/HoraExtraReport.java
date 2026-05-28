package br.com.srcsoftware.sistema.pessoa;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import br.com.srcsoftware.manager.util.Utilidades;
import br.com.srcsoftware.modular.manager.empresa.EmpresaDTO;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.report.abstracts.MasterReport;
import br.com.srcsoftware.modular.manager.utilidades.Parametrizacao;
import br.com.srcsoftware.sistema.pessoa.funcionario.horaextra.HoraExtraDTO;

public final class HoraExtraReport extends MasterReport{

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
	public HoraExtraReport( String nomePastaOrigemRelatorios, String camposFitragem, String versaoSistema ) throws ApplicationException{
		super( nomePastaOrigemRelatorios, camposFitragem, versaoSistema );
	}

	/**
	 * M�todo respons�vel por gerar e exibir o relatorio.
	 * 
	 * @param HttpServletResponse response - Response usado para "escrever" o relatorio na tela.
	 * @param ArrayList< ContratanteHCDTO > lista = Lista contendo todos os objetos cujos dados serao
	 *        exibidos no relatorio.
	 * @throws ApplicationException
	 * @throws ReportException
	 * @throws IOException
	 */
	public void gerarRelatorio( HttpServletResponse response, ArrayList< HoraExtraDTO > horasExtras, String camposPesquisa ) throws ApplicationException, IOException {

		EmpresaDTO empresa = horasExtras.get( 0 ).getColaborador().getEmpresa();
		
		calcularTotais( horasExtras );
		
		String pathDestinoServidor = Parametrizacao.parametros.get( Parametrizacao.URL_CONTEXT_PATH ) + File.separator + "temp" + File.separator + empresa.getPrefixoImagem();

		String pathImagem = String.join( File.separator, pathDestinoServidor, empresa.getImagem() );
		Path caminho = Paths.get(pathImagem);
        
        if (Files.exists(caminho)) {
            System.out.println("O arquivo existe! :" + pathImagem);
        } else {
            System.out.println("O arquivo NÃO foi encontrado. :" + pathImagem);
            pathImagem = Parametrizacao.parametros.get( Parametrizacao.URL_CONTEXT_PATH ) + File.separator + "temp" + File.separator + "empty.png";
        }

		this.parametros.put( "LOGO_PATH", pathImagem );
		this.parametros.put( "CAMPOS_PESQUISA", camposPesquisa );

		byte[ ] reportByte = this.facade.gerarRelatorioPDF( "horasextras", horasExtras, this.reportPath, this.parametros );

		// Chamando o metodo responsavel por exibir na tela o relatorio gerado.
		Utilidades.writeHtmlToResponse( response, reportByte );// Nao muda nunca
	}
	
	public void calcularTotais( List< HoraExtraDTO > encontradosParaTotais ) {

		BigDecimal totalHoras = BigDecimal.ZERO;
		BigDecimal total50 = BigDecimal.ZERO;
		BigDecimal total100 = BigDecimal.ZERO;
		BigDecimal totalDomingo = BigDecimal.ZERO;
		BigDecimal totalFeriado = BigDecimal.ZERO;

		for ( HoraExtraDTO corrente : encontradosParaTotais ) {

			BigDecimal quantidadeHoras = br.com.srcsoftware.modular.manager.utilidades.Utilidades.parseBigDecimal( corrente.getQuantidadeHoras() );
			totalHoras = totalHoras.add( quantidadeHoras );

			BigDecimal quantidade50 = br.com.srcsoftware.modular.manager.utilidades.Utilidades.parseBigDecimal( corrente.getQuantidade50Porcento() );
			total50 = total50.add( quantidade50 );

			BigDecimal quantidade100 = br.com.srcsoftware.modular.manager.utilidades.Utilidades.parseBigDecimal( corrente.getQuantidade100Porcento() );
			total100 = total100.add( quantidade100 );
			
			BigDecimal quantidadeDomingo = br.com.srcsoftware.modular.manager.utilidades.Utilidades.parseBigDecimal( corrente.getQuantidadeDomingo() );
			totalDomingo = totalDomingo.add( quantidadeDomingo );
			
			BigDecimal quantidadeFeriado = br.com.srcsoftware.modular.manager.utilidades.Utilidades.parseBigDecimal( corrente.getQuantidadeFeriado() );
			totalFeriado = totalFeriado.add( quantidadeFeriado );
		}

		this.parametros.put( "TOTAL_HORAS", br.com.srcsoftware.modular.manager.utilidades.Utilidades.parseBigDecimal( totalHoras ).replace(",00", "") );
		this.parametros.put( "TOTAL_50", br.com.srcsoftware.modular.manager.utilidades.Utilidades.parseBigDecimal( total50 ).replace(",00", "") );
		this.parametros.put( "TOTAL_100", br.com.srcsoftware.modular.manager.utilidades.Utilidades.parseBigDecimal( total100 ).replace(",00", "") );
		this.parametros.put( "TOTAL_DOMINGO", br.com.srcsoftware.modular.manager.utilidades.Utilidades.parseBigDecimal( totalDomingo ).replace(",00", "") );
		this.parametros.put( "TOTAL_FERIADO", br.com.srcsoftware.modular.manager.utilidades.Utilidades.parseBigDecimal( totalFeriado ).replace(",00", "") );
	}
}