<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!-- INICIO - MODAIS DE ERRO -->
<logic:messagesPresent message="false">
	<html:messages id="message" message="false">

		<div class="row">
			<div class="alert alert-danger alert-dismissible fade in mensagensSucessoErros">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>Falha</strong>
				<bean:write filter='false' name='message' />
			</div>
		</div>
	</html:messages>
</logic:messagesPresent>

<!-- INICIO - MODAIS DE SUCESSO -->
<logic:messagesPresent message="true">
	<html:messages id="message" message="true">
		<div class="row">
			<div class="alert alert-success alert-dismissible fade in mensagensSucessoErros">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>Sucesso!</strong>
				<bean:write filter='false' name='message' />
			</div>
		</div>
	</html:messages>
</logic:messagesPresent>
<script type="text/javascript">
	$(document).ready(function() {
		setTimeout(function() {
			$('.mensagensSucessoErros').alert('close')
		}, 12000);
	});
</script>