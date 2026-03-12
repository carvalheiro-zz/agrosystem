<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<jsp:include page="../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<div class="row">
	<!-- Define o tamanho geral da notaFiscalCupom -->
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel sombra">

			<!-- INICIO FORMULARIO -->
			<div class="panel-body">
				<logic:present property="notaFiscalCupom.id" name="notaFiscalCupomForm">
					<div class="row">
						<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px;">
							<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
								<i class="fa fa-user-plus"></i>
								&nbsp;${notaFiscalCupomForm.notaFiscalCupom.nomeUsuarioCriacao } 
							</div>
						</div>
						<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
							<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
								<i class="fa fa-calendar-plus-o"></i>
								&nbsp;${notaFiscalCupomForm.notaFiscalCupom.dataOcorrenciaCriacao}
							</div>
						</div>
						<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
							<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
								<i class="fa fa-refresh"></i>
								&nbsp;${notaFiscalCupomForm.notaFiscalCupom.nomeUsuarioAlteracao}
							</div>
						</div>
						<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-left: 0px;">
							<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
								<i class="fa fa-calendar-o"></i>
								&nbsp;${notaFiscalCupomForm.notaFiscalCupom.dataOcorrenciaAlteracao}
							</div>
						</div>
					</div>
				</logic:present>
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_notaFiscalCupom" action="restrito/sistema/notaFiscalCupom" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-4" style="padding-right: 3px;">
									<div class="panel panel-default" style="border-color: #909090;">
										<div class="panel-body">


											<div class="row">
												<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
													<label style="font-size: 16px;">Nº Nota Cupom</label>
													<html:text styleClass="form-control text-center input-lg" style="font-size: 25px;" styleId="numero" property="notaFiscalCupom.numero" name="notaFiscalCupomForm" />
												</div>
												<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
													<label style="font-size: 16px;">Data</label>
													<html:text styleClass="form-control text-center data input-lg" style="font-size: 20px; padding-right: 40px;" styleId="data" property="notaFiscalCupom.data"
														name="notaFiscalCupomForm" />
													<i style="margin-top: -33px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-calendar"></i>													
												</div>
											</div>

											<div class="row">
												<div class="form-group col-xs-12 col-sm-6 col-md-8 col-lg-8">
													<label>Nome Fornecedor</label>													
													<html:text styleClass="form-control input-sm" styleId="fornecedor" property="notaFiscalCupom.fornecedor.nome" name="notaFiscalCupomForm" />
													<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
													<html:hidden styleId="fornecedorSelecionado" property="notaFiscalCupom.fornecedor.id" name="notaFiscalCupomForm" />
												</div>
												<div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-4">
													<label>Tel Fornecedor</label>
													<html:text styleClass="form-control input-sm bloqueado" styleId="telefoneFornecedor" property="notaFiscalCupom.fornecedor.telefone" name="notaFiscalCupomForm" />
												</div>
												<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
													<label>Observação Fornecedor</label>
													<html:text styleClass="form-control input-sm bloqueado" styleId="observacaoFornecedor" property="notaFiscalCupom.fornecedor.observacao" name="notaFiscalCupomForm" />
												</div>
											</div>

											<div class="row">
												<!-- BOTOES -->
												<logic:notPresent property="notaFiscalCupom.id" name="notaFiscalCupomForm">
													<logic:equal name="notaFiscalCupomForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.notaFiscalCupom.inserir)">
														<div class="form-group col-xs-12 col-sm-3 col-md-4 col-lg-4" style="margin-bottom: 0px;">
															<button type="submit" id="inserir" class="btn btn-success btn-sm cor-sistema btn-block">
																<i class="fa fa-save"></i>
																Inserir
															</button>
														</div>
													</logic:equal>
												</logic:notPresent>
												<logic:present property="notaFiscalCupom.id" name="notaFiscalCupomForm">
													<logic:equal name="notaFiscalCupomForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.notaFiscalCupom.alterar)">
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

												<logic:equal name="notaFiscalCupomForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.notaFiscalCupom.filtrar)">
													<div class="form-group col-xs-12 col-sm-3 col-md-4 col-lg-4" style="margin-bottom: 0px;">
														<button type="button" id="listagem" class="btn btn-success btn-sm cor-sistema btn-block">
															<i class="fa fa-search"></i>
															Listagem
														</button>
													</div>
												</logic:equal>
											</div>
										</div>

										<div class="panel-footer" id="id_totalizador">
											<jsp:include page="ajax/totalizador-ajax.jsp"></jsp:include>
										</div>
									</div>
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-8" style="padding-left: 3px;">
									<div class="panel" style="border-color: #909090;" id="id-itens">
										<jsp:include page="itens-tabela.jsp"></jsp:include>
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
		$("#numero").focus();

		/* Setando NOTNULL nos campos*/
		$("#numero").addClass("obrigatorio");
		$("#data").addClass("obrigatorio");

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#numero").attr("maxlength", 20);		
		$("#observacaoFornecedor").attr("maxlength", 255);
		$("#enderecoFornecedor").attr("maxlength", 100);

		/* Setando os placeholder dos campos*/
		$("#numero").attr("placeholder", "Numero");
		$("#fornecedor").prop("placeholder", "Fornecedor");

		$('#fornecedor').keyup(function() {
			if ($('#fornecedor').val() == null || $('#fornecedor').val() == '') {
				$('#fornecedorSelecionado').val(null);
				$('#telefoneFornecedor').val(null);
				$('#observacaoFornecedor').val(null);
			}
		});
		$('#fornecedor').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'notaFiscalVendaDireta.fornecedor.nome',
			/* params : {
				'saidaGrao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/notaFiscalVendaDireta.src?method=selecionarFornecedorAutoComplete',
			onSelect : function(suggestion) {
				$('#fornecedorSelecionado').val(suggestion.data);
				$('#telefoneFornecedor').val(suggestion.telefone);
				$('#observacaoFornecedor').val(suggestion.observacao);
				
				$("#produto").focus();
				
				executarComSubmit('form_notaFiscalCupom', 'carregarCuponsParaBaixa');
			},
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			}, */
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#fornecedorSelecionado').val(null);
					$('#telefoneFornecedor').val(null);
					$('#observacaoFornecedor').val(null);
				}
			}
		});

		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_notaFiscalCupom").attr("autocomplete", "off");

		$('#form_notaFiscalCupom').on('submit', function(e) {
			abrirModalProcessando();
		});

		$('#inserir').click(function() {
			executar('form_notaFiscalCupom', 'inserir');
		});

		$('#alterar').click(function() {
			executar('form_notaFiscalCupom', 'alterar');
		});

		$('#limpar').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_notaFiscalCupom', 'limpar');
		});

		$('#listagem').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_notaFiscalCupom', 'abrirListagem');
		});
	});
</script>