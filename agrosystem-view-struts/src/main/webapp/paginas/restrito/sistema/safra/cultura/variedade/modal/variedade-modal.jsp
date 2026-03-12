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

	<!-- Define o tamanho geral da variedade -->

	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<html:form styleId="form_variedade" action="restrito/sistema/variedade" method="post">
			<html:hidden property="method" value="empty" />

			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="row">
							
								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<html:text styleClass="form-control input-lg bloqueado text-center" styleId="nomeCompletoModal" property="variedade.nomeCompleto" name="variedadeForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<label>Nome</label>
									<html:text styleClass="form-control input-sm montarNomeCompletoVariedadeModal" styleId="nomeModal" property="variedade.nome" name="variedadeForm" />									
								</div>								
								
								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<label>Cultura</label>
									<a href="#" id="modalCadastrarCulturaModal">
										<i style="text-shadow: 1px 0px 0px white, -1px 0px 0px white, 0px 1px 0px white, 0px -1px 0px white; color: green; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-plus"></i>
									</a>									
									<html:text styleClass="form-control input-sm montarNomeCompletoVariedade" styleId="culturaModal" property="variedade.cultura.nome" name="variedadeForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="culturaSelecionadaModal" property="variedade.cultura.id" name="variedadeForm" />
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

		/* Setando NOTNULL nos campos*/
		$("#nomeModal").addClass("obrigatorio");
		
		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#nomeModal").prop("maxlength", 100);
		
		/* Setando os placeholder dos campos*/
		$("#nomeModal").prop("placeholder", "Nome");
		
		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_variedade").prop("autocomplete", "off");
		//$("#form_variedade").prop("onSubmit", "return false");

		$('#form_variedade').on('submit', function(e) {
			return false;
		});

		$('#nomeModal').autocomplete({
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
				$('#nomeModal').val(suggestion.nome);
				
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
		
		$('#culturaModal').keyup(function() {
			if ($('#culturaModal').val() == null || $('#cultura').val() == '') {
				$('#culturaSelecionadaModal').val(null);
			}
		});
		$('#culturaModal').autocomplete({
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
				$('#culturaSelecionadaModal').val(suggestion.data);
				
				montarNomeCompletoVariedade();
			},
			formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.data;
				return valorExibir;
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#culturaSelecionadaModal').val(null);
				}
			}
		});
						
		$('#modalCadastrarCulturaModal').on('click', function() {
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

										$("#culturaModal").focus();
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
				
		
		$('.montarNomeCompletoVariedadeModal').keyup(function() {
			montarNomeCompletoVariedade();
		});
		function montarNomeCompletoVariedade() {
			
			var nome = $('#nomeModal').val();
			var cultura = $('#culturaModal').val();
						
			$('#nomeCompletoModal').val( nome + ' (' + cultura + ')');
		}
		carregarMascaras();
	});
</script>