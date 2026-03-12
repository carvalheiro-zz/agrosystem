<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!-- CAMPOS DE CADASTRO -->
<!-- <div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<h1 class="page-header cabecalho_pagina" style="margin: 15px 0 10px;">
			<i class="fa fa-newspaper-o fa-fw" style="font-size: 26px;"></i>
			Demonstrativo de Safra Sintético
		</h1>
	</div>
</div> -->

<jsp:include page="../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<div class="row">
	<!-- Define o tamanho geral da cultura -->
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel sombra">

			<div class="panel-heading">
				<h1 class="page-header cabecalho_pagina" style="margin: 15px 0 -10px;">
					<i class="fa fa-newspaper-o fa-fw" style="font-size: 26px;"></i>
					Demonstrativo de Safra Sintético
				</h1>
			</div>

			<!-- INICIO FORMULARIO -->
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_demonstrativoSafraSintetico" action="restrito/sistema/demonstrativoSafraSintetico" method="post">
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
									<html:select styleClass="form-control input-sm" styleId="tipoSintetico" property="tipo" name="demonstrativoSafraSinteticoForm">
										<html:option value="">Selecione...</html:option>
										<html:option value="Safra/Setor">Safra/Setor</html:option>
										<html:option value="Cultura">Safra/Cultura</html:option>
										<html:option value="Tudo">Safra</html:option>
									</html:select>
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3 div_safraSintetico">
									<label>Safra</label>
									<html:text styleClass="form-control input-sm" styleId="safraSintetico" property="safra.nome" name="demonstrativoSafraSinteticoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="safraSelecionadaSintetico" property="safra.id" name="demonstrativoSafraSinteticoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3 div_setorSintetico">
									<label>Setor</label>
									<html:text styleClass="form-control input-sm" styleId="setorSintetico" property="setor.nomeCompleto" name="demonstrativoSafraSinteticoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="setorSelecionadoSintetico" property="setor.id" name="demonstrativoSafraSinteticoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3 div_culturaSintetico">
									<label>Cultura</label>
									<html:text styleClass="form-control input-sm" styleId="culturaSintetico" property="cultura.nome" name="demonstrativoSafraSinteticoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="culturaSelecionadaSintetico" property="cultura.id" name="demonstrativoSafraSinteticoForm" />
								</div>
							</div>

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3">
									<button type="button" id="filtrarSintetico" style="margin-top: 23px;" class="btn btn-primary btn-sm cor-sistema btn-block">
										<i class="fa fa-search"></i>
										Filtrar
									</button>
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<button type="button" id="limparSintetico" style="margin-top: 23px;" class="btn btn-primary btn-sm cor-sistema btn-block">
										<i class="fa fa-eraser"></i>
										Limpar
									</button>
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<button type="button" id="modalExcelTabelaSintetica" style="margin-top: 23px;" class="btn btn-primary btn-sm btn-block">
										<i class="fa fa-reorder"></i>
										Abrir para Copiar
									</button>
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<button type="button" id="gerarPdfSintetico" style="margin-top: 23px;" class="btn btn-primary btn-sm btn-block">
										<i class="fa fa-file-pdf-o"></i>
										Gerar PDF
									</button>
								</div>
							</div>


							<div class="row">
								<div class="text-center col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -20px;">
									<H3>${demonstrativoSafraSinteticoForm.informativoResultado }</H3>
								</div>
							</div>

							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<div class="panel panel-danger">
										<div class="panel-heading" style="font-size: 20px;">
											<i class="fa fa-money fa-fw"></i>
											Custos Diretos
										</div>
										<!-- /.panel-heading -->
										<div class="panel-body">

											<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
													<div class="panel panel-danger">
														<div class="panel-heading" style="font-size: 20px;">
															<i class="fa fa-cubes fa-fw"></i>
															Detalhamento de custo por Setor
														</div>
														<!-- /.panel-heading -->
														<div class="panel-body">
															<div class="table-responsive">
																<table class="table table-bordered table-striped" style="font-size: 12px;">
																	<thead>
																		<tr class="bg-danger">
																			<th class="text-right" style="font-size: 16px;" colspan="2">Totais</th>
																			<th class="text-center" style="width: 180px; min-width: 180px; font-size: 16px;">R$ ${demonstrativoSafraSinteticoForm.totalArea}</th>
																			<th class="text-center" style="width: 180px; min-width: 180px; font-size: 16px;">R$ ${demonstrativoSafraSinteticoForm.totalPorAlqueire}</th>
																			<th class="text-center" style="width: 180px; min-width: 180px; font-size: 16px;">R$ ${demonstrativoSafraSinteticoForm.totalPorArea}</th>
																		</tr>

																		<!-- CABEÇALHO DA TABELA -->
																		<tr class="bg-danger">
																			<th class="text-center">Setor</th>
																			<th class="text-center">Variedade</th>
																			<th class="text-center">Área em Alqueire</th>
																			<th class="text-center" style="width: 180px; min-width: 180px;">Custo por Alqueire</th>
																			<th class="text-center" style="width: 180px; min-width: 180px;">Custo da área</th>
																		</tr>
																	</thead>
																	<tbody>
																		<!-- TABELA -->
																		<logic:iterate indexId="i" id="sinteticoCorrente" name="demonstrativoSafraSinteticoForm" property="sinteticos" type="br.com.srcsoftware.sistema.demonstrativosafra.custodireto.insumo.sintetico.CustoDiretoInsumoSinteticoPOJO">

																			<tr>
																				<td style="vertical-align: middle;" class="text-center">${sinteticoCorrente.setor}</td>
																				<td style="vertical-align: middle;" class="text-center">${sinteticoCorrente.variedade}</td>
																				<td style="vertical-align: middle;" class="text-center">${sinteticoCorrente.areaTotal}</td>
																				<td style="vertical-align: middle;" class="text-center">R$&nbsp;${sinteticoCorrente.totalPorAlqueireViewToString}</td>
																				<td style="vertical-align: middle;" class="text-center">R$&nbsp;${sinteticoCorrente.totalPorAreaViewToString}</td>

																				<%-- <td style="vertical-align: middle;">${sinteticoCorrente.informativoResultado}</td> --%>
																			</tr>

																		</logic:iterate>
																	</tbody>
																</table>
															</div>
														</div>
													</div>
												</div>

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

		$("#safraSintetico").prop("maxlength", 100);
		$("#setorSintetico").prop("maxlength", 100);
		$("#culturaSintetico").prop("maxlength", 60);

		$("#safraSintetico").prop("placeholder", "Safra");
		$("#setorSintetico").prop("placeholder", "Setor");
		$("#culturaSintetico").prop("placeholder", "Cultura");

		$('#filtrarSintetico').click(function() {
			var camposNaoPreenchidos = getCamposObrigatoriosVaziosSintetico();

			if (camposNaoPreenchidos == null || camposNaoPreenchidos == '') {
				var theForm = $('form[name=demonstrativoSafraSinteticoForm]');
				var params = theForm.serialize();
				var actionURL = theForm.attr('action') + '?method=filtrarDemonstrativoSafraCustosDiretosSintetico';

				$.ajax({
					type : 'POST',
					url : actionURL,
					data : params,
					beforeSend : function(data, textStatus, XMLHttpRequest) {
						$('#processando').text('Carregando...');
					},
					success : function(data, textStatus, XMLHttpRequest) {
						$('#idPainelDemonstrativoSintetico').html(data);

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

		function getCamposObrigatoriosVaziosSintetico() {
			var camposNaoPreenchidos = '';

			/* Validando os campos Obrigatorios */
			$(".obrigatorioSintetico").each(function(index, element) {
				if ($(element).val() == null || $(element).val() == '') {

					camposNaoPreenchidos = camposNaoPreenchidos + ' / ' + $(element).attr("id")
				}
			});

			return camposNaoPreenchidos;
		}

		$('#limparSintetico').click(function() {

			var theForm = $('form[name=demonstrativoSafraSinteticoForm]');
			var params = theForm.serialize();
			var actionURL = theForm.attr('action') + '?method=abrirPainelDemonstrativoSintetico';

			$.ajax({
				type : 'POST',
				url : actionURL,
				data : params,
				beforeSend : function(data, textStatus, XMLHttpRequest) {
					$('#processando').text('Carregando...');
				},
				success : function(data, textStatus, XMLHttpRequest) {
					$('#idPainelDemonstrativoSintetico').html(data);

					$('.loader').css('display', 'none');
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(textStatus);
				}
			});

			$('.loader').css('display', 'none');
		});

		$('#gerarPdfSintetico').click(function() {
			gerarRelatorio('form_demonstrativoSafraSintetico', 'gerarPdfSintetico');
		});

		$('#safraSintetico').keyup(function() {
			if ($('#safraSintetico').val() == null || $('#safraSintetico').val().trim() == '') {
				$('#safraSelecionadaSintetico').val(null);

				$('#setorSintetico').val(null);
				$('#setorSelecionadoSintetico').val(null);

				$('#cultura').val(null);
				$('#culturaSelecionadaSintetico').val(null);
			}
		});

		$('#safraSintetico').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'safra.nome',
			serviceUrl : '${contextPath}/restrito/sistema/demonstrativoSafraSintetico.src?method=selecionarSafraAutoComplete',
			onSelect : function(suggestion) {
				$('#safraSelecionadaSintetico').val(suggestion.data);

				if ($('#tipoSintetico').val() == '') {
					$("#safraSintetico").focus();
				} else if ($('#tipoSintetico').val() == 'Safra/Setor') {
					$("#setorSintetico").focus();
				} else if ($('#tipoSintetico').val() == 'Tudo') {
					$("#safraSintetico").focus();
				} else if ($('#tipoSintetico').val() == 'Cultura') {
					$("#culturaSintetico").focus();
				}
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#safraSelecionadaSintetico').val(null);

					$('#setorSintetico').val(null);
					$('#setorSelecionadoSintetico').val(null);

					$('#culturaSintetico').val(null);
					$('#culturaSelecionadaSintetico').val(null);
				}
			}
		});

		$('#setorSintetico').keyup(function() {
			if ($('#setorSintetico').val() == null || $('#setorSintetico').val().trim() == '') {
				$('#setorSelecionadoSintetico').val(null);

				$('#culturaSintetico').val(null);
				$('#culturaSelecionadaSintetico').val(null);
			}
		});

		$('#setorSintetico').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'setor.nomeCompleto',
			params : {
				'safra.id' : function() {
					return $('#safraSelecionadaSintetico').val();
				}
			},
			serviceUrl : '${contextPath}/restrito/sistema/demonstrativoSafraSintetico.src?method=selecionarSetorAutoComplete',
			onSelect : function(suggestion) {
				$('#setorSelecionadoSintetico').val(suggestion.data);

				if ($('#tipoSintetico').val() == '') {
					$("#safraSintetico").focus();
				} else if ($('#tipoSintetico').val() == 'Safra/Setor') {
					$("#safraSintetico").focus();
				} else if ($('#tipoSintetico').val() == 'Tudo') {
					$("#safraSintetico").focus();
				} else if ($('#tipoSintetico').val() == 'Cultura') {
					$("#culturaSintetico").focus();
				}
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#setorSelecionadoSintetico').val(null);

					$('#culturaSintetico').val(null);
					$('#culturaSelecionadaSintetico').val(null);
				}
			}
		});

		$('#culturaSintetico').keyup(function() {
			if ($('#culturaSintetico').val() == null || $('#culturaSintetico').val().trim() == '') {
				$('#culturaSelecionadaSintetico').val(null);
			}
		});

		$('#culturaSintetico').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'cultura.nome',
			params : {
				'setor.id' : function() {
					return $('#setorSelecionadoSintetico').val();
				},
				'safra.id' : function() {
					return $('#safraSelecionadaSintetico').val();
				}
			},
			/* params : {
				'saidaGrao.demonstrativoSafra.id' : function() {
					return $('#demonstrativoSafraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/demonstrativoSafraSintetico.src?method=selecionarCulturaAutoComplete',
			onSelect : function(suggestion) {
				$('#culturaSelecionadaSintetico').val(suggestion.data);
			},
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			}, */
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#culturaSelecionadaSintetico').val(null);
				}
			}
		});

		$('#tipoSintetico').change(function() {
			$('#safraSintetico').val(null);
			$('#safraSelecionadaSintetico').val(null);
			$('#setorSintetico').val(null);
			$('#setorSelecionadoSintetico').val(null);
			$('#culturaSintetico').val(null);
			$('#culturaSelecionadaSintetico').val(null);
			gerenciarCamposSafraSetorCulturaSintetico();
		});

		gerenciarCamposSafraSetorCulturaSintetico();

		$('#modalExcelTabelaSintetica').on('click', function() {

			var actionURL = '${contextPath}/restrito/sistema/demonstrativoSafraSintetico.src?method=abrirModalDadosExcel';

			$.ajax({
				type : 'POST',
				url : actionURL,
				success : function(data, textStatus, XMLHttpRequest) {

					BootstrapDialog.show({
						size : BootstrapDialog.SIZE_WIDE,
						title : 'Demonstrativo Sintético para Exportação',
						message : $('<div id="id-modalExcelTabelaSintetica"></div>').append(data),
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
	});

	function gerenciarCamposSafraSetorCulturaSintetico() {
		if ($('#tipoSintetico').val() == '') {
			$("#safraSintetico").removeClass("obrigatorio");
			$("#culturaSintetico").removeClass("obrigatorio");
			$("#culturaSintetico").removeClass("obrigatorioSintetico");
			$("#setorSintetico").removeClass("obrigatorio");

			/* $("#safra").addClass("bloqueado");
			$("#cultura").addClass("bloqueado");
			$("#setor").addClass("bloqueado"); */
			$(".div_safraSintetico").css("display", "none");
			$(".div_culturaSintetico").css("display", "none");
			$(".div_setorSintetico").css("display", "none");

			$('#safraSintetico').val(null);
			$('#safraSelecionadaSintetico').val(null);
			$('#setorSintetico').val(null);
			$('#setorSelecionadoSintetico').val(null);
			$('#culturaSintetico').val(null);
			$('#variedadeSintetico').val(null);
			$('#culturaSelecionadaSintetico').val(null);

		} else if ($('#tipoSintetico').val() == 'Safra/Setor') {
			$("#safraSintetico").addClass("obrigatorio");
			$("#setorSintetico").addClass("obrigatorio");

			//$("#safra").removeClass("bloqueado");
			//$("#setor").removeClass("bloqueado");
			$(".div_safraSintetico").css("display", "block");
			$(".div_setorSintetico").css("display", "block");

			$("#culturaSintetico").removeClass("obrigatorio");
			$("#culturaSintetico").removeClass("obrigatorioSintetico");
			//$("#cultura").addClass("bloqueado");
			$(".div_culturaSintetico").css("display", "none");

			$("#safraSintetico").focus();
		} else if ($('#tipoSintetico').val() == 'Tudo') {
			$("#safraSintetico").addClass("obrigatorio");

			//$("#safra").removeClass("bloqueado");
			$(".div_safraSintetico").css("display", "block");

			$("#setorSintetico").removeClass("obrigatorio");
			//$("#setor").addClass("bloqueado");
			$(".div_setorSintetico").css("display", "none");

			$("#culturaSintetico").removeClass("obrigatorio");
			$("#culturaSintetico").removeClass("obrigatorioSintetico");
			//$("#cultura").addClass("bloqueado");
			$(".div_culturaSintetico").css("display", "none");

			$("#safraSintetico").focus();
		} else if ($('#tipoSintetico').val() == 'Cultura') {
			$("#safraSintetico").addClass("obrigatorio");
			$("#culturaSintetico").addClass("obrigatorio");
			$("#culturaSintetico").addClass("obrigatorioSintetico");

			//$("#safra").removeClass("bloqueado");
			//$("#cultura").removeClass("bloqueado");
			$(".div_safraSintetico").css("display", "block");
			$(".div_culturaSintetico").css("display", "block");

			$("#setorSintetico").removeClass("obrigatorio");
			//$("#setor").addClass("bloqueado");
			$(".div_setorSintetico").css("display", "none");

			$("#safraSinteticop").focus();
		}

		recarregarObrigatorios();
	}
</script>