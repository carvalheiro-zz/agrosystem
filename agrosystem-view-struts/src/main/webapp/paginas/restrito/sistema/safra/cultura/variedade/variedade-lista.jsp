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
			Pesquisar Variedades
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Listagem das Variedades
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<div class="row">

	<!-- Define o tamanho geral da tela -->
	<div class="col-md-offset-0 col-lg-offset-0 col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel" style="box-shadow: 0 10px 30px rgba(0, 0, 0, 0.8); border-color: #909090;">

			<!-- INICIO CAMPOS DE PESQUISA -->
			<div class="panel-heading cor-sistema">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_variedade_consulta" action="restrito/sistema/variedade" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<label class="text-white">Nome</label>
									<html:text styleClass="form-control input-sm" styleId="nome" property="variedade.nomeCompleto" name="variedadeForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<label class="text-white">Cultura</label>									
									<html:text styleClass="form-control input-sm" styleId="cultura" property="variedade.cultura.nome" name="variedadeForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
								</div>
								
																							
							</div>
							<div class="row">

								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.variedade.filtrar)">
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
								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.variedade.inserir)">
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
			<logic:empty name="variedadeForm" property="variedades">
				<div class="row" style="margin-top: 15px;">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="alert alert-warning">Nenhum registro encontrado!</div>
					</div>
				</div>
			</logic:empty>
			<logic:notEmpty name="variedadeForm" property="variedades">
				<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.variedade.filtrar)">
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
								<thead>
									<!-- CABEÇALHO DA TABELA -->
									<tr class="cor-sistema" style="color: white;">
										<th>Nome</th>
										<th>Cultura</th>
										<th class="text-center" style="width: 80px;">Opções</th>
									</tr>
								</thead>
								<tbody>
									<!-- TABELA -->
									<logic:iterate id="variedadeCorrente" name="variedadeForm" property="variedades" type="br.com.srcsoftware.sistema.safra.cultura.variedade.VariedadeDTO">

										<tr>
											<!-- success / info / warning / danger -->
											<td onclick="selecionar('${contextPath}/restrito/sistema/variedade.src?method=selecionar&variedade.id=${variedadeCorrente.id}')">${variedadeCorrente.nome}</td>
											<td onclick="selecionar('${contextPath}/restrito/sistema/variedade.src?method=selecionar&variedade.id=${variedadeCorrente.id}')">${variedadeCorrente.cultura.nome}</td>
											
											<td class="text-center" style="min-width: 80px; vertical-align: middle;">
												<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.variedade.excluir)">
													<button type="button" id="excluir" class="btn btn-danger btn-xs fa fa-trash" style="font-size: 14px" onclick="excluir('${variedadeCorrente.nomeCompleto}', 'sistema/variedade', ${variedadeCorrente.id} );"></button>
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
			<logic:notEmpty name="variedadeForm" property="variedades">
				<div class="panel-footer">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">

							<nav aria-label="Page navigation">
								<ul class="pagination" style="font-size: 12px;">
									<li>
										<a href="javascript://" style="cursor: default;">Encontrados: ${variedadeForm.paginacao.totalRegistros} </a>
									</li>
									<logic:equal name="variedadeForm" property="paginacao.voltarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="variedadeForm" property="paginacao.voltarPaginacaoInativo" value="false">
										<li class="page-item">
											<a class="page-link" href="${contextPath}/restrito/sistema/variedade.src?method=voltar&forwardDestino=variedadeLista" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>

									<c:forEach var="i" begin="${variedadeForm.paginacao.paginaInicial}" end="${variedadeForm.paginacao.paginaFinal}" step="1" varStatus="row">
										<c:if test="${variedadeForm.paginacao.paginaCorrente == i}">
											<li class="page-item active">
										</c:if>

										<c:if test="${variedadeForm.paginacao.paginaCorrente != i}">
											<li class="page-item ">
										</c:if>

										<a class="page-link" href="${contextPath}/restrito/sistema/variedade.src?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>
										</li>
									</c:forEach>

									<logic:equal name="variedadeForm" property="paginacao.avancarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Next">
												<span aria-hidden="true">&raquo;</span>
												<span class="sr-only">Próximo</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="variedadeForm" property="paginacao.avancarPaginacaoInativo" value="false">
										<li class="page-item ">
											<a class="page-link" href="${contextPath}/restrito/sistema/variedade.src?method=avancar&forwardDestino=variedadeLista" aria-label="Next">
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
		$("#cultura").prop("maxlength", 100);

		/* Setando os placeholder dos campos*/
		$( "#nome" ).prop("placeholder","Nome a ser pesquisado");
		$( "#cultura" ).prop("placeholder","Cultura a ser pesquisada");
		
		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_variedade_consulta").prop("autocomplete", "off");
		
		$('#form_variedade_consulta').on('submit', function(e){
	        abrirModalProcessando();
	    });
		
		$( '#pesquisar' ).click(function() {  	
			executar('form_variedade_consulta','filtrar');
		});
		
		$( '#limparPesquisa' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_variedade_consulta','limparFiltro');
		});
		
		$( '#novo' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_variedade_consulta','abrirCadastro');
		});
		
		$('#nome').keyup(function() {
			if ($('#nome').val() == null || $('#nome').val() == '') {
				//$('#variedadeSelecionado').val(null);
			}
		});
		
		$('#nome').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'variedade.nomeCompleto',
			/* params : {
				'saidaGrao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/variedade.src?method=selecionarVariedadeAutoComplete',
			onSelect : function(suggestion) {
				//$('#variedadeSelecionado').val(suggestion.data);
			},
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			}, */
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					//$('#variedadeSelecionado').val(null);
				}
			}
		});
		
		$('#cultura').keyup(function() {
			if ($('#cultura').val() == null || $('#cultura').val() == '') {
				//$('#culturaSelecionada').val(null);
			}
		});
		$('#cultura').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'variedade.cultura.nome',
			/* params : {
				'saidaGrao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/variedade.src?method=selecionarCulturaAutoComplete',
			onSelect : function(suggestion) {
				//$('#culturaSelecionada').val(suggestion.data);
			},
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			}, */
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					//$('#culturaSelecionada').val(null);
				}
			}
		});		
	
	});
	
	function selecionar(urlAction) {
		abrirModalProcessando();
		window.location = urlAction;
	}
</script>