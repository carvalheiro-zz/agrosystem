<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>

<style>
<!--
a {
	color: #101010;
}
-->
</style>

<!-- MENU PRINCIPAL LATERAL -->
<ul class="nav" id="side-menu">

	<!-- <li class="active">
		<a>
			<i class="fa fa-home fa-fw" style="font-size: 18px;"></i>
			<span class="menu-text" id="statusServidorView"></span>
		</a>
	</li> -->

	<li style="border-bottom: 0px;">
		<!-- <li> -->
		<a href="${contextPath}/restrito/admin/desktop.src?method=abrir" class="cor-sistema" style="text-shadow: 1px 0px 0px black, -1px 0px 0px black, 0px 1px 0px black, 0px -1px 0px black; color: white; font-weight: bold;">
			<img src='${contextPath}/temp/${usuarioSessaoPOJO.imagem}' class='img-circle' style="max-width: 50px;">

			<i class="fa fa-fw"></i>
			<span class="menu-text">(${usuarioSessaoPOJO.usuario.tipoUsuario.nome})</span>
		</a>
	</li>

	<jsp:include page="../menu.jsp"></jsp:include>

	<core:if test="${usuarioSessaoPOJO.usuario.tipoUsuario.nome != 'Fornecedor'}">
		<li>
			<a href="${contextPath}/publico/admin/login.src?method=sair" style="font-size: 18px;">
				<i class="fa fa-power-off fa-fw" style="color: red;"></i>
				<span class="menu-text">Sair</span>
			</a>
		</li>
	</core:if>

	<li>
		<a id="linkSuporte" class="cor-sistema text-center" style="text-shadow: 1px 0px 0px black, -1px 0px 0px black, 0px 1px 0px black, 0px -1px 0px black; color: white; font-weight: bold; cursor: help;">
			<i class="fa fa-headphones fa-fw" style="font-size: 18px;"></i>
			<span class="menu-text"> SRC Software </span>
		</a>
	</li>
	<li>
		<a class="cor-sistema text-center" style="text-shadow: 1px 0px 0px black, -1px 0px 0px black, 0px 1px 0px black, 0px -1px 0px black; color: white; font-weight: bold; cursor: help;">
			<i class="fa fa-circle fa-fw" style="font-size: 18px;"></i>
			<span class="menu-text" id="statusServidorView">Servidor</span>
		</a>
	</li>
</ul>
<script type="text/javascript">
	$(function() {
		$('#linkSuporte').click(function() {
			//executarComSubmit('form_login', 'reenviarSenha');
			modalSuporte();
		});
	});

	function modalSuporte() {
		var mensagem = "";
		mensagem = '<div class="row">' + '<div class="form-group col-lg-12">' + '<i class="fa fa-institution"></i>&nbsp;Desenvolvido por SRC Treinamento Tecnológico' + '</div>'
				+ '<div class="form-group col-lg-12">' + '<i class="fa fa-phone"></i>&nbsp;(14)9.9652-7007 - Gabriel' + '</div>' + '<div class="form-group col-lg-12">'
				+ '<i class="fa fa-envelope-o"></i>&nbsp;adm@gruposrc.com.br' + '</div>' + '</div>';

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
</script>

<script type="text/javascript">
	$(document).ready(function() {
		function verificarStatusServidor() {
			setInterval(function() {

				var actionURL = '${contextPath}/restrito/admin/desktop.src?method=verificarStatusServidor';
				$.ajax({
					type : "POST",
					url : actionURL,
					success : function(data, textStatus, XMLHttpRequest) {

						// we have the response
						//$("#statusServidorView").text("Servidor (" + data.statusServidorAtual + " )");
						$("#statusServidorView").html("Servidor <i class='fa fa-rss fa-fw' style='font-size: 18px;;color:greenyellow'></i>");
						$("#statusServidorView").addClass('On');
						$("#statusServidorView").removeClass('Off');

						//$("p").hasClass("intro")
						if ($("#statusServidorView").hasClass("On")) {
							modalAtualizacao.close();
						}

					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {

						//alert(textStatus);
						//$("#statusServidorView").text("Servidor ( " + data.statusServidorAtual + " )");
						$("#statusServidorView").html("Servidor <i class='fa fa-rss fa-fw' style='font-size: 18px;color:red'></i>");
						$("#statusServidorView").addClass('Off');
						$("#statusServidorView").removeClass('On');

						if ($("#statusServidorView").hasClass("Off")) {
							modalAtualizacao.open();
						}
					}
				});
			}, 1000);
		}

		//verificarStatusServidor();

	});

	var modalAtualizacao = new BootstrapDialog({
		size : BootstrapDialog.SIZE_LARGE,
		title : 'Servidor em Manutenção',
		message : 'Aguarde um momento!',
		type : BootstrapDialog.TYPE_INFO, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
		closable : false, // <-- Default value is false
		draggable : true
	// <-- Default value is false			
	});
	//function modalAtualizacao() {
	/* modalAtualizacao = new BootstrapDialog({
		size : BootstrapDialog.SIZE_LARGE,
		title : 'Servidor em Manutenção',
		message : 'Aguarde um momento!',
		type : BootstrapDialog.TYPE_INFO, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
		closable : false, // <-- Default value is false
		draggable : true
	// <-- Default value is false			
	}); */
	//modalAtualizacao.open();
	//}

	function exibirVideoExplicativo(nomeVideo) {

		var htmlVideo = "<div class='row'>" + "<div class='col-xs-12 col-sm-12 col-md-12 col-lg-12'>" + "<video controls width='100%'>"
				+ "<source src='${contextPath}/temp/videos/"+nomeVideo+"' type='video/mp4'>" + "Formato não suportado" + "</video>" + "</div>" + "</div>";
		//alert("<source src='${contextPath}/temp/videos/"+nomeVideo+"' type='video/mp4'>")
		BootstrapDialog.show({
			size : BootstrapDialog.SIZE_WIDE,
			title : 'Video explicativo',
			message : htmlVideo,
			type : BootstrapDialog.TYPE_INFO, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
			closable : true, // <-- Default value is false
			draggable : true, // <-- Default value is false
			buttons : [ {
				label : 'Fechar',
				action : function(dialogRef) {
					dialogRef.close();
				}
			} ]
		});

	}
</script>
<!-- /MENU PRINCIPAL LATERAL -->

