package br.com.srcsoftware.sistema.safra.relatorios;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import br.com.srcsoftware.modular.manager.empresa.EmpresaDTO;
import br.com.srcsoftware.modular.manager.empresa.EmpresaFacade;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.utilidades.Constantes;
import br.com.srcsoftware.modular.manager.utilidades.Parametrizacao;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.modular.queryview.QueryViewReport;
import br.com.srcsoftware.sistema.relatorios.RelacaoSafraSetorCulturaVariedadeReport;

public class RelacaoSafraSetorCulturaVariedadeServiceImpl implements RelacaoSafraSetorCulturaVariedadeInterface{

	private transient Logger logger = Logger.getLogger( this.getClass().getName() );

	private RelacaoSafraSetorCulturaVariedadeDAOInterface daoInterface;

	public RelacaoSafraSetorCulturaVariedadeServiceImpl(){
		this.daoInterface = new RelacaoSafraSetorCulturaVariedadeDAOImpl();
	}

	@Override
	public List< RelacaoSafraSetorCulturaVariedadePOJO > filtrarRelacao( String nomeSafra, String nomeCultura ) throws ApplicationException {
		try {

			URL url = null;
			HashMap< String, Object > parametros = new HashMap<>();

			parametros.put( "nomeSafraParam", Long.valueOf( nomeSafra ) );
			if ( Utilidades.isNuloOuVazio( nomeCultura ) ) {
				parametros.put( "nomeCulturaParam", "%%" );
			} else {
				parametros.put( "nomeCulturaParam", nomeCultura );
			}

			url = this.getClass().getResource( "/relatorios/relacaoSafraSetorCulturaVariedade.txt" );

			Path origem = Paths.get( url.toURI() );
			List< String > linhas = Files.readAllLines( origem, Charset.forName( "ISO-8859-1" ) );

			final StringBuffer SQL = new StringBuffer();

			for ( String linhaQueryCorrente : linhas ) {
				SQL.append( linhaQueryCorrente.concat( " " ) );
			}

			List< RelacaoSafraSetorCulturaVariedadePOJO > encontrados = this.daoInterface.filtrarRelacao( SQL, parametros );

			return encontrados;
		} catch ( ApplicationException e ) {
			this.logger.error( e.getMessage(), e );
			throw e;
		} catch ( Exception e ) {

			this.logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void gerarPDF( HttpServletResponse response, String idEmpresa, List< RelacaoSafraSetorCulturaVariedadePOJO > encontrados, String safra, String cultura ) throws ApplicationException {

		try {

			EmpresaDTO empresa = EmpresaFacade.getInstance().filtrarPorId( idEmpresa );

			String versaoSistema = Constantes.VERSAO_SISTEMA;

			RelacaoSafraSetorCulturaVariedadeReport report = new RelacaoSafraSetorCulturaVariedadeReport( "safra/relacaosafrasetorculturavariedade", getCamposPesquisa( safra, cultura ), versaoSistema );

			BigDecimal totalAlqueire = BigDecimal.ZERO;

			for ( RelacaoSafraSetorCulturaVariedadePOJO pojoCorrente : encontrados ) {
				BigDecimal area = pojoCorrente.getAreaAlqueire();

				totalAlqueire = totalAlqueire.add( area );
			}

			report.gerarRelatorioPDF( response, empresa, encontrados, Utilidades.parseBigDecimal( totalAlqueire ) );

		} catch ( ApplicationException e ) {
			this.logger.error( e.getMessage(), e );
			throw e;
		} catch ( Exception e ) {
			this.logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}

	}

	@Override
	public void gerarXLS( HttpServletResponse response, String idEmpresa, List< RelacaoSafraSetorCulturaVariedadePOJO > encontrados, String safra, String cultura ) throws ApplicationException {

		try {

			EmpresaDTO empresa = EmpresaFacade.getInstance().filtrarPorId( idEmpresa );

			String versaoSistema = Constantes.VERSAO_SISTEMA;

			RelacaoSafraSetorCulturaVariedadeReport report = new RelacaoSafraSetorCulturaVariedadeReport( "safra/relacaosafrasetorculturavariedade", getCamposPesquisa( safra, cultura ), versaoSistema );

			BigDecimal totalAlqueire = BigDecimal.ZERO;

			for ( RelacaoSafraSetorCulturaVariedadePOJO pojoCorrente : encontrados ) {
				BigDecimal area = pojoCorrente.getAreaAlqueire();

				totalAlqueire = totalAlqueire.add( area );
			}

			report.gerarRelatorioXLS( response, empresa, encontrados, Utilidades.parseBigDecimal( totalAlqueire ) );

		} catch ( ApplicationException e ) {
			this.logger.error( e.getMessage(), e );
			throw e;
		} catch ( Exception e ) {
			this.logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}

	}

	private String getCamposPesquisa( String safra, String cultura ) {
		StringBuilder campos = new StringBuilder();

		if ( !Utilidades.isNuloOuVazio( safra ) ) {
			campos.append( "Safra: ".concat( safra ).concat( " / " ) );
		}

		if ( !Utilidades.isNuloOuVazio( cultura ) ) {
			campos.append( "Cultura: ".concat( cultura ).concat( " / " ) );
		}

		return campos.toString();
	}

	@Override
	public void exportarXLS( ArrayList< RelacaoSafraSetorCulturaVariedadePOJO > encontrados, HttpServletResponse response, String nomeArquivo ) throws ApplicationException {

		if ( Utilidades.isNuloOuVazio( encontrados ) ) {
			throw new ApplicationException( "N�o consta nenhum dado para a exportação!" );
		}

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet firstSheet = workbook.createSheet( "Relação Safra-Setor-Variedades" );

		// Fonts
		HSSFFont headerFont = workbook.createFont();
		headerFont.setBold( true );
		headerFont.setFontName( "Arial" );
		headerFont.setFontHeightInPoints( (short) 12 );

		HSSFFont contentFont = workbook.createFont();
		contentFont.setBold( false );
		contentFont.setColor( HSSFColor.HSSFColorPredefined.BLACK.getIndex() );
		contentFont.setFontName( "Arial" );
		contentFont.setFontHeightInPoints( (short) 10 );

		// Generate styles
		HSSFCellStyle headerStyleEsquerda = workbook.createCellStyle();
		headerStyleEsquerda.setFont( headerFont );
		headerStyleEsquerda.setAlignment( HorizontalAlignment.LEFT );
		headerStyleEsquerda.setWrapText( false );

		HSSFCellStyle headerStyleDireita = workbook.createCellStyle();
		headerStyleDireita.setFont( headerFont );
		headerStyleDireita.setAlignment( HorizontalAlignment.RIGHT );
		headerStyleDireita.setWrapText( false );

		HSSFCellStyle headerStyleCentro = workbook.createCellStyle();
		headerStyleCentro.setFont( headerFont );
		headerStyleCentro.setAlignment( HorizontalAlignment.CENTER );
		headerStyleCentro.setWrapText( false );

		HSSFCellStyle conteudoStyleEsquerda = workbook.createCellStyle();
		conteudoStyleEsquerda.setFont( contentFont );
		conteudoStyleEsquerda.setAlignment( HorizontalAlignment.LEFT );
		conteudoStyleEsquerda.setVerticalAlignment( VerticalAlignment.CENTER );
		conteudoStyleEsquerda.setWrapText( false );

		HSSFCellStyle conteudoStyleDireita = workbook.createCellStyle();
		conteudoStyleDireita.setFont( contentFont );
		conteudoStyleDireita.setAlignment( HorizontalAlignment.RIGHT );
		conteudoStyleDireita.setVerticalAlignment( VerticalAlignment.CENTER );
		conteudoStyleDireita.setWrapText( false );

		HSSFCellStyle conteudoStyleCentro = workbook.createCellStyle();
		conteudoStyleCentro.setFont( contentFont );
		conteudoStyleCentro.setAlignment( HorizontalAlignment.CENTER );
		conteudoStyleCentro.setVerticalAlignment( VerticalAlignment.CENTER );
		conteudoStyleCentro.setWrapText( false );

		// Generate styles das celulas de Conte�do Monetario
		HSSFDataFormat format = workbook.createDataFormat();
		HSSFCellStyle conteudoStyleMoeda = workbook.createCellStyle();
		conteudoStyleMoeda.setFont( contentFont );
		conteudoStyleMoeda.setAlignment( HorizontalAlignment.RIGHT );
		conteudoStyleMoeda.setWrapText( false );
		String formato = "R$ #,##0.00_);[Red](R$ #,##0.00)";
		conteudoStyleMoeda.setDataFormat( format.getFormat( formato ) );

		// Generate styles das celulas de Conte�do Monetario
		HSSFCellStyle conteudoStyleNumerico = workbook.createCellStyle();
		conteudoStyleNumerico.setFont( contentFont );
		conteudoStyleNumerico.setAlignment( HorizontalAlignment.RIGHT );
		conteudoStyleNumerico.setWrapText( false );
		String formatoNumerico = "#,##0.00000_);[Red](#,##0.00000)";
		conteudoStyleNumerico.setDataFormat( format.getFormat( formatoNumerico ) );

		HSSFCellStyle conteudoStyleNumericoNegrito = workbook.createCellStyle();
		conteudoStyleNumericoNegrito.setFont( headerFont );
		conteudoStyleNumericoNegrito.setAlignment( HorizontalAlignment.RIGHT );
		conteudoStyleNumericoNegrito.setWrapText( false );
		conteudoStyleNumericoNegrito.setDataFormat( format.getFormat( formatoNumerico ) );

		try {
			String pathDestinoServidor = String.join( File.separator, Parametrizacao.parametros.get( Parametrizacao.URL_CONTEXT_PATH ).toString(), "temp", "relatorios", "XLSs" );

			File f = new File( pathDestinoServidor );
			if ( !f.exists() ) {
				logger.info( "Criando pasta(s) destino:  " + pathDestinoServidor );
				f.mkdirs();
			}

			// Parei aqui. Criar uma pasta padra no servidor para estes arquivos "Temporarios", fazer e exclus�o destes arquivos e solicitar o nome do arquivo ao usuario
			File file = new File( pathDestinoServidor.concat( File.separator ).concat( nomeArquivo.concat( ".xls" ) ) );

			/** CABE�ALHO */
			HSSFRow cabecalho = firstSheet.createRow( 0 );

			HSSFCell cabecalhoPlanilha = cabecalho.createCell( 0 );
			cabecalhoPlanilha.setCellValue( "Safra" );
			cabecalhoPlanilha.setCellStyle( headerStyleCentro );

			HSSFCell cabecalhoGuia = cabecalho.createCell( 1 );
			cabecalhoGuia.setCellValue( "Setor" );
			cabecalhoGuia.setCellStyle( headerStyleCentro );

			HSSFCell cabecalhoStatus = cabecalho.createCell( 2 );
			cabecalhoStatus.setCellValue( "Variedade" );
			cabecalhoStatus.setCellStyle( headerStyleEsquerda );

			HSSFCell cabecalhoUnimedPagadora = cabecalho.createCell( 3 );
			cabecalhoUnimedPagadora.setCellValue( "Cultura" );
			cabecalhoUnimedPagadora.setCellStyle( headerStyleEsquerda );

			HSSFCell cabecalhoCarteiraBeneficiario = cabecalho.createCell( 4 );
			cabecalhoCarteiraBeneficiario.setCellValue( "�rea em Alqueire" );
			cabecalhoCarteiraBeneficiario.setCellStyle( headerStyleDireita );

			BigDecimal totalAlqueire = BigDecimal.ZERO;

			/** DADOS */
			int numeroLinha = 0;
			for ( int i = 0; i < encontrados.size(); i++ ) {
				numeroLinha = numeroLinha + ( 1 );
				HSSFRow linha = firstSheet.createRow( ( numeroLinha ) );

				// Safra
				HSSFCell celulaSafra = linha.createCell( 0 );
				celulaSafra.setCellValue( encontrados.get( i ).getSafra() );
				celulaSafra.setCellStyle( conteudoStyleCentro );

				// Setor
				HSSFCell celulaSetor = linha.createCell( 1 );
				celulaSetor.setCellValue( encontrados.get( i ).getSetor() );
				celulaSetor.setCellStyle( conteudoStyleEsquerda );

				// Variedade
				HSSFCell celulaVariedade = linha.createCell( 2 );
				celulaVariedade.setCellValue( encontrados.get( i ).getVariedade() );
				celulaVariedade.setCellStyle( conteudoStyleEsquerda );

				// Cultura	
				HSSFCell celulaCultura = linha.createCell( 3 );
				celulaCultura.setCellValue( encontrados.get( i ).getCultura() );
				celulaCultura.setCellStyle( conteudoStyleEsquerda );

				// �rea em Alqueire
				HSSFCell celulaAreaAlqueire = linha.createCell( 4 );
				celulaAreaAlqueire.setCellStyle( conteudoStyleNumerico );
				celulaAreaAlqueire.setCellValue( Utilidades.parseBigDecimalOrZero( encontrados.get( i ).getAreaAlqueireToString() ).doubleValue() );

				/*CellRangeAddress mergedRegionCompetencia = new CellRangeAddress( numeroLinha - 1, numeroLinha, 0, 0 ); // row 1 and 2 col A 
				firstSheet.addMergedRegion( mergedRegionCompetencia );
				CellRangeAddress mergedRegionPrestador = new CellRangeAddress( numeroLinha - 1, numeroLinha, 1, 1 ); // row 1 and 2 col A 
				firstSheet.addMergedRegion( mergedRegionPrestador );
				CellRangeAddress mergedRegionStatus = new CellRangeAddress( numeroLinha - 1, numeroLinha, 2, 2 ); // row 1 and 2 col A 
				firstSheet.addMergedRegion( mergedRegionStatus );
				CellRangeAddress mergedRegionObservacao = new CellRangeAddress( numeroLinha - 1, numeroLinha, 3, 3 ); // row 1 and 2 col A 
				firstSheet.addMergedRegion( mergedRegionObservacao );*/

				BigDecimal area = encontrados.get( i ).getAreaAlqueire();

				totalAlqueire = totalAlqueire.add( area );
			}

			// �rea em Alqueire
			numeroLinha = numeroLinha + ( 1 );
			HSSFRow linha = firstSheet.createRow( ( numeroLinha ) );
			HSSFCell linhaTotalAlqueire = linha.createCell( 4 );
			linhaTotalAlqueire.setCellStyle( conteudoStyleNumericoNegrito );
			linhaTotalAlqueire.setCellValue( totalAlqueire.doubleValue() );

			autosizeColumns( firstSheet, 16 );

			workbook.write( file );
			workbook.close();

			new QueryViewReport().exportarXLS( response, file );

			System.out.println( "Success!!" );

		} catch ( Exception e ) {
			e.printStackTrace();
			System.out.println( "Erro ao exportar arquivo" );
		}
	}

	public static int MIN_COL_WIDTH = 6 << 8;
	public static int MAX_COL_WIDTH = 120 << 8;

	public static void autosizeColumns( Sheet sheet, int numColumns ) {
		for ( int i = 0; i < numColumns; i++ ) {
			sheet.autoSizeColumn( i );
			int cw = sheet.getColumnWidth( i ) * 1;
			sheet.setColumnWidth( i, Math.max( Math.min( cw, MAX_COL_WIDTH ), MIN_COL_WIDTH ) );
		}
	}

	public static void autosizeColumns( Sheet sheet, int numColum, int tamanho ) {
		tamanho *= 256;/** Faz com que o valor seja bem proximo. Ex: tamanho:10 fica 9,29 */
		sheet.autoSizeColumn( numColum );
		sheet.setColumnWidth( numColum, Math.max( Math.min( tamanho, MAX_COL_WIDTH ), MIN_COL_WIDTH ) );
	}

	public short getIndiceHSSFColor( int r, int g, int b, HSSFWorkbook workbook ) {
		/** Define uma COR GRB */
		HSSFPalette palette = workbook.getCustomPalette();
		palette.setColorAtIndex( palette.getColor( 36 ).getIndex(), (byte) r, (byte) g, (byte) b ); //amarelo
		return ( palette.getColor( 36 ).getIndex() );
	}
}