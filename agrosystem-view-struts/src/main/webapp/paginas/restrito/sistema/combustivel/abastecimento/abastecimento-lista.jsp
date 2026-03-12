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
			Pesquisar Abastecimentos
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Listagem das Abastecimentos
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<%-- <jsp:include page="../../../../errors/alerts-sucesso_erro.jsp"></jsp:include> --%>

<div class="row">

	<!-- Define o tamanho geral da tela -->
	<div class="col-md-offset-1 col-lg-offset-1 col-xs-12 col-sm-12 col-md-10 col-lg-10">
		<div class="panel" style="box-shadow: 0 10px 30px rgba(0, 0, 0, 0.8); border-color: #909090;">

			<!-- INICIO CAMPOS DE PESQUISA -->
			<div class="panel-heading cor-sistema">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_abastecimento_consulta" action="restrito/sistema/abastecimento" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label class="text-white">Data Inicial</label>
									<html:text styleClass="form-control input-sm data text-center" styleId="dataInicial" property="dataInicial" name="abastecimentoForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label class="text-white">Data Final</label>
									<html:text styleClass="form-control input-sm data text-center" styleId="dataFinal" property="dataFinal" name="abastecimentoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<label class="text-white">Requerente</label>
									<html:text styleClass="form-control input-sm" styleId="requerente" property="abastecimento.requerente.pessoaFisica.razaoSocial" name="abastecimentoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<label class="text-white">Almoxarife</label>
									<html:text styleClass="form-control input-sm" styleId="almoxarife" property="abastecimento.almoxarife.pessoaFisica.razaoSocial" name="abastecimentoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-5">
									<label class="text-white">Produto</label>
									<html:text styleClass="form-control input-sm" styleId="produto" property="abastecimento.produto.nome" name="abastecimentoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="produtoSelecionado" property="abastecimento.produto.id" name="abastecimentoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-5">
									<label class="text-white">Veiculo</label>
									<html:text styleClass="form-control input-sm" styleId="veiculo" property="abastecimento.veiculo.nomeCompleto" name="abastecimentoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
								</div>
								<%-- <div class="form-group col-xs-12 col-sm-4 col-md-2 col-lg-2">
									<label class="text-white">Código Veiculo</label>
									<html:text styleClass="form-control input-sm text-center" styleId="codigoVeiculo" property="abastecimento.veiculo.codigo" name="abastecimentoForm" />
								</div> --%>

							</div>
							<div class="row">

								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.abastecimento.filtrar)">
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
								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.abastecimento.inserir)">
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
			<logic:empty name="abastecimentoForm" property="abastecimentos">
				<div class="row" style="margin-top: 15px;">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="alert alert-warning">Nenhum registro encontrado!</div>
					</div>
				</div>
			</logic:empty>
			<logic:notEmpty name="abastecimentoForm" property="abastecimentos">
				<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.abastecimento.filtrar)">
					<div class="panel-body">
						<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
							<span class="label cor-sistema" style="font-size: 15px;">Total quantidade: ${abastecimentoForm.totalQuantidade}&nbsp;litros</span>
							<span class="label cor-sistema" style="font-size: 15px;">Total Custo: R$ ${abastecimentoForm.totalValor}</span>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
							<div class="table-responsive">
								<table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
									<thead>
										<!-- CABEÇALHO DA TABELA -->
										<tr class="cor-sistema" style="color: white;">
											<th>Veiculo</th>
											<th>Km / Horímetro</th>
											<th class="text-center">Data</th>
											<th>Produto</th>
											<th class="text-right">Quantidade</th>
											<th class="text-right">Custo Total</th>
											<th>Requerente</th>
											<th>Almoxarife</th>
											<th class="text-center" style="width: 80px;">Opções</th>
										</tr>
									</thead>
									<tbody>
										<!-- TABELA -->
										<logic:iterate id="abastecimentoCorrente" name="abastecimentoForm" property="abastecimentos" type="br.com.srcsoftware.sistema.combustivel.abastecimento.AbastecimentoDTO">

											<tr>
												<td onclick="selecionar('${contextPath}/restrito/sistema/abastecimento.src?method=selecionar&abastecimento.id=${abastecimentoCorrente.id}')">${abastecimentoCorrente.veiculo.nomeCompleto}</td>
												<td onclick="selecionar('${contextPath}/restrito/sistema/abastecimento.src?method=selecionar&abastecimento.id=${abastecimentoCorrente.id}')">${abastecimentoCorrente.kmHorimetro}</td>

												<td onclick="selecionar('${contextPath}/restrito/sistema/abastecimento.src?method=selecionar&abastecimento.id=${abastecimentoCorrente.id}')">${abastecimentoCorrente.dataToString}</td>
												<%-- <td onclick="selecionar('${contextPath}/restrito/sistema/abastecimento.src?method=selecionar&abastecimento.id=${abastecimentoCorrente.id}')">
												${abastecimentoCorrente.produto.nome}&nbsp;/${abastecimentoCorrente.produto.marca.nome}&nbsp;/${abastecimentoCorrente.produto.tipo.nome}</td> --%>
												<td onclick="selecionar('${contextPath}/restrito/sistema/abastecimento.src?method=selecionar&abastecimento.id=${abastecimentoCorrente.id}')">${abastecimentoCorrente.produto.nomeCompleto}</td>
												<td class="text-right" onclick="selecionar('${contextPath}/restrito/sistema/abastecimento.src?method=selecionar&abastecimento.id=${abastecimentoCorrente.id}')">
													${abastecimentoCorrente.quantidade}&nbsp;${abastecimentoCorrente.produto.unidadeMedida.sigla}</td>
												<td class="text-right" onclick="selecionar('${contextPath}/restrito/sistema/abastecimento.src?method=selecionar&abastecimento.id=${abastecimentoCorrente.id}')">${abastecimentoCorrente.custoTotal}</td>

												<td onclick="selecionar('${contextPath}/restrito/sistema/abastecimento.src?method=selecionar&abastecimento.id=${abastecimentoCorrente.id}')">${abastecimentoCorrente.requerente.pessoaFisica.razaoSocial}</td>
												<td onclick="selecionar('${contextPath}/restrito/sistema/abastecimento.src?method=selecionar&abastecimento.id=${abastecimentoCorrente.id}')">${abastecimentoCorrente.almoxarife.pessoaFisica.razaoSocial}</td>

												<td class="text-center" style="min-width: 80px; vertical-align: middle;">
													<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.abastecimento.excluir)">
														<button type="button" id="excluir" class="btn btn-danger btn-xs fa fa-trash" style="font-size: 14px"
															onclick="excluir('Data: ${abastecimentoCorrente.dataToString}<br/>Produto: ${abastecimentoCorrente.produto.nomeCompleto}<br/>Veiculo: ${abastecimentoCorrente.veiculo.nomeCompleto}', 'sistema/abastecimento', ${abastecimentoCorrente.id} );"></button>
													</logic:equal>
												</td>

											</tr>
										</logic:iterate>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</logic:equal>
			</logic:notEmpty>
			<!-- TERMINO TABELA -->
			<!-- /.panel-body -->

			<!-- INICIO PAGINAÇÃO -->
			<logic:notEmpty name="abastecimentoForm" property="abastecimentos">
				<div class="panel-footer">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">

							<nav aria-label="Page navigation">
								<ul class="pagination" style="font-size: 12px;">
									<li>
										<a href="javascript://" style="cursor: default;">Encontrados: ${abastecimentoForm.paginacao.totalRegistros} </a>
									</li>
									<logic:equal name="abastecimentoForm" property="paginacao.voltarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="abastecimentoForm" property="paginacao.voltarPaginacaoInativo" value="false">
										<li class="page-item">
											<a class="page-link" href="${contextPath}/restrito/sistema/abastecimento.src?method=voltar&forwardDestino=abastecimentoLista" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>

									<c:forEach var="i" begin="${abastecimentoForm.paginacao.paginaInicial}" end="${abastecimentoForm.paginacao.paginaFinal}" step="1" varStatus="row">
										<c:if test="${abastecimentoForm.paginacao.paginaCorrente == i}">
											<li class="page-item active">
										</c:if>

										<c:if test="${abastecimentoForm.paginacao.paginaCorrente != i}">
											<li class="page-item ">
										</c:if>

										<a class="page-link" href="${contextPath}/restrito/sistema/abastecimento.src?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>
										</li>
									</c:forEach>

									<logic:equal name="abastecimentoForm" property="paginacao.avancarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Next">
												<span aria-hidden="true">&raquo;</span>
												<span class="sr-only">Próximo</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="abastecimentoForm" property="paginacao.avancarPaginacaoInativo" value="false">
										<li class="page-item ">
											<a class="page-link" href="${contextPath}/restrito/sistema/abastecimento.src?method=avancar&forwardDestino=abastecimentoLista" aria-label="Next">
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
		$( "#dataInicial" ).focus();

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#requerente").prop("maxlength", 100);
		$("#almoxarife").prop("maxlength", 100);
		$("#codigoVeiculo").prop("maxlength", 10);		
		$("#produto").prop("maxlength", 50);
		$("#veiculo").prop("maxlength", 50);

		/* Setando os placeholder dos campos*/
		$( "#requerente" ).prop("placeholder","Requerente pesquisar");
		$( "#almoxarife" ).prop("placeholder","Almoxarife pesquisar");
		$( "#veiculo" ).prop("placeholder","Veiculo a ser pesquisado");
		$( "#codigoVeiculo" ).prop("placeholder","Cod. Veiculo");
		$( "#produto" ).prop("placeholder","Produto a ser pesquisado");

		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_abastecimento_consulta").prop("autocomplete", "off");
		
		$('#form_abastecimento_consulta').on('submit', function(e){
	        abrirModalProcessando();
	    });
		
		$( '#pesquisar' ).click(function() {  	
			executar('form_abastecimento_consulta','filtrar');
		});
		
		$( '#limparPesquisa' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_abastecimento_consulta','limparFiltro');
		});
		
		$( '#novo' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_abastecimento_consulta','abrirCadastro');
		});
		
		$('#veiculo').autocomplete({
			minChars : 2,			
			paramName : 'abastecimento.veiculo.nomeCompleto',
			serviceUrl : '${contextPath}/restrito/sistema/abastecimento.src?method=selecionarVeiculoAutoComplete',
			onSelect : function(suggestion) {				
							
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {					
					
				}
			}
		});	
		
		$('#produto').keyup(function() {
			if ($('#produto').val() == null || $('#produto').val() == '') {
				$('#produtoSelecionado').val(null);
			}
		});
		$('#produto').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'abastecimento.produto.nome',
			serviceUrl : '${contextPath}/restrito/sistema/abastecimento.src?method=selecionarProdutoAutoComplete',
			onSelect : function(suggestion) {
				//alert('You selected: ' + suggestion.value + ', ' + suggestion.data);
				$('#produtoSelecionado').val(suggestion.data);
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#produtoSelecionado').val(null);
				}
			}
		});
		
		$('#requerente').autocomplete({
			minChars : 2,
			paramName : 'abastecimento.requerente.pessoaFisica.razaoSocial',
			serviceUrl : '${contextPath}/restrito/sistema/abastecimento.src?method=selecionarRequerenteAutoComplete',
			onSelect : function(suggestion) {
				//alert('You selected: ' + suggestion.value + ', ' + suggestion.data);
				
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					
				}
			}
		});
		
		$('#almoxarife').autocomplete({
			minChars : 2,
			paramName : 'abastecimento.almoxarife.pessoaFisica.razaoSocial',
			serviceUrl : '${contextPath}/restrito/sistema/abastecimento.src?method=selecionarAlmoxarifeAutoComplete',
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