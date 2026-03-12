<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!-- CAMPOS DE FILTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<h1 class="page-header cabecalho_pagina" style="margin: 15px 0 10px;">
			<logic:equal name="notaFiscalVendaForm" value="Futura" property="notaFiscalVenda.tipo">
				<i class="fa fa-newspaper-o fa-fw" style="font-size: 26px;"></i>
				Pesquisar Notas Fiscais Futuras
				<small class="sub_cabecalho_pagina">
					<i class="fa fa-angle-double-right"></i>
					Listagem das Notas Fiscais Futuras
				</small>
			</logic:equal>

			<logic:equal name="notaFiscalVendaForm" value="Venda" property="notaFiscalVenda.tipo">
				<i class="fa fa-newspaper-o fa-fw" style="font-size: 26px;"></i>
				Pesquisar Notas Fiscais de Venda
				<small class="sub_cabecalho_pagina">
					<i class="fa fa-angle-double-right"></i>
					Listagem das Notas Fiscais de Venda
				</small>
			</logic:equal>

		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<div class="row">

	<!-- Define o tamanho geral da notaFiscalVenda -->
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel" style="box-shadow: 0 10px 30px rgba(0, 0, 0, 0.8); border-color: #909090;">

			<!-- INICIO CAMPOS DE PESQUISA -->
			<div class="panel-heading cor-sistema">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_notaFiscalVenda_consulta" action="restrito/sistema/notaFiscalVenda" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">

								<div class="form-group col-xs-12 col-sm-3 col-md-2 col-lg-2">
									<label class="text-white">Número</label>
									<html:text styleClass="form-control input-sm" styleId="numero" property="notaFiscalVenda.numero" name="notaFiscalVendaForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-3 col-md-2 col-lg-2">
									<label class="text-white">Status NF</label>
									<html:select styleClass="form-control input-sm" styleId="statusNF" property="notaFiscalVenda.status" name="notaFiscalVendaForm">
										<html:option value="">Selecione...</html:option>
										<html:option value="ANDAMENTO">ANDAMENTO</html:option>
										<html:option value="FINALIZADO">FINALIZADO</html:option>
									</html:select>
								</div>
								<div class="form-group col-xs-12 col-sm-4 col-md-3 col-lg-2">
									<label class="text-white">Data Inicial</label>
									<html:text styleClass="form-control input-sm data text-center" styleId="dataInicial" property="dataInicial" name="notaFiscalVendaForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-4 col-md-3 col-lg-2">
									<label class="text-white">Data Final</label>
									<html:text styleClass="form-control input-sm data text-center" styleId="dataFinal" property="dataFinal" name="notaFiscalVendaForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-3 col-md-2 col-lg-2">
									<label class="text-white">Pedido</label>
									<html:text styleClass="form-control input-sm text-center" styleId="numeroPedido" property="notaFiscalVenda.pedido.numero" name="notaFiscalVendaForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-3 col-md-2 col-lg-2">
									<label class="text-white">Status Pedido</label>
									<html:select styleClass="form-control input-sm" styleId="statusPedido" property="notaFiscalVenda.pedido.status" name="notaFiscalVendaForm">
										<html:option value="">Selecione...</html:option>
										<html:option value="ANDAMENTO">ANDAMENTO</html:option>
										<html:option value="FINALIZADO">FINALIZADO</html:option>
									</html:select>
								</div>
								<div class="form-group col-xs-12 col-sm-4 col-md-4 col-lg-4">
									<label class="text-white">Fornecedor</label>
									<html:text styleClass="form-control input-sm" styleId="fornecedor" property="notaFiscalVenda.pedido.fornecedor.nome" name="notaFiscalVendaForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
								</div>

								<div class="form-group col-xs-12 col-sm-3 col-md-2 col-lg-2">
									<label class="text-white">Receptor</label>
									<html:text styleClass="form-control input-sm" styleId="receptor" property="nomeFuncionarioReceptor" name="notaFiscalVendaForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleClass="form-control input-sm" styleId="nomeUsuarioReceptor" property="notaFiscalVenda.nomeUsuarioCriacao" name="notaFiscalVendaForm" />
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<label class="text-white">Produto</label>
									<html:text styleClass="form-control input-sm" styleId="produto" property="itemPesquisar.itemPedido.produto.nomeCompleto" name="notaFiscalVendaForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="produtoSelecionado" property="itemPesquisar.itemPedido.produto.id" name="notaFiscalVendaForm" />
								</div>
							</div>

							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<div class="row">
										<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.notaFiscalVenda.filtrar)">
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
										<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.notaFiscalVenda.inserir)">
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
			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.notaFiscalVenda.listagem)">
				<div class="panel-body">
					<div style="max-height: 470px; min-height: 365px;">
						<table class="table table-responsive table-bordered table-striped table-hover" style="font-size: 12px;">
							<thead>
								<!-- CABEÇALHO DA TABELA -->
								<tr class="cor-sistema" style="color: white;">
									<th class="text-center">Tipo</th>

									<logic:equal name="notaFiscalVendaForm" value="Futura" property="notaFiscalVenda.tipo">
										<th class="text-center">NF Futura</th>
									</logic:equal>

									<logic:equal name="notaFiscalVendaForm" value="Venda" property="notaFiscalVenda.tipo">
										<th class="text-center">NF Venda</th>
									</logic:equal>

									<logic:equal name="notaFiscalVendaForm" value="Futura" property="notaFiscalVenda.tipo">
										<th class="text-center">Emissão</th>
									</logic:equal>

									<logic:equal name="notaFiscalVendaForm" value="Venda" property="notaFiscalVenda.tipo">
										<th class="text-center">Data Entrega</th>
									</logic:equal>

									<th class="text-center">Pedido</th>
									<th class="text-center">Data Pedido</th>
									<th>Fornecedor</th>
									<th class="text-center">Qtd. de Itens</th>
									<th class="text-center">Total de Itens</th>
									<th class="text-center">Valor Total</th>

									<logic:equal name="notaFiscalVendaForm" value="Futura" property="notaFiscalVenda.tipo">
										<th class="text-center">Restantes</th>
									</logic:equal>

									<!-- <th class="text-right">Valor Total</th> -->
									<th class="text-center" style="width: 80px;">Opções</th>
								</tr>
							</thead>
							<tbody>
								<!-- TABELA -->
								<logic:iterate id="notaFiscalVendaCorrente" name="notaFiscalVendaForm" property="notas" type="br.com.srcsoftware.sistema.pedido.notafiscal.venda.NotaFiscalVendaDTO">

									<logic:equal name="notaFiscalVendaCorrente" value="ABERTO" property="status">
										<tr>
									</logic:equal>
									<logic:equal name="notaFiscalVendaCorrente" value="FINALIZADO" property="status">
										<tr style="background-color: rgba(0, 18, 230, 0.41)">
									</logic:equal>
									<logic:equal name="notaFiscalVendaCorrente" value="ANDAMENTO" property="status">
										<tr style="background-color: rgba(255, 255, 0, 0.41)">
											<!-- Usado para quando FUTURA. Amarelo comentado pois nunca uma NF estara vinculada com um pedido com nada entregue-->
										<tr>
									</logic:equal>
									<!-- success / info / warning / danger -->
									<td class="text-center" style="font-weight: bold;" onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalVenda.src?method=selecionar&notaFiscalVenda.id=${notaFiscalVendaCorrente.id}')">${notaFiscalVendaCorrente.tipo}</td>
									<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalVenda.src?method=selecionar&notaFiscalVenda.id=${notaFiscalVendaCorrente.id}')">${notaFiscalVendaCorrente.numero}</td>
									<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalVenda.src?method=selecionar&notaFiscalVenda.id=${notaFiscalVendaCorrente.id}')">${notaFiscalVendaCorrente.dataToString}</td>
									<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalVenda.src?method=selecionar&notaFiscalVenda.id=${notaFiscalVendaCorrente.id}')">${notaFiscalVendaCorrente.pedido.numero}</td>
									<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalVenda.src?method=selecionar&notaFiscalVenda.id=${notaFiscalVendaCorrente.id}')">${notaFiscalVendaCorrente.pedido.dataToString}</td>
									<td onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalVenda.src?method=selecionar&notaFiscalVenda.id=${notaFiscalVendaCorrente.id}')">
										${notaFiscalVendaCorrente.pedido.fornecedor.nome}&nbsp;-&nbsp;${notaFiscalVendaCorrente.pedido.fornecedor.telefone}</td>
									<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalVenda.src?method=selecionar&notaFiscalVenda.id=${notaFiscalVendaCorrente.id}')">${notaFiscalVendaCorrente.quantidadeItens}</td>
									<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalVenda.src?method=selecionar&notaFiscalVenda.id=${notaFiscalVendaCorrente.id}')">${notaFiscalVendaCorrente.quantidadeTotalItens}</td>

									<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalVenda.src?method=selecionar&notaFiscalVenda.id=${notaFiscalVendaCorrente.id}')">${notaFiscalVendaCorrente.valorCustoTotal}</td>

									<logic:equal name="notaFiscalVendaForm" value="Futura" property="notaFiscalVenda.tipo">
										<td class="text-center" style="background-color: #ffffc4;" onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalVenda.src?method=selecionar&notaFiscalVenda.id=${notaFiscalVendaCorrente.id}')">
											${notaFiscalVendaCorrente.quantidadeRestante}
											<i class="fa fa-truck" style="color: black;"></i>
										</td>
									</logic:equal>
									<td class="text-center" style="min-width: 80px; vertical-align: middle;">
										<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.notaFiscalVenda.excluir)">
											<button type="button" id="excluir" class="btn btn-danger btn-xs fa fa-trash" style="font-size: 14px"
												onclick="excluir('NF Nº ${notaFiscalVendaCorrente.numero} - Fornecedor: ${notaFiscalVendaCorrente.pedido.fornecedor.nome}', 'sistema/notaFiscalVenda', ${notaFiscalVendaCorrente.id} );"></button>
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
			<logic:notEmpty name="notaFiscalVendaForm" property="notas">
				<div class="panel-footer">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">

							<nav aria-label="Page navigation">
								<ul class="pagination" style="font-size: 12px;">
									<li>
										<a href="javascript://" style="cursor: default;">Encontrados: ${notaFiscalVendaForm.paginacao.totalRegistros} </a>
									</li>
									<logic:equal name="notaFiscalVendaForm" property="paginacao.voltarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="notaFiscalVendaForm" property="paginacao.voltarPaginacaoInativo" value="false">
										<li class="page-item">
											<a class="page-link" href="${contextPath}/restrito/sistema/notaFiscalVenda.src?method=voltar&forwardDestino=notaFiscalVendaLista" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>

									<c:forEach var="i" begin="${notaFiscalVendaForm.paginacao.paginaInicial}" end="${notaFiscalVendaForm.paginacao.paginaFinal}" step="1" varStatus="row">
										<c:if test="${notaFiscalVendaForm.paginacao.paginaCorrente == i}">
											<li class="page-item active">
										</c:if>

										<c:if test="${notaFiscalVendaForm.paginacao.paginaCorrente != i}">
											<li class="page-item ">
										</c:if>

										<a class="page-link" href="${contextPath}/restrito/sistema/notaFiscalVenda.src?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>
										</li>
									</c:forEach>

									<logic:equal name="notaFiscalVendaForm" property="paginacao.avancarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Next">
												<span aria-hidden="true">&raquo;</span>
												<span class="sr-only">Próximo</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="notaFiscalVendaForm" property="paginacao.avancarPaginacaoInativo" value="false">
										<li class="page-item ">
											<a class="page-link" href="${contextPath}/restrito/sistema/notaFiscalVenda.src?method=avancar&forwardDestino=notaFiscalVendaLista" aria-label="Next">
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
		$("#numero").attr("maxlength", 50);
		$("#numeroPedido").attr("maxlength", 10);
		$("#fornecedor").attr("maxlength", 20);
		$("#receptor").attr("maxlength", 20);
		$("#produto").attr("maxlength", 100);
		

		/* Setando os placeholder dos campos*/
		$( "#numero" ).attr("placeholder","NF a ser pesquisada");
		$("#numeroPedido").attr("placeholder","Pedido a ser pesquisado");
		$("#fornecedor").attr("placeholder","Fornecedor a ser pesquisado");
		$("#receptor").attr("placeholder","Receptor a ser pesquisado");
		$("#produto").attr("placeholder","Produto a ser pesquisado");

		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_notaFiscalVenda_consulta").attr("autocomplete", "off");
		
		$('#form_notaFiscalVenda_consulta').on('submit', function(e){
	        abrirModalProcessando();
	    });
		
		$( '#pesquisar' ).click(function() {  	 
			executar('form_notaFiscalVenda_consulta','filtrar');
		});
		
		$( '#limparPesquisa' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_notaFiscalVenda_consulta','limparFiltro');
		});
		
		$( '#novo' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_notaFiscalVenda_consulta','abrirCadastro');
		});
		
		$('#fornecedor').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'notaFiscalVenda.pedido.fornecedor.nome',
			serviceUrl : '${contextPath}/restrito/sistema/notaFiscalVenda.src?method=selecionarFornecedorAutoComplete',
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
			serviceUrl : '${contextPath}/restrito/sistema/notaFiscalVenda.src?method=selecionarReceptorAutoComplete',
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


