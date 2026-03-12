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
			Pesquisar Nota Fiscal Cupom
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Listagem das Notas de Cupons
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<div class="row">

	<!-- Define o tamanho geral da notaFiscalCupom -->
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel" style="box-shadow: 0 10px 30px rgba(0, 0, 0, 0.8); border-color: #909090;">
	
			<!-- INICIO CAMPOS DE PESQUISA -->
			<div class="panel-heading cor-sistema">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_notaFiscalCupom_consulta" action="restrito/sistema/notaFiscalCupom" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<div class="form-group col-xs-12 col-sm-3 col-md-2 col-lg-2">
									<label class="text-white">Número</label>
									<html:text styleClass="form-control input-sm" styleId="numeroPesquisa" property="notaFiscalCupom.numero" name="notaFiscalCupomForm" />
								</div>									
								<div class="form-group col-xs-12 col-sm-4 col-md-4 col-lg-4">
									<label class="text-white">Fornecedor</label>
									<html:text styleClass="form-control input-sm" styleId="nomeFornecedorPesquisa" property="notaFiscalCupom.fornecedor.nome" name="notaFiscalCupomForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
								</div>								
								<div class="form-group col-xs-12 col-sm-4 col-md-3 col-lg-2">
									<label class="text-white">Data Inicial</label>
									<html:text styleClass="form-control input-sm data text-center" styleId="dataInicialPesquisa" property="dataInicial" name="notaFiscalCupomForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-4 col-md-3 col-lg-2">
									<label class="text-white">Data Final</label>
									<html:text styleClass="form-control input-sm data text-center" styleId="dataFinalPesquisa" property="dataFinal" name="notaFiscalCupomForm" />
								</div>								
							</div>
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<div class="row">
										<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.notaFiscalCupom.filtrar)">
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
										<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.notaFiscalCupom.inserir)">
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
			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.notaFiscalCupom.listagem)">
				<div class="panel-body">
					<div class="table-responsive" style="max-height: 470px; min-height: 365px;">
						<table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
							<thead>
								<!-- CABEÇALHO DA TABELA -->
								<tr class="cor-sistema" style="color: white;">
									<th class="text-center">NF</th>
									<th class="text-center">Data</th>
									<th>Fornecedor</th>
									<th class="text-center">Cupons</th>
									<th class="text-right">R$ Total NF</th>
									<th class="text-center" style="width: 80px;">Opções</th>
								</tr>
							</thead>
							<tbody>
								<!-- TABELA -->
								<logic:iterate id="notaFiscalCupomCorrente" name="notaFiscalCupomForm" property="notas" type="br.com.srcsoftware.sistema.notafiscalcupom.NotaFiscalCupomDTO">

									<tr>
									
										<!-- success / info / warning / danger -->
										<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalCupom.src?method=selecionar&notaFiscalCupom.id=${notaFiscalCupomCorrente.id}')">
											${notaFiscalCupomCorrente.numero}
										</td>
										<td  class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalCupom.src?method=selecionar&notaFiscalCupom.id=${notaFiscalCupomCorrente.id}')">
											${notaFiscalCupomCorrente.dataToString}
										</td>		
										<td onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalCupom.src?method=selecionar&notaFiscalCupom.id=${notaFiscalCupomCorrente.id}')">
											${notaFiscalCupomCorrente.fornecedor.nome}&nbsp;-&nbsp;${notaFiscalCupomCorrente.fornecedor.telefone}
										</td>	
										<td  class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalCupom.src?method=selecionar&notaFiscalCupom.id=${notaFiscalCupomCorrente.id}')">
											${notaFiscalCupomCorrente.quantidadeCupons}
										</td>		
										<td  class="text-right" onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalCupom.src?method=selecionar&notaFiscalCupom.id=${notaFiscalCupomCorrente.id}')">
											${notaFiscalCupomCorrente.valorCustoTotal}
										</td>																							
										<td class="text-center" style="min-width: 80px; vertical-align: middle;">
											<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.notaFiscalCupom.excluir)">
												<button type="button" id="excluir" class="btn btn-danger btn-xs fa fa-trash" style="font-size: 14px"
													onclick="excluir('Nº ${notaFiscalCupomCorrente.id} - Fornecedor: ${notaFiscalCupomCorrente.fornecedor.nome}', 'sistema/notaFiscalCupom', ${notaFiscalCupomCorrente.id} );"></button>
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
			<logic:notEmpty name="notaFiscalCupomForm" property="notas">
				<div class="panel-footer">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">

							<nav aria-label="Page navigation">
								<ul class="pagination" style="font-size: 12px;">
									<li>
										<a href="javascript://" style="cursor: default;">Encontrados: ${notaFiscalCupomForm.paginacao.totalRegistros} </a>
									</li>
									<logic:equal name="notaFiscalCupomForm" property="paginacao.voltarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="notaFiscalCupomForm" property="paginacao.voltarPaginacaoInativo" value="false">
										<li class="page-item">
											<a class="page-link" href="${contextPath}/restrito/sistema/notaFiscalCupom.src?method=voltar&forwardDestino=notaFiscalCupomLista" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>

									<c:forEach var="i" begin="${notaFiscalCupomForm.paginacao.paginaInicial}" end="${notaFiscalCupomForm.paginacao.paginaFinal}" step="1" varStatus="row">
										<c:if test="${notaFiscalCupomForm.paginacao.paginaCorrente == i}">
											<li class="page-item active">
										</c:if>

										<c:if test="${notaFiscalCupomForm.paginacao.paginaCorrente != i}">
											<li class="page-item ">
										</c:if>

										<a class="page-link" href="${contextPath}/restrito/sistema/notaFiscalCupom.src?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>
										</li>
									</c:forEach>

									<logic:equal name="notaFiscalCupomForm" property="paginacao.avancarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Next">
												<span aria-hidden="true">&raquo;</span>
												<span class="sr-only">Próximo</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="notaFiscalCupomForm" property="paginacao.avancarPaginacaoInativo" value="false">
										<li class="page-item ">
											<a class="page-link" href="${contextPath}/restrito/sistema/notaFiscalCupom.src?method=avancar&forwardDestino=notaFiscalCupomLista" aria-label="Next">
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
		$("#nomeFornecedorPesquisa").attr("maxlength", 50);

		/* Setando os placeholder dos campos*/
		$( "#numeroPesquisa" ).attr("placeholder","Número a ser pesquisado");
		$( "#nomeFornecedorPesquisa" ).attr("placeholder","Fornecedor a ser pesquisado");
		
		// Desliga o auto-complete da pagina
		$("#form_notaFiscalCupom_consulta").attr("autocomplete", "off");

		/* EVENTOS */		
		$('#form_notaFiscalCupom_consulta').on('submit', function(e){
	        abrirModalProcessando();
	    });
		
		$( '#pesquisar' ).click(function() {  	
			executar('form_notaFiscalCupom_consulta','filtrar');
		});
		
		$( '#limparPesquisa' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_notaFiscalCupom_consulta','limparFiltro');
		});
		
		$( '#novo' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_notaFiscalCupom_consulta','abrirCadastro');
		});
		
		$('#nomeFornecedorPesquisa').autocomplete({
			minChars : 2,
			paramName : 'notaFiscalCupom.fornecedor.nome',
			serviceUrl : '${contextPath}/restrito/sistema/notaFiscalCupom.src?method=selecionarFornecedorAutoComplete',
			onSelect : function(suggestion) {
				//alert('You selected: ' + suggestion.value + ', ' + suggestion.data);
				
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


