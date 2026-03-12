<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!-- CAMPOS DE CADASTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -30px; margin-bottom: -15px;">
		<h1 class="page-header cabecalho_pagina">
			<i class="fa fa-graduation-cap" style="font-size: 26px;"></i>
			Cadastrar Itens em Implementos
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Cadastro dos Itens em Implementos
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<logic:present property="itemImplemento.id" name="itemImplementoForm">
	<div class="row">
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px;">
			<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-user-plus"></i>
				&nbsp;${itemImplementoForm.itemImplemento.nomeUsuarioCriacao }
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
			<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-calendar-plus-o"></i>
				&nbsp;${itemImplementoForm.itemImplemento.dataOcorrenciaCriacao}
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
			<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-refresh"></i>
				&nbsp;${itemImplementoForm.itemImplemento.nomeUsuarioAlteracao}
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-left: 0px;">
			<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-calendar-o"></i>
				&nbsp;${itemImplementoForm.itemImplemento.dataOcorrenciaAlteracao}
			</div>
		</div>
	</div>
</logic:present>

<div class="row">
	<!-- Define o tamanho geral da itemImplemento -->
	<div class="col-md-offset-1 col-lg-offset-1 col-xs-12 col-sm-12 col-md-10 col-lg-10">
		<html:form styleId="form_itemImplemento" action="restrito/sistema/itemImplemento" method="post">
			<html:hidden property="method" value="empty" />

			<div class="panel sombra">

				<!-- INICIO FORMULARIO -->
				<div class="panel-body">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
									<label>Data</label>
									<html:text styleClass="form-control input-sm text-center data focoInicial" styleId="data" property="itemImplemento.data" name="itemImplementoForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-9 col-lg-6">
									<label>Produto</label>
									<html:text styleClass="form-control input-sm" styleId="produto" property="itemImplemento.produto.nomeCompleto" name="itemImplementoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="produtoSelecionado" property="itemImplemento.produto.id" name="itemImplementoForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
									<label>Estoque</label>
									<html:text styleClass="form-control input-sm text-center bloqueado" styleId="estoque" property="estoque" name="itemImplementoForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
									<label>Custo médio</label>
									<html:text styleClass="form-control input-sm text-center bloqueado" styleId="custoMedio" property="itemImplemento.custoMedio" name="itemImplementoForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
									<label>Quatidade</label>
									<html:text styleClass="form-control input-sm text-center" styleId="quantidade" property="itemImplemento.quantidade" name="itemImplementoForm" />
									<p class="help-block" id="helpQuantidade"></p>
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label>Custo Total</label>
									<html:text styleClass="form-control input-sm text-center bloqueado" styleId="custoTotal" property="itemImplemento.custoTotal" name="itemImplementoForm" />
								</div>
							</div>

							<div class="row">  
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3">
									<label>Atribuição de Custo</label>
									<html:select styleClass="form-control input-sm" styleId="tipo" property="itemImplemento.tipo" name="itemImplementoForm">
										<html:option value="">Selecione...</html:option>
										<html:option value="Safra/Setor">Safra/Setor</html:option>
										<html:option value="Tudo">Tudo</html:option>
										<html:option value="Cultura">Cultura</html:option>
									</html:select>
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3">
									<label>Safra</label>
									<html:text styleClass="form-control input-sm" styleId="safra" property="itemImplemento.safra.nome" name="itemImplementoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="safraSelecionada" property="itemImplemento.safra.id" name="itemImplementoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3">
									<label>Setor</label>
									<html:text styleClass="form-control input-sm" styleId="setor" property="itemImplemento.setor.nomeCompleto" name="itemImplementoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="setorSelecionado" property="itemImplemento.setor.id" name="itemImplementoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3">
									<label>Cultura</label>
									<html:text styleClass="form-control input-sm" styleId="cultura" property="itemImplemento.cultura.nome" name="itemImplementoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="culturaSelecionada" property="itemImplemento.cultura.id" name="itemImplementoForm" />
								</div>
							</div>

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<label>Almoxarife</label>
									<html:text styleClass="form-control input-sm" styleId="almoxarife" property="itemImplemento.almoxarife.pessoaFisica.razaoSocial" name="itemImplementoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="almoxarifeSelecionado" property="itemImplemento.almoxarife.id" name="itemImplementoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<label>Requerente</label>
									<html:text styleClass="form-control input-sm" styleId="requerente" property="itemImplemento.requerente.pessoaFisica.razaoSocial" name="itemImplementoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="requerenteSelecionado" property="itemImplemento.requerente.id" name="itemImplementoForm" />
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<label>Prestador de Serviço</label>
									<html:text styleClass="form-control input-sm" styleId="prestador" property="itemImplemento.prestador.nome" name="itemImplementoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="prestadorSelecionado" property="itemImplemento.prestador.id" name="itemImplementoForm" />
								</div>
							</div>

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<div class="panel panel-success" style="margin-bottom: 0px;">
										<div class="panel-heading cor-sistema" style="color: white;">
											<label>Implemento</label>
										</div>
										<div class="panel-body">
											<div class="row">
												<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-12">
													<label>Implemento</label>
													<a href="#" id="modalCadastrarImplemento">
														<i style="text-shadow: 1px 0px 0px white, -1px 0px 0px white, 0px 1px 0px white, 0px -1px 0px white; color: green; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-plus"></i>
													</a>
													<html:text styleClass="form-control input-sm cor-campos-composicao-sistema" styleId="implemento" property="itemImplemento.implemento.nomeCompleto" name="itemImplementoForm" />
													<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
													<html:hidden styleId="implementoSelecionado" property="itemImplemento.implemento.id" name="itemImplementoForm" />
												</div>
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
						<logic:notPresent property="itemImplemento.id" name="itemImplementoForm">
							<logic:equal name="itemImplementoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.item.inserir)">
								<div class="form-group col-xs-12 col-sm-3 col-md-4 col-lg-4" style="margin-bottom: 0px;">
									<button type="submit" id="inserir" class="btn btn-success btn-sm cor-sistema btn-block">
										<i class="fa fa-save"></i>
										Inserir
									</button>
								</div>
							</logic:equal>
						</logic:notPresent>
						<logic:present property="itemImplemento.id" name="itemImplementoForm">
							<logic:equal name="itemImplementoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.item.alterar)">
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

						<logic:equal name="itemImplementoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.item.filtrar)">
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
		$("#produto").addClass("obrigatorio");
		$("#quantidade").addClass("obrigatorio");
		$("#almoxarife").addClass("obrigatorio");
		$("#requerente").addClass("obrigatorio");
		$("#implemento").addClass("obrigatorio");

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#produto").prop("maxlength", 100);
		$("#implemento").prop("maxlength", 100);
		$("#quantidade").prop("maxlength", 15);
		$("#almoxarife").prop("maxlength", 70);
		$("#requerente").prop("maxlength", 70);
		$("#safra").prop("maxlength", 100);
		$("#setor").prop("maxlength", 100);
		$("#cultura").prop("maxlength", 60);
		$("#prestador").attr("maxlength", 50);
		
		
		$( "#prestador" ).attr("placeholder","Prestador de Serviço");

		/* Setando os placeholder dos campos*/
		$("#produto").prop("placeholder", "Produto (digite 4 caracteres)");
		$("#implemento").prop("placeholder", "Imóvel");
		$("#almoxarife").prop("placeholder", "Almoxarife");
		$("#requerente").prop("placeholder", "Requerente");
		$("#veiculo").prop("placeholder", "Veiculo");
		$("#safra").prop("placeholder", "Safra");
		$("#setor").prop("placeholder", "Setor");
		$("#cultura").prop("placeholder", "Cultura");

		$("#quantidade").prop("placeholder", "0,00");

		$('#helpQuantidade').css("display", "none");
		$('#helpQuantidade').css("color", "red");

		$('#produto').keyup(function() {
			if ($('#produto').val() == '') {
				$('#produtoSelecionado').val(null);
				$('#custoMedio').val(null);
				$('#estoque').val(null);
			}
		});

		$('#produto').autocomplete({
			minChars : 4,
			noCache : true,
			paramName : 'itemImplemento.produto.nomeCompleto',
			serviceUrl : '${contextPath}/restrito/sistema/itemImplemento.src?method=selecionarProdutoAutoComplete',
			onSelect : function(suggestion) {
				$('#produtoSelecionado').val(suggestion.data);
				/* $('#custoMedio').val(suggestion.custoMedio);
				$('#estoque').val(suggestion.estoque);

				calcularCustoTotal(); */
				
				calcularSaldoPorProduto();

				$("#quantidade").focus();
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#produtoSelecionado').val(null);
					$('#custoMedio').val(null);
					$('#estoque').val(null);
					$('#custoTotal').val(null);
					//calcularSaldoPorProduto();
				}
			}
		});

		$('#almoxarife').keyup(function() {
			if ($('#almoxarife').val() == '') {
				$('#almoxarifeSelecionado').val(null);
			}
		});
		$('#almoxarife').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'itemImplemento.almoxarife.pessoaFisica.razaoSocial',
			serviceUrl : '${contextPath}/restrito/sistema/itemImplemento.src?method=selecionarAlmoxarifeAutoComplete',
			onSelect : function(suggestion) {
				$('#almoxarifeSelecionado').val(suggestion.data);

				$("#requerente").focus();
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#almoxarifeSelecionado').val(null);
				}
			}
		});

		$('#requerente').keyup(function() {
			if ($('#requerente').val() == '') {
				$('#requerenteSelecionado').val(null);
			}
		});
		$('#requerente').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'itemImplemento.requerente.pessoaFisica.razaoSocial',
			serviceUrl : '${contextPath}/restrito/sistema/itemImplemento.src?method=selecionarRequerenteAutoComplete',
			onSelect : function(suggestion) {
				$('#requerenteSelecionado').val(suggestion.data);
				
				$("#implemento").focus();
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#requerenteSelecionado').val(null);
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

		$('#quantidade').keyup(function() {
			var estoque = $("#estoque").val();			
			
			var theForm = $('form[name=itemImplementoForm]');
			var params = theForm.serialize();
			var actionURL = theForm.attr('action') + '?method=calcularDisponibilidadeQuantidadeEstoque';
			$.ajax({
				type : 'POST',
				url : actionURL,
				data : params,
				success : function(data, textStatus, XMLHttpRequest) {
					
					var estoqueDisponivel = (data.estoqueDisponivel);

					if (estoqueDisponivel == 'false') {
						$('#quantidade').css("background-color", "#bf0000");
						$('#quantidade').css("color", "white");
						$('#quantidade').css("font-weight", "bold");
						$('#quantidade').css("font-size", "16px");

						$('#helpQuantidade').css("display", "block");
						$('#helpQuantidade').html("Máximo: " + estoque);

						$('#inserir').css("display", "none");
						$('#alterar').css("display", "none");
					} else {

						$('#quantidade').css("background-color", "#fff");
						$('#quantidade').css("color", "#333");
						$('#quantidade').css("font-weight", "normal");
						$('#quantidade').css("font-size", "12px");

						$('#helpQuantidade').css("display", "none");

						$('#inserir').css("display", "block");
						$('#alterar').css("display", "block");
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(textStatus);					
				}
			});
			
		});

		$('#quantidade').keyup(function() {
			calcularCustoTotal();
		});

		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_itemImplemento").prop("autocomplete", "off");

		$('#form_itemImplemento').on('submit', function(e) {
			abrirModalProcessando();
		});

		$('#inserir').click(function() {
			executar('form_itemImplemento', 'inserir');
		});

		$('#alterar').click(function() {
			executar('form_itemImplemento', 'alterar');
		});

		$('#limpar').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_itemImplemento', 'limpar');
		});

		$('#listagem').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_itemImplemento', 'abrirListagem');
		});
		
		$('#implemento').keyup(function() {
			if ($('#implemento').val() == null || $('#implemento').val() == '') {
				$('#implementoSelecionado').val(null);
			}
		});
		
		$('#implemento').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'itemImplemento.implemento.nomeCompleto',
			/* params : {
				'saidaGrao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/itemImplemento.src?method=selecionarImplementoAutoComplete',
			onSelect : function(suggestion) {
				$('#implementoSelecionado').val(suggestion.data);
			},
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			}, */
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#implementoSelecionado').val(null);
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
			paramName : 'itemImplemento.safra.nome',
			serviceUrl : '${contextPath}/restrito/sistema/itemImplemento.src?method=selecionarSafraAutoComplete',
			onSelect : function(suggestion) {
				$('#safraSelecionada').val(suggestion.data);

				if ($('#tipo').val() == '') {
					$("#safra").focus();
				} else if ($('#tipo').val() == 'Safra/Setor') {
					$("#setor").focus();
				} else if ($('#tipo').val() == 'Tudo') {
					$("#almoxarife").focus();
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
			paramName : 'itemImplemento.setor.nomeCompleto',
			params : {
				'itemImplemento.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			},
			serviceUrl : '${contextPath}/restrito/sistema/itemImplemento.src?method=selecionarSetorAutoComplete',
			onSelect : function(suggestion) {
				$('#setorSelecionado').val(suggestion.data);

				if ($('#tipo').val() == '') {
					$("#safra").focus();
				} else if ($('#tipo').val() == 'Safra/Setor') {
					$("#almoxarife").focus();
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
			paramName : 'itemImplemento.cultura.nome',
			params : {
				'itemImplemento.setor.id' : function() {
					return $('#setorSelecionado').val();
				},
				'itemImplemento.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			},
			/* params : {
				'saidaGrao.itemImplemento.id' : function() {
					return $('#itemImplementoSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/itemImplemento.src?method=selecionarCulturaAutoComplete',
			onSelect : function(suggestion) {
				$('#culturaSelecionada').val(suggestion.data);

				$("#almoxarife").focus();
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
		
		$('#modalCadastrarImplemento').on('click', function() {
			var actionURL = '${contextPath}/restrito/sistema/implemento.src?method=abrirModal';

			$.ajax({
				type : 'POST',
				url : actionURL,
				success : function(data, textStatus, XMLHttpRequest) {
					BootstrapDialog.show({
						size : BootstrapDialog.SIZE_NORMAL,
						title : 'Cadastrar Implemento',
						message : $('<div id="id-modalImplemento"></div>').append(data),
						type : BootstrapDialog.TYPE_SUCCESS, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
						closable : false, // <-- Default value is false
						draggable : true, // <-- Default value is false
						onshown : function(dialogRef) {
							/* Foco inicial */
							$("#codigoModal").focus();
						},
						buttons : [ {
							hotkey : 13,
							label : 'Inserir',
							cssClass : 'btn-success',
							action : function(dialogRef) {
								var theForm = $('form[name=implementoForm]');
								var params = theForm.serialize();
								var actionURL = theForm.prop('action') + '?method=inserirModal';

								$.ajax({
									type : 'POST',
									url : actionURL,
									data : params,
									success : function(data, textStatus, XMLHttpRequest) {
										$('#id-modalImplemento').html(data);

										$("#codigoModal").focus();
									},
									error : function(XMLHttpRequest, textStatus, errorThrown) {
										alert(textStatus);
									}
								});
							}
						}, {
							label : 'Fechar',
							action : function(dialogRef) {

								var actionURL = '${contextPath}/restrito/sistema/implemento.src?method=fecharModal';

								$.ajax({
									type : 'POST',
									url : actionURL,
									success : function(data, textStatus, XMLHttpRequest) {
										dialogRef.close();

										$("#implemento").focus();
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
	
	function calcularSaldoPorProduto() {								
		var theForm = $('form[name=itemImplementoForm]');
		var params = theForm.serialize();
		var actionURL = theForm.attr('action') + '?method=calcularSaldoPorProduto';
		$.ajax({
			type : 'POST',
			url : actionURL,
			data : params,
			success : function(data, textStatus, XMLHttpRequest) {
				$('#custoMedio').val(data.custoMedio);
				$('#estoque').val(data.estoque);

				calcularCustoTotal();
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(textStatus);
				$('#custoMedio').val(null);
				$('#estoque').val(null);

				calcularCustoTotal(); 
			}
		}); 
	}

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

			//$("#safra").focus();
		} else if ($('#tipo').val() == 'Tudo') {
			$("#safra").addClass("obrigatorio");

			$("#safra").removeClass("bloqueado");

			$("#setor").removeClass("obrigatorio");
			$("#setor").addClass("bloqueado");

			$("#cultura").removeClass("obrigatorio");
			$("#cultura").addClass("bloqueado");

			//$("#safra").focus();
		} else if ($('#tipo').val() == 'Cultura') {
			$("#safra").addClass("obrigatorio");
			$("#cultura").addClass("obrigatorio");

			$("#safra").removeClass("bloqueado");
			$("#cultura").removeClass("bloqueado");

			$("#setor").removeClass("obrigatorio");
			$("#setor").addClass("bloqueado");

			//$("#safra").focus();
		}

		recarregarObrigatorios();
	}

	function calcularCustoTotal() {
		var custoMedio = $("#custoMedio").val();
		var quantidade = $("#quantidade").val();
		var custoTotal = "0.0";

		if (custoMedio == null || custoMedio == '') {
			valorPago = '0.0';
		}
		if (quantidade == null || quantidade == '') {
			quantidade = '0.0';
		}

		custoMedio = parseFloat(custoMedio.replace('.', '').replace(',', '.').replace('R$', ''), 10);
		quantidade = parseFloat(quantidade.replace('.', '').replace(',', '.').replace('R$', ''), 10);

		custoTotal = custoMedio * quantidade;

		$("#custoTotal").val(String(custoTotal.toFixed(2)).formatMoney());

	}

	String.prototype.formatMoney = function() {
		var v = this;

		if (v.indexOf('.') === -1) {
			v = v.replace(/([\d]+)/, "$1,00");
		}

		v = v.replace(/([\d]+)\.([\d]{1})$/, "$1,$20");
		v = v.replace(/([\d]+)\.([\d]{2})$/, "$1,$2");
		v = v.replace(/([\d]+)([\d]{3}),([\d]{2})$/, "$1.$2,$3");

		return v;
	};
</script>