<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>



<!-- INICIO - MODAIS DE ERRO -->
<logic:messagesPresent message="false">
	<div class="row">
		<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<html:messages id="message" message="false">

				<div class="alert alert-danger">
					<strong>
						<bean:write filter='false' name='message' />
					</strong>
				</div>

			</html:messages>
		</div>
	</div>
</logic:messagesPresent>

<div class="panel panel-success" style="border-color: #909090;">
	<div class="panel-heading" style="font-size: 30px; padding: 0px 15px">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-5">
				<label>Itens</label>
			</div>

			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-7 text-right">
				<label>Total NF R$&nbsp;${cupomForm.cupom.valorCustoTotal}</label>
			</div>
		</div>

	</div>
	<div class="panel-body">

		<div class="row">
			<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">

				<div class="table-responsive" style="max-height: 523px; min-height: 523px;">
					<table class="table table-bordered table-striped table-hover" style="font-size: 11px;">
						<thead>
							<tr class="cor-sistema" style="background-color: #438eb9; color: white; font-size: 14px;">
								<th>Produto</th>
								<!-- <th style="width: 150px; min-width: 150px;">Marca</th> -->
								<th class="text-center" style="width: 110px; min-width: 110px;">R$ Custo</th>
								<th class="text-center" style="width: 120px; min-width: 120px;">Qtd</th>

								<th class="text-center" style="width: 100px; min-width: 100px;">Uni.Med</th>
								<logic:present property="cupom.id" name="cupomForm">
									<logic:equal name="cupomForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.cupom.alterar)">
										<th class="text-center" style="width: 80px; min-width: 80px;">Remover</th>
									</logic:equal>
								</logic:present>
								<logic:notPresent property="cupom.id" name="cupomForm">
									<th class="text-center" style="width: 80px; min-width: 80px;">Remover</th>
								</logic:notPresent>
							</tr>
						</thead>
						<tbody>
							<logic:iterate indexId="i" id="itemCorrente" name="cupomForm" property="cupom.itens" type="br.com.srcsoftware.sistema.cupom.item.ItemCupomDTO">
								<tr">
									<td>${itemCorrente.produto.nomeCompleto}</td>
									<%-- <td>${itemCorrente.produto.marca.nome}</td> --%>

									<td class="text-right has-success">
										<html:text style="min-width: 135px;" styleClass="form-control input-sm text-right precoCusto" styleId="precoCusto${i}" property="cupom.itens[${i}].precoCusto" name="cupomForm" />
									</td>
									
									<td>
										<html:text style="min-width: 135px;" styleClass="form-control text-right input-sm  quantidade" styleId="quantidade${i}" property="cupom.itens[${i}].quantidade" name="cupomForm" />
									</td>																

									<td class="text-center">${itemCorrente.produto.unidadeMedida.sigla}</td>

									<logic:present property="cupom.id" name="cupomForm">
										<logic:equal name="cupomForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.cupom.alterar)">
											<td class="text-center">
												<a onclick="removerItem('${itemCorrente.idTemp}');" class="btn btn-danger btn-xs fa fa-trash" style="text-decoration: none; font-size: 18px"></a>
											</td>
										</logic:equal>
									</logic:present>
									<logic:notPresent property="cupom.id" name="cupomForm">
										<td class="text-center">
											<a onclick="removerItem('${itemCorrente.idTemp}');" class="btn btn-danger btn-xs fa fa-trash" style="text-decoration: none; font-size: 18px"></a>
										</td>
									</logic:notPresent>
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

				var theForm = $('form[name=cupomForm]');
				var params = theForm.serialize();
				var actionURL = theForm.prop('action') + '?method=atualizarValorNF';

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

				var theForm = $('form[name=cupomForm]');
				var params = theForm.serialize();
				var actionURL = theForm.prop('action') + '?method=atualizarValorNF';

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

			var theForm = $('form[name=cupomForm]');
			var actionURL = theForm.prop('action') + '?method=removerItem&idItemRemover=' + idItem;
			$.ajax({
				type : 'POST',
				url : actionURL,
				success : function(data, textStatus, XMLHttpRequest) {

					$('#id-itens').html(data);
					$("#produto").focus();
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(textStatus);
				}
			});
		}
	</script>