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
			Pesquisar Prestadores
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Listagem dos Prestadores
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<div class="row">

	<!-- Define o tamanho geral da tela -->
	<div class="col-md-offset-1 col-lg-offset-2 col-xs-12 col-sm-12 col-md-6 col-lg-6">
		<div class="panel" style="box-shadow: 0 10px 30px rgba(0, 0, 0, 0.8); border-color: #909090;">

			<!-- INICIO CAMPOS DE PESQUISA -->
			<div class="panel-heading cor-sistema">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_prestadorServico_consulta" action="restrito/sistema/prestadorServico" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-7 col-lg-7">
									<label class="text-white">Nome</label>
									<html:text styleClass="form-control input-sm" styleId="nome" property="prestadorServico.nome" name="prestadorServicoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-5 col-lg-5">
									<label class="text-white">Telefone</label>
									<html:text styleClass="form-control cel input-sm" styleId="telefone" property="prestadorServico.telefone" name="prestadorServicoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<label class="text-white">Empresa</label>
									<html:text styleClass="form-control input-sm" styleId="empresa" property="prestadorServico.empresa" name="prestadorServicoForm" />
								</div>
							</div>
							
							<div class="row">

								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.prestadorServico.filtrar)">
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
								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.prestadorServico.inserir)">
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
			<logic:empty name="prestadorServicoForm" property="prestadores">
				<div class="row" style="margin-top: 15px;">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="alert alert-warning">Nenhum registro encontrado!</div>
					</div>
				</div>
			</logic:empty>
			<logic:notEmpty name="prestadorServicoForm" property="prestadores">
				<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.prestadorServico.filtrar)">
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
								<thead>
									<!-- CABEÇALHO DA TABELA -->
									<tr class="cor-sistema" style="color: white;">
										<th class="text-center">#</th>
										<th>Nome</th>
										<th>Telefone</th>
										<th>Empresa</th>
										<th class="text-center" style="width: 80px;">Opções</th>
									</tr>
								</thead>
								<tbody>
									<!-- TABELA -->
									<logic:iterate id="prestadorServicoCorrente" name="prestadorServicoForm" property="prestadores" type="br.com.srcsoftware.sistema.pessoa.prestadorservico.PrestadorServicoDTO">

										<tr>
											<!-- success / info / warning / danger -->
											<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/prestadorServico.src?method=selecionar&prestadorServico.id=${prestadorServicoCorrente.id}')">${prestadorServicoCorrente.id}</td>
											<td onclick="selecionar('${contextPath}/restrito/sistema/prestadorServico.src?method=selecionar&prestadorServico.id=${prestadorServicoCorrente.id}')">${prestadorServicoCorrente.nome}</td>
											<td onclick="selecionar('${contextPath}/restrito/sistema/prestadorServico.src?method=selecionar&prestadorServico.id=${prestadorServicoCorrente.id}')">${prestadorServicoCorrente.telefone}</td>
											<td onclick="selecionar('${contextPath}/restrito/sistema/prestadorServico.src?method=selecionar&prestadorServico.id=${prestadorServicoCorrente.id}')">${prestadorServicoCorrente.empresa}</td>
											<td class="text-center" style="min-width: 80px; vertical-align: middle;">

												<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.prestadorServico.excluir)">
													<button type="button" id="excluir" class="btn btn-danger btn-xs fa fa-trash" style="font-size: 14px" onclick="excluir('${prestadorServicoCorrente.nome}', 'sistema/prestadorServico', ${prestadorServicoCorrente.id} );"></button>
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
			<logic:notEmpty name="prestadorServicoForm" property="prestadores">
				<div class="panel-footer">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">

							<nav aria-label="Page navigation">
								<ul class="pagination" style="font-size: 12px;">
									<li>
										<a href="javascript://" style="cursor: default;">Encontrados: ${prestadorServicoForm.paginacao.totalRegistros} </a>
									</li>
									<logic:equal name="prestadorServicoForm" property="paginacao.voltarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="prestadorServicoForm" property="paginacao.voltarPaginacaoInativo" value="false">
										<li class="page-item">
											<a class="page-link" href="${contextPath}/restrito/sistema/prestadorServico.src?method=voltar&forwardDestino=prestadorServicoLista" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>

									<c:forEach var="i" begin="${prestadorServicoForm.paginacao.paginaInicial}" end="${prestadorServicoForm.paginacao.paginaFinal}" step="1" varStatus="row">
										<c:if test="${prestadorServicoForm.paginacao.paginaCorrente == i}">
											<li class="page-item active">
										</c:if>

										<c:if test="${prestadorServicoForm.paginacao.paginaCorrente != i}">
											<li class="page-item ">
										</c:if>

										<a class="page-link" href="${contextPath}/restrito/sistema/prestadorServico.src?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>
										</li>
									</c:forEach>

									<logic:equal name="prestadorServicoForm" property="paginacao.avancarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Next">
												<span aria-hidden="true">&raquo;</span>
												<span class="sr-only">Próximo</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="prestadorServicoForm" property="paginacao.avancarPaginacaoInativo" value="false">
										<li class="page-item ">
											<a class="page-link" href="${contextPath}/restrito/sistema/prestadorServico.src?method=avancar&forwardDestino=prestadorServicoLista" aria-label="Next">
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
		$("#nome").focus();

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#nome").attr("maxlength", 100);
		$("#telefone").attr("maxlength", 30);
		$("#empresa").attr("maxlength", 100);

		/* Setando os placeholder dos campos*/
		$("#nome").attr("placeholder", "Nome");
		$("#telefone").attr("placeholder","(__) ____-____");
		$( "#empresa" ).attr("placeholder","Empresa");
		
		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_prestadorServico_consulta").prop("autocomplete", "off");
		
		$('#form_prestadorServico_consulta').on('submit', function(e){
	        abrirModalProcessando();
	    });
		
		$('#pesquisar').on('click', function(e){
			executar('form_prestadorServico_consulta','filtrar');
		 });
	   		
		$('#limparPesquisa').on('click', function(e){
			abrirModalProcessando();
			executarComSubmit('form_prestadorServico_consulta','limparFiltro');
	    });
		
		$('#novo').on('click', function(e){
			abrirModalProcessando();
			executarComSubmit('form_prestadorServico_consulta','abrirCadastro');
	    });
	});
	
	function selecionar(urlAction) {
		abrirModalProcessando();
		window.location = urlAction;
	}
</script>