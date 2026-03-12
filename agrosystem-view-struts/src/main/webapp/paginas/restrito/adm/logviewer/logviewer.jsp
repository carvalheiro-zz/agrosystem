<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<jsp:include page="../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<div class="row">
	<!-- Define o tamanho geral da tela -->
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel sombra">

			<!-- INICIO FORMULARIO -->
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_logViewer" action="restrito/sistema/logViewer" method="post">
							<html:hidden property="method" value="empty" />
							<div class="row" style="margin-top: 15px;">
								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label>Data a partir</label>
									<html:text styleClass="form-control input-sm data" styleId="data" property="data" name="logViewerForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label>Hora a partir</label>
									<html:text styleClass="form-control input-sm hora" styleId="hora" property="hora" name="logViewerForm" />
								</div>
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-2">
									<!-- BOTOES -->
									<button type="button" id="atualizar" class="btn btn-success btn-sm cor-sistema" style="margin-top: 25px;">
										<i class="fa fa-save"></i>
										Atualizar
									</button>
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<label>Arquivo de Log</label>
									<html:text styleClass="form-control input-sm bloqueado" styleId="caminhoArquivo" property="caminhoArquivo" name="logViewerForm" />
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<html:textarea styleClass="form-control input-sm" styleId="conteudo" property="conteudo" name="logViewerForm" rows="30" />
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

		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_logViewer").attr("autocomplete", "off");

		$('#form_logViewer').on('submit', function(e) {
			abrirModalProcessando();
		});

		$('#atualizar').on('click', function() {
			var actionURL = '${contextPath}/restrito/sistema/logViewer.src?method=atualizar&data='+$('#data').val()+"&hora="+$('#hora').val();

			$.ajax({
				type : 'POST',
				url : actionURL,
				success : function(data, textStatus, XMLHttpRequest) {
					$("#conteudo").val(data.conteudo);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(textStatus);
				}
			});
		});

		window.setInterval(function() {
			var actionURL = '${contextPath}/restrito/sistema/logViewer.src?method=atualizar&data='+$('#data').val()+"&hora="+$('#hora').val();

			$.ajax({
				type : 'POST',
				url : actionURL,
				success : function(data, textStatus, XMLHttpRequest) {
					$("#conteudo").val(data.conteudo);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(textStatus);
				}
			});
		}, 10000);

	});
</script>