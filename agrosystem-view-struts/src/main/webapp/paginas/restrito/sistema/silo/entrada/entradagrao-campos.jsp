<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!-- CAMPOS DE CADASTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -30px; margin-bottom: -15px;">
		<h1 class="page-header cabecalho_pagina">
			<i class="fa fa-graduation-cap" style="font-size: 26px;"></i>
			Cadastrar Entradas no Silo
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Cadastro das Entradas no Silo
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<logic:present property="entradaGrao.id" name="entradaGraoForm">
	<div class="row">
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px;">
			<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-user-plus"></i>
				&nbsp;${entradaGraoForm.entradaGrao.nomeUsuarioCriacao }
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
			<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-calendar-plus-o"></i>
				&nbsp;${entradaGraoForm.entradaGrao.dataOcorrenciaCriacao}
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
			<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-refresh"></i>
				&nbsp;${entradaGraoForm.entradaGrao.nomeUsuarioAlteracao}
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-left: 0px;">
			<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-calendar-o"></i>
				&nbsp;${entradaGraoForm.entradaGrao.dataOcorrenciaAlteracao}
			</div>
		</div>
	</div>
</logic:present>

<div class="row">
	<!-- Define o tamanho geral da entradaGrao -->
	<div class="col-md-offset-1 col-lg-offset-1 col-xs-12 col-sm-12 col-md-10 col-lg-10">
		<html:form styleId="form_entradaGrao" action="restrito/sistema/entradaGrao" method="post">
			<html:hidden property="method" value="empty" />

			<div class="panel sombra">

				<!-- INICIO FORMULARIO -->
				<div class="panel-body">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

							<div class="row">

								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
									<label>Data</label>
									<html:text styleClass="form-control input-sm text-center data focoInicial" styleId="data" property="entradaGrao.data" name="entradaGraoForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-4">
									<label>Motorista</label>
									<html:text styleClass="form-control input-sm" styleId="nomeMotorista" property="entradaGrao.nomeMotorista" name="entradaGraoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3">
									<label>Local Armazenagem</label>
									<html:select styleClass="form-control input-sm" styleId="localArmazenagem" property="entradaGrao.localArmazenagem.id" name="entradaGraoForm">
										<html:option value="">Selecione...</html:option>
										<html:optionsCollection name="entradaGraoForm" property="comboSilos" label="label" value="value" />
									</html:select>
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3">
									<label>Estoquista</label>
									<html:text styleClass="form-control input-sm" styleId="estoquista" property="entradaGrao.estoquista.pessoaFisica.razaoSocial" name="entradaGraoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="estoquistaSelecionado" property="entradaGrao.estoquista.id" name="entradaGraoForm" />
								</div>


								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3">
									<label>Safra</label>
									<html:text styleClass="form-control input-sm" styleId="safra" property="entradaGrao.safra.nome" name="entradaGraoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="safraSelecionada" property="entradaGrao.safra.id" name="entradaGraoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3">
									<label>Setor</label>
									<html:text styleClass="form-control input-sm" styleId="setor" property="entradaGrao.setor.nomeCompleto" name="entradaGraoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="setorSelecionado" property="entradaGrao.setor.id" name="entradaGraoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<label>Variedade</label>
									<html:text styleClass="form-control input-sm" styleId="variedade" property="entradaGrao.variedade.nome" name="entradaGraoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="variedadeSelecionada" property="entradaGrao.variedade.id" name="entradaGraoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label>Cultura</label>
									<html:text styleClass="form-control input-sm bloqueado" styleId="nomeCultura" property="entradaGrao.variedade.cultura.nome" name="entradaGraoForm" />
								</div>

							</div>
							<div class="row">


								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label>Peso Bruto</label>
									<html:text styleClass="form-control input-lg text-center decimal calcularLiquidoSacas" styleId="pesoBruto" property="entradaGrao.pesoBruto" name="entradaGraoForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label>Umidade</label>
									<html:text styleClass="form-control input-lg text-center decimal" styleId="umidade" property="entradaGrao.umidade" name="entradaGraoForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label>% Desc. Umidade</label>
									<html:text styleClass="form-control input-lg text-center decimal calcularLiquidoSacas" styleId="percentualDescontoGeradoUmidade" property="entradaGrao.percentualDescontoGeradoUmidade" name="entradaGraoForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label>% Desc. Impureza</label>
									<html:text styleClass="form-control input-lg text-center decimal calcularLiquidoSacas" styleId="percentualDescontoGeradoImpureza" property="entradaGrao.percentualDescontoGeradoImpureza" name="entradaGraoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<label>Peso Liquido</label>
									<html:text styleClass="form-control input-lg text-center decimal bloqueado" styleId="pesoLiquido" property="entradaGrao.pesoLiquido" name="entradaGraoForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<label>Sacas</label>
									<html:text styleClass="form-control input-lg text-center decimal bloqueado" styleId="emSacas" property="entradaGrao.emSacas" name="entradaGraoForm" />
								</div>
							</div>

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">

									<div class="panel sombra">

										<div class="panel-heading cor-sistema" style="padding: 6px 0px 0px 15px; color: white;background-color: rgb(66, 66, 66) !important;">
											<h4>Informações de Armazenagem</h4>
										</div>

										<!-- INICIO FORMULARIO -->
										<div class="panel-body">
											<div class="row">
												<logic:iterate id="siloCorrente" name="entradaGraoForm" property="silos" type="br.com.srcsoftware.sistema.silo.silo.InformacoesSiloPOJO">
													<div class="col-xs-12 col-sm-12 col-md-12 col-lg-3">
														<label>Local</label>
														<html:text styleClass="form-control input-sm text-center bloqueado" styleId="silo" property="silo" name="siloCorrente" />
													</div>
													<div class="col-xs-12 col-sm-12 col-md-12 col-lg-3">
														<label>Cultura</label>
														<html:text styleClass="form-control input-sm text-center bloqueado" styleId="culturaSilo" property="cultura" name="siloCorrente" />
													</div>
													<div class="col-xs-12 col-sm-12 col-md-12 col-lg-6">
														<label>Saldo&nbsp;Kg&nbsp;(Sacas:&nbsp;${siloCorrente.getEmSacas() })</label>
														<html:text style="background-color: #acd5ec !important;" styleClass="form-control input-lg text-center dinheiro bloqueado" styleId="saldoSilo" property="saldoSilo" name="siloCorrente" title="Sacas:&nbsp;${siloCorrente.getEmSacas() }" />
													</div>
												</logic:iterate>
											</div>
										</div>

									</div>
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<div class="panel sombra">

										<div class="panel-heading cor-sistema" style="padding: 6px 0px 0px 15px; color: white;background-color: rgb(66, 66, 66) !important;">
											<h4>Informações Saldo das Safras</h4>
										</div>

										<!-- INICIO FORMULARIO -->
										<div class="panel-body">
											<div class="row">
												<logic:iterate id="safraCorrente" name="entradaGraoForm" property="safras" type="br.com.srcsoftware.sistema.silo.entrada.InformacoesProducaoSafraPOJO">
													<div class="col-xs-12 col-sm-12 col-md-12 col-lg-3">
														<label>Safra</label>
														<html:text styleClass="form-control input-sm text-center bloqueado" styleId="nomeSafra" property="nomeSafra" name="safraCorrente" />
													</div>
													<div class="col-xs-12 col-sm-12 col-md-12 col-lg-3">
														<label>Cultura</label>
														<html:text styleClass="form-control input-sm text-center bloqueado" styleId="culturaSafra" property="cultura" name="safraCorrente" />
													</div>
													<div class="col-xs-12 col-sm-12 col-md-12 col-lg-6">
														<label>Saldo&nbsp;Kg&nbsp;(Sacas:&nbsp;${safraCorrente.getEmSacas() })</label>
														<html:text style="background-color: #acd5ec !important;" styleClass="form-control input-lg text-center dinheiro bloqueado" styleId="producao" property="producao" name="safraCorrente" title="Sacas:&nbsp;${safraCorrente.getEmSacas() }"/>
													</div>
												</logic:iterate>
											</div>
										</div>
									</div>

								</div>
							</div>
						</div>

						<%-- <div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<label>Peso Liquido</label>
										<html:text styleClass="form-control input-lg text-center decimal bloqueado" styleId="pesoLiquido" property="entradaGrao.pesoLiquido" name="entradaGraoForm" />
									</div>
									<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<label>Sacas</label>
										<html:text styleClass="form-control input-lg text-center decimal bloqueado" styleId="emSacas" property="entradaGrao.emSacas" name="entradaGraoForm" />
									</div>
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<div class="row">

										<div class="panel sombra">

											<div class="panel-heading cor-sistema" style="padding: 6px 0px 0px 15px; color: white;">
												<h4>Informações de Armazenagem</h4>
											</div>

											<!-- INICIO FORMULARIO -->
											<div class="panel-body">
												<div class="row">
													<logic:iterate id="siloCorrente" name="entradaGraoForm" property="silos" type="br.com.srcsoftware.sistema.silo.silo.InformacoesSiloPOJO">
														<div class="col-xs-12 col-sm-12 col-md-12 col-lg-3">
															<label>Local</label>
															<html:text styleClass="form-control input-sm text-center bloqueado" styleId="silo" property="silo" name="siloCorrente" />
														</div>
														<div class="col-xs-12 col-sm-12 col-md-12 col-lg-3">
															<label>Cultura</label>
															<html:text styleClass="form-control input-sm text-center bloqueado" styleId="culturaSilo" property="cultura" name="siloCorrente" />
														</div>
														<div class="col-xs-12 col-sm-12 col-md-12 col-lg-6">
															<label>Saldo</label>
															<html:text style="background-color: #acd5ec !important;" styleClass="form-control input-lg text-center dinheiro bloqueado" styleId="saldoSilo" property="saldoSilo" name="siloCorrente" />
														</div>
													</logic:iterate>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="panel sombra">

											<div class="panel-heading cor-sistema" style="padding: 6px 0px 0px 15px; color: white;">
												<h4>Informações Saldo das Safras</h4>
											</div>

											<!-- INICIO FORMULARIO -->
											<div class="panel-body">
												<div class="row">
													<logic:iterate id="safraCorrente" name="entradaGraoForm" property="safras" type="br.com.srcsoftware.sistema.silo.entrada.InformacoesProducaoSafraPOJO">
														<div class="col-xs-12 col-sm-12 col-md-12 col-lg-3">
															<label>Safra</label>
															<html:text styleClass="form-control input-sm text-center bloqueado" styleId="nomeSafra" property="nomeSafra" name="safraCorrente" />
														</div>
														<div class="col-xs-12 col-sm-12 col-md-12 col-lg-3">
															<label>Cultura</label>
															<html:text styleClass="form-control input-sm text-center bloqueado" styleId="culturaSafra" property="cultura" name="safraCorrente" />
														</div>
														<div class="col-xs-12 col-sm-12 col-md-12 col-lg-6">
															<label>Saldo</label>
															<html:text style="background-color: #acd5ec !important;" styleClass="form-control input-lg text-center dinheiro bloqueado" styleId="producao" property="producao" name="safraCorrente" />
														</div>
													</logic:iterate>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div> --%>


					</div>
				</div>
				<div class="panel-footer">
					<div class="row">
						<!-- BOTOES -->
						<logic:notPresent property="entradaGrao.id" name="entradaGraoForm">
							<logic:equal name="entradaGraoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.entradaGrao.inserir)">
								<div class="form-group col-xs-12 col-sm-3 col-md-4 col-lg-4" style="margin-bottom: 0px;">
									<button type="submit" id="inserir" class="btn btn-success btn-sm cor-sistema btn-block">
										<i class="fa fa-save"></i>
										Inserir
									</button>
								</div>
							</logic:equal>
						</logic:notPresent>
						<logic:present property="entradaGrao.id" name="entradaGraoForm">
							<logic:equal name="entradaGraoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.entradaGrao.alterar)">
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

						<logic:equal name="entradaGraoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.entradaGrao.filtrar)">
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
	$(document).ready(
			function() {
				/* Foco inicial */
				$(".focoInicial").focus();

				/* Setando NOTNULL nos campos*/
				$("#data").addClass("obrigatorio");
				$("#nomeMotorista").addClass("obrigatorio");
				$("#pesoBruto").addClass("obrigatorio");
				$("#umidade").addClass("obrigatorio");
				$("#percentualDescontoGeradoUmidade").addClass("obrigatorio");
				$("#percentualDescontoGeradoImpureza").addClass("obrigatorio");
				$("#estoquista").addClass("obrigatorio");
				$("#safra").addClass("obrigatorio");
				$("#setor").addClass("obrigatorio");
				$("#variedade").addClass("obrigatorio");

				/* Setando os tamanhos maximos dos campos baseando-se no PO*/
				$("#nomeMotorista").prop("maxlength", 255);
				$("#pesoBruto").prop("maxlength", 16);
				$("#umidade").prop("maxlength", 16);
				$("#percentualDescontoGeradoUmidade").prop("maxlength", 16);
				$("#percentualDescontoGeradoImpureza").prop("maxlength", 16);
				$("#safra").prop("maxlength", 100);
				$("#setor").prop("maxlength", 100);
				$("#variedade").prop("maxlength", 100);

				/* Setando os placeholder dos campos*/
				$("#nomeMotorista").prop("placeholder", "Motorista");
				$("#safra").prop("placeholder", "Safra");
				$("#setor").prop("placeholder", "Setor");
				$("#variedade").prop("placeholder", "Variedade");
				$("#localArmazenagem").prop("placeholder", "Local de Armazenagem");
				$("#estoquista").prop("placeholder", "Estoquista");

				/* EVENTOS */

				$('.calcularLiquidoSacas').focusout(
						function() {

							if (($('#pesoBruto').val() != null && $('#pesoBruto').val() != '')
									&& ($('#percentualDescontoGeradoUmidade').val() != null && $('#percentualDescontoGeradoUmidade').val() != '')
									&& ($('#percentualDescontoGeradoImpureza').val() != null && $('#percentualDescontoGeradoImpureza').val() != '')) {

								var theForm = $('form[name=entradaGraoForm]');
								var params = theForm.serialize();
								var actionURL = theForm.attr('action') + '?method=calcularLiquidoSacas';
								$.ajax({
									type : 'POST',
									url : actionURL,
									data : params,
									success : function(data, textStatus, XMLHttpRequest) {
										$('#pesoLiquido').val(data.pesoLiquido);
										$('#emSacas').val(data.emSacas);
									},
									error : function(XMLHttpRequest, textStatus, errorThrown) {
										alert(textStatus);
										$('#pesoLiquido').val(null);
										$('#emSacas').val(null);
									}
								});
							}
						});

				// Desliga o auto-complete da pagina
				$("#form_entradaGrao").prop("autocomplete", "off");

				$('#form_entradaGrao').on('submit', function(e) {
					abrirModalProcessando();
				});

				$('#inserir').click(function() {
					executar('form_entradaGrao', 'inserir');
				});

				$('#alterar').click(function() {
					executar('form_entradaGrao', 'alterar');
				});

				$('#limpar').click(function() {
					abrirModalProcessando();
					executarComSubmit('form_entradaGrao', 'limpar');
				});

				$('#listagem').click(function() {
					abrirModalProcessando();
					executarComSubmit('form_entradaGrao', 'abrirListagem');
				});

				// ######################################### Auto Complete Safra/Setor/Variedade		

				$('#estoquista').keyup(function() {
					if ($('#estoquista').val() == null || $('#marca').val() == '') {
						$('#estoquistaSelecionado').val(null);
					}
				});
				$('#estoquista').autocomplete({
					minChars : 2,
					noCache : true,
					paramName : 'entradaGrao.estoquista.pessoaFisica.razaoSocial',
					/* params : {
						'entradaGrao.safra.id' : function() {
							return $('#safraSelecionada').val();
						}
					}, */
					serviceUrl : '${contextPath}/restrito/sistema/entradaGrao.src?method=selecionarEstoquistaAutoComplete',
					onSelect : function(suggestion) {
						$('#estoquistaSelecionado').val(suggestion.data);
					},
					/* formatResult : function(suggestion, currentValue) {
						var valorExibir = suggestion.value + " - " + suggestion.variedade;
						return valorExibir;
					}, */
					onSearchComplete : function(query, suggestions) {
						if (suggestions == null || suggestions == '') {
							$('#estoquistaSelecionado').val(null);
						}
					}
				});

				$('#safra').keyup(function() {
					if ($('#safra').val() == null || $('#safra').val().trim() == '') {
						$('#safraSelecionada').val(null);

						$('#setor').val(null);
						$('#setorSelecionado').val(null);

						$('#variedade').val(null);
						$('#variedadeSelecionada').val(null);
						$('#nomeCultura').val(null);
					}
				});

				$('#safra').autocomplete({
					minChars : 2,
					noCache : true,
					paramName : 'entradaGrao.safra.nome',
					serviceUrl : '${contextPath}/restrito/sistema/entradaGrao.src?method=selecionarSafraAutoComplete',
					onSelect : function(suggestion) {
						$('#safraSelecionada').val(suggestion.data);

						$("#setor").focus();
					},
					onSearchComplete : function(query, suggestions) {
						if (suggestions == null || suggestions == '') {
							$('#safraSelecionada').val(null);

							$('#setor').val(null);
							$('#setorSelecionado').val(null);

							$('#variedade').val(null);
							$('#variedadeSelecionada').val(null);
							$('#nomeCultura').val(null);
						}
					}
				});

				$('#setor').keyup(function() {
					if ($('#setor').val() == null || $('#setor').val().trim() == '') {
						$('#setorSelecionado').val(null);

						$('#variedade').val(null);
						$('#variedadeSelecionada').val(null);
						$('#nomeCultura').val(null);
					}
				});

				$('#setor').autocomplete({
					minChars : 2,
					noCache : true,
					paramName : 'entradaGrao.setor.nomeCompleto',
					params : {
						'entradaGrao.safra.id' : function() {
							return $('#safraSelecionada').val();
						}
					},
					serviceUrl : '${contextPath}/restrito/sistema/entradaGrao.src?method=selecionarSetorAutoComplete',
					onSelect : function(suggestion) {
						$('#setorSelecionado').val(suggestion.data);

						$("#variedade").focus();
					},
					onSearchComplete : function(query, suggestions) {
						if (suggestions == null || suggestions == '') {
							$('#setorSelecionado').val(null);

							$('#variedade').val(null);
							$('#variedadeSelecionada').val(null);
							$('#nomeCultura').val(null);
						}
					}
				});

				$('#variedade').keyup(function() {
					if ($('#variedade').val() == null || $('#variedade').val().trim() == '') {
						$('#variedadeSelecionada').val(null);
						$('#nomeCultura').val(null);
					}
				});

				$('#variedade').autocomplete({
					minChars : 2,
					noCache : true,
					paramName : 'entradaGrao.variedade.nome',
					params : {
						'entradaGrao.setor.id' : function() {
							return $('#setorSelecionado').val();
						}
					},
					/* params : {
						'entradaGrao.entradaGrao.id' : function() {
							return $('#entradaGraoSelecionada').val();
						}
					}, */
					serviceUrl : '${contextPath}/restrito/sistema/entradaGrao.src?method=selecionarVariedadeAutoComplete',
					onSelect : function(suggestion) {
						$('#variedadeSelecionada').val(suggestion.data);
						$('#nomeCultura').val(suggestion.nomeCultura);

						//abrirModalProcessando();
						//executarComSubmit('form_entradaGrao', 'selecionarVariedade');

					},
					formatResult : function(suggestion, currentValue) {
						var valorExibir = suggestion.nomeCompleto;
						return valorExibir;
					},
					onSearchComplete : function(query, suggestions) {
						if (suggestions == null || suggestions == '') {
							$('#variedadeSelecionada').val(null);
							$('#nomeCultura').val(null);
						}
					}
				});
			});
</script>