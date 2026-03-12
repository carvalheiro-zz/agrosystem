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
			Pesquisar Cupons
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Listagem dos Cupons
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<div class="row">

	<!-- Define o tamanho geral da cupom -->
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel" style="box-shadow: 0 10px 30px rgba(0, 0, 0, 0.8); border-color: #909090;">

			<!-- INICIO CAMPOS DE PESQUISA -->
			<div class="panel-heading cor-sistema">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_cupom_consulta" action="restrito/sistema/cupom" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<div class="form-group col-xs-12 col-sm-4 col-md-3 col-lg-2">
									<label class="text-white">Data Inicial</label>
									<html:text styleClass="form-control input-sm data text-center" styleId="dataInicial" property="dataInicial" name="cupomForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-4 col-md-3 col-lg-2">
									<label class="text-white">Data Final</label>
									<html:text styleClass="form-control input-sm data text-center" styleId="dataFinal" property="dataFinal" name="cupomForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-3 col-md-2 col-lg-2">
									<label class="text-white">Número</label>
									<html:text styleClass="form-control input-sm" styleId="numero" property="cupom.numero" name="cupomForm" />
								</div>		
								
								<div class="form-group col-xs-12 col-sm-4 col-md-6 col-lg-6">
									<label class="text-white">Fornecedor</label>
									<html:text styleClass="form-control input-sm" styleId="fornecedor" property="cupom.fornecedor.nome" name="cupomForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<label class="text-white">Produto</label>
									<html:text styleClass="form-control input-sm" styleId="produto" property="itemPesquisar.produto.nomeCompleto" name="cupomForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="produtoSelecionado" property="itemPesquisar.produto.id" name="cupomForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label class="text-white">Marca</label>									
									<html:text styleClass="form-control input-sm" styleId="marca" property="itemPesquisar.produto.marca.nome" name="cupomForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="marcaSelecionada" property="itemPesquisar.produto.marca.id" name="cupomForm" />
								</div>								
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label class="text-white">Categoria</label>									
									<html:text styleClass="form-control input-sm" styleId="tipo" property="itemPesquisar.produto.tipo.nome" name="cupomForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="tipoSelecionado" property="itemPesquisar.produto.tipo.id" name="cupomForm" />
								</div>
								
							</div>
							<div class="row">

								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.cupom.filtrar)">
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
								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.cupom.inserir)">
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
			<logic:empty name="cupomForm" property="cupons">
				<div class="row" style="margin-top: 15px;">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="alert alert-warning">Nenhum registro encontrado!</div>
					</div>
				</div>
			</logic:empty>
			<logic:notEmpty name="cupomForm" property="cupons">
				<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.cupom.filtrar)">
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
								<thead>
									<!-- CABEÇALHO DA TABELA -->
									<tr class="cor-sistema" style="color: white;">
										<th class="text-center">Número</th>
										<th class="text-center">Data</th>
										<th>Fornecedor</th>
										<th class="text-center">Qtd. Itens</th>
										<th class="text-center">Total Itens</th>
										<th class="text-center">NF Emitida?</th>
										<th class="text-center" style="width: 80px;">Opções</th>
									</tr>
								</thead>
								<tbody>
									<!-- TABELA -->
									<logic:iterate id="cupomCorrente" name="cupomForm" property="cupons" type="br.com.srcsoftware.sistema.cupom.CupomDTO">

										<tr class="${cupomCorrente.corInformativa}" style="font-size: 12px; font-weight: bold;">
											<!-- success / info / warning / danger -->
											<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/cupom.src?method=selecionar&cupom.id=${cupomCorrente.id}')">${cupomCorrente.numero}</td>
											<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/cupom.src?method=selecionar&cupom.id=${cupomCorrente.id}')">${cupomCorrente.dataToString}</td>
											<td onclick="selecionar('${contextPath}/restrito/sistema/cupom.src?method=selecionar&cupom.id=${cupomCorrente.id}')">${cupomCorrente.fornecedor.nome}&nbsp;-&nbsp;${cupomCorrente.fornecedor.telefone}</td>
											<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/cupom.src?method=selecionar&cupom.id=${cupomCorrente.id}')">${cupomCorrente.quantidadeItens}</td>
											<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/cupom.src?method=selecionar&cupom.id=${cupomCorrente.id}')">${cupomCorrente.quantidadeTotalItens}</td>
											
											<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/cupom.src?method=selecionar&cupom.id=${cupomCorrente.id}')">
												<logic:notEmpty name="cupomCorrente" property="corInformativa">
													<i class="fa fa-check" style="font-size:15px;color:blue"></i>
												</logic:notEmpty>
											</td>
											
											<td class="text-center" style="min-width: 80px; vertical-align: middle;">
												<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.cupom.excluir)">
													<button type="button" id="excluir" class="btn btn-danger btn-xs fa fa-trash" style="font-size: 14px" onclick="excluir('Nº ${cupomCorrente.id} - Fornecedor: ${cupomCorrente.fornecedor.nome}', 'sistema/cupom', ${cupomCorrente.id} );"></button>
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
			<logic:notEmpty name="cupomForm" property="cupons">
				<div class="panel-footer">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">

							<nav aria-label="Page navigation">
								<ul class="pagination" style="font-size: 12px;">
									<li>
										<a href="javascript://" style="cursor: default;">Encontrados: ${cupomForm.paginacao.totalRegistros} </a>
									</li>
									<logic:equal name="cupomForm" property="paginacao.voltarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="cupomForm" property="paginacao.voltarPaginacaoInativo" value="false">
										<li class="page-item">
											<a class="page-link" href="${contextPath}/restrito/sistema/cupom.src?method=voltar&forwardDestino=cupomLista" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>

									<c:forEach var="i" begin="${cupomForm.paginacao.paginaInicial}" end="${cupomForm.paginacao.paginaFinal}" step="1" varStatus="row">
										<c:if test="${cupomForm.paginacao.paginaCorrente == i}">
											<li class="page-item active">
										</c:if>

										<c:if test="${cupomForm.paginacao.paginaCorrente != i}">
											<li class="page-item ">
										</c:if>

										<a class="page-link" href="${contextPath}/restrito/sistema/cupom.src?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>
										</li>
									</c:forEach>

									<logic:equal name="cupomForm" property="paginacao.avancarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Next">
												<span aria-hidden="true">&raquo;</span>
												<span class="sr-only">Próximo</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="cupomForm" property="paginacao.avancarPaginacaoInativo" value="false">
										<li class="page-item ">
											<a class="page-link" href="${contextPath}/restrito/sistema/cupom.src?method=avancar&forwardDestino=cupomLista" aria-label="Next">
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
		$("#produto").prop("maxlength", 50);

		/* Setando os placeholder dos campos*/
		$( "#numero" ).prop("placeholder","Número a ser pesquisado");
		$( "#fornecedor" ).prop("placeholder","Fornecedor a ser pesquisado");
		$( "#produto" ).prop("placeholder","Produto a ser pesquisado");
		
		// Desliga o auto-complete da pagina
		$("#form_cupom_consulta").prop("autocomplete", "off");

		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_cupom_consulta").prop("autocomplete", "off");
		
		$('#form_cupom_consulta').on('submit', function(e){
	        abrirModalProcessando();
	    });
		
		$( '#pesquisar' ).click(function() {  	
			executar('form_cupom_consulta','filtrar');
		});
		
		$( '#limparPesquisa' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_cupom_consulta','limparFiltro');
		});
		
		$( '#novo' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_cupom_consulta','abrirCadastro');
		});
		
		$('#fornecedor').autocomplete({
			minChars : 2,
			paramName : 'cupom.fornecedor.nome',
			serviceUrl : '${contextPath}/restrito/sistema/cupom.src?method=selecionarFornecedorAutoComplete',
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
		
		$('#marca').keyup(function() {
			if ($('#marca').val() == null || $('#marca').val() == '') {
				$('#marcaSelecionada').val(null);
			}
		});
		$('#marca').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'itemPesquisar.produto.marca.nome',
			/* params : {
				'saidaGrao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/cupom.src?method=selecionarMarcaAutoComplete',
			onSelect : function(suggestion) {
				$('#marcaSelecionada').val(suggestion.data);
			},
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			}, */
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#marcaSelecionada').val(null);
				}
			}
		});
		
		$('#tipo').keyup(function() {
			if ($('#tipo').val() == null || $('#tipo').val() == '') {
				$('#tipoSelecionado').val(null);
			}
		});
		$('#tipo').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'itemPesquisar.produto.tipo.nome',
			/* params : {
				'saidaGrao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/cupom.src?method=selecionarTipoAutoComplete',
			onSelect : function(suggestion) {
				$('#tipoSelecionado').val(suggestion.data);
			},
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			}, */
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#tipoSelecionado').val(null);
				}
			}
		});
	});
	
	function selecionar(urlAction) {
		abrirModalProcessando();
		window.location = urlAction;
	}
</script>


