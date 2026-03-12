<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>


<!-- INICIO TABELA -->
<logic:empty name="funcionarioForm" property="funcionario.pessoaFisica.documentos">
	<div class="row" style="margin-top: 15px;">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="alert alert-warning">Nenhum documento adicionado!</div>
		</div>
	</div>
</logic:empty>
<logic:notEmpty name="funcionarioForm" property="funcionario.pessoaFisica.documentos">
	<div class="table-responsive">
		<table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
			<thead>
				<!-- CABEÇALHO DA TABELA -->
				<tr class="cor-sistema" style="color: white;">
					<th>Documento</th>
					<th>Número</th>
					<th class="text-center" style="width: 80px;">Opções</th>
				</tr>
			</thead>
			<tbody>
				<!-- TABELA -->
				<logic:iterate id="documentoCorrente" name="funcionarioForm" property="funcionario.pessoaFisica.documentos" type="br.com.srcsoftware.modular.manager.pessoa.documento.DocumentoDTO">
					<tr>
						<td>${documentoCorrente.nome}</td>
						<td>${documentoCorrente.numero}</td>
						<td class="text-center">
							<logic:equal name="funcionarioForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.funcionario.alterar)">
								<a onclick="removerItem('${documentoCorrente.idTemp}');" class="btn btn-danger btn-xs fa fa-trash btnRemover" style="text-decoration: none; font-size: 18px"></a>
							</logic:equal>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div class="row">
								<logic:notEmpty property="anexo1" name="documentoCorrente">
									<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-left: 30px;">
										<a href="${contextPath}/temp/${documentoCorrente.prefixoAnexo1}/${documentoCorrente.anexo1}" target="_blank" class="btn btn-success btn-xs" title="Visualizar">
											<i class="fa fa-eye"></i>
										</a>
										<a href="${contextPath}/restrito/sistema/funcionario.src?method=removerAnexo1" class="btn btn-danger btn-xs" title="Remover">
											<i class="fa fa-trash"></i>
										</a>
										<label>Anexo 1 - </label>
										${documentoCorrente.anexo1}
									</div>
								</logic:notEmpty>
								<logic:notEmpty property="anexo2" name="documentoCorrente">
									<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-left: 30px;">
										<a href="${contextPath}/temp/${documentoCorrente.prefixoAnexo2}/${documentoCorrente.anexo2}" target="_blank" class="btn btn-success btn-xs" title="Visualizar">
											<i class="fa fa-eye"></i>
										</a>
										<a href="${contextPath}/restrito/sistema/funcionario.src?method=removerAnexo2" class="btn btn-danger btn-xs" title="Remover">
											<i class="fa fa-trash"></i>
										</a>
										<label>Anexo 2 - </label>
										${documentoCorrente.anexo2}
									</div>
								</logic:notEmpty>
								<logic:notEmpty property="anexo3" name="documentoCorrente">
									<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-left: 30px;">
										<a href="${contextPath}/temp/${documentoCorrente.prefixoAnexo3}/${documentoCorrente.anexo3}" target="_blank" class="btn btn-success btn-xs" title="Visualizar">
											<i class="fa fa-eye"></i>
										</a>
										<a href="${contextPath}/restrito/sistema/funcionario.src?method=removerAnexo3" class="btn btn-danger btn-xs" title="Remover">
											<i class="fa fa-trash"></i>
										</a>
										<label>Anexo 3 - </label>
										${documentoCorrente.anexo3}
									</div>
								</logic:notEmpty>
								<logic:notEmpty property="anexo4" name="documentoCorrente">
									<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-left: 30px;">
										<a href="${contextPath}/temp/${documentoCorrente.prefixoAnexo4}/${documentoCorrente.anexo4}" target="_blank" class="btn btn-success btn-xs" title="Visualizar">
											<i class="fa fa-eye"></i>
										</a>
										<a href="${contextPath}/restrito/sistema/funcionario.src?method=removerAnexo4" class="btn btn-danger btn-xs" title="Remover">
											<i class="fa fa-trash"></i>
										</a>
										<label>Anexo 4 - </label>
										${documentoCorrente.anexo4}
									</div>
								</logic:notEmpty>
								
							</div>
						</td>
					</tr>

				</logic:iterate>
			</tbody>
		</table>
	</div>
</logic:notEmpty>





<script type="text/javascript">
	$(document).ready(function() {
		carregarMascaras();
	});

	function removerItem(idTempItem) {

		var theForm = $('form[name=funcionarioForm]');
		var actionURL = theForm.attr('action') + '?method=removerDocumento&idDocumento=' + idTempItem;
		$.ajax({
			type : 'POST',
			url : actionURL,
			success : function(data, textStatus, XMLHttpRequest) {

				$('#id_documentos_ajax').html(data);
				$("#nome").focus();
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(textStatus);
			}
		});

	}
</script>