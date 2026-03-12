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

	<!-- Define o tamanho geral da marca -->

	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<html:form styleId="form_marca" action="restrito/sistema/marca" method="post">
			<html:hidden property="method" value="empty" />

			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="row">
						<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<label>Nome</label>
							<html:text styleClass="form-control input-sm" styleId="nomeMarcaModal" property="marca.nome" name="marcaForm" />
							<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
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

		$('#nomeMarcaModal').val(null);

		/* Setando NOTNULL nos campos*/
		$("#nomeMarcaModal").addClass("obrigatorio");

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#nomeMarcaModal").prop("maxlength", 50);

		/* Setando os placeholder dos campos*/
		$("#nomeMarcaModal").prop("placeholder", "Nome");

		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_marca").prop("autocomplete", "off");
		//$("#form_marca").prop("onSubmit", "return false");
		
		$('#form_marca').on('submit', function(e) {
			return false;
		});

		$('#nomeMarcaModal').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'marca.nome',
			/* params : {
				'saidaGrao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/marca.src?method=selecionarMarcaAutoComplete',
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