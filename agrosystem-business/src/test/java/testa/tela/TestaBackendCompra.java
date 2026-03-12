package testa.tela;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import org.junit.Test;
import org.omg.CORBA.portable.ApplicationException;

import br.com.srcsoftware.manager.util.Utilidades;
import br.com.srcsoftware.modular.financeiro.contapagar.ContaPagarDTO;
import br.com.srcsoftware.modular.financeiro.formapagamento.FormaPagamentoFacade;
import br.com.srcsoftware.modular.financeiro.titulo.titulopagar.TituloPagarDTO;
import br.com.srcsoftware.modular.financeiro.titulo.titulopagar.TituloPagarFacade;
import br.com.srcsoftware.modular.manager.empresa.EmpresaFacade;
import br.com.srcsoftware.modular.manager.exceptions.ModelFactoryException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.pessoa.fornecedor.FornecedorPadraoFacade;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;
import br.com.srcsoftware.modular.manager.utilidades.DateUtil;
import br.com.srcsoftware.sistema.compra.compra.CompraDTO;
import br.com.srcsoftware.sistema.compra.compra.CompraFacade;
import br.com.srcsoftware.sistema.compra.item.ItemCompraDTO;
import br.com.srcsoftware.sistema.servico.equipamento.EquipamentoFacade;
import br.com.srcsoftware.sistema.servico.materiaprima.MateriaPrimaFacade;

public class TestaBackendCompra{

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

		this.camposOrders = new HashMap< >();
		this.camposOrders.put( "numero", "DESC" );

		long inicio = System.currentTimeMillis();

		for ( int i = 0; i < 1; i++ ) {
			this.testar( this.paginacao, this.camposOrders, i );
		}

		long termino = System.currentTimeMillis();

		System.err.println( "Executado em " + ( ( termino - inicio ) / 1000.0 ) + " segundos. Erros: " + this.erros );
	}

	private void testar( Paginacao paginacao, HashMap< String, String > camposOrders, int i ) {

		try {

			UsuarioSessaoPOJO usuarioSessaoPOJO = new UsuarioSessaoPOJO();
			usuarioSessaoPOJO.getUsuario().setLogin( "Eu mesmo" );

			/** INSERINDO COMPRA */
			//testarCompraAVistaQuitado( usuarioSessaoPOJO );
			//testarCompraAVistaPraDiferenteDeHoje( usuarioSessaoPOJO );
			//testarCompraAPrazoSemEntrada( usuarioSessaoPOJO );
			//testarCompraAPrazoComEntrada( usuarioSessaoPOJO );
			testarCompraAPrazoComEntradaPraAntesDeHoje( usuarioSessaoPOJO );
			//testarCompraAPrazoComEntradaPraHoje( usuarioSessaoPOJO );
			//testarCompraPeriodica( usuarioSessaoPOJO );
			testarQuitacaoRapida( usuarioSessaoPOJO );
			//testarQuitacao( usuarioSessaoPOJO );
			//testarQuitacaoComAcrescimo( usuarioSessaoPOJO );
			//testarQuitacaoParcial( usuarioSessaoPOJO );
			//testarEstorno( usuarioSessaoPOJO );

			/** FILTRAR TODAS */
			/** FILTRAR POR ID */
			/** QUITAR PARCELA */
			/** FILTRAR POR ID */
			/** ALTERAR */
			/** FILTRAR POR ALGUM CAMPO */
			/** EXCLUIR */

		} catch ( ApplicationException e ) {
			this.erros++;
			e.printStackTrace();
			this.logger.error( e.getMessage(), e );
		}
	}

	public void testarCompraAVistaQuitado( UsuarioSessaoPOJO usuarioSessaoPOJO ) throws ApplicationException {
		this.logger.info( "Criando a instancia do FACADE: " + CompraFacade.class );
		CompraFacade facade = CompraFacade.getInstance();

		/** CRIANDO UMA COMPRA */
		ContaPagarDTO contaPagar = new ContaPagarDTO();
		contaPagar.setId( null );
		contaPagar.setSituacao( null );
		contaPagar.setValorEntrada( null );
		contaPagar.setValorParcela( null );//VERIFICAR SETAMENTO DESTE VALOR NO SERVICE
		contaPagar.setValorFinal( null );//VERIFICAR SETAMENTO DESTE VALOR NO SERVICE
		contaPagar.setVencimentoPrimeiraParcela( null );//VERIFICAR a limpeza DESTE VALOR NO SERVICE
		contaPagar.setData( DateUtil.parseLocalDate( LocalDate.now() ) );
		contaPagar.setNumero( Utilidades.generateId().toString() );
		contaPagar.setQuantidadeParcelas( "1" );
		contaPagar.setValor( "1200" );
		contaPagar.setFornecedor( FornecedorPadraoFacade.getInstance().filtrarPorId( "1" ) );
		contaPagar.setFormaPagamento( FormaPagamentoFacade.getInstance().filtrarPorId( "1" ) );
		contaPagar.setEmpresa( EmpresaFacade.getInstance().filtrarPorId( "1" ) );

		CompraDTO compra = new CompraDTO();
		compra.setId( null );
		compra.setContaPagar( contaPagar );
		compra.setEmpresa( EmpresaFacade.getInstance().filtrarPorId( "1" ) );

		ArrayList< ItemCompraDTO > itens = new ArrayList< >();
		ItemCompraDTO item = new ItemCompraDTO();
		item.setId( null );
		item.setEmpresa( EmpresaFacade.getInstance().filtrarPorId( "1" ) );
		item.setEquipamento( EquipamentoFacade.getInstance().filtrarPorId( "1" ) );
		item.setQuantidade( "5" );
		item.setValorUnitario( "600" );
		itens.add( item );

		ItemCompraDTO item2 = new ItemCompraDTO();
		item2.setId( null );
		item2.setEmpresa( EmpresaFacade.getInstance().filtrarPorId( "1" ) );
		item2.setMateriaPrima( MateriaPrimaFacade.getInstance().filtrarPorId( "1" ) );
		item2.setQuantidade( "10" );
		item2.setValorUnitario( "600" );
		itens.add( item2 );

		compra.setItens( itens );

		/** INSERINDO COMPRA */
		facade.inserir( usuarioSessaoPOJO, compra );
	}

	public void testarCompraAVistaPraDiferenteDeHoje( UsuarioSessaoPOJO usuarioSessaoPOJO ) throws ApplicationException {

		this.logger.info( "Criando a instancia do FACADE: " + CompraFacade.class );
		CompraFacade facade = CompraFacade.getInstance();

		/** CRIANDO UMA COMPRA */
		ContaPagarDTO contaPagar = new ContaPagarDTO();
		contaPagar.setId( null );
		contaPagar.setSituacao( null );
		contaPagar.setValorEntrada( null );
		contaPagar.setVencimentoPrimeiraParcela( null );//VERIFICAR a limpeza DESTE VALOR NO SERVICE
		contaPagar.setValorFinal( null );//VERIFICAR SETAMENTO DESTE VALOR NO SERVICE
		contaPagar.setValorParcela( null );//VERIFICAR SETAMENTO DESTE VALOR NO SERVICE
		contaPagar.setData( "10/04/2018" );
		contaPagar.setNumero( Utilidades.generateId().toString() );
		contaPagar.setQuantidadeParcelas( "1" );
		contaPagar.setValor( "1200" );
		contaPagar.setFornecedor( FornecedorPadraoFacade.getInstance().filtrarPorId( "1" ) );
		contaPagar.setFormaPagamento( FormaPagamentoFacade.getInstance().filtrarPorId( "1" ) );
		contaPagar.setEmpresa( EmpresaFacade.getInstance().filtrarPorId( "1" ) );

		CompraDTO compra = new CompraDTO();
		compra.setId( null );
		compra.setContaPagar( contaPagar );
		compra.setEmpresa( EmpresaFacade.getInstance().filtrarPorId( "1" ) );

		ArrayList< ItemCompraDTO > itens = new ArrayList< >();
		ItemCompraDTO item = new ItemCompraDTO();
		item.setId( null );
		item.setEmpresa( EmpresaFacade.getInstance().filtrarPorId( "1" ) );
		item.setEquipamento( EquipamentoFacade.getInstance().filtrarPorId( "1" ) );
		item.setQuantidade( "5" );
		item.setValorUnitario( "600" );
		itens.add( item );

		ItemCompraDTO item2 = new ItemCompraDTO();
		item2.setId( null );
		item2.setEmpresa( EmpresaFacade.getInstance().filtrarPorId( "1" ) );
		item2.setMateriaPrima( MateriaPrimaFacade.getInstance().filtrarPorId( "1" ) );
		item2.setQuantidade( "10" );
		item2.setValorUnitario( "600" );
		itens.add( item2 );

		compra.setItens( itens );

		/** INSERINDO COMPRA */
		facade.inserir( usuarioSessaoPOJO, compra );
	}

	public void testarCompraAPrazoSemEntrada( UsuarioSessaoPOJO usuarioSessaoPOJO ) throws ApplicationException {
		this.logger.info( "Criando a instancia do FACADE: " + CompraFacade.class );
		CompraFacade facade = CompraFacade.getInstance();

		/** CRIANDO UMA COMPRA */
		ContaPagarDTO contaPagar = new ContaPagarDTO();
		contaPagar.setId( null );
		contaPagar.setSituacao( null );
		contaPagar.setValorEntrada( null );
		contaPagar.setVencimentoPrimeiraParcela( "10/05/2018" );//VERIFICAR a limpeza DESTE VALOR NO SERVICE
		contaPagar.setValorFinal( null );//VERIFICAR SETAMENTO DESTE VALOR NO SERVICE
		contaPagar.setValorParcela( null );//VERIFICAR SETAMENTO DESTE VALOR NO SERVICE
		contaPagar.setData( "19/04/2018" );
		contaPagar.setNumero( Utilidades.generateId().toString() );
		contaPagar.setQuantidadeParcelas( "3" );
		contaPagar.setValor( "1200" );
		contaPagar.setFornecedor( FornecedorPadraoFacade.getInstance().filtrarPorId( "1" ) );
		contaPagar.setFormaPagamento( FormaPagamentoFacade.getInstance().filtrarPorId( "1" ) );
		contaPagar.setEmpresa( EmpresaFacade.getInstance().filtrarPorId( "1" ) );

		CompraDTO compra = new CompraDTO();
		compra.setId( null );
		compra.setContaPagar( contaPagar );
		compra.setEmpresa( EmpresaFacade.getInstance().filtrarPorId( "1" ) );

		ArrayList< ItemCompraDTO > itens = new ArrayList< >();
		ItemCompraDTO item = new ItemCompraDTO();
		item.setId( null );
		item.setEmpresa( EmpresaFacade.getInstance().filtrarPorId( "1" ) );
		item.setEquipamento( EquipamentoFacade.getInstance().filtrarPorId( "1" ) );
		item.setQuantidade( "5" );
		item.setValorUnitario( "600" );
		itens.add( item );

		ItemCompraDTO item2 = new ItemCompraDTO();
		item2.setId( null );
		item2.setEmpresa( EmpresaFacade.getInstance().filtrarPorId( "1" ) );
		item2.setMateriaPrima( MateriaPrimaFacade.getInstance().filtrarPorId( "1" ) );
		item2.setQuantidade( "10" );
		item2.setValorUnitario( "600" );
		itens.add( item2 );

		compra.setItens( itens );

		/** INSERINDO COMPRA */
		facade.inserir( usuarioSessaoPOJO, compra );
	}

	public void testarCompraAPrazoComEntrada( UsuarioSessaoPOJO usuarioSessaoPOJO ) throws ApplicationException {
		this.logger.info( "Criando a instancia do FACADE: " + CompraFacade.class );
		CompraFacade facade = CompraFacade.getInstance();

		/** CRIANDO UMA COMPRA */
		ContaPagarDTO contaPagar = new ContaPagarDTO();
		contaPagar.setId( null );
		contaPagar.setSituacao( null );
		contaPagar.setValorEntrada( "300" );
		contaPagar.setFormaPagamentoEntrada( FormaPagamentoFacade.getInstance().filtrarPorId( "1" ) );
		contaPagar.setVencimentoPrimeiraParcela( "10/05/2018" );//VERIFICAR a limpeza DESTE VALOR NO SERVICE
		contaPagar.setValorFinal( null );//VERIFICAR SETAMENTO DESTE VALOR NO SERVICE
		contaPagar.setValorParcela( null );//VERIFICAR SETAMENTO DESTE VALOR NO SERVICE
		contaPagar.setData( "19/04/2018" );
		contaPagar.setNumero( Utilidades.generateId().toString() );
		contaPagar.setQuantidadeParcelas( "3" );
		contaPagar.setValor( "1200" );
		contaPagar.setFornecedor( FornecedorPadraoFacade.getInstance().filtrarPorId( "1" ) );
		contaPagar.setFormaPagamento( FormaPagamentoFacade.getInstance().filtrarPorId( "1" ) );
		contaPagar.setEmpresa( EmpresaFacade.getInstance().filtrarPorId( "1" ) );

		CompraDTO compra = new CompraDTO();
		compra.setId( null );
		compra.setContaPagar( contaPagar );
		compra.setEmpresa( EmpresaFacade.getInstance().filtrarPorId( "1" ) );

		ArrayList< ItemCompraDTO > itens = new ArrayList< >();
		ItemCompraDTO item = new ItemCompraDTO();
		item.setId( null );
		item.setEmpresa( EmpresaFacade.getInstance().filtrarPorId( "1" ) );
		item.setEquipamento( EquipamentoFacade.getInstance().filtrarPorId( "1" ) );
		item.setQuantidade( "5" );
		item.setValorUnitario( "600" );
		itens.add( item );

		ItemCompraDTO item2 = new ItemCompraDTO();
		item2.setId( null );
		item2.setEmpresa( EmpresaFacade.getInstance().filtrarPorId( "1" ) );
		item2.setMateriaPrima( MateriaPrimaFacade.getInstance().filtrarPorId( "1" ) );
		item2.setQuantidade( "10" );
		item2.setValorUnitario( "600" );
		itens.add( item2 );

		compra.setItens( itens );

		/** INSERINDO COMPRA */
		facade.inserir( usuarioSessaoPOJO, compra );
	}

	public void testarCompraAPrazoComEntradaPraAntesDeHoje( UsuarioSessaoPOJO usuarioSessaoPOJO ) throws ApplicationException {
		this.logger.info( "Criando a instancia do FACADE: " + CompraFacade.class );
		CompraFacade facade = CompraFacade.getInstance();

		/** CRIANDO UMA COMPRA */
		ContaPagarDTO contaPagar = new ContaPagarDTO();
		contaPagar.setId( null );
		contaPagar.setSituacao( null );
		contaPagar.setValorEntrada( "650" );
		contaPagar.setFormaPagamentoEntrada( FormaPagamentoFacade.getInstance().filtrarPorId( "1" ) );
		contaPagar.setVencimentoPrimeiraParcela( "19/03/2018" );//VERIFICAR a limpeza DESTE VALOR NO SERVICE
		contaPagar.setValorFinal( null );//VERIFICAR SETAMENTO DESTE VALOR NO SERVICE
		contaPagar.setValorParcela( null );//VERIFICAR SETAMENTO DESTE VALOR NO SERVICE
		contaPagar.setData( "19/02/2018" );
		contaPagar.setNumero( Utilidades.generateId().toString() );
		contaPagar.setQuantidadeParcelas( "10" );
		contaPagar.setValor( "4500" );
		contaPagar.setFornecedor( FornecedorPadraoFacade.getInstance().filtrarPorId( "1" ) );
		contaPagar.setFormaPagamento( FormaPagamentoFacade.getInstance().filtrarPorId( "1" ) );
		contaPagar.setEmpresa( EmpresaFacade.getInstance().filtrarPorId( "1" ) );

		CompraDTO compra = new CompraDTO();
		compra.setId( null );
		compra.setContaPagar( contaPagar );
		compra.setEmpresa( EmpresaFacade.getInstance().filtrarPorId( "1" ) );

		ArrayList< ItemCompraDTO > itens = new ArrayList< >();
		ItemCompraDTO item = new ItemCompraDTO();
		item.setId( null );
		item.setEmpresa( EmpresaFacade.getInstance().filtrarPorId( "1" ) );
		item.setEquipamento( EquipamentoFacade.getInstance().filtrarPorId( "1" ) );
		item.setQuantidade( "15" );
		item.setValorUnitario( "500" );
		itens.add( item );

		ItemCompraDTO item2 = new ItemCompraDTO();
		item2.setId( null );
		item2.setEmpresa( EmpresaFacade.getInstance().filtrarPorId( "1" ) );
		item2.setMateriaPrima( MateriaPrimaFacade.getInstance().filtrarPorId( "1" ) );
		item2.setQuantidade( "18" );
		item2.setValorUnitario( "300" );
		itens.add( item2 );

		compra.setItens( itens );

		/** INSERINDO COMPRA */
		facade.inserir( usuarioSessaoPOJO, compra );
	}

	public void testarCompraAPrazoComEntradaPraHoje( UsuarioSessaoPOJO usuarioSessaoPOJO ) throws ApplicationException {
		this.logger.info( "Criando a instancia do FACADE: " + CompraFacade.class );
		CompraFacade facade = CompraFacade.getInstance();

		/** CRIANDO UMA COMPRA */
		ContaPagarDTO contaPagar = new ContaPagarDTO();
		contaPagar.setId( null );
		contaPagar.setSituacao( null );
		contaPagar.setValorEntrada( "300" );
		contaPagar.setFormaPagamentoEntrada( FormaPagamentoFacade.getInstance().filtrarPorId( "1" ) );
		contaPagar.setVencimentoPrimeiraParcela( "19/04/2018" );//VERIFICAR a limpeza DESTE VALOR NO SERVICE
		contaPagar.setValorFinal( null );//VERIFICAR SETAMENTO DESTE VALOR NO SERVICE
		contaPagar.setValorParcela( null );//VERIFICAR SETAMENTO DESTE VALOR NO SERVICE
		contaPagar.setData( "19/04/2018" );
		contaPagar.setNumero( Utilidades.generateId().toString() );
		contaPagar.setQuantidadeParcelas( "3" );
		contaPagar.setValor( "1200" );
		contaPagar.setFornecedor( FornecedorPadraoFacade.getInstance().filtrarPorId( "1" ) );
		contaPagar.setFormaPagamento( FormaPagamentoFacade.getInstance().filtrarPorId( "1" ) );
		contaPagar.setEmpresa( EmpresaFacade.getInstance().filtrarPorId( "1" ) );

		CompraDTO compra = new CompraDTO();
		compra.setId( null );
		compra.setContaPagar( contaPagar );
		compra.setEmpresa( EmpresaFacade.getInstance().filtrarPorId( "1" ) );

		ArrayList< ItemCompraDTO > itens = new ArrayList< >();
		ItemCompraDTO item = new ItemCompraDTO();
		item.setId( null );
		item.setEmpresa( EmpresaFacade.getInstance().filtrarPorId( "1" ) );
		item.setEquipamento( EquipamentoFacade.getInstance().filtrarPorId( "1" ) );
		item.setQuantidade( "5" );
		item.setValorUnitario( "600" );
		itens.add( item );

		ItemCompraDTO item2 = new ItemCompraDTO();
		item2.setId( null );
		item2.setEmpresa( EmpresaFacade.getInstance().filtrarPorId( "1" ) );
		item2.setMateriaPrima( MateriaPrimaFacade.getInstance().filtrarPorId( "1" ) );
		item2.setQuantidade( "10" );
		item2.setValorUnitario( "600" );
		itens.add( item2 );

		compra.setItens( itens );

		/** INSERINDO COMPRA */
		facade.inserir( usuarioSessaoPOJO, compra );
	}

	/*public void testarCompraPeriodica( UsuarioSessaoPOJO usuarioSessaoPOJO ) throws ApplicationException {
		this.logger.info( "Criando a instancia do FACADE: " + CompraFacade.class );
		CompraFacade facade = CompraFacade.getInstance();
	
		*//** CRIANDO UMA COMPRA */
	/*
	ContaPagarDTO contaPagar = new ContaPagarDTO();
	contaPagar.setId( null );
	contaPagar.setSituacao( null );
	contaPagar.setValorEntrada( null );
	contaPagar.setFormaPagamentoEntrada( null );
	contaPagar.setQuantidadeParcelas( null );
	contaPagar.setValorFinal( null );//VERIFICAR SETAMENTO DESTE VALOR NO SERVICE
	contaPagar.setValorParcela( null );//VERIFICAR SETAMENTO DESTE VALOR NO SERVICE
	contaPagar.setVencimentoPrimeiraParcela( "15/04/2018" );//VERIFICAR a limpeza DESTE VALOR NO SERVICE
	contaPagar.setData( "19/04/2018" );
	contaPagar.setNumero( Utilidades.generateId().toString() );
	contaPagar.setValor( "89" );
	contaPagar.setFornecedor( FornecedorPadraoFacade.getInstance().filtrarPorId( "1" ) );
	contaPagar.setFormaPagamento( FormaPagamentoFacade.getInstance().filtrarPorId( "1" ) );
	contaPagar.setEmpresa( EmpresaFacade.getInstance().filtrarPorId( "1" ) );
	
	CompraDTO compra = new CompraDTO();
	compra.setId( null );
	compra.setContaPagar( contaPagar );
	compra.setEmpresa( EmpresaFacade.getInstance().filtrarPorId( "1" ) );
	
	ArrayList< ItemCompraDTO > itens = new ArrayList< ItemCompraDTO >();
	ItemCompraDTO item = new ItemCompraDTO();
	item.setId( null );
	item.setEmpresa( EmpresaFacade.getInstance().filtrarPorId( "1" ) );
	item.setEquipamento( EquipamentoFacade.getInstance().filtrarPorId( "1" ) );
	item.setQuantidade( "5" );
	item.setValorUnitario( "600" );
	itens.add( item );
	
	ItemCompraDTO item2 = new ItemCompraDTO();
	item2.setId( null );
	item2.setEmpresa( EmpresaFacade.getInstance().filtrarPorId( "1" ) );
	item2.setMateriaPrima( MateriaPrimaFacade.getInstance().filtrarPorId( "1" ) );
	item2.setQuantidade( "10" );
	item2.setValorUnitario( "600" );
	itens.add( item2 );
	
	compra.setItens( itens );
	
	*//** INSERINDO COMPRA *//*
    facade.inserir( usuarioSessaoPOJO, compra );
    }*/


	/**
	 * testarQuitacaoRapida( empresa, fornecedor );
	 * 
	 * testarQuitacaoComAcrescimo( empresa, fornecedor );
	 * 
	 * testarQuitacaoSemAcrescimo( empresa, fornecedor );
	 * 
	 * testarQuitacaoParcial( empresa, fornecedor );
	 * 
	 * @throws ApplicationException
	 * @throws ModelFactoryException
	 */
	private void testarQuitacaoRapida( UsuarioSessaoPOJO usuarioSessaoPOJO ) throws ApplicationException, ModelFactoryException {
		this.logger.info( "Criando a instancia do FACADE: " + CompraFacade.class );
		TituloPagarFacade facade = TituloPagarFacade.getInstance();

		TituloPagarDTO parcelaQuitar = TituloPagarFacade.getInstance().filtrarPorId( "4" );

		/** INSERINDO COMPRA */
		facade.quitarRapido( usuarioSessaoPOJO, parcelaQuitar, FormaPagamentoFacade.getInstance().filtrarPorId( "1" ) );
	}

	private void testarQuitacao( UsuarioSessaoPOJO usuarioSessaoPOJO ) throws ApplicationException, ModelFactoryException {
		this.logger.info( "Criando a instancia do FACADE: " + CompraFacade.class );
		TituloPagarFacade facade = TituloPagarFacade.getInstance();

		TituloPagarDTO parcelaQuitar = TituloPagarFacade.getInstance().filtrarPorId( "5" );

		String dataRecebimento = "19/04/2018";
		String valorPago = "1200";

		/** INSERINDO COMPRA */
		facade.quitar( usuarioSessaoPOJO, parcelaQuitar, FormaPagamentoFacade.getInstance().filtrarPorId( "1" ), dataRecebimento, valorPago );
	}

	private void testarQuitacaoParcial( UsuarioSessaoPOJO usuarioSessaoPOJO ) throws ApplicationException, ModelFactoryException {
		this.logger.info( "Criando a instancia do FACADE: " + CompraFacade.class );
		TituloPagarFacade facade = TituloPagarFacade.getInstance();

		TituloPagarDTO parcelaQuitar = TituloPagarFacade.getInstance().filtrarPorId( "32" );

		String dataRecebimento = "19/04/2018";
		String valorPago = "10";

		/** INSERINDO COMPRA */
		facade.quitar( usuarioSessaoPOJO, parcelaQuitar, FormaPagamentoFacade.getInstance().filtrarPorId( "1" ), dataRecebimento, valorPago );
	}

	private void testarQuitacaoComAcrescimo( UsuarioSessaoPOJO usuarioSessaoPOJO ) throws ApplicationException, ModelFactoryException {
		this.logger.info( "Criando a instancia do FACADE: " + CompraFacade.class );
		TituloPagarFacade facade = TituloPagarFacade.getInstance();

		TituloPagarDTO parcelaQuitar = TituloPagarFacade.getInstance().filtrarPorId( "8" );

		String dataRecebimento = "19/04/2018";
		String valorPago = "1500";

		/** INSERINDO COMPRA */
		facade.quitar( usuarioSessaoPOJO, parcelaQuitar, FormaPagamentoFacade.getInstance().filtrarPorId( "1" ), dataRecebimento, valorPago );
	}

	private void testarEstorno( UsuarioSessaoPOJO usuarioSessaoPOJO ) throws ApplicationException, ModelFactoryException {
		this.logger.info( "Criando a instancia do FACADE: " + CompraFacade.class );
		TituloPagarFacade facade = TituloPagarFacade.getInstance();

		TituloPagarDTO parcelaEstornar = TituloPagarFacade.getInstance().filtrarPorId( "7" );

		/** INSERINDO COMPRA */
		facade.estornar( usuarioSessaoPOJO, parcelaEstornar );
	}
}
