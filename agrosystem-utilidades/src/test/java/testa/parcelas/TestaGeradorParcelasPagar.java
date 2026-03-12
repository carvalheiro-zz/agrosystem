package testa.parcelas;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import br.com.srcsoftware.financeiro.gerenciadortitulos.GerenciadorTituloPagar;
import br.com.srcsoftware.modular.manager.empresa.EmpresaPO;
import br.com.srcsoftware.modular.manager.pessoa.fornecedor.FornecedorPO;
import br.com.srcsoftware.sistema.financeiro.formapagamento.FormaPagamentoPO;
import br.com.srcsoftware.sistema.financeiro.titulo.titulopagar.TituloPagarPO;

public class TestaGeradorParcelasPagar{

	public static void main( String[ ] args ) {

		//Utilidades.geradorParcelasPagar();
		FormaPagamentoPO formaPagamento;
		String nomeUsuario;
		LocalDate dataVencimentoPrimeiraParcela;
		FornecedorPO fornecedor;
		BigDecimal valorEntrada;
		EmpresaPO empresa;
		FormaPagamentoPO formaPagamentoEntrada;
		BigDecimal valor;

		/** Testando a geração de Parcela a vista pra hoje */
		formaPagamento = new FormaPagamentoPO();
		formaPagamento.setNome( "Dinheiro" );

		nomeUsuario = "Teste Gabriel";

		fornecedor = new FornecedorPO();
		fornecedor.getPessoaJuridica().setRazaoSocial( "A Santa Terezinha" );

		empresa = new EmpresaPO();
		empresa.setRazaoSocial( "SRC Software" );

		valor = new BigDecimal( "1500.90" );

		dataVencimentoPrimeiraParcela = null;
		valorEntrada = null;
		formaPagamentoEntrada = null;

		testarTituloAVistaPraHoje( fornecedor, dataVencimentoPrimeiraParcela, empresa, nomeUsuario, formaPagamento, valor, formaPagamentoEntrada, valorEntrada );

		testarTituloAVistaPraDepois( fornecedor, dataVencimentoPrimeiraParcela, empresa, nomeUsuario, formaPagamento, valor, formaPagamentoEntrada, valorEntrada );

		testarTituloAVistaPraAntes( fornecedor, dataVencimentoPrimeiraParcela, empresa, nomeUsuario, formaPagamento, valor, formaPagamentoEntrada, valorEntrada );

		testarTituloAPrazoSemEntrada( fornecedor, empresa, nomeUsuario, formaPagamentoEntrada );

		testarTituloAPrazoComEntrada( fornecedor, empresa, nomeUsuario, formaPagamentoEntrada );

		testarTituloAPrazoSemEntradaPraHoje( fornecedor, empresa, nomeUsuario, formaPagamentoEntrada );

		testarTituloPeriodico( fornecedor, empresa, nomeUsuario );

		testarQuitacaoRapida( empresa, fornecedor );

		testarQuitacaoComAcrescimo( empresa, fornecedor );

		testarQuitacaoSemAcrescimo( empresa, fornecedor );

		testarQuitacaoParcial( empresa, fornecedor );

	}

	private static void testarQuitacaoRapida( EmpresaPO empresa, FornecedorPO fornecedor ) {
		String nomeUsuario = "Eu";
		FormaPagamentoPO formaPagamento = new FormaPagamentoPO();
		formaPagamento.setNome( "Dinheiro" );

		TituloPagarPO titulo = GerenciadorTituloPagar.geradorTitulos( "Eu", null, LocalDate.parse( "2018-04-11" ), empresa, fornecedor, Byte.valueOf( "1" ), null, new BigDecimal( "20150" ), null, null ).get( 0 );

		System.out.println( "##### TITULO ABERTO #####" );
		System.out.println( "Dt Lan�amento:-" + titulo.getDataLancamento() );
		System.out.println( "Tipo:----------" + titulo.getTipo() );
		System.out.println( "Parcelas:------" + titulo.getNumero() );
		System.out.println( "Dt Vencimento:-" + titulo.getDataVencimento() );
		System.out.println( "Dt Recebimento:" + titulo.getDataRecebimento() );
		System.out.println( "Valor:---------" + titulo.getValor() );
		System.out.println( "Acr�scimo:-----" + titulo.getAcrescimo() );
		System.out.println( "Valor Final:---" + titulo.getValorFinal() );
		System.out.println( "Valor Pago:----" + titulo.getValorPago() );
		System.out.println( "Situação:------" + titulo.getSituacao() );

		GerenciadorTituloPagar.montarTituloQuitadoRapido( nomeUsuario, titulo, formaPagamento );

		System.out.println( "##### TITULO QUITADO #####" );
		System.out.println( "Dt Lan�amento:-" + titulo.getDataLancamento() );
		System.out.println( "Tipo:----------" + titulo.getTipo() );
		System.out.println( "Parcelas:------" + titulo.getNumero() );
		System.out.println( "Dt Vencimento:-" + titulo.getDataVencimento() );
		System.out.println( "Dt Recebimento:" + titulo.getDataRecebimento() );
		System.out.println( "Valor:---------" + titulo.getValor() );
		System.out.println( "Acr�scimo:-----" + titulo.getAcrescimo() );
		System.out.println( "Valor Final:---" + titulo.getValorFinal() );
		System.out.println( "Valor Pago:----" + titulo.getValorPago() );
		System.out.println( "Situação:------" + titulo.getSituacao() );
	}

	private static void testarQuitacaoComAcrescimo( EmpresaPO empresa, FornecedorPO fornecedor ) {
		String nomeUsuario = "Eu";
		FormaPagamentoPO formaPagamento = new FormaPagamentoPO();
		formaPagamento.setNome( "Dinheiro" );

		TituloPagarPO titulo = GerenciadorTituloPagar.geradorTitulos( "Eu", null, LocalDate.parse( "2018-01-04" ), empresa, fornecedor, Byte.valueOf( "1" ), null, new BigDecimal( "100" ), null, null ).get( 0 );

		System.out.println( "##### TITULO ABERTO #####" );
		System.out.println( "Dt Lan�amento:-" + titulo.getDataLancamento() );
		System.out.println( "Tipo:----------" + titulo.getTipo() );
		System.out.println( "Parcelas:------" + titulo.getNumero() );
		System.out.println( "Dt Vencimento:-" + titulo.getDataVencimento() );
		System.out.println( "Dt Recebimento:" + titulo.getDataRecebimento() );
		System.out.println( "Valor:---------" + titulo.getValor() );
		System.out.println( "Acr�scimo:-----" + titulo.getAcrescimo() );
		System.out.println( "Valor Final:---" + titulo.getValorFinal() );
		System.out.println( "Valor Pago:----" + titulo.getValorPago() );
		System.out.println( "Situação:------" + titulo.getSituacao() );

		GerenciadorTituloPagar.montarTituloQuitado( nomeUsuario, titulo, formaPagamento, LocalDate.parse( "2018-04-12" ), new BigDecimal( "120" ) );

		System.out.println( "##### TITULO QUITADO #####" );
		System.out.println( "Dt Lan�amento:-" + titulo.getDataLancamento() );
		System.out.println( "Tipo:----------" + titulo.getTipo() );
		System.out.println( "Parcelas:------" + titulo.getNumero() );
		System.out.println( "Dt Vencimento:-" + titulo.getDataVencimento() );
		System.out.println( "Dt Recebimento:" + titulo.getDataRecebimento() );
		System.out.println( "Valor:---------" + titulo.getValor() );
		System.out.println( "Acr�scimo:-----" + titulo.getAcrescimo() );
		System.out.println( "Valor Final:---" + titulo.getValorFinal() );
		System.out.println( "Valor Pago:----" + titulo.getValorPago() );
		System.out.println( "Situação:------" + titulo.getSituacao() );
	}

	private static void testarQuitacaoSemAcrescimo( EmpresaPO empresa, FornecedorPO fornecedor ) {
		String nomeUsuario = "Eu";
		FormaPagamentoPO formaPagamento = new FormaPagamentoPO();
		formaPagamento.setNome( "Dinheiro" );

		TituloPagarPO titulo = GerenciadorTituloPagar.geradorTitulos( "Eu", null, LocalDate.parse( "2018-01-04" ), empresa, fornecedor, Byte.valueOf( "1" ), null, new BigDecimal( "100" ), null, null ).get( 0 );

		System.out.println( "##### TITULO ABERTO #####" );
		System.out.println( "Dt Lan�amento:-" + titulo.getDataLancamento() );
		System.out.println( "Tipo:----------" + titulo.getTipo() );
		System.out.println( "Parcelas:------" + titulo.getNumero() );
		System.out.println( "Dt Vencimento:-" + titulo.getDataVencimento() );
		System.out.println( "Dt Recebimento:" + titulo.getDataRecebimento() );
		System.out.println( "Valor:---------" + titulo.getValor() );
		System.out.println( "Acr�scimo:-----" + titulo.getAcrescimo() );
		System.out.println( "Valor Final:---" + titulo.getValorFinal() );
		System.out.println( "Valor Pago:----" + titulo.getValorPago() );
		System.out.println( "Situação:------" + titulo.getSituacao() );

		GerenciadorTituloPagar.montarTituloQuitado( nomeUsuario, titulo, formaPagamento, LocalDate.parse( "2018-04-12" ), new BigDecimal( "100" ) );

		System.out.println( "##### TITULO QUITADO #####" );
		System.out.println( "Dt Lan�amento:-" + titulo.getDataLancamento() );
		System.out.println( "Tipo:----------" + titulo.getTipo() );
		System.out.println( "Parcelas:------" + titulo.getNumero() );
		System.out.println( "Dt Vencimento:-" + titulo.getDataVencimento() );
		System.out.println( "Dt Recebimento:" + titulo.getDataRecebimento() );
		System.out.println( "Valor:---------" + titulo.getValor() );
		System.out.println( "Acr�scimo:-----" + titulo.getAcrescimo() );
		System.out.println( "Valor Final:---" + titulo.getValorFinal() );
		System.out.println( "Valor Pago:----" + titulo.getValorPago() );
		System.out.println( "Situação:------" + titulo.getSituacao() );
	}

	private static void testarQuitacaoParcial( EmpresaPO empresa, FornecedorPO fornecedor ) {
		String nomeUsuario = "Eu";
		FormaPagamentoPO formaPagamento = new FormaPagamentoPO();
		formaPagamento.setNome( "Dinheiro" );

		TituloPagarPO titulo = GerenciadorTituloPagar.geradorTitulos( "Eu", null, LocalDate.parse( "2018-01-04" ), empresa, fornecedor, Byte.valueOf( "1" ), null, new BigDecimal( "100" ), null, null ).get( 0 );

		System.out.println( "##### TITULO ABERTO #####" );
		System.out.println( "Dt Lan�amento:-" + titulo.getDataLancamento() );
		System.out.println( "Tipo:----------" + titulo.getTipo() );
		System.out.println( "Parcelas:------" + titulo.getNumero() );
		System.out.println( "Dt Vencimento:-" + titulo.getDataVencimento() );
		System.out.println( "Dt Recebimento:" + titulo.getDataRecebimento() );
		System.out.println( "Valor:---------" + titulo.getValor() );
		System.out.println( "Acr�scimo:-----" + titulo.getAcrescimo() );
		System.out.println( "Valor Final:---" + titulo.getValorFinal() );
		System.out.println( "Valor Pago:----" + titulo.getValorPago() );
		System.out.println( "Situação:------" + titulo.getSituacao() );

		GerenciadorTituloPagar.montarTituloQuitado( nomeUsuario, titulo, formaPagamento, LocalDate.parse( "2018-04-12" ), new BigDecimal( "50" ) );

		System.out.println( "##### TITULO QUITADO #####" );
		System.out.println( "Dt Lan�amento:-" + titulo.getDataLancamento() );
		System.out.println( "Tipo:----------" + titulo.getTipo() );
		System.out.println( "Parcelas:------" + titulo.getNumero() );
		System.out.println( "Dt Vencimento:-" + titulo.getDataVencimento() );
		System.out.println( "Dt Recebimento:" + titulo.getDataRecebimento() );
		System.out.println( "Valor:---------" + titulo.getValor() );
		System.out.println( "Acr�scimo:-----" + titulo.getAcrescimo() );
		System.out.println( "Valor Final:---" + titulo.getValorFinal() );
		System.out.println( "Valor Pago:----" + titulo.getValorPago() );
		System.out.println( "Situação:------" + titulo.getSituacao() );

		System.out.println( "##### TITULO PARCIAL #####" );
		System.out.println( "Dt Lan�amento:-" + titulo.getParcial().getDataLancamento() );
		System.out.println( "Tipo:----------" + titulo.getParcial().getTipo() );
		System.out.println( "Parcelas:------" + titulo.getParcial().getNumero() );
		System.out.println( "Dt Vencimento:-" + titulo.getParcial().getDataVencimento() );
		System.out.println( "Dt Recebimento:" + titulo.getParcial().getDataRecebimento() );
		System.out.println( "Valor:---------" + titulo.getParcial().getValor() );
		System.out.println( "Acr�scimo:-----" + titulo.getParcial().getAcrescimo() );
		System.out.println( "Valor Final:---" + titulo.getParcial().getValorFinal() );
		System.out.println( "Valor Pago:----" + titulo.getParcial().getValorPago() );
		System.out.println( "Situação:------" + titulo.getParcial().getSituacao() );
	}

	private static void testarTituloAVistaPraDepois( FornecedorPO fornecedor,
	        LocalDate dataVencimentoPrimeiraParcela,
	        EmpresaPO empresa,
	        String nomeUsuario,
	        FormaPagamentoPO formaPagamento,
	        BigDecimal valor,
	        FormaPagamentoPO formaPagamentoEntrada,
	        BigDecimal valorEntrada ) {

		Byte quantidadeParcelas = Byte.valueOf( "1" );
		LocalDate dataLancamento = LocalDate.parse( "2018-04-20" );

		ArrayList< TituloPagarPO > parcelaAVistaGerada = GerenciadorTituloPagar.geradorTitulos( nomeUsuario, dataVencimentoPrimeiraParcela, dataLancamento, empresa, fornecedor, quantidadeParcelas, formaPagamento, valor, valorEntrada, formaPagamentoEntrada );
		System.out.println( parcelaAVistaGerada );

		System.out.println( "##### TITULO A VISTA PRA DEPOIS #####" );
		for ( TituloPagarPO tituloCorrente : parcelaAVistaGerada ) {
			System.out.println( "Dt Lan�amento:-" + tituloCorrente.getDataLancamento() );
			System.out.println( "Tipo:----------" + tituloCorrente.getTipo() );
			System.out.println( "Parcelas:------" + tituloCorrente.getNumero() );
			System.out.println( "Dt Vencimento:-" + tituloCorrente.getDataVencimento() );
			System.out.println( "Dt Recebimento:" + tituloCorrente.getDataRecebimento() );
			System.out.println( "Valor:---------" + tituloCorrente.getValor() );
			System.out.println( "Acr�scimo:-----" + tituloCorrente.getAcrescimo() );
			System.out.println( "Valor Final:---" + tituloCorrente.getValorFinal() );
			System.out.println( "Valor Pago:----" + tituloCorrente.getValorPago() );
			System.out.println( "Situação:------" + tituloCorrente.getSituacao() );

		}

	}

	private static void testarTituloAVistaPraAntes( FornecedorPO fornecedor,
	        LocalDate dataVencimentoPrimeiraParcela,
	        EmpresaPO empresa,
	        String nomeUsuario,
	        FormaPagamentoPO formaPagamento,
	        BigDecimal valor,
	        FormaPagamentoPO formaPagamentoEntrada,
	        BigDecimal valorEntrada ) {

		Byte quantidadeParcelas = Byte.valueOf( "1" );
		LocalDate dataLancamento = LocalDate.parse( "2018-03-20" );

		ArrayList< TituloPagarPO > parcelaAVistaGerada = GerenciadorTituloPagar.geradorTitulos( nomeUsuario, dataVencimentoPrimeiraParcela, dataLancamento, empresa, fornecedor, quantidadeParcelas, formaPagamento, valor, valorEntrada, formaPagamentoEntrada );
		System.out.println( parcelaAVistaGerada );

		System.out.println( "##### TITULO A VISTA PRA ANTES #####" );
		for ( TituloPagarPO tituloCorrente : parcelaAVistaGerada ) {
			System.out.println( "Dt Lan�amento:-" + tituloCorrente.getDataLancamento() );
			System.out.println( "Tipo:----------" + tituloCorrente.getTipo() );
			System.out.println( "Parcelas:------" + tituloCorrente.getNumero() );
			System.out.println( "Dt Vencimento:-" + tituloCorrente.getDataVencimento() );
			System.out.println( "Dt Recebimento:" + tituloCorrente.getDataRecebimento() );
			System.out.println( "Valor:---------" + tituloCorrente.getValor() );
			System.out.println( "Acr�scimo:-----" + tituloCorrente.getAcrescimo() );
			System.out.println( "Valor Final:---" + tituloCorrente.getValorFinal() );
			System.out.println( "Valor Pago:----" + tituloCorrente.getValorPago() );
			System.out.println( "Situação:------" + tituloCorrente.getSituacao() );

		}

	}

	private static void testarTituloAVistaPraHoje( FornecedorPO fornecedor,
	        LocalDate dataVencimentoPrimeiraParcela,
	        EmpresaPO empresa,
	        String nomeUsuario,
	        FormaPagamentoPO formaPagamento,
	        BigDecimal valor,
	        FormaPagamentoPO formaPagamentoEntrada,
	        BigDecimal valorEntrada ) {
		Byte quantidadeParcelas = Byte.valueOf( "1" );
		LocalDate dataLancamento = LocalDate.now();

		ArrayList< TituloPagarPO > parcelaAVistaGerada = GerenciadorTituloPagar.geradorTitulos( nomeUsuario, dataVencimentoPrimeiraParcela, dataLancamento, empresa, fornecedor, quantidadeParcelas, formaPagamento, valor, valorEntrada, formaPagamentoEntrada );
		System.out.println( parcelaAVistaGerada );

		System.out.println( "##### TITULO A VISTA QUITADO #####" );
		for ( TituloPagarPO tituloCorrente : parcelaAVistaGerada ) {
			System.out.println( "Dt Lan�amento:-" + tituloCorrente.getDataLancamento() );
			System.out.println( "Tipo:----------" + tituloCorrente.getTipo() );
			System.out.println( "Parcelas:------" + tituloCorrente.getNumero() );
			System.out.println( "Dt Vencimento:-" + tituloCorrente.getDataVencimento() );
			System.out.println( "Dt Recebimento:" + tituloCorrente.getDataRecebimento() );
			System.out.println( "Valor:---------" + tituloCorrente.getValor() );
			System.out.println( "Acr�scimo:-----" + tituloCorrente.getAcrescimo() );
			System.out.println( "Valor Final:---" + tituloCorrente.getValorFinal() );
			System.out.println( "Valor Pago:----" + tituloCorrente.getValorPago() );
			System.out.println( "Situação:------" + tituloCorrente.getSituacao() );
		}
	}

	private static void testarTituloAPrazoSemEntrada( FornecedorPO fornecedor, EmpresaPO empresa, String nomeUsuario, FormaPagamentoPO formaPagamento ) {

		FormaPagamentoPO formaPagamentoEntrada = null;
		BigDecimal valorEntrada = null;
		Byte quantidadeParcelas = Byte.valueOf( "3" );
		LocalDate dataLancamento = LocalDate.now();
		LocalDate dataVencimentoPrimeiraParcela = LocalDate.parse( "2018-05-20" );
		BigDecimal valor = new BigDecimal( "1501" );

		ArrayList< TituloPagarPO > parcelasAPrazoGerada = GerenciadorTituloPagar.geradorTitulos( nomeUsuario, dataVencimentoPrimeiraParcela, dataLancamento, empresa, fornecedor, quantidadeParcelas, formaPagamento, valor, valorEntrada, formaPagamentoEntrada );
		System.out.println( parcelasAPrazoGerada );

		System.out.println( "##### TITULO A PRAZO #####" );
		System.out.println( "Valor venda: " + valor );
		for ( TituloPagarPO tituloCorrente : parcelasAPrazoGerada ) {
			System.out.println( "Dt Lan�amento:-" + tituloCorrente.getDataLancamento() );
			System.out.println( "Tipo:----------" + tituloCorrente.getTipo() );
			System.out.println( "Parcelas:------" + tituloCorrente.getNumero() );
			System.out.println( "Dt Vencimento:-" + tituloCorrente.getDataVencimento() );
			System.out.println( "Dt Recebimento:" + tituloCorrente.getDataRecebimento() );
			System.out.println( "Valor:---------" + tituloCorrente.getValor() );
			System.out.println( "Acr�scimo:-----" + tituloCorrente.getAcrescimo() );
			System.out.println( "Valor Final:---" + tituloCorrente.getValorFinal() );
			System.out.println( "Valor Pago:----" + tituloCorrente.getValorPago() );
			System.out.println( "Situação:------" + tituloCorrente.getSituacao() );
			System.out.println( "###########" );
		}
	}

	private static void testarTituloAPrazoComEntrada( FornecedorPO fornecedor, EmpresaPO empresa, String nomeUsuario, FormaPagamentoPO formaPagamento ) {

		FormaPagamentoPO formaPagamentoEntrada = null;
		BigDecimal valorEntrada = new BigDecimal( "501" );
		Byte quantidadeParcelas = Byte.valueOf( "127" );
		LocalDate dataLancamento = LocalDate.now();
		LocalDate dataVencimentoPrimeiraParcela = LocalDate.parse( "2018-05-12" );
		BigDecimal valor = new BigDecimal( "1501" );

		ArrayList< TituloPagarPO > parcelasAPrazoGerada = GerenciadorTituloPagar.geradorTitulos( nomeUsuario, dataVencimentoPrimeiraParcela, dataLancamento, empresa, fornecedor, quantidadeParcelas, formaPagamento, valor, valorEntrada, formaPagamentoEntrada );
		System.out.println( parcelasAPrazoGerada );

		System.out.println( "##### TITULO A PRAZO #####" );
		System.out.println( "Valor venda: " + valor );
		for ( TituloPagarPO tituloCorrente : parcelasAPrazoGerada ) {
			System.out.println( "Dt Lan�amento:-" + tituloCorrente.getDataLancamento() );
			System.out.println( "Tipo:----------" + tituloCorrente.getTipo() );
			System.out.println( "Parcelas:------" + tituloCorrente.getNumero() );
			System.out.println( "Dt Vencimento:-" + tituloCorrente.getDataVencimento() );
			System.out.println( "Dt Recebimento:" + tituloCorrente.getDataRecebimento() );
			System.out.println( "Valor:---------" + tituloCorrente.getValor() );
			System.out.println( "Acr�scimo:-----" + tituloCorrente.getAcrescimo() );
			System.out.println( "Valor Final:---" + tituloCorrente.getValorFinal() );
			System.out.println( "Valor Pago:----" + tituloCorrente.getValorPago() );
			System.out.println( "Situação:------" + tituloCorrente.getSituacao() );
			System.out.println( "###########" );
		}
	}

	private static void testarTituloAPrazoSemEntradaPraHoje( FornecedorPO fornecedor, EmpresaPO empresa, String nomeUsuario, FormaPagamentoPO formaPagamento ) {

		FormaPagamentoPO formaPagamentoEntrada = null;
		BigDecimal valorEntrada = null;
		Byte quantidadeParcelas = Byte.valueOf( "3" );
		LocalDate dataLancamento = LocalDate.now();
		LocalDate dataVencimentoPrimeiraParcela = LocalDate.parse( "2018-04-12" );
		BigDecimal valor = new BigDecimal( "1501" );

		ArrayList< TituloPagarPO > parcelasAPrazoGerada = GerenciadorTituloPagar.geradorTitulos( nomeUsuario, dataVencimentoPrimeiraParcela, dataLancamento, empresa, fornecedor, quantidadeParcelas, formaPagamento, valor, valorEntrada, formaPagamentoEntrada );
		System.out.println( parcelasAPrazoGerada );

		System.out.println( "##### TITULO A PRAZO #####" );
		System.out.println( "Valor venda: " + valor );
		for ( TituloPagarPO tituloCorrente : parcelasAPrazoGerada ) {
			System.out.println( "Dt Lan�amento:-" + tituloCorrente.getDataLancamento() );
			System.out.println( "Tipo:----------" + tituloCorrente.getTipo() );
			System.out.println( "Parcelas:------" + tituloCorrente.getNumero() );
			System.out.println( "Dt Vencimento:-" + tituloCorrente.getDataVencimento() );
			System.out.println( "Dt Recebimento:" + tituloCorrente.getDataRecebimento() );
			System.out.println( "Valor:---------" + tituloCorrente.getValor() );
			System.out.println( "Acr�scimo:-----" + tituloCorrente.getAcrescimo() );
			System.out.println( "Valor Final:---" + tituloCorrente.getValorFinal() );
			System.out.println( "Valor Pago:----" + tituloCorrente.getValorPago() );
			System.out.println( "Situação:------" + tituloCorrente.getSituacao() );
			System.out.println( "###########" );
		}
	}

	/*private static void testarTituloPeriodico( FornecedorPadraoPO fornecedor, EmpresaPO empresa, String nomeUsuario ) {
		LocalDate dataLancamento = LocalDate.now();
		LocalDate dataVencimento = LocalDate.parse( "2018-04-20" );
		BigDecimal valor = new BigDecimal( "1501" );
	
		TituloPagarPO periodicoGerada = GerenciadorTituloPagar.geradorTituloPeriodico( nomeUsuario, LocalDateTime.now(), dataLancamento, dataVencimento, empresa, fornecedor, valor );
		System.out.println( periodicoGerada );
	
		System.out.println( "##### TITULO PERIODICO #####" );
		System.out.println( "Dt Lan�amento:-" + periodicoGerada.getDataLancamento() );
		System.out.println( "Tipo:----------" + periodicoGerada.getTipo() );
		System.out.println( "Parcelas:------" + periodicoGerada.getNumero() );
		System.out.println( "Dt Vencimento:-" + periodicoGerada.getDataVencimento() );
		System.out.println( "Dt Recebimento:" + periodicoGerada.getDataRecebimento() );
		System.out.println( "Valor:---------" + periodicoGerada.getValor() );
		System.out.println( "Acr�scimo:-----" + periodicoGerada.getAcrescimo() );
		System.out.println( "Valor Final:---" + periodicoGerada.getValorFinal() );
		System.out.println( "Valor Pago:----" + periodicoGerada.getValorPago() );
		System.out.println( "Situação:------" + periodicoGerada.getSituacao() );
	}*/

}
