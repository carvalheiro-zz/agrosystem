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
			Pesquisar Itens em Imóveis
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Listagem dos Itens em Imóveis
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
						<html:form styleId="form_itemImovel_consulta" action="restrito/sistema/itemImovel" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<div class="form-group col-xs-12 col-sm-4 col-md-3 col-lg-3">
									<label class="text-white">Data Inicial</label>
									<html:text styleClass="form-control input-sm data text-center" styleId="dataInicial" property="dataInicial" name="itemImovelForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-4 col-md-3 col-lg-3">
									<label class="text-white">Data Final</label>
									<html:text styleClass="form-control input-sm data text-center" styleId="dataFinal" property="dataFinal" name="itemImovelForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label class="text-white">Requerente</label>
									<html:text styleClass="form-control input-sm" styleId="requerente" property="itemImovel.requerente.pessoaFisica.razaoSocial" name="itemImovelForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label class="text-white">Almoxarife</label>
									<html:text styleClass="form-control input-sm" styleId="almoxarife" property="itemImovel.almoxarife.pessoaFisica.razaoSocial" name="itemImovelForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3">
									<label class="text-white">Imovel</label>
									<html:text styleClass="form-control input-sm" styleId="imovel" property="itemImovel.imovel.nome" name="itemImovelForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="imovelSelecionado" property="itemImovel.imovel.id" name="itemImovelForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-5">
									<label class="text-white">Produto</label>
									<html:text styleClass="form-control input-sm" styleId="produto" property="itemImovel.produto.nomeCompleto" name="itemImovelForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="produtoSelecionado" property="itemImovel.produto.id" name="itemImovelForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label class="text-white">Prestador</label>
									<html:text styleClass="form-control input-sm" styleId="prestador" property="itemImovel.prestador.nome" name="itemImovelForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="prestadorSelecionado" property="itemImovel.prestador.id" name="itemImovelForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-4 col-md-3 col-lg-3">
									<label class="text-white">Atribuição de Custo</label>
									<html:select styleClass="form-control input-sm" styleId="tipo" property="itemImovel.tipo" name="itemImovelForm">
										<html:option value="">Selecione</html:option>
										<html:option value="Safra/Setor">Safra/Setor</html:option>
										<html:option value="Tudo">Tudo</html:option>
										<html:option value="Cultura">Cultura</html:option>
									</html:select>
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
									<label class="text-white">Safra</label>
									<html:text styleClass="form-control input-sm" styleId="safra" property="itemImovel.safra.nome" name="itemImovelForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="safraSelecionada" property="itemImovel.safra.id" name="itemImovelForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
									<label class="text-white">Setor</label>
									<html:text styleClass="form-control input-sm" styleId="setor" property="itemImovel.setor.nomeCompleto" name="itemImovelForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="setorSelecionado" property="itemImovel.setor.id" name="itemImovelForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label class="text-white">Cultura</label>
									<html:text styleClass="form-control input-sm" styleId="cultura" property="itemImovel.cultura.nome" name="itemImovelForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="culturaSelecionada" property="itemImovel.cultura.id" name="itemImovelForm" />
								</div>




							</div>
							<div class="row">

								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.item.filtrar)">
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
								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.item.inserir)">
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
			<logic:empty name="itemImovelForm" property="itens">
				<div class="row" style="margin-top: 15px;">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="alert alert-warning">Nenhum registro encontrado!</div>
					</div>
				</div>
			</logic:empty>
			<logic:notEmpty name="itemImovelForm" property="itens">
				<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.item.filtrar)">
					<div class="panel-body">
						<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
							<span class="label cor-sistema" style="font-size: 15px;">Total quantidade: ${itemImovelForm.totalQuantidade}</span>
							<span class="label cor-sistema" style="font-size: 15px;">Total Custo: R$ ${itemImovelForm.totalValor}</span>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
							<div class="table-responsive">
								<table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
									<thead>
										<!-- CABEÇALHO DA TABELA -->
										<tr class="cor-sistema" style="color: white;">
											<th class="text-center">Data</th>
											<th>Produto</th>
											<th>Imóvel</th>
											<th class="text-right">Quantidade</th>
											<th class="text-right">Custo Total</th>
											<th>Atribuição de Custo</th>
											<th>Requerente</th>
											<th>Almoxarife</th>
											<th class="text-center" style="width: 80px;">Opções</th>
										</tr>
									</thead>
									<tbody>
										<!-- TABELA -->
										<logic:iterate id="itemImovelCorrente" name="itemImovelForm" property="itens" type="br.com.srcsoftware.sistema.aplicacao.item.ItemDTO">

											<tr>
												<!-- success / info / warning / danger -->
												<td onclick="selecionar('${contextPath}/restrito/sistema/itemImovel.src?method=selecionar&itemImovel.id=${itemImovelCorrente.id}')">${itemImovelCorrente.dataToString}</td>
												<td onclick="selecionar('${contextPath}/restrito/sistema/itemImovel.src?method=selecionar&itemImovel.id=${itemImovelCorrente.id}')">${itemImovelCorrente.produto.nomeCompleto}</td>
												<td onclick="selecionar('${contextPath}/restrito/sistema/itemImovel.src?method=selecionar&itemImovel.id=${itemImovelCorrente.id}')">${itemImovelCorrente.imovel.nome}</td>

												<td class="text-right" onclick="selecionar('${contextPath}/restrito/sistema/itemImovel.src?method=selecionar&itemImovel.id=${itemImovelCorrente.id}')">${itemImovelCorrente.quantidade}&nbsp;${itemImovelCorrente.produto.unidadeMedida.sigla}</td>
												<td class="text-right" onclick="selecionar('${contextPath}/restrito/sistema/itemImovel.src?method=selecionar&itemImovel.id=${itemImovelCorrente.id}')">${itemImovelCorrente.custoTotal}</td>

												<td style="background-color: #e6e6e6;" onclick="selecionar('${contextPath}/restrito/sistema/itemImovel.src?method=selecionar&itemImovel.id=${itemImovelCorrente.id}')">
													<strong>${itemImovelCorrente.tipo}:</strong>
													${itemImovelCorrente.nomeCompleto}
												</td>

												<td onclick="selecionar('${contextPath}/restrito/sistema/itemImovel.src?method=selecionar&itemImovel.id=${itemImovelCorrente.id}')">${itemImovelCorrente.requerente.pessoaFisica.razaoSocial}</td>
												<td onclick="selecionar('${contextPath}/restrito/sistema/itemImovel.src?method=selecionar&itemImovel.id=${itemImovelCorrente.id}')">${itemImovelCorrente.almoxarife.pessoaFisica.razaoSocial}</td>

												<td class="text-center" style="min-width: 80px; vertical-align: middle;">
													<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.item.excluir)">
														<button type="button" id="excluir" class="btn btn-danger btn-xs fa fa-trash" style="font-size: 14px"
															onclick="excluir('Data: ${itemImovelCorrente.dataToString}<br/>Produto: ${itemImovelCorrente.produto.nomeCompleto.replaceAll('\"', '&quot;').replaceAll('\'', '&quot;')}<br/>Safra: ${itemImovelCorrente.safra.nomeCompleto}<br/>Setor: ${itemImovelCorrente.setor.nomeCompleto}', 'sistema/itemImovel', ${itemImovelCorrente.id} );"></button>
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
			<logic:notEmpty name="itemImovelForm" property="itens">
				<div class="panel-footer">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">

							<nav aria-label="Page navigation">
								<ul class="pagination" style="font-size: 12px;">
									<li>
										<a href="javascript://" style="cursor: default;">Encontrados: ${itemImovelForm.paginacao.totalRegistros} </a>
									</li>
									<logic:equal name="itemImovelForm" property="paginacao.voltarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="itemImovelForm" property="paginacao.voltarPaginacaoInativo" value="false">
										<li class="page-item">
											<a class="page-link" href="${contextPath}/restrito/sistema/itemImovel.src?method=voltar&forwardDestino=itemImovelLista" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>

									<c:forEach var="i" begin="${itemImovelForm.paginacao.paginaInicial}" end="${itemImovelForm.paginacao.paginaFinal}" step="1" varStatus="row">
										<c:if test="${itemImovelForm.paginacao.paginaCorrente == i}">
											<li class="page-item active">
										</c:if>

										<c:if test="${itemImovelForm.paginacao.paginaCorrente != i}">
											<li class="page-item ">
										</c:if>

										<a class="page-link" href="${contextPath}/restrito/sistema/itemImovel.src?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>
										</li>
									</c:forEach>

									<logic:equal name="itemImovelForm" property="paginacao.avancarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Next">
												<span aria-hidden="true">&raquo;</span>
												<span class="sr-only">Próximo</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="itemImovelForm" property="paginacao.avancarPaginacaoInativo" value="false">
										<li class="page-item ">
											<a class="page-link" href="${contextPath}/restrito/sistema/itemImovel.src?method=avancar&forwardDestino=itemImovelLista" aria-label="Next">
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
		$("#imovel").prop("maxlength", 50);
		$("#safra").attr("maxlength", 100);
		$("#setor").attr("maxlength", 20);
		$("#cultura").attr("maxlength", 50);
		$("#prestador").attr("maxlength", 50);
		$("#prestador").attr("maxlength", 50);
		$( "#prestador" ).attr("placeholder","Prestador");

		/* Setando os placeholder dos campos*/
		$( "#requerente" ).prop("placeholder","Requerente pesquisar");
		$( "#almoxarife" ).prop("placeholder","Almoxarife pesquisar");
		$( "#codigoVeiculo" ).prop("placeholder","Cod. Veiculo");
		$( "#produto" ).prop("placeholder","Produto a ser pesquisado");
		$( "#imovel" ).attr("placeholder","Imovel a ser pesquisado");
		$( "#safra" ).attr("placeholder","Safra pesquisar");
		$( "#setor" ).attr("placeholder","Setor pesquisar");
		$( "#cultura" ).attr("placeholder","Cultura pesquisar");
		$( "#prestador" ).attr("placeholder","Prestador pesquisar");

		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_itemImovel_consulta").prop("autocomplete", "off");
		
		$('#form_itemImovel_consulta').on('submit', function(e){
	        abrirModalProcessando();
	    });
		
		$( '#pesquisar' ).click(function() {  	
			executar('form_itemImovel_consulta','filtrar');
		});
		
		$( '#limparPesquisa' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_itemImovel_consulta','limparFiltro');
		});
		
		$( '#novo' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_itemImovel_consulta','abrirCadastro');
		});
			
		$('#produto').keyup(function() {
			if ($('#produto').val() == null || $('#produto').val() == '') {
				$('#produtoSelecionado').val(null);
			}
		});
		$('#produto').autocomplete({
			minChars : 4,
			noCache : true,
			paramName : 'itemImovel.produto.nomeCompleto',
			serviceUrl : '${contextPath}/restrito/sistema/itemImovel.src?method=selecionarProdutoAutoComplete',
			onSelect : function(suggestion) {
				$('#produtoSelecionado').val(suggestion.data);
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#imovelSelecionado').val(null);
				}
			}
		});
		
		$('#imovel').keyup(function() {
			if ($('#imovel').val() == null || $('#imovel').val() == '') {
				$('#imovelSelecionado').val(null);
			}
		});
		$('#imovel').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'itemImovel.imovel.nome',
			/* params : {
				'saidaGrao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/itemImovel.src?method=selecionarImovelAutoComplete',
			onSelect : function(suggestion) {
				$('#imovelSelecionado').val(suggestion.data);
			},
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			}, */
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#imovelSelecionado').val(null);
				}
			}
		});
		
		$('#requerente').autocomplete({
			minChars : 2,
			paramName : 'itemImovel.requerente.pessoaFisica.razaoSocial',
			serviceUrl : '${contextPath}/restrito/sistema/itemImovel.src?method=selecionarRequerenteAutoComplete',
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
			paramName : 'itemImovel.almoxarife.pessoaFisica.razaoSocial',
			serviceUrl : '${contextPath}/restrito/sistema/itemImovel.src?method=selecionarAlmoxarifeAutoComplete',
			onSelect : function(suggestion) {
				//alert('You selected: ' + suggestion.value + ', ' + suggestion.data);
				
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					
				}
			}
		});	
				
		$('#prestador').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'prestadorServico.nome',			
			serviceUrl : '${contextPath}/restrito/sistema/prestadorServico.src?method=selecionarPrestadorServicoAutoComplete',
			onSelect : function(suggestion) {
				
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					
				}
			}
		});
		
		$('#safra').keyup(function() {
			if ($('#safra').val() == null || $('#safra').val().trim() == '') {
				$('#safraSelecionada').val(null);

				$('#setor').val(null);
				$('#setorSelecionado').val(null);

				$('#cultura').val(null);
				$('#culturaSelecionada').val(null);
			}
		});

		$('#safra').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'itemImovel.safra.nome',
			serviceUrl : '${contextPath}/restrito/sistema/itemImovel.src?method=selecionarSafraAutoComplete',
			onSelect : function(suggestion) {
				$('#safraSelecionada').val(suggestion.data);

				if ($('#tipo').val() == '') {
					$("#safra").focus();
				} else if ($('#tipo').val() == 'Safra/Setor') {
					$("#setor").focus();
				} else if ($('#tipo').val() == 'Tudo') {
					$("#almoxarife").focus();
				} else if ($('#tipo').val() == 'Cultura') {
					$("#cultura").focus();
				}
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#safraSelecionada').val(null);

					$('#setor').val(null);
					$('#setorSelecionado').val(null);

					$('#cultura').val(null);
					$('#culturaSelecionada').val(null);
				}
			}
		});

		$('#setor').keyup(function() {
			if ($('#setor').val() == null || $('#setor').val().trim() == '') {
				$('#setorSelecionado').val(null);

				$('#cultura').val(null);
				$('#culturaSelecionada').val(null);
			}
		});

		$('#setor').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'itemImovel.setor.nomeCompleto',
			params : {
				'itemImovel.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			},
			serviceUrl : '${contextPath}/restrito/sistema/itemImovel.src?method=selecionarSetorAutoComplete',
			onSelect : function(suggestion) {
				$('#setorSelecionado').val(suggestion.data);

				if ($('#tipo').val() == '') {
					$("#safra").focus();
				} else if ($('#tipo').val() == 'Safra/Setor') {
					$("#almoxarife").focus();
				} else if ($('#tipo').val() == 'Tudo') {
					$("#safra").focus();
				} else if ($('#tipo').val() == 'Cultura') {
					$("#cultura").focus();
				}
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#setorSelecionado').val(null);

					$('#cultura').val(null);
					$('#culturaSelecionada').val(null);
				}
			}
		});

		$('#cultura').keyup(function() {
			if ($('#cultura').val() == null || $('#cultura').val().trim() == '') {
				$('#culturaSelecionada').val(null);
			}
		});

		$('#cultura').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'itemImovel.cultura.nome',
			params : {
				'itemImovel.setor.id' : function() {
					return $('#setorSelecionado').val();
				},
				'itemImovel.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			},
			/* params : {
				'saidaGrao.itemImovel.id' : function() {
					return $('#itemImovelSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/itemImovel.src?method=selecionarCulturaAutoComplete',
			onSelect : function(suggestion) {
				$('#culturaSelecionada').val(suggestion.data);

				$("#almoxarife").focus();
			},
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			}, */
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#culturaSelecionada').val(null);
				}
			}
		});

		$('#tipo').change(function() { 
			$('#safra').val(null);
			$('#safraSelecionada').val(null);
			$('#setor').val(null);
			$('#setorSelecionado').val(null);
			$('#cultura').val(null);
			$('#culturaSelecionada').val(null);
			gerenciarCamposSafraSetorCultura();
		});
		
		gerenciarCamposSafraSetorCultura();
	});
	
	function gerenciarCamposSafraSetorCultura() {
		if ($('#tipo').val() == '') {
			//$("#safra").removeClass("obrigatorio");
			//$("#cultura").removeClass("obrigatorio");
			//$("#setor").removeClass("obrigatorio");

			$("#safra").addClass("bloqueado");
			$("#cultura").addClass("bloqueado");
			$("#setor").addClass("bloqueado");

			$('#safra').val(null);
			$('#safraSelecionada').val(null);
			$('#setor').val(null);
			$('#setorSelecionado').val(null);
			$('#cultura').val(null);
			$('#variedade').val(null);
			$('#culturaSelecionada').val(null);

		} else if ($('#tipo').val() == 'Safra/Setor') {
			//$("#safra").addClass("obrigatorio");
			//$("#setor").addClass("obrigatorio");

			$("#safra").removeClass("bloqueado");
			$("#setor").removeClass("bloqueado");

			//$("#cultura").removeClass("obrigatorio");
			$("#cultura").addClass("bloqueado");

			$("#safra").focus();
		} else if ($('#tipo').val() == 'Tudo') {
			//$("#safra").addClass("obrigatorio");

			$("#safra").removeClass("bloqueado");

			//$("#setor").removeClass("obrigatorio");
			$("#setor").addClass("bloqueado");

			//$("#cultura").removeClass("obrigatorio");
			$("#cultura").addClass("bloqueado");

			$("#safra").focus();
		} else if ($('#tipo').val() == 'Cultura') {
			//$("#safra").addClass("obrigatorio");
			//$("#cultura").addClass("obrigatorio");

			$("#safra").removeClass("bloqueado");
			$("#cultura").removeClass("bloqueado");

			//$("#setor").removeClass("obrigatorio");
			$("#setor").addClass("bloqueado");

			$("#safra").focus();
		}

		recarregarObrigatorios();
	}
	
	function selecionar(urlAction) {
		abrirModalProcessando();
		window.location = urlAction;
	}
</script>