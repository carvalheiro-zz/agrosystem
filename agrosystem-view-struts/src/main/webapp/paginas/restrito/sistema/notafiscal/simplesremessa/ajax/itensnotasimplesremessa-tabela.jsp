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
				<label>Total NF R$&nbsp;${notaFiscalSimplesRemessaForm.notaFiscalSimplesRemessa.valorCustoTotal}</label>
			</div>
		</div>

	</div>
	<div class="panel-body"> 

		<div class="row">
			<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">

				<div class="table-responsive" style="max-height: 502px; min-height: 502px;">
					<table class="table table-bordered table-striped table-hover" style="font-size: 11px;">
						<thead>
							<tr class="cor-sistema" style="background-color: #438eb9; color: white; font-size: 14px;">
								<th>Produto</th>
								<th>Marca</th>
								<th class="text-center" style="width: 90px; min-width: 90px;">Qtd</th>
								<th style="width: 100px; min-width: 100px;">Uni.Med</th>
								<th style="width: 100px; min-width: 100px;">R$ Custo</th>
								<th style="width: 100px; min-width: 100px;">R$ Total</th>
								<th class="text-center" style="width: 80px; min-width: 80px;">Retornar</th>
							</tr>
						</thead>
						<tbody>
							<logic:iterate indexId="i" id="itemNotaFiscalSimplesRemessaCorrente" name="notaFiscalSimplesRemessaForm" property="notaFiscalSimplesRemessa.itens"
								type="br.com.srcsoftware.sistema.pedido.notafiscal.simplesremessa.ItemNotaFiscalSimplesRemessaDTO">
								<tr>
									<td>${itemNotaFiscalSimplesRemessaCorrente.itemNotaFiscalVendaFutura.itemPedido.produto.nome}</td>
									<td>${itemNotaFiscalSimplesRemessaCorrente.itemNotaFiscalVendaFutura.itemPedido.produto.marca.nome}</td>
									<td class="text-center">${itemNotaFiscalSimplesRemessaCorrente.quantidade}</td>
									<td>${itemNotaFiscalSimplesRemessaCorrente.itemNotaFiscalVendaFutura.itemPedido.produto.unidadeMedida.nome}</td>

									<td>
										<html:text style="min-width: 135px;" styleClass="form-control input-sm text-right precoCusto" styleId="precoCusto${i}"
											property="notaFiscalSimplesRemessa.itens[${i}].precoCusto" name="notaFiscalSimplesRemessaForm" />
									</td>
									
									<td class="text-center">${itemNotaFiscalSimplesRemessaCorrente.totalCusto}</td>

									<td class="text-center">
										<a
											onclick="abrirModalRetornarItens('${itemNotaFiscalSimplesRemessaCorrente.itemNotaFiscalVendaFutura.id}','${itemNotaFiscalSimplesRemessaCorrente.itemNotaFiscalVendaFutura.itemPedido.produto.nome}','${itemNotaFiscalSimplesRemessaCorrente.itemNotaFiscalVendaFutura.itemPedido.produto.marca.nome}');"
											class="btn btn-danger btn-xs fa fa-angle-double-left" style="text-decoration: none; font-size: 18px"></a>
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

			var theForm = $('form[name=notaFiscalSimplesRemessaForm]');
			var params = theForm.serialize();
			var actionURL = theForm.attr('action') + '?method=atualizarPrecosCusto';

			$.ajax({
				type : 'POST',
				url : actionURL,
				data : params,
				success : function(data, textStatus, XMLHttpRequest) {
					$('#id-itens-simples-remessa').html(data);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(textStatus);
				}
			});

		});
		carregarMascaras();
	});
</script>