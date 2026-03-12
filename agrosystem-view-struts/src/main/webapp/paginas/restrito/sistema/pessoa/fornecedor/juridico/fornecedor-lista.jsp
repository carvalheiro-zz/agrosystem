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
			Pesquisar Fornecedores
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Listagem dos Fornecedores
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
						<html:form styleId="form_fornecedorJuridico_consulta" action="restrito/sistema/fornecedorJuridico" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<label class="text-white">Nome</label>
									<html:text styleClass="form-control input-sm" styleId="nome" property="fornecedorJuridico.nome" name="fornecedorJuridicoForm" />
								</div>

								<%-- <div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label class="text-white">Celular</label>
									<html:text styleClass="form-control cel input-sm" styleId="celular" property="fornecedorJuridico.celular" name="fornecedorJuridicoForm" />
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label class="text-white">Fixo</label>
									<html:text styleClass="form-control fixo input-sm" styleId="fixo" property="fornecedorJuridico.fixo" name="fornecedorJuridicoForm" />
								</div> --%>

								<div class="form-group col-xs-12 col-sm-12 col-md-5 col-lg-5">
									<label class="text-white">Telefone</label>
									<html:text styleClass="form-control cel input-sm" styleId="telefone" property="fornecedorJuridico.telefone" name="fornecedorJuridicoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<label class="text-white">Endereço</label>
									<html:text styleClass="form-control input-sm" styleId="endereco" property="fornecedorJuridico.endereco" name="fornecedorJuridicoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<label class="text-white">Observação</label>
									<html:text styleClass="form-control input-sm" styleId="observacao" property="fornecedorJuridico.observacao" name="fornecedorJuridicoForm" />
								</div>
							</div>


							<div class="row">

								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.fornecedorJuridico.filtrar)">
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
								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.fornecedorJuridico.inserir)">
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
			<logic:empty name="fornecedorJuridicoForm" property="fornecedores">
				<div class="row" style="margin-top: 15px;">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="alert alert-warning">Nenhum registro encontrado!</div>
					</div>
				</div>
			</logic:empty>
			<logic:notEmpty name="fornecedorJuridicoForm" property="fornecedores">
				<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.fornecedorJuridico.filtrar)">
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
								<thead>
									<!-- CABEÇALHO DA TABELA -->
									<tr class="cor-sistema" style="color: white;">
										<th class="text-center">#</th>
										<th>Nome</th>
										<th>Telefone</th>
										<th>Endereço</th>
										<th>Observação</th>
										<th class="text-center" style="width: 80px;">Opções</th>
									</tr>
								</thead>
								<tbody>
									<!-- TABELA -->
									<logic:iterate id="fornecedorJuridicoCorrente" name="fornecedorJuridicoForm" property="fornecedores" type="br.com.srcsoftware.sistema.pessoa.fornecedor.juridico.FornecedorJuridicoDTO">

										<tr>
											<!-- success / info / warning / danger -->
											<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/fornecedorJuridico.src?method=selecionar&fornecedorJuridico.id=${fornecedorJuridicoCorrente.id}')">${fornecedorJuridicoCorrente.id}</td>

											<td onclick="selecionar('${contextPath}/restrito/sistema/fornecedorJuridico.src?method=selecionar&fornecedorJuridico.id=${fornecedorJuridicoCorrente.id}')">${fornecedorJuridicoCorrente.nome}</td>

											<%-- <td onclick="selecionar('${contextPath}/restrito/sistema/fornecedorJuridico.src?method=selecionar&fornecedorJuridico.id=${fornecedorJuridicoCorrente.id}')">${fornecedorJuridicoCorrente.celular}| ${fornecedorJuridicoCorrente.fixo}</td> --%>
											<td onclick="selecionar('${contextPath}/restrito/sistema/fornecedorJuridico.src?method=selecionar&fornecedorJuridico.id=${fornecedorJuridicoCorrente.id}')">${fornecedorJuridicoCorrente.telefone}</td>

											<td onclick="selecionar('${contextPath}/restrito/sistema/fornecedorJuridico.src?method=selecionar&fornecedorJuridico.id=${fornecedorJuridicoCorrente.id}')">${fornecedorJuridicoCorrente.endereco}</td>

											<td onclick="selecionar('${contextPath}/restrito/sistema/fornecedorJuridico.src?method=selecionar&fornecedorJuridico.id=${fornecedorJuridicoCorrente.id}')">${fornecedorJuridicoCorrente.observacao}</td>
											<td class="text-center" style="min-width: 80px; vertical-align: middle;">

												<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.fornecedorJuridico.excluir)">
													<button type="button" id="excluir" class="btn btn-danger btn-xs fa fa-trash" style="font-size: 14px" onclick="excluir('${fornecedorJuridicoCorrente.nome}', 'sistema/fornecedorJuridico', ${fornecedorJuridicoCorrente.id} );"></button>
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
			<logic:notEmpty name="fornecedorJuridicoForm" property="fornecedores">
				<div class="panel-footer">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">

							<nav aria-label="Page navigation">
								<ul class="pagination" style="font-size: 12px;">
									<li>
										<a href="javascript://" style="cursor: default;">Encontrados: ${fornecedorJuridicoForm.paginacao.totalRegistros} </a>
									</li>
									<logic:equal name="fornecedorJuridicoForm" property="paginacao.voltarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="fornecedorJuridicoForm" property="paginacao.voltarPaginacaoInativo" value="false">
										<li class="page-item">
											<a class="page-link" href="${contextPath}/restrito/sistema/fornecedorJuridico.src?method=voltar&forwardDestino=fornecedorJuridicoLista" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>

									<c:forEach var="i" begin="${fornecedorJuridicoForm.paginacao.paginaInicial}" end="${fornecedorJuridicoForm.paginacao.paginaFinal}" step="1" varStatus="row">
										<c:if test="${fornecedorJuridicoForm.paginacao.paginaCorrente == i}">
											<li class="page-item active">
										</c:if>

										<c:if test="${fornecedorJuridicoForm.paginacao.paginaCorrente != i}">
											<li class="page-item ">
										</c:if>

										<a class="page-link" href="${contextPath}/restrito/sistema/fornecedorJuridico.src?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>
										</li>
									</c:forEach>

									<logic:equal name="fornecedorJuridicoForm" property="paginacao.avancarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Next">
												<span aria-hidden="true">&raquo;</span>
												<span class="sr-only">Próximo</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="fornecedorJuridicoForm" property="paginacao.avancarPaginacaoInativo" value="false">
										<li class="page-item ">
											<a class="page-link" href="${contextPath}/restrito/sistema/fornecedorJuridico.src?method=avancar&forwardDestino=fornecedorJuridicoLista" aria-label="Next">
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
		$("#nome").attr("maxlength", 50);
		$("#endereco").attr("maxlength", 255);
		$("#observacao").attr("maxlength", 255);

		/* Setando os placeholder dos campos*/
		$("#nome").attr("placeholder", "Nome");
		$("#endereco").attr("placeholder","Rua/Av, nº Bairro CEP Cidade/UF");
		$( "#observacao" ).attr("placeholder","Observação");
		
		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_fornecedorJuridico_consulta").prop("autocomplete", "off");
		
		$('#form_fornecedorJuridico_consulta').on('submit', function(e){
	        abrirModalProcessando();
	    });
		
		$('#pesquisar').on('click', function(e){
			executar('form_fornecedorJuridico_consulta','filtrar');
		 });
	   		
		$('#limparPesquisa').on('click', function(e){
			abrirModalProcessando();
			executarComSubmit('form_fornecedorJuridico_consulta','limparFiltro');
	    });
		
		$('#novo').on('click', function(e){
			abrirModalProcessando();
			executarComSubmit('form_fornecedorJuridico_consulta','abrirCadastro');
	    });
	});
	
	function selecionar(urlAction) {
		abrirModalProcessando();
		window.location = urlAction;
	}
</script>