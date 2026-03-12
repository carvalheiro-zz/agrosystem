<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!-- CAMPOS DE CADASTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -30px; margin-bottom: -15px;">
		<h1 class="page-header cabecalho_pagina">
			<i class="fa fa-graduation-cap" style="font-size: 26px;"></i>
			Cadastrar Produtos
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Cadastro das Produtos
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<logic:present property="produto.id" name="produtoForm">
	<div class="row">
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px;">
			<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-user-plus"></i>
				&nbsp;${produtoForm.produto.nomeUsuarioCriacao }
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
			<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-calendar-plus-o"></i>
				&nbsp;${produtoForm.produto.dataOcorrenciaCriacao}
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
			<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-refresh"></i>
				&nbsp;${produtoForm.produto.nomeUsuarioAlteracao}
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-left: 0px;">
			<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-calendar-o"></i>
				&nbsp;${produtoForm.produto.dataOcorrenciaAlteracao}
			</div>
		</div>
	</div>
</logic:present>

<div class="row">
	<!-- Define o tamanho geral da produto -->
	<div class="col-md-offset-1 col-lg-offset-2 col-xs-12 col-sm-12 col-md-6 col-lg-6">
		<html:form styleId="form_produto" action="restrito/sistema/produto" method="post">
			<html:hidden property="method" value="empty" />

			<div class="panel sombra">

				<!-- INICIO FORMULARIO -->
				<div class="panel-body">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

							<div class="row">
							
								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<html:text styleClass="form-control input-lg bloqueado text-center" styleId="nomeCompleto" property="produto.nomeCompleto" name="produtoForm" />
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<label>Categoria</label>	
									<a href="#" id="modalCadastrarTipo">
										<i style="text-shadow: 1px 0px 0px white, -1px 0px 0px white, 0px 1px 0px white, 0px -1px 0px white; color: green; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-plus"></i>
									</a>								
									<html:text styleClass="form-control input-sm montarNomeCompletoProduto focoInicial" styleId="tipo" property="produto.tipo.nome" name="produtoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="tipoSelecionado" property="produto.tipo.id" name="produtoForm" />
									<html:hidden styleId="classificacaoTipoSelecionado" property="produto.tipo.classificacao" name="produtoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-6">
									<label>Nome</label>
									<html:text styleClass="form-control input-sm montarNomeCompletoProduto" styleId="nome" property="produto.nome" name="produtoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="produtoSelecionado" property="produto.id" name="produtoForm" />
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-4">
									<label>Localizacao</label>
									<html:text styleClass="form-control input-sm text-center" styleId="localizacao" property="produto.localizacao" name="produtoForm" />
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-8">
									<label>Marca</label>
									<a href="#" id="modalCadastrarMarca">
										<i style="text-shadow: 1px 0px 0px white, -1px 0px 0px white, 0px 1px 0px white, 0px -1px 0px white; color: green; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-plus"></i>
									</a>									
									<html:text styleClass="form-control input-sm montarNomeCompletoProduto" styleId="marca" property="produto.marca.nome" name="produtoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="marcaSelecionada" property="produto.marca.id" name="produtoForm" />
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-7 col-lg-7">
									<label>Unidade Medida</label>
									<a href="#" id="modalCadastrarUnidadeMedida">
										<i style="text-shadow: 1px 0px 0px white, -1px 0px 0px white, 0px 1px 0px white, 0px -1px 0px white; color: green; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-plus"></i>
									</a>									
									<html:text styleClass="form-control input-sm montarNomeCompletoProduto" styleId="unidadeMedida" property="produto.unidadeMedida.nome" name="produtoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="unidadeMedidaSelecionada" property="produto.unidadeMedida.id" name="produtoForm" />
									<html:hidden styleId="sigla" property="produto.unidadeMedida.sigla" name="produtoForm" />
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-5 col-lg-5">
									<label>Estoque Min.</label>
									<html:text styleClass="form-control input-sm decimal text-center" styleId="quantidadeMinimaEstoque" property="produto.quantidadeMinimaEstoque" name="produtoForm" />
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="panel-footer">
					<div class="row">
						<!-- BOTOES -->
						<logic:notPresent property="produto.id" name="produtoForm">
							<logic:equal name="produtoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.produto.inserir)">
								<div class="form-group col-xs-12 col-sm-3 col-md-4 col-lg-4" style="margin-bottom: 0px;">
									<button type="submit" id="inserir" class="btn btn-success btn-sm cor-sistema btn-block">
										<i class="fa fa-save"></i>
										Inserir
									</button>
								</div>
							</logic:equal>
						</logic:notPresent>
						<logic:present property="produto.id" name="produtoForm">
							<logic:equal name="produtoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.produto.alterar)">
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

						<logic:equal name="produtoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.produto.filtrar)">
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
		$( ".focoInicial" ).focus();

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#nome").prop("maxlength", 100);
		$("#localizacao").prop("maxlength", 20);
		$("#quantidadeMinimaEstoque").prop("maxlength", 25);
		
		/* Setando NOTNULL nos campos*/
		$("#nome").addClass("obrigatorio");
		$("#localizacao").addClass("obrigatorio");
		$("#marca").addClass("obrigatorio");
		$("#tipo").addClass("obrigatorio");
		$("#unidadeMedida").addClass("obrigatorio");
		$("#quantidadeMinimaEstoque").addClass("obrigatorio");

		/* Setando os placeholder dos campos*/
		$( "#nome" ).prop("placeholder","Produto");
		$( "#localizacao" ).prop("placeholder","Localizacao");
		$( "#marca" ).prop("placeholder","Marca");
		$( "#tipo" ).prop("placeholder","Categoria");
		$( "#unidadeMedida" ).prop("placeholder","Unidade de medida");

		/* EVENTOS */

		// Desliga o auto-complete da pagina
		$("#form_produto").prop("autocomplete", "off");

		$('#form_produto').on('submit', function(e) {
			abrirModalProcessando();
		});

		$('#inserir').click(function() {
			executar('form_produto', 'inserir');
		});

		$('#alterar').click(function() {
			executar('form_produto', 'alterar');
		});

		$('#limpar').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_produto', 'limpar');
		});

		$('#listagem').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_produto', 'abrirListagem');
		});
			
		$('#nome').keyup(function() {
			if ($('#nome').val() == null || $('#nome').val() == '') {
				//$('#produtoSelecionado').val(null);
			}
		});
		
		$('#nome').autocomplete({
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
				$('#nome').val(suggestion.nome);
				
				montarNomeCompletoProduto();
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
		
		$('#marca').keyup(function() {
			if ($('#marca').val() == null || $('#marca').val() == '') {
				$('#marcaSelecionada').val(null);
			}
		});
		$('#marca').autocomplete({
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
				$('#marcaSelecionada').val(suggestion.data);
				
				montarNomeCompletoProduto();
			},
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			}, */
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#marcaSelecionada').val(null);
				}
			}
		});
		
		$('#tipo').keyup(function() {
			if ($('#tipo').val() == null || $('#tipo').val() == '') {
				$('#tipoSelecionado').val(null);
				$('#classificacaoTipoSelecionado').val(null);
			}
		});
		$('#tipo').autocomplete({
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
				$('#tipoSelecionado').val(suggestion.data);
				$('#classificacaoTipoSelecionado').val(suggestion.classificacao);
				
				
				montarNomeCompletoProduto();
			},
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			}, */
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#tipoSelecionado').val(null);
					$('#classificacaoTipoSelecionado').val(null);
				}
			}
		});
		
		$('#unidadeMedida').keyup(function() {
			if ($('#unidadeMedida').val() == null || $('#unidadeMedida').val() == '') {
				$('#unidadeMedidaSelecionada').val(null);
			}
		});
		$('#unidadeMedida').autocomplete({
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
				$('#unidadeMedidaSelecionada').val(suggestion.data);
				$('#sigla').val(suggestion.sigla);
				
				montarNomeCompletoProduto();
			},
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			}, */
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#unidadeMedidaSelecionada').val(null);
					$('#sigla').val(null);
				}
			}
		});
		
		$('#modalCadastrarMarca').on('click', function() {
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
		
		$('#modalCadastrarTipo').on('click', function() {
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
		
		$('#modalCadastrarUnidadeMedida').on('click', function() {
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
		
		$('.montarNomeCompletoProduto').keyup(function() {
			montarNomeCompletoProduto();
		});
		function montarNomeCompletoProduto() {
			
			var nome = $('#nome').val();
			var marca = $('#marca').val();
			var nomeTipo = $('#tipo').val();
			var classificacaoTipo = $('#classificacaoTipoSelecionado').val();
			//var nomeUnidadeMedida = $('#unidadeMedida').val();
			var siglaUnidadeMedida = $('#sigla').val();
			
			$('#nomeCompleto').val('['+classificacaoTipo+'] ' + nomeTipo + ' ' + nome + ' ' + marca + ' (' + siglaUnidadeMedida + ')');
		}
	});
</script>