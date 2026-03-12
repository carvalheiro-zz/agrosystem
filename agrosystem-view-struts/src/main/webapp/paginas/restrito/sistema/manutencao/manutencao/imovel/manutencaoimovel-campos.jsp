<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!-- CAMPOS DE CADASTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -30px; margin-bottom: -15px;">
		<h1 class="page-header cabecalho_pagina">
			<i class="fa fa-graduation-cap" style="font-size: 26px;"></i>
			Cadastrar Mão de Obra em Imóvel
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Cadastro da Mão de Obra em Imóveis
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<logic:present property="manutencaoImovel.id" name="manutencaoImovelForm">
	<div class="row">
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px;">
			<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-user-plus"></i>
				&nbsp;${manutencaoImovelForm.manutencaoImovel.nomeUsuarioCriacao }
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
			<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-calendar-plus-o"></i>
				&nbsp;${manutencaoImovelForm.manutencaoImovel.dataOcorrenciaCriacao}
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
			<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-refresh"></i>
				&nbsp;${manutencaoImovelForm.manutencaoImovel.nomeUsuarioAlteracao}
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-left: 0px;">
			<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-calendar-o"></i>
				&nbsp;${manutencaoImovelForm.manutencaoImovel.dataOcorrenciaAlteracao}
			</div>
		</div>
	</div>
</logic:present>

<div class="row">
	<!-- Define o tamanho geral da manutencaoImovel -->
	<div class="col-md-offset-1 col-lg-offset-1 col-xs-12 col-sm-12 col-md-10 col-lg-10">
		<html:form styleId="form_manutencaoImovel" action="restrito/sistema/manutencaoImovel" method="post">
			<html:hidden property="method" value="empty" />

			<div class="panel sombra">

				<!-- INICIO FORMULARIO -->
				<div class="panel-body">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-2">
									<label>Nota Fiscal</label>
									<html:text styleClass="form-control input-sm text-center focoInicial" styleId="notaFiscal" property="manutencaoImovel.notaFiscal" name="manutencaoImovelForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-8 col-lg-2">
									<label>Data</label>
									<html:text styleClass="form-control input-sm data" styleId="data" property="manutencaoImovel.data" name="manutencaoImovelForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-2">
									<label>Finalidade</label>
									<html:select styleClass="form-control input-sm" styleId="finalidade" property="manutencaoImovel.finalidade" name="manutencaoImovelForm">
										<html:option value="">Selecione...</html:option>
										<html:option value="Manutenção">Manutenção</html:option>
										<html:option value="Conserto">Conserto</html:option>
									</html:select>
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-2">
									<label>Custo</label>
									<html:text styleClass="form-control input-sm text-center dinheiro" styleId="custo" property="manutencaoImovel.custo" name="manutencaoImovelForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<label>Fornecedor</label>
									<a href="#" id="modalCadastrarFornecedor">
										<i style="text-shadow: 1px 0px 0px white, -1px 0px 0px white, 0px 1px 0px white, 0px -1px 0px white; color: green; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-plus"></i>
									</a>
									<html:text styleClass="form-control input-sm" styleId="fornecedor" property="manutencaoImovel.fornecedor.nome" name="manutencaoImovelForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="fornecedorSelecionado" property="manutencaoImovel.fornecedor.id" name="manutencaoImovelForm" />
								</div>
								
								

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3">
									<label>Atribuição de Custo</label>
									<html:select styleClass="form-control input-sm" styleId="tipo" property="manutencaoImovel.tipo" name="manutencaoImovelForm">
										<html:option value="">Selecione...</html:option>
										<html:option value="Safra/Setor">Safra/Setor</html:option>
										<html:option value="Tudo">Tudo</html:option>
										<html:option value="Cultura">Cultura</html:option>
									</html:select>
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<label>Safra</label>
									<html:text styleClass="form-control input-sm" styleId="safra" property="manutencaoImovel.safra.nome" name="manutencaoImovelForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="safraSelecionada" property="manutencaoImovel.safra.id" name="manutencaoImovelForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3">
									<label>Setor</label>
									<html:text styleClass="form-control input-sm" styleId="setor" property="manutencaoImovel.setor.nomeCompleto" name="manutencaoImovelForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="setorSelecionado" property="manutencaoImovel.setor.id" name="manutencaoImovelForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3">
									<label>Cultura</label>
									<html:text styleClass="form-control input-sm" styleId="cultura" property="manutencaoImovel.cultura.nome" name="manutencaoImovelForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="culturaSelecionada" property="manutencaoImovel.cultura.id" name="manutencaoImovelForm" />
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<label>Prestador de Serviço</label>
									<html:text styleClass="form-control input-sm" styleId="prestador" property="manutencaoImovel.prestador.nome" name="manutencaoImovelForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="prestadorSelecionado" property="manutencaoImovel.prestador.id" name="manutencaoImovelForm" />
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<div class="panel panel-success">
										<div class="panel-heading cor-sistema" style="color: white;">
											<label>Imóvel</label>
										</div>
										<div class="panel-body">
											<div class="row">
												<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-12">
													<label>Imovel</label>
													<a href="#" id="modalCadastrarImovel">
														<i style="text-shadow: 1px 0px 0px white, -1px 0px 0px white, 0px 1px 0px white, 0px -1px 0px white; color: green; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-plus"></i>
													</a>
													<html:text styleClass="form-control input-sm cor-campos-composicao-sistema" styleId="imovel" property="manutencaoImovel.imovel.nome" name="manutencaoImovelForm" />
													<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
													<html:hidden styleId="imovelSelecionado" property="manutencaoImovel.imovel.id" name="manutencaoImovelForm" />
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
													<html:text styleClass="form-control input-sm cor-campos-composicao-sistema" styleId="servico" property="manutencaoImovel.servico.nome" name="manutencaoImovelForm" />
													<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
													<html:hidden styleId="servicoSelecionado" property="manutencaoImovel.servico.id" name="manutencaoImovelForm" />
												</div>
												<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-12">
													<label>Observação</label>
													<html:text styleClass="form-control input-sm bloqueado" styleId="servicoObservacao" property="manutencaoImovel.servico.observacao" name="manutencaoImovelForm" />
												</div>
											</div>
										</div>
									</div>
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<label>Descrição do Serviço</label>
									<h6 class="pull-right" id="count_observacao"></h6>
									<html:textarea styleClass="form-control input-sm" styleId="observacao" property="manutencaoImovel.observacao" name="manutencaoImovelForm" rows="4" />
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="panel-footer">
					<div class="row">
						<!-- BOTOES -->
						<logic:notPresent property="manutencaoImovel.id" name="manutencaoImovelForm">
							<logic:equal name="manutencaoImovelForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.manutencao.inserir)">
								<div class="form-group col-xs-12 col-sm-3 col-md-4 col-lg-4" style="margin-bottom: 0px;">
									<button type="submit" id="inserir" class="btn btn-success btn-sm cor-sistema btn-block">
										<i class="fa fa-save"></i>
										Inserir
									</button>
								</div>
							</logic:equal>
						</logic:notPresent>
						<logic:present property="manutencaoImovel.id" name="manutencaoImovelForm">
							<logic:equal name="manutencaoImovelForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.manutencao.alterar)">
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

						<logic:equal name="manutencaoImovelForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.manutencao.filtrar)">
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
		$("#imovel").addClass("obrigatorio");
		$("#fornecedor").addClass("obrigatorio");
		$("#servico").addClass("obrigatorio");

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#custo").prop("maxlength", 15);
		$("#notaFiscal").prop("maxlength", 15);
		$("#fornecedor").prop("maxlength", 100);
		$("#safra").prop("maxlength", 100);
		$("#setor").prop("maxlength", 100);
		$("#cultura").prop("maxlength", 60);
		$("#imovel").prop("maxlength", 100);
		$("#servico").prop("maxlength", 255);
		$("#servicoObservacao").prop("maxlength", 255);
		$("#prestador").attr("maxlength", 50);
		$("#observacao").prop("maxlength", 1000);
		
		
		$( "#prestador" ).attr("placeholder","Prestador de Serviço");

		/* Setando os placeholder dos campos*/
		$("#notaFiscal").prop("placeholder", "NF");
		$("#veiculo").prop("placeholder", "Veiculo");
		$("#fornecedor").prop("placeholder", "Fornecedor");
		$("#servico").prop("placeholder", "Serviço");
		$("#safra").prop("placeholder", "Safra");
		$("#setor").prop("placeholder", "Setor");
		$("#cultura").prop("placeholder", "Cultura");
		$("#imovel").prop("placeholder", "Imóvel");

		/* EVENTOS */

		// Desliga o auto-complete da pagina
		$("#form_manutencaoImovel").prop("autocomplete", "off");

		$('#form_manutencaoImovel').on('submit', function(e) {
			abrirModalProcessando();
		});

		$('#inserir').click(function() {
			executar('form_manutencaoImovel', 'inserir');
		});

		$('#alterar').click(function() {
			executar('form_manutencaoImovel', 'alterar');
		});

		$('#limpar').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_manutencaoImovel', 'limpar');
		});

		$('#listagem').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_manutencaoImovel', 'abrirListagem');
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

		$('#imovel').keyup(function() {
			if ($('#imovel').val() == null || $('#imovel').val() == '') {
				$('#imovelSelecionado').val(null);
			}
		});
		$('#imovel').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'manutencaoImovel.imovel.nome',
			/* params : {
				'saidaGrao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/manutencaoImovel.src?method=selecionarImovelAutoComplete',
			onSelect : function(suggestion) {
				$('#imovelSelecionado').val(suggestion.data);

				$("#servico").focus();
			},
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			}, */
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#imovelSelecionado').val(null);
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
			paramName : 'manutencaoImovel.servico.nome',
			/* params : {
				'saidaGrao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/manutencaoImovel.src?method=selecionarServicoAutoComplete',
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
			paramName : 'manutencaoImovel.fornecedor.nome',
			/* params : {
				'saidaGrao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/manutencaoImovel.src?method=selecionarFornecedorAutoComplete',
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
			paramName : 'manutencaoImovel.safra.nome',
			serviceUrl : '${contextPath}/restrito/sistema/manutencaoImovel.src?method=selecionarSafraAutoComplete',
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
			paramName : 'manutencaoImovel.setor.nomeCompleto',
			params : {
				'manutencaoImovel.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			},
			serviceUrl : '${contextPath}/restrito/sistema/manutencaoImovel.src?method=selecionarSetorAutoComplete',
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
			paramName : 'manutencaoImovel.cultura.nome',
			params : {
				'manutencaoImovel.setor.id' : function() {
					return $('#setorSelecionado').val();
				},
				'manutencaoImovel.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			},
			/* params : {
				'saidaGrao.manutencaoImovel.id' : function() {
					return $('#manutencaoImovelSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/manutencaoImovel.src?method=selecionarCulturaAutoComplete',
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

		$('#modalCadastrarImovel').on('click', function() {
			var actionURL = '${contextPath}/restrito/sistema/imovel.src?method=abrirModal';

			$.ajax({
				type : 'POST',
				url : actionURL,
				success : function(data, textStatus, XMLHttpRequest) {
					BootstrapDialog.show({
						size : BootstrapDialog.SIZE_NORMAL,
						title : 'Cadastrar Imovel',
						message : $('<div id="id-modalImovel"></div>').append(data),
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
								var theForm = $('form[name=imovelForm]');
								var params = theForm.serialize();
								var actionURL = theForm.prop('action') + '?method=inserirModal';

								$.ajax({
									type : 'POST',
									url : actionURL,
									data : params,
									success : function(data, textStatus, XMLHttpRequest) {
										$('#id-modalImovel').html(data);

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

								var actionURL = '${contextPath}/restrito/sistema/imovel.src?method=fecharModal';

								$.ajax({
									type : 'POST',
									url : actionURL,
									success : function(data, textStatus, XMLHttpRequest) {
										dialogRef.close();

										$("#imovel").focus();
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