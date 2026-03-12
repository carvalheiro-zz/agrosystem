<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<div class="panel panel-success" style="border-color: #909090;">
	<div class="panel-heading" style="font-size: 30px; padding: 0px 15px">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-5">
				<label>Itens Entregues</label>
			</div>
		
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-7 text-right">
				<label>Total NF R$&nbsp;${notaFiscalVendaForm.notaFiscalVenda.valorCustoTotal}</label>
			</div>
		</div>

	</div>
	<div class="panel-body">

		<div class="row">
			<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">

				<div class="table-responsive" style="max-height: 403px; min-height: 403px;">
					<table class="table table-bordered table-striped table-hover" style="font-size: 11px;">
						<thead>
							<tr class="cor-sistema" style="background-color: #438eb9; color: white; font-size: 14px;">
								<th>Produto</th>
								<th>Marca</th>
								<th class="text-center">Qtd</th>
								<logic:equal name="notaFiscalVendaForm" value="Futura" property="tipoNF">
									<th class="text-center" style="width: 120px; min-width: 120px;">Restante</th>
								</logic:equal>
								<th>Uni.Med</th>
								<th style="width: 100px; min-width: 100px;">R$ Custo</th>
								<th>R$ Total</th>
								<th class="text-center">Retornar</th>
							</tr>
						</thead>
						<tbody>
							<logic:iterate indexId="i" id="itemNotaFiscalVendaCorrente" name="notaFiscalVendaForm" property="notaFiscalVenda.itens"
								type="br.com.srcsoftware.sistema.pedido.notafiscal.venda.ItemNotaFiscalVendaDTO">
								<tr style="background-color:${itemNotaFiscalVendaCorrente.corInformativaEntrega}">
									<td>${itemNotaFiscalVendaCorrente.itemPedido.produto.nome}</td>
									<td>${itemNotaFiscalVendaCorrente.itemPedido.produto.marca.nome}</td>
									<td class="text-right" style="font-weight: bold !important;">${itemNotaFiscalVendaCorrente.quantidade}</td>
									<logic:equal name="notaFiscalVendaForm" value="Futura" property="tipoNF">
										<td class="text-center" style="background-color: #ffffc4;">${itemNotaFiscalVendaCorrente.quantidadeRestante}</td>
									</logic:equal>
									<td>${itemNotaFiscalVendaCorrente.itemPedido.produto.unidadeMedida.nome}</td>

									<td>
										<html:text style="min-width: 135px;" styleClass="form-control input-sm text-right precoCusto" styleId="precoCusto${i}"
											property="notaFiscalVenda.itens[${i}].precoCusto" name="notaFiscalVendaForm" />
									</td>
									
									<td class="text-right">${itemNotaFiscalVendaCorrente.totalCusto}</td>

									<td class="text-center">
										<a
											onclick="abrirModalRetornarItens('${itemNotaFiscalVendaCorrente.itemPedido.id}','${itemNotaFiscalVendaCorrente.itemPedido.produto.nome.replaceAll('\"', '&quot;').replaceAll('\'', '&quot;')}','${itemNotaFiscalVendaCorrente.itemPedido.produto.marca.nome}');"
											class="btn btn-danger btn-xs fa fa-angle-double-left" style="text-decoration: none; font-size: 18px"></a>
									</td>

									
								</tr>
								<tr>									
									<td class="text-right has-success" colspan="7">
										<html:text style="font-size: 16px;" styleClass="form-control input-sm" styleId="observacao${i}"
											property="notaFiscalVenda.itens[${i}].observacao" name="notaFiscalVendaForm" />
											
											<script type="text/javascript">
												$("#observacao${i}").attr("placeholder", "Observação do Item");
											</script>
									</td>
								</tr>
							</logic:iterate>
						</tbody>

					</table>
				</div>


			</div>
		</div>
	</div>
	<div class="panel-footer"></div>
</div>



<script type="text/javascript">
	$(document).ready(function() {
		$('.precoCusto').blur(function() {

			var theForm = $('form[name=notaFiscalVendaForm]');
			var params = theForm.serialize();
			var actionURL = theForm.attr('action') + '?method=atualizarPrecosCusto';

			$.ajax({
				type : 'POST',
				url : actionURL,
				data : params,
				success : function(data, textStatus, XMLHttpRequest) {
					$('#id-itens-entregues').html(data);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(textStatus);
				}
			});

		});
		carregarMascaras();	
	});
</script>