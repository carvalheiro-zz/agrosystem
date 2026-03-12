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

<title>Union++ V1.0</title>

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
			<div class="col-lg-offset-3 col-md-offset-2 col-sm-offset-1 col-lg-6 col-md-8 col-sm-10">
				<div class="panel panel-dander" style="margin-top: 120px; border: 0px solid transparent; */">

					<!-- INICIO CAMPOS DE PESQUISA -->
					<div class="panel-heading" style="background-color: #b94343;">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12">
								<span class="titulo_formulario">
									<i class="fa fa-coffee" style="font-size: 18px;"></i>
									Ativação de Licença de Software
								</span>
							</div>
						</div>
					</div>
					<!-- TERMINO CAMPOS DE PESQUISA -->

					<!-- INICIO FORMULARIO -->
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12">
								<html:form styleId="form_licenca" action="restrito/admin/desktop" method="post">
									<html:hidden property="method" value="empty" />

									<div class="row" style="font-size: 30px;">
										<div class="form-group col-lg-12 col-md-12 col-sm-12 text-center">
											<label>Dias restantes de acesso:</label>
										</div>
										<div class="form-group col-lg-12 col-md-12 col-sm-12 text-center">
											<label>${desktopForm.diasRestantesChaveValida}</label>
										</div>
									</div>

									<div class="row">
										<div class="form-group col-lg-12 col-md-12 col-sm-12">
											<label style="font-size: 24px;">Nº da Chave</label>
											<html:text styleClass="form-control input-lg text-center" styleId="chaveAtivacao" property="chaveAtivacao" name="desktopForm" />
										</div>
									</div>
									<div class="row">
										<div class="form-group col-lg-12">
											<i class="fa fa-institution"></i>
											&nbsp;Desenvolvido por SRC Treinamento Tecnológico
										</div>
										<div class="form-group col-lg-12">
											<i class="fa fa-phone"></i>
											&nbsp;(14)9.9652-7007 - Gabriel
										</div>
										<div class="form-group col-lg-12">
											<i class="fa fa-envelope-o"></i>
											&nbsp;adm@gruposrc.com.br
										</div>
									</div>
									<!-- BOTOES -->
									<div class="row" style="margin-top: 15px;">
										<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
											<button type="submit" id="ativar" class="btn btn-success btn-block">Ativar</button>
										</div>
										<logic:equal property="permiteContinuar" value="true" name="desktopForm">
											<div class="col-sm-offset-4 col-md-offset-4 col-lg-offset-4 col-xs-12 col-sm-4 col-md-4 col-lg-4">
												<button type="submit" id="continuar" class="btn btn-primary btn-block">Continuar</button>
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
	</div>

	<script type="text/javascript">
		$(function() {
			// Aqui setamos o caminho do elemento img
			$("#chaveAtivacao").focus();

			$("#chaveAtivacao").attr("maxlength", 30);
			$("#login").attr("placeholder", "Chave de Ativação");

			$('#ativar').click(function() {
				executarComSubmit('form_licenca', 'ativar');
			});

			$('#continuar').click(function() {
				executarComSubmit('form_licenca', 'continuar');
			});

		});
	</script>

</body>

</html:html>