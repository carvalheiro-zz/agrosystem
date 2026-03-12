<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<logic:notEmpty name="erroAjax" scope="session">
	<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="alert alert-danger alertModal">
			<strong>Atenção!</strong>
			${erroAjax}
		</div>
	</div>
</logic:notEmpty>
<logic:notEmpty name="sucessoAjax" scope="session">
	<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="alert alert-success alertModal">
			<strong>Parabéns!</strong>
			${sucessoAjax}
		</div>
	</div>
</logic:notEmpty>

<script type="text/javascript">
	$(document).ready(function() {
		$(".alertModal").delay(12000).slideUp(200, function() {
			$(this).alert('close');
		});
	});
</script>