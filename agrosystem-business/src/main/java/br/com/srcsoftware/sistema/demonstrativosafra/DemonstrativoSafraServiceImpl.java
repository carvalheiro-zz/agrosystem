package br.com.srcsoftware.sistema.demonstrativosafra;

import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import br.com.srcsoftware.modular.manager.empresa.EmpresaDTO;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.utilidades.Constantes;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.demonstrativosafra.custodireto.insumo.CustoDiretoInsumoPOJO;
import br.com.srcsoftware.sistema.demonstrativosafra.custodireto.insumo.sintetico.CustoDiretoInsumoSinteticoPOJO;
import br.com.srcsoftware.sistema.demonstrativosafra.custodireto.insumo.sintetico.DemonstrativoSafraSinteticoReport;

public class DemonstrativoSafraServiceImpl implements DemonstrativoSafraServiceInterface{

	private transient Logger logger = Logger.getLogger( this.getClass().getName() );

	private DemonstrativoSafraDAOInterface daoInterface;

	public DemonstrativoSafraServiceImpl(){
		this.daoInterface = new DemonstrativoSafraDAOImpl();
	}

	@Override
	public List< CustoDiretoInsumoPOJO > filtrarDemonstrativoSafraCustosDiretos( String idSafra, String idSetor, String idCultura, String classificacao ) throws ApplicationException {
		try {

			String tipoConsulta = null;

			if ( !Utilidades.isNuloOuVazio( idSafra ) && !Utilidades.isNuloOuVazio( idSetor ) ) {
				tipoConsulta = "Setor";
			} else if ( !Utilidades.isNuloOuVazio( idSafra ) && !Utilidades.isNuloOuVazio( idCultura ) ) {
				tipoConsulta = "Cultura";
			} else if ( !Utilidades.isNuloOuVazio( idSafra ) && ( Utilidades.isNuloOuVazio( idCultura ) && Utilidades.isNuloOuVazio( idSetor ) ) ) {
				tipoConsulta = "Tudo";
			}

			URL url = null;
			HashMap< String, Object > parametros = new HashMap<>();

			parametros.put( "classificacaoParam", classificacao );

			if ( tipoConsulta.equals( "Setor" ) ) {
				url = this.getClass().getResource( "/selects_demonstrativo/setor.txt" );

				parametros.put( "idSafraParam", Long.valueOf( idSafra ) );
				parametros.put( "idSetorParam", Long.valueOf( idSetor ) );
			} else if ( tipoConsulta.equals( "Cultura" ) ) {
				url = this.getClass().getResource( "/selects_demonstrativo/cultura.txt" );

				parametros.put( "idSafraParam", Long.valueOf( idSafra ) );
				parametros.put( "idCulturaParam", Long.valueOf( idCultura ) );
			} else if ( tipoConsulta.equals( "Tudo" ) ) {
				url = this.getClass().getResource( "/selects_demonstrativo/tudo.txt" );

				parametros.put( "idSafraParam", Long.valueOf( idSafra ) );
			}

			Path origem = Paths.get( url.toURI() );
			List< String > linhas = Files.readAllLines( origem, Charset.forName( "ISO-8859-1" ) );

			final StringBuffer SQL = new StringBuffer();

			for ( String linhaQueryCorrente : linhas ) {
				SQL.append( linhaQueryCorrente );
			}

			List< CustoDiretoInsumoPOJO > encontrados = this.daoInterface.filtrarDemonstrativoSafraCustosDiretos( SQL, parametros );

			BigDecimal soma = BigDecimal.ZERO;
			for ( CustoDiretoInsumoPOJO corrente : encontrados ) {
				soma = soma.add( corrente.getCustoTotal() );// Não arredondado, com 5 casas decimais				
			}
			soma = soma.setScale( Utilidades.SCALE_CALCULOS, BigDecimal.ROUND_HALF_EVEN );
			for ( CustoDiretoInsumoPOJO corrente : encontrados ) {
				BigDecimal custoTotal = corrente.getCustoTotal().setScale( Utilidades.SCALE_CALCULOS, BigDecimal.ROUND_HALF_EVEN );

				BigDecimal percentualCusto = ( custoTotal.multiply( new BigDecimal( "100" ) ).divide( soma, Utilidades.SCALE_CALCULOS, BigDecimal.ROUND_HALF_EVEN ) );
				corrente.setPercentualCusto( percentualCusto );
			}

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
	public void gerarPdfSintetico( HttpServletResponse response,
	        EmpresaDTO empresa,
	        List< CustoDiretoInsumoSinteticoPOJO > sinteticos,
	        String cabecalhoInformativo,
	        String totalArea,
	        String totalCustoPorAlqueire,
	        String totalCustoArea ) throws ApplicationException {

		try {
			String versaoSistema = Constantes.VERSAO_SISTEMA;

			DemonstrativoSafraSinteticoReport report = new DemonstrativoSafraSinteticoReport( "demonstrativosafrasintetico", null, versaoSistema );

			report.gerarRelatorio( response, empresa, sinteticos, cabecalhoInformativo, totalArea, totalCustoPorAlqueire, totalCustoArea );

		} catch ( ApplicationException e ) {
			this.logger.error( e.getMessage(), e );
			throw e;
		} catch ( Exception e ) {
			this.logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}

	}
}