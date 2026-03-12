<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!-- CAMPOS DE CADASTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -30px; margin-bottom: -15px;">
		<h1 class="page-header cabecalho_pagina">
			<i class="fa fa-graduation-cap" style="font-size: 26px;"></i>
			${queryForm.query.tituloTela}
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Pesquisa
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<div class="row">

	<!-- Define o tamanho geral da query -->
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<jsp:include page="query-view.jsp"></jsp:include> 
	</div>
</div>


<script type="text/javascript">
	$(document).ready(function() {
		/* Foco inicial */
		$("#0").focus();			
	});
</script>