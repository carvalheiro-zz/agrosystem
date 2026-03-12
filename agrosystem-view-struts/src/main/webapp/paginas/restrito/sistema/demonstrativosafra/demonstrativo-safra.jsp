<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!-- CAMPOS DE CADASTRO -->
<!-- <div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<h1 class="page-header cabecalho_pagina" style="margin: 15px 0 10px;">
			<i class="fa fa-newspaper-o fa-fw" style="font-size: 26px;"></i>
			Demonstrativo de Safra Analítico
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
					Demonstrativo de Safra Analítico
				</h1>
			</div>
			<!-- INICIO FORMULARIO -->
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_demonstrativoSafra" action="restrito/sistema/demonstrativoSafra" method="post">
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
									<label>Forma de Pesquisa</label>
									<html:select styleClass="form-control input-sm" styleId="tipo" property="tipo" name="demonstrativoSafraForm">
										<html:option value="">Selecione...</html:option>
										<html:option value="Safra/Setor">Safra/Setor</html:option>
										<html:option value="Cultura">Safra/Cultura</html:option>
										<html:option value="Tudo">Safra</html:option>
									</html:select>
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3 div_safra">
									<label>Safra</label>
									<html:text styleClass="form-control input-sm" styleId="safra" property="safra.nome" name="demonstrativoSafraForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="safraSelecionada" property="safra.id" name="demonstrativoSafraForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3 div_setor">
									<label>Setor</label>
									<html:text styleClass="form-control input-sm" styleId="setor" property="setor.nomeCompleto" name="demonstrativoSafraForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="setorSelecionado" property="setor.id" name="demonstrativoSafraForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3 div_cultura">
									<label>Cultura</label>
									<html:text styleClass="form-control input-sm" styleId="cultura" property="cultura.nome" name="demonstrativoSafraForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="culturaSelecionada" property="cultura.id" name="demonstrativoSafraForm" />
								</div>
							</div>

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3">
									<button type="button" id="filtrar" style="margin-top: 23px;" class="btn btn-primary btn-sm cor-sistema btn-block">
										<i class="fa fa-search"></i>
										Filtrar
									</button>
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<button type="button" id="modalExcelTabela" style="margin-top: 23px;" class="btn btn-primary btn-sm btn-block">
										<i class="fa fa-reorder"></i>
										Abrir para Copiar
									</button>
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<button type="button" id="limparAnalitico" style="margin-top: 23px;" class="btn btn-primary btn-sm cor-sistema btn-block">
										<i class="fa fa-eraser"></i>
										Limpar
									</button>
								</div>
							</div>

							<div class="row">
								<div class="text-center col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -20px;">
									<H3>${demonstrativoSafraForm.informativoResultado }</H3>
								</div>
							</div>

							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<div class="panel panel-danger">
										<div class="panel-heading" style="font-size: 20px;">
											<i class="fa fa-money fa-fw"></i>
											Custos Diretos

											<%-- <span class="pull-right lg">
												<em>Total consultado: R$ ${demonstrativoSafraForm.custoTotalEncontrado}</em>
											</span> --%>

										</div>
										<!-- /.panel-heading -->
										<div class="panel-body">

											<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
													<div class="panel panel-danger">
														<div class="panel-heading" style="font-size: 20px;">
															<i class="fa fa-cubes fa-fw"></i>
															Detalhamento de custo com insumos

															<%-- <span class="pull-right lg">
																<em>Total consultado: R$ ${demonstrativoSafraForm.custoTotalEncontrado}</em>
															</span> --%>

														</div>
														<!-- /.panel-heading -->
														<div class="panel-body">
															<%-- <div class="table-responsive">
																<table class="table table-bordered table-striped" style="font-size: 12px;">
																	<thead>
																		<!-- CABEÇALHO DA TABELA -->
																		<tr class="bg-danger">
																			<th class="text-right" style="font-size: 16px;">Totais</th>
																			<th class="text-center" style="width: 180px; min-width: 180px; font-size: 16px;">R$ ${demonstrativoSafraForm.totalPorAlqueire}</th>
																			<th class="text-center" style="width: 180px; min-width: 180px; font-size: 16px;">R$ ${demonstrativoSafraForm.totalPorArea}</th>
																			<th class="text-center" style="width: 180px; min-width: 180px;"></th>
																		</tr>
																	</thead>
																</table>
															</div> --%>
															<div class="table-responsive">
																<table class="table table-bordered table-striped" style="font-size: 12px;">
																	<thead>
																		<tr class="bg-danger">
																			<th class="text-right" style="font-size: 16px;">Totais</th>
																			<th class="text-center" style="width: 180px; min-width: 180px; font-size: 16px;">R$ ${demonstrativoSafraForm.totalPorAlqueire}</th>
																			<th class="text-center" style="width: 180px; min-width: 180px; font-size: 16px;">R$ ${demonstrativoSafraForm.totalPorArea}</th>
																			<th class="text-center" style="width: 180px; min-width: 180px;"></th>
																		</tr>
																		<!-- CABEÇALHO DA TABELA -->
																		<tr class="bg-danger">
																			<th>Insumo</th>
																			<th class="text-center" style="width: 180px; min-width: 180px;">Custo por Alqueire</th>
																			<th class="text-center" style="width: 180px; min-width: 180px;">Custo da área</th>
																			<th class="text-center" style="width: 180px; min-width: 180px;">% Custo</th>
																		</tr>
																	</thead>
																	<tbody>
																		<!-- TABELA -->
																		<logic:iterate indexId="i" id="demonstrativoCorrente" name="demonstrativoSafraForm" property="demonstrativos" type="br.com.srcsoftware.sistema.demonstrativosafra.custodireto.insumo.CustoDiretoInsumoPOJO">

																			<tr>

																				<td style="vertical-align: middle;">${demonstrativoCorrente.nome}</td>

																				<td style="vertical-align: middle;" class="text-right">R$ ${demonstrativoCorrente.custoPorAlqueireViewToString}</td>
																				<td style="vertical-align: middle;" class="text-right">R$ ${demonstrativoCorrente.custoTotalViewToString}</td>
																				<td style="vertical-align: middle;" class="text-right">${demonstrativoCorrente.percentualCustoViewToString}&nbsp;%</td>

																			</tr>

																		</logic:iterate>
																	</tbody>
																</table>
															</div>
														</div>
													</div>
												</div>

											</div>















											<%-- <div class="table-responsive">
												<table class="table table-bordered table-striped" style="font-size: 12px;">
													<thead>
														<!-- CABEÇALHO DA TABELA -->
														<tr class="bg-danger">
															<th>Insumo</th>
															<th class="text-center" style="width: 180px; min-width: 180px;">Custo por Alqueire</th>
															<th class="text-center" style="width: 180px; min-width: 180px;">Custo da área</th>
															<th class="text-center" style="width: 180px; min-width: 180px;">% Custo</th>
														</tr>
													</thead>
													<tbody>
														<!-- TABELA -->
														<logic:iterate indexId="i" id="demonstrativoCorrente" name="demonstrativoSafraForm" property="demonstrativos" type="br.com.srcsoftware.sistema.demonstrativosafra.custodireto.insumo.CustoDiretoInsumoPOJO">

															<tr>

																<td style="vertical-align: middle;">${demonstrativoCorrente.nome}</td>

																<td style="vertical-align: middle;" class="text-right">R$ ${demonstrativoCorrente.custoPorAlqueireToString}</td>
																<td style="vertical-align: middle;" class="text-right">R$ ${demonstrativoCorrente.custoTotalToString}</td>
																<td style="vertical-align: middle;" class="text-right">${demonstrativoCorrente.percentualCusto}&nbsp;%</td>

															</tr>

														</logic:iterate>
													</tbody>
												</table>
											</div> --%>

											<%-- <div class="list-group">
												<logic:iterate indexId="i" id="demonstrativoCorrente" name="demonstrativoSafraForm" property="demonstrativos" type="br.com.srcsoftware.sistema.demonstrativosafra.custodireto.insumo.CustoDiretoInsumoPOJO">
													<a href="#" class="list-group-item">
														<i class="fa fa-dot-circle-o fa-fw"></i>
														${demonstrativoCorrente.nome }

														<span class="pull-right text-muted small">
															<em>R$ ${demonstrativoCorrente.percentualCusto }</em>
														</span>

														<span class="pull-right text-muted small">
															<em>R$ ${demonstrativoCorrente.custoTotalToString }</em>
														</span>

														<span class="pull-right text-muted small">
															<em>R$ ${demonstrativoCorrente.percentualCusto }</em>
														</span>

													</a>
												</logic:iterate>
											</div> --%>
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

		$("#safra").prop("maxlength", 100);
		$("#setor").prop("maxlength", 100);
		$("#cultura").prop("maxlength", 60);

		$("#safra").prop("placeholder", "Safra");
		$("#setor").prop("placeholder", "Setor");
		$("#cultura").prop("placeholder", "Cultura");

		$('#migrar').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_demonstrativoSafra', 'migrar');
		});

		$('#filtrar').click(function() {
			var camposNaoPreenchidos = getCamposObrigatoriosVazios();

			if (camposNaoPreenchidos == null || camposNaoPreenchidos == '') {
				var theForm = $('form[name=demonstrativoSafraForm]');
				var params = theForm.serialize();
				var actionURL = theForm.attr('action') + '?method=filtrarDemonstrativoSafraCustosDiretos';

				$.ajax({
					type : 'POST',
					url : actionURL,
					data : params,
					beforeSend : function(data, textStatus, XMLHttpRequest) {
						$('#processando').text('Carregando...');
					},
					success : function(data, textStatus, XMLHttpRequest) {
						$('#idPainelDemonstrativo').html(data);

						$('.loader').css('display', 'none');
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert(textStatus);
					}
				});
			} else {
				modalCamposObrigatorios(camposNaoPreenchidos);
			}

			$('.loader').css('display', 'none');
		});

		$('#limparAnalitico').click(function() {

			var theForm = $('form[name=demonstrativoSafraForm]');
			var params = theForm.serialize();
			var actionURL = theForm.attr('action') + '?method=abrirPainelDemonstrativo';

			$.ajax({
				type : 'POST',
				url : actionURL,
				data : params,
				beforeSend : function(data, textStatus, XMLHttpRequest) {
					$('#processando').text('Carregando...');
				},
				success : function(data, textStatus, XMLHttpRequest) {
					$('#idPainelDemonstrativo').html(data);

					$('.loader').css('display', 'none');
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(textStatus);
				}
			});

			$('.loader').css('display', 'none');
		});

		$('#safra').keyup(function() {
			if ($('#safra').val() == null || $('#safra').val().trim() == '') {
				$('#safraSelecionada').val(null);

				$('#setor').val(null);
				$('#setorSelecionado').val(null);

				$('#cultura').val(null);
				$('#culturaSelecionada').val(null);
			}
		});

		$('#safra').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'safra.nome',
			serviceUrl : '${contextPath}/restrito/sistema/demonstrativoSafra.src?method=selecionarSafraAutoComplete',
			onSelect : function(suggestion) {
				$('#safraSelecionada').val(suggestion.data);

				if ($('#tipo').val() == '') {
					$("#safra").focus();
				} else if ($('#tipo').val() == 'Safra/Setor') {
					$("#setor").focus();
				} else if ($('#tipo').val() == 'Tudo') {
					$("#imovel").focus();
				} else if ($('#tipo').val() == 'Cultura') {
					$("#cultura").focus();
				}
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#safraSelecionada').val(null);

					$('#setor').val(null);
					$('#setorSelecionado').val(null);

					$('#cultura').val(null);
					$('#culturaSelecionada').val(null);
				}
			}
		});

		$('#setor').keyup(function() {
			if ($('#setor').val() == null || $('#setor').val().trim() == '') {
				$('#setorSelecionado').val(null);

				$('#cultura').val(null);
				$('#culturaSelecionada').val(null);
			}
		});

		$('#setor').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'setor.nomeCompleto',
			params : {
				'safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			},
			serviceUrl : '${contextPath}/restrito/sistema/demonstrativoSafra.src?method=selecionarSetorAutoComplete',
			onSelect : function(suggestion) {
				$('#setorSelecionado').val(suggestion.data);

				if ($('#tipo').val() == '') {
					$("#safra").focus();
				} else if ($('#tipo').val() == 'Safra/Setor') {
					$("#imovel").focus();
				} else if ($('#tipo').val() == 'Tudo') {
					$("#safra").focus();
				} else if ($('#tipo').val() == 'Cultura') {
					$("#cultura").focus();
				}
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#setorSelecionado').val(null);

					$('#cultura').val(null);
					$('#culturaSelecionada').val(null);
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
			paramName : 'cultura.nome',
			params : {
				'setor.id' : function() {
					return $('#setorSelecionado').val();
				},
				'safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			},
			/* params : {
				'saidaGrao.demonstrativoSafra.id' : function() {
					return $('#demonstrativoSafraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/demonstrativoSafra.src?method=selecionarCulturaAutoComplete',
			onSelect : function(suggestion) {
				$('#culturaSelecionada').val(suggestion.data);

				$("#imovel").focus();
			},
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			}, */
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#culturaSelecionada').val(null);
				}
			}
		});

		$('#tipo').change(function() {
			$('#safra').val(null);
			$('#safraSelecionada').val(null);
			$('#setor').val(null);
			$('#setorSelecionado').val(null);
			$('#cultura').val(null);
			$('#culturaSelecionada').val(null);
			gerenciarCamposSafraSetorCultura();
		});

		$('#modalExcelTabela').on('click', function() {

			var actionURL = '${contextPath}/restrito/sistema/demonstrativoSafra.src?method=abrirModalDadosExcel';

			$.ajax({
				type : 'POST',
				url : actionURL,
				success : function(data, textStatus, XMLHttpRequest) {

					BootstrapDialog.show({
						size : BootstrapDialog.SIZE_WIDE,
						title : 'Demonstrativo Analítico para Exportação',
						message : $('<div id="id-modalExcelTabela"></div>').append(data),
						type : BootstrapDialog.TYPE_SUCCESS, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
						closable : false, // <-- Default value is false
						draggable : true, // <-- Default value is false
						onshown : function(dialogRef) {
							/* Foco inicial */
						},
						buttons : [ {
							label : 'Fechar',
							action : function(dialogRef) {
								dialogRef.close();
							}
						} ]
					});
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(textStatus);
				}
			});
		});

		gerenciarCamposSafraSetorCultura();
	});

	function gerenciarCamposSafraSetorCultura() {
		if ($('#tipo').val() == '') {
			$("#safra").removeClass("obrigatorio");
			$("#cultura").removeClass("obrigatorio");
			$("#setor").removeClass("obrigatorio");

			/* $("#safra").addClass("bloqueado");
			$("#cultura").addClass("bloqueado");
			$("#setor").addClass("bloqueado"); */
			$(".div_safra").css("display", "none");
			$(".div_cultura").css("display", "none");
			$(".div_setor").css("display", "none");

			$('#safra').val(null);
			$('#safraSelecionada').val(null);
			$('#setor').val(null);
			$('#setorSelecionado').val(null);
			$('#cultura').val(null);
			$('#variedade').val(null);
			$('#culturaSelecionada').val(null);

		} else if ($('#tipo').val() == 'Safra/Setor') {
			$("#safra").addClass("obrigatorio");
			$("#setor").addClass("obrigatorio");

			//$("#safra").removeClass("bloqueado");
			//$("#setor").removeClass("bloqueado");
			$(".div_safra").css("display", "block");
			$(".div_setor").css("display", "block");

			$("#cultura").removeClass("obrigatorio");
			//$("#cultura").addClass("bloqueado");
			$(".div_cultura").css("display", "none");

			$("#safra").focus();
		} else if ($('#tipo').val() == 'Tudo') {
			$("#safra").addClass("obrigatorio");

			//$("#safra").removeClass("bloqueado");
			$(".div_safra").css("display", "block");

			$("#setor").removeClass("obrigatorio");
			//$("#setor").addClass("bloqueado");
			$(".div_setor").css("display", "none");

			$("#cultura").removeClass("obrigatorio");
			//$("#cultura").addClass("bloqueado");
			$(".div_cultura").css("display", "none");

			$("#safra").focus();
		} else if ($('#tipo').val() == 'Cultura') {
			$("#safra").addClass("obrigatorio");
			$("#cultura").addClass("obrigatorio");

			//$("#safra").removeClass("bloqueado");
			//$("#cultura").removeClass("bloqueado");
			$(".div_safra").css("display", "block");
			$(".div_cultura").css("display", "block");

			$("#setor").removeClass("obrigatorio");
			//$("#setor").addClass("bloqueado");
			$(".div_setor").css("display", "none");

			$("#safra").focus();
		}

		recarregarObrigatorios();
	}
</script>