<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<div class="panel panel-success" style="border-color: #909090;">
	<div class="panel-heading" style="font-size: 30px; padding: 0px 15px">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-5">
			<label>Itens Pedidos</label>
		</div>

		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-7 text-right">
			<label>Total R$&nbsp;${pedidoForm.pedido.valorCustoTotal}</label>
		</div>
	</div>
	<div class="panel-body" id="id-itens">
		<div class="row">
			<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">

				<div class="table-responsive" style="max-height: 460px; min-height: 460px;">
					<table class="table table-bordered table-striped table-hover" style="font-size: 11px;">
						<thead>
							<tr class="cor-sistema" style="background-color: #438eb9; color: white; font-size: 14px;">
								<th>Produto</th>
								<!-- <th style="width: 150px; min-width: 150px;">Marca</th> -->
								<th style="width: 110px; min-width: 110px;">R$ Custo</th>
								<th class="text-center" style="width: 120px; min-width: 120px;">Qtd</th>
								<th class="text-center" style="width: 120px; min-width: 120px;">Restante</th>
								<th style="width: 100px; min-width: 100px;">Uni.Med</th>

								<logic:equal name="pedidoForm" value="ABERTO" property="pedido.status">
									<logic:present property="pedido.id" name="pedidoForm">
										<logic:equal name="pedidoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.pedido.alterar)">
											<th class="text-center" style="width: 80px; min-width: 80px;">Remover</th>
										</logic:equal>
									</logic:present>
									<logic:notPresent property="pedido.id" name="pedidoForm">
										<th class="text-center" style="width: 80px; min-width: 80px;">Remover</th>
									</logic:notPresent>
								</logic:equal>
							</tr>
						</thead>
						<tbody>
							<logic:iterate indexId="i" id="itemCorrente" name="pedidoForm" property="pedido.itens" type="br.com.srcsoftware.sistema.pedido.itempedido.ItemPedidoDTO">
								<tr style="background-color:${itemCorrente.corInformativaEntrega}">
									<td style=" font-size: 11px; font-weight: bold;">${itemCorrente.produto.nomeCompleto}</td>
									<%-- <td style=" font-size: 11px; font-weight: bold;">${itemCorrente.produto.marca.nome}</td> --%>

									<logic:equal name="pedidoForm" value="ABERTO" property="pedido.status">
										<td>
											<html:text style="min-width: 135px;" styleClass="form-control input-sm text-right precoCusto" styleId="precoCusto${i}" property="pedido.itens[${i}].precoCusto" name="pedidoForm" />
										</td>
									</logic:equal>

									<logic:notEqual name="pedidoForm" value="ABERTO" property="pedido.status">
										<td class="text-center" style=" font-size: 11px; font-weight: bold;">${itemCorrente.precoCusto}</td>
									</logic:notEqual>

									<logic:notEqual value="0,00" property="quantidadeRestante" name="itemCorrente">
										<td>
											<html:text style="min-width: 135px;" styleClass="form-control text-right input-sm quantidade" styleId="quantidade${i}" property="pedido.itens[${i}].quantidade" name="pedidoForm" />
										</td>
									</logic:notEqual>
									<logic:equal value="0,00" property="quantidadeRestante" name="itemCorrente">
										<td class="text-center" style=" font-size: 11px; font-weight: bold;">${itemCorrente.quantidade}</td>
									</logic:equal>
									<%-- <logic:equal name="pedidoForm" value="ABERTO" property="pedido.status">
										<td>
											<html:text style="min-width: 135px;" styleClass="form-control text-right input-sm quantidade" styleId="quantidade${i}" property="pedido.itens[${i}].quantidade" name="pedidoForm" />
										</td>
									</logic:equal>
									<logic:notEqual name="pedidoForm" value="ABERTO" property="pedido.status">
										<td class="text-center" style=" font-size: 11px; font-weight: bold;">${itemCorrente.quantidade}</td>
									</logic:notEqual> --%>

									<td class="text-center" style=" font-size: 11px; font-weight: bold;">${itemCorrente.quantidadeRestante}</td>

									<td style=" font-size: 11px; font-weight: bold;">${itemCorrente.produto.unidadeMedida.nome}</td>

									<logic:equal name="pedidoForm" value="ABERTO" property="pedido.status">
										<logic:present property="pedido.id" name="pedidoForm">
											<logic:equal name="pedidoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.pedido.alterar)">
												<td class="text-center">
													<a onclick="removerItem('${itemCorrente.idTemp}');" class="btn btn-danger btn-xs fa fa-trash" style="text-decoration: none; font-size: 18px"></a>
												</td>
											</logic:equal>
										</logic:present>
										<logic:notPresent property="pedido.id" name="pedidoForm">
											<td class="text-center">
												<a onclick="removerItem('${itemCorrente.idTemp}');" class="btn btn-danger btn-xs fa fa-trash" style="text-decoration: none; font-size: 18px"></a>
											</td>
										</logic:notPresent>
									</logic:equal>
								</tr>
							</logic:iterate>
						</tbody>

					</table>
				</div>


			</div>
		</div>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {

			$('.precoCusto').blur(function() {

				var theForm = $('form[name=pedidoForm]');
				var params = theForm.serialize();
				var actionURL = theForm.attr('action') + '?method=atualizarPrecosCusto';

				$.ajax({
					type : 'POST',
					url : actionURL,
					data : params,
					success : function(data, textStatus, XMLHttpRequest) {
						$('#id-itens').html(data);
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert(textStatus);
					}
				});

			});

			$('.quantidade').blur(function() {

				var theForm = $('form[name=pedidoForm]');
				var params = theForm.serialize();
				var actionURL = theForm.attr('action') + '?method=atualizarQuantidade';

				$.ajax({
					type : 'POST',
					url : actionURL,
					data : params,
					success : function(data, textStatus, XMLHttpRequest) {

						$('#id-itens').html(data);
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert(textStatus);
					}
				});

			});
			carregarMascaras();
		});

		function removerItem(idItem) {

			var theForm = $('form[name=pedidoForm]');
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