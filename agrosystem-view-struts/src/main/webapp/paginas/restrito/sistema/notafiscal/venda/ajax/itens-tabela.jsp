<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<div class="row">
	<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">

		<div class="table-responsive" style="max-height: 170px; min-height: 170px;">
			<table class="table table-bordered table-striped" style="font-size: 11px;">
				<thead>
					<tr class="cor-sistema" style="background-color: #b94343 !important; color: white; font-size: 14px;">
						<th>Produto</th>
						<th>Marca</th>
						<th class="text-center">Restante</th> 
						<th>Uni.Med</th>
						<th>R$ Custo</th>
						<th class="text-center">Receber</th>						
					</tr>
				</thead>
				<tbody>
					<logic:iterate indexId="i" id="itemPedidoCorrente" name="notaFiscalVendaForm" property="itensPedidosRestantes" type="br.com.srcsoftware.sistema.pedido.itempedido.ItemPedidoDTO">
						<tr>
							<td>${itemPedidoCorrente.produto.nome}</td>
							<td>${itemPedidoCorrente.produto.marca.nome}</td>

							<logic:notPresent property="notaFiscalVenda.pedido.id" name="notaFiscalVendaForm">
								<td>
									<html:text style="min-width: 135px;" styleClass="form-control text-center input-sm  quantidade" styleId="quantidade${i}" property="itensPedidosRestantes[${i}].quantidade"
										name="notaFiscalVendaForm" />
								</td>
							</logic:notPresent>
							<logic:present property="notaFiscalVenda.pedido.id" name="notaFiscalVendaForm">
								<td class="text-right">${itemPedidoCorrente.quantidadeRestante}</td>
							</logic:present>

							<td>${itemPedidoCorrente.produto.unidadeMedida.nome}</td>
							
							<td class="text-right">${itemPedidoCorrente.precoCusto}</td>

							<td class="text-center">
								<a onclick="abrirModalAdicionarItens('${itemPedidoCorrente.id}','${itemPedidoCorrente.produto.nome.replaceAll('\"', '&quot;').replaceAll('\'', '&quot;')}','${itemPedidoCorrente.produto.marca.nome}','${itemPedidoCorrente.quantidadeRestante}');" class="btn btn-success btn-xs fa fa-angle-double-right" style="text-decoration: none; font-size: 18px"></a>
							</td>
							
						</tr>
					</logic:iterate>
				</tbody>

			</table>
		</div>


	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {

		
	});
	
	/* function adicionar(idItemPedido){
		window.location = "${contextPath}/restrito/sistema/notaFiscalVenda.src?method=adicionarItemRecebido&idItemReceber="+idItemPedido+"&notaFiscalVenda.numero=" +$('#numero').val();
	} */

	function removerItem(idItem) {

		var theForm = $('form[name=notaFiscalVendaForm]');
		var actionURL = theForm.attr('action') + '?method=removerItem&idItemRemover=' + idItem;
		$.ajax({
			type : 'POST',
			url : actionURL,
			success : function(data, textStatus, XMLHttpRequest) {

				$('#id-itens').html(data);
				$("#nomeProduto").focus();
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(textStatus);
			}
		});
	}
</script>