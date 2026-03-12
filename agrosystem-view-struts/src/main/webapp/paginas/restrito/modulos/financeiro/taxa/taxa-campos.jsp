<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!-- CAMPOS DE CADASTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -30px; margin-bottom: -15px;">
		<h1 class="page-header cabecalho_pagina">
			<i class="fa fa-graduation-cap" style="font-size: 26px;"></i>
			Taxa
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Cadastro
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<logic:present property="taxa.id" name="taxaForm">
	<div class="row">
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px;">
			<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-user-plus"></i>
				&nbsp;${taxaForm.taxa.nomeUsuarioCriacao }
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
			<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-calendar-plus-o"></i>
				&nbsp;${taxaForm.taxa.dataOcorrenciaCriacao}
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
			<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-refresh"></i>
				&nbsp;${taxaForm.taxa.nomeUsuarioAlteracao}
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-left: 0px;">
			<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-calendar-o"></i>
				&nbsp;${taxaForm.taxa.dataOcorrenciaAlteracao}
			</div>
		</div>
	</div>
</logic:present>

<div class="row">
	<!-- Define o tamanho geral da taxa -->
	<div class="col-md-offset-3 col-lg-offset-3 col-xs-12 col-sm-12 col-md-6 col-lg-6">
		<div class="panel sombra">

			<!-- INICIO FORMULARIO -->
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_taxa" action="restrito/sistema/taxa" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12" style="display: none;">
									<label>Empresa</label>
									<logic:equal value="true" name="usuarioSessaoPOJO" property="isADM" scope="session">
										<html:select styleClass="form-control input-sm" styleId="empresa" property="taxa.empresa.id" name="taxaForm">
											<html:optionsCollection name="taxaForm" property="comboEmpresas" label="label" value="value" />
										</html:select>
									</logic:equal>
									<logic:equal value="false" name="usuarioSessaoPOJO" property="isADM" scope="session">
										<html:text styleClass="form-control input-sm bloqueado" styleId="empresaView" property="taxa.empresa.nomeFantasia" name="taxaForm" />
										<html:hidden styleId="empresa" property="taxa.empresa.id" name="taxaForm" />
									</logic:equal>
								</div>
							</div>
							
							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<label>Nome</label>
									<html:text styleClass="form-control input-sm" styleId="nome" property="taxa.nome" name="taxaForm" />
								</div>
							</div>

							<div class="row row-botoes">
								<!-- BOTOES -->
								<logic:notPresent property="taxa.id" name="taxaForm">
									<logic:equal name="taxaForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.taxa.inserir)">
										<div class="col-xs-12 col-sm-3 col-md-4 col-lg-3">
											<button type="submit" id="inserir" class="btn btn-success btn-sm cor-sistema btn-block">
												<i class="fa fa-save"></i>
												Inserir
											</button>
										</div>
									</logic:equal>
								</logic:notPresent>
								<logic:present property="taxa.id" name="taxaForm">
									<logic:equal name="taxaForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.taxa.alterar)">
										<div class="col-xs-12 col-sm-3 col-md-4 col-lg-3">
											<button type="submit" id="alterar" class="btn btn-success btn-sm cor-sistema btn-block">
												<i class="fa fa-edit"></i>
												Alterar
											</button>
										</div>
									</logic:equal>
								</logic:present>

								<div class="ol-xs-12 col-sm-3 col-md-4 col-lg-3">
									<button type="button" id="limpar" class="btn btn-success btn-sm cor-sistema btn-block">
										<i class="glyphicon glyphicon-erase"></i>
										Limpar
									</button>
								</div>

								<logic:equal name="taxaForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.taxa.filtrar)">
									<div class="col-xs-12 col-sm-3 col-md-4 col-lg-3">
										<button type="button" id="filtrar" class="btn btn-success btn-sm cor-sistema btn-block">
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
		$("#nome").addClass("obrigatorio");		

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#nome").attr("maxlength", 50);		

		/* Setando os placeholder dos campos*/
		$("#nome").attr("placeholder", "Ex: ISS, Frete");

		// Desliga o auto-complete da pagina
		$("#form_taxa").attr("autocomplete", "off");

		$('#form_taxa').on('submit', function(e) {
			abrirModalProcessando();
		});

		$('#inserir').click(function() {
			executar('form_taxa', 'inserir');
		});

		$('#alterar').click(function() {
			executar('form_taxa', 'alterar');
		});

		$('#limpar').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_taxa', 'limpar');
		});

		$('#filtrar').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_taxa', 'abrirListagem');
		});
		
	});
</script>