<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<!-- INICIO TABELA -->
<logic:empty name="receitaForm" property="receita.contaReceber.parcelas">
	<div class="row" style="margin-top: 15px;">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="alert alert-warning">Nenhum registro encontrado!</div>
		</div>
	</div>
</logic:empty>
<logic:notEmpty name="receitaForm" property="receita.contaReceber.parcelas">
	<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.tituloReceber.filtrar)">
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
							<th>Multa</th>
							<th>Juros Mora</th>
							<th>Total</th>
							<th>Recebido</th>
							<th class="text-center">Pagamento</th>
							<th style="min-width: 140px;">Forma Pagto</th>
							<th class="text-center" style="width: 120px;">Opções</th>
						</tr>
					</thead>
					<tbody>
						<!-- TABELA -->
						<logic:iterate indexId="i" id="tituloReceberCorrente" name="receitaForm" property="receita.contaReceber.parcelas"
							type="br.com.srcsoftware.modular.financeiro.titulo.tituloreceber.TituloReceberDTO">

							<html:hidden styleClass="form-control input-sm dinheiro bloqueado text-right juros" styleId="juros${i}" property="receita.contaReceber.parcelas[${i}].juros" name="receitaForm" />


							<logic:equal value="Aberta" property="situacaoToString" name="tituloReceberCorrente">
								<tr title="${tituloReceberCorrente.descricaoOrigem}">
							</logic:equal>
							<logic:equal value="Quitada" property="situacaoToString" name="tituloReceberCorrente">
								<tr style="background-color: #a6e1ff !important;" title="${tituloReceberCorrente.descricaoOrigem}">
							</logic:equal>
							<logic:equal value="A_Vencer" property="situacaoToString" name="tituloReceberCorrente">
								<tr style="background-color: #fff0a7 !important;" title="${tituloReceberCorrente.descricaoOrigem}">
							</logic:equal>
							<logic:equal value="Vencida" property="situacaoToString" name="tituloReceberCorrente">
								<tr style="background-color: #ffa7a7 !important;" title="${tituloReceberCorrente.descricaoOrigem}">
							</logic:equal>


							<!-- success / info / warning / danger -->
							<td class="text-center">${tituloReceberCorrente.tipo}</td>
							<td class="text-center">${tituloReceberCorrente.numero}</td>
							<td class="text-center">${tituloReceberCorrente.situacao}</td>
							<td class="text-center">${tituloReceberCorrente.dataVencimentoToString}</td>

							<td class="text-right" id="valor${i}">

								<logic:equal value="Aberta" property="situacao" name="tituloReceberCorrente">
									<logic:equal value="Fixa" property="tipo" name="tituloReceberCorrente">
										<html:text style="width:80px" styleClass="form-control input-sm dinheiro text-right" styleId="valorCampo${i}" property="receita.contaReceber.parcelas[${i}].valor" name="receitaForm" />
									</logic:equal>
									<logic:notEqual value="Fixa" property="tipo" name="tituloReceberCorrente">
										${tituloReceberCorrente.valor}
										<html:hidden styleId="valorCampo${i}" property="receita.contaReceber.parcelas[${i}].valor" name="receitaForm" />
									</logic:notEqual>
								</logic:equal>
								<logic:equal value="Quitada" property="situacao" name="tituloReceberCorrente">
									${tituloReceberCorrente.valor}
								</logic:equal>

							</td>
													
							<td class="text-right">
								<logic:equal value="Aberta" property="situacao" name="tituloReceberCorrente">									
									<html:text style="width:80px" styleClass="form-control input-sm decimal text-right" styleId="percentualMulta${i}" property="receita.contaReceber.parcelas[${i}].percentualMulta" name="receitaForm" />
								</logic:equal>
								<logic:equal value="Quitada" property="situacao" name="tituloReceberCorrente">
									${tituloReceberCorrente.percentualMulta}
								</logic:equal>
							</td>
							<td class="text-right">
								<logic:equal value="Aberta" property="situacao" name="tituloReceberCorrente">
									<html:text style="width:80px" styleClass="form-control input-sm decimal text-right" styleId="percentualJuros${i}" property="receita.contaReceber.parcelas[${i}].percentualJuros" name="receitaForm" />
								</logic:equal>
								<logic:equal value="Quitada" property="situacao" name="tituloReceberCorrente">
									${tituloReceberCorrente.percentualJuros}
								</logic:equal>
							</td>
							
							<td class="text-right">
								<label id="valorFinal${i}" style="margin-bottom: 0px !important;">${tituloReceberCorrente.valorFinal}</label>
								<html:hidden styleId="valorFinalCampo${i}" property="receita.contaReceber.parcelas[${i}].valorFinal" name="receitaForm" />
							</td>
							<td class="text-right">
								<logic:equal value="Aberta" property="situacao" name="tituloReceberCorrente">
									<html:text style="width:85px" styleClass="form-control input-sm dinheiro text-right pago" styleId="valorPago${i}" property="receita.contaReceber.parcelas[${i}].valorPago"
										name="receitaForm" />
								</logic:equal>
								<logic:equal value="Quitada" property="situacao" name="tituloReceberCorrente">
									${tituloReceberCorrente.valorPago}
								</logic:equal>
							</td>
							<td class="text-center">
								<logic:equal value="Aberta" property="situacao" name="tituloReceberCorrente">
									<html:text style="width:85px" styleClass="form-control input-sm data text-center" styleId="dataRecebimento${i}" property="receita.contaReceber.parcelas[${i}].dataRecebimento"
										name="receitaForm" />
								</logic:equal>
								<logic:equal value="Quitada" property="situacao" name="tituloReceberCorrente">
									${tituloReceberCorrente.dataRecebimentoToString}
								</logic:equal>
							</td>

							<td>
								<logic:equal value="Aberta" property="situacao" name="tituloReceberCorrente">
									<html:select styleClass="form-control input-sm" styleId="formaPagamento${i}" property="receita.contaReceber.parcelas[${i}].formaPagamento.id" name="receitaForm">
										<html:option value="">Selecione...</html:option>
										<html:optionsCollection name="receitaForm" property="comboFormaPagamentoAVista(${usuarioSessaoPOJO.isADM})" label="label" value="value" />
									</html:select>
								</logic:equal>
								<logic:equal value="Quitada" property="situacao" name="tituloReceberCorrente">
									${tituloReceberCorrente.formaPagamento.nomeCompleto}
								</logic:equal>
							</td>

							<td class="text-center" style="min-width: 80px; vertical-align: middle;">

								<logic:equal value="Aberta" property="situacao" name="tituloReceberCorrente">
									<button type="button" id="quitar${i}" class="btn btn-primary btn-xs" style="font-size: 14px;">Quitar</button>
								</logic:equal>

								<logic:equal value="Quitada" property="situacao" name="tituloReceberCorrente">
									<button type="button" id="estornar${i}" class="btn btn-warning btn-xs" style="font-size: 14px">Estornar</button>
								</logic:equal>

								<%-- <logic:equal value="Fixa" property="tipo" name="tituloReceberCorrente">
									<button type="button" id="excluir${i}" class="btn btn-danger btn-sm fa fa-trash" style="font-size: 13px" onclick="excluir('${tituloReceberCorrente.descricaoOrigem}', 'sistema/tituloReceber', ${tituloReceberCorrente.id} );"></button>
								</logic:equal> --%>
							</td>

							</tr>

							<script type="text/javascript">
								$(document)
										.ready(
												function() {
													
													$('#quitar${i}')
															.click(
																	function() {
																		var dataVencimento = '${tituloReceberCorrente.dataVencimentoToString}';
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
																		
																		if ($('#percentualJuros${i}').val() == null || $('#percentualJuros${i}').val() == '') {
																			mensagem = mensagem + '<div class="form-group col-lg-4">' + '% Juros:<strong>0,00</strong>' + '</div>';
																		} else {
																			mensagem = mensagem + '<div class="form-group col-lg-4">' + '% Juros:<strong>' + $('#percentualJuros${i}').val()
																					+ '</strong>' + '</div>';
																		}
																		
																		if ($('#percentualMulta${i}').val() == null || $('#percentualMulta${i}').val() == '') {
																			mensagem = mensagem + '<div class="form-group col-lg-4">' + '% Multa:<strong>0,00</strong>' + '</div>';
																		} else {
																			mensagem = mensagem + '<div class="form-group col-lg-4">' + '% Multa:<strong>' + $('#percentualMulta${i}').val()
																					+ '</strong>' + '</div>';
																		}
																		
																		mensagem = mensagem + '<div class="form-group col-lg-4">' + 'Valor Total:<strong>' + valorFinal + '</strong>' + '</div>';
																		
																		if ($('#valorPago${i}').val() == null || $('#valorPago${i}').val() == '') {
																			mensagem = mensagem + '<div class="form-group col-lg-12">' + 'Valor Recebido:<strong>' + valorFinal
																					+ '</strong> (preenchido automaticamente)' + '</div>';
																		} else {
																			mensagem = mensagem + '<div class="form-group col-lg-12">' + 'Valor Recebido:<strong>' + $('#valorPago${i}').val()
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
																			
																			BootstrapDialog
																					.show({
																						size : BootstrapDialog.SIZE_NORMAL,
																						title : 'Dados de Quitação',
																						message : mensagem,
																						type : BootstrapDialog.TYPE_INFO, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
																						closable : true, // <-- Default value is false
																						draggable : true, // <-- Default value is false
																						buttons : [
																								{
																									label : 'Confirmar',
																									action : function(dialogRef) {
																										var theForm = $('form[name=receitaForm]');
																										var params = theForm.serialize();
																										var actionURL = theForm.attr('action')
																												+ '?method=quitarParcela&parcelaSelecionada.id=${tituloReceberCorrente.id}';
																										$
																												.ajax({
																													type : 'POST',
																													url : actionURL,
																													data : params,
																													success : function(data, textStatus, XMLHttpRequest) {
																														
																														if ('${tituloReceberCorrente.tipo}' == 'Fixa') {
																															
																															var camposModal = '<div class="row">'
																																	+ '<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">'
																																	+ '<label>Deseja gerar esta Receita para o MÊS sugerido?</label>'
																																	+ '</div>'
																																	+ '<div class="form-group col-xs-12 col-sm-12 col-md-5 col-lg-4">'
																																	+ '<label>Vencimento Próxima</label>'
																																	+ '<input type="text" class="form-control input-sm dataMesAno" id="mesAnoProximaParcela" />'
																																	+ '</div>' + '</div>';
																															
																															BootstrapDialog
																																	.show({
																																		size : BootstrapDialog.SIZE_LARGE,
																																		title : 'Atenção...',
																																		message : camposModal,
																																		closable : true,
																																		type : BootstrapDialog.TYPE_DANGER,
																																		onshown : function() {
																																			$("#mesAnoProximaParcela").focus();
																																			
																																			var mesAno = (new Date().getMonth() + 2) + '/'
																																					+ new Date().getFullYear();
																																			
																																			mesAno = mesAno.padStart(7, "0"); // "00000abc"
																																			$("#mesAnoProximaParcela").val(mesAno);
																																		},
																																		buttons : [
																																				{
																																					label : 'Sim, gerar',
																																					action : function(dialogRef) {
																																						
																																						var actionURL = theForm.attr('action')
																																								+ '?method=gerarProxima&parcelaSelecionada.id=${tituloReceberCorrente.id}&mesAnoProximaParcela='
																																								+ $('#mesAnoProximaParcela').val();
																																						$
																																								.ajax({
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
																														} else {
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
																	var actionURL = theForm.attr('action') + '?method=estornarParcela&parcelaSelecionada.id=${tituloReceberCorrente.id}';
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
													
													$('#percentualJuros${i}').keyup(function() {
														calcularValorTotal();
													});
													
													$('#percentualMulta${i}').keyup(function() {
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
														var actionURL = theForm.attr('action') + '?method=calcularValorTotal' + '&parcelaSelecionada.id=${tituloReceberCorrente.id}';
														$.ajax({
															type : 'POST',
															url : actionURL,
															data : params,
															success : function(data, textStatus, XMLHttpRequest) {
																
																$('#valorFinal${i}').text(data.valorTotal);
																$('#valorFinalCampo${i}').val(data.valorTotal);																
																
																$('#juros${i}').val(data.juros);
															},
															error : function(XMLHttpRequest, textStatus, errorThrown) {
																alert(textStatus);
																$('#valorFinal${i}').val(null);
																$('#valorFinalCampo${i}').val(null);	
																$('#juros${i}').val(null);
															}
														});
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