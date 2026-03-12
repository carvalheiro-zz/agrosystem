<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!-- CAMPOS DE CADASTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -30px; margin-bottom: -15px;">
		<h1 class="page-header cabecalho_pagina">
			<i class="fa fa-graduation-cap" style="font-size: 26px;"></i>
			Cadastrar ${ajusteSiloForm.ajusteSilo.tipo}
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Lançamento de ${ajusteSiloForm.ajusteSilo.tipo}
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<logic:present property="ajusteSilo.id" name="ajusteSiloForm">
	<div class="row">
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px;">
			<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-user-plus"></i>
				&nbsp;${ajusteSiloForm.ajusteSilo.nomeUsuarioCriacao }
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
			<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-calendar-plus-o"></i>
				&nbsp;${ajusteSiloForm.ajusteSilo.dataOcorrenciaCriacao}
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
			<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-refresh"></i>
				&nbsp;${ajusteSiloForm.ajusteSilo.nomeUsuarioAlteracao}
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-left: 0px;">
			<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-calendar-o"></i>
				&nbsp;${ajusteSiloForm.ajusteSilo.dataOcorrenciaAlteracao}
			</div>
		</div>
	</div>
</logic:present>

<div class="row">
	<!-- Define o tamanho geral da ajusteSilo -->
	<div class="col-md-offset-1 col-lg-offset-1 col-xs-12 col-sm-12 col-md-10 col-lg-10">
		<html:form styleId="form_ajusteSilo" action="restrito/sistema/ajusteSilo" method="post">
			<html:hidden property="method" value="empty" />

			<div class="panel sombra">

				<!-- INICIO FORMULARIO -->
				<div class="panel-body">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<html:text styleClass="form-control input-lg bloqueado text-center" styleId="tipo" property="ajusteSilo.tipo" name="ajusteSiloForm" />
								</div>
								<%-- <div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-4">
									<label>Tipo</label>
									<html:select styleClass="form-control input-sm" styleId="tipo" property="ajusteSilo.tipo" name="ajusteSiloForm">
										<html:option value="">Selecione...</html:option>
										<html:option value="Sobra de Classificação">Sobra de Classificação (+)</html:option>
										<html:option value="Quebra de Classificação">Quebra de Classificação (-)</html:option>
									</html:select>
								</div> --%>
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<label>Lançador</label>
									<html:text styleClass="form-control input-sm" styleId="lancador" property="ajusteSilo.lancador.pessoaFisica.razaoSocial" name="ajusteSiloForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="lancadorSelecionado" property="ajusteSilo.lancador.id" name="ajusteSiloForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
									<label>Data</label>
									<html:text styleClass="form-control input-sm text-center data focoInicial" styleId="data" property="ajusteSilo.data" name="ajusteSiloForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3">
									<label>Local Armazenagem</label>
									<html:select styleClass="form-control input-sm" styleId="localArmazenagem" property="ajusteSilo.localArmazenagem.id" name="ajusteSiloForm">
										<html:option value="">Selecione...</html:option>
										<html:optionsCollection name="ajusteSiloForm" property="comboSilos" label="label" value="value" />
									</html:select>
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3">
									<label>Safra</label>
									<html:text styleClass="form-control input-sm" styleId="safra" property="ajusteSilo.safra.nome" name="ajusteSiloForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="safraSelecionada" property="ajusteSilo.safra.id" name="ajusteSiloForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label>Cultura</label>
									<html:text styleClass="form-control input-sm" styleId="cultura" property="ajusteSilo.cultura.nome" name="ajusteSiloForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="culturaSelecionada" property="ajusteSilo.cultura.id" name="ajusteSiloForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label>Motivo</label>
									<html:text styleClass="form-control input-sm text-center" styleId="motivo" property="ajusteSilo.motivo" name="ajusteSiloForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label>Peso Liquido</label>
									<html:text styleClass="form-control input-sm text-center decimal calcularLiquidoSacas" styleId="quantidade" property="ajusteSilo.quantidade" name="ajusteSiloForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3">
									<label>Sacas</label>
									<html:text styleClass="form-control input-lg text-center decimal bloqueado" styleId="emSacas" property="ajusteSilo.emSacas" name="ajusteSiloForm" />
								</div>


							</div>
						</div>

					</div>
				</div>
				<div class="panel-footer">
					<div class="row">
						<!-- BOTOES -->
						<logic:notPresent property="ajusteSilo.id" name="ajusteSiloForm">
							<logic:equal name="ajusteSiloForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.ajusteSilo.inserir)">
								<div class="form-group col-xs-12 col-sm-3 col-md-4 col-lg-4" style="margin-bottom: 0px;">
									<button type="submit" id="inserir" class="btn btn-success btn-sm cor-sistema btn-block">
										<i class="fa fa-save"></i>
										Inserir
									</button>
								</div>
							</logic:equal>
						</logic:notPresent>
						<logic:present property="ajusteSilo.id" name="ajusteSiloForm">
							<logic:equal name="ajusteSiloForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.ajusteSilo.alterar)">
								<div class="form-group col-xs-12 col-sm-3 col-md-4 col-lg-4" style="margin-bottom: 0px;">
									<button type="submit" id="alterar" class="btn btn-success btn-sm cor-sistema btn-block">
										<i class="fa fa-edit"></i>
										Alterar
									</button>
								</div>
							</logic:equal>
						</logic:present>

						<div class="form-group ol-xs-12 col-sm-3 col-md-4 col-lg-4" style="margin-bottom: 0px;">
							<button type="button" id="limpar" class="btn btn-success btn-sm cor-sistema btn-block">
								<i class="glyphicon glyphicon-erase"></i>
								Limpar
							</button>
						</div>

						<logic:equal name="ajusteSiloForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.ajusteSilo.filtrar)">
							<div class="form-group col-xs-12 col-sm-3 col-md-4 col-lg-4" style="margin-bottom: 0px;">
								<button type="button" id="listagem" class="btn btn-success btn-sm cor-sistema btn-block">
									<i class="fa fa-search"></i>
									Listagem
								</button>
							</div>
						</logic:equal>

					</div>
				</div>
				<!-- TERMINO FORMULARIO -->
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</html:form>
	</div>
	<!-- /.col-lg-12 -->
</div>


<script type="text/javascript">
	$(document).ready(function() {
		/* Foco inicial */
		$(".focoInicial").focus();

		/* Setando NOTNULL nos campos*/
		$("#tipo").addClass("obrigatorio");
		$("#lancador").addClass("obrigatorio");
		$("#data").addClass("obrigatorio");
		$("#safra").addClass("obrigatorio");
		$("#cultura").addClass("obrigatorio");
		$("#localArmazenagem").addClass("obrigatorio");
		$("#quantidade").addClass("obrigatorio");

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#tipo").prop("maxlength", 30);
		$("#lancador").prop("maxlength", 100);
		$("#data").prop("maxlength", 12);
		$("#safra").prop("maxlength", 20);
		$("#cultura").prop("maxlength", 20);
		$("#localArmazenagem").prop("maxlength", 20);
		$("#quantidade").prop("maxlength", 10);

		/* Setando os placeholder dos campos*/
		$("#lancador").prop("placeholder", "Lançador");
		$("#safra").prop("placeholder", "Safra");
		$("#cultura").prop("placeholder", "Cultura");

		/* EVENTOS */

		$('.calcularLiquidoSacas').focusout(function() {

			if ($('#quantidade').val() != null && $('#quantidade').val() != '') {

				var theForm = $('form[name=ajusteSiloForm]');
				var params = theForm.serialize();
				var actionURL = theForm.attr('action') + '?method=calcularLiquidoSacas';
				$.ajax({
					type : 'POST',
					url : actionURL,
					data : params,
					success : function(data, textStatus, XMLHttpRequest) {
						$('#quantidade').val(data.quantidade);
						$('#emSacas').val(data.emSacas);
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert(textStatus);
						$('#quantidade').val(null);
						$('#emSacas').val(null);
					}
				});
			}
		});

		// Desliga o auto-complete da pagina
		$("#form_ajusteSilo").prop("autocomplete", "off");

		$('#form_ajusteSilo').on('submit', function(e) {
			abrirModalProcessando();
		});

		$('#inserir').click(function() {
			executar('form_ajusteSilo', 'inserir');
		});

		$('#alterar').click(function() {
			executar('form_ajusteSilo', 'alterar');
		});

		$('#limpar').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_ajusteSilo', 'limpar');
		});

		$('#listagem').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_ajusteSilo', 'abrirListagem');
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
</script>