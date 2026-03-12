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
			Pesquisar Centros de Custos
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Listagem dos Centros de Custos
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<div class="row">

	<!-- Define o tamanho geral da tela -->
	<div class="col-md-offset-1 col-lg-offset-1 col-xs-12 col-sm-12 col-md-10 col-lg-10">
		<div class="panel" style="box-shadow: 0 10px 30px rgba(0, 0, 0, 0.8); border-color: #909090;">

			<!-- INICIO CAMPOS DE PESQUISA -->
			<div class="panel-heading cor-sistema">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_centroCusto_consulta" action="restrito/sistema/centroCusto" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<div class="form-group col-xs-12 col-sm-4 col-md-3 col-lg-3">
									<label class="text-white">Código</label>
									<html:text styleClass="form-control input-sm focoInicial" styleId="codigo" property="centroCusto.codigo" name="centroCustoForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-4 col-md-6 col-lg-6">
									<label class="text-white">Descrição</label>
									<html:text styleClass="form-control input-sm" styleId="descricao" property="centroCusto.descricao" name="centroCustoForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-4 col-md-3 col-lg-3">
									<label class="text-white">Tipo</label>
									<html:select styleClass="form-control input-sm" styleId="tipo" property="centroCusto.tipo" name="centroCustoForm">
										<html:option value="">Selecione...</html:option>
										<html:option value="Despesa">Despesa</html:option>
										<html:option value="Investimento">Investimento</html:option>
										<html:option value="Receita">Receita</html:option>
									</html:select>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
									<div class="row">
										<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.centroCusto.filtrar)">
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
										<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.centroCusto.inserir)">
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
			<logic:empty name="centroCustoForm" property="centros">
				<div class="row" style="margin-top: 15px;">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="alert alert-warning">Nenhum registro encontrado!</div>
					</div>
				</div>
			</logic:empty>
			<logic:notEmpty name="centroCustoForm" property="centros">
				<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.centroCusto.filtrar)">
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
								<thead>
									<!-- CABEÇALHO DA TABELA -->
									<tr class="cor-sistema" style="color: white;">
										<th>Código</th>
										<th>Descrição</th>
										<th>Tipo</th>
										<th class="text-center" style="width: 80px;">Opções</th>
									</tr>
								</thead>
								<tbody>
									<!-- TABELA -->
									<logic:iterate id="centroCustoCorrente" name="centroCustoForm" property="centros" type="br.com.srcsoftware.sistema.notafiscal.rateio.centrocusto.CentroCustoDTO">

										<tr>
											<!-- success / info / warning / danger -->
											<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/centroCusto.src?method=selecionar&centroCusto.id=${centroCustoCorrente.id}')">${centroCustoCorrente.codigo}</td>
											<td onclick="selecionar('${contextPath}/restrito/sistema/centroCusto.src?method=selecionar&centroCusto.id=${centroCustoCorrente.id}')">${centroCustoCorrente.descricao}</td>
											<td onclick="selecionar('${contextPath}/restrito/sistema/centroCusto.src?method=selecionar&centroCusto.id=${centroCustoCorrente.id}')">${centroCustoCorrente.tipo}</td>

											<td class="text-center" style="min-width: 80px; vertical-align: middle;">
												<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.centroCusto.excluir)">
													<button type="button" id="excluir" class="btn btn-danger btn-xs fa fa-trash" style="font-size: 14px" onclick="excluir('${centroCustoCorrente.codigo}', 'sistema/centroCusto', ${centroCustoCorrente.id} );"></button>
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
			<logic:notEmpty name="centroCustoForm" property="centros">
				<div class="panel-footer">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">

							<nav aria-label="Page navigation">
								<ul class="pagination" style="font-size: 12px;">
									<li>
										<a href="javascript://" style="cursor: default;">Encontrados: ${centroCustoForm.paginacao.totalRegistros} </a>
									</li>
									<logic:equal name="centroCustoForm" property="paginacao.voltarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="centroCustoForm" property="paginacao.voltarPaginacaoInativo" value="false">
										<li class="page-item">
											<a class="page-link" href="${contextPath}/restrito/sistema/centroCusto.src?method=voltar&forwardDestino=centroCustoLista" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>

									<c:forEach var="i" begin="${centroCustoForm.paginacao.paginaInicial}" end="${centroCustoForm.paginacao.paginaFinal}" step="1" varStatus="row">
										<c:if test="${centroCustoForm.paginacao.paginaCorrente == i}">
											<li class="page-item active">
										</c:if>

										<c:if test="${centroCustoForm.paginacao.paginaCorrente != i}">
											<li class="page-item ">
										</c:if>

										<a class="page-link" href="${contextPath}/restrito/sistema/centroCusto.src?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>
										</li>
									</c:forEach>

									<logic:equal name="centroCustoForm" property="paginacao.avancarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Next">
												<span aria-hidden="true">&raquo;</span>
												<span class="sr-only">Próximo</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="centroCustoForm" property="paginacao.avancarPaginacaoInativo" value="false">
										<li class="page-item ">
											<a class="page-link" href="${contextPath}/restrito/sistema/centroCusto.src?method=avancar&forwardDestino=centroCustoLista" aria-label="Next">
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
		$( ".focoInicial" ).focus();

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#codigo").attr("maxlength", 50);
		$("#descricao").attr("maxlength", 255);
		$("#tipo").attr("maxlength", 50);

		/* Setando os placeholder dos campos*/
		$( "#codigo" ).attr("placeholder","Codigo");
		$( "#descricao" ).attr("placeholder","Descrição");
		$( "#tipo" ).attr("placeholder","Tipo");
				
		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_centroCusto_consulta").prop("autocomplete", "off");
		
		$('#form_centroCusto_consulta').on('submit', function(e){
	        abrirModalProcessando();
	    });
		
		$( '#pesquisar' ).click(function() {  	
			executar('form_centroCusto_consulta','filtrar');
		});
		
		$( '#limparPesquisa' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_centroCusto_consulta','limparFiltro');
		});
		
		$( '#novo' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_centroCusto_consulta','abrirCadastro');
		});
	});
	
	function selecionar(urlAction) {
		abrirModalProcessando();
		window.location = urlAction;
	}
</script>