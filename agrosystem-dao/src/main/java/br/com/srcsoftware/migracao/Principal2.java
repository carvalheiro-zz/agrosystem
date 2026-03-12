package br.com.srcsoftware.migracao;

import org.junit.Test;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.gestao.manager.pessoa.funcionario.FuncionarioMigracaoPO;
import br.com.srcsoftware.gestao.manager.pessoa.pessoa.EnderecoMigracaoPO;
import br.com.srcsoftware.gestao.manager.pessoa.pessoa.pessoajuridica.PessoaJuridicaMigracaoPO;
import br.com.srcsoftware.gestao.manager.pessoa.pessoafisica.PessoaFisicaMigracaoPO;
import br.com.srcsoftware.gestao.sistema.aplicacao.setor.SetorMigracaoPO;
import br.com.srcsoftware.gestao.sistema.cultura.CulturaMigracaoPO;
import br.com.srcsoftware.gestao.sistema.manutencao.imovel.ImovelMigracaoPO;
import br.com.srcsoftware.gestao.sistema.manutencao.implemento.ImplementoMigracaoPO;
import br.com.srcsoftware.gestao.sistema.manutencao.servico.ServicoMigracaoPO;
import br.com.srcsoftware.gestao.sistema.manutencao.veiculo.VeiculoMigracaoPO;
import br.com.srcsoftware.gestao.sistema.notafiscal.rateio.centrocusto.CentroCustoReceitaMigracaoPO;
import br.com.srcsoftware.gestao.sistema.pedido.fornecedor.FornecedorMigracaoPO;
import br.com.srcsoftware.gestao.sistema.produto.marca.MarcaMigracaoPO;
import br.com.srcsoftware.gestao.sistema.produto.produto.ProdutoMigracaoPO;
import br.com.srcsoftware.gestao.sistema.produto.tipo.TipoMigracaoPO;
import br.com.srcsoftware.gestao.sistema.produto.unidademedida.UnidadeMedidaMigracaoPO;
import br.com.srcsoftware.migracao.conexao.HibernateConnectionMigracao;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.pessoa.endereco.EnderecoPO;
import br.com.srcsoftware.modular.manager.pessoa.funcionario.FuncionarioPO;
import br.com.srcsoftware.modular.manager.pessoa.pessoafisica.PessoaFisicaPO;
import br.com.srcsoftware.modular.manager.pessoa.pessoajuridica.PessoaJuridicaPO;
import br.com.srcsoftware.sistema.manutencao.imovel.ImovelPO;
import br.com.srcsoftware.sistema.manutencao.implemento.ImplementoPO;
import br.com.srcsoftware.sistema.manutencao.servico.ServicoPO;
import br.com.srcsoftware.sistema.manutencao.veiculo.VeiculoPO;
import br.com.srcsoftware.sistema.notafiscal.rateio.centrocusto.CentroCustoPO;
import br.com.srcsoftware.sistema.pessoa.fornecedor.juridico.FornecedorJuridicoPO;
import br.com.srcsoftware.sistema.produto.marca.MarcaPO;
import br.com.srcsoftware.sistema.produto.produto.ProdutoPO;
import br.com.srcsoftware.sistema.produto.tipo.TipoPO;
import br.com.srcsoftware.sistema.produto.unidademedida.UnidadeMedidaPO;
import br.com.srcsoftware.sistema.safra.cultura.CulturaPO;
import br.com.srcsoftware.sistema.safra.setor.SetorPO;

public class Principal2{
	@Test
	public void executar() throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();
		HibernateConnectionMigracao hibernateConnectionMigracao = new HibernateConnectionMigracao();
		try {
			// PRODUTO

			hibernate.iniciarTransacao();

			/* PESSOA */
			MigracaoManager.migrar( EnderecoMigracaoPO.class, EnderecoPO.class, hibernate, hibernateConnectionMigracao );
			MigracaoManager.migrar( PessoaJuridicaMigracaoPO.class, PessoaJuridicaPO.class, hibernate, hibernateConnectionMigracao );
			MigracaoManager.migrar( PessoaFisicaMigracaoPO.class, PessoaFisicaPO.class, hibernate, hibernateConnectionMigracao );
			MigracaoManager.migrar( FornecedorMigracaoPO.class, FornecedorJuridicoPO.class, hibernate, hibernateConnectionMigracao );
			MigracaoManager.migrar( FuncionarioMigracaoPO.class, FuncionarioPO.class, hibernate, hibernateConnectionMigracao );

			MigracaoManager.migrar( MarcaMigracaoPO.class, MarcaPO.class, hibernate, hibernateConnectionMigracao );
			MigracaoManager.migrar( TipoMigracaoPO.class, TipoPO.class, hibernate, hibernateConnectionMigracao );
			MigracaoManager.migrar( UnidadeMedidaMigracaoPO.class, UnidadeMedidaPO.class, hibernate, hibernateConnectionMigracao );
			MigracaoManager.migrar( ProdutoMigracaoPO.class, ProdutoPO.class, hibernate, hibernateConnectionMigracao );

			/*MigracaoManager.migrar( PedidoMigracaoPO.class, PedidoPO.class, hibernate, hibernateConnectionMigracao );
			MigracaoManager.migrar( ItemPedidoMigracaoPO.class, ItemPedidoPO.class, hibernate, hibernateConnectionMigracao );
			
			MigracaoManager.migrar( NotaFiscalVendaDiretaMigracaoPO.class, NotaFiscalVendaDiretaPO.class, hibernate, hibernateConnectionMigracao );
			MigracaoManager.migrar( ItemNotaFiscalVendaDiretaMigracaoPO.class, ItemNotaFiscalVendaDiretaPO.class, hibernate, hibernateConnectionMigracao );
			
			MigracaoManager.migrar( NotaFiscalVendaMigracaoPO.class, NotaFiscalVendaPO.class, hibernate, hibernateConnectionMigracao );
			MigracaoManager.migrar( ItemNotaFiscalVendaMigracaoPO.class, ItemNotaFiscalVendaPO.class, hibernate, hibernateConnectionMigracao );
			
			MigracaoManager.migrar( NotaFiscalSimplesRemessaMigracaoPO.class, NotaFiscalSimplesRemessaPO.class, hibernate, hibernateConnectionMigracao );
			MigracaoManager.migrar( ItemNotaFiscalSimplesRemessaMigracaoPO.class, ItemNotaFiscalSimplesRemessaPO.class, hibernate, hibernateConnectionMigracao );
			
			MigracaoManager.migrar( CupomMigracaoPO.class, CupomPO.class, hibernate, hibernateConnectionMigracao );
			MigracaoManager.migrar( ItemCupomMigracaoPO.class, ItemCupomPO.class, hibernate, hibernateConnectionMigracao );
			
			MigracaoManager.migrar( NotaFiscalCupomMigracaoPO.class, NotaFiscalCupomPO.class, hibernate, hibernateConnectionMigracao );
			*/
			MigracaoManager.migrar( CulturaMigracaoPO.class, CulturaPO.class, hibernate, hibernateConnectionMigracao );
			MigracaoManager.migrar( SetorMigracaoPO.class, SetorPO.class, hibernate, hibernateConnectionMigracao );
			/*MigracaoManager.migrar( SafraMigracaoPO.class, SafraPO.class, hibernate, hibernateConnectionMigracao );
			MigracaoManager.migrar( SetorSafraMigracaoPO.class, SetorSafraPO.class, hibernate, hibernateConnectionMigracao );
			
			MigracaoManager.migrar( SaidaGraoMigracaoPO.class, SaidaGraoPO.class, hibernate, hibernateConnectionMigracao );
			MigracaoManager.migrar( EntradaGraoMigracaoPO.class, EntradaGraoPO.class, hibernate, hibernateConnectionMigracao );
			
			MigracaoManager.migrar( AplicacaoMigracaoPO.class, AplicacaoPO.class, hibernate, hibernateConnectionMigracao );
			MigracaoManager.migrar( ItemMigracaoPO.class, ItemPO.class, hibernate, hibernateConnectionMigracao );*/

			MigracaoManager.migrar( ImovelMigracaoPO.class, ImovelPO.class, hibernate, hibernateConnectionMigracao );
			MigracaoManager.migrar( ImplementoMigracaoPO.class, ImplementoPO.class, hibernate, hibernateConnectionMigracao );
			MigracaoManager.migrar( ServicoMigracaoPO.class, ServicoPO.class, hibernate, hibernateConnectionMigracao );
			MigracaoManager.migrar( VeiculoMigracaoPO.class, VeiculoPO.class, hibernate, hibernateConnectionMigracao );
			//MigracaoManager.migrar( ManutencaoMigracaoPO.class, ManutencaoPO.class, hibernate, hibernateConnectionMigracao );

			//MigracaoManager.migrar( AbastecimentoMigracaoPO.class, AbastecimentoPO.class, hibernate, hibernateConnectionMigracao );

			MigracaoManager.migrar( CentroCustoReceitaMigracaoPO.class, CentroCustoPO.class, hibernate, hibernateConnectionMigracao );
			//MigracaoManager.migrar( NotaFiscalRateioMigracaoPO.class, NotaFiscalRateioPO.class, hibernate, hibernateConnectionMigracao );
			//MigracaoManager.migrar( ItemNotaFiscalRateioMigracaoPO.class, ItemNotaFiscalRateioPO.class, hibernate, hibernateConnectionMigracao );

			hibernate.commitTransacao();

		} catch ( ApplicationException e ) {
			hibernate.rollbackTransacao();
			throw e;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();
			throw e;
		}
		/*try {
			hibernate.shutdown();
			hibernateConnectionMigracao.shutdown();
		} catch ( ApplicationException e ) {
			e.printStackTrace();
			throw e;
		}*/
		System.out.println( "\n## MIGRAçãO COMPLETA ##" );
	}

	/*@SuppressWarnings( "unchecked" )
	private static void migrarMarca() throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();
	
		try {
			System.out.println( "INICIO MIGRAçãO MARCA" );
	
			System.out.println( "#### AGROSYSTEM: Pegando de" );
			ArrayList< br.com.srcsoftware.gestao.sistema.produto.marca.MarcaPO > marcas = (ArrayList< br.com.srcsoftware.gestao.sistema.produto.marca.MarcaPO >) new HibernateConnectionMigracao().filtrarTodos( br.com.srcsoftware.gestao.sistema.produto.marca.MarcaPO.class );
	
			System.out.println( "#### AGROSYSTEM: Encontrados : ".concat( String.valueOf( marcas.size() ) ) );
	
			String marcasGSON = new Gson().toJson( marcas );
	
			System.out.println( "#### ESAFRA: Convertendo para " );
			Gson gson = new Gson();
			ArrayList< br.com.srcsoftware.sistema.produto.marca.MarcaPO > migrados = new ArrayList<>();
			JsonParser parser = new JsonParser();
			JsonArray array = parser.parse( marcasGSON ).getAsJsonArray();
	
			for ( int i = 0; i < array.size(); i++ ) {
				migrados.add( gson.fromJson( array.get( i ), br.com.srcsoftware.sistema.produto.marca.MarcaPO.class ) );
			}
	
			System.out.println( "#### ESAFRA: Convertidos: ".concat( String.valueOf( migrados.size() ) ) );
			System.out.println( "#### ESAFRA: Inserindo " );
	
			hibernate.iniciarTransacao();
			MarcaDAOImpl dao = new MarcaDAOImpl();
			for ( br.com.srcsoftware.sistema.produto.marca.MarcaPO po : migrados ) {
				dao.inserir( hibernate, po );
			}
	
			hibernate.commitTransacao();
	
			System.out.println( "FIM MIGRAçãO MARCA" );
	
		} catch ( ApplicationException e ) {
			hibernate.rollbackTransacao();
			throw e;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}
	
	@SuppressWarnings( "unchecked" )
	private static void migrarTipo() throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();
	
		try {
			System.out.println( "INICIO MIGRAçãO TIPO" );
	
			System.out.println( "#### AGROSYSTEM: Pegando de" );
			ArrayList< br.com.srcsoftware.gestao.sistema.produto.tipo.TipoPO > tipos = (ArrayList< br.com.srcsoftware.gestao.sistema.produto.tipo.TipoPO >) new HibernateConnectionMigracao().filtrarTodos( br.com.srcsoftware.gestao.sistema.produto.tipo.TipoPO.class );
	
			System.out.println( "#### AGROSYSTEM: Encontrados : ".concat( String.valueOf( tipos.size() ) ) );
	
			String tiposGSON = new Gson().toJson( tipos );
	
			System.out.println( "#### ESAFRA: Convertendo para " );
			Gson gson = new Gson();
			ArrayList< br.com.srcsoftware.sistema.produto.tipo.TipoPO > migrados = new ArrayList<>();
			JsonParser parser = new JsonParser();
			JsonArray array = parser.parse( tiposGSON ).getAsJsonArray();
	
			for ( int i = 0; i < array.size(); i++ ) {
				migrados.add( gson.fromJson( array.get( i ), br.com.srcsoftware.sistema.produto.tipo.TipoPO.class ) );
			}
	
			System.out.println( "#### ESAFRA: Convertidos: ".concat( String.valueOf( migrados.size() ) ) );
			System.out.println( "#### ESAFRA: Inserindo " );
	
			hibernate.iniciarTransacao();
			TipoDAOImpl dao = new TipoDAOImpl();
			for ( br.com.srcsoftware.sistema.produto.tipo.TipoPO po : migrados ) {
				dao.inserir( hibernate, po );
			}
	
			hibernate.commitTransacao();
	
			System.out.println( "FIM MIGRAçãO TIPO" );
	
		} catch ( ApplicationException e ) {
			hibernate.rollbackTransacao();
			throw e;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}
	
	@SuppressWarnings( "unchecked" )
	private static void migrarVeiculo() throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();
	
		try {
			System.out.println( "INICIO MIGRAçãO TIPO" );
	
			System.out.println( "#### AGROSYSTEM: Pegando de" );
			ArrayList< br.com.srcsoftware.gestao.sistema.manutencao.veiculo.VeiculoPO > veiculos = (ArrayList< br.com.srcsoftware.gestao.sistema.manutencao.veiculo.VeiculoPO >) new HibernateConnectionMigracao().filtrarTodos( br.com.srcsoftware.gestao.sistema.manutencao.veiculo.VeiculoPO.class );
	
			System.out.println( "#### AGROSYSTEM: Encontrados : ".concat( String.valueOf( veiculos.size() ) ) );
	
			String veiculosGSON = new Gson().toJson( veiculos );
	
			System.out.println( "#### ESAFRA: Convertendo para " );
			Gson gson = new Gson();
			ArrayList< br.com.srcsoftware.sistema.manutencao.veiculo.VeiculoPO > migrados = new ArrayList<>();
			JsonParser parser = new JsonParser();
			JsonArray array = parser.parse( veiculosGSON ).getAsJsonArray();
	
			for ( int i = 0; i < array.size(); i++ ) {
				migrados.add( gson.fromJson( array.get( i ), br.com.srcsoftware.sistema.manutencao.veiculo.VeiculoPO.class ) );
			}
	
			System.out.println( "#### ESAFRA: Convertidos: ".concat( String.valueOf( migrados.size() ) ) );
			System.out.println( "#### ESAFRA: Inserindo " );
	
			hibernate.iniciarTransacao();
			VeiculoDAOImpl dao = new VeiculoDAOImpl();
			for ( br.com.srcsoftware.sistema.manutencao.veiculo.VeiculoPO po : migrados ) {
				dao.inserir( hibernate, po );
			}
	
			hibernate.commitTransacao();
	
			System.out.println( "FIM MIGRAçãO TIPO" );
	
		} catch ( ApplicationException e ) {
			hibernate.rollbackTransacao();
			throw e;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}*/

}
