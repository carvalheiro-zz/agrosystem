package br.com.srcsoftware.sistema.demonstrativovenda;

import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.utilidades.DateUtil;
import br.com.srcsoftware.sistema.silo.contratovenda.InformacoesRestanteEntregarPOJO;

public class DemonstrativoVendaServiceImpl implements DemonstrativoVendaServiceInterface{

	private transient Logger logger = Logger.getLogger( this.getClass().getName() );

	private DemonstrativoVendaDAOInterface daoInterface;

	public DemonstrativoVendaServiceImpl(){
		this.daoInterface = new DemonstrativoVendaDAOImpl();
	}

	@Override
	public List< InformacoesRestanteEntregarPOJO > filtrarDemonstrativoVenda( String idCultura ) throws ApplicationException {
		try {

			URL url = null;
			HashMap< String, Object > parametros = new HashMap<>();

			parametros.put( "idCulturaParam", Long.valueOf( idCultura ) );
			parametros.put( "dataInicialParam", DateUtil.getPrimeiroDiaMesFromData( LocalDate.now().minusMonths( 120 ) ) );
			parametros.put( "dataFinalParam", DateUtil.getUltimoDiaMesFromData( LocalDate.now().plusMonths( 36 ) ) );

			/*ArrayList< Long > ids = new ArrayList<>();
			for ( String idCorrente : idSafra ) {
				ids.add( Long.valueOf( idCorrente ) );
			}
			parametros.put( "idsSafrasParam", ids );*/

			url = this.getClass().getResource( "/selects_demonstrativo/venda.txt" );

			Path origem = Paths.get( url.toURI() );
			List< String > linhas = Files.readAllLines( origem, Charset.forName( "ISO-8859-1" ) );

			final StringBuffer SQL = new StringBuffer();

			for ( String linhaQueryCorrente : linhas ) {
				SQL.append( linhaQueryCorrente.concat( " " ) );
			}

			List< InformacoesRestanteEntregarPOJO > encontrados = this.daoInterface.filtrarDemonstrativoVenda( SQL, parametros );

			return encontrados;
		} catch ( ApplicationException e ) {
			this.logger.error( e.getMessage(), e );
			throw e;
		} catch ( Exception e ) {

			this.logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

}