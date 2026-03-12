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
			Pesquisar Empresas
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Listagem das Empresas
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<div class="row">

	<!-- Define o tamanho geral da empresa -->
	<div class="col-xs-offset-0 col-sm-offset-0 col-md-offset-0 col-lg-offset-0 col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel sombra">

			<!-- INICIO CAMPOS DE PESQUISA -->
			<div class="panel-heading cor-sistema">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_empresa_consulta" action="restrito/admin/empresa" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
							<logic:equal value="true" name="usuarioSessaoPOJO" property="isADM" scope="session">
									<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<label class="text-white">Empresa</label>
										<html:select styleClass="form-control input-sm" styleId="empresa" property="empresaFilter.empresa.id" name="empresaForm">
											<html:option value="">Selecione...</html:option>
											<html:optionsCollection name="empresaForm" property="comboEmpresas" label="label" value="value" />
										</html:select>
									</div>
								</logic:equal>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<label class="text-white">Razão Social</label>
									<html:text styleClass="form-control input-sm" styleId="nomePesquisa" property="empresaFilter.razaoSocial" name="empresaForm" />
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<label class="text-white">Sistema</label>
									<html:text styleClass="form-control input-sm" styleId="nomeSistemaPesquisa" property="empresaFilter.nomeSistema" name="empresaForm" />
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<label class="text-white">Status</label>
									<html:select styleClass="form-control input-sm" styleId="estado" property="empresaFilter.ativo" name="empresaForm">
										<html:option value="">Selecione</html:option>
										<html:option value="true">Ativa</html:option>
										<html:option value="false">Inativa</html:option>										
									</html:select>
								</div>
							</div>
							<div class="row">
								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.empresa.filtrar)">
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
								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.empresa.inserir)">
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
			<logic:empty name="empresaForm" property="empresas">
				<div class="row" style="margin-top: 15px;">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="alert alert-warning">Nenhum registro encontrado!</div>
					</div>
				</div>
			</logic:empty>
			<logic:notEmpty name="empresaForm" property="empresas">
				<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.empresa.filtrar)">
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
								<thead>
									<!-- CABEÇALHO DA TABELA -->
									<tr class="cor-sistema" style="color: white;">
										<th class="text-center">#</th>
										<th>Nome</th>
										<th>Código</th>
										<th>Sistema</th>
										<th>Status</th>
										<th class="text-center">Opções</th>
									</tr>
								</thead>
								<tbody>
									<!-- TABELA -->
									<logic:iterate id="empresaCorrente" name="empresaForm" property="empresas" type="br.com.srcsoftware.modular.manager.empresa.EmpresaDTO">

										<tr>
											<!-- success / info / warning / danger -->
											<td class="text-center" onclick="selecionar('${contextPath}/restrito/admin/empresa.src?method=selecionar&empresaFilter.id=${empresaCorrente.id}')">${empresaCorrente.id}</td>
											<td onclick="selecionar('${contextPath}/restrito/admin/empresa.src?method=selecionar&empresaFilter.id=${empresaCorrente.id}')">${empresaCorrente.razaoSocial}</td>
											<td onclick="selecionar('${contextPath}/restrito/admin/empresa.src?method=selecionar&empresaFilter.id=${empresaCorrente.id}')">${empresaCorrente.codigo}</td>
											<td onclick="selecionar('${contextPath}/restrito/admin/empresa.src?method=selecionar&empresaFilter.id=${empresaCorrente.id}')">${empresaCorrente.nomeSistema}</td>
											<td onclick="selecionar('${contextPath}/restrito/admin/empresa.src?method=selecionar&empresaFilter.id=${empresaCorrente.id}')">${empresaCorrente.ativoToString}</td>
											<td class="text-center" style="min-width: 80px; vertical-align: middle;">

												<button type="button" data-container="body" data-content="<img src='${contextPath}/temp/${empresaCorrente.prefixoImagem}/${empresaCorrente.imagem}' class='img-responsive'>" class="btn btn-primary btn-xs fa fa-camera-retro popoverImagem"
													style="font-size: 14px"></button>


												<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.empresa.excluir)">
													<button type="button" id="excluir" class="btn btn-danger btn-xs fa fa-trash" style="font-size: 14px" onclick="excluir('${empresaCorrente.razaoSocial}', 'admin/empresa', ${empresaCorrente.id} );"></button>
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
			<logic:notEmpty name="empresaForm" property="empresas">
				<div class="panel-footer">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">

							<nav aria-label="Page navigation">
								<ul class="pagination" style="font-size: 12px;">
									<li>
										<a href="javascript://" style="cursor: default;">Encontrados: ${empresaForm.paginacao.totalRegistros} </a>
									</li>
									<logic:equal name="empresaForm" property="paginacao.voltarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="empresaForm" property="paginacao.voltarPaginacaoInativo" value="false">
										<li class="page-item">
											<a class="page-link" href="${contextPath}/restrito/admin/empresa.src?method=voltar&forwardDestino=empresaLista" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>

									<c:forEach var="i" begin="${empresaForm.paginacao.paginaInicial}" end="${empresaForm.paginacao.paginaFinal}" step="1" varStatus="row">
										<c:if test="${empresaForm.paginacao.paginaCorrente == i}">
											<li class="page-item active">
										</c:if>

										<c:if test="${empresaForm.paginacao.paginaCorrente != i}">
											<li class="page-item ">
										</c:if>

										<a class="page-link" href="${contextPath}/restrito/admin/empresa.src?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>
										</li>
									</c:forEach>

									<logic:equal name="empresaForm" property="paginacao.avancarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Next">
												<span aria-hidden="true">&raquo;</span>
												<span class="sr-only">Próximo</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="empresaForm" property="paginacao.avancarPaginacaoInativo" value="false">
										<li class="page-item ">
											<a class="page-link" href="${contextPath}/restrito/admin/empresa.src?method=avancar&forwardDestino=empresaLista" aria-label="Next">
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
		$( "#nomePesquisa" ).attr("maxlength",80);
		$( "#nomeSistemaPesquisa" ).attr("maxlength",80);
		
		/* Setando os placeholder dos campos*/
		$( "#nomePesquisa" ).attr("placeholder", "Nome a ser pesquisado");		
		$( "#nomeSistemaPesquisa" ).attr("placeholder", "Sistema a ser pesquisado");	
		
		/* EVENTOS */	
		// Desliga o auto-complete da pagina
		$("#form_empresa_consulta").attr("autocomplete", "off");
		
		$('#form_empresa_consulta').on('submit', function(e){
	        abrirModalProcessando();
	    });
		
		$( '#pesquisar' ).click(function() {  	
			executar('form_empresa_consulta','filtrar');
		});
		
		$( '#limparPesquisa' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_empresa_consulta','limparFiltro');
		});
		
		$( '#novo' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_empresa_consulta','abrirCadastro');
		});
		
		// Associa o evento do popover ao clicar no link.
        $(".popoverImagem").popover({ 
            trigger: "hover" ,
            html: true,
            delay: 200,
            animation: true,
            placement: "left"
           });
	});
	
	function selecionar(urlAction) {
		abrirModalProcessando();
		window.location = urlAction;
	}
</script>
