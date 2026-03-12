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
			Pesquisar Contratos de Venda
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Listagem dos Contratos de Venda
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
						<html:form styleId="form_contratoVenda_consulta" action="restrito/sistema/contratoVenda" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label class="text-white">Data inicial</label>
									<html:text styleClass="form-control input-sm data" styleId="dataInicial" property="dataInicial" name="contratoVendaForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label class="text-white">Data final</label>
									<html:text styleClass="form-control input-sm data" styleId="dataFinal" property="dataFinal" name="contratoVendaForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-4">
									<label class="text-white">Cliente</label>
									<html:text styleClass="form-control input-sm" styleId="cliente" property="contratoVenda.cliente.nome" name="contratoVendaForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="clienteSelecionado" property="contratoVenda.cliente.id" name="contratoVendaForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-4">
									<label class="text-white">Vendedor</label>
									<html:text styleClass="form-control input-sm" styleId="vendedor" property="contratoVenda.vendedor.pessoaFisica.razaoSocial" name="contratoVendaForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="vendedorSelecionado" property="contratoVenda.vendedor.id" name="contratoVendaForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<label class="text-white">Cultura</label>
									<html:text styleClass="form-control input-sm" styleId="cultura" property="contratoVenda.cultura.nome" name="contratoVendaForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="culturaSelecionada" property="contratoVenda.cultura.id" name="contratoVendaForm" />
								</div>

							</div>
							<div class="row">

								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.contratoVenda.filtrar)">
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
								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.contratoVenda.inserir)">
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
			<logic:empty name="contratoVendaForm" property="contratos">
				<div class="row" style="margin-top: 15px;">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="alert alert-warning">Nenhum registro encontrado!</div>
					</div>
				</div>
			</logic:empty>
			<logic:notEmpty name="contratoVendaForm" property="contratos">
				<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.contratoVenda.filtrar)">
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
								<thead>
									<!-- CABEÇALHO DA TABELA -->
									<tr class="cor-sistema" style="color: white;">
										<th class="text-center">Número</th>
										<th class="text-center">Data</th>
										<th>Cultura</th>
										<th class="text-right">Valor Unitário</th>
										<th class="text-right">Quilos</th>
										<th class="text-right">Sacas</th>
										<th class="text-right">Valor Total</th>
										<th class="text-right">% Funrual</th>
										<th class="text-right">Total Líquido</th>
										<th>Cliente</th>
										<th class="text-right">Restante</th>
										<th class="text-right">Entregue</th>
										<th class="text-center" style="width: 80px;">Opções</th>
									</tr>
								</thead>
								<tbody>
									<!-- TABELA -->
									<logic:iterate id="contratoVendaCorrente" name="contratoVendaForm" property="contratos" type="br.com.srcsoftware.sistema.silo.contratovenda.ContratoVendaDTO">

										<logic:equal value="0,00" name="contratoVendaCorrente" property="quantidadeRestante">
											<tr style="background-color: #8ab3fd;">
										</logic:equal>
										<logic:notEqual value="0,00" name="contratoVendaCorrente" property="quantidadeRestante">
											<tr>
										</logic:notEqual>
										<!-- success / info / warning / danger -->
										<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/contratoVenda.src?method=selecionar&contratoVenda.id=${contratoVendaCorrente.id}')">${contratoVendaCorrente.numero}</td>
										<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/contratoVenda.src?method=selecionar&contratoVenda.id=${contratoVendaCorrente.id}')">${contratoVendaCorrente.dataToString}</td>
										<td onclick="selecionar('${contextPath}/restrito/sistema/contratoVenda.src?method=selecionar&contratoVenda.id=${contratoVendaCorrente.id}')">${contratoVendaCorrente.cultura.nome}</td>
										<td class="text-right" onclick="selecionar('${contextPath}/restrito/sistema/contratoVenda.src?method=selecionar&contratoVenda.id=${contratoVendaCorrente.id}')">R$&nbsp;${contratoVendaCorrente.valorUnitario}</td>
										<td class="text-right" onclick="selecionar('${contextPath}/restrito/sistema/contratoVenda.src?method=selecionar&contratoVenda.id=${contratoVendaCorrente.id}')">${contratoVendaCorrente.quantidade}&nbsp;Kg</td>
										<td class="text-right" onclick="selecionar('${contextPath}/restrito/sistema/contratoVenda.src?method=selecionar&contratoVenda.id=${contratoVendaCorrente.id}')">${contratoVendaCorrente.emSacas}</td>
										<td class="text-right" onclick="selecionar('${contextPath}/restrito/sistema/contratoVenda.src?method=selecionar&contratoVenda.id=${contratoVendaCorrente.id}')">R$&nbsp;${contratoVendaCorrente.valorTotal}</td>
										
										<td class="text-right" onclick="selecionar('${contextPath}/restrito/sistema/contratoVenda.src?method=selecionar&contratoVenda.id=${contratoVendaCorrente.id}')">${contratoVendaCorrente.funrural}</td>
										<td class="text-right" onclick="selecionar('${contextPath}/restrito/sistema/contratoVenda.src?method=selecionar&contratoVenda.id=${contratoVendaCorrente.id}')">R$&nbsp;${contratoVendaCorrente.valorTotalLiquido}</td>
										
										<td onclick="selecionar('${contextPath}/restrito/sistema/contratoVenda.src?method=selecionar&contratoVenda.id=${contratoVendaCorrente.id}')">${contratoVendaCorrente.cliente.pessoaJuridica.razaoSocial}</td>
										<td class="text-right" style="background-color: #ffbc007a;" onclick="selecionar('${contextPath}/restrito/sistema/contratoVenda.src?method=selecionar&contratoVenda.id=${contratoVendaCorrente.id}')">${contratoVendaCorrente.quantidadeRestante}&nbsp;Kg</td>
										<td class="text-right" style="background-color: #0089ff61;" onclick="selecionar('${contextPath}/restrito/sistema/contratoVenda.src?method=selecionar&contratoVenda.id=${contratoVendaCorrente.id}')">${contratoVendaCorrente.quantidadeEntregue}&nbsp;Kg</td>

										<td class="text-center" style="min-width: 80px; vertical-align: middle;">
											<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.contratoVenda.excluir)">
												<button type="button" id="excluir" class="btn btn-danger btn-xs fa fa-trash" style="font-size: 14px"
													onclick="excluir('Data: ${contratoVendaCorrente.dataToString}<br/>Produto: ${contratoVendaCorrente.cultura.nome}<br/>Safra: ${contratoVendaCorrente.cliente.nome}', 'sistema/contratoVenda', ${contratoVendaCorrente.id} );"></button>
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
			<logic:notEmpty name="contratoVendaForm" property="contratos">
				<div class="panel-footer">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">

							<nav aria-label="Page navigation">
								<ul class="pagination" style="font-size: 12px;">
									<li>
										<a href="javascript://" style="cursor: default;">Encontrados: ${contratoVendaForm.paginacao.totalRegistros} </a>
									</li>
									<logic:equal name="contratoVendaForm" property="paginacao.voltarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="contratoVendaForm" property="paginacao.voltarPaginacaoInativo" value="false">
										<li class="page-item">
											<a class="page-link" href="${contextPath}/restrito/sistema/contratoVenda.src?method=voltar&forwardDestino=contratoVendaLista" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>

									<c:forEach var="i" begin="${contratoVendaForm.paginacao.paginaInicial}" end="${contratoVendaForm.paginacao.paginaFinal}" step="1" varStatus="row">
										<c:if test="${contratoVendaForm.paginacao.paginaCorrente == i}">
											<li class="page-item active">
										</c:if>

										<c:if test="${contratoVendaForm.paginacao.paginaCorrente != i}">
											<li class="page-item ">
										</c:if>

										<a class="page-link" href="${contextPath}/restrito/sistema/contratoVenda.src?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>
										</li>
									</c:forEach>

									<logic:equal name="contratoVendaForm" property="paginacao.avancarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Next">
												<span aria-hidden="true">&raquo;</span>
												<span class="sr-only">Próximo</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="contratoVendaForm" property="paginacao.avancarPaginacaoInativo" value="false">
										<li class="page-item ">
											<a class="page-link" href="${contextPath}/restrito/sistema/contratoVenda.src?method=avancar&forwardDestino=contratoVendaLista" aria-label="Next">
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
		$("#numero").attr("maxlength", 50);
		$("#cliente").attr("maxlength", 100);
		$("#vendedor").attr("maxlength", 100);
		$("#cultura").attr("maxlength", 50);
		$("#observacao").attr("maxlength", 1000);

		/* Setando os placeholder dos campos*/
		$( "#numero" ).prop("placeholder","Número a ser pesquisada");
		$( "#cliente" ).prop("placeholder","Cliente a ser pesquisado");
		$( "#vendedor" ).prop("placeholder","Vendedor a ser pesquisado");
		$( "#cultura" ).prop("placeholder","Cultura a ser pesquisada");
		$( "#observacao" ).prop("placeholder","Observação a ser pesquisada");
		
		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_contratoVenda_consulta").prop("autocomplete", "off");
		
		$('#form_contratoVenda_consulta').on('submit', function(e){
	        abrirModalProcessando();
	    });		
		
		$('#pesquisar').on('click', function(e){
			executar('form_contratoVenda_consulta','filtrar');
	    });		
		
		$('#limparPesquisa').on('click', function(e){
			abrirModalProcessando();
			executarComSubmit('form_contratoVenda_consulta','limparFiltro');
	    });
		
		$('#novo').on('click', function(e){
			abrirModalProcessando();
			executarComSubmit('form_contratoVenda_consulta','abrirCadastro');
	    });
				
		$('#vendedor').keyup(function() {
			if ($('#vendedor').val() == null) {
				$('#vendedorSelecionado').val(null);
			}
		});
		$('#vendedor').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'contratoVenda.vendedor.pessoaFisica.razaoSocial',
			/* params : {
				'contratoVenda.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/contratoVenda.src?method=selecionarVendedorAutoComplete',
			onSelect : function(suggestion) {
				$('#vendedorSelecionado').val(suggestion.data);
			},
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			}, */
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#vendedorSelecionado').val(null);
				}
			}
		});
		
		$('#cliente').keyup(function() {
			if ($('#cliente').val() == null || $('#marca').val() == '') {
				$('#clienteSelecionado').val(null);
			}
		});
		$('#cliente').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'contratoVenda.cliente.nome',
			/* params : {
				'contratoVenda.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/contratoVenda.src?method=selecionarClienteAutoComplete',
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
						
		$('#cultura').keyup(function() {
			if ($('#cultura').val() == null || $('#cultura').val() == '') {
				$('#culturaSelecionada').val(null);
			}
		});

		$('#cultura').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'contratoVenda.cultura.nome',
			params : {
				'contratoVenda.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			},
			/* params : {
				'contratoVenda.contratoVenda.id' : function() {
					return $('#contratoVendaSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/contratoVenda.src?method=selecionarCulturaAutoComplete',
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