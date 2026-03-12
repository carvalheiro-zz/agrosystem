<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>


<div class="panel-heading cor-sistema text-white" style="font-size: 30px; padding: 0px 15px">
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<i class="fa fa-list fa-fw"></i>
			Cupons
		</div>
	</div>
</div>

<div class="panel-body">

	<div class="row">
		<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">

			<div class="table-responsive" style="max-height: 460px; min-height: 460px;">
				<table class="table table-bordered table-striped table-hover" style="font-size: 11px;">
					<thead>
						<tr class="cor-sistema" style="background-color: #438eb9; color: white; font-size: 14px;">
							<th class="text-center" style="width: 90px; min-width: 90px;">Número</th>
							<th class="text-center" style="width: 90px; min-width: 90px;">Data</th>
							<th>Fornecedor</th>
							<th class="text-right" style="width: 120px; min-width: 120px;">Valor</th>
							<th class="text-center" style="width: 140px; min-width: 140px;">Adicionar</th>
						</tr>
					</thead>
					<tbody>
						<logic:iterate indexId="i" id="cupomCorrente" name="notaFiscalCupomForm" property="notaFiscalCupom.cupons" type="br.com.srcsoftware.sistema.cupom.CupomDTO">
							<tr>
								<td style="vertical-align: middle; font-size: 12px; font-weight: bold;" class="text-center">${cupomCorrente.numero}</td>
								<td style="vertical-align: middle; font-size: 12px; font-weight: bold;" class="text-center">${cupomCorrente.dataToString}</td>
								<td style="vertical-align: middle; font-size: 12px; font-weight: bold;">${cupomCorrente.fornecedor.nome}</td>
								<td style="vertical-align: middle; font-size: 12px; font-weight: bold;" class="text-right">${cupomCorrente.valorCustoTotal}</td>

								<td class="text-center">
									<input type="checkbox" id="notaEmitidaCheck${i}" checked data-toggle="toggle" data-on="<i class='fa fa-check-square-o' style='font-size: 20px;'></i>"
										data-off="<i class='fa fa-square-o' style='font-size: 20px;'></i>" data-onstyle="primary" data-offstyle="danger" data-width="100%" data-size="small">
									<html:hidden styleId="notaEmitida${i}" property="notaFiscalCupom.cupons[${i}].notaEmitida" name="notaFiscalCupomForm" />
								</td>

							</tr>

							<script type="text/javascript">
								$('#notaEmitidaCheck${i}').change(function() {
									$('#notaEmitida${i}').val($(this).prop('checked'));
									
									adicionarAjax();
								});

								function preencherCkecks() {
									if ($('#notaEmitida${i}').val() == 'false') {
										$('#notaEmitidaCheck${i}').bootstrapToggle('off')
									} else {
										$('#notaEmitidaCheck${i}').bootstrapToggle('on')
									}
								}
								preencherCkecks();

								function adicionarAjax() {					
									var theForm = $('form[name=notaFiscalCupomForm]');
									var params = theForm.serialize();
									var actionURL = '${contextPath}/restrito/sistema/notaFiscalCupom.src?method=atualizarValorTotal';

									$.ajax({
										type : 'POST',
										url : actionURL,
										data : params,
										success : function(data, textStatus, XMLHttpRequest) {

											$('#id_totalizador').html(data);											
										},
										error : function(XMLHttpRequest, textStatus, errorThrown) {
											alert(textStatus);
										}
									});
								}
							</script>
						</logic:iterate>
					</tbody>

				</table>
			</div>


		</div>
	</div>
</div>
