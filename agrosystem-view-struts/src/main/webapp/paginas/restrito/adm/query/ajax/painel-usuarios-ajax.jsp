<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<div class="panel" style="border-color: #909090;">
	<div class="panel-heading cor-sistema" style="color: white;">
		<strong>Usuários com permissão de acesso</strong>
	</div>

	<div class="panel-body">
		<div class="row">
			<div class="form-group col-xs-11 col-sm-11 col-md-4 col-lg-4">
				<label>Usuario</label>
				<html:text styleClass="form-control input-sm " styleId="usuario" property="loginUsuario" name="queryForm" />
				<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
				<html:hidden styleId="usuarioSelecionado" property="idUsuario" name="queryForm" />
			</div>
			<div class="form-group col-xs-11 col-sm-11 col-md-4 col-lg-4">
				<label>Perfil</label>
				<html:text styleClass="form-control input-sm bloqueado" styleId="perfil" property="perfilUsuario" name="queryForm" />				
			</div>
			<div class="form-group col-xs-12 col-sm-12 col-md-1 col-lg-1" style="padding-left: 0px;">
				<button type="button" id="adicionarUsuario" class="btn btn-success btn-sm cor-sistema" style="margin-top: 25px;">
					<i class="fa fa-plus"></i>
				</button>
			</div>

			<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="table-responsive">
					<table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
						<thead>
							<!-- CABEÇALHO DA TABELA -->
							<tr class="cor-sistema" style="color: white;">								
								<th>Username</th>		
								<th>Perfil</th>								
								<th class="text-center" style="width: 80px;">Remover</th>
							</tr>
						</thead>
						<tbody>
							<!-- TABELA -->
							<logic:iterate indexId="i" id="usuarioCorrente" name="queryForm" property="query.usuarioPermitidos" type="br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioDTO">

								<tr>
									<td style="vertical-align: middle;">${usuarioCorrente.login}</td>									
									<td style="vertical-align: middle;">${usuarioCorrente.tipoUsuario.nome}</td>
									<td class="text-center" style="min-width: 80px; vertical-align: middle;">
										<a onclick="removerUsuario('${usuarioCorrente.id}');" class="btn btn-danger btn-xs fa fa-trash" style="text-decoration: none; font-size: 18px"></a>
									</td>
								</tr>

							</logic:iterate>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		
		$('#usuario').autocomplete({
			minChars : 2,
			noCache:true,
			paramName : 'loginUsuario',
			serviceUrl : '${contextPath}/restrito/sistema/query.src?method=selecionarUsuarioAutoComplete',
			onSelect : function(suggestion) {
				$('#usuarioSelecionado').val(suggestion.data);
				$('#perfil').val(suggestion.perfil);
			},
			formatResult : function(suggestion, currentValue) {
				var result = formatResultAutoCompleteHightLigth((suggestion.value + " - " + suggestion.perfil), currentValue);				
				return result;
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {					
					$('#usuarioSelecionado').val(null);
					$('#perfil').val(null);
				}
			}
		});

		recarregarObrigatorios();

		$('#adicionarUsuario').click(function() {
			var theForm = $('form[name=queryForm]');
			var actionURL = theForm.attr('action') + '?method=adicionarUsuario&idUsuario=' + $('#usuarioSelecionado').val();
			$.ajax({
				type : 'POST',
				url : actionURL,
				success : function(data, textStatus, XMLHttpRequest) {

					$('#id-usuarios').html(data);

				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(textStatus);
				}
			});

		});
	});

	function removerUsuario(idUsuario) {
		var theForm = $('form[name=queryForm]');
		var actionURL = theForm.attr('action') + '?method=removerUsuario&idUsuario=' + idUsuario;
		$.ajax({
			type : 'POST',
			url : actionURL,
			success : function(data, textStatus, XMLHttpRequest) {

				$('#id-usuarios').html(data);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(textStatus);
			}
		});
	}
</script>