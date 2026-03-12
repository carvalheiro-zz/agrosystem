<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<div class="row">
	<div class="col-xs-12 col-sm-10 col-md-12 col-lg-4">
		<strong class="text-white">Situação</strong>
		<html:text styleClass="form-control input-lg bloqueado text-center" style="font-size: 35px;" styleId="situacao" property="despesa.contaPagar.situacao" name="despesaForm" />
	</div>
	<div class="col-xs-12 col-sm-10 col-md-12 col-lg-4 text-center">
		<strong class="text-white">Total R$ </strong>
		<html:text styleClass="form-control input-lg bloqueado text-center" style="font-size: 35px;" styleId="valorTotal" property="despesa.contaPagar.valor" name="despesaForm" />
	</div>
	<div class="col-xs-12 col-sm-10 col-md-12 col-lg-4 text-right">
		<strong class="text-white">Pago + Acréscimo R$ </strong>
		<html:text styleClass="form-control input-lg bloqueado text-center" style="font-size: 35px;" styleId="totalPago" property="despesa.contaPagar.totalPago" name="despesaForm" />
	</div>
</div>