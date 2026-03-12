<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!-- CAMPOS DE CADASTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -30px; margin-bottom: -15px;">
		<h1 class="page-header cabecalho_pagina">
			<i class="fa fa-graduation-cap" style="font-size: 26px;"></i>
			Cadastrar Aplicações de Insumos
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Cadastro das Aplicações de Insumos
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<logic:present property="aplicacao.id" name="aplicacaoForm">
	<div class="row">
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px;">
			<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-user-plus"></i>
				&nbsp;${aplicacaoForm.aplicacao.nomeUsuarioCriacao }
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
			<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-calendar-plus-o"></i>
				&nbsp;${aplicacaoForm.aplicacao.dataOcorrenciaCriacao}
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
			<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-refresh"></i>
				&nbsp;${aplicacaoForm.aplicacao.nomeUsuarioAlteracao}
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-left: 0px;">
			<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-calendar-o"></i>
				&nbsp;${aplicacaoForm.aplicacao.dataOcorrenciaAlteracao}
			</div>
		</div>
	</div>
</logic:present>

<div class="row">
	<!-- Define o tamanho geral da aplicacao -->
	<div class="col-md-offset-1 col-lg-offset-1 col-xs-12 col-sm-12 col-md-10 col-lg-10">
		<html:form styleId="form_aplicacao" action="restrito/sistema/aplicacao" method="post">
			<html:hidden property="method" value="empty" />

			<div class="panel sombra">

				<!-- INICIO FORMULARIO -->
				<div class="panel-body">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
									<label>Data</label>
									<html:text styleClass="form-control input-sm text-center data focoInicial" styleId="data" property="aplicacao.data" name="aplicacaoForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-9 col-lg-6">
									<label>Produto</label>
									<html:text styleClass="form-control input-sm" styleId="produto" property="aplicacao.produto.nomeCompleto" name="aplicacaoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="produtoSelecionado" property="aplicacao.produto.id" name="aplicacaoForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
									<label>Estoque</label>
									<html:text styleClass="form-control input-sm text-center bloqueado" styleId="estoque" property="estoque" name="aplicacaoForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
									<label>Custo médio</label>
									<html:text styleClass="form-control input-sm text-center bloqueado" styleId="custoMedio" property="aplicacao.custoMedio" name="aplicacaoForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
									<label>Quatidade</label>
									<html:text styleClass="form-control input-sm text-center" styleId="quantidade" property="aplicacao.quantidade" name="aplicacaoForm" />
									<p class="help-block" id="helpQuantidade"></p>
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label>Custo Total</label>
									<html:text styleClass="form-control input-sm text-center bloqueado" styleId="custoTotal" property="aplicacao.custoTotal" name="aplicacaoForm" />
								</div>
							</div>

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3">
									<label>Atribuição de Custo</label>
									<html:select styleClass="form-control input-sm" styleId="tipo" property="aplicacao.tipo" name="aplicacaoForm">
										<html:option value="">Selecione...</html:option>
										<html:option value="Safra/Setor">Safra/Setor</html:option>
										<html:option value="Tudo">Tudo</html:option>
										<html:option value="Cultura">Cultura</html:option>
									</html:select>
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3">
									<label>Safra</label>
									<html:text styleClass="form-control input-sm" styleId="safra" property="aplicacao.safra.nome" name="aplicacaoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="safraSelecionada" property="aplicacao.safra.id" name="aplicacaoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3">
									<label>Setor</label>
									<html:text styleClass="form-control input-sm" styleId="setor" property="aplicacao.setor.nomeCompleto" name="aplicacaoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="setorSelecionado" property="aplicacao.setor.id" name="aplicacaoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3">
									<label>Cultura</label>
									<html:text styleClass="form-control input-sm" styleId="cultura" property="aplicacao.cultura.nome" name="aplicacaoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="culturaSelecionada" property="aplicacao.cultura.id" name="aplicacaoForm" />
								</div>
							</div>

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<label>Almoxarife</label>
									<html:text styleClass="form-control input-sm" styleId="almoxarife" property="aplicacao.almoxarife.pessoaFisica.razaoSocial" name="aplicacaoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="almoxarifeSelecionado" property="aplicacao.almoxarife.id" name="aplicacaoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<label>Aplicador</label>
									<html:text styleClass="form-control input-sm" styleId="aplicador" property="aplicacao.aplicador.pessoaFisica.razaoSocial" name="aplicacaoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="aplicadorSelecionado" property="aplicacao.aplicador.id" name="aplicacaoForm" />
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<label>Prestador</label>
									<html:text styleClass="form-control input-sm" styleId="prestador" property="aplicacao.prestador.nome" name="aplicacaoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="prestadorSelecionado" property="aplicacao.prestador.id" name="aplicacaoForm" />
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="panel-footer">
					<div class="row">
						<!-- BOTOES -->
						<logic:notPresent property="aplicacao.id" name="aplicacaoForm">
							<logic:equal name="aplicacaoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.aplicacao.inserir)">
								<div class="form-group col-xs-12 col-sm-3 col-md-4 col-lg-4" style="margin-bottom: 0px;">
									<button type="submit" id="inserir" class="btn btn-success btn-sm cor-sistema btn-block">
										<i class="fa fa-save"></i>
										Inserir
									</button>
								</div>
							</logic:equal>
						</logic:notPresent>
						<logic:present property="aplicacao.id" name="aplicacaoForm">
							<logic:equal name="aplicacaoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.aplicacao.alterar)">
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

						<logic:equal name="aplicacaoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.aplicacao.filtrar)">
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
		$("#aplicador").addClass("obrigatorio");
		$("#veiculo").addClass("obrigatorio");

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#produto").prop("maxlength", 100);
		$("#quantidade").prop("maxlength", 15);
		$("#almoxarife").prop("maxlength", 70);
		$("#aplicador").prop("maxlength", 70);
		$("#veiculo").prop("maxlength", 50);
		$("#safra").prop("maxlength", 100);
		$("#setor").prop("maxlength", 100);
		$("#cultura").prop("maxlength", 60);
		$("#prestador").attr("maxlength", 50);
		$( "#prestador" ).attr("placeholder","Prestador");

		/* Setando os placeholder dos campos*/
		$("#produto").prop("placeholder", "Produto");
		$("#almoxarife").prop("placeholder", "Almoxarife");
		$("#aplicador").prop("placeholder", "Aplicador");
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
			minChars : 2,
			noCache : true,
			paramName : 'aplicacao.produto.nomeCompleto',
			serviceUrl : '${contextPath}/restrito/sistema/aplicacao.src?method=selecionarProdutoAutoComplete',
			formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value;
				return formatResultAutoCompleteHightLigth(valorExibir, currentValue);
			},
			onSelect : function(suggestion) {
				$('#produtoSelecionado').val(suggestion.data);
				 $('#custoMedio').val(suggestion.custoMedio);
				$('#estoque').val(suggestion.estoque);

				calcularCustoTotal(); 
				
				//calcularSaldoPorProduto();

				$("#quantidade").focus();
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#produtoSelecionado').val(null);
					$('#custoMedio').val(null);
					$('#estoque').val(null);
					$('#custoTotal').val(null);
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
			paramName : 'aplicacao.almoxarife.pessoaFisica.razaoSocial',
			serviceUrl : '${contextPath}/restrito/sistema/aplicacao.src?method=selecionarAlmoxarifeAutoComplete',
			onSelect : function(suggestion) {
				$('#almoxarifeSelecionado').val(suggestion.data);

				$("#aplicador").focus();
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#almoxarifeSelecionado').val(null);
				}
			}
		});

		$('#aplicador').keyup(function() {
			if ($('#aplicador').val() == '') {
				$('#aplicadorSelecionado').val(null);
			}
		});
		$('#aplicador').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'aplicacao.aplicador.pessoaFisica.razaoSocial',
			serviceUrl : '${contextPath}/restrito/sistema/aplicacao.src?method=selecionarAplicadorAutoComplete',
			onSelect : function(suggestion) {
				$('#aplicadorSelecionado').val(suggestion.data);
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#aplicadorSelecionado').val(null);
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
			
			var theForm = $('form[name=aplicacaoForm]');
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
		$("#form_aplicacao").prop("autocomplete", "off");

		$('#form_aplicacao').on('submit', function(e) {
			abrirModalProcessando();
		});

		$('#inserir').click(function() {
			executar('form_aplicacao', 'inserir');
		});

		$('#alterar').click(function() {
			executar('form_aplicacao', 'alterar');
		});

		$('#limpar').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_aplicacao', 'limpar');
		});

		$('#listagem').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_aplicacao', 'abrirListagem');
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
			paramName : 'aplicacao.safra.nome',
			serviceUrl : '${contextPath}/restrito/sistema/aplicacao.src?method=selecionarSafraAutoComplete',
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
			paramName : 'aplicacao.setor.nomeCompleto',
			params : {
				'aplicacao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			},
			serviceUrl : '${contextPath}/restrito/sistema/aplicacao.src?method=selecionarSetorAutoComplete',
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
			paramName : 'aplicacao.cultura.nome',
			params : {
				'aplicacao.setor.id' : function() {
					return $('#setorSelecionado').val();
				},
				'aplicacao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			},
			/* params : {
				'saidaGrao.aplicacao.id' : function() {
					return $('#aplicacaoSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/aplicacao.src?method=selecionarCulturaAutoComplete',
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

		gerenciarCamposSafraSetorCultura();

	});
	
	function calcularSaldoPorProduto() {								
		var theForm = $('form[name=aplicacaoForm]');
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