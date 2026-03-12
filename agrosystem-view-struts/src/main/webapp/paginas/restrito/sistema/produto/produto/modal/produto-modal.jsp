<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!-- CAMPOS DE CADASTRO -->
<div class="row">

	<logic:notEmpty name="erroAjax" scope="session">
		<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-11">
			<div class="alert alert-danger">
				<strong>Atenção!</strong>
				${erroAjax}
			</div>
		</div>
	</logic:notEmpty>
	<logic:notEmpty name="sucessoAjax" scope="session">
		<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-11">
			<div class="alert alert-success">
				<strong>Parabéns!</strong>
				${sucessoAjax}
			</div>
		</div>
	</logic:notEmpty>

	<!-- Define o tamanho geral da produto -->

	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<html:form styleId="form_produto" action="restrito/sistema/produto" method="post">
			<html:hidden property="method" value="empty" />

			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="row">

						<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<html:text styleClass="form-control input-lg bloqueado text-center" styleId="nomeCompletoModal" property="produto.nomeCompleto" name="produtoForm" />
						</div>

						<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
							<label>Categoria</label>
							<a href="#" id="modalCadastrarTipoModal">
								<i style="text-shadow: 1px 0px 0px white, -1px 0px 0px white, 0px 1px 0px white, 0px -1px 0px white; color: green; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-plus"></i>
							</a>
							<html:text styleClass="form-control input-sm montarNomeCompletoProduto focoInicial" styleId="tipoModal" property="produto.tipo.nome" name="produtoForm" />
							<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
							<html:hidden styleId="tipoSelecionadoModal" property="produto.tipo.id" name="produtoForm" />
							<html:hidden styleId="classificacaoTipoSelecionadoModal" property="produto.tipo.classificacao" name="produtoForm" />
						</div>

						<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-6">
							<label>Nome</label>
							<html:text styleClass="form-control input-sm montarNomeCompletoProduto" styleId="nomeModal" property="produto.nome" name="produtoForm" />
							<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
							<html:hidden styleId="produtoSelecionadoModal" property="produto.id" name="produtoForm" />
						</div>

						<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-4">
							<label>Localizacao</label>
							<html:text styleClass="form-control input-sm text-center" styleId="localizacaoModal" property="produto.localizacao" name="produtoForm" />
						</div>

						<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-8">
							<label>Marca</label>
							<a href="#" id="modalCadastrarMarcaModal">
								<i style="text-shadow: 1px 0px 0px white, -1px 0px 0px white, 0px 1px 0px white, 0px -1px 0px white; color: green; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-plus"></i>
							</a>
							<html:text styleClass="form-control input-sm montarNomeCompletoProduto" styleId="marcaModal" property="produto.marca.nome" name="produtoForm" />
							<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
							<html:hidden styleId="marcaSelecionadaModal" property="produto.marca.id" name="produtoForm" />
						</div>

						<div class="form-group col-xs-12 col-sm-12 col-md-7 col-lg-7">
							<label>Unidade Medida</label>
							<a href="#" id="modalCadastrarUnidadeMedidaModal">
								<i style="text-shadow: 1px 0px 0px white, -1px 0px 0px white, 0px 1px 0px white, 0px -1px 0px white; color: green; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-plus"></i>
							</a>
							<html:text styleClass="form-control input-sm montarNomeCompletoProduto" styleId="unidadeMedidaModal" property="produto.unidadeMedida.nome" name="produtoForm" />
							<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
							<html:hidden styleId="unidadeMedidaSelecionadaModal" property="produto.unidadeMedida.id" name="produtoForm" />
							<html:hidden styleId="siglaModal" property="produto.unidadeMedida.sigla" name="produtoForm" />
						</div>

						<div class="form-group col-xs-12 col-sm-12 col-md-5 col-lg-5">
							<label>Estoque Min.</label>
							<html:text styleClass="form-control input-sm decimal text-center" styleId="quantidadeMinimaEstoqueModal" property="produto.quantidadeMinimaEstoque" name="produtoForm" />
						</div>
					</div>
				</div>
			</div>
		</html:form>
	</div>

	<!-- /.col-lg-12 -->
</div>


<script type="text/javascript">
	$(document).ready(function() {

		$('#nomeModal').val(null);

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#nomeModal").prop("maxlength", 100);
		$("#localizacaoModal").prop("maxlength", 20);
		$("#quantidadeMinimaEstoqueModal").prop("maxlength", 25);

		/* Setando NOTNULL nos campos*/
		$("#nomeModal").addClass("obrigatorio");
		$("#localizacaoModal").addClass("obrigatorio");
		$("#marcaModal").addClass("obrigatorio");
		$("#tipoModal").addClass("obrigatorio");
		$("#unidadeMedidaModal").addClass("obrigatorio");
		$("#quantidadeMinimaEstoqueModal").addClass("obrigatorio");

		/* Setando os placeholder dos campos*/
		$("#nomeModal").prop("placeholder", "Produto");
		$("#localizacaoModal").prop("placeholder", "Localizacao");
		$("#marcaModal").prop("placeholder", "Marca");
		$("#tipoModal").prop("placeholder", "Categoria");
		$("#unidadeMedidaModal").prop("placeholder", "Unidade de medida");

		/* EVENTOS */

		// Desliga o auto-complete da pagina
		$("#form_produto").prop("autocomplete", "off");

		$('#form_produto').on('submit', function(e) {
			return false;
		});

		$('#nomeModal').keyup(function() {
			if ($('#nomeModal').val() == null || $('#nome').val() == '') {
				//$('#produtoSelecionado').val(null);
			}
		});

		$('#nomeModal').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'produto.nomeCompleto',
			/* params : {
				'saidaGrao.produto.id' : function() {
					return $('#produtoSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/produto.src?method=selecionarProdutoAutoComplete',
			onSelect : function(suggestion) {
				//$('#produtoSelecionado').val(suggestion.data);

				montarNomeCompletoProduto();
				
				$("#localizacaoModal").focus();
			},
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			}, */
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					//$('#produtoSelecionado').val(null);
				}
			}
		});

		$('#marcaModal').keyup(function() {
			if ($('#marcaModal').val() == null || $('#marca').val() == '') {
				$('#marcaSelecionadaModal').val(null);
			}
		});
		$('#marcaModal').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'produto.marca.nome',
			/* params : {
				'saidaGrao.produto.id' : function() {
					return $('#produtoSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/produto.src?method=selecionarMarcaAutoComplete',
			onSelect : function(suggestion) {
				$('#marcaSelecionadaModal').val(suggestion.data);

				montarNomeCompletoProduto();
				
				$("#unidadeMedidaModal").focus();
			},
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			}, */
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#marcaSelecionadaModal').val(null);
				}
			}
		});

		$('#tipoModal').keyup(function() {
			if ($('#tipoModal').val() == null || $('#tipoModal').val() == '') {
				$('#tipoSelecionadoModal').val(null);
			}
		});
		$('#tipoModal').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'produto.tipo.nome',
			/* params : {
				'saidaGrao.produto.id' : function() {
					return $('#produtoSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/produto.src?method=selecionarTipoAutoComplete',
			onSelect : function(suggestion) {
				$('#tipoSelecionadoModal').val(suggestion.data);
				$('#classificacaoTipoSelecionadoModal').val(suggestion.classificacao);
				
				
				montarNomeCompletoProduto();
				
				$("#nomeModal").focus();
			},
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			}, */
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#tipoSelecionadoModal').val(null);
					$('#classificacaoTipoSelecionadoModal').val(null);
				}
			}
		});
		
		$('#unidadeMedidaModal').keyup(function() {
			if ($('#unidadeMedidaModal').val() == null || $('#unidadeMedidaModal').val() == '') {
				$('#unidadeMedidaSelecionadaModal').val(null);
			}
		});
		$('#unidadeMedidaModal').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'produto.unidadeMedida.nome',
			/* params : {
				'saidaGrao.produto.id' : function() {
					return $('#produtoSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/produto.src?method=selecionarUnidadeMedidaAutoComplete',
			onSelect : function(suggestion) {
				$('#unidadeMedidaSelecionadaModal').val(suggestion.data);
				$('#siglaModal').val(suggestion.sigla);

				montarNomeCompletoProduto();
				
				$("#quantidadeMinimaEstoqueModal").focus();
			},
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			}, */
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#unidadeMedidaSelecionadaModal').val(null);
					$('#siglaModal').val(null);
				}
			}
		});

		$('#modalCadastrarMarcaModal').on('click', function() {
			var actionURL = '${contextPath}/restrito/sistema/marca.src?method=abrirModal';

			$.ajax({
				type : 'POST',
				url : actionURL,
				success : function(data, textStatus, XMLHttpRequest) {
					BootstrapDialog.show({
						size : BootstrapDialog.SIZE_NORMAL,
						title : 'Cadastrar Marca',
						message : $('<div id="id-modalMarca"></div>').append(data),
						type : BootstrapDialog.TYPE_SUCCESS, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
						closable : false, // <-- Default value is false
						draggable : true, // <-- Default value is false
						onshown : function(dialogRef) {
							/* Foco inicial */
							$("#nomeMarcaModal").focus();
						},
						buttons : [ {
							hotkey : 13,
							label : 'Inserir',
							cssClass : 'btn-success',
							action : function(dialogRef) {
								var theForm = $('form[name=marcaForm]');
								var params = theForm.serialize();
								var actionURL = theForm.attr('action') + '?method=inserirModal';

								$.ajax({
									type : 'POST',
									url : actionURL,
									data : params,
									success : function(data, textStatus, XMLHttpRequest) {
										$('#id-modalMarca').html(data);

										$("#nomeMarcaModal").focus();
									},
									error : function(XMLHttpRequest, textStatus, errorThrown) {
										alert(textStatus);
									}
								});
							}
						}, {
							label : 'Fechar',
							action : function(dialogRef) {

								var actionURL = '${contextPath}/restrito/sistema/marca.src?method=fecharModal';

								$.ajax({
									type : 'POST',
									url : actionURL,
									success : function(data, textStatus, XMLHttpRequest) {
										dialogRef.close();

										$("#marca").focus();
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

		$('#modalCadastrarTipoModal').on('click', function() {
			var actionURL = '${contextPath}/restrito/sistema/tipo.src?method=abrirModal';

			$.ajax({
				type : 'POST',
				url : actionURL,
				success : function(data, textStatus, XMLHttpRequest) {

					BootstrapDialog.show({
						size : BootstrapDialog.SIZE_NORMAL,
						title : 'Cadastrar Tipo',
						message : $('<div id="id-modalTipo"></div>').append(data),
						type : BootstrapDialog.TYPE_SUCCESS, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
						closable : false, // <-- Default value is false
						draggable : true, // <-- Default value is false
						onshown : function(dialogRef) {
							/* Foco inicial */
							$("#nomeTipoModal").focus();
						},
						buttons : [ {
							hotkey : 13,
							label : 'Inserir',
							cssClass : 'btn-success',
							action : function(dialogRef) {
								var theForm = $('form[name=tipoForm]');
								var params = theForm.serialize();
								var actionURL = theForm.attr('action') + '?method=inserirModal';

								$.ajax({
									type : 'POST',
									url : actionURL,
									data : params,
									success : function(data, textStatus, XMLHttpRequest) {
										$('#id-modalTipo').html(data);

										$("#nomeTipoModal").focus();
									},
									error : function(XMLHttpRequest, textStatus, errorThrown) {
										alert(textStatus);
									}
								});
							}
						}, {
							label : 'Fechar',
							action : function(dialogRef) {

								var actionURL = '${contextPath}/restrito/sistema/tipo.src?method=fecharModal';

								$.ajax({
									type : 'POST',
									url : actionURL,
									success : function(data, textStatus, XMLHttpRequest) {
										dialogRef.close();

										$("#tipo").focus();
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

		$('#modalCadastrarUnidadeMedidaModal').on('click', function() {
			var actionURL = '${contextPath}/restrito/sistema/unidadeMedida.src?method=abrirModal';

			$.ajax({
				type : 'POST',
				url : actionURL,
				success : function(data, textStatus, XMLHttpRequest) {

					BootstrapDialog.show({
						size : BootstrapDialog.SIZE_NORMAL,
						title : 'Cadastrar Unidade de Medida',
						message : $('<div id="id-modalUnidadeMedida"></div>').append(data),
						type : BootstrapDialog.TYPE_SUCCESS, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
						closable : false, // <-- Default value is false
						draggable : true, // <-- Default value is false
						onshown : function(dialogRef) {
							/* Foco inicial */
							$("#nomeUnidadeMedidaModal").focus();
						},
						buttons : [ {
							hotkey : 13,
							label : 'Inserir',
							cssClass : 'btn-success',
							action : function(dialogRef) {
								var theForm = $('form[name=unidadeMedidaForm]');
								var params = theForm.serialize();
								var actionURL = theForm.attr('action') + '?method=inserirModal';

								$.ajax({
									type : 'POST',
									url : actionURL,
									data : params,
									success : function(data, textStatus, XMLHttpRequest) {
										$('#id-modalUnidadeMedida').html(data);

										$("#nomeUnidadeMedidaModal").focus();
									},
									error : function(XMLHttpRequest, textStatus, errorThrown) {
										alert(textStatus);
									}
								});
							}
						}, {
							label : 'Fechar',
							action : function(dialogRef) {

								var actionURL = '${contextPath}/restrito/sistema/unidadeMedida.src?method=fecharModal';

								$.ajax({
									type : 'POST',
									url : actionURL,
									success : function(data, textStatus, XMLHttpRequest) {
										dialogRef.close();

										$("#unidadeMedida").focus();
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

		$('.montarNomeCompletoProdutoModal').keyup(function() {
			montarNomeCompletoProduto();
		});
		function montarNomeCompletoProduto() {
			var nome = $('#nomeModal').val();
			var marca = $('#marcaModal').val();
			var nomeTipo = $('#tipoModal').val();
			var classificacaoTipo = $('#classificacaoTipoSelecionadoModal').val();
			//var nomeUnidadeMedida = $('#unidadeMedida').val();
			var siglaUnidadeMedida = $('#siglaModal').val();
			
			$('#nomeCompletoModal').val('['+classificacaoTipo+'] ' + nomeTipo + ' ' + nome + ' ' + marca + ' (' + siglaUnidadeMedida + ')');
		}
	});
</script>