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

<logic:iterate indexId="i" id="parcelaCorrente" name="despesaForm" property="despesa.contaPagar.parcelas" type="br.com.srcsoftware.modular.financeiro.titulo.titulopagar.TituloPagarDTO">

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
					<html:text styleClass="form-control input-sm data text-center" styleId="dataVencimento${i}" property="despesa.contaPagar.parcelas[${i}].dataVencimento" name="despesaForm" />
				</logic:equal>
				<logic:equal value="Quitada" property="situacao" name="parcelaCorrente">
					<html:text styleClass="form-control input-sm data text-center bloqueado" styleId="dataVencimento${i}" property="despesa.contaPagar.parcelas[${i}].dataVencimento" name="despesaForm" />
				</logic:equal>
			</div>
			<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-2">
				<label>Pagamento</label>
				<logic:equal value="Aberta" property="situacao" name="parcelaCorrente">
					<html:text styleClass="form-control input-sm data text-center" styleId="dataRecebimento${i}" property="despesa.contaPagar.parcelas[${i}].dataRecebimento" name="despesaForm" />
				</logic:equal>
				<logic:equal value="Quitada" property="situacao" name="parcelaCorrente">
					<html:text styleClass="form-control input-sm data text-center bloqueado" styleId="dataRecebimento${i}" property="despesa.contaPagar.parcelas[${i}].dataRecebimento" name="despesaForm" />
				</logic:equal>

			</div>
			<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-2">
				<label>Forma pagto.</label>
				<logic:equal value="Aberta" property="situacao" name="parcelaCorrente">
					<html:select styleClass="form-control input-sm formaPagamento" styleId="formaPagamento${i}" property="despesa.contaPagar.parcelas[${i}].formaPagamento.id" name="despesaForm">
						<html:option value="">Selecione...</html:option>
						<html:optionsCollection name="despesaForm" property="comboFormaPagamentoAVista(${usuarioSessaoPOJO.isADM})" label="label" value="value" />
					</html:select>
				</logic:equal>
				<logic:equal value="Quitada" property="situacao" name="parcelaCorrente">
					<%-- <html:select styleClass="form-control input-sm formaPagamento bloqueado" styleId="formaPagamento${i}" property="despesa.contaPagar.parcelas[${i}].formaPagamento.id" name="despesaForm">
					<html:option value="">Selecione...</html:option>
					<html:optionsCollection name="despesaForm" property="comboFormaPagamentoAVista(${usuarioSessaoPOJO.isADM})" label="label" value="value" />
				</html:select> --%>
					<html:text styleClass="form-control input-sm formaPagamento bloqueado" styleId="formaPagamento${i}" property="despesa.contaPagar.parcelas[${i}].formaPagamento.nomeCompleto" name="despesaForm" />
				</logic:equal>

			</div>
			<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-2">
				<label>Valor</label>
				<logic:equal value="Aberta" property="situacao" name="parcelaCorrente">
					<html:text styleClass="form-control input-sm dinheiro text-right valorAberta" styleId="valor${i}" property="despesa.contaPagar.parcelas[${i}].valor" name="despesaForm" />
				</logic:equal>
				<logic:equal value="Quitada" property="situacao" name="parcelaCorrente">
					<html:text styleClass="form-control input-sm dinheiro text-right bloqueado" styleId="valor${i}" property="despesa.contaPagar.parcelas[${i}].valor" name="despesaForm" />
				</logic:equal>

			</div>
			<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-2">
				<label>Acréscimo</label>
				<logic:equal value="Aberta" property="situacao" name="parcelaCorrente">
					<html:text styleClass="form-control input-sm dinheiro text-right acrescimo" styleId="valorAcrescimo${i}" property="despesa.contaPagar.parcelas[${i}].acrescimo" name="despesaForm" />
				</logic:equal>
				<logic:equal value="Quitada" property="situacao" name="parcelaCorrente">
					<html:text styleClass="form-control input-sm dinheiro text-right acrescimo bloqueado" styleId="valorAcrescimo${i}" property="despesa.contaPagar.parcelas[${i}].acrescimo" name="despesaForm" />
				</logic:equal>

			</div>
			<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-2">
				<label>Total</label>
				<html:text styleClass="form-control input-sm dinheiro bloqueado text-right valorFinal${parcelaCorrente.situacao}" styleId="valorFinal${i}" property="despesa.contaPagar.parcelas[${i}].valorFinal" name="despesaForm" />
			</div>
			<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-2">
				<label>Pago</label>
				<logic:equal value="Aberta" property="situacao" name="parcelaCorrente">
					<html:text styleClass="form-control input-sm dinheiro text-right pago" styleId="valorPago${i}" property="despesa.contaPagar.parcelas[${i}].valorPago" name="despesaForm" />
				</logic:equal>
				<logic:equal value="Quitada" property="situacao" name="parcelaCorrente">
					<html:text styleClass="form-control input-sm dinheiro text-right pago bloqueado pagoQuitada" styleId="valorPago${i}" property="despesa.contaPagar.parcelas[${i}].valorPago" name="despesaForm" />
				</logic:equal>

			</div>

			<logic:notEqual value="Fixa" property="tipo" name="parcelaCorrente">
				<div class="form-group col-xs-12 col-sm-12 col-md-10 col-lg-2">
					<logic:empty name="despesaForm" property="despesa.contaPagar.canceladoToString">
						<logic:equal value="Aberta" property="situacao" name="parcelaCorrente">
							<button type="button" id="quitar${i}" class="btn btn-primary btn-xs btn-block" style="font-size: 14px; margin-top: 28px;">Quitar</button>
						</logic:equal>

						<logic:equal value="Quitada" property="situacao" name="parcelaCorrente">
							<button type="button" id="estornar${i}" class="btn btn-danger btn-xs btn-block" style="font-size: 14px; margin-top: 28px;">Estornar</button>
						</logic:equal>
					</logic:empty>
				</div>
			</logic:notEqual>
		</div>

		<logic:equal value="Cheque" property="formaPagamento.nome" name="parcelaCorrente">
			<div class="row" style="border-top: 1px solid #b9b9b9; padding-top: 5px;">
				<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-3">
					<label>Banco</label>
					<html:text styleClass="form-control input-sm" styleId="banco${i}" property="despesa.contaPagar.parcelas[${i}].bancoCheque" name="despesaForm" />
				</div>
				<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-2">
					<label>Conta corrente</label>
					<html:text styleClass="form-control input-sm text-center" styleId="ccCheque${i}" property="despesa.contaPagar.parcelas[${i}].ccCheque" name="despesaForm" />
				</div>
				<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-2">
					<label>Nº cheque</label>
					<html:text styleClass="form-control input-sm text-center" styleId="numeroCheque${i}" property="despesa.contaPagar.parcelas[${i}].numeroCheque" name="despesaForm" />
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
	<!-- close alert -->


	<script type="text/javascript">
		$(document).ready(function() {
			/* Setando os placeholder dos campos*/
			$("#banco${i}").attr("placeholder", "Ex: Banco do Brasil");
			$("#ccCheque${i}").attr("placeholder", "Ex: 1562789");
			$("#numeroCheque${i}").attr("placeholder", "Ex: 17445-6");

			$('#valorTotalDespesa').text('${despesaForm.despesa.contaPagar.valor}');
			$('#valor').val('${despesaForm.despesa.contaPagar.valor}');

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

				if ($('#formaPagamento${i}').val() == null || $('#formaPagamento${i}').val() == '') {
					BootstrapDialog.alert({
						title : 'Atenção',
						message : 'Por favor informe a Forma de Pagamento!',
						type : BootstrapDialog.TYPE_WARNING, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
						closable : true, // <-- Default value is false
						draggable : true, // <-- Default value is false
						buttonLabel : 'OK', // <-- Default value is 'OK',
						callback : function(result) {
							// result will be true if button was click, while it will be false if users close the dialog directly.
							//alert('Result is: ' + result);
						}
					});
				} else {

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
								var theForm = $('form[name=despesaForm]');
								var params = theForm.serialize();
								var actionURL = theForm.attr('action') + '?method=quitarParcela&parcelaSelecionada.id=${parcelaCorrente.id}';
								$.ajax({
									type : 'POST',
									url : actionURL,
									data : params,
									success : function(data, textStatus, XMLHttpRequest) {

										if ('${parcelaCorrente.tipo}' == 'Fixa') {
											BootstrapDialog.show({
												size : BootstrapDialog.SIZE_LARGE,
												title : 'Atenção...',
												message : "Deseja gerar esta Despesa para o MÊS seguinte?",
												closable : true,
												type : BootstrapDialog.TYPE_DANGER,
												buttons : [ {
													label : 'Sim, gerar',
													action : function(dialogRef) {

														var actionURL = theForm.attr('action') + '?method=gerarProxima&parcelaSelecionada.id=${parcelaCorrente.id}';
														$.ajax({
															type : 'POST',
															url : actionURL,
															success : function(data, textStatus, XMLHttpRequest) {

																$('#id_parcelas_ajax').html(data);
																$('#tabsDespesa a[href="#financeiro"]').tab('show');

																dialogRef.close();

															},
															error : function(XMLHttpRequest, textStatus, errorThrown) {
																alert(textStatus);
															}
														});

														dialogRef.close();
													}
												}, {
													label : 'Não, Obrigado',
													action : function(dialogRef) {
														$('#id_parcelas_ajax').html(data);
														$('#tabsDespesa a[href="#financeiro"]').tab('show');
														dialogRef.close();
													}
												} ]
											});
										} else {
											$('#id_parcelas_ajax').html(data);
											$('#tabsDespesa a[href="#financeiro"]').tab('show');
										}
										dialogRef.close();

									},
									error : function(XMLHttpRequest, textStatus, errorThrown) {
										alert(textStatus);
									},
									complete : function(XMLHttpRequest, textStatus) {

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
				}

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
							var theForm = $('form[name=despesaForm]');
							var actionURL = theForm.attr('action') + '?method=estornarParcela&parcelaSelecionada.id=${parcelaCorrente.id}';
							$.ajax({
								type : 'POST',
								url : actionURL,
								success : function(data, textStatus, XMLHttpRequest) {

									$('#id_parcelas_ajax').html(data);

									$('#tabsDespesa a[href="#financeiro"]').tab('show');

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

			//calcularValorTotal();

		});
	</script>

</logic:iterate>






<script type="text/javascript">
	$(document).ready(function() {
		recarregarObrigatorios();

		if ($(".situacao").length > 0) {
			$('#situacaoDespesa').text('Quitada');
			$('#alert-situacaoDespesa').removeClass('alert-success');
			$('#alert-situacaoDespesa').css('background-color', 'rgba(0, 18, 230, 0.41)');// Quitada

			$(".situacao").each(function(index, element) {
				if ($(element).val() != null && $(element).val() != '') {
					if ($(element).val() == 'Aberta') {
						$('#situacaoDespesa').text('Aberta');
						$('#alert-situacaoDespesa').css('background-color', '');// Aberta
						$('#alert-situacaoDespesa').addClass('alert-success');
						return false;
					}
				}
			});
		} else {
			$('#situacaoDespesa').text('Aberta');
			$('#alert-situacaoDespesa').css('background-color', '');// Aberta
			$('#alert-situacaoDespesa').addClass('alert-success');

		}

		if ($(".pago").length > 0) {
			$('#valorTotalMaisAcrescimoDespesa').text('0,00');

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

			$('#valorTotalMaisAcrescimoDespesa').text(soma);
		} else {
			$('#valorTotalMaisAcrescimoDespesa').text('0,00');
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
		// Somando o Total do Campos Hidden Acrescimo das Parcelas 
		var somaAcrescimo = '0.0';
		somaAcrescimo = parseFloat(somaAcrescimo.replace('.', '').replace(',', '.').replace('R$', ''), 10);
		$(".acrescimo").each(function(index, element) {
			if ($(element).val() != null && $(element).val() != '') {
				var acrescimo = $(element).val();

				if (acrescimo == null || acrescimo == '') {
					acrescimo = '0.0';
				}

				acrescimo = parseFloat(acrescimo.replace('.', '').replace(',', '.').replace('R$', ''), 10);

				somaAcrescimo = acrescimo + somaAcrescimo
			}
		});

		// Valor da Despesa
		var valorDespesa = '${despesaForm.despesa.contaPagar.valor}';
		valorDespesa = parseFloat((valorDespesa + ".0").replace('.', '').replace(',', '.').replace('R$', ''), 2);

		var diferenca = somaValorPagoQuitada + somaValorFinalAberta;
		diferenca = diferenca.toFixed(2) - somaAcrescimo;
		diferenca = diferenca.toFixed(2) - valorDespesa;

		//alert('valorDespesa: ' +valorDespesa+ ' somaAcrescimo: ' + somaAcrescimo + ' somaValorPagoQuitada: ' + somaValorPagoQuitada + ' somaValorFinalAberta: ' + somaValorFinalAberta);

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