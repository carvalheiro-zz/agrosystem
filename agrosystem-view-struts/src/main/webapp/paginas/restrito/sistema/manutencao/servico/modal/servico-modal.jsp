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

	<!-- Define o tamanho geral da servico -->

	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<html:form styleId="form_servico" action="restrito/sistema/servico" method="post">
			<html:hidden property="method" value="empty" />

			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="row">						

						<div class="form-group col-xs-12 col-sm-12 col-md-9 col-lg-10">
							<label>Nome</label>
							<html:text styleClass="form-control input-sm" styleId="nomeModal" property="servico.nome" name="servicoForm" />
						</div>
						
						<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<label>Observação</label>
									<h6 class="pull-right" id="count_observacao"></h6>
									<html:textarea styleClass="form-control input-sm" styleId="observacaoModal" property="servico.observacao" name="servicoForm" rows="4" />
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

		/* Setando NOTNULL nos campos*/
		$("#nomeModal").addClass("obrigatorio");

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#nomeModal").prop("maxlength", 50);
		$("#observacaoModal").prop("maxlength", 255);

		/* Setando os placeholder dos campos*/
		$("#nomeModal").prop("placeholder", "Nome");
		$("#observacaoModal").prop("placeholder", "Descrição");

		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_servico").prop("autocomplete", "off");
		//$("#form_servico").prop("onSubmit", "return false");
		
		$('#form_servico').on('submit', function(e) {
			return false;
		});

		$('#nomeModal').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'servico.nome',
			/* params : {
				'saidaGrao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/servico.src?method=selecionarServicoAutoComplete',
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