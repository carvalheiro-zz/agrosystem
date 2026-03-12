<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!-- CAMPOS DE CADASTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -30px; margin-bottom: -15px;">
		<h1 class="page-header cabecalho_pagina">
			<i class="fa fa-save fa-fw" style="font-size: 26px;"></i>
			Parametrização - ${parametrizacaoForm.horaAtual}
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Cadastro e Visualização dos Parametros do Sistema
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<div class="row">
	<!-- Define o tamanho geral da tela -->
	<div class="col-xs-offset-0 col-sm-offset-0 col-md-offset-0 col-lg-offset-0 col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel sombra">

			<!-- INICIO FORMULARIO -->
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_parametrizacao" action="restrito/admin/parametrizacao" method="post">
							<html:hidden property="method" value="empty" />


							<logic:equal name="parametrizacaoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.parametrizacao.acessar)">

								<div class="row">
									<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
										<i class="fa fa-key"></i>
										<label>Chave de Ativação: ${parametrizacaoForm.parametrizacao.chaveAtivacao}fk - Validade: ${parametrizacaoForm.parametrizacao.diasRestantesChaveValida} dias.</label>
										<i class="fa fa-key"></i>

										<logic:notPresent property="parametrizacao.chaveAtivacao" name="parametrizacaoForm">
											<html:text styleClass="form-control input-sm text-center" styleId="pathRaizUpload" property="parametrizacao.chaveAtivacao" name="parametrizacaoForm" />
										</logic:notPresent>
									</div>
									<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<label>Empresa</label>
										<logic:equal value="true" name="usuarioSessaoPOJO" property="isADM" scope="session">
											<html:select styleClass="form-control input-sm" styleId="empresa" property="parametrizacao.empresa.id" name="parametrizacaoForm">
												<html:optionsCollection name="parametrizacaoForm" property="comboEmpresas" label="label" value="value" />
											</html:select>
										</logic:equal>
										<logic:equal value="false" name="usuarioSessaoPOJO" property="isADM" scope="session">
											<html:text styleClass="form-control input-sm bloqueado" styleId="empresa" property="parametrizacao.empresa.razaoSocial" name="parametrizacaoForm" />
										</logic:equal>
									</div>

									<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
										<i class="fa fa-sun-o"></i>
										<label>Horario de Verão?</label>
										<i class="fa fa-moon-o"></i>
										<html:select styleClass="form-control input-sm" styleId="horarioVerao" property="parametrizacao.horarioVerao" name="parametrizacaoForm">
											<html:option value="true">Sim</html:option>
											<html:option value="false">Não</html:option>
										</html:select>
									</div>
								</div>

							</logic:equal>

							<div class="row">
								<logic:equal name="parametrizacaoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.parametrizacao.acessar)">
									<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<i class="fa fa-database"></i>
										<label>Local pasta BKP de Banco de Dados</label>
										<html:text styleClass="form-control input-sm" styleId="pathBackupBD" property="parametrizacao.pathBackupBD" name="parametrizacaoForm" />
									</div>

									<div class="form-group col-xs-12 col-sm-12 col-md-5 col-lg-5">
										<i class="fa fa-fa-terminal"></i>
										<label>Context Path MySQL</label>
										<html:text styleClass="form-control input-sm" styleId="pathMySQL" property="parametrizacao.pathMySQL" name="parametrizacaoForm" />

										<small id="passwordHelpBlock" class="form-text text-muted"> Para restaurar: mysql -u root -p gymsolutions < c:/temp/gymsolutions.sql </small>
									</div>
									<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
										<i class="fa fa-user"></i>
										<label>Usuário BD</label>
										<html:text styleClass="form-control input-sm" styleId="bdUser" property="parametrizacao.bdUser" name="parametrizacaoForm" />
									</div>
									<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
										<i class="fa fa-eye-slash"></i>
										<label>Senha BD</label>
										<html:text styleClass="form-control input-sm senha" styleId="bdPassword" property="parametrizacao.bdPassword" name="parametrizacaoForm" />
									</div>
								</logic:equal>
								<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3" style="margin-top: 24px;">
									<button type="button" id="ativarBackupBancoDados" class="btn btn-info btn-sm btn-block cor-sistema">
										<i class="fa fa-upload"></i>
										Backup BD - ${ parametrizacaoForm.parametrizacao.empresa.horaFechamento }
									</button>
								</div>
							</div>

							<logic:equal name="parametrizacaoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.parametrizacao.acessar)">
								<div class="row">
									<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
										<i class="fa fa-archive"></i>
										<label>Local pasta para upload de arquivos</label>
										<html:text styleClass="form-control input-sm" styleId="pathUploadFiles" property="parametrizacao.pathUploadFiles" name="parametrizacaoForm" />
									</div>
									<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
										<i class="fa fa-archive"></i>
										<label>Local pasta BKP para upload de arquivos</label>
										<html:text styleClass="form-control input-sm" styleId="pathUploadBKPFiles" property="parametrizacao.pathUploadBKPFiles" name="parametrizacaoForm" />
									</div>
									<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<i class="fa fa-terminal"></i>
										<label>Context Path servidor</label>
										<html:text styleClass="form-control input-sm" styleId="urlContextPath" property="parametrizacao.urlContextPath" name="parametrizacaoForm" />
									</div>
									<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
										<i class="fa fa-envelope-o"></i>
										<label>Email do remetente</label>
										<html:text styleClass="form-control input-sm" styleId="emailRemetente" property="parametrizacao.emailRemetente" name="parametrizacaoForm" />
									</div>
									<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
										<i class="fa fa-envelope"></i>
										<label>Email do servidor</label>
										<html:text styleClass="form-control input-sm" styleId="emailServidor" property="parametrizacao.emailServidor" name="parametrizacaoForm" />
									</div>
									<div class="form-group col-xs-12 col-sm-4 col-md-4 col-lg-4">
										<i class="fa fa-key"></i>
										<label>Senha do servidor</label>
										<html:text styleClass="form-control input-sm" styleId="senhaServidor" property="parametrizacao.senhaServidor" name="parametrizacaoForm" />
									</div>
									<div class="form-group col-xs-12 col-sm-4 col-md-4 col-lg-4">
										<i class="fa fa-sitemap"></i>
										<label>Porta do servidor</label>
										<html:text styleClass="form-control input-sm" styleId="portaServidor" property="parametrizacao.portaServidor" name="parametrizacaoForm" />
									</div>
									<div class="form-group col-xs-12 col-sm-4 col-md-4 col-lg-4">
										<i class="fa fa-exchange"></i>
										<label>SMTP do servidor</label>
										<html:text styleClass="form-control input-sm" styleId="smtpServidor" property="parametrizacao.smtpServidor" name="parametrizacaoForm" />
									</div>
								</div>

								<div class="row" style="margin-top: 15px;">
									<!-- BOTOES -->
									<%-- <logic:notPresent property="parametrizacao.id" name="parametrizacaoForm"> --%>
									<logic:equal name="parametrizacaoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.parametrizacao.inserir)">
										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-3">
											<button type="submit" id="confirmar" class="btn btn-success btn-sm btn-block cor-sistema">
												<i class="fa fa-save"></i>
												Confirmar
											</button>
										</div>
									</logic:equal>
									<%-- </logic:notPresent> --%>

									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-5">
										<button type="button" id="carregarUploadServidor" class="btn btn-success btn-sm btn-block cor-sistema">
											<i class="fa fa-upload"></i>
											Upload arquivos para servidor
										</button>
									</div>


									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-2">
										<button type="button" id="sair" class="btn btn-success btn-sm btn-block cor-sistema">
											<i class="fa fa-sign-out"></i>
											Sair
										</button>
									</div>
								</div>
							</logic:equal>

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
		$("#pathBackupBD").focus();

		/* Setando NOTNULL nos campos*/
		$(".form-control").addClass("obrigatorio");

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#pathBackupBD").attr("maxlength", 255);
		$("#pathUploadFiles").attr("maxlength", 255);
		$("#pathUploadBKPFiles").attr("maxlength", 255);
		$("#urlContextPath").attr("maxlength", 255);
		$("#pathMySQL").attr("maxlength", 255);
		$("#emailRemetente").attr("maxlength", 255);
		$("#emailServidor").attr("maxlength", 255);
		$("#senhaServidor").attr("maxlength", 20);
		$("#portaServidor").attr("maxlength", 5);
		$("#smtpServidor").attr("maxlength", 50);
		$("#bdUser").attr("maxlength", 10);
		$("#bdPassword").attr("maxlength", 10);

		/* Setando os placeholder dos campos*/
		$("#pathBackupBD").attr("placeholder", "Caminho da pasta onde ficará os Backups de Banco de Dados.");
		$("#pathUploadFiles").attr("placeholder", "Caminho da pasta onde ficará os arquivos de upload.");
		$("#pathUploadBKPFiles").attr("placeholder", "Caminho da pasta onde ficará os Backups dos arquivos de upload.");
		$("#urlContextPath").attr("placeholder", "Caminho da pasta onde é publicado o arquivo .WAR.");
		$("#pathMySQL").attr("placeholder", "Caminho da pasta BIN do MYSQL");
		$("#emailRemetente").attr("placeholder", "Email remetente de envio.");
		$("#emailServidor").attr("placeholder", "Email do servidor de envio.");
		$("#senhaServidor").attr("placeholder", "Senha do Email do servidor de envio.");
		$("#portaServidor").attr("placeholder", "Porta do Email do servidor de envio.");
		$("#smtpServidor").attr("placeholder", "SMTP do Email do servidor de envio.");
		$("#bdUser").attr("placeholder", "root.");
		$("#bdPassword").attr("placeholder", "root.");

		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_parametrizacao").attr("autocomplete", "off");

		$('#form_parametrizacao').on('submit', function(e) {
			abrirModalProcessando();
		});

		$('#confirmar').click(function() {
			executar('form_parametrizacao', 'confirmar');
		});

		$('#carregarUploadServidor').click(function() {
			executarComSubmit('form_parametrizacao', 'carregarUploadServidor');
		});

		$('#sair').click(function() {
			executarComSubmit('form_parametrizacao', 'sair');
		});

		$('#ativarBackupBancoDados').click(function() {
			executarComSubmit('form_parametrizacao', 'ativarBackupBancoDados');
		});

		$('#empresa').change(function() {
			/* var actionURL = '${contextPath}/restrito/admin/parametrizacao.src?method=carregarParametrosEmpresaSelecionada&parametrizacao.empresa.id=' + $('#empresa').val();
			$.ajax({
				type : "POST",
				url : actionURL,
				success : function(data, textStatus, XMLHttpRequest) {
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(textStatus);
				}
			}); */

			executarComSubmit('form_parametrizacao', 'carregarParametrosEmpresaSelecionada');

		});
	});
</script>


