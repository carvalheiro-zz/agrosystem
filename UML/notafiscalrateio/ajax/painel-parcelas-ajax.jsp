<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>


<logic:notEmpty name="erroAjax" scope="session">
	<!--ICONE DE ERRO-->
	<script type="text/javascript">
		BootstrapDialog.show({
			onshow : function(dialogRef) {
				/* setTimeout(function(){
					dialogRef.close();
				}, 5000); */
			},
			size : BootstrapDialog.SIZE_LARGE,
			title : 'Atenção...',
			message : "${erroAjax}",
			closable : true,
			type : BootstrapDialog.TYPE_DANGER,
			buttons : [ {
				label : 'Fechar',
				action : function(dialogRef) {
					dialogRef.close();
				}
			} ]
		});
	</script>
</logic:notEmpty>

<div class="row" id="linhaInformativo">
	<div class="alert alert-warning">
		<strong id="informativo"></strong>
	</div>
</div>

<logic:iterate indexId="i" id="parcelaCorrente" name="notaFiscalRateioForm" property="notaFiscalRateio.contaPagar.parcelas" type="br.com.srcsoftware.sistema.financeiro.titulo.titulopagar.TituloPagarDTO">

	<logic:equal value="Aberta" property="situacaoToString" name="parcelaCorrente">
		<div class="alert alert-success">
	</logic:equal>
	<logic:equal value="Quitada" property="situacaoToString" name="parcelaCorrente">
		<div class="alert alert-info" style="background-color: #a6e1ff !important;">
	</logic:equal>
	<logic:equal value="A_Vencer" property="situacaoToString" name="parcelaCorrente">
		<div class="alert alert-warning" style="background-color: #fff0a7 !important;">
	</logic:equal>
	<logic:equal value="Vencida" property="situacaoToString" name="parcelaCorrente">
		<div class="alert alert-danger" style="background-color: #ffa7a7 !important;">
	</logic:equal>

	<div class="row">
		<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-1">
			<label style="font-size: 35px;">${parcelaCorrente.numero}</label>
		</div>

		<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-2">
			<label>Tipo</label>
			<html:text styleClass="form-control input-sm text-center bloqueado" styleId="tipo${i}" property="tipo" name="parcelaCorrente" />
		</div>
		<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-2">
			<label>Situação</label>
			<html:text styleClass="form-control input-sm text-center bloqueado situacao" styleId="situacao${i}" property="situacao" name="parcelaCorrente" />
		</div>

		<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-2">
			<label>Emissão</label>
			<html:text styleClass="form-control input-sm data bloqueado text-center" styleId="dataLancamento${i}" property="dataLancamento" name="parcelaCorrente" />
		</div>
		<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-2">
			<label>Vencimento</label>
			<logic:equal value="Aberta" property="situacao" name="parcelaCorrente">
				<html:text styleClass="form-control input-sm data text-center" styleId="dataVencimento${i}" property="notaFiscalRateio.contaPagar.parcelas[${i}].dataVencimento" name="notaFiscalRateioForm" />
			</logic:equal>
			<logic:equal value="Quitada" property="situacao" name="parcelaCorrente">
				<html:text styleClass="form-control input-sm data text-center bloqueado" styleId="dataVencimento${i}" property="notaFiscalRateio.contaPagar.parcelas[${i}].dataVencimento" name="notaFiscalRateioForm" />
			</logic:equal>
		</div>
		<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-2">
			<label>Pagamento</label>
			<logic:equal value="Aberta" property="situacao" name="parcelaCorrente">
				<html:text styleClass="form-control input-sm data text-center" styleId="dataRecebimento${i}" property="notaFiscalRateio.contaPagar.parcelas[${i}].dataRecebimento" name="notaFiscalRateioForm" />
			</logic:equal>
			<logic:equal value="Quitada" property="situacao" name="parcelaCorrente">
				<html:text styleClass="form-control input-sm data text-center bloqueado" styleId="dataRecebimento${i}" property="notaFiscalRateio.contaPagar.parcelas[${i}].dataRecebimento" name="notaFiscalRateioForm" />
			</logic:equal>

		</div>
		<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-2">
			<label>Forma pagto.</label>
			<logic:equal value="Aberta" property="situacao" name="parcelaCorrente">
				<html:select styleClass="form-control input-sm formaPagamento" styleId="formaPagamento${i}" property="notaFiscalRateio.contaPagar.parcelas[${i}].formaPagamento.id" name="notaFiscalRateioForm">
					<html:option value="">Selecione...</html:option>
					<html:optionsCollection name="notaFiscalRateioForm" property="comboFormaPagamento" label="label" value="value" />
				</html:select>
			</logic:equal>
			<logic:equal value="Quitada" property="situacao" name="parcelaCorrente">
				<html:select styleClass="form-control input-sm formaPagamento bloqueado" styleId="formaPagamento${i}" property="notaFiscalRateio.contaPagar.parcelas[${i}].formaPagamento.id" name="notaFiscalRateioForm">
					<html:option value="">Selecione...</html:option>
					<html:optionsCollection name="notaFiscalRateioForm" property="comboFormaPagamento" label="label" value="value" />
				</html:select>
			</logic:equal>

		</div>
		<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-2">
			<label>Valor</label>
			<logic:equal value="Aberta" property="situacao" name="parcelaCorrente">
				<html:text styleClass="form-control input-sm dinheiro text-right valorParcela ${parcelaCorrente.situacao}Valor" styleId="valor${i}" property="notaFiscalRateio.contaPagar.parcelas[${i}].valor" name="notaFiscalRateioForm" />
			</logic:equal>
			<logic:equal value="Quitada" property="situacao" name="parcelaCorrente">
				<html:text styleClass="form-control input-sm dinheiro text-right valorParcela ${parcelaCorrente.situacao}Valor bloqueado" styleId="valor${i}" property="notaFiscalRateio.contaPagar.parcelas[${i}].valor" name="notaFiscalRateioForm" />
			</logic:equal>

		</div>
		<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-2">
			<label>Acréscimo</label>
			<logic:equal value="Aberta" property="situacao" name="parcelaCorrente">
				<html:text styleClass="form-control input-sm dinheiro text-right ${parcelaCorrente.situacao}Acrescimo" styleId="valorAcrescimo${i}" property="notaFiscalRateio.contaPagar.parcelas[${i}].acrescimo" name="notaFiscalRateioForm" />
			</logic:equal>
			<logic:equal value="Quitada" property="situacao" name="parcelaCorrente">
				<html:text styleClass="form-control input-sm dinheiro text-right ${parcelaCorrente.situacao}Acrescimo bloqueado" styleId="valorAcrescimo${i}" property="notaFiscalRateio.contaPagar.parcelas[${i}].acrescimo" name="notaFiscalRateioForm" />
			</logic:equal>

		</div>
		<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-2">
			<label>Total</label>
			<html:text styleClass="form-control input-sm dinheiro bloqueado text-right" styleId="valorFinal${i}" property="notaFiscalRateio.contaPagar.parcelas[${i}].valorFinal" name="notaFiscalRateioForm" />
		</div>
		<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-2">
			<label>Pago</label>
			<logic:equal value="Aberta" property="situacao" name="parcelaCorrente">
				<html:text styleClass="form-control input-sm dinheiro text-right pago ${parcelaCorrente.situacao}Pago" styleId="valorPago${i}" property="notaFiscalRateio.contaPagar.parcelas[${i}].valorPago" name="notaFiscalRateioForm" />
			</logic:equal>
			<logic:equal value="Quitada" property="situacao" name="parcelaCorrente">
				<html:text styleClass="form-control input-sm dinheiro text-right pago ${parcelaCorrente.situacao}Pago bloqueado" styleId="valorPago${i}" property="notaFiscalRateio.contaPagar.parcelas[${i}].valorPago" name="notaFiscalRateioForm" />
			</logic:equal>

		</div>

		<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-2 text-right">
			<%-- <logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.tituloPagar.quitar)"> --%>

			<logic:equal value="Aberta" property="situacao" name="parcelaCorrente">
				<button type="button" id="quitar${i}" class="btn btn-primary btn-xs btn-block" style="font-size: 14px; margin-bottom: 5px;">Quitar</button>
			</logic:equal>
			<%-- </logic:equal> --%>

			<%-- <logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.tituloPagar.estornar)"> --%>
			<logic:equal value="Quitada" property="situacao" name="parcelaCorrente">
				<button type="button" id="estornar${i}" class="btn btn-danger btn-xs" style="font-size: 14px">Estornar</button>
			</logic:equal>
			<%-- </logic:equal> --%>
		</div>

	</div>
	</div>


	<script type="text/javascript">
		$(document).ready(function() {

			$('#quitar${i}').click(function() {
				var mensagem = "";
				mensagem = '<div class="row">';

				if ($('#formaPagamento${i}').val() == null || $('#formaPagamento${i}').val() == '') {
					mensagem = mensagem + '<div class="form-group col-lg-12" style="color:red">' + 'Forma de Pagamento: <strong>Não Informado!</strong>' + '</div>';
				} else {
					mensagem = mensagem + '<div class="form-group col-lg-12">' + 'Forma de Pagamento:<strong>' + $('#formaPagamento${i} :selected').text() + '</strong>' + '</div>';
				}

				mensagem = mensagem + '</div>';
				mensagem = mensagem + '<div class="row">';

				mensagem = mensagem + '<div class="form-group col-lg-6">' + 'Data Vencimento:<strong>' + $('#dataVencimento${i}').val() + '</strong>' + '</div>';

				if ($('#dataRecebimento${i}').val() == null || $('#dataRecebimento${i}').val() == '') {
					mensagem = mensagem + '<div class="form-group col-lg-6">' + 'Data Pagamento:<strong>' + $('#dataVencimento${i}').val() + '</strong> (preenchido automaticamente)' + '</div>';
				} else {
					mensagem = mensagem + '<div class="form-group col-lg-6">' + 'Data Pagamento:<strong>' + $('#dataRecebimento${i}').val() + '</strong>' + '</div>';
				}

				mensagem = mensagem + '</div>';
				mensagem = mensagem + '<div class="row">';

				mensagem = mensagem + '<div class="form-group col-lg-4">' + 'Valor:<strong>' + $('#valor${i}').val() + '</strong>' + '</div>';

				if ($('#valorAcrescimo${i}').val() == null || $('#valorAcrescimo${i}').val() == '') {
					mensagem = mensagem + '<div class="form-group col-lg-4">' + 'Valor Acréscimo:<strong>0,00</strong>' + '</div>';
				} else {
					mensagem = mensagem + '<div class="form-group col-lg-4">' + 'Valor Acréscimo:<strong>' + $('#valorAcrescimo${i}').val() + '</strong>' + '</div>';
				}

				mensagem = mensagem + '<div class="form-group col-lg-4">' + 'Valor Total:<strong>' + $('#valorFinal${i}').val() + '</strong>' + '</div>';

				if ($('#valorPago${i}').val() == null || $('#valorPago${i}').val() == '') {
					mensagem = mensagem + '<div class="form-group col-lg-12">' + 'Valor Pago:<strong>' + $('#valorFinal${i}').val() + '</strong> (preenchido automaticamente)' + '</div>';
				} else {
					mensagem = mensagem + '<div class="form-group col-lg-12">' + 'Valor Pago:<strong>' + $('#valorPago${i}').val() + '</strong>';

					var valorFinal = $("#valorFinal${i}").val();
					var valorPago = $("#valorPago${i}").val();

					if (valorFinal == null || valorFinal == '') {
						valorFinal = '0.0';
					}
					if (valorPago == null || valorPago == '') {
						valorPago = '0.0';
					}

					valorFinal = parseFloat(valorFinal.replace('.', '').replace(',', '.').replace('R$', ''), 10);
					valorPago = parseFloat(valorPago.replace('.', '').replace(',', '.').replace('R$', ''), 10);

					if (valorPago < valorFinal) {
						mensagem = mensagem + ' (Será gerada uma parcela PARCIAL no valor de : <strong>' + (formatarDinheiro((valorFinal - valorPago).toFixed(2))) + '</strong>)';
					}

					mensagem = mensagem + '</div>';
				}
				mensagem = mensagem + '</div>';

				BootstrapDialog.show({
					size : BootstrapDialog.SIZE_NORMAL,
					title : 'Dados de Quitação',
					message : mensagem,
					type : BootstrapDialog.TYPE_INFO, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
					closable : true, // <-- Default value is false
					draggable : true, // <-- Default value is false
					buttons : [ {
						label : 'Confirmar',
						action : function(dialogRef) {
							var theForm = $('form[name=notaFiscalRateioForm]');
							var params = theForm.serialize();
							var actionURL = theForm.attr('action') + '?method=quitarParcela&parcelaSelecionada.idTemp=${parcelaCorrente.idTemp}';
							$.ajax({
								type : 'POST',
								url : actionURL,
								data : params,
								success : function(data, textStatus, XMLHttpRequest) {

									$('#id_parcelas_ajax').html(data);

									$('#tabsNotaFiscalRateio a[href="#financeiro"]').tab('show');

									dialogRef.close();

								},
								error : function(XMLHttpRequest, textStatus, errorThrown) {
									alert(textStatus);
								}
							});
						}
					}, {
						label : 'Fechar',
						action : function(dialogRef) {
							dialogRef.close();
						}
					} ]
				});

			});

			$('#estornar${i}').click(function() {
				var mensagem = "";
				mensagem = '<div class="row">';
				mensagem = mensagem + '<div class="form-group col-lg-12" style="color:red">';
				mensagem = mensagem + 'Tem certeza que deseja <strong>ESTORNAR</strong> este titulo?';
				mensagem = mensagem + '</div>';
				mensagem = mensagem + '</div>';

				mensagem = mensagem + '<div class="row">';
				mensagem = mensagem + '<div class="form-group col-lg-12" style="color:red">';
				mensagem = mensagem + 'Isso fará com que os dados referentes ao pagamento sejam apagados tornando o titulo em questão ABERTO novamente!';
				mensagem = mensagem + '</div>';
				mensagem = mensagem + '</div>';

				mensagem = mensagem + '<div class="row">';
				mensagem = mensagem + '<div class="form-group col-lg-12" style="color:red">';
				mensagem = mensagem + '<strong>PS.</strong> Todos os pagamentos PARCIAIS referentes ao titulo em questão serão <strong>APAGADOS!</strong>';
				mensagem = mensagem + '</div>';
				mensagem = mensagem + '</div>';

				BootstrapDialog.show({
					size : BootstrapDialog.SIZE_NORMAL,
					title : 'Dados de Estorno',
					message : mensagem,
					type : BootstrapDialog.TYPE_WARNING, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
					closable : true, // <-- Default value is false
					draggable : true, // <-- Default value is false
					buttons : [ {
						label : 'Confirmar',
						action : function(dialogRef) {
							var theForm = $('form[name=notaFiscalRateioForm]');
							var actionURL = theForm.attr('action') + '?method=estornarParcela&parcelaSelecionada.idTemp=${parcelaCorrente.idTemp}';
							$.ajax({
								type : 'POST',
								url : actionURL,
								success : function(data, textStatus, XMLHttpRequest) {

									$('#id_parcelas_ajax').html(data);

									$('#tabsNotaFiscalRateio a[href="#financeiro"]').tab('show');

									dialogRef.close();
								},
								error : function(XMLHttpRequest, textStatus, errorThrown) {
									alert(textStatus);
								}
							});
						}
					}, {
						label : 'Fechar',
						action : function(dialogRef) {
							dialogRef.close();
						}
					} ]
				});
			});

			$('#valorPago${i}').keyup(function() {
				//calcularValorAcrescimo();
			});

			$('#valorAcrescimo${i}').keyup(function() {
				calcularValorTotal();
			});

			$('#valor${i}').keyup(function() {
				calcularValorTotal();
			});

			function calcularValorTotal() {
				var valor = $("#valor${i}").val();
				var acrescimo = $("#valorAcrescimo${i}").val();

				if (valor == null || valor == '') {
					valor = '0.0';
				}
				if (acrescimo == null || acrescimo == '') {
					acrescimo = '0.0';
				}

				valor = parseFloat(valor.replace('.', '').replace(',', '.').replace('R$', ''), 10);
				acrescimo = parseFloat(acrescimo.replace('.', '').replace(',', '.').replace('R$', ''), 10);

				$('#valorFinal${i}').val(formatarDinheiro((valor + acrescimo).toFixed(2)));
			}

			/* function calcularValorAcrescimo() {
				var valorPago = $("#valorPago${i}").val();
				var valor = $("#valor${i}").val();

				if (valorPago == null || valorPago == '' || valorPago == '0.0') {
					return;
				}
				if (valor == null || valor == '') {
					valor = '0.0';
				}

				valorPago = parseFloat(valorPago.replace('.', '').replace(',', '.').replace('R$', ''), 10);
				valor = parseFloat(valor.replace('.', '').replace(',', '.').replace('R$', ''), 10);

				if (valorPago < valor) {
					return;
				}
				$('#valorAcrescimo${i}').val(formatarDinheiro((valorPago - valor).toFixed(2)));

				$('#valorFinal${i}').val(formatarDinheiro(valorPago.toFixed(2)));
			} */

			calcularValorTotal();
			//calcularValorAcrescimo();

		});
	</script>

</logic:iterate>






<script type="text/javascript">
	$(document).ready(function() {
		recarregarObrigatorios();

		if ($(".situacao").length > 0) {
			$('#situacaoNotaFiscalRateio').text('Quitada');
			$('#alert-situacaoNotaFiscalRateio').removeClass('alert-success');
			$('#alert-situacaoNotaFiscalRateio').css('background-color', 'rgba(0, 18, 230, 0.41)');// Quitada

			$(".situacao").each(function(index, element) {
				if ($(element).val() != null && $(element).val() != '') {
					if ($(element).val() == 'Aberta') {
						$('#situacaoNotaFiscalRateio').text('Aberta');
						$('#alert-situacaoNotaFiscalRateio').css('background-color', '');// Aberta
						$('#alert-situacaoNotaFiscalRateio').addClass('alert-success');
						return false;
					}
				}
			});
		} else {
			$('#situacaoNotaFiscalRateio').text('Aberta');
			$('#alert-situacaoNotaFiscalRateio').css('background-color', '');// Aberta
			$('#alert-situacaoNotaFiscalRateio').addClass('alert-success');

		}

		if ($(".pago").length > 0) {
			$('#valorTotalMaisAcrescimoNotaFiscalRateio').text('0,00');

			var soma = '0.0';
			$(".pago").each(function(index, element) {
				if ($(element).val() != null && $(element).val() != '') {
					var valor = $(element).val();

					if (valor == null || valor == '') {
						valor = '0.0';
					}

					valor = parseFloat(valor.replace('.', '').replace(',', '.').replace('R$', ''), 10);
					soma = parseFloat(soma.replace('.', '').replace(',', '.').replace('R$', ''), 10);

					soma = (formatarDinheiro((valor + soma).toFixed(2)));
				}
			});

			$('#valorTotalMaisAcrescimoNotaFiscalRateio').text(soma);
		} else {
			$('#valorTotalMaisAcrescimoNotaFiscalRateio').text('0,00');
		}

		if ($(".formaPagamento").length > 0) {
			var cont = 0;
			$(".formaPagamento").each(function(index, element) {
				if ($(element).val() != null && $(element).val() != '') {

					//var formaPagamento = $(element).find(":selected").text();
					var formaPagamento = $(element).val();

					$('#formaPagamento').val(formaPagamento);
					cont++;
				}
			});
			if (cont > 1) {
				$('#formaPagamento').val('xxx');
			}
		}

		$('.valorParcela').focusout(function() {
			validadeValoresParcelas();
		});

		validadeValoresParcelas();
	});

	function validadeValoresParcelas() {
		var somaQuitada = '0.0';
		$(".QuitadaPago").each(function(index, element) {
			if ($(element).val() != null && $(element).val() != '') {
				var valorQuitada = $(element).val();

				if (valorQuitada == null || valorQuitada == '') {
					valorQuitada = '0.0';
				}

				valorQuitada = parseFloat(valorQuitada.replace('.', '').replace(',', '.').replace('R$', ''), 10);
				somaQuitada = parseFloat(somaQuitada.replace('.', '').replace(',', '.').replace('R$', ''), 10);

				somaQuitada = (formatarDinheiro((valorQuitada + somaQuitada).toFixed(2)));
			}
		});
		somaQuitada = parseFloat(somaQuitada.replace('.', '').replace(',', '.').replace('R$', ''), 10);

		var somaAberta = '0.0';
		$(".AbertaValor").each(function(index, element) {
			if ($(element).val() != null && $(element).val() != '') {
				var valorAberta = $(element).val();

				if (valorAberta == null || valorAberta == '') {
					valorAberta = '0.0';
				}

				valorAberta = parseFloat(valorAberta.replace('.', '').replace(',', '.').replace('R$', ''), 10);
				somaAberta = parseFloat(somaAberta.replace('.', '').replace(',', '.').replace('R$', ''), 10);

				somaAberta = (formatarDinheiro((valorAberta + somaAberta).toFixed(2)));
			}
		});
		somaAberta = parseFloat(somaAberta.replace('.', '').replace(',', '.').replace('R$', ''), 10);

		var somaAcrescimo = '0.0';
		$(".QuitadaAcrescimo").each(function(index, element) {
			if ($(element).val() != null && $(element).val() != '') {
				var valorAcrescimo = $(element).val();

				if (valorAcrescimo == null || valorAcrescimo == '') {
					valorAcrescimo = '0.0';
				}

				valorAcrescimo = parseFloat(valorAcrescimo.replace('.', '').replace(',', '.').replace('R$', ''), 10);
				somaAcrescimo = parseFloat(somaAcrescimo.replace('.', '').replace(',', '.').replace('R$', ''), 10);

				somaAcrescimo = (formatarDinheiro((valorAcrescimo + somaAcrescimo).toFixed(2)));
			}
		});
		somaAcrescimo = parseFloat(somaAcrescimo.replace('.', '').replace(',', '.').replace('R$', ''), 10);

		var valorTotal = '${notaFiscalRateioForm.notaFiscalRateio.valorTotal}';
		valorTotal = parseFloat(valorTotal.replace('.', '').replace(',', '.').replace('R$', ''), 10);

		var diferenca = (somaAberta + somaQuitada) - (valorTotal + somaAcrescimo);

		if (diferenca != 0) {
			$('.valorParcela').css('border-color', '#ff0000');
			$('.valorParcela').css('background-color', '#ffcfcf');
			$('#linhaInformativo').css("display", "block");
			if (diferenca > 0) {
				$('#informativo').text('Diferença gerada no valor das parcelas: ' + diferenca.toFixed(2) + ', SUBTRAIA este valor de alguma parcela!');
			} else {
				$('#informativo').text('Diferença gerada no valor das parcelas: ' + diferenca.toFixed(2) + ', SOME este valor de alguma parcela!');
			}
		} else {
			$('.valorParcela').css('border-color', '#999');
			$('.valorParcela').css('background-color', '#fff');
			$('#linhaInformativo').css("display", "none");
		}

	}
</script>