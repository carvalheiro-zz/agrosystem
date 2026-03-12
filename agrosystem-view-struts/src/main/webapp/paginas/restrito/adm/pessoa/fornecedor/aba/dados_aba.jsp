<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<div class="row">
	<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12" style="display: none;">
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

	<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-3">
		<label>Natureza</label>
		<html:select styleClass="form-control input-sm" styleId="natureza" property="fornecedor.natureza" name="fornecedorForm">
			<html:option value="">Selecione</html:option>
			<html:option value="PF">Pessoa Fisica</html:option>
			<html:option value="PJ">Pessoa Juridica</html:option>
		</html:select>
	</div>
	<div class="form-group col-xs-12 col-sm-12 col-md-8 col-lg-9" id="info">
		<div class="alert alert-info" style="margin-bottom: 0px; font-size: 16px;">
			<strong>
				<i class="fa fa-hand-o-left" style="font-size: 24px"></i>
				&nbsp;
			</strong>
			Informe se o fornecedor é Fisico ou Juridico.
		</div>
	</div>
</div>

<div class="row" id="fisico">
	<jsp:include page="../includes/pessoafisica-campos.jsp"></jsp:include>
</div>
<div class="row" id="juridico">
	<jsp:include page="../includes/pessoajuridica-campos.jsp"></jsp:include>
</div>

<div class="row">
	<!-- BOTOES -->
	<logic:notPresent property="fornecedor.id" name="fornecedorForm">
		<logic:equal name="fornecedorForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.fornecedor.inserir)">
			<div class="col-xs-12 col-sm-3 col-md-4 col-lg-2">
				<button type="submit" id="inserir" class="btn btn-success btn-sm cor-sistema btn-block">
					<i class="fa fa-save"></i>
					Inserir
				</button>
			</div>
		</logic:equal>
	</logic:notPresent>
	<logic:present property="fornecedor.id" name="fornecedorForm">
		<logic:equal name="fornecedorForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.fornecedor.alterar)">
			<div class="col-xs-12 col-sm-3 col-md-4 col-lg-2">
				<button type="submit" id="alterar" class="btn btn-success btn-sm cor-sistema btn-block">
					<i class="fa fa-edit"></i>
					Alterar
				</button>
			</div>
		</logic:equal>
	</logic:present>

	<div class="ol-xs-12 col-sm-3 col-md-4 col-lg-2">
		<button type="button" id="limpar" class="btn btn-success btn-sm cor-sistema btn-block">
			<i class="glyphicon glyphicon-erase"></i>
			Limpar
		</button>
	</div>

	<logic:equal name="fornecedorForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.fornecedor.filtrar)">
		<div class="col-xs-12 col-sm-3 col-md-4 col-lg-2">
			<button type="button" id="filtrar" class="btn btn-success btn-sm cor-sistema btn-block">
				<i class="fa fa-search"></i>
				Listagem
			</button>
		</div>
	</logic:equal>

</div>