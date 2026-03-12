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
			Pesquisar Aplicações de Insumos
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Listagem das Aplicações de Insumos
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
						<html:form styleId="form_aplicacao_consulta" action="restrito/sistema/aplicacao" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<div class="form-group col-xs-12 col-sm-4 col-md-3 col-lg-3">
									<label class="text-white">Data Inicial</label>
									<html:text styleClass="form-control input-sm data text-center" styleId="dataInicial" property="dataInicial" name="aplicacaoForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-4 col-md-3 col-lg-3">
									<label class="text-white">Data Final</label>
									<html:text styleClass="form-control input-sm data text-center" styleId="dataFinal" property="dataFinal" name="aplicacaoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label class="text-white">Aplicador</label>
									<html:text styleClass="form-control input-sm" styleId="aplicador" property="aplicacao.aplicador.pessoaFisica.razaoSocial" name="aplicacaoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label class="text-white">Almoxarife</label>
									<html:text styleClass="form-control input-sm" styleId="almoxarife" property="aplicacao.almoxarife.pessoaFisica.razaoSocial" name="aplicacaoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<label class="text-white">Produto</label>
									<html:text styleClass="form-control input-sm" styleId="produto" property="aplicacao.produto.nomeCompleto" name="aplicacaoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<%-- <html:hidden styleId="produtoSelecionado" property="aplicacao.produto.id" name="aplicacaoForm" /> --%>
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label class="text-white">Categoria</label>
									<html:text styleClass="form-control input-sm" styleId="tipo" property="aplicacao.produto.tipo.nome" name="aplicacaoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="tipoSelecionado" property="aplicacao.produto.tipo.id" name="aplicacaoForm" />
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label class="text-white">Prestador</label>
									<html:text styleClass="form-control input-sm" styleId="prestador" property="aplicacao.prestador.nome" name="aplicacaoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="prestadorSelecionado" property="aplicacao.prestador.id" name="aplicacaoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-4 col-md-3 col-lg-3">
									<label class="text-white">Atribuição de Custo</label>
									<html:select styleClass="form-control input-sm" styleId="atribuicaoCusto" property="aplicacao.tipo" name="aplicacaoForm">
										<html:option value="">Selecione</html:option>
										<html:option value="Safra/Setor">Safra/Setor</html:option>
										<html:option value="Tudo">Tudo</html:option> 
										<html:option value="Cultura">Cultura</html:option>
									</html:select>
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
									<label class="text-white">Safra</label>
									<html:text styleClass="form-control input-sm" styleId="safra" property="aplicacao.safra.nome" name="aplicacaoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="safraSelecionada" property="aplicacao.safra.id" name="aplicacaoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
									<label class="text-white">Setor</label>
									<html:text styleClass="form-control input-sm" styleId="setor" property="aplicacao.setor.nomeCompleto" name="aplicacaoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="setorSelecionado" property="aplicacao.setor.id" name="aplicacaoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label class="text-white">Cultura</label>
									<html:text styleClass="form-control input-sm" styleId="cultura" property="aplicacao.cultura.nome" name="aplicacaoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="culturaSelecionada" property="aplicacao.cultura.id" name="aplicacaoForm" />
								</div>

								

							</div>
							<div class="row">

								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.aplicacao.filtrar)">
									<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3" style="margin-bottom: 0px;">
										<button type="submit" id="pesquisar" class="btn btn-success btn-sm cor-sistema btn-block">
											<i class="fa fa-search"></i>
											Pesquisar
										</button>
									</div>
								</logic:equal>
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3" style="margin-bottom: 0px;">
									<button type="button" id="limparPesquisa" class="btn btn-success btn-sm cor-sistema btn-block">
										<i class="glyphicon glyphicon-erase"></i>
										Limpar
									</button>
								</div>
								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.aplicacao.inserir)">
									<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3" style="margin-bottom: 0px;">
										<button type="button" id="novo" class="btn btn-success btn-sm cor-sistema btn-block">
											<i class="fa fa-file-o"></i>
											Novo
										</button>
									</div>
								</logic:equal>
								<logic:notEmpty name="aplicacaoForm" property="aplicacoes">
									<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3" style="margin-bottom: 0px;">
										<button type="button" id="gerarPDF" class="btn btn-primary btn-sm btn-block">
											<i class="fa fa-file-pdf-o"></i>
											Gerar PDF
										</button>
									</div>								
								</logic:notEmpty>
							</div>
						</html:form>
					</div>
				</div>
			</div>
			<!-- TERMINO CAMPOS DE PESQUISA -->

			<!-- INICIO TABELA -->
			<logic:empty name="aplicacaoForm" property="aplicacoes">
				<div class="row" style="margin-top: 15px;">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="alert alert-warning">Nenhum registro encontrado!</div>
					</div>
				</div>
			</logic:empty>
			<logic:notEmpty name="aplicacaoForm" property="aplicacoes">
				<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.aplicacao.filtrar)">
					<div class="panel-body">
						<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
							<span class="label cor-sistema" style="font-size: 15px;">Total quantidade: ${aplicacaoForm.totalQuantidade}</span>
							<span class="label cor-sistema" style="font-size: 15px;">Total Custo: R$ ${aplicacaoForm.totalValor}</span>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
							<div class="table-responsive">
								<table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
									<thead>
										<!-- CABEÇALHO DA TABELA -->
										<tr class="cor-sistema" style="color: white;">
											<th class="text-center">Data</th>
											<th>Produto</th>
											<th class="text-right">Quantidade</th>
											<th class="text-right">Custo Total</th>
											<th>Atribuição de Custo</th>
											<th>Aplicador</th>
											<th>Almoxarife</th>
											<th class="text-center" style="width: 80px;">Opções</th>
										</tr>
									</thead>
									<tbody>
										<!-- TABELA -->
										<logic:iterate id="aplicacaoCorrente" name="aplicacaoForm" property="aplicacoes" type="br.com.srcsoftware.sistema.aplicacao.aplicacao.AplicacaoDTO">

											<tr>
												<!-- success / info / warning / danger -->
												<td onclick="selecionar('${contextPath}/restrito/sistema/aplicacao.src?method=selecionar&aplicacao.id=${aplicacaoCorrente.id}')">${aplicacaoCorrente.dataToString}</td>
												<td onclick="selecionar('${contextPath}/restrito/sistema/aplicacao.src?method=selecionar&aplicacao.id=${aplicacaoCorrente.id}')">${aplicacaoCorrente.produto.nomeCompleto}</td>
												<td class="text-right" onclick="selecionar('${contextPath}/restrito/sistema/aplicacao.src?method=selecionar&aplicacao.id=${aplicacaoCorrente.id}')">${aplicacaoCorrente.quantidade}&nbsp;${aplicacaoCorrente.produto.unidadeMedida.sigla}</td>
												<td class="text-right" onclick="selecionar('${contextPath}/restrito/sistema/aplicacao.src?method=selecionar&aplicacao.id=${aplicacaoCorrente.id}')">${aplicacaoCorrente.custoTotal}</td>

												<td style="background-color: #e6e6e6;" onclick="selecionar('${contextPath}/restrito/sistema/aplicacao.src?method=selecionar&aplicacao.id=${aplicacaoCorrente.id}')">
													<strong>${aplicacaoCorrente.tipo}:</strong>
													${aplicacaoCorrente.nomeCompleto}
												</td>

												<td onclick="selecionar('${contextPath}/restrito/sistema/aplicacao.src?method=selecionar&aplicacao.id=${aplicacaoCorrente.id}')">${aplicacaoCorrente.aplicador.pessoaFisica.razaoSocial}</td>
												<td onclick="selecionar('${contextPath}/restrito/sistema/aplicacao.src?method=selecionar&aplicacao.id=${aplicacaoCorrente.id}')">${aplicacaoCorrente.almoxarife.pessoaFisica.razaoSocial}</td>

												<td class="text-center" style="min-width: 80px; vertical-align: middle;">
													<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.aplicacao.excluir)">
														<button type="button" id="excluir" class="btn btn-danger btn-xs fa fa-trash" style="font-size: 14px"
															onclick="excluir('Data: ${aplicacaoCorrente.dataToString}<br/>Produto: ${aplicacaoCorrente.produto.nomeCompleto}<br/>Safra: ${aplicacaoCorrente.safra.nomeCompleto}<br/>Setor: ${aplicacaoCorrente.setor.nomeCompleto}', 'sistema/aplicacao', ${aplicacaoCorrente.id} );"></button>
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
			<logic:notEmpty name="aplicacaoForm" property="aplicacoes">
				<div class="panel-footer">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">

							<nav aria-label="Page navigation">
								<ul class="pagination" style="font-size: 12px;">
									<li>
										<a href="javascript://" style="cursor: default;">Encontrados: ${aplicacaoForm.paginacao.totalRegistros} </a>
									</li>
									<logic:equal name="aplicacaoForm" property="paginacao.voltarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="aplicacaoForm" property="paginacao.voltarPaginacaoInativo" value="false">
										<li class="page-item">
											<a class="page-link" href="${contextPath}/restrito/sistema/aplicacao.src?method=voltar&forwardDestino=aplicacaoLista" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>

									<c:forEach var="i" begin="${aplicacaoForm.paginacao.paginaInicial}" end="${aplicacaoForm.paginacao.paginaFinal}" step="1" varStatus="row">
										<c:if test="${aplicacaoForm.paginacao.paginaCorrente == i}">
											<li class="page-item active">
										</c:if>

										<c:if test="${aplicacaoForm.paginacao.paginaCorrente != i}">
											<li class="page-item ">
										</c:if>

										<a class="page-link" href="${contextPath}/restrito/sistema/aplicacao.src?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>
										</li>
									</c:forEach>

									<logic:equal name="aplicacaoForm" property="paginacao.avancarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Next">
												<span aria-hidden="true">&raquo;</span>
												<span class="sr-only">Próximo</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="aplicacaoForm" property="paginacao.avancarPaginacaoInativo" value="false">
										<li class="page-item ">
											<a class="page-link" href="${contextPath}/restrito/sistema/aplicacao.src?method=avancar&forwardDestino=aplicacaoLista" aria-label="Next">
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
		$("#aplicador").prop("maxlength", 100);
		$("#almoxarife").prop("maxlength", 100);
		$("#codigoVeiculo").prop("maxlength", 10);		
		$("#produto").prop("maxlength", 50);
		$("#tipo").prop("maxlength", 50);
		$("#safra").attr("maxlength", 100);
		$("#setor").attr("maxlength", 20);
		$("#cultura").attr("maxlength", 50);
		$("#prestador").attr("maxlength", 50);
		$( "#prestador" ).attr("placeholder","Prestador");

		/* Setando os placeholder dos campos*/
		$( "#aplicador" ).prop("placeholder","Aplicador pesquisar");
		$( "#almoxarife" ).prop("placeholder","Almoxarife pesquisar");
		$( "#codigoVeiculo" ).prop("placeholder","Cod. Veiculo");
		$( "#produto" ).prop("placeholder","Produto a ser pesquisado");
		$( "#tipo" ).prop("placeholder","Categoria a ser pesquisada");
		$( "#safra" ).attr("placeholder","Safra pesquisar");
		$( "#setor" ).attr("placeholder","Setor pesquisar");
		$( "#cultura" ).attr("placeholder","Cultura pesquisar");

		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_aplicacao_consulta").prop("autocomplete", "off");
		
		$('#form_aplicacao_consulta').on('submit', function(e){
	        abrirModalProcessando();
	    });
		
		$( '#pesquisar' ).click(function() {  	
			executar('form_aplicacao_consulta','filtrar');
		});
		
		$( '#limparPesquisa' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_aplicacao_consulta','limparFiltro');
		});
		
		$( '#novo' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_aplicacao_consulta','abrirCadastro');
		});
		
		$('#gerarPDF').click(function() {
			gerarRelatorio('form_aplicacao_consulta', 'gerarPDF');
		});
			
		$('#produto').keyup(function() {
			if ($('#produto').val() == null || $('#produto').val() == '') {
				$('#produtoSelecionado').val(null);
			}
		});
		$('#produto').autocomplete({
			minChars : 4,
			noCache : true,
			paramName : 'aplicacao.produto.nomeCompleto',
			serviceUrl : '${contextPath}/restrito/sistema/aplicacao.src?method=selecionarProdutoAutoComplete',
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
		$('#tipo').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'aplicacao.produto.tipo.nome',
			serviceUrl : '${contextPath}/restrito/sistema/aplicacao.src?method=selecionarTipoAutoComplete',
			onSelect : function(suggestion) {
				//$('#tipoSelecionado').val(suggestion.data);
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					//$('#tipoSelecionado').val(null);
				}
			}
		});
		$('#aplicador').autocomplete({
			minChars : 2,
			paramName : 'aplicacao.aplicador.pessoaFisica.razaoSocial',
			serviceUrl : '${contextPath}/restrito/sistema/aplicacao.src?method=selecionarAplicadorAutoComplete',
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
			paramName : 'aplicacao.almoxarife.pessoaFisica.razaoSocial',
			serviceUrl : '${contextPath}/restrito/sistema/aplicacao.src?method=selecionarAlmoxarifeAutoComplete',
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
			paramName : 'aplicacao.safra.nome',
			serviceUrl : '${contextPath}/restrito/sistema/aplicacao.src?method=selecionarSafraAutoComplete',
			onSelect : function(suggestion) {
				$('#safraSelecionada').val(suggestion.data);

				if ($('#atribuicaoCusto').val() == '') {
					$("#safra").focus();
				} else if ($('#atribuicaoCusto').val() == 'Safra/Setor') {
					$("#setor").focus();
				} else if ($('#atribuicaoCusto').val() == 'Tudo') {
					$("#imovel").focus();
				} else if ($('#atribuicaoCusto').val() == 'Cultura') {
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
			paramName : 'aplicacao.setor.nomeCompleto',
			params : {
				'aplicacao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			},
			serviceUrl : '${contextPath}/restrito/sistema/aplicacao.src?method=selecionarSetorAutoComplete',
			onSelect : function(suggestion) {
				$('#setorSelecionado').val(suggestion.data);

				if ($('#atribuicaoCusto').val() == '') {
					$("#safra").focus();
				} else if ($('#atribuicaoCusto').val() == 'Safra/Setor') {
					$("#imovel").focus();
				} else if ($('#atribuicaoCusto').val() == 'Tudo') {
					$("#safra").focus();
				} else if ($('#atribuicaoCusto').val() == 'Cultura') {
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
			paramName : 'aplicacao.cultura.nome',
			params : {
				'aplicacao.setor.id' : function() {
					return $('#setorSelecionado').val();
				},
				'aplicacao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			},
			/* params : {
				'saidaGrao.aplicacao.id' : function() {
					return $('#aplicacaoSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/aplicacao.src?method=selecionarCulturaAutoComplete',
			onSelect : function(suggestion) {
				$('#culturaSelecionada').val(suggestion.data);

				$("#imovel").focus();
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

		$('#atribuicaoCusto').change(function() {
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
		if ($('#atribuicaoCusto').val() == '') {
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

		} else if ($('#atribuicaoCusto').val() == 'Safra/Setor') {
			//$("#safra").addClass("obrigatorio");
			//$("#setor").addClass("obrigatorio");

			$("#safra").removeClass("bloqueado");
			$("#setor").removeClass("bloqueado");

			//$("#cultura").removeClass("obrigatorio");
			//$("#cultura").addClass("bloqueado");

			//$("#safra").focus();
		} else if ($('#atribuicaoCusto').val() == 'Tudo') {
			//$("#safra").addClass("obrigatorio");

			$("#safra").removeClass("bloqueado");

			//$("#setor").removeClass("obrigatorio");
			$("#setor").addClass("bloqueado");

			//$("#cultura").removeClass("obrigatorio");
			$("#cultura").addClass("bloqueado");

			//$("#safra").focus();
		} else if ($('#atribuicaoCusto').val() == 'Cultura') {
			//$("#safra").addClass("obrigatorio");
			//$("#cultura").addClass("obrigatorio");

			$("#safra").removeClass("bloqueado");
			$("#cultura").removeClass("bloqueado");

			//$("#setor").removeClass("obrigatorio");
			$("#setor").addClass("bloqueado");

			//$("#safra").focus();
		}

		recarregarObrigatorios();
	}
	
	function selecionar(urlAction) {
		abrirModalProcessando();
		window.location = urlAction;
	}
</script>