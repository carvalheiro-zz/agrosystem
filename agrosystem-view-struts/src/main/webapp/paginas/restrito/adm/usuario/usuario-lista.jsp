<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- CAMPOS DE FILTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -30px; margin-bottom: -15px;">
		<h1 class="page-header cabecalho_pagina">
			<i class="fa fa-user-plus fa-fw" style="font-size: 26px;"></i>
			Usuários do sistema
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Pesquisa
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<div class="row">

	<!-- Define o tamanho geral da usuario -->
	<div class="col-xs-offset-0 col-sm-offset-0 col-md-offset-1 col-lg-offset-1 col-xs-12 col-sm-12 col-md-10 col-lg-10">
		<div class="panel sombra">

			<!-- INICIO CAMPOS DE PESQUISA -->
			<div class="panel-heading cor-sistema">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_usuario_consulta" action="restrito/admin/usuario" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<%-- <logic:equal value="true" name="usuarioSessaoPOJO" property="isADM" scope="session">
									<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<label class="text-white">Empresa</label>
										<html:select styleClass="form-control input-sm" styleId="empresa" property="usuarioFilter.empresa.id" name="usuarioForm">
											<html:option value="">Selecione...</html:option>
											<html:optionsCollection name="usuarioForm" property="comboEmpresas" label="label" value="value" />
										</html:select>
									</div>
								</logic:equal> --%>

								<div class="form-group col-xs-12 col-sm-612 col-md-4 col-lg-4">
									<label class="text-white">Usuário</label>
									<html:text styleClass="form-control input-sm" styleId="loginPesquisa" property="usuarioFilter.login" name="usuarioForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-8 col-lg-8">
									<label class="text-white">E-mail</label>
									<html:text styleClass="form-control input-sm" styleId="emailPesquisa" property="usuarioFilter.email" name="usuarioForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-8 col-lg-8">
									<label class="text-white">Tipo</label>
									<html:select styleClass="form-control input-sm" styleId="tipoPesquisa" property="usuarioFilter.tipoUsuario.id" name="usuarioForm">
										<html:option value="">Selecione...</html:option>
										<html:optionsCollection name="usuarioForm" property="comboTiposUsuario(${usuarioSessaoPOJO.isADM})" label="label" value="value" />
									</html:select>
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<label class="text-white">Status</label>
									<html:select styleClass="form-control input-sm" styleId="statusPesquisa" property="usuarioFilter.status" name="usuarioForm">
										<html:option value="">Selecione...</html:option>
										<html:option value="true">Ativo</html:option>
										<html:option value="false">Inativo</html:option>
									</html:select>
								</div>
							</div>
							<div class="row">
								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.usuario.filtrar)">
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-2">
										<button type="submit" id="pesquisar" class="btn btn-success btn-sm btn-block cor-sistema">
											<i class="fa fa-search"></i>
											Pesquisar
										</button>
									</div>
								</logic:equal>

								<div class="col-xs-12 col-sm-12 col-md-4 col-lg-2">
									<button type="button" id="limparPesquisa" class="btn btn-success btn-sm btn-block cor-sistema">
										<i class="glyphicon glyphicon-erase"></i>
										Limpar
									</button>
								</div>
								<%-- <logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.usuario.inserir)">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-2">
										<button type="button" id="novo" class="btn btn-success btn-sm btn-block cor-sistema">
											<i class="fa fa-file-o"></i>
											Novo
										</button>
									</div>
								</logic:equal> --%>
							</div>
						</html:form>
					</div>
				</div>
			</div>
			<!-- TERMINO CAMPOS DE PESQUISA -->

			<!-- INICIO TABELA -->
			<logic:empty name="usuarioForm" property="usuarios">
				<div class="row" style="margin-top: 15px;">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="alert alert-warning">Nenhum registro encontrado!</div>
					</div>
				</div>
			</logic:empty>
			<logic:notEmpty name="usuarioForm" property="usuarios">
				<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.usuario.filtrar)">
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
								<thead>
									<!-- CABEÇALHO DA TABELA -->
									<tr class="cor-sistema" style="color: white;">
										<th class="text-center">#</th>
										<th>Login</th>
										<th>Email</th>
										<th>Tipo</th>
										<th>Status</th>
										<%-- <logic:equal value="true" name="usuarioSessaoPOJO" property="isADM" scope="session">
											<th>Empresa</th>
										</logic:equal> --%>
										<th class="text-center">Opções</th>
									</tr>
								</thead>
								<tbody>
									<!-- TABELA -->
									<logic:iterate id="usuarioCorrente" name="usuarioForm" property="usuarios" type="br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioDTO">

										<tr>
											<!-- success / info / warning / danger -->
											<td class="text-center" onclick="selecionar('${contextPath}/restrito/admin/usuario.src?method=selecionar&usuarioFilter.id=${usuarioCorrente.id}')">${usuarioCorrente.id}</td>
											<td onclick="selecionar('${contextPath}/restrito/admin/usuario.src?method=selecionar&usuarioFilter.id=${usuarioCorrente.id}')">${usuarioCorrente.login}</td>
											<td onclick="selecionar('${contextPath}/restrito/admin/usuario.src?method=selecionar&usuarioFilter.id=${usuarioCorrente.id}')">${usuarioCorrente.email}</td>
											<td onclick="selecionar('${contextPath}/restrito/admin/usuario.src?method=selecionar&usuarioFilter.id=${usuarioCorrente.id}')">${usuarioCorrente.tipoUsuario.nome}</td>
											<td onclick="selecionar('${contextPath}/restrito/admin/usuario.src?method=selecionar&usuarioFilter.id=${usuarioCorrente.id}')">${usuarioCorrente.statusToString}</td>

											<%-- <logic:equal value="true" name="usuarioSessaoPOJO" property="isADM" scope="session">
												<td onclick="selecionar('${contextPath}/restrito/admin/usuario.src?method=selecionar&usuarioFilter.id=${usuarioCorrente.id}')">${usuarioCorrente.empresa.razaoSocial}</td>
											</logic:equal> --%>

											<td class="text-center" style="min-width: 80px; vertical-align: middle;">
												<a href="${contextPath}/restrito/admin/usuario.src?method=selecionar&usuarioFilter.id=${usuarioCorrente.id}" class="btn btn-success btn-xs fa fa-edit" style="text-decoration: none; font-size: 14px"></a>

												<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.usuario.excluir)">
													<button type="button" id="excluir" class="btn btn-danger btn-xs fa fa-trash" style="font-size: 14px" onclick="excluir('${usuarioCorrente.email}', 'admin/usuario', ${usuarioCorrente.id} );"></button>
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
			<logic:notEmpty name="usuarioForm" property="usuarios">
				<div class="panel-footer">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">

							<nav aria-label="Page navigation">
								<ul class="pagination" style="font-size: 12px;">
									<li>
										<a href="javascript://" style="cursor: default;">Encontrados: ${usuarioForm.paginacao.totalRegistros} </a>
									</li>
									<logic:equal name="usuarioForm" property="paginacao.voltarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="usuarioForm" property="paginacao.voltarPaginacaoInativo" value="false">
										<li class="page-item">
											<a class="page-link" href="${contextPath}/restrito/admin/usuario.src?method=voltar&forwardDestino=usuarioLista" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>

									<c:forEach var="i" begin="${usuarioForm.paginacao.paginaInicial}" end="${usuarioForm.paginacao.paginaFinal}" step="1" varStatus="row">
										<c:if test="${usuarioForm.paginacao.paginaCorrente == i}">
											<li class="page-item active">
										</c:if>

										<c:if test="${usuarioForm.paginacao.paginaCorrente != i}">
											<li class="page-item ">
										</c:if>

										<a class="page-link" href="${contextPath}/restrito/admin/usuario.src?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>
										</li>
									</c:forEach>

									<logic:equal name="usuarioForm" property="paginacao.avancarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Next">
												<span aria-hidden="true">&raquo;</span>
												<span class="sr-only">Próximo</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="usuarioForm" property="paginacao.avancarPaginacaoInativo" value="false">
										<li class="page-item ">
											<a class="page-link" href="${contextPath}/restrito/admin/usuario.src?method=avancar&forwardDestino=usuarioLista" aria-label="Next">
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
		$( "#loginPesquisa" ).focus();
		
		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$( "#loginPesquisa" ).attr("maxlength",18);
		$( "#emailPesquisa" ).attr("maxlength",100);
		
		/* Setando os placeholder dos campos*/
		$( "#loginPesquisa" ).attr("placeholder", "Login a ser pesquisado");		
		$( "#emailPesquisa" ).attr("placeholder","Email a ser pesquisado");
		
		/* EVENTOS */	
		// Desliga o auto-complete da pagina
		$("#form_usuario_consulta").attr("autocomplete", "off");
		
		$('#form_usuario_consulta').on('submit', function(e){
	        abrirModalProcessando();
	    });
		
		$( '#pesquisar' ).click(function() {  	
			executar('form_usuario_consulta','filtrar');
		});
		
		$( '#limparPesquisa' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_usuario_consulta','limparFiltro');
		});
		
		$( '#novo' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_usuario_consulta','abrirCadastro');
		});
	});
	
	function selecionar(urlAction) {
		abrirModalProcessando();
		window.location = urlAction;
	}
</script>
