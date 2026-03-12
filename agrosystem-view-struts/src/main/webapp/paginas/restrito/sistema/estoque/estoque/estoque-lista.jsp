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
			Visualizar Estoque
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Listagem dos Produtos em estoque
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<div class="row">

	<!-- Define o tamanho geral da tela -->
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel" style="box-shadow: 0 10px 30px rgba(0, 0, 0, 0.8); border-color: #909090;">

			<!-- INICIO CAMPOS DE PESQUISA -->
			<div class="panel-heading cor-sistema">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_estoque_consulta" action="restrito/sistema/estoque" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">

								<div class="form-group col-xs-12 col-sm-12 col-md-8 col-lg-8">
									<label class="text-white">Produto</label>
									<html:text styleClass="form-control input-sm focoInicial" styleId="nomeProduto" property="nomeProduto" name="estoqueForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<label class="text-white">Localização</label>
									<html:text styleClass="form-control input-sm " styleId="localizacaoProduto" property="localizacaoProduto" name="estoqueForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-3">
									<label class="text-white">Marca</label>
									<html:text styleClass="form-control input-sm " styleId="nomeMarca" property="nomeMarca" name="estoqueForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="marcaSelecionada" property="idMarca" name="estoqueForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-3">
									<label class="text-white">Categoria</label>
									<html:text styleClass="form-control input-sm " styleId="nomeTipo" property="nomeTipo" name="estoqueForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="tipoSelecionado" property="idTipo" name="estoqueForm" />
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-3">
									<label class="text-white">Exibir apenas negativos (-)</label>
									<html:select styleClass="form-control input-sm " styleId="negativos" property="negativos" name="estoqueForm" >
										<html:option value="false">Não</html:option>
										<html:option value="true">Sim</html:option>
									</html:select>
								</div>

							</div>
							<div class="row">

								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.estoque.filtrar)">
									<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4" style="margin-bottom: 0px;">
										<button type="submit" id="pesquisar" class="btn btn-success btn-sm cor-sistema btn-block">
											<i class="fa fa-search"></i>
											Pesquisar
										</button>
									</div>
								</logic:equal>
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4" style="margin-bottom: 0px;">
									<button type="button" id="limparPesquisa" class="btn btn-success btn-sm cor-sistema btn-block">
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
			<logic:empty name="estoqueForm" property="estoques">
				<div class="row" style="margin-top: 15px;">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="alert alert-warning">Nenhum registro encontrado!</div>
					</div>
				</div>
			</logic:empty>
			<logic:notEmpty name="estoqueForm" property="estoques">
				<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.estoque.filtrar)">
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
								<thead>
									<!-- CABEÇALHO DA TABELA -->
									<tr class="cor-sistema" style="color: white;">
										<th style="min-width: 400px;">Produto</th>
										<th class="text-center">Localização</th>
										<th class="text-right" style="width: 130px; min-width: 130px;">R$ Custo médio</th>
										<th class="text-right" style="width: 150px; min-width: 150px; font-size: 16px; color: greenyellow;">Quantidade</th>
										<th class="text-right" style="width: 150px; min-width: 150px; font-size: 16px; color: rgb(255, 120, 0);">Entregar</th>
									</tr>
								</thead>
								<tbody>
									<!-- TABELA -->
									<logic:iterate id="estoqueCorrente" name="estoqueForm" property="estoques" type="br.com.srcsoftware.sistema.estoque.estoque.pojo.EstoquePOJO">

										<tr class="${estoqueCorrente.corAlerta}" style="font-size: 12px; font-weight: bold;cursor: default;">
											
											<td style="vertical-align: middle;">${estoqueCorrente.nomeCompletoProduto}</td>

											<td style="vertical-align: middle;" class="text-center">${estoqueCorrente.localizacao}</td>

											<td style="vertical-align: middle; color: red" class="text-right has-danger">R$ ${estoqueCorrente.custoMedio}</td>

											<td style="vertical-align: middle; font-size: 14px;" class="text-right">${estoqueCorrente.saldo} ${estoqueCorrente.unidadeMedida}
												<i class="fa fa-building-o" style="color: black;"></i>
											</td>

											<td style="vertical-align: middle; font-size: 14px; color: rgb(255, 120, 0);" class="text-right">
												${estoqueCorrente.restantes}
												<i class="fa fa-truck" style="color: black;"></i>
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
			
			
			<!-- INICIO PAGINAÇÃO -->
			<logic:notEmpty name="estoqueForm" property="estoques">
				<div class="panel-footer">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">

							<nav aria-label="Page navigation">
								<ul class="pagination" style="font-size: 12px;">
									<li>
										<a href="javascript://" style="cursor: default;">Encontrados: ${estoqueForm.paginacao.totalRegistros} </a>
									</li>
									<logic:equal name="estoqueForm" property="paginacao.voltarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="estoqueForm" property="paginacao.voltarPaginacaoInativo" value="false">
										<li class="page-item">
											<a class="page-link" href="${contextPath}/restrito/sistema/estoque.src?method=voltar&forwardDestino=estoqueLista" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>

									<c:forEach var="i" begin="${estoqueForm.paginacao.paginaInicial}" end="${estoqueForm.paginacao.paginaFinal}" step="1" varStatus="row">
										<c:if test="${estoqueForm.paginacao.paginaCorrente == i}">
											<li class="page-item active">
										</c:if>

										<c:if test="${estoqueForm.paginacao.paginaCorrente != i}">
											<li class="page-item ">
										</c:if>

										<a class="page-link" href="${contextPath}/restrito/sistema/estoque.src?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>
										</li>
									</c:forEach>

									<logic:equal name="estoqueForm" property="paginacao.avancarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Next">
												<span aria-hidden="true">&raquo;</span>
												<span class="sr-only">Próximo</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="estoqueForm" property="paginacao.avancarPaginacaoInativo" value="false">
										<li class="page-item ">
											<a class="page-link" href="${contextPath}/restrito/sistema/estoque.src?method=avancar&forwardDestino=estoqueLista" aria-label="Next">
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
			
			
			
			<!-- /.panel-body -->

		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>


<script type="text/javascript">
	$(document).ready(function() {
		/* Foco inicial */
		$( ".focoInicial" ).focus(); 

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#nomeProduto").attr("maxlength", 50);
		$("#nomeMarca").attr("maxlength", 50);
		$("#nomeTipo").attr("maxlength", 50);
		$("#localizacaoProduto").attr("maxlength", 10);

		/* Setando os placeholder dos campos*/
		$("#nomeProduto").attr("placeholder", "Produto a ser pesquisado");
		$("#nomeMarca").attr("placeholder", "Marca a ser pesquisada");
		$("#nomeTipo").attr("placeholder", "Categoria a ser pesquisada");
		$("#localizacaoProduto").attr("placeholder", "Localização a ser pesquisada");

		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_estoque_consulta").attr("autocomplete", "off");

		$('#form_estoque_consulta').on('submit', function(e) {
			abrirModalProcessando();
		});

		$('#pesquisar').click(function() {
			executar('form_estoque_consulta', 'filtrar');
		});

		$('#limparPesquisa').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_estoque_consulta', 'limparFiltro');
		});

		$('#nomeProduto').autocomplete({
			minChars : 2,
			paramName : 'nomeProduto',
			serviceUrl : '${contextPath}/restrito/sistema/estoque.src?method=selecionarProdutoAutoComplete',
			onSelect : function(suggestion) {
				
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					
				}
			}
		});
		
		$('#nomeMarca').autocomplete({
			minChars : 2,
			paramName : 'nomeMarca',
			serviceUrl : '${contextPath}/restrito/sistema/estoque.src?method=selecionarMarcaAutoComplete',
			onSelect : function(suggestion) {
				$('#marcaSelecionada').val(suggestion.data);
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#marcaSelecionada').val(null);
				}
			}
		});

		$('#nomeTipo').autocomplete({
			minChars : 2,
			paramName : 'nomeTipo',
			serviceUrl : '${contextPath}/restrito/sistema/estoque.src?method=selecionarTipoAutoComplete',
			onSelect : function(suggestion) {
				$('#tipoSelecionado').val(suggestion.data);
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#tipoSelecionado').val(null);
				}
			}
		});
	});
</script>