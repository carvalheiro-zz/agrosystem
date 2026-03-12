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
			Pesquisar Notas Fiscais de Rateio de Despesa
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Listagem das Notas Fiscais de Rateio de Despesa
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<div class="row">

	<!-- Define o tamanho geral da notaFiscalRateioDespesa -->
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel" style="box-shadow: 0 10px 30px rgba(0, 0, 0, 0.8); border-color: #909090;">

			<!-- INICIO CAMPOS DE PESQUISA -->
			<div class="panel-heading cor-sistema">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_notaFiscalRateioDespesa_consulta" action="restrito/sistema/notaFiscalRateioDespesa" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label class="text-white">Nº NF</label>
									<html:text styleClass="form-control input-sm" styleId="numero" property="notaFiscalRateio.numero" name="notaFiscalRateioDespesaForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label class="text-white">Nº Recibo</label>
									<html:text styleClass="form-control input-sm" styleId="numeroRecibo" property="notaFiscalRateio.numeroRecibo" name="notaFiscalRateioDespesaForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<label class="text-white">Fornecedor</label>
									<html:text styleClass="form-control input-sm" styleId="fornecedor" property="notaFiscalRateio.fornecedor.nome" name="notaFiscalRateioDespesaForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label class="text-white">Data Inicial</label>
									<html:text styleClass="form-control input-sm data text-center" styleId="dataInicial" property="dataInicial" name="notaFiscalRateioDespesaForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label class="text-white">Data Final</label>
									<html:text styleClass="form-control input-sm data text-center" styleId="dataFinal" property="dataFinal" name="notaFiscalRateioDespesaForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label class="text-white">Centro de Custo</label>
									<html:text styleClass="form-control input-sm" styleId="centroCusto" property="itemAdicionar.centroCusto.codigo" name="notaFiscalRateioDespesaForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
								</div>
								
								
								
								
								
								
								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
											<label class="text-white">Atribuição de Custo</label>
											<html:select styleClass="form-control input-sm " styleId="tipo" property="itemAdicionar.tipo" name="notaFiscalRateioDespesaForm">
												<html:option value="">Selecione...</html:option>
												<html:option value="Safra/Setor">Safra/Setor</html:option>
												<html:option value="Tudo">Tudo</html:option>
												<html:option value="Cultura">Cultura</html:option>
											</html:select>
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
											<label class="text-white">Safra</label>
											<html:text styleClass="form-control input-sm " styleId="safra" property="itemAdicionar.safra.nome" name="notaFiscalRateioDespesaForm" />
											<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
											<html:hidden styleId="safraSelecionada" styleClass=" itemAdicionar" property="itemAdicionar.safra.id" name="notaFiscalRateioDespesaForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
											<label class="text-white">Setor</label>
											<html:text styleClass="form-control input-sm " styleId="setor" property="itemAdicionar.setor.nomeCompleto" name="notaFiscalRateioDespesaForm" />
											<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
											<html:hidden styleId="setorSelecionado" styleClass=" itemAdicionar" property="itemAdicionar.setor.id" name="notaFiscalRateioDespesaForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
											<label class="text-white">Cultura</label>
											<html:text styleClass="form-control input-sm itemAdicionar" styleId="cultura" property="itemAdicionar.cultura.nome" name="notaFiscalRateioDespesaForm" />
											<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
											<html:hidden styleId="culturaSelecionada" styleClass=" itemAdicionar" property="itemAdicionar.cultura.id" name="notaFiscalRateioDespesaForm" />
										</div>
								
								
								
								
								
								
							</div>
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<div class="row">
										<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.notaFiscalRateio.filtrar)">
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
										<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.notaFiscalRateio.inserir)">
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
			<logic:empty name="notaFiscalRateioDespesaForm" property="notas">
				<div class="row" style="margin-top: 15px;">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="alert alert-warning">Nenhum registro encontrado!</div>
					</div>
				</div>
			</logic:empty>
			<logic:notEmpty name="notaFiscalRateioDespesaForm" property="notas">
				<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.notaFiscalRateio.filtrar)">
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
								<thead>
									<!-- CABEÇALHO DA TABELA -->
									<tr class="cor-sistema" style="color: white;">
										<th class="text-center">Tipo</th>
										<th class="text-center">Número NF</th>
										<th class="text-center">Número Recibo</th>
										<th class="text-center">Data</th>
										<th>Fornecedor</th>
										<th class="text-center">Situação</th>
										<th class="text-center">Centros de Custos</th>
										<th>Valor</th>
										<th>Pago+Acrés.</th>
										<th class="text-center" style="width: 80px;">Opções</th>
									</tr>
								</thead>
								<tbody>
									<!-- TABELA -->
									<logic:iterate id="notaFiscalRateioDespesaCorrente" name="notaFiscalRateioDespesaForm" property="notas" type="br.com.srcsoftware.sistema.notafiscal.rateio.NotaFiscalRateioDTO">
										<logic:equal name="notaFiscalRateioDespesaCorrente" value="Aberta" property="contaPagar.situacao">
											<tr>
										</logic:equal>
										<logic:equal name="notaFiscalRateioDespesaCorrente" value="Quitada" property="contaPagar.situacao">
											<tr style="background-color: rgba(0, 18, 230, 0.41)">
										</logic:equal>
										<logic:equal name="notaFiscalRateioDespesaCorrente" value="Cancelada" property="contaPagar.situacao">
											<tr style="background-color: rgba(255, 27, 0, 0.4)">
										</logic:equal>


										<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalRateioDespesa.src?method=selecionar&notaFiscalRateio.id=${notaFiscalRateioDespesaCorrente.id}')">${notaFiscalRateioDespesaCorrente.tipo}</td>
										<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalRateioDespesa.src?method=selecionar&notaFiscalRateio.id=${notaFiscalRateioDespesaCorrente.id}')">${notaFiscalRateioDespesaCorrente.numero}</td>
										<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalRateioDespesa.src?method=selecionar&notaFiscalRateio.id=${notaFiscalRateioDespesaCorrente.id}')">${notaFiscalRateioDespesaCorrente.numeroRecibo}</td>
										<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalRateioDespesa.src?method=selecionar&notaFiscalRateio.id=${notaFiscalRateioDespesaCorrente.id}')">${notaFiscalRateioDespesaCorrente.contaPagar.dataToString}</td>

										<td onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalRateioDespesa.src?method=selecionar&notaFiscalRateio.id=${notaFiscalRateioDespesaCorrente.id}')">
											${notaFiscalRateioDespesaCorrente.fornecedor.nome}&nbsp;-&nbsp;${notaFiscalRateioDespesaCorrente.fornecedor.telefone}</td>

										<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalRateioDespesa.src?method=selecionar&notaFiscalRateio.id=${notaFiscalRateioDespesaCorrente.id}')">${notaFiscalRateioDespesaCorrente.contaPagar.situacao}</td>
										<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalRateioDespesa.src?method=selecionar&notaFiscalRateio.id=${notaFiscalRateioDespesaCorrente.id}')">${notaFiscalRateioDespesaCorrente.centrosCustosToString}</td>
										<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalRateioDespesa.src?method=selecionar&notaFiscalRateio.id=${notaFiscalRateioDespesaCorrente.id}')">${notaFiscalRateioDespesaCorrente.contaPagar.valor}</td>

										<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/notaFiscalRateioDespesa.src?method=selecionar&notaFiscalRateio.id=${notaFiscalRateioDespesaCorrente.id}')">${notaFiscalRateioDespesaCorrente.contaPagar.totalRecebido}</td>

										<td class="text-center" style="min-width: 80px; vertical-align: middle;">
											<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.notaFiscalRateio.excluir)">
												<button type="button" id="excluir" class="btn btn-danger btn-xs fa fa-trash" style="font-size: 14px"
													onclick="excluir('Nº ${notaFiscalRateioDespesaCorrente.contaPagar.numero} - Fornecedor: ${notaFiscalRateioDespesaCorrente.fornecedor.nome}&nbsp;-&nbsp;${notaFiscalRateioDespesaCorrente.fornecedor.telefone}', 'sistema/notaFiscalRateioDespesa', ${notaFiscalRateioDespesaCorrente.id} );"></button>
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
			<logic:notEmpty name="notaFiscalRateioDespesaForm" property="notas">
				<div class="panel-footer">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">

							<nav aria-label="Page navigation">
								<ul class="pagination" style="font-size: 12px;">
									<li>
										<a href="javascript://" style="cursor: default;">Encontrados: ${notaFiscalRateioDespesaForm.paginacao.totalRegistros} </a>
									</li>
									<logic:equal name="notaFiscalRateioDespesaForm" property="paginacao.voltarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="notaFiscalRateioDespesaForm" property="paginacao.voltarPaginacaoInativo" value="false">
										<li class="page-item">
											<a class="page-link" href="${contextPath}/restrito/sistema/notaFiscalRateioDespesa.src?method=voltar&forwardDestino=notaFiscalRateioDespesaLista" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>

									<c:forEach var="i" begin="${notaFiscalRateioDespesaForm.paginacao.paginaInicial}" end="${notaFiscalRateioDespesaForm.paginacao.paginaFinal}" step="1" varStatus="row">
										<c:if test="${notaFiscalRateioDespesaForm.paginacao.paginaCorrente == i}">
											<li class="page-item active">
										</c:if>

										<c:if test="${notaFiscalRateioDespesaForm.paginacao.paginaCorrente != i}">
											<li class="page-item ">
										</c:if>

										<a class="page-link" href="${contextPath}/restrito/sistema/notaFiscalRateioDespesa.src?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>
										</li>
									</c:forEach>

									<logic:equal name="notaFiscalRateioDespesaForm" property="paginacao.avancarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Next">
												<span aria-hidden="true">&raquo;</span>
												<span class="sr-only">Próximo</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="notaFiscalRateioDespesaForm" property="paginacao.avancarPaginacaoInativo" value="false">
										<li class="page-item ">
											<a class="page-link" href="${contextPath}/restrito/sistema/notaFiscalRateioDespesa.src?method=avancar&forwardDestino=notaFiscalRateioDespesaLista" aria-label="Next">
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
		$("#numero").attr("maxlength", 50);
		$("#numeroRecibo").attr("maxlength", 50);
		$("#fornecedor").attr("maxlength", 50);

		/* Setando os placeholder dos campos*/
		$( "#numero" ).attr("placeholder","Número a ser pesquisado");
		$( "#numeroRecibo" ).attr("placeholder","Número a ser pesquisado");
		$( "#fornecedor" ).attr("placeholder","Fornecedor a ser pesquisado");
		
		
		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_notaFiscalRateioDespesa_consulta").attr("autocomplete", "off");
		
		$('#form_notaFiscalRateioDespesa_consulta').on('submit', function(e){
	        abrirModalProcessando();
	    });
		
		$( '#pesquisar' ).click(function() {  	
			executar('form_notaFiscalRateioDespesa_consulta','filtrar');
		});
		
		$( '#limparPesquisa' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_notaFiscalRateioDespesa_consulta','limparFiltro');
		});
		
		$( '#novo' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_notaFiscalRateioDespesa_consulta','abrirCadastro');
		});		
		
		$('#fornecedor').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'notaFiscalRateio.fornecedor.nome',
			serviceUrl : '${contextPath}/restrito/sistema/notaFiscalRateioDespesa.src?method=selecionarFornecedorAutoComplete',
			onSelect : function(suggestion) {
				//alert('You selected: ' + suggestion.value + ', ' + suggestion.data);				
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					
				}
			}
		});		
		
		$('#centroCusto').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'itemAdicionar.centroCusto.codigo',
			/*params : {
				'notaFiscalRateio.tipo' : function() {
					return $('#tipoNF').val();
				}
			},*/
			formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.descricao;
				return valorExibir;
			},
			serviceUrl : '${contextPath}/restrito/sistema/notaFiscalRateioDespesa.src?method=selecionarCentroCustoAutoComplete',
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
			paramName : 'itemAdicionar.safra.nome',
			serviceUrl : '${contextPath}/restrito/sistema/notaFiscalRateioDespesa.src?method=selecionarSafraAutoComplete',
			onSelect : function(suggestion) {
				$('#safraSelecionada').val(suggestion.data);

				if ($('#tipo').val() == '') {
					$("#safra").focus();
				} else if ($('#tipo').val() == 'Safra/Setor') {
					$("#setor").focus();
				} else if ($('#tipo').val() == 'Tudo') {
					$("#imovel").focus();
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
			paramName : 'itemAdicionar.setor.nomeCompleto',
			params : {
				'itemAdicionar.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			},
			serviceUrl : '${contextPath}/restrito/sistema/notaFiscalRateioDespesa.src?method=selecionarSetorAutoComplete',
			onSelect : function(suggestion) {
				$('#setorSelecionado').val(suggestion.data);

				if ($('#tipo').val() == '') {
					$("#safra").focus();
				} else if ($('#tipo').val() == 'Safra/Setor') {
					$("#imovel").focus();
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
			paramName : 'itemAdicionar.cultura.nome',
			params : {
				'itemAdicionar.setor.id' : function() {
					return $('#setorSelecionado').val();
				},
				'itemAdicionar.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			},
			/* params : {
				'saidaGrao.notaFiscalRateio.id' : function() {
					return $('#aplicacaoSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/notaFiscalRateioDespesa.src?method=selecionarCulturaAutoComplete',
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