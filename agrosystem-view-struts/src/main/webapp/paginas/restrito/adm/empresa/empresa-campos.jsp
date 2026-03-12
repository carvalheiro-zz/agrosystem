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
			<i class="fa fa-graduation-cap" style="font-size: 26px;"></i>
			Cadastrar Empresas
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Cadastro das Empresas
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<logic:present property="empresa.id" name="empresaForm">
	<div class="row">
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px;">
			<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-user-plus"></i>
				&nbsp;${empresaForm.empresa.nomeUsuarioCriacao }
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
			<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-calendar-plus-o"></i>
				&nbsp;${empresaForm.empresa.dataOcorrenciaCriacao}
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
			<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-refresh"></i>
				&nbsp;${empresaForm.empresa.nomeUsuarioAlteracao}
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-left: 0px;">
			<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-calendar-o"></i>
				&nbsp;${empresaForm.empresa.dataOcorrenciaAlteracao}
			</div>
		</div>
	</div>
</logic:present>

<div class="row">
	<!-- Define o tamanho geral da empresa -->
	<div class="col-md-offset-0 col-lg-offset-0 col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel sombra">

			<!-- INICIO FORMULARIO -->
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_empresa" action="restrito/admin/empresa" method="post" enctype="multipart/form-data">
							<html:hidden property="method" value="empty" />
							<html:hidden styleId="imagem" property="empresa.imagem" name="empresaForm" />

							<ul class="nav nav-tabs">
								<li class="active">
									<a data-toggle="tab" href="#dados">Dados</a>
								</li>
								<li>
									<a data-toggle="tab" href="#foto">Logo</a>
								</li>
							</ul>
							<div class="tab-content">
								<div id="dados" class="tab-pane fade in active" style="margin-top: 20px;">
									<div class="row">
										<div class="form-group text-center col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<div id="kv-avatar-errors" class="center-block" style="width: 800px; display: none"></div>
										</div>
									</div>

									<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<label>Empresa Mãe</label>
										<logic:equal value="true" name="usuarioSessaoPOJO" property="isADM" scope="session">
											<html:select styleClass="form-control input-sm" styleId="empresa" property="empresa.empresa.id" name="empresaForm">
												<html:option value="">Selecione...</html:option>
												<html:optionsCollection name="empresaForm" property="comboEmpresas" label="label" value="value" />
											</html:select>
										</logic:equal>
										<logic:equal value="false" name="usuarioSessaoPOJO" property="isADM" scope="session">
											<html:text styleClass="form-control input-sm bloqueado" styleId="empresaView" property="empresa.empresa.razaoSocial" name="empresaForm" />
											<html:hidden styleId="empresa" property="empresa.empresa.id" name="empresaForm" />
										</logic:equal>
									</div>

									<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-5">
										<label>CNPJ</label>
										<html:text styleClass="form-control input-sm cnpj text-center" style="font-size: 24px;" styleId="cnpj" property="empresa.cnpj" name="empresaForm" />
									</div>

									<div class="form-group col-xs-12 col-sm-9 col-md-4 col-lg-3">
										<label>Email</label>
										<html:text styleClass="form-control input-sm" styleId="email" property="empresa.email" name="empresaForm" />
									</div>


									<div class="form-group col-xs-12 col-sm-12 col-md-8 col-lg-6">
										<label>Razão Social</label>
										<html:text styleClass="form-control input-sm" styleId="razaoSocial" property="empresa.razaoSocial" name="empresaForm" />
									</div>
									<div class="form-group col-xs-12 col-sm-12 col-md-8 col-lg-6">
										<label>Nome Fantasia</label>
										<html:text styleClass="form-control input-sm" styleId="nomeFantasia" property="empresa.nomeFantasia" name="empresaForm" />
									</div>

									<div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-3">
										<label>Contato 1</label>
										<html:text styleClass="form-control input-sm cel text-center" styleId="telefone1" property="empresa.telefone1" name="empresaForm" />
									</div>

									<div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-3">
										<label>Contato 2</label>
										<html:text styleClass="form-control input-sm cel text-center" styleId="telefone2" property="empresa.telefone2" name="empresaForm" />
									</div>

									<div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-3">
										<label>Contato 3</label>
										<html:text styleClass="form-control input-sm cel text-center" styleId="telefone3" property="empresa.telefone3" name="empresaForm" />
									</div>

									<div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-3">
										<label>Insc. Est.</label>
										<html:text styleClass="form-control input-sm text-center" styleId="inscricaoEstadual" property="empresa.inscricaoEstadual" name="empresaForm" />
									</div>

									<div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-3">
										<label>Insc. Mun.</label>
										<html:text styleClass="form-control input-sm text-center" styleId="inscricaoMunicipal" property="empresa.inscricaoMunicipal" name="empresaForm" />
									</div>

									<div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-3">
										<label>Nome Responsável</label>
										<html:text styleClass="form-control input-sm" styleId="nomeResponsavel" property="empresa.nomeResponsavel" name="empresaForm" />
									</div>

									<div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-3">
										<label>Cargo Responsavel</label>
										<html:text styleClass="form-control input-sm" styleId="cargoResponsavel" property="empresa.cargoResponsavel" name="empresaForm" />
									</div>

									<div class="form-group col-lg-offset-3 col-xs-12 col-sm-6 col-md-4 col-lg-3">
										<label>Email Responsavel</label>
										<html:text styleClass="form-control input-sm " styleId="emailResponsavel" property="empresa.emailResponsavel" name="empresaForm" />
									</div>

									<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-3">
										<label>Horário de Abertura</label>
										<html:text styleClass="form-control input-sm hora" styleId="horaAbertura" property="empresa.horaAbertura" name="empresaForm" />
									</div>

									<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-3">
										<label>Horário de Fechamento</label>
										<html:text styleClass="form-control input-sm hora" styleId="horaFechamento" property="empresa.horaFechamento" name="empresaForm" />
									</div>

									<div class="row">
										<div class="form-group col-xs-12 col-sm-9 col-md-5 col-lg-5">
											<label>Logradouro</label>
											<html:text styleClass="form-control input-sm" styleId="logradouro" property="empresa.logradouro" name="empresaForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-3 col-md-2 col-lg-2">
											<label>Numero</label>
											<html:text styleClass="form-control input-sm text-center" styleId="numeroEndereco" property="empresa.numeroEndereco" name="empresaForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-8 col-md-5 col-lg-3">
											<label>Bairro</label>
											<html:text styleClass="form-control input-sm" styleId="bairro" property="empresa.bairro" name="empresaForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-4 col-md-3 col-lg-2">
											<label>CEP</label>
											<html:text styleClass="form-control cep input-sm text-center" styleId="cep" property="empresa.cep" name="empresaForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-8 col-md-5 col-lg-5">
											<label>Cidade</label>
											<html:text styleClass="form-control input-sm" styleId="cidade" property="empresa.cidade" name="empresaForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-4 col-md-4 col-lg-2">
											<label>UF</label>
											<html:select styleClass="form-control input-sm" styleId="estado" property="empresa.estado" name="empresaForm">
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
											<html:text styleClass="form-control input-sm" styleId="complemento" property="empresa.complemento" name="empresaForm" />
										</div>
									</div>

									<div class="row">
										<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-3">
											<label>Código</label>
											<html:text styleClass="form-control input-sm text-center" styleId="codigo" property="empresa.codigo" name="empresaForm" />
										</div>
										<div class="form-group col-xs-12 col-sm-12 col-md-5 col-lg-3">
											<label>Sistema</label>
											<html:text styleClass="form-control input-sm" styleId="nomeSistema" property="empresa.nomeSistema" name="empresaForm" />
										</div>
										<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-3">
											<label>Versão Sistema</label>
											<html:select styleClass="form-control input-sm" styleId="versaoSistema" property="empresa.versaoSistema" name="empresaForm">
												<html:option value="">Selecione</html:option>
												<html:option value="CONVENCIONAL">CONVENCIONAL</html:option>
												<html:option value="CEAFS">CEAFS</html:option>												
											</html:select>
										</div>

										<div class="form-group col-xs-6 col-sm-6 col-md-5 col-lg-3">
											<label>Status</label>
											<input type="checkbox" id="ativoCheck" checked data-toggle="toggle" data-on="<i class='fa fa-check-square-o' style='font-size: 20px;'></i>"
												data-off="<i class='fa fa-square-o' style='font-size: 20px;'></i>" data-onstyle="primary" data-offstyle="danger" data-width="100%" data-size="mini">
											<html:hidden styleId="ativo" property="empresa.ativo" name="empresaForm" />
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

							<div class="row" style="margin-top: 15px;">
								<!-- BOTOES -->
								<logic:notPresent property="empresa.id" name="empresaForm">
									<logic:equal name="empresaForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.empresa.inserir)">
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-2">
											<button type="submit" id="inserir" class="btn btn-success btn-sm btn-block cor-sistema">
												<i class="fa fa-save"></i>
												Inserir
											</button>
										</div>
									</logic:equal>
								</logic:notPresent>
								<logic:present property="empresa.id" name="empresaForm">
									<logic:equal name="empresaForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.empresa.alterar)">
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

								<logic:equal name="empresaForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.empresa.filtrar)">
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
	$(document).ready(function() {
		/* Foco inicial */
		$("#email").focus();

		/* Setando NOTNULL nos campos*/
		$("#cnpj").addClass("obrigatorio");
		$("#nomeFantasia").addClass("obrigatorio");
		$("#razaoSocial").addClass("obrigatorio");
		$("#telefone1").addClass("obrigatorio");
		$("#nomeResponsavel").addClass("obrigatorio");
		$("#cargoResponsavel").addClass("obrigatorio");
		$("#emailResponsavel").addClass("obrigatorio");

		$("#logradouro").addClass("obrigatorio");
		$("#numeroEndereco").addClass("obrigatorio");
		$("#bairro").addClass("obrigatorio");
		$("#cep").addClass("obrigatorio");
		$("#cidade").addClass("obrigatorio");
		$("#estado").addClass("obrigatorio");

		$("#codigo").addClass("obrigatorio");
		$("#ativo").addClass("obrigatorio");
		$("#nomeSistema").addClass("obrigatorio");
		$("#versaoSistema").addClass("obrigatorio");
		$("#horaFechamento").addClass("obrigatorio");
		$("#horaAbertura").addClass("obrigatorio");

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#versaoSistema").attr("maxlength", 15);
		$("#nomeSistema").attr("maxlength", 20);
		$("#codigo").attr("maxlength", 10);
		$("#emailResponsavel").attr("maxlength", 70);
		$("#cargoResponsavel").attr("maxlength", 20);
		$("#nomeResponsavel").attr("maxlength", 70);
		$("#inscricaoMunicipal").attr("maxlength", 20);
		$("#inscricaoEstadual").attr("maxlength", 20);
		$("#nomeFantasia").attr("maxlength", 100);
		$("#cnpj").attr("maxlength", 18);
		$("#imagem").attr("maxlength", 200);
		$("#email").attr("maxlength", 50);
		$("#telefone3").attr("maxlength", 20);
		$("#telefone2").attr("maxlength", 20);
		$("#telefone1").attr("maxlength", 20);
		$("#razaoSocial").attr("maxlength", 100);
		$("#complemento").attr("maxlength", 50);
		$("#estado").attr("maxlength", 2);
		$("#cidade").attr("maxlength", 50);
		$("#cep").attr("maxlength", 12);
		$("#bairro").attr("maxlength", 100);
		$("#numeroEndereco").attr("maxlength", 5);
		$("#logradouro").attr("maxlength", 100);
		$("#horaAbertura").attr("maxlength", 30);
		$("#horaFechamento").attr("maxlength", 30);

		/* Setando os placeholder dos campos*/
		$("#horaAbertura").attr("placeholder", "Horário de Abertura");
		$("#horaFechamento").attr("placeholder", "Horário de Fechamento");
		$("#versaoSistema").attr("placeholder", "Versão");
		$("#nomeSistema").attr("placeholder", "Nome do Sistema");
		$("#codigo").attr("placeholder", "Código");
		$("#emailResponsavel").attr("placeholder", "Email Responsável");
		$("#cargoResponsavel").attr("placeholder", "Cargo Responsável");
		$("#nomeResponsavel").attr("placeholder", "Nome Responsável");
		$("#inscricaoMunicipal").attr("placeholder", "I.E");
		$("#inscricaoEstadual").attr("placeholder", "I.M");
		$("#nomeFantasia").attr("placeholder", "Fantasia");
		$("#cnpj").attr("placeholder", "CNPJ");
		$("#imagem").attr("placeholder", "Foto");
		$("#email").attr("placeholder", "Email");
		$("#telefone3").attr("placeholder", "Telefone 3");
		$("#telefone2").attr("placeholder", "Telefone 2");
		$("#telefone1").attr("placeholder", "Telefone 1");
		$("#razaoSocial").attr("placeholder", "Razão Social");
		$("#complemento").attr("placeholder", "Complemento");
		$("#estado").attr("placeholder", "Estado");
		$("#cidade").attr("placeholder", "Cidade");
		$("#cep").attr("placeholder", "C.E.P");
		$("#bairro").attr("placeholder", "Bairro");
		$("#numeroEndereco").attr("placeholder", "Nº");
		$("#logradouro").attr("placeholder", "Avenida/Rua/Viela");

		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_empresa").attr("autocomplete", "off");

		$('#form_empresa').on('submit', function(e) {
			abrirModalProcessando();
		});

		$('#inserir').click(function() {
			executar('form_empresa', 'inserir');
		});

		$('#alterar').click(function() {
			executar('form_empresa', 'alterar');
		});

		$('#limpar').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_empresa', 'limpar');
		});

		$('#filtrar').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_empresa', 'abrirListagem');
		});

		$('#ativoCheck').change(function() {
			$('#ativo').val($(this).prop('checked'));
		});

		function preencherCkecks() {
			if ($('#ativo').val() == 'false') {
				$('#ativoCheck').bootstrapToggle('off')
			} else {
				$('#ativoCheck').bootstrapToggle('on')
			}
		}
		preencherCkecks();

		var pathTemp1 = 'remover_${empresaForm.empresa.imagem}';
		pathTemp1 = pathTemp1.replace(".", "_").replace("/", "_");
		$("#foto1").fileinput({
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
			initialPreview : '<img src="${contextPath}/temp/${empresaForm.empresa.prefixoImagem}/${empresaForm.empresa.imagem}" alt="Imagem" style="width:100%">',
			defaultPreviewContent : '<img src="${contextPath}/assets/images/propria/empty.png" alt="Imagem" style="width:100%">',
			layoutTemplates : {
				main2 : '{preview} {remove} {browse}'
			},
			allowedFileExtensions : [ "jpg", "png", "gif" ]
		}).on('filecleared', function(event, data) {
			$('#imagem').val(null);
		});
		
	

	});
</script>