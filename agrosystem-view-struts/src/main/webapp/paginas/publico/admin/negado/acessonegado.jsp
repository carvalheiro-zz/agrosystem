<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<html lang="pt">

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
<jsp:include page="../../../imports/imports.jsp"></jsp:include>

<title>Ops...</title>

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
				<div class="login-panel panel panel-default"
					style="border-radius: 3px; box-shadow: 0 6px 6px rgba(0, 0, 0, 0.3); background: rgba(255, 255, 255, 0.7); padding: 30px 40px 30px 40px; border: none; text-align: center; margin-top: 100px;">
					<div class="panel-body">

						<fieldset>
							<div class="row">
								<div class="col-xs-12">
									<img alt="Logo" title="Logo src" src="${contextPath}/assets/images/propria/404-img.png" style="margin-bottom: 15px;">
								</div>

								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="text-align: center;">
									<label style="color: #337ab7;">																			
										Ops.
										<br />
										<bean:message key="text.tela.acesso_negado"/>
									</label>

								</div>
							</div>
						</fieldset>

					</div>
				</div>
			</div>

		</div>
	</div>
</body>
</html>