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
		<html:form styleId="form_fornecedorJuridico" action="restrito/sistema/fornecedorJuridico" method="post">
			<html:hidden property="method" value="empty" />

			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="row">
						<div class="form-group col-xs-12 col-sm-12 col-md-7 col-lg-7">
							<label>Nome</label>
							<html:text styleClass="form-control input-sm focoInicial" styleId="nomeModal" property="fornecedorJuridico.nome" name="fornecedorJuridicoForm" />
						</div>

						<div class="form-group col-xs-12 col-sm-12 col-md-5 col-lg-5">
							<label>Telefone</label>
							<html:text styleClass="form-control cel input-sm" styleId="telefoneModal" property="fornecedorJuridico.telefone" name="fornecedorJuridicoForm" />
						</div>

						<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<label>Endereço</label>
							<html:text styleClass="form-control input-sm" styleId="enderecoModal" property="fornecedorJuridico.endereco" name="fornecedorJuridicoForm" />
						</div>
					</div>

					<div class="row">
						<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<label>Observação</label>
							<html:text styleClass="form-control input-sm" styleId="observacaoModal" property="fornecedorJuridico.observacao" name="fornecedorJuridicoForm" />
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
		$("#telefoneModal").addClass("obrigatorio");

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#nomeModal").prop("maxlength", 50);
		$("#enderecoModal").prop("maxlength", 255);
		$("#observacaoModal").prop("maxlength", 255);

		/* Setando os placeholder dos campos*/
		$("#nomeModal").prop("placeholder", "Nome");
		$("#enderecoModal").prop("placeholder","Rua/Av, nº Bairro CEP Cidade/UF");
		$( "#observacaoModal" ).prop("placeholder","Observação");

		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_fornecedorJuridico").prop("autocomplete", "off");
		$("#form_fornecedorJuridico").prop("onSubmit", "return false");

		$('#nomeModal').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'fornecedorJuridico.nome',
			/* params : {
				'saidaGrao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/fornecedorJuridico.src?method=selecionarFornecedorAutoComplete',
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