<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!-- CAMPOS DE CADASTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -30px; margin-bottom: -15px;">
		<h1 class="page-header cabecalho_pagina">
			<i class="fa fa-save fa-fw" style="font-size: 26px;"></i>
			Ativação do Software
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Ativação da Licença do Software
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<div class="row">
	<!-- Define o tamanho geral da tela -->
	<div class="col-xs-offset-0 col-sm-offset-1 col-md-offset-1 col-lg-offset-2 col-xs-12 col-sm-10 col-md-10 col-lg-8">
		<div class="panel sombra">

			<!-- INICIO FORMULARIO -->
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_ativacao" action="restrito/admin/ativacao" method="post">
							<html:hidden property="method" value="empty" />
							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<label>Empresa</label>
									<logic:equal value="true" name="usuarioSessaoPOJO" property="isADM" scope="session">
										<html:select styleClass="form-control input-sm" styleId="empresa" property="parametrizacao.empresa.id" name="ativacaoForm">
											<html:optionsCollection name="ativacaoForm" property="comboEmpresas" label="label" value="value" />
										</html:select>
									</logic:equal>
									<logic:equal value="false" name="usuarioSessaoPOJO" property="isADM" scope="session">
										<html:text styleClass="form-control input-sm bloqueado" styleId="empresa" property="parametrizacao.empresa.razaoSocial" name="ativacaoForm" />
									</logic:equal>
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<i class="fa fa-key"></i>
									<label>Chave de Ativação</label>
									<i class="fa fa-key"></i>
									<html:text styleClass="form-control input-sm text-center" styleId="novaChaveAtivacao" property="novaChaveAtivacao" name="ativacaoForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<div class="alert alert-success" style="margin-bottom: 0px" id="alert-situacaoCompra">

										<label id="infoAtivacao">Chave de Ativação: ${ativacaoForm.parametrizacao.chaveAtivacao}fk - Validade: ${ativacaoForm.parametrizacao.diasRestantesChaveValida}
											dias.</label>
										<i class="fa fa-key"></i>

									</div>
								</div>

							</div>

							<div class="row" style="margin-top: 15px;">
								<!-- BOTOES -->
								<logic:equal name="ativacaoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.ativacao.alterar)">
									<div id="divConfirmar" class="col-xs-12 col-sm-12 col-md-12 col-lg-3">
										<button type="submit" id="confirmar" class="btn btn-success btn-sm btn-block cor-sistema">
											<i class="fa fa-save"></i>
											Confirmar
										</button>
									</div>
								</logic:equal>


								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-2">
									<button type="button" id="sair" class="btn btn-success btn-sm btn-block cor-sistema">
										<i class="fa fa-sign-out"></i>
										Sair
									</button>
								</div>
								
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-4">
									<button type="button" id="gerarChaveAcesso" class="btn btn-info btn-sm btn-block cor-sistema">
										<i class="fa fa-sign-out"></i>
										Gerar Chave de Acesso
									</button>
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
		$("#novaChaveAtivacao").focus();

		/* Setando NOTNULL nos campos*/
		$(".form-control").addClass("obrigatorio");

		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_ativacao").attr("autocomplete", "off");

		$('#form_ativacao').on('submit', function(e) {
			abrirModalProcessando();
		});

		$('#confirmar').click(function() {
			executar('form_ativacao', 'confirmar');
		});

		$('#sair').click(function() {
			executarComSubmit('form_ativacao', 'sair');
		});

		$('#empresa').change(function() {
			executarComSubmit('form_ativacao', 'carregarParametrosEmpresaSelecionada');
		});
		
		$('#novaChaveAtivacao').keyup(function() {
			gerenciarExibicaoBotaoConfirmar();
		});
		
		gerenciarExibicaoBotaoConfirmar();
		
		$('#gerarChaveAcesso').click(function() {
			BootstrapDialog.show({
				size : BootstrapDialog.SIZE_LARGE,
				title : 'Chave de Acesso Gerada',
				message : 	'<div class="row">' + 
								'<div class="form-group col-xs-12">' + 
									'<label>Quantidade de Dias</label>' +
									'<html:text styleClass="form-control input-sm text-center" styleId="quantidadeDiasModal" property="quantidadeDias" name="ativacaoForm" />' + 
								'</div>' +
								'<div class="form-group col-xs-12">' + 
									'<label>Senha ADM</label>' +
									'<html:password styleClass="form-control input-sm text-center senha" styleId="senhaADMModal" property="senhaADM" name="ativacaoForm" />' + 
								'</div>' +
								'<div class="form-group col-xs-12">' + 
									'<label>Chave Gerada</label>' +
									'<input type="text" class="form-control input-sm text-center" readonly="readonly" id="chaveGeradaModal" />' + 
								'</div>' +'<div class="form-group col-xs-12">' + 
									'<label>Data de Vencimento</label>' +
									'<input type="text" class="form-control input-sm text-center" readonly="readonly" id="dataValidadeModal" />' + 
								'</div>' +
				 			'</div>',
				closable : false,
				onshown : function(dialogRef) {
					$('#quantidadeDiasModal').focus();
					$('#quantidadeDiasModal').val(null);
					$('#senhaADMModal').val(null);
					$('#chaveGeradaModal').val(null);
					$('#dataValidadeModal').val(null);
				},
				type : BootstrapDialog.TYPE_DANGER,
				buttons : [ {
					label : 'Gerar',
					action : function(dialogRef) {
						
						var theForm = $('form[name=ativacaoForm]');
						var params = theForm.serialize();
						var actionURL = theForm.attr('action') + '?method=gerarChaveAcesso&quantidadeDias='+$('#quantidadeDiasModal').val()+'&senhaADM='+$('#senhaADMModal').val();
						$.ajax({
							type : 'POST',
							url : actionURL,
							data : params,
							success : function(data, textStatus, XMLHttpRequest) {					
								$('#chaveGeradaModal').val(data.chaveGerada);
								$('#dataValidadeModal').val(data.dataValidade);									
							},
							error : function(XMLHttpRequest, textStatus, errorThrown) {
								alert(textStatus);
							}
						});
						
						//dialogRef.close();
					}
				},
				{
					label : 'Fechar',
					action : function(dialogRef) {			
						
						$('#quantidadeDiasModal').val(null);
						$('#senhaADMModal').val(null);
						$('#chaveGeradaModal').val(null);
						$('#dataValidadeModal').val(null);	
						
						dialogRef.close();
					}
				}]
			});
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		});
	});
	
	function gerenciarExibicaoBotaoConfirmar(){
		if( $('#novaChaveAtivacao').val() != null && $('#novaChaveAtivacao').val() != ''){
			$('#divConfirmar').css('display','block');
		}else{
			$('#divConfirmar').css('display','none');
		}
	}
</script>


