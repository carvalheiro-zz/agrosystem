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
			Pesquisar Notas Fiscais de Rateio (B)
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Listagem das Notas Fiscais de Rateio (B)
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<div class="row">

	<!-- Define o tamanho geral da notaFiscalRateio -->
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel" style="box-shadow: 0 10px 30px rgba(0, 0, 0, 0.8); border-color: #909090;">

			<!-- INICIO CAMPOS DE PESQUISA -->
			<div class="panel-heading cor-sistema">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_notaFiscalRateio_consulta" action="restrito/sistema/notaFiscalRateio" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<div class="form-group col-xs-12 col-sm-3 col-md-2 col-lg-2">
									<label class="text-white">Nº NF</label>
									<html:text styleClass="form-control input-sm" styleId="numeroPesquisa" property="notaFiscalRateioFilter.numero" name="notaFiscalRateioForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-3 col-md-2 col-lg-2">
									<label class="text-white">Nº Recibo</label>
									<html:text styleClass="form-control input-sm" styleId="numeroReciboPesquisa" property="notaFiscalRateioFilter.numeroRecibo" name="notaFiscalRateioForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-9 col-md-6 col-lg-6">
									<label class="text-white">Fornecedor</label>
									<html:text styleClass="form-control input-sm" styleId="nomeFornecedorPesquisa" property="notaFiscalRateioFilter.fornecedor.nome" name="notaFiscalRateioForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
								</div>
								<div class="form-group col-xs-12 col-sm-3 col-md-2 col-lg-2">
									<label class="text-white">Data Inicial</label>
									<html:text styleClass="form-control input-sm data text-center" styleId="dataInicialPesquisa" property="dataInicial" name="notaFiscalRateioForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-3 col-md-2 col-lg-2">
									<label class="text-white">Data Final</label>
									<html:text styleClass="form-control input-sm data text-center" styleId="dataFinalPesquisa" property="dataFinal" name="notaFiscalRateioForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label class="text-white">Tipo</label>
									<html:select styleClass="form-control input-sm" styleId="tipoNFPesquisa" property="notaFiscalRateioFilter.tipo" name="notaFiscalRateioForm">
										<html:option value="">Selecione...</html:option>
										<html:option value="Investimento/Despesa">Investimento / Despesa</html:option>
										<html:option value="Receita">Receita</html:option>
									</html:select>
								</div>


								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label class="text-white">Centro de Custo</label>
									<html:text styleClass="form-control input-sm" styleId="centroCusto" property="itemAdicionar.centroCustoReceita.codigo" name="notaFiscalRateioForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
								</div>
							</div>
							<div class="row">

								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuario_logado.id}.notaFiscalRateio.filtrar)">
									<div class="form-group col-xs-12 col-sm-3 col-md-2 col-lg-2">
										<button type="submit" id="pesquisar" class="btn btn-success btn-sm cor-sistema btn-block">
											<i class="fa fa-search"></i>
											Pesquisar
										</button>
									</div>
								</logic:equal>
								<div class="form-group col-xs-12 col-sm-3 col-md-2 col-lg-2">
									<button type="button" id="limparPesquisa" class="btn btn-success btn-sm cor-sistema btn-block">
										<i class="glyphicon glyphicon-erase"></i>
										Limpar
									</button>
								</div>
								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuario_logado.id}.notaFiscalRateio.inserir)">
									<div class="form-group col-xs-12 col-sm-3 col-md-2 col-lg-2">
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
			<logic:empty name="notaFiscalRateioForm" property="notas">
				<div class="row" style="margin-top: 15px;">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="alert alert-warning">Nenhum registro encontrado!</div>
					</div>
				</div>
			</logic:empty>
			<logic:notEmpty name="notaFiscalRateioForm" property="notas">
				<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuario_logado.id}.notaFiscalRateio.filtrar)">
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
								<thead>
									<!-- CABEÇALHO DA TABELA -->
									<tr class="cor-sistema" style="color: white;">
										<!-- <th class="text-center">Número</th> -->
										<th class="text-center">Número NF</th>
										<th class="text-center">Número Recibo</th>
										<th class="text-center">Data</th>
										<th>Fornecedor</th>
										<th class="text-center">Situação</th>
										<th class="text-center">Qtd. Itens</th>
										<th>Valor</th>
										<th>Pago+Acrés.</th>
										<th class="text-center" style="width: 80px;">Opções</th>
									</tr>
								</thead>
								<tbody>
									<!-- TABELA -->
									<logic:iterate id="notaFiscalRateioCorrente" name="notaFiscalRateioForm" property="notas" type="br.com.srcsoftware.sistema.notafiscal.rateio.NotaFiscalRateioDTO">

										<logic:equal name="notaFiscalRateioCorrente" value="Aberta" property="contaPagar.situacao">
											<tr>
										</logic:equal>
										<logic:equal name="notaFiscalRateioCorrente" value="Quitada" property="contaPagar.situacao">
											<tr style="background-color: rgba(0, 18, 230, 0.41)">
										</logic:equal>
										<logic:equal name="notaFiscalRateioCorrente" value="Cancelada" property="contaPagar.situacao">
											<tr style="background-color: rgba(255, 27, 0, 0.4)">
										</logic:equal>


										<%-- <td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalRateio.src?method=selecionar&notaFiscalRateioFilter.id=${notaFiscalRateioCorrente.id}')">${notaFiscalRateioCorrente.contaPagar.numero}</td> --%>
										<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalRateio.src?method=selecionar&notaFiscalRateioFilter.id=${notaFiscalRateioCorrente.id}')">${notaFiscalRateioCorrente.numero}</td>
										<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalRateio.src?method=selecionar&notaFiscalRateioFilter.id=${notaFiscalRateioCorrente.id}')">${notaFiscalRateioCorrente.numeroRecibo}</td>
										<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalRateio.src?method=selecionar&notaFiscalRateioFilter.id=${notaFiscalRateioCorrente.id}')">${notaFiscalRateioCorrente.contaPagar.dataToString}</td>

										<td onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalRateio.src?method=selecionar&notaFiscalRateioFilter.id=${notaFiscalRateioCorrente.id}')">
											${notaFiscalRateioCorrente.fornecedor.nome}&nbsp;-&nbsp;${notaFiscalRateioCorrente.fornecedor.telefone}</td>

										<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalRateio.src?method=selecionar&notaFiscalRateioFilter.id=${notaFiscalRateioCorrente.id}')">${notaFiscalRateioCorrente.contaPagar.situacao}</td>
										<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalRateio.src?method=selecionar&notaFiscalRateioFilter.id=${notaFiscalRateioCorrente.id}')">${notaFiscalRateioCorrente.quantidadeItens}</td>
										<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalRateio.src?method=selecionar&notaFiscalRateioFilter.id=${notaFiscalRateioCorrente.id}')">${notaFiscalRateioCorrente.contaPagar.valor}</td>

										<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalRateio.src?method=selecionar&notaFiscalRateioFilter.id=${notaFiscalRateioCorrente.id}')">${notaFiscalRateioCorrente.contaPagar.totalRecebido}</td>

										<td class="text-center" style="min-width: 80px; vertical-align: middle;">
											<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuario_logado.id}.notaFiscalRateio.excluir)">
												<button type="button" id="excluir" class="btn btn-danger btn-xs fa fa-trash" style="font-size: 14px"
													onclick="excluir('Nº ${notaFiscalRateioCorrente.contaPagar.numero} - Fornecedor: ${notaFiscalRateioCorrente.fornecedor.nome}&nbsp;-&nbsp;${notaFiscalRateioCorrente.fornecedor.telefone}', 'sistema/notaFiscalRateio', 'notaFiscalRateioFilter', ${notaFiscalRateioCorrente.id} );"></button>
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
			<logic:notEmpty name="notaFiscalRateioForm" property="notas">
				<div class="panel-footer">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">

							<nav aria-label="Page navigation">
								<ul class="pagination" style="font-size: 12px;">
									<li>
										<a href="javascript://" style="cursor: default;">Encontrados: ${notaFiscalRateioForm.paginacao.totalRegistros} </a>
									</li>
									<logic:equal name="notaFiscalRateioForm" property="paginacao.voltarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="notaFiscalRateioForm" property="paginacao.voltarPaginacaoInativo" value="false">
										<li class="page-item">
											<a class="page-link" href="${contextPath}/restrito/sistema/notaFiscalRateio.src?method=voltar&forwardDestino=notaFiscalRateioLista" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>

									<c:forEach var="i" begin="${notaFiscalRateioForm.paginacao.paginaInicial}" end="${notaFiscalRateioForm.paginacao.paginaFinal}" step="1" varStatus="row">
										<c:if test="${notaFiscalRateioForm.paginacao.paginaCorrente == i}">
											<li class="page-item active">
										</c:if>

										<c:if test="${notaFiscalRateioForm.paginacao.paginaCorrente != i}">
											<li class="page-item ">
										</c:if>

										<a class="page-link" href="${contextPath}/restrito/sistema/notaFiscalRateio.src?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>
										</li>
									</c:forEach>

									<logic:equal name="notaFiscalRateioForm" property="paginacao.avancarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Next">
												<span aria-hidden="true">&raquo;</span>
												<span class="sr-only">Próximo</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="notaFiscalRateioForm" property="paginacao.avancarPaginacaoInativo" value="false">
										<li class="page-item ">
											<a class="page-link" href="${contextPath}/restrito/sistema/notaFiscalRateio.src?method=avancar&forwardDestino=notaFiscalRateioLista" aria-label="Next">
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
		$( "#numeroPesquisa" ).focus();

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#numeroPesquisa").attr("maxlength", 50);
		$("#numeroReciboPesquisa").attr("maxlength", 50);
		$("#nomeFornecedorPesquisa").attr("maxlength", 50);

		/* Setando os placeholder dos campos*/
		$( "#numeroPesquisa" ).attr("placeholder","Número a ser pesquisado");
		$( "#numeroReciboPesquisa" ).attr("placeholder","Número a ser pesquisado");
		$( "#nomeFornecedorPesquisa" ).attr("placeholder","Fornecedor a ser pesquisado");
		
		// Desliga o auto-complete da pagina
		$("#form_notaFiscalRateio_consulta").attr("autocomplete", "off");

		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_notaFiscalRateio_consulta").attr("autocomplete", "off");
		
		$('#form_notaFiscalRateio_consulta').on('submit', function(e){
	        abrirModalProcessando();
	    });
		
		$( '#pesquisar' ).click(function() {  	
			executar('form_notaFiscalRateio_consulta','filtrar');
		});
		
		$( '#limparPesquisa' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_notaFiscalRateio_consulta','limparFiltro');
		});
		
		$( '#novo' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_notaFiscalRateio_consulta','abrirCadastro');
		});
		
		$('#nomeFornecedorPesquisa').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'notaFiscalRateioFilter.fornecedor.nome',
			serviceUrl : '${contextPath}/restrito/sistema/notaFiscalRateio.src?method=selecionarFornecedorAutoCompleteFilter',
			onSelect : function(suggestion) {
				//alert('You selected: ' + suggestion.value + ', ' + suggestion.data);				
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					
				}
			}
		});
		
		
		$('#centroCusto').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'itemAdicionar.centroCustoReceita.codigo',
			/*params : {
				'notaFiscalRateio.tipo' : function() {
					return $('#tipoNF').val();
				}
			},*/
			serviceUrl : '${contextPath}/restrito/sistema/notaFiscalRateio.src?method=selecionarCentroCustoReceitaAutoComplete',
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


