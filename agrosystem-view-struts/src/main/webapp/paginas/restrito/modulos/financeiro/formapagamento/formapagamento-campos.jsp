<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!-- CAMPOS DE CADASTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -30px; margin-bottom: -15px;">
		<h1 class="page-header cabecalho_pagina">
			<i class="fa fa-graduation-cap" style="font-size: 26px;"></i>
			Forma de pagamento
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Cadastro
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<logic:present property="formaPagamento.id" name="formaPagamentoForm">
	<div class="row">
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px;">
			<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-user-plus"></i>
				&nbsp;${formaPagamentoForm.formaPagamento.nomeUsuarioCriacao }
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
			<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-calendar-plus-o"></i>
				&nbsp;${formaPagamentoForm.formaPagamento.dataOcorrenciaCriacao}
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
			<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-refresh"></i>
				&nbsp;${formaPagamentoForm.formaPagamento.nomeUsuarioAlteracao}
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-left: 0px;">
			<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-calendar-o"></i>
				&nbsp;${formaPagamentoForm.formaPagamento.dataOcorrenciaAlteracao}
			</div>
		</div>
	</div>
</logic:present>

<div class="row">
	<!-- Define o tamanho geral da formaPagamento -->
	<div class="col-md-offset-1 col-lg-offset-1 col-xs-12 col-sm-12 col-md-10 col-lg-10">
		<div class="panel sombra">

			<!-- INICIO FORMULARIO -->
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_formaPagamento" action="restrito/sistema/formaPagamento" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12" style="display: none;">
									<label>Empresa</label>
									<logic:equal value="true" name="usuarioSessaoPOJO" property="isADM" scope="session">
										<html:select styleClass="form-control input-sm" styleId="empresa" property="formaPagamento.empresa.id" name="formaPagamentoForm">
											<html:optionsCollection name="formaPagamentoForm" property="comboEmpresas" label="label" value="value" />
										</html:select>
									</logic:equal>
									<logic:equal value="false" name="usuarioSessaoPOJO" property="isADM" scope="session">
										<html:text styleClass="form-control input-sm bloqueado" styleId="empresaView" property="formaPagamento.empresa.nomeFantasia" name="formaPagamentoForm" />
										<html:hidden styleId="empresa" property="formaPagamento.empresa.id" name="formaPagamentoForm" />
									</logic:equal>
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<html:text styleClass="form-control input-lg bloqueado text-center" styleId="nomeCompleto" property="formaPagamento.nomeCompleto" name="formaPagamentoForm" />
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label>Tipo</label>
									<html:select styleClass="form-control input-sm" styleId="tipo" property="formaPagamento.tipo" name="formaPagamentoForm">
										<html:option value="Receita">Receita</html:option>
										<html:option value="Despesa">Despesa</html:option>
									</html:select>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label>Nome</label>
									<html:select styleClass="form-control input-sm" styleId="tipoFormaPagamento" property="formaPagamento.tipoFormaPagamento.id" name="formaPagamentoForm">
										<html:option value="">Selecione...</html:option>
										<html:optionsCollection name="formaPagamentoForm" property="comboTipoFormaPagamento(${usuarioSessaoPOJO.isADM})" label="label" value="value" />
									</html:select>
									<html:hidden styleId="nome" name="formaPagamentoForm" property="formaPagamento.tipoFormaPagamento.nome" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label>Descritivo</label>
									<html:text styleClass="form-control input-sm montarNomeCompleto" styleId="especificacao" property="formaPagamento.especificacao" name="formaPagamentoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label>À Vista?</label>
									<input type="checkbox" id="aVistaCheck" checked data-toggle="toggle" data-on="Sim" data-off="Não" data-onstyle="primary" data-offstyle="danger" data-width="100%" data-size="small">
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label>Parcelas</label>
									<html:text styleClass="form-control input-sm inteiro montarNomeCompleto" styleId="parcelas" property="formaPagamento.parcelas" name="formaPagamentoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label>Gerar</label>
									<input type="checkbox" id="gerarQuitadaCheck" checked data-toggle="toggle" data-on="Compensado" data-off="Aberto" data-onstyle="primary" data-offstyle="info" data-width="100%" data-size="small">
									<html:hidden styleId="gerarQuitada" property="formaPagamento.gerarQuitada" name="formaPagamentoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-2">
									<label>% Taxa</label>
									<html:text styleClass="form-control input-sm decimal" styleId="percentualTaxa" property="formaPagamento.percentualTaxa" name="formaPagamentoForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-2">
									<label>R$ Taxa</label>
									<html:text styleClass="form-control input-sm decimal" styleId="valorTaxa" property="formaPagamento.valorTaxa" name="formaPagamentoForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-3">
									<label>Dias compensação</label>
									<html:text styleClass="form-control input-sm inteiro" styleId="diasCompensacao" property="formaPagamento.diasCompensacao" name="formaPagamentoForm" />
								</div>

							</div>

							<div class="row row-botoes">
								<!-- BOTOES -->
								<logic:notPresent property="formaPagamento.id" name="formaPagamentoForm">
									<logic:equal name="formaPagamentoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.formaPagamento.inserir)">
										<div class="col-xs-12 col-sm-3 col-md-4 col-lg-2">
											<button type="submit" id="inserir" class="btn btn-success btn-sm cor-sistema btn-block">
												<i class="fa fa-save"></i>
												Inserir
											</button>
										</div>
									</logic:equal>
								</logic:notPresent>
								<logic:present property="formaPagamento.id" name="formaPagamentoForm">
									<logic:equal name="formaPagamentoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.formaPagamento.alterar)">
										<div class="col-xs-12 col-sm-3 col-md-4 col-lg-2">
											<button type="submit" id="alterar" class="btn btn-success btn-sm cor-sistema btn-block">
												<i class="fa fa-edit"></i>
												Alterar
											</button>
										</div>
									</logic:equal>
								</logic:present>

								<div class="ol-xs-12 col-sm-3 col-md-4 col-lg-2">
									<button type="button" id="limpar" class="btn btn-success btn-sm cor-sistema btn-block">
										<i class="glyphicon glyphicon-erase"></i>
										Limpar
									</button>
								</div>

								<logic:equal name="formaPagamentoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.formaPagamento.filtrar)">
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
		/* Foco inicial */
		$("#tipoFormaPagamento").focus();

		$("#parcelas").prop("type", "number");
		$("#parcelas").prop("min", 1);

		/* Setando NOTNULL nos campos*/
		$("#tipoFormaPagamento").addClass("obrigatorio");
		$("#parcelas").addClass("obrigatorio");

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#tipoFormaPagamento").attr("maxlength", 50);
		$("#especificacao").attr("maxlength", 50);
		$("#parcelas").attr("maxlength", 2);

		/* Setando os placeholder dos campos*/
		$("#especificacao").attr("placeholder", "Ex: Stone crédito.");

		// Desliga o auto-complete da pagina
		$("#form_formaPagamento").attr("autocomplete", "off");

		$('#form_formaPagamento').on('submit', function(e) {
			abrirModalProcessando();
		});

		$('#gerarQuitadaCheck').change(function() {
			$('#gerarQuitada').val($(this).prop('checked'));
		});

		$('#aVistaCheck').change(function() {
			if ($(this).prop('checked')) {				
				$('#parcelas').val('0');

				$('#parcelas').addClass('bloqueado');
			} else {
				//$('#parcelas').val(null);
				$('#parcelas').removeClass('bloqueado');
			}
			montarNomeCompleto();
			recarregarObrigatorios();
		});

		function preencherCkecks() {
			if ($('#gerarQuitada').val() == 'false') {
				$('#gerarQuitadaCheck').bootstrapToggle('off')
			} else {
				$('#gerarQuitadaCheck').bootstrapToggle('on')
			}
			
			if ($('#parcelas').val() == '' || $('#parcelas').val() == null || $('#parcelas').val() == '0') {
				$('#parcelas').val('0');
				$('#aVistaCheck').bootstrapToggle('on')
			} else {
				$('#aVistaCheck').bootstrapToggle('off')
			}
		}
		preencherCkecks();

		$('#inserir').click(function() {
			executar('form_formaPagamento', 'inserir');
		});

		$('#alterar').click(function() {
			executar('form_formaPagamento', 'alterar');
		});

		$('#limpar').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_formaPagamento', 'limpar');
		});

		$('#filtrar').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_formaPagamento', 'abrirListagem');
		});

		$('#tipoFormaPagamento').on('change', function() {
			
			var theForm = $('form[name=formaPagamentoForm]');
			var params = theForm.serialize();
			var actionURL = theForm.attr('action') + '?method=selecionarTipoFormaPagamento';

			$.ajax({
				type : 'POST',
				url : actionURL,
				data : params,
				success : function(data, textStatus, XMLHttpRequest) {
					$('#nome').val(data.nome);
					
					montarNomeCompleto();
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(textStatus);
				}
			});			
			
			//gerenciarCamposCartao();
			
		});

		function gerenciarCamposCartao() {
			/* if ($('#tipoFormaPagamento').val() == 'Cartão') {
				$(".cartao").css('display', 'block');
			} else {
				$(".cartao").css('display', 'none');
			} */
		}

		gerenciarCamposCartao();

		$('.montarNomeCompleto').keyup(function() {
			montarNomeCompleto();
		});
		function montarNomeCompleto() {
			var nome = $('#nome').val();
			var especificacao = $('#especificacao').val();
			var parcelas = getParcelasToString($('#parcelas').val());

			$('#nomeCompleto').val(nome + ' ' + especificacao + ' ' + parcelas);
		}

		function getParcelasToString(parcelas) {
			if (parcelas == '') {
				return '';
			}

			if (parcelas == '0') {
				return '( A Vista )';
			} else {
				return '( Parcelado ' + parcelas + 'x )';
			}
		}
		montarNomeCompleto();
	});
</script>