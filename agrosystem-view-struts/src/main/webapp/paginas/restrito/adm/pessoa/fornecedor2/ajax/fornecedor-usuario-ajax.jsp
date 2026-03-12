<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>



<logic:equal value="true" property="fornecedor.usuario.status" name="fornecedorForm">
	<div class="alert alert-success">
</logic:equal>
<logic:equal value="false" property="fornecedor.usuario.status" name="fornecedorForm">
	<div class="alert alert-danger">
</logic:equal>
<logic:notPresent property="fornecedor.usuario.status" name="fornecedorForm">
	<div class="alert alert-info">
</logic:notPresent>
<div class="row">
	<!-- PARA QUANDO O USUARIO ESTIVER PRE-CADASTRADO COM O EMAIL INFORMADO PARA O FUNCIONARIO -->
	<logic:present property="fornecedor.usuario.id" name="fornecedorForm">
		<div class="form-group col-xs-12 col-sm-6 col-md-6 col-lg-3">
			<label>Usuário</label>
			<html:text styleClass="form-control input-sm text-center bloqueado" styleId="usuario" property="fornecedor.usuario.login" name="fornecedorForm" />
		</div>
		<div class="form-group col-xs-12 col-sm-6 col-md-6 col-lg-3">
			<label>Perfil</label>
			<html:text styleClass="form-control input-sm text-center bloqueado" styleId="tipoUsuario" property="fornecedor.usuario.tipoUsuario.nome" name="fornecedorForm" />
		</div>
	</logic:present>

	<!-- PARA QUANDO O USUARIO NÃO TIVER PRE-CADASTRADO COM O EMAIL INFORMADO PARA O FUNCIONARIO, POR ISSO O CADASTRO DE USUARIO SERÁ FEITO AQUI -->
	<logic:notPresent property="fornecedor.usuario.id" name="fornecedorForm">
		<div class="form-group col-xs-12 col-sm-6 col-md-6 col-lg-3">
			<label>Usuário</label>
			<html:text styleClass="form-control input-sm text-center" styleId="usuario" property="fornecedor.usuario.login" name="fornecedorForm" />
		</div>
		<div class="form-group col-xs-12 col-sm-6 col-md-6 col-lg-3">
			<label>Senha</label>
			<html:text styleClass="form-control input-sm text-center senha" styleId="senha" property="fornecedor.usuario.senha" name="fornecedorForm" />
		</div>
		<div class="form-group col-xs-6 col-sm-6 col-md-6 col-lg-3">
			<label>Perfil</label>
			<html:select styleClass="form-control input-sm " styleId="tipo" property="fornecedor.usuario.tipoUsuario.id" name="fornecedorForm">
				<html:optionsCollection name="fornecedorForm" property="comboTiposUsuario(${usuarioSessaoPOJO.isADM})" label="label" value="value" />
			</html:select>
		</div>
	</logic:notPresent>
</div>
</div>

<script type="text/javascript">
	$(document).ready(
			function() {	

				recarregarObrigatorios();

			});
</script>