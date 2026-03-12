package testa.tela;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.junit.Test;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.tela.TelaDTO;
import br.com.srcsoftware.modular.manager.usuario.tela.TelaFacade;

public class TestaBackend{

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
		this.camposOrders.put( "nome", "DESC" );

		long inicio = System.currentTimeMillis();

		for ( int i = 0; i < 500; i++ ) {
			this.testar( this.paginacao, this.camposOrders, i );
		}

		this.executarEmLote( 500 );

		long termino = System.currentTimeMillis();

		System.err.println( "Executado em " + ( ( termino - inicio ) / 1000.0 ) + " segundos. Erros: " + this.erros );
	}

	private void testar( Paginacao paginacao, HashMap< String, String > camposOrders, int i ) {

		try {

			/** Criando o Objeto a ser testado */
			this.logger.info( "Criando o Objeto de teste: " + TelaDTO.class );
			TelaDTO dto = new TelaDTO();
			dto.setNome( "Empresa-" + i );

			this.logger.info( "Criando a instancia do FACADE: " + TelaFacade.class );
			TelaFacade facade = TelaFacade.getInstance();

			/** Inserindo */
			this.logger.info( "Inserindo: " + dto.getClass() );
			facade.inserir( dto );

			/** Consultando todos */
			this.logger.info( "Consultando todos: " + dto.getClass() );
			System.out.println( facade.filtrar() );

			/** Consultando por id */
			this.logger.info( "Consultando por id: " + dto.getId() );
			TelaDTO dtoAlterar = facade.filtrarPorId( dto.getId() );
			this.logger.info( "Exibindo o encontrado com id: " + dto.getId() );
			System.out.println( "Encontrado:" + dtoAlterar );

			/** Alterando */
			dtoAlterar.setNome( "Funcionario-" + i );
			this.logger.info( "Alterando de " + dto.getNome() + " para " + dtoAlterar.getNome() );
			facade.alterar( dtoAlterar );

			/** Consultando todos */
			this.logger.info( "Consultando todos: " + dto.getClass() );
			System.out.println( facade.filtrar( Paginacao.NAO, HibernateConnection.SEM_ORDENACAO, null ) );

			/** Consultando por algum altributo */
			this.logger.info( "Consultando por algum altributo: " + TelaDTO.class );
			TelaDTO dtoFiltrar = new TelaDTO();
			dtoFiltrar.setNome( "Abec" );
			this.logger.info( "Consultando por algum altributo: " + dtoFiltrar );
			System.out.println( facade.filtrar( this.paginacao, this.camposOrders, dtoFiltrar ) );

			/** Consultando por algum altributo de relacionamento (quando tiver) */
			/*this.logger.info( "Consultando por algum altributo de relacionamento: " + dtoFiltrar.getEndereco().getClass() );
			dtoFiltrar = new TelaDTO();
			dtoFiltrar.getEndereco().setCidade( "Ourinhos" );
			System.out.println( facade.filtrar( this.paginacao, this.camposOrders, dtoFiltrar ) );*/

			/** Consultando por id */
			this.logger.info( "Consultando por id: " + dto.getId() );
			TelaDTO dtoExcluir = facade.filtrarPorId( dto.getId() );
			this.logger.info( "Exibindo o encontrado com id: " + dto.getId() );
			System.out.println( "Para Excluir:" + dtoExcluir );

			/** Excluindo */
			this.logger.info( "Excluindo: " + dtoExcluir );
			facade.excluir( dtoExcluir );

			/** Consultando todos */
			this.logger.info( "Consultando todos: " + dto.getClass() );
			System.out.println( facade.filtrar( Paginacao.NAO, HibernateConnection.SEM_ORDENACAO, null ) );

		} catch ( ApplicationException e ) {
			this.erros++;
			e.printStackTrace();
			this.logger.error( e.getMessage(), e );
		}
	}

	private void executarEmLote( final int MAXIMO ) {
		try {
			this.logger.info( "Testando execução em Lote" );
			TelaFacade facade = TelaFacade.getInstance();

			for ( int i = 0; i < MAXIMO; i++ ) {
				TelaDTO lote = new TelaDTO();
				lote.setNome( "Lote-" + i );
				/** Inserindo em lote */
				this.logger.info( "Inserindo em lote: " + lote.getClass() );
				facade.inserir( lote );
			}

			this.logger.info( "Excluindo em lote: " + TelaDTO.class );
			ArrayList< TelaDTO > telasExcluir = facade.filtrar( Paginacao.NAO, HibernateConnection.SEM_ORDENACAO, null );
			System.out.println( "Excuir em Lotes:\n" + telasExcluir );
			for ( TelaDTO excluir : telasExcluir ) {
				facade.excluir( excluir );
			}
		} catch ( ApplicationException e ) {
			this.erros++;
			e.printStackTrace();
			this.logger.error( e.getMessage(), e );
		}
	}
}
