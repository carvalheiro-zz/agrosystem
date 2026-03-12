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
			Forma de pagamento
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
						<html:form styleId="form_formaPagamento_consulta" action="restrito/sistema/formaPagamento" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<label class="text-white">Tipo</label>
									<html:select styleClass="form-control input-sm" styleId="tipo" property="formaPagamentoFilter.tipo" name="formaPagamentoForm">
										<html:option value="">Selecione...</html:option>
										<html:option value="Receita">Receita</html:option>
										<html:option value="Despesa">Despesa</html:option>
									</html:select>
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<label class="text-white">Nome</label>
									<html:select styleClass="form-control input-sm" styleId="nome" property="formaPagamentoFilter.tipoFormaPagamento.id" name="formaPagamentoForm">
										<html:option value="">Selecione...</html:option>
										<html:optionsCollection name="formaPagamentoForm" property="comboTipoFormaPagamento(${usuarioSessaoPOJO.isADM})" label="label" value="value" />
									</html:select>
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<label class="text-white">Descritivo</label>
									<html:text styleClass="form-control input-sm" styleId="especificacao" property="formaPagamentoFilter.especificacao" name="formaPagamentoForm" />
								</div>

							</div>	
													
							<div class="row row-botoes">
								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.formaPagamento.filtrar)">
									<div class="col-xs-12 col-sm-3 col-md-4 col-lg-2">
										<button type="submit" id="pesquisar" class="btn btn-success btn-sm cor-sistema btn-block">
											<i class="fa fa-search"></i>
											Pesquisar
										</button>
									</div>
								</logic:equal>
								<div class="col-xs-12 col-sm-3 col-md-4 col-lg-2">
									<button type="button" id="limparPesquisa" class="btn btn-success btn-sm cor-sistema btn-block">
										<i class="glyphicon glyphicon-erase"></i>
										Limpar
									</button>
								</div>
								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.formaPagamento.inserir)">
									<div class="col-xs-12 col-sm-3 col-md-4 col-lg-2">
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
			<logic:empty name="formaPagamentoForm" property="formaPagamentos">
				<div class="row" style="margin-top: 15px;">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="alert alert-warning">Nenhum registro encontrado!</div>
					</div>
				</div>
			</logic:empty>
			<logic:notEmpty name="formaPagamentoForm" property="formaPagamentos">
				<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.formaPagamento.filtrar)">
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
								<thead>
									<!-- CABEÇALHO DA TABELA -->
									<tr class="cor-sistema" style="color: white;">
										<th>Tipo</th>
										<th>Forma de Pagamento</th>
										<th>Nome</th>
										<th>Descritivo</th>
										<th>Parcelas</th>
										<th>Status</th>

										<th>% Taxa</th>
										<th>R$ Taxa</th>
										<th>Dias compensação</th>

										<th class="text-center" style="width: 80px;">Opções</th>
									</tr>
								</thead>
								<tbody>
									<!-- TABELA -->
									<logic:iterate id="formaPagamentoCorrente" name="formaPagamentoForm" property="formaPagamentos"
										type="br.com.srcsoftware.modular.financeiro.formapagamento.FormaPagamentoDTO">

										<tr>
											<!-- success / info / warning / danger -->
											<td onclick="selecionar('${contextPath}/restrito/sistema/formaPagamento.src?method=selecionar&formaPagamentoFilter.id=${formaPagamentoCorrente.id}')">${formaPagamentoCorrente.tipo}</td>
											
											<td onclick="selecionar('${contextPath}/restrito/sistema/formaPagamento.src?method=selecionar&formaPagamentoFilter.id=${formaPagamentoCorrente.id}')">${formaPagamentoCorrente.nomeCompleto}</td>
											<td onclick="selecionar('${contextPath}/restrito/sistema/formaPagamento.src?method=selecionar&formaPagamentoFilter.id=${formaPagamentoCorrente.id}')">${formaPagamentoCorrente.tipoFormaPagamento.nome}</td>
											<td onclick="selecionar('${contextPath}/restrito/sistema/formaPagamento.src?method=selecionar&formaPagamentoFilter.id=${formaPagamentoCorrente.id}')">${formaPagamentoCorrente.especificacao}</td>
											<td onclick="selecionar('${contextPath}/restrito/sistema/formaPagamento.src?method=selecionar&formaPagamentoFilter.id=${formaPagamentoCorrente.id}')">${formaPagamentoCorrente.parcelasToString}</td>
											<td onclick="selecionar('${contextPath}/restrito/sistema/formaPagamento.src?method=selecionar&formaPagamentoFilter.id=${formaPagamentoCorrente.id}')">${formaPagamentoCorrente.gerarQuitadaToString}</td>

											<%-- <logic:equal value="Cartão" name="formaPagamentoCorrente" property="tipoFormaPagamento.nome"> --%>
												<td onclick="selecionar('${contextPath}/restrito/sistema/formaPagamento.src?method=selecionar&formaPagamentoFilter.id=${formaPagamentoCorrente.id}')">${formaPagamentoCorrente.percentualTaxa}</td>
												<td onclick="selecionar('${contextPath}/restrito/sistema/formaPagamento.src?method=selecionar&formaPagamentoFilter.id=${formaPagamentoCorrente.id}')">${formaPagamentoCorrente.valorTaxa}</td>
												<td onclick="selecionar('${contextPath}/restrito/sistema/formaPagamento.src?method=selecionar&formaPagamentoFilter.id=${formaPagamentoCorrente.id}')">${formaPagamentoCorrente.diasCompensacao}</td>
											<%-- </logic:equal> --%>
											<%-- <logic:notEqual value="Cartão" name="formaPagamentoCorrente" property="tipoFormaPagamento.nome">
												<td onclick="selecionar('${contextPath}/restrito/sistema/formaPagamento.src?method=selecionar&formaPagamentoFilter.id=${formaPagamentoCorrente.id}')" class="bloqueado"></td>
												<td onclick="selecionar('${contextPath}/restrito/sistema/formaPagamento.src?method=selecionar&formaPagamentoFilter.id=${formaPagamentoCorrente.id}')" class="bloqueado"></td>
											</logic:notEqual> --%>

											<td class="text-center" style="min-width: 80px; vertical-align: middle;">
												<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.formaPagamento.excluir)">
													<button type="button" id="excluir" class="btn btn-danger btn-xs fa fa-trash" style="font-size: 14px"
														onclick="excluir('${formaPagamentoCorrente.nomeCompleto}', 'sistema/formaPagamento', ${formaPagamentoCorrente.id} );"></button>
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
			<logic:notEmpty name="formaPagamentoForm" property="formaPagamentos">
				<div class="panel-footer">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">

							<nav aria-label="Page navigation">
								<ul class="pagination" style="font-size: 12px;">
									<li>
										<a href="javascript://" style="cursor: default;">Encontrados: ${formaPagamentoForm.paginacao.totalRegistros} </a>
									</li>
									<logic:equal name="formaPagamentoForm" property="paginacao.voltarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="formaPagamentoForm" property="paginacao.voltarPaginacaoInativo" value="false">
										<li class="page-item">
											<a class="page-link" href="${contextPath}/restrito/sistema/formaPagamento.src?method=voltar&forwardDestino=formaPagamentoLista" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>

									<c:forEach var="i" begin="${formaPagamentoForm.paginacao.paginaInicial}" end="${formaPagamentoForm.paginacao.paginaFinal}" step="1" varStatus="row">
										<c:if test="${formaPagamentoForm.paginacao.paginaCorrente == i}">
											<li class="page-item active">
										</c:if>

										<c:if test="${formaPagamentoForm.paginacao.paginaCorrente != i}">
											<li class="page-item ">
										</c:if>

										<a class="page-link" href="${contextPath}/restrito/sistema/formaPagamento.src?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>
										</li>
									</c:forEach>

									<logic:equal name="formaPagamentoForm" property="paginacao.avancarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Next">
												<span aria-hidden="true">&raquo;</span>
												<span class="sr-only">Próximo</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="formaPagamentoForm" property="paginacao.avancarPaginacaoInativo" value="false">
										<li class="page-item ">
											<a class="page-link" href="${contextPath}/restrito/sistema/formaPagamento.src?method=avancar&forwardDestino=formaPagamentoLista" aria-label="Next">
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

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#nome").attr("maxlength", 50);

		/* Setando os placeholder dos campos*/
		$( "#especificacao" ).attr("placeholder","Descritivo a ser pesquisado");
		
		
		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_formaPagamento_consulta").attr("autocomplete", "off");
		
		$('#form_formaPagamento_consulta').on('submit', function(e){
	        abrirModalProcessando();
	    });
		
		$( '#pesquisar' ).click(function() {  	
			executar('form_formaPagamento_consulta','filtrar');
		});
		
		$( '#limparPesquisa' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_formaPagamento_consulta','limparFiltro');
		});
		
		$( '#novo' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_formaPagamento_consulta','abrirCadastro');
		});
		
		
	});
	
	function selecionar(urlAction) {
		abrirModalProcessando();
		window.location = urlAction;
	}
</script>