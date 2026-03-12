<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<jsp:include page="../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<div class="row">
	<!-- Define o tamanho geral da receita -->
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel sombra">

			<!-- INICIO FORMULARIO -->
			<div class="panel-body">
				<logic:present property="receita.id" name="receitaForm">
					<div class="row">
						<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px;">
							<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
								<i class="fa fa-user-plus"></i>
								&nbsp;${receitaForm.receita.contaReceber.nomeUsuarioCriacao }
							</div>
						</div>
						<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
							<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
								<i class="fa fa-calendar-plus-o"></i>
								&nbsp;${receitaForm.receita.contaReceber.dataOcorrenciaCriacao}
							</div>
						</div>
						<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
							<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
								<i class="fa fa-refresh"></i>
								&nbsp;${receitaForm.receita.contaReceber.nomeUsuarioAlteracao}
							</div>
						</div>
						<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-left: 0px;">
							<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
								<i class="fa fa-calendar-o"></i>
								&nbsp;${receitaForm.receita.contaReceber.dataOcorrenciaAlteracao}
							</div>
						</div>
					</div>
				</logic:present>
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_receita" action="restrito/sistema/receita" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<div class="alert alert-success" style="margin-bottom: 0px; margin-bottom: 0px; padding-top: 5px; padding-bottom: 5px;" id="alert-situacaoReceita">
										<div class="row">
											<div class="col-xs-12 col-sm-10 col-md-12 col-lg-4">
												<div class="col-xs-12 col-sm-10 col-md-12 col-lg-7">
													<strong>Situação:</strong>
													<span id="situacaoReceita" style="font-size: 30px;"></span>
												</div>
												<div class="col-xs-12 col-sm-10 col-md-12 col-lg-5">
													<span style="font-size: 30px; color: red;">${receitaForm.receita.contaReceber.canceladoToString}</span>
												</div>
											</div>
											<div class="col-xs-12 col-sm-10 col-md-12 col-lg-4 text-center">
												<strong>Total R$ </strong>
												<span id="valorTotalReceita" style="font-size: 30px;"></span>
											</div>
											<div class="col-xs-12 col-sm-10 col-md-12 col-lg-4 text-right">
												<strong>Pago + Acréscimo R$ </strong>
												<span id="valorTotalMaisJurosMultaReceita"></span>
											</div>
										</div>
									</div>
								</div>
							</div>
							<ul class="nav nav-tabs" id="tabsReceita">
								<li class="active">
									<a data-toggle="tab" href="#dadosReceita">Dados da Receita</a>
								</li>
								<li>
									<a data-toggle="tab" href="#dadosFinanceiros">Dados Financeiros</a>
								</li>
								<logic:notEmpty name="receitaForm" property="receita.contaReceber.parcelas">
									<li>
										<a data-toggle="tab" href="#dadosParcelas">Parcelas</a>
									</li>
								</logic:notEmpty>
							</ul>
							<div class="tab-content">
								<div id="dadosReceita" class="tab-pane fade in active" style="margin-top: 20px;">

									<div class="row">
										<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12" id="abaReceita">
											<label>Descrição</label>
											<html:text styleClass="form-control input-sm " styleId="nome" property="receita.nome" name="receitaForm" />
										</div>
										<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<label>Observação</label>
											<h6 class="pull-right" id="count_observacao"></h6>
											<html:textarea styleClass="form-control input-sm" styleId="observacao" property="receita.observacao" name="receitaForm" rows="6" />
										</div>
										<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-4" id="abaReceita">
											<label>Valor</label>
											<html:text styleClass="form-control input-lg dinheiro" styleId="valor" property="receita.contaReceber.valor" name="receitaForm" />
										</div>
									</div>
									
									
									
									
									
									
									<div class="row" style="margin-top: 15px;">
										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<!-- BOTOES -->
											<logic:notPresent property="receita.id" name="receitaForm">
												<logic:equal name="receitaForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.receita.inserir)">
													<button type="submit" id="inserir" class="btn btn-success btn-sm cor-sistema">
														<i class="fa fa-save"></i>
														Inserir
													</button>
												</logic:equal>
											</logic:notPresent>
											<logic:present property="receita.id" name="receitaForm">
												<logic:empty name="receitaForm" property="receita.contaReceber.canceladoToString">
													<logic:equal name="receitaForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.receita.alterar)">
														<button type="submit" id="alterar" class="btn btn-success btn-sm cor-sistema">
															<i class="fa fa-edit"></i>
															Alterar
														</button>
													</logic:equal>
												</logic:empty>
											</logic:present>
											<button type="button" id="limpar" class="btn btn-success btn-sm cor-sistema">
												<i class="glyphicon glyphicon-erase"></i>
												Limpar
											</button>

											<logic:equal name="receitaForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.receita.filtrar)">
												<button type="button" id="filtrar" class="btn btn-success btn-sm cor-sistema">
													<i class="fa fa-search"></i>
													Listagem
												</button>
											</logic:equal>

											<%-- Descomentar cado queira permitir cancelamento -FUNCIONANDO- <logic:present property="receita.id" name="receitaForm">
												<logic:empty name="receitaForm" property="receita.contaReceber.canceladoToString">
													<logic:equal name="receitaForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.receita.alterar)">
														<button type="button" id="cancelar" class="btn btn-danger btn-sm">
															<i class="fa fa-warning"></i>
															Cancelar
														</button>
													</logic:equal>
												</logic:empty>
											</logic:present>--%>
											
										</div>
									</div>
									
									
									
									
									
									
									
									
									
									
									
									
									
									
									
								</div>
								<div id="dadosFinanceiros" class="tab-pane fade" style="margin-top: 20px;">
									<div class="row">

										<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
											<label>Emissão</label>
											<html:text styleClass="form-control input-sm data text-center " styleId="data" property="receita.contaReceber.data" name="receitaForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
											<label>Número</label>
											<html:text styleClass="form-control numero input-sm text-center" styleId="numero" property="receita.contaReceber.numero" name="receitaForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-2">
											<label>À vista / Parcelado</label>
											<html:select styleClass="form-control input-sm " styleId="tipoPagamento" property="receita.contaReceber.tipo" name="receitaForm">
												<html:option value="">Selecione...</html:option>
												<html:option value="À vista">À vista</html:option>
												<html:option value="Parcelado">Parcelado</html:option>
											</html:select>
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
											<label>Forma Pagamento</label>
											<logic:notPresent property="receita.id" name="receitaForm">
												<a href="#" id="modalCadastrarFormaPagamento">
													<i style="text-shadow: 1px 0px 0px white, -1px 0px 0px white, 0px 1px 0px white, 0px -1px 0px white; color: green; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-plus"></i>
												</a>
											</logic:notPresent>
											<html:select styleClass="form-control input-sm " styleId="formaPagamento" property="receita.contaReceber.formaPagamento.id" name="receitaForm">
												<html:option value="">Selecione...</html:option>
												<html:optionsCollection name="receitaForm" property="comboFormaPagamento(${usuarioSessaoPOJO.isADM})" label="label" value="value" />
											</html:select>
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2" id="divVencimentoPrimeiraParcela">
											<label id="lblVencimentoPrimeiraParcela">Venc.1ª Parcela</label>
											<html:text styleClass="form-control input-sm data text-center " styleId="vencimentoPrimeiraParcela" property="receita.contaReceber.vencimentoPrimeiraParcela" name="receitaForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2" id="divValorEntrada">
											<label>R$ Entrada</label>
											<html:text styleClass="form-control input-sm dinheiro " styleId="valorEntrada" property="receita.contaReceber.valorEntrada" name="receitaForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3" id="divFormaPagamentoEntrada">
											<label>Forma Pag. Entrada</label>
											<html:select styleClass="form-control input-sm " styleId="formaPagamentoEntrada" property="receita.contaReceber.formaPagamentoEntrada.id" name="receitaForm">
												<html:option value="">Selecione...</html:option>
												<html:optionsCollection name="receitaForm" property="comboFormaPagamentoAVista(${usuarioSessaoPOJO.isADM})" label="label" value="value" />
											</html:select>
										</div>
									</div>
								</div>

								<div id="dadosParcelas" class="tab-pane fade" style="margin-top: 20px;">
									<div class="row">
										<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12" id="id_parcelas_ajax">
											<jsp:include page="ajax/painel-parcelas-ajax.jsp"></jsp:include>
										</div>
									</div>
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
		$("#nome").focus();

		/* Setando NOTNULL nos campos*/
		$("#nome").addClass("obrigatorio");
		$("#valor").addClass("obrigatorio");
		$("#data").addClass("obrigatorio");
		$("#numero").addClass("obrigatorio");
		$("#tipoPagamento").addClass("obrigatorio");
		$("#vencimentoPrimeiraParcela").addClass("obrigatorio");

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#nome").attr("maxlength", 100);
		$("#observacao").attr("maxlength", 1000);
		$("#numero").attr("maxlength", 20);
		$("#valor").attr("maxlength", 15);

		/* Setando os placeholder dos campos*/
		$("#nome").attr("placeholder", "Descrição da receita");
		$("#numero").attr("placeholder", "Número");
		$("#observacao").attr("placeholder", "Observação sobre a receita");

		var count_observacao_max = 1000;
		$('#count_observacao').html(count_observacao_max + ' restantes');
		$('#observacao').keyup(function() {
			gerenciarContadorCaracteresObservacao();
		});
		function gerenciarContadorCaracteresObservacao() {
			var text_length = $('#observacao').val().length;
			var text_remaining = count_observacao_max - text_length;
			$('#count_observacao').html(text_remaining + ' restantes');
		}

		gerenciarContadorCaracteresObservacao();

		$('#tipoPagamento').change(function() {
			atualizarComboFormaPagamento();
		});

		var vencimentoPrimeiraParcelaOld = null;
		$("#vencimentoPrimeiraParcela").focusin(function() {
			vencimentoPrimeiraParcelaOld = $("#vencimentoPrimeiraParcela").val();
		});
		$("#vencimentoPrimeiraParcela").focusout(function() {

			if ($("#vencimentoPrimeiraParcela").val() == vencimentoPrimeiraParcelaOld) {
				return;
			}

			if ('${receitaForm.receita.id}' != null && '${receitaForm.receita.id}' != '') {
				BootstrapDialog.show({
					size : BootstrapDialog.SIZE_LARGE,
					title : 'Atenção...',
					message : "Atenção, alterando o Vencimento, as parcelas já existentes serão substituidas por novas com valores corrigidos!",
					closable : false,
					type : BootstrapDialog.TYPE_DANGER,
					buttons : [ {
						label : 'Confirmar',
						action : function(dialogRef) {

							gerenciarCamposTipoFinanceiro();

							dialogRef.close();
						}
					}, {
						label : 'Cancelar Vencimento informada',
						action : function(dialogRef) {

							$("#vencimentoPrimeiraParcela").val(vencimentoPrimeiraParcelaOld);

							gerenciarCamposTipoFinanceiro();

							dialogRef.close();
						}
					} ]
				});
			}
		});

		var valorEntradaOld = null;
		$("#valorEntrada").focusin(function() {
			valorEntradaOld = $("#valorEntrada").val();
		});
		$("#valorEntrada").focusout(function() {
			if ($("#valorEntrada").val() == valorEntradaOld) {
				return;
			}
			if ('${receitaForm.receita.id}' != null && '${receitaForm.receita.id}' != '') {
				BootstrapDialog.show({
					size : BootstrapDialog.SIZE_LARGE,
					title : 'Atenção...',
					message : "Alterando o Valor da Entrada, as parcelas já existentes serão substituidas por novas com valores corrigidos!",
					closable : false,
					type : BootstrapDialog.TYPE_DANGER,
					buttons : [ {
						label : 'Confirmar',
						action : function(dialogRef) {
							dialogRef.close();
						}
					}, {
						label : 'Cancelar valor informado',
						action : function(dialogRef) {

							$("#valorEntrada").val(valorEntradaOld);

							dialogRef.close();
						}
					} ]
				});
			}
		});

		var dataOld = null;
		$("#data").focusin(function() {
			dataOld = $("#data").val();
		});
		$("#data").focusout(function() {
			if ($("#data").val() == dataOld) {
				return;
			}
			if ('${receitaForm.receita.id}' != null && '${receitaForm.receita.id}' != '') {
				BootstrapDialog.show({
					size : BootstrapDialog.SIZE_LARGE,
					title : 'Atenção...',
					message : "Alterando a Data de Emissão, as parcelas já existentes serão substituidas por novas com datas corrigidas!",
					closable : false,
					type : BootstrapDialog.TYPE_DANGER,
					buttons : [ {
						label : 'Confirmar',
						action : function(dialogRef) {
							dialogRef.close();
						}
					}, {
						label : 'Cancelar data informada',
						action : function(dialogRef) {

							$("#data").val(dataOld);

							dialogRef.close();
						}
					} ]
				});
			}
		});

		var tipoPagamentoOld = null;
		$("#tipoPagamento").focusin(function() {
			tipoPagamentoOld = $("#tipoPagamento").val();
		});
		$("#tipoPagamento").focusout(function() {
			if ($("#tipoPagamento").val() == tipoPagamentoOld) {
				return;
			}
			if ('${receitaForm.receita.id}' != null && '${receitaForm.receita.id}' != '') {
				BootstrapDialog.show({
					size : BootstrapDialog.SIZE_LARGE,
					title : 'Atenção...',
					message : "Alterando o campo À Vista/Parcelado, fará com que as parcelas já existentes sejão substituidas por novas!",
					closable : false,
					type : BootstrapDialog.TYPE_DANGER,
					buttons : [ {
						label : 'Confirmar',
						action : function(dialogRef) {

							gerenciarCamposTipoFinanceiro();

							dialogRef.close();
						}
					}, {
						label : 'Cancelar alteração informada',
						action : function(dialogRef) {

							$("#tipoPagamento").val(tipoPagamentoOld);

							gerenciarCamposTipoFinanceiro();

							dialogRef.close();
						}
					} ]
				});

			}
		});

		var formaPagamentoOld = null;
		$("#formaPagamento").focusin(function() {
			formaPagamentoOld = $("#formaPagamento").val();
		});
		$("#formaPagamento").focusout(function() {
			if ($("#formaPagamento").val() == formaPagamentoOld) {
				return;
			}
			if ('${receitaForm.receita.id}' != null && '${receitaForm.receita.id}' != '') {
				BootstrapDialog.show({
					size : BootstrapDialog.SIZE_LARGE,
					title : 'Atenção...',
					message : "Alterando o campo Forma de Pagamento, fará com que as parcelas já existentes sejão substituidas por novas!",
					closable : false,
					type : BootstrapDialog.TYPE_DANGER,
					buttons : [ {
						label : 'Confirmar',
						action : function(dialogRef) {
							dialogRef.close();
						}
					}, {
						label : 'Cancelar alteração informada',
						action : function(dialogRef) {
							$("#formaPagamento").val(formaPagamentoOld);
							dialogRef.close();
						}
					} ]
				});

			}
		});

		$('#valorTotalReceita').text('${receitaForm.receita.contaReceber.valor}');
		var valorOld = null;
		$("#valor").focusin(function() {
			valorOld = $("#valor").val();
		});
		$("#valor").focusout(function() {
			if ($("#valor").val() == valorOld) {
				return;
			}
			if ('${receitaForm.receita.id}' != null && '${receitaForm.receita.id}' != '') {
				BootstrapDialog.show({
					size : BootstrapDialog.SIZE_LARGE,
					title : 'Atenção...',
					message : "Alterando o campo Valor da Receita, as parcelas já existentes serão substituidas por novas com valores corrigidos!",
					closable : false,
					type : BootstrapDialog.TYPE_DANGER,
					buttons : [ {
						label : 'Confirmar',
						action : function(dialogRef) {

							$('#valorTotalReceita').text($("#valor").val());

							dialogRef.close();
						}
					}, {
						label : 'Cancelar valor informado',
						action : function(dialogRef) {

							$("#valor").val(valorOld);

							$('#valorTotalReceita').text(valorOld);

							dialogRef.close();
						}
					} ]
				});
			} else {
				$('#valorTotalReceita').text($("#valor").val());
			}
		});

		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_receita").attr("autocomplete", "off");

		$('#form_receita').on('submit', function(e) {
			abrirModalProcessando();
		});

		$('#inserir').click(function() {
			inserirComAba('form_receita', 'inserir');
		});

		$('#alterar').click(function() {
			alterarComAba('form_receita', 'alterar');
		});

		$('#cancelar').click(function() {
			BootstrapDialog.show({
				size : BootstrapDialog.SIZE_LARGE,
				title : 'Informativo.',
				message : "Esse processo cancelará todas os titulos não vencidos e não pagos!",
				closable : false,
				type : BootstrapDialog.TYPE_DANGER,
				buttons : [ {
					label : 'Confirmar',
					action : function(dialogRef) {
						executarComSubmit('form_receita', 'cancelar');
						dialogRef.close();
					}
				}, {
					label : 'Fechar',
					action : function(dialogRef) {
						dialogRef.close();
					}
				} ]
			});
		});

		$('#limpar').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_receita', 'limpar');
		});

		$('#filtrar').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_receita', 'abrirListagem');
		});

		$('#valorEntrada').keyup(function() {
			if ($('#valorEntrada').val() != '') {
				$("#valorEntrada").addClass("obrigatorio");
				$("#formaPagamentoEntrada").addClass("obrigatorio");

			} else {
				$("#valorEntrada").removeClass("obrigatorio");
				$("#formaPagamentoEntrada").removeClass("obrigatorio");
			}
			recarregarObrigatorios();
		});
		$('#formaPagamentoEntrada').change(function() {
			if ($('#formaPagamentoEntrada').val() != '') {
				$("#valorEntrada").addClass("obrigatorio");
				$("#formaPagamentoEntrada").addClass("obrigatorio");

			} else {
				$("#valorEntrada").removeClass("obrigatorio");
				$("#formaPagamentoEntrada").removeClass("obrigatorio");
			}
			recarregarObrigatorios();
		});

		$('#modalCadastrarFormaPagamento').on('click', function() {
			var actionURL = '${contextPath}/restrito/sistema/formaPagamento.src?method=abrirModal';

			$.ajax({
				type : 'POST',
				url : actionURL,
				success : function(data, textStatus, XMLHttpRequest) {
					BootstrapDialog.show({
						size : BootstrapDialog.SIZE_WIDE,
						title : 'Cadastrar Tipo de Serviço',
						message : $('<div id="id-modalFormaPagamento"></div>').append(data),
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
								var theForm = $('form[name=formaPagamentoForm]');
								var params = theForm.serialize();
								var actionURL = theForm.attr('action') + '?method=inserirModal';

								$.ajax({
									type : 'POST',
									url : actionURL,
									data : params,
									success : function(data, textStatus, XMLHttpRequest) {
										$('#id-modalFormaPagamento').html(data);

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

								var actionURL = '${contextPath}/restrito/sistema/formaPagamento.src?method=fecharModal';

								$.ajax({
									type : 'POST',
									url : actionURL,
									success : function(data, textStatus, XMLHttpRequest) {
										dialogRef.close();

										atualizarComboFormaPagamento();

										//$("#formaPagamento").focus();

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

		function atualizarComboFormaPagamento() {
			var theForm = $('form[name=receitaForm]');
			var params = theForm.serialize();
			var actionURL = theForm.attr('action') + '?method=carregarComboFormaPagamento';

			$.ajax({
				type : 'POST',
				url : actionURL,
				data : params,
				success : function(data, textStatus, XMLHttpRequest) {

					var html = "";

					html = html + '<html:select styleClass="form-control input-sm " styleId="formaPagamento" property="receita.contaReceber.formaPagamento.id" name="receitaForm">';
					html = html + '<html:option value="">Selecione...</html:option>';

					if (data != null) {

						for (i = 0; i < data.dados.length; i++) {
							html = html + '<html:option value="'+data.dados[i].data+'">' + data.dados[i].value + '</html:option>'
						}

					}

					html = html + '</html:select>'

					$('#formaPagamento').html(html);

					gerenciarCamposTipoFinanceiro();
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(textStatus);
				}
			});
		}

		gerenciarCamposTipoFinanceiro();
	});

	function gerenciarCamposTipoFinanceiro() {
		if ($('#tipoPagamento').val() != null && $('#tipoPagamento').val() != '') {

			// Adicionando os campos
			$('#formaPagamento').removeClass("bloqueado");
			$('#vencimentoPrimeiraParcela').removeClass("bloqueado");

			if ($('#tipoPagamento').val() == 'À vista') {

				$("#lblVencimentoPrimeiraParcela").text('Data Quitação');

				// Removendo os campos				
				$('#valorEntrada').addClass("bloqueado");
				$('#valorEntrada').val(null);
				$('#formaPagamentoEntrada').addClass("bloqueado");
				$('#valoformaPagamentoEntradaEntrada').val(null);
				$("#valorEntrada").removeClass("obrigatorio");
				$("#formaPagamentoEntrada").removeClass("obrigatorio");
			} else if ($('#tipoPagamento').val() == 'Parcelado') {

				$("#lblVencimentoPrimeiraParcela").text('Venc.1ª Parcela');

				$('#valorEntrada').removeClass("bloqueado");
				$('#formaPagamentoEntrada').removeClass("bloqueado");
			}
		} else {
			$('#formaPagamento').addClass("bloqueado");
			$('#formaPagamento').val(null);
			$('#vencimentoPrimeiraParcela').addClass("bloqueado");
			$('#vencimentoPrimeiraParcela').val(null);
			$('#valorEntrada').addClass("bloqueado");
			$('#valorEntrada').val(null);
			$('#formaPagamentoEntrada').addClass("bloqueado");
			$('#formaPagamentoEntrada').val(null);
		}

		recarregarObrigatorios();
	}
</script>