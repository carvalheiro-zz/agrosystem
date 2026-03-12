package testa.parcelas;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import br.com.srcsoftware.financeiro.gerenciadortitulos.GerenciadorTituloReceber;
import br.com.srcsoftware.modular.manager.empresa.EmpresaPO;
import br.com.srcsoftware.modular.manager.pessoa.cliente.ClientePadraoPO;
import br.com.srcsoftware.sistema.financeiro.formapagamento.FormaPagamentoPO;
import br.com.srcsoftware.sistema.financeiro.titulo.tituloreceber.TituloReceberPO;

public class TestaGeradorParcelasReceber{

	public static void main( String[ ] args ) {

		//Utilidades.geradorParcelasReceber();
		FormaPagamentoPO formaPagamento;
		String nomeUsuario;
		LocalDate dataVencimentoPrimeiraParcela;
		ClientePadraoPO cliente;
		BigDecimal valorEntrada;
		EmpresaPO empresa;
		FormaPagamentoPO formaPagamentoEntrada;
		BigDecimal valor;

		/** Testando a geração de Parcela a vista pra hoje */
		formaPagamento = new FormaPagamentoPO();
		formaPagamento.setNome( "Dinheiro" );

		nomeUsuario = "Teste Gabriel";

		cliente = new ClientePadraoPO();
		cliente.getPessoaFisica().setRazaoSocial( "A Santa Terezinha" );

		empresa = new EmpresaPO();
		empresa.setRazaoSocial( "SRC Software" );

		valor = new BigDecimal( "1500.90" );

		dataVencimentoPrimeiraParcela = null;
		valorEntrada = null;
		formaPagamentoEntrada = null;

		//testarTituloAVistaPraHoje( cliente, dataVencimentoPrimeiraParcela, empresa, nomeUsuario, formaPagamento, valor, formaPagamentoEntrada, valorEntrada );

		//testarTituloAVistaPraDepois( cliente, dataVencimentoPrimeiraParcela, empresa, nomeUsuario, formaPagamento, valor, formaPagamentoEntrada, valorEntrada );

		//testarTituloAVistaPraAntes( cliente, dataVencimentoPrimeiraParcela, empresa, nomeUsuario, formaPagamento, valor, formaPagamentoEntrada, valorEntrada );

		//testarTituloAPrazoSemEntrada( cliente, empresa, nomeUsuario, formaPagamentoEntrada );

		//testarTituloAPrazoComEntrada( cliente, empresa, nomeUsuario, formaPagamentoEntrada );

		//testarTituloAPrazoSemEntradaPraHoje( cliente, empresa, nomeUsuario, formaPagamentoEntrada );

		//testarTituloLicenca( cliente, empresa, nomeUsuario );

		//testarQuitacaoRapida( empresa, cliente );

		//testarQuitacaoComAcrescimo( empresa, cliente );

		//testarQuitacaoSemAcrescimo( empresa, cliente );

		//testarQuitacaoParcial( empresa, cliente );

	}

	private static void testarQuitacaoRapida( EmpresaPO empresa, ClientePadraoPO cliente ) {
		String nomeUsuario = "Eu";
		FormaPagamentoPO formaPagamento = new FormaPagamentoPO();
		formaPagamento.setNome( "Dinheiro" );

		TituloReceberPO titulo = GerenciadorTituloReceber.geradorTitulos( "Eu", null, LocalDate.parse( "2018-04-11" ), empresa, cliente, Byte.valueOf( "1" ), null, new BigDecimal( "20150" ), null, null, null ).get( 0 );

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

		GerenciadorTituloReceber.montarTituloQuitadoRapido( nomeUsuario, titulo, formaPagamento );

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

	private static void testarQuitacaoComAcrescimo( EmpresaPO empresa, ClientePadraoPO cliente ) {
		String nomeUsuario = "Eu";
		FormaPagamentoPO formaPagamento = new FormaPagamentoPO();
		formaPagamento.setNome( "Dinheiro" );

		TituloReceberPO titulo = GerenciadorTituloReceber.geradorTitulos( "Eu", null, LocalDate.parse( "2018-01-04" ), empresa, cliente, Byte.valueOf( "1" ), null, new BigDecimal( "100" ), null, null, null ).get( 0 );

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

		GerenciadorTituloReceber.montarTituloQuitado( nomeUsuario, titulo, formaPagamento, LocalDate.parse( "2018-04-12" ), new BigDecimal( "120" ), new BigDecimal( "2" ), new BigDecimal( "1" ) );

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

	private static void testarQuitacaoSemAcrescimo( EmpresaPO empresa, ClientePadraoPO cliente ) {
		String nomeUsuario = "Eu";
		FormaPagamentoPO formaPagamento = new FormaPagamentoPO();
		formaPagamento.setNome( "Dinheiro" );

		TituloReceberPO titulo = GerenciadorTituloReceber.geradorTitulos( "Eu", null, LocalDate.parse( "2018-01-04" ), empresa, cliente, Byte.valueOf( "1" ), null, new BigDecimal( "100" ), null, null, null ).get( 0 );

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

		GerenciadorTituloReceber.montarTituloQuitado( nomeUsuario, titulo, formaPagamento, LocalDate.parse( "2018-04-12" ), new BigDecimal( "100" ), new BigDecimal( "0" ), new BigDecimal( "0" ) );

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

	private static void testarQuitacaoParcial( EmpresaPO empresa, ClientePadraoPO cliente ) {
		String nomeUsuario = "Eu";
		FormaPagamentoPO formaPagamento = new FormaPagamentoPO();
		formaPagamento.setNome( "Dinheiro" );

		TituloReceberPO titulo = GerenciadorTituloReceber.geradorTitulos( "Eu", null, LocalDate.parse( "2018-01-04" ), empresa, cliente, Byte.valueOf( "1" ), null, new BigDecimal( "100" ), null, null, null ).get( 0 );

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

		GerenciadorTituloReceber.montarTituloQuitado( nomeUsuario, titulo, formaPagamento, LocalDate.parse( "2018-04-12" ), new BigDecimal( "50" ), new BigDecimal( "2" ), new BigDecimal( "1" ) );

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

	private static void testarTituloAVistaPraDepois( ClientePadraoPO cliente,
	        LocalDate dataVencimentoPrimeiraParcela,
	        EmpresaPO empresa,
	        String nomeUsuario,
	        FormaPagamentoPO formaPagamento,
	        BigDecimal valor,
	        FormaPagamentoPO formaPagamentoEntrada,
	        BigDecimal valorEntrada ) {

		Byte quantidadeParcelas = Byte.valueOf( "1" );
		LocalDate dataLancamento = LocalDate.parse( "2018-04-20" );
		BigDecimal multa = new BigDecimal( "3" );

		ArrayList< TituloReceberPO > parcelaAVistaGerada = GerenciadorTituloReceber.geradorTitulos( nomeUsuario, dataVencimentoPrimeiraParcela, dataLancamento, empresa, cliente, quantidadeParcelas, formaPagamento, valor, valorEntrada, formaPagamentoEntrada, multa );
		System.out.println( parcelaAVistaGerada );

		System.out.println( "##### TITULO A VISTA PRA DEPOIS #####" );
		for ( TituloReceberPO tituloCorrente : parcelaAVistaGerada ) {
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

	private static void testarTituloAVistaPraAntes( ClientePadraoPO cliente,
	        LocalDate dataVencimentoPrimeiraParcela,
	        EmpresaPO empresa,
	        String nomeUsuario,
	        FormaPagamentoPO formaPagamento,
	        BigDecimal valor,
	        FormaPagamentoPO formaPagamentoEntrada,
	        BigDecimal valorEntrada ) {

		Byte quantidadeParcelas = Byte.valueOf( "1" );
		LocalDate dataLancamento = LocalDate.parse( "2018-03-20" );
		BigDecimal multa = new BigDecimal( "3" );

		ArrayList< TituloReceberPO > parcelaAVistaGerada = GerenciadorTituloReceber.geradorTitulos( nomeUsuario, dataVencimentoPrimeiraParcela, dataLancamento, empresa, cliente, quantidadeParcelas, formaPagamento, valor, valorEntrada, formaPagamentoEntrada, multa );
		System.out.println( parcelaAVistaGerada );

		System.out.println( "##### TITULO A VISTA PRA ANTES #####" );
		for ( TituloReceberPO tituloCorrente : parcelaAVistaGerada ) {
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

	private static void testarTituloAVistaPraHoje( ClientePadraoPO cliente,
	        LocalDate dataVencimentoPrimeiraParcela,
	        EmpresaPO empresa,
	        String nomeUsuario,
	        FormaPagamentoPO formaPagamento,
	        BigDecimal valor,
	        FormaPagamentoPO formaPagamentoEntrada,
	        BigDecimal valorEntrada ) {

		Byte quantidadeParcelas = Byte.valueOf( "1" );
		LocalDate dataLancamento = LocalDate.now();
		BigDecimal multa = new BigDecimal( "3" );

		ArrayList< TituloReceberPO > parcelaAVistaGerada = GerenciadorTituloReceber.geradorTitulos( nomeUsuario, dataVencimentoPrimeiraParcela, dataLancamento, empresa, cliente, quantidadeParcelas, formaPagamento, valor, valorEntrada, formaPagamentoEntrada, multa );
		System.out.println( parcelaAVistaGerada );

		System.out.println( "##### TITULO A VISTA QUITADO #####" );
		for ( TituloReceberPO tituloCorrente : parcelaAVistaGerada ) {
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

	private static void testarTituloAPrazoSemEntrada( ClientePadraoPO cliente, EmpresaPO empresa, String nomeUsuario, FormaPagamentoPO formaPagamento ) {

		FormaPagamentoPO formaPagamentoEntrada = null;
		BigDecimal valorEntrada = null;
		Byte quantidadeParcelas = Byte.valueOf( "3" );
		LocalDate dataLancamento = LocalDate.now();
		LocalDate dataVencimentoPrimeiraParcela = LocalDate.parse( "2018-05-20" );
		BigDecimal valor = new BigDecimal( "1501" );
		BigDecimal multa = new BigDecimal( "3" );

		ArrayList< TituloReceberPO > parcelasAPrazoGerada = GerenciadorTituloReceber.geradorTitulos( nomeUsuario, dataVencimentoPrimeiraParcela, dataLancamento, empresa, cliente, quantidadeParcelas, formaPagamento, valor, valorEntrada, formaPagamentoEntrada, multa );
		System.out.println( parcelasAPrazoGerada );

		System.out.println( "##### TITULO A PRAZO #####" );
		System.out.println( "Valor venda: " + valor );
		for ( TituloReceberPO tituloCorrente : parcelasAPrazoGerada ) {
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

	private static void testarTituloAPrazoComEntrada( ClientePadraoPO cliente, EmpresaPO empresa, String nomeUsuario, FormaPagamentoPO formaPagamento ) {

		FormaPagamentoPO formaPagamentoEntrada = null;
		BigDecimal valorEntrada = new BigDecimal( "501" );
		Byte quantidadeParcelas = Byte.valueOf( "2" );
		LocalDate dataLancamento = LocalDate.now();
		LocalDate dataVencimentoPrimeiraParcela = LocalDate.parse( "2018-05-12" );
		BigDecimal valor = new BigDecimal( "1501" );
		BigDecimal multa = new BigDecimal( "3" );

		ArrayList< TituloReceberPO > parcelasAPrazoGerada = GerenciadorTituloReceber.geradorTitulos( nomeUsuario, dataVencimentoPrimeiraParcela, dataLancamento, empresa, cliente, quantidadeParcelas, formaPagamento, valor, valorEntrada, formaPagamentoEntrada, multa );
		System.out.println( parcelasAPrazoGerada );

		System.out.println( "##### TITULO A PRAZO #####" );
		System.out.println( "Valor venda: " + valor );
		for ( TituloReceberPO tituloCorrente : parcelasAPrazoGerada ) {
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

	private static void testarTituloAPrazoSemEntradaPraHoje( ClientePadraoPO cliente, EmpresaPO empresa, String nomeUsuario, FormaPagamentoPO formaPagamento ) {

		FormaPagamentoPO formaPagamentoEntrada = null;
		BigDecimal valorEntrada = null;
		Byte quantidadeParcelas = Byte.valueOf( "3" );
		LocalDate dataLancamento = LocalDate.now();
		LocalDate dataVencimentoPrimeiraParcela = LocalDate.now();
		BigDecimal valor = new BigDecimal( "1501" );
		BigDecimal multa = new BigDecimal( "3" );

		ArrayList< TituloReceberPO > parcelasAPrazoGerada = GerenciadorTituloReceber.geradorTitulos( nomeUsuario, dataVencimentoPrimeiraParcela, dataLancamento, empresa, cliente, quantidadeParcelas, formaPagamento, valor, valorEntrada, formaPagamentoEntrada, multa );
		System.out.println( parcelasAPrazoGerada );

		System.out.println( "##### TITULO A PRAZO #####" );
		System.out.println( "Valor venda: " + valor );
		for ( TituloReceberPO tituloCorrente : parcelasAPrazoGerada ) {
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

	private static void testarTituloLicenca( ClientePadraoPO cliente, EmpresaPO empresa, String nomeUsuario ) {
		LocalDate dataLancamento = LocalDate.now();
		LocalDate dataVencimento = LocalDate.parse( "2018-04-20" );
		BigDecimal valor = new BigDecimal( "1501" );

		TituloReceberPO periodicoGerada = GerenciadorTituloReceber.geradorTituloLicenca( nomeUsuario, dataLancamento, dataVencimento, empresa, cliente, valor );
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
	}

}