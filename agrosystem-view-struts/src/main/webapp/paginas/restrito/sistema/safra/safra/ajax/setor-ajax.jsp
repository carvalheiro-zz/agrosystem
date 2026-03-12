<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>


<div class="panel panel-primary" style="min-height: 275px;">
	<!-- INICIO FORMULARIO -->
	<div class="panel-heading cor-sistema">
		<strong>Setores</strong>
		&nbsp;&nbsp; (&nbsp;${safraForm.safra.quantidadeSetores }&nbsp;)
	</div>
	<div class="panel-body">
		<div class="row">

			<!-- INICIO - PROCEDIMENTO -->
			<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="row">

					<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
						<label>Setor</label>
						<a href="#" id="modalCadastrarSetor">
							<i style="text-shadow: 1px 0px 0px white, -1px 0px 0px white, 0px 1px 0px white, 0px -1px 0px white; color: green; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-plus"></i>
						</a>
						<html:text styleClass="form-control input-sm obrigatorioItem" styleId="setor" property="setorSafra.setor.nomeCompleto" name="safraForm" />
						<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
						<html:hidden styleId="setorSelecionado" property="setorSafra.setor.id" name="safraForm" />
					</div>


					<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
						<label>Área</label>
						<html:text styleClass="form-control input-sm obrigatorioItem text-center" styleId="area" property="setorSafra.area" name="safraForm" />
					</div>

					<div class="form-group col-xs-12 col-sm-12 col-md-5 col-lg-5">
						<label>Variedade</label>
						<a href="#" id="modalCadastrarVariedade">
							<i style="text-shadow: 1px 0px 0px white, -1px 0px 0px white, 0px 1px 0px white, 0px -1px 0px white; color: green; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-plus"></i>
						</a>
						<html:text styleClass="form-control input-sm obrigatorioItem" styleId="variedade" property="setorSafra.variedade.nomeCompleto" name="safraForm" />
						<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
						<html:hidden styleId="variedadeSelecionada" property="setorSafra.variedade.id" name="safraForm" />
					</div>
					
					<%-- <div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
						<label>Cultura</label>
						<a href="#" id="modalCadastrarCultura">
							<i style="text-shadow: 1px 0px 0px white, -1px 0px 0px white, 0px 1px 0px white, 0px -1px 0px white; color: green; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-plus"></i>
						</a>
						<html:text styleClass="form-control input-sm obrigatorioItem" styleId="cultura" property="setorSafra.cultura.nome" name="safraForm" />
						<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
						<html:hidden styleId="culturaSelecionada" property="setorSafra.cultura.id" name="safraForm" />
					</div> --%>

					<%-- <div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
						<label>Variedade</label>
						<html:text styleClass="form-control input-sm bloqueado" styleId="variedade" property="setorSafra.cultura.variedade" name="safraForm" />
					</div> --%>

					<div class="form-group col-xs-12 col-sm-6 col-md-2 col-lg-2">
						<button type="button" id="adicionarSetor" class="btn btn-primary btn-sm btn-block" style="margin-top: 25px">
							<i class="glyphicon glyphicon glyphicon-plus "></i>
							Setor
						</button>
					</div>

					<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<!-- TABELA DE DADOS PROCEDIMENTOS -->
						<div class="table-responsive" style="max-height: 220px;">
							<table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
								<thead>
									<!-- CABEÇALHO DA TABELA -->
									<tr class="cor-sistema" style="color: white;">
										<th style="max-width: 120px; width: 120px; min-width: 120px;" class="text-center">Setores</th>
										<th style="max-width: 120px; width: 120px; min-width: 120px;" class="text-center">Sub-Setor</th>
										<th>Variedade</th>
										<th style="max-width: 80px; width: 80px; min-width: 80px;" class="text-right">Área</th>
										<th style="max-width: 80px; width: 80px; min-width: 80px;">Remover</th>
									</tr>
								</thead>
								<tbody>
									<!-- TABELA -->
									<logic:iterate id="setorSafraCorrente" name="safraForm" property="safra.setoresSafras" type="br.com.srcsoftware.sistema.safra.setorsafra.SetorSafraDTO">

										<tr style="cursor: default;">
											<!-- success / info / warning / danger -->
											<td class="text-center">${setorSafraCorrente.setor.nome}</td>
											<td class="text-center">${setorSafraCorrente.setor.subSetor}</td>
											<td>${setorSafraCorrente.variedade.nomeCompleto}</td>
											<td class="text-right">${setorSafraCorrente.area}</td>
											<td class="text-center">
												<a href="${contextPath}/restrito/sistema/safra.src?method=removerSetores&setorSafra.idTemp=${setorSafraCorrente.idTemp}" class="btn btn-danger btn-xs fa fa-trash" style="font-size: 18px"></a>
											</td>
										</tr>
									</logic:iterate>
								</tbody>
							</table>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {

		/* CONFIGURACAO */
		carregarMascaras();

		$("#setor").attr("placeholder", "Setor");
		$("#subSetor").attr("placeholder", "Sub-Setor");
		$("#area").attr("placeholder", "Área");
		$("#variedade").attr("placeholder", "Variedade");

		$('#setor').keyup(function() {
			if ($('#setor').val() == '') {
				$('#setorSelecionado').val(null);
				//$('#subSetor').val(null);
			}
		});

		$('#setor').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'setorSafra.setor.nomeCompleto',
			serviceUrl : '${contextPath}/restrito/sistema/safra.src?method=selecionarSetorAutoComplete',
			onSelect : function(suggestion) {
				$('#setorSelecionado').val(suggestion.data);
				//$('#subSetor').val(suggestion.subSetor);
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#setorSelecionado').val(null);
					//$('#subSetor').val(null);
				}
			}
		});

		$('#variedade').keyup(function() {
			if ($('#variedade').val() == '') {
				$('#variedadeSelecionada').val(null);
			}
		});		

		$('#variedade').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'setorSafra.variedade.nomeCompleto',
			/* params : {
				'saidaGrao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/safra.src?method=selecionarVariedadeAutoComplete',
			onSelect : function(suggestion) {
				$('#variedadeSelecionada').val(suggestion.data);

				//$("#localArmazenagem").focus();
			},
			/* formatResult : function(suggestion, currentValue) {
				
				

			    return formatResultAutoCompleteHightLigth((suggestion.value + " - " + suggestion.variedade),currentValue);
				
				
				
				//var valorExibir = suggestion.value + " - " + suggestion.variedade;			

				//return valorExibir.replace(currentValue.toUpperCase(), '<b>'+currentValue.toUpperCase()+'</b>').replace(currentValue, '<b>'+currentValue+'</b>');
			}, */
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#variedadeSelecionada').val(null);
					$('#variedade').val(null);
				}
			}
		});		

		//######## botao adicionar setor ##############	
		$('#adicionarSetor').click(function() {
			adicionarAjax();
		});
		
		
		
		
		$('#modalCadastrarSetor').on('click', function() {
			var actionURL = '${contextPath}/restrito/sistema/setor.src?method=abrirModal';

			$.ajax({
				type : 'POST',
				url : actionURL,				
				success : function(data, textStatus, XMLHttpRequest) {		

					BootstrapDialog.show({
						size : BootstrapDialog.SIZE_NORMAL,
						title : 'Cadastrar Setor',
						message : $('<div id="id-modalSetor"></div>').append(data),
						type : BootstrapDialog.TYPE_SUCCESS, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
						closable : false, // <-- Default value is false
						draggable : true, // <-- Default value is false
						onshown : function(dialogRef) {
							/* Foco inicial */					
							$("#nomeSetorModal").focus();
						},
						buttons : [ {
							hotkey : 13,
							label : 'Inserir',
							cssClass : 'btn-success',
							action : function(dialogRef) {		
								var theForm = $('form[name=setorForm]');
								var params = theForm.serialize();
								var actionURL = theForm.attr('action') + '?method=inserirModal';

								$.ajax({
									type : 'POST',
									url : actionURL,
									data : params,
									success : function(data, textStatus, XMLHttpRequest) {
										$('#id-modalSetor').html(data);
																		
										$("#nomeSetorModal").focus();
									},
									error : function(XMLHttpRequest, textStatus, errorThrown) {
										alert(textStatus);
									}
								});
							}
						}, {
							label : 'Fechar',
							action : function(dialogRef) {
								
								var actionURL = '${contextPath}/restrito/sistema/setor.src?method=fecharModal';

								$.ajax({
									type : 'POST',
									url : actionURL,							
									success : function(data, textStatus, XMLHttpRequest) {
										dialogRef.close();

										$("#setor").focus();
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
		
		$('#modalCadastrarVariedade').on('click', function() {
			var actionURL = '${contextPath}/restrito/sistema/variedade.src?method=abrirModal';

			$.ajax({
				type : 'POST',
				url : actionURL,				
				success : function(data, textStatus, XMLHttpRequest) {		

					BootstrapDialog.show({
						size : BootstrapDialog.SIZE_NORMAL,
						title : 'Cadastrar Variedade',
						message : $('<div id="id-modalVariedade"></div>').append(data),
						type : BootstrapDialog.TYPE_SUCCESS, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
						closable : false, // <-- Default value is false
						draggable : true, // <-- Default value is false
						onshown : function(dialogRef) {
							/* Foco inicial */					
							$("#nomeVariedadeModal").focus();
						},
						buttons : [ {
							hotkey : 13,
							label : 'Inserir',
							cssClass : 'btn-success',
							action : function(dialogRef) {		
								var theForm = $('form[name=variedadeForm]');
								var params = theForm.serialize();
								var actionURL = theForm.attr('action') + '?method=inserirModal';

								$.ajax({
									type : 'POST',
									url : actionURL,
									data : params,
									success : function(data, textStatus, XMLHttpRequest) {
										$('#id-modalVariedade').html(data);
																		
										$("#nomeVariedadeModal").focus();
									},
									error : function(XMLHttpRequest, textStatus, errorThrown) {
										alert(textStatus);
									}
								});
							}
						}, {
							label : 'Fechar',
							action : function(dialogRef) {
								
								var actionURL = '${contextPath}/restrito/sistema/variedade.src?method=fecharModal';

								$.ajax({
									type : 'POST',
									url : actionURL,							
									success : function(data, textStatus, XMLHttpRequest) {
										dialogRef.close();

										$("#variedade").focus();
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

	function adicionarAjax() {
		/* var theForm = $('form[name=safraForm]');
		var params = theForm.serialize();
		var actionURL = theForm.attr('action') + '?method=adicionarSetores';
		$.ajax({
			type : 'POST',
			url : actionURL,
			data : params,
			success : function(data, textStatus, XMLHttpRequest) {
				$('#id_setor_ajax').html(data);				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(textStatus);
			}
		}); */
		executarComModalObrigatorios('form_safra','adicionarSetores','obrigatorioItem')
		//executarComSubmit('form_safra', 'adicionarSetores');
	}

	function removerAjax(idSetor) {
		var actionURL = '${contextPath}/restrito/sistema/safra.src?method=removerSetores&setorSafra.setor.id=' + idSetor;
		$.ajax({
			type : "POST",
			url : actionURL,
			scriptCharset : "ISO-8859-1",
			success : function(data, textStatus, XMLHttpRequest) {
				$('#id_setor_ajax').html(data);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {

			}
		});
	}
</script>
