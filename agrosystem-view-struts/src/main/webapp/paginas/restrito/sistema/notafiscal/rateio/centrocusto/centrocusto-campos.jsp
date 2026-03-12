<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!-- CAMPOS DE CADASTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -30px; margin-bottom: -15px;">
		<h1 class="page-header cabecalho_pagina">
			<i class="fa fa-graduation-cap" style="font-size: 26px;"></i>
			Cadastrar Centro de Custo
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Cadastro dos Centros de Custos
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<div class="row">
	<!-- Define o tamanho geral da centroCusto -->
	<div class="col-md-offset-1 col-lg-offset-1 col-xs-12 col-sm-12 col-md-10 col-lg-10">
		<html:form styleId="form_centroCusto" action="restrito/sistema/centroCusto" method="post">
			<html:hidden property="method" value="empty" />

			<div class="panel sombra">

				<!-- INICIO FORMULARIO -->
				<div class="panel-body">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

							<div class="row">
								<div class="form-group col-xs-12 col-sm-8 col-md-8 col-lg-3">
									<label>Código</label>
									<html:text styleClass="form-control input-sm focoInicial" styleId="codigo" property="centroCusto.codigo" name="centroCustoForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-4 col-md-4 col-lg-6">
									<label>Descrição</label>
									<html:text styleClass="form-control input-sm" styleId="descricao" property="centroCusto.descricao" name="centroCustoForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-4 col-md-4 col-lg-3">
									<label>Tipo</label>
									<html:select styleClass="form-control input-sm" styleId="tipo" property="centroCusto.tipo" name="centroCustoForm">
										<html:option value="">Selecione...</html:option>
										<html:option value="Despesa">Despesa</html:option>
										<html:option value="Investimento">Investimento</html:option>
										<html:option value="Receita">Receita</html:option>
									</html:select>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="panel-footer">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
							<div class="row">
								<!-- BOTOES -->
								<logic:notPresent property="centroCusto.id" name="centroCustoForm">
									<logic:equal name="centroCustoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.centroCusto.inserir)">
										<div class="form-group col-xs-12 col-sm-3 col-md-4 col-lg-4" style="margin-bottom: 0px;">
											<button type="submit" id="inserir" class="btn btn-success btn-sm cor-sistema btn-block">
												<i class="fa fa-save"></i>
												Inserir
											</button>
										</div>
									</logic:equal>
								</logic:notPresent>
								<logic:present property="centroCusto.id" name="centroCustoForm">
									<logic:equal name="centroCustoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.centroCusto.alterar)">
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

								<logic:equal name="centroCustoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.centroCusto.filtrar)">
									<div class="form-group col-xs-12 col-sm-3 col-md-4 col-lg-4" style="margin-bottom: 0px;">
										<button type="button" id="listagem" class="btn btn-success btn-sm cor-sistema btn-block">
											<i class="fa fa-search"></i>
											Listagem
										</button>
									</div>
								</logic:equal>
							</div>
						</div>
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

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#codigo").attr("maxlength", 50);
		$("#descricao").attr("maxlength", 255);
		$("#tipo").attr("maxlength", 50);

		/* Setando os placeholder dos campos*/
		$("#codigo").attr("placeholder", "Codigo");
		$("#descricao").attr("placeholder", "Descrição");
		$("#tipo").attr("placeholder", "Tipo");

		/* Setando NOTNULL nos campos*/
		$("#codigo").addClass("obrigatorio");
		$("#descricao").addClass("obrigatorio");
		$("#tipo").addClass("obrigatorio");

		/* EVENTOS */

		// Desliga o auto-complete da pagina
		$("#form_centroCusto").prop("autocomplete", "off");

		$('#form_centroCusto').on('submit', function(e) {
			abrirModalProcessando();
		});

		$('#inserir').click(function() {
			executar('form_centroCusto', 'inserir');
		});

		$('#alterar').click(function() {
			executar('form_centroCusto', 'alterar');
		});

		$('#limpar').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_centroCusto', 'limpar');
		});

		$('#listagem').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_centroCusto', 'abrirListagem');
		});

		$('#codigo').autocomplete({
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
	});
</script>