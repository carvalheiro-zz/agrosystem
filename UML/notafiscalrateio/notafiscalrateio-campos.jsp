<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<div class="row">
	<!-- Define o tamanho geral da notaFiscalRateio -->
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel sombra">

			<!-- INICIO FORMULARIO -->
			<div class="panel-body">
				<logic:present property="notaFiscalRateio.id" name="notaFiscalRateioForm">
					<div class="row">
						<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px;">
							<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
								<i class="fa fa-user-plus"></i>
								&nbsp;${notaFiscalRateioForm.notaFiscalRateio.contaPagar.nomeUsuarioCriacao }
							</div>
						</div>
						<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
							<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
								<i class="fa fa-calendar-plus-o"></i>
								&nbsp;${notaFiscalRateioForm.notaFiscalRateio.contaPagar.dataOcorrenciaCriacao}
							</div>
						</div>
						<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
							<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
								<i class="fa fa-refresh"></i>
								&nbsp;${notaFiscalRateioForm.notaFiscalRateio.contaPagar.nomeUsuarioAlteracao}
							</div>
						</div>
						<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-left: 0px;">
							<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
								<i class="fa fa-calendar-o"></i>
								&nbsp;${notaFiscalRateioForm.notaFiscalRateio.contaPagar.dataOcorrenciaAlteracao}
							</div>
						</div>
					</div>
				</logic:present>
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_notaFiscalRateio" action="restrito/sistema/notaFiscalRateio" method="post">
							<html:hidden property="method" value="empty" />
							<%-- <html:hidden property="notaFiscalRateio.contaPagar.quantidadeParcelas" name="notaFiscalRateioForm" styleClass="avista" /> --%>

							<ul class="nav nav-tabs" id="tabsNotaFiscalRateio">
								<li class="active">
									<a data-toggle="tab" href="#dados">Dados da Nota Fiscal de Rateio</a>
								</li>
								<li>
									<a data-toggle="tab" href="#itens">Centros de Custos</a>
								</li>
								<logic:notEmpty name="notaFiscalRateioForm" property="notaFiscalRateio.contaPagar.parcelas">
									<li>
										<a data-toggle="tab" href="#financeiro">Financeiro</a>
									</li>
								</logic:notEmpty>
							</ul>

							<div class="tab-content">
								<div id="dados" class="tab-pane fade in active" style="margin-top: 20px;">
									<div class="row">
										<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<div class="alert alert-success" style="margin-bottom: 0px" id="alert-situacaoNotaFiscalRateio">
												<div class="row">
													<div class="col-xs-12 col-sm-10 col-md-12 col-lg-4">
														<strong>Situação:</strong>
														<span id="situacaoNotaFiscalRateio"></span>
													</div>
													<div class="col-xs-12 col-sm-10 col-md-12 col-lg-4 text-center">
														<strong>Total R$ </strong>
														<span id="valorTotalNotaFiscalRateio"></span>
													</div>
													<div class="col-xs-12 col-sm-10 col-md-12 col-lg-4 text-right">
														<strong>Pago + Acréscimo R$ </strong>
														<span id="valorTotalMaisAcrescimoNotaFiscalRateio"></span>
													</div>
												</div>
											</div>
										</div>
										<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-3">
											<label>Tipo</label>
											<html:select styleClass="form-control input-sm inalteraveis" styleId="tipoNF" property="notaFiscalRateio.tipo" name="notaFiscalRateioForm">
												<html:option value="">Selecione...</html:option>
												<html:option value="Investimento/Despesa">Investimento / Despesa</html:option>
												<html:option value="Receita">Receita</html:option>
											</html:select>
										</div>
										<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-5">
											<label>Nome Fornecedor</label>
											<html:text styleClass="form-control input-sm cor-campos-composicao-sistema" styleId="nomeFornecedor" property="notaFiscalRateio.fornecedor.nome" name="notaFiscalRateioForm" />
											<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
											<html:hidden styleId="fornecedorSelecionado" property="notaFiscalRateio.fornecedor.id" name="notaFiscalRateioForm" />
										</div>
										<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-2">
											<label>Emissão</label>
											<html:text styleClass="form-control input-sm data text-center " styleId="data" property="notaFiscalRateio.contaPagar.data" name="notaFiscalRateioForm" />
										</div>
										<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-3">
											<label>Nº NF</label>
											<html:text styleClass="form-control numero numeroVendaNumeroRecibo input-sm text-center" styleId="numero" property="notaFiscalRateio.numero" name="notaFiscalRateioForm" />
										</div>
										<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-3">
											<label>Nº Recibo</label>
											<html:text styleClass="form-control numero numeroVendaNumeroRecibo input-sm text-center" styleId="numeroRecibo" property="notaFiscalRateio.numeroRecibo" name="notaFiscalRateioForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-2">
											<label>À vista / À prazo</label>
											<html:select styleClass="form-control input-sm " styleId="tipoPagamento" property="notaFiscalRateio.contaPagar.tipo" name="notaFiscalRateioForm">
												<html:option value="">Selecione...</html:option>
												<html:option value="À vista">À vista</html:option>
												<html:option value="À prazo">À prazo</html:option>
											</html:select>
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2" id="divQuantidadeParcelas">
											<label>Parcelas</label>
											<html:text styleClass="form-control input-sm text-center " styleId="quantidadeParcelas" property="notaFiscalRateio.contaPagar.quantidadeParcelas" name="notaFiscalRateioForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-2" id="divValorEntrada">
											<label>R$ Entrada</label>
											<html:text styleClass="form-control input-sm dinheiro " styleId="valorEntrada" property="notaFiscalRateio.contaPagar.valorEntrada" name="notaFiscalRateioForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-2" id="divFormaPagamentoEntrada">
											<label>Forma Pag. Entrada</label>
											<html:select styleClass="form-control input-sm " styleId="formaPagamentoEntrada" property="notaFiscalRateio.contaPagar.formaPagamentoEntrada.id" name="notaFiscalRateioForm">
												<html:option value="">Selecione...</html:option>
												<html:optionsCollection name="notaFiscalRateioForm" property="comboFormaPagamento" label="label" value="value" />
											</html:select>
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-2" id="divVencimentoPrimeiraParcela">
											<label id="lblVencimentoPrimeiraParcela">Venc.1ª Parcela</label>
											<html:text styleClass="form-control input-sm data text-center " styleId="vencimentoPrimeiraParcela" property="notaFiscalRateio.contaPagar.vencimentoPrimeiraParcela" name="notaFiscalRateioForm" />
										</div>
										
										<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-2" id="divFormaPagamento">
											<label>Forma Pagamento</label>
											<html:select styleClass="form-control input-sm " styleId="formaPagamento" property="notaFiscalRateio.contaPagar.formaPagamento.id" name="notaFiscalRateioForm">
												<html:option value="">Selecione...</html:option>
												<html:optionsCollection name="notaFiscalRateioForm" property="comboFormaPagamento" label="label" value="value" />
												<logic:present property="notaFiscalRateio.id" name="notaFiscalRateioForm">
													<html:option value="xxx">Misto</html:option>
												</logic:present>
											</html:select>
										</div>
									</div>
									<div class="row">
										<!-- BOTOES -->
										<logic:notPresent property="notaFiscalRateio.id" name="notaFiscalRateioForm">
											<logic:equal name="notaFiscalRateioForm" value="true" property="acessoPermitido(${usuario_logado.id}.notaFiscalRateio.inserir)">
												<div class="form-group col-xs-12 col-sm-3 col-md-3 col-lg-3">
													<button type="submit" id="inserir" class="btn btn-success btn-sm cor-sistema btn-block">
														<i class="fa fa-save"></i>
														Inserir
													</button>
												</div>
											</logic:equal>
										</logic:notPresent>
										<logic:present property="notaFiscalRateio.id" name="notaFiscalRateioForm">
											<logic:equal name="notaFiscalRateioForm" value="true" property="acessoPermitido(${usuario_logado.id}.notaFiscalRateio.alterar)">
												<div class="form-group col-xs-12 col-sm-3 col-md-3 col-lg-3">
													<button type="submit" id="alterar" class="btn btn-success btn-sm cor-sistema btn-block">
														<i class="fa fa-edit"></i>
														Alterar
													</button>
												</div>
											</logic:equal>
										</logic:present>

										<div class="form-group ol-xs-12 col-sm-3 col-md-3 col-lg-3">
											<button type="button" id="limpar" class="btn btn-success btn-sm cor-sistema btn-block">
												<i class="glyphicon glyphicon-erase"></i>
												Limpar
											</button>
										</div>

										<logic:equal name="notaFiscalRateioForm" value="true" property="acessoPermitido(${usuario_logado.id}.notaFiscalRateio.filtrar)">
											<div class="form-group col-xs-12 col-sm-3 col-md-3 col-lg-3">
												<button type="button" id="listagem" class="btn btn-success btn-sm cor-sistema btn-block">
													<i class="fa fa-search"></i>
													Listagem
												</button>
											</div>
										</logic:equal>

									</div>
								</div>

								<div id="itens" class="tab-pane fade" style="margin-top: 20px;">
									<div class="row">
										<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-4">
											<label>Código</label>
											<html:text styleClass="form-control input-sm itemAdicionar" styleId="codigo" property="itemAdicionar.centroCustoReceita.codigo" name="notaFiscalRateioForm" />
											<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
											<html:hidden styleClass="itemAdicionar" styleId="centroCustoReceitaSelecionado" property="itemAdicionar.centroCustoReceita.id" name="notaFiscalRateioForm" />
										</div>
										<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-8">
											<label>Centro de Custo</label>
											<html:text styleClass="form-control input-sm itemAdicionar bloqueado" styleId="descricaoCentroCustoReceita" property="itemAdicionar.centroCustoReceita.descricao" name="notaFiscalRateioForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<label>Descrição Uso</label>
											<html:text styleClass="form-control input-sm itemAdicionar" styleId="descricao" property="itemAdicionar.descricao" name="notaFiscalRateioForm" />
										</div>
										<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-4">
											<label>R$ Valor</label>
											<html:text styleClass="form-control input-sm itemAdicionar dinheiro" styleId="valor" property="itemAdicionar.valor" name="notaFiscalRateioForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-4">
											<label>Destino</label>
											<html:select styleClass="form-control input-sm itemAdicionar" styleId="tipo" property="itemAdicionar.tipo" name="notaFiscalRateioForm">
												<html:option value="">Selecione...</html:option>
												<html:option value="Safra/Setor">Safra/Setor</html:option>
												<html:option value="Tudo">Tudo</html:option>
												<html:option value="Cultura">Cultura</html:option>
											</html:select>
										</div>

										<div class="form-group text-left col-xs-12 col-sm-12 col-md-12 col-lg-4">
											<button class="btn btn-success btn-block" type="button" id="btnAdicionarCentroCustoReceita" style="margin-top: 21px; background-color: #407140;">
												<i class="fa fa-plus">Adicionar</i>
											</button>
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-3" id="divSafra">
											<label>Safra</label>
											<html:text styleClass="form-control input-sm itemAdicionar" styleId="safra" property="itemAdicionar.safra.nome" name="notaFiscalRateioForm" />
											<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
											<html:hidden styleClass="itemAdicionar" styleId="safraSelecionada" property="itemAdicionar.safra.id" name="notaFiscalRateioForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-3" id="culturaAjax">
											<jsp:include page="ajax/cultura-ajax.jsp"></jsp:include>
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-3" id="setorAjax">
											<jsp:include page="ajax/setor-ajax.jsp"></jsp:include>
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12" id="id-itens">
											<jsp:include page="ajax/painel-itens-ajax.jsp"></jsp:include>
										</div>
									</div>

								</div>

								<div id="financeiro" class="tab-pane fade" style="margin-top: 20px;">
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
		/* Foco inicial */
		if (($('#numero').val() != null && $('#numero').val() != '') || ($('#numeroRecibo').val() != null && $('#numeroRecibo').val() != '')) {

			if ($('#codigo').val() == null || $('#codigo').val() == '') {
				$("#codigo").focus();
			} else {
				$("#descricao").focus();
			}
		} else {
			$("#tipoNF").focus();
		}

		/* Setando NOTNULL nos campos*/
		$("#numero").addClass("obrigatorio");
		$("#data").addClass("obrigatorio");
		$("#vencimentoPrimeiraParcela").addClass("obrigatorio");
		$("#tipoNF").addClass("obrigatorio");
		$("#tipoPagamento").addClass("obrigatorio");
		$("#nomeFornecedor").addClass("obrigatorio");

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#numero").attr("maxlength", 20);
		$("#numeroRecibo").attr("maxlength", 20);
		$("#valor").attr("maxlength", 15);
		$("#descricao").attr("maxlength", 1000);

		$("#quantidadeParcelas").attr("max", 999);
		$("#quantidadeParcelas").attr("min", 1);
		$("#quantidadeParcelas").attr("type", "number");

		/* Setando os placeholder dos campos*/
		$("#numero").attr("placeholder", "Nº Nota Fiscal");
		$("#numeroRecibo").attr("placeholder", "Nº Recibo");
		$("#nomeFornecedor").attr("placeholder", "Fornecedor");

		$('#numero').keyup(function() {
			gerenciarObrigatoriedadeNumerosReciboAndNota('numero');
		});

		$('#numeroRecibo').keyup(function() {
			gerenciarObrigatoriedadeNumerosReciboAndNota('numeroRecibo');
		});
		
		$('#vencimentoPrimeiraParcela').keyup(function() {			
			gerenciarFormaPagamento();
		});
		function gerenciarFormaPagamento(){
			//Convertendo em novas datas     
			var dataVencimento = new Date($('#vencimentoPrimeiraParcela').val().replace(/(\d{2})\/(\d{2})\/(\d{4})/, '$2/$1/$3'));
			dataVencimento.setHours(0,0,0,0);
			
			var hoje = new Date();
			hoje.setHours(0,0,0,0);
			
			//Exemplo de comparação de datas    
			if (dataVencimento < hoje || dataVencimento > hoje) {
				$('#divFormaPagamento').css("display", "none");
			} else {
				$('#divFormaPagamento').css("display", "block");
			}
			
			if ( $('#situacaoNotaFiscalRateio').text() == 'Quitada') {
				$('#divFormaPagamento').css("display", "block");
			}
		}
		
		function gerenciarObrigatoriedadeNumerosReciboAndNota(campo) {
			var numeroNF = $("#numero").val();
			var numeroRecibo = $("#numeroRecibo").val();

			if ((numeroNF != null && numeroNF != '')) {
				$("#numero").addClass("obrigatorio");
				$("#numero").attr("required", "required");

				$("#numeroRecibo").removeClass("obrigatorio");
				$("#numeroRecibo").removeAttr("required");
				$("#numeroRecibo").val(null);
			}

			if ((numeroRecibo != null && numeroRecibo != '')) {
				$("#numero").removeClass("obrigatorio");
				$("#numero").removeAttr("required");
				$("#numero").val(null);

				$("#numeroRecibo").addClass("obrigatorio");
				$("#numeroRecibo").attr("required", "required");
			}

			if ((numeroNF == null || numeroNF == '') && (numeroRecibo == null || numeroRecibo == '')) {
				$("#numero").addClass("obrigatorio");
				$("#numero").attr("required", "required");
				$("#numeroRecibo").addClass("obrigatorio");
				$("#numeroRecibo").attr("required", "required");
			}
		}

		$('#quantidadeParcelas').keyup(function() {
			gerenciarCamposTipoFinanceiro();
		});
		var quantidadeParcelasOld = null;
		$("#quantidadeParcelas").focusin(function() {
			quantidadeParcelasOld = $("#quantidadeParcelas").val();
		});
		$("#quantidadeParcelas").focusout(function() {

			if ($("#quantidadeParcelas").val() == quantidadeParcelasOld) {
				return;
			}

			if ('${notaFiscalRateioForm.notaFiscalRateio.id}' != null && '${notaFiscalRateioForm.notaFiscalRateio.id}' != '') {
				BootstrapDialog.show({
					size : BootstrapDialog.SIZE_LARGE,
					title : 'Atenção...',
					message : "Atenção, alterando a Quantidade de Parcelas, as parcelas já existentes serão substituidas por novas com valores corrigidos!",
					closable : true,
					type : BootstrapDialog.TYPE_DANGER,
					buttons : [ {
						label : 'Confirmar',
						action : function(dialogRef) {
							gerenciarCamposTipoFinanceiro();
							dialogRef.close();
						}
					}, {
						label : 'Cancelar quantidade informada',
						action : function(dialogRef) {
							$("#quantidadeParcelas").val(quantidadeParcelasOld);
							gerenciarCamposTipoFinanceiro();
							dialogRef.close();
						}
					} ]
				});
			}
			//recarregarObrigatorios();
		});
		
		var vencimentoPrimeiraParcelaOld = null;
		$("#vencimentoPrimeiraParcela").focusin(function() {
			vencimentoPrimeiraParcelaOld = $("#vencimentoPrimeiraParcela").val();
		});
		$("#vencimentoPrimeiraParcela").focusout(function() {

			if ($("#vencimentoPrimeiraParcela").val() == vencimentoPrimeiraParcelaOld) {
				return;
			}

			if ('${notaFiscalRateioForm.notaFiscalRateio.id}' != null && '${notaFiscalRateioForm.notaFiscalRateio.id}' != '') {
				BootstrapDialog.show({
					size : BootstrapDialog.SIZE_LARGE,
					title : 'Atenção...',
					message : "Atenção, alterando o Vencimento, as parcelas já existentes serão substituidas por novas com valores corrigidos!",
					closable : true,
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
			if ('${notaFiscalRateioForm.notaFiscalRateio.id}' != null && '${notaFiscalRateioForm.notaFiscalRateio.id}' != '') {
				BootstrapDialog.show({
					size : BootstrapDialog.SIZE_LARGE,
					title : 'Atenção...',
					message : "Alterando o Valor da Entrada, as parcelas já existentes serão substituidas por novas com valores corrigidos!",
					closable : true,
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
			if ('${notaFiscalRateioForm.notaFiscalRateio.id}' != null && '${notaFiscalRateioForm.notaFiscalRateio.id}' != '') {
				BootstrapDialog.show({
					size : BootstrapDialog.SIZE_LARGE,
					title : 'Atenção...',
					message : "Alterando a Data de Emissão, as parcelas já existentes serão substituidas por novas com datas corrigidas!",
					closable : true,
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
			if ('${notaFiscalRateioForm.notaFiscalRateio.id}' != null && '${notaFiscalRateioForm.notaFiscalRateio.id}' != '') {
				BootstrapDialog.show({
					size : BootstrapDialog.SIZE_LARGE,
					title : 'Atenção...',
					message : "Alterando o campo À Vista/À Prazo, fará com que as parcelas já existentes sejão substituidas por novas!",
					closable : true,
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

		$('#nomeFornecedor').keyup(function() {
			if ($('#nomeFornecedor').val() == '') {
				$('#fornecedorSelecionado').val(null);
			}
		});

		$('#nomeFornecedor').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'notaFiscalRateio.fornecedor.nome',
			serviceUrl : '${contextPath}/restrito/sistema/notaFiscalRateio.src?method=selecionarFornecedorAutoComplete',
			onSelect : function(suggestion) {
				$('#fornecedorSelecionado').val(suggestion.data);

				$('#nomeFornecedor').val($('#nomeFornecedor').val().split('-')[0]);
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#fornecedorSelecionado').val(null);
				}
			}
		});

		$('#codigo').keyup(function() {
			if ($('#codigo').val() == '') {
				$('.itemAdicionar').val(null);
			}
			gerenciarCamposObrigatoriosItemAdicionar()
		});
		$('#codigo').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'itemAdicionar.centroCustoReceita.codigo',
			params : {
				'notaFiscalRateio.tipo' : function() {
					return $('#tipoNF').val();
				}
			},
			serviceUrl : '${contextPath}/restrito/sistema/notaFiscalRateio.src?method=selecionarCentroCustoReceitaAutoComplete',
			onSelect : function(suggestion) {
				$('#centroCustoReceitaSelecionado').val(suggestion.data);

				$('#descricaoCentroCustoReceita').val(suggestion.descricao);

				//executarComSubmit('form_notaFiscalRateio', 'selecionarCentroCustoReceitaById');
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('.itemAdicionar').val(null);
				}
			}
		});

		$('#btnAdicionarCentroCustoReceita').click(function() {
			if ('${notaFiscalRateioForm.notaFiscalRateio.id}' != null && '${notaFiscalRateioForm.notaFiscalRateio.id}' != '') {
				BootstrapDialog.show({
					size : BootstrapDialog.SIZE_LARGE,
					title : 'Atenção...',
					message : "Alterando o Valor Total da NF, TODAS as parcelas já existentes serão substituidas por novas com valores corrigidos!",
					closable : true,
					type : BootstrapDialog.TYPE_DANGER,
					buttons : [ {
						label : 'Confirmar',
						action : function(dialogRef) {

							var theForm = $('form[name=notaFiscalRateioForm]');
							var params = theForm.serialize();
							var actionURL = theForm.attr('action') + '?method=adicionarItem';

							$.ajax({
								type : 'POST',
								url : actionURL,
								data : params,
								success : function(data, textStatus, XMLHttpRequest) {

									$('#id-itens').html(data);
									$('.itemAdicionar').val(null);
									$("#codigo").focus();

									gerenciarCamposSafraSetorCultura();
									gerenciarCamposObrigatoriosItemAdicionar();
								},
								error : function(XMLHttpRequest, textStatus, errorThrown) {
									alert(textStatus);
								}
							});

							dialogRef.close();
						}
					}, {
						label : 'Cancelar Adição',
						action : function(dialogRef) {

							dialogRef.close();
						}
					} ]
				});

			} else {
				var theForm = $('form[name=notaFiscalRateioForm]');
				var params = theForm.serialize();
				var actionURL = theForm.attr('action') + '?method=adicionarItem';

				$.ajax({
					type : 'POST',
					url : actionURL,
					data : params,
					success : function(data, textStatus, XMLHttpRequest) {

						$('#id-itens').html(data);
						$('.itemAdicionar').val(null);
						$("#codigo").focus();

						gerenciarCamposSafraSetorCultura();
						gerenciarCamposObrigatoriosItemAdicionar()
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert(textStatus);
					}
				});
			}
		});

		//function gerarParcelas() {
		/* $('#btnGerarParcelas').click(function() {

			abrirModalProcessando();

			var theForm = $("form[name=notaFiscalRateioForm]");
			var params = theForm.serialize();
			var actionURL = theForm.attr("action") + "?method=gerarParcelas";

			$.ajax({
				type : "POST",
				url : actionURL,
				data : params,
				success : function(data, textStatus, XMLHttpRequest) {

					fecharModalProcessando();

					// we have the response
					$('#id_parcelas_ajax').html(data);

					$('#tabsNotaFiscalRateio a[href="#financeiro"]').tab('show');
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(textStatus + "-" + XMLHttpRequest + "-" + errorThrown);
				}
			});
		}); */
		//}
		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_notaFiscalRateio").attr("autocomplete", "off");

		$('#form_notaFiscalRateio').on('submit', function(e) {
			abrirModalProcessando();
		});

		$('#inserir').click(function() {
			executar('form_notaFiscalRateio', 'inserir');
		});

		$('#alterar').click(function() {
			executar('form_notaFiscalRateio', 'alterar');
		});

		$('#limpar').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_notaFiscalRateio', 'limpar');
		});

		$('#listagem').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_notaFiscalRateio', 'abrirListagem');
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

		$('#tipo').change(function() {
			gerenciarCamposSafraSetorCultura();
		});

		$('#safra').keyup(function() {
			if ($('#safra').val() == '') {
				$('#safraSelecionada').val(null);
				$('#setorSelecionado').val(null);
				$('#setor').val(null);
				$('#cultura').val(null);

				selecionarSafra();
			}
		});
		$('#safra').autocomplete({
			minChars : 2,
			paramName : 'notaFiscalRateio.safra.nome',
			serviceUrl : '${contextPath}/restrito/sistema/notaFiscalRateio.src?method=selecionarSafraAutoComplete',
			onSelect : function(suggestion) {
				$('#safraSelecionada').val(suggestion.data);

				selecionarSafra();
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#safraSelecionada').val(null);

					$('#setor').val(null);
					$('#cultura').val(null);

					selecionarSafra();
				}
			}
		});

		$('#tipoNF').change(function() {
			$('.itemAdicionar').val(null);
			//gerenciarExibicaoPainelCentrosCusto($('#tipoNF').val())
			$("#codigo").focus();
		});

		$('#tipoPagamento').change(function() {
			gerenciarCamposTipoFinanceiro();
			gerenciarFormaPagamento();
			//gerenciarCamposEntrada();
		});

		gerenciarCamposTipoFinanceiro();
		gerenciarObrigatoriedadeNumerosReciboAndNota(null);
		gerenciarCamposSafraSetorCultura();
		//gerenciarExibicaoPainelCentrosCusto($('#tipoNF').val())
		//gerenciarCamposEntrada();
		gerenciarFormaPagamento();

		gerenciarCamposObrigatoriosItemAdicionar();

		if ($('#idNFRateio').val() != null && $('#idNFRateio').val() != '') {
			$('.inalteraveis').addClass("bloqueado");
			$('.btnRemover').css("display", "none");

			recarregarObrigatorios();
		} else {
			$('.inalteraveis').removeClass("bloqueado");
			recarregarObrigatorios();
		}
	});

	function gerenciarCamposTipoFinanceiro() {
		if ($('#tipoPagamento').val() != null && $('#tipoPagamento').val() != '') {

			if ($('#tipoPagamento').val() == 'À vista') {

				$("#lblVencimentoPrimeiraParcela").text('Data Quitação');

				/* Adicionando os campos*/
				$('#divVencimentoPrimeiraParcela').css("display", "block");

				/* Removendo os campos*/
				$('#divQuantidadeParcelas').css("display", "none");
				$('#divValorEntrada').css("display", "none");
				$('#divFormaPagamentoEntrada').css("display", "none");
				$("#formaPagamentoEntrada").removeClass("obrigatorio");
				$("#valorEntrada").removeClass("obrigatorio");

				//$("#formaPagamento").addClass("obrigatorio");
				$("#quantidadeParcelas").removeClass("obrigatorio");

				//$("#formaPagamento").focus();

			} else if ($('#tipoPagamento').val() == 'À prazo') {

				$("#lblVencimentoPrimeiraParcela").text('Venc.1ª Parcela');

				/* Adicionando os campos*/
				$('#divVencimentoPrimeiraParcela').css("display", "block");
				$('#divQuantidadeParcelas').css("display", "block");
				if ($('#quantidadeParcelas').val() > 1) {
					$('#divValorEntrada').css("display", "block");
					$('#divFormaPagamentoEntrada').css("display", "block");
					$('#divFormaPagamento').css("display", "none");
				} else {
					$('#divValorEntrada').css("display", "none");
					$('#divFormaPagamentoEntrada').css("display", "none");
					$('#divFormaPagamento').css("display", "block");
				}

				//$("#quantidadeParcelas").focus();

				$("#quantidadeParcelas").addClass("obrigatorio");
				//$("#formaPagamento").removeClass("obrigatorio");
			}

		} else {
			$('#divQuantidadeParcelas').css("display", "none");
			$('#divValorEntrada').css("display", "none");
			$('#divFormaPagamentoEntrada').css("display", "none");
			$('#divVencimentoPrimeiraParcela').css("display", "none");
		}

		recarregarObrigatorios();
	}

	/* function gerenciarExibicaoPainelCentrosCusto(tipo) {
		if (tipo == null || tipo == '') {
			$('#painelCentroCusto').css("display", "none");
		} else {
			$('#painelCentroCusto').css("display", "block");
		}
	} */

	function gerenciarCamposObrigatoriosItemAdicionar() {
		if ($('#codigo').val() == null || $('#codigo').val() == '') {

			$("#codigo").removeClass("obrigatorio");
			$("#descricao").removeClass("obrigatorio");
			$("#valor").removeClass("obrigatorio");
			$("#tipo").removeClass("obrigatorio");

			$("#safra").removeClass("obrigatorio");
			$("#cultura").removeClass("obrigatorio");
			$("#setor").removeClass("obrigatorio");
		} else {

			$("#codigo").addClass("obrigatorio");
			$("#descricao").addClass("obrigatorio");
			$("#valor").addClass("obrigatorio");
			$("#tipo").addClass("obrigatorio");

			$("#safra").addClass("obrigatorio");
			$("#cultura").addClass("obrigatorio");
			$("#setor").addClass("obrigatorio");
		}
		recarregarObrigatorios();
	}

	function gerenciarCamposSafraSetorCultura() {
		if ($('#tipo').val() == '') {
			$("#safra").removeClass("obrigatorio");
			$("#cultura").removeClass("obrigatorio");
			$("#setor").removeClass("obrigatorio");

			$('#divSafra').css("display", "none");
			$('#culturaAjax').css("display", "none");
			$('#setorAjax').css("display", "none");

		} else if ($('#tipo').val() == 'Safra/Setor') {
			$('#divSafra').css("display", "block");
			$("#safra").addClass("obrigatorio");
			$('#setorAjax').css("display", "block");
			$("#setor").addClass("obrigatorio");

			$("#cultura").removeClass("obrigatorio");
			$('#culturaAjax').css("display", "none");

		} else if ($('#tipo').val() == 'Tudo') {
			$('#divSafra').css("display", "block");
			$("#safra").addClass("obrigatorio");

			$("#setor").removeClass("obrigatorio");
			$('#setorAjax').css("display", "none");

			$("#cultura").removeClass("obrigatorio");
			$('#culturaAjax').css("display", "none");

		} else if ($('#tipo').val() == 'Cultura') {
			$('#divSafra').css("display", "block");
			$("#safra").addClass("obrigatorio");
			$('#culturaAjax').css("display", "block");
			$("#cultura").addClass("obrigatorio");

			$("#setor").removeClass("obrigatorio");
			$('#setorAjax').css("display", "none");
		}

		/* $("#safra").val(null);
		$("#safraSelecionada").val(null);
		selecionarSafra(); */

		recarregarObrigatorios();
	}

	function selecionarSafra() {
		var theForm = $('form[name=notaFiscalRateioForm]');
		var params = theForm.serialize();
		var actionURL = theForm.attr('action') + '?method=selecionarSafra';

		$.ajax({
			type : 'POST',
			url : actionURL,
			data : params,
			success : function(data, textStatus, XMLHttpRequest) {

				if ($('#tipo').val() == 'Safra/Setor') {
					$('#setorAjax').html(data);

					$("#setor").focus();
				} else if ($('#tipo').val() == 'Cultura') {
					$('#culturaAjax').html(data);
					$("#cultura").focus();
				}

				if ($('#safraSelecionada').val() == null || $('#safraSelecionada').val() == '') {
					$("#safra").focus();
				}

			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(textStatus);
			}
		});
	}
</script>