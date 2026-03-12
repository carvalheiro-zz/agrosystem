<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<div class="panel" style="border-color: #909090;">
	<div class="panel-heading cor-sistema" style="color: white;">
		<strong>Taxas</strong>
	</div>

	<div class="panel-body">

		<div class="row">

			<logic:notEmpty name="erroAjax" scope="session">
				<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-11">
					<div class="alert alert-danger">
						<strong>Atenção!</strong>
						${erroAjax}
					</div>
				</div>
			</logic:notEmpty>

			<div class="form-group col-xs-11 col-sm-11 col-md-6 col-lg-6">
				<label>Taxa</label>
				<a href="#" id="modalCadastrarTaxa">
					<i style="text-shadow: 1px 0px 0px white, -1px 0px 0px white, 0px 1px 0px white, 0px -1px 0px white; color: green; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-plus"></i>
				</a>
				<html:select styleClass="form-control input-sm" styleId="taxa" property="taxaLancadaAdicionar.taxa.id" name="despesaForm">
					<html:option value="">Selecione...</html:option>
					<html:optionsCollection name="despesaForm" property="comboTaxas(${usuarioSessaoPOJO.isADM})" label="label" value="value" />
				</html:select>
				
			</div>

			<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
				<label>% Taxa</label>
				<html:text styleClass="form-control input-sm decimal itemAdicionar" styleId="percentualTaxa" property="taxaLancadaAdicionar.percentualTaxa" name="despesaForm" />
			</div>

			<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
				<label>R$ Taxa</label>
				<html:text styleClass="form-control input-sm dinheiro itemAdicionar" styleId="valorTaxa" property="taxaLancadaAdicionar.valorTaxa" name="despesaForm" />
			</div>

			<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-32">
				<button type="button" id="adicionarTaxa" class="btn btn-success btn-sm cor-sistema">
					<i class="fa fa-plus"></i>
					Adicionar Taxa
				</button>

			</div>

			<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="table-responsive" style="overflow-y: scroll; max-height: 115px;">
					<table class="table table-bordered table-striped" style="font-size: 12px;">
						<thead>
							<!-- CABEÇALHO DA TABELA -->
							<tr class="cor-sistema" style="color: white;">
								<th>Taxa</th>
								<th style="width: 95px; min-width: 95px; max-width: 95px;">% Taxa</th>
								<th style="width: 90px; min-width: 90px; max-width: 90px;">R$ Taxa</th>

								<logic:equal name="despesaForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.despesa.alterar)">
									<th class="text-center" style="width: 80px; min-width: 80px;">Remover</th>
								</logic:equal>
							</tr>
						</thead>
						<tbody>
							<!-- TABELA -->
							<logic:iterate indexId="i" id="taxaLancadaCorrente" name="despesaForm" property="despesa.contaPagar.taxas" type="br.com.srcsoftware.modular.financeiro.taxa.TaxaLancadaDTO">

								<tr>
									<td>${taxaLancadaCorrente.taxa.nome}</td>
									<td class="text-center">${taxaLancadaCorrente.percentualTaxa}</td>
									<td class="text-center">${taxaLancadaCorrente.valorTaxa}</td>
									<logic:equal name="despesaForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.despesa.alterar)">
										<td class="text-center" style="min-width: 80px; vertical-align: middle;">
											<a onclick="removerTaxa('${taxaLancadaCorrente.taxa.id}');" class="btn btn-danger btn-xs fa fa-trash" style="text-decoration: none; font-size: 18px"></a>
										</td>
									</logic:equal>
								</tr>

							</logic:iterate>
						</tbody>
					</table>
				</div>
			</div>



		</div>



	</div>

</div>


<script type="text/javascript">
	$(document).ready(function() {
		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#taxa").attr("maxlength", 50);
		$("#percentualTaxa").attr("maxlength", 5);
		$("#valorTaxa").attr("maxlength", 10);

		/* Setando os placeholder dos campos*/
		$("#taxa").attr("placeholder", "Taxa");

		recarregarObrigatorios();		
		
		$('#taxa').on('change', function(e) {
			$("#percentualTaxa").focus();
		});

		$('#adicionarTaxa').click(function() {

			if ($('#taxa').val() == null || $('#taxa').val() == '') {
				modalCamposObrigatorios("Taxa");
			} else if (($('#percentualTaxa').val() == null || $('#percentualTaxa').val() == '') && ($('#valorTaxa').val() == null || $('#valorTaxa').val() == '')) {
				modalCamposObrigatorios("Valores Taxa");
			} else {

				var theForm = $('form[name=despesaForm]');
				var params = theForm.serialize();
				var actionURL = theForm.attr('action') + '?method=adicionarTaxa';

				$.ajax({
					type : 'POST',
					url : actionURL,
					data : params,
					success : function(data, textStatus, XMLHttpRequest) {

						$('#id-taxas').html(data);
						$('.itemAdicionar').val(null);
						$("#taxa").focus();
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert(textStatus);
					}
				});

			}
		});

		$('#modalCadastrarTaxa').on('click', function() {
			var actionURL = '${contextPath}/restrito/sistema/taxa.src?method=abrirModal';

			$.ajax({
				type : 'POST',
				url : actionURL,
				success : function(data, textStatus, XMLHttpRequest) {
					BootstrapDialog.show({
						size : BootstrapDialog.SIZE_NORMAL,
						title : 'Cadastrar Taxa',
						message : $('<div id="id-modalTaxa"></div>').append(data),
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
								var theForm = $('form[name=taxaForm]');
								var params = theForm.serialize();
								var actionURL = theForm.attr('action') + '?method=inserirModal';

								$.ajax({
									type : 'POST',
									url : actionURL,
									data : params,
									success : function(data, textStatus, XMLHttpRequest) {
										$('#id-modalTaxa').html(data);

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

								var actionURL = '${contextPath}/restrito/sistema/taxa.src?method=fecharModal';

								$.ajax({
									type : 'POST',
									url : actionURL,
									success : function(data, textStatus, XMLHttpRequest) {
										dialogRef.close();

										$("#taxa").focus();
										
										atualizarComboTaxas();
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
		
		function atualizarComboTaxas () {
			var theForm = $('form[name=despesaForm]');
			var params = theForm.serialize();
			var actionURL = theForm.attr('action') + '?method=carregarComboTaxas';

			$.ajax({
				type : 'POST',
				url : actionURL,
				data : params,
				success : function(data, textStatus, XMLHttpRequest) {

					var html = "";

					html = html + '<html:select styleClass="form-control input-sm " styleId="taxa" property="taxaLancadaAdicionar.taxa.id" name="despesaForm">';
					html = html + '<html:option value="">Selecione...</html:option>';

					if (data != null) {

						for (i = 0; i < data.dados.length; i++) {
							html = html + '<html:option value="'+data.dados[i].data+'">' + data.dados[i].value + '</html:option>'
						}

					}

					html = html + '</html:select>'

					$('#taxa').html(html);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(textStatus);
				}
			});
		}
	});

	function removerTaxa(idTaxa) {		
			var actionURL = '${contextPath}/restrito/sistema/despesa.src?method=removerTaxa&idTaxaRemover=' + idTaxa;

			$.ajax({
				type : 'POST',
				url : actionURL,
				success : function(data, textStatus, XMLHttpRequest) {

					$('#id-taxas').html(data);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(textStatus);
				}
			});
		

	}
</script>