<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<style>
.kv-avatar .file-preview-frame, .kv-avatar .file-preview-frame:hover {
	margin: 0;
	padding: 0;
	border: none;
	box-shadow: none;
	text-align: center;
}

.kv-avatar .file-input {
	display: table-cell;
	max-width: 220px;
}
</style>

<!-- UPLOAD DA IMAGEM -->
<link href="${contextPath}/assets/bootstrap/bootstrap-fileinput-master/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />
<script src="${contextPath}/assets/bootstrap/bootstrap-fileinput-master/js/plugins/canvas-to-blob.min.js" type="text/javascript"></script>
<script src="${contextPath}/assets/bootstrap/bootstrap-fileinput-master/js/fileinput.min.js" type="text/javascript"></script>
<script src="${contextPath}/assets/bootstrap/bootstrap-fileinput-master/js/fileinput_locale_pt-BR.js"></script>

<script src="${contextPath}/assets/bootstrap/bower_components/bootstrap-filestyle-1.2.1/src/bootstrap-filestyle.min.js"></script>

<!-- CAMPOS DE CADASTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -30px; margin-bottom: -15px;">
		<h1 class="page-header cabecalho_pagina">
			<i class="fa fa-user-plus" style="font-size: 26px;"></i>
			Fornecedor
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Cadastro
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<logic:present property="fornecedor.id" name="fornecedorForm">
	<div class="row">
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px;">
			<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-user-plus"></i>
				&nbsp;${fornecedorForm.fornecedor.nomeUsuarioCriacao }
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
			<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-calendar-plus-o"></i>
				&nbsp;${fornecedorForm.fornecedor.dataOcorrenciaCriacao}
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
			<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-refresh"></i>
				&nbsp;${fornecedorForm.fornecedor.nomeUsuarioAlteracao}
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-left: 0px;">
			<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-calendar-o"></i>
				&nbsp;${fornecedorForm.fornecedor.dataOcorrenciaAlteracao}
			</div>
		</div>
	</div>
</logic:present>

<jsp:include page="../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<div class="row">
	<!-- Define o tamanho geral da fornecedor -->
	<div class="col-md-offset-0 col-lg-offset-0 col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel sombra">

			<!-- INICIO FORMULARIO -->
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_fornecedor" action="restrito/admin/fornecedor" method="post" enctype="multipart/form-data">
							<html:hidden property="method" value="empty" />							
							<html:hidden styleId="imagem" property="fornecedor.imagem" name="fornecedorForm" />

							<ul class="nav nav-tabs" id="tabClienteMensalista">
								<li class="active">
									<a data-toggle="tab" href="#dados">Dados do Fornecedor</a>
								</li>
								<li>
									<a data-toggle="tab" href="#foto">Foto</a>
								</li>
								<li>
									<a data-toggle="tab" href="#anexo">Documentos</a>
								</li>
							</ul>
							<div class="tab-content">
								<div id="dados" class="tab-pane fade in active" style="margin-top: 20px;">
									<div class="row">
										<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<jsp:include page="aba/dados_aba.jsp"></jsp:include>
										</div>
									</div>
								</div>
								<div id="foto" class="tab-pane fade" style="margin-top: 20px;">
									<div class="row">
										<div class="form-group text-center col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<div id="kv-avatar-errors" class="center-block" style="width: 800px; display: none"></div>
										</div>
										<div class="form-group col-lg-offset-4 col-md-offset-4 col-xs-12 col-sm-12 col-md-8 col-lg-8">
											<div class="kv-avatar center-block">
												<input id="foto1" name="file" type="file" class="file-loading">
											</div>
										</div>
									</div>
								</div>
								<div id="anexo" class="tab-pane fade" style="margin-top: 20px;">
									<div class="row">
										<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<div class="alert alert-success" role="alert" style="height: 172px">
												<div class="row">
													<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
														<label>Descrição</label>
														<html:text styleClass="form-control input-sm documentoAnexo1" styleId="descricaoDocumento1" property="fornecedor.documento1.descricao" name="fornecedorForm" />
														<html:hidden styleClass="documentoAnexo1" styleId="idDocumento1" property="fornecedor.documento1.id" name="fornecedorForm" />
													</div>


													<!-- SE TIVER ANEXO SALVO -->
													<div class="form-group col-xs-12 col-sm-12 col-md-8 col-lg-9 exibirAnexado1">
														<html:text styleClass="form-control input-sm documentoAnexo1 bloqueado" styleId="anexoDocumento1" property="fornecedor.documento1.anexo" name="fornecedorForm" />
													</div>
													<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-3 text-right exibirAnexado1">
														<a style="padding: 0px 6px; font-size: 18px;"
															href="${contextPath}/temp/${fornecedorForm.fornecedor.documento1.prefixoAnexo}/${fornecedorForm.fornecedor.documento1.anexo}" target="_blank"
															class="btn btn-success btn-xs" title="Visualizar">
															<i class="fa fa-eye"></i>
														</a>
														<button style="padding: 0px 6px; font-size: 18px;" type="button" id="removerAnexo11" class="btn btn-danger btn-xs removerAnexo1" title="Remover">
															<i class="fa fa-trash"></i>
														</button>
													</div>

													<!-- ANEXOS UPLOAD -->
													<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12 exibirAnexar1">
														<html:file styleClass="form-control input-sm anexos" styleId="anexo1File" property="anexo1File" name="fornecedorForm" />
														<button type="button" id="removerAnexo12" class="btn btn-success btn-sm cor-sistema removerAnexo1">
															<i class="fa fa-close"></i>
															Limpar Arquivo Anexo
														</button>
													</div>
												</div>
											</div>
										</div>


										<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<div class="alert alert-success" role="alert" style="height: 172px">
												<div class="row">
													<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
														<label>Descrição</label>
														<html:text styleClass="form-control input-sm documentoAnexo2" styleId="descricaoDocumento2" property="fornecedor.documento2.descricao" name="fornecedorForm" />
														<html:hidden styleClass="documentoAnexo2" styleId="idDocumento2" property="fornecedor.documento2.id" name="fornecedorForm" />
													</div>


													<!-- SE TIVER ANEXO SALVO -->
													<div class="form-group col-xs-12 col-sm-12 col-md-8 col-lg-9 exibirAnexado2">
														<html:text styleClass="form-control input-sm documentoAnexo2 bloqueado" styleId="anexoDocumento2" property="fornecedor.documento2.anexo" name="fornecedorForm" />
													</div>
													<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-3 text-right exibirAnexado2">
														<a style="padding: 0px 6px; font-size: 18px;"
															href="${contextPath}/temp/${fornecedorForm.fornecedor.documento2.prefixoAnexo}/${fornecedorForm.fornecedor.documento2.anexo}" target="_blank"
															class="btn btn-success btn-xs" title="Visualizar">
															<i class="fa fa-eye"></i>
														</a>
														<button style="padding: 0px 6px; font-size: 18px;" type="button" id="removerAnexo21" class="btn btn-danger btn-xs removerAnexo2" title="Remover">
															<i class="fa fa-trash"></i>
														</button>
													</div>

													<!-- ANEXOS UPLOAD -->
													<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12 exibirAnexar2">
														<html:file styleClass="form-control input-sm anexos" styleId="anexo2File" property="anexo2File" name="fornecedorForm" />
														<button type="button" id="removerAnexo22" class="btn btn-success btn-sm cor-sistema removerAnexo2">
															<i class="fa fa-close"></i>
															Limpar Arquivo Anexo
														</button>
													</div>
												</div>
											</div>
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<div class="alert alert-success" role="alert" style="height: 172px">
												<div class="row">
													<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
														<label>Descrição</label>
														<html:text styleClass="form-control input-sm documentoAnexo3" styleId="descricaoDocumento3" property="fornecedor.documento3.descricao" name="fornecedorForm" />
														<html:hidden styleClass="documentoAnexo3" styleId="idDocumento3" property="fornecedor.documento3.id" name="fornecedorForm" />
													</div>


													<!-- SE TIVER ANEXO SALVO -->
													<div class="form-group col-xs-12 col-sm-12 col-md-8 col-lg-9 exibirAnexado3">
														<html:text styleClass="form-control input-sm documentoAnexo3 bloqueado" styleId="anexoDocumento3" property="fornecedor.documento3.anexo" name="fornecedorForm" />
													</div>
													<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-3 text-right exibirAnexado3">
														<a style="padding: 0px 6px; font-size: 18px;"
															href="${contextPath}/temp/${fornecedorForm.fornecedor.documento3.prefixoAnexo}/${fornecedorForm.fornecedor.documento3.anexo}" target="_blank"
															class="btn btn-success btn-xs" title="Visualizar">
															<i class="fa fa-eye"></i>
														</a>
														<button style="padding: 0px 6px; font-size: 18px;" type="button" id="removerAnexo31" class="btn btn-danger btn-xs removerAnexo3" title="Remover">
															<i class="fa fa-trash"></i>
														</button>
													</div>

													<!-- ANEXOS UPLOAD -->
													<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12 exibirAnexar3">
														<html:file styleClass="form-control input-sm anexos" styleId="anexo3File" property="anexo3File" name="fornecedorForm" />
														<button type="button" id="removerAnexo32" class="btn btn-success btn-sm cor-sistema removerAnexo3">
															<i class="fa fa-close"></i>
															Limpar Arquivo Anexo
														</button>
													</div>
												</div>
											</div>
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<div class="alert alert-success" role="alert" style="height: 172px">
												<div class="row">
													<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
														<label>Descrição</label>
														<html:text styleClass="form-control input-sm documentoAnexo4" styleId="descricaoDocumento4" property="fornecedor.documento4.descricao" name="fornecedorForm" />
														<html:hidden styleClass="documentoAnexo4" styleId="idDocumento4" property="fornecedor.documento4.id" name="fornecedorForm" />
													</div>


													<!-- SE TIVER ANEXO SALVO -->
													<div class="form-group col-xs-12 col-sm-12 col-md-8 col-lg-9 exibirAnexado4">
														<html:text styleClass="form-control input-sm documentoAnexo4 bloqueado" styleId="anexoDocumento4" property="fornecedor.documento4.anexo" name="fornecedorForm" />
													</div>
													<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-3 text-right exibirAnexado4">
														<a style="padding: 0px 6px; font-size: 18px;"
															href="${contextPath}/temp/${fornecedorForm.fornecedor.documento4.prefixoAnexo}/${fornecedorForm.fornecedor.documento4.anexo}" target="_blank"
															class="btn btn-success btn-xs" title="Visualizar">
															<i class="fa fa-eye"></i>
														</a>
														<button style="padding: 0px 6px; font-size: 18px;" type="button" id="removerAnexo41" class="btn btn-danger btn-xs removerAnexo4" title="Remover">
															<i class="fa fa-trash"></i>
														</button>
													</div>

													<!-- ANEXOS UPLOAD -->
													<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12 exibirAnexar4">
														<html:file styleClass="form-control input-sm anexos" styleId="anexo4File" property="anexo4File" name="fornecedorForm" />
														<button type="button" id="removerAnexo42" class="btn btn-success btn-sm cor-sistema removerAnexo4">
															<i class="fa fa-close"></i>
															Limpar Arquivo Anexo
														</button>
													</div>
												</div>
											</div>
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
						$("#juridico").css('display', 'none');
						$("#fisico").css('display', 'none');

						/* Foco inicial */
						$("#natureza").focus();

						/* Setando NOTNULL nos campos*/
						$(".nome").addClass("obrigatorio");
						$(".numeroDocumentoIdentificacao").addClass("obrigatorio");
						$(".ativo").addClass("obrigatorio");						

						/* Setando os tamanhos maximos dos campos baseando-se no PO*/
						$(".nome").attr("maxlength", 100);
						$(".numeroDocumentoIdentificacao").attr("maxlength", 18);
						$(".descricao").attr("maxlength", 1500);
						$(".email").attr("maxlength", 50);
						$(".logradouro").attr("maxlength", 100);
						$(".numero").attr("maxlength", 5);
						$(".bairro").attr("maxlength", 100);
						$(".cep").attr("maxlength", 12);
						$(".cidade").attr("maxlength", 50);
						$(".estado").attr("maxlength", 2);
						$(".complemento").attr("maxlength", 50);						

						/* Setando os placeholder dos campos*/
						$(".nome").attr("placeholder", "Nome");
						$(".descricao").attr("placeholder", "Descrição");
						$(".email").attr("placeholder", "E-mail");
						$(".logradouro").attr("placeholder", "Logradouro");
						$(".numero").attr("placeholder", "Nº");
						$(".bairro").attr("placeholder", "Bairro");
						$(".cep").attr("placeholder", "CEP");
						$(".cidade").attr("placeholder", "Cidade");
						$(".estado").attr("placeholder", "Estado");
						$(".complemento").attr("placeholder", "Complemento");

						$(".descricaoDocumento").attr("placeholder", "Identificação do Anexo");
						
						$("#emailsCC").attr("aria-describedby", "helpEmailsCC");

						/* EVENTOS */
						// Desliga o auto-complete da pagina
						$("#form_fornecedor").attr("autocomplete", "off");

						$('#form_fornecedor').on('submit', function(e) {
							abrirModalProcessando();
						});

						$('#inserir').click(function() {
							executar('form_fornecedor', 'inserir');
						});

						$('#alterar').click(function() {							
							executar('form_fornecedor', 'alterar');
						});

						$('#limpar').click(function() {
							abrirModalProcessando();
							executarComSubmit('form_fornecedor', 'limpar');
						});

						$('#filtrar').click(function() {
							abrirModalProcessando();
							executarComSubmit('form_fornecedor', 'abrirListagem');
						});

						$('.data').focusout(function() {
							if ($(this).val() != null && $(this).val() != '') {
								if (validarData($(this).val())) {
									$(this).css('border-color', '#999');
								} else {
									$(this).focus();
									$(this).css('border-color', '#a94442');
									$(this).val(null);
								}
							}
						});

						$('.numeroDocumentoIdentificacao').keyup(function() {
							if ($('#numeroDocumentoIdentificacao').val() == null || $('#numeroDocumentoIdentificacao').val() == '') {

							}
						});
						$('.numeroDocumentoIdentificacao').autocomplete({
							minChars : 2,
							noCache : true,
							paramName : 'fornecedor.numeroDocumentoIdentificacao',
							params : {
								'fornecedor.natureza' : function() {
									return $('#natureza').val();
								}
							},
							serviceUrl : '${contextPath}/restrito/admin/fornecedor.src?method=selecionarPessoaAutoComplete',
							onSelect : function(suggestion) {
								$('.numeroDocumentoIdentificacao').val(suggestion.data);

								executarComSubmit('form_fornecedor', 'buscarPessoaByNumeroDocumentoIdentificacao');
							},
							formatResult : function(suggestion, currentValue) {
								return formatResultAutoCompleteHightLigth(suggestion.nomeExibir, currentValue);
							},
							onSearchComplete : function(query, suggestions) {
								if (suggestions == null || suggestions == '') {

								}
							}
						});

						$('#natureza').change(function() {
							mascaraCPFCNPJ();
						});

						mascaraCPFCNPJ();

						function mascaraCPFCNPJ() {
							if ($('#natureza').val() == 'PF') {
								/* $("#numeroDocumentoIdentificacaoFisico").mask("000.000.000-00", {
									placeholder : "000.000.000-00",
									clearIfNotMatch : true
								}); */
								$("#natureza").val('PF');
								$("#fisico").css('display', 'block');
								$("#juridico").css('display', 'none');

								/* Setando NOTNULL nos campos*/
								$("#numeroDocumentoIdentificacaoFisico").addClass("obrigatorio");
								$("#nomeFisico").addClass("obrigatorio");
								$("#limiteCreditoFisico").addClass("obrigatorio");
								$("#emailFisico").addClass("obrigatorio");
								$("#telefoneCelularFisico").addClass("obrigatorio");
								//$("#sexo").addClass("obrigatorio");
								
								/* Tirando NOTNULL dos campos*/
								$("#numeroDocumentoIdentificacaoJuridico").removeClass("obrigatorio");
								$("#nomeJuridico").removeClass("obrigatorio");
								$("#limiteCreditoJuridico").removeClass("obrigatorio");
								$("#emailJuridico").removeClass("obrigatorio");
								$("#telefoneCelularJuridico").removeClass("obrigatorio");
								

								$("#info").css('display', 'none');
							} else if ($('#natureza').val() == 'PJ') {
								/* $("#numeroDocumentoIdentificacaoJuridico").mask("00.000.000/0000-00", {
									placeholder : "00.000.000/0000-00",
									clearIfNotMatch : true
								}); */
								$("#natureza").val('PJ');
								$("#juridico").css('display', 'block');
								$("#fisico").css('display', 'none');

								/* Setando NOTNULL nos campos*/
								$("#numeroDocumentoIdentificacaoJuridico").addClass("obrigatorio");
								$("#nomeJuridico").addClass("obrigatorio");
								$("#limiteCreditoJuridico").addClass("obrigatorio");
								$("#emailJuridico").addClass("obrigatorio");
								$("#telefoneCelularJuridico").addClass("obrigatorio");
								
								/* Tirando NOTNULL dos campos*/
								$("#numeroDocumentoIdentificacaoFisico").removeClass("obrigatorio");
								$("#nomeFisico").removeClass("obrigatorio");
								$("#limiteCreditoFisico").removeClass("obrigatorio");
								$("#emailFisico").removeClass("obrigatorio");
								$("#telefoneCelularFisico").removeClass("obrigatorio");
								//$("#sexo").removeClass("obrigatorio");
								

								$("#info").css('display', 'none');
							} else {

								$("#juridico").css('display', 'none');
								$("#fisico").css('display', 'none');

								$("#numeroDocumentoIdentificacao").unmask();
								$("#numeroDocumentoIdentificacao").attr("placeholder", "Por favor escolha o Natureza do Cliente");

								$("#info").css('display', 'block');
							}

							recarregarObrigatorios();
						}

						var pathTemp1 = 'remover_${fornecedorForm.fornecedor.imagem}';
						pathTemp1 = pathTemp1.replace(".", "_").replace("/", "_");
						$("#foto1")
								.fileinput(
										{
											overwriteInitial : true,
											maxFileSize : 5000,
											showClose : false,
											showCaption : false,
											browseLabel : 'Selecione',
											removeLabel : '',
											browseIcon : '<i class="glyphicon glyphicon-folder-open"></i>',
											removeIcon : '<i class="glyphicon glyphicon-remove"></i>',
											removeTitle : 'Cancela ou desfaz as alterações',
											removeClass : pathTemp1 + ' btn btn-default',
											elErrorContainer : '#kv-avatar-errors',
											msgErrorClass : 'alert alert-block alert-danger',
											initialPreview : '<img src="${contextPath}/temp/${fornecedorForm.fornecedor.prefixoImagem}/${fornecedorForm.fornecedor.imagem}" alt="Imagem" style="width:100%">',
											defaultPreviewContent : '<img src="${contextPath}/assets/images/propria/empty.png" alt="Imagem" style="width:100%">',
											layoutTemplates : {
												main2 : '{preview} {remove} {browse}'
											},
											allowedFileExtensions : [ "jpg", "png", "gif" ]
										}).on('filecleared', function(event, data) {
									$('#imagem').val(null);
								});

						$(".anexos").filestyle({
							input : true,
							buttonName : "btn-primary",
							size : "sm col-xs-12 col-sm-12 col-md-12 col-lg-12",
							badge : false,
							buttonText : "Anexo",
							placeholder : "Selecione o arquivo"
						});
						
						$('.removerAnexo1').click(function() {
							$("#anexo1File").filestyle('clear');
							
							$('.documentoAnexo1').val(null);
							gerenciarExibicaoAnexos();
						});
						$('.removerAnexo2').click(function() {
							$("#anexo2File").filestyle('clear');
							
							$('.documentoAnexo2').val(null);
							gerenciarExibicaoAnexos();
						});
						$('.removerAnexo3').click(function() {
							$("#anexo3File").filestyle('clear');
							
							$('.documentoAnexo3').val(null);
							gerenciarExibicaoAnexos();
						});
						$('.removerAnexo4').click(function() {
							$("#anexo4File").filestyle('clear');
							
							$('.documentoAnexo4').val(null);
							gerenciarExibicaoAnexos();
						});
												

						$('a[data-toggle="tab"]').on('hide.bs.tab', function(e) {
							var aba = $(this).attr('href');

							if (aba == '#anexo') {

								var campoVazio = false;

								if ($('#anexo1File').val() != null && $('#anexo1File').val() != '') {
									if ($('#descricaoDocumento1').val() == null || $('#descricaoDocumento1').val() == '') {

										$('#descricaoDocumento1').addClass("obrigatorio");

										campoVazio = true;

										$("#descricaoDocumento1").focus();
									}
								}

								if ($('#anexo2File').val() != null && $('#anexo2File').val() != '') {
									if ($('#descricaoDocumento2').val() == null || $('#descricaoDocumento2').val() == '') {

										$('#descricaoDocumento2').addClass("obrigatorio");

										campoVazio = true;

										$("#descricaoDocumento2").focus();
									}
								}

								if ($('#anexo3File').val() != null && $('#anexo3File').val() != '') {
									if ($('#descricaoDocumento3').val() == null || $('#descricaoDocumento3').val() == '') {

										$('#descricaoDocumento3').addClass("obrigatorio");

										campoVazio = true;

										$("#descricaoDocumento3").focus();
									}
								}

								if ($('#anexo4File').val() != null && $('#anexo4File').val() != '') {
									if ($('#descricaoDocumento4').val() == null || $('#descricaoDocumento4').val() == '') {

										$('#descricaoDocumento4').addClass("obrigatorio");

										campoVazio = true;

										$("#descricaoDocumento4").focus();
									}
								}

								recarregarObrigatorios();

								if (campoVazio) {
									return false;
								}

							}

						});

						gerenciarExibicaoAnexos();

					});
	

	function gerenciarExibicaoAnexos() {
		if ($('#idDocumento1').val() == null || $('#idDocumento1').val() == '') {
			$('.exibirAnexar1').css('display', 'block');
			$('.exibirAnexado1').css('display', 'none');
		} else {
			$('.exibirAnexar1').css('display', 'none');
			$('.exibirAnexado1').css('display', 'block');
		}

		if ($('#idDocumento2').val() == null || $('#idDocumento2').val() == '') {
			$('.exibirAnexar2').css('display', 'block');
			$('.exibirAnexado2').css('display', 'none');
		} else {
			$('.exibirAnexar2').css('display', 'none');
			$('.exibirAnexado2').css('display', 'block');
		}

		if ($('#idDocumento3').val() == null || $('#idDocumento3').val() == '') {
			$('.exibirAnexar3').css('display', 'block');
			$('.exibirAnexado3').css('display', 'none');
		} else {
			$('.exibirAnexar3').css('display', 'none');
			$('.exibirAnexado3').css('display', 'block');
		}

		if ($('#idDocumento4').val() == null || $('#idDocumento4').val() == '') {
			$('.exibirAnexar4').css('display', 'block');
			$('.exibirAnexado4').css('display', 'none');
		} else {
			$('.exibirAnexar4').css('display', 'none');
			$('.exibirAnexado4').css('display', 'block');
		}

	}
</script>