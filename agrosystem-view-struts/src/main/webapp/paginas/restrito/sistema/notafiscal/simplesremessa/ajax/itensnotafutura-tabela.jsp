<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<div class="row">
	<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">

		<div class="table-responsive" style="max-height: 236px; min-height: 236px;">
			<table class="table table-bordered table-striped" style="font-size: 11px;">
				<thead>
					<tr class="cor-sistema" style="background-color: #b94343 !important; color: white; font-size: 14px;">
						<th>Produto</th>
						<th>Marca</th>
						<th class="text-center" style="width: 90px; min-width: 90px;">Restante</th> 
						<th style="width: 100px; min-width: 100px;">Uni.Med</th>
						<th style="width: 100px; min-width: 100px;">R$ Custo</th>
						<th class="text-center" style="width: 80px; min-width: 80px;">Receber</th>						
					</tr>
				</thead> 
				<tbody>
					<logic:iterate indexId="i" id="itemNotaFiscalVendaFuturaCorrente" name="notaFiscalSimplesRemessaForm" property="itensNotasFiscaisVendasFuturasRestantes" type="br.com.srcsoftware.sistema.pedido.notafiscal.venda.ItemNotaFiscalVendaDTO">
						<tr>
							<td>${itemNotaFiscalVendaFuturaCorrente.itemPedido.produto.nome}</td>
							<td>${itemNotaFiscalVendaFuturaCorrente.itemPedido.produto.marca.nome}</td>							
							<td class="text-center">${itemNotaFiscalVendaFuturaCorrente.quantidadeRestante}</td>
							<td>${itemNotaFiscalVendaFuturaCorrente.itemPedido.produto.unidadeMedida.nome}</td>							
							<td>${itemNotaFiscalVendaFuturaCorrente.precoCusto}</td>

							<td class="text-center">
								<a onclick="abrirModalAdicionarItens('${itemNotaFiscalVendaFuturaCorrente.id}','${itemNotaFiscalVendaFuturaCorrente.itemPedido.produto.nome.replaceAll('\"', '&quot;').replaceAll('\'', '&quot;')}','${itemNotaFiscalVendaFuturaCorrente.itemPedido.produto.marca.nome}','${itemNotaFiscalVendaFuturaCorrente.quantidadeRestante}');" class="btn btn-success btn-xs fa fa-angle-double-right" style="text-decoration: none; font-size: 18px"></a>
							</td>
							
						</tr>
					</logic:iterate>
				</tbody>

			</table>
		</div>


	</div>
</div>