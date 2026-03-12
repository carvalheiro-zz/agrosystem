<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!-- CAMPOS DE CADASTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -30px; margin-bottom: -15px;">
		<h1 class="page-header cabecalho_pagina">
			<i class="fa fa-graduation-cap" style="font-size: 26px;"></i>
			Cadastrar Contrato de Venda
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Cadastro dos Contratos de Vendas
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<logic:present property="contratoVenda.id" name="contratoVendaForm">
	<div class="row">
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px;">
			<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-user-plus"></i>
				&nbsp;${contratoVendaForm.contratoVenda.nomeUsuarioCriacao }
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
			<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-calendar-plus-o"></i>
				&nbsp;${contratoVendaForm.contratoVenda.dataOcorrenciaCriacao}
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
			<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-refresh"></i>
				&nbsp;${contratoVendaForm.contratoVenda.nomeUsuarioAlteracao}
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-left: 0px;">
			<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-calendar-o"></i>
				&nbsp;${contratoVendaForm.contratoVenda.dataOcorrenciaAlteracao}
			</div>
		</div>
	</div>
</logic:present>

<div class="row">
	<!-- Define o tamanho geral da contratoVenda -->
	<div class="col-md-offset-0 col-lg-offset-0 col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<html:form styleId="form_contratoVenda" action="restrito/sistema/contratoVenda" method="post">
			<html:hidden property="method" value="empty" />

			<div class="panel sombra">

				<!-- INICIO FORMULARIO -->
				<div class="panel-body">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<label>Vendedor</label>
									<html:text styleClass="form-control input-sm focoInicial" styleId="vendedor" property="contratoVenda.vendedor.pessoaFisica.razaoSocial" name="contratoVendaForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="vendedorSelecionado" property="contratoVenda.vendedor.id" name="contratoVendaForm" />
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
									<label>Data</label>
									<html:text styleClass="form-control input-sm text-center data" styleId="data" property="contratoVenda.data" name="contratoVendaForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label>Número</label>
									<html:text styleClass="form-control input-sm text-center inteiro" styleId="numero" property="contratoVenda.numero" name="contratoVendaForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label>Cultura</label>
									<html:text styleClass="form-control input-sm" styleId="cultura" property="contratoVenda.cultura.nome" name="contratoVendaForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="culturaSelecionada" property="contratoVenda.cultura.id" name="contratoVendaForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<label>Cliente</label>
									<html:text styleClass="form-control input-sm" styleId="cliente" property="contratoVenda.cliente.pessoaJuridica.razaoSocial" name="contratoVendaForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="clienteSelecionado" property="contratoVenda.cliente.id" name="contratoVendaForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label>% Funrural</label>
									<html:text styleClass="form-control input-lg text-center decimal" styleId="funrural" property="contratoVenda.funrural" name="contratoVendaForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label>Valor Total (R$)</label>
									<html:text styleClass="form-control input-lg text-center decimal" styleId="valorTotal" property="contratoVenda.valorTotal" name="contratoVendaForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label>Quantidade (Kg)</label>
									<html:text styleClass="form-control input-lg text-center decimal" styleId="quantidade" property="contratoVenda.quantidade" name="contratoVendaForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label>Sacas</label>
									<html:text styleClass="form-control input-lg text-center bloqueado" styleId="emSacas" property="contratoVenda.emSacas" name="contratoVendaForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label>Valor Unitário (R$)</label>
									<html:text styleClass="form-control input-lg text-center bloqueado" styleId="valorUnitario" property="contratoVenda.valorUnitario" name="contratoVendaForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label>Valor Líquido (R$)</label>
									<html:text styleClass="form-control input-lg text-center decimal bloqueado" styleId="valorTotalLiquido" property="contratoVenda.valorTotalLiquido" name="contratoVendaForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<label>Observação</label>
									<html:textarea styleClass="form-control input-sm" styleId="observacao" property="contratoVenda.observacao" name="contratoVendaForm" rows="6" />
								</div>

							</div>
							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">

									<div class="panel sombra">

										<div class="panel-heading cor-sistema" style="padding: 6px 0px 0px 15px; color: white; background-color: rgb(66, 66, 66) !important;">
											<h4>Informações de Armazenagem</h4>
										</div>

										<!-- INICIO FORMULARIO -->
										<div class="panel-body">
											<div class="row">
												<logic:iterate id="siloCorrente" name="contratoVendaForm" property="silos" type="br.com.srcsoftware.sistema.silo.silo.InformacoesSiloPOJO">
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

										<div class="panel-heading cor-sistema" style="padding: 6px 0px 0px 15px; color: white; background-color: rgb(66, 66, 66) !important;">
											<h4>Informações Saldo das Safras</h4>
										</div>

										<!-- INICIO FORMULARIO -->
										<div class="panel-body">
											<div class="row">
												<logic:iterate id="safraCorrente" name="contratoVendaForm" property="safras" type="br.com.srcsoftware.sistema.silo.entrada.InformacoesProducaoSafraPOJO">
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
														<html:text style="background-color: #acd5ec !important;" styleClass="form-control input-lg text-center dinheiro bloqueado" styleId="producao" property="producao" name="safraCorrente" title="Sacas:&nbsp;${safraCorrente.getEmSacas() }" />
													</div>
												</logic:iterate>
											</div>
										</div>
									</div>

								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="panel-footer">
					<div class="row">
						<!-- BOTOES -->
						<logic:notPresent property="contratoVenda.id" name="contratoVendaForm">
							<logic:equal name="contratoVendaForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.contratoVenda.inserir)">
								<div class="form-group col-xs-12 col-sm-3 col-md-4 col-lg-4" style="margin-bottom: 0px;">
									<button type="submit" id="inserir" class="btn btn-success btn-sm cor-sistema btn-block">
										<i class="fa fa-save"></i>
										Inserir
									</button>
								</div>
							</logic:equal>
						</logic:notPresent>
						<logic:present property="contratoVenda.id" name="contratoVendaForm">
							<logic:equal name="contratoVendaForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.contratoVenda.alterar)">
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

						<logic:equal name="contratoVendaForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.contratoVenda.filtrar)">
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
		$("#data").addClass("obrigatorio");
		$("#numero").addClass("obrigatorio");
		$("#cliente").addClass("obrigatorio");
		$("#vendedor").addClass("obrigatorio");
		$("#cultura").addClass("obrigatorio");
		//$("#valorUnitario").addClass("obrigatorio");
		$("#valorTotal").addClass("obrigatorio");
		$("#quantidade").addClass("obrigatorio");

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#numero").prop("maxlength", 50);
		$("#cliente").prop("maxlength", 50);
		$("#vendedor").prop("maxlength", 50);
		$("#cultura").prop("maxlength", 50);
		//$("#valorUnitario").prop("maxlength", 10);
		$("#valorTotal").prop("maxlength", 16);
		$("#quantidade").prop("maxlength", 16);
		$("#observacao").prop("maxlength", 1000);

		/* Setando os placeholder dos campos*/
		$("#numero").prop("placeholder", "Número");
		$("#cliente").prop("placeholder", "Cliente");
		$("#vendedor").prop("placeholder", "Vendedor");
		$("#cultura").prop("placeholder", "Cultura");
		$("#observacao").prop("placeholder", "Observação em geral");

		/* EVENTOS */
		$('#valorTotal').focusout(function() {
			$('#quantidade').focusout();
		});

		$('#quantidade').focusout(function() {
			calcularValores();			
		});

		$('#funrural').focusout(function() {
			calcularValores();			
		});
		
		function calcularValores(){
			if (($('#quantidade').val() != null && $('#quantidade').val() != '')) {

				var theForm = $('form[name=contratoVendaForm]');
				var actionURL = theForm.attr('action') + '?method=calcularSacas';
				$.ajax({
					type : 'POST',
					url : actionURL,
					data : {
						"contratoVenda.quantidade" : $('#quantidade').val(),
						"contratoVenda.valorTotal" : $('#valorTotal').val(),
						"contratoVenda.funrural" : $('#funrural').val()
					},
					success : function(data, textStatus, XMLHttpRequest) {
						$('#emSacas').val(data.emSacas);
						$('#valorUnitario').val(data.valorUnitario);
						$('#valorTotalLiquido').val(data.valorTotalLiquido);
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert(textStatus);
						$('#emSacas').val(null);
						$('#valorUnitario').val(null);
						$('#valorTotalLiquido').val(null);
					}
				});

			} else {
				$('#emSacas').val(null);
				$('#valorUnitario').val(null);
				$('#valorTotalLiquido').val(null);
			}
		}

		// Desliga o auto-complete da pagina
		$("#form_contratoVenda").prop("autocomplete", "off");

		$('#form_contratoVenda').on('submit', function(e) {
			abrirModalProcessando();
		});

		$('#inserir').click(function() {
			executar('form_contratoVenda', 'inserir');
		});

		$('#alterar').click(function() {
			executar('form_contratoVenda', 'alterar');
		});

		$('#limpar').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_contratoVenda', 'limpar');
		});

		$('#listagem').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_contratoVenda', 'abrirListagem');
		});

		$('#vendedor').keyup(function() {
			if ($('#vendedor').val() == null || $('#marca').val() == '') {
				$('#vendedorSelecionado').val(null);
			}
		});
		$('#vendedor').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'contratoVenda.vendedor.pessoaFisica.razaoSocial',
			/* params : {
				'contratoVenda.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/contratoVenda.src?method=selecionarVendedorAutoComplete',
			onSelect : function(suggestion) {
				$('#vendedorSelecionado').val(suggestion.data);

				$("#data").focus();
			},
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			}, */
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#vendedorSelecionado').val(null);
				}
			}
		});

		// ######################################### Auto Complete Safra/Setor/Cultura		

		$('#cliente').keyup(function() {
			if ($('#cliente').val() == null || $('#marca').val() == '') {
				$('#clienteSelecionado').val(null);
			}
		});
		$('#cliente').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'contratoVenda.cliente.pessoaJuridica.razaoSocial',
			/* params : {
				'contratoVenda.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/contratoVenda.src?method=selecionarClienteAutoComplete',
			onSelect : function(suggestion) {
				$('#clienteSelecionado').val(suggestion.data);

				$("#valorUnitario").focus();
			},
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			}, */
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#clienteSelecionado').val(null);
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
			paramName : 'contratoVenda.cultura.nome',
			/* params : {
				'contratoVenda.setor.id' : function() {
					return $('#setorSelecionado').val();
				}
			}, */
			/* params : {
				'contratoVenda.contratoVenda.id' : function() {
					return $('#contratoVendaSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/contratoVenda.src?method=selecionarCulturaAutoComplete',
			onSelect : function(suggestion) {
				$('#culturaSelecionada').val(suggestion.data);

				$("#cliente").focus();
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