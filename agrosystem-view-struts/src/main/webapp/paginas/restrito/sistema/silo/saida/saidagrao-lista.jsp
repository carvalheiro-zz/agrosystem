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
			Pesquisar Saidas do Silo
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Listagem das Saidas do Silo
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
						<html:form styleId="form_saidaGrao_consulta" action="restrito/sistema/saidaGrao" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label class="text-white">Contrato</label>
									<html:text styleClass="form-control input-sm" styleId="contrato" property="saidaGrao.contratoVenda.numero" name="saidaGraoForm" />
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label class="text-white">Data inicial</label>
									<html:text styleClass="form-control input-sm data" styleId="dataInicial" property="dataInicial" name="saidaGraoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label class="text-white">Data final</label>
									<html:text styleClass="form-control input-sm data" styleId="dataFinal" property="dataFinal" name="saidaGraoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-2">
									<label class="text-white">Local Armazenagem</label>
									<html:select styleClass="form-control input-sm" styleId="localArmazenagem01" property="saidaGrao.localArmazenagem01.id" name="saidaGraoForm">
										<html:option value="">Selecione...</html:option>
										<html:optionsCollection name="saidaGraoForm" property="comboSilos" label="label" value="value" />
									</html:select>
								</div>


								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-4">
									<label class="text-white">Cliente</label>
									<html:text styleClass="form-control input-sm" styleId="cliente" property="saidaGrao.cliente.pessoaJuridica.razaoSocial" name="saidaGraoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="clienteSelecionado" property="saidaGrao.cliente.id" name="saidaGraoForm" />
								</div>


								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<label class="text-white">Safra</label>
									<html:text styleClass="form-control input-sm" styleId="safra01" property="saidaGrao.safra01.nome" name="saidaGraoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="safra01Selecionada" property="saidaGrao.safra01.id" name="saidaGraoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label class="text-white">Cultura</label>
									<html:text styleClass="form-control input-sm" styleId="cultura" property="saidaGrao.cultura.nome" name="saidaGraoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="culturaSelecionada" property="saidaGrao.cultura.id" name="saidaGraoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label class="text-white">Placa</label>
									<html:text styleClass="form-control input-sm text-center placa" styleId="placa" property="saidaGrao.placa" name="saidaGraoForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label class="text-white">Motorista</label>
									<html:text styleClass="form-control input-sm" styleId="motorista" property="saidaGrao.motorista" name="saidaGraoForm" />
								</div>

							</div>
							<div class="row">

								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.saidaGrao.filtrar)">
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
								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.saidaGrao.inserir)">
									<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2" style="margin-bottom: 0px;">
										<button type="button" id="novo" class="btn btn-success btn-sm cor-sistema btn-block">
											<i class="fa fa-file-o"></i>
											Novo
										</button>
									</div>
								</logic:equal>
								<logic:notEmpty name="saidaGraoForm" property="saidas">
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
			<logic:empty name="saidaGraoForm" property="saidas">
				<div class="row" style="margin-top: 15px;">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="alert alert-warning">Nenhum registro encontrado!</div>
					</div>
				</div>
			</logic:empty>
			<logic:notEmpty name="saidaGraoForm" property="saidas">
				<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.saidaGrao.filtrar)">
					<div class="panel-body">
						<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
							<span class="label cor-sistema" style="font-size: 15px;">Total Peso Liquido: ${saidaGraoForm.totalPesoLiquido}</span>
							<span class="label cor-sistema" style="font-size: 15px;">Total Sacas: ${saidaGraoForm.totalSacas}</span>
							<span class="label cor-sistema" style="font-size: 15px;">Total Valor Liquido: R$ ${saidaGraoForm.totalValorLiquido}</span>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
							<div class="table-responsive">
								<table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
									<thead>
										<!-- CABEÇALHO DA TABELA -->
										<tr class="cor-sistema" style="color: white;">
											<th class="text-center">Contrato</th>
											<th class="text-center">Nota</th>
											<th class="text-center">Data</th>
											<th>Cultura</th>
											<th class="text-right">Peso Liquido</th>
											<th class="text-right">Sacas</th>
											<th class="text-right">Valor Liquido</th>
											<th>Cliente</th>
											<th>Placa</th>
											<th>Motorista</th>
											<th>Natureza de Operação</th>
											<th class="text-center" style="width: 80px;">Opções</th>
										</tr>
									</thead>
									<tbody>
										<!-- TABELA -->
										<logic:iterate id="saidaGraoCorrente" name="saidaGraoForm" property="saidas" type="br.com.srcsoftware.sistema.silo.saida.SaidaGraoDTO">

											<tr>
												<!-- success / info / warning / danger -->
												<td onclick="selecionar('${contextPath}/restrito/sistema/saidaGrao.src?method=selecionar&saidaGrao.id=${saidaGraoCorrente.id}')"">${saidaGraoCorrente.contratoVenda.numero}</td>
												<td onclick="selecionar('${contextPath}/restrito/sistema/saidaGrao.src?method=selecionar&saidaGrao.id=${saidaGraoCorrente.id}')"">${saidaGraoCorrente.numeroNotaFiscal}</td>
												<td onclick="selecionar('${contextPath}/restrito/sistema/saidaGrao.src?method=selecionar&saidaGrao.id=${saidaGraoCorrente.id}')">${saidaGraoCorrente.dataToString}</td>
												<td onclick="selecionar('${contextPath}/restrito/sistema/saidaGrao.src?method=selecionar&saidaGrao.id=${saidaGraoCorrente.id}')">${saidaGraoCorrente.cultura.nome}</td>
												<td class="text-right" onclick="selecionar('${contextPath}/restrito/sistema/saidaGrao.src?method=selecionar&saidaGrao.id=${saidaGraoCorrente.id}')">${saidaGraoCorrente.pesoLiquido}&nbsp;Kg</td>
												<td class="text-right" onclick="selecionar('${contextPath}/restrito/sistema/saidaGrao.src?method=selecionar&saidaGrao.id=${saidaGraoCorrente.id}')">${saidaGraoCorrente.emSacas}</td>
												<td class="text-right" onclick="selecionar('${contextPath}/restrito/sistema/saidaGrao.src?method=selecionar&saidaGrao.id=${saidaGraoCorrente.id}')">${saidaGraoCorrente.valorLiquido}</td>

												<td onclick="selecionar('${contextPath}/restrito/sistema/saidaGrao.src?method=selecionar&saidaGrao.id=${saidaGraoCorrente.id}')">${saidaGraoCorrente.cliente.nome}</td>
												<td onclick="selecionar('${contextPath}/restrito/sistema/saidaGrao.src?method=selecionar&saidaGrao.id=${saidaGraoCorrente.id}')">${saidaGraoCorrente.placa}</td>
												<td onclick="selecionar('${contextPath}/restrito/sistema/saidaGrao.src?method=selecionar&saidaGrao.id=${saidaGraoCorrente.id}')">${saidaGraoCorrente.motorista}</td>
												<td onclick="selecionar('${contextPath}/restrito/sistema/saidaGrao.src?method=selecionar&saidaGrao.id=${saidaGraoCorrente.id}')">${saidaGraoCorrente.naturezaOperacao.nome}</td>

												<td class="text-center" style="min-width: 80px; vertical-align: middle;">
													<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.saidaGrao.excluir)">
														<button type="button" id="excluir" class="btn btn-danger btn-xs fa fa-trash" style="font-size: 14px"
															onclick="excluir('Data: ${saidaGraoCorrente.dataToString}<br/>Produto: ${saidaGraoCorrente.cultura.nome}<br/>Safra: ${saidaGraoCorrente.safra01.nomeCompleto}', 'sistema/saidaGrao', ${saidaGraoCorrente.id} );"></button>
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
			<logic:notEmpty name="saidaGraoForm" property="saidas">
				<div class="panel-footer">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">

							<nav aria-label="Page navigation">
								<ul class="pagination" style="font-size: 12px;">
									<li>
										<a href="javascript://" style="cursor: default;">Encontrados: ${saidaGraoForm.paginacao.totalRegistros} </a>
									</li>
									<logic:equal name="saidaGraoForm" property="paginacao.voltarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="saidaGraoForm" property="paginacao.voltarPaginacaoInativo" value="false">
										<li class="page-item">
											<a class="page-link" href="${contextPath}/restrito/sistema/saidaGrao.src?method=voltar&forwardDestino=saidaGraoLista" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>

									<c:forEach var="i" begin="${saidaGraoForm.paginacao.paginaInicial}" end="${saidaGraoForm.paginacao.paginaFinal}" step="1" varStatus="row">
										<c:if test="${saidaGraoForm.paginacao.paginaCorrente == i}">
											<li class="page-item active">
										</c:if>

										<c:if test="${saidaGraoForm.paginacao.paginaCorrente != i}">
											<li class="page-item ">
										</c:if>

										<a class="page-link" href="${contextPath}/restrito/sistema/saidaGrao.src?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>
										</li>
									</c:forEach>

									<logic:equal name="saidaGraoForm" property="paginacao.avancarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Next">
												<span aria-hidden="true">&raquo;</span>
												<span class="sr-only">Próximo</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="saidaGraoForm" property="paginacao.avancarPaginacaoInativo" value="false">
										<li class="page-item ">
											<a class="page-link" href="${contextPath}/restrito/sistema/saidaGrao.src?method=avancar&forwardDestino=saidaGraoLista" aria-label="Next">
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
		//$("#safra01").addClass("obrigatorio");
		
		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#cliente").attr("maxlength", 100);
		$("#safra01").attr("maxlength", 100);
		$("#cultura").attr("maxlength", 50);
		$("#motorista").prop("maxlength", 100);
		$("#contrato").prop("maxlength", 50);

		/* Setando os placeholder dos campos*/
		$( "#cliente" ).prop("placeholder","Cliente a ser pesquisado");
		$( "#safra01" ).prop("placeholder","Sara a ser pesquisada");
		$( "#cultura" ).prop("placeholder","Cultura a ser pesquisada");
		$( "#motorista").prop("placeholder", "Motorista a ser pesquisado");
		$( "#contrato").prop("placeholder", "Contrato a ser pesquisado");
		
		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_saidaGrao_consulta").prop("autocomplete", "off");
		
		$('#form_saidaGrao_consulta').on('submit', function(e){
	        abrirModalProcessando();
	    });		
		
		$('#pesquisar').on('click', function(e){
			executar('form_saidaGrao_consulta','filtrar');
	    });		
		
		$('#limparPesquisa').on('click', function(e){
			abrirModalProcessando();
			executarComSubmit('form_saidaGrao_consulta','limparFiltro');
	    });
		
		$('#novo').on('click', function(e){
			abrirModalProcessando();
			executarComSubmit('form_saidaGrao_consulta','abrirCadastro');
	    });
		
		$('#gerarPDF').click(function() {
			gerarRelatorio('form_saidaGrao_consulta', 'gerarPDF');
		});
				
		$('#cliente').keyup(function() {
			if ($('#cliente').val() == null || $('#marca').val() == '') {
				$('#clienteSelecionado').val(null);
			}
		});
		$('#cliente').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'saidaGrao.cliente.pessoaJuridica.razaoSocial',
			/* params : {
				'saidaGrao.safra01.id' : function() {
					return $('#safra01Selecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/saidaGrao.src?method=selecionarClienteAutoComplete',
			onSelect : function(suggestion) {
				$('#clienteSelecionado').val(suggestion.data);
			},
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			}, */
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#clienteSelecionado').val(null);
				}
			}
		});
		
		$('#safra01').keyup(function() {
			if ($('#safra01').val() == null || $('#safra01').val() == '') {
				$('#safra01Selecionada').val(null);
			}
		});
		
		$('#safra01').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'saidaGrao.safra01.nome',
			serviceUrl : '${contextPath}/restrito/sistema/saidaGrao.src?method=selecionarSafraAutoComplete',
			onSelect : function(suggestion) {
				$('#safra01Selecionada').val(suggestion.data);
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#safra01Selecionada').val(null);
				}
			}
		});
				
		$('#cultura').keyup(function() {
			if ($('#cultura').val() == null || $('#cultura').val() == '') {
				$('#culturaSelecionada').val(null);
			}
		});

		$('#cultura').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'saidaGrao.cultura.nome',
			params : {
				'saidaGrao.safra01.id' : function() {
					return $('#safra01Selecionada').val();
				}
			},
			/* params : {
				'saidaGrao.saidaGrao.id' : function() {
					return $('#saidaGraoSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/saidaGrao.src?method=selecionarCulturaAutoComplete',
			onSelect : function(suggestion) {
				$('#culturaSelecionada').val(suggestion.data);
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
	});
	
	function selecionar(urlAction) {
		abrirModalProcessando();
		window.location = urlAction;
	}
</script>