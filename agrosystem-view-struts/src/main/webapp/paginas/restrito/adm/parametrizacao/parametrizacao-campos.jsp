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

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
									<i class="fa fa-key"></i>
									<label>Chave de Ativação: ${parametrizacaoForm.parametrizacao.chaveAtivacao}fk - Validade: ${parametrizacaoForm.parametrizacao.diasRestantesChaveValida} dias.</label>
									<i class="fa fa-key"></i>

									<logic:notPresent property="parametrizacao.chaveAtivacao" name="parametrizacaoForm">
										<html:text styleClass="form-control input-sm text-center obrigatorio" styleId="pathRaizUpload" property="parametrizacao.chaveAtivacao" name="parametrizacaoForm" />
									</logic:notPresent>
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<label>Empresa</label>
									<logic:equal value="true" name="usuarioSessaoPOJO" property="isADM" scope="session">
										<html:select styleClass="form-control input-sm obrigatorio" styleId="empresa" property="parametrizacao.empresa.id" name="parametrizacaoForm">
											<html:optionsCollection name="parametrizacaoForm" property="comboEmpresas" label="label" value="value" />
										</html:select>
									</logic:equal>
									<logic:equal value="false" name="usuarioSessaoPOJO" property="isADM" scope="session">
										<html:text styleClass="form-control input-sm bloqueado" styleId="empresa" property="parametrizacao.empresa.razaoSocial" name="parametrizacaoForm" />
									</logic:equal>
								</div>
							</div>

							<ul class="nav nav-tabs">
								<li class="active">
									<a data-toggle="tab" href="#sistema">Sistema</a>
								</li>
								<li>
									<a data-toggle="tab" href="#bancoDados">Banco de Dados</a>
								</li>
								<li>
									<a data-toggle="tab" href="#email">E-mail</a>
								</li>
							</ul>


							<div class="tab-content">
								<div id="sistema" class="tab-pane fade in active">
									<div class="panel">
										<div class="panel-body">
											<logic:equal name="parametrizacaoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.parametrizacao.acessar)">

												<div class="row">

													<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
														<i class="fa fa-sun-o"></i>
														<label>Horario de Verão?</label>
														<i class="fa fa-moon-o"></i>
														<html:select styleClass="form-control input-sm obrigatorio" styleId="horarioVerao" property="parametrizacao.horarioVerao" name="parametrizacaoForm">
															<html:option value="true">Sim</html:option>
															<html:option value="false">Não</html:option>
														</html:select>
													</div>

													<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
														<i class="fa fa-archive"></i>
														<label>Local pasta para upload de arquivos</label>
														<html:text styleClass="form-control input-sm obrigatorio" styleId="pathUploadFiles" property="parametrizacao.pathUploadFiles" name="parametrizacaoForm" />
													</div>
													<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
														<i class="fa fa-archive"></i>
														<label>Local pasta BKP para upload de arquivos</label>
														<html:text styleClass="form-control input-sm obrigatorio" styleId="pathUploadBKPFiles" property="parametrizacao.pathUploadBKPFiles" name="parametrizacaoForm" />
													</div>
													<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
														<i class="fa fa-terminal"></i>
														<label>Context Path servidor</label>
														<html:text styleClass="form-control input-sm obrigatorio" styleId="urlContextPath" property="parametrizacao.urlContextPath" name="parametrizacaoForm" />
													</div>
												</div>

											</logic:equal>
											<logic:equal name="parametrizacaoForm" value="false" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.parametrizacao.acessar)">
												<div class="row" style="margin-top: 15px;">
													<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
														<div class="alert alert-info">Usuário sem Permissão a esses dados!</div>
													</div>
												</div>
											</logic:equal>
										</div>
									</div>
								</div>

								<div id="bancoDados" class="tab-pane fade in">
									<div class="panel">
										<div class="panel-body">
											<div class="row">
												<logic:equal name="parametrizacaoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.parametrizacao.acessar)">
													<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
														<i class="fa fa-database"></i>
														<label>Local pasta BKP de Banco de Dados</label>
														<html:text styleClass="form-control input-sm obrigatorio" styleId="pathBackupBD" property="parametrizacao.pathBackupBD" name="parametrizacaoForm" />
													</div>

													<div class="form-group col-xs-12 col-sm-12 col-md-5 col-lg-5">
														<i class="fa fa-fa-terminal"></i>
														<label>Context Path MySQL</label>
														<html:text styleClass="form-control input-sm obrigatorio" styleId="pathMySQL" property="parametrizacao.pathMySQL" name="parametrizacaoForm" />

														<small id="passwordHelpBlock" class="form-text text-muted"> Para restaurar: mysql -u root -p gymsolutions < c:/temp/gymsolutions.sql </small>
													</div>
													<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
														<i class="fa fa-user"></i>
														<label>Usuário BD</label>
														<html:text styleClass="form-control input-sm obrigatorio" styleId="bdUser" property="parametrizacao.bdUser" name="parametrizacaoForm" />
													</div>
													<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
														<i class="fa fa-eye-slash"></i>
														<label>Senha BD</label>
														<html:text styleClass="form-control input-sm obrigatorio senha" styleId="bdPassword" property="parametrizacao.bdPassword" name="parametrizacaoForm" />
													</div>
												</logic:equal>
												<logic:equal name="parametrizacaoForm" value="false" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.parametrizacao.acessar)">
													<div class="row" style="margin-top: 15px;">
														<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
															<div class="alert alert-info">Usuário sem Permissão a esses dados!</div>
														</div>
													</div>
												</logic:equal>
												<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3" style="margin-top: 24px;">
													<button type="button" id="ativarBackupBancoDados" class="btn btn-info btn-sm btn-block cor-sistema">
														<i class="fa fa-upload"></i>
														Backup BD - ${ parametrizacaoForm.parametrizacao.empresa.horaFechamento }
													</button>
												</div>
											</div>
										</div>
									</div>
								</div>

								<div id="email" class="tab-pane fade in">
									<div class="panel">
										<div class="panel-body">
											<logic:equal name="parametrizacaoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.parametrizacao.acessar)">
												<div class="row">					

													<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
														<i class="fa fa-envelope-o"></i>
														<label>Email do remetente</label>
														<html:text styleClass="form-control input-sm obrigatorio" styleId="emailRemetente" property="parametrizacao.emailRemetente" name="parametrizacaoForm" />
													</div>
													<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
														<i class="fa fa-envelope"></i>
														<label>Email do servidor</label>
														<html:text styleClass="form-control input-sm obrigatorio" styleId="emailServidor" property="parametrizacao.emailServidor" name="parametrizacaoForm" />
													</div>
													<div class="form-group col-xs-12 col-sm-4 col-md-4 col-lg-4">
														<i class="fa fa-key"></i>
														<label>Senha do servidor</label>
														<html:text styleClass="form-control input-sm obrigatorio" styleId="senhaServidor" property="parametrizacao.senhaServidor" name="parametrizacaoForm" />
													</div>	
													<div class="form-group col-xs-12 col-sm-4 col-md-4 col-lg-4">
														<i class="fa fa-exchange"></i>
														<label>smtp.host</label>
														<html:text styleClass="form-control input-sm obrigatorio" styleId="smtpServidor" property="parametrizacao.smtpServidor" name="parametrizacaoForm" />
														<small id="smtpHelpBlock" class="form-text text-muted"> O servidor SMTP ao qual se conectar </small>
													</div>												
													<div class="form-group col-xs-12 col-sm-4 col-md-4 col-lg-4">
														<i class="fa fa-sitemap"></i>
														<label>smtp.port</label>
														<html:text styleClass="form-control input-sm obrigatorio" styleId="portaServidor" property="parametrizacao.portaServidor" name="parametrizacaoForm" />
														<small id="portaHelpBlock" class="form-text text-muted"> A porta do servidor SMTP à qual se conectar, se o método connect () não especificar explicitamente uma. O padrão é 25 </small>
													</div>
													<div class="form-group col-xs-12 col-sm-4 col-md-4 col-lg-4">
														<i class="fa fa-sitemap"></i>
														<label>smtp.starttls.enable</label>
														<html:select styleClass="form-control input-sm" styleId="smtpStarttls" property="parametrizacao.smtpStarttls" name="parametrizacaoForm">
															<html:option value="">Selecione...</html:option>
															<html:option value="true">Sim</html:option>
															<html:option value="false">Não</html:option>
														</html:select>
														<small id="smtpStarttlsHelpBlock" class="form-text text-muted"> Se verdadeiro, habilita o uso do STARTTLS comando (se suportado pelo servidor) para alternar a conexão para uma conexão protegida por TLS antes de emitir qualquer comando de login. Se o servidor não suportar STARTTLS, a conexão continuará sem o uso do TLS; propriedade falha se STARTTLS não for suportado. Observe que um armazenamento confiável apropriado deve ser configurado para que o cliente confie no certificado do servidor. O padrão é false </small>
													</div>
													<div class="form-group col-xs-12 col-sm-4 col-md-4 col-lg-4">
														<i class="fa fa-sitemap"></i>
														<label>smtp.ssl.trust</label>
														<html:text styleClass="form-control input-sm" styleId="smtpSslTrust" property="parametrizacao.smtpSslTrust" name="parametrizacaoForm" />
														<small id="smtpSslTrustHelpBlock" class="form-text text-muted"> Se definido, e uma fábrica de soquetes não foi especificada, habilita o uso de a MailSSLSocketFactory. Se definido como "*", todos os hosts são confiáveis. Se definido como uma lista de hosts separados por espaço em branco, esses hosts são confiáveis. Caso contrário, a confiança depende do certificado que o servidor apresenta </small>
													</div>
													<div class="form-group col-xs-12 col-sm-4 col-md-4 col-lg-4">
														<i class="fa fa-sitemap"></i>
														<label>smtp.socketFactory.port</label>
														<html:text styleClass="form-control input-sm" styleId="smtpSocketFactoryPort" property="parametrizacao.smtpSocketFactoryPort" name="parametrizacaoForm" />
														<small id="smtpSocketFactoryPortHelpBlock" class="form-text text-muted"> Especifica a porta à qual se conectar ao usar o socket factory especificado. Se não estiver definido, a porta padrão será usada </small>
													</div>
													<div class="form-group col-xs-12 col-sm-4 col-md-4 col-lg-4">
														<i class="fa fa-sitemap"></i>
														<label>smtp.socketFactory.class</label>
														<html:text styleClass="form-control input-sm" styleId="smtpSocketFactoryClass" property="parametrizacao.smtpSocketFactoryClass" name="parametrizacaoForm" />
														<small id="smtpSocketFactoryClassHelpBlock" class="form-text text-muted"> Se definido, especifica o nome de uma classe que estende a javax.net.ssl.SSLSocketFactoryclasse. Esta classe será usada para criar soquetes SMTP SSL </small>
													</div>													
													
													<div class="form-group col-xs-12 col-sm-4 col-md-4 col-lg-4" style="background-color: #f9cd6d;">
														<i class="fa fa-envelope"></i>
														<label>Email para teste</label>
														<html:text styleClass="form-control input-sm obrigatorio" styleId="emailParaTeste" property="parametrizacao.emailParaTeste" name="parametrizacaoForm" />
														<small id="emailParaTesteHelpBlock" class="form-text text-muted"> Quando em "Modo Teste", todos os emails serão enviados para este  </small>
													</div>
													<div class="form-group col-xs-12 col-sm-4 col-md-4 col-lg-2" style="background-color: #f9cd6d;">
														<i class="fa fa-warning"></i>
														<label>Modo Teste</label>
														<html:select styleClass="form-control input-sm obrigatorio" styleId="modoTeste" property="parametrizacao.modoTeste" name="parametrizacaoForm">
															<html:option value="true">Sim</html:option>
															<html:option value="false">Não</html:option>
														</html:select>
														<small id="modoTesteHelpBlock" class="form-text text-muted"> Faz com que todos os emails sejam enviados para o email informado no campo "Email para teste" </small>
													</div>
													
												</div>
											</logic:equal>
											<logic:equal name="parametrizacaoForm" value="false" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.parametrizacao.acessar)">
												<div class="row" style="margin-top: 15px;">
													<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
														<div class="alert alert-info">Usuário sem Permissão a esses dados!</div>
													</div>
												</div>
											</logic:equal>
										</div>
									</div>
								</div>
							</div>

							<logic:equal name="parametrizacaoForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.parametrizacao.acessar)">
								<div class="row">
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
		$("#horarioVerao").focus();

		/* Setando NOTNULL nos campos*/
		//$(".form-control").addClass("obrigatorio");

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#pathBackupBD").attr("maxlength", 255);
		$("#pathUploadFiles").attr("maxlength", 255);
		$("#pathUploadBKPFiles").attr("maxlength", 255);
		$("#urlContextPath").attr("maxlength", 255);
		$("#pathMySQL").attr("maxlength", 255);		
		$("#bdUser").attr("maxlength", 10);
		$("#bdPassword").attr("maxlength", 10);
		
		$("#emailRemetente").attr("maxlength", 255);
		$("#emailServidor").attr("maxlength", 255);
		$("#senhaServidor").attr("maxlength", 20);
		$("#portaServidor").attr("maxlength", 5);
		$("#smtpServidor").attr("maxlength", 50);
		$("#smtpStarttls").attr("maxlength", 5);
		$("#smtpSslTrust").attr("maxlength", 50);
		$("#smtpSocketFactoryPort").attr("maxlength", 5);
		$("#smtpSocketFactoryClass").attr("maxlength", 50);

		/* Setando os placeholder dos campos*/
		$("#pathBackupBD").attr("placeholder", "Caminho da pasta onde ficará os Backups de Banco de Dados.");
		$("#pathUploadFiles").attr("placeholder", "Caminho da pasta onde ficará os arquivos de upload.");
		$("#pathUploadBKPFiles").attr("placeholder", "Caminho da pasta onde ficará os Backups dos arquivos de upload.");
		$("#urlContextPath").attr("placeholder", "Caminho da pasta onde é publicado o arquivo .WAR.");
		$("#pathMySQL").attr("placeholder", "Caminho da pasta BIN do MYSQL");		
		$("#bdUser").attr("placeholder", "root.");
		$("#bdPassword").attr("placeholder", "root.");
		
		$("#emailRemetente").attr("placeholder", "Ex: gabriel@gmail.com");
		$("#emailServidor").attr("placeholder", "Ex: srcsoftware@gmail.com");
		$("#senhaServidor").attr("placeholder", "Ex: xpto");
		$("#portaServidor").attr("placeholder", "Ex: 587");
		$("#smtpServidor").attr("placeholder", "Ex: smtp.gmail.com");
		$("#smtpStarttls").attr("placeholder", "Ex: true");
		$("#smtpSslTrust").attr("placeholder", "Ex: smtp.gmail.com");
		$("#smtpSocketFactoryPort").attr("placeholder", "Ex: 587");
		$("#smtpSocketFactoryClass").attr("placeholder", "Ex: javax.net.ssl.SSLSocketFactory");
				 				
		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_parametrizacao").attr("autocomplete", "off");

		$('#form_parametrizacao').on('submit', function(e) {
			abrirModalProcessando();
		});

		$('#confirmar').click(function() {
			confirmarComAba('form_parametrizacao');
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
		
		/* Usado para telas com ABAS */
		function confirmarComAba(formulario) {
			var camposNaoPreenchidos = '';

			/* Validando os campos Obrigatorios */
			$(".obrigatorio").each(function(index, element) {
				if ($(element).val() == null || $(element).val() == '') {
					camposNaoPreenchidos = camposNaoPreenchidos + ' / ' + $(element).attr("id")
				}
			});

			if (camposNaoPreenchidos == null || camposNaoPreenchidos == '') {
				executar(formulario, 'confirmar');
			} else {
				modalCamposObrigatorios(camposNaoPreenchidos);
			}

		}

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


