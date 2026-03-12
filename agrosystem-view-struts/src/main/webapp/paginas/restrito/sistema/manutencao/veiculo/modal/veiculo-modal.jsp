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

	<!-- Define o tamanho geral da veiculo -->

	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<html:form styleId="form_veiculo" action="restrito/sistema/veiculo" method="post">
			<html:hidden property="method" value="empty" />

			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="row">
					
						<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<html:text styleClass="form-control input-lg bloqueado text-center" styleId="nomeCompletoModal" property="veiculo.nomeCompleto" name="veiculoForm" />
								</div>
						<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
							<label>Código</label>
							<html:text styleClass="form-control input-sm focoInicial montarNomeCompletoProduto" styleId="codigoModal" property="veiculo.codigo" name="veiculoForm" />
						</div>

						<div class="form-group col-xs-12 col-sm-12 col-md-9 col-lg-10">
							<label>Nome</label>
							<html:text styleClass="form-control input-sm montarNomeCompletoProduto" styleId="nomeModal" property="veiculo.nome" name="veiculoForm" />
							<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
							<html:hidden styleId="veiculoSelecionadoModal" property="veiculo.id" name="veiculoForm" />
						</div>

						<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
							<label>Modelo</label>
							<html:text styleClass="form-control input-sm montarNomeCompletoProduto" styleId="modeloModal" property="veiculo.modelo" name="veiculoForm" />
						</div>

						<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
							<label>Tipo</label>
							<html:select styleClass="form-control input-sm" styleId="tipoModal" property="veiculo.tipo" name="veiculoForm">
								<html:option value="">Selecione...</html:option>
								<html:option value="Veiculo">Veiculo</html:option>
								<html:option value="Maquina">Maquina</html:option>
							</html:select>
						</div>

						<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
							<label>Ano Fabricação</label>
							<html:text styleClass="form-control input-sm inteiro montarNomeCompletoProduto" styleId="anoFabricacaoModal" property="veiculo.anoFabricacao" name="veiculoForm" />
						</div>

						<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
							<label>Nº Chassis</label>
							<html:text styleClass="form-control input-sm montarNomeCompletoProduto" styleId="numeroChassisModal" property="veiculo.numeroChassis" name="veiculoForm" />
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
		$("#tipoModal").addClass("obrigatorio");

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#codigoModal").prop("maxlength", 10);
		$("#nomeModal").prop("maxlength", 50);
		$("#modeloModal").prop("maxlength", 20);
		$("#anoFabricacaoModal").prop("maxlength", 4);
		$("#numeroChassisModal").prop("maxlength", 20);

		/* Setando os placeholder dos campos*/
		$("#codigoModal").prop("placeholder", "Código");
		$("#nomeModal").prop("placeholder", "Nome");
		$("#modeloModal").prop("placeholder", "Modelo");
		$("#anoFabricacaoModal").prop("placeholder", "Ano de Fabricação");
		$("#numeroChassisModal").prop("placeholder", "Numero do Chassis");

		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_veiculo").prop("autocomplete", "off");
		$("#form_veiculo").prop("onSubmit", "return false");
		
		$('#nomeModal').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'veiculo.nome',
			/* params : {
				'saidaGrao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/veiculo.src?method=selecionarVeiculoAutoComplete',
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
		$('#tipoModal').change(function() {
			montarNomeCompletoProduto();
		});
		function montarNomeCompletoProduto() {
			var codigo = $('#codigoModal').val();
			var nome = $('#nomeModal').val();
			
			var tipo = '';
			if($('#tipoModal').val() != null && $('#tipoModal').val() != ''){
				tipo = '[' +$('#tipoModal').val() + ']';
			}
			
			var modelo = '';
			if($('#modeloModal').val() != null && $('#modeloModal').val() != ''){
				modelo = '(' +$('#modeloModal').val() + ')';
			}			
			
			var anoFabricacao = $('#anoFabricacaoModal').val();
			var numeroChassis = $('#numeroChassisModal').val();

			$('#nomeCompletoModal').val(tipo+ ' ' + codigo + ' ' + nome + ' ' + modelo + ' ' + anoFabricacao + ' ' + numeroChassis);
		}		
		montarNomeCompletoProduto();
		carregarMascaras();
	});
</script>