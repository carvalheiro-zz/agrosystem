<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!-- CAMPOS DE CADASTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -30px; margin-bottom: -15px;">
		<h1 class="page-header cabecalho_pagina">
			<i class="fa fa-graduation-cap" style="font-size: 26px;"></i>
			${queryViewForm.query.tituloTela}
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Pesquisa
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<div class="row">

	<!-- Define o tamanho geral da queryView -->
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<%-- <jsp:include page="${queryViewForm.query.arquivoJSP}"></jsp:include> --%>
		<jsp:include page="query-view.jsp"></jsp:include> 
	</div>
</div>


<script type="text/javascript">
	$(document).ready(function() {
		/* Foco inicial */
		$("#0").focus();
		
		/* EVENTOS */

		// Desliga o auto-complete da pagina
		$("#form_queryView").prop("autocomplete", "off");

		$('#form_queryView').on('submit', function(e) {
			abrirModalProcessando();
		});

		$( '#pesquisar' ).click(function() {  	
			executar('form_queryView','filtrar');
		});
		
		$( '#limparPesquisa' ).click(function() {
			abrirModalProcessando();
			executarComSubmit('form_queryView','limparFiltro');
		});
		
		$( '#exportarXLS' ).click(function() {	
			BootstrapDialog.show({
				size : BootstrapDialog.SIZE_LARGE,
				title : 'Salvar como...',
				message : 	'<div class="row">' 								
								+ '<div class="form-group col-lg-offset-3 col-md-offset-3 col-xs-12 col-sm-12 col-md-6 col-lg-6">' 
									+ '<label>Nome do arquivo</label>'
									+ '<input type="text" class="form-control input-sm"id="nomeArquivoModal"/>'
									+ '<span id="helpBlock1" class="help-block">Não informe nenhuma extensão (.xls)</span>' 
								+ '</div>' 
							+ '</div>',
				closable : true,
				type : BootstrapDialog.TYPE_SUCCESS,
				onshown : function(dialogRef) {
					$('#nomeArquivoModal').focus();
					$("#nomeArquivoModal").attr("maxlength", 50);
					$("#nomeArquivoModal").attr('aria-describedby', 'helpBlock1');
					
					$('#nomeArquivoModal').val('${queryViewForm.sugestaoNomeArquivo}');
				},
				buttons : [ {
					label : 'Gerar',
					action : function(dialogRef) {	
						$('#nomeArquivo').val($('#nomeArquivoModal').val())
						dialogRef.close();
						
						gerarRelatorio('form_queryView','exportarXLS');
								
						
						/* var theForm = $('form[name=queryViewForm]');
						var params = theForm.serialize();
						var actionURL = theForm.attr('action') + '?method=exportarXLS&nomeArquivo='+$('#nomeArquivo').val();

						$.ajax({
							type : 'POST',
							url : actionURL,
							data : params,
							success : function(data, textStatus, XMLHttpRequest) {
								dialogRef.close();
							},
							error : function(XMLHttpRequest, textStatus, errorThrown) {
								alert(textStatus);
							}
						});	 */
						
					}
				}, {
					label : 'Cancelar',
					action : function(dialogRef) {

						dialogRef.close();
					}
				} ]
			});
			
			
			
			
		});
		
		
			
	});
</script>