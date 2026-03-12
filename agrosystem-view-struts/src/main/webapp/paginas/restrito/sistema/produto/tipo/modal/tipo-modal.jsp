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

	<!-- Define o tamanho geral da tipo -->

	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<html:form styleId="form_tipo" action="restrito/sistema/tipo" method="post">
			<html:hidden property="method" value="empty" />

			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="row">
						<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
							<label>Classificação</label>
							<html:select styleClass="form-control input-sm text-center" styleId="classificacaoTipoModal" property="tipo.classificacao" name="tipoForm">
								<html:option value="">Selecione...</html:option>
								<html:option value="Insumo">Insumo</html:option>
								<html:option value="Item">Item</html:option>
							</html:select>
						</div>
						<div class="form-group col-xs-12 col-sm-12 col-md-9 col-lg-9">
							<label>Nome</label>
							<html:text styleClass="form-control input-sm" styleId="nomeTipoModal" property="tipo.nome" name="tipoForm" />
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
		$('#nomeTipoModal').val(null);
		/* Setando NOTNULL nos campos*/
		$("#nomeTipoModal").addClass("obrigatorio");
		$("#classificacaoTipoModal").addClass("obrigatorio");

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#nomeTipoModal").prop("maxlength", 50);

		/* Setando os placeholder dos campos*/
		$("#nomeTipoModal").prop("placeholder", "Nome");

		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_tipo").prop("autocomplete", "off");

		$('#form_tipo').on('submit', function(e) {
			return false;
		});

		$('#nomeTipoModal').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'tipo.nome',
			/* params : {
				'saidaGrao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/tipo.src?method=selecionarTipoAutoComplete',
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