<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!-- CAMPOS DE CADASTRO -->
<!-- <div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<h1 class="page-header cabecalho_pagina" style="margin: 15px 0 10px;">
			<i class="fa fa-newspaper-o fa-fw" style="font-size: 26px;"></i>
			Demonstrativo de Produção
		</h1>
	</div>
</div> -->

<jsp:include page="../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<div class="row">
	<!-- Define o tamanho geral da cultura -->
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel sombra">

			<div class="panel-heading">
				<h1 class="page-header cabecalho_pagina" style="margin: 15px 0 -10px;">
					<i class="fa fa-newspaper-o fa-fw" style="font-size: 26px;"></i>
					Demonstrativo de Produção
				</h1>
			</div>

			<!-- INICIO FORMULARIO -->
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_demonstrativoProducao" action="restrito/sistema/demonstrativoProducao" method="post">
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

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3">
									<label>Safra</label>
									<html:text styleClass="form-control input-sm" styleId="safraProducao" property="safra.nome" name="demonstrativoProducaoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="safraProducaoSelecionada" property="safra.id" name="demonstrativoProducaoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3">
									<label>Setor</label>
									<html:text styleClass="form-control input-sm" styleId="setorProducao" property="setor.nomeCompleto" name="demonstrativoProducaoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="setorProducaoSelecionado" property="setor.id" name="demonstrativoProducaoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3">
									<label>Cultura</label>
									<html:text styleClass="form-control input-sm" styleId="culturaProducao" property="cultura.nome" name="demonstrativoProducaoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="culturaProducaoSelecionada" property="cultura.id" name="demonstrativoProducaoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3">
									<label>Variedade</label>
									<html:text styleClass="form-control input-sm" styleId="variedadeProducao" property="variedade.nome" name="demonstrativoProducaoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="variedadeProducaoSelecionada" property="variedade.id" name="demonstrativoProducaoForm" />
								</div>
							</div>

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3">
									<button type="button" id="filtrarProducao" style="margin-top: 23px;" class="btn btn-primary btn-sm cor-sistema btn-block">
										<i class="fa fa-search"></i>
										Filtrar
									</button>
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<button type="button" id="limparProducao" style="margin-top: 23px;" class="btn btn-primary btn-sm cor-sistema btn-block">
										<i class="fa fa-eraser"></i>
										Limpar
									</button>
								</div>

								<logic:notEmpty name="demonstrativoProducaoForm" property="demonstrativos">
									<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
										<button type="button" id="gerarPDF" style="margin-top: 23px;" class="btn btn-primary btn-sm btn-block">
											<i class="fa fa-file-pdf-o"></i>
											Gerar PDF
										</button>
									</div>
								</logic:notEmpty>
							</div>
						</html:form>

						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<div class="panel panel-danger">
									<div class="panel-heading" style="font-size: 20px;">
										<i class="fa fa-cubes fa-fw"></i>
										Saldo de Produção
									</div>
									<!-- /.panel-heading -->
									<div class="panel-body">
										<div class="table-responsive">
											<table class="table table-bordered table-striped" style="font-size: 12px;">
												<thead>

													<!-- CABEÇALHO DA TABELA -->
													<tr class="bg-danger">
														<th>Safra</th>
														<th>Setor</th>
														<th>Cultura</th>
														<th>Variedade</th>
														<th class="text-center" style="width: 180px; min-width: 180px;">Produção</th>
														<th class="text-center" style="width: 180px; min-width: 180px;">Sacas</th>
													</tr>
												</thead>
												<tbody>
													<!-- TABELA -->
													<logic:iterate indexId="i" id="demonstrativoCorrente" name="demonstrativoProducaoForm" property="demonstrativos" type="br.com.srcsoftware.sistema.silo.entrada.InformacoesProducaoSafraPOJO">
														<tr>
															<td style="vertical-align: middle;">${demonstrativoCorrente.nomeSafraView}</td>
															<td style="vertical-align: middle;">${demonstrativoCorrente.nomeSetorView}</td>
															<td style="vertical-align: middle;">${demonstrativoCorrente.culturaView}</td>
															<td style="vertical-align: middle;">${demonstrativoCorrente.variedadeView}</td>
															<td style="vertical-align: middle;" class="text-right">${demonstrativoCorrente.producao}&nbsp;Kg</td>
															<td style="vertical-align: middle;" class="text-right">${demonstrativoCorrente.emSacas}</td>
														</tr>
													</logic:iterate>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<div class="panel sombra">

									<div class="panel-heading cor-sistema" style="padding: 6px 0px 0px 15px; background-color: #e6db56 !important">
										<h4>
											Informações Restante a entregar
											<small>( Com base nos Contratos - [Comprado - Entregue] )</small>
										</h4>

									</div>

									<!-- INICIO FORMULARIO -->
									<div class="panel-body">
										<div class="row">
											<logic:iterate indexId="i" id="restanteCorrente" name="demonstrativoProducaoForm" property="restantes" type="br.com.srcsoftware.sistema.silo.contratovenda.InformacoesRestanteEntregarPOJO">

												<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-2">
													<label>Cultura</label>
													<html:text styleClass="form-control input-sm text-center bloqueado" styleId="culturaRestante${i}" property="cultura" name="restanteCorrente" />
												</div>
												<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-2">
													<label>Produzido&nbsp;Kg</label>&nbsp;<small>( A )</small>
													<html:text style="background-color: #acd5ec !important;" styleClass="form-control input-sm text-center bloqueado" styleId="quantidadeProduzida${i}" property="quantidadeProduzida" name="restanteCorrente"
														title="Sacas:&nbsp;${restanteCorrente.getProduzidaEmSacas() }" />
													<small id="quantidadeProduzidaEmSacasHelpBlock" class="form-text text-muted">Sacas:&nbsp;${restanteCorrente.getProduzidaEmSacas() }</small>
												</div>

												<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-2">
													<label>Vendida&nbsp;Kg</label>&nbsp;<small>( B )</small>
													<html:text style="background-color: #acd5ec !important;" styleClass="form-control input-sm text-center bloqueado" styleId="quantidadeVendida${i}" property="quantidadeVendida" name="restanteCorrente"
														title="Sacas:&nbsp;${restanteCorrente.getVendidaEmSacas() }" />
													<small id="quantidadeVendidaEmSacasHelpBlock" class="form-text text-muted">Sacas:&nbsp;${restanteCorrente.getVendidaEmSacas() }</small>
												</div>

												<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-2">
													<label>Saldo&nbsp;Kg</label>&nbsp;<small>( A - B )</small>
													<html:text style="background-color:#56e65a !important" styleClass="form-control input-sm text-center bloqueado" styleId="saldo${i}" property="saldo" name="restanteCorrente" title="Sacas:&nbsp;${restanteCorrente.getSaldoEmSacas() }" />
													<small id="saldoEmSacasHelpBlock" class="form-text text-muted">Sacas:&nbsp;${restanteCorrente.getSaldoEmSacas() }</small>
												</div>

												<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-2">
													<label>Entregue&nbsp;Kg</label>&nbsp;<small>( C )</small>
													<html:text style="background-color: #e6db56 !important;" styleClass="form-control input-sm text-center bloqueado" styleId="quantidadeEntregue${i}" property="quantidadeEntregue" name="restanteCorrente"
														title="Sacas:&nbsp;${restanteCorrente.getRestanteEmSacas() }" />
													<small id="quantidadeEntregueEmSacasHelpBlock" class="form-text text-muted">Sacas:&nbsp;${restanteCorrente.getEntregueEmSacas() }</small>
												</div>

												<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-2">
													<label>Á Entregar&nbsp;Kg</label>&nbsp;<small>( B - C )</small>
													<html:text style="background-color: #ff9191 !important" styleClass="form-control input-sm text-center bloqueado" styleId="quantidadeRestante${i}" property="quantidadeRestante" name="restanteCorrente"
														title="Sacas:&nbsp;${restanteCorrente.getRestanteEmSacas() }" />
													<small id="quantidadeRestanteEmSacasHelpBlock" class="form-text text-muted">Sacas:&nbsp;${restanteCorrente.getRestanteEmSacas() }</small>
												</div>

											</logic:iterate>
										</div>
									</div>
								</div>

							</div>
						</div>

						<div class="row">
							<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">

								<div class="panel sombra">

									<div class="panel-heading cor-sistema" style="padding: 6px 0px 0px 15px; color: white; background-color: rgb(66, 66, 66) !important;">
										<h4>
											Saldo de Produção por Silos
											<small>( Produzido&nbsp;Kg - Entregue&nbsp;Kg )</small>
										</h4>
										
									</div>

									<!-- INICIO FORMULARIO -->
									<div class="panel-body">
										<div class="row">
											<logic:iterate indexId="i" id="siloCorrente" name="demonstrativoProducaoForm" property="silos" type="br.com.srcsoftware.sistema.silo.silo.InformacoesSiloPOJO">
												<div class="col-xs-12 col-sm-12 col-md-12 col-lg-3">
													<label>Local</label>
													<html:text styleClass="form-control input-sm text-center bloqueado" styleId="siloArmazenagem${i}" property="silo" name="siloCorrente" />
												</div>
												<div class="col-xs-12 col-sm-12 col-md-12 col-lg-3">
													<label>Cultura</label>
													<html:text styleClass="form-control input-sm text-center bloqueado" styleId="culturaSiloArmazenagem${i}" property="cultura" name="siloCorrente" />
												</div>
												<div class="col-xs-12 col-sm-12 col-md-12 col-lg-6">
													<label>Saldo&nbsp;Kg&nbsp;(Sacas:&nbsp;${siloCorrente.getEmSacas() })</label>
													<html:text style="background-color: #acd5ec !important;" styleClass="form-control input-lg text-center bloqueado" styleId="saldoSiloArmazenagem${i}" property="saldoSilo" name="siloCorrente"
														title="Sacas:&nbsp;${siloCorrente.getEmSacas() }" />
												</div>
											</logic:iterate>
										</div>
									</div>

								</div>
							</div>
							<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">

								<div class="panel sombra">

									<div class="panel-heading cor-sistema" style="padding: 6px 0px 0px 15px; color: white; background-color: rgb(66, 66, 66) !important;">
										<h4>
											Total de Produção de Culturas por Safras											
										</h4>

									</div>

									<!-- INICIO FORMULARIO -->
									<div class="panel-body" style="overflow-y: auto; max-height: 456px;">
										<div class="table-responsive">
											<table class="table table-bordered table-striped" style="font-size: 12px;">
												<thead>

													<!-- CABEÇALHO DA TABELA -->
													<tr class="bg-danger">
														<th>Safra</th>
														<th>Cultura</th>
														<th class="text-center" style="width: 180px; min-width: 180px;">Produção</th>
														<th class="text-center" style="width: 180px; min-width: 180px;">Sacas</th>
													</tr>
												</thead>
												<tbody>
													<!-- TABELA -->
													<logic:iterate indexId="i" id="safraCorrente" name="demonstrativoProducaoForm" property="safras" type="br.com.srcsoftware.sistema.silo.entrada.InformacoesProducaoSafraPOJO">
														<tr>
															<td style="vertical-align: middle;">${safraCorrente.nomeSafra}</td>
															<td style="vertical-align: middle;">${safraCorrente.cultura}</td>
															<td style="vertical-align: middle;" class="text-right">${safraCorrente.producao}&nbsp;Kg</td>
															<td style="vertical-align: middle;" class="text-right">${safraCorrente.emSacas}</td>
														</tr>
													</logic:iterate>
												</tbody>
											</table>
										</div>
									
									
									
									
									
									
									
										<%-- <div class="row">
											<logic:iterate indexId="i" id="safraCorrente" name="demonstrativoProducaoForm" property="safras" type="br.com.srcsoftware.sistema.silo.entrada.InformacoesProducaoSafraPOJO">
												<div class="col-xs-12 col-sm-12 col-md-12 col-lg-3">
													<label>Safra</label>
													<html:text styleClass="form-control input-sm text-center bloqueado" styleId="nomeSafraSaldo${i}" property="nomeSafra" name="safraCorrente" />
												</div>
												<div class="col-xs-12 col-sm-12 col-md-12 col-lg-3">
													<label>Cultura</label>
													<html:text styleClass="form-control input-sm text-center bloqueado" styleId="culturaSafraSaldo${i}" property="cultura" name="safraCorrente" />
												</div>
												<div class="col-xs-12 col-sm-12 col-md-12 col-lg-6">
													<label>Saldo&nbsp;Kg&nbsp;(Sacas:&nbsp;${safraCorrente.getEmSacas() })</label>
													<html:text style="background-color: #acd5ec !important;" styleClass="form-control input-lg text-center bloqueado" styleId="producaoSaldo${i}" property="producao" name="safraCorrente" title="Sacas:&nbsp;${safraCorrente.getEmSacas() }" />
												</div>
											</logic:iterate>
										</div> --%>
									</div>
								</div>

							</div>
						</div>



























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

		$("#safraProducao").prop("maxlength", 100);
		$("#setorProducao").prop("maxlength", 100);
		$("#culturaProducao").prop("maxlength", 60);
		$("#variedadeProducao").prop("maxlength", 60);

		$("#safraProducao").prop("placeholder", "Safra");
		$("#setorProducao").prop("placeholder", "Setor");
		$("#culturaProducao").prop("placeholder", "Cultura");
		$("#variedadeProducao").prop("placeholder", "Variedade");

		$('#filtrarProducao').click(function() {

			var actionURL = '${contextPath}/restrito/sistema/demonstrativoProducao.src?method=filtrarDemonstrativoProducao';

			$.ajax({
				type : 'POST',
				url : actionURL,
				data : {
					"safra.id" : $('#safraProducaoSelecionada').val(),
					"safra.nome" : $('#safraProducao').val(),
					"setor.id" : $('#setorProducaoSelecionado').val(),
					"setor.nomeCompleto" : $('#setorProducao').val(),
					"cultura.id" : $('#culturaProducaoSelecionada').val(),
					"cultura.nome" : $('#culturaProducao').val(),
					"variedade.id" : $('#variedadeProducaoSelecionada').val(),
					"variedade.nome" : $('#variedadeProducao').val()
				},
				beforeSend : function(data, textStatus, XMLHttpRequest) {
					$('#processando').text('Carregando...');
				},
				success : function(data, textStatus, XMLHttpRequest) {
					$('#idPainelDemonstrativoProducao').html(data);

					$('.loader').css('display', 'none');
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(textStatus);
				}
			});

			$('.loader').css('display', 'none');
		});

		$('#limparProducao').click(function() {

			var actionURL = '${contextPath}/restrito/sistema/demonstrativoProducao.src?method=abrirPainelDemonstrativoProducao';

			$.ajax({
				type : 'POST',
				url : actionURL,
				beforeSend : function(data, textStatus, XMLHttpRequest) {
					$('#processando').text('Carregando...');
				},
				success : function(data, textStatus, XMLHttpRequest) {
					$('#idPainelDemonstrativoProducao').html(data);

					$('.loader').css('display', 'none');
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(textStatus);
				}
			});
		});

		$('#gerarPDF').click(function() {

			gerarRelatorio('form_demonstrativoProducao', 'gerarPDF');

			/* var actionURL = '${contextPath}/restrito/sistema/demonstrativoProducao.src?method=gerarPDF';

			$.ajax({
				type : 'POST',
				url : actionURL,
				data : {
					"safra.id" : $('#safraProducaoSelecionada').val(),
					"safra.nome" : $('#safraProducao').val(),
					"setor.id" : $('#setorProducaoSelecionado').val(),
					"setor.nomeCompleto" : $('#setorProducao').val(),
					"cultura.id" : $('#culturaProducaoSelecionada').val(),
					"cultura.nome" : $('#culturaProducao').val(),
					"variedade.id" : $('#variedadeProducaoSelecionada').val(),
					"variedade.nome" : $('#variedadeProducao').val()
				},
				beforeSend : function(data, textStatus, XMLHttpRequest) {
					$('#processando').text('Carregando...');
				},
				success : function(data, textStatus, XMLHttpRequest) {
					$('#idPainelDemonstrativoProducao').html(data);
										
					$('.loader').css('display', 'none');
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(textStatus);
				}
			}); */
		});

		$('#safraProducao').keyup(function() {
			if ($('#safraProducao').val() == null || $('#safraProducao').val().trim() == '') {
				$('#safraProducaoSelecionada').val(null);

				/* $('#setorProducao').val(null);
				$('#setorProducaoSelecionado').val(null);

				$('#culturaProducao').val(null);
				$('#culturaProducaoSelecionada').val(null);
				
				$('#variedadeProducao').val(null);
				$('#variedadeProducaoSelecionada').val(null); */
			}
		});

		$('#safraProducao').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'safra.nome',
			serviceUrl : '${contextPath}/restrito/sistema/demonstrativoProducao.src?method=selecionarSafraAutoComplete',
			onSelect : function(suggestion) {
				$('#safraProducaoSelecionada').val(suggestion.data);
				$("#setorProducao").focus();
			},
			onSearchComplete : function(query, suggestions) {
				//if (suggestions == null || suggestions == '') {
				$('#safraProducaoSelecionada').val(null);

				/* $('#setorProducao').val(null);
				$('#setorProducaoSelecionado').val(null);

				$('#culturaProducao').val(null);
				$('#culturaProducaoSelecionada').val(null); */
				//}
				$('#setorProducao').val(null);
				$('#setorProducaoSelecionado').val(null);

				$('#culturaProducao').val(null);
				$('#culturaProducaoSelecionada').val(null);

				$('#variedadeProducao').val(null);
				$('#variedadeProducaoSelecionada').val(null);
			}
		});

		$('#setorProducao').keyup(function() {
			if ($('#setorProducao').val() == null || $('#setorProducao').val().trim() == '') {
				$('#setorProducaoSelecionado').val(null);

				/* $('#culturaProducao').val(null);
				$('#culturaProducaoSelecionada').val(null); */
			}
		});

		$('#setorProducao').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'setor.nomeCompleto',
			params : {
				'safra.id' : function() {
					return $('#safraProducaoSelecionada').val();
				}
			},
			serviceUrl : '${contextPath}/restrito/sistema/demonstrativoProducao.src?method=selecionarSetorAutoComplete',
			onSelect : function(suggestion) {
				$('#setorProducaoSelecionado').val(suggestion.data);
				$("#culturaProducao").focus();

			},
			onSearchComplete : function(query, suggestions) {
				//if (suggestions == null || suggestions == '') {
				$('#setorProducaoSelecionado').val(null);

				/* $('#culturaProducao').val(null);
				$('#culturaProducaoSelecionada').val(null); */
				//}				
				$('#culturaProducao').val(null);
				$('#culturaProducaoSelecionada').val(null);

				$('#variedadeProducao').val(null);
				$('#variedadeProducaoSelecionada').val(null);
			}
		});

		$('#culturaProducao').keyup(function() {
			if ($('#culturaProducao').val() == null || $('#culturaProducao').val().trim() == '') {
				$('#culturaProducaoSelecionada').val(null);
			}
		});

		$('#culturaProducao').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'cultura.nome',
			params : {
				'setor.id' : function() {
					return $('#setorProducaoSelecionado').val();
				},
				'safra.id' : function() {
					return $('#safraProducaoSelecionada').val();
				}
			},
			/* params : {
				'saidaGrao.demonstrativoProducao.id' : function() {
					return $('#demonstrativoProducaoSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/demonstrativoProducao.src?method=selecionarCulturaAutoComplete',
			onSelect : function(suggestion) {
				$('#culturaProducaoSelecionada').val(suggestion.data);

				$("#variedadeProducao").focus();
			},
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			}, */
			onSearchComplete : function(query, suggestions) {
				//if (suggestions == null || suggestions == '') {
				$('#culturaProducaoSelecionada').val(null);
				//}				

				$('#variedadeProducao').val(null);
				$('#variedadeProducaoSelecionada').val(null);
			}
		});

		$('#variedadeProducao').keyup(function() {
			if ($('#variedadeProducao').val() == null || $('#variedadeProducao').val().trim() == '') {
				$('#variedadeProducaoSelecionada').val(null);
			}
		});

		$('#variedadeProducao').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'variedade.nome',
			params : {
				'setor.id' : function() {
					return $('#setorProducaoSelecionado').val();
				},
				'safra.id' : function() {
					return $('#safraProducaoSelecionada').val();
				},
				'cultura.id' : function() {
					return $('#culturaProducaoSelecionada').val();
				}
			},
			/* params : {
				'saidaGrao.demonstrativoProducao.id' : function() {
					return $('#demonstrativoProducaoSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/demonstrativoProducao.src?method=selecionarVariedadeAutoComplete',
			onSelect : function(suggestion) {
				$('#variedadeProducaoSelecionada').val(suggestion.data);
			},
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			}, */
			onSearchComplete : function(query, suggestions) {
				//if (suggestions == null || suggestions == '') {
				$('#variedadeProducaoSelecionada').val(null);
				//}
			}
		});

		recarregarObrigatorios();
	});
</script>