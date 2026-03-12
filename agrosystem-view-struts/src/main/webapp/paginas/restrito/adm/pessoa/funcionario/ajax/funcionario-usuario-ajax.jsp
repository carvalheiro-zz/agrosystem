<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>



<logic:equal value="true" property="funcionario.usuario.status" name="funcionarioForm">
	<div class="alert alert-success">
</logic:equal>
<logic:equal value="false" property="funcionario.usuario.status" name="funcionarioForm">
	<div class="alert alert-danger">
</logic:equal>
<logic:notPresent property="funcionario.usuario.status" name="funcionarioForm">
	<div class="alert alert-info">
</logic:notPresent>
<div class="row">

	<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
		<label>Código</label>
		<html:text styleClass="form-control input-sm text-center" styleId="codigo" property="funcionario.codigo" name="funcionarioForm" />
	</div>

	<!-- PARA QUANDO O USUARIO ESTIVER PRE-CADASTRADO COM O EMAIL INFORMADO PARA O FUNCIONARIO -->
	<logic:present property="funcionario.usuario.id" name="funcionarioForm">
		<div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-3">
			<label>Usuário</label>
			<html:text styleClass="form-control input-sm text-center bloqueado" styleId="usuario" property="funcionario.usuario.login" name="funcionarioForm" />
		</div>
		<div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-3">
			<label>Perfil</label>
			<html:text styleClass="form-control input-sm text-center bloqueado" styleId="tipoUsuario" property="funcionario.usuario.tipoUsuario.nome" name="funcionarioForm" />
		</div>
		
		<div class="form-group col-xs-12 col-sm-6 col-md-6 col-lg-3">
			<button type="button" id="gerenciarPermissoes" class="btn btn-success btn-sm btn-block cor-sistema" style="margin-top: 25px;">
				<i class="glyphicon glyphicon-erase"></i>
				Gerenciar Permissões
			</button>
		</div>
	</logic:present>

	<!-- PARA QUANDO O USUARIO NÃO TIVER PRE-CADASTRADO COM O EMAIL INFORMADO PARA O FUNCIONARIO, POR ISSO O CADASTRO DE USUARIO SERÁ FEITO AQUI -->
	<logic:notPresent property="funcionario.usuario.id" name="funcionarioForm">
		<div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-3">
			<label>Usuário</label>
			<html:text styleClass="form-control input-sm text-center autoCompleteOff" styleId="usuario" property="funcionario.usuario.login" name="funcionarioForm" />
		</div>
		<div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-3">
			<label>Senha</label>
			<html:text styleClass="form-control input-sm text-center autoCompleteOff senha" styleId="senha" property="funcionario.usuario.senha" name="funcionarioForm" />
		</div>
		<div class="form-group col-xs-6 col-sm-6 col-md-4 col-lg-3">
			<label>Perfil</label>
			<html:select styleClass="form-control input-sm " styleId="tipo" property="funcionario.usuario.tipoUsuario.id" name="funcionarioForm">
				<html:optionsCollection name="funcionarioForm" property="comboTiposUsuario(${usuarioSessaoPOJO.isADM})" label="label" value="value" />
			</html:select>
		</div>
	</logic:notPresent>
</div>
</div>

<script type="text/javascript">
	$(document).ready(
			function() {	
				$("#usuario").attr("maxlength", 18);
				$("#usuario").attr("placeholder", "Usuário");
				
				$("#senha").attr("maxlength", 20);
				$("#senha").attr("placeholder", "Senha");
				
				$("#usuario").attr("autocomplete", "off");
				
				recarregarObrigatorios();
				
			});
</script>