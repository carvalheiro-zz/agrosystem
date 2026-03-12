<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<style>
.loader {
	position: absolute;
	left: 50%;
	top: 50%;
	z-index: 1;
	width: 150px;
	height: 150px;
	margin: -75px 0 0 -75px;
	border: 16px solid #f3f3f3;
	border-radius: 50%;
	border-top: 16px solid #3498db;
	width: 120px;
	height: 120px;
	-webkit-animation: spin 2s linear infinite;
	animation: spin 2s linear infinite;
}

@
keyframes spin { 0% {
	transform: rotate(0deg);
}
100%{
transform:rotate(360deg);
}
}
</style>


<jsp:include page="../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.demonstrativoSafra.acessar)">
	<div class="loader"></div>

	<div id="idPainelDemonstrativo" style="margin-top: 10px;"></div>

	<div id="idPainelDemonstrativoSintetico" style="margin-top: 10px;"></div>

	<script type="text/javascript">
		$(document).ready(function() {

			var actionURL = '${contextPath}/restrito/sistema/demonstrativoSafra.src?method=abrirPainelDemonstrativo';

			$.ajax({
				type : 'POST',
				url : actionURL,
				beforeSend : function(data, textStatus, XMLHttpRequest) {
					$('#processando').text('Carregando...');
				},
				success : function(data, textStatus, XMLHttpRequest) {
					$('#idPainelDemonstrativo').html(data);

					$('.loader').css('display', 'none');
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(textStatus);
				}
			});

			$('.loader').css('display', 'none');

			var actionURL = '${contextPath}/restrito/sistema/demonstrativoSafraSintetico.src?method=abrirPainelDemonstrativoSintetico';

			$.ajax({
				type : 'POST',
				url : actionURL,
				beforeSend : function(data, textStatus, XMLHttpRequest) {
					$('#processandoSintetico').text('Carregando...');
				},
				success : function(data, textStatus, XMLHttpRequest) {
					$('#idPainelDemonstrativoSintetico').html(data);

					$('.loader').css('display', 'none');
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(textStatus);
				}
			});

			$('.loader').css('display', 'none');
		});
	</script>

</logic:equal>



<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.demonstrativoProducao.acessar)">
	<div class="loader"></div>

	<div id="idPainelDemonstrativoProducao" style="margin-top: 10px;"></div>

	<script type="text/javascript">
		$(document).ready(function() {

			var actionURL = '${contextPath}/restrito/sistema/demonstrativoProducao.src?method=abrirPainelDemonstrativoProducao';

			$.ajax({
				type : 'POST',
				url : actionURL,
				beforeSend : function(data, textStatus, XMLHttpRequest) {
					$('#processandoProducao').text('Carregando...');
				},
				success : function(data, textStatus, XMLHttpRequest) {
					$('#idPainelDemonstrativoProducao').html(data);

					$('.loader').css('display', 'none');
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(textStatus);
				}
			});

			$('.loader').css('display', 'none');
			
		});
	</script>

</logic:equal>

<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.demonstrativoLivroPonto.acessar)">
	<div class="loader"></div>

	<div id="idPainelDemonstrativoLivroPonto" style="margin-top: 10px;"></div>

	<script type="text/javascript">
		$(document).ready(function() {

			var actionURL = '${contextPath}/restrito/sistema/demonstrativoLivroPonto.src?method=abrirPainelDemonstrativoLivroPonto';

			$.ajax({
				type : 'POST',
				url : actionURL,
				beforeSend : function(data, textStatus, XMLHttpRequest) {
					$('#processandoHoraExtra').text('Carregando...');
				},
				success : function(data, textStatus, XMLHttpRequest) {
					$('#idPainelDemonstrativoLivroPonto').html(data);

					$('.loader').css('display', 'none');
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(textStatus);
				}
			});

			$('.loader').css('display', 'none');
			
		});
	</script>
	
	<div class="loader"></div>

	<div id="idPainelDemonstrativoLivroPontoFerias" style="margin-top: 10px;"></div>

	<script type="text/javascript">
		$(document).ready(function() {

			var actionURL = '${contextPath}/restrito/sistema/demonstrativoLivroPontoFerias.src?method=abrirPainelDemonstrativoLivroPonto';

			$.ajax({
				type : 'POST',
				url : actionURL,
				beforeSend : function(data, textStatus, XMLHttpRequest) {
					$('#processandoFerias').text('Carregando...');
				},
				success : function(data, textStatus, XMLHttpRequest) {
					$('#idPainelDemonstrativoLivroPontoFerias').html(data);

					$('.loader').css('display', 'none');
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(textStatus);
				}
			});

			$('.loader').css('display', 'none');
			
		});
	</script>

</logic:equal>
