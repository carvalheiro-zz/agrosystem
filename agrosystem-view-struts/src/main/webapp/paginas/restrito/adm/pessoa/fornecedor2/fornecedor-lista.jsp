<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!-- CAMPOS DE FILTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -30px; margin-bottom: -15px;">
		<h1 class="page-header cabecalho_pagina">
			<i class="fa fa-building-o" style="font-size: 26px;"></i>
			Fornecedor
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Pesquisa
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<div class="row">

	<!-- Define o tamanho geral da tela -->
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel" style="box-shadow: 0 10px 30px rgba(0, 0, 0, 0.8); border-color: #909090;">

			<!-- INICIO CAMPOS DE PESQUISA -->
			<div class="panel-heading cor-sistema">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_fornecedor_consulta" action="restrito/admin/fornecedor" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<%-- <logic:equal value="true" name="usuarioSessaoPOJO" property="isADM" scope="session">
									<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-4">
										<label class="text-white">Empresa</label>
										<html:select styleClass="form-control input-sm" styleId="empresa" property="fornecedorFilter.empresa.id" name="fornecedorForm">
											<html:option value="">Selecione...</html:option>
											<html:optionsCollection name="fornecedorForm" property="comboEmpresas" label="label" value="value" />
										</html:select>
									</div>
								</logic:equal> --%>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<label class="text-white">Razão Social</label>
									<html:text styleClass="form-control input-sm" styleId="razaoSocialPesquisa" property="fornecedorFilter.pessoaJuridica.razaoSocial" name="fornecedorForm" />
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<label class="text-white">Nome Fantasia</label>
									<html:text styleClass="form-control input-sm" styleId="nomeFantasiaPesquisa" property="fornecedorFilter.pessoaJuridica.nomeFantasia" name="fornecedorForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<label class="text-white">CNPJ</label>
									<html:text styleClass="form-control cnpj input-sm text-center" styleId="cnpjPesquisa" property="fornecedorFilter.pessoaJuridica.cnpj" name="fornecedorForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<label class="text-white">Cidade</label>
									<html:text styleClass="form-control input-sm" styleId="cidadePesquisa" property="fornecedorFilter.pessoaJuridica.endereco.cidade" name="fornecedorForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<label class="text-white">E-mail</label>
									<html:text styleClass="form-control input-sm" styleId="emailRepresentantePesquisa" property="fornecedorFilter.pessoaJuridica.emailRepresentante" name="fornecedorForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-4">
									<label class="text-white">Contato</label>
									<html:text styleClass="form-control cel input-sm text-center" styleId="representantePesquisa" property="fornecedorFilter.pessoaJuridica.representante" name="fornecedorForm" />
								</div>

							</div>
							<div class="row">
								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.fornecedor.filtrar)">
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
								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.fornecedor.inserir)">
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
			<logic:empty name="fornecedorForm" property="fornecedores">
				<div class="row" style="margin-top: 15px;">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="alert alert-warning">Nenhum registro encontrado!</div>
					</div>
				</div>
			</logic:empty>
			<logic:notEmpty name="fornecedorForm" property="fornecedores">
				<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.fornecedor.filtrar)">
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
								<thead>
									<!-- CABEÇALHO DA TABELA -->
									<tr class="cor-sistema" style="color: white;">										
										<th>Nome Fantasia</th>
										<th>Cidade</th>
										<th>Email</th>
										<th>Contato</th>
										<th>Representante</th>
										<th class="text-center" style="width: 80px;">Opções</th>
									</tr>
								</thead>
								<tbody>
									<!-- TABELA -->
									<logic:iterate id="fornecedorCorrente" name="fornecedorForm" property="fornecedores" type="br.com.srcsoftware.modular.manager.pessoa.fornecedor.FornecedorDTO">

										<tr>
											<!-- success / info / warning / danger -->
											<td onclick="selecionar('${contextPath}/restrito/admin/fornecedor.src?method=selecionar&fornecedorFilter.id=${fornecedorCorrente.id}')">
											${fornecedorCorrente.pessoaJuridica.nomeFantasia}</td>
											<td onclick="selecionar('${contextPath}/restrito/admin/fornecedor.src?method=selecionar&fornecedorFilter.id=${fornecedorCorrente.id}')">
											${fornecedorCorrente.pessoaJuridica.endereco.cidade}</td>
											<td onclick="selecionar('${contextPath}/restrito/admin/fornecedor.src?method=selecionar&fornecedorFilter.id=${fornecedorCorrente.id}')">
											${fornecedorCorrente.pessoaJuridica.email}</td>
											<td onclick="selecionar('${contextPath}/restrito/admin/fornecedor.src?method=selecionar&fornecedorFilter.id=${fornecedorCorrente.id}')">
											${fornecedorCorrente.pessoaJuridica.telefoneToString}</td>
											<td onclick="selecionar('${contextPath}/restrito/admin/fornecedor.src?method=selecionar&fornecedorFilter.id=${fornecedorCorrente.id}')">
											${fornecedorCorrente.pessoaJuridica.representante}</td>
																						
											<td class="text-center" style="min-width: 80px; vertical-align: middle;">
												<button type="button" data-container="body" data-content="<img src='${contextPath}/temp/${fornecedorCorrente.pessoaJuridica.prefixoImagem}/${fornecedorCorrente.pessoaJuridica.imagem}' class='img-responsive'>"
													class="btn btn-primary btn-xs fa fa-camera-retro popoverImagem" style="font-size: 14px"></button>

												<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.fornecedor.excluir)">
													<button type="button" id="excluir" class="btn btn-danger btn-xs fa fa-trash" style="font-size: 14px" onclick="excluir('${fornecedorCorrente.pessoaJuridica.razaoSocial}', 'admin/fornecedor', ${fornecedorCorrente.id} );"></button>
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
			<logic:notEmpty name="fornecedorForm" property="fornecedores">
				<div class="panel-footer">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">

							<nav aria-label="Page navigation">
								<ul class="pagination" style="font-size: 12px;">
									<li>
										<a href="javascript://" style="cursor: default;">Encontrados: ${fornecedorForm.paginacao.totalRegistros} </a>
									</li>
									<logic:equal name="fornecedorForm" property="paginacao.voltarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="fornecedorForm" property="paginacao.voltarPaginacaoInativo" value="false">
										<li class="page-item">
											<a class="page-link" href="${contextPath}/restrito/admin/fornecedor.src?method=voltar&forwardDestino=fornecedorLista" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>

									<c:forEach var="i" begin="${fornecedorForm.paginacao.paginaInicial}" end="${fornecedorForm.paginacao.paginaFinal}" step="1" varStatus="row">
										<c:if test="${fornecedorForm.paginacao.paginaCorrente == i}">
											<li class="page-item active">
										</c:if>

										<c:if test="${fornecedorForm.paginacao.paginaCorrente != i}">
											<li class="page-item ">
										</c:if>

										<a class="page-link" href="${contextPath}/restrito/admin/fornecedor.src?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>
										</li>
									</c:forEach>

									<logic:equal name="fornecedorForm" property="paginacao.avancarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Next">
												<span aria-hidden="true">&raquo;</span>
												<span class="sr-only">Próximo</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="fornecedorForm" property="paginacao.avancarPaginacaoInativo" value="false">
										<li class="page-item ">
											<a class="page-link" href="${contextPath}/restrito/admin/fornecedor.src?method=avancar&forwardDestino=fornecedorLista" aria-label="Next">
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
		$( "#razaoSocialPesquisa" ).focus();

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#razaoSocialPesquisa").attr("maxlength", 70);
		$("#nomeFantasiaPesquisa").attr("maxlength", 100);
		$("#cnpjPesquisa").attr("maxlength", 18);
		$("#cidadePesquisa").attr("maxlength", 50);
		$("#emailRepresentantePesquisa").attr("maxlength", 50);
		$("#representantePesquisa").attr("maxlength", 20);

		/* Setando os placeholder dos campos*/
		$( "#razaoSocialPesquisa" ).attr("placeholder","Nome a ser pesquisado");
		$( "#nomeFantasiaPesquisa" ).attr("placeholder","Nome fantasia a ser pesquisado");
		$( "#cnpjPesquisa" ).attr("placeholder","CNPJ a ser pesquisado");
		$( "#cidadePesquisa" ).attr("placeholder","Cidade a ser pesquisado");
		$( "#emailRepresentantePesquisa" ).attr("placeholder","E-mail a ser pesquisado");
		$( "#representantePesquisa" ).attr("placeholder","Telefone a ser pesquisado");
		
		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_fornecedor_consulta").attr("autocomplete", "off");
		
		$('#form_fornecedor_consulta').on('submit', function(e){
	        abrirModalProcessando();
	    });
		
		$( '#pesquisar' ).click(function() {  	
			executar('form_fornecedor_consulta','filtrar');
		});
		
		$( '#limparPesquisa' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_fornecedor_consulta','limparFiltro');
		});
		
		$( '#novo' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_fornecedor_consulta','abrirCadastro');
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


