<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!-- CAMPOS DE CADASTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -30px; margin-bottom: -15px;">
		<h1 class="page-header cabecalho_pagina">
			<i class="fa fa-graduation-cap" style="font-size: 26px;"></i>
			Cadastrar Abastecimento
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Cadastro dos Abastecimentos de veiculos e implementos
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<logic:present property="abastecimento.id" name="abastecimentoForm">
	<div class="row">
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px;">
			<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-user-plus"></i>
				&nbsp;${abastecimentoForm.abastecimento.nomeUsuarioCriacao }
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
			<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-calendar-plus-o"></i>
				&nbsp;${abastecimentoForm.abastecimento.dataOcorrenciaCriacao}
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
			<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-refresh"></i>
				&nbsp;${abastecimentoForm.abastecimento.nomeUsuarioAlteracao}
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-left: 0px;">
			<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-calendar-o"></i>
				&nbsp;${abastecimentoForm.abastecimento.dataOcorrenciaAlteracao}
			</div>
		</div>
	</div>
</logic:present>

<div class="row">
	<!-- Define o tamanho geral da abastecimento -->
	<div class="col-md-offset-1 col-lg-offset-1 col-xs-12 col-sm-12 col-md-10 col-lg-10">
		<html:form styleId="form_abastecimento" action="restrito/sistema/abastecimento" method="post">
			<html:hidden property="method" value="empty" />

			<div class="panel sombra">

				<!-- INICIO FORMULARIO -->
				<div class="panel-body">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
									<label>Data</label>
									<html:text styleClass="form-control input-sm text-center data focoInicial" styleId="data" property="abastecimento.data" name="abastecimentoForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-9 col-lg-6">
									<label>Produto</label>
									<html:text styleClass="form-control input-sm" styleId="produto" property="abastecimento.produto.nome" name="abastecimentoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="produtoSelecionado" property="abastecimento.produto.id" name="abastecimentoForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
									<label>Estoque</label>
									<html:text styleClass="form-control input-sm text-center bloqueado" styleId="estoque" property="estoque" name="abastecimentoForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
									<label>Custo médio</label>
									<html:text styleClass="form-control input-sm text-center bloqueado" styleId="custoMedio" property="abastecimento.custoMedio" name="abastecimentoForm" />
								</div>



								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
									<label>Quatidade</label>
									<html:text styleClass="form-control input-sm text-center" styleId="quantidade" property="abastecimento.quantidade" name="abastecimentoForm" />
									<p class="help-block" id="helpQuantidade"></p>
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
									<label>Custo Total</label>
									<html:text styleClass="form-control input-sm text-center bloqueado" styleId="custoTotal" property="abastecimento.custoTotal" name="abastecimentoForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<label>Almoxarife</label>
									<html:text styleClass="form-control input-sm" styleId="almoxarife" property="abastecimento.almoxarife.pessoaFisica.razaoSocial" name="abastecimentoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="almoxarifeSelecionado" property="abastecimento.almoxarife.id" name="abastecimentoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<label>Requerente</label>
									<html:text styleClass="form-control input-sm" styleId="requerente" property="abastecimento.requerente.pessoaFisica.razaoSocial" name="abastecimentoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="requerenteSelecionado" property="abastecimento.requerente.id" name="abastecimentoForm" />
								</div>
							</div>

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<div class="panel panel-success">
										<div class="panel-heading cor-sistema" style="color: white;">
											<label>Veiculo</label>
										</div>
										<div class="panel-body">
											<div class="row">
												<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-4">
													<label>Veiculo</label>
													<a href="#" id="modalCadastrarVeiculo">
														<i style="text-shadow: 1px 0px 0px white, -1px 0px 0px white, 0px 1px 0px white, 0px -1px 0px white; color: green; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-plus"></i>
													</a>
													<html:text styleClass="form-control input-sm cor-campos-composicao-sistema" styleId="veiculo" property="abastecimento.veiculo.nome" name="abastecimentoForm" />
													<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
													<html:hidden styleId="veiculoSelecionado" property="abastecimento.veiculo.id" name="abastecimentoForm" />

												</div>
												<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3">
													<label>Tipo</label>
													<html:text styleClass="form-control input-sm bloqueado" styleId="veiculoTipo" property="abastecimento.veiculo.tipo" name="abastecimentoForm" />
												</div>
												<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-2">
													<label>Código</label>
													<html:text styleClass="form-control input-sm bloqueado text-center" styleId="veiculoCodigo" property="abastecimento.veiculo.codigo" name="abastecimentoForm" />
												</div>
												<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3">
													<label>Modelo</label>
													<html:text styleClass="form-control input-sm bloqueado" styleId="veiculoModelo" property="abastecimento.veiculo.modelo" name="abastecimentoForm" />
												</div>
												<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3">
													<label>Ano Fabricação</label>
													<html:text styleClass="form-control input-sm bloqueado text-center" styleId="veiculoAnoFabricacao" property="abastecimento.veiculo.anoFabricacao" name="abastecimentoForm" />
												</div>
												<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
													<label>Nº Chassis</label>
													<html:text styleClass="form-control input-sm bloqueado text-center" styleId="veiculoNumeroChassis" property="abastecimento.veiculo.numeroChassis" name="abastecimentoForm" />
												</div>
												<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
													<label>Km/Horímetro</label>
													<html:text styleClass="form-control input-sm text-center" styleId="kmHorimetro" property="abastecimento.kmHorimetro" name="abastecimentoForm" />
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
						<logic:notPresent property="abastecimento.id" name="abastecimentoForm">
							<logic:equal name="abastecimentoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.abastecimento.inserir)">
								<div class="form-group col-xs-12 col-sm-3 col-md-4 col-lg-4" style="margin-bottom: 0px;">
									<button type="submit" id="inserir" class="btn btn-success btn-sm cor-sistema btn-block">
										<i class="fa fa-save"></i>
										Inserir
									</button>
								</div>
							</logic:equal>
						</logic:notPresent>
						<logic:present property="abastecimento.id" name="abastecimentoForm">
							<logic:equal name="abastecimentoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.abastecimento.alterar)">
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

						<logic:equal name="abastecimentoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.abastecimento.filtrar)">
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
		$("#veiculo").addClass("obrigatorio");

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#produto").prop("maxlength", 100);
		$("#quantidade").prop("maxlength", 15);
		$("#almoxarife").prop("maxlength", 70);
		$("#requerente").prop("maxlength", 70);
		$("#veiculo").prop("maxlength", 50);
		$("#kmHorimetro").prop("maxlength", 50);

		/* Setando os placeholder dos campos*/
		$("#produto").prop("placeholder", "Produto");
		$("#almoxarife").prop("placeholder", "Almoxarife");
		$("#requerente").prop("placeholder", "Requerente");
		$("#veiculo").prop("placeholder", "Veiculo");
		$("#kmHorimetro").prop("placeholder", "km / Horímetro");

		$("#quantidade").prop("placeholder", "0,00");

		$('#helpQuantidade').css("display", "none");
		$('#helpQuantidade').css("color", "red");

		$('#veiculo').keyup(function() {
			if ($('#veiculo').val() == null || $('#veiculo').val() == "") {
				$('#veiculoSelecionado').val(null);
				$('#veiculoTipo').val(null);
				$('#veiculoCodigo').val(null);
				$('#veiculoModelo').val(null);
				$('#veiculoAnoFabricacao').val(null);
				$('#veiculoNumeroChassis').val(null);
			}
		});
		$('#veiculo').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'abastecimento.veiculo.nomeCompleto',
			serviceUrl : '${contextPath}/restrito/sistema/abastecimento.src?method=selecionarVeiculoAutoComplete',
			onSelect : function(suggestion) {
				$('#veiculoSelecionado').val(suggestion.data);
				$('#veiculoTipo').val(suggestion.tipo);
				$('#veiculoCodigo').val(suggestion.codigo);
				$('#veiculoModelo').val(suggestion.modelo);
				$('#veiculoAnoFabricacao').val(suggestion.anoFabricacao);
				$('#veiculoNumeroChassis').val(suggestion.numeroChassis);

				$('#veiculo').val(suggestion.nome);
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#veiculoSelecionado').val(null);
					$('#veiculoTipo').val(null);
					$('#veiculoCodigo').val(null);
					$('#veiculoModelo').val(null);
					$('#veiculoAnoFabricacao').val(null);
					$('#veiculoNumeroChassis').val(null);
				}
			}
		});
		

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
			paramName : 'abastecimento.produto.nome',
			serviceUrl : '${contextPath}/restrito/sistema/abastecimento.src?method=selecionarProdutoAutoComplete',
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value;
				return formatResultAutoCompleteHightLigth(valorExibir, currentValue);
			}, */
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
			paramName : 'abastecimento.almoxarife.pessoaFisica.razaoSocial',
			serviceUrl : '${contextPath}/restrito/sistema/abastecimento.src?method=selecionarAlmoxarifeAutoComplete',
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
			paramName : 'abastecimento.requerente.pessoaFisica.razaoSocial',
			serviceUrl : '${contextPath}/restrito/sistema/abastecimento.src?method=selecionarRequerenteAutoComplete',
			onSelect : function(suggestion) {
				$('#requerenteSelecionado').val(suggestion.data);

				$("#veiculo").focus();
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#requerenteSelecionado').val(null);
				}
			}
		});

		$('#quantidade').keyup(function() {
			var estoque = $("#estoque").val();			
			
			var theForm = $('form[name=abastecimentoForm]');
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
		$("#form_abastecimento").prop("autocomplete", "off");

		$('#form_abastecimento').on('submit', function(e) {
			abrirModalProcessando();
		});

		$('#inserir').click(function() {
			executar('form_abastecimento', 'inserir');
		});

		$('#alterar').click(function() {
			executar('form_abastecimento', 'alterar');
		});

		$('#limpar').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_abastecimento', 'limpar');
		});

		$('#listagem').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_abastecimento', 'abrirListagem');
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
						message : $('<div id="id-modalVeiculo"></div>').html(data),
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

	});
	
	function calcularSaldoPorProduto() {								
		var theForm = $('form[name=abastecimentoForm]');
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