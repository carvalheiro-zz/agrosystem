<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!-- CAMPOS DE FILTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -30px; margin-bottom: -15px;">
		<h1 class="page-header cabecalho_pagina">
			<i class="fa fa-users" style="font-size: 26px;"></i>
			Colaborador
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
						<html:form styleId="form_funcionario_consulta" action="restrito/admin/funcionario" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<%-- <logic:equal value="true" name="usuarioSessaoPOJO" property="isADM" scope="session">
									<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-4">
										<label class="text-white">Empresa</label>
										<html:select styleClass="form-control input-sm" styleId="empresa" property="funcionarioFilter.empresa.id" name="funcionarioForm">
											<html:option value="">Selecione...</html:option>
											<html:optionsCollection name="funcionarioForm" property="comboEmpresas" label="label" value="value" />
										</html:select>
									</div>
								</logic:equal> --%>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<label class="text-white">Nome</label>
									<html:text styleClass="form-control input-sm" styleId="razaoSocialPesquisa" property="funcionarioFilter.pessoaFisica.razaoSocial" name="funcionarioForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<label class="text-white">CPF</label>
									<html:text styleClass="form-control cpf input-sm text-center" styleId="cpfPesquisa" property="funcionarioFilter.pessoaFisica.cpf" name="funcionarioForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<label class="text-white">RG</label>
									<html:text styleClass="form-control input-sm" styleId="rgPesquisa" property="funcionarioFilter.pessoaFisica.rg" name="funcionarioForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<label class="text-white">E-mail</label>
									<html:text styleClass="form-control input-sm" styleId="emailPesquisa" property="funcionarioFilter.pessoaFisica.email" name="funcionarioForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
									<label class="text-white">Fixo</label>
									<html:text styleClass="form-control fixo input-sm text-center" styleId="telefone3Pesquisa" property="funcionarioFilter.pessoaFisica.telefone3" name="funcionarioForm" />
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
									<label class="text-white">Celular</label>
									<html:text styleClass="form-control cel input-sm text-center" styleId="telefone1Pesquisa" property="funcionarioFilter.pessoaFisica.telefone1" name="funcionarioForm" />
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
									<label class="text-white">Status</label>
									<html:select styleClass="form-control input-sm" styleId="ativoPesquisa" property="funcionarioFilter.ativo" name="funcionarioForm">
										<html:option value="">Selecione</html:option>
										<html:option value="true">Ativo</html:option>
										<html:option value="false">Inativo</html:option>
									</html:select>
								</div>
							</div>
							<div class="row">
								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.funcionario.filtrar)">
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-2" style="margin-bottom: 0px;">
										<button type="submit" id="pesquisar" class="btn btn-success btn-sm btn-block cor-sistema">
											<i class="fa fa-search"></i>
											Pesquisar
										</button>
									</div>
								</logic:equal>

								<div class="col-xs-12 col-sm-12 col-md-4 col-lg-2" style="margin-bottom: 0px;">
									<button type="button" id="limparPesquisa" class="btn btn-success btn-sm btn-block cor-sistema">
										<i class="glyphicon glyphicon-erase"></i>
										Limpar
									</button>
								</div>
								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.funcionario.inserir)">
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-2" style="margin-bottom: 0px;">
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
			<logic:empty name="funcionarioForm" property="funcionarios">
				<div class="row" style="margin-top: 15px;">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="alert alert-warning">Nenhum registro encontrado!</div>
					</div>
				</div>
			</logic:empty>
			<logic:notEmpty name="funcionarioForm" property="funcionarios">
				<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.funcionario.filtrar)">
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
								<thead>
									<!-- CABEÇALHO DA TABELA -->
									<tr class="cor-sistema" style="color: white;">
										<th class="text-center">Código</th>
										<th>Nome</th>
										<th>CPF</th>
										<th>RG</th>
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
									<logic:iterate id="funcionarioCorrente" name="funcionarioForm" property="funcionarios" type="br.com.srcsoftware.modular.manager.pessoa.funcionario.FuncionarioDTO">

										<logic:equal name="funcionarioCorrente" value="true" property="ativo">
											<tr>
										</logic:equal>
										<logic:equal name="funcionarioCorrente" value="false" property="ativo">
											<tr class="danger">
										</logic:equal>
										<!-- success / info / warning / danger -->
										<td class="text-center" onclick="selecionar('${contextPath}/restrito/admin/funcionario.src?method=selecionar&funcionarioFilter.id=${funcionarioCorrente.id}')">${funcionarioCorrente.codigo}</td>
										<td onclick="selecionar('${contextPath}/restrito/admin/funcionario.src?method=selecionar&funcionarioFilter.id=${funcionarioCorrente.id}')">${funcionarioCorrente.pessoaFisica.razaoSocial}</td>
										<td onclick="selecionar('${contextPath}/restrito/admin/funcionario.src?method=selecionar&funcionarioFilter.id=${funcionarioCorrente.id}')">${funcionarioCorrente.pessoaFisica.cpf}</td>
										<td onclick="selecionar('${contextPath}/restrito/admin/funcionario.src?method=selecionar&funcionarioFilter.id=${funcionarioCorrente.id}')">${funcionarioCorrente.pessoaFisica.rg}</td>
										<td onclick="selecionar('${contextPath}/restrito/admin/funcionario.src?method=selecionar&funcionarioFilter.id=${funcionarioCorrente.id}')">${funcionarioCorrente.pessoaFisica.email}</td>
										<td onclick="selecionar('${contextPath}/restrito/admin/funcionario.src?method=selecionar&funcionarioFilter.id=${funcionarioCorrente.id}')">${funcionarioCorrente.pessoaFisica.telefoneToString}</td>
										<%-- <logic:equal value="true" name="usuarioSessaoPOJO" property="isADM" scope="session">
											<td onclick="selecionar('${contextPath}/restrito/admin/funcionario.src?method=selecionar&funcionarioFilter.id=${funcionarioCorrente.id}')">${funcionarioCorrente.empresa.razaoSocial}</td>
										</logic:equal> --%>

										<td class="text-center" style="min-width: 80px; vertical-align: middle;">

											<button type="button" data-container="body" data-content="<img src='${contextPath}/temp/${funcionarioCorrente.pessoaFisica.prefixoImagem}/${funcionarioCorrente.pessoaFisica.imagem}' class='img-responsive'>"
												class="btn btn-primary btn-xs fa fa-camera-retro popoverImagem" style="font-size: 14px"></button>

											<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.funcionario.excluir)">
												<button type="button" id="excluir" class="btn btn-danger btn-xs fa fa-trash" style="font-size: 14px" onclick="excluir('${funcionarioCorrente.pessoaFisica.razaoSocial}', 'admin/funcionario', ${funcionarioCorrente.id} );"></button>
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
			<logic:notEmpty name="funcionarioForm" property="funcionarios">
				<div class="panel-footer">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">

							<nav aria-label="Page navigation">
								<ul class="pagination" style="font-size: 12px;">
									<li>
										<a href="javascript://" style="cursor: default;">Encontrados: ${funcionarioForm.paginacao.totalRegistros} </a>
									</li>
									<logic:equal name="funcionarioForm" property="paginacao.voltarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="funcionarioForm" property="paginacao.voltarPaginacaoInativo" value="false">
										<li class="page-item">
											<a class="page-link" href="${contextPath}/restrito/admin/funcionario.src?method=voltar&forwardDestino=funcionarioLista" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>

									<c:forEach var="i" begin="${funcionarioForm.paginacao.paginaInicial}" end="${funcionarioForm.paginacao.paginaFinal}" step="1" varStatus="row">
										<c:if test="${funcionarioForm.paginacao.paginaCorrente == i}">
											<li class="page-item active">
										</c:if>

										<c:if test="${funcionarioForm.paginacao.paginaCorrente != i}">
											<li class="page-item ">
										</c:if>

										<a class="page-link" href="${contextPath}/restrito/admin/funcionario.src?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>
										</li>
									</c:forEach>

									<logic:equal name="funcionarioForm" property="paginacao.avancarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Next">
												<span aria-hidden="true">&raquo;</span>
												<span class="sr-only">Próximo</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="funcionarioForm" property="paginacao.avancarPaginacaoInativo" value="false">
										<li class="page-item ">
											<a class="page-link" href="${contextPath}/restrito/admin/funcionario.src?method=avancar&forwardDestino=funcionarioLista" aria-label="Next">
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
		$("#cpfPesquisa").attr("maxlength", 14);
		$("#rgPesquisa").attr("maxlength", 50);
		$("#emailPesquisa").attr("maxlength", 50);
		$("#sexoPesquisa").attr("maxlength", 9);

		/* Setando os placeholder dos campos*/
		$( "#razaoSocialPesquisa" ).attr("placeholder","Nome a ser pesquisado");
		$( "#cpfPesquisa" ).attr("placeholder","CPF a ser pesquisado");
		$( "#rgPesquisa" ).attr("placeholder","RG a ser pesquisado");
		$( "#emailPesquisa" ).attr("placeholder","E-mail a ser pesquisado");
		$( "#sexoPesquisa" ).attr("placeholder","Sexo a ser pesquisado");
		
		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_funcionario_consulta").attr("autocomplete", "off");
		
		$('#form_funcionario_consulta').on('submit', function(e){
	        abrirModalProcessando();
	    });
		
		$( '#pesquisar' ).click(function() {  	
			executar('form_funcionario_consulta','filtrar');
		});
		
		$( '#limparPesquisa' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_funcionario_consulta','limparFiltro');
		});
		
		$( '#novo' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_funcionario_consulta','abrirCadastro');
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


