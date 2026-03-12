<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!-- CAMPOS DE CADASTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -30px; margin-bottom: -15px;">
		<h1 class="page-header cabecalho_pagina">
			<i class="fa fa-graduation-cap" style="font-size: 26px;"></i>
			Cadastrar Receita
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Cadastro das Receitas Fixas ou não
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

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

<jsp:include page="../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<div class="row">
	<!-- Define o tamanho geral da serviço -->
	<div class="col-md-offset-0 col-lg-offset-0 col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel sombra">

			<div class="panel-heading cor-sistema" id="idCabecalhoAjax">
				<jsp:include page="ajax/cabecalho-ajax.jsp"></jsp:include>
			</div>

			<!-- INICIO FORMULARIO -->
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_receita" action="restrito/sistema/receita" method="post">
							<html:hidden property="method" value="empty" />
							<html:hidden styleId="gerarProxima" property="gerarProxima" name="receitaForm" />

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12" style="display: none;">
									<label>Empresa</label>
									<logic:equal value="true" name="usuarioSessaoPOJO" property="isADM" scope="session">
										<html:select styleClass="form-control input-sm" styleId="empresa" property="receita.empresa.id" name="receitaForm">
											<html:optionsCollection name="receitaForm" property="comboEmpresas" label="label" value="value" />
										</html:select>
									</logic:equal>
									<logic:equal value="false" name="usuarioSessaoPOJO" property="isADM" scope="session">
										<html:text styleClass="form-control input-sm bloqueado" styleId="empresaView" property="receita.empresa.nomeFantasia" name="receitaForm" />
										<html:hidden styleId="empresa" property="receita.empresa.id" name="receitaForm" />
									</logic:equal>
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<label>Descrição</label>
										<html:text styleClass="form-control input-sm " styleId="nome" property="receita.nome" name="receitaForm" />
									</div>

									<logic:equal name="receitaForm" property="receita.contaReceber.tipo" value="Fixa">
										<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<label>Valor Atual</label>
											<html:text styleClass="form-control input-lg dinheiro" styleId="valorAtual" property="receita.contaReceber.valorUltimaParcela" name="receitaForm" />
										</div>
										<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<label>Valor Acumulado</label>
											<html:text styleClass="form-control input-lg dinheiro" styleId="valor" property="receita.contaReceber.valor" name="receitaForm" />
										</div>
									</logic:equal>

									<logic:notEqual name="receitaForm" property="receita.contaReceber.tipo" value="Fixa">
										<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<label>Valor</label>
											<html:text styleClass="form-control input-lg dinheiro" styleId="valor" property="receita.contaReceber.valor" name="receitaForm" />
										</div>
										<div class="form-group col-xs-6 col-sm-6 col-md-6 col-lg-6" id="divAtiva">
											<label>Permite gerar próximas?</label>
											<input type="checkbox" id="ativaCheck" checked data-toggle="toggle" data-on="<i class='fa fa-check-square-o' style='font-size: 20px;'>&nbsp;Sim</i>" data-off="<i class='fa fa-square-o' style='font-size: 20px;'>&nbsp;Não</i>"
												data-onstyle="primary" data-offstyle="danger" data-width="100%" data-size="small">
											<html:hidden styleId="ativa" property="receita.ativa" name="receitaForm" />
										</div>
									</logic:notEqual>
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<label>Observação</label>
										<h6 class="pull-right" id="count_observacao"></h6>
										<html:textarea styleClass="form-control input-sm" styleId="observacao" property="receita.observacao" name="receitaForm" rows="6" />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12" id="id-financeiro">
									<jsp:include page="ajax/painel-financeiro-ajax.jsp"></jsp:include>
								</div>
							</div>


							<div class="row">
								<!-- BOTOES -->
								<logic:notPresent property="receita.id" name="receitaForm">
									<logic:equal name="receitaForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.receita.inserir)">
										<div class="col-xs-12 col-sm-3 col-md-4 col-lg-2">
											<button type="submit" id="inserir" class="btn btn-success btn-sm cor-sistema btn-block">
												<i class="fa fa-save"></i>
												Inserir
											</button>
										</div>
									</logic:equal>
								</logic:notPresent>
								<logic:present property="receita.id" name="receitaForm">
									<logic:equal name="receitaForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.receita.alterar)">
										<div class="col-xs-12 col-sm-3 col-md-4 col-lg-2">
											<button type="submit" id="alterar" class="btn btn-success btn-sm cor-sistema btn-block">
												<i class="fa fa-edit"></i>
												Alterar
											</button>
										</div>
									</logic:equal>
								</logic:present>

								<div class="col-xs-12 col-sm-3 col-md-4 col-lg-2">
									<button type="button" id="limpar" class="btn btn-success btn-sm cor-sistema btn-block">
										<i class="glyphicon glyphicon-erase"></i>
										Limpar
									</button>
								</div>

								<logic:equal name="receitaForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.receita.filtrar)">
									<div class="col-xs-12 col-sm-3 col-md-4 col-lg-2">
										<button type="button" id="filtrar" class="btn btn-success btn-sm cor-sistema btn-block">
											<i class="fa fa-search"></i>
											Listagem
										</button>
									</div>
								</logic:equal>
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
		if ($('#tipoPagamentoFixo').val() == 'Fixa') {
			/* Validando os campos Obrigatorios */
			$("#valor").addClass("bloqueado");
			$("#valorAtual").addClass("bloqueado");
			$("#numero").addClass("bloqueado");
			
			recarregarObrigatorios();
		}
		
		$("#nome").focus();

		/* Setando NOTNULL nos campos*/
		$("#nome").addClass("obrigatorio");
		$("#valor").addClass("obrigatorio");

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#nome").attr("maxlength", 100);
		$("#observacao").attr("maxlength", 1000);
		$("#valor").attr("maxlength", 15);

		/* Setando os placeholder dos campos*/
		$("#nome").attr("placeholder", "Descrição da receita");
		$("#observacao").attr("placeholder", "Observação sobre a receita");

		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_receita").attr("autocomplete", "off");

		$('#form_receita').on('submit', function(e) {
			abrirModalProcessando();
		});

		$('#inserir').click(function() {
			executar('form_receita', 'inserir');
		});

		$('#alterar').click(function() {
			executar('form_receita', 'alterar');
		});

		$('#limpar').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_receita', 'limpar');
		});

		$('#filtrar').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_receita', 'abrirListagem');
		});
		
		var valorOld = null;
		$("#valor").focusin(function() {
			valorOld = $("#valor").val();
		});
		$("#valor").focusout(function() {
			if ($("#valor").val() == valorOld) {
				return;
			}
			$("#valorTotal").val($("#valor").val());

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

							$("#valor").val(valorOld);
							$("#valorTotal").val(valorOld);

							dialogRef.close();
						}
					} ]
				});
			}
		});

		$('#ativaCheck').change(function() {
			$('#ativa').val($(this).prop('checked'));
		});

		function preencherCkecks() {
			if ($('#ativa').val() == 'false') {
				$('#ativaCheck').bootstrapToggle('off')
			} else {
				$('#ativaCheck').bootstrapToggle('on')
			}
		}
		preencherCkecks();

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
	});
</script>