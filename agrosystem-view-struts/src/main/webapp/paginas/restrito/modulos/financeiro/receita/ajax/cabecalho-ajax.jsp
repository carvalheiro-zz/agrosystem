<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<div class="row">
	<div class="col-xs-12 col-sm-10 col-md-12 col-lg-4">
		<strong class="text-white">Situação</strong>
		<html:text styleClass="form-control input-lg bloqueado text-center" style="font-size: 35px;" styleId="situacao" property="receita.contaReceber.situacao" name="receitaForm" />
	</div>
	<div class="col-xs-12 col-sm-10 col-md-12 col-lg-4 text-center">
		<strong class="text-white">Total R$ </strong>
		<html:text styleClass="form-control input-lg bloqueado text-center" style="font-size: 35px;" styleId="valorTotal" property="receita.contaReceber.valor" name="receitaForm" />
	</div>
	<div class="col-xs-12 col-sm-10 col-md-12 col-lg-4 text-right">
		<strong class="text-white">Recebidos + Juros R$ </strong>
		<html:text styleClass="form-control input-lg bloqueado text-center" style="font-size: 35px;" styleId="totalRecebido" property="receita.contaReceber.totalRecebido" name="receitaForm" />
	</div>
</div>