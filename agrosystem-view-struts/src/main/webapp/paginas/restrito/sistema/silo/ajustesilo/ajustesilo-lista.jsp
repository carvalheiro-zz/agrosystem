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
			Pesquisar ${ajusteSiloForm.ajusteSilo.tipo}
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Listagem das ${ajusteSiloForm.ajusteSilo.tipo}
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
						<html:form styleId="form_ajusteSilo_consulta" action="restrito/sistema/ajusteSilo" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<%-- <div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<html:text styleClass="form-control input-lg bloqueado text-center" styleId="tipo" property="ajusteSilo.tipo" name="ajusteSiloForm" />
								</div> --%>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label class="text-white">Data inicial</label>
									<html:text styleClass="form-control input-sm data" styleId="dataInicial" property="dataInicial" name="ajusteSiloForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label class="text-white">Data final</label>
									<html:text styleClass="form-control input-sm data" styleId="dataFinal" property="dataFinal" name="ajusteSiloForm" />
								</div>

								<%-- <div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-4">
									<label class="text-white">Tipo</label>
									<html:select styleClass="form-control input-sm" styleId="nomeMotorista" property="ajusteSilo.tipo" name="ajusteSiloForm">
										<html:option value="">Selecione...</html:option>
										<html:option value="Sobra de Classificação">Sobra de Classificação (+)</html:option>
										<html:option value="Quebra de Classificação">Quebra de Classificação (-)</html:option>
									</html:select>
								</div> --%>
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3">
									<label class="text-white">Lançador</label>
									<html:text styleClass="form-control input-sm" styleId="lancador" property="ajusteSilo.lancador.pessoaFisica.razaoSocial" name="ajusteSiloForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="lancadorSelecionado" property="ajusteSilo.lancador.id" name="ajusteSiloForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3">
									<label class="text-white">Local Armazenagem</label>
									<html:select styleClass="form-control input-sm" styleId="localArmazenagem" property="ajusteSilo.localArmazenagem.id" name="ajusteSiloForm">
										<html:option value="">Selecione...</html:option>
										<html:optionsCollection name="ajusteSiloForm" property="comboSilos" label="label" value="value" />
									</html:select>
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3">
									<label class="text-white">Safra</label>
									<html:text styleClass="form-control input-sm" styleId="safra" property="ajusteSilo.safra.nome" name="ajusteSiloForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="safraSelecionada" property="ajusteSilo.safra.id" name="ajusteSiloForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label class="text-white">Cultura</label>
									<html:text styleClass="form-control input-sm" styleId="cultura" property="ajusteSilo.cultura.nome" name="ajusteSiloForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="culturaSelecionada" property="ajusteSilo.cultura.id" name="ajusteSiloForm" />
								</div>

							</div>
							<div class="row">

								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.ajusteSilo.filtrar)">
									<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2" style="margin-bottom: 0px;">
										<button type="submit" id="pesquisar" class="btn btn-success btn-sm cor-sistema btn-block">
											<i class="fa fa-search"></i>
											Pesquisar
										</button>
									</div>
								</logic:equal>
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2" style="margin-bottom: 0px;">
									<button type="button" id="limparPesquisa" class="btn btn-success btn-sm cor-sistema btn-block">
										<i class="glyphicon glyphicon-erase"></i>
										Limpar
									</button>
								</div>
								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.ajusteSilo.inserir)">
									<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2" style="margin-bottom: 0px;">
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
			<logic:empty name="ajusteSiloForm" property="ajustes">
				<div class="row" style="margin-top: 15px;">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="alert alert-warning">Nenhum registro encontrado!</div>
					</div>
				</div>
			</logic:empty>
			<logic:notEmpty name="ajusteSiloForm" property="ajustes">
				<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.ajusteSilo.filtrar)">
					<div class="panel-body">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
							<div class="table-responsive">
								<table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
									<thead>
										<!-- CABEÇALHO DA TABELA -->
										<tr class="cor-sistema" style="color: white;">
											<th>Tipo</th>
											<th class="text-center">Data</th>
											<th>Lançador</th>
											<th class="text-center">Armazenagem</th>
											<th class="text-center">Safra</th>
											<th class="text-center">Cultura</th>
											<th class="text-right">Peso Liquido</th>
											<th class="text-right">Sacas</th>
											<th class="text-center" style="width: 80px;">Opções</th>
										</tr>
									</thead>
									<tbody>
										<!-- TABELA -->
										<logic:iterate id="ajusteSiloCorrente" name="ajusteSiloForm" property="ajustes" type="br.com.srcsoftware.sistema.silo.ajuste.AjusteSiloDTO">

											<tr>
												<!-- success / info / warning / danger -->
												<td class="text-left" onclick="selecionar('${contextPath}/restrito/sistema/ajusteSilo.src?method=selecionar&ajusteSilo.id=${ajusteSiloCorrente.id}')">${ajusteSiloCorrente.tipo}</td>
												<td onclick="selecionar('${contextPath}/restrito/sistema/ajusteSilo.src?method=selecionar&ajusteSilo.id=${ajusteSiloCorrente.id}')">${ajusteSiloCorrente.dataToString}</td>
												<td class="text-left" onclick="selecionar('${contextPath}/restrito/sistema/ajusteSilo.src?method=selecionar&ajusteSilo.id=${ajusteSiloCorrente.id}')">${ajusteSiloCorrente.lancador.pessoaFisica.razaoSocial}</td>
												<td onclick="selecionar('${contextPath}/restrito/sistema/ajusteSilo.src?method=selecionar&ajusteSilo.id=${ajusteSiloCorrente.id}')">${ajusteSiloCorrente.localArmazenagem.nome}</td>

												<td onclick="selecionar('${contextPath}/restrito/sistema/ajusteSilo.src?method=selecionar&ajusteSilo.id=${ajusteSiloCorrente.id}')">${ajusteSiloCorrente.safra.nome}</td>
												<td onclick="selecionar('${contextPath}/restrito/sistema/ajusteSilo.src?method=selecionar&ajusteSilo.id=${ajusteSiloCorrente.id}')">${ajusteSiloCorrente.cultura.nome}</td>
												<td class="text-right" onclick="selecionar('${contextPath}/restrito/sistema/ajusteSilo.src?method=selecionar&ajusteSilo.id=${ajusteSiloCorrente.id}')">${ajusteSiloCorrente.quantidade}&nbsp;Kg</td>
												<td class="text-right" onclick="selecionar('${contextPath}/restrito/sistema/ajusteSilo.src?method=selecionar&ajusteSilo.id=${ajusteSiloCorrente.id}')">${ajusteSiloCorrente.emSacas}</td>


												<td class="text-center" style="min-width: 80px; vertical-align: middle;">
													<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.ajusteSilo.excluir)">
														<button type="button" id="excluir" class="btn btn-danger btn-xs fa fa-trash" style="font-size: 14px"
															onclick="excluir('Data: ${ajusteSiloCorrente.dataToString}<br/>Safra: ${ajusteSiloCorrente.safra.nomeCompleto}<br/>Cultura: ${ajusteSiloCorrente.cultura.nome}<br/>Peso liquido: ${ajusteSiloCorrente.quantidade} (${ajusteSiloCorrente.emSacas})', 'sistema/ajusteSilo', ${ajusteSiloCorrente.id} );"></button>
													</logic:equal>
												</td>

											</tr>
										</logic:iterate>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</logic:equal>
			</logic:notEmpty>
			<!-- TERMINO TABELA -->
			<!-- /.panel-body -->

			<!-- INICIO PAGINAÇÃO -->
			<logic:notEmpty name="ajusteSiloForm" property="ajustes">
				<div class="panel-footer">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">

							<nav aria-label="Page navigation">
								<ul class="pagination" style="font-size: 12px;">
									<li>
										<a href="javascript://" style="cursor: default;">Encontrados: ${ajusteSiloForm.paginacao.totalRegistros} </a>
									</li>
									<logic:equal name="ajusteSiloForm" property="paginacao.voltarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="ajusteSiloForm" property="paginacao.voltarPaginacaoInativo" value="false">
										<li class="page-item">
											<a class="page-link" href="${contextPath}/restrito/sistema/ajusteSilo.src?method=voltar&forwardDestino=ajusteSiloLista" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>

									<c:forEach var="i" begin="${ajusteSiloForm.paginacao.paginaInicial}" end="${ajusteSiloForm.paginacao.paginaFinal}" step="1" varStatus="row">
										<c:if test="${ajusteSiloForm.paginacao.paginaCorrente == i}">
											<li class="page-item active">
										</c:if>

										<c:if test="${ajusteSiloForm.paginacao.paginaCorrente != i}">
											<li class="page-item ">
										</c:if>

										<a class="page-link" href="${contextPath}/restrito/sistema/ajusteSilo.src?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>
										</li>
									</c:forEach>

									<logic:equal name="ajusteSiloForm" property="paginacao.avancarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Next">
												<span aria-hidden="true">&raquo;</span>
												<span class="sr-only">Próximo</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="ajusteSiloForm" property="paginacao.avancarPaginacaoInativo" value="false">
										<li class="page-item ">
											<a class="page-link" href="${contextPath}/restrito/sistema/ajusteSilo.src?method=avancar&forwardDestino=ajusteSiloLista" aria-label="Next">
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
		$( "#dataInicial" ).focus();
		
		/* Setando NOTNULL nos campos*/
		//$("#safra").addClass("obrigatorio");
		
		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#tipo").prop("maxlength", 30);
		$("#lancador").prop("maxlength", 100);
		$("#data").prop("maxlength", 12);
		$("#safra").prop("maxlength", 20);
		$("#cultura").prop("maxlength", 20);
		$("#localArmazenagem").prop("maxlength", 20);
		$("#quantidade").prop("maxlength", 10);

		/* Setando os placeholder dos campos*/
		$("#lancador").prop("placeholder", "Lançador a pesquisar");
		$("#safra").prop("placeholder", "Safra a pesquisar");
		$("#cultura").prop("placeholder", "Cultura a pesquisar");
		
		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_ajusteSilo_consulta").prop("autocomplete", "off");
		
		$('#form_ajusteSilo_consulta').on('submit', function(e){
	        abrirModalProcessando();
	    });		
		
		$('#pesquisar').on('click', function(e){
			executar('form_ajusteSilo_consulta','filtrar');
	    });		
		
		$('#limparPesquisa').on('click', function(e){
			abrirModalProcessando();
			executarComSubmit('form_ajusteSilo_consulta','limparFiltro');
	    });
		
		$('#novo').on('click', function(e){
			abrirModalProcessando();
			executarComSubmit('form_ajusteSilo_consulta','abrirCadastro');
	    });
		
		$('#gerarPDF').click(function() {
			gerarRelatorio('form_ajusteSilo_consulta', 'gerarPDF');
		});
				
		// ######################################### Auto Complete Safra/Setor/Variedade		

		$('#lancador').keyup(function() {
			if ($('#lancador').val() == null || $('#marca').val() == '') {
				$('#lancadorSelecionado').val(null);
			}
		});
		$('#lancador').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'ajusteSilo.lancador.pessoaFisica.razaoSocial',
			/* params : {
				'ajusteSilo.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/ajusteSilo.src?method=selecionarLancadorAutoComplete',
			onSelect : function(suggestion) {
				$('#lancadorSelecionado').val(suggestion.data);
			},
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			}, */
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#lancadorSelecionado').val(null);
				}
			}
		});

		$('#safra').keyup(function() {
			if ($('#safra').val() == null || $('#safra').val().trim() == '') {
				$('#safraSelecionada').val(null);
				$('#cultura').val(null);
			}
		});

		$('#safra').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'ajusteSilo.safra.nome',
			serviceUrl : '${contextPath}/restrito/sistema/ajusteSilo.src?method=selecionarSafraAutoComplete',
			onSelect : function(suggestion) {
				$('#safraSelecionada').val(suggestion.data);
				$("#cultura").focus();
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#safraSelecionada').val(null);
					$('#cultura').val(null);
				}
			}
		});

		$('#cultura').keyup(function() {
			if ($('#cultura').val() == null || $('#cultura').val().trim() == '') {
				$('#culturaSelecionada').val(null);
			}
		});

		$('#cultura').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'ajusteSilo.cultura.nome',
			params : {
				'ajusteSilo.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			},
			serviceUrl : '${contextPath}/restrito/sistema/ajusteSilo.src?method=selecionarCulturaAutoComplete',
			onSelect : function(suggestion) {
				$('#culturaSelecionada').val(suggestion.data);

				$("#quantidade").focus();
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#culturaSelecionada').val(null);
				}
			}
		});
	});
	
	function selecionar(urlAction) {
		abrirModalProcessando();
		window.location = urlAction;
	}
</script>