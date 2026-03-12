<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<jsp:include page="../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<div class="row">
	<!-- Define o tamanho geral da notaFiscalVenda -->
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel sombra">

			<!-- INICIO FORMULARIO -->
			<div class="panel-body">
				<logic:present property="notaFiscalVenda.id" name="notaFiscalVendaForm">
					<div class="row">
						<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px;">
							<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px; margin-bottom: 5px;">
								<i class="fa fa-user-plus"></i>
								&nbsp;${notaFiscalVendaForm.notaFiscalVenda.nomeUsuarioCriacao }
							</div>
						</div>
						<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
							<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px; margin-bottom: 5px;">
								<i class="fa fa-calendar-plus-o"></i>
								&nbsp;${notaFiscalVendaForm.notaFiscalVenda.dataOcorrenciaCriacao}
							</div>
						</div>
						<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
							<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px; margin-bottom: 5px;">
								<i class="fa fa-refresh"></i>
								&nbsp;${notaFiscalVendaForm.notaFiscalVenda.nomeUsuarioAlteracao}
							</div>
						</div>
						<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-left: 0px;">
							<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px; margin-bottom: 5px;">
								<i class="fa fa-calendar-o"></i>
								&nbsp;${notaFiscalVendaForm.notaFiscalVenda.dataOcorrenciaAlteracao}
							</div>
						</div>
					</div>
				</logic:present>
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_notaFiscalVenda" action="restrito/sistema/notaFiscalVenda" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6" style="padding-right: 3px;">
									<div class="panel" style="border-color: #909090;">

										<logic:equal name="notaFiscalVendaForm" value="Venda" property="tipoNF">
											<div class="panel-heading cor-sistema" style="font-size: 30px; padding: 6px 0px 0px 15px; color: white; background-color: rgb(1, 86, 133) !important;">
												<div class="row">
													<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
														<a data-toggle="collapse" style="color: #ffbf5d; float: right; line-height: 1; margin-right: 12px; margin-top: 5px;" id="modalInfo">
															<i class="fa fa-question"></i>
														</a>
														<i class="fa fa-file-o"></i>
														<label id="label_venda">Nota Fiscal de Venda</label>
													</div>
												</div>
											</div>
										</logic:equal>
										<logic:equal name="notaFiscalVendaForm" value="Futura" property="tipoNF">
											<div class="panel-heading cor-sistema" style="font-size: 30px; padding: 6px 0px 0px 15px; color: white; background-color: rgb(1, 128, 133) !important;">
												<div class="row">
													<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
														<a data-toggle="collapse" style="color: #ffbf5d; float: right; line-height: 1; margin-right: 12px; margin-top: 5px;" id="modalInfoFutura">
															<i class="fa fa-question"></i>
														</a>
														<i class="fa fa-external-link"></i>
														<label id="label_venda">Nota Fiscal de Venda Futura</label>
													</div>
												</div>
											</div>
										</logic:equal>

										<div class="panel-body">

											<div class="row">
												<div class="form-group col-xs-12 col-sm-6 col-md-6 col-lg-4">
													<html:text styleClass="form-control text-center input-lg" style="font-size: 25px;" styleId="numero" property="notaFiscalVenda.numero" name="notaFiscalVendaForm" />
												</div>
												<div class="form-group text-right col-xs-12 col-sm-12 col-md-3 col-lg-4">
													<html:text styleClass="form-control text-center data input-lg" style="font-size: 20px; padding-right: 40px;" styleId="data" property="notaFiscalVenda.data" name="notaFiscalVendaForm" />
													<i style="margin-top: -33px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-calendar"></i>
												</div>

											</div>

											<div class="row">
												<div class="form-group col-xs-12 col-sm-5 col-md-5 col-lg-4">
													<label>Nº Pedido - Dt.&nbsp;${notaFiscalVendaForm.notaFiscalVenda.pedido.dataToString}</label>
													<html:text styleClass="form-control text-center input-lg " style="font-size: 25px;" styleId="numeroPedido" property="notaFiscalVenda.pedido.numero" name="notaFiscalVendaForm" />
													<i style="margin-top: -33px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
													<html:hidden styleId="pedidoSelecionado" property="notaFiscalVenda.pedido.id" name="notaFiscalVendaForm" />
												</div>
												<div class="form-group col-xs-12 col-sm-7 col-md-7 col-lg-8">
													<label>Nome Fornecedor</label>
													<html:text styleClass="form-control bloqueado input-lg " style="font-size: 25px;" styleId="fornecedor" property="notaFiscalVenda.pedido.fornecedor.nome" name="notaFiscalVendaForm" />
												</div>
											</div>

											<div class="row">
												<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
													<div class="panel panel-danger" style="border-color: #909090; margin-top: 15px; margin-bottom: 0px;">
														<div class="panel-heading" style="font-size: 30px; padding: 0px 15px">
															<label>Itens Pedidos Restantes</label>
														</div>
														<div class="panel-body" id="id-itens">
															<jsp:include page="ajax/itens-tabela.jsp"></jsp:include>
														</div>
													</div>
												</div>
											</div>

											<div class="row">
												<!-- BOTOES -->
												<logic:notPresent property="notaFiscalVenda.id" name="notaFiscalVendaForm">
													<logic:equal name="notaFiscalVendaForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.notaFiscalVenda.inserir)">
														<div class="form-group col-xs-6 col-sm-4 col-md-3 col-lg-3">
															<button type="submit" id="inserir" class="btn btn-success btn-sm cor-sistema btn-block">
																<i class="fa fa-save"></i>
																Inserir
															</button>
														</div>
													</logic:equal>
												</logic:notPresent>
												<logic:present property="notaFiscalVenda.id" name="notaFiscalVendaForm">
													<logic:equal name="notaFiscalVendaForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.notaFiscalVenda.alterar)">
														<div class="form-group col-xs-6 col-sm-4 col-md-3 col-lg-3">
															<button type="submit" id="alterar" class="btn btn-success btn-sm cor-sistema btn-block">
																<i class="fa fa-edit"></i>
																Alterar
															</button>
														</div>
													</logic:equal>
												</logic:present>

												<div class="form-group col-xs-6 col-sm-4 col-md-3 col-lg-3">
													<button type="button" id="limpar" class="btn btn-success btn-sm cor-sistema btn-block">
														<i class="glyphicon glyphicon-erase"></i>
														Limpar
													</button>
												</div>

												<logic:equal name="notaFiscalVendaForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.notaFiscalVenda.listagem)">
													<div class="form-group col-xs-6 col-sm-4 col-md-3 col-lg-3">
														<button type="button" id="listagem" class="btn btn-success btn-sm cor-sistema btn-block">
															<i class="fa fa-search"></i>
															Listagem
														</button>
													</div>
												</logic:equal>
											</div>
										</div>
									</div>
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6" style="padding-left: 3px;" id="id-itens-entregues">
									<jsp:include page="ajax/itens-entregues-tabela.jsp"></jsp:include>
								</div>
							</div>

						</html:form>
					</div>
				</div>
			</div>
			<!-- TERMINO FORMULARIO -->
			<!-- /.panel-body -->

		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>


<script type="text/javascript">
	$(document).ready(function() {

		validarData();

		/* Foco inicial */
		$("#numero").focus();

		/* Setando NOTNULL nos campos*/
		$("#numeroPedido").addClass("obrigatorio");
		$("#numero").addClass("obrigatorio");
		$("#data").addClass("obrigatorio");

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#numeroPedido").attr("maxlength", 20);
		$("#numero").attr("maxlength", 20);

		/* Setando os placeholder dos campos*/
		$("#numeroPedido").attr("placeholder", "Nº Pedido");
		$("#numero").attr("placeholder", "Nº NF Venda");
		
		$('#modalInfo').click(
				function() {
					BootstrapDialog.show({
						size : BootstrapDialog.SIZE_LARGE,
						title : 'Informativo',
						message : '<div class="row">' + '<div class="col-xs-12">'
								+ '<p style="margin-bottom: 0px;font-weight: bold;">Os itens lançados nas Notas Fiscais de Vendas serão contabilidazados no ESTOQUE.</p>' + '</div>'
								+ '</div>',
						closable : true,
						type : BootstrapDialog.TYPE_SUCCESS,
						buttons : [ {
							label : 'Obrigado',
							action : function(dialogRef) {

								dialogRef.close();
							}
						} ]
					});

				});
		
		$('#modalInfoFutura').click(
				function() {
					BootstrapDialog.show({
						size : BootstrapDialog.SIZE_LARGE,
						title : 'Informativo',
						message : '<div class="row"><div class="col-xs-12">'
								+ '<p style="margin-bottom: 0px;font-weight: bold;">Os itens lançados nas Notas Fiscais de Vendas Futuras NÃO SERÃO contabilidazados no ESTOQUE.</p>'
								+ '<p style="margin-bottom: 0px;font-weight: bold;">A contabilização se dará a partir do lançamento da Nota Fiscal de Simples Remessa.</p>'
								+  '</div></div>',
						closable : true,
						type : BootstrapDialog.TYPE_SUCCESS,
						buttons : [ {
							label : 'Obrigado',
							action : function(dialogRef) {

								dialogRef.close();
							}
						} ]
					});

				});

		$('#numeroPedido').keyup(function() {
			if ($('#numeroPedido').val() == null || $('#numeroPedido').val() == '') {
				$('#pedidoSelecionado').val(null);
				$('#fornecedor').val(null);
			}
		});

		$('#numeroPedido').autocomplete({
			minChars : 1,
			noCache : true,
			paramName : 'notaFiscalVenda.pedido.numero',
			serviceUrl : '${contextPath}/restrito/sistema/notaFiscalVenda.src?method=selecionarPedidoAutoComplete',
			onSelect : function(suggestion) {
				$('#pedidoSelecionado').val(suggestion.data);

				executarComSubmit('form_notaFiscalVenda', 'selecionarPedidoById');
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#pedidoSelecionado').val(null);
					$('#fornecedor').val(null);
				}
			}
		});

		/* $('#numeroPedido').keypress(function() {
			if ($('#numeroPedido').val() == null || $('#numeroPedido').val() == '') {
				executarComSubmit('form_notaFiscalVenda', 'limparDadosPedido');
			}
		}); */

		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_notaFiscalVenda").attr("autocomplete", "off");

		$('#form_notaFiscalVenda').on('submit', function(e) {
			abrirModalProcessando();
		});

		$('#inserir').click(function() {
			executar('form_notaFiscalVenda', 'inserir');
		});

		$('#alterar').click(function() {
			executar('form_notaFiscalVenda', 'alterar');
		});

		$('#limpar').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_notaFiscalVenda', 'limpar');
		});

		$('#listagem').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_notaFiscalVenda', 'abrirListagem');
		});

		$('#data').keyup(function() {
			validarData();
		});

		function validarData() {
			var dataNotaFutura = $("#data").val();
			var dataPedido = '${notaFiscalVendaForm.notaFiscalVenda.pedido.dataToString}';
			if (dataPedido > dataNotaFutura) {
				$("#data").css('background-color', '#ff9f9f');
			} else {
				$("#data").css('background-color', '#ffffff');
			}
		}
	});

	function abrirModalAdicionarItens(idItemPedidoReceber, nomeProdutoAdicionar, marcaProdutoAdicionar, quantidadeItemPedido) {
		BootstrapDialog
				.show({
					size : BootstrapDialog.SIZE_LARGE,
					title : "Quantidade de Itens a serem Recebidos",
					message : '<div class="row">' + '<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12 class="text-center" style="font-size: 30px;">' + '<label>Produto:&nbsp;</label>'
							+ nomeProdutoAdicionar + '&nbsp;' + marcaProdutoAdicionar + '</div>' + '<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12 class="text-center">'
							+ '<input type="text" style="font-size: 82px; font-weight: bold; height: 90px;" class="form-control text-center input-lg numeros" id="quantidadeAdicionar"/>' + '</div>'
							+ '</div>',
					closable : true,
					type : BootstrapDialog.TYPE_PRIMARY,
					onshown : function(dialogRef) {
						$('#quantidadeAdicionar').focus();
						$('.botaoModal').css("font-size", "40px");
						$("#quantidadeAdicionar").attr("maxlength", 20);
						$("#quantidadeAdicionar").val(quantidadeItemPedido);

					},
					buttons : [ {
						label : 'Confirmar',
						hotkey : 13, // Keycode of keyup event of key ENTER is 13.
						cssClass : 'btn-primary',
						action : function(dialogRef) {
							window.location = '${contextPath}/restrito/sistema/notaFiscalVenda.src?method=adicionarItemRecebido&idItemPedidoReceber=' + idItemPedidoReceber
									+ '&notaFiscalVenda.numero=' + $('#numero').val() + '&quantidadeItensAdicionar=' + $("#quantidadeAdicionar").val() + '&notaFiscalVenda.data=' + $("#data").val()
									+ '&notaFiscalVenda.tipo=' + $("#tipo").val();
						}
					} ]
				});
	}

	function abrirModalRetornarItens(idItemPedidoRetornar, nomeProdutoRetornar, marcaProdutoRetornar) {
		alert(idItemPedidoRetornar);
		BootstrapDialog.show({
			size : BootstrapDialog.SIZE_LARGE,
			title : "Quantidade de Itens a serem devolvidos",
			message : '<div class="row">' + '<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12 class="text-center" style="font-size: 30px;">' + '<label>Produto:&nbsp;</label>'
					+ nomeProdutoRetornar + '&nbsp;' + marcaProdutoRetornar + '</div>' + '<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12 class="text-center">'
					+ '<input type="text" style="font-size: 82px; font-weight: bold; height: 90px;" class="form-control text-center input-lg numeros" id="quantidadeRetornar"/>' + '</div>' + '</div>',
			closable : true,
			type : BootstrapDialog.TYPE_DANGER,
			onshown : function(dialogRef) {
				$('#quantidadeRetornar').focus();
				$('.botaoModal').css("font-size", "40px");
				$("#quantidadeRetornar").attr("maxlength", 20);
			},
			buttons : [ {
				label : 'Confirmar',
				hotkey : 13, // Keycode of keyup event of key ENTER is 13.
				cssClass : 'btn-primary',
				action : function(dialogRef) {
					window.location = '${contextPath}/restrito/sistema/notaFiscalVenda.src?method=removerItemRecebido&idItemPedidoRetornar=' + idItemPedidoRetornar + '&quantidadeItensRetornar='
							+ $("#quantidadeRetornar").val();
				}
			} ]
		});
	}
</script>