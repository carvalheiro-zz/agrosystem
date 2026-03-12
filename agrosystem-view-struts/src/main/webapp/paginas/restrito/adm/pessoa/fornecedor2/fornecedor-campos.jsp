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

<!-- CAMPOS DE CADASTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -30px; margin-bottom: -15px;">
		<h1 class="page-header cabecalho_pagina">
			<i class="fa fa-building-o" style="font-size: 26px;"></i>
			Fornecedor
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Cadastro
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

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

<div class="row">
	<!-- Define o tamanho geral da fornecedor -->
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel sombra">

			<!-- INICIO FORMULARIO -->
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_fornecedor" action="restrito/admin/fornecedor" method="post" enctype="multipart/form-data">
							<html:hidden property="method" value="empty" />
							<html:hidden styleId="imagem" property="fornecedor.pessoaJuridica.imagem" name="fornecedorForm" />

							<ul class="nav nav-tabs">
								<li class="active">
									<a data-toggle="tab" href="#dados">Dados</a>
								</li>
								<li>
									<a data-toggle="tab" href="#endereco">Endereço</a>
								</li>
								<li>
									<a data-toggle="tab" href="#foto">Foto</a>
								</li>
							</ul>
							<div class="tab-content">
								<div id="dados" class="tab-pane fade in active" style="margin-top: 20px;">
									<div class="row">
										<div class="form-group text-center col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<div id="kv-avatar-errors" class="center-block" style="width: 800px; display: none"></div>
										</div>
									</div>

									<div class="row">

										<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-4" style="display: none;">
											<label>Empresa</label>
											<logic:equal value="true" name="usuarioSessaoPOJO" property="isADM" scope="session">
												<html:select styleClass="form-control input-sm" styleId="empresa" property="fornecedor.empresa.id" name="fornecedorForm">
													<html:optionsCollection name="fornecedorForm" property="comboEmpresas" label="label" value="value" />
												</html:select>
											</logic:equal>
											<logic:equal value="false" name="usuarioSessaoPOJO" property="isADM" scope="session">
												<html:text styleClass="form-control input-sm bloqueado" styleId="empresaView" property="fornecedor.empresa.nomeFantasia" name="fornecedorForm" />
												<html:hidden styleId="empresa" property="fornecedor.empresa.id" name="fornecedorForm" />
											</logic:equal>
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<label>Razão Social</label>
											<html:text styleClass="form-control input-sm" styleId="razaoSocial" property="fornecedor.pessoaJuridica.razaoSocial" name="fornecedorForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<label>Nome Fantasia</label>
											<html:text styleClass="form-control input-sm" styleId="nomeFantasia" property="fornecedor.pessoaJuridica.nomeFantasia" name="fornecedorForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<label>CNPJ</label>
											<html:text styleClass="form-control input-sm cnpj text-center" style="font-size: 20px;" styleId="cnpj" property="fornecedor.pessoaJuridica.cnpj" name="fornecedorForm" />
											<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
											<html:hidden styleId="fornecedorSelecionado" property="fornecedor.pessoaJuridica.id" name="fornecedorForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<label>Inscrição Estadual</label>
											<html:text styleClass="form-control input-sm text-center" styleId="inscricaoEstadual" property="fornecedor.pessoaJuridica.inscricaoEstadual" name="fornecedorForm" />
										</div>



										<div class="form-group col-xs-12 col-sm-6 col-md-3 col-lg-2">
											<label>Fixo</label>
											<html:text styleClass="form-control input-sm text-center" styleId="telefone3" property="fornecedor.pessoaJuridica.telefone3" name="fornecedorForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-6 col-md-3 col-lg-2">
											<label>Celular</label>
											<html:text styleClass="form-control input-sm cel text-center" styleId="telefone1" property="fornecedor.pessoaJuridica.telefone1" name="fornecedorForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-9 col-md-5 col-lg-5">
											<label>Email</label>
											<html:text styleClass="form-control input-sm" styleId="email" property="fornecedor.pessoaJuridica.email" name="fornecedorForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<label>Representante</label>
											<html:text styleClass="form-control input-sm" styleId="representante" property="fornecedor.pessoaJuridica.representante" name="fornecedorForm" />
										</div>
										<div class="form-group col-xs-12 col-sm-6 col-md-3 col-lg-3">
											<label>Contato do Representante</label>
											<html:text styleClass="form-control input-sm cel text-center" styleId="telefoneRepresentante" property="fornecedor.pessoaJuridica.telefoneRepresentante" name="fornecedorForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-4">
											<label>Email do Representante</label>
											<html:text styleClass="form-control input-sm " styleId="emailRepresentante" property="fornecedor.pessoaJuridica.emailRepresentante" name="fornecedorForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-8 col-lg-8">
											<label>Observação</label>
											<h6 class="pull-right" id="count_observacao" style="margin-top: 0px; margin-bottom: 0px;"></h6>
											<html:textarea styleClass="form-control input-sm" styleId="observacao" property="fornecedor.pessoaJuridica.observacao" name="fornecedorForm" rows="2" />
										</div>

										<%-- <div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-3">
											<label>Celular 2</label>
											<html:text styleClass="form-control input-sm cel text-center" styleId="telefone2" property="fornecedor.pessoaJuridica.telefone2" name="fornecedorForm" />
										</div> --%>

										<%-- <div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-4">
											<label>Inscrição Municipal</label>
											<html:text styleClass="form-control input-sm" styleId="inscricaoMunicipal" property="fornecedor.pessoaJuridica.inscricaoMunicipal" name="fornecedorForm" />
										</div> --%>

									</div>
								</div>
								<div id="endereco" class="tab-pane fade" style="margin-top: 20px;">
									<div class="row">
										<div class="form-group col-xs-12 col-sm-9 col-md-5 col-lg-5">
											<label>Logradouro</label>
											<html:text styleClass="form-control input-sm" styleId="logradouro" property="fornecedor.pessoaJuridica.endereco.logradouro" name="fornecedorForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-3 col-md-2 col-lg-2">
											<label>Número</label>
											<html:text styleClass="form-control input-sm text-center" styleId="numero" property="fornecedor.pessoaJuridica.endereco.numero" name="fornecedorForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-8 col-md-5 col-lg-3">
											<label>Bairro</label>
											<html:text styleClass="form-control input-sm" styleId="bairro" property="fornecedor.pessoaJuridica.endereco.bairro" name="fornecedorForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-4 col-md-3 col-lg-2">
											<label>CEP</label>
											<html:text styleClass="form-control cep input-sm text-center" styleId="cep" property="fornecedor.pessoaJuridica.endereco.cep" name="fornecedorForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-8 col-md-5 col-lg-5">
											<label>Cidade</label>
											<html:text styleClass="form-control input-sm" styleId="cidade" property="fornecedor.pessoaJuridica.endereco.cidade" name="fornecedorForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-4 col-md-4 col-lg-2">
											<label>UF</label>
											<html:select styleClass="form-control input-sm" styleId="estado" property="fornecedor.pessoaJuridica.endereco.estado" name="fornecedorForm">
												<html:option value="">Selecione</html:option>
												<html:option value="AC">AC</html:option>
												<html:option value="AL">AL</html:option>
												<html:option value="AP">AP</html:option>
												<html:option value="AM">AM</html:option>
												<html:option value="BA">BA</html:option>
												<html:option value="CE">CE</html:option>
												<html:option value="DF">DF</html:option>
												<html:option value="ES">ES</html:option>
												<html:option value="GO">GO</html:option>
												<html:option value="MA">MA</html:option>
												<html:option value="MT">MT</html:option>
												<html:option value="MS">MS</html:option>
												<html:option value="MG">MG</html:option>
												<html:option value="PR">PR</html:option>
												<html:option value="PB">PB</html:option>
												<html:option value="PA">PA</html:option>
												<html:option value="PE">PE</html:option>
												<html:option value="PI">PI</html:option>
												<html:option value="RJ">RJ</html:option>
												<html:option value="RN">RN</html:option>
												<html:option value="RS">RS</html:option>
												<html:option value="RO">RO</html:option>
												<html:option value="RR">RR</html:option>
												<html:option value="SC">SC</html:option>
												<html:option value="SE">SE</html:option>
												<html:option value="SP">SP</html:option>
												<html:option value="TO">TO</html:option>
											</html:select>
										</div>

										<div class="form-group col-xs-12 col-sm-8 col-md-5 col-lg-5">
											<label>Complemento</label>
											<html:text styleClass="form-control input-sm" styleId="complemento" property="fornecedor.pessoaJuridica.endereco.complemento" name="fornecedorForm" />
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
							</div>

							<div class="row">
								<!-- BOTOES -->
								<logic:notPresent property="fornecedor.id" name="fornecedorForm">
									<logic:equal name="fornecedorForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.fornecedor.inserir)">
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-2">
											<button type="submit" id="inserir" class="btn btn-success btn-sm btn-block cor-sistema">
												<i class="fa fa-save"></i>
												Inserir
											</button>
										</div>
									</logic:equal>
								</logic:notPresent>
								<logic:present property="fornecedor.id" name="fornecedorForm">
									<logic:equal name="fornecedorForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.fornecedor.alterar)">
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-2">
											<button type="submit" id="alterar" class="btn btn-success btn-sm btn-block cor-sistema">
												<i class="fa fa-edit"></i>
												Alterar
											</button>
										</div>
									</logic:equal>
								</logic:present>


								<div class="col-xs-12 col-sm-12 col-md-4 col-lg-2">
									<button type="button" id="limpar" class="btn btn-success btn-sm btn-block cor-sistema">
										<i class="glyphicon glyphicon-erase"></i>
										Limpar
									</button>
								</div>

								<logic:equal name="fornecedorForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.fornecedor.filtrar)">
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-2">
										<button type="button" id="filtrar" class="btn btn-success btn-sm btn-block cor-sistema">
											<i class="fa fa-search"></i>
											Listagem
										</button>
									</div>
								</logic:equal>

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
						/* Foco inicial */
						$("#cnpj").focus();

						/* Setando NOTNULL nos campos*/
						/* Pessoa Física */
						$("#cnpj").addClass("obrigatorio");

						$("#empresa").addClass("obrigatorio");
						$("#empresaView").addClass("obrigatorio");

						/* Pessoa */
						$("#razaoSocial").addClass("obrigatorio");
						$("#email").addClass("obrigatorio");
						$("#telefone1").addClass("obrigatorio");

						/* Logradouro */
						$("#logradouro").addClass("obrigatorio");
						$("#numero").addClass("obrigatorio");
						$("#bairro").addClass("obrigatorio");
						$("#cep").addClass("obrigatorio");
						$("#cidade").addClass("obrigatorio");
						$("#estado").addClass("obrigatorio");

						/* Funcionário */
						$("#matricula").addClass("obrigatorio");
						$("#admissao").addClass("obrigatorio");

						/* Setando os tamanhos maximos dos campos baseando-se no PO*/
						/* Pessoa Física */
						$("#cnpj").attr("maxlength", 18);
						$("#rg").attr("maxlength", 15);
						$("#comissao").attr("maxlength", 5);
						$("#divisaoLucro").attr("maxlength", 5);

						/* Pessoa */
						$("#razaoSocial").attr("maxlength", 70);
						$("#email").attr("maxlength", 50);
						$("#emailRepresentante").attr("maxlength", 70);

						/* Logradouro */
						$("#logradouro").attr("maxlength", 100);
						$("#numero").attr("maxlength", 5);
						$("#bairro").attr("maxlength", 100);
						$("#cep").attr("maxlength", 12);
						$("#cidade").attr("maxlength", 50);
						$("#estado").attr("maxlength", 2);
						$("#complemento").attr("maxlength", 50);

						/* Setando os placeholder dos campos*/
						/* Pessoa Física */
						$("#cnpj").attr("placeholder", "CNPJ");
						$("#rg").attr("placeholder", "RG");
						$("#dataNascimento").attr("placeholder", "Data de Nascimento");

						/* Pessoa */
						$("#razaoSocial").attr("placeholder", "Nome");
						$("#email").attr("placeholder", "E-mail");
						$("#emailRepresentante").attr("placeholder", "E-mail do Representante");

						/* Endereço */
						$("#logradouro").attr("placeholder", "Rua / Avenida");
						$("#numero").attr("placeholder", "Nº");
						$("#bairro").attr("placeholder", "Bairro");
						$("#cep").attr("placeholder", "CEP");
						$("#cidade").attr("placeholder", "Cidade");
						$("#estado").attr("placeholder", "Estado");
						$("#complemento").attr("placeholder", "Complemento");

						/* Funcionário */
						$("#matricula").attr("placeholder", "Matrícula");

						/* EVENTOS */
						// Desliga o auto-complete da pagina
						$("#form_fornecedor").attr("autocomplete", "off");

						$('#form_fornecedor').on('submit', function(e) {
							abrirModalProcessando();
						});

						$('#inserir').click(function() {
							/* Usado para telas com ABAS */
							inserirComAba('form_fornecedor');
						});

						$('#alterar').click(function() {
							/* Usado para telas com ABAS */
							alterarComAba('form_fornecedor');
						});

						$('#limpar').click(function() {
							abrirModalProcessando();
							executarComSubmit('form_fornecedor', 'limpar');
						});

						$('#filtrar').click(function() {
							abrirModalProcessando();
							executarComSubmit('form_fornecedor', 'abrirListagem');
						});

						var count_observacao_max = 1000;
						$('#count_observacao').html(count_observacao_max + ' restantes');
						$('#observacao').keyup(function() {
							gerenciarContadorCaracteresObservacao();
						});
						function gerenciarContadorCaracteresObservacao() {
							var text_length = $('#observacao').val().length;
							var text_remaining = count_observacao_max - text_length;
							$('#count_observacao').html(text_remaining + ' restantes');
						}

						$('#ativoCheck').change(function() {
							$('#ativo').val($(this).prop('checked'));
						});

						function preencherChecks() {
							if ($('#ativo').val() == 'false') {
								$('#ativoCheck').bootstrapToggle('off')
							} else {
								$('#ativoCheck').bootstrapToggle('on')
							}
						}
						preencherChecks();

						var pathTemp1 = 'remover_${fornecedorForm.fornecedor.pessoaJuridica.imagem}';
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
											initialPreview : '<img src="${contextPath}/temp/${fornecedorForm.fornecedor.pessoaJuridica.prefixoImagem}/${fornecedorForm.fornecedor.pessoaJuridica.imagem}" alt="Imagem" style="width:100%">',
											defaultPreviewContent : '<img src="${contextPath}/assets/images/propria/empty.png" alt="Imagem" style="width:100%">',
											layoutTemplates : {
												main2 : '{preview} {remove} {browse}'
											},
											allowedFileExtensions : [ "jpg", "png", "gif" ]
										}).on('filecleared', function(event, data) {
									$('#imagem').val(null);
								});

						$('#cnpj').autocomplete({
							minChars : 2,
							paramName : 'fornecedor.pessoaJuridica.cnpj',
							serviceUrl : '${contextPath}/restrito/admin/fornecedor.src?method=selecionarPessoaJuridicaAutoComplete',
							onSelect : function(suggestion) {
								//alert('You selected: ' + suggestion.value + ', ' + suggestion.data);
								$('#fornecedorSelecionado').val(suggestion.data);

								executarComSubmit('form_fornecedor', 'buscarPessoaJuridicaByCNPJ');
							}
						});

						$('#empresa').change(function() {
							var actionURL = '${contextPath}/restrito/admin/fornecedor.src?method=selecionarEmpresa&fornecedor.empresa.id=' + $('#empresa').val();
							$.ajax({
								type : "POST",
								url : actionURL,
								success : function(data, textStatus, XMLHttpRequest) {
								},
								error : function(XMLHttpRequest, textStatus, errorThrown) {
									alert(textStatus);
								}
							}); 

							//executarComSubmit('form_fornecedor', 'selecionarEmpresa');
						});

					});

	String.prototype.formatMoney = function() {
		var v = this;

		if (v.indexOf('.') === -1) {
			v = v.replace(/([\d]+)/, "$1,00");
		}

		v = v.replace(/([\d]+)\.([\d]{1})$/, "$1,$20");
		v = v.replace(/([\d]+)\.([\d]{2})$/, "$1,$2");
		v = v.replace(/([\d]+)([\d]{3}),([\d]{2})$/, "$1.$2,$3");

		return v;
	};
</script>