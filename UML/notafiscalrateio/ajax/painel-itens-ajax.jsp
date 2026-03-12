<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<div class="panel panel-success" style="border-color: #909090;">
	<div class="panel-heading" style="font-size: 20px; padding: 0px 15px">
		<div class="row">
			<div class="col-xs-12 col-sm-2 col-md-2 col-lg-2">
				<label>Itens</label>
			</div>

			<div class="col-xs-12 col-sm-10 col-md-10 col-lg-10 text-right">
				<label>Total NF R$&nbsp;${notaFiscalRateioForm.notaFiscalRateio.valorTotal}</label>
			</div>
		</div>

	</div>
	<div class="panel-body">

		<div class="row">
			<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">

				<div class="table-responsive">
					<table class="table table-bordered table-striped table-hover" style="font-size: 11px;">
						<thead>
							<tr class="cor-sistema" style="background-color: #438eb9; color: white; font-size: 14px;">
								<th style="width: 135px; min-width: 135px;">Centro de Custo</th>
								<th>Descrição</th>
								<th>R$ Valor</th>								
								<th>Safra</th>
								<th>Setor</th>

								<logic:equal name="notaFiscalRateioForm" value="true" property="acessoPermitido(${usuario_logado.id}.notaFiscalRateio.alterar)">
									<th class="text-center" style="width: 80px; min-width: 80px;">Remover</th>
								</logic:equal>
							</tr>
						</thead>
						<tbody>
							<logic:iterate indexId="i" id="itemCorrente" name="notaFiscalRateioForm" property="notaFiscalRateio.itens" type="br.com.srcsoftware.sistema.notafiscal.rateio.itemnotafiscalrateio.ItemNotaFiscalRateioDTO">
								<tr>
									<td>
										<html:select styleClass="form-control input-sm" styleId="centroCustoReceita${i}" property="notaFiscalRateio.itens[${i}].centroCustoReceita.id" name="notaFiscalRateioForm">
											<html:optionsCollection name="notaFiscalRateioForm" property="comboCentroCusto" label="label" value="value" />
										</html:select>								
									</td>
									
									<td>${itemCorrente.descricao}</td>	
									<td>${itemCorrente.valor}</td>

									<logic:equal value="Safra/Setor" name="itemCorrente" property="tipo">
										<td>${itemCorrente.safra.nomeCompleto}</td>
										<td>${itemCorrente.setor.nomeCompleto}</td>
									</logic:equal>
									<logic:equal value="Tudo" name="itemCorrente" property="tipo">
										<td class="text-center" style="background-color: #e6e6e6;" colspan="2">
											<strong>${itemCorrente.tipo} -</strong>
											${itemCorrente.safra.nomeCompleto}
										</td>
									</logic:equal>
									<logic:equal value="Cultura" name="itemCorrente" property="tipo">
										<td class="text-center" style="background-color: #e6e6e6;" colspan="2">
											<strong>${itemCorrente.tipo}:</strong>
											${itemCorrente.cultura} - ${itemCorrente.safra.nomeCompleto}
										</td>
									</logic:equal>

									<logic:equal name="notaFiscalRateioForm" value="true" property="acessoPermitido(${usuario_logado.id}.notaFiscalRateio.alterar)">
										<td class="text-center">
											<a onclick="removerItem('${itemCorrente.idTemp}');" class="btn btn-danger btn-xs fa fa-trash btnRemover" style="text-decoration: none; font-size: 18px"></a>
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
		carregarMascaras();		

		var valor = '${notaFiscalRateioForm.notaFiscalRateio.valorTotal}';		
		if (valor == null || valor == '') {
			valor = '0.0';
		}	
		valor = parseFloat(valor.replace('.', '').replace(',', '.').replace('R$', ''), 10);
		$('#valorTotalNotaFiscalRateio').text(formatarDinheiro((valor).toFixed(2)));
		
	});

	function removerItem(idTempItem) {
		if( '${notaFiscalRateioForm.notaFiscalRateio.id}' != null && '${notaFiscalRateioForm.notaFiscalRateio.id}' != ''  ){
			BootstrapDialog.show({					
				size : BootstrapDialog.SIZE_LARGE,
				title : 'Atenção...',
				message : "Alterando o Valor Total da NF, TODAS as parcelas já existentes serão substituidas por novas com valores corrigidos!",
				closable : true,
				type : BootstrapDialog.TYPE_DANGER,
				buttons : [ {
					label : 'Confirmar',
					action : function(dialogRef) {	
						
						var theForm = $('form[name=notaFiscalRateioForm]');
						var actionURL = theForm.attr('action') + '?method=removerItem&idItemRemover=' + idTempItem;
						$.ajax({
							type : 'POST',
							url : actionURL,
							success : function(data, textStatus, XMLHttpRequest) {

								$('#id-itens').html(data);
								$("#descricaoCentroCustoReceita").focus();
							},
							error : function(XMLHttpRequest, textStatus, errorThrown) {
								alert(textStatus);
							}
						});
						
						dialogRef.close();
					}
				},{
					label : 'Cancelar Remoção',
					action : function(dialogRef) {
						
						dialogRef.close();
					}
				} ]
			});
							
		}		
	}	
</script>