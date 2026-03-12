<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<html:html lang="pt">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="Gabriel Damiani Carvalheiro">
<meta http-equiv="Content-Language" content="pt-br">

<%-- <link rel="shortcut icon" href="${contextPath}/assets/images/propria/favicon.ico" type="image/x-icon" /> --%>
<link rel="apple-touch-icon" sizes="57x57" href="${contextPath}/assets/images/propria/favicons/${usuarioSessaoPOJO.empresa.codigo}/apple-icon-57x57.png">
<link rel="apple-touch-icon" sizes="60x60" href="${contextPath}/assets/images/propria/favicons/${usuarioSessaoPOJO.empresa.codigo}/apple-icon-60x60.png">
<link rel="apple-touch-icon" sizes="72x72" href="${contextPath}/assets/images/propria/favicons/${usuarioSessaoPOJO.empresa.codigo}/apple-icon-72x72.png">
<link rel="apple-touch-icon" sizes="76x76" href="${contextPath}/assets/images/propria/favicons/${usuarioSessaoPOJO.empresa.codigo}/apple-icon-76x76.png">
<link rel="apple-touch-icon" sizes="114x114" href="${contextPath}/assets/images/propria/favicons/${usuarioSessaoPOJO.empresa.codigo}/apple-icon-114x114.png">
<link rel="apple-touch-icon" sizes="120x120" href="${contextPath}/assets/images/propria/favicons/${usuarioSessaoPOJO.empresa.codigo}/apple-icon-120x120.png">
<link rel="apple-touch-icon" sizes="144x144" href="${contextPath}/assets/images/propria/favicons/${usuarioSessaoPOJO.empresa.codigo}/apple-icon-144x144.png">
<link rel="apple-touch-icon" sizes="152x152" href="${contextPath}/assets/images/propria/favicons/${usuarioSessaoPOJO.empresa.codigo}/apple-icon-152x152.png">
<link rel="apple-touch-icon" sizes="180x180" href="${contextPath}/assets/images/propria/favicons/${usuarioSessaoPOJO.empresa.codigo}/apple-icon-180x180.png">
<link rel="icon" type="image/png" sizes="192x192"  href="${contextPath}/assets/images/propria/favicons/${usuarioSessaoPOJO.empresa.codigo}/android-icon-192x192.png">
<link rel="icon" type="image/png" sizes="32x32" href="${contextPath}/assets/images/propria/favicons/${usuarioSessaoPOJO.empresa.codigo}/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="96x96" href="${contextPath}/assets/images/propria/favicons/${usuarioSessaoPOJO.empresa.codigo}/favicon-96x96.png">
<link rel="icon" type="image/png" sizes="16x16" href="${contextPath}/assets/images/propria/favicons/${usuarioSessaoPOJO.empresa.codigo}/favicon-16x16.png">
<link rel="manifest" href="${contextPath}/assets/images/propria/favicons/${usuarioSessaoPOJO.empresa.codigo}/manifest.json">
<meta name="msapplication-TileColor" content="#ffffff">
<meta name="msapplication-TileImage" content="${contextPath}/assets/images/propria/favicons/${usuarioSessaoPOJO.empresa.codigo}/ms-icon-144x144.png">
<meta name="theme-color" content="#ffffff">

<%
	request.getSession().setAttribute("contextPath", request.getContextPath());
%>

<!-- IMPORTAÇÃO DOS CSS e DOS JSs -->
<jsp:include page="../../imports/imports.jsp"></jsp:include>

<title>${usuarioSessaoPOJO.empresa.nomeSistema}</title>

</head>

<%
	/* int i = (int) (Math.random() * 28);
	i = i == 0 ? 1 : i; */
	int i = 8;
%>

<body
	style="background: url('${contextPath}/assets/images/propria/background-login<%=i%>.png') no-repeat center center fixed; -webkit-background-size: cover;
    -moz-background-size: cover;
    -o-background-size: cover;
    background-size: cover;">
	<!-- Original: #343434 -->

	<div class="container">
		<div class="row">
			<div class="col-xs-offset-0 col-sm-offset-2 col-md-offset-3 col-lg-offset-3 col-xs-12 col-sm-8 col-md-6 col-lg-6">
				<div class="login-panel panel panel-default" style="border-radius: 3px; box-shadow: 0 6px 6px rgba(0, 0, 0, 0.3); background: rgba(255, 255, 255, 0.5); padding: 30px 40px 30px 40px; border: none; text-align: center; margin-top: 100px;">
					<div>
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 text-left">
								<label style="font-size: 14px; color: #337ab7; text-decoration: none;">									
									<i class="fa fa-link"></i>
									Versão 7.1.0.0 (14/08/2025)									
								</label>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 text-right">
								<label>
									<a id="linkSuporte">
										Suporte
										<i class="fa fa-question-circle" style="font-size: 24px"></i>
									</a>
								</label>
							</div>
						</div>
					</div>
					<div class="panel-body">
						<html:form styleId="form_login" action="publico/admin/login" method="post">
							<html:hidden property="method" value="empty" />
							
								<div class="row">
									<div class="col-xs-offset-1 col-sm-offset-3 col-md-offset-3 col-lg-offset-0 col-xs-10 col-sm-6 col-md-6 col-lg-5">
										<img alt="Logo" title="Logo src" class='img-responsive' src="${contextPath}/temp/${usuarioSessaoPOJO.empresa.prefixoImagem}/${usuarioSessaoPOJO.empresa.imagem}" style="margin-bottom: 15px;">
									</div>

									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-7">	
										<div class="form-group" style="color: #006496;">
											<html:text styleClass="form-control text-center" style="color: #006496; font-weight: bold; padding-right: 27px" styleId="identificadorEmpresa" property="identificadorEmpresa" name="loginForm" />
											<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 21px;" class="fa fa-institution"></i>
										</div>									
										<div class="form-group" style="color: #006496;">
											<html:text styleClass="form-control" style="color: #006496; font-weight: bold; padding-right: 27px" styleId="login" property="login" name="loginForm" />
											<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 21px;" class="fa fa-envelope-o"></i>
										</div>
										<div class="form-group" style="color: #006496;">
											<html:password styleClass="form-control senha" style="color: #006496; font-weight: bold; padding-right: 27px" styleId="senha" property="senha" name="loginForm" />
											<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 21px;" class="fa fa-lock"></i>
										</div>
										<div style="margin: 15px; text-align: right;">
											<label>
												<a id="reenviarSenha">
													<i class="fa fa-envelope"></i>
													Esqueci minha senha
												</a>
											</label>
										</div>
										<div class="form-group">
											<button type="submit" class="btn btn-primary-login btn-md btn-block" id="logar">
												<i class="fa fa-key"></i>
												Login
											</button>
										</div>
									</div>
								</div>
							
						</html:form>
					</div>
					<logic:messagesPresent message="false">
						<div class="panel-footer" id="mensagem-login" style="background-color: rgba(255, 0, 0, 0.65); color: white; font-weight: bold;">
							<!-- INICIO - MODAIS DE ERRO -->
							<html:messages id="message" message="false">
								<bean:write filter='false' name='message' />
							</html:messages>
						</div>
					</logic:messagesPresent>
					<logic:messagesPresent message="true">
						<div class="panel-footer" id="mensagem-login" style="background-color: rgba(0, 164, 255, 0.65); color: white; font-weight: bold;">
							<!-- INICIO - MODAIS DE SUCESSO -->
							<html:messages id="message" message="true">
								<bean:write filter='false' name='message'/>
							</html:messages>
						</div>
					</logic:messagesPresent>
				</div>
			</div>

		</div>
	</div>

	<script type="text/javascript">
		$(function() {
			// Aqui setamos o caminho do elemento img
			$("#identificadorEmpresa").focus();

			$("#login").attr("maxlength", 18);
			$("#login").attr("placeholder", "Usuário");

			$("#senha").attr("maxlength", 20);
			$("#senha").attr("placeholder", "Senha");
			
			$("#identificadorEmpresa").attr("maxlength", 3);
			//$("#identificadorEmpresa").attr("maxlength", 14);
			$("#identificadorEmpresa").attr("placeholder", "Código Empresa");
			//$("#identificadorEmpresa").attr("placeholder", "C.N.P.J Empresa");
			
			$("#identificadorEmpresa").addClass("obrigatorio");
			$("#login").addClass("obrigatorio");
			$("#senha").addClass("obrigatorio");

			// Desliga o auto-complete da pagina
			$("#form_login").attr("autocomplete", "off");

			$('#logar').click(function() {
				executar('form_login', 'autenticar');
			});

			$('#reenviarSenha').click(function() {
				modalReenviarSenha();
			});

			$('#linkSuporte').click(function() {
				modalSuporte();
			});

			//Após a leitura da pagina
			setTimeout(function() {
				/* INICIO - MODAL DE PROCESSANDO */
				$("#mensagem-login").css("display", "none");
				/* TERMINO - MODAL DE PROCESSANDO */
			}, 10000);

			function modalReenviarSenha() {
				BootstrapDialog.show({
					size : BootstrapDialog.SIZE_LARGE,
					title : 'Confirmação de reenvio de senha',
					message : '<div class="row">' + '<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12 class="text-center" style="font-size: 16px;">'
							+ '<label>Por favor, informe o email cadastrado para o usuario em questão.</label>'
							+ '<br \>'
							+ '<label>Sua senha será reenviada ao email informado.</label>' + '</div>'
							+ '<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12 class="text-center">'
							+ '<input type="text" style="font-size: 14px; font-weight: bold;" class="form-control text-center input-lg" id="emailReenvio"/>' + '</div>' + '</div>',
					type : BootstrapDialog.TYPE_INFO, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
					closable : true, // <-- Default value is false
					draggable : true, // <-- Default value is false
					onshown: function(dialogRef){
						$('#emailReenvio').focus();
					},
					buttons : [ {
						label : 'Cancelar',
						action : function(dialogRef) {
							dialogRef.close();
						}
					}, {
						icon : 'fa fa-send-o',
						label : 'Confirmar envio',
						cssClass : 'btn-primary',
						action : function(dialogRef) {
							window.location = '${contextPath}/publico/admin/login.src?method=reenviarSenha&email=' + $("#emailReenvio").val();
						}
					} ]
				});

			}

			function modalSuporte() {
				var mensagem = "";
				mensagem = '<div class="row">' + '<div class="form-group col-lg-12">' + '<i class="fa fa-institution"></i>&nbsp;Desenvolvido por SRC Treinamento Tecnológico' + '</div>'
						+ '<div class="form-group col-lg-12">' + '<i class="fa fa-phone"></i>&nbsp;(14)9.9652-7007 - Gabriel' + '</div>' + '<div class="form-group col-lg-12">'
						+ '<i class="fa fa-envelope-o"></i>&nbsp;gabriel.carvalheiro@gmail.com' + '</div>' + '</div>';

				BootstrapDialog.show({
					size : BootstrapDialog.SIZE_NORMAL,
					title : 'Suporte',
					message : mensagem,
					type : BootstrapDialog.TYPE_INFO, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
					closable : true, // <-- Default value is false
					draggable : true, // <-- Default value is false
					buttons : [ {
						label : 'Ok obrigado',
						action : function(dialogRef) {
							dialogRef.close();
						}
					} ]
				});

			}
			
			/* Definindo os Campos Obrigatórios */
			$(".obrigatorio").each(function(index, element) {
				$(element).attr("required", "required");
			});
		});
	</script>	
</body>

</html:html>