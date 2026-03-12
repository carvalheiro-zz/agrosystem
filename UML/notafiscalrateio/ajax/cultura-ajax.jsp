<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<label>Cultura</label>
<html:text styleClass="form-control input-sm itemAdicionar" styleId="cultura" property="itemAdicionar.cultura" name="notaFiscalRateioForm" />
<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>

<script type="text/javascript">
	$(document).ready(function() {

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#cultura").attr("maxlength", 100);

		/* Setando os placeholder dos campos*/
		$("#cultura").attr("placeholder", "Cultura");

		 $('#cultura').keyup(function() {
			if ($('#cultura').val() == '') {
				$('#cultura').val(null);
			}
		});
		$('#cultura').autocomplete({
			minChars : 2,
			paramName : 'itemAdicionar.cultura',
			serviceUrl : '${contextPath}/restrito/sistema/notaFiscalRateio.src?method=selecionarCulturaAutoComplete',
			onSelect : function(suggestion) {
				$('#cultura').val(suggestion.data);

				$("#btnAdicionarCentroCustoReceita").focus();
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#cultura').val(null);
				}
			}
		});

	});
</script>