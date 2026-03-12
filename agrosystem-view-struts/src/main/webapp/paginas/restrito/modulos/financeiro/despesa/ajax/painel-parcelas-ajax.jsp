<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<!-- INICIO TABELA -->
<logic:empty name="despesaForm" property="despesa.contaPagar.parcelas">
	<div class="row" style="margin-top: 15px;">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="alert alert-warning">Nenhum registro encontrado!</div>
		</div>
	</div>
</logic:empty>
<logic:notEmpty name="despesaForm" property="despesa.contaPagar.parcelas">
	<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.tituloPagar.filtrar)">
		<div class="panel-body">
			<div class="table-responsive">
				<table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
					<thead>
						<!-- CABEÇALHO DA TABELA -->
						<tr class="cor-sistema" style="color: white;">
							<th class="text-center">Tipo</th>
							<th class="text-center">Parcela</th>
							<th class="text-center">Situação</th>
							<th class="text-center">Vencimento</th>
							<th>Valor</th>
							<th>Acréscimo</th>
							<th>Total</th>
							<th>Pago</th>
							<th class="text-center">Pagamento</th>
							<th style="min-width: 140px;">Forma Pagto</th>
							<th class="text-center" style="width: 120px;">Opções</th>
						</tr>
					</thead>
					<tbody>
						<!-- TABELA -->
						<logic:iterate indexId="i" id="tituloPagarCorrente" name="despesaForm" property="despesa.contaPagar.parcelas" type="br.com.srcsoftware.modular.financeiro.titulo.titulopagar.TituloPagarDTO">

							<logic:equal value="Aberta" property="situacaoToString" name="tituloPagarCorrente">
								<tr title="${tituloPagarCorrente.descricaoOrigem}">
							</logic:equal>
							<logic:equal value="Quitada" property="situacaoToString" name="tituloPagarCorrente">
								<tr style="background-color: #a6e1ff !important;" title="${tituloPagarCorrente.descricaoOrigem}">
							</logic:equal>
							<logic:equal value="A_Vencer" property="situacaoToString" name="tituloPagarCorrente">
								<tr style="background-color: #fff0a7 !important;" title="${tituloPagarCorrente.descricaoOrigem}">
							</logic:equal>
							<logic:equal value="Vencida" property="situacaoToString" name="tituloPagarCorrente">
								<tr style="background-color: #ffa7a7 !important;" title="${tituloPagarCorrente.descricaoOrigem}">
							</logic:equal>


							<!-- success / info / warning / danger -->
							<td class="text-center">${tituloPagarCorrente.tipo}</td>
							<td class="text-center">${tituloPagarCorrente.numero}</td>
							<td class="text-center">${tituloPagarCorrente.situacao}</td>
							<td class="text-center">${tituloPagarCorrente.dataVencimentoToString}</td>

							<td class="text-right" id="valor${i}">

								<logic:equal value="Aberta" property="situacao" name="tituloPagarCorrente">
									<logic:equal value="Fixa" property="tipo" name="tituloPagarCorrente">
										<html:text style="width:80px" styleClass="form-control input-sm dinheiro text-right" styleId="valorCampo${i}" property="despesa.contaPagar.parcelas[${i}].valor" name="despesaForm" />
									</logic:equal>
									<logic:notEqual value="Fixa" property="tipo" name="tituloPagarCorrente">
										${tituloPagarCorrente.valor}
										<html:hidden styleId="valorCampo${i}" property="despesa.contaPagar.parcelas[${i}].valor" name="despesaForm" />
									</logic:notEqual>
								</logic:equal>
								<logic:equal value="Quitada" property="situacao" name="tituloPagarCorrente">
									${tituloPagarCorrente.valor}
								</logic:equal>

							</td>
							<td class="text-right">
								<logic:equal value="Aberta" property="situacao" name="tituloPagarCorrente">
									<html:text style="width:80px" styleClass="form-control input-sm dinheiro text-right" styleId="valorAcrescimo${i}" property="despesa.contaPagar.parcelas[${i}].acrescimo" name="despesaForm" />
								</logic:equal>
								<logic:equal value="Quitada" property="situacao" name="tituloPagarCorrente">
									${tituloPagarCorrente.acrescimo}
								</logic:equal>
							</td>
							<td class="text-right">
								<label id="valorFinal${i}" style="margin-bottom: 0px !important;">${tituloPagarCorrente.valorFinal}</label>
								<html:hidden styleId="valorFinalCampo${i}" property="despesa.contaPagar.parcelas[${i}].valorFinal" name="despesaForm" />
							</td>
							<td class="text-right">
								<logic:equal value="Aberta" property="situacao" name="tituloPagarCorrente">
									<html:text style="width:85px" styleClass="form-control input-sm dinheiro text-right pago" styleId="valorPago${i}" property="despesa.contaPagar.parcelas[${i}].valorPago" name="despesaForm" />
								</logic:equal>
								<logic:equal value="Quitada" property="situacao" name="tituloPagarCorrente">
									${tituloPagarCorrente.valorPago}
								</logic:equal>
							</td>
							<td class="text-center">
								<logic:equal value="Aberta" property="situacao" name="tituloPagarCorrente">
									<html:text style="width:85px" styleClass="form-control input-sm data text-center" styleId="dataRecebimento${i}" property="despesa.contaPagar.parcelas[${i}].dataRecebimento" name="despesaForm" />
								</logic:equal>
								<logic:equal value="Quitada" property="situacao" name="tituloPagarCorrente">
									${tituloPagarCorrente.dataRecebimentoToString}
								</logic:equal>
							</td>

							<td>
								<logic:equal value="Aberta" property="situacao" name="tituloPagarCorrente">
									<html:select styleClass="form-control input-sm" styleId="formaPagamento${i}" property="despesa.contaPagar.parcelas[${i}].formaPagamento.id" name="despesaForm">
										<html:option value="">Selecione...</html:option>
										<html:optionsCollection name="despesaForm" property="comboFormaPagamentoAVista(${usuarioSessaoPOJO.isADM})" label="label" value="value" />
									</html:select>
								</logic:equal>
								<logic:equal value="Quitada" property="situacao" name="tituloPagarCorrente">
									${tituloPagarCorrente.formaPagamento.nomeCompleto}
								</logic:equal>
							</td>

							<td class="text-center" style="min-width: 80px; vertical-align: middle;">

								<logic:equal value="Aberta" property="situacao" name="tituloPagarCorrente">
									<button type="button" id="quitar${i}" class="btn btn-primary btn-xs" style="font-size: 14px;">Quitar</button>
								</logic:equal>

								<logic:equal value="Quitada" property="situacao" name="tituloPagarCorrente">
									<button type="button" id="estornar${i}" class="btn btn-warning btn-xs" style="font-size: 14px">Estornar</button>
								</logic:equal>

								<%-- <logic:equal value="Fixa" property="tipo" name="tituloPagarCorrente">
									<button type="button" id="excluir${i}" class="btn btn-danger btn-sm fa fa-trash" style="font-size: 13px" onclick="excluir('${tituloPagarCorrente.descricaoOrigem}', 'sistema/tituloPagar', ${tituloPagarCorrente.id} );"></button>
								</logic:equal> --%>
							</td>

							</tr>

							<script type="text/javascript">
												$(document).ready(
														function() {

															$('#quitar${i}').click(
																	function() {
																		var dataVencimento = '${tituloPagarCorrente.dataVencimentoToString}';
																		var valor = $('#valorCampo${i}').val();
																		var valorFinal = $('#valorFinalCampo${i}').val();

																		var mensagem = "";
																		mensagem = '<div class="row">';

																		if ($('#formaPagamento${i}').val() == null || $('#formaPagamento${i}').val() == '') {
																			mensagem = mensagem + '<div class="form-group col-lg-12" style="color:red">'
																					+ 'Forma de Pagamento: <strong>Não Informado!</strong>' + '</div>';
																		} else {
																			mensagem = mensagem + '<div class="form-group col-lg-12">' + 'Forma de Pagamento:<strong>'
																					+ $('#formaPagamento${i} :selected').text() + '</strong>' + '</div>';
																		}

																		mensagem = mensagem + '</div>';
																		mensagem = mensagem + '<div class="row">';

																		mensagem = mensagem + '<div class="form-group col-lg-6">' + 'Data Vencimento:<strong>' + dataVencimento + '</strong>'
																				+ '</div>';

																		if ($('#dataRecebimento${i}').val() == null || $('#dataRecebimento${i}').val() == '') {
																			mensagem = mensagem + '<div class="form-group col-lg-6">' + 'Data Pagamento:<strong>' + dataVencimento
																					+ '</strong> (preenchido automaticamente)' + '</div>';
																		} else {
																			mensagem = mensagem + '<div class="form-group col-lg-6">' + 'Data Pagamento:<strong>' + $('#dataRecebimento${i}').val()
																					+ '</strong>' + '</div>';
																		}

																		mensagem = mensagem + '</div>';
																		mensagem = mensagem + '<div class="row">';

																		mensagem = mensagem + '<div class="form-group col-lg-4">' + 'Valor:<strong>' + valor + '</strong>' + '</div>';

																		if ($('#valorAcrescimo${i}').val() == null || $('#valorAcrescimo${i}').val() == '') {
																			mensagem = mensagem + '<div class="form-group col-lg-4">' + 'Valor Acréscimo:<strong>0,00</strong>' + '</div>';
																		} else {
																			mensagem = mensagem + '<div class="form-group col-lg-4">' + 'Valor Acréscimo:<strong>' + $('#valorAcrescimo${i}').val()
																					+ '</strong>' + '</div>';
																		}

																		mensagem = mensagem + '<div class="form-group col-lg-4">' + 'Valor Total:<strong>' + valorFinal + '</strong>' + '</div>';

																		if ($('#valorPago${i}').val() == null || $('#valorPago${i}').val() == '') {
																			mensagem = mensagem + '<div class="form-group col-lg-12">' + 'Valor Pago:<strong>' + valorFinal
																					+ '</strong> (preenchido automaticamente)' + '</div>';
																		} else {
																			mensagem = mensagem + '<div class="form-group col-lg-12">' + 'Valor Pago:<strong>' + $('#valorPago${i}').val()
																					+ '</strong>';

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
																				mensagem = mensagem + ' (Será gerada uma parcela PARCIAL no valor de : <strong>'
																						+ (formatarDinheiro((valorFinal - valorPago).toFixed(2))) + '</strong>)';
																			}

																			mensagem = mensagem + '</div>';
																		}
																		mensagem = mensagem + '</div>';

																		if ($('#formaPagamento${i}').val() == null || $('#formaPagamento${i}').val() == '') {
																			BootstrapDialog.alert({
																	            title: 'Atenção',
																	            message: 'Por favor informe a Forma de Pagamento!',
																	            type: BootstrapDialog.TYPE_WARNING, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
																	            closable: true, // <-- Default value is false
																	            draggable: true, // <-- Default value is false
																	            buttonLabel: 'OK', // <-- Default value is 'OK',
																	            callback: function(result) {
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
																				buttons : [{
																							label : 'Confirmar',
																							action : function(dialogRef) {
																								var theForm = $('form[name=despesaForm]');
																								var params = theForm.serialize();
																								var actionURL = theForm.attr('action') + '?method=quitarParcela&parcelaSelecionada.id=${tituloPagarCorrente.id}';
																								$.ajax({
																									type : 'POST',
																									url : actionURL,
																									data : params,
																									success : function(data, textStatus, XMLHttpRequest) {	
																																																				
																										if ('${tituloPagarCorrente.tipo}' == 'Fixa') {
																											
																											var camposModal = 
																												'<div class="row">'+
																													'<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">'+
																														'<label>Deseja gerar esta Despesa para o MÊS sugerido?</label>'+
																													'</div>'+
																													'<div class="form-group col-xs-12 col-sm-12 col-md-5 col-lg-4">'+
																														'<label>Vencimento Próxima</label>'+
																														'<input type="text" class="form-control input-sm dataMesAno" id="mesAnoProximaParcela" />'+
																													'</div>'+
																												'</div>';
																												
																												
																											BootstrapDialog.show({
																												size : BootstrapDialog.SIZE_LARGE,
																												title : 'Atenção...',
																												message : camposModal,
																												closable : true,
																												type : BootstrapDialog.TYPE_DANGER,
																												onshown : function() {
																													$("#mesAnoProximaParcela").focus();	
																													
																													var mesAno = (new Date().getMonth()+2)+'/'+new Date().getFullYear();
																													
																													mesAno = mesAno.padStart(7, "0");     // "00000abc"
																													$("#mesAnoProximaParcela").val(mesAno);		
																									            },
																												buttons : [ {
																													label : 'Sim, gerar',
																													action : function(dialogRef) {

																														var actionURL = theForm.attr('action') + '?method=gerarProxima&parcelaSelecionada.id=${tituloPagarCorrente.id}&mesAnoProximaParcela='+$('#mesAnoProximaParcela').val();
																														$.ajax({
																															type : 'POST',
																															url : actionURL,
																															success : function(data, textStatus, XMLHttpRequest) {
																																$('#id-financeiro').html(data);
																																dialogRef.close();
																																
																																atualizarCabecalho();
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
																														$('#id-financeiro').html(data);
																														dialogRef.close();
																													}
																												} ]
																											});
																										}else{																								
																											$('#id-financeiro').html(data);	
																											dialogRef.close();	
																										}
																										
																										dialogRef.close();																										
																										atualizarCabecalho();
	
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

															$('#estornar${i}').click(
																	function() {
																		var mensagem = "";
																		mensagem = '<div class="row">';
																		mensagem = mensagem + '<div class="form-group col-lg-12" style="color:red">';
																		mensagem = mensagem + 'Tem certeza que deseja <strong>ESTORNAR</strong> este titulo?';
																		mensagem = mensagem + '</div>';
																		mensagem = mensagem + '</div>';

																		mensagem = mensagem + '<div class="row">';
																		mensagem = mensagem + '<div class="form-group col-lg-12" style="color:red">';
																		mensagem = mensagem
																				+ 'Isso fará com que os dados referentes ao pagamento sejam apagados tornando o titulo em questão ABERTO novamente!';
																		mensagem = mensagem + '</div>';
																		mensagem = mensagem + '</div>';

																		mensagem = mensagem + '<div class="row">';
																		mensagem = mensagem + '<div class="form-group col-lg-12" style="color:red">';
																		mensagem = mensagem
																				+ '<strong>PS.</strong> Todos os pagamentos PARCIAIS referentes ao titulo em questão serão <strong>APAGADOS!</strong>';
																		mensagem = mensagem + '</div>';
																		mensagem = mensagem + '</div>';

																		BootstrapDialog.show({
																			size : BootstrapDialog.SIZE_NORMAL,
																			title : 'Dados de Estorno',
																			message : mensagem,
																			type : BootstrapDialog.TYPE_WARNING, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
																			closable : true, // <-- Default value is false
																			draggable : true, // <-- Default value is false
																			buttons : [
																					{
																						label : 'Confirmar',
																						action : function(dialogRef) {
																							var theForm = $('form[name=despesaForm]');
																							var actionURL = theForm.attr('action')
																									+ '?method=estornarParcela&parcelaSelecionada.id=${tituloPagarCorrente.id}';
																							$.ajax({
																								type : 'POST',
																								url : actionURL,
																								success : function(data, textStatus, XMLHttpRequest) {

																									$('#id-financeiro').html(data);
																									
																									dialogRef.close();
																									
																									atualizarCabecalho();
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

															$('#valorCampo${i}').keyup(function() {
																calcularValorTotal();
															});

															$('#valorAcrescimo${i}').keyup(function() {
																calcularValorTotal();
															});

															function calcularValorTotal() {
																var valor = $("#valorCampo${i}").val();
																var acrescimo = $("#valorAcrescimo${i}").val();

																if (valor == null || valor == '') {
																	valor = '0.0';
																}
																if (acrescimo == null || acrescimo == '') {
																	acrescimo = '0.0';
																}

																valor = parseFloat(valor.replace('.', '').replace(',', '.').replace('R$', ''), 10);
																acrescimo = parseFloat(acrescimo.replace('.', '').replace(',', '.').replace('R$', ''), 10);

																$('#valorFinal${i}').text(formatarDinheiro((valor + acrescimo).toFixed(2)));
																$('#valorFinalCampo${i}').val(formatarDinheiro((valor + acrescimo).toFixed(2)));
															}
															
															$("#dataRecebimento${i}").focusout(function() {
																validarData('dataRecebimento${i}');
															});

														});
											</script>
						</logic:iterate>
					</tbody>
				</table>
			</div>
		</div>
	</logic:equal>
</logic:notEmpty>