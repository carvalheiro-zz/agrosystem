<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!-- CAMPOS DE CADASTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -30px; margin-bottom: -15px;">
		<h1 class="page-header cabecalho_pagina">
			<i class="fa fa-object-ungroup fa-fw" style="font-size: 26px;"></i>
			Tipo de usuário
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Cadastro
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<logic:present property="tipoUsuario.id" name="tipoUsuarioForm">
	<div class="row">
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px;">
			<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-user-plus"></i>
				&nbsp;${tipoUsuarioForm.tipoUsuario.nomeUsuarioCriacao }
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
			<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-calendar-plus-o"></i>
				&nbsp;${tipoUsuarioForm.tipoUsuario.dataOcorrenciaCriacao}
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
			<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-refresh"></i>
				&nbsp;${tipoUsuarioForm.tipoUsuario.nomeUsuarioAlteracao}
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-left: 0px;">
			<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-calendar-o"></i>
				&nbsp;${tipoUsuarioForm.tipoUsuario.dataOcorrenciaAlteracao}
			</div>
		</div>
	</div>
</logic:present>
<div class="row">
	<!-- Define o tamanho geral da tipoUsuario -->
	<div class="col-xs-offset-0 col-sm-offset-0 col-md-offset-1 col-lg-offset-1 col-xs-12 col-sm-12 col-md-10 col-lg-10">
		<div class="panel sombra">

			<!-- INICIO FORMULARIO -->
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_tipoUsuario" action="restrito/admin/tipoUsuario" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12" style="display: none;">
									<label>Empresa</label>
									<logic:equal value="true" name="usuarioSessaoPOJO" property="isADM" scope="session">
										<html:select styleClass="form-control input-sm" styleId="empresa" property="tipoUsuario.empresa.id" name="tipoUsuarioForm">
											<html:optionsCollection name="tipoUsuarioForm" property="comboEmpresas" label="label" value="value" />
										</html:select>
									</logic:equal>
									<logic:equal value="false" name="usuarioSessaoPOJO" property="isADM" scope="session">
										<html:text styleClass="form-control input-sm bloqueado" styleId="empresaView" property="tipoUsuario.empresa.nomeFantasia" name="tipoUsuarioForm" />
										<html:hidden styleId="empresa" property="tipoUsuario.empresa.id" name="tipoUsuarioForm" />
									</logic:equal>
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-5 col-lg-5">
									<label>Nome</label>
									<html:text styleClass="form-control input-sm" styleId="nome" property="tipoUsuario.nome" name="tipoUsuarioForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-3">
									<button type="button" id="atribuirTodas" class="btn btn-primary btn-sm btn-block cor-sistema" style="margin-top: 23px;">
										<i class="fa fa-check-square-o"></i>
										Todas
									</button>
								</div>
							</div>

							<div class="row">
								<!-- BOTOES -->
								<logic:notPresent property="tipoUsuario.id" name="tipoUsuarioForm">
									<logic:equal name="tipoUsuarioForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.tipoUsuario.inserir)">
										<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-2">
											<button type="submit" id="inserir" class="btn btn-success btn-sm btn-block cor-sistema">
												<i class="fa fa-save"></i>
												Inserir
											</button>
										</div>
									</logic:equal>
								</logic:notPresent>
								<logic:present property="tipoUsuario.id" name="tipoUsuarioForm">
									<logic:equal name="tipoUsuarioForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.tipoUsuario.alterar)">
										<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-2">
											<button type="submit" id="alterar" class="btn btn-success btn-sm btn-block cor-sistema">
												<i class="fa fa-edit"></i>
												Alterar
											</button>
										</div>
									</logic:equal>
								</logic:present>


								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-2">
									<button type="button" id="limpar" class="btn btn-success btn-sm btn-block cor-sistema">
										<i class="glyphicon glyphicon-erase"></i>
										Limpar
									</button>
								</div>

								<logic:equal name="tipoUsuarioForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.tipoUsuario.filtrar)">
									<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-2">
										<button type="button" id="filtrar" class="btn btn-success btn-sm btn-block cor-sistema">
											<i class="fa fa-search"></i>
											Listagem
										</button>
									</div>
								</logic:equal>

							</div>

							<!-- INICIO CAMPOS DE DADOS DO USUARIO -->
							<logic:equal name="tipoUsuarioForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.tipoUsuario.ger_perm)">
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<jsp:include page="usuario_part/permissoes_campos_party.jsp" />
									</div>
								</div>
							</logic:equal>
							<logic:equal name="tipoUsuarioForm" value="false" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.tipoUsuario.ger_perm)">
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
		$("#nome").addClass("obrigatorio");

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#nome").attr("maxlength", 50);

		/* Setando os placeholder dos campos*/
		$("#nome").attr("placeholder", "Nome");

		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_tipoUsuario").attr("autocomplete", "off");

		$('#form_tipoUsuario').on('submit', function(e) {
			abrirModalProcessando();
		});

		$('#inserir').click(function() {
			executar('form_tipoUsuario', 'inserir');
		});

		$('#alterar').click(function() {
			executar('form_tipoUsuario', 'alterar');
		});

		$('#limpar').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_tipoUsuario', 'limpar');
		});

		$('#filtrar').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_tipoUsuario', 'abrirListagem');
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
	});
</script>
