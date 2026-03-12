<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!-- CAMPOS DE CADASTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -30px; margin-bottom: -15px;">
		<h1 class="page-header cabecalho_pagina">
			<i class="fa fa-graduation-cap" style="font-size: 26px;"></i>
			Cadastrar Implementos
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Cadastro dos Implementos
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<div class="row">
	<!-- Define o tamanho geral da implemento -->
	<div class="col-md-offset-1 col-lg-offset-2 col-xs-12 col-sm-12 col-md-6 col-lg-6">
		<html:form styleId="form_implemento" action="restrito/sistema/implemento" method="post">
			<html:hidden property="method" value="empty" />

			<div class="panel sombra">

				<!-- INICIO FORMULARIO -->
				<div class="panel-body">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<html:text styleClass="form-control input-lg bloqueado text-center" styleId="nomeCompleto" property="implemento.nomeCompleto" name="implementoForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-3">
									<label>Código</label>
									<html:text styleClass="form-control input-sm focoInicial montarNomeCompletoProduto" styleId="codigo" property="implemento.codigo" name="implementoForm" />
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-5">
									<label>Nome</label>
									<html:text styleClass="form-control input-sm montarNomeCompletoProduto" styleId="nome" property="implemento.nome" name="implementoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="implementoSelecionado" property="implemento.id" name="implementoForm" />
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<label>Modelo</label>
									<html:text styleClass="form-control input-sm montarNomeCompletoProduto" styleId="modelo" property="implemento.modelo" name="implementoForm" />
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<label>Ano Fabricação</label>
									<html:text styleClass="form-control input-sm inteiro montarNomeCompletoProduto" styleId="anoFabricacao" property="implemento.anoFabricacao" name="implementoForm" />
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<label>Nº Chassis</label>
									<html:text styleClass="form-control input-sm montarNomeCompletoProduto" styleId="numeroChassis" property="implemento.numeroChassis" name="implementoForm" />
								</div>
								
							</div>
						</div>
					</div>
				</div>
				<div class="panel-footer">
					<div class="row">
						<!-- BOTOES -->
						<logic:notPresent property="implemento.id" name="implementoForm">
							<logic:equal name="implementoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.implemento.inserir)">
								<div class="form-group col-xs-12 col-sm-3 col-md-4 col-lg-4" style="margin-bottom: 0px;">
									<button type="submit" id="inserir" class="btn btn-success btn-sm cor-sistema btn-block">
										<i class="fa fa-save"></i>
										Inserir
									</button>
								</div>
							</logic:equal>
						</logic:notPresent>
						<logic:present property="implemento.id" name="implementoForm">
							<logic:equal name="implementoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.implemento.alterar)">
								<div class="form-group col-xs-12 col-sm-3 col-md-4 col-lg-4" style="margin-bottom: 0px;">
									<button type="submit" id="alterar" class="btn btn-success btn-sm cor-sistema btn-block">
										<i class="fa fa-edit"></i>
										Alterar
									</button>
								</div>
							</logic:equal>
						</logic:present>

						<div class="form-group ol-xs-12 col-sm-3 col-md-4 col-lg-4" style="margin-bottom: 0px;">
							<button type="button" id="limpar" class="btn btn-success btn-sm cor-sistema btn-block">
								<i class="glyphicon glyphicon-erase"></i>
								Limpar
							</button>
						</div>

						<logic:equal name="implementoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.implemento.filtrar)">
							<div class="form-group col-xs-12 col-sm-3 col-md-4 col-lg-4" style="margin-bottom: 0px;">
								<button type="button" id="listagem" class="btn btn-success btn-sm cor-sistema btn-block">
									<i class="fa fa-search"></i>
									Listagem
								</button>
							</div>
						</logic:equal>

					</div>
				</div>
				<!-- TERMINO FORMULARIO -->
				<!-- /.panel-body -->

			</div>
			<!-- /.panel -->
		</html:form>
	</div>
	<!-- /.col-lg-12 -->
</div>


<script type="text/javascript">
	$(document).ready(function() {
		/* Foco inicial */
		$( ".focoInicial" ).focus();

		/* Setando NOTNULL nos campos*/
		$("#codigo").addClass("obrigatorio");
		$("#nome").addClass("obrigatorio");
		$("#modelo").addClass("obrigatorio");

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#codigo").attr("maxlength", 20);
		$("#nome").attr("maxlength", 100);
		$("modelo").attr("maxlength", 50);
		$("#anoFabricacao").attr("maxlength", 4);
		$("#numeroChassis").attr("maxlength", 20);

		/* Setando os placeholder dos campos*/
		$( "#codigo" ).attr("placeholder","Código");
		$( "#nome" ).attr("placeholder","Nome");
		$( "#modelo" ).attr("placeholder","Modelo");
		$( "#anoFabricacao" ).attr("placeholder","Ano de Fabricação");
		$( "#numeroChassis" ).attr("placeholder","Numero do Chassis");

		/* EVENTOS */

		// Desliga o auto-complete da pagina
		$("#form_implemento").prop("autocomplete", "off");

		$('#form_implemento').on('submit', function(e) {
			abrirModalProcessando();
		});

		$('#inserir').click(function() {
			executar('form_implemento', 'inserir');
		});

		$('#alterar').click(function() {
			executar('form_implemento', 'alterar');
		});

		$('#limpar').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_implemento', 'limpar');
		});

		$('#listagem').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_implemento', 'abrirListagem');
		});
						
		$('#nome').autocomplete({
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
			var codigo = $('#codigo').val();
			var nome = $('#nome').val();		
			
			var modelo = '';
			if($('#modelo').val() != null && $('#modelo').val() != ''){
				modelo = '(' +$('#modelo').val() + ')';
			}			
			
			var anoFabricacao = $('#anoFabricacao').val();
			var numeroChassis = $('#numeroChassis').val();

			$('#nomeCompleto').val(codigo + ' ' + nome + ' ' + modelo + ' ' + anoFabricacao + ' ' + numeroChassis);	
		}
		
		montarNomeCompletoProduto();
	});
</script>