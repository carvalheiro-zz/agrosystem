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

<logic:iterate indexId="i" id="parcelaCorrente" name="receitaForm" property="receita.contaReceber.parcelas" type="br.com.srcsoftware.modular.financeiro.titulo.tituloreceber.TituloReceberDTO">

	<html:hidden styleClass="form-control input-sm dinheiro bloqueado text-right juros" styleId="juros${i}" property="receita.contaReceber.parcelas[${i}].juros" name="receitaForm" />


	<logic:empty name="parcelaCorrente" property="canceladoToString">
		<logic:equal value="Aberta" property="situacaoToString" name="parcelaCorrente">
			<div class="alert alert-success" style="margin-bottom: 5px;">
		</logic:equal>
		<logic:equal value="Quitada" property="situacaoToString" name="parcelaCorrente">
			<div class="alert alert-info" style="background-color: #a6e1ff !important; margin-bottom: 5px;">
		</logic:equal>
		<logic:equal value="A_Vencer" property="situacaoToString" name="parcelaCorrente">
			<div class="alert alert-warning" style="background-color: #fff0a7 !important; margin-bottom: 5px;">
		</logic:equal>
		<logic:equal value="Vencida" property="situacaoToString" name="parcelaCorrente">
			<div class="alert alert-danger" style="background-color: #ffa7a7 !important; margin-bottom: 5px;">
		</logic:equal>

		<div class="row">
			<div class="form-group col-xs-12 col-sm-12 col-md-1 col-lg-1">
				<label style="font-size: 35px;">${parcelaCorrente.numero}</label>
			</div>

			<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
				<label>Tipo</label>
				<html:text styleClass="form-control input-sm text-center bloqueado" styleId="tipo${i}" property="tipo" name="parcelaCorrente" />
			</div>
			<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
				<label>Situação</label>
				<html:text styleClass="form-control input-sm text-center bloqueado situacao" styleId="situacao${i}" property="situacao" name="parcelaCorrente" />
			</div>

			<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
				<label>Emissão</label>
				<html:text styleClass="form-control input-sm data bloqueado text-center" styleId="dataLancamento${i}" property="dataLancamento" name="parcelaCorrente" />
			</div>
			<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
				<label>Vencimento</label>
				<logic:equal value="Aberta" property="situacao" name="parcelaCorrente">
					<html:text styleClass="form-control input-sm data text-center" styleId="dataVencimento${i}" property="receita.contaReceber.parcelas[${i}].dataVencimento" name="receitaForm" />
				</logic:equal>
				<logic:equal value="Quitada" property="situacao" name="parcelaCorrente">
					<html:text styleClass="form-control input-sm data text-center bloqueado" styleId="dataVencimento${i}" property="receita.contaReceber.parcelas[${i}].dataVencimento" name="receitaForm" />
				</logic:equal>
			</div>
			<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
				<label>Pagamento</label>
				<logic:equal value="Aberta" property="situacao" name="parcelaCorrente">
					<html:text styleClass="form-control input-sm data text-center" styleId="dataRecebimento${i}" property="receita.contaReceber.parcelas[${i}].dataRecebimento" name="receitaForm" />
				</logic:equal>
				<logic:equal value="Quitada" property="situacao" name="parcelaCorrente">
					<html:text styleClass="form-control input-sm data text-center bloqueado" styleId="dataRecebimento${i}" property="receita.contaReceber.parcelas[${i}].dataRecebimento" name="receitaForm" />
				</logic:equal>

			</div>
			<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
				<label>Forma pagto.</label>
				<logic:equal value="Aberta" property="situacao" name="parcelaCorrente">
					<html:select styleClass="form-control input-sm formaPagamento" styleId="formaPagamento${i}" property="receita.contaReceber.parcelas[${i}].formaPagamento.id" name="receitaForm">
						<html:option value="">Selecione...</html:option>
						<html:optionsCollection name="receitaForm" property="comboFormaPagamentoAVista(${usuarioSessaoPOJO.isADM})" label="label" value="value" />
					</html:select>
				</logic:equal>
				<logic:equal value="Quitada" property="situacao" name="parcelaCorrente">
					<%-- <html:select styleClass="form-control input-sm formaPagamento bloqueado" styleId="formaPagamento${i}" property="receita.contaReceber.parcelas[${i}].formaPagamento.id" name="receitaForm">
					<html:option value="">Selecione...</html:option>
					<html:optionsCollection name="receitaForm" property="comboFormaPagamento(${usuarioSessaoPOJO.isADM})" label="label" value="value" />
				</html:select> --%>
					<html:text styleClass="form-control input-sm formaPagamento bloqueado" styleId="formaPagamento${i}" property="receita.contaReceber.parcelas[${i}].formaPagamento.nomeCompleto" name="receitaForm" />
				</logic:equal>

			</div>
			<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
				<label>Valor</label>
				<logic:equal value="Aberta" property="situacao" name="parcelaCorrente">
					<html:text styleClass="form-control input-sm dinheiro text-right valor valorAberta" styleId="valor${i}" property="receita.contaReceber.parcelas[${i}].valor" name="receitaForm" />
				</logic:equal>
				<logic:equal value="Quitada" property="situacao" name="parcelaCorrente">
					<html:text styleClass="form-control input-sm dinheiro text-right bloqueado" styleId="valor${i}" property="receita.contaReceber.parcelas[${i}].valor" name="receitaForm" />
				</logic:equal>

			</div>
			<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-1">
				<label>% Multa</label>
				<logic:equal value="Aberta" property="situacao" name="parcelaCorrente">
					<html:text styleClass="form-control input-sm decimal text-right " styleId="percentualMulta${i}" property="receita.contaReceber.parcelas[${i}].percentualMulta" name="receitaForm" />
				</logic:equal>
				<logic:equal value="Quitada" property="situacao" name="parcelaCorrente">
					<html:text styleClass="form-control input-sm decimal text-right bloqueado" styleId="percentualMulta${i}" property="receita.contaReceber.parcelas[${i}].percentualMulta" name="receitaForm" />
				</logic:equal>
			</div>

			<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-1">
				<label>% Juros</label>
				<logic:equal value="Aberta" property="situacao" name="parcelaCorrente">
					<html:text styleClass="form-control input-sm decimal text-right" styleId="percentualJuros${i}" property="receita.contaReceber.parcelas[${i}].percentualJuros" name="receitaForm" />
				</logic:equal>
				<logic:equal value="Quitada" property="situacao" name="parcelaCorrente">
					<html:text styleClass="form-control input-sm decimal text-right bloqueado" styleId="percentualJuros${i}" property="receita.contaReceber.parcelas[${i}].percentualJuros" name="receitaForm" />
				</logic:equal>
			</div>
			<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
				<label>Total</label>
				<html:text styleClass="form-control input-sm dinheiro bloqueado text-right valorFinal${parcelaCorrente.situacao}" styleId="valorFinal${i}" property="receita.contaReceber.parcelas[${i}].valorFinal" name="receitaForm" />
			</div>
			<%-- <div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
			<label>Juros</label>
			<html:hidden styleClass="form-control input-sm dinheiro bloqueado text-right juros" styleId="juros${i}" property="receita.contaReceber.parcelas[${i}].juros" name="receitaForm" />
		</div> --%>
			<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
				<label>Pago</label>
				<logic:equal value="Aberta" property="situacao" name="parcelaCorrente">
					<html:text styleClass="form-control input-sm dinheiro text-right pago" styleId="valorPago${i}" property="receita.contaReceber.parcelas[${i}].valorPago" name="receitaForm" />
				</logic:equal>
				<logic:equal value="Quitada" property="situacao" name="parcelaCorrente">
					<html:text styleClass="form-control input-sm dinheiro text-right pago bloqueado pagoQuitada" styleId="valorPago${i}" property="receita.contaReceber.parcelas[${i}].valorPago" name="receitaForm" />
				</logic:equal>

			</div>

			<div class="form-group col-xs-12 col-sm-12 col-md-10 col-lg-2">
				<logic:empty name="receitaForm" property="receita.contaReceber.canceladoToString">
					<logic:equal value="Aberta" property="situacao" name="parcelaCorrente">
						<button type="button" id="quitar${i}" class="btn btn-primary btn-xs btn-block" style="font-size: 14px; margin-top: 28px;">Quitar</button>
					</logic:equal>
					<logic:equal value="Quitada" property="situacao" name="parcelaCorrente">
						<button type="button" id="estornar${i}" class="btn btn-danger btn-xs btn-block" style="font-size: 14px; margin-top: 28px;">Estornar</button>
					</logic:equal>
				</logic:empty>
			</div>
		</div>

		<logic:equal value="Cheque" property="formaPagamento.nome" name="parcelaCorrente">
			<div class="row" style="border-top: 1px solid #b9b9b9; padding-top: 5px;">
				<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-3">
					<label>Banco</label>
					<html:text styleClass="form-control input-sm" styleId="banco${i}" property="receita.contaReceber.parcelas[${i}].bancoCheque" name="receitaForm" />
				</div>
				<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-2">
					<label>Conta corrente</label>
					<html:text styleClass="form-control input-sm text-center" styleId="ccCheque${i}" property="receita.contaReceber.parcelas[${i}].ccCheque" name="receitaForm" />
				</div>
				<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-2">
					<label>Nº cheque</label>
					<html:text styleClass="form-control input-sm text-center" styleId="numeroCheque${i}" property="receita.contaReceber.parcelas[${i}].numeroCheque" name="receitaForm" />
				</div>
			</div>
		</logic:equal>
		</div>
	</logic:empty>
	<logic:notEmpty name="parcelaCorrente" property="canceladoToString">

		<div class="alert alert-danger" style="background-color: #9a9a9a !important; margin-bottom: 5px;">
			<div class="row" style="color: #842323">
				<div class="form-group col-xs-12 col-sm-6 col-md-1 col-lg-1">
					<label style="font-size: 35px;">${parcelaCorrente.numero}</label>
				</div>
				<div class="form-group col-xs-12 col-sm-6 col-md-11 col-lg-11">
					<h1>${parcelaCorrente.canceladoToString}</h1>
				</div>
			</div>
		</div>

	</logic:notEmpty>






	<script type="text/javascript">
		$(document).ready(function() {
			/* Setando os placeholder dos campos*/
			$("#banco${i}").attr("placeholder", "Ex: Banco do Brasil");
			$("#ccCheque${i}").attr("placeholder", "Ex: 1562789");
			$("#numeroCheque${i}").attr("placeholder", "Ex: 17445-6");

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
					mensagem = mensagem + '<div class="form-group col-lg-6">' + 'Data Pagamento:<strong>Hoje</strong> (preenchido automaticamente)' + '</div>';
				} else {
					mensagem = mensagem + '<div class="form-group col-lg-6">' + 'Data Pagamento:<strong>' + $('#dataRecebimento${i}').val() + '</strong>' + '</div>';
				}

				mensagem = mensagem + '</div>';
				mensagem = mensagem + '<div class="row">';

				mensagem = mensagem + '<div class="form-group col-lg-4">' + 'Valor:<strong>' + $('#valor${i}').val() + '</strong>' + '</div>';

				if ($('#percentualJuros${i}').val() == null || $('#percentualJuros${i}').val() == '') {
					mensagem = mensagem + '<div class="form-group col-lg-4">' + '% Juros:<strong>0,00</strong>' + '</div>';
				} else {
					mensagem = mensagem + '<div class="form-group col-lg-4">' + '% Juros:<strong>' + $('#percentualJuros${i}').val() + '</strong>' + '</div>';
				}

				if ($('#percentualMulta${i}').val() == null || $('#percentualMulta${i}').val() == '') {
					mensagem = mensagem + '<div class="form-group col-lg-4">' + '% Multa:<strong>0,00</strong>' + '</div>';
				} else {
					mensagem = mensagem + '<div class="form-group col-lg-4">' + '% Multa:<strong>' + $('#percentualMulta${i}').val() + '</strong>' + '</div>';
				}

				mensagem = mensagem + '<div class="form-group col-lg-4">' + 'Valor Total:<strong>' + $('#valorFinal${i}').val() + '</strong>' + '</div>';

				if ($('#valorPago${i}').val() == null || $('#valorPago${i}').val() == '') {
					mensagem = mensagem + '<div class="form-group col-lg-8">' + 'Valor Pago:<strong>' + $('#valorFinal${i}').val() + '</strong> (preenchido automaticamente)' + '</div>';
				} else {
					mensagem = mensagem + '<div class="form-group col-lg-8">' + 'Valor Pago:<strong>' + $('#valorPago${i}').val() + '</strong>';

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
							var theForm = $('form[name=receitaForm]');
							var params = theForm.serialize();
							var actionURL = theForm.attr('action') + '?method=quitarParcela&parcelaSelecionada.id=${parcelaCorrente.id}';
							$.ajax({
								type : 'POST',
								url : actionURL,
								data : params,
								success : function(data, textStatus, XMLHttpRequest) {

									$('#id_parcelas_ajax').html(data);

									$('#tabsReceita a[href="#financeiro"]').tab('show');

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
							var theForm = $('form[name=receitaForm]');
							var actionURL = theForm.attr('action') + '?method=estornarParcela&parcelaSelecionada.id=${parcelaCorrente.id}';
							$.ajax({
								type : 'POST',
								url : actionURL,
								success : function(data, textStatus, XMLHttpRequest) {

									$('#id_parcelas_ajax').html(data);

									$('#tabsReceita a[href="#financeiro"]').tab('show');

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

			});

			$('#percentualJuros${i}').keyup(function() {
				calcularValorTotal();
			});

			$('#percentualMulta${i}').keyup(function() {
				calcularValorTotal();
			});

			$('#valor${i}').keyup(function() {
				calcularValorTotal();
			});

			$('#dataVencimento${i}').focusout(function() {
				calcularValorTotal();
			});

			$('#dataRecebimento${i}').focusout(function() {
				calcularValorTotal();
			});

			function calcularValorTotal() {
				var theForm = $('form[name=receitaForm]');
				var params = theForm.serialize();
				var actionURL = theForm.attr('action') + '?method=calcularValorTotal' + '&parcelaSelecionada.id=${parcelaCorrente.id}';
				$.ajax({
					type : 'POST',
					url : actionURL,
					data : params,
					success : function(data, textStatus, XMLHttpRequest) {
						$('#valorFinal${i}').val(data.valorTotal);
						$('#juros${i}').val(data.juros);
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert(textStatus);
						$('#valorFinal${i}').val(null);
						$('#juros${i}').val(null);
					}
				});
			}

			//calcularValorTotal();

		});
	</script>

</logic:iterate>






<script type="text/javascript">
	$(document).ready(function() {
		recarregarObrigatorios();

		if ($(".situacao").length > 0) {
			$('#situacaoReceita').text('Quitada');
			$('#alert-situacaoReceita').removeClass('alert-success');
			$('#alert-situacaoReceita').css('background-color', 'rgba(0, 18, 230, 0.41)');// Quitada

			$(".situacao").each(function(index, element) {
				if ($(element).val() != null && $(element).val() != '') {
					if ($(element).val() == 'Aberta') {
						$('#situacaoReceita').text('Aberta');
						$('#alert-situacaoReceita').css('background-color', '');// Aberta
						$('#alert-situacaoReceita').addClass('alert-success');
						return false;
					}
				}
			});
		} else {
			$('#situacaoReceita').text('Aberta');
			$('#alert-situacaoReceita').css('background-color', '');// Aberta
			$('#alert-situacaoReceita').addClass('alert-success');

		}

		if ($(".pago").length > 0) {
			$('#valorTotalMaisJurosMultaReceita').text('0,00');

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

			$('#valorTotalMaisJurosMultaReceita').text(soma);
		} else {
			$('#valorTotalMaisJurosMultaReceita').text('0,00');
		}

		/* if ($(".formaPagamento").length > 0) {
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
		} */

		$('.valorAberta').focusout(function() {
			validadeValoresParcelas();
		});

		validadeValoresParcelas();
	});

	function validadeValoresParcelas() {
		//FORMULA: (SomaQ.P + SomaA.F-SomaJ) - Total		

		// Somando o Total do Campos Valor das Parcelas QUITADAS Pagas (SomaQ.P)
		var somaValorPagoQuitada = '0.0';
		somaValorPagoQuitada = parseFloat(somaValorPagoQuitada.replace('.', '').replace(',', '.').replace('R$', ''), 10);
		$(".pagoQuitada").each(function(index, element) {
			if ($(element).val() != null && $(element).val() != '') {
				var pagoQuitada = $(element).val();

				if (pagoQuitada == null || pagoQuitada == '') {
					pagoQuitada = '0.0';
				}

				pagoQuitada = parseFloat(pagoQuitada.replace('.', '').replace(',', '.').replace('R$', ''), 10);

				somaValorPagoQuitada = pagoQuitada + somaValorPagoQuitada
			}
		});

		// Somando o Total do Campos ValorFinal das Parcelas ABERTAS (SomaA.V)		
		var somaValorFinalAberta = '0.0';
		somaValorFinalAberta = parseFloat(somaValorFinalAberta.replace('.', '').replace(',', '.').replace('R$', ''), 10);
		$(".valorFinalAberta").each(function(index, element) {
			if ($(element).val() != null && $(element).val() != '') {
				var valorFinalAberta = $(element).val();

				if (valorFinalAberta == null || valorFinalAberta == '') {
					valorFinalAberta = '0.0';
				}

				valorFinalAberta = parseFloat(valorFinalAberta.replace('.', '').replace(',', '.').replace('R$', ''), 10);

				somaValorFinalAberta = valorFinalAberta + somaValorFinalAberta
			}
		});

		// ########## CALCULO DE JUROS ##########  //
		// Somando o Total do Campos Hidden Juros das Parcelas 
		var somaJuros = '0.0';
		somaJuros = parseFloat(somaJuros.replace('.', '').replace(',', '.').replace('R$', ''), 10);
		$(".juros").each(function(index, element) {
			if ($(element).val() != null && $(element).val() != '') {
				var juros = $(element).val();

				if (juros == null || juros == '') {
					juros = '0.0';
				}

				juros = parseFloat(juros.replace('.', '').replace(',', '.').replace('R$', ''), 10);

				somaJuros = juros + somaJuros
			}
		});

		// Valor DA NF
		var valorNF = '${receitaForm.receita.contaReceber.valor}';
		valorNF = parseFloat((valorNF + ".0").replace('.', '').replace(',', '.').replace('R$', ''), 2);

		var diferenca = somaValorPagoQuitada + somaValorFinalAberta;
		diferenca = diferenca.toFixed(2) - somaJuros;
		diferenca = diferenca.toFixed(2) - valorNF;

		if (diferenca != 0) {
			$('.valorAberta').css('border-color', '#ff0000');
			$('.valorAberta').css('background-color', '#ffcfcf');
			$('#linhaInformativo').css("display", "block");
			if (diferenca > 0) {
				$('#informativo').text('Diferença gerada no valor das parcelas: ' + diferenca.toFixed(2) + ', SUBTRAIA este valor de alguma parcela!');
			} else {
				$('#informativo').text('Diferença gerada no valor das parcelas: ' + diferenca.toFixed(2) + ', SOME este valor de alguma parcela!');
			}
		} else {
			$('.valorAberta').css('border-color', '#999');
			$('.valorAberta').css('background-color', '#fff');
			$('#linhaInformativo').css("display", "none");
		}

	}
</script>