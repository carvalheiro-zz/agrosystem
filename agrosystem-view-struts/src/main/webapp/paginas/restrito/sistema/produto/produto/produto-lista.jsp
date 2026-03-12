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
			Pesquisar Produtos
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Listagem das Produtos
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<div class="row">

	<!-- Define o tamanho geral da tela -->
	<div class="col-md-offset-1 col-lg-offset-1 col-xs-12 col-sm-12 col-md-10 col-lg-10">
		<div class="panel" style="box-shadow: 0 10px 30px rgba(0, 0, 0, 0.8); border-color: #909090;">

			<!-- INICIO CAMPOS DE PESQUISA -->
			<div class="panel-heading cor-sistema">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_produto_consulta" action="restrito/sistema/produto" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-8">
									<label class="text-white">Nome</label>
									<html:text styleClass="form-control input-sm" styleId="nome" property="produto.nomeCompleto" name="produtoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="produtoSelecionado" property="produto.id" name="produtoForm" />
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-4">
									<label class="text-white">Localizacao</label>
									<html:text styleClass="form-control input-sm" styleId="localizacao" property="produto.localizacao" name="produtoForm" />
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<label class="text-white">Marca</label>									
									<html:text styleClass="form-control input-sm" styleId="marca" property="produto.marca.nome" name="produtoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="marcaSelecionada" property="produto.marca.id" name="produtoForm" />
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<label class="text-white">Categoria</label>									
									<html:text styleClass="form-control input-sm" styleId="tipo" property="produto.tipo.nome" name="produtoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="tipoSelecionado" property="produto.tipo.id" name="produtoForm" />
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<label class="text-white">Unidade Medida</label>									
									<html:text styleClass="form-control input-sm" styleId="unidadeMedida" property="produto.unidadeMedida.nome" name="produtoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="unidadeMedidaSelecionada" property="produto.unidadeMedida.id" name="produtoForm" />
								</div>
																							
							</div>
							<div class="row">

								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.produto.filtrar)">
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
								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.produto.inserir)">
									<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4" style="margin-bottom: 0px;">
										<button type="button" id="novo" class="btn btn-success btn-sm cor-sistema btn-block">
											<i class="fa fa-file-o"></i>
											Novo
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
			<logic:empty name="produtoForm" property="produtos">
				<div class="row" style="margin-top: 15px;">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="alert alert-warning">Nenhum registro encontrado!</div>
					</div>
				</div>
			</logic:empty>
			<logic:notEmpty name="produtoForm" property="produtos">
				<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.produto.filtrar)">
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
								<thead>
									<!-- CABEÇALHO DA TABELA -->
									<tr class="cor-sistema" style="color: white;">
										<th class="text-center">Local</th>
										<th>Nome</th>
										<th>Marca</th>
										<th>Categoria</th>
										<th class="text-center">Un.Med.</th>
										<th class="text-center" style="width: 80px;">Opções</th>
									</tr>
								</thead>
								<tbody>
									<!-- TABELA -->
									<logic:iterate id="produtoCorrente" name="produtoForm" property="produtos" type="br.com.srcsoftware.sistema.produto.produto.ProdutoDTO">

										<tr>
											<!-- success / info / warning / danger -->
											<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/produto.src?method=selecionar&produto.id=${produtoCorrente.id}')">${produtoCorrente.localizacao}</td>
											<td onclick="selecionar('${contextPath}/restrito/sistema/produto.src?method=selecionar&produto.id=${produtoCorrente.id}')">${produtoCorrente.nome}</td>
											<td onclick="selecionar('${contextPath}/restrito/sistema/produto.src?method=selecionar&produto.id=${produtoCorrente.id}')">${produtoCorrente.marca.nome}</td>
											<td onclick="selecionar('${contextPath}/restrito/sistema/produto.src?method=selecionar&produto.id=${produtoCorrente.id}')">${produtoCorrente.tipo.nome}</td>
											<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/produto.src?method=selecionar&produto.id=${produtoCorrente.id}')">${produtoCorrente.unidadeMedida.sigla}</td>
											
											<td class="text-center" style="min-width: 80px; vertical-align: middle;">
												<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.produto.excluir)">
													<button type="button" id="excluir" class="btn btn-danger btn-xs fa fa-trash" style="font-size: 14px" onclick="excluir('${produtoCorrente.nomeCompleto}', 'sistema/produto', ${produtoCorrente.id} );"></button>
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
			<logic:notEmpty name="produtoForm" property="produtos">
				<div class="panel-footer">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">

							<nav aria-label="Page navigation">
								<ul class="pagination" style="font-size: 12px;">
									<li>
										<a href="javascript://" style="cursor: default;">Encontrados: ${produtoForm.paginacao.totalRegistros} </a>
									</li>
									<logic:equal name="produtoForm" property="paginacao.voltarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="produtoForm" property="paginacao.voltarPaginacaoInativo" value="false">
										<li class="page-item">
											<a class="page-link" href="${contextPath}/restrito/sistema/produto.src?method=voltar&forwardDestino=produtoLista" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>

									<c:forEach var="i" begin="${produtoForm.paginacao.paginaInicial}" end="${produtoForm.paginacao.paginaFinal}" step="1" varStatus="row">
										<c:if test="${produtoForm.paginacao.paginaCorrente == i}">
											<li class="page-item active">
										</c:if>

										<c:if test="${produtoForm.paginacao.paginaCorrente != i}">
											<li class="page-item ">
										</c:if>

										<a class="page-link" href="${contextPath}/restrito/sistema/produto.src?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>
										</li>
									</c:forEach>

									<logic:equal name="produtoForm" property="paginacao.avancarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Next">
												<span aria-hidden="true">&raquo;</span>
												<span class="sr-only">Próximo</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="produtoForm" property="paginacao.avancarPaginacaoInativo" value="false">
										<li class="page-item ">
											<a class="page-link" href="${contextPath}/restrito/sistema/produto.src?method=avancar&forwardDestino=produtoLista" aria-label="Next">
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
		$( "#nome" ).focus();

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#nome").prop("maxlength", 100);
		$("#localizacao").prop("maxlength", 20);

		/* Setando os placeholder dos campos*/
		$( "#nome" ).prop("placeholder","Nome a ser pesquisado");
		$( "#localizacao" ).prop("placeholder","Localizacao a ser pesquisada");
		$( "#marca" ).prop("placeholder","Marca a ser pesquisada");
		$( "#tipo" ).prop("placeholder","Categoria a ser pesquisada");
		$( "#unidadeMedida" ).prop("placeholder","Unidade de medida a ser pesquisada");
		
		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_produto_consulta").prop("autocomplete", "off");
		
		$('#form_produto_consulta').on('submit', function(e){
	        abrirModalProcessando();
	    });
		
		$( '#pesquisar' ).click(function() {  	
			executar('form_produto_consulta','filtrar');
		});
		
		$( '#limparPesquisa' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_produto_consulta','limparFiltro');
		});
		
		$( '#novo' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_produto_consulta','abrirCadastro');
		});
		
		$('#nome').keyup(function() {
			if ($('#nome').val() == null || $('#nome').val() == '') {
				$('#produtoSelecionado').val(null);
			}
		});
		
		$('#nome').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'produto.nomeCompleto',
			/* params : {
				'saidaGrao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/produto.src?method=selecionarProdutoAutoComplete',
			onSelect : function(suggestion) {
				$('#produtoSelecionado').val(suggestion.data);
			},
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			}, */
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#produtoSelecionado').val(null);
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
			paramName : 'produto.marca.nome',
			/* params : {
				'saidaGrao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/produto.src?method=selecionarMarcaAutoComplete',
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
			paramName : 'produto.tipo.nome',
			/* params : {
				'saidaGrao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/produto.src?method=selecionarTipoAutoComplete',
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
		
		$('#unidadeMedida').keyup(function() {
			if ($('#unidadeMedida').val() == null || $('#unidadeMedida').val() == '') {
				$('#unidadeMedidaSelecionada').val(null);
			}
		});
		$('#unidadeMedida').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'produto.unidadeMedida.nome',
			/* params : {
				'saidaGrao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/produto.src?method=selecionarUnidadeMedidaAutoComplete',
			onSelect : function(suggestion) {
				$('#unidadeMedidaSelecionada').val(suggestion.data);
			},
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			}, */
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#unidadeMedidaSelecionada').val(null);
				}
			}
		});
	});
	
	function selecionar(urlAction) {
		abrirModalProcessando();
		window.location = urlAction;
	}
</script>