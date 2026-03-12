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
			Pesquisar Despesas
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Listagem das despesas
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<div class="row">

	<!-- Define o tamanho geral da despesa -->
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel" style="box-shadow: 0 10px 30px rgba(0, 0, 0, 0.8); border-color: #909090;">

			<!-- INICIO CAMPOS DE PESQUISA -->
			<div class="panel-heading cor-sistema">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_despesa_consulta" action="restrito/sistema/despesa" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label class="text-white">Número</label>
									<html:text styleClass="form-control input-sm" styleId="numeroPesquisa" property="despesaFilter.contaPagar.numero" name="despesaForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<label class="text-white">Nome</label>
									<html:text styleClass="form-control input-sm" styleId="nomePesquisa" property="despesaFilter.nome" name="despesaForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label class="text-white">Data Inicial</label>
									<html:text styleClass="form-control input-sm data text-center" styleId="dataInicialPesquisa" property="dataInicial" name="despesaForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label class="text-white">Data Final</label>
									<html:text styleClass="form-control input-sm data text-center" styleId="dataFinalPesquisa" property="dataFinal" name="despesaForm" />
								</div>
							</div>
							<div class="row">

								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.despesa.filtrar)">
									<div class="form-group col-xs-12 col-sm-3 col-md-2 col-lg-2">
										<button type="submit" id="pesquisar" class="btn btn-success btn-sm cor-sistema btn-block">
											<i class="fa fa-search"></i>
											Pesquisar
										</button>
									</div>
								</logic:equal>
								<div class="form-group col-xs-12 col-sm-3 col-md-2 col-lg-2">
									<button type="button" id="limparPesquisa" class="btn btn-success btn-sm cor-sistema btn-block">
										<i class="glyphicon glyphicon-erase"></i>
										Limpar
									</button>
								</div>
								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.despesa.inserir)">
									<div class="form-group col-xs-12 col-sm-3 col-md-2 col-lg-2">
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
			<logic:empty name="despesaForm" property="despesas">
				<div class="row" style="margin-top: 15px;">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="alert alert-warning">Nenhum registro encontrado!</div>
					</div>
				</div>
			</logic:empty>
			<logic:notEmpty name="despesaForm" property="despesas">
				<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.despesa.filtrar)">
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
										<th>Valor</th>
										<th>Pago+Acrés.</th>
										<th class="text-center">Situação</th>
										<!-- <th class="text-center">Tipo</th>
										<th>Forma Pagamento</th> -->
										<th class="text-center" style="width: 80px;">Opções</th>
									</tr>
								</thead>
								<tbody>
									<!-- TABELA -->
									<logic:iterate id="despesaCorrente" name="despesaForm" property="despesas" type="br.com.srcsoftware.modular.financeiro.despesa.DespesaDTO">

										<logic:empty name="despesaCorrente" property="contaPagar.canceladoToString">
											<logic:equal name="despesaCorrente" value="Aberta" property="contaPagar.situacao">
												<tr>
											</logic:equal>
											<logic:equal name="despesaCorrente" value="Quitada" property="contaPagar.situacao">
												<tr style="background-color: rgba(0, 18, 230, 0.41)">
											</logic:equal>
											<logic:equal name="despesaCorrente" value="Cancelada" property="contaPagar.situacao">
												<tr style="background-color: rgba(255, 27, 0, 0.4)">
											</logic:equal>
										</logic:empty>
										<logic:notEmpty name="despesaCorrente" property="contaPagar.canceladoToString">
											<tr style="background-color: #ffa7a7 !important;">
										</logic:notEmpty>

										<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/despesa.src?method=selecionar&despesaFilter.id=${despesaCorrente.id}')">${despesaCorrente.contaPagar.numero}</td>

										<td onclick="selecionar('${contextPath}/restrito/sistema/despesa.src?method=selecionar&despesaFilter.id=${despesaCorrente.id}')">${despesaCorrente.nome}</td>

										<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/despesa.src?method=selecionar&despesaFilter.id=${despesaCorrente.id}')">${despesaCorrente.contaPagar.dataToString}</td>

										<td onclick="selecionar('${contextPath}/restrito/sistema/despesa.src?method=selecionar&despesaFilter.id=${despesaCorrente.id}')">${despesaCorrente.observacao}</td>

										<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/despesa.src?method=selecionar&despesaFilter.id=${despesaCorrente.id}')">${despesaCorrente.contaPagar.valor}</td>

										<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/despesa.src?method=selecionar&despesaFilter.id=${despesaCorrente.id}')">${despesaCorrente.contaPagar.totalPago}</td>

										<%-- <td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/despesa.src?method=selecionar&despesaFilter.id=${despesaCorrente.id}')">
										${despesaCorrente.contaPagar.situacao}</td> --%>




										<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/despesa.src?method=selecionar&despesaFilter.id=${despesaCorrente.id}')">
											<logic:empty name="despesaCorrente" property="contaPagar.canceladoToString">
												${despesaCorrente.contaPagar.situacao}
											</logic:empty>
											<logic:notEmpty name="despesaCorrente" property="contaPagar.canceladoToString">
												${despesaCorrente.contaPagar.canceladoToString}
											</logic:notEmpty>
										</td>





										<!-- TIPO -->
										<%-- <logic:notPresent name="despesaCorrente" property="ativa">
											<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/despesa.src?method=selecionar&despesaFilter.id=${despesaCorrente.id}')">
												${despesaCorrente.contaPagar.tipo}
											</td>
										</logic:notPresent>
										<logic:equal name="despesaCorrente" value="true" property="ativa">
											<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/despesa.src?method=selecionar&despesaFilter.id=${despesaCorrente.id}')">
											${despesaCorrente.contaPagar.tipo}</td>
										</logic:equal>
										<logic:equal name="despesaCorrente" value="false" property="ativa">
											<td class="text-center" style="font-weight: bold !important; color:red" onclick="selecionar('${contextPath}/restrito/sistema/despesa.src?method=selecionar&despesaFilter.id=${despesaCorrente.id}')">
											${despesaCorrente.contaPagar.tipo} / ${despesaCorrente.ativaToString}</td>
										</logic:equal>
										
										<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/despesa.src?method=selecionar&despesaFilter.id=${despesaCorrente.id}')">
										${despesaCorrente.contaPagar.formaPagamento.nome}</td> --%>


										<td class="text-center" style="min-width: 80px; vertical-align: middle;">
											<logic:equal value="true" property="ativa" name="despesaCorrente">
												<logic:equal value="Fixa" property="contaPagar.tipo" name="despesaCorrente">
													<button type="button" id="gerarProxima${i}" title="Gerar próxima Conta" class="btn btn-info btn-xs fa fa-plus-square-o" style="font-size: 14px" onclick="gerarProxima('${despesaCorrente.id}');"></button>
												</logic:equal>
											</logic:equal>
											<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.despesa.excluir)">
												<button type="button" id="excluir" title="Excluir a despesa" class="btn btn-danger btn-xs fa fa-trash" style="font-size: 14px"
													onclick="excluir('Nº ${despesaCorrente.contaPagar.numero}&nbsp;-&nbsp;Despesa: ${despesaCorrente.nome}', 'sistema/despesa', ${despesaCorrente.id} );"></button>
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
			<logic:notEmpty name="despesaForm" property="despesas">
				<div class="panel-footer">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">

							<nav aria-label="Page navigation">
								<ul class="pagination" style="font-size: 12px;">
									<li>
										<a href="javascript://" style="cursor: default;">Encontrados: ${despesaForm.paginacao.totalRegistros} </a>
									</li>
									<logic:equal name="despesaForm" property="paginacao.voltarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="despesaForm" property="paginacao.voltarPaginacaoInativo" value="false">
										<li class="page-item">
											<a class="page-link" href="${contextPath}/restrito/sistema/despesa.src?method=voltar&forwardDestino=despesaLista" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>

									<c:forEach var="i" begin="${despesaForm.paginacao.paginaInicial}" end="${despesaForm.paginacao.paginaFinal}" step="1" varStatus="row">
										<c:if test="${despesaForm.paginacao.paginaCorrente == i}">
											<li class="page-item active">
										</c:if>

										<c:if test="${despesaForm.paginacao.paginaCorrente != i}">
											<li class="page-item ">
										</c:if>

										<a class="page-link" href="${contextPath}/restrito/sistema/despesa.src?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>
										</li>
									</c:forEach>

									<logic:equal name="despesaForm" property="paginacao.avancarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Next">
												<span aria-hidden="true">&raquo;</span>
												<span class="sr-only">Próximo</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="despesaForm" property="paginacao.avancarPaginacaoInativo" value="false">
										<li class="page-item ">
											<a class="page-link" href="${contextPath}/restrito/sistema/despesa.src?method=avancar&forwardDestino=despesaLista" aria-label="Next">
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
		$("#form_despesa_consulta").attr("autocomplete", "off");
		
		$('#form_despesa_consulta').on('submit', function(e){
	        abrirModalProcessando();
	    });
		
		$( '#pesquisar' ).click(function() {  	
			executar('form_despesa_consulta','filtrar');
		});
		
		$( '#limparPesquisa' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_despesa_consulta','limparFiltro');
		});
		
		$( '#novo' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_despesa_consulta','abrirCadastro');
		});		
		
	});
	
	function selecionar(urlAction) {
		abrirModalProcessando();
		window.location = urlAction;
	}
	
	function gerarProxima(idDespesa) {
		var camposModal = 
			'<div class="row">'+
				'<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">'+
					'<label>Deseja gerar esta Despesa para o MÊS sugerido?</label>'+
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
					
					var actionURL = '${contextPath}/restrito/sistema/despesa.src?method=gerarProximaPorDespesa&despesa.id='+idDespesa+'&mesAnoProximaParcela='+$('#mesAnoProximaParcela').val();
					$.ajax({
						type : 'POST',
						url : actionURL,
						complete : function(XMLHttpRequest, textStatus) {
							abrirModalProcessando();
							executarComSubmit('form_despesa_consulta','paginar');
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
					executarComSubmit('form_despesa_consulta', 'paginar');
					dialogRef.close();
				}
			} ]
		});
	}
</script>