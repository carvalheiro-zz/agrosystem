<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<link href="${contextPath}/assets/bootstrap/bootstrap-switch-master/dist/css/bootstrap3/bootstrap-switch.css" rel="stylesheet">
<script src="${contextPath}/assets/bootstrap/bootstrap-switch-master/dist/js/bootstrap-switch.js"></script>

<html:hidden name="tipoUsuarioForm" property="permissoesSelecionadas" styleId="permissoesSelecionadas" />

<div class="panel panel panel-primary" style="border-color: #909090;">
	<div class="panel-heading cor-sistema">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12">
				<span>
					<i class="fa fa-lock" style="font-size: 18px;"></i>
					Atribuição das Permissões de Acesso
				</span>
			</div>
		</div>
	</div>
	<!-- INICIO FORMULARIO -->
	<div class="panel-body" style="background-color: rgba(67, 142, 185, 0.01);">
		<div class="row">
			<logic:iterate id="telaCorrente" name="tipoUsuarioForm" property="telasExistentes" type="java.lang.String">
				<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="panel panel panel-primary" style="border-color: #909090; margin-bottom: 0px;">
						<div class="panel-heading cor-sistema">
							<div class="row">
								<div class="col-lg-12">
									<span>
										<i class="fa fa-list-alt" style="font-size: 14px;"></i>
										${telaCorrente}
									</span>
								</div>
							</div>
							<!-- switch-labelText -->
						</div>
						<div class="panel-body" style="background-color: rgba(67, 142, 185, 0.01);">
							<div class="row">
								<logic:iterate indexId="i" id="permissaoCorrente" name="tipoUsuarioForm" property="permissoesPorTela(${telaCorrente})" type="br.com.srcsoftware.modular.manager.usuario.permissao.PermissaoDTO">
									<div class="form-group col-xs-6 col-sm-6 col-md-6 col-lg-4 text-center">

										<logic:equal name="tipoUsuarioForm" property="permissaoAtribuida(${permissaoCorrente.id})" value="true">
											<input checked data-off-color="danger" data-label-width="100" data-size="mini" id="${permissaoCorrente.id}" type="checkbox" data-label-text="${permissaoCorrente.acao}" data-on-text=Sim data-off-text="Não" class="check-label">
										</logic:equal>

										<logic:equal name="tipoUsuarioForm" property="permissaoAtribuida(${permissaoCorrente.id})" value="false">
											<input data-off-color="danger" data-label-width="100" data-size="mini" id="${permissaoCorrente.id}" type="checkbox" data-label-text="${permissaoCorrente.acao}" data-on-text=Sim data-off-text="Não" class="check-label">
										</logic:equal>
									</div>
								</logic:iterate>
							</div>
						</div>
					</div>
				</div>
			</logic:iterate>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$(".check-label").bootstrapSwitch();

		$(".check-label").on('switchChange.bootstrapSwitch', function(event, state) {
			pegarDadosChecks();
		});		
	});

	function pegarDadosChecks() {
		var permissoesSelecionadas1 = [];
		var i = 0;

		$(".check-label").each(function(index, element) {
			if ($(this).prop("checked") == true) {
				permissoesSelecionadas1[i++] = $(this).prop("id");
			}
		});

		$("#permissoesSelecionadas").val(permissoesSelecionadas1);
	}
</script>