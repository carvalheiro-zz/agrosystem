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
			Pesquisar Notas Fiscais de Simples Remessa
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Listagem das Notas Fiscais de Simples Remessa
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<div class="row">

	<!-- Define o tamanho geral da notaFiscalSimplesRemessa -->
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel" style="box-shadow: 0 10px 30px rgba(0, 0, 0, 0.8); border-color: #909090;">

			<!-- INICIO CAMPOS DE PESQUISA -->
			<div class="panel-heading cor-sistema">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_notaFiscalSimplesRemessa_consulta" action="restrito/sistema/notaFiscalSimplesRemessa" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-2">
									<label class="text-white">Número NF Simples Remessa</label>
									<html:text styleClass="form-control input-sm" styleId="numero" property="notaFiscalSimplesRemessa.numero" name="notaFiscalSimplesRemessaForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-2">
									<label class="text-white">Data Inicial</label>
									<html:text styleClass="form-control input-sm data text-center" styleId="dataInicial" property="dataInicial" name="notaFiscalSimplesRemessaForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-2">
									<label class="text-white">Data Final</label>
									<html:text styleClass="form-control input-sm data text-center" styleId="dataFinal" property="dataFinal" name="notaFiscalSimplesRemessaForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label class="text-white">Receptor</label>
									<html:text styleClass="form-control input-sm" styleId="receptor" property="nomeFuncionarioReceptor" name="notaFiscalSimplesRemessaForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleClass="form-control input-sm" styleId="nomeUsuarioReceptor" property="notaFiscalSimplesRemessa.nomeUsuarioCriacao" name="notaFiscalSimplesRemessaForm" />
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label class="text-white">Produto</label>
									<html:text styleClass="form-control input-sm" styleId="produto" property="itemPesquisar.itemNotaFiscalVendaFutura.itemPedido.produto.nomeCompleto" name="notaFiscalSimplesRemessaForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="produtoSelecionado" property="itemPesquisar.itemNotaFiscalVendaFutura.itemPedido.produto.id" name="notaFiscalSimplesRemessaForm" />
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-2">
									<label class="text-white">Nº NF Venda Futura</label>
									<html:text styleClass="form-control input-sm text-center" styleId="numeroNotaFiscalVendaFutura" property="notaFiscalSimplesRemessa.notaFiscalVendaFutura.numero" name="notaFiscalSimplesRemessaForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-2">
									<label class="text-white">Status NF Venda Futura</label>
									<html:select styleClass="form-control input-sm" styleId="status" property="notaFiscalSimplesRemessa.notaFiscalVendaFutura.status" name="notaFiscalSimplesRemessaForm">
										<html:option value="">Selecione...</html:option>
										<html:option value="ANDAMENTO">ANDAMENTO</html:option>
										<html:option value="FINALIZADO">FINALIZADO</html:option>
									</html:select>
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-4">
									<label class="text-white">Fornecedor Pedido NF Venda Futura</label>
									<html:text styleClass="form-control input-sm" styleId="fornecedor" property="notaFiscalSimplesRemessa.notaFiscalVendaFutura.pedido.fornecedor.nome" name="notaFiscalSimplesRemessaForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
								</div>
							</div>

							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<div class="row">
										<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.notaFiscalSimplesRemessa.filtrar)">
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
										<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.notaFiscalSimplesRemessa.inserir)">
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
			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.notaFiscalSimplesRemessa.listagem)">
				<div class="panel-body">
					<div style="max-height: 470px; min-height: 365px;">
						<table class="table table-responsive table-bordered table-striped table-hover" style="font-size: 12px;">
							<thead>
								<!-- CABEÇALHO DA TABELA -->
								<tr class="cor-sistema" style="color: white;">
									<th class="text-center">NF Simples Remessa</th>
									<th class="text-center">Data Entrega</th>
									<th class="text-center">Venda Futura</th>
									<th class="text-center">Data Venda Futura</th>
									<th>Fornecedor</th>
									<th class="text-center">Qtd. de Itens</th>
									<th class="text-center">Total de Itens</th>
									<th class="text-right">Valor Total</th>
									<th class="text-center" style="width: 80px;">Opções</th>
								</tr>
							</thead>
							<tbody>
								<!-- TABELA -->
								<logic:iterate id="notaFiscalSimplesRemessaCorrente" name="notaFiscalSimplesRemessaForm" property="notas" type="br.com.srcsoftware.sistema.pedido.notafiscal.simplesremessa.NotaFiscalSimplesRemessaDTO">

									<%-- <logic:equal name="notaFiscalSimplesRemessaCorrente" value="ABERTO" property="notaFiscalVendaFutura.status">
										<tr>
									</logic:equal>
									<logic:equal name="notaFiscalSimplesRemessaCorrente" value="FINALIZADO" property="notaFiscalVendaFutura.status">
										<tr style="background-color: rgba(0, 18, 230, 0.41)">
									</logic:equal>
									<logic:equal name="notaFiscalSimplesRemessaCorrente" value="ANDAMENTO" property="notaFiscalVendaFutura.status">
										<tr>
									</logic:equal> --%>
									<tr>
										<!-- success / info / warning / danger -->
										<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalSimplesRemessa.src?method=selecionar&notaFiscalSimplesRemessa.id=${notaFiscalSimplesRemessaCorrente.id}')">
											${notaFiscalSimplesRemessaCorrente.numero}</td>
										<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalSimplesRemessa.src?method=selecionar&notaFiscalSimplesRemessa.id=${notaFiscalSimplesRemessaCorrente.id}')">
											${notaFiscalSimplesRemessaCorrente.dataToString}</td>
										<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalSimplesRemessa.src?method=selecionar&notaFiscalSimplesRemessa.id=${notaFiscalSimplesRemessaCorrente.id}')">
											${notaFiscalSimplesRemessaCorrente.notaFiscalVendaFutura.numero}</td>
										<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalSimplesRemessa.src?method=selecionar&notaFiscalSimplesRemessa.id=${notaFiscalSimplesRemessaCorrente.id}')">
											${notaFiscalSimplesRemessaCorrente.notaFiscalVendaFutura.dataToString}</td>
										<td onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalSimplesRemessa.src?method=selecionar&notaFiscalSimplesRemessa.id=${notaFiscalSimplesRemessaCorrente.id}')">
											${notaFiscalSimplesRemessaCorrente.notaFiscalVendaFutura.pedido.fornecedor.nome}&nbsp;-&nbsp;${notaFiscalSimplesRemessaCorrente.notaFiscalVendaFutura.pedido.fornecedor.telefone}</td>
										<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalSimplesRemessa.src?method=selecionar&notaFiscalSimplesRemessa.id=${notaFiscalSimplesRemessaCorrente.id}')">
											${notaFiscalSimplesRemessaCorrente.quantidadeItens}</td>
										<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalSimplesRemessa.src?method=selecionar&notaFiscalSimplesRemessa.id=${notaFiscalSimplesRemessaCorrente.id}')">
											${notaFiscalSimplesRemessaCorrente.quantidadeTotalItens}</td>
										<td class="text-right" onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalSimplesRemessa.src?method=selecionar&notaFiscalSimplesRemessa.id=${notaFiscalSimplesRemessaCorrente.id}')">
											${notaFiscalSimplesRemessaCorrente.valorCustoTotal}</td>
										<td class="text-center" style="min-width: 80px; vertical-align: middle;">
											<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.notaFiscalSimplesRemessa.excluir)">
												<button type="button" id="excluir" class="btn btn-danger btn-xs fa fa-trash" style="font-size: 14px"
													onclick="excluir('NF Nº ${notaFiscalSimplesRemessaCorrente.numero} - Fornecedor: ${notaFiscalSimplesRemessaCorrente.notaFiscalVendaFutura.pedido.fornecedor.nome}', 'sistema/notaFiscalSimplesRemessa',${notaFiscalSimplesRemessaCorrente.id} );"></button>
											</logic:equal>
										</td>
									</tr>

								</logic:iterate>
							</tbody>
						</table>
					</div>
				</div>
			</logic:equal>
			<!-- TERMINO TABELA -->
			<!-- /.panel-body -->

			<!-- INICIO PAGINAÇÃO -->
			<logic:notEmpty name="notaFiscalSimplesRemessaForm" property="notas">
				<div class="panel-footer">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">

							<nav aria-label="Page navigation">
								<ul class="pagination" style="font-size: 12px;">
									<li>
										<a href="javascript://" style="cursor: default;">Encontrados: ${notaFiscalSimplesRemessaForm.paginacao.totalRegistros} </a>
									</li>
									<logic:equal name="notaFiscalSimplesRemessaForm" property="paginacao.voltarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="notaFiscalSimplesRemessaForm" property="paginacao.voltarPaginacaoInativo" value="false">
										<li class="page-item">
											<a class="page-link" href="${contextPath}/restrito/sistema/notaFiscalSimplesRemessa.src?method=voltar&forwardDestino=notaFiscalSimplesRemessaLista" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>

									<c:forEach var="i" begin="${notaFiscalSimplesRemessaForm.paginacao.paginaInicial}" end="${notaFiscalSimplesRemessaForm.paginacao.paginaFinal}" step="1" varStatus="row">
										<c:if test="${notaFiscalSimplesRemessaForm.paginacao.paginaCorrente == i}">
											<li class="page-item active">
										</c:if>

										<c:if test="${notaFiscalSimplesRemessaForm.paginacao.paginaCorrente != i}">
											<li class="page-item ">
										</c:if>

										<a class="page-link" href="${contextPath}/restrito/sistema/notaFiscalSimplesRemessa.src?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>
										</li>
									</c:forEach>

									<logic:equal name="notaFiscalSimplesRemessaForm" property="paginacao.avancarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Next">
												<span aria-hidden="true">&raquo;</span>
												<span class="sr-only">Próximo</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="notaFiscalSimplesRemessaForm" property="paginacao.avancarPaginacaoInativo" value="false">
										<li class="page-item ">
											<a class="page-link" href="${contextPath}/restrito/sistema/notaFiscalSimplesRemessa.src?method=avancar&forwardDestino=notaFiscalSimplesRemessaLista" aria-label="Next">
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
		$("#numeroNotaFiscalVendaFutura").prop("maxlength", 20);
		$("#fornecedor").prop("maxlength", 100);
		$("#receptor").prop("maxlength", 100);
		$("#produto").attr("maxlength", 100);
		

		/* Setando os placeholder dos campos*/
		$( "#numero" ).prop("placeholder","NF a ser pesquisada");
		$("#numeroNotaFiscalVendaFutura").prop("placeholder","NF Futura a ser pesquisada");
		$("#fornecedor").prop("placeholder","Fornecedor a ser pesquisado");
		$("#receptor").prop("placeholder","Receptor a ser pesquisado");
		$("#produto").prop("placeholder","Produto a ser pesquisado");

		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_notaFiscalSimplesRemessa_consulta").prop("autocomplete", "off");
		
		$('#form_notaFiscalSimplesRemessa_consulta').on('submit', function(e){
	        abrirModalProcessando();
	    });
		
		$( '#pesquisar' ).click(function() {  	 
			executar('form_notaFiscalSimplesRemessa_consulta','filtrar');
		});
		
		$( '#limparPesquisa' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_notaFiscalSimplesRemessa_consulta','limparFiltro');
		});
		
		$( '#novo' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_notaFiscalSimplesRemessa_consulta','abrirCadastro');
		});
		
		$('#fornecedor').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'notaFiscalSimplesRemessa.notaFiscalVendaFutura.pedido.fornecedor.nome',
			serviceUrl : '${contextPath}/restrito/sistema/notaFiscalSimplesRemessa.src?method=selecionarFornecedorAutoComplete',
			onSelect : function(suggestion) {
				//alert('You selected: ' + suggestion.value + ', ' + suggestion.data);
				
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					
				}
			}
		});
		
		$('#receptor').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'nomeFuncionarioReceptor',
			serviceUrl : '${contextPath}/restrito/sistema/notaFiscalSimplesRemessa.src?method=selecionarReceptorAutoComplete',
			onSelect : function(suggestion) {
				$('#nomeUsuarioReceptor').val(suggestion.data);
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#nomeUsuarioReceptor').val(null);
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


