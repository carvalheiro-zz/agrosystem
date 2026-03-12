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

	<!-- Define o tamanho geral da implemento -->

	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<html:form styleId="form_implemento" action="restrito/sistema/implemento" method="post">
			<html:hidden property="method" value="empty" />

			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="row">
						<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<html:text styleClass="form-control input-lg bloqueado text-center" styleId="nomeCompletoModal" property="implemento.nomeCompleto" name="implementoForm" />
						</div>
						<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-3">
							<label>Código</label>
							<html:text styleClass="form-control input-sm focoInicial montarNomeCompletoProduto" styleId="codigoModal" property="implemento.codigo" name="implementoForm" />
						</div>

						<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-5">
							<label>Nome</label>
							<html:text styleClass="form-control input-sm montarNomeCompletoProduto" styleId="nomeModal" property="implemento.nome" name="implementoForm" />
							<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
							<html:hidden styleId="implementoSelecionadoModal" property="implemento.id" name="implementoForm" />
						</div>

						<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
							<label>Modelo</label>
							<html:text styleClass="form-control input-sm montarNomeCompletoProduto" styleId="modeloModal" property="implemento.modelo" name="implementoForm" />
						</div>

						<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
							<label>Ano Fabricação</label>
							<html:text styleClass="form-control input-sm inteiro montarNomeCompletoProduto" styleId="anoFabricacaoModal" property="implemento.anoFabricacao" name="implementoForm" />
						</div>

						<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
							<label>Nº Chassis</label>
							<html:text styleClass="form-control input-sm montarNomeCompletoProduto" styleId="numeroChassisModal" property="implemento.numeroChassis" name="implementoForm" />
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
		$("#codigoModal").addClass("obrigatorio");
		$("#nomeModal").addClass("obrigatorio");
		$("#modeloModal").addClass("obrigatorio");

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#codigoModal").prop("maxlength", 20);
		$("#nomeModal").prop("maxlength", 100);
		$("modeloModal").prop("maxlength", 50);
		$("#anoFabricacaoModal").prop("maxlength", 4);
		$("#numeroChassisModal").prop("maxlength", 20);

		/* Setando os placeholder dos campos*/
		$("#codigoModal").prop("placeholder", "Código");
		$("#nomeModal").prop("placeholder", "Nome");
		$("#modeloModal").prop("placeholder", "Modelo");
		$("#anoFabricacaoModal").prop("placeholder", "Ano de Fabricação");
		$("#numeroChassisModal").prop("placeholder", "Numero do Chassis");
		
		$("#form_implemento").prop("autocomplete", "off");
		$("#form_implemento").prop("onSubmit", "return false");
		
		$('#nomeModal').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'implemento.nome',
			/* params : {
				'saidaGrao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/implemento.src?method=selecionarImplementoAutoComplete',
			onSelect : function(suggestion) {
				montarNomeCompletoProduto();
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

		$('.montarNomeCompletoProduto').keyup(function() {
			montarNomeCompletoProduto();
		});
		
		function montarNomeCompletoProduto() {
			var codigo = $('#codigoModal').val();
			var nome = $('#nomeModal').val();		
			
			var modelo = '';
			if($('#modeloModal').val() != null && $('#modeloModal').val() != ''){
				modelo = '(' +$('#modeloModal').val() + ')';
			}			
			
			var anoFabricacao = $('#anoFabricacaoModal').val();
			var numeroChassis = $('#numeroChassisModal').val();

			$('#nomeCompletoModal').val(codigo + ' ' + nome + ' ' + modelo + ' ' + anoFabricacao + ' ' + numeroChassis);			
		}
	
		carregarMascaras();
	});
</script>