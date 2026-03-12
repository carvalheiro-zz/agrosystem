<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!-- CAMPOS DE CADASTRO -->
<div class="row">

	<logic:notEmpty name="erroAjax" scope="session">
		<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-11">
			<div class="alert alert-danger">
				<strong>Atenção!</strong>
				${erroAjax}
			</div>
		</div>
	</logic:notEmpty>
	<logic:notEmpty name="sucessoAjax" scope="session">
		<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-11">
			<div class="alert alert-success">
				<strong>Parabéns!</strong>
				${sucessoAjax}
			</div>
		</div>
	</logic:notEmpty>

	<!-- Define o tamanho geral da fornecedorJuridico -->

	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<html:form styleId="form_prestadorServico" action="restrito/sistema/prestadorServico" method="post">
			<html:hidden property="method" value="empty" />

			<div class="row">
				<div class="form-group col-xs-12 col-sm-12 col-md-5 col-lg-5">
					<label>Nome</label>
					<html:text styleClass="form-control input-sm focoInicial" styleId="nomeModal" property="prestadorServico.nome" name="prestadorServicoForm" />
				</div>

				<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
					<label>Telefone</label>
					<html:text styleClass="form-control cel input-sm" styleId="telefoneModal" property="prestadorServico.telefone" name="prestadorServicoForm" />
				</div>

				<div class="form-group col-xs-12 col-sm-12 col-md-5 col-lg-5">
					<label>Empresa</label>
					<html:text styleClass="form-control input-sm" styleId="empresaModal" property="prestadorServico.empresa" name="prestadorServicoForm" />
				</div>
			</div>
		</html:form>
	</div>

	<!-- /.col-lg-12 -->
</div>


<script type="text/javascript">
	$(document).ready(function() {

		/* Setando NOTNULL nos campos*/
		$("#nomeModal").addClass("obrigatorio");
		$("#telefoneModal").addClass("obrigatorio");
		$("#empresaModal").addClass("obrigatorio");

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#nomeModal").attr("maxlength", 100);
		$("#telefoneModal").attr("maxlength", 30);
		$("#empresaModal").attr("maxlength", 100);

		/* Setando os placeholder dos campos*/
		$("#nomeModal").attr("placeholder", "Nome");
		$("#telefoneModal").attr("placeholder","(__) ____-____");
		$( "#empresaModal" ).attr("placeholder","Empresa");

		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_prestadorServico").prop("autocomplete", "off");
		$("#form_prestadorServico").prop("onSubmit", "return false");

		$('#nomeModal').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'prestadorServico.nome',
			/* params : {
				'saidaGrao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/prestadorServico.src?method=selecionarPrestadorServicoAutoComplete',
			onSelect : function(suggestion) {
				
			},
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			}, */
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					
				}
			}
		});
		carregarMascaras();
	});
</script>