package testa.tela;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.junit.Test;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.enums.TipoFormaPagamentoEnum;
import br.com.srcsoftware.modular.financeiro.titulo.titulopagar.TituloPagarDTO;
import br.com.srcsoftware.modular.financeiro.titulo.titulopagar.TituloPagarFacade;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;

public class TestaBackendTituloPagar{

	private transient Logger logger = Logger.getLogger( this.getClass().getName() );

	private int erros;

	private HashMap< String, String > camposOrders;
	private Paginacao paginacao;

	@Test
	public void executar() {

		this.paginacao = new Paginacao();
		this.paginacao.setPaginaCorrente( 1 );
		this.paginacao.setPaginaInicial( 1 );
		this.paginacao.setPaginaFinal( 1 );
		this.paginacao.setTotalRegistros( 0 );

		this.camposOrders = new HashMap< String, String >();
		this.camposOrders.put( "numero", "DESC" );

		long inicio = System.currentTimeMillis();

		for ( int i = 0; i < 10; i++ ) {
			this.testar( this.paginacao, this.camposOrders, i );
		}

		long termino = System.currentTimeMillis();

		System.err.println( "Executado em " + ( ( termino - inicio ) / 1000.0 ) + " segundos. Erros: " + this.erros );
	}

	private void testar( Paginacao paginacao, HashMap< String, String > camposOrders, int i ) {

		try {

			this.logger.info( "Criando a instancia do FACADE: " + TituloPagarFacade.class );
			TituloPagarFacade facade = TituloPagarFacade.getInstance();

			/** Consultando todos */
			this.logger.info( "Consultando todos: " + TituloPagarDTO.class );
			System.out.println( facade.filtrar( Paginacao.NAO, HibernateConnection.SEM_ORDENACAO, null ) );

			/** Consultando por algum altributo */
			this.logger.info( "Consultando por algum altributo: " + TituloPagarDTO.class );
			TituloPagarDTO dtoFiltrar = new TituloPagarDTO();
			dtoFiltrar.setTipo( TipoFormaPagamentoEnum.TIPO_A_VISTA.getValor() );
			this.logger.info( "Consultando por algum altributo: " + dtoFiltrar );
			System.out.println( facade.filtrar( this.paginacao, this.camposOrders, null, dtoFiltrar ) );

			/** Consultando por algum altributo de relacionamento (quando tiver) */
			this.logger.info( "Consultando por algum altributo de relacionamento: " + dtoFiltrar.getFornecedor().getClass() );
			dtoFiltrar = new TituloPagarDTO();
			dtoFiltrar.getFornecedor().setId( "1" );
			System.out.println( facade.filtrar( this.paginacao, this.camposOrders, null, dtoFiltrar ) );

			/** Consultando por id */
			TituloPagarDTO dto = new TituloPagarDTO();
			dto.setId( "1" );
			this.logger.info( "Consultando por id: " + dto.getId() );
			dto = facade.filtrarPorId( dto.getId() );
			this.logger.info( "Exibindo o encontrado com id: " + dto );

			/** Consultando por Between */
			this.logger.info( "Consultando por Between: " + TituloPagarDTO.class );

			HashMap< String, ArrayList< Object > > camposBetween = new HashMap< String, ArrayList< Object > >();

			LocalDate dataInicialPesquisa = LocalDate.parse( "01/01/2018", DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) );
			LocalDate dataFinalPesquisa = LocalDate.parse( "01/02/2018", DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) );

			camposBetween.put( "dataVencimento", new ArrayList<>( Arrays.asList( dataInicialPesquisa, dataFinalPesquisa ) ) );

			TituloPagarDTO dtoFiltrarBetween = new TituloPagarDTO();
			dtoFiltrar.setTipo( TipoFormaPagamentoEnum.TIPO_A_VISTA.getValor() );
			this.logger.info( "Consultando por Between: " + dtoFiltrarBetween );
			System.out.println( facade.filtrar( this.paginacao, this.camposOrders, camposBetween, dtoFiltrar ) );

		} catch ( ApplicationException e ) {
			this.erros++;
			e.printStackTrace();
			this.logger.error( e.getMessage(), e );
		}
	}

}
