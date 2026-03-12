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
			Pesquisar Itens em Veículos
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Listagem dos Itens em Veículos
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
						<html:form styleId="form_itemVeiculo_consulta" action="restrito/sistema/itemVeiculo" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<div class="form-group col-xs-12 col-sm-4 col-md-3 col-lg-3">
									<label class="text-white">Data Inicial</label>
									<html:text styleClass="form-control input-sm data text-center" styleId="dataInicial" property="dataInicial" name="itemVeiculoForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-4 col-md-3 col-lg-3">
									<label class="text-white">Data Final</label>
									<html:text styleClass="form-control input-sm data text-center" styleId="dataFinal" property="dataFinal" name="itemVeiculoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label class="text-white">Requerente</label>
									<html:text styleClass="form-control input-sm" styleId="requerente" property="itemVeiculo.requerente.pessoaFisica.razaoSocial" name="itemVeiculoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label class="text-white">Almoxarife</label>
									<html:text styleClass="form-control input-sm" styleId="almoxarife" property="itemVeiculo.almoxarife.pessoaFisica.razaoSocial" name="itemVeiculoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label class="text-white">Veiculo</label>
									<html:text styleClass="form-control input-sm" styleId="veiculo" property="itemVeiculo.veiculo.nomeCompleto" name="itemVeiculoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="veiculoSelecionado" property="itemVeiculo.veiculo.id" name="itemVeiculoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<label class="text-white">Produto</label>
									<html:text styleClass="form-control input-sm" styleId="produto" property="itemVeiculo.produto.nomeCompleto" name="itemVeiculoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="produtoSelecionado" property="itemVeiculo.produto.id" name="itemVeiculoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label class="text-white">Prestador de Serviço</label>
									<html:text styleClass="form-control input-sm" styleId="prestador" property="itemVeiculo.prestador.nome" name="itemVeiculoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="prestadorSelecionado" property="itemVeiculo.prestador.id" name="itemVeiculoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-4 col-md-3 col-lg-3">
									<label class="text-white">Atribuição de Custo</label>
									<html:select styleClass="form-control input-sm" styleId="tipo" property="itemVeiculo.tipo" name="itemVeiculoForm">
										<html:option value="">Selecione</html:option>
										<html:option value="Safra/Setor">Safra/Setor</html:option>
										<html:option value="Tudo">Tudo</html:option>
										<html:option value="Cultura">Cultura</html:option>
									</html:select>
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label class="text-white">Safra</label>
									<html:text styleClass="form-control input-sm" styleId="safra" property="itemVeiculo.safra.nome" name="itemVeiculoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="safraSelecionada" property="itemVeiculo.safra.id" name="itemVeiculoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label class="text-white">Setor</label>
									<html:text styleClass="form-control input-sm" styleId="setor" property="itemVeiculo.setor.nomeCompleto" name="itemVeiculoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="setorSelecionado" property="itemVeiculo.setor.id" name="itemVeiculoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label class="text-white">Cultura</label>
									<html:text styleClass="form-control input-sm" styleId="cultura" property="itemVeiculo.cultura.nome" name="itemVeiculoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="culturaSelecionada" property="itemVeiculo.cultura.id" name="itemVeiculoForm" />
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
			<logic:empty name="itemVeiculoForm" property="itens">
				<div class="row" style="margin-top: 15px;">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="alert alert-warning">Nenhum registro encontrado!</div>
					</div>
				</div>
			</logic:empty>
			<logic:notEmpty name="itemVeiculoForm" property="itens">
				<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.item.filtrar)">
					<div class="panel-body">
						<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
							<span class="label cor-sistema" style="font-size: 15px;">Total quantidade: ${itemVeiculoForm.totalQuantidade}</span>
							<span class="label cor-sistema" style="font-size: 15px;">Total Custo: R$ ${itemVeiculoForm.totalValor}</span>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
							<div class="table-responsive">
								<table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
									<thead>
										<!-- CABEÇALHO DA TABELA -->
										<tr class="cor-sistema" style="color: white;">
											<th class="text-center">Data</th>
											<th>Produto</th>
											<th>Veículo</th>
											<th>Km / Horímetro</th>
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
										<logic:iterate id="itemVeiculoCorrente" name="itemVeiculoForm" property="itens" type="br.com.srcsoftware.sistema.aplicacao.item.ItemDTO">

											<tr>
												<!-- success / info / warning / danger -->
												<td onclick="selecionar('${contextPath}/restrito/sistema/itemVeiculo.src?method=selecionar&itemVeiculo.id=${itemVeiculoCorrente.id}')">${itemVeiculoCorrente.dataToString}</td>
												<td onclick="selecionar('${contextPath}/restrito/sistema/itemVeiculo.src?method=selecionar&itemVeiculo.id=${itemVeiculoCorrente.id}')">${itemVeiculoCorrente.produto.nomeCompleto}</td>
												<td onclick="selecionar('${contextPath}/restrito/sistema/itemVeiculo.src?method=selecionar&itemVeiculo.id=${itemVeiculoCorrente.id}')">${itemVeiculoCorrente.veiculo.nomeCompleto}</td>
												<td onclick="selecionar('${contextPath}/restrito/sistema/itemVeiculo.src?method=selecionar&itemVeiculo.id=${itemVeiculoCorrente.id}')">${itemVeiculoCorrente.kmHorimetro}</td>

												<td class="text-right" onclick="selecionar('${contextPath}/restrito/sistema/itemVeiculo.src?method=selecionar&itemVeiculo.id=${itemVeiculoCorrente.id}')">${itemVeiculoCorrente.quantidade}&nbsp;${itemVeiculoCorrente.produto.unidadeMedida.sigla}</td>
												<td class="text-right" onclick="selecionar('${contextPath}/restrito/sistema/itemVeiculo.src?method=selecionar&itemVeiculo.id=${itemVeiculoCorrente.id}')">${itemVeiculoCorrente.custoTotal}</td>

												<td style="background-color: #e6e6e6;" onclick="selecionar('${contextPath}/restrito/sistema/itemVeiculo.src?method=selecionar&itemVeiculo.id=${itemVeiculoCorrente.id}')">
													<strong>${itemVeiculoCorrente.tipo}:</strong>
													${itemVeiculoCorrente.nomeCompleto}
												</td>

												<td onclick="selecionar('${contextPath}/restrito/sistema/itemVeiculo.src?method=selecionar&itemVeiculo.id=${itemVeiculoCorrente.id}')">${itemVeiculoCorrente.requerente.pessoaFisica.razaoSocial}</td>
												<td onclick="selecionar('${contextPath}/restrito/sistema/itemVeiculo.src?method=selecionar&itemVeiculo.id=${itemVeiculoCorrente.id}')">${itemVeiculoCorrente.almoxarife.pessoaFisica.razaoSocial}</td>

												<td class="text-center" style="min-width: 80px; vertical-align: middle;">
													<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.item.excluir)">
														<button type="button" id="excluir" class="btn btn-danger btn-xs fa fa-trash" style="font-size: 14px"
															onclick="excluir('Data: ${itemVeiculoCorrente.dataToString}<br/>Produto: ${itemVeiculoCorrente.produto.nomeCompleto}<br/>Safra: ${itemVeiculoCorrente.safra.nomeCompleto}<br/>Setor: ${itemVeiculoCorrente.setor.nomeCompleto}', 'sistema/itemVeiculo', ${itemVeiculoCorrente.id} );"></button>
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
			<logic:notEmpty name="itemVeiculoForm" property="itens">
				<div class="panel-footer">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">

							<nav aria-label="Page navigation">
								<ul class="pagination" style="font-size: 12px;">
									<li>
										<a href="javascript://" style="cursor: default;">Encontrados: ${itemVeiculoForm.paginacao.totalRegistros} </a>
									</li>
									<logic:equal name="itemVeiculoForm" property="paginacao.voltarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="itemVeiculoForm" property="paginacao.voltarPaginacaoInativo" value="false">
										<li class="page-item">
											<a class="page-link" href="${contextPath}/restrito/sistema/itemVeiculo.src?method=voltar&forwardDestino=itemVeiculoLista" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>

									<c:forEach var="i" begin="${itemVeiculoForm.paginacao.paginaInicial}" end="${itemVeiculoForm.paginacao.paginaFinal}" step="1" varStatus="row">
										<c:if test="${itemVeiculoForm.paginacao.paginaCorrente == i}">
											<li class="page-item active">
										</c:if>

										<c:if test="${itemVeiculoForm.paginacao.paginaCorrente != i}">
											<li class="page-item ">
										</c:if>

										<a class="page-link" href="${contextPath}/restrito/sistema/itemVeiculo.src?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>
										</li>
									</c:forEach>

									<logic:equal name="itemVeiculoForm" property="paginacao.avancarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Next">
												<span aria-hidden="true">&raquo;</span>
												<span class="sr-only">Próximo</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="itemVeiculoForm" property="paginacao.avancarPaginacaoInativo" value="false">
										<li class="page-item ">
											<a class="page-link" href="${contextPath}/restrito/sistema/itemVeiculo.src?method=avancar&forwardDestino=itemVeiculoLista" aria-label="Next">
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
		$("#safra").attr("maxlength", 100);
		$("#setor").attr("maxlength", 20);
		$("#cultura").attr("maxlength", 50);
		$("#prestador").attr("maxlength", 50);
		$( "#prestador" ).attr("placeholder","Prestador de Serviço pesquisar");

		/* Setando os placeholder dos campos*/
		$( "#requerente" ).prop("placeholder","Requerente pesquisar");
		$( "#almoxarife" ).prop("placeholder","Almoxarife pesquisar");
		$( "#codigoVeiculo" ).prop("placeholder","Cod. Veiculo");
		$( "#produto" ).prop("placeholder","Produto a ser pesquisado");
		$( "#veiculo" ).attr("placeholder","Veiculo a ser pesquisado");
		$( "#safra" ).attr("placeholder","Safra pesquisar");
		$( "#setor" ).attr("placeholder","Setor pesquisar");
		$( "#cultura" ).attr("placeholder","Cultura pesquisar");

		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_itemVeiculo_consulta").prop("autocomplete", "off");
		
		$('#form_itemVeiculo_consulta').on('submit', function(e){
	        abrirModalProcessando();
	    });
		
		$( '#pesquisar' ).click(function() {  	
			executar('form_itemVeiculo_consulta','filtrar');
		});
		
		$( '#limparPesquisa' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_itemVeiculo_consulta','limparFiltro');
		});
		
		$( '#novo' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_itemVeiculo_consulta','abrirCadastro');
		});
				
		$('#produto').keyup(function() {
			if ($('#produto').val() == null || $('#produto').val() == '') {
				$('#produtoSelecionado').val(null);
			}
		});
		$('#produto').autocomplete({
			minChars : 4,
			noCache : true,
			paramName : 'itemVeiculo.produto.nomeCompleto',
			serviceUrl : '${contextPath}/restrito/sistema/itemVeiculo.src?method=selecionarProdutoAutoComplete',
			onSelect : function(suggestion) {
				//alert('You selected: ' + suggestion.value + ', ' + suggestion.data);
				$('#produtoSelecionado').val(suggestion.data);
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#produtoSelecionado').val(null)
				}
			}
		});		
		
		$('#veiculo').keyup(function() {
			if ($('#veiculo').val() == null || $('#veiculo').val() == '') {
				$('#veiculoSelecionado').val(null);
			}
		});
		$('#veiculo').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'itemVeiculo.veiculo.nomeCompleto',
			/* params : {
				'saidaGrao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/itemVeiculo.src?method=selecionarVeiculoAutoComplete',
			onSelect : function(suggestion) {
				$('#veiculoSelecionado').val(suggestion.data);
			},
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			}, */
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#veiculoSelecionado').val(null);
				}
			}
		});
		
		$('#requerente').autocomplete({
			minChars : 2,
			paramName : 'itemVeiculo.requerente.pessoaFisica.razaoSocial',
			serviceUrl : '${contextPath}/restrito/sistema/itemVeiculo.src?method=selecionarRequerenteAutoComplete',
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
			paramName : 'itemVeiculo.almoxarife.pessoaFisica.razaoSocial',
			serviceUrl : '${contextPath}/restrito/sistema/itemVeiculo.src?method=selecionarAlmoxarifeAutoComplete',
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
			paramName : 'itemVeiculo.safra.nome',
			serviceUrl : '${contextPath}/restrito/sistema/itemVeiculo.src?method=selecionarSafraAutoComplete',
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
			paramName : 'itemVeiculo.setor.nomeCompleto',
			params : {
				'itemVeiculo.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			},
			serviceUrl : '${contextPath}/restrito/sistema/itemVeiculo.src?method=selecionarSetorAutoComplete',
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
			paramName : 'itemVeiculo.cultura.nome',
			params : {
				'itemVeiculo.setor.id' : function() {
					return $('#setorSelecionado').val();
				},
				'itemVeiculo.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			},
			/* params : {
				'saidaGrao.itemVeiculo.id' : function() {
					return $('#itemVeiculoSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/itemVeiculo.src?method=selecionarCulturaAutoComplete',
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