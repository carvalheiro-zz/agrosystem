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
			Pesquisar Entradas no Silo
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Listagem das Entradas no Silo
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
						<html:form styleId="form_entradaGrao_consulta" action="restrito/sistema/entradaGrao" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label class="text-white">Data inicial</label>
									<html:text styleClass="form-control input-sm data" styleId="dataInicial" property="dataInicial" name="entradaGraoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label class="text-white">Data final</label>
									<html:text styleClass="form-control input-sm data" styleId="dataFinal" property="dataFinal" name="entradaGraoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<label class="text-white">Local Armazenagem</label>
									<html:select styleClass="form-control input-sm" styleId="localArmazenagem" property="entradaGrao.localArmazenagem.id" name="entradaGraoForm">
										<html:option value="">Selecione...</html:option>
										<html:optionsCollection name="entradaGraoForm" property="comboSilos" label="label" value="value" />
									</html:select>
								</div>


								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-4">
									<label class="text-white">Estoquista</label>
									<html:text styleClass="form-control input-sm" styleId="estoquista" property="entradaGrao.estoquista.pessoaFisica.razaoSocial" name="entradaGraoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="estoquistaSelecionado" property="entradaGrao.estoquista.id" name="entradaGraoForm" />
								</div>


								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<label class="text-white">Safra</label>
									<html:text styleClass="form-control input-sm" styleId="safra" property="entradaGrao.safra.nome" name="entradaGraoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="safraSelecionada" property="entradaGrao.safra.id" name="entradaGraoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<label class="text-white">Setor</label>
									<html:text styleClass="form-control input-sm" styleId="setor" property="entradaGrao.setor.nome" name="entradaGraoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="setorSelecionado" property="entradaGrao.setor.id" name="entradaGraoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<label class="text-white">Variedade</label>
									<html:text styleClass="form-control input-sm" styleId="variedade" property="entradaGrao.variedade.nome" name="entradaGraoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="variedadeSelecionada" property="entradaGrao.variedade.id" name="entradaGraoForm" />
								</div>

							</div>
							<div class="row">

								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.entradaGrao.filtrar)">
									<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2" style="margin-bottom: 0px;">
										<button type="submit" id="pesquisar" class="btn btn-success btn-sm cor-sistema btn-block">
											<i class="fa fa-search"></i>
											Pesquisar
										</button>
									</div>
								</logic:equal>
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2" style="margin-bottom: 0px;">
									<button type="button" id="limparPesquisa" class="btn btn-success btn-sm cor-sistema btn-block">
										<i class="glyphicon glyphicon-erase"></i>
										Limpar
									</button>
								</div>
								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.entradaGrao.inserir)">
									<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2" style="margin-bottom: 0px;">
										<button type="button" id="novo" class="btn btn-success btn-sm cor-sistema btn-block">
											<i class="fa fa-file-o"></i>
											Novo
										</button>
									</div>
								</logic:equal>
								<logic:notEmpty name="entradaGraoForm" property="entradas">
									<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2" style="margin-bottom: 0px;">
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
			<logic:empty name="entradaGraoForm" property="entradas">
				<div class="row" style="margin-top: 15px;">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="alert alert-warning">Nenhum registro encontrado!</div>
					</div>
				</div>
			</logic:empty>
			<logic:notEmpty name="entradaGraoForm" property="entradas">
				<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.entradaGrao.filtrar)">
					<div class="panel-body">						
						<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
							<span class="label cor-sistema" style="font-size: 15px;">Total Peso Bruto: ${entradaGraoForm.totalPesoBruto}</span>
							<span class="label cor-sistema" style="font-size: 15px;">Total Peso Liquido: ${entradaGraoForm.totalPesoLiquido}</span>
							<span class="label cor-sistema" style="font-size: 15px;">Total Sacas: ${entradaGraoForm.totalSacas}</span>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
							<div class="table-responsive">
								<table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
									<thead>
										<!-- CABEÇALHO DA TABELA -->
										<tr class="cor-sistema" style="color: white;">
											<th class="text-center">Data</th>
											<th>Armazenagem</th>
											<th>Variedade</th>
											<th>Safra</th>
											<th>Setor</th>
											<th class="text-right">Peso Bruto</th>
											<th class="text-right">Peso Liquido</th>
											<th class="text-right">Sacas</th>
											<th>Estoquista</th>
											<th class="text-center" style="width: 80px;">Opções</th>
										</tr>
									</thead>
									<tbody>
										<!-- TABELA -->
										<logic:iterate id="entradaGraoCorrente" name="entradaGraoForm" property="entradas" type="br.com.srcsoftware.sistema.silo.entrada.EntradaGraoDTO">

											<tr>
												<!-- success / info / warning / danger -->
												<td onclick="selecionar('${contextPath}/restrito/sistema/entradaGrao.src?method=selecionar&entradaGrao.id=${entradaGraoCorrente.id}')">${entradaGraoCorrente.dataToString}</td>
												<td onclick="selecionar('${contextPath}/restrito/sistema/entradaGrao.src?method=selecionar&entradaGrao.id=${entradaGraoCorrente.id}')">${entradaGraoCorrente.localArmazenagem.nome}</td>

												<td onclick="selecionar('${contextPath}/restrito/sistema/entradaGrao.src?method=selecionar&entradaGrao.id=${entradaGraoCorrente.id}')">${entradaGraoCorrente.variedade.nomeCompleto}</td>
												<td onclick="selecionar('${contextPath}/restrito/sistema/entradaGrao.src?method=selecionar&entradaGrao.id=${entradaGraoCorrente.id}')">${entradaGraoCorrente.safra.nome}</td>
												<td onclick="selecionar('${contextPath}/restrito/sistema/entradaGrao.src?method=selecionar&entradaGrao.id=${entradaGraoCorrente.id}')">${entradaGraoCorrente.setor.nomeCompleto}</td>
												<td class="text-right" onclick="selecionar('${contextPath}/restrito/sistema/entradaGrao.src?method=selecionar&entradaGrao.id=${entradaGraoCorrente.id}')">${entradaGraoCorrente.pesoBruto}&nbsp;Kg</td>
												<td class="text-right" onclick="selecionar('${contextPath}/restrito/sistema/entradaGrao.src?method=selecionar&entradaGrao.id=${entradaGraoCorrente.id}')">${entradaGraoCorrente.pesoLiquido}&nbsp;Kg</td>
												<td class="text-right" onclick="selecionar('${contextPath}/restrito/sistema/entradaGrao.src?method=selecionar&entradaGrao.id=${entradaGraoCorrente.id}')">${entradaGraoCorrente.emSacas}</td>

												<td onclick="selecionar('${contextPath}/restrito/sistema/entradaGrao.src?method=selecionar&entradaGrao.id=${entradaGraoCorrente.id}')">${entradaGraoCorrente.estoquista.pessoaFisica.razaoSocial}</td>

												<td class="text-center" style="min-width: 80px; vertical-align: middle;">
													<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.entradaGrao.excluir)">
														<button type="button" id="excluir" class="btn btn-danger btn-xs fa fa-trash" style="font-size: 14px"
															onclick="excluir('Data: ${entradaGraoCorrente.dataToString}<br/>Produto: ${entradaGraoCorrente.variedade.nomeCompleto}<br/>Safra: ${entradaGraoCorrente.safra.nomeCompleto}<br/>Setor: ${entradaGraoCorrente.setor.nomeCompleto}', 'sistema/entradaGrao', ${entradaGraoCorrente.id} );"></button>
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
			<logic:notEmpty name="entradaGraoForm" property="entradas">
				<div class="panel-footer">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">

							<nav aria-label="Page navigation">
								<ul class="pagination" style="font-size: 12px;">
									<li>
										<a href="javascript://" style="cursor: default;">Encontrados: ${entradaGraoForm.paginacao.totalRegistros} </a>
									</li>
									<logic:equal name="entradaGraoForm" property="paginacao.voltarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="entradaGraoForm" property="paginacao.voltarPaginacaoInativo" value="false">
										<li class="page-item">
											<a class="page-link" href="${contextPath}/restrito/sistema/entradaGrao.src?method=voltar&forwardDestino=entradaGraoLista" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>

									<c:forEach var="i" begin="${entradaGraoForm.paginacao.paginaInicial}" end="${entradaGraoForm.paginacao.paginaFinal}" step="1" varStatus="row">
										<c:if test="${entradaGraoForm.paginacao.paginaCorrente == i}">
											<li class="page-item active">
										</c:if>

										<c:if test="${entradaGraoForm.paginacao.paginaCorrente != i}">
											<li class="page-item ">
										</c:if>

										<a class="page-link" href="${contextPath}/restrito/sistema/entradaGrao.src?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>
										</li>
									</c:forEach>

									<logic:equal name="entradaGraoForm" property="paginacao.avancarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Next">
												<span aria-hidden="true">&raquo;</span>
												<span class="sr-only">Próximo</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="entradaGraoForm" property="paginacao.avancarPaginacaoInativo" value="false">
										<li class="page-item ">
											<a class="page-link" href="${contextPath}/restrito/sistema/entradaGrao.src?method=avancar&forwardDestino=entradaGraoLista" aria-label="Next">
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
		
		/* Setando NOTNULL nos campos*/
		//$("#safra").addClass("obrigatorio");
		
		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#estoquista").attr("maxlength", 100);
		$("#safra").attr("maxlength", 100);
		$("#setor").attr("maxlength", 20);
		$("#variedade").attr("maxlength", 50);

		/* Setando os placeholder dos campos*/
		$( "#estoquista" ).prop("placeholder","Estoquista a ser pesquisado");
		$( "#safra" ).prop("placeholder","Sara a ser pesquisada");
		$( "#setor" ).prop("placeholder","Setor a ser pesquisada");
		$( "#variedade" ).prop("placeholder","Variedade a ser pesquisada");
		
		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_entradaGrao_consulta").prop("autocomplete", "off");
		
		$('#form_entradaGrao_consulta').on('submit', function(e){
	        abrirModalProcessando();
	    });		
		
		$('#pesquisar').on('click', function(e){
			executar('form_entradaGrao_consulta','filtrar');
	    });		
		
		$('#limparPesquisa').on('click', function(e){
			abrirModalProcessando();
			executarComSubmit('form_entradaGrao_consulta','limparFiltro');
	    });
		
		$('#novo').on('click', function(e){
			abrirModalProcessando();
			executarComSubmit('form_entradaGrao_consulta','abrirCadastro');
	    });
		
		$('#gerarPDF').click(function() {
			gerarRelatorio('form_entradaGrao_consulta', 'gerarPDF');
		});
				
		$('#estoquista').keyup(function() {
			if ($('#estoquista').val() == null || $('#marca').val() == '') {
				$('#estoquistaSelecionado').val(null);
			}
		});
		$('#estoquista').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'entradaGrao.estoquista.pessoaFisica.razaoSocial',
			/* params : {
				'saidaGrao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/entradaGrao.src?method=selecionarEstoquistaAutoComplete',
			onSelect : function(suggestion) {
				$('#estoquistaSelecionado').val(suggestion.data);
			},
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			}, */
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#estoquistaSelecionado').val(null);
				}
			}
		});
		
		$('#safra').keyup(function() {
			if ($('#safra').val() == null || $('#safra').val() == '') {
				$('#safraSelecionada').val(null);
			}
		});
		
		$('#safra').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'entradaGrao.safra.nome',
			serviceUrl : '${contextPath}/restrito/sistema/entradaGrao.src?method=selecionarSafraAutoComplete',
			onSelect : function(suggestion) {
				$('#safraSelecionada').val(suggestion.data);
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#safraSelecionada').val(null);
				}
			}
		});
		
		$('#setor').keyup(function() {
			if ($('#setor').val() == null || $('#setor').val() == '') {
				$('#setorSelecionado').val(null);
			}
		});
		
		$('#setor').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'entradaGrao.setor.nomeCompleto',
			params : {
				'entradaGrao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			},
			serviceUrl : '${contextPath}/restrito/sistema/entradaGrao.src?method=selecionarSetorAutoComplete',
			onSelect : function(suggestion) {
				$('#setorSelecionado').val(suggestion.data);
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#setorSelecionado').val(null);
				}
			}
		});
		
		$('#variedade').keyup(function() {
			if ($('#variedade').val() == null || $('#variedade').val() == '') {
				$('#variedadeSelecionada').val(null);
			}
		});

		$('#variedade').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'entradaGrao.variedade.nome',
			params : {
				'entradaGrao.setor.id' : function() {
					return $('#setorSelecionado').val();
				}
			},
			/* params : {
				'saidaGrao.entradaGrao.id' : function() {
					return $('#entradaGraoSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/entradaGrao.src?method=selecionarVariedadeAutoComplete',
			onSelect : function(suggestion) {
				$('#variedadeSelecionada').val(suggestion.data);
			},
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			}, */
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#variedadeSelecionada').val(null);
				}
			}
		});
	});
	
	function selecionar(urlAction) {
		abrirModalProcessando();
		window.location = urlAction;
	}
</script>