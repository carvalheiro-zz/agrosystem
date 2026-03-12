package teste.full;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.empresa.EmpresaDAOImpl;
import br.com.srcsoftware.modular.manager.empresa.EmpresaPO;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.parametrizacao.parametrizacao.ParametrizacaoDAOImpl;
import br.com.srcsoftware.modular.manager.parametrizacao.parametrizacao.ParametrizacaoPO;
import br.com.srcsoftware.modular.manager.pessoa.endereco.EnderecoDAOImpl;
import br.com.srcsoftware.modular.manager.pessoa.endereco.EnderecoPO;
import br.com.srcsoftware.modular.manager.pessoa.funcionario.FuncionarioPadraoDAOImpl;
import br.com.srcsoftware.modular.manager.pessoa.funcionario.FuncionarioPadraoPO;
import br.com.srcsoftware.modular.manager.pessoa.pessoafisica.PessoaFisicaDAOImpl;
import br.com.srcsoftware.modular.manager.pessoa.pessoafisica.PessoaFisicaPO;
import br.com.srcsoftware.modular.manager.pessoa.pessoajuridica.PessoaJuridicaDAOImpl;
import br.com.srcsoftware.modular.manager.pessoa.pessoajuridica.PessoaJuridicaPO;
import br.com.srcsoftware.modular.manager.usuario.permissao.PermissaoDAOImpl;
import br.com.srcsoftware.modular.manager.usuario.permissao.PermissaoPO;
import br.com.srcsoftware.modular.manager.usuario.tela.TelaDAOImpl;
import br.com.srcsoftware.modular.manager.usuario.tela.TelaPO;
import br.com.srcsoftware.modular.manager.usuario.tipousuario.TipoUsuarioDAOImpl;
import br.com.srcsoftware.modular.manager.usuario.tipousuario.TipoUsuarioPO;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioDAOImpl;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioPO;

public class TestaDAO{

	@Test
	public void executar() {

		try {
			long inicio = System.currentTimeMillis();
			for ( int i = 0; i < 1; i++ ) {
				//this.inserirTela();
				this.pesquisarTela();
				//this.exluirTela();
				/*pesquisarPermissao();
				pesquisarTipoUsuario();
				pesquisarUsuario();
				pesquisarFuncionario();
				pesquisarPessoaFisica();
				pesquisarPessoaJuridica();
				pesquisarEndereco();
				pesquisarEmpresa();
				pesquisarParametrizacao();*/
			}
			long termino = System.currentTimeMillis();

			double resultado = ( ( termino - inicio ) / 1000.0 );

			System.out.println( "Tempo: " + resultado );
		} catch ( ApplicationException e ) {
			//e.printStackTrace();
		}

	}

	private void inserirTela() throws ApplicationException {

		HibernateConnection hibernate = new HibernateConnection();
		try {

			hibernate.iniciarTransacao();

			TelaDAOImpl dao = new TelaDAOImpl();

			for ( int i = 0; i < 500000; i++ ) {
				TelaPO poFilter = new TelaPO();
				poFilter.setNome( "Tela" + i );

				dao.inserir( hibernate, poFilter );

			}
			hibernate.commitTransacao();
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();
			e.printStackTrace();
		}

	}

	private void exluirTela() throws ApplicationException {

		HibernateConnection hibernate = new HibernateConnection();
		try {
			TelaDAOImpl dao = new TelaDAOImpl();

			HashMap< String, String > camposOrders = new HashMap<>();
			camposOrders.put( "nome", "ASC" );

			List< TelaPO > encontrados = dao.filtrar( Paginacao.NAO, camposOrders, new TelaPO() );

			hibernate.iniciarTransacao();
			for ( TelaPO telaCorrente : encontrados ) {
				dao.excluir( hibernate, telaCorrente );
			}
			hibernate.commitTransacao();
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();
			e.printStackTrace();
		}

	}

	private void pesquisarTela() throws ApplicationException {

		TelaPO poFilter = new TelaPO();
		poFilter.setNome( "tela" );

		TelaDAOImpl dao = new TelaDAOImpl();

		HashMap< String, String > camposOrders = new HashMap<>();
		camposOrders.put( "nome", "ASC" );

		List< TelaPO > encontrados = dao.filtrar( Paginacao.NAO, camposOrders, poFilter );

		for ( TelaPO telaCorrente : encontrados ) {
			System.out.println( telaCorrente );
		}
	}

	private void pesquisarPermissao() throws ApplicationException {

		PermissaoPO poFilter = new PermissaoPO();
		poFilter.setAcao( "ACESSAR" );
		poFilter.getTela().setNome( "Permissao" );

		PermissaoDAOImpl dao = new PermissaoDAOImpl();

		HashMap< String, String > camposOrders = new HashMap<>();
		camposOrders.put( "acao", "ASC" );

		List< PermissaoPO > encontrados = dao.filtrar( Paginacao.NAO, camposOrders, poFilter );

		for ( PermissaoPO permissaoCorrente : encontrados ) {
			//System.out.println( permissaoCorrente );
		}
	}

	private void pesquisarTipoUsuario() throws ApplicationException {

		TipoUsuarioPO poFilter = new TipoUsuarioPO();
		poFilter.setNome( "Administrador" );
		poFilter.getEmpresa().setId( 2L );

		TipoUsuarioDAOImpl dao = new TipoUsuarioDAOImpl();

		HashMap< String, String > camposOrders = new HashMap<>();
		camposOrders.put( "nome", "ASC" );

		List< TipoUsuarioPO > encontrados = dao.filtrar( Paginacao.NAO, camposOrders, poFilter );

		for ( TipoUsuarioPO tipoUsuarioCorrente : encontrados ) {
			//System.out.println( tipoUsuarioCorrente );
		}
	}

	private void pesquisarUsuario() throws ApplicationException {

		UsuarioDAOImpl dao = new UsuarioDAOImpl();

		HashMap< String, String > camposOrders = new HashMap<>();
		camposOrders.put( "login", "ASC" );

		try {
			String encontrado = dao.filtrarSenhaUsuario( 1L );
			//System.out.println( encontrado );
		} catch ( ApplicationException e ) {
			//e.printStackTrace();
		}

		try {
			UsuarioPO encontrado = dao.filtrarPorEmail( "adm@gmail.com" );
			//System.out.println( encontrado );
		} catch ( ApplicationException e ) {
			//e.printStackTrace();
		}

	}

	private void pesquisarFuncionario() throws ApplicationException {

		FuncionarioPadraoPO poFilter = new FuncionarioPadraoPO();
		poFilter.setMatricula( "123456" );
		poFilter.getPessoaFisica().setRazaoSocial( "Maria das Graças" );
		poFilter.getPessoaFisica().getEndereco().setCidade( "Ourinhos" );
		poFilter.getEmpresa().setId( 2L );

		FuncionarioPadraoDAOImpl dao = new FuncionarioPadraoDAOImpl();

		HashMap< String, String > camposOrders = new HashMap<>();
		camposOrders.put( "pessoaFisica.razaoSocial", "ASC" );

		List< FuncionarioPadraoPO > encontrados = dao.filtrar( Paginacao.NAO, camposOrders, poFilter );

		for ( FuncionarioPadraoPO funcionarioCorrente : encontrados ) {
			//System.out.println( funcionarioCorrente );
		}
	}

	private void pesquisarPessoaFisica() throws ApplicationException {

		PessoaFisicaDAOImpl dao = new PessoaFisicaDAOImpl();

		HashMap< String, String > camposOrdenacao = new HashMap<>();
		camposOrdenacao.put( "razaoSocial", "ASC" );

		List< PessoaFisicaPO > encontrados = dao.filtrarPorCPF( 1L, camposOrdenacao, "226.420.158-40" );

		for ( PessoaFisicaPO pessoaFisicaCorrente : encontrados ) {
			//System.out.println( pessoaFisicaCorrente );
		}

		encontrados = dao.filtrarPorRazaoSocial( 1L, camposOrdenacao, "Gabriel Damiani Carvalheiro" );

		for ( PessoaFisicaPO pessoaFisicaCorrente : encontrados ) {
			//System.out.println( pessoaFisicaCorrente );
		}

		try {
			PessoaFisicaPO encontrado = dao.filtrarPorCPF( 1L, "226.420.158-40" );
			//System.out.println(encontrado );
		} catch ( ApplicationException e ) {
			////e.printStackTrace();
		}

	}

	private void pesquisarPessoaJuridica() throws ApplicationException {

		PessoaJuridicaDAOImpl dao = new PessoaJuridicaDAOImpl();

		HashMap< String, String > camposOrdenacao = new HashMap<>();
		camposOrdenacao.put( "razaoSocial", "ASC" );

		List< PessoaJuridicaPO > encontrados = dao.filtrarPorCNPJ( 1L, camposOrdenacao, "24.365.521/0001-58" );

		for ( PessoaJuridicaPO pessoaJuridicaCorrente : encontrados ) {
			//System.out.println( pessoaJuridicaCorrente );
		}

		try {
			PessoaJuridicaPO encontrado = dao.filtrarPorCNPJ( 1L, "24.365.521/0001-58" );
			//System.out.println( encontrado );
		} catch ( ApplicationException e ) {
			////e.printStackTrace();
		}

	}

	private void pesquisarEndereco() throws ApplicationException {

		EnderecoDAOImpl dao = new EnderecoDAOImpl();

		HashMap< String, String > camposOrdenacao = new HashMap<>();
		camposOrdenacao.put( "logradouro", "ASC" );

		try {
			EnderecoPO encontrado = dao.filtrarPorCEP( "19.904-282" );
			//System.out.println( encontrado );
		} catch ( ApplicationException e ) {
			//e.printStackTrace();
		}

	}

	private void pesquisarEmpresa() throws ApplicationException {

		EmpresaPO poFilter = new EmpresaPO();

		poFilter.setCodigo( "001" );
		poFilter.setAtivo( true );
		EmpresaPO empresa = new EmpresaPO();
		empresa.setCodigo( "006" );

		poFilter.setEmpresa( empresa );

		EmpresaDAOImpl dao = new EmpresaDAOImpl();

		HashMap< String, String > camposOrders = new HashMap<>();
		camposOrders.put( "Anome", "ASC" );
		camposOrders.put( "Bcodigo", "DESC" );

		List< EmpresaPO > encontrados = dao.filtrar( Paginacao.NAO, camposOrders, poFilter );

		for ( EmpresaPO empresaCorrente : encontrados ) {
			//System.out.println( empresaCorrente );
		}
	}

	private void pesquisarParametrizacao() throws ApplicationException {

		ParametrizacaoPO poFilter = new ParametrizacaoPO();
		poFilter.setId( 22L );
		poFilter.getEmpresa().setId( 2L );

		ParametrizacaoDAOImpl dao = new ParametrizacaoDAOImpl();

		List< ParametrizacaoPO > encontrados = dao.filtrar( Paginacao.NAO, HibernateConnection.SEM_ORDENACAO, poFilter );

		for ( ParametrizacaoPO parametrizacaoCorrente : encontrados ) {
			//System.out.println( parametrizacaoCorrente );
		}
	}
}
