<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!-- CAMPOS DE CADASTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -30px; margin-bottom: -15px;">
		<h1 class="page-header cabecalho_pagina">
			<i class="fa fa-graduation-cap" style="font-size: 26px;"></i>
			Cadastrar Marcas
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Cadastro das Marcas
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<div class="row">
	<!-- Define o tamanho geral da marca -->
	<div class="col-md-offset-1 col-lg-offset-2 col-xs-12 col-sm-12 col-md-6 col-lg-6">
		<html:form styleId="form_marca" action="restrito/sistema/marca" method="post">
			<html:hidden property="method" value="empty" />

			<div class="panel sombra">

				<!-- INICIO FORMULARIO -->
				<div class="panel-body">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

							<div class="row">

								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<label>Nome</label>
									<html:text styleClass="form-control input-sm focoInicial" styleId="nome" property="marca.nome" name="marcaForm" />
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="panel-footer">
					<div class="row">
						<!-- BOTOES -->
						<logic:notPresent property="marca.id" name="marcaForm">
							<logic:equal name="marcaForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.marca.inserir)">
								<div class="form-group col-xs-12 col-sm-3 col-md-4 col-lg-4" style="margin-bottom: 0px;">
									<button type="submit" id="inserir" class="btn btn-success btn-sm cor-sistema btn-block">
										<i class="fa fa-save"></i>
										Inserir
									</button>
								</div>
							</logic:equal>
						</logic:notPresent>
						<logic:present property="marca.id" name="marcaForm">
							<logic:equal name="marcaForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.marca.alterar)">
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

						<logic:equal name="marcaForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.marca.filtrar)">
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
		$("#nome").addClass("obrigatorio");

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#nome").prop("maxlength", 50);

		/* Setando os placeholder dos campos*/
		$("#nome").prop("placeholder", "Nome");

		/* EVENTOS */

		// Desliga o auto-complete da pagina
		$("#form_marca").prop("autocomplete", "off");

		$('#form_marca').on('submit', function(e) {
			abrirModalProcessando();
		});

		$('#inserir').click(function() {
			executar('form_marca', 'inserir');
		});

		$('#alterar').click(function() {
			executar('form_marca', 'alterar');
		});

		$('#limpar').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_marca', 'limpar');
		});

		$('#listagem').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_marca', 'abrirListagem');
		});
				
		$('#nome').autocomplete({
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
	});
</script>