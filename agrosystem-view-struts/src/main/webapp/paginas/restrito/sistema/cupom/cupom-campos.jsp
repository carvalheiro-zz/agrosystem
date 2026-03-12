<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<jsp:include page="../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<div class="row">
	<!-- Define o tamanho geral da cupom -->
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel sombra">

			<!-- INICIO FORMULARIO -->
			<div class="panel-body">
				<logic:present property="cupom.id" name="cupomForm">
					<div class="row">
						<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px;">
							<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
								<i class="fa fa-user-plus"></i>
								&nbsp;${cupomForm.cupom.nomeUsuarioCriacao }
							</div>
						</div>
						<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
							<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
								<i class="fa fa-calendar-plus-o"></i>
								&nbsp;${cupomForm.cupom.dataOcorrenciaCriacao}
							</div>
						</div>
						<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
							<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
								<i class="fa fa-refresh"></i>
								&nbsp;${cupomForm.cupom.nomeUsuarioAlteracao}
							</div>
						</div>
						<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-left: 0px;">
							<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
								<i class="fa fa-calendar-o"></i>
								&nbsp;${cupomForm.cupom.dataOcorrenciaAlteracao}
							</div>
						</div>
					</div>
				</logic:present>
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_cupom" action="restrito/sistema/cupom" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-4" style="padding-right: 3px;">
									<div class="panel" style="border-color: #909090;">
										<div class="panel-heading cor-sistema" style="font-size: 30px; padding: 6px 0px 0px 15px; color: white;">
											<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
													<a data-toggle="collapse" style="color: #ffbf5d; float: right; line-height: 1; margin-right: 12px; margin-top: 5px;" id="modalInfo">
														<i class="fa fa-question"></i>
													</a>
													<i class="fa fa-file-o"></i>
													<label>Cupom</label>
												</div>
											</div>

										</div>

										<div class="panel-body">

											<div class="row">

												<div class="form-group text-right col-xs-12 col-sm-12 col-md-6 col-lg-6">
													<html:text styleClass="form-control text-center data input-lg" style="font-size: 25px;" styleId="data" property="cupom.data" name="cupomForm" />
													<i style="margin-top: -40px; float: right; margin-right: 8px; font-size: 30px;" class="fa fa-calendar"></i>
												</div>
												<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
													<html:text styleClass="form-control numero input-lg" style="font-size: 25px;" styleId="numero" property="cupom.numero" name="cupomForm" />
												</div>

											</div>

											<div class="row">
												<div class="form-group col-xs-12 col-sm-6 col-md-8 col-lg-8">
													<label>Nome Fornecedor</label>
													<a href="#" id="modalCadastrarFornecedor">
														<i style="text-shadow: 1px 0px 0px white, -1px 0px 0px white, 0px 1px 0px white, 0px -1px 0px white; color: green; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-plus"></i>
													</a>
													<html:text styleClass="form-control input-sm" styleId="fornecedor" property="cupom.fornecedor.nome" name="cupomForm" />
													<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
													<html:hidden styleId="fornecedorSelecionado" property="cupom.fornecedor.id" name="cupomForm" />
												</div>
												<div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-4">
													<label>Tel Fornecedor</label>
													<html:text styleClass="form-control input-sm bloqueado" styleId="telefoneFornecedor" property="cupom.fornecedor.telefone" name="cupomForm" />
												</div>
												<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
													<label>Observação Fornecedor</label>
													<html:text styleClass="form-control input-sm bloqueado" styleId="observacaoFornecedor" property="cupom.fornecedor.observacao" name="cupomForm" />
												</div>
											</div>

											<div class="row">
												<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
													<div class="panel panel-success" style="border-color: #909090; margin-top: 15px; margin-bottom: 0px;">
														<div class="panel-heading">
															<label>Item do Cupom</label>
														</div>
														<div class="panel-body">

															<div class="row">
																<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
																	<label>Produto</label>
																	<a href="#" id="modalCadastrarProduto">
																		<i style="text-shadow: 1px 0px 0px white, -1px 0px 0px white, 0px 1px 0px white, 0px -1px 0px white; color: green; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-plus"></i>
																	</a>
																	<html:text styleClass="form-control input-sm itemAdicionar" styleId="produto" property="itemAdicionar.produto.nomeCompleto" name="cupomForm" />
																	<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
																	<html:hidden styleId="produtoSelecionado" property="itemAdicionar.produto.id" name="cupomForm" />
																</div>
																<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<label>Un. Med.</label>
																	<html:text styleClass="form-control input-sm itemAdicionar bloqueado" styleId="unidadeMedida" property="itemAdicionar.produto.unidadeMedida.nome" name="cupomForm" />
																</div>
																<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<label>R$ Custo</label>
																	<html:text styleClass="form-control input-sm itemAdicionar" styleId="precoCusto" property="itemAdicionar.precoCusto" name="cupomForm" />
																</div>
																<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
																	<label>Quantidade</label>
																	<html:text styleClass="form-control input-sm itemAdicionar " styleId="quantidade" property="itemAdicionar.quantidade" name="cupomForm" />
																</div>
																<div class="text-right col-xs-12 col-sm-12 col-md-12 col-lg-12">
																	<button class="btn btn-success" type="button" id="btnAdicionarProduto" style="background-color: #407140;">
																		<i class="fa fa-plus"></i>
																		ADD
																	</button>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>

										</div>
										<div class="panel-footer">
											<div class="row">
												<!-- BOTOES -->
												<logic:notPresent property="cupom.id" name="cupomForm">
													<logic:equal name="cupomForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.cupom.inserir)">
														<div class="form-group col-xs-12 col-sm-3 col-md-4 col-lg-4" style="margin-bottom: 0px;">
															<button type="submit" id="inserir" class="btn btn-success btn-sm cor-sistema btn-block">
																<i class="fa fa-save"></i>
																Inserir
															</button>
														</div>
													</logic:equal>
												</logic:notPresent>
												<logic:present property="cupom.id" name="cupomForm">
													<logic:equal name="cupomForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.cupom.alterar)">
														<div class="form-group col-xs-12 col-sm-3 col-md-4 col-lg-4" style="margin-bottom: 0px;">
															<button type="submit" id="alterar" class="btn btn-success btn-sm cor-sistema btn-block">
																<i class="fa fa-edit"></i>
																Alterar
															</button>
														</div>
													</logic:equal>
												</logic:present>

												<div class="form-group ol-xs-12 col-sm-3 col-md-4 col-lg-4" style="margin-bottom: 0px;">
													<button type="button" id="limpar" class="btn btn-success btn-sm cor-sistema btn-block">
														<i class="glyphicon glyphicon-erase"></i>
														Limpar
													</button>
												</div>

												<logic:equal name="cupomForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.cupom.filtrar)">
													<div class="form-group col-xs-12 col-sm-3 col-md-4 col-lg-4" style="margin-bottom: 0px;">
														<button type="button" id="listagem" class="btn btn-success btn-sm cor-sistema btn-block">
															<i class="fa fa-search"></i>
															Listagem
														</button>
													</div>
												</logic:equal>

											</div>
										</div>
									</div>
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-8" style="padding-left: 3px;" id="id-itens">
									<jsp:include page="ajax/itens-tabela.jsp"></jsp:include>
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
	$(document).ready(
			function() {
				/* Foco inicial */
				if ($('#numero').val() != null && $('#numero').val() != '') {

					if ($('#produto').val() == null || $('#produto').val() == '') {
						$("#produto").focus();
					} else {
						$("#precoCusto").focus();
					}
				} else {
					$("#numero").focus();
				}

				/* Setando NOTNULL nos campos*/
				$("#numero").addClass("obrigatorio");
				$("#data").addClass("obrigatorio");
				$("#fornecedor").addClass("obrigatorio");

				/* Setando os tamanhos maximos dos campos baseando-se no PO*/
				$("#numero").prop("maxlength", 20);
				$("#numeroRecibo").prop("maxlength", 20);
				$("#precoCusto").prop("maxlength", 26);
				$("#quantidade").prop("maxlength", 26);
				$("#observacaoFornecedor").prop("maxlength", 255);
				$("#enderecoFornecedor").prop("maxlength", 100);
				
				$("#produto").prop("maxlength", 100);

				/* Setando os placeholder dos campos*/
				$("#numero").prop("placeholder", "Nº Cupom");
				$("#fornecedor").prop("placeholder", "Fornecedor");
				$("#produto").prop("placeholder", "Produto");

				$('#modalInfo').click(
						function() {
							BootstrapDialog.show({
								size : BootstrapDialog.SIZE_LARGE,
								title : 'Informativo',
								message : '<div class="row">' + '<div class="col-xs-12">'
										+ '<p style="margin-bottom: 0px;font-weight: bold;">Os itens lançados nos Cupons serão contabilidazados no ESTOQUE.</p>' + '</div>' + '</div>',
								closable : true,
								type : BootstrapDialog.TYPE_SUCCESS,
								buttons : [ {
									label : 'Obrigado',
									action : function(dialogRef) {

										dialogRef.close();
									}
								} ]
							});

						});

				$('#fornecedor').keyup(function() {
					if ($('#fornecedor').val() == null || $('#fornecedor').val() == '') {
						$('#fornecedorSelecionado').val(null);
						$('#telefoneFornecedor').val(null);
						$('#observacaoFornecedor').val(null);
					}
				});
				$('#fornecedor').autocomplete({
					minChars : 2,
					noCache : true,
					paramName : 'cupom.fornecedor.nome',
					/* params : {
						'saidaGrao.safra.id' : function() {
							return $('#safraSelecionada').val();
						}
					}, */
					serviceUrl : '${contextPath}/restrito/sistema/cupom.src?method=selecionarFornecedorAutoComplete',
					onSelect : function(suggestion) {
						$('#fornecedorSelecionado').val(suggestion.data);
						$('#telefoneFornecedor').val(suggestion.telefone);
						$('#observacaoFornecedor').val(suggestion.observacao);

						$("#produto").focus();
					},
					/* formatResult : function(suggestion, currentValue) {
						var valorExibir = suggestion.value + " - " + suggestion.variedade;
						return valorExibir;
					}, */
					onSearchComplete : function(query, suggestions) {
						if (suggestions == null || suggestions == '') {
							$('#fornecedorSelecionado').val(null);
							$('#telefoneFornecedor').val(null);
							$('#observacaoFornecedor').val(null);
						}
					}
				});

				$('#produto').keyup(function() {
					if ($('#produto').val() == '') {
						$('#produtoSelecionado').val(null);
						$('#unidadeMedida').val(null);
					}
				});
				$('#produto').autocomplete({
					minChars : 2,
					noCache : true,
					paramName : 'produto.nomeCompleto',
					serviceUrl : '${contextPath}/restrito/sistema/produto.src?method=selecionarProdutoAutoComplete',
					onSelect : function(suggestion) {
						$('#produtoSelecionado').val(suggestion.data);

						$('#unidadeMedida').val(suggestion.unidadeMedida);

						$("#precoCusto").focus();
					},
					onSearchComplete : function(query, suggestions) {
						if (suggestions == null || suggestions == '') {
							$('#produtoSelecionado').val(null);
							$('#unidadeMedida').val(null);
						}
					}
				});

				$('#btnAdicionarProduto').click(function() {
					var theForm = $('form[name=cupomForm]');
					var params = theForm.serialize();
					var actionURL = theForm.prop('action') + '?method=adicionarItem';

					$.ajax({
						type : 'POST',
						url : actionURL,
						data : params,
						success : function(data, textStatus, XMLHttpRequest) {

							$('#id-itens').html(data);
							$('.itemAdicionar').val(null);
							$("#produto").focus();
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							alert(textStatus);
						}
					});
				});

				/* EVENTOS */
				// Desliga o auto-complete da pagina
				$("#form_cupom").prop("autocomplete", "off");

				$('#form_cupom').on('submit', function(e) {
					abrirModalProcessando();
				});

				$('#inserir').click(function() {
					executar('form_cupom', 'inserir');
				});

				$('#alterar').click(function() {
					executar('form_cupom', 'alterar');
				});

				$('#limpar').click(function() {
					abrirModalProcessando();
					executarComSubmit('form_cupom', 'limpar');
				});

				$('#listagem').click(function() {
					abrirModalProcessando();
					executarComSubmit('form_cupom', 'abrirListagem');
				});

				$('#modalCadastrarFornecedor').on('click', function() {

					var actionURL = '${contextPath}/restrito/sistema/fornecedorJuridico.src?method=abrirModal';

					$.ajax({
						type : 'POST',
						url : actionURL,
						success : function(data, textStatus, XMLHttpRequest) {

							BootstrapDialog.show({
								size : BootstrapDialog.SIZE_NORMAL,
								title : 'Cadastrar Fornecedor',
								message : $('<div id="id-modalFornecedor"></div>').append(data),
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
										var theForm = $('form[name=fornecedorJuridicoForm]');
										var params = theForm.serialize();
										var actionURL = theForm.prop('action') + '?method=inserirModal';

										$.ajax({
											type : 'POST',
											url : actionURL,
											data : params,
											success : function(data, textStatus, XMLHttpRequest) {
												$('#id-modalFornecedor').html(data);

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

										var actionURL = '${contextPath}/restrito/sistema/fornecedorJuridico.src?method=fecharModal';

										$.ajax({
											type : 'POST',
											url : actionURL,
											success : function(data, textStatus, XMLHttpRequest) {
												dialogRef.close();

												$("#fornecedor").focus();
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

				$('#modalCadastrarProduto').on('click', function() {

					var actionURL = '${contextPath}/restrito/sistema/produto.src?method=abrirModal';

					$.ajax({
						type : 'POST',
						url : actionURL,
						success : function(data, textStatus, XMLHttpRequest) {

							BootstrapDialog.show({
								size : BootstrapDialog.SIZE_NORMAL,
								title : 'Cadastrar Produto',
								message : $('<div id="id-modalProduto"></div>').append(data),
								type : BootstrapDialog.TYPE_SUCCESS, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
								closable : false, // <-- Default value is false
								draggable : true, // <-- Default value is false
								onshown : function(dialogRef) {
									/* Foco inicial */
									$("#tipoModal").focus();
								},
								buttons : [ {
									hotkey : 13,
									label : 'Inserir',
									cssClass : 'btn-success',
									action : function(dialogRef) {
										var theForm = $('form[name=produtoForm]');
										var params = theForm.serialize();
										var actionURL = theForm.prop('action') + '?method=inserirModal';

										$.ajax({
											type : 'POST',
											url : actionURL,
											data : params,
											success : function(data, textStatus, XMLHttpRequest) {
												$('#id-modalProduto').html(data);

												$("#tipoModal").focus();
											},
											error : function(XMLHttpRequest, textStatus, errorThrown) {
												alert(textStatus);
											}
										});
									}
								}, {
									label : 'Fechar',
									action : function(dialogRef) {

										var actionURL = '${contextPath}/restrito/sistema/produto.src?method=fecharModal';

										$.ajax({
											type : 'POST',
											url : actionURL,
											success : function(data, textStatus, XMLHttpRequest) {
												dialogRef.close();

												$("#produto").focus();
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
			});
</script>