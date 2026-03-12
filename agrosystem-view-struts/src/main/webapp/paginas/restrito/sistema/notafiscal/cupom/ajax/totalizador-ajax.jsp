<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<label style="font-size: 16px;">Total Nota Fiscal Cupom</label>
		<html:text styleClass="form-control text-center input-lg bloqueado " style="font-size: 30px; padding-right: 40px;" styleId="valorCustoTotal"
			property="notaFiscalCupom.valorCustoTotal" name="notaFiscalCupomForm" />
		<i style="margin-top: -44px; float: right; margin-right: 8px; font-size: 42px; color: green;" class="fa fa-money"></i>
	</div>
</div> 