<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<label>Setor&nbsp;(&nbsp;${notaFiscalRateioForm.itemAdicionar.safra.quantidadeSetores}&nbsp;)</label>
<html:text styleClass="form-control input-sm itemAdicionar" styleId="setor" property="itemAdicionar.setor.nome" name="notaFiscalRateioForm" />
<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
<html:hidden styleClass="itemAdicionar" styleId="setorSelecionado" property="itemAdicionar.setor.id" name="notaFiscalRateioForm" />

<script type="text/javascript">
	$(document).ready(function() {

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#setor").attr("maxlength", 100);

		/* Setando os placeholder dos campos*/
		$("#setor").attr("placeholder", "Setor");

		$('#setor').keyup(function() {
			if ($('#setor').val() == '') {
				$('#setorSelecionado').val(null);
			}
		});
		$('#setor').autocomplete({
			minChars : 2,
			paramName : 'itemAdicionar.setor.nome',
			serviceUrl : '${contextPath}/restrito/sistema/notaFiscalRateio.src?method=selecionarSetorAutoComplete',
			onSelect : function(suggestion) {
				$('#setorSelecionado').val(suggestion.data);
				
				$("#btnAdicionarCentroCustoReceita").focus();
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#setorSelecionado').val(null);
					$('#setor').val(null);
				}
			}
		});

	});
</script>