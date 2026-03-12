<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<!-- CAMPOS DE CADASTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -30px; margin-bottom: -15px;">
		<h1 class="page-header cabecalho_pagina">
			<i class="fa fa-graduation-cap" style="font-size: 26px;"></i>
			Painel de Envios de Emails
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Painel de Envios de Emails para testes de configuração
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>


<div class="row">
	<!-- Define o tamanho geral da painelTestesMail -->
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel sombra">

			<!-- INICIO FORMULARIO -->
			<div class="panel-body">
				<html:form styleId="form_painelTestesMail" action="restrito/admin/painelTestesMail" method="post">
					<html:hidden property="method" value="empty" />

					<div class="row">

						<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-4">
							<label>Remetente</label>
							<html:text styleClass="form-control input-sm" styleId="emailRemetente" property="emailRemetente" name="painelTestesMailForm" />
						</div>
						<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-2">
							<label>Senha Remetente</label>
							<html:text styleClass="form-control input-sm" styleId="senhaEmailRemetente" property="senhaEmailRemetente" name="painelTestesMailForm" />
						</div>
						<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
							<label>Propertie</label>
							<html:textarea styleClass="form-control input-sm" styleId="properties" property="properties" name="painelTestesMailForm" rows="5" />
							<%-- <html:select styleClass="form-control input-sm" styleId="properties" property="properties" name="painelTestesMailForm">
								<html:option value="">Selecione</html:option>
								<html:option value="gmail-ssl">GMAIL SSL</html:option>
								<html:option value="gmail-tsl">GMAIL TSLl</html:option>
								<html:option value="office365">OFFICE365</html:option>
							</html:select> --%>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
							<label>Destinatarios</label>
							<html:textarea styleClass="form-control input-sm" styleId="destinatarios" property="destinatarios" name="painelTestesMailForm" rows="2" />
							<small id="destinatariosHelp" class="form-text text-muted">Separe com (,) ex: xxx@xxx.xx,zzz@zzz.zz</small>
						</div>
						<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
							<label>CC</label>
							<html:textarea styleClass="form-control input-sm" styleId="comCopias" property="comCopias" name="painelTestesMailForm" rows="2" />
							<small id="comCopiasHelp" class="form-text text-muted">Separe com (,) ex: xxx@xxx.xx,zzz@zzz.zz</small>
						</div>
						<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-4">
							<label>Assunto</label>
							<html:text styleClass="form-control input-sm" styleId="assunto" property="assunto" name="painelTestesMailForm" />
						</div>
						<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-4">
							<label>Caminho Imagem Assinatura</label>
							<html:text styleClass="form-control input-sm" styleId="pathImagemAssinatura" property="pathImagemAssinatura" name="painelTestesMailForm" />
						</div>
						<%-- <div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-4">
							<label>HTML Assinatura</label>
							<html:text styleClass="form-control input-sm" styleId="assinaturaHTML" property="assinaturaHTML" name="painelTestesMailForm" />
						</div> --%>

						<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
							<label>Conteúdo</label>
							<html:textarea styleClass="form-control input-sm" styleId="conteudoEmail" property="conteudoEmail" name="painelTestesMailForm" rows="5" />
						</div>
						<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
							<label>HTML Assinatura</label>
							<html:textarea styleClass="form-control input-sm" styleId="assinaturaHTML" property="assinaturaHTML" name="painelTestesMailForm" rows="5" />
						</div>



						<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<label>Anexos</label>
							<html:textarea styleClass="form-control input-sm" styleId="pathsAttachs" property="pathsAttachs" name="painelTestesMailForm" rows="5" />
							<small id="anexosHelp" class="form-text text-muted">Exemplo: arquivo.txt : C:/temp/anexos/arquivo.txt</small>
							<%-- <div class="row">
								<logic:iterate name="painelTestesMailForm" property="pathsAttachs" id="map" indexId="i">
									<div class="form-group col-xs-12 col-sm-9 col-md-6 col-lg-2">
										<label>Nome</label>
										<html:text styleClass="form-control input-sm" styleId="keyAnexo${i}" property="key" name="map" />
									</div>
									<div class="form-group col-xs-12 col-sm-9 col-md-6 col-lg-10">
										<label>Caminho arquivo</label>
										<html:text styleClass="form-control input-sm" styleId="valueAnexo${i}" property="value" name="map" />
									</div>									
								</logic:iterate>
							</div> --%>
						</div>
					</div>


				</html:form>
			</div>
			<div class="panel-footer">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-4 col-lg-2">
						<button type="button" id="enviar" class="btn btn-success btn-sm btn-block cor-sistema">
							<i class="fa fa-mail"></i>
							Enviar
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- TERMINO FORMULARIO -->
	<!-- /.panel-body -->

</div>
<!-- /.panel -->



<script type="text/javascript">
	$(document).ready(function() {
		/* Foco inicial */
		$("#remetente").focus();

		/* Setando NOTNULL nos campos*/
		$("#emailRemetente").addClass("obrigatorio");
		$("#senhaEmailRemetente").addClass("obrigatorio");
		$("#destinatarios").addClass("obrigatorio");
		$("#assunto").addClass("obrigatorio");
		$("#conteudoEmail").addClass("obrigatorio");
		$("#properties").addClass("obrigatorio");

		/* Setando os placeholder dos campos*/
		$("#emailRemetente").attr("placeholder", "Remetente");
		$("#senhaEmailRemetente").attr("placeholder", "Senha Remetente");
		$("#destinatarios").attr("placeholder", "Destinatários");
		$("#comCopias").attr("placeholder", "CC");
		$("#pathsAttachs").attr("placeholder", "arquivo.txt:C:/temp/anexos/arquivo.txt");
		$("#pathImagemAssinatura").attr("placeholder", "Caminho imagem assinatura");
		$("#assinaturaHTML").attr("placeholder", "HTML assinatura");
		$("#assunto").attr("placeholder", "Assunto");
		$("#conteudoEmail").attr("placeholder", "Conteudo");
		$("#properties").attr("placeholder", "mail.smtp.host : smtp.gmail.com\n"+
				"mail.smtp.socketFactory.port : 465\n"+
				"mail.smtp.socketFactory.class : javax.net.ssl.SSLSocketFactory\n"+
				"mail.smtp.auth : true\n"+
				"mail.smtp.port : 465\n"+
				"mail.debug : true");
		

		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_painelTestesMail").attr("autocomplete", "off");

		$('#form_painelTestesMail').on('submit', function(e) {
			abrirModalProcessando();
		});

		$('#limpar').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_painelTestesMail', 'limpar');
		});

		$('#enviar').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_painelTestesMail', 'enviar');
		});
	});
</script>