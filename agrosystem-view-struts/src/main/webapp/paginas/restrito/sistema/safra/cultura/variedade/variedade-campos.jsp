<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!-- CAMPOS DE CADASTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -30px; margin-bottom: -15px;">
		<h1 class="page-header cabecalho_pagina">
			<i class="fa fa-graduation-cap" style="font-size: 26px;"></i>
			Cadastrar Variedades
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Cadastro das Variedades
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<div class="row">
	<!-- Define o tamanho geral da variedade -->
	<div class="col-md-offset-1 col-lg-offset-1 col-xs-12 col-sm-12 col-md-10 col-lg-10">
		<html:form styleId="form_variedade" action="restrito/sistema/variedade" method="post">
			<html:hidden property="method" value="empty" />

			<div class="panel sombra">

				<!-- INICIO FORMULARIO -->
				<div class="panel-body">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

							<div class="row">
							
								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<html:text styleClass="form-control input-lg bloqueado text-center" styleId="nomeCompleto" property="variedade.nomeCompleto" name="variedadeForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<label>Nome</label>
									<html:text styleClass="form-control input-sm montarNomeCompletoVariedade" styleId="nome" property="variedade.nome" name="variedadeForm" />									
								</div>								
								
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<label>Cultura</label>
									<a href="#" id="modalCadastrarCultura">
										<i style="text-shadow: 1px 0px 0px white, -1px 0px 0px white, 0px 1px 0px white, 0px -1px 0px white; color: green; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-plus"></i>
									</a>									
									<html:text styleClass="form-control input-sm montarNomeCompletoVariedade" styleId="cultura" property="variedade.cultura.nome" name="variedadeForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="culturaSelecionada" property="variedade.cultura.id" name="variedadeForm" />
								</div>
								
							</div>
						</div>
					</div>
				</div>
				<div class="panel-footer">
					<div class="row">
						<!-- BOTOES -->
						<logic:notPresent property="variedade.id" name="variedadeForm">
							<logic:equal name="variedadeForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.variedade.inserir)">
								<div class="form-group col-xs-12 col-sm-3 col-md-4 col-lg-4" style="margin-bottom: 0px;">
									<button type="submit" id="inserir" class="btn btn-success btn-sm cor-sistema btn-block">
										<i class="fa fa-save"></i>
										Inserir
									</button>
								</div>
							</logic:equal>
						</logic:notPresent>
						<logic:present property="variedade.id" name="variedadeForm">
							<logic:equal name="variedadeForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.variedade.alterar)">
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

						<logic:equal name="variedadeForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.variedade.filtrar)">
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
		$("#cultura").prop("maxlength", 100);
		
		/* Setando NOTNULL nos campos*/
		$("#nome").addClass("obrigatorio");
		$("#cultura").addClass("obrigatorio");

		/* Setando os placeholder dos campos*/
		$( "#nome" ).prop("placeholder","Variedade");
		$( "#cultura" ).prop("placeholder","Cultura");

		/* EVENTOS */

		// Desliga o auto-complete da pagina
		$("#form_variedade").prop("autocomplete", "off");

		$('#form_variedade').on('submit', function(e) {
			abrirModalProcessando();
		});

		$('#inserir').click(function() {
			executar('form_variedade', 'inserir');
		});

		$('#alterar').click(function() {
			executar('form_variedade', 'alterar');
		});

		$('#limpar').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_variedade', 'limpar');
		});

		$('#listagem').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_variedade', 'abrirListagem');
		});
			
		$('#nome').keyup(function() {
			if ($('#nome').val() == null || $('#nome').val() == '') {
				//$('#variedadeSelecionado').val(null);
			}
		});
		
		$('#nome').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'variedade.nome',
			/* params : {
				'saidaGrao.variedade.id' : function() {
					return $('#variedadeSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/variedade.src?method=selecionarVariedadeAutoComplete',
			onSelect : function(suggestion) {
				//$('#variedadeSelecionado').val(suggestion.data);
				$('#nome').val(suggestion.nome);
				
				montarNomeCompletoVariedade();
			},
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			}, */
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					//$('#variedadeSelecionado').val(null);
				}
			}
		});
		
		$('#cultura').keyup(function() {
			if ($('#cultura').val() == null || $('#cultura').val() == '') {
				$('#culturaSelecionada').val(null);
			}
		});
		$('#cultura').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'variedade.cultura.nome',
			/* params : {
				'saidaGrao.variedade.id' : function() {
					return $('#variedadeSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/variedade.src?method=selecionarCulturaAutoComplete',
			onSelect : function(suggestion) {
				$('#culturaSelecionada').val(suggestion.data);
				
				montarNomeCompletoVariedade();
			},
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.data;
				return valorExibir;
			}, */
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#culturaSelecionada').val(null);
				}
			}
		});
						
		$('#modalCadastrarCultura').on('click', function() {
			var actionURL = '${contextPath}/restrito/sistema/cultura.src?method=abrirModal';

			$.ajax({
				type : 'POST',
				url : actionURL,				
				success : function(data, textStatus, XMLHttpRequest) {		
					BootstrapDialog.show({
						size : BootstrapDialog.SIZE_NORMAL,
						title : 'Cadastrar Cultura',
						message : $('<div id="id-modalCultura"></div>').append(data),
						type : BootstrapDialog.TYPE_SUCCESS, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
						closable : false, // <-- Default value is false
						draggable : true, // <-- Default value is false
						onshown : function(dialogRef) {
							/* Foco inicial */					
							$("#nomeCulturaModal").focus();
						},
						buttons : [ {
							hotkey : 13,
							label : 'Inserir',
							cssClass : 'btn-success',
							action : function(dialogRef) {		
								var theForm = $('form[name=culturaForm]');
								var params = theForm.serialize();
								var actionURL = theForm.attr('action') + '?method=inserirModal';

								$.ajax({
									type : 'POST',
									url : actionURL,
									data : params,
									success : function(data, textStatus, XMLHttpRequest) {
										$('#id-modalCultura').html(data);
																		
										$("#nomeCulturaModal").focus();
									},
									error : function(XMLHttpRequest, textStatus, errorThrown) {
										alert(textStatus);
									}
								});
							}
						}, {
							label : 'Fechar',
							action : function(dialogRef) {
								
								var actionURL = '${contextPath}/restrito/sistema/cultura.src?method=fecharModal';

								$.ajax({
									type : 'POST',
									url : actionURL,							
									success : function(data, textStatus, XMLHttpRequest) {
										dialogRef.close();

										$("#cultura").focus();
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
				
		
		$('.montarNomeCompletoVariedade').keyup(function() {
			montarNomeCompletoVariedade();
		});
		function montarNomeCompletoVariedade() {
			
			var nome = $('#nome').val();
			var cultura = $('#cultura').val();
						
			$('#nomeCompleto').val( nome + ' (' + cultura + ')');
		}
	});
</script>