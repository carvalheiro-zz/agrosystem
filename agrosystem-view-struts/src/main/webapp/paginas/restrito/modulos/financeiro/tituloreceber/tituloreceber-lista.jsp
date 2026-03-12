<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

<!-- CAMPOS DE FILTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -30px; margin-bottom: -15px;">
		<h1 class="page-header cabecalho_pagina">
			<i class="fa fa-graduation-cap" style="font-size: 26px;"></i>
			Pesquisar Parcelas a Receber
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Listagem das Parcelas a Receber
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<html:form styleId="form_tituloReceber_consulta" action="restrito/sistema/tituloReceber" method="post">
	<html:hidden property="method" value="empty" />

	<div class="row">

		<!-- Define o tamanho geral da tela -->
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="panel" style="box-shadow: 0 10px 30px rgba(0, 0, 0, 0.8); border-color: #909090;">

				<!-- INICIO CAMPOS DE PESQUISA -->
				<div class="panel-heading cor-sistema">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">


							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label class="text-white">Nº Nota</label>
									<html:text styleClass="form-control input-sm" styleId="numeroNota" property="tituloReceberFilter.numeroNota" name="tituloReceberForm" />
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label class="text-white">Cliente</label>
									<html:text styleClass="form-control input-sm" styleId="nomePessoa" property="tituloReceberFilter.nomePessoa" name="tituloReceberForm" />
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label class="text-white">Descrição</label>
									<html:text styleClass="form-control input-sm" styleId="descricao" property="tituloReceberFilter.descricaoOrigem" name="tituloReceberForm" />
								</div>

								<%-- <div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-1">
									<label class="text-white">Parcela</label>
									<html:text styleClass="form-control input-sm inteiro" styleId="numero" property="tituloReceberFilter.numero" name="tituloReceberForm" />
								</div> --%>
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
									<label class="text-white">Emissão Ini.</label>
									<html:text styleClass="form-control input-sm data" styleId="dataLancamentoInicial" property="dataLancamentoInicial" name="tituloReceberForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
									<label class="text-white">Emissão Fin.</label>
									<html:text styleClass="form-control input-sm data" styleId="dataLancamentoFinal" property="dataLancamentoFinal" name="tituloReceberForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
									<label class="text-white">Vencimento Ini.</label>
									<html:text styleClass="form-control input-sm data" styleId="dataVencimentoInicial" property="dataVencimentoInicial" name="tituloReceberForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
									<label class="text-white">Vencimento Fin.</label>
									<html:text styleClass="form-control input-sm data" styleId="dataVencimentoFinal" property="dataVencimentoFinal" name="tituloReceberForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
									<label class="text-white">Pagamento Ini.</label>
									<html:text styleClass="form-control input-sm data" styleId="dataRecebimentoInicial" property="dataRecebimentoInicial" name="tituloReceberForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
									<label class="text-white">Pagamento Fin.</label>
									<html:text styleClass="form-control input-sm data" styleId="dataRecebimentoFinal" property="dataRecebimentoFinal" name="tituloReceberForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
									<label class="text-white">Tipo</label>
									<html:select styleClass="form-control input-sm" styleId="situacao" property="tituloReceberFilter.tipo" name="tituloReceberForm">
										<html:option value="">Selecione...</html:option>
										<html:option value="A Vista">A Vista</html:option>
										<html:option value="Parcela">Parcela</html:option>
										<html:option value="Entrada">Entrada</html:option>
										<html:option value="Fixa">Fixa</html:option>
										<html:option value="Mensal">Mensal</html:option>
									</html:select>
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
									<label class="text-white">Forma Pagamento</label>
									<html:select styleClass="form-control input-sm" styleId="formaPagamento" property="tituloReceberFilter.formaPagamento.id" name="tituloReceberForm">
										<html:option value="">Selecione...</html:option>
										<html:optionsCollection name="tituloReceberForm" property="comboFormaPagamento(${usuarioSessaoPOJO.isADM})" label="label" value="value" />
									</html:select>
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
									<label class="text-white">Situaçao</label>
									<html:select styleClass="form-control input-sm" styleId="situacao" property="tituloReceberFilter.situacao" name="tituloReceberForm">
										<html:option value="">Selecione...</html:option>
										<html:option value="Aberta">Aberta</html:option>
										<html:option value="Quitada">Quitada</html:option>
									</html:select>
								</div>
								<div class="form-group col-xs-12 col-sm-2 col-md-2 col-lg-2">
									<label class="text-white">Hoje?</label>
									<input type="checkbox" id="hojeCheck" checked data-toggle="toggle" data-on="Hoje" data-off="-" data-onstyle="primary" data-offstyle="danger" data-width="100%" data-size="small">
									<html:hidden styleId="hoje" property="hoje" name="tituloReceberForm" />
								</div>
							</div>
							<div class="row">
								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.tituloReceber.filtrar)">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-2">
										<button type="submit" id="pesquisar" class="btn btn-success btn-sm btn-block cor-sistema">
											<i class="fa fa-search"></i>
											Pesquisar
										</button>
									</div>
								</logic:equal>
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-2">
									<button type="button" id="limparPesquisa" class="btn btn-success btn-sm btn-block cor-sistema">
										<i class="glyphicon glyphicon-erase"></i>
										Limpar
									</button>
								</div>
							</div>

						</div>
					</div>
				</div>
				<!-- TERMINO CAMPOS DE PESQUISA -->

				<!-- INICIO TABELA -->
				<logic:empty name="tituloReceberForm" property="titulosReceber">
					<div class="row" style="margin-top: 15px;">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class="alert alert-warning">Nenhum registro encontrado!</div>
						</div>
					</div>
				</logic:empty>
				<logic:notEmpty name="tituloReceberForm" property="titulosReceber">
					<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.tituloReceber.filtrar)">
						<div class="panel-body">
							<div class="table-responsive">
								<table class="table table-bordered table-striped" style="font-size: 12px;">
									<thead>
										<!-- CABEÇALHO DA TABELA -->
										<tr class="cor-sistema" style="color: white;">
											<th class="text-center">Nº Nota</th>
											<th>Cliente</th>
											<!-- <th class="text-center">Descrição</th> -->
											<th class="text-center">Tipo</th>
											<th class="text-center">Parcela</th>
											<th class="text-center">Situação</th>
											<th class="text-center">Vencimento</th>
											<th>Valor</th>
											<th>Multa</th>
											<th>Juros Mora</th>
											<th>Total</th>
											<th>Pago</th>
											<th class="text-center">Pagamento</th>
											<th style="min-width: 140px;">Forma Pagto</th>
											<%-- <logic:equal value="true" name="usuarioSessaoPOJO" property="isADM" scope="session">
												<th>Empresa</th>
											</logic:equal> --%>
											<th class="text-center" style="width: 80px;">Opções</th>
										</tr>
									</thead>
									<tbody>
										<!-- TABELA -->
										<logic:iterate indexId="i" id="tituloReceberCorrente" name="tituloReceberForm" property="titulosReceber"
											type="br.com.srcsoftware.modular.financeiro.titulo.tituloreceber.TituloReceberDTO">
											<html:hidden styleClass="form-control input-sm decimal bloqueado text-right juros" styleId="juros${i}" property="titulosReceber[${i}].juros" name="tituloReceberForm" />

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
											<%-- <td class="text-center">${tituloReceberCorrente.numeroNota}</td> --%>											
											
											<td class="text-center">
											<logic:equal value="true" property="parcial" name="tituloReceberCorrente">
												<i class="fa fa fa-reply fa-rotate-180" style="font-size: 14px; color: #00a9ff;"></i>
												${tituloReceberCorrente.numeroNota}
											</logic:equal>
											<logic:notEqual value="true" property="parcial" name="tituloReceberCorrente">
												${tituloReceberCorrente.numeroNota}
											</logic:notEqual>											
											
											</td>
											
											<td>${tituloReceberCorrente.nomePessoa}</td>
											<%-- <td class="text-center">${tituloReceberCorrente.numeroOrigem}</td> --%>
											<%-- <td>${tituloReceberCorrente.descricaoOrigem}</td> --%>
											<td class="text-center">${tituloReceberCorrente.tipo}</td>
											<td class="text-center">${tituloReceberCorrente.numero}</td>
											<td class="text-center">${tituloReceberCorrente.situacao}</td>
											<td class="text-center">${tituloReceberCorrente.dataVencimentoToString}</td>

											<td class="text-right" id="valor${i}">
												${tituloReceberCorrente.valor}
												<html:hidden styleId="valorCampo${i}" property="titulosReceber[${i}].valor" name="tituloReceberForm" />
											</td>
											<logic:equal value="Aberta" property="situacao" name="tituloReceberCorrente">
												<td class="text-right">
													<html:text style="width:80px" styleClass="form-control input-sm decimal text-right" styleId="percentualMulta${i}" property="titulosReceber[${i}].percentualMulta"
														name="tituloReceberForm" />
												</td>
												<td class="text-right">
													<html:text style="width:80px" styleClass="form-control input-sm decimal text-right" styleId="percentualJuros${i}" property="titulosReceber[${i}].percentualJuros"
														name="tituloReceberForm" />
												</td>
											</logic:equal>
											<logic:equal value="Quitada" property="situacao" name="tituloReceberCorrente">
												<td class="text-right">${tituloReceberCorrente.percentualMulta}</td>
												<td class="text-right">${tituloReceberCorrente.percentualJuros}</td>
											</logic:equal>

											<td class="text-right">
												<label id="valorFinal${i}" style="margin-bottom: 0px !important;">${tituloReceberCorrente.valorFinal}</label>
												<html:hidden styleId="valorFinalCampo${i}" property="titulosReceber[${i}].valorFinal" name="tituloReceberForm" />
											</td>
											<td class="text-right">
												<logic:equal value="Aberta" property="situacao" name="tituloReceberCorrente">
													<html:text style="width:85px" styleClass="form-control input-sm dinheiro text-right pago" styleId="valorPago${i}" property="titulosReceber[${i}].valorPago"
														name="tituloReceberForm" />
												</logic:equal>
												<logic:equal value="Quitada" property="situacao" name="tituloReceberCorrente">
													${tituloReceberCorrente.valorPago}
												</logic:equal>
											</td>
											<td class="text-center">
												<logic:equal value="Aberta" property="situacao" name="tituloReceberCorrente">
													<html:text style="width:85px" styleClass="form-control input-sm data text-center" styleId="dataRecebimento${i}" property="titulosReceber[${i}].dataRecebimento"
														name="tituloReceberForm" />
												</logic:equal>
												<logic:equal value="Quitada" property="situacao" name="tituloReceberCorrente">
													${tituloReceberCorrente.dataRecebimentoToString}
												</logic:equal>
											</td>

											<logic:equal value="Haver" property="formaPagamento.tipoFormaPagamento.nome" name="tituloReceberCorrente">
												<td style="color: rgb(236, 151, 31);">
											</logic:equal>
											<logic:notEqual value="Haver" property="formaPagamento.tipoFormaPagamento.nome" name="tituloReceberCorrente">
												<td>
											</logic:notEqual>
											<logic:equal value="Aberta" property="situacao" name="tituloReceberCorrente">
												<html:select styleClass="form-control input-sm" styleId="formaPagamento${i}" property="titulosReceber[${i}].formaPagamento.id" name="tituloReceberForm">
													<html:option value="">Selecione...</html:option>
													<html:optionsCollection name="tituloReceberForm" property="comboFormaPagamento(${usuarioSessaoPOJO.isADM})" label="label" value="value" />
												</html:select>
											</logic:equal>
											<logic:equal value="Quitada" property="situacao" name="tituloReceberCorrente">
													${tituloReceberCorrente.formaPagamento.nomeCompleto}
												</logic:equal>
											</td>

											<%-- <logic:equal value="true" name="usuarioSessaoPOJO" property="isADM" scope="session">
												<td>${tituloReceberCorrente.empresa.nomeFantasia}</td>
											</logic:equal> --%>

											<td class="text-center" style="min-width: 80px; vertical-align: middle;">
												<logic:equal value="true" property="permiteQuitarEstornar" name="tituloReceberCorrente">
													<logic:equal value="Aberta" property="situacao" name="tituloReceberCorrente">
														<button type="button" id="quitar${i}" class="btn btn-primary btn-xs" style="font-size: 14px;">Quitar</button>
													</logic:equal>

													<logic:equal value="Quitada" property="situacao" name="tituloReceberCorrente">
														<button type="button" id="estornar${i}" class="btn btn-warning btn-xs" style="font-size: 14px">Estornar</button>
													</logic:equal>

													<logic:equal value="Fixa" property="tipo" name="tituloReceberCorrente">
														<button type="button" id="excluir${i}" class="btn btn-danger btn-sm fa fa-trash" style="font-size: 13px"
															onclick="excluir('${tituloReceberCorrente.descricaoOrigem}', 'sistema/tituloReceber', ${tituloReceberCorrente.id} );"></button>
													</logic:equal>
												</logic:equal>
											</td>

											</tr>

											<script type="text/javascript">
												$(document).ready(
														function() {
															
															
															
															$('#quitar${i}').click(function() {
																var dataVencimento = '${tituloReceberCorrente.dataVencimentoToString}';
																var valor = '${tituloReceberCorrente.valor}';
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
																	mensagem = mensagem + '<div class="form-group col-lg-4">' + '% Juros:<strong>' + $('#percentualJuros${i}').val() + '</strong>' + '</div>';
																}
																
																if ($('#percentualMulta${i}').val() == null || $('#percentualMulta${i}').val() == '') {
																	mensagem = mensagem + '<div class="form-group col-lg-4">' + '% Multa:<strong>0,00</strong>' + '</div>';
																} else {
																	mensagem = mensagem + '<div class="form-group col-lg-4">' + '% Multa:<strong>' + $('#percentualMulta${i}').val() + '</strong>' + '</div>';
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
																						var theForm = $('form[name=tituloReceberForm]');
																						var params = theForm.serialize();
																						var actionURL = theForm.attr('action') + '?method=quitarTituloReceber&tituloReceberSelecionado.id=${tituloReceberCorrente.id}';
																						$.ajax({
																							type : 'POST',
																							url : actionURL,
																							data : params,
																							success : function(data, textStatus, XMLHttpRequest) {
																								
																								abrirModalProcessando();
																								executarComSubmit('form_tituloReceber_consulta', 'paginar');
																								
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
																							var theForm = $('form[name=tituloReceberForm]');
																							var actionURL = theForm.attr('action')
																									+ '?method=estornarTituloReceber&tituloReceberSelecionado.id=${tituloReceberCorrente.id}';
																							$.ajax({
																								type : 'POST',
																								url : actionURL,
																								success : function(data, textStatus, XMLHttpRequest) {

																									//$('#id_parcelas_ajax').html(data);
																									abrirModalProcessando();
																									executarComSubmit('form_tituloReceber_consulta', 'paginar');
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
															
															$('#percentualJuros${i}').keyup(function() {
																calcularValorTotal();
															});
															
															$('#percentualMulta${i}').keyup(function() {
																calcularValorTotal();
															});															
															
															$('#dataRecebimento${i}').keyup(function() {																
																if( $('#dataRecebimento${i}').val().length == 10 ){																	
																	calcularValorTotal();
																}
															});

															function calcularValorTotal() {								
																var theForm = $('form[name=tituloReceberForm]');
																var params = theForm.serialize();
																var actionURL = theForm.attr('action') + '?method=calcularValorTotal&tituloReceberSelecionado.id=${tituloReceberCorrente.id}';
																$.ajax({
																	type : 'POST',
																	url : actionURL,
																	data : params,
																	success : function(data, textStatus, XMLHttpRequest) {
																		$('#valorFinalCampo${i}').val(data.valorTotal);
																		$('#valorFinal${i}').text(data.valorTotal);
																		$('#juros${i}').val(data.juros);
																	},
																	error : function(XMLHttpRequest, textStatus, errorThrown) {
																		alert(textStatus);
																		$('#valorFinalCampo${i}').val(null);
																		$('#valorFinal${i}').text(null);
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
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
								<span class="label label-danger" style="font-size: 15px;">Total a vista (${tituloReceberForm.totalVista})</span>
								<span class="label label-warning" style="font-size: 15px;">Total a parcelado (${tituloReceberForm.totalParcelado})</span>
								<span class="label label-info" style="font-size: 15px;">Total Fixo (${tituloReceberForm.totalFixo})</span>							
								<span class="label label-default" style="font-size: 15px;">Bruto (${tituloReceberForm.bruto})</span>
								<span class="label label-default" style="font-size: 15px;">Bruto + Acrés (${tituloReceberForm.brutoMaisAcrescimo})</span>
								<span class="label label-primary" style="font-size: 15px;">Pagos (${tituloReceberForm.pagos})</span>
								<span class="label label-success" style="font-size: 15px;">Abertos (${tituloReceberForm.abertos})</span>
							</div>
							
							<%-- <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 text-left">
								<span class="label label-danger" style="font-size: 15px;">Total a vista (${tituloReceberForm.totalVista})</span>
								<span class="label label-warning" style="font-size: 15px;">Total a parcelado (${tituloReceberForm.totalParcelado})</span>
								<span class="label label-info" style="font-size: 15px;">Total Fixo (${tituloReceberForm.totalFixo})</span>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 text-right">
								<span class="label label-default" style="font-size: 15px;">Bruto (${tituloReceberForm.bruto})</span>
								<span class="label label-default" style="font-size: 15px;">Bruto + Acrés (${tituloReceberForm.brutoMaisAcrescimo})</span>
								<span class="label label-primary" style="font-size: 15px;">Pagos (${tituloReceberForm.pagos})</span>
								<span class="label label-success" style="font-size: 15px;">Abertos (${tituloReceberForm.abertos})</span>
							</div> --%>
						</div>
					</logic:equal>
				</logic:notEmpty>
				<!-- TERMINO TABELA -->
				<!-- /.panel-body -->

				<!-- INICIO PAGINAÇÃO -->
				<logic:notEmpty name="tituloReceberForm" property="titulosReceber">
					<div class="panel-footer">
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">

								<nav aria-label="Page navigation">
									<ul class="pagination" style="font-size: 12px;">
										<li>
											<a href="javascript://" style="cursor: default;">Encontrados: ${tituloReceberForm.paginacao.totalRegistros} </a>
										</li>
										<logic:equal name="tituloReceberForm" property="paginacao.voltarPaginacaoInativo" value="true">
											<li class="page-item disabled">
												<a class="page-link" href="javascript://" aria-label="Previous">
													<span aria-hidden="true">&laquo;</span>
													<span class="sr-only">Anterior</span>
												</a>
											</li>
										</logic:equal>
										<logic:equal name="tituloReceberForm" property="paginacao.voltarPaginacaoInativo" value="false">
											<li class="page-item">
												<a class="page-link" href="${contextPath}/restrito/sistema/tituloReceber.src?method=voltar&forwardDestino=tituloReceberLista" aria-label="Previous">
													<span aria-hidden="true">&laquo;</span>
													<span class="sr-only">Anterior</span>
												</a>
											</li>
										</logic:equal>

										<c:forEach var="i" begin="${tituloReceberForm.paginacao.paginaInicial}" end="${tituloReceberForm.paginacao.paginaFinal}" step="1" varStatus="row">
											<c:if test="${tituloReceberForm.paginacao.paginaCorrente == i}">
												<li class="page-item active">
											</c:if>

											<c:if test="${tituloReceberForm.paginacao.paginaCorrente != i}">
												<li class="page-item ">
											</c:if>

											<a class="page-link" href="${contextPath}/restrito/sistema/tituloReceber.src?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>
											</li>
										</c:forEach>

										<logic:equal name="tituloReceberForm" property="paginacao.avancarPaginacaoInativo" value="true">
											<li class="page-item disabled">
												<a class="page-link" href="javascript://" aria-label="Next">
													<span aria-hidden="true">&raquo;</span>
													<span class="sr-only">Próximo</span>
												</a>
											</li>
										</logic:equal>
										<logic:equal name="tituloReceberForm" property="paginacao.avancarPaginacaoInativo" value="false">
											<li class="page-item ">
												<a class="page-link" href="${contextPath}/restrito/sistema/tituloReceber.src?method=avancar&forwardDestino=tituloReceberLista" aria-label="Next">
													<span aria-hidden="true">&raquo;</span>
													<span class="sr-only">Próximo</span>
												</a>
											</li>
										</logic:equal>
									</ul>
								</nav>
							</div>
						</div>
					</div>
				</logic:notEmpty>
				<!-- TERMINO PAGINAÇÃO -->

			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
</html:form>

<script type="text/javascript">
	$(document).ready(function() {
		/* Foco inicial */
		$("#descricao").focus();

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#numeroNota").attr("maxlength", 10);
		$("#nomePessoa").attr("maxlength", 50);
		$("#descricao").attr("maxlength", 50);

		/* Setando os placeholder dos campos*/
		$("#numeroNota").attr("placeholder", "Número nota a ser pesquisada");
		$("#nomePessoa").attr("placeholder", "Cliente a ser pesquisado");
		$("#descricao").attr("placeholder", "Descrição a ser pesquisada");

		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_tituloReceber_consulta").attr("autocomplete", "off");

		$('#form_tituloReceber_consulta').on('submit', function(e) {
			abrirModalProcessando();
		});

		$('#pesquisar').click(function() {
			executar('form_tituloReceber_consulta', 'filtrar');
		});

		$('#limparPesquisa').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_tituloReceber_consulta', 'limparFiltro');
		});		

		$('#hojeCheck').change(function() {
			$('#hoje').val($(this).prop('checked'));
		});

		function preencherCkecks() {
			if ($('#hoje').val() == 'false') {
				$('#hojeCheck').bootstrapToggle('off')
			} else {
				$('#hojeCheck').bootstrapToggle('on')
			}
		}
		preencherCkecks();
		
		$("#dataLancamentoInicial").focusout(function() {
			validarData('dataLancamentoInicial');
		});
		$("#dataLancamentoFinal").focusout(function() {
			validarData('dataLancamentoFinal');
		});
		
		$("#dataVencimentoInicial").focusout(function() {
			validarData('dataVencimentoInicial');
		});
		$("#dataVencimentoFinal").focusout(function() {
			validarData('dataVencimentoFinal');
		});
		
		$("#dataRecebimentoInicial").focusout(function() {
			validarData('dataRecebimentoInicial');
		});
		$("#dataRecebimentoFinal").focusout(function() {
			validarData('dataRecebimentoFinal');
		});
	});

	function selecionar(urlAction) {
		abrirModalProcessando();
		window.location = urlAction;
	}
</script>