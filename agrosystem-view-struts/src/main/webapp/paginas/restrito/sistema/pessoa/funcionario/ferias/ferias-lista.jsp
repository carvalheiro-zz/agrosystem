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
			Pesquisar registros de férias
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Listagem dos registros de férias
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
						<html:form styleId="form_ferias_consulta" action="restrito/sistema/ferias" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">								
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<label class="text-white">Colaborador</label>
									<html:text styleClass="form-control input-sm" styleId="colaborador" property="ferias.colaborador.pessoaFisica.razaoSocial" name="feriasForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="colaboradorSelecionado" property="ferias.colaborador.id" name="feriasForm" />
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label class="text-white">Data inicial</label>
									<html:text styleClass="form-control input-sm data" styleId="dataInicial" property="dataInicial" name="feriasForm" />
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label class="text-white">Data final</label>
									<html:text styleClass="form-control input-sm data" styleId="dataFinal" property="dataFinal" name="feriasForm" />
								</div>								
								
								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label class="text-white">Tipo</label>
									<html:select styleClass="form-control input-sm" styleId="tipo" property="ferias.tipo" name="feriasForm" >
										<html:option value="">Selecione...</html:option>
										<html:option value="Adquirida">Adquirida</html:option>
										<html:option value="Cumprida">Cumprida</html:option>
										<html:option value="Vendida">Vendida</html:option>
									</html:select>
								</div>
																							
							</div>
							<div class="row">

								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.ferias.filtrar)">
									<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4" style="margin-bottom: 0px;">
										<button type="submit" id="pesquisar" class="btn btn-success btn-sm cor-sistema btn-block">
											<i class="fa fa-search"></i>
											Pesquisar
										</button>
									</div>
								</logic:equal>
								<div class="form-group col-xs-12 col-sm-3 col-md-4 col-lg-4" style="margin-bottom: 0px;">
									<button type="button" id="limparPesquisa" class="btn btn-success btn-sm cor-sistema btn-block">
										<i class="glyphicon glyphicon-erase"></i>
										Limpar
									</button>
								</div>
								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.ferias.inserir)">
									<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4" style="margin-bottom: 0px;">
										<button type="button" id="novo" class="btn btn-success btn-sm cor-sistema btn-block">
											<i class="fa fa-file-o"></i>
											Lançar
										</button>
									</div>
								</logic:equal>

							</div>
						</html:form>
					</div>
				</div>
			</div>
			<!-- TERMINO CAMPOS DE PESQUISA -->

			<!-- INICIO TABELA -->
			<logic:empty name="feriasForm" property="lista">
				<div class="row" style="margin-top: 15px;">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="alert alert-warning">Nenhum registro encontrado!</div>
					</div>
				</div>
			</logic:empty>
			<logic:notEmpty name="feriasForm" property="lista">
				<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.ferias.filtrar)">
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
								<thead>
									<!-- CABEÇALHO DA TABELA -->
									<tr class="cor-sistema" style="color: white;">
										<th>Colaborador</th>
										<th style="width: 80px;">Data</th>
										<th style="width: 160px;">Data cumprimento final</th>
										<th style="width: 80px;">Qtd. horas</th>
										<th style="width: 120px;">Tipo</th>
										<th class="text-center" style="width: 80px;">Opções</th>
									</tr>
								</thead>
								<tbody>
									<!-- TABELA -->
									<logic:iterate id="feriasCorrente" name="feriasForm" property="lista" type="br.com.srcsoftware.sistema.pessoa.funcionario.ferias.FeriasDTO">

										<tr>
											<!-- success / info / warning / danger -->
											<td onclick="selecionar('${contextPath}/restrito/sistema/ferias.src?method=selecionar&ferias.id=${feriasCorrente.id}')">${feriasCorrente.colaborador.pessoaFisica.razaoSocial}</td>
											<td onclick="selecionar('${contextPath}/restrito/sistema/ferias.src?method=selecionar&ferias.id=${feriasCorrente.id}')">${feriasCorrente.data}</td>
											<td onclick="selecionar('${contextPath}/restrito/sistema/ferias.src?method=selecionar&ferias.id=${feriasCorrente.id}')">${feriasCorrente.dataCumprimentoTermino}</td>
											<td onclick="selecionar('${contextPath}/restrito/sistema/ferias.src?method=selecionar&ferias.id=${feriasCorrente.id}')">${feriasCorrente.quantidadeHoras}</td>
											<td onclick="selecionar('${contextPath}/restrito/sistema/ferias.src?method=selecionar&ferias.id=${feriasCorrente.id}')">${feriasCorrente.tipo}</td>
											
											<td class="text-center" style="min-width: 80px; vertical-align: middle;">
												<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.ferias.excluir)">
													<button type="button" id="excluir" class="btn btn-danger btn-xs fa fa-trash" style="font-size: 14px" onclick="excluir('${feriasCorrente.colaborador.pessoaFisica.razaoSocial}', 'sistema/ferias', ${feriasCorrente.id} );"></button>
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
			<logic:notEmpty name="feriasForm" property="lista">
				<div class="panel-footer">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">

							<nav aria-label="Page navigation">
								<ul class="pagination" style="font-size: 12px;">
									<li>
										<a href="javascript://" style="cursor: default;">Encontrados: ${feriasForm.paginacao.totalRegistros} </a>
									</li>
									<logic:equal name="feriasForm" property="paginacao.voltarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="feriasForm" property="paginacao.voltarPaginacaoInativo" value="false">
										<li class="page-item">
											<a class="page-link" href="${contextPath}/restrito/sistema/ferias.src?method=voltar&forwardDestino=feriasLista" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>

									<c:forEach var="i" begin="${feriasForm.paginacao.paginaInicial}" end="${feriasForm.paginacao.paginaFinal}" step="1" varStatus="row">
										<c:if test="${feriasForm.paginacao.paginaCorrente == i}">
											<li class="page-item active">
										</c:if>

										<c:if test="${feriasForm.paginacao.paginaCorrente != i}">
											<li class="page-item ">
										</c:if>

										<a class="page-link" href="${contextPath}/restrito/sistema/ferias.src?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>
										</li>
									</c:forEach>

									<logic:equal name="feriasForm" property="paginacao.avancarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Next">
												<span aria-hidden="true">&raquo;</span>
												<span class="sr-only">Próximo</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="feriasForm" property="paginacao.avancarPaginacaoInativo" value="false">
										<li class="page-item ">
											<a class="page-link" href="${contextPath}/restrito/sistema/ferias.src?method=avancar&forwardDestino=feriasLista" aria-label="Next">
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
	$(document).ready(function(){
		/* Foco inicial */
		$( "#colaborador" ).focus();

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#colaborador").prop("maxlength", 50);

		/* Setando os placeholder dos campos*/
		$( "#colaborador" ).prop("placeholder","Nome a ser pesquisado");
		
		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_ferias_consulta").prop("autocomplete", "off");
		
		$('#form_ferias_consulta').on('submit', function(e){
	        abrirModalProcessando();
	    });
		
		$( '#pesquisar' ).click(function() {  	
			executar('form_ferias_consulta','filtrar');
		});
		
		$( '#limparPesquisa' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_ferias_consulta','limparFiltro');
		});
		
		$( '#novo' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_ferias_consulta','abrirCadastro');
		});
		
		$('#colaborador').keyup(function(e) {
			if ($('#colaborador').val() == '') {
				$('#colaboradorSelecionado').val(null);
			}
		});
		$('#colaborador').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'ferias.colaborador.pessoaFisica.razaoSocial',
			serviceUrl : '${contextPath}/restrito/sistema/ferias.src?method=selecionarColaboradorAutoComplete',
			onSelect : function(suggestion) {
				$('#colaboradorSelecionado').val(suggestion.data);
			},
			onSearchComplete : function(query, suggestions) {
				$('#colaboradorSelecionado').val(null);
			}
		});
	});
	
	function selecionar(urlAction) {
		abrirModalProcessando();
		window.location = urlAction;
	}
</script>