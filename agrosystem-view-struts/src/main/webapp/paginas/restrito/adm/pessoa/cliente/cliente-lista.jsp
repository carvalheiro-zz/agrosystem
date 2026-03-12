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
			Pesquisar Clientes
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Listagem dos Clientes
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
						<html:form styleId="form_cliente_consulta" action="restrito/admin/cliente" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label class="text-white">Natureza Pessoa</label>
									<html:select styleClass="form-control input-sm" styleId="natureza" property="clienteFilter.natureza" name="clienteForm" >
										<html:option value="">Selecione...</html:option>
										<html:option value="PF">PF</html:option>
										<html:option value="PJ">PJ</html:option>
									</html:select>
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<label class="text-white">Nome</label>
									<html:text styleClass="form-control input-sm" styleId="nome" property="clienteFilter.nome" name="clienteForm" />
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label class="text-white">CPF/CNPJ</label>
									<html:text styleClass="form-control input-sm" styleId="cpf" property="clienteFilter.numeroDocumentoIdentificacao" name="clienteForm" />
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label class="text-white">Status</label>
									<html:select styleClass="form-control input-sm" styleId="ativo" property="clienteFilter.ativo" name="clienteForm" >
										<html:option value="">Selecione...</html:option>
										<html:option value="true">Ativo</html:option>
										<html:option value="false">Inativo</html:option>
									</html:select>
								</div>								

							</div>
							<div class="row">

								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.cliente.filtrar)">
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-2" style="margin-bottom: 0px;">
										<button type="submit" id="pesquisar" class="btn btn-success btn-sm cor-sistema btn-block">
											<i class="fa fa-search"></i>
											Pesquisar
										</button>
									</div>
								</logic:equal>
								<div class="col-xs-12 col-sm-12 col-md-4 col-lg-2" style="margin-bottom: 0px;">
									<button type="button" id="limparPesquisa" class="btn btn-success btn-sm cor-sistema btn-block">
										<i class="glyphicon glyphicon-erase"></i>
										Limpar
									</button>
								</div>
								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.cliente.inserir)">
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-2" style="margin-bottom: 0px;">
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
			<logic:empty name="clienteForm" property="clientes">
				<div class="row" style="margin-top: 15px;">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="alert alert-warning">Nenhum registro encontrado!</div>
					</div>
				</div>
			</logic:empty>
			<logic:notEmpty name="clienteForm" property="clientes">
				<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.cliente.filtrar)">
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
								<thead>
									<!-- CABEÇALHO DA TABELA -->
									<tr class="cor-sistema" style="color: white;">
										<th>Natureza</th>
										<th>Nome</th>
										<th>CPF/CNPJ</th>
										<th>Ativo</th>
										<th>E-mail</th>
										<th>Telefone</th>
										<%-- <logic:equal value="true" name="usuarioSessaoPOJO" property="isADM" scope="session">
											<th>Empresa</th>
										</logic:equal> --%>
										<th class="text-center" style="width: 80px;">Opções</th>
									</tr>
								</thead>
								<tbody>
									<!-- TABELA -->
									<logic:iterate id="clienteCorrente" name="clienteForm" property="clientes" type="br.com.srcsoftware.modular.manager.pessoa.cliente.ClienteDTO">

										<logic:equal name="clienteCorrente" value="true" property="ativo">
											<tr>
										</logic:equal>
										<logic:equal name="clienteCorrente" value="false" property="ativo">
											<tr class="danger">
										</logic:equal>
										
											<!-- success / info / warning / danger -->
											<td onclick="selecionar('${contextPath}/restrito/admin/cliente.src?method=selecionar&clienteFilter.id=${clienteCorrente.id}')">${clienteCorrente.natureza}</td>
											<td onclick="selecionar('${contextPath}/restrito/admin/cliente.src?method=selecionar&clienteFilter.id=${clienteCorrente.id}')">${clienteCorrente.nome}</td>											
											<td onclick="selecionar('${contextPath}/restrito/admin/cliente.src?method=selecionar&clienteFilter.id=${clienteCorrente.id}')">${clienteCorrente.numeroDocumentoIdentificacao}</td>
											<td onclick="selecionar('${contextPath}/restrito/admin/cliente.src?method=selecionar&clienteFilter.id=${clienteCorrente.id}')">${clienteCorrente.ativoToString}</td>
											<td onclick="selecionar('${contextPath}/restrito/admin/cliente.src?method=selecionar&clienteFilter.id=${clienteCorrente.id}')">${clienteCorrente.email}</td>
											<td onclick="selecionar('${contextPath}/restrito/admin/cliente.src?method=selecionar&clienteFilter.id=${clienteCorrente.id}')">${clienteCorrente.telefoneToString}</td>
											<%-- <logic:equal value="true" name="usuarioSessaoPOJO" property="isADM" scope="session">
												<td onclick="selecionar('${contextPath}/restrito/admin/cliente.src?method=selecionar&clienteFilter.id=${clienteCorrente.id}')">${clienteCorrente.empresa.nomeFantasia}</td>
											</logic:equal> --%>

											<td class="text-center" style="min-width: 80px; vertical-align: middle;">

												<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.cliente.excluir)">
													<button type="button" id="excluir" class="btn btn-danger btn-xs fa fa-trash" style="font-size: 14px" onclick="excluir('${clienteCorrente.nome}', 'admin/cliente', ${clienteCorrente.id} );"></button>
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
			<logic:notEmpty name="clienteForm" property="clientes">
				<div class="panel-footer">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">

							<nav aria-label="Page navigation">
								<ul class="pagination" style="font-size: 12px;">
									<li>
										<a href="javascript://" style="cursor: default;">Encontrados: ${clienteForm.paginacao.totalRegistros} </a>
									</li>
									<logic:equal name="clienteForm" property="paginacao.voltarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="clienteForm" property="paginacao.voltarPaginacaoInativo" value="false">
										<li class="page-item">
											<a class="page-link" href="${contextPath}/restrito/admin/cliente.src?method=voltar&forwardDestino=clienteLista" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>

									<c:forEach var="i" begin="${clienteForm.paginacao.paginaInicial}" end="${clienteForm.paginacao.paginaFinal}" step="1" varStatus="row">
										<c:if test="${clienteForm.paginacao.paginaCorrente == i}">
											<li class="page-item active">
										</c:if>

										<c:if test="${clienteForm.paginacao.paginaCorrente != i}">
											<li class="page-item ">
										</c:if>

										<a class="page-link" href="${contextPath}/restrito/admin/cliente.src?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>
										</li>
									</c:forEach>

									<logic:equal name="clienteForm" property="paginacao.avancarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Next">
												<span aria-hidden="true">&raquo;</span>
												<span class="sr-only">Próximo</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="clienteForm" property="paginacao.avancarPaginacaoInativo" value="false">
										<li class="page-item ">
											<a class="page-link" href="${contextPath}/restrito/admin/cliente.src?method=avancar&forwardDestino=clienteLista" aria-label="Next">
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
	$(document).ready(function() {
		/* Foco inicial */
		$("#nome").focus();

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#nome").attr("maxlength", 50);
		$("#numeroDocumentoIdentificacao").attr("maxlength", 50);
		$("#ativo").attr("maxlength", 10);
		$("#email").attr("maxlength", 50);

		/* Setando os placeholder dos campos*/
		$("#nome").attr("placeholder", "Nome");
		$("#numeroDocumentoIdentificacao").attr("placeholder", "Descrição");
		$("#ativo").attr("placeholder", "Ativo");
		$("#email").attr("placeholder", "E-mail");

		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_cliente_consulta").attr("autocomplete", "off");
		
		$('#form_cliente_consulta').on('submit', function(e){
	        abrirModalProcessando();
	    });
		
		$( '#pesquisar' ).click(function() {  	
			executar('form_cliente_consulta','filtrar');
		});
		
		$( '#limparPesquisa' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_cliente_consulta','limparFiltro');
		});
		
		$( '#novo' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_cliente_consulta','abrirCadastro');
		});
		
	});
	
	function selecionar(urlAction) {
		abrirModalProcessando();
		window.location = urlAction;
	}
</script>