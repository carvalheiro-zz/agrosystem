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
			Pesquisar Culturas
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Listagem dos Culturas
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<div class="row">

	<!-- Define o tamanho geral da tela -->
	<div class="col-md-offset-1 col-lg-offset-2 col-xs-12 col-sm-12 col-md-10 col-lg-8">
		<div class="panel" style="box-shadow: 0 10px 30px rgba(0, 0, 0, 0.8); border-color: #909090;">

			<!-- INICIO CAMPOS DE PESQUISA -->
			<div class="panel-heading cor-sistema">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_cultura_consulta" action="restrito/sistema/cultura" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<div class="form-group col-xs-12 col-sm-8 col-md-8 col-lg-8">
									<label class="text-white">Nome</label>
									<html:text styleClass="form-control input-sm" styleId="nome" property="cultura.nome" name="culturaForm" />
								</div>
							</div>
							<div class="row">

								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.cultura.filtrar)">
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
								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.cultura.inserir)">
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
			<logic:empty name="culturaForm" property="culturas">
				<div class="row" style="margin-top: 15px;">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="alert alert-warning">Nenhum registro encontrado!</div>
					</div>
				</div>
			</logic:empty>
			<logic:notEmpty name="culturaForm" property="culturas">
				<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.cultura.filtrar)">
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
								<thead>
									<!-- CABEÇALHO DA TABELA -->
									<tr class="cor-sistema" style="color: white;">
										<th>Nome</th>
										<th class="text-center" style="width: 80px;">Opções</th>
									</tr>
								</thead>
								<tbody>
									<!-- TABELA -->
									<logic:iterate id="culturaCorrente" name="culturaForm" property="culturas" type="br.com.srcsoftware.sistema.safra.cultura.CulturaDTO">

										<tr>
											<!-- success / info / warning / danger -->
											<td onclick="selecionar('${contextPath}/restrito/sistema/cultura.src?method=selecionar&cultura.id=${culturaCorrente.id}')">${culturaCorrente.nome}</td>
											
											<td class="text-center" style="min-width: 80px; vertical-align: middle;">
												<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.cultura.excluir)">
													<button type="button" id="excluir" class="btn btn-danger btn-xs fa fa-trash" style="font-size: 14px" onclick="excluir('${culturaCorrente.nome}', 'sistema/cultura', ${culturaCorrente.id} );"></button>
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
			<logic:notEmpty name="culturaForm" property="culturas">
				<div class="panel-footer">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">

							<nav aria-label="Page navigation">
								<ul class="pagination" style="font-size: 12px;">
									<li>
										<a href="javascript://" style="cursor: default;">Encontrados: ${culturaForm.paginacao.totalRegistros} </a>
									</li>
									<logic:equal name="culturaForm" property="paginacao.voltarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="culturaForm" property="paginacao.voltarPaginacaoInativo" value="false">
										<li class="page-item">
											<a class="page-link" href="${contextPath}/restrito/sistema/cultura.src?method=voltar&forwardDestino=culturaLista" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>

									<c:forEach var="i" begin="${culturaForm.paginacao.paginaInicial}" end="${culturaForm.paginacao.paginaFinal}" step="1" varStatus="row">
										<c:if test="${culturaForm.paginacao.paginaCorrente == i}">
											<li class="page-item active">
										</c:if>

										<c:if test="${culturaForm.paginacao.paginaCorrente != i}">
											<li class="page-item ">
										</c:if>

										<a class="page-link" href="${contextPath}/restrito/sistema/cultura.src?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>
										</li>
									</c:forEach>

									<logic:equal name="culturaForm" property="paginacao.avancarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Next">
												<span aria-hidden="true">&raquo;</span>
												<span class="sr-only">Próximo</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="culturaForm" property="paginacao.avancarPaginacaoInativo" value="false">
										<li class="page-item ">
											<a class="page-link" href="${contextPath}/restrito/sistema/cultura.src?method=avancar&forwardDestino=culturaLista" aria-label="Next">
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
		$("#nome").prop("maxlength", 50);
		$("#variedade").prop("maxlength", 50);

		/* Setando os placeholder dos campos*/
		$( "#nome" ).prop("placeholder","Nome a ser pesquisado");
		$( "#variedade" ).prop("placeholder","Variedade a ser pesquisada");
		
		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_cultura_consulta").prop("autocomplete", "off");
		
		$('#form_cultura_consulta').on('submit', function(e){
	        abrirModalProcessando();
	    });
		
		$( '#pesquisar' ).click(function() {  	
			executar('form_cultura_consulta','filtrar');
		});
		
		$( '#limparPesquisa' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_cultura_consulta','limparFiltro');
		});
		
		$( '#novo' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_cultura_consulta','abrirCadastro');
		});
	});
	
	function selecionar(urlAction) {
		abrirModalProcessando();
		window.location = urlAction;
	}
</script>