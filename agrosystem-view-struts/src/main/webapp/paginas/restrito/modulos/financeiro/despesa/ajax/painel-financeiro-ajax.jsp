<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<div class="panel" style="border-color: #909090;">
	<div class="panel-heading cor-sistema" style="color: white;">
		<strong>Financeiro</strong>
	</div>

	<div class="panel-body">

		<div class="row">

			<logic:notEmpty name="erroAjax" scope="session">
				<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-11">
					<div class="alert alert-danger">
						<strong>Atenção!</strong>
						${erroAjax}
					</div>
				</div>
			</logic:notEmpty>

		</div>

		<div class="row">

			<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
				<div class="row">
					<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
						<label>Número NFe</label>
						<html:text styleClass="form-control numero input-sm text-center" styleId="numero" property="despesa.contaPagar.numero" name="despesaForm" />
					</div>

					<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
						<label>À vista / Parcelado</label>
						<html:select styleClass="form-control input-sm " styleId="tipoPagamento" property="despesa.contaPagar.tipo" name="despesaForm">
							<html:option value="">Selecione...</html:option>
							<html:option value="À vista">À vista</html:option>
							<html:option value="Parcelado">Parcelado</html:option>
						</html:select>
					</div>

					<div class="form-group col-xs-12 col-sm-12 col-md-8 col-lg-8">
						<label>Forma Pagamento</label>
						<a href="#" id="modalCadastrarFormaPagamento">
							<i style="text-shadow: 1px 0px 0px white, -1px 0px 0px white, 0px 1px 0px white, 0px -1px 0px white; color: green; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-plus"></i>
						</a>
						<html:select styleClass="form-control input-sm " styleId="formaPagamento" property="despesa.contaPagar.formaPagamento.id" name="despesaForm">
							<html:option value="">Selecione...</html:option>
							<html:optionsCollection name="despesaForm" property="comboFormaPagamento(${usuarioSessaoPOJO.isADM})" label="label" value="value" />
						</html:select>
					</div>

					<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4" id="divVencimentoPrimeiraParcela">
						<label id="lblVencimentoPrimeiraParcela">Venc.1ª Parcela</label>
						<html:text styleClass="form-control input-sm data text-center " styleId="vencimentoPrimeiraParcela" property="despesa.contaPagar.vencimentoPrimeiraParcela" name="despesaForm" />
					</div>

					<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4" id="divValorEntrada">
						<label>R$ Entrada</label>
						<html:text styleClass="form-control input-sm dinheiro " styleId="valorEntrada" property="despesa.contaPagar.valorEntrada" name="despesaForm" />
					</div>

					<div class="form-group col-xs-12 col-sm-12 col-md-8 col-lg-8" id="divFormaPagamentoEntrada">
						<label>Forma Pag. Entrada</label>
						<html:select styleClass="form-control input-sm " styleId="formaPagamentoEntrada" property="despesa.contaPagar.formaPagamentoEntrada.id" name="despesaForm">
							<html:option value="">Selecione...</html:option>
							<html:optionsCollection name="despesaForm" property="comboFormaPagamentoAVista(${usuarioSessaoPOJO.isADM})" label="label" value="value" />
						</html:select>
					</div>
				</div>
			</div>

			<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
				<div class="row">
					<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12" id="id-taxas">
						<jsp:include page="painel-taxas-ajax.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="panel-footer">

		<div class="row">
			<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<jsp:include page="painel-parcelas-ajax.jsp"></jsp:include>
			</div>
		</div>

	</div>

</div>

<script type="text/javascript">
	$(document).ready(function() {

		/* Setando NOTNULL nos campos*/
		$("#data").addClass("obrigatorio");
		$("#numero").addClass("obrigatorio");
		$("#tipoPagamento").addClass("obrigatorio");
		$("#vencimentoPrimeiraParcela").addClass("obrigatorio");

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#numero").attr("maxlength", 20);
		$("#materiaPrima").attr("maxlength", 50);
		$("#equipamento").attr("maxlength", 50);

		/* Setando os placeholder dos campos*/
		$("#numero").attr("placeholder", "Número");
		
		$('#modalCadastrarFormaPagamento').on('click', function() {
			var actionURL = '${contextPath}/restrito/sistema/formaPagamento.src?method=abrirModal&formaPagamento.tipo=Despesa';

			$.ajax({
				type : 'POST',
				url : actionURL,
				success : function(data, textStatus, XMLHttpRequest) {
					BootstrapDialog.show({
						size : BootstrapDialog.SIZE_WIDE,
						title : 'Cadastrar Forma de Pagamento',
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

		$('#tipoPagamento').change(function() {
			atualizarComboFormaPagamento();
		});

		function atualizarComboFormaPagamento() {
			var theForm = $('form[name=despesaForm]');
			var params = theForm.serialize();
			var actionURL = theForm.attr('action') + '?method=carregarComboFormaPagamento';

			$.ajax({
				type : 'POST',
				url : actionURL,
				data : params,
				success : function(data, textStatus, XMLHttpRequest) {

					var html = "";

					html = html + '<html:select styleClass="form-control input-sm " styleId="formaPagamento" property="despesa.contaPagar.formaPagamento.id" name="despesaForm">';
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

		var vencimentoPrimeiraParcelaOld = null;
		$("#vencimentoPrimeiraParcela").focusin(function() {
			vencimentoPrimeiraParcelaOld = $("#vencimentoPrimeiraParcela").val();
		});
		$("#vencimentoPrimeiraParcela").focusout(function() {
			
			if ($("#vencimentoPrimeiraParcela").val() == vencimentoPrimeiraParcelaOld) {
				return;
			}

			if ('${despesaForm.despesa.id}' != null && '${despesaForm.despesa.id}' != '') {
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
			if ('${despesaForm.despesa.id}' != null && '${despesaForm.despesa.id}' != '') {
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
			if ('${despesaForm.despesa.id}' != null && '${despesaForm.despesa.id}' != '') {
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
			if ('${despesaForm.despesa.id}' != null && '${despesaForm.despesa.id}' != '') {
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
			if ('${despesaForm.despesa.id}' != null && '${despesaForm.despesa.id}' != '') {
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

		function calcularValorFinal() {
			var theForm = $('form[name=despesaForm]');
			var params = theForm.serialize();
			var actionURL = theForm.attr('action') + '?method=calcularValorFinal';
			$.ajax({
				type : 'POST',
				url : actionURL,
				data : params,
				success : function(data, textStatus, XMLHttpRequest) {
					$('#valorFinal').val(data.valorFinal);
					$('#valorTotaldespesa').text(data.valorFinal);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(textStatus);
				}
			});
		}
		gerenciarCamposTipoFinanceiro();
		
		$("#vencimentoPrimeiraParcela").focusout(function() {
			validarData('vencimentoPrimeiraParcela');
		});
	});

	function gerenciarCamposTipoFinanceiro() {
		if ($('#tipoPagamento').val() != null && $('#tipoPagamento').val() != '') {

			// Adicionando os campos
			$('#formaPagamento').removeClass("bloqueado");
			$('#vencimentoPrimeiraParcela').removeClass("bloqueado");

			if ($('#tipoPagamento').val() == 'À vista' || $('#tipoPagamento').val() == 'Fixa') {

				if ($('#tipoPagamento').val() == 'Fixa') {
					$('#divAtiva').css("display", "block");
				} else {
					$('#divAtiva').css("display", "none");
				}

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

				$('#divAtiva').css("display", "none");
				$('#ativa').val(null);
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
			$('#divAtiva').css("display", "none");
		}

		recarregarObrigatorios();
	}

	function atualizarCabecalho() {
		// PAREI AQUI
		var theForm = $('form[name=despesaForm]');
		var actionURL = theForm.attr('action') + '?method=atualizarCabecalho';
		$.ajax({
			type : 'POST',
			url : actionURL,
			success : function(data, textStatus, XMLHttpRequest) {
				$('#idCabecalhoAjax').html(data);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(textStatus);
			}
		});
	}
</script>