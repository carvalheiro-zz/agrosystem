<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!-- CAMPOS DE FILTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<h1 class="page-header cabecalho_pagina" style="margin: 15px 0 10px;">
			<i class="fa fa-newspaper-o fa-fw" style="font-size: 26px;"></i>
			Pesquisar Notas Fiscais de Vendas Diretas
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Listagem das Notas Fiscais de Vendas Diretas
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<div class="row">

	<!-- Define o tamanho geral da notaFiscalVendaDireta -->
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel" style="box-shadow: 0 10px 30px rgba(0, 0, 0, 0.8); border-color: #909090;">

			<!-- INICIO CAMPOS DE PESQUISA -->
			<div class="panel-heading cor-sistema">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_notaFiscalVendaDireta_consulta" action="restrito/sistema/notaFiscalVendaDireta" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<div class="form-group col-xs-12 col-sm-3 col-md-2 col-lg-2">
									<label class="text-white">Número</label>
									<html:text styleClass="form-control input-sm" styleId="numero" property="notaFiscalVendaDireta.numero" name="notaFiscalVendaDiretaForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-4 col-md-4 col-lg-4">
									<label class="text-white">Fornecedor</label>
									<html:text styleClass="form-control input-sm" styleId="fornecedor" property="notaFiscalVendaDireta.fornecedor.nome" name="notaFiscalVendaDiretaForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
								</div>
								<div class="form-group col-xs-12 col-sm-3 col-md-2 col-lg-2">
									<label class="text-white">Status</label>
									<html:select styleClass="form-control input-sm" styleId="status" property="notaFiscalVendaDireta.status" name="notaFiscalVendaDiretaForm">
										<html:option value="">Selecione...</html:option>
										<html:option value="ABERTO">ABERTO</html:option>
										<html:option value="ANDAMENTO">ANDAMENTO</html:option>
										<html:option value="FINALIZADO">FINALIZADO</html:option>
									</html:select>
								</div>
								<div class="form-group col-xs-12 col-sm-4 col-md-3 col-lg-2">
									<label class="text-white">Data Inicial</label>
									<html:text styleClass="form-control input-sm data text-center" styleId="dataInicial" property="dataInicial" name="notaFiscalVendaDiretaForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-4 col-md-3 col-lg-2">
									<label class="text-white">Data Final</label>
									<html:text styleClass="form-control input-sm data text-center" styleId="dataFinal" property="dataFinal" name="notaFiscalVendaDiretaForm" />
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<label class="text-white">Produto</label>
									<html:text styleClass="form-control input-sm" styleId="produto" property="itemPesquisar.produto.nomeCompleto" name="notaFiscalVendaDiretaForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="produtoSelecionado" property="itemPesquisar.produto.id" name="notaFiscalVendaDiretaForm" />
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<div class="row">
										<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.notaFiscalVendaDireta.filtrar)">
											<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<button type="submit" id="pesquisar" class="btn btn-success btn-sm cor-sistema btn-block">
													<i class="fa fa-search"></i>
													Pesquisar
												</button>
											</div>
										</logic:equal>
										<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<button type="button" id="limparPesquisa" class="btn btn-success btn-sm cor-sistema btn-block">
												<i class="glyphicon glyphicon-erase"></i>
												Limpar
											</button>
										</div>
										<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.notaFiscalVendaDireta.inserir)">
											<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
												<button type="button" id="novo" class="btn btn-success btn-sm cor-sistema btn-block">
													<i class="fa fa-file-o"></i>
													Lançar
												</button>
											</div>
										</logic:equal>
									</div>
								</div>
							</div>
						</html:form>
					</div>
				</div>
			</div>
			<!-- TERMINO CAMPOS DE PESQUISA -->

			<!-- INICIO TABELA -->
			<logic:empty name="notaFiscalVendaDiretaForm" property="notas">
				<div class="row" style="margin-top: 15px;">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="alert alert-warning">Nenhum registro encontrado!</div>
					</div>
				</div>
			</logic:empty>
			<logic:notEmpty name="notaFiscalVendaDiretaForm" property="notas">
				<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.notaFiscalVendaDireta.filtrar)">
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
								<thead>
									<!-- CABEÇALHO DA TABELA -->
									<tr class="cor-sistema" style="color: white;">
										<th class="text-center">NF</th>
										<th class="text-center">Recibo</th>
										<th class="text-center">Data</th>
										<th>Fornecedor</th>
										<th class="text-center">Qtd. Itens</th>
										<th class="text-center">Total Itens</th>
										<th class="text-center">Valor Total</th>
										<!-- <th class="text-center" style="width: 80px;">Status</th> -->
										<th class="text-center" style="width: 80px;">Opções</th>
									</tr>
								</thead>
								<tbody>
									<!-- TABELA -->
									<logic:iterate id="notaFiscalVendaDiretaCorrente" name="notaFiscalVendaDiretaForm" property="notas" type="br.com.srcsoftware.sistema.notafiscal.direta.NotaFiscalVendaDiretaDTO">

										<%-- <logic:equal name="notaFiscalVendaDiretaCorrente" value="ABERTO" property="status">
											<tr>
										</logic:equal>
										<logic:equal name="notaFiscalVendaDiretaCorrente" value="FINALIZADO" property="status">
											<tr style="background-color: rgba(0, 18, 230, 0.41)">
										</logic:equal>
										<logic:equal name="notaFiscalVendaDiretaCorrente" value="ANDAMENTO" property="status">
											<tr style="background-color: rgba(255, 255, 0, 0.41)">
										</logic:equal> --%>
										<tr>
											<!-- success / info / warning / danger -->
											<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalVendaDireta.src?method=selecionar&notaFiscalVendaDireta.id=${notaFiscalVendaDiretaCorrente.id}')">${notaFiscalVendaDiretaCorrente.numero}</td>
											<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalVendaDireta.src?method=selecionar&notaFiscalVendaDireta.id=${notaFiscalVendaDiretaCorrente.id}')">${notaFiscalVendaDiretaCorrente.numeroRecibo}</td>
											<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalVendaDireta.src?method=selecionar&notaFiscalVendaDireta.id=${notaFiscalVendaDiretaCorrente.id}')">
												${notaFiscalVendaDiretaCorrente.dataToString}</td>
											<td onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalVendaDireta.src?method=selecionar&notaFiscalVendaDireta.id=${notaFiscalVendaDiretaCorrente.id}')">
												${notaFiscalVendaDiretaCorrente.fornecedor.nome}&nbsp;-&nbsp;${notaFiscalVendaDiretaCorrente.fornecedor.telefone}</td>
											<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalVendaDireta.src?method=selecionar&notaFiscalVendaDireta.id=${notaFiscalVendaDiretaCorrente.id}')">
												${notaFiscalVendaDiretaCorrente.quantidadeItens}</td>
											<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalVendaDireta.src?method=selecionar&notaFiscalVendaDireta.id=${notaFiscalVendaDiretaCorrente.id}')">
												${notaFiscalVendaDiretaCorrente.quantidadeTotalItens}</td>
												
											<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalVendaDireta.src?method=selecionar&notaFiscalVendaDireta.id=${notaFiscalVendaDiretaCorrente.id}')">
												${notaFiscalVendaDiretaCorrente.valorCustoTotal}</td>	
												
											<%-- <td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalVendaDireta.src?method=selecionar&notaFiscalVendaDireta.id=${notaFiscalVendaDiretaCorrente.id}')">
												<label>${notaFiscalVendaDiretaCorrente.status}</label>
											</td> --%>
											<td class="text-center" style="min-width: 80px; vertical-align: middle;">
												<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.notaFiscalVendaDireta.excluir)">
													<button type="button" id="excluir" class="btn btn-danger btn-xs fa fa-trash" style="font-size: 14px"
														onclick="excluir('Nº ${notaFiscalVendaDiretaCorrente.id} - Fornecedor: ${notaFiscalVendaDiretaCorrente.fornecedor.nome}', 'sistema/notaFiscalVendaDireta', ${notaFiscalVendaDiretaCorrente.id} );"></button>
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
			<logic:notEmpty name="notaFiscalVendaDiretaForm" property="notas">
				<div class="panel-footer">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">

							<nav aria-label="Page navigation">
								<ul class="pagination" style="font-size: 12px;">
									<li>
										<a href="javascript://" style="cursor: default;">Encontrados: ${notaFiscalVendaDiretaForm.paginacao.totalRegistros} </a>
									</li>
									<logic:equal name="notaFiscalVendaDiretaForm" property="paginacao.voltarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="notaFiscalVendaDiretaForm" property="paginacao.voltarPaginacaoInativo" value="false">
										<li class="page-item">
											<a class="page-link" href="${contextPath}/restrito/sistema/notaFiscalVendaDireta.src?method=voltar&forwardDestino=notaFiscalVendaDiretaLista" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>

									<c:forEach var="i" begin="${notaFiscalVendaDiretaForm.paginacao.paginaInicial}" end="${notaFiscalVendaDiretaForm.paginacao.paginaFinal}" step="1" varStatus="row">
										<c:if test="${notaFiscalVendaDiretaForm.paginacao.paginaCorrente == i}">
											<li class="page-item active">
										</c:if>

										<c:if test="${notaFiscalVendaDiretaForm.paginacao.paginaCorrente != i}">
											<li class="page-item ">
										</c:if>

										<a class="page-link" href="${contextPath}/restrito/sistema/notaFiscalVendaDireta.src?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>
										</li>
									</c:forEach>

									<logic:equal name="notaFiscalVendaDiretaForm" property="paginacao.avancarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Next">
												<span aria-hidden="true">&raquo;</span>
												<span class="sr-only">Próximo</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="notaFiscalVendaDiretaForm" property="paginacao.avancarPaginacaoInativo" value="false">
										<li class="page-item ">
											<a class="page-link" href="${contextPath}/restrito/sistema/notaFiscalVendaDireta.src?method=avancar&forwardDestino=notaFiscalVendaDiretaLista" aria-label="Next">
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
		$( "#numero" ).focus();

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#numero").prop("maxlength", 50);
		$("#fornecedor").prop("maxlength", 50);
		$("#produto").attr("maxlength", 100);

		/* Setando os placeholder dos campos*/
		$( "#numero" ).prop("placeholder","Número a ser pesquisado");
		$( "#fornecedor" ).prop("placeholder","Fornecedor a ser pesquisado");
		$( "#produto" ).prop("placeholder","Produto a ser pesquisado");
		
		// Desliga o auto-complete da pagina
		$("#form_notaFiscalVendaDireta_consulta").prop("autocomplete", "off");

		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_notaFiscalVendaDireta_consulta").prop("autocomplete", "off");
		
		$('#form_notaFiscalVendaDireta_consulta').on('submit', function(e){
	        abrirModalProcessando();
	    });
		
		$( '#pesquisar' ).click(function() {  	
			executar('form_notaFiscalVendaDireta_consulta','filtrar');
		});
		
		$( '#limparPesquisa' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_notaFiscalVendaDireta_consulta','limparFiltro');
		});
		
		$( '#novo' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_notaFiscalVendaDireta_consulta','abrirCadastro');
		});
		
		$('#fornecedor').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'notaFiscalVendaDireta.fornecedor.nome',
			serviceUrl : '${contextPath}/restrito/sistema/notaFiscalVendaDireta.src?method=selecionarFornecedorAutoComplete',
			onSelect : function(suggestion) {
				//alert('You selected: ' + suggestion.value + ', ' + suggestion.data);
				
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					
				}
			}
		});
		
		$('#produto').keyup(function() {
			if ($('#produto').val() == null || $('#produto').val() == '') {
				
			}
		});
		$('#produto').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'produto.nomeCompleto',
			serviceUrl : '${contextPath}/restrito/sistema/produto.src?method=selecionarProdutoAutoComplete',
			onSelect : function(suggestion) {
				
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					
				}
			}
		});
	});
	
	function selecionar(urlAction) {
		abrirModalProcessando();
		window.location = urlAction;
	}
</script>


