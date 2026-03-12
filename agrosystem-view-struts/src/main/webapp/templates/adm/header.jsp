<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!-- MENU QUANDO A TELA DIMINUI -->
<div class="container-fluid">
	<button type="button" style="border-color: #FFF;" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
		<span class="sr-only">Toggle navigation</span>
		<span class="icon-bar" style="background-color: #FFF;"></span>
		<span class="icon-bar" style="background-color: #FFF;"></span>
		<span class="icon-bar" style="background-color: #FFF;"></span>
	</button>

	<!-- TITULO DO SISTEMA -->
	<ul class="nav navbar-nav navbar-left">
		<li>
			<a class="navbar-brand" style="text-shadow: 1px 0px 0px black, -1px 0px 0px black, 0px 1px 0px black, 0px -1px 0px black; color: #fff; font-size: 23px;" href="${contextPath}/restrito/admin/desktop.src?method=abrir">
				<i class="fa fa-globe fa-fw" style="font-size: 30px"></i>
				${usuarioSessaoPOJO.empresa.nomeSistema}
				<small style="font-size: 40%;">Versão:${usuarioSessaoPOJO.empresa.versaoSistema}</small>
			</a>
		</li>
	</ul>
	<!-- RELOGIO DO SISTEMA -->
	<%-- <ul class="nav navbar-nav navbar-center">
		<li>
			<a class="navbar-brand" style="text-shadow: 1px 0px 0px black, -1px 0px 0px black, 0px 1px 0px black, 0px -1px 0px black; color: #fff; font-size: 23px;" href="${contextPath}/restrito/admin/desktop.src?method=abrir">
				<i class="fa fa-clock-o fa-fw" style="font-size: 30px"></i>
				<label id="hora"></label>				
			</a>
		</li>
	</ul> --%>
	<!-- Colocar no canto direito -->
	<ul class="nav navbar-nav navbar-right">
		<li>
			<a href="javascript://" style="text-shadow: 1px 0px 0px black, -1px 0px 0px black, 0px 1px 0px black, 0px -1px 0px black; font-size: 12px; font-weight: bold; color: #FFF; font-weight: bold; cursor: default;" class="navbar-brand ">
				<i class="fa fa-institution fa-fw" style="font-size: 20px"></i>
				${usuarioSessaoPOJO.empresa.nomeFantasia}
			</a>
		</li>
		<li>
			<a href="javascript://" style="text-shadow: 1px 0px 0px black, -1px 0px 0px black, 0px 1px 0px black, 0px -1px 0px black; font-size: 12px; font-weight: bold; color: #FFF; font-weight: bold; cursor: default;" class="navbar-brand ">
				<i class="fa fa-street-view fa-fw" style="font-size: 20px"></i>
				${usuarioSessaoPOJO.nomePessoa}&nbsp;(&nbsp;
				<i class="fa fa-laptop fa-fw" style="font-size: 20px"></i>${usuarioSessaoPOJO.ipCorrente}&nbsp;)
			</a>
		</li>
		<li>
			<a href="javascript://" style="text-shadow: 1px 0px 0px black, -1px 0px 0px black, 0px 1px 0px black, 0px -1px 0px black; font-size: 24px; font-weight: bold; color: #FFF; font-weight: bold; cursor: default;" class="navbar-brand ">
				<i class="fa fa-clock-o fa-fw" style="font-size: 20px"></i>
				<label id="hora"></label>				
			</a>
			
		</li>
	</ul>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		function atualizarHora(){
			var data = new Date();
			
			var hora = data.getHours();
			var minuto = data.getMinutes();
			var segundo = data.getSeconds();
			
			if(hora<10){
				hora = "0"+hora;
			}
			if(minuto<10){
				minuto = "0"+minuto;
			}
			if(segundo<10){
				segundo = "0"+segundo;
			}
			
			var horas = hora + ":" + minuto +":" + segundo;
			
			$('#hora').html(horas);
			
			
		}
		
		atualizarHora();
		setInterval(atualizarHora , 1000);
	});

	/* $(document).ready(function() {
		atualizarHora();
		setInterval(function() {
			atualizarHora();
		}, 1000);
	});
	
	function atualizarHora() {
		
		var actionURL = "${contextPath}/restrito/admin/desktop.src?method=getHoraAtual";

		$.ajax({
			type : 'POST',
			url : actionURL,			
			success : function(data, textStatus, XMLHttpRequest) {
				$('#hora').html(data.hora);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				//alert(textStatus);
			}
		});
	} */
</script>