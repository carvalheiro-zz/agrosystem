<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!-- CAMPOS DE FILTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<h1 class="page-header cabecalho_pagina" style="margin: 15px 0 10px;">
			<i class="fa fa-newspaper-o fa-fw" style="font-size: 26px;"></i>
			Pesquisar Receitas
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Listagem das receitas
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<div class="row">

	<!-- Define o tamanho geral da receita -->
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel" style="box-shadow: 0 10px 30px rgba(0, 0, 0, 0.8); border-color: #909090;">

			<!-- INICIO CAMPOS DE PESQUISA -->
			<div class="panel-heading cor-sistema">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_receita_consulta" action="restrito/sistema/receita" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label class="text-white">Número</label>
									<html:text styleClass="form-control input-sm" styleId="numeroPesquisa" property="receitaFilter.contaReceber.numero" name="receitaForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<label class="text-white">Nome</label>
									<html:text styleClass="form-control input-sm" styleId="nomePesquisa" property="receitaFilter.nome" name="receitaForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label class="text-white">Data Inicial</label>
									<html:text styleClass="form-control input-sm data text-center" styleId="dataInicialPesquisa" property="dataInicial" name="receitaForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label class="text-white">Data Final</label>
									<html:text styleClass="form-control input-sm data text-center" styleId="dataFinalPesquisa" property="dataFinal" name="receitaForm" />
								</div>
							</div>
							<div class="row">

								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.receita.filtrar)">
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
								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.receita.inserir)">
									<div class="col-xs-12 col-sm-3 col-md-4 col-lg-2">
										<button type="button" id="novo" class="btn btn-success btn-sm cor-sistema btn-block">
											<i class="fa fa-file-o"></i>
											Lançar
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
			<logic:empty name="receitaForm" property="receitas">
				<div class="row" style="margin-top: 15px;">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="alert alert-warning">Nenhum registro encontrado!</div>
					</div>
				</div>
			</logic:empty>
			<logic:notEmpty name="receitaForm" property="receitas">
				<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.receita.filtrar)">
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
								<thead>
									<!-- CABEÇALHO DA TABELA -->
									<tr class="cor-sistema" style="color: white;">
										<th class="text-center">Número</th>
										<th>Nome</th>
										<th class="text-center">Emissão</th>
										<th>Observação</th>
										<th class="text-right">Valor</th>
										<th class="text-right">Recebido+Multa+Juros.</th>
										<th class="text-center">Situação</th>
										<th class="text-center" style="width: 80px;">Opções</th>
									</tr>
								</thead>
								<tbody>
									<!-- TABELA -->
									<logic:iterate id="receitaCorrente" name="receitaForm" property="receitas" type="br.com.srcsoftware.modular.financeiro.receita.ReceitaDTO">

										<logic:empty name="receitaCorrente" property="contaReceber.canceladoToString">
											<logic:equal name="receitaCorrente" value="Aberta" property="contaReceber.situacao">
												<tr>
											</logic:equal>
											<logic:equal name="receitaCorrente" value="Quitada" property="contaReceber.situacao">
												<tr style="background-color: rgba(0, 18, 230, 0.41)">
											</logic:equal>
											<logic:equal name="receitaCorrente" value="Cancelada" property="contaReceber.situacao">
												<tr style="background-color: rgba(255, 27, 0, 0.4)">
											</logic:equal>
										</logic:empty>
										<logic:notEmpty name="receitaCorrente" property="contaReceber.canceladoToString">
											<tr style="background-color: #ffa7a7 !important;">
										</logic:notEmpty>

										<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/receita.src?method=selecionar&receitaFilter.id=${receitaCorrente.id}')">${receitaCorrente.contaReceber.numero}</td>

										<td onclick="selecionar('${contextPath}/restrito/sistema/receita.src?method=selecionar&receitaFilter.id=${receitaCorrente.id}')">${receitaCorrente.nome}</td>

										<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/receita.src?method=selecionar&receitaFilter.id=${receitaCorrente.id}')">${receitaCorrente.contaReceber.dataToString}</td>

										<td onclick="selecionar('${contextPath}/restrito/sistema/receita.src?method=selecionar&receitaFilter.id=${receitaCorrente.id}')">${receitaCorrente.observacao}</td>

										<td class="text-right" onclick="selecionar('${contextPath}/restrito/sistema/receita.src?method=selecionar&receitaFilter.id=${receitaCorrente.id}')">${receitaCorrente.contaReceber.valorUltimaParcela}</td>
										<td class="text-right" onclick="selecionar('${contextPath}/restrito/sistema/receita.src?method=selecionar&receitaFilter.id=${receitaCorrente.id}')">${receitaCorrente.contaReceber.totalRecebido}</td>


										<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/receita.src?method=selecionar&receitaFilter.id=${receitaCorrente.id}')">
											<logic:empty name="receitaCorrente" property="contaReceber.canceladoToString">
												${receitaCorrente.contaReceber.situacao}
											</logic:empty>
											<logic:notEmpty name="receitaCorrente" property="contaReceber.canceladoToString">
												${receitaCorrente.contaReceber.canceladoToString}
											</logic:notEmpty>
										</td>

										<td class="text-center" style="min-width: 80px; vertical-align: middle;">
											<logic:equal value="true" property="ativa" name="receitaCorrente">
												<logic:equal value="Fixa" property="contaReceber.tipo" name="receitaCorrente">
													<button type="button" id="gerarProxima${i}" title="Gerar próxima Conta" class="btn btn-info btn-xs fa fa-plus-square-o" style="font-size: 14px" onclick="gerarProxima('${receitaCorrente.id}');"></button>
												</logic:equal>
											</logic:equal>
											<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.receita.excluir)">
												<button type="button" id="excluir" title="Excluir a receita" class="btn btn-danger btn-xs fa fa-trash" style="font-size: 14px"
													onclick="excluir('Nº ${receitaCorrente.contaReceber.numero}&nbsp;-&nbsp;Receita: ${receitaCorrente.nome}', 'sistema/receita', ${receitaCorrente.id} );"></button>
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
			<logic:notEmpty name="receitaForm" property="receitas">
				<div class="panel-footer">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">

							<nav aria-label="Page navigation">
								<ul class="pagination" style="font-size: 12px;">
									<li>
										<a href="javascript://" style="cursor: default;">Encontrados: ${receitaForm.paginacao.totalRegistros} </a>
									</li>
									<logic:equal name="receitaForm" property="paginacao.voltarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="receitaForm" property="paginacao.voltarPaginacaoInativo" value="false">
										<li class="page-item">
											<a class="page-link" href="${contextPath}/restrito/sistema/receita.src?method=voltar&forwardDestino=receitaLista" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>

									<c:forEach var="i" begin="${receitaForm.paginacao.paginaInicial}" end="${receitaForm.paginacao.paginaFinal}" step="1" varStatus="row">
										<c:if test="${receitaForm.paginacao.paginaCorrente == i}">
											<li class="page-item active">
										</c:if>

										<c:if test="${receitaForm.paginacao.paginaCorrente != i}">
											<li class="page-item ">
										</c:if>

										<a class="page-link" href="${contextPath}/restrito/sistema/receita.src?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>
										</li>
									</c:forEach>

									<logic:equal name="receitaForm" property="paginacao.avancarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Next">
												<span aria-hidden="true">&raquo;</span>
												<span class="sr-only">Próximo</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="receitaForm" property="paginacao.avancarPaginacaoInativo" value="false">
										<li class="page-item ">
											<a class="page-link" href="${contextPath}/restrito/sistema/receita.src?method=avancar&forwardDestino=receitaLista" aria-label="Next">
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
		$( "#numeroPesquisa" ).focus();

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#numeroPesquisa").attr("maxlength", 50);
		$("#nomePesquisa").attr("maxlength", 100);

		/* Setando os placeholder dos campos*/
		$( "#numeroPesquisa" ).attr("placeholder","Número a ser pesquisado");
		$( "#nomePesquisa" ).attr("placeholder","Nome a ser pesquisado");
		
		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_receita_consulta").attr("autocomplete", "off");
		
		$('#form_receita_consulta').on('submit', function(e){
	        abrirModalProcessando();
	    });
		
		$( '#pesquisar' ).click(function() {  	
			executar('form_receita_consulta','filtrar');
		});
		
		$( '#limparPesquisa' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_receita_consulta','limparFiltro');
		});
		
		$( '#novo' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_receita_consulta','abrirCadastro');
		});		
		
		$("#dataInicialPesquisa").focusout(function() {
			validarData('dataInicialPesquisa');
		});	
		$("#dataFinalPesquisa").focusout(function() {
			validarData('dataFinalPesquisa');
		});
		
	});
	
	function selecionar(urlAction) {
		abrirModalProcessando();
		window.location = urlAction;
	}
	
	function gerarProxima(idReceita) {
		var camposModal = 
			'<div class="row">'+
				'<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">'+
					'<label>Deseja gerar esta Receita para o MÊS sugerido?</label>'+
				'</div>'+
				'<div class="form-group col-xs-12 col-sm-12 col-md-5 col-lg-4">'+
					'<label>Vencimento Próxima</label>'+
					'<input type="text" class="form-control input-sm dataMesAno" id="mesAnoProximaParcela" />'+
				'</div>'+
			'</div>';
			
		BootstrapDialog.show({
			size : BootstrapDialog.SIZE_LARGE,
			title : 'Atenção...',
			message: camposModal,
			closable : false,
			type : BootstrapDialog.TYPE_DANGER,
			onshown : function() {
				$("#mesAnoProximaParcela").focus();	
				
				var mesAno = (new Date().getMonth()+2)+'/'+new Date().getFullYear();
				
				mesAno = mesAno.padStart(7, "0");     // "00000abc"
				$("#mesAnoProximaParcela").val(mesAno);		
            },
			buttons : [ {
				label : 'Sim, gerar',
				action : function(dialogRef) { 
					
					var actionURL = '${contextPath}/restrito/sistema/receita.src?method=gerarProximaPorReceita&receita.id='+idReceita+'&mesAnoProximaParcela='+$('#mesAnoProximaParcela').val();
					$.ajax({
						type : 'POST',
						url : actionURL,
						complete : function(XMLHttpRequest, textStatus) {
							abrirModalProcessando();
							executarComSubmit('form_receita_consulta','paginar');
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							alert(textStatus);
						}
					});

					dialogRef.close();
				}
			}, {
				label : 'Não, Obrigado',
				action : function(dialogRef) {
					abrirModalProcessando();
					executarComSubmit('form_receita_consulta', 'paginar');
					dialogRef.close();
				}
			} ]
		});
	}
</script>