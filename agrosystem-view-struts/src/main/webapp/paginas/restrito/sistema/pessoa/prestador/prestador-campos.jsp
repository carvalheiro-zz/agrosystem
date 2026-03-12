<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!-- CAMPOS DE CADASTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -30px; margin-bottom: -15px;">
		<h1 class="page-header cabecalho_pagina">
			<i class="fa fa-graduation-cap" style="font-size: 26px;"></i>
			Cadastrar Prestadores
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Cadastro dos Prestadores
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<div class="row">
	<!-- Define o tamanho geral da prestadorServico -->
	<div class="col-md-offset-1 col-lg-offset-1 col-xs-12 col-sm-12 col-md-10 col-lg-10">
		<html:form styleId="form_prestadorServico" action="restrito/sistema/prestadorServico" method="post">
			<html:hidden property="method" value="empty" />

			<div class="panel sombra">

				<!-- INICIO FORMULARIO -->
				<div class="panel-body">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

							<div class="row">								
								<div class="form-group col-xs-12 col-sm-12 col-md-5 col-lg-5">
									<label>Nome</label>
									<html:text styleClass="form-control input-sm focoInicial" styleId="nome" property="prestadorServico.nome" name="prestadorServicoForm" />
								</div>
														
								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label>Telefone</label>
									<html:text styleClass="form-control cel input-sm" styleId="telefone" property="prestadorServico.telefone" name="prestadorServicoForm" />
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-5 col-lg-5">
									<label>Empresa</label>
									<html:text styleClass="form-control input-sm" styleId="empresa" property="prestadorServico.empresa" name="prestadorServicoForm" />
								</div>
							</div>							
						</div>
					</div>
				</div>
				<div class="panel-footer">
					<div class="row">
						<!-- BOTOES -->
						<logic:notPresent property="prestadorServico.id" name="prestadorServicoForm">
							<logic:equal name="prestadorServicoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.prestadorServico.inserir)">
								<div class="form-group col-xs-12 col-sm-3 col-md-4 col-lg-4" style="margin-bottom: 0px;">
									<button type="submit" id="inserir" class="btn btn-success btn-sm cor-sistema btn-block">
										<i class="fa fa-save"></i>
										Inserir
									</button>
								</div>
							</logic:equal>
						</logic:notPresent>
						<logic:present property="prestadorServico.id" name="prestadorServicoForm">
							<logic:equal name="prestadorServicoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.prestadorServico.alterar)">
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

						<logic:equal name="prestadorServicoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.prestadorServico.filtrar)">
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
		$("#telefone").addClass("obrigatorio");
		$("#empresa").addClass("obrigatorio");

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#nome").attr("maxlength", 100);
		$("#telefone").attr("maxlength", 30);
		$("#empresa").attr("maxlength", 100);

		/* Setando os placeholder dos campos*/
		$("#nome").attr("placeholder", "Nome");
		$("#telefone").attr("placeholder","(__) ____-____");
		$( "#empresa" ).attr("placeholder","Empresa");

		/* EVENTOS */

		// Desliga o auto-complete da pagina
		$("#form_prestadorServico").prop("autocomplete", "off");

		$('#form_prestadorServico').on('submit', function(e) {
			abrirModalProcessando();
		});

		$('#inserir').click(function() {
			executar('form_prestadorServico', 'inserir');
		});

		$('#alterar').click(function() {
			executar('form_prestadorServico', 'alterar');
		});

		$('#limpar').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_prestadorServico', 'limpar');
		});

		$('#listagem').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_prestadorServico', 'abrirListagem');
		});
		
				
		$('#nome').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'prestadorServico.nome',
			/* params : {
				'saidaGrao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/prestadorServico.src?method=selecionarPrestadorServicoAutoComplete',
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