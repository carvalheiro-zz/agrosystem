package testa_refatoracao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.sistema.demonstrativosafra.DemonstrativoSafraDAOImpl;
import br.com.srcsoftware.sistema.demonstrativosafra.custodireto.insumo.CustoDiretoInsumoPOJO;
import br.com.srcsoftware.sistema.estoque.estoque.EstoqueDAOImpl;
import br.com.srcsoftware.sistema.estoque.estoque.EstoquePO;
import br.com.srcsoftware.sistema.produto.marca.MarcaDAOImpl;
import br.com.srcsoftware.sistema.produto.marca.MarcaPO;

public class TestaRefatoracaoDAO{

	@Test
	public void executar() {

		try {
			long inicio = System.currentTimeMillis();
			for ( int i = 0; i < 1; i++ ) {
				filtrarMarca( null );
				//inserirMarca();
				filtrarMarca( null );

				filtrarEstoque();
				calcularValoresRestantesEntregar( 134l, 4l );

				demonstrativoFiltrarPorSetor( 4L, 13L );
			}
			long termino = System.currentTimeMillis();

			double resultado = ( ( termino - inicio ) / 1000.0 );

			System.out.println( "Tempo: " + resultado );
		} catch ( ApplicationException e ) {
			e.printStackTrace();
		}

	}

	private void filtrarMarca( String nome ) throws ApplicationException {

		MarcaPO poFilter = new MarcaPO();
		poFilter.setNome( nome );

		MarcaDAOImpl dao = new MarcaDAOImpl();

		HashMap< String, String > camposOrders = new HashMap<>();
		camposOrders.put( "nome", "ASC" );

		List< MarcaPO > encontrados = dao.filtrar( Paginacao.NAO, camposOrders, poFilter );

		for ( MarcaPO telaCorrente : encontrados ) {
			System.out.println( telaCorrente );
		}

		System.out.println( "Encontrados:" + encontrados.size() );
	}

	private void inserirMarca() throws ApplicationException {

		HibernateConnection hibernate = new HibernateConnection();
		try {

			hibernate.iniciarTransacao();

			MarcaDAOImpl dao = new MarcaDAOImpl();

			for ( int i = 0; i < 10; i++ ) {
				MarcaPO poFilter = new MarcaPO();
				poFilter.setNome( "Marca" + i );

				dao.inserir( hibernate, poFilter );

			}
			hibernate.commitTransacao();
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();
			e.printStackTrace();
		}

	}

	private void filtrarEstoque() throws ApplicationException {

		EstoquePO poFilter = new EstoquePO();

		EstoqueDAOImpl dao = new EstoqueDAOImpl();

		HashMap< String, String > camposOrders = new HashMap<>();
		camposOrders.put( "produto.nome", "ASC" );

		List< EstoquePO > encontrados = dao.filtrar( Paginacao.NAO, camposOrders, poFilter );

		for ( EstoquePO estoqueCorrente : encontrados ) {
			System.out.println( estoqueCorrente );
		}

		System.out.println( "Encontrados:" + encontrados.size() );
	}

	private void filtrarEstoque( Long idProduto ) throws ApplicationException {
		EstoqueDAOImpl dao = new EstoqueDAOImpl();

		EstoquePO encontrado = dao.filtrarPorProduto( idProduto );

		System.out.println( encontrado );
	}

	private void calcularValoresRestantesEntregar( Long idProduto, Long idPedido ) throws ApplicationException {

		EstoqueDAOImpl dao = new EstoqueDAOImpl();

		BigDecimal encontrados = dao.calcularValoresRestantesEntregar( idProduto, idPedido );

		System.out.println( "Valor restante:" + encontrados );
	}

	private void demonstrativoFiltrarPorSetor( Long idSafra, Long idSetor ) throws ApplicationException {

		DemonstrativoSafraDAOImpl dao = new DemonstrativoSafraDAOImpl();

		List< CustoDiretoInsumoPOJO > encontrados = dao.filtrarPorSetor( idSafra, idSetor );

		for ( CustoDiretoInsumoPOJO demonstrativoCorrente : encontrados ) {
			System.out.println( demonstrativoCorrente );
		}

		System.out.println( "Encontrados:" + encontrados.size() );
	}
}
