<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!-- CAMPOS DE CADASTRO -->
<%-- <div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<h1 class="page-header cabecalho_pagina" style="margin: 15px 0 10px;">
			<i class="fa fa-newspaper-o fa-fw" style="font-size: 26px;"></i>
			Resumo Férias&nbsp;
			<small>
				<a href="${contextPath}/restrito/sistema/ferias.src?method=abrirListagem">
					<i class="fa fa-angle-double-right"></i>
					Lançar
				</a>
			</small>
		</h1>
	</div>
</div> --%>

<jsp:include page="../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<div class="row">
	<!-- Define o tamanho geral da cultura -->
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel sombra">

			<div class="panel-heading">
				<h1 class="page-header cabecalho_pagina" style="margin: 15px 0 -10px;">
					<i class="fa fa-newspaper-o fa-fw" style="font-size: 26px;"></i>
					Resumo Férias&nbsp;
					<small>
						<a href="${contextPath}/restrito/sistema/ferias.src?method=abrirListagem">
							<i class="fa fa-angle-double-right"></i>
							Lançar
						</a>
					</small>
				</h1>
			</div>

			<!-- INICIO FORMULARIO -->
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_demonstrativoLivroPontoFerias" action="restrito/sistema/demonstrativoLivroPontoFerias" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<logic:notEmpty name="erroAjax" scope="session">
									<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<div class="alert alert-danger">
											<strong>Atenção!</strong>
											${erroAjax}
										</div>
									</div>
								</logic:notEmpty>
								<logic:notEmpty name="sucessoAjax" scope="session">
									<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<div class="alert alert-success">
											<strong>Parabéns!</strong>
											${sucessoAjax}
										</div>
									</div>
								</logic:notEmpty>

								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<label>Colaborador</label>
									<html:text styleClass="form-control input-sm" styleId="colaboradorFerias" property="nomeColaborador" name="demonstrativoLivroPontoFeriasForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="colaboradorFeriasSelecionado" property="idColaborador" name="demonstrativoLivroPontoFeriasForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
									<label>Data inicial</label>
									<html:text styleClass="form-control input-sm data" styleId="dataInicialFerias" property="dataInicial" name="demonstrativoLivroPontoFeriasForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
									<label>Data final</label>
									<html:text styleClass="form-control input-sm data" styleId="dataFinalFerias" property="dataFinal" name="demonstrativoLivroPontoFeriasForm" />
								</div>
							</div>

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3">
									<button type="button" id="filtrarLivroPontoFerias" style="margin-top: 23px;" class="btn btn-primary btn-sm cor-sistema btn-block">
										<i class="fa fa-search"></i>
										Filtrar
									</button>
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<button type="button" id="limparLivroPontoFerias" style="margin-top: 23px;" class="btn btn-primary btn-sm cor-sistema btn-block">
										<i class="fa fa-eraser"></i>
										Limpar
									</button>
								</div>
							</div>

							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<div class="panel panel-danger">
										<div class="panel-heading" style="font-size: 20px;">

											<p>
												<i class="fa fa-cubes fa-fw"></i>
												Saldo de Férias
											</p>
											<p style="margin-top: 0px; margin-bottom: 0px;">
												<small>Período da consulta:&nbsp;${demonstrativoLivroPontoFeriasForm.textoIntervaloPesquisa }</small>
											</p>
											<p style="margin-top: 0px; margin-bottom: 0px;">
												<small>Saldo:&nbsp;${demonstrativoLivroPontoFeriasForm.saldo }</small>
											</p>
										</div>
										<!-- /.panel-heading -->
										<div class="panel-body">
											<div class="table-responsive">
												<table class="table table-bordered table-striped" style="font-size: 12px;">
													<thead>

														<!-- CABEÇALHO DA TABELA -->
														<tr class="bg-danger">
															<th>Colaborador</th>
															<th style="width: 180px; min-width: 180px;">Adquidira(s)</th>
															<th style="width: 180px; min-width: 180px;">Cumprida(s)</th>
															<th style="width: 180px; min-width: 180px;">Vendida(s)</th>
															<th style="width: 180px; min-width: 180px;">Saldo</th>
															<th style="width: 180px; min-width: 180px;">Saldo em dias</th>
														</tr>
													</thead>
													<tbody>
														<!-- TABELA -->
														<logic:iterate indexId="i" id="saldoCorrente" name="demonstrativoLivroPontoFeriasForm" property="saldosFerias" type="br.com.srcsoftware.sistema.pessoa.funcionario.ferias.SaldoFeriasPOJO">
															<tr>
																<td style="vertical-align: middle;">${saldoCorrente.colaborador.pessoaFisica.razaoSocial}</td>
																<td style="vertical-align: middle;">${saldoCorrente.adquiridas}</td>
																<td style="vertical-align: middle;">${saldoCorrente.cumpridas}</td>
																<td style="vertical-align: middle;">${saldoCorrente.vendidas}</td>
																<td style="vertical-align: middle;">${saldoCorrente.saldo}</td>
																<td style="vertical-align: middle;">${saldoCorrente.saldoEmDias}</td>
															</tr>
														</logic:iterate>
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>

						</html:form>
					</div>
				</div>
			</div>
			<!-- TERMINO FORMULARIO -->

		</div>
	</div>
</div>


<script type="text/javascript">
	$(document).ready(function() {

		$(".alert").delay(4000).slideUp(200, function() {
			$(this).alert('close');
		});

		$("#colaboradorFerias").prop("maxlength", 100);

		$("#colaboradorFerias").prop("placeholder", "Colaborador");

		$('#filtrarLivroPontoFerias').click(function() {

			var actionURL = '${contextPath}/restrito/sistema/demonstrativoLivroPontoFerias.src?method=filtrarDemonstrativoLivroPonto';

			$.ajax({
				type : 'POST',
				url : actionURL,
				data : {
					"idColaborador" : $('#colaboradorFeriasSelecionado').val(),
					"dataInicial" : $('#dataInicialFerias').val(),
					"dataFinal" : $('#dataFinalFerias').val()
				},
				beforeSend : function(data, textStatus, XMLHttpRequest) {
					$('#processando').text('Carregando...');
				},
				success : function(data, textStatus, XMLHttpRequest) {
					$('#idPainelDemonstrativoLivroPontoFerias').html(data);

					$('.loader').css('display', 'none');
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(textStatus);
				}
			});

			$('.loader').css('display', 'none');
		});

		$('#limparLivroPontoFerias').click(function() {

			var actionURL = '${contextPath}/restrito/sistema/demonstrativoLivroPontoFerias.src?method=abrirPainelDemonstrativoLivroPonto';

			$.ajax({
				type : 'POST',
				url : actionURL,
				beforeSend : function(data, textStatus, XMLHttpRequest) {
					$('#processando').text('Carregando...');
				},
				success : function(data, textStatus, XMLHttpRequest) {
					$('#idPainelDemonstrativoLivroPontoFerias').html(data);

					$('.loader').css('display', 'none');
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(textStatus);
				}
			});
		});

		$('#colaboradorFerias').keyup(function(e) {
			if ($('#colaboradorFerias').val() == '') {
				$('#colaboradorFeriasSelecionado').val(null);
			}
		});
		$('#colaboradorFerias').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'nomeColaborador',
			serviceUrl : '${contextPath}/restrito/sistema/demonstrativoLivroPontoFerias.src?method=selecionarColaboradorAutoComplete',
			onSelect : function(suggestion) {
				$('#colaboradorFeriasSelecionado').val(suggestion.data);
			},
			onSearchComplete : function(query, suggestions) {
				$('#colaboradorFeriasSelecionado').val(null);
			}
		});
	});
</script>