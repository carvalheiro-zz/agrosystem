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
			Pesquisar Safras
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Listagem das Safras
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<div class="row">

	<!-- Define o tamanho geral da tela -->
	<div class="col-md-offset-0 col-lg-offset-0 col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel" style="box-shadow: 0 10px 30px rgba(0, 0, 0, 0.8); border-color: #909090;">

			<!-- INICIO CAMPOS DE PESQUISA -->
			<div class="panel-heading cor-sistema">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_safra_consulta" action="restrito/sistema/safra" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-3">
									<label class="text-white">Nome</label>
									<html:text styleClass="form-control input-sm" styleId="nome" property="safra.nome" name="safraForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label class="text-white">Cultura</label>
									<html:text styleClass="form-control input-sm" styleId="cultura" property="setorSafra.variedade.cultura.nome" name="safraForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="culturaSelecionada" property="setorSafra.variedade.cultura.id" name="safraForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label class="text-white">Setor</label>
									<html:text styleClass="form-control input-sm" styleId="setor" property="setorSafra.setor.nomeCompleto" name="safraForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="setorSelecionado" property="setorSafra.setor.id" name="safraForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<label class="text-white">Variedade</label>
									<html:text styleClass="form-control input-sm" styleId="variedade" property="setorSafra.variedade.nomeCompleto" name="safraForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="variedadeSelecionada" property="setorSafra.variedade.id" name="safraForm" />
								</div>
							</div>
							<div class="row">

								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.safra.filtrar)">
									<div class="form-group col-xs-12 col-sm-3 col-md-2 col-lg-2" style="margin-bottom: 0px;">
										<button type="submit" id="pesquisar" class="btn btn-success btn-sm cor-sistema btn-block">
											<i class="fa fa-search"></i>
											Pesquisar
										</button>
									</div>
								</logic:equal>
								<div class="form-group col-xs-12 col-sm-3 col-md-2 col-lg-2" style="margin-bottom: 0px;">
									<button type="button" id="limparPesquisa" class="btn btn-success btn-sm cor-sistema btn-block">
										<i class="glyphicon glyphicon-erase"></i>
										Limpar
									</button>
								</div>
								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.safra.inserir)">
									<div class="form-group col-xs-12 col-sm-3 col-md-2 col-lg-2" style="margin-bottom: 0px;">
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
			<logic:empty name="safraForm" property="safras">
				<div class="row" style="margin-top: 15px;">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="alert alert-warning">Nenhum registro encontrado!</div>
					</div>
				</div>
			</logic:empty>
			<logic:notEmpty name="safraForm" property="safras">
				<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.safra.filtrar)">
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
								<thead>
									<!-- CABEÇALHO DA TABELA -->
									<tr class="cor-sistema" style="color: white;">
										<th class="text-center" style="width: 135px;">Período</th>
										<th>Nome</th>
										<th class="text-right" style="width: 90px;">Área</th>
										<th class="text-center" style="width: 80px;">Setores</th>
										<th class="text-center">Variedades</th>
										<th class="text-center" style="width: 80px;">Opções</th>
									</tr>
								</thead>
								<tbody>
									<!-- TABELA -->
									<logic:iterate id="safraCorrente" name="safraForm" property="safras" type="br.com.srcsoftware.sistema.safra.SafraDTO">

										<tr>
											<td style="vertical-align: middle;" onclick="selecionar('${contextPath}/restrito/sistema/safra.src?method=selecionar&safra.id=${safraCorrente.id}')">${safraCorrente.dataInicioFim}</td>
											<td style="vertical-align: middle;" onclick="selecionar('${contextPath}/restrito/sistema/safra.src?method=selecionar&safra.id=${safraCorrente.id}')">${safraCorrente.nome}</td>
											<td style="vertical-align: middle;" class="text-right" onclick="selecionar('${contextPath}/restrito/sistema/safra.src?method=selecionar&safra.id=${safraCorrente.id}')">${safraCorrente.areaTotal}</td>
											<td style="vertical-align: middle;" class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/safra.src?method=selecionar&safra.id=${safraCorrente.id}')">${safraCorrente.quantidadeSetores}</td>
											<td style="vertical-align: middle;" class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/safra.src?method=selecionar&safra.id=${safraCorrente.id}')">${safraCorrente.variedades}</td>
											<td class="text-center" style="min-width: 80px; vertical-align: middle;">
												<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.safra.excluir)">
													<button type="button" id="excluir" class="btn btn-danger btn-xs fa fa-trash" style="font-size: 14px" onclick="excluir('${safraCorrente.nome}', 'sistema/safra', ${safraCorrente.id} );"></button>
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
			<logic:notEmpty name="safraForm" property="safras">
				<div class="panel-footer">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">

							<nav aria-label="Page navigation">
								<ul class="pagination" style="font-size: 12px;">
									<li>
										<a href="javascript://" style="cursor: default;">Encontrados: ${safraForm.paginacao.totalRegistros} </a>
									</li>
									<logic:equal name="safraForm" property="paginacao.voltarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="safraForm" property="paginacao.voltarPaginacaoInativo" value="false">
										<li class="page-item">
											<a class="page-link" href="${contextPath}/restrito/sistema/safra.src?method=voltar&forwardDestino=safraLista" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>

									<c:forEach var="i" begin="${safraForm.paginacao.paginaInicial}" end="${safraForm.paginacao.paginaFinal}" step="1" varStatus="row">
										<c:if test="${safraForm.paginacao.paginaCorrente == i}">
											<li class="page-item active">
										</c:if>

										<c:if test="${safraForm.paginacao.paginaCorrente != i}">
											<li class="page-item ">
										</c:if>

										<a class="page-link" href="${contextPath}/restrito/sistema/safra.src?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>
										</li>
									</c:forEach>

									<logic:equal name="safraForm" property="paginacao.avancarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Next">
												<span aria-hidden="true">&raquo;</span>
												<span class="sr-only">Próximo</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="safraForm" property="paginacao.avancarPaginacaoInativo" value="false">
										<li class="page-item ">
											<a class="page-link" href="${contextPath}/restrito/sistema/safra.src?method=avancar&forwardDestino=safraLista" aria-label="Next">
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
		$("#nome").attr("maxlength", 100);
		$("#cultura").attr("maxlength", 100);
		$("#variedade").attr("maxlength", 100);
		$("#setor").attr("maxlength", 100);

		/* Setando os placeholder dos campos*/
		$("#nome").attr("placeholder", "Nome a ser pesquisado");
		$("#cultura").attr("placeholder", "Cultura a ser pesquisada");
		$("#variedade").attr("placeholder", "Variedade a ser pesquisada");
		$("#setor").attr("placeholder", "Setor a ser pesquisada");
		
		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_safra_consulta").prop("autocomplete", "off");
		
		$('#form_safra_consulta').on('submit', function(e){
	        abrirModalProcessando();
	    });
		
		$( '#pesquisar' ).click(function() {  	
			executar('form_safra_consulta','filtrar');
		});
		
		$( '#limparPesquisa' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_safra_consulta','limparFiltro');
		});
		
		$( '#novo' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_safra_consulta','abrirCadastro');
		});
		
		$('#setor').keyup(function() {
			if ($('#setor').val() == null || $('#setor').val() == '') {
				$('#setorSelecionado').val(null);
			}
		});
		
		$('#setor').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'setorSafra.setor.nomeCompleto',
			serviceUrl : '${contextPath}/restrito/sistema/safra.src?method=selecionarSetorAutoComplete',
			onSelect : function(suggestion) {
				$('#setorSelecionado').val(suggestion.data);
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#setorSelecionado').val(null);
				}
			}
		});
		
		$('#cultura').keyup(function() {
			if ($('#cultura').val() == null || $('#cultura').val() == '') {
				$('#culturaSelecionada').val(null);
				//$('#variedade').val(null);
			}
		});

		$('#cultura').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'setorSafra.variedade.cultura.nome',
			/* params : {
				'saidaGrao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/safra.src?method=selecionarCulturaAutoComplete',
			onSelect : function(suggestion) {
				$('#culturaSelecionada').val(suggestion.data);
				//$('#variedade').val(suggestion.variedade);
			},
			/*formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			},*/
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#culturaSelecionada').val(null);
					//$('#variedade').val(null);
				}
			}
		});
		
		$('#variedade').keyup(function() {
			if ($('#variedade').val() == null || $('#cultura').val() == '') {
				$('#variedadeSelecionada').val(null);
				//$('#variedade').val(null);
			}
		});

		$('#variedade').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'setorSafra.variedade.nomeCompleto',
			/* params : {
				'saidaGrao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/safra.src?method=selecionarVariedadeAutoComplete',
			onSelect : function(suggestion) {
				$('#variedadeSelecionada').val(suggestion.data);
				//$('#variedade').val(suggestion.variedade);
			},
			/*formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			},*/
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#variedadeSelecionada').val(null);
					//$('#variedade').val(null);
				}
			}
		});
	});
	
	function selecionar(urlAction) {
		abrirModalProcessando();
		window.location = urlAction;
	}
</script>