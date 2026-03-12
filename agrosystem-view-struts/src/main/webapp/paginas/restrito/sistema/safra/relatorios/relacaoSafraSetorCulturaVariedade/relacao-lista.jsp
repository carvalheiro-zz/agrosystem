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
			Relatório Relação Safra/Variedades
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Listagem das Variedades
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<div class="row">

	<!-- Define o tamanho geral da tela -->
	<div class="col-md-offset-1 col-lg-offset-2 col-xs-12 col-sm-12 col-md-10 col-lg-8">
		<div class="panel" style="box-shadow: 0 10px 30px rgba(0, 0, 0, 0.8); border-color: #909090;">

			<!-- INICIO CAMPOS DE PESQUISA -->
			<div class="panel-heading cor-sistema">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_relacaoSafraSetorCulturaVariedade_consulta" action="restrito/sistema/relacaoSafraSetorCulturaVariedade" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3">
									<label class="text-white">Safra</label>
									<html:text styleClass="form-control input-sm" styleId="safra" property="safra.nome" name="relacaoSafraSetorCulturaVariedadeForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="safraSelecionada" property="safra.id" name="relacaoSafraSetorCulturaVariedadeForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3">
									<label class="text-white">Cultura</label>
									<html:text styleClass="form-control input-sm" styleId="cultura" property="cultura.nome" name="relacaoSafraSetorCulturaVariedadeForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="culturaSelecionada" property="cultura.id" name="relacaoSafraSetorCulturaVariedadeForm" />
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-12 col-sm-3 col-md-2 col-lg-2" style="margin-bottom: 0px;">
									<button type="submit" id="pesquisar" class="btn btn-success btn-sm cor-sistema btn-block">
										<i class="fa fa-search"></i>
										Pesquisar
									</button>
								</div>
								<div class="form-group col-xs-12 col-sm-3 col-md-2 col-lg-2" style="margin-bottom: 0px;">
									<button type="button" id="limparPesquisa" class="btn btn-success btn-sm cor-sistema btn-block">
										<i class="glyphicon glyphicon-erase"></i>
										Limpar
									</button>
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2" style="margin-bottom: 0px;">
									<button type="button" id="gerarPDF" class="btn btn-primary btn-sm btn-block">
										<i class="fa fa-file-pdf-o"></i>
										Gerar PDF
									</button>
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2" style="margin-bottom: 0px;">
									<button type="button" id="gerarXLS" class="btn btn-primary btn-sm btn-block">
										<i class="fa fa-file-excel-o"></i>
										Gerar XLS
									</button>
								</div>

							</div>
						</html:form>
					</div>
				</div>
			</div>
			<!-- TERMINO CAMPOS DE PESQUISA -->

			<!-- INICIO TABELA -->
			<logic:empty name="relacaoSafraSetorCulturaVariedadeForm" property="dados">
				<div class="row" style="margin-top: 15px;">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="alert alert-warning">Nenhum registro encontrado!</div>
					</div>
				</div>
			</logic:empty>
			<logic:notEmpty name="relacaoSafraSetorCulturaVariedadeForm" property="dados">
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
							<thead>
								<!-- CABEÇALHO DA TABELA -->
								<tr class="cor-sistema" style="color: white;">
									<th>Safra</th>
									<th>Setor</th>
									<th>Variedade</th>
									<th>Cultura</th>
									<th class="text-center">Alqueires</th>
								</tr>
							</thead>
							<tbody>
								<!-- TABELA -->
								<logic:iterate id="relacaoSafraSetorCulturaVariedadeCorrente" name="relacaoSafraSetorCulturaVariedadeForm" property="dados" type="br.com.srcsoftware.sistema.safra.relatorios.RelacaoSafraSetorCulturaVariedadePOJO">
									<tr>
										<!-- success / info / warning / danger -->
										<td>${relacaoSafraSetorCulturaVariedadeCorrente.safra}</td>
										<td>${relacaoSafraSetorCulturaVariedadeCorrente.setor}</td>
										<td>${relacaoSafraSetorCulturaVariedadeCorrente.variedade}</td>
										<td>${relacaoSafraSetorCulturaVariedadeCorrente.cultura}</td>
										<td class="text-right">${relacaoSafraSetorCulturaVariedadeCorrente.areaAlqueireToString}</td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</div>
				</div>
			</logic:notEmpty>
			<!-- TERMINO TABELA -->
			<!-- /.panel-body -->

		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>


<script type="text/javascript">
	$(document).ready(function() {
		/* Foco inicial */
		$("#safra").focus();

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#safra").prop("maxlength", 50);
		$("#cultura").prop("maxlength", 50);

		/* Setando os placeholder dos campos*/
		$("#safra").prop("placeholder", "Safra a ser pesquisado");
		$("#cultura").prop("placeholder", "Cultura a ser pesquisado");

		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_relacaoSafraSetorCulturaVariedade_consulta").prop("autocomplete", "off");

		$('#form_relacaoSafraSetorCulturaVariedade_consulta').on('submit', function(e) {
			abrirModalProcessando();
		});

		$('#pesquisar').click(function() {
			executar('form_relacaoSafraSetorCulturaVariedade_consulta', 'filtrar');
		});

		$('#limparPesquisa').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_relacaoSafraSetorCulturaVariedade_consulta', 'abrirListagem');
		});

		$('#gerarPDF').click(function() {
			gerarRelatorio('form_relacaoSafraSetorCulturaVariedade_consulta', 'gerarPDF');
		});

		$('#gerarXLS').click(function() {
			gerarRelatorio('form_relacaoSafraSetorCulturaVariedade_consulta', 'gerarXLS');
		});

		$('#safra').keyup(function() {
			if ($('#safra').val() == null || $('#safra').val().trim() == '') {
				$('#safraSelecionada').val(null);

				$('#cultura').val(null);
				$('#culturaSelecionada').val(null);
			}
		});

		$('#safra').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'safra.nome',
			serviceUrl : '${contextPath}/restrito/sistema/relacaoSafraSetorCulturaVariedade.src?method=selecionarSafraAutoComplete',
			onSelect : function(suggestion) {
				$('#safraSelecionada').val(suggestion.data);
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#safraSelecionada').val(null);

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
				'safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			},
			/* params : {
				'saidaGrao.demonstrativoSafra.id' : function() {
					return $('#demonstrativoSafraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/relacaoSafraSetorCulturaVariedade.src?method=selecionarCulturaAutoComplete',
			onSelect : function(suggestion) {
				$('#culturaSelecionada').val(suggestion.data);
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
	});
</script>