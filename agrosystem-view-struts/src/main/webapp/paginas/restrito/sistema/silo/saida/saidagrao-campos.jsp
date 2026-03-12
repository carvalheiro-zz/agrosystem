<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!-- CAMPOS DE CADASTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -30px; margin-bottom: -15px;">
		<h1 class="page-header cabecalho_pagina">
			<i class="fa fa-graduation-cap" style="font-size: 26px;"></i>
			Cadastrar Saidas no Silo
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Cadastro das Saidas no Silo
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<logic:present property="saidaGrao.id" name="saidaGraoForm">
	<div class="row">
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px;">
			<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-user-plus"></i>
				&nbsp;${saidaGraoForm.saidaGrao.nomeUsuarioCriacao }
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
			<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-calendar-plus-o"></i>
				&nbsp;${saidaGraoForm.saidaGrao.dataOcorrenciaCriacao}
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
			<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-refresh"></i>
				&nbsp;${saidaGraoForm.saidaGrao.nomeUsuarioAlteracao}
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-left: 0px;">
			<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-calendar-o"></i>
				&nbsp;${saidaGraoForm.saidaGrao.dataOcorrenciaAlteracao}
			</div>
		</div>
	</div>
</logic:present>

<div class="row">
	<!-- Define o tamanho geral da saidaGrao -->
	<div class="col-md-offset-0 col-lg-offset-0 col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<html:form styleId="form_saidaGrao" action="restrito/sistema/saidaGrao" method="post">
			<html:hidden property="method" value="empty" />

			<div class="panel sombra">

				<div class="panel-heading" style="background-color: rgba(60, 118, 61, 0.39) !important;">
					<div class="row">
						<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-2">
							<label>Nº Contrato</label>
							<html:text styleClass="form-control input-lg focoInicial" styleId="contratoVenda" property="saidaGrao.contratoVenda.numero" name="saidaGraoForm" />
						</div>
						<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-2">
							<label>Cultura</label>
							<html:text styleClass="form-control input-lg bloqueado" styleId="culturaVenda" property="saidaGrao.contratoVenda.cultura.nome" name="saidaGraoForm" />
						</div>
						<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
							<label>Valor Unitário (Kg)</label>
							<html:text styleClass="form-control input-lg bloqueado " styleId="valorUnitarioContrato" property="saidaGrao.contratoVenda.valorUnitario" name="saidaGraoForm" />
						</div>
						<%-- <div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-2">
							<label>Quantidade (Kg)</label>
							<html:text styleClass="form-control input-lg bloqueado decimal" styleId="quantidadeContrato" property="saidaGrao.contratoVenda.quantidade" name="saidaGraoForm" />
						</div> --%>
						<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
							<label>Sacas</label>
							<html:text styleClass="form-control input-lg bloqueado decimal" styleId="emSacasContrato" property="saidaGrao.contratoVenda.emSacas" name="saidaGraoForm" />
						</div>
						<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-2">
							<label>Restante</label>
							<html:text styleClass="form-control input-lg bloqueado decimal" style="background-color: #ffbc007a !important;" styleId="quantidadeRestanteContrato" property="saidaGrao.contratoVenda.quantidadeRestante" name="saidaGraoForm" />
						</div>
						<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-2">
							<label>Entregue</label>
							<html:text styleClass="form-control input-lg bloqueado decimal" style="background-color: #0089ff61 !important;" styleId="quantidadeEntregueContrato" property="saidaGrao.contratoVenda.quantidadeEntregue" name="saidaGraoForm" />
						</div>
					</div>
				</div>

				<!-- INICIO FORMULARIO -->
				<div class="panel-body">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-7 col-lg-7">

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<label>Peso Liquido</label>
									<html:text styleClass="form-control input-lg text-center decimal" styleId="pesoLiquido" property="saidaGrao.pesoLiquido" name="saidaGraoForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-4">
									<label>Sacas</label>
									<html:text styleClass="form-control input-lg text-center decimal bloqueado" styleId="emSacas" property="saidaGrao.emSacas" name="saidaGraoForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-4">
									<label>Valor Bruto</label>
									<html:text styleClass="form-control input-lg text-center dinheiro bloqueado" styleId="valorBruto" property="saidaGrao.valorBruto" name="saidaGraoForm" />
								</div>
							</div>

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
									<label>% Desconto</label>
									<html:text styleClass="form-control input-lg text-center decimal calcularValorLiquido" styleId="percentualDesconto" property="saidaGrao.percentualDesconto" name="saidaGraoForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-7">
									<label>Observação ( 19 linhas )</label>
									<html:textarea styleClass="form-control input-lg" styleId="observacaoDesconto" property="saidaGrao.observacaoDesconto" name="saidaGraoForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label>Valor liquido</label>
									<html:text styleClass="form-control input-lg text-center dinheiro bloqueado" styleId="valorLiquido" property="saidaGrao.valorLiquido" name="saidaGraoForm" />
								</div>
							</div>

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-6">
									<div class="panel sombra">

										<div class="panel-heading cor-sistema" style="padding: 6px 0px 0px 15px; color: white;">
											<h4>Safra(s) origem</h4>
										</div>

										<!-- INICIO FORMULARIO -->
										<div class="panel-body">
											<div class="row">
												<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-6">
													<label>Safra</label>
													<html:text styleClass="form-control input-sm bloqueado" styleId="safra01" property="saidaGrao.safra01.nome" name="saidaGraoForm" />
													<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
													<html:hidden styleId="safraSelecionada01" property="saidaGrao.safra01.id" name="saidaGraoForm" />
												</div>
												<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-6">
													<label>Peso Liquido</label>
													<html:text styleClass="form-control input-sm text-center decimal bloqueado" styleId="pesoLiquidoSafra01" property="saidaGrao.pesoLiquidoSafra01" name="saidaGraoForm" />
												</div>

												<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-6">
													<label>Safra</label>
													<html:text styleClass="form-control input-sm bloqueado" styleId="safra02" property="saidaGrao.safra02.nome" name="saidaGraoForm" />
													<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
													<html:hidden styleId="safraSelecionada02" property="saidaGrao.safra02.id" name="saidaGraoForm" />
												</div>
												<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-6">
													<label>Peso Liquido</label>
													<html:text styleClass="form-control input-sm text-center decimal bloqueado" styleId="pesoLiquidoSafra02" property="saidaGrao.pesoLiquidoSafra02" name="saidaGraoForm" />
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-6">
									<div class="panel sombra">

										<div class="panel-heading cor-sistema" style="padding: 6px 0px 0px 15px; color: white;">
											<h4>Silo(s) origem</h4>
										</div>

										<!-- INICIO FORMULARIO -->
										<div class="panel-body">
											<div class="row">
												<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-6">
													<label>Silo</label>
													<html:select styleClass="form-control input-sm" styleId="localArmazenagem01" property="saidaGrao.localArmazenagem01.id" name="saidaGraoForm">
														<html:option value="">Selecione...</html:option>
														<html:optionsCollection name="saidaGraoForm" property="comboSilos" label="label" value="value" />
													</html:select>
												</div>

												<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-6">
													<label>Peso Liquido</label>
													<html:text styleClass="form-control input-sm text-center decimal" styleId="pesoLiquido01" property="saidaGrao.pesoLiquido01" name="saidaGraoForm" />
												</div>

												<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-6">
													<label>Silo</label>
													<html:select styleClass="form-control input-sm" styleId="localArmazenagem02" property="saidaGrao.localArmazenagem02.id" name="saidaGraoForm">
														<html:option value="">Selecione...</html:option>
														<html:optionsCollection name="saidaGraoForm" property="comboSilos" label="label" value="value" />
													</html:select>
												</div>

												<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-6">
													<label>Peso Liquido</label>
													<html:text styleClass="form-control input-sm text-center decimal" styleId="pesoLiquido02" property="saidaGrao.pesoLiquido02" name="saidaGraoForm" />
												</div>
											</div>
										</div>

									</div>
								</div>
							</div>

							<%-- <div class="row">

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<label>Safra</label>
									<html:text styleClass="form-control input-sm" styleId="safra01" property="saidaGrao.safra01.nome" name="saidaGraoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="safraSelecionada01" property="saidaGrao.safra01.id" name="saidaGraoForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<label>Peso Liquido Safra</label>
									<html:text styleClass="form-control input-sm text-center decimal" styleId="pesoLiquidoSafra01" property="saidaGrao.pesoLiquidoSafra01" name="saidaGraoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<label>Safra 2</label>
									<html:text styleClass="form-control input-sm" styleId="safra02" property="saidaGrao.safra02.nome" name="saidaGraoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="safraSelecionada02" property="saidaGrao.safra02.id" name="saidaGraoForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<label>Peso Liquido Safra 2</label>
									<html:text styleClass="form-control input-sm text-center decimal" styleId="pesoLiquidoSafra02" property="saidaGrao.pesoLiquidoSafra02" name="saidaGraoForm" />
								</div>
							</div> --%>							

							<div class="row">

								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
									<label>Data</label>
									<html:text styleClass="form-control input-sm text-center data" styleId="data" property="saidaGrao.data" name="saidaGraoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label>Ticket</label>
									<html:text styleClass="form-control input-sm text-center inteiro" styleId="ticket" property="saidaGrao.ticket" name="saidaGraoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label>Nº Nota</label>
									<html:text styleClass="form-control input-sm text-center inteiro" styleId="numeroNotaFiscal" property="saidaGrao.numeroNotaFiscal" name="saidaGraoForm" />
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label>Placa</label>
									<html:text styleClass="form-control input-sm text-center" styleId="placa" property="saidaGrao.placa" name="saidaGraoForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<label>Motorista</label>
									<html:text styleClass="form-control input-sm" styleId="motorista" property="saidaGrao.motorista" name="saidaGraoForm" />
								</div>
								
								
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<label>Natureza da Operação</label>
									<html:select styleClass="form-control input-sm" styleId="naturezaOperacao" property="saidaGrao.naturezaOperacao.id" name="saidaGraoForm">
										<html:option value="">Selecione...</html:option>
										<html:optionsCollection name="saidaGraoForm" property="comboNaturezas" label="label" value="value" />
									</html:select>
								</div>
							
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<label>Estoquista</label>
									<html:text styleClass="form-control input-sm" styleId="estoquista" property="saidaGrao.estoquista.pessoaFisica.razaoSocial" name="saidaGraoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="estoquistaSelecionado" property="saidaGrao.estoquista.id" name="saidaGraoForm" />
								</div>
							</div>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-5 col-lg-5">
							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<label>Cliente</label>
									<html:text styleClass="form-control input-lg bloqueado" styleId="cliente" property="saidaGrao.cliente.pessoaJuridica.razaoSocial" name="saidaGraoForm" />
								</div>
							</div>
							<div class="row">
								<div class="panel sombra">

									<div class="panel-heading cor-sistema" style="padding: 6px 0px 0px 15px; color: white;background-color: rgb(66, 66, 66) !important;">
										<h4>Informações de Armazenagem</h4>
									</div>

									<!-- INICIO FORMULARIO -->
									<div class="panel-body">
										<div class="row">
											<logic:iterate id="siloCorrente" name="saidaGraoForm" property="silos" type="br.com.srcsoftware.sistema.silo.silo.InformacoesSiloPOJO">
												<div class="col-xs-12 col-sm-12 col-md-12 col-lg-3">
													<label>Local</label>
													<html:text styleClass="form-control input-sm text-center bloqueado" styleId="silo" property="silo" name="siloCorrente" />
												</div>
												<div class="col-xs-12 col-sm-12 col-md-12 col-lg-3">
													<label>Cultura</label>
													<html:text styleClass="form-control input-sm text-center bloqueado" styleId="culturaSilo" property="cultura" name="siloCorrente" />
												</div>
												<div class="col-xs-12 col-sm-12 col-md-12 col-lg-6">
													<label>Saldo&nbsp;Kg&nbsp;(Sacas:&nbsp;${siloCorrente.getEmSacas() })</label>
													<html:text style="background-color: #acd5ec !important;" styleClass="form-control input-lg text-center dinheiro bloqueado" styleId="saldoSilo" property="saldoSilo" name="siloCorrente" title="Sacas:&nbsp;${siloCorrente.getEmSacas() }" />
												</div>
											</logic:iterate>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="panel sombra">

									<div class="panel-heading cor-sistema" style="padding: 6px 0px 0px 15px; color: white;background-color: rgb(66, 66, 66) !important;">
										<h4>Informações Saldo das Safras</h4>
									</div>

									<!-- INICIO FORMULARIO -->
									<div class="panel-body">
										<div class="row">
											<logic:iterate id="safraCorrente" name="saidaGraoForm" property="safras" type="br.com.srcsoftware.sistema.silo.entrada.InformacoesProducaoSafraPOJO">
												<div class="col-xs-12 col-sm-12 col-md-12 col-lg-3">
													<label>Safra</label>
													<html:text styleClass="form-control input-sm text-center bloqueado" styleId="nomeSafra" property="nomeSafra" name="safraCorrente" />
												</div>
												<div class="col-xs-12 col-sm-12 col-md-12 col-lg-3">
													<label>Cultura</label>
													<html:text styleClass="form-control input-sm text-center bloqueado" styleId="culturaSafra" property="cultura" name="safraCorrente" />
												</div>
												<div class="col-xs-12 col-sm-12 col-md-12 col-lg-6">
													<label>Saldo&nbsp;Kg&nbsp;(Sacas:&nbsp;${safraCorrente.getEmSacas() })</label>
													<html:text style="background-color: #acd5ec !important;" styleClass="form-control input-lg text-center dinheiro bloqueado" styleId="producao" property="producao" name="safraCorrente" title="Sacas:&nbsp;${safraCorrente.getEmSacas() }" />
												</div>
											</logic:iterate>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="panel-footer">
					<div class="row">
						<!-- BOTOES -->
						<logic:notPresent property="saidaGrao.id" name="saidaGraoForm">
							<logic:equal name="saidaGraoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.saidaGrao.inserir)">
								<div class="form-group col-xs-12 col-sm-3 col-md-4 col-lg-4" style="margin-bottom: 0px;">
									<button type="submit" id="inserir" class="btn btn-success btn-sm cor-sistema btn-block">
										<i class="fa fa-save"></i>
										Inserir
									</button>
								</div>
							</logic:equal>
						</logic:notPresent>
						<logic:present property="saidaGrao.id" name="saidaGraoForm">
							<logic:equal name="saidaGraoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.saidaGrao.alterar)">
								<div class="form-group col-xs-12 col-sm-3 col-md-4 col-lg-4" style="margin-bottom: 0px;">
									<button type="submit" id="alterar" class="btn btn-success btn-sm cor-sistema btn-block">
										<i class="fa fa-edit"></i>
										Alterar
									</button>
								</div>
							</logic:equal>
						</logic:present>

						<div class="form-group ol-xs-12 col-sm-3 col-md-4 col-lg-4" style="margin-bottom: 0px;">
							<button type="button" id="limpar" class="btn btn-success btn-sm cor-sistema btn-block">
								<i class="glyphicon glyphicon-erase"></i>
								Limpar
							</button>
						</div>

						<logic:equal name="saidaGraoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.saidaGrao.filtrar)">
							<div class="form-group col-xs-12 col-sm-3 col-md-4 col-lg-4" style="margin-bottom: 0px;">
								<button type="button" id="listagem" class="btn btn-success btn-sm cor-sistema btn-block">
									<i class="fa fa-search"></i>
									Listagem
								</button>
							</div>
						</logic:equal>

					</div>
				</div>
				<!-- TERMINO FORMULARIO -->
				<!-- /.panel-body -->

			</div>
			<!-- /.panel -->
		</html:form>
	</div>
	<!-- /.col-lg-12 -->
</div>


<script type="text/javascript">
	$(document).ready(function() {
		/* Foco inicial */
		if (($('#contratoVenda').val() == null || $('#contratoVenda').val() == '')) {
			$(".focoInicial").focus();
		} else {
			$("#pesoLiquido").focus();
		}

		/* Setando NOTNULL nos campos*/
		$("#contratoVenda").addClass("obrigatorio");
		$("#data").addClass("obrigatorio");

		$("#safra01").addClass("obrigatorio");
		$("#pesoLiquidoSafra01").addClass("obrigatorio");

		$("#localArmazenagem01").addClass("obrigatorio");
		$("#pesoLiquido01").addClass("obrigatorio");

		$("#cultura").addClass("obrigatorio");
		$("#cliente").addClass("obrigatorio");
		$("#pesoLiquido").addClass("obrigatorio");
		$("#valorBruto").addClass("obrigatorio");
		$("#ticket").addClass("obrigatorio");
		$("#numeroNotaFiscal").addClass("obrigatorio");
		$("#naturezaOperacao").addClass("obrigatorio");
		
		$("#placa").addClass("obrigatorio");
		$("#motorista").addClass("obrigatorio");

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#contratoVenda").prop("maxlength", 50);
		$("#safra01").prop("maxlength", 100);
		$("#safra02").prop("maxlength", 100);
		$("#cultura").prop("maxlength", 50);
		$("#localArmazenagem01").prop("maxlength", 100);
		$("#localArmazenagem02").prop("maxlength", 100);
		$("#cliente").prop("maxlength", 255);

		$("#pesoLiquido").prop("maxlength", 16);
		$("#pesoLiquido01").prop("maxlength", 16);
		$("#pesoLiquido02").prop("maxlength", 16);
		$("#pesoLiquidoSafra01").prop("maxlength", 16);
		$("#pesoLiquidoSafra02").prop("maxlength", 16);

		$("#valorBruto").prop("maxlength", 16);
		$("#percentualDesconto").prop("maxlength", 16);
		$("#observacaoDesconto").prop("maxlength", 1000);

		$("#ticket").prop("maxlength", 50);
		$("#numeroNotaFiscal").prop("maxlength", 50);
		
		$("#motorista").prop("maxlength", 100);

		/* Setando os placeholder dos campos*/
		$("#contratoVenda").prop("placeholder", "Nº Contrato");
		$("#safra01").prop("placeholder", "Safra");
		$("#safra02").prop("placeholder", "Safra 02");
		$("#cultura").prop("placeholder", "Cultura");
		$("#localArmazenagem01").prop("placeholder", "Local armazenagem");
		$("#localArmazenagem02").prop("placeholder", "Local armazenagem 02");
		$("#cliente").prop("placeholder", "Cliente");
		$("#observacaoDesconto").prop("placeholder", "Observação em geral ( 19 linhas )");
		
		$("#motorista").prop("placeholder", "Motorista");

		/* EVENTOS */
		$('#contratoVenda').focusout(function() {
			if (($('#contratoVenda').val() != null && $('#contratoVenda').val() != '')) {

				abrirModalProcessando();
				executarComSubmit('form_saidaGrao', 'selecionarContratoVenda');

			}
			/* var theForm = $('form[name=saidaGraoForm]');
			var actionURL = theForm.attr('action') + '?method=selecionarContratoVenda';
			$.ajax({
				type : 'POST',
				url : actionURL,
				data : {
					"contratoVenda.numero" : $('#contratoVenda').val()
				},
				success : function(data, textStatus, XMLHttpRequest) {
					$('#valorUnitarioContrato').val(data.valorUnitarioContrato);
					$('#quantidadeContrato').val(data.quantidadeContrato);
					$('#quantidadeRestanteContrato').val(data.quantidadeRestanteContrato);
					$('#emSacasContrato').val(data.emSacasContrato);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$('#valorUnitarioContrato').val(null);
					$('#quantidadeContrato').val(null);
					$('#quantidadeRestanteContrato').val(null);
					$('#emSacasContrato').val(null);
				}
			}); */
		});

		$('.calcularValorLiquido').focusout(function() {
			calcularValorLiquido();
		});

		function calcularValorLiquido() {
			if (($('#valorBruto').val() != null && $('#valorBruto').val() != '') && ($('#percentualDesconto').val() != null && $('#percentualDesconto').val() != '')) {

				var theForm = $('form[name=saidaGraoForm]');
				var actionURL = theForm.attr('action') + '?method=calcularValorLiquido';
				$.ajax({
					type : 'POST',
					url : actionURL,
					data : {
						"saidaGrao.valorBruto" : $('#valorBruto').val(),
						"saidaGrao.percentualDesconto" : $('#percentualDesconto').val()
					},
					success : function(data, textStatus, XMLHttpRequest) {
						$('#valorLiquido').val(data.valorLiquido);
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert(textStatus);
						$('#valorLiquido').val(null);
					}
				});

			} else if (($('#valorBruto').val() != null && $('#valorBruto').val() != '')) {
				$('#valorLiquido').val($('#valorBruto').val());
			}
		}

		$('#pesoLiquido').focusout(function() {

			if (($('#pesoLiquido').val() != null && $('#pesoLiquido').val() != '')) {

				var theForm = $('form[name=saidaGraoForm]');
				var actionURL = theForm.attr('action') + '?method=calcularQuantidades';
				$.ajax({
					type : 'POST',
					url : actionURL,
					data : {
						"saidaGrao.pesoLiquido" : $('#pesoLiquido').val(),
						"saidaGrao.contratoVenda.valorUnitario" : $('#valorUnitarioContrato').val()
					},
					success : function(data, textStatus, XMLHttpRequest) {
						$('#emSacas').val(data.emSacas);
						$('#valorBruto').val(data.valorBruto);

						var mensagemQuantidadeInvalida = data.mensagemQuantidadeInvalida;
						if (mensagemQuantidadeInvalida != null && mensagemQuantidadeInvalida != '') {
							$('#pesoLiquido').css('background-color', '#ffbebe');

							BootstrapDialog.show({
								size : BootstrapDialog.SIZE_NORMAL,
								title : 'Atenção',
								message : mensagemQuantidadeInvalida,
								type : BootstrapDialog.TYPE_DANGER, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
								closable : true, // <-- Default value is false
								draggable : true, // <-- Default value is false
								buttons : [ {
									hotkey : 13,
									label : 'Fechar',
									action : function(dialogRef) {
										dialogRef.close();
									}
								} ]
							});
						} else {
							$('#pesoLiquido').css('background-color', '#fff');
						}

						$('#safra01').val(data.nomeSafra01);
						$('#safraSelecionada01').val(data.idSafra01);
						$('#pesoLiquidoSafra01').val(data.pesoLiquidoSafra01);

						$('#safra02').val(data.nomeSafra02);
						$('#safraSelecionada02').val(data.idSafra02);
						$('#pesoLiquidoSafra02').val(data.pesoLiquidoSafra02);

						/* $('#localArmazenagem01').val(data.idLocalArmazenagem01);
						$('#pesoLiquido01').val(data.pesoLiquido01);
						$('#localArmazenagem02').val(data.idLocalArmazenagem02);
						$('#pesoLiquido02').val(data.pesoLiquido02);					
						 */

						calcularValorLiquido();

					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert(textStatus);
						$('#emSacas').val(null);
						$('#valorBruto').val(null);
					}
				});

			} else {
				$('#emSacas').val(null);
			}
		});
		
		
		$('#localArmazenagem01').change(function() {

			if (($('#pesoLiquido').val() != null && $('#pesoLiquido').val() != '')) {

				var theForm = $('form[name=saidaGraoForm]');
				var actionURL = theForm.attr('action') + '?method=gerenciarBaixasSilo1';
				$.ajax({
					type : 'POST',
					url : actionURL,
					data : {
						"saidaGrao.localArmazenagem01.id" : $('#localArmazenagem01').val(),
						"saidaGrao.pesoLiquido" : $('#pesoLiquido').val()
					},
					success : function(data, textStatus, XMLHttpRequest) {						

						var mensagemQuantidadeInvalida = data.mensagemQuantidadeInvalida;
						if (mensagemQuantidadeInvalida != null && mensagemQuantidadeInvalida != '') {
							$('#localArmazenagem01').css('background-color', '#ffbebe');

							BootstrapDialog.show({
								size : BootstrapDialog.SIZE_NORMAL,
								title : 'Atenção',
								message : mensagemQuantidadeInvalida,
								type : BootstrapDialog.TYPE_DANGER, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
								closable : true, // <-- Default value is false
								draggable : true, // <-- Default value is false
								buttons : [ {
									hotkey : 13,
									label : 'Fechar',
									action : function(dialogRef) {
										dialogRef.close();
									}
								} ]
							});
							$('#localArmazenagem01').val(null);
							$('#pesoLiquido01').val(null);
							$('#pesoLiquido02').val(null);
						} else {
							$('#localArmazenagem01').css('background-color', '#fff');	
							
							$('#localArmazenagem01').val(data.idLocalArmazenagem01);
							$('#pesoLiquido01').val(data.pesoLiquido01);
							$('#pesoLiquido02').val(data.pesoLiquido02);
							
							if(data.pesoLiquido02 != null && data.pesoLiquido02 != ''){
								$("#localArmazenagem02").addClass("obrigatorio");
								$("#pesoLiquido02").addClass("obrigatorio");
							}else{
								$("#localArmazenagem02").removeClass("obrigatorio");
								$("#pesoLiquido02").removeClass("obrigatorio");
							}
							
							recarregarObrigatorios();
						}
						
						/* 
						$('#localArmazenagem02').val(data.idLocalArmazenagem02);
						$('#pesoLiquido02').val(data.pesoLiquido02);					
						 */
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert(textStatus);
						$('#localArmazenagem01').val(null);
						$('#pesoLiquido01').val(null);
						$('#pesoLiquido02').val(null);
					}
				});

			} else {
				$('#localArmazenagem01').val(null);
			}			
			
		});
		
		$('#localArmazenagem02').change(function() {

			if (($('#pesoLiquido').val() != null && $('#pesoLiquido').val() != '')) {

				var theForm = $('form[name=saidaGraoForm]');
				var actionURL = theForm.attr('action') + '?method=gerenciarBaixasSilo2';
				$.ajax({
					type : 'POST',
					url : actionURL,
					data : {
						"saidaGrao.localArmazenagem02.id" : $('#localArmazenagem02').val(),
						"saidaGrao.pesoLiquido" : $('#pesoLiquido').val()
					},
					success : function(data, textStatus, XMLHttpRequest) {						

						var mensagemQuantidadeInvalida = data.mensagemQuantidadeInvalida;
						if (mensagemQuantidadeInvalida != null && mensagemQuantidadeInvalida != '') {
							$('#localArmazenagem02').css('background-color', '#ffbebe');

							BootstrapDialog.show({
								size : BootstrapDialog.SIZE_NORMAL,
								title : 'Atenção',
								message : mensagemQuantidadeInvalida,
								type : BootstrapDialog.TYPE_DANGER, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
								closable : true, // <-- Default value is false
								draggable : true, // <-- Default value is false
								buttons : [ {
									hotkey : 13,
									label : 'Fechar',
									action : function(dialogRef) {
										dialogRef.close();
									}
								} ]
							});
							
							$('#localArmazenagem02').val(null);
						} else {
							$('#localArmazenagem02').css('background-color', '#fff');
							$('#localArmazenagem02').val(data.idLocalArmazenagem02);
						}

						
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert(textStatus);
						$('#localArmazenagem02').val(null);
					}
				});

			} else {
				$('#localArmazenagem02').val(null);
			}
		});
		
		
		// Desliga o auto-complete da pagina
		$("#form_saidaGrao").prop("autocomplete", "off");

		$('#form_saidaGrao').on('submit', function(e) {
			abrirModalProcessando();
		});

		$('#inserir').click(function() {
			executar('form_saidaGrao', 'inserir');
		});

		$('#alterar').click(function() {
			executar('form_saidaGrao', 'alterar');
		});

		$('#limpar').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_saidaGrao', 'limpar');
		});

		$('#listagem').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_saidaGrao', 'abrirListagem');
		});

		$('#estoquista').keyup(function() {
			if ($('#estoquista').val() == null || $('#marca').val() == '') {
				$('#estoquistaSelecionado').val(null);
			}
		});
		$('#estoquista').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'saidaGrao.estoquista.pessoaFisica.razaoSocial',
			/* params : {
				'saidaGrao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/saidaGrao.src?method=selecionarEstoquistaAutoComplete',
			onSelect : function(suggestion) {
				$('#estoquistaSelecionado').val(suggestion.data);
			},
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			}, */
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#estoquistaSelecionado').val(null);
				}
			}
		});

		// ######################################### Auto Complete Safra/Setor/Cultura		

		$('#cliente').keyup(function() {
			if ($('#cliente').val() == null || $('#marca').val() == '') {
				$('#clienteSelecionado').val(null);
			}
		});
		$('#cliente').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'saidaGrao.cliente.pessoaJuridica.razaoSocial',
			/* params : {
				'saidaGrao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/saidaGrao.src?method=selecionarClienteAutoComplete',
			onSelect : function(suggestion) {
				$('#clienteSelecionado').val(suggestion.data);

				$("#pesoLiquido").focus();
			},
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			}, */
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#clienteSelecionado').val(null);
				}
			}
		});

		$('#safra01').keyup(function() {
			if ($('#safra01').val() == null || $('#safra01').val().trim() == '') {
				$('#safraSelecionada01').val(null);
			}
		});

		$('#safra01').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'saidaGrao.safra01.nome',
			serviceUrl : '${contextPath}/restrito/sistema/saidaGrao.src?method=selecionarSafraAutoComplete',
			onSelect : function(suggestion) {
				$('#safraSelecionada01').val(suggestion.data);
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#safraSelecionada').val(null);
				}
			}
		});

		$('#safra02').keyup(function() {
			if ($('#safra02').val() == null || $('#safra02').val().trim() == '') {
				$('#safraSelecionada02').val(null);
			}
		});

		$('#safra02').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'saidaGrao.safra02.nome',
			serviceUrl : '${contextPath}/restrito/sistema/saidaGrao.src?method=selecionarSafra02AutoComplete',
			onSelect : function(suggestion) {
				$('#safraSelecionada02').val(suggestion.data);
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#safraSelecionada02').val(null);
				}
			}
		});
	});
</script>