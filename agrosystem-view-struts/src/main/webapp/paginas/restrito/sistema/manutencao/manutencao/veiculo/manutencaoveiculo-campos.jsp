<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!-- CAMPOS DE CADASTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -30px; margin-bottom: -15px;">
		<h1 class="page-header cabecalho_pagina">
			<i class="fa fa-graduation-cap" style="font-size: 26px;"></i>
			Cadastrar Mão de Obra em Veículo
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Cadastro da Mão de Obra em Veículos
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<logic:present property="manutencaoVeiculo.id" name="manutencaoVeiculoForm">
	<div class="row">
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px;">
			<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-user-plus"></i>
				&nbsp;${manutencaoVeiculoForm.manutencaoVeiculo.nomeUsuarioCriacao }
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
			<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-calendar-plus-o"></i>
				&nbsp;${manutencaoVeiculoForm.manutencaoVeiculo.dataOcorrenciaCriacao}
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
			<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-refresh"></i>
				&nbsp;${manutencaoVeiculoForm.manutencaoVeiculo.nomeUsuarioAlteracao}
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-left: 0px;">
			<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-calendar-o"></i>
				&nbsp;${manutencaoVeiculoForm.manutencaoVeiculo.dataOcorrenciaAlteracao}
			</div>
		</div>
	</div>
</logic:present>

<div class="row">
	<!-- Define o tamanho geral da manutencaoVeiculo -->
	<div class="col-md-offset-1 col-lg-offset-1 col-xs-12 col-sm-12 col-md-10 col-lg-10">
		<html:form styleId="form_manutencaoVeiculo" action="restrito/sistema/manutencaoVeiculo" method="post">
			<html:hidden property="method" value="empty" />

			<div class="panel sombra">

				<!-- INICIO FORMULARIO -->
				<div class="panel-body">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-2">
									<label>Nota Fiscal</label>
									<html:text styleClass="form-control input-sm text-center focoInicial" styleId="notaFiscal" property="manutencaoVeiculo.notaFiscal" name="manutencaoVeiculoForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-8 col-lg-2">
									<label>Data</label>
									<html:text styleClass="form-control input-sm data" styleId="data" property="manutencaoVeiculo.data" name="manutencaoVeiculoForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-2">
									<label>Finalidade</label>
									<html:select styleClass="form-control input-sm" styleId="finalidade" property="manutencaoVeiculo.finalidade" name="manutencaoVeiculoForm">
										<html:option value="">Selecione...</html:option>
										<html:option value="Manutenção">Manutenção</html:option>
										<html:option value="Conserto">Conserto</html:option>
									</html:select>
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-2">
									<label>Custo</label>
									<html:text styleClass="form-control input-sm text-center dinheiro" styleId="custo" property="manutencaoVeiculo.custo" name="manutencaoVeiculoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<label>Fornecedor</label>
									<a href="#" id="modalCadastrarFornecedor">
										<i style="text-shadow: 1px 0px 0px white, -1px 0px 0px white, 0px 1px 0px white, 0px -1px 0px white; color: green; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-plus"></i>
									</a>
									<html:text styleClass="form-control input-sm" styleId="fornecedor" property="manutencaoVeiculo.fornecedor.nome" name="manutencaoVeiculoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="fornecedorSelecionado" property="manutencaoVeiculo.fornecedor.id" name="manutencaoVeiculoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3">
									<label>Atribuição de Custo</label>
									<html:select styleClass="form-control input-sm" styleId="tipo" property="manutencaoVeiculo.tipo" name="manutencaoVeiculoForm">
										<html:option value="">Selecione...</html:option>
										<html:option value="Safra/Setor">Safra/Setor</html:option>
										<html:option value="Tudo">Tudo</html:option>
										<html:option value="Cultura">Cultura</html:option>
									</html:select>
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3">
									<label>Safra</label>
									<html:text styleClass="form-control input-sm" styleId="safra" property="manutencaoVeiculo.safra.nome" name="manutencaoVeiculoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="safraSelecionada" property="manutencaoVeiculo.safra.id" name="manutencaoVeiculoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3">
									<label>Setor</label>
									<html:text styleClass="form-control input-sm" styleId="setor" property="manutencaoVeiculo.setor.nomeCompleto" name="manutencaoVeiculoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="setorSelecionado" property="manutencaoVeiculo.setor.id" name="manutencaoVeiculoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3">
									<label>Cultura</label>
									<html:text styleClass="form-control input-sm" styleId="cultura" property="manutencaoVeiculo.cultura.nome" name="manutencaoVeiculoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="culturaSelecionada" property="manutencaoVeiculo.cultura.id" name="manutencaoVeiculoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<label>Prestador de Serviço</label>
									<html:text styleClass="form-control input-sm" styleId="prestador" property="manutencaoVeiculo.prestador.nome" name="manutencaoVeiculoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="prestadorSelecionado" property="manutencaoVeiculo.prestador.id" name="manutencaoVeiculoForm" />
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
									<label>Km/Horímetro</label>
									<html:text styleClass="form-control input-sm text-center" styleId="kmHorimetro" property="manutencaoVeiculo.kmHorimetro" name="manutencaoVeiculoForm" />
								</div>	
							</div>
							<div class="row">
								<%-- <div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<div class="panel panel-success">
										<div class="panel-heading cor-sistema" style="color: white;">
											<label>Veículo</label>
										</div>
										<div class="panel-body">
											<div class="row">
												<div class="form-group col-xs-12 col-sm-12 col-md-9 col-lg-9">
													<label>Veiculo</label>
													<a href="#" id="modalCadastrarVeiculo">
														<i style="text-shadow: 1px 0px 0px white, -1px 0px 0px white, 0px 1px 0px white, 0px -1px 0px white; color: green; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-plus"></i>
													</a>
													<html:text styleClass="form-control input-sm cor-campos-composicao-sistema" styleId="veiculo" property="manutencaoVeiculo.veiculo.nome" name="manutencaoVeiculoForm" />
													<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
													<html:hidden styleId="veiculoSelecionado" property="manutencaoVeiculo.veiculo.id" name="manutencaoVeiculoForm" />
												</div>

												<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
													<label>Tipo</label>
													<html:text styleClass="form-control input-sm bloqueado" styleId="veiculoTipo" property="manutencaoVeiculo.veiculo.tipo" name="manutencaoVeiculoForm" />
												</div>
												<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<label>Código</label>
													<html:text styleClass="form-control input-sm bloqueado" styleId="veiculoCodigo" property="manutencaoVeiculo.veiculo.codigo" name="manutencaoVeiculoForm" />
												</div>
												<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<label>Modelo</label>
													<html:text styleClass="form-control input-sm bloqueado" styleId="veiculoModelo" property="manutencaoVeiculo.veiculo.modelo" name="manutencaoVeiculoForm" />
												</div>

												<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
													<label>Fabricação</label>
													<html:text styleClass="form-control input-sm bloqueado" styleId="veiculoAnoFabricacao" property="manutencaoVeiculo.veiculo.anoFabricacao" name="manutencaoVeiculoForm" />
												</div>

												<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
													<label>Nº Chassis</label>
													<html:text styleClass="form-control input-sm bloqueado" styleId="veiculoNumeroChassis" property="manutencaoVeiculo.veiculo.numeroChassis" name="manutencaoVeiculoForm" />
												</div>
											</div>
										</div>
									</div>
								</div> --%>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<div class="panel panel-success">
										<div class="panel-heading cor-sistema" style="color: white;">
											<label>Veículo</label>
										</div>
										<div class="panel-body">
											<div class="row">
												<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-12">
													<label>Veiculo</label>
													<a href="#" id="modalCadastrarVeiculo">
														<i style="text-shadow: 1px 0px 0px white, -1px 0px 0px white, 0px 1px 0px white, 0px -1px 0px white; color: green; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-plus"></i>
													</a>
													<html:text styleClass="form-control input-sm cor-campos-composicao-sistema" styleId="veiculo" property="manutencaoVeiculo.veiculo.nomeCompleto" name="manutencaoVeiculoForm" />
													<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
													<html:hidden styleId="veiculoSelecionado" property="manutencaoVeiculo.veiculo.id" name="manutencaoVeiculoForm" />
												</div>
											</div>
										</div>
									</div>
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<div class="panel panel-success" style="margin-bottom: 0px;">
										<div class="panel-heading cor-sistema" style="color: white;">
											<label>Serviço</label>
										</div>
										<div class="panel-body">
											<div class="row">
												<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-12">
													<label>Serviço</label>
													<a href="#" id="modalCadastrarServico">
														<i style="text-shadow: 1px 0px 0px white, -1px 0px 0px white, 0px 1px 0px white, 0px -1px 0px white; color: green; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-plus"></i>
													</a>
													<html:text styleClass="form-control input-sm cor-campos-composicao-sistema" styleId="servico" property="manutencaoVeiculo.servico.nome" name="manutencaoVeiculoForm" />
													<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
													<html:hidden styleId="servicoSelecionado" property="manutencaoVeiculo.servico.id" name="manutencaoVeiculoForm" />
												</div>
												<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-12">
													<label>Observação</label>
													<html:text styleClass="form-control input-sm bloqueado" styleId="servicoObservacao" property="manutencaoVeiculo.servico.observacao" name="manutencaoVeiculoForm" />
												</div>
											</div>
										</div>
									</div>
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<label>Descrição do Serviço</label>
									<h6 class="pull-right" id="count_observacao"></h6>
									<html:textarea styleClass="form-control input-sm" styleId="observacao" property="manutencaoVeiculo.observacao" name="manutencaoVeiculoForm" rows="4" />
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="panel-footer">
					<div class="row">
						<!-- BOTOES -->
						<logic:notPresent property="manutencaoVeiculo.id" name="manutencaoVeiculoForm">
							<logic:equal name="manutencaoVeiculoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.manutencao.inserir)">
								<div class="form-group col-xs-12 col-sm-3 col-md-4 col-lg-4" style="margin-bottom: 0px;">
									<button type="submit" id="inserir" class="btn btn-success btn-sm cor-sistema btn-block">
										<i class="fa fa-save"></i>
										Inserir
									</button>
								</div>
							</logic:equal>
						</logic:notPresent>
						<logic:present property="manutencaoVeiculo.id" name="manutencaoVeiculoForm">
							<logic:equal name="manutencaoVeiculoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.manutencao.alterar)">
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

						<logic:equal name="manutencaoVeiculoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.manutencao.filtrar)">
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
		$("#finalidade").addClass("obrigatorio");
		$("#custo").addClass("obrigatorio");
		$("#veiculo").addClass("obrigatorio");
		$("#fornecedor").addClass("obrigatorio");
		$("#servico").addClass("obrigatorio");

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#custo").prop("maxlength", 15);
		$("#notaFiscal").prop("maxlength", 15);
		$("#fornecedor").prop("maxlength", 100);
		$("#safra").prop("maxlength", 100);
		$("#setor").prop("maxlength", 100);
		$("#cultura").prop("maxlength", 60);
		$("#veiculo").prop("maxlength", 100);
		$("#servico").prop("maxlength", 255);
		$("#servicoObservacao").prop("maxlength", 255);
		$("#prestador").attr("maxlength", 50);
		$("#observacao").prop("maxlength", 1000);
		$("#kmHorimetro").prop("maxlength", 50);
		
		
		$("#prestador").attr("placeholder", "Prestador de Serviço");
		$("#kmHorimetro").prop("placeholder", "km / Horímetro");

		/* Setando os placeholder dos campos*/
		$("#notaFiscal").prop("placeholder", "NF");
		$("#veiculo").prop("placeholder", "Veiculo");
		$("#fornecedor").prop("placeholder", "Fornecedor");
		$("#servico").prop("placeholder", "Serviço");
		$("#safra").prop("placeholder", "Safra");
		$("#setor").prop("placeholder", "Setor");
		$("#cultura").prop("placeholder", "Cultura");
		$("#veiculo").prop("placeholder", "Veículo");

		/* EVENTOS */

		// Desliga o auto-complete da pagina
		$("#form_manutencaoVeiculo").prop("autocomplete", "off");

		$('#form_manutencaoVeiculo').on('submit', function(e) {
			abrirModalProcessando();
		});

		$('#inserir').click(function() {
			executar('form_manutencaoVeiculo', 'inserir');
		});

		$('#alterar').click(function() {
			executar('form_manutencaoVeiculo', 'alterar');
		});

		$('#limpar').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_manutencaoVeiculo', 'limpar');
		});

		$('#listagem').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_manutencaoVeiculo', 'abrirListagem');
		});
		
		var count_observacao_max = 1000;
		$('#count_observacao').html(count_observacao_max + ' restantes');
		$('#observacao').keyup(function() {
			gerenciarContadorCaracteresObservacao();
		});
		function gerenciarContadorCaracteresObservacao() {
			var text_length = $('#observacao').val().length;
			var text_remaining = count_observacao_max - text_length;
			$('#count_observacao').html(text_remaining + ' restantes');
		}

		gerenciarContadorCaracteresObservacao();

		// ######################################### Auto Complete Safra/Setor/Cultura		
		$('#veiculo').keyup(function() {
			if ($('#veiculo').val() == null || $('#veiculo').val() == '') {
				$('#veiculoSelecionado').val(null);
			}
		});

		$('#veiculo').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'manutencaoVeiculo.veiculo.nomeCompleto',
			/* params : {
				'saidaGrao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/manutencaoVeiculo.src?method=selecionarVeiculoAutoComplete',
			onSelect : function(suggestion) {
				$('#veiculoSelecionado').val(suggestion.data);
			},
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			}, */
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#veiculoSelecionado').val(null);
				}
			}
		});

		$('#servico').keyup(function() {
			if ($('#servico').val() == null || $('#servico').val() == '') {
				$('#servicoSelecionado').val(null);
			}
		});
		$('#servico').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'manutencaoVeiculo.servico.nome',
			/* params : {
				'saidaGrao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/manutencaoVeiculo.src?method=selecionarServicoAutoComplete',
			onSelect : function(suggestion) {
				$('#servicoSelecionado').val(suggestion.data);
				$('#servicoObservacao').val(suggestion.observacao);
			},
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			}, */
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#servicoSelecionado').val(null);
					$('#servicoObservacao').val(null);
				}
			}
		});

		$('#fornecedor').keyup(function() {
			if ($('#fornecedor').val() == null || $('#fornecedor').val() == '') {
				$('#fornecedorSelecionado').val(null);
			}
		});
		$('#fornecedor').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'manutencaoVeiculo.fornecedor.nome',
			/* params : {
				'saidaGrao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/manutencaoVeiculo.src?method=selecionarFornecedorAutoComplete',
			onSelect : function(suggestion) {
				$('#fornecedorSelecionado').val(suggestion.data);

				$("#tipo").focus();
			},
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			}, */
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#fornecedorSelecionado').val(null);
				}
			}
		});

		$('#prestador').keyup(function() {
			if ($('#prestador').val() == '') {
				$('#prestadorSelecionado').val(null);
			}
		});
		$('#prestador').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'prestadorServico.nome',
			serviceUrl : '${contextPath}/restrito/sistema/prestadorServico.src?method=selecionarPrestadorServicoAutoComplete',
			onSelect : function(suggestion) {
				$('#prestadorSelecionado').val(suggestion.data);
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#prestadorSelecionado').val(null);
				}
			}
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
			paramName : 'manutencaoVeiculo.safra.nome',
			serviceUrl : '${contextPath}/restrito/sistema/manutencaoVeiculo.src?method=selecionarSafraAutoComplete',
			onSelect : function(suggestion) {
				$('#safraSelecionada').val(suggestion.data);

				if ($('#tipo').val() == '') {
					$("#safra").focus();
				} else if ($('#tipo').val() == 'Safra/Setor') {
					$("#setor").focus();
				} else if ($('#tipo').val() == 'Tudo') {
					$("#veiculo").focus();
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
			paramName : 'manutencaoVeiculo.setor.nomeCompleto',
			params : {
				'manutencaoVeiculo.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			},
			serviceUrl : '${contextPath}/restrito/sistema/manutencaoVeiculo.src?method=selecionarSetorAutoComplete',
			onSelect : function(suggestion) {
				$('#setorSelecionado').val(suggestion.data);

				if ($('#tipo').val() == '') {
					$("#safra").focus();
				} else if ($('#tipo').val() == 'Safra/Setor') {
					$("#veiculo").focus();
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
			paramName : 'manutencaoVeiculo.cultura.nome',
			params : {
				'manutencaoVeiculo.setor.id' : function() {
					return $('#setorSelecionado').val();
				},
				'manutencaoVeiculo.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			},
			/* params : {
				'saidaGrao.manutencaoVeiculo.id' : function() {
					return $('#manutencaoVeiculoSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/manutencaoVeiculo.src?method=selecionarCulturaAutoComplete',
			onSelect : function(suggestion) {
				$('#culturaSelecionada').val(suggestion.data);

				$("#veiculo").focus();
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

		$('#modalCadastrarFornecedor').on('click', function() {

			var actionURL = '${contextPath}/restrito/sistema/fornecedorJuridico.src?method=abrirModal';

			$.ajax({
				type : 'POST',
				url : actionURL,
				success : function(data, textStatus, XMLHttpRequest) {

					BootstrapDialog.show({
						size : BootstrapDialog.SIZE_NORMAL,
						title : 'Cadastrar Fornecedor',
						message : $('<div id="id-modalFornecedor"></div>').append(data),
						type : BootstrapDialog.TYPE_SUCCESS, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
						closable : false, // <-- Default value is false
						draggable : true, // <-- Default value is false
						onshown : function(dialogRef) {
							/* Foco inicial */
							$("#nomeModal").focus();
						},
						buttons : [ {
							hotkey : 13,
							label : 'Inserir',
							cssClass : 'btn-success',
							action : function(dialogRef) {
								var theForm = $('form[name=fornecedorJuridicoForm]');
								var params = theForm.serialize();
								var actionURL = theForm.prop('action') + '?method=inserirModal';

								$.ajax({
									type : 'POST',
									url : actionURL,
									data : params,
									success : function(data, textStatus, XMLHttpRequest) {
										$('#id-modalFornecedor').html(data);

										$("#nomeModal").focus();
									},
									error : function(XMLHttpRequest, textStatus, errorThrown) {
										alert(textStatus);
									}
								});
							}
						}, {
							label : 'Fechar',
							action : function(dialogRef) {

								var actionURL = '${contextPath}/restrito/sistema/fornecedorJuridico.src?method=fecharModal';

								$.ajax({
									type : 'POST',
									url : actionURL,
									success : function(data, textStatus, XMLHttpRequest) {
										dialogRef.close();

										$("#fornecedor").focus();
									},
									error : function(XMLHttpRequest, textStatus, errorThrown) {
										alert(textStatus);
									}
								});

							}
						} ]
					});
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(textStatus);
				}
			});
		});

		$('#modalCadastrarVeiculo').on('click', function() {

			var actionURL = '${contextPath}/restrito/sistema/veiculo.src?method=abrirModal';

			$.ajax({
				type : 'POST',
				url : actionURL,
				success : function(data, textStatus, XMLHttpRequest) {

					BootstrapDialog.show({
						size : BootstrapDialog.SIZE_NORMAL,
						title : 'Cadastrar Veiculo',
						message : $('<div id="id-modalVeiculo"></div>').append(data),
						type : BootstrapDialog.TYPE_SUCCESS, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
						closable : false, // <-- Default value is false
						draggable : true, // <-- Default value is false
						onshown : function(dialogRef) {
							/* Foco inicial */
							$("#nomeModal").focus();
						},
						buttons : [ {
							hotkey : 13,
							label : 'Inserir',
							cssClass : 'btn-success',
							action : function(dialogRef) {
								var theForm = $('form[name=veiculoForm]');
								var params = theForm.serialize();
								var actionURL = theForm.prop('action') + '?method=inserirModal';

								$.ajax({
									type : 'POST',
									url : actionURL,
									data : params,
									success : function(data, textStatus, XMLHttpRequest) {
										$('#id-modalVeiculo').html(data);

										$("#nomeModal").focus();
									},
									error : function(XMLHttpRequest, textStatus, errorThrown) {
										alert(textStatus);
									}
								});
							}
						}, {
							label : 'Fechar',
							action : function(dialogRef) {

								var actionURL = '${contextPath}/restrito/sistema/veiculo.src?method=fecharModal';

								$.ajax({
									type : 'POST',
									url : actionURL,
									success : function(data, textStatus, XMLHttpRequest) {
										dialogRef.close();

										$("#veiculo").focus();
									},
									error : function(XMLHttpRequest, textStatus, errorThrown) {
										alert(textStatus);
									}
								});

							}
						} ]
					});
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(textStatus);
				}
			});

		});

		$('#modalCadastrarServico').on('click', function() {
			var actionURL = '${contextPath}/restrito/sistema/servico.src?method=abrirModal';

			$.ajax({
				type : 'POST',
				url : actionURL,
				success : function(data, textStatus, XMLHttpRequest) {
					BootstrapDialog.show({
						size : BootstrapDialog.SIZE_NORMAL,
						title : 'Cadastrar Servico',
						message : $('<div id="id-modalServico"></div>').append(data),
						type : BootstrapDialog.TYPE_SUCCESS, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
						closable : false, // <-- Default value is false
						draggable : true, // <-- Default value is false
						onshown : function(dialogRef) {
							/* Foco inicial */
							$("#nomeModal").focus();
						},
						buttons : [ {
							hotkey : 13,
							label : 'Inserir',
							cssClass : 'btn-success',
							action : function(dialogRef) {
								var theForm = $('form[name=servicoForm]');
								var params = theForm.serialize();
								var actionURL = theForm.prop('action') + '?method=inserirModal';

								$.ajax({
									type : 'POST',
									url : actionURL,
									data : params,
									success : function(data, textStatus, XMLHttpRequest) {
										$('#id-modalServico').html(data);

										$("#nomeModal").focus();
									},
									error : function(XMLHttpRequest, textStatus, errorThrown) {
										alert(textStatus);
									}
								});
							}
						}, {
							label : 'Fechar',
							action : function(dialogRef) {

								var actionURL = '${contextPath}/restrito/sistema/servico.src?method=fecharModal';

								$.ajax({
									type : 'POST',
									url : actionURL,
									success : function(data, textStatus, XMLHttpRequest) {
										dialogRef.close();

										$("#servico").focus();
									},
									error : function(XMLHttpRequest, textStatus, errorThrown) {
										alert(textStatus);
									}
								});

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

			$("#safra").addClass("bloqueado");
			$("#cultura").addClass("bloqueado");
			$("#setor").addClass("bloqueado");

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

			$("#safra").removeClass("bloqueado");
			$("#setor").removeClass("bloqueado");

			$("#cultura").removeClass("obrigatorio");
			$("#cultura").addClass("bloqueado");

			$("#safra").focus();
		} else if ($('#tipo').val() == 'Tudo') {
			$("#safra").addClass("obrigatorio");

			$("#safra").removeClass("bloqueado"); 

			$("#setor").removeClass("obrigatorio");
			$("#setor").addClass("bloqueado");

			$("#cultura").removeClass("obrigatorio");
			$("#cultura").addClass("bloqueado");

			$("#safra").focus();
		} else if ($('#tipo').val() == 'Cultura') {
			$("#safra").addClass("obrigatorio");
			$("#cultura").addClass("obrigatorio");

			$("#safra").removeClass("bloqueado");
			$("#cultura").removeClass("bloqueado");

			$("#setor").removeClass("obrigatorio");
			$("#setor").addClass("bloqueado");

			$("#safra").focus();
		}

		recarregarObrigatorios();
	}
</script>