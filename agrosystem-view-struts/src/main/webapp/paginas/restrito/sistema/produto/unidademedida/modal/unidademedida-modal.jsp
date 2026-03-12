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

	<!-- Define o tamanho geral da unidadeMedida -->

	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<html:form styleId="form_unidadeMedida" action="restrito/sistema/unidadeMedida" method="post">
			<html:hidden property="method" value="empty" />

			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="row">
						<div class="form-group col-xs-12 col-sm-12 col-md-9 col-lg-9">
							<label>Nome</label>
							<html:text styleClass="form-control input-sm" styleId="nomeUnidadeMedidaModal" property="unidadeMedida.nome" name="unidadeMedidaForm" />
							<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
						</div>
						<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
							<label>Sigla</label>
							<html:text styleClass="form-control input-sm" styleId="siglaUnidadeMedidaModal" property="unidadeMedida.sigla" name="unidadeMedidaForm" />
						</div>
					</div>
				</div>
			</div>
		</html:form>
	</div>

	<!-- /.col-lg-12 -->
</div>


<script type="text/javascript">
	$(document).ready(function() {
		$('#nomeUnidadeMedidaModal').val(null);
		$('#siglaUnidadeMedidaModal').val(null);

		/* Setando NOTNULL nos campos*/
		$("#nomeUnidadeMedidaModal").addClass("obrigatorio");
		$("#siglaUnidadeMedidaModal").addClass("obrigatorio");

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#nomeUnidadeMedidaModal").prop("maxlength", 20);
		$("#siglaUnidadeMedidaModal").prop("maxlength", 4);

		/* Setando os placeholder dos campos*/
		$("#nomeUnidadeMedidaModal").prop("placeholder", "Nome");
		$("#siglaUnidadeMedidaModal").prop("placeholder", "Sigla");

		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_unidadeMedida").prop("autocomplete", "off");
		$("#form_unidadeMedida").prop("onSubmit", "return false");

		$('#form_unidadeMedida').on('submit', function(e) {
			return false;
		});

		$('#nomeUnidadeMedidaModal').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'unidadeMedida.nome',
			/* params : {
				'saidaGrao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/unidadeMedida.src?method=selecionarUnidadeMedidaAutoComplete',
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