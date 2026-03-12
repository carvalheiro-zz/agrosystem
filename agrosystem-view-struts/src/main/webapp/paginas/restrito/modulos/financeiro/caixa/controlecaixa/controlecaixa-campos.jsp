<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!-- CAMPOS DE CADASTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -30px; margin-bottom: -15px;">
		<h1 class="page-header cabecalho_pagina">
			<i class="fa fa-graduation-cap" style="font-size: 26px;"></i>
			Gerenciar Caixa
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Gerenciar aberturas e fechamentos de caixa
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<logic:present property="controleCaixa.id" name="controleCaixaForm">
	<div class="row">
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px;">
			<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-user-plus"></i>
				&nbsp;${controleCaixaForm.controleCaixa.nomeUsuarioCriacao }
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
			<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-calendar-plus-o"></i>
				&nbsp;${controleCaixaForm.controleCaixa.dataOcorrenciaCriacao}
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
			<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-refresh"></i>
				&nbsp;${controleCaixaForm.controleCaixa.nomeUsuarioAlteracao}
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-left: 0px;">
			<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-calendar-o"></i>
				&nbsp;${controleCaixaForm.controleCaixa.dataOcorrenciaAlteracao}
			</div>
		</div>
	</div>
</logic:present>

<div class="row">
	<!-- Define o tamanho geral da controleCaixa -->
	<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
		<div class="panel sombra">
		
		<logic:notPresent property="controleCaixa.id" name="controleCaixaForm">
			<div class="panel-heading cor-sistema" style="color: white;">
				<strong>Abertura de Caixa</strong>
			</div>
		</logic:notPresent>
		<logic:present property="controleCaixa.id" name="controleCaixaForm">
			<div class="panel-heading cor-sistema" style="color: white;">
				<strong>Fechamento de Caixa</strong>
			</div>
		</logic:present>
		

			<!-- INICIO FORMULARIO -->
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_controleCaixa" action="restrito/sistema/controleCaixa" method="post">
							<html:hidden property="method" value="empty" />
							
							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<label>Funcionario</label>
									<html:text styleClass="form-control input-md text-center bloqueado" styleId="funcionario" property="controleCaixa.funcionarioCaixa.pessoaFisica.razaoSocial" name="controleCaixaForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<label>Data abertura</label>
									<html:text styleClass="form-control input-md text-center bloqueado" styleId="dataAbertura" property="controleCaixa.dataAbertura" name="controleCaixaForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<label>Hora abertura</label>
									<html:text styleClass="form-control input-md text-center bloqueado" styleId="horaAbertura" property="controleCaixa.horaAbertura" name="controleCaixaForm" />
								</div>
								<div class="form-group col-md-offset-3 col-lg-offset-3 col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<label>Valor abertura</label>
									<html:text styleClass="form-control input-md dinheiro text-center" styleId="valorAbertura" property="controleCaixa.valorAbertura" name="controleCaixaForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<label>Valor Liquido</label>
									<html:text styleClass="form-control input-md bloqueado text-center" styleId="valorLiquido" property="controleCaixa.valorLiquido" name="controleCaixaForm" />
									<small id="valorLiquidoHelp" class="form-text text-muted">( créditos - débitos )</small>
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<label>Valor Bruto</label>
									<html:text styleClass="form-control input-md bloqueado text-center" styleId="valorBruto" property="controleCaixa.valorBruto" name="controleCaixaForm" />
									<small id="valorBrutoHelp" class="form-text text-muted">( valor abertura + total liquido )</small>
								</div>

								<div class="form-group col-md-offset-4 col-lg-offset-4 col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<label>Assinatura eletronica</label>
									<html:text styleClass="form-control input-md text-center autoCompleteOff senha" styleId="assinaturaEletronicaGerencia" property="controleCaixa.assinaturaEletronicaGerencia" name="controleCaixaForm" />
								</div>

							</div>

							<div class="row">
								<!-- BOTOES -->
								<logic:notPresent property="controleCaixa.id" name="controleCaixaForm">
									<logic:equal name="controleCaixaForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.controleCaixa.abrir)">
										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<button type="submit" id="abrirCaixa" class="btn btn-success btn-lg cor-sistema btn-block">
												<i class="fa fa-money"></i>
												Abrir caixa
											</button>
										</div>
									</logic:equal>
								</logic:notPresent>
								<logic:present property="controleCaixa.id" name="controleCaixaForm">
									<logic:equal name="controleCaixaForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.controleCaixa.fechar)">
										<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<button type="button" id="gerarResumoPDF" class="btn btn-primary btn-lg btn-block">
												<i class="fa fa-file-pdf-o"></i>
												Exibir resumo caixa
											</button>
										</div>
									</logic:equal>
									<logic:equal name="controleCaixaForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.controleCaixa.fechar)">
										<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<button type="button" id="confirmarFechamentoCaixa" class="btn btn-success btn-lg cor-sistema btn-block">
												<i class="fa fa-check-circle-o"></i>
												Confirmar fechamento
											</button>
										</div>
									</logic:equal>
								</logic:present>

								<%-- <div class="ol-xs-12 col-sm-3 col-md-3 col-lg-3">
											<button type="button" id="limpar" class="btn btn-success btn-sm cor-sistema btn-block">
												<i class="glyphicon glyphicon-erase"></i>
												Limpar
											</button>
										</div>

										<logic:equal name="controleCaixaForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.controleCaixa.filtrar)">
											<div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
												<button type="button" id="listagem" class="btn btn-success btn-sm cor-sistema btn-block">
													<i class="fa fa-search"></i>
													Listagem
												</button>
											</div>
										</logic:equal> --%>

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





	<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
		<div class="panel sombra">

			<div class="panel-heading cor-sistema" style="color: white;">
				<strong>Resumo</strong>
			</div>

			<!-- INICIO FORMULARIO -->
			<div class="panel-body">
				<div class="row">
					<%-- <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<logic:iterate id="resumoCorrente" name="controleCaixaForm" property="controleCaixa.resumoCaixa" type="br.com.srcsoftware.modular.financeiro.caixa.aberturafechamento.ResumoCaixaPOJO">
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-8">
									<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px; font-weight: bold;">
										<i class="fa fa-info-circle fa-fw" style="font-size: 18px;"></i>
										${resumoCorrente.formaPagamento}
									</div>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="alert alert-success" role="alert" style="padding-bottom: 5px; padding-top: 5px; font-weight: bold;">
										<i class="fa fa-money" style="font-size: 18px;"></i>
										${resumoCorrente.resultado}
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
										<i class="fa fa-plus" style="font-size: 18px;"></i>
										${resumoCorrente.valorCredito}
									</div>

								</div>
								<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<div class="alert alert-danger" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
										<i class="fa fa-minus" style="font-size: 18px;"></i>
										${resumoCorrente.valorDebito}
									</div>
								</div>


							</div>
						</logic:iterate>
					</div> --%>




					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="table-responsive">
							<table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
								<thead>
									<!-- CABEÇALHO DA TABELA -->
									<tr class="cor-sistema" style="color: white;">
										<th>Forma pagamento</th>
										<th class="text-center">Receitas</th>
										<th class="text-center">Despesas</th>
										<th class="text-center">Saldo</th>
									</tr>
								</thead>
								<tbody>
									<!-- TABELA -->
									<logic:iterate id="resumoCorrente" name="controleCaixaForm" property="controleCaixa.resumoCaixa" type="br.com.srcsoftware.modular.financeiro.caixa.aberturafechamento.ResumoCaixaPOJO">
										<tr>
											<!-- success / info / warning / danger -->
											<td>${resumoCorrente.formaPagamento}</td>
											<td style="color: #1919ab;" class="text-right">
												<i class="fa fa-plus"></i>
												&nbsp;&nbsp;${resumoCorrente.valorCredito}
											</td>
											<td style="color: #ab1919;" class="text-right">
												<i class="fa fa-minus"></i>
												&nbsp;&nbsp;${resumoCorrente.valorDebito}
											</td>
											<logic:notMatch value="-" name="resumoCorrente" property="resultado">
												<td style="color: #1919ab; font-weight: bold !important;" class="text-right">	
													<!-- <i class="fa fa-money"></i>
													&nbsp;&nbsp; -->${resumoCorrente.resultado}
												</td>
											</logic:notMatch>
											<logic:match value="-" name="resumoCorrente" property="resultado">
												<td style="color: #ab1919; font-weight: bold !important;" class="text-right">	
													<!-- <i class="fa fa-money"></i>
													&nbsp;&nbsp; -->(${resumoCorrente.resultado})
												</td>
											</logic:match>
										</tr>
									</logic:iterate>
								</tbody>
							</table>
						</div>
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
		$("#valorAbertura").focus();

		/* Setando NOTNULL nos campos*/
		$("#valorAbertura").addClass("obrigatorio");

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		//$("#nome").attr("maxlength", 50);

		/* Setando os placeholder dos campos*/
		//$("#nome").attr("placeholder", "Nome da forma de pagamento");

		// Desliga o auto-complete da pagina
		$("#form_controleCaixa").attr("autocomplete", "off");

		$('#form_controleCaixa').on('submit', function(e) {
			abrirModalProcessando();
		});

		$('#abrirCaixa').click(function() {
			executar('form_controleCaixa', 'abrirCaixa');
		});

		$('#gerarResumoPDF').click(function() {
			gerarRelatorio('form_controleCaixa', 'gerarResumoPDF');
		});
		
		$('#confirmarFechamentoCaixa').click(function() {
			executarComSubmit('form_controleCaixa', 'confirmarFechamentoCaixa');
		});
		

	});
</script>