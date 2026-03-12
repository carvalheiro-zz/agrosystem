<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- CAMPOS DE FILTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -30px; margin-bottom: -15px;">
		<h1 class="page-header cabecalho_pagina">
			<i class="fa fa-object-ungroup fa-fw" style="font-size: 26px;"></i>
			Tipo de usuário
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

	<!-- Define o tamanho geral da tipoUsuario -->
	<div class="col-xs-offset-0 col-sm-offset-0 col-md-offset-1 col-lg-offset-1 col-xs-12 col-sm-12 col-md-10 col-lg-10">
		<div class="panel sombra">

			<!-- INICIO CAMPOS DE PESQUISA -->
			<div class="panel-heading cor-sistema">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_tipoUsuario_consulta" action="restrito/admin/tipoUsuario" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<%-- <logic:equal value="true" name="usuarioSessaoPOJO" property="isADM" scope="session">
									<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<label class="text-white">Empresa</label>
										<html:select styleClass="form-control input-sm" styleId="empresa" property="tipoUsuarioFilter.empresa.id" name="tipoUsuarioForm">
											<html:option value="">Selecione...</html:option>
											<html:optionsCollection name="tipoUsuarioForm" property="comboEmpresas" label="label" value="value" />
										</html:select>
									</div>
								</logic:equal> --%>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<label class="text-white">Nome</label>
									<html:text styleClass="form-control input-sm" styleId="nomePesquisa" property="tipoUsuarioFilter.nome" name="tipoUsuarioForm" />
								</div>


							</div>
							<div class="row">
								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.tipoUsuario.filtrar)">
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
								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.tipoUsuario.inserir)">
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-2">
										<button type="button" id="novo" class="btn btn-success btn-sm btn-block cor-sistema">
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
			<logic:empty name="tipoUsuarioForm" property="tiposUsuarios">
				<div class="row" style="margin-top: 15px;">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="alert alert-warning">Nenhum registro encontrado!</div>
					</div>
				</div>
			</logic:empty>
			<logic:notEmpty name="tipoUsuarioForm" property="tiposUsuarios">
				<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.tipoUsuario.filtrar)">
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
								<thead>
									<!-- CABEÇALHO DA TABELA -->
									<tr class="cor-sistema" style="color: white;">
										<th class="text-center">#</th>
										<th>Nome</th>
										<%-- <logic:equal value="true" name="usuarioSessaoPOJO" property="isADM" scope="session">
											<th>Empresa</th>
										</logic:equal> --%>
										<th class="text-center">Opções</th>
									</tr>
								</thead>
								<tbody>
									<!-- TABELA -->
									<logic:iterate id="tipoUsuarioCorrente" name="tipoUsuarioForm" property="tiposUsuarios" type="br.com.srcsoftware.modular.manager.usuario.tipousuario.TipoUsuarioDTO">

										<tr>
											<!-- success / info / warning / danger -->
											<td class="text-center" onclick="selecionar('${contextPath}/restrito/admin/tipoUsuario.src?method=selecionar&tipoUsuarioFilter.id=${tipoUsuarioCorrente.id}')">${tipoUsuarioCorrente.id}</td>
											<td onclick="selecionar('${contextPath}/restrito/admin/tipoUsuario.src?method=selecionar&tipoUsuarioFilter.id=${tipoUsuarioCorrente.id}')">${tipoUsuarioCorrente.nome}</td>
											<%-- <logic:equal value="true" name="usuarioSessaoPOJO" property="isADM" scope="session">
												<td onclick="selecionar('${contextPath}/restrito/admin/tipoUsuario.src?method=selecionar&tipoUsuarioFilter.id=${tipoUsuarioCorrente.id}')">${tipoUsuarioCorrente.empresa.razaoSocial}</td>
											</logic:equal> --%>

											<td class="text-center" style="min-width: 80px; vertical-align: middle;">
												<a href="${contextPath}/restrito/admin/tipoUsuario.src?method=selecionar&tipoUsuarioFilter.id=${tipoUsuarioCorrente.id}" class="btn btn-success btn-xs fa fa-edit" style="text-decoration: none; font-size: 14px"></a>

												<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.tipoUsuario.excluir)">
													<button type="button" id="excluir" class="btn btn-danger btn-xs fa fa-trash" style="font-size: 14px"
														onclick="excluir('${tipoUsuarioCorrente.nome} - Empresa: ${tipoUsuarioCorrente.empresa.razaoSocial}', 'admin/tipoUsuario', ${tipoUsuarioCorrente.id} );"></button>
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
			<logic:notEmpty name="tipoUsuarioForm" property="tiposUsuarios">
				<div class="panel-footer">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">

							<nav aria-label="Page navigation">
								<ul class="pagination" style="font-size: 12px;">
									<li>
										<a href="javascript://" style="cursor: default;">Encontrados: ${tipoUsuarioForm.paginacao.totalRegistros} </a>
									</li>
									<logic:equal name="tipoUsuarioForm" property="paginacao.voltarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="tipoUsuarioForm" property="paginacao.voltarPaginacaoInativo" value="false">
										<li class="page-item">
											<a class="page-link" href="${contextPath}/restrito/admin/tipoUsuario.src?method=voltar&forwardDestino=tipoUsuarioLista" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>

									<c:forEach var="i" begin="${tipoUsuarioForm.paginacao.paginaInicial}" end="${tipoUsuarioForm.paginacao.paginaFinal}" step="1" varStatus="row">
										<c:if test="${tipoUsuarioForm.paginacao.paginaCorrente == i}">
											<li class="page-item active">
										</c:if>

										<c:if test="${tipoUsuarioForm.paginacao.paginaCorrente != i}">
											<li class="page-item ">
										</c:if>

										<a class="page-link" href="${contextPath}/restrito/admin/tipoUsuario.src?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>
										</li>
									</c:forEach>

									<logic:equal name="tipoUsuarioForm" property="paginacao.avancarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Next">
												<span aria-hidden="true">&raquo;</span>
												<span class="sr-only">Próximo</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="tipoUsuarioForm" property="paginacao.avancarPaginacaoInativo" value="false">
										<li class="page-item ">
											<a class="page-link" href="${contextPath}/restrito/admin/tipoUsuario.src?method=avancar&forwardDestino=tipoUsuarioLista" aria-label="Next">
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
		$( "#nomePesquisa" ).focus();

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$( "#nomePesquisa" ).attr("maxlength",50);

		/* Setando os placeholder dos campos*/
		$( "#nomePesquisa" ).attr("placeholder","Nome");

		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_tipoUsuario_consulta").attr("autocomplete", "off");
		
		$('#form_tipoUsuario_consulta').on('submit', function(e){
	        abrirModalProcessando();
	    });
		
		$( '#pesquisar' ).click(function() {  	
			executar('form_tipoUsuario_consulta','filtrar');
		});
		
		$( '#limparPesquisa' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_tipoUsuario_consulta','limparFiltro');
		});
		
		$( '#novo' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_tipoUsuario_consulta','abrirCadastro');
		});
	});
	
	function selecionar(urlAction) {
		abrirModalProcessando();
		window.location = urlAction;
	}
</script>


