<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!-- CAMPOS DE CADASTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -30px; margin-bottom: -15px;">
		<h1 class="page-header cabecalho_pagina">
			<i class="fa fa-user-plus fa-fw" style="font-size: 26px;"></i>
			Usuários do sistema
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Cadastro
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<logic:present property="usuario.id" name="usuarioForm">
	<div class="row">
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px;">
			<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-user-plus"></i>
				&nbsp;${usuarioForm.usuario.nomeUsuarioCriacao }
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
			<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-calendar-plus-o"></i>
				&nbsp;${usuarioForm.usuario.dataOcorrenciaCriacao}
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
			<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-refresh"></i>
				&nbsp;${usuarioForm.usuario.nomeUsuarioAlteracao}
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-left: 0px;">
			<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-calendar-o"></i>
				&nbsp;${usuarioForm.usuario.dataOcorrenciaAlteracao}
			</div>
		</div>
	</div>
</logic:present>
<div class="row">
	<!-- Define o tamanho geral da usuario -->
	<div class="col-xs-offset-0 col-sm-offset-0 col-md-offset-1 col-lg-offset-1 col-xs-12 col-sm-12 col-md-10 col-lg-10">
		<div class="panel sombra">

			<!-- INICIO FORMULARIO -->
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_usuario" action="restrito/admin/usuario" method="post">
							<html:hidden property="method" value="empty" />

							<!-- INICIO CAMPOS DE DADOS DO USUARIO -->
							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12" style="display: none;">
									<label>Empresa</label>
									<logic:equal value="true" name="usuarioSessaoPOJO" property="isADM" scope="session">
										<html:select styleClass="form-control input-sm" styleId="empresa" property="usuario.empresa.id" name="usuarioForm">
											<html:optionsCollection name="usuarioForm" property="comboEmpresas" label="label" value="value" />
										</html:select>
									</logic:equal>
									<logic:equal value="false" name="usuarioSessaoPOJO" property="isADM" scope="session">
										<html:text styleClass="form-control input-sm bloqueado" styleId="empresaView" property="usuario.empresa.nomeFantasia" name="usuarioForm" />
										<html:hidden styleId="empresa" property="usuario.empresa.id" name="usuarioForm" />
									</logic:equal>
								</div>

								<div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-4">
									<label>Usuário</label>
									<html:text styleClass="form-control input-sm autoCompleteOff" styleId="login" property="usuario.login" name="usuarioForm" />
								</div>
								
								<logic:notPresent property="usuario.id" name="usuarioForm">
									<div class="form-group col-xs-12 col-sm-6 col-md-8 col-lg-8">
										<label>E-mail</label>
										<html:text styleClass="form-control input-sm" styleId="email" property="usuario.email" name="usuarioForm" />
									</div>
								</logic:notPresent>
								<logic:present property="usuario.id" name="usuarioForm">
									<div class="form-group col-xs-12 col-sm-6 col-md-8 col-lg-8">
										<label>E-mail</label>
										<html:text styleClass="form-control input-sm bloqueado" styleId="email" property="usuario.email" name="usuarioForm" />
									</div>
								</logic:present>
								
								
								<div class="form-group col-xs-6 col-sm-6 col-md-6 col-lg-6">
									<label>Senha</label>
									<html:text styleClass="form-control input-sm autoCompleteOff senha" styleId="senha" property="usuario.senha" name="usuarioForm" />
								</div>
								<div class="form-group col-xs-6 col-sm-6 col-md-6 col-lg-6">
									<label>Perfil</label>
									<html:select styleClass="form-control input-sm " styleId="tipo" property="usuario.tipoUsuario.id" name="usuarioForm">
										<html:optionsCollection name="usuarioForm" property="comboTiposUsuario(${usuarioSessaoPOJO.isADM})" label="label" value="value" />
									</html:select>
								</div>

								
								<div class="form-group col-xs-6 col-sm-6 col-md-63 col-lg-3">
									<label>Status</label>
									<input type="checkbox" id="statusCheck" checked data-toggle="toggle" data-on="<i class='fa fa-check-square-o' style='font-size: 20px;'></i>" data-off="<i class='fa fa-square-o' style='font-size: 20px;'></i>" data-onstyle="primary"
										data-offstyle="danger" data-width="100%" data-size="mini">
									<html:hidden styleId="status" property="usuario.status" name="usuarioForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-3">
									<button type="button" id="atribuirTodas" class="btn btn-primary btn-sm btn-block cor-sistema" style="margin-top: 23px;">
										<i class="fa fa-check-square-o"></i>
										Todas
									</button>
								</div>
							</div>
							<%-- <div class="row">
								<div class="form-group col-lg-6 col-md-6 col-sm-6">
									<label>Status</label>
									<input type="checkbox" id="statusCheck${i}" checked data-toggle="toggle" data-on="<i class='fa fa-check-square-o' style='font-size: 20px;'></i>" data-off="<i class='fa fa-square-o' style='font-size: 20px;'></i>" data-onstyle="primary"
										data-offstyle="danger" data-width="100%" data-size="small">
									<html:hidden styleId="status" property="usuario.status" name="usuarioForm" />




									<div class="form-group input-sm">
										<label class="radio-inline">
											<html:radio property="usuario.status" styleId="statusAtivo" value="true">Ativo</html:radio>
										</label>
										<label class="radio-inline">
											<html:radio property="usuario.status" styleId="statusInativo" value="false">Inativo</html:radio>
										</label>
									</div>
								</div>
							</div> --%>

							<!-- INICIO CAMPOS DE DADOS DO USUARIO -->
							<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.usuario.ger_perm)">
								<jsp:include page="usuario_part/permissoes_campos_party.jsp" />
							</logic:equal>
							<logic:equal name="loginForm" value="false" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.usuario.ger_perm)">
								<div class="row" style="margin-top: 15px;">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<div class="alert alert-danger">
											<strong>Atenção!</strong>
											Usuário sem permissão para manipular permissões!
										</div>
									</div>
								</div>
							</logic:equal>
							<!-- TERMINO CAMPOS DE DADOS DO USUARIO -->

							<div class="row">
								<!-- BOTOES -->
								<%-- <logic:notPresent property="usuario.id" name="usuarioForm">
									<logic:equal name="usuarioForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.usuario.inserir)">
										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-2">
											<button type="submit" id="inserir" class="btn btn-success btn-sm btn-block cor-sistema">
												<i class="fa fa-save"></i>
												Inserir
											</button>
										</div>
									</logic:equal>
								</logic:notPresent> --%>
								<logic:present property="usuario.id" name="usuarioForm">
									<logic:equal name="usuarioForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.usuario.alterar)">
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

								<logic:equal name="usuarioForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.usuario.filtrar)">
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
		$("#nome").focus();

		/* Setando NOTNULL nos campos*/
		$("#email").addClass("obrigatorio");
		$("#login").addClass("obrigatorio");
		$("#senha").addClass("obrigatorio");
		$("#tipo").addClass("obrigatorio");
		$("#statusAtivo").addClass("obrigatorio");
		$("#statusInativo").addClass("obrigatorio");

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#email").attr("maxlength", 100);
		$("#login").attr("maxlength", 18);
		$("#senha").attr("maxlength", 50);

		/* Setando os placeholder dos campos*/
		$("#email").attr("placeholder", "Email");
		$("#login").attr("placeholder", "Username");
		$("#senha").attr("placeholder", "Senha");

		/* CONFIGURACAO */

		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_usuario").attr("autocomplete", "off");

		$('#form_usuario').on('submit', function(e) {
			abrirModalProcessando();
		});

		$('#inserir').click(function() {
			executar('form_usuario', 'inserir');
		});

		$('#alterar').click(function() {
			executar('form_usuario', 'alterar');
		});

		$('#limpar').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_usuario', 'limpar');
		});

		$('#filtrar').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_usuario', 'abrirListagem');
		});

		$('#tipo').change(function() {
			abrirModalProcessando();
			executarComSubmit('form_usuario', 'selecionarTipoUsuario');
		});

		$("#atribuirTodas").click(function() {
			var permissoesSelecionadas1 = [];
			var i = 0;

			$(".check-label").each(function(index, element) {
				permissoesSelecionadas1[i++] = $(this).prop("id");
				$(this).bootstrapSwitch('state', true)
			});

			$("#permissoesSelecionadas").val(permissoesSelecionadas1);
		});

		$('#statusCheck').change(function() {
			$('#status').val($(this).prop('checked'));
		});

		function preencherCkecks() {
			if ($('#status').val() == 'false') {
				$('#statusCheck').bootstrapToggle('off')
			} else {
				$('#statusCheck').bootstrapToggle('on')
			}
		}
		preencherCkecks();
	});
</script>