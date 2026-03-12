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
			Pesquisar Veiculos
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Listagem dos Veiculos
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
						<html:form styleId="form_veiculo_consulta" action="restrito/sistema/veiculo" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
									<label class="text-white">Código</label>
									<html:text styleClass="form-control input-sm" styleId="codigo" property="veiculo.codigo" name="veiculoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-9 col-lg-10">
									<label class="text-white">Nome</label>
									<html:text styleClass="form-control input-sm" styleId="nome" property="veiculo.nome" name="veiculoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<label class="text-white">Modelo</label>
									<html:text styleClass="form-control input-sm" styleId="modelo" property="veiculo.modelo" name="veiculoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<label class="text-white">Tipo</label>
									<html:select styleClass="form-control input-sm" styleId="tipo" property="veiculo.tipo" name="veiculoForm">
										<html:option value="">Selecione...</html:option>
										<html:option value="Veiculo">Veiculo</html:option>
										<html:option value="Maquina">Maquina</html:option>
									</html:select>
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<label class="text-white">Ano Fabricação</label>
									<html:text styleClass="form-control input-sm" styleId="anoFabricacao" property="veiculo.anoFabricacao" name="veiculoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<label class="text-white">Nº Chassis</label>
									<html:text styleClass="form-control input-sm" styleId="numeroChassis" property="veiculo.numeroChassis" name="veiculoForm" />
								</div>

							</div>
							<div class="row">

								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.veiculo.filtrar)">
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
								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.veiculo.inserir)">
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
			<logic:empty name="veiculoForm" property="veiculos">
				<div class="row" style="margin-top: 15px;">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="alert alert-warning">Nenhum registro encontrado!</div>
					</div>
				</div>
			</logic:empty>
			<logic:notEmpty name="veiculoForm" property="veiculos">
				<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.veiculo.filtrar)">
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
								<thead>
									<!-- CABEÇALHO DA TABELA -->
									<tr class="cor-sistema" style="color: white;">
										<th class="text-center">Código</th>
										<th>Nome</th>
										<th>Modelo</th>
										<th>Tipo</th>
										<th>Fabricação</th>
										<th>Chassis</th>
										<th class="text-center" style="width: 80px;">Opções</th>
									</tr>
								</thead>
								<tbody>
									<!-- TABELA -->
									<logic:iterate id="veiculoCorrente" name="veiculoForm" property="veiculos" type="br.com.srcsoftware.sistema.manutencao.veiculo.VeiculoDTO">

										<tr>
											<!-- success / info / warning / danger -->
											<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/veiculo.src?method=selecionar&veiculo.id=${veiculoCorrente.id}')">${veiculoCorrente.codigo}</td>
											<td onclick="selecionar('${contextPath}/restrito/sistema/veiculo.src?method=selecionar&veiculo.id=${veiculoCorrente.id}')">${veiculoCorrente.nome}</td>
											<td onclick="selecionar('${contextPath}/restrito/sistema/veiculo.src?method=selecionar&veiculo.id=${veiculoCorrente.id}')">${veiculoCorrente.modelo}</td>
											<td onclick="selecionar('${contextPath}/restrito/sistema/veiculo.src?method=selecionar&veiculo.id=${veiculoCorrente.id}')">${veiculoCorrente.tipo}</td>
											<td onclick="selecionar('${contextPath}/restrito/sistema/veiculo.src?method=selecionar&veiculo.id=${veiculoCorrente.id}')">${veiculoCorrente.anoFabricacao}</td>
											<td onclick="selecionar('${contextPath}/restrito/sistema/veiculo.src?method=selecionar&veiculo.id=${veiculoCorrente.id}')">${veiculoCorrente.numeroChassis}</td>

											<td class="text-center" style="min-width: 80px; vertical-align: middle;">
												<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.veiculo.excluir)">
													<button type="button" id="excluir" class="btn btn-danger btn-xs fa fa-trash" style="font-size: 14px" onclick="excluir('${veiculoCorrente.nome}', 'sistema/veiculo', ${veiculoCorrente.id} );"></button>
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
			<logic:notEmpty name="veiculoForm" property="veiculos">
				<div class="panel-footer">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">

							<nav aria-label="Page navigation">
								<ul class="pagination" style="font-size: 12px;">
									<li>
										<a href="javascript://" style="cursor: default;">Encontrados: ${veiculoForm.paginacao.totalRegistros} </a>
									</li>
									<logic:equal name="veiculoForm" property="paginacao.voltarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="veiculoForm" property="paginacao.voltarPaginacaoInativo" value="false">
										<li class="page-item">
											<a class="page-link" href="${contextPath}/restrito/sistema/veiculo.src?method=voltar&forwardDestino=veiculoLista" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>

									<c:forEach var="i" begin="${veiculoForm.paginacao.paginaInicial}" end="${veiculoForm.paginacao.paginaFinal}" step="1" varStatus="row">
										<c:if test="${veiculoForm.paginacao.paginaCorrente == i}">
											<li class="page-item active">
										</c:if>

										<c:if test="${veiculoForm.paginacao.paginaCorrente != i}">
											<li class="page-item ">
										</c:if>

										<a class="page-link" href="${contextPath}/restrito/sistema/veiculo.src?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>
										</li>
									</c:forEach>

									<logic:equal name="veiculoForm" property="paginacao.avancarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Next">
												<span aria-hidden="true">&raquo;</span>
												<span class="sr-only">Próximo</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="veiculoForm" property="paginacao.avancarPaginacaoInativo" value="false">
										<li class="page-item ">
											<a class="page-link" href="${contextPath}/restrito/sistema/veiculo.src?method=avancar&forwardDestino=veiculoLista" aria-label="Next">
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

		$("#codigo").prop("maxlength", 10);
		$("#nome").prop("maxlength", 50);
		$("#modelo").prop("maxlength", 20);
		$("#anoFabricacao").prop("maxlength", 4);
		$("#numeroChassis").prop("maxlength", 20);

		/* Setando os placeholder dos campos*/
		$( "#codigo" ).prop("placeholder","Código a ser pesquisado");
		$( "#nome" ).prop("placeholder","Nome a ser pesquisado");
		$( "#modelo" ).prop("placeholder","Modelo a ser pesquisado");
		$( "#anoFabricacao" ).prop("placeholder","Fabricação a ser pesquisada");
		$( "#numeroChassis" ).prop("placeholder","Chassis a ser pesquisado");
		
		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_veiculo_consulta").prop("autocomplete", "off");
		
		$('#form_veiculo_consulta').on('submit', function(e){
	        abrirModalProcessando();
	    });
		
		$( '#pesquisar' ).click(function() {  	
			executar('form_veiculo_consulta','filtrar');
		});
		
		$( '#limparPesquisa' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_veiculo_consulta','limparFiltro');
		});
		
		$( '#novo' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_veiculo_consulta','abrirCadastro');
		});
	});
	
	function selecionar(urlAction) {
		abrirModalProcessando();
		window.location = urlAction;
	}
</script>