<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- CAMPOS DE FILTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -30px; margin-bottom: -15px;">
		<h1 class="page-header cabecalho_pagina">
			<i class="fa fa-graduation-cap" style="font-size: 26px;"></i>
			Pesquisar Caixas
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Listagem das aberturas/fechamentos dos caixas
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<div class="row">

	<!-- Define o tamanho geral da tela -->
	<div class="col-md-offset-1 col-lg-offset-1 col-xs-12 col-sm-12 col-md-10 col-lg-10">
		<div class="panel" style="box-shadow: 0 10px 30px rgba(0, 0, 0, 0.8); border-color: #909090;">

			<!-- INICIO CAMPOS DE PESQUISA -->
			<div class="panel-heading cor-sistema">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_controleCaixa_consulta" action="restrito/sistema/controleCaixa" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<label class="text-white">Funcionário</label>
									<html:text styleClass="form-control input-sm" styleId="funcionarioCaixa" property="controleCaixaFilter.funcionarioCaixa.pessoaFisica.razaoSocial" name="controleCaixaForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="funcionarioCaixaSelecionado" property="controleCaixaFilter.funcionarioCaixa.id" name="controleCaixaForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label class="text-white">Data Abertura Inicial</label>
									<html:text styleClass="form-control input-sm data text-center" styleId="dataAberturaInicial" property="dataAberturaInicial" name="controleCaixaForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label class="text-white">Data Abertura Final</label>
									<html:text styleClass="form-control input-sm data text-center" styleId="dataAberturaFinal" property="dataAberturaFinal" name="controleCaixaForm" />
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.controleCaixa.filtrar)">
										<button type="submit" id="pesquisar" class="btn btn-success btn-sm cor-sistema">
											<i class="fa fa-search"></i>
											Pesquisar
										</button>
									</logic:equal>
									<button type="button" id="limparPesquisa" class="btn btn-success btn-sm cor-sistema">
										<i class="glyphicon glyphicon-erase"></i>
										Limpar
									</button>
								</div>
							</div>
						</html:form>
					</div>
				</div>
			</div>
			<!-- TERMINO CAMPOS DE PESQUISA -->

			<!-- INICIO TABELA -->
			<logic:empty name="controleCaixaForm" property="controlesCaixa">
				<div class="row" style="margin-top: 15px;">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="alert alert-warning">Nenhum registro encontrado!</div>
					</div>
				</div>
			</logic:empty>
			<logic:notEmpty name="controleCaixaForm" property="controlesCaixa">
				<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.controleCaixa.filtrar)">
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-bordered table-striped" style="font-size: 12px;">
								<thead>
									<!-- CABEÇALHO DA TABELA -->
									<tr class="cor-sistema" style="color: white;">
										<th>Funcionario</th>
										<th class="text-center" >Abertura</th>
										<th class="text-right">R$ Abertura</th>
										<th class="text-center">Fechamento</th>
										<th class="text-right">R$ Fechamento</th>
										<th class="text-center" style="width: 80px;">Opções</th>
									</tr>
								</thead>
								<tbody>
									<!-- TABELA -->
									<logic:iterate id="controleCaixaCorrente" name="controleCaixaForm" property="controlesCaixa" type="br.com.srcsoftware.modular.financeiro.caixa.aberturafechamento.ControleCaixaDTO">

										<tr>
											<!-- success / info / warning / danger -->
											<td>${controleCaixaCorrente.funcionarioCaixa.pessoaFisica.razaoSocial}</td>
											<td class="text-center">${controleCaixaCorrente.dataAbertura}&nbsp;${controleCaixaCorrente.horaAbertura}</td>
											<td class="text-right">${controleCaixaCorrente.valorAbertura}</td>
											<td class="text-center">${controleCaixaCorrente.dataFechamento}&nbsp;${controleCaixaCorrente.horaFechamento}</td>
											<td class="text-right">${controleCaixaCorrente.valorBruto}</td>

											<td class="text-center" style="min-width: 80px; vertical-align: middle;">
												<logic:present name="controleCaixaCorrente" property="dataFechamento">
													<a class="btn btn-primary btn-sm fa fa-file-pdf-o" style="font-size: 17px; padding: 3px 3px;" target="_blank"
														href='${contextPath}/restrito/sistema/controleCaixa.src?method=gerarDetalhadoPDF&controleCaixaFilter.id=${controleCaixaCorrente.id}'></a>
												</logic:present>
												
												<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.formaPagamento.excluir)">
													<button type="button" id="excluir" class="btn btn-danger btn-sm fa fa-trash" style="font-size: 17px; padding: 3px 3px;" onclick="excluir('${controleCaixaCorrente.funcionarioCaixa.pessoaFisica.razaoSocial}', 'sistema/controleCaixa', ${controleCaixaCorrente.id} );"></button>
												</logic:equal>
											</td>
										</tr>
									</logic:iterate>
								</tbody>
							</table>
						</div>
					</div>
				</logic:equal>
			</logic:notEmpty>
			<!-- TERMINO TABELA -->
			<!-- /.panel-body -->

			<!-- INICIO PAGINAÇÃO -->
			<logic:notEmpty name="controleCaixaForm" property="controlesCaixa">
				<div class="panel-footer">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">

							<nav aria-label="Page navigation">
								<ul class="pagination" style="font-size: 12px;">
									<li>
										<a href="javascript://" style="cursor: default;">Encontrados: ${controleCaixaForm.paginacao.totalRegistros} </a>
									</li>
									<logic:equal name="controleCaixaForm" property="paginacao.voltarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="controleCaixaForm" property="paginacao.voltarPaginacaoInativo" value="false">
										<li class="page-item">
											<a class="page-link" href="${contextPath}/restrito/sistema/controleCaixa.src?method=voltar&forwardDestino=controleCaixaLista" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>

									<c:forEach var="i" begin="${controleCaixaForm.paginacao.paginaInicial}" end="${controleCaixaForm.paginacao.paginaFinal}" step="1" varStatus="row">
										<c:if test="${controleCaixaForm.paginacao.paginaCorrente == i}">
											<li class="page-item active">
										</c:if>

										<c:if test="${controleCaixaForm.paginacao.paginaCorrente != i}">
											<li class="page-item ">
										</c:if>

										<a class="page-link" href="${contextPath}/restrito/sistema/controleCaixa.src?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>
										</li>
									</c:forEach>

									<logic:equal name="controleCaixaForm" property="paginacao.avancarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Next">
												<span aria-hidden="true">&raquo;</span>
												<span class="sr-only">Próximo</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="controleCaixaForm" property="paginacao.avancarPaginacaoInativo" value="false">
										<li class="page-item ">
											<a class="page-link" href="${contextPath}/restrito/sistema/controleCaixa.src?method=avancar&forwardDestino=controleCaixaLista" aria-label="Next">
												<span aria-hidden="true">&raquo;</span>
												<span class="sr-only">Próximo</span>
											</a>
										</li>
									</logic:equal>
								</ul>
							</nav>
						</div>
					</div>
				</div>
			</logic:notEmpty>
			<!-- TERMINO PAGINAÇÃO -->

		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>


<script type="text/javascript">
	$(document).ready(function() {
		/* Foco inicial */
		//$( "#nome" ).focus();
		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		//$("#nome").attr("maxlength", 50);
		/* Setando os placeholder dos campos*/
		//$( "#nome" ).attr("placeholder","Nome a ser pesquisado");
		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_controleCaixa_consulta").attr("autocomplete", "off");

		$('#form_controleCaixa_consulta').on('submit', function(e) {
			abrirModalProcessando();
		});

		$('#pesquisar').click(function() {
			executar('form_controleCaixa_consulta', 'filtrar');
		});

		$('#limparPesquisa').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_controleCaixa_consulta', 'limparFiltro');
		});

		$('#gerarResumoPDF').click(function() {
			gerarRelatorio('form_controleCaixa', 'gerarResumoPDF');
		});

		$('#funcionarioCaixa').keyup(function(e) {
			if ($('#funcionarioCaixa').val() == '') {
				$('#funcionarioCaixaSelecionado').val(null);
			}
		});

		$('#funcionarioCaixa').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'funcionarioFilter.pessoaFisica.razaoSocial',
			serviceUrl : '${contextPath}/restrito/admin/funcionario.src?method=selecionarFuncionarioAutoComplete',
			onSelect : function(suggestion) {
				$('#funcionarioCaixaSelecionado').val(suggestion.data);

				$("#funcionarioCaixa").focus();
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#funcionarioCaixaSelecionado').val(null);
				}
			}

		});
		
		$("#dataAberturaInicial").focusout(function() {
			validarData('dataAberturaInicial');
		});	
		$("#dataAberturaFinal").focusout(function() {
			validarData('dataAberturaFinal');
		});		

	});

	function selecionar(urlAction) {
		abrirModalProcessando();
		window.location = urlAction;
	}
</script>