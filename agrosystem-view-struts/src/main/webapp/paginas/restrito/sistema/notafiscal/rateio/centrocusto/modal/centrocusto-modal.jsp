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

	<!-- Define o tamanho geral da centroCusto -->

	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<html:form styleId="form_centroCusto" action="restrito/sistema/centroCusto" method="post">
			<html:hidden property="method" value="empty" />

			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

					<div class="row">
						<div class="form-group col-xs-12 col-sm-8 col-md-8 col-lg-3">
							<label>Código</label>
							<html:text styleClass="form-control input-sm" styleId="codigoModal" property="centroCusto.codigo" name="centroCustoForm" />
							<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
						</div>
						<div class="form-group col-xs-12 col-sm-4 col-md-4 col-lg-6">
							<label>Descrição</label>
							<html:text styleClass="form-control input-sm" styleId="descricaoModal" property="centroCusto.descricao" name="centroCustoForm" />
						</div>
						<div class="form-group col-xs-12 col-sm-4 col-md-4 col-lg-3">
							<label>Tipo</label>
							<html:select styleClass="form-control input-sm" styleId="tipoModal" property="centroCusto.tipo" name="centroCustoForm">
								<html:option value="">Selecione...</html:option>
								<html:option value="Despesa">Despesa</html:option>
								<html:option value="Investimento">Investimento</html:option>
								<html:option value="Receita">Receita</html:option>
							</html:select>
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

		$('#codigoModal').val(null);
		$('#descricaoModal').val(null);
		$('#tipoModal').val(null);

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#codigoModal").attr("maxlength", 50);
		$("#descricaoModal").attr("maxlength", 255);
		$("#tipoModal").attr("maxlength", 50);

		/* Setando os placeholder dos campos*/
		$( "#codigoModal" ).attr("placeholder","Codigo");
		$( "#descricaoModal" ).attr("placeholder","Descrição");
		$( "#tipoModal" ).attr("placeholder","Tipo");
		
		/* Setando NOTNULL nos campos*/
		$("#codigoModal").addClass("obrigatorio");
		$("#descricaoModal").addClass("obrigatorio");
		$("#tipoModal").addClass("obrigatorio");

		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_centroCusto").prop("autocomplete", "off");

		$('#form_centroCusto').on('submit', function(e) {
			return false;
		});

		$('#codigoModal').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'centroCusto.codigo',
			/* params : {
				'saidaGrao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/centroCusto.src?method=selecionarCentroCustoAutoComplete',
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