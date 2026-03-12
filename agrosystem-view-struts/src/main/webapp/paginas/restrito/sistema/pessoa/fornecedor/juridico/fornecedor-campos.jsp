<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!-- CAMPOS DE CADASTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -30px; margin-bottom: -15px;">
		<h1 class="page-header cabecalho_pagina">
			<i class="fa fa-graduation-cap" style="font-size: 26px;"></i>
			Cadastrar Fornecedores
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Cadastro dos Fornecedores
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<div class="row">
	<!-- Define o tamanho geral da fornecedorJuridico -->
	<div class="col-md-offset-2 col-lg-offset-2 col-xs-12 col-sm-12 col-md-8 col-lg-8">
		<html:form styleId="form_fornecedorJuridico" action="restrito/sistema/fornecedorJuridico" method="post">
			<html:hidden property="method" value="empty" />

			<div class="panel sombra">

				<!-- INICIO FORMULARIO -->
				<div class="panel-body">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<label>Nome</label>
									<html:text styleClass="form-control input-sm focoInicial" styleId="nome" property="fornecedorJuridico.nome" name="fornecedorJuridicoForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-5 col-lg-5">
									<label>Telefone</label>
									<html:text styleClass="form-control cel input-sm" styleId="telefone" property="fornecedorJuridico.telefone" name="fornecedorJuridicoForm" />
								</div>
								<%-- <div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label>Celular</label>
									<html:text styleClass="form-control cel input-sm" styleId="celular" property="fornecedorJuridico.celular" name="fornecedorJuridicoForm" />
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label>Fixo</label>
									<html:text styleClass="form-control fixo input-sm" styleId="fixo" property="fornecedorJuridico.fixo" name="fornecedorJuridicoForm" />
								</div> --%>

								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<label>Endereço</label>
									<html:text styleClass="form-control input-sm" styleId="endereco" property="fornecedorJuridico.endereco" name="fornecedorJuridicoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<label>Observação</label>
									<html:text styleClass="form-control input-sm" styleId="observacao" property="fornecedorJuridico.observacao" name="fornecedorJuridicoForm" />
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="panel-footer">
					<div class="row">
						<!-- BOTOES -->
						<logic:notPresent property="fornecedorJuridico.id" name="fornecedorJuridicoForm">
							<logic:equal name="fornecedorJuridicoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.fornecedorJuridico.inserir)">
								<div class="form-group col-xs-12 col-sm-3 col-md-4 col-lg-4" style="margin-bottom: 0px;">
									<button type="submit" id="inserir" class="btn btn-success btn-sm cor-sistema btn-block">
										<i class="fa fa-save"></i>
										Inserir
									</button>
								</div>
							</logic:equal>
						</logic:notPresent>
						<logic:present property="fornecedorJuridico.id" name="fornecedorJuridicoForm">
							<logic:equal name="fornecedorJuridicoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.fornecedorJuridico.alterar)">
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

						<logic:equal name="fornecedorJuridicoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.fornecedorJuridico.filtrar)">
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
		$(".focoInicial").focus();

		/* Setando NOTNULL nos campos*/
		$("#nome").addClass("obrigatorio");
		$("#telefone").addClass("obrigatorio");
		//$("#celular").addClass("obrigatorio");

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#nome").attr("maxlength", 50);
		$("#endereco").attr("maxlength", 255);
		$("#observacao").attr("maxlength", 255);

		/* Setando os placeholder dos campos*/
		$("#nome").attr("placeholder", "Nome");
		$("#endereco").attr("placeholder", "Rua/Av, nº Bairro CEP Cidade/UF");
		$("#observacao").attr("placeholder", "Observação");

		/* EVENTOS */

		// Desliga o auto-complete da pagina
		$("#form_fornecedorJuridico").prop("autocomplete", "off");

		$('#form_fornecedorJuridico').on('submit', function(e) {
			abrirModalProcessando();
		});

		$('#inserir').click(function() {
			executar('form_fornecedorJuridico', 'inserir');
		});

		$('#alterar').click(function() {
			executar('form_fornecedorJuridico', 'alterar');
		});

		$('#limpar').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_fornecedorJuridico', 'limpar');
		});

		$('#listagem').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_fornecedorJuridico', 'abrirListagem');
		});

		$('#nome').autocomplete({
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
	});
</script>