<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!-- CAMPOS DE CADASTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -30px; margin-bottom: -15px;">
		<h1 class="page-header cabecalho_pagina">
			<i class="fa fa-graduation-cap" style="font-size: 26px;"></i>
			Cadastrar Safra
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Cadastro das Safras
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<logic:present property="safra.id" name="safraForm">
	<div class="row">
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px;">
			<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-user-plus"></i>
				&nbsp;${safraForm.safra.nomeUsuarioCriacao }
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
			<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-calendar-plus-o"></i>
				&nbsp;${safraForm.safra.dataOcorrenciaCriacao}
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
			<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-refresh"></i>
				&nbsp;${safraForm.safra.nomeUsuarioAlteracao}
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-left: 0px;">
			<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-calendar-o"></i>
				&nbsp;${safraForm.safra.dataOcorrenciaAlteracao}
			</div>
		</div>
	</div>
</logic:present>

<div class="row">
	<!-- Define o tamanho geral da safra -->
	<div class="col-md-offset-0 col-lg-offset-0 col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<html:form styleId="form_safra" action="restrito/sistema/safra" method="post">
			<html:hidden property="method" value="empty" />

			<div class="panel sombra">

				<!-- INICIO FORMULARIO -->
				<div class="panel-body">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

							<div class="row">

								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center" style="margin-bottom: 0px;">
									<i class="fa fa-calendar"></i>
									<label>${safraForm.safra.dataInicioFim}</label>									
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<label>Nome</label>
									<html:text styleClass="form-control input-sm focoInicial" styleId="nome" property="safra.nome" name="safraForm" />
								</div>
								
								<div class="form-group col-xs-12 col-sm-4 col-md-4 col-lg-4">
									<label>Área</label>
									<html:text styleClass="form-control input-sm bloqueado text-center" styleId="areaSafra" property="safra.areaTotal" name="safraForm" />
								</div>															
								
								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12" id="id_setor_ajax">
									<jsp:include page="ajax/setor-ajax.jsp"></jsp:include>
								</div>

							</div>

						</div>
					</div>
				</div>
				<div class="panel-footer">
					<div class="row">
						<!-- BOTOES -->
						<logic:notPresent property="safra.id" name="safraForm">
							<logic:equal name="safraForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.safra.inserir)">
								<div class="form-group col-xs-12 col-sm-3 col-md-3 col-lg-3" style="margin-bottom: 0px;">
									<button type="submit" id="inserir" class="btn btn-success btn-sm cor-sistema btn-block">
										<i class="fa fa-save"></i>
										Inserir
									</button>
								</div>
							</logic:equal>
						</logic:notPresent>
						<logic:present property="safra.id" name="safraForm">
							<logic:equal name="safraForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.safra.alterar)">
								<div class="form-group col-xs-12 col-sm-3 col-md-3 col-lg-3" style="margin-bottom: 0px;">
									<button type="submit" id="alterar" class="btn btn-success btn-sm cor-sistema btn-block">
										<i class="fa fa-edit"></i>
										Alterar
									</button>
								</div>
							</logic:equal>
						</logic:present>

						<div class="form-group ol-xs-12 col-sm-3 col-md-3 col-lg-3" style="margin-bottom: 0px;">
							<button type="button" id="limpar" class="btn btn-success btn-sm cor-sistema btn-block">
								<i class="glyphicon glyphicon-erase"></i>
								Limpar
							</button>
						</div>

						<logic:equal name="safraForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.safra.filtrar)">
							<div class="form-group col-xs-12 col-sm-3 col-md-3 col-lg-3" style="margin-bottom: 0px;">
								<button type="button" id="listagem" class="btn btn-success btn-sm cor-sistema btn-block">
									<i class="fa fa-search"></i>
									Listagem
								</button>
							</div>
						</logic:equal>

					</div>
				</div>
				<!-- TERMINO FORMULARIO -->
				<!-- /.panel-body -->

			</div>
			<!-- /.panel -->
		</html:form>
	</div>
	<!-- /.col-lg-12 -->
</div>


<script type="text/javascript">
	$(document).ready(function() {
		/* Foco inicial */
		$( ".focoInicial" ).focus();

		/* Setando NOTNULL nos campos*/
		$("#nome").addClass("obrigatorio");

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#nome").attr("maxlength", 100);
		$("#areaSafra").attr("maxlength", 100);

		/* Setando os placeholder dos campos*/
		$("#nome").attr("placeholder", "Nome");
		$("#area").attr("placeholder", "Área");

		/* EVENTOS */

		// Desliga o auto-complete da pagina
		$("#form_safra").prop("autocomplete", "off");

		$('#form_safra').on('submit', function(e) {
			abrirModalProcessando();
		});

		$('#inserir').click(function() {
			executar('form_safra', 'inserir');
		});

		$('#alterar').click(function() {
			executar('form_safra', 'alterar');
		});

		$('#limpar').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_safra', 'limpar');
		});

		$('#listagem').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_safra', 'abrirListagem');
		});
	});
</script>