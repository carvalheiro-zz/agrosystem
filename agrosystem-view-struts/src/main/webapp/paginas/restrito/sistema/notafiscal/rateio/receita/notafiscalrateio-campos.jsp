<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<jsp:include page="../../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<div class="row">
	<!-- Define o tamanho geral da notaFiscalRateioReceita -->
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel sombra">

			<!-- INICIO FORMULARIO -->
			<div class="panel-body">
				<logic:present property="notaFiscalRateio.id" name="notaFiscalRateioReceitaForm">
					<div class="row">
						<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px;">
							<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
								<i class="fa fa-user-plus"></i>
								&nbsp;${notaFiscalRateioReceitaForm.notaFiscalRateio.contaReceber.nomeUsuarioCriacao }
							</div>
						</div>
						<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
							<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
								<i class="fa fa-calendar-plus-o"></i>
								&nbsp;${notaFiscalRateioReceitaForm.notaFiscalRateio.contaReceber.dataOcorrenciaCriacao}
							</div>
						</div>
						<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
							<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
								<i class="fa fa-refresh"></i>
								&nbsp;${notaFiscalRateioReceitaForm.notaFiscalRateio.contaReceber.nomeUsuarioAlteracao}
							</div>
						</div>
						<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-left: 0px;">
							<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
								<i class="fa fa-calendar-o"></i>
								&nbsp;${notaFiscalRateioReceitaForm.notaFiscalRateio.contaReceber.dataOcorrenciaAlteracao}
							</div>
						</div>
					</div>
				</logic:present>
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_notaFiscalRateioReceita" action="restrito/sistema/notaFiscalRateioReceita" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<div class="alert alert-success" style="margin-bottom: 0px; margin-bottom: 0px; padding-top: 5px; padding-bottom: 5px;" id="alert-situacaoNotaFiscalRateioReceita">
										<div class="row">
											<div class="col-xs-12 col-sm-10 col-md-12 col-lg-4">
												<strong>Rateio:</strong>
												<span style="font-size: 30px;">Receita</span>
											</div>
											<div class="col-xs-12 col-sm-10 col-md-12 col-lg-4">
												<div class="col-xs-12 col-sm-10 col-md-12 col-lg-7">
													<strong>Situação:</strong>
													<span id="situacaoNotaFiscalRateioReceita" style="font-size: 30px;"></span>
												</div>
												<div class="col-xs-12 col-sm-10 col-md-12 col-lg-5">
													<span style="font-size: 30px; color: red;">${notaFiscalRateioReceitaForm.notaFiscalRateio.contaReceber.canceladoToString}</span>
												</div>
											</div>
											<!-- <div class="col-xs-12 col-sm-10 col-md-12 col-lg-2">
												<strong>Situação:</strong>
												<span id="situacaoNotaFiscalRateioReceita" style="font-size: 30px;"></span>
											</div> -->
											<div class="col-xs-12 col-sm-10 col-md-12 col-lg-3 text-center">
												<strong>Total R$ </strong>
												<span id="valorTotalNotaFiscalRateioReceita" style="font-size: 30px;"></span>
											</div>
											<div class="col-xs-12 col-sm-10 col-md-12 col-lg-3 text-right">
												<strong>Pago + Acréscimo R$ </strong>
												<span id="valorTotalMaisAcrescimoNotaFiscalRateioReceita"></span>
											</div>
										</div>
									</div>
								</div>
							</div>
							<ul class="nav nav-tabs" id="tabsNotaFiscalRateioReceita">
								<li class="active">
									<a data-toggle="tab" href="#centrosCustos">Centros de Custos</a>
								</li>
								<li>
									<a data-toggle="tab" href="#dadosFinanceiros">Dados Financeiros</a>
								</li>
								<logic:notEmpty name="notaFiscalRateioReceitaForm" property="notaFiscalRateio.contaReceber.parcelas">
									<li>
										<a data-toggle="tab" href="#dadosParcelas">Parcelas</a>
									</li>
								</logic:notEmpty>
							</ul>

							<div class="tab-content">
								<div id="centrosCustos" class="tab-pane fade in active" style="margin-top: 20px;">
									<div class="row">
										<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
											<label>Código</label>
											<html:text styleClass="form-control input-sm itemAdicionar" styleId="centroCusto" property="itemAdicionar.centroCusto.codigo" name="notaFiscalRateioReceitaForm" />
											<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
											<html:hidden styleClass="itemAdicionar" styleId="centroCustoSelecionado" property="itemAdicionar.centroCusto.id" name="notaFiscalRateioReceitaForm" />
										</div>
										<div class="form-group col-xs-12 col-sm-12 col-md-7 col-lg-7">
											<label>Centro de Custo</label>
											<html:text styleClass="form-control input-sm itemAdicionar bloqueado" styleId="descricaoCentroCusto" property="itemAdicionar.centroCusto.descricao" name="notaFiscalRateioReceitaForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
											<label>R$ Valor</label>
											<html:text styleClass="form-control input-sm itemAdicionar dinheiro" styleId="valor" property="itemAdicionar.valor" name="notaFiscalRateioReceitaForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<label>Descrição Uso</label>
											<html:text styleClass="form-control input-sm itemAdicionar" styleId="descricao" property="itemAdicionar.descricao" name="notaFiscalRateioReceitaForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
											<label>Atribuição de Custo</label>
											<html:select styleClass="form-control input-sm itemAdicionar" styleId="tipo" property="itemAdicionar.tipo" name="notaFiscalRateioReceitaForm">
												<html:option value="">Selecione...</html:option>
												<html:option value="Safra/Setor">Safra/Setor</html:option>
												<html:option value="Tudo">Tudo</html:option>
												<html:option value="Cultura">Cultura</html:option>
											</html:select>
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
											<label>Safra</label>
											<html:text styleClass="form-control input-sm itemAdicionar" styleId="safra" property="itemAdicionar.safra.nome" name="notaFiscalRateioReceitaForm" />
											<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
											<html:hidden styleId="safraSelecionada" styleClass=" itemAdicionar" property="itemAdicionar.safra.id" name="notaFiscalRateioReceitaForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
											<label>Setor</label>
											<html:text styleClass="form-control input-sm itemAdicionar" styleId="setor" property="itemAdicionar.setor.nomeCompleto" name="notaFiscalRateioReceitaForm" />
											<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
											<html:hidden styleId="setorSelecionado" styleClass=" itemAdicionar" property="itemAdicionar.setor.id" name="notaFiscalRateioReceitaForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
											<label>Cultura</label>
											<html:text styleClass="form-control input-sm itemAdicionar" styleId="cultura" property="itemAdicionar.cultura.nome" name="notaFiscalRateioReceitaForm" />
											<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
											<html:hidden styleId="culturaSelecionada" styleClass=" itemAdicionar" property="itemAdicionar.cultura.id" name="notaFiscalRateioReceitaForm" />
										</div>


										<div class="form-group text-left col-xs-12 col-sm-12 col-md-offset-2 col-md-2 col-lg-offset-2 col-lg-2">
											<button class="btn btn-success btn-block" type="button" id="btnAdicionarCentroCusto" style="margin-top: 21px; background-color: #407140;">
												<i class="fa fa-plus">Adicionar</i>
											</button>
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12" id="id-itens">
											<jsp:include page="ajax/painel-itens-ajax.jsp"></jsp:include>
										</div>
									</div>

									<div class="row" style="margin-top: 15px;">
										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<!-- BOTOES -->
											<logic:notPresent property="notaFiscalRateio.id" name="notaFiscalRateioReceitaForm">
												<logic:equal name="notaFiscalRateioReceitaForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.notaFiscalRateio.inserir)">
													<button type="submit" id="inserir" class="btn btn-success btn-sm cor-sistema">
														<i class="fa fa-save"></i>
														Inserir
													</button>
												</logic:equal>
											</logic:notPresent>
											<logic:present property="notaFiscalRateio.id" name="notaFiscalRateioReceitaForm">
												<logic:empty name="notaFiscalRateioReceitaForm" property="notaFiscalRateio.contaReceber.canceladoToString">
													<logic:equal name="notaFiscalRateioReceitaForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.notaFiscalRateio.alterar)">
														<button type="submit" id="alterar" class="btn btn-success btn-sm cor-sistema">
															<i class="fa fa-edit"></i>
															Alterar
														</button>
													</logic:equal>
												</logic:empty>
											</logic:present>
											<button type="button" id="limpar" class="btn btn-success btn-sm cor-sistema">
												<i class="glyphicon glyphicon-erase"></i>
												Limpar
											</button>

											<logic:equal name="notaFiscalRateioReceitaForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.notaFiscalRateio.filtrar)">
												<button type="button" id="filtrar" class="btn btn-success btn-sm cor-sistema">
													<i class="fa fa-search"></i>
													Listagem
												</button>
											</logic:equal>

											<logic:present property="notaFiscalRateio.id" name="notaFiscalRateioReceitaForm">
												<logic:empty name="notaFiscalRateioReceitaForm" property="notaFiscalRateio.contaReceber.canceladoToString">
													<logic:equal name="notaFiscalRateioReceitaForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.notaFiscalRateio.alterar)">
														<button type="button" id="cancelar" class="btn btn-danger btn-sm">
															<i class="fa fa-warning"></i>
															Cancelar
														</button>
													</logic:equal>
												</logic:empty>
											</logic:present>

										</div>
									</div>


								</div>
								<div id="dadosFinanceiros" class="tab-pane fade" style="margin-top: 20px;">

									<div class="row">
										<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
											<label>Tipo</label>
											<html:select styleClass="form-control input-sm " styleId="tipoNF" property="notaFiscalRateio.tipo" name="notaFiscalRateioReceitaForm">
												<html:option value="Receita">Receita</html:option>
											</html:select>
										</div>
										<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4" id="abaNotaFiscalRateioReceita">
											<label>Nome Cliente</label>
											<html:text styleClass="form-control input-sm cor-campos-composicao-sistema" styleId="cliente" property="notaFiscalRateio.cliente.nome" name="notaFiscalRateioReceitaForm" />
											<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
											<html:hidden styleId="clienteSelecionado" property="notaFiscalRateio.cliente.id" name="notaFiscalRateioReceitaForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
											<label>Nº NF</label>
											<html:text styleClass="form-control numero numeroVendaNumeroRecibo input-sm text-center" styleId="numero" property="notaFiscalRateio.numero" name="notaFiscalRateioReceitaForm" />
										</div>
										<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-3">
											<label>Nº Recibo</label>
											<html:text styleClass="form-control numero numeroVendaNumeroRecibo input-sm text-center" styleId="numeroRecibo" property="notaFiscalRateio.numeroRecibo" name="notaFiscalRateioReceitaForm" />
										</div>
										<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
											<label>Emissão</label>
											<html:text styleClass="form-control input-sm data text-center " styleId="data" property="notaFiscalRateio.contaReceber.data" name="notaFiscalRateioReceitaForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-2">
											<label>À vista / Parcelado</label>
											<html:select styleClass="form-control input-sm " styleId="tipoPagamento" property="notaFiscalRateio.contaReceber.tipo" name="notaFiscalRateioReceitaForm">
												<html:option value="">Selecione...</html:option>
												<html:option value="À vista">À vista</html:option>
												<html:option value="Parcelado">Parcelado</html:option>
											</html:select>
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<label>Forma Pagamento</label>
											<logic:notPresent property="notaFiscalRateio.id" name="notaFiscalRateioReceitaForm">
												<a href="#" id="modalCadastrarFormaPagamento">
													<i style="text-shadow: 1px 0px 0px white, -1px 0px 0px white, 0px 1px 0px white, 0px -1px 0px white; color: green; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-plus"></i>
												</a>
											</logic:notPresent>
											<html:select styleClass="form-control input-sm " styleId="formaPagamento" property="notaFiscalRateio.contaReceber.formaPagamento.id" name="notaFiscalRateioReceitaForm">
												<html:option value="">Selecione...</html:option>
												<html:optionsCollection name="notaFiscalRateioReceitaForm" property="comboFormaPagamento(${usuarioSessaoPOJO.isADM})" label="label" value="value" />
											</html:select>
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2" id="divVencimentoPrimeiraParcela">
											<label id="lblVencimentoPrimeiraParcela">Venc.1ª Parcela</label>
											<html:text styleClass="form-control input-sm data text-center " styleId="vencimentoPrimeiraParcela" property="notaFiscalRateio.contaReceber.vencimentoPrimeiraParcela" name="notaFiscalRateioReceitaForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2" id="divValorEntrada">
											<label>R$ Entrada</label>
											<html:text styleClass="form-control input-sm dinheiro " styleId="valorEntrada" property="notaFiscalRateio.contaReceber.valorEntrada" name="notaFiscalRateioReceitaForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3" id="divFormaPagamentoEntrada">
											<label>Forma Pag. Entrada</label>
											<html:select styleClass="form-control input-sm " styleId="formaPagamentoEntrada" property="notaFiscalRateio.contaReceber.formaPagamentoEntrada.id" name="notaFiscalRateioReceitaForm">
												<html:option value="">Selecione...</html:option>
												<html:optionsCollection name="notaFiscalRateioReceitaForm" property="comboFormaPagamentoAVista(${usuarioSessaoPOJO.isADM})" label="label" value="value" />
											</html:select>
										</div>
									</div>

								</div>

								<div id="dadosParcelas" class="tab-pane fade" style="margin-top: 20px;">
									<div class="row">
										<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12" id="id_parcelas_ajax">
											<jsp:include page="ajax/painel-parcelas-ajax.jsp"></jsp:include>
										</div>
									</div>
								</div>
							</div>


						</html:form>
					</div>
				</div>
			</div>
			<!-- TERMINO FORMULARIO -->
			<!-- /.panel-body -->

		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>


<script type="text/javascript">
	$(document)
			.ready(
					function() {

						$("#tipoNF").focus();

						/* Setando NOTNULL nos campos*/
						$("#numero").addClass("obrigatorio");
						$("#data").addClass("obrigatorio");
						$("#tipoPagamento").addClass("obrigatorio");
						$("#vencimentoPrimeiraParcela").addClass("obrigatorio");
						$("#tipoNF").addClass("obrigatorio");

						/* Setando os tamanhos maximos dos campos baseando-se no PO*/
						$("#numero").attr("maxlength", 20);
						$("#numeroRecibo").attr("maxlength", 20);
						$("#safra").prop("maxlength", 100);
						$("#setor").prop("maxlength", 100);
						$("#cultura").prop("maxlength", 60);

						/* Setando os placeholder dos campos*/
						$("#numero").attr("placeholder", "Nº Nota Fiscal");
						$("#numeroRecibo").attr("placeholder", "Nº Recibo");
						$("#cliente").attr("placeholder", "Cliente");
						$("#safra").prop("placeholder", "Safra");
						$("#setor").prop("placeholder", "Setor");
						$("#cultura").prop("placeholder", "Cultura");

						$('#numero').keyup(function() {
							gerenciarObrigatoriedadeNumerosReciboAndNota('numero');
						});

						$('#numeroRecibo').keyup(function() {
							gerenciarObrigatoriedadeNumerosReciboAndNota('numeroRecibo');
						});
						function gerenciarObrigatoriedadeNumerosReciboAndNota(campo) {
							var numeroNF = $("#numero").val();
							var numeroRecibo = $("#numeroRecibo").val();

							if ((numeroNF != null && numeroNF != '')) {
								$("#numero").addClass("obrigatorio");
								$("#numero").attr("required", "required");

								$("#numeroRecibo").removeClass("obrigatorio");
								$("#numeroRecibo").removeAttr("required");
								$("#numeroRecibo").val(null);
							}

							if ((numeroRecibo != null && numeroRecibo != '')) {
								$("#numero").removeClass("obrigatorio");
								$("#numero").removeAttr("required");
								$("#numero").val(null);

								$("#numeroRecibo").addClass("obrigatorio");
								$("#numeroRecibo").attr("required", "required");
							}

							if ((numeroNF == null || numeroNF == '') && (numeroRecibo == null || numeroRecibo == '')) {
								$("#numero").addClass("obrigatorio");
								$("#numero").attr("required", "required");
								$("#numeroRecibo").addClass("obrigatorio");
								$("#numeroRecibo").attr("required", "required");
							}
						}

						$('#cliente').keyup(function() {
							if ($('#cliente').val() == '') {
								$('#clienteSelecionado').val(null);
							}
						});

						$('#cliente').autocomplete({
							minChars : 2,
							noCache : true,
							paramName : 'notaFiscalRateio.cliente.nome',
							serviceUrl : '${contextPath}/restrito/sistema/notaFiscalRateioReceita.src?method=selecionarClienteAutoComplete',
							onSelect : function(suggestion) {
								$('#clienteSelecionado').val(suggestion.data);

								$("#numero").focus();
							},
							onSearchComplete : function(query, suggestions) {
								if (suggestions == null || suggestions == '') {
									$('#clienteSelecionado').val(null);
								}
							}
						});

						$('#centroCusto').keyup(function() {
							if ($('#centroCusto').val() == '') {
								$('.itemAdicionar').val(null);
							}
							gerenciarCamposObrigatoriosItemAdicionar()
						});
						$('#centroCusto').autocomplete({
							minChars : 2,
							noCache : true,
							paramName : 'itemAdicionar.centroCusto.codigo',
							params : {
								'notaFiscalRateio.tipo' : function() {
									return $('#tipoNF').val();
								}
							},
							formatResult : function(suggestion, currentValue) {
								var valorExibir = suggestion.value + " - " + suggestion.descricao;
								return valorExibir;
							},
							serviceUrl : '${contextPath}/restrito/sistema/notaFiscalRateioReceita.src?method=selecionarCentroCustoAutoComplete',
							onSelect : function(suggestion) {
								$('#centroCustoSelecionado').val(suggestion.data);

								$('#descricaoCentroCusto').val(suggestion.descricao);

								$("#valor").focus();
								//executarComSubmit('form_notaFiscalRateioReceita', 'selecionarCentroCustoById');
							},
							onSearchComplete : function(query, suggestions) {
								if (suggestions == null || suggestions == '') {
									$('.itemAdicionar').val(null);
								}
							}
						});

						$('#safra').keyup(function() {
							if ($('#safra').val() == null || $('#safra').val().trim() == '') {
								$('#safraSelecionada').val(null);

								$('#setor').val(null);
								$('#setorSelecionado').val(null);

								$('#cultura').val(null);
								$('#culturaSelecionada').val(null);
							}
						});

						$('#safra').autocomplete({
							minChars : 2,
							noCache : true,
							paramName : 'itemAdicionar.safra.nome',
							serviceUrl : '${contextPath}/restrito/sistema/notaFiscalRateioReceita.src?method=selecionarSafraAutoComplete',
							onSelect : function(suggestion) {
								$('#safraSelecionada').val(suggestion.data);

								if ($('#tipo').val() == '') {
									$("#safra").focus();
								} else if ($('#tipo').val() == 'Safra/Setor') {
									$("#setor").focus();
								} else if ($('#tipo').val() == 'Tudo') {
									$("#imovel").focus();
								} else if ($('#tipo').val() == 'Cultura') {
									$("#cultura").focus();
								}
							},
							onSearchComplete : function(query, suggestions) {
								if (suggestions == null || suggestions == '') {
									$('#safraSelecionada').val(null);

									$('#setor').val(null);
									$('#setorSelecionado').val(null);

									$('#cultura').val(null);
									$('#culturaSelecionada').val(null);
								}
							}
						});

						$('#setor').keyup(function() {
							if ($('#setor').val() == null || $('#setor').val().trim() == '') {
								$('#setorSelecionado').val(null);

								$('#cultura').val(null);
								$('#culturaSelecionada').val(null);
							}
						});

						$('#setor').autocomplete({
							minChars : 2,
							noCache : true,
							paramName : 'itemAdicionar.setor.nomeCompleto',
							params : {
								'itemAdicionar.safra.id' : function() {
									return $('#safraSelecionada').val();
								}
							},
							serviceUrl : '${contextPath}/restrito/sistema/notaFiscalRateioReceita.src?method=selecionarSetorAutoComplete',
							onSelect : function(suggestion) {
								$('#setorSelecionado').val(suggestion.data);

								if ($('#tipo').val() == '') {
									$("#safra").focus();
								} else if ($('#tipo').val() == 'Safra/Setor') {
									$("#imovel").focus();
								} else if ($('#tipo').val() == 'Tudo') {
									$("#safra").focus();
								} else if ($('#tipo').val() == 'Cultura') {
									$("#cultura").focus();
								}
							},
							onSearchComplete : function(query, suggestions) {
								if (suggestions == null || suggestions == '') {
									$('#setorSelecionado').val(null);

									$('#cultura').val(null);
									$('#culturaSelecionada').val(null);
								}
							}
						});

						$('#cultura').keyup(function() {
							if ($('#cultura').val() == null || $('#cultura').val().trim() == '') {
								$('#culturaSelecionada').val(null);
							}
						});

						$('#cultura').autocomplete({
							minChars : 2,
							noCache : true,
							paramName : 'itemAdicionar.cultura.nome',
							params : {
								'itemAdicionar.setor.id' : function() {
									return $('#setorSelecionado').val();
								},
								'itemAdicionar.safra.id' : function() {
									return $('#safraSelecionada').val();
								}
							},
							/* params : {
								'saidaGrao.notaFiscalRateio.id' : function() {
									return $('#aplicacaoSelecionada').val();
								}
							}, */
							serviceUrl : '${contextPath}/restrito/sistema/notaFiscalRateioReceita.src?method=selecionarCulturaAutoComplete',
							onSelect : function(suggestion) {
								$('#culturaSelecionada').val(suggestion.data);

								$("#imovel").focus();
							},
							/* formatResult : function(suggestion, currentValue) {
								var valorExibir = suggestion.value + " - " + suggestion.variedade;
								return valorExibir;
							}, */
							onSearchComplete : function(query, suggestions) {
								if (suggestions == null || suggestions == '') {
									$('#culturaSelecionada').val(null);
								}
							}
						});

						$('#tipo').change(function() {
							$('#safra').val(null);
							$('#safraSelecionada').val(null);
							$('#setor').val(null);
							$('#setorSelecionado').val(null);
							$('#cultura').val(null);
							$('#culturaSelecionada').val(null);
							gerenciarCamposSafraSetorCultura();
						});

						$('#btnAdicionarCentroCusto').click(function() {
							if ('${notaFiscalRateioReceitaForm.notaFiscalRateio.id}' != null && '${notaFiscalRateioReceitaForm.notaFiscalRateio.id}' != '') {
								BootstrapDialog.show({
									size : BootstrapDialog.SIZE_LARGE,
									title : 'Atenção...',
									message : "Alterando o Valor Total da NF, TODAS as parcelas já existentes serão substituidas por novas com valores corrigidos!",
									closable : true,
									type : BootstrapDialog.TYPE_DANGER,
									buttons : [ {
										label : 'Confirmar',
										action : function(dialogRef) {

											var theForm = $('form[name=notaFiscalRateioReceitaForm]');
											var params = theForm.serialize();
											var actionURL = theForm.attr('action') + '?method=adicionarItem';

											$.ajax({
												type : 'POST',
												url : actionURL,
												data : params,
												success : function(data, textStatus, XMLHttpRequest) {

													$('#id-itens').html(data);
													$('.itemAdicionar').val(null);
													$("#centroCusto").focus();

													gerenciarCamposObrigatoriosItemAdicionar();
													gerenciarCamposSafraSetorCultura();
												},
												error : function(XMLHttpRequest, textStatus, errorThrown) {
													alert(textStatus);
												}
											});

											dialogRef.close();
										}
									}, {
										label : 'Cancelar Adição',
										action : function(dialogRef) {

											dialogRef.close();
										}
									} ]
								});

							} else {
								var theForm = $('form[name=notaFiscalRateioReceitaForm]');
								var params = theForm.serialize();
								var actionURL = theForm.attr('action') + '?method=adicionarItem';

								$.ajax({
									type : 'POST',
									url : actionURL,
									data : params,
									success : function(data, textStatus, XMLHttpRequest) {

										$('#id-itens').html(data);
										$('.itemAdicionar').val(null);
										$("#centroCusto").focus();

										gerenciarCamposObrigatoriosItemAdicionar();
										gerenciarCamposSafraSetorCultura();
									},
									error : function(XMLHttpRequest, textStatus, errorThrown) {
										alert(textStatus);
									}
								});
							}
						});

						$('#tipoNF').change(function() {
							$('.itemAdicionar').val(null);
							//gerenciarExibicaoPainelCentrosCusto($('#tipoNF').val())

						});
						$("#tipoNF").focusout(function() {
							if ($('#tipoNF').val() == null || $('#tipoNF').val() == '') {
								$("#tipoNF").focus();
							}

						});

						$('#tipoPagamento').change(function() {
							atualizarComboFormaPagamento();
						});

						var vencimentoPrimeiraParcelaOld = null;
						$("#vencimentoPrimeiraParcela").focusin(function() {
							vencimentoPrimeiraParcelaOld = $("#vencimentoPrimeiraParcela").val();
						});
						$("#vencimentoPrimeiraParcela").focusout(function() {

							if ($("#vencimentoPrimeiraParcela").val() == vencimentoPrimeiraParcelaOld) {
								return;
							}

							if ('${notaFiscalRateioReceitaForm.notaFiscalRateio.id}' != null && '${notaFiscalRateioReceitaForm.notaFiscalRateio.id}' != '') {
								BootstrapDialog.show({
									size : BootstrapDialog.SIZE_LARGE,
									title : 'Atenção...',
									message : "Atenção, alterando o Vencimento, as parcelas já existentes serão substituidas por novas com valores corrigidos!",
									closable : false,
									type : BootstrapDialog.TYPE_DANGER,
									buttons : [ {
										label : 'Confirmar',
										action : function(dialogRef) {

											gerenciarCamposTipoFinanceiro();

											dialogRef.close();
										}
									}, {
										label : 'Cancelar Vencimento informada',
										action : function(dialogRef) {

											$("#vencimentoPrimeiraParcela").val(vencimentoPrimeiraParcelaOld);

											gerenciarCamposTipoFinanceiro();

											dialogRef.close();
										}
									} ]
								});
							}
						});

						var valorEntradaOld = null;
						$("#valorEntrada").focusin(function() {
							valorEntradaOld = $("#valorEntrada").val();
						});
						$("#valorEntrada").focusout(function() {
							if ($("#valorEntrada").val() == valorEntradaOld) {
								return;
							}
							if ('${notaFiscalRateioReceitaForm.notaFiscalRateio.id}' != null && '${notaFiscalRateioReceitaForm.notaFiscalRateio.id}' != '') {
								BootstrapDialog.show({
									size : BootstrapDialog.SIZE_LARGE,
									title : 'Atenção...',
									message : "Alterando o Valor da Entrada, as parcelas já existentes serão substituidas por novas com valores corrigidos!",
									closable : false,
									type : BootstrapDialog.TYPE_DANGER,
									buttons : [ {
										label : 'Confirmar',
										action : function(dialogRef) {
											dialogRef.close();
										}
									}, {
										label : 'Cancelar valor informado',
										action : function(dialogRef) {

											$("#valorEntrada").val(valorEntradaOld);

											dialogRef.close();
										}
									} ]
								});
							}
						});

						var dataOld = null;
						$("#data").focusin(function() {
							dataOld = $("#data").val();
						});
						$("#data").focusout(function() {
							if ($("#data").val() == dataOld) {
								return;
							}
							if ('${notaFiscalRateioReceitaForm.notaFiscalRateio.id}' != null && '${notaFiscalRateioReceitaForm.notaFiscalRateio.id}' != '') {
								BootstrapDialog.show({
									size : BootstrapDialog.SIZE_LARGE,
									title : 'Atenção...',
									message : "Alterando a Data de Emissão, as parcelas já existentes serão substituidas por novas com datas corrigidas!",
									closable : false,
									type : BootstrapDialog.TYPE_DANGER,
									buttons : [ {
										label : 'Confirmar',
										action : function(dialogRef) {
											dialogRef.close();
										}
									}, {
										label : 'Cancelar data informada',
										action : function(dialogRef) {

											$("#data").val(dataOld);

											dialogRef.close();
										}
									} ]
								});
							}
						});

						var tipoPagamentoOld = null;
						$("#tipoPagamento").focusin(function() {
							tipoPagamentoOld = $("#tipoPagamento").val();
						});
						$("#tipoPagamento").focusout(function() {
							if ($("#tipoPagamento").val() == tipoPagamentoOld) {
								return;
							}
							if ('${notaFiscalRateioReceitaForm.notaFiscalRateio.id}' != null && '${notaFiscalRateioReceitaForm.notaFiscalRateio.id}' != '') {
								BootstrapDialog.show({
									size : BootstrapDialog.SIZE_LARGE,
									title : 'Atenção...',
									message : "Alterando o campo À Vista/À Prazo, fará com que as parcelas já existentes sejão substituidas por novas!",
									closable : false,
									type : BootstrapDialog.TYPE_DANGER,
									buttons : [ {
										label : 'Confirmar',
										action : function(dialogRef) {

											gerenciarCamposTipoFinanceiro();

											dialogRef.close();
										}
									}, {
										label : 'Cancelar alteração informada',
										action : function(dialogRef) {

											$("#tipoPagamento").val(tipoPagamentoOld);

											gerenciarCamposTipoFinanceiro();

											dialogRef.close();
										}
									} ]
								});

							}
						});

						var formaPagamentoOld = null;
						$("#formaPagamento").focusin(function() {
							formaPagamentoOld = $("#formaPagamento").val();
						});
						$("#formaPagamento").focusout(function() {
							if ($("#formaPagamento").val() == formaPagamentoOld) {
								return;
							}
							if ('${receitaForm.receita.id}' != null && '${receitaForm.receita.id}' != '') {
								BootstrapDialog.show({
									size : BootstrapDialog.SIZE_LARGE,
									title : 'Atenção...',
									message : "Alterando o campo Forma de Pagamento, fará com que as parcelas já existentes sejão substituidas por novas!",
									closable : false,
									type : BootstrapDialog.TYPE_DANGER,
									buttons : [ {
										label : 'Confirmar',
										action : function(dialogRef) {
											dialogRef.close();
										}
									}, {
										label : 'Cancelar alteração informada',
										action : function(dialogRef) {
											$("#formaPagamento").val(formaPagamentoOld);
											dialogRef.close();
										}
									} ]
								});

							}
						});

						$('#valorTotalNotaFiscalRateioReceita').text('${notaFiscalRateioReceitaForm.notaFiscalRateio.contaReceber.valor}');
						var valorOld = null;
						$("#valor").focusin(function() {
							valorOld = $("#valor").val();
						});
						$("#valor").focusout(function() {
							if ($("#valor").val() == valorOld) {
								return;
							}
							if ('${notaFiscalRateioReceitaForm.notaFiscalRateio.id}' != null && '${notaFiscalRateioReceitaForm.notaFiscalRateio.id}' != '') {
								BootstrapDialog.show({
									size : BootstrapDialog.SIZE_LARGE,
									title : 'Atenção...',
									message : "Alterando o campo Valor da Receita, as parcelas já existentes serão substituidas por novas com valores corrigidos!",
									closable : false,
									type : BootstrapDialog.TYPE_DANGER,
									buttons : [ {
										label : 'Confirmar',
										action : function(dialogRef) {

											$('#valorTotalNotaFiscalRateioReceita').text($("#valor").val());

											dialogRef.close();
										}
									}, {
										label : 'Cancelar valor informado',
										action : function(dialogRef) {

											$("#valor").val(valorOld);

											$('#valorTotalNotaFiscalRateioReceita').text(valorOld);

											dialogRef.close();
										}
									} ]
								});
							} else {
								$('#valorTotalNotaFiscalRateioReceita').text($("#valor").val());
							}
						});

						/* EVENTOS */
						// Desliga o auto-complete da pagina
						$("#form_notaFiscalRateioReceita").attr("autocomplete", "off");

						$('#form_notaFiscalRateioReceita').on('submit', function(e) {
							abrirModalProcessando();
						});

						$('#inserir').click(function() {
							inserirComAba('form_notaFiscalRateioReceita');
						});

						$('#alterar').click(function() {
							alterarComAba('form_notaFiscalRateioReceita');
						});

						$('#cancelar').click(function() {
							BootstrapDialog.show({
								size : BootstrapDialog.SIZE_LARGE,
								title : 'Informativo.',
								message : "Esse processo cancelará todas os titulos não vencidos e não pagos!",
								closable : false,
								type : BootstrapDialog.TYPE_DANGER,
								buttons : [ {
									label : 'Confirmar',
									action : function(dialogRef) {
										executarComSubmit('form_notaFiscalRateioReceita', 'cancelar');
										dialogRef.close();
									}
								}, {
									label : 'Fechar',
									action : function(dialogRef) {
										dialogRef.close();
									}
								} ]
							});
						});

						$('#limpar').click(function() {
							abrirModalProcessando();
							executarComSubmit('form_notaFiscalRateioReceita', 'limpar');
						});

						$('#filtrar').click(function() {
							abrirModalProcessando();
							executarComSubmit('form_notaFiscalRateioReceita', 'abrirListagem');
						});

						$('#valorEntrada').keyup(function() {
							if ($('#valorEntrada').val() != '') {
								$("#valorEntrada").addClass("obrigatorio");
								$("#formaPagamentoEntrada").addClass("obrigatorio");

							} else {
								$("#valorEntrada").removeClass("obrigatorio");
								$("#formaPagamentoEntrada").removeClass("obrigatorio");
							}
							recarregarObrigatorios();
						});
						$('#formaPagamentoEntrada').change(function() {
							if ($('#formaPagamentoEntrada').val() != '') {
								$("#valorEntrada").addClass("obrigatorio");
								$("#formaPagamentoEntrada").addClass("obrigatorio");

							} else {
								$("#valorEntrada").removeClass("obrigatorio");
								$("#formaPagamentoEntrada").removeClass("obrigatorio");
							}
							recarregarObrigatorios();
						});

						$('#ativaCheck').change(function() {
							$('#ativa').val($(this).prop('checked'));
						});

						function preencherCkecks() {
							if ($('#ativa').val() == 'false') {
								$('#ativaCheck').bootstrapToggle('off')
							} else {
								$('#ativaCheck').bootstrapToggle('on')
							}
						}
						preencherCkecks();

						$('#modalCadastrarFormaPagamento').on('click', function() {
							var actionURL = '${contextPath}/restrito/sistema/formaPagamento.src?method=abrirModal';

							$.ajax({
								type : 'POST',
								url : actionURL,
								success : function(data, textStatus, XMLHttpRequest) {
									BootstrapDialog.show({
										size : BootstrapDialog.SIZE_WIDE,
										title : 'Cadastrar Forma de Pagamento',
										message : $('<div id="id-modalFormaPagamento"></div>').append(data),
										type : BootstrapDialog.TYPE_SUCCESS, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
										closable : false, // <-- Default value is false
										draggable : true, // <-- Default value is false
										onshown : function(dialogRef) {
											/* Foco inicial */
											$("#nomeModal").focus();
										},
										buttons : [ {
											hotkey : 13,
											label : 'Inserir',
											cssClass : 'btn-success',
											action : function(dialogRef) {
												var theForm = $('form[name=formaPagamentoForm]');
												var params = theForm.serialize();
												var actionURL = theForm.attr('action') + '?method=inserirModal';

												$.ajax({
													type : 'POST',
													url : actionURL,
													data : params,
													success : function(data, textStatus, XMLHttpRequest) {
														$('#id-modalFormaPagamento').html(data);

														$("#nomeModal").focus();
													},
													error : function(XMLHttpRequest, textStatus, errorThrown) {
														alert(textStatus);
													}
												});
											}
										}, {
											label : 'Fechar',
											action : function(dialogRef) {

												var actionURL = '${contextPath}/restrito/sistema/formaPagamento.src?method=fecharModal';

												$.ajax({
													type : 'POST',
													url : actionURL,
													success : function(data, textStatus, XMLHttpRequest) {
														dialogRef.close();

														atualizarComboFormaPagamento();

														//$("#formaPagamento").focus();

													},
													error : function(XMLHttpRequest, textStatus, errorThrown) {
														alert(textStatus);
													}
												});

											}
										} ]
									});

								},
								error : function(XMLHttpRequest, textStatus, errorThrown) {
									alert(textStatus);
								}
							});

						});

						function atualizarComboFormaPagamento() {
							var theForm = $('form[name=notaFiscalRateioReceitaForm]');
							var params = theForm.serialize();
							var actionURL = theForm.attr('action') + '?method=carregarComboFormaPagamento';

							$
									.ajax({
										type : 'POST',
										url : actionURL,
										data : params,
										success : function(data, textStatus, XMLHttpRequest) {

											var html = "";

											html = html
													+ '<html:select styleClass="form-control input-sm " styleId="formaPagamento" property="notaFiscalRateio.contaReceber.formaPagamento.id" name="notaFiscalRateioReceitaForm">';
											html = html + '<html:option value="">Selecione...</html:option>';

											if (data != null) {

												for (i = 0; i < data.dados.length; i++) {
													html = html + '<html:option value="'+data.dados[i].data+'">' + data.dados[i].value + '</html:option>'
												}

											}

											html = html + '</html:select>'

											$('#formaPagamento').html(html);

											gerenciarCamposTipoFinanceiro();
										},
										error : function(XMLHttpRequest, textStatus, errorThrown) {
											alert(textStatus);
										}
									});
						}

						gerenciarCamposTipoFinanceiro();
						gerenciarObrigatoriedadeNumerosReciboAndNota(null);
						gerenciarCamposObrigatoriosItemAdicionar();
						gerenciarCamposSafraSetorCultura();
					});

	function gerenciarCamposSafraSetorCultura() {
		if ($('#tipo').val() == '') {
			$("#safra").removeClass("obrigatorio");
			$("#cultura").removeClass("obrigatorio");
			$("#setor").removeClass("obrigatorio");

			$("#safra").addClass("bloqueado");
			$("#cultura").addClass("bloqueado");
			$("#setor").addClass("bloqueado");

			$('#safra').val(null);
			$('#safraSelecionada').val(null);
			$('#setor').val(null);
			$('#setorSelecionado').val(null);
			$('#cultura').val(null);
			$('#variedade').val(null);
			$('#culturaSelecionada').val(null);

		} else if ($('#tipo').val() == 'Safra/Setor') {
			$("#safra").addClass("obrigatorio");
			$("#setor").addClass("obrigatorio");

			$("#safra").removeClass("bloqueado");
			$("#setor").removeClass("bloqueado");

			$("#cultura").removeClass("obrigatorio");
			$("#cultura").addClass("bloqueado");

			$("#safra").focus();
		} else if ($('#tipo').val() == 'Tudo') {
			$("#safra").addClass("obrigatorio");

			$("#safra").removeClass("bloqueado");
 
			$("#setor").removeClass("obrigatorio");
			$("#setor").addClass("bloqueado");

			$("#cultura").removeClass("obrigatorio");
			$("#cultura").addClass("bloqueado");

			$("#safra").focus();
		} else if ($('#tipo').val() == 'Cultura') {
			$("#safra").addClass("obrigatorio");
			$("#cultura").addClass("obrigatorio");

			$("#safra").removeClass("bloqueado");
			$("#cultura").removeClass("bloqueado");

			$("#setor").removeClass("obrigatorio");
			$("#setor").addClass("bloqueado");

			$("#safra").focus();
		}

		recarregarObrigatorios();
	}

	function gerenciarCamposTipoFinanceiro() {
		if ($('#tipoPagamento').val() != null && $('#tipoPagamento').val() != '') {

			// Adicionando os campos
			$('#formaPagamento').removeClass("bloqueado");
			$('#vencimentoPrimeiraParcela').removeClass("bloqueado");

			if ($('#tipoPagamento').val() == 'À vista') {

				$("#lblVencimentoPrimeiraParcela").text('Data Quitação');

				// Removendo os campos				
				$('#valorEntrada').addClass("bloqueado");
				$('#valorEntrada').val(null);
				$('#formaPagamentoEntrada').addClass("bloqueado");
				$('#valoformaPagamentoEntradaEntrada').val(null);
				$("#valorEntrada").removeClass("obrigatorio");
				$("#formaPagamentoEntrada").removeClass("obrigatorio");
			} else if ($('#tipoPagamento').val() == 'Parcelado') {

				$("#lblVencimentoPrimeiraParcela").text('Venc.1ª Parcela');

				$('#valorEntrada').removeClass("bloqueado");
				$('#formaPagamentoEntrada').removeClass("bloqueado");
			}
		} else {
			$('#formaPagamento').addClass("bloqueado");
			$('#formaPagamento').val(null);
			$('#vencimentoPrimeiraParcela').addClass("bloqueado");
			$('#vencimentoPrimeiraParcela').val(null);
			$('#valorEntrada').addClass("bloqueado");
			$('#valorEntrada').val(null);
			$('#formaPagamentoEntrada').addClass("bloqueado");
			$('#formaPagamentoEntrada').val(null);
		}

		recarregarObrigatorios();
	}

	function gerenciarCamposObrigatoriosItemAdicionar() {
		if ($('#centroCusto').val() == null || $('#centroCusto').val() == '') {

			$("#centroCusto").removeClass("obrigatorio");
			$("#descricao").removeClass("obrigatorio");
			$("#valor").removeClass("obrigatorio");
			$("#tipo").removeClass("obrigatorio");

			$("#safra").removeClass("obrigatorio");
			$("#cultura").removeClass("obrigatorio");
			$("#setor").removeClass("obrigatorio");
		} else {

			$("#centroCusto").addClass("obrigatorio");
			$("#descricao").addClass("obrigatorio");
			$("#valor").addClass("obrigatorio");
			$("#tipo").addClass("obrigatorio");

			$("#safra").addClass("obrigatorio");
			$("#cultura").addClass("obrigatorio");
			$("#setor").addClass("obrigatorio");
		}
		recarregarObrigatorios();
	}
</script>