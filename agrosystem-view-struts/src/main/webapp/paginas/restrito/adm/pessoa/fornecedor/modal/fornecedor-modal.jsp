<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<style>
.kv-avatar .file-preview-frame, .kv-avatar .file-preview-frame:hover {
	margin: 0;
	padding: 0;
	border: none;
	box-shadow: none;
	text-align: center;
}

.kv-avatar .file-input {
	display: table-cell;
	max-width: 220px;
}
</style>

<!-- UPLOAD DA IMAGEM -->
<link href="${contextPath}/assets/bootstrap/bootstrap-fileinput-master/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />
<script src="${contextPath}/assets/bootstrap/bootstrap-fileinput-master/js/plugins/canvas-to-blob.min.js" type="text/javascript"></script>
<script src="${contextPath}/assets/bootstrap/bootstrap-fileinput-master/js/fileinput.min.js" type="text/javascript"></script>
<script src="${contextPath}/assets/bootstrap/bootstrap-fileinput-master/js/fileinput_locale_pt-BR.js"></script>

<script src="${contextPath}/assets/bootstrap/bower_components/bootstrap-filestyle-1.2.1/src/bootstrap-filestyle.min.js"></script>

<!-- CAMPOS DE CADASTRO -->
<div class="row">
	<jsp:include page="../../../../adm/alert-modal/alert-modal.jsp"></jsp:include>

	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<html:form styleId="form_fornecedor" action="restrito/admin/fornecedor" method="post" enctype="multipart/form-data">
			<html:hidden property="method" value="empty" />
			<html:hidden styleId="tipo" property="fornecedor.tipo" name="fornecedorForm" value="Mensalista" />
			<html:hidden styleId="imagem" property="fornecedor.imagem" name="fornecedorForm" />

			<ul class="nav nav-tabs" id="tabClienteMensalista">
				<li class="active">
					<a data-toggle="tab" href="#dados">Dados do Fornecedor</a>
				</li>
				<li>
					<a data-toggle="tab" href="#foto">Foto</a>
				</li>
				<li>
					<a data-toggle="tab" href="#anexo">Documentos</a>
				</li>
			</ul>
			<div class="tab-content">
				<div id="dados" class="tab-pane fade in active" style="margin-top: 20px;">
					<div class="row">
						<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<jsp:include page="../aba/dados_aba.jsp"></jsp:include>
						</div>
					</div>
				</div>
				<div id="foto" class="tab-pane fade" style="margin-top: 20px;">
					<div class="row">
						<div class="form-group text-center col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div id="kv-avatar-errors" class="center-block" style="width: 800px; display: none"></div>
						</div>
						<div class="form-group col-lg-offset-4 col-md-offset-4 col-xs-12 col-sm-12 col-md-8 col-lg-8">
							<div class="kv-avatar center-block">
								<input id="foto1" name="file" type="file" class="file-loading">
							</div>
						</div>
					</div>
				</div>
				<div id="anexo" class="tab-pane fade" style="margin-top: 20px;">
					<div class="row">
						<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
							<div class="alert alert-success" role="alert" style="height: 172px">
								<div class="row">
									<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<label>Descrição</label>
										<html:text styleClass="form-control input-sm documentoAnexo1" styleId="descricaoDocumento1" property="fornecedor.documento1.descricao" name="fornecedorForm" />
										<html:hidden styleClass="documentoAnexo1" styleId="idDocumento1" property="fornecedor.documento1.id" name="fornecedorForm" />
									</div>


									<!-- SE TIVER ANEXO SALVO -->
									<div class="form-group col-xs-12 col-sm-12 col-md-8 col-lg-9 exibirAnexado1">
										<html:text styleClass="form-control input-sm documentoAnexo1 bloqueado" styleId="anexoDocumento1" property="fornecedor.documento1.anexo" name="fornecedorForm" />
									</div>
									<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-3 text-right exibirAnexado1">
										<a style="padding: 0px 6px; font-size: 18px;" href="${contextPath}/temp/${fornecedorForm.fornecedor.documento1.prefixoAnexo}/${fornecedorForm.fornecedor.documento1.anexo}" target="_blank" class="btn btn-success btn-xs" title="Visualizar">
											<i class="fa fa-eye"></i>
										</a>
										<button style="padding: 0px 6px; font-size: 18px;" type="button" id="removerAnexo11" class="btn btn-danger btn-xs removerAnexo1" title="Remover">
											<i class="fa fa-trash"></i>
										</button>
									</div>

									<!-- ANEXOS UPLOAD -->
									<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12 exibirAnexar1">
										<html:file styleClass="form-control input-sm anexos" styleId="anexo1File" property="anexo1File" name="fornecedorForm" />
										<button type="button" id="removerAnexo12" class="btn btn-success btn-sm cor-sistema removerAnexo1">
											<i class="fa fa-close"></i>
											Limpar Arquivo Anexo
										</button>
									</div>
								</div>
							</div>
						</div>


						<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
							<div class="alert alert-success" role="alert" style="height: 172px">
								<div class="row">
									<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<label>Descrição</label>
										<html:text styleClass="form-control input-sm documentoAnexo2" styleId="descricaoDocumento2" property="fornecedor.documento2.descricao" name="fornecedorForm" />
										<html:hidden styleClass="documentoAnexo2" styleId="idDocumento2" property="fornecedor.documento2.id" name="fornecedorForm" />
									</div>


									<!-- SE TIVER ANEXO SALVO -->
									<div class="form-group col-xs-12 col-sm-12 col-md-8 col-lg-9 exibirAnexado2">
										<html:text styleClass="form-control input-sm documentoAnexo2 bloqueado" styleId="anexoDocumento2" property="fornecedor.documento2.anexo" name="fornecedorForm" />
									</div>
									<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-3 text-right exibirAnexado2">
										<a style="padding: 0px 6px; font-size: 18px;" href="${contextPath}/temp/${fornecedorForm.fornecedor.documento2.prefixoAnexo}/${fornecedorForm.fornecedor.documento2.anexo}" target="_blank" class="btn btn-success btn-xs" title="Visualizar">
											<i class="fa fa-eye"></i>
										</a>
										<button style="padding: 0px 6px; font-size: 18px;" type="button" id="removerAnexo21" class="btn btn-danger btn-xs removerAnexo2" title="Remover">
											<i class="fa fa-trash"></i>
										</button>
									</div>

									<!-- ANEXOS UPLOAD -->
									<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12 exibirAnexar2">
										<html:file styleClass="form-control input-sm anexos" styleId="anexo2File" property="anexo2File" name="fornecedorForm" />
										<button type="button" id="removerAnexo22" class="btn btn-success btn-sm cor-sistema removerAnexo2">
											<i class="fa fa-close"></i>
											Limpar Arquivo Anexo
										</button>
									</div>
								</div>
							</div>
						</div>

						<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
							<div class="alert alert-success" role="alert" style="height: 172px">
								<div class="row">
									<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<label>Descrição</label>
										<html:text styleClass="form-control input-sm documentoAnexo3" styleId="descricaoDocumento3" property="fornecedor.documento3.descricao" name="fornecedorForm" />
										<html:hidden styleClass="documentoAnexo3" styleId="idDocumento3" property="fornecedor.documento3.id" name="fornecedorForm" />
									</div>


									<!-- SE TIVER ANEXO SALVO -->
									<div class="form-group col-xs-12 col-sm-12 col-md-8 col-lg-9 exibirAnexado3">
										<html:text styleClass="form-control input-sm documentoAnexo3 bloqueado" styleId="anexoDocumento3" property="fornecedor.documento3.anexo" name="fornecedorForm" />
									</div>
									<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-3 text-right exibirAnexado3">
										<a style="padding: 0px 6px; font-size: 18px;" href="${contextPath}/temp/${fornecedorForm.fornecedor.documento3.prefixoAnexo}/${fornecedorForm.fornecedor.documento3.anexo}" target="_blank" class="btn btn-success btn-xs" title="Visualizar">
											<i class="fa fa-eye"></i>
										</a>
										<button style="padding: 0px 6px; font-size: 18px;" type="button" id="removerAnexo31" class="btn btn-danger btn-xs removerAnexo3" title="Remover">
											<i class="fa fa-trash"></i>
										</button>
									</div>

									<!-- ANEXOS UPLOAD -->
									<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12 exibirAnexar3">
										<html:file styleClass="form-control input-sm anexos" styleId="anexo3File" property="anexo3File" name="fornecedorForm" />
										<button type="button" id="removerAnexo32" class="btn btn-success btn-sm cor-sistema removerAnexo3">
											<i class="fa fa-close"></i>
											Limpar Arquivo Anexo
										</button>
									</div>
								</div>
							</div>
						</div>

						<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
							<div class="alert alert-success" role="alert" style="height: 172px">
								<div class="row">
									<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<label>Descrição</label>
										<html:text styleClass="form-control input-sm documentoAnexo4" styleId="descricaoDocumento4" property="fornecedor.documento4.descricao" name="fornecedorForm" />
										<html:hidden styleClass="documentoAnexo4" styleId="idDocumento4" property="fornecedor.documento4.id" name="fornecedorForm" />
									</div>


									<!-- SE TIVER ANEXO SALVO -->
									<div class="form-group col-xs-12 col-sm-12 col-md-8 col-lg-9 exibirAnexado4">
										<html:text styleClass="form-control input-sm documentoAnexo4 bloqueado" styleId="anexoDocumento4" property="fornecedor.documento4.anexo" name="fornecedorForm" />
									</div>
									<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-3 text-right exibirAnexado4">
										<a style="padding: 0px 6px; font-size: 18px;" href="${contextPath}/temp/${fornecedorForm.fornecedor.documento4.prefixoAnexo}/${fornecedorForm.fornecedor.documento4.anexo}" target="_blank" class="btn btn-success btn-xs" title="Visualizar">
											<i class="fa fa-eye"></i>
										</a>
										<button style="padding: 0px 6px; font-size: 18px;" type="button" id="removerAnexo41" class="btn btn-danger btn-xs removerAnexo4" title="Remover">
											<i class="fa fa-trash"></i>
										</button>
									</div>

									<!-- ANEXOS UPLOAD -->
									<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12 exibirAnexar4">
										<html:file styleClass="form-control input-sm anexos" styleId="anexo4File" property="anexo4File" name="fornecedorForm" />
										<button type="button" id="removerAnexo42" class="btn btn-success btn-sm cor-sistema removerAnexo4">
											<i class="fa fa-close"></i>
											Limpar Arquivo Anexo
										</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

		</html:form>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$("#juridico").css('display', 'none');
		$("#fisico").css('display', 'none');

		/* Foco inicial */
		$("#natureza").focus();

		/* Setando NOTNULL nos campos*/
		$(".nome").addClass("obrigatorio");
		$(".numeroDocumentoIdentificacao").addClass("obrigatorio");
		$(".ativo").addClass("obrigatorio");

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$(".nome").attr("maxlength", 100);
		$(".numeroDocumentoIdentificacao").attr("maxlength", 18);
		$(".email").attr("maxlength", 50);
		$(".logradouro").attr("maxlength", 100);
		$(".numero").attr("maxlength", 5);
		$(".bairro").attr("maxlength", 100);
		$(".cep").attr("maxlength", 12);
		$(".cidade").attr("maxlength", 50);
		$(".estado").attr("maxlength", 2);
		$(".complemento").attr("maxlength", 50);

		/* Setando os placeholder dos campos*/
		$(".nome").attr("placeholder", "Nome");
		$(".email").attr("placeholder", "E-mail");
		$(".logradouro").attr("placeholder", "Logradouro");
		$(".numero").attr("placeholder", "Nº");
		$(".bairro").attr("placeholder", "Bairro");
		$(".cep").attr("placeholder", "CEP");
		$(".cidade").attr("placeholder", "Cidade");
		$(".estado").attr("placeholder", "Estado");
		$(".complemento").attr("placeholder", "Complemento");

		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_fornecedor").attr("autocomplete", "off");

		$('#form_fornecedor').on('submit', function(e) {
			return false;
		});

		$('#natureza').change(function() {
			mascaraCPFCNPJ();
		});

		mascaraCPFCNPJ();

		function mascaraCPFCNPJ() {
			if ($('#natureza').val() == 'PF') {
				$("#natureza").val('PF');
				$("#fisico").css('display', 'block');
				$("#juridico").css('display', 'none');

				/* Setando NOTNULL nos campos*/
				$("#numeroDocumentoIdentificacaoFisico").addClass("obrigatorio");
				$("#nomeFisico").addClass("obrigatorio");
				//$("#emailFisico").addClass("obrigatorio");
				//$("#telefoneCelularFisico").addClass("obrigatorio");

				/* Tirando NOTNULL dos campos*/
				$("#numeroDocumentoIdentificacaoJuridico").removeClass("obrigatorio");
				$("#nomeJuridico").removeClass("obrigatorio");
				//$("#emailJuridico").removeClass("obrigatorio");
				//$("#telefoneCelularJuridico").removeClass("obrigatorio");				

				$("#info").css('display', 'none');
			} else if ($('#natureza').val() == 'PJ') {
				/* $("#numeroDocumentoIdentificacaoJuridico").mask("00.000.000/0000-00", {
					placeholder : "00.000.000/0000-00",
					clearIfNotMatch : true
				}); */
				$("#natureza").val('PJ');
				$("#juridico").css('display', 'block');
				$("#fisico").css('display', 'none');

				/* Setando NOTNULL nos campos*/
				$("#numeroDocumentoIdentificacaoJuridico").addClass("obrigatorio");
				$("#nomeJuridico").addClass("obrigatorio");
				//$("#emailJuridico").addClass("obrigatorio");
				//$("#telefoneCelularJuridico").addClass("obrigatorio");

				/* Tirando NOTNULL dos campos*/
				$("#numeroDocumentoIdentificacaoFisico").removeClass("obrigatorio");
				$("#nomeFisico").removeClass("obrigatorio");
				//$("#emailFisico").removeClass("obrigatorio");
				//$("#telefoneCelularFisico").removeClass("obrigatorio");

				$("#info").css('display', 'none');
			} else {

				$("#juridico").css('display', 'none');
				$("#fisico").css('display', 'none');

				$("#numeroDocumentoIdentificacao").unmask();
				$("#numeroDocumentoIdentificacao").attr("placeholder", "Por favor escolha o Natureza do Cliente");

				$("#info").css('display', 'block');
			}

			recarregarObrigatorios();
		}

		$('.numeroDocumentoIdentificacao').keyup(function() {
			if ($('#numeroDocumentoIdentificacao').val() == null || $('#numeroDocumentoIdentificacao').val() == '') {

			}
		});
		$('.numeroDocumentoIdentificacao').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'fornecedor.numeroDocumentoIdentificacao',
			params : {
				'fornecedor.natureza' : function() {
					return $('#natureza').val();
				}
			},
			serviceUrl : '${contextPath}/restrito/admin/fornecedor.src?method=selecionarPessoaAutoComplete',
			onSelect : function(suggestion) {
				$('.numeroDocumentoIdentificacao').val(null);

				//executarComSubmit('form_fornecedor', 'buscarPessoaByNumeroDocumentoIdentificacao');
			},
			formatResult : function(suggestion, currentValue) {
				return formatResultAutoCompleteHightLigth(suggestion.nomeExibir, currentValue);
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					//$('.numeroDocumentoIdentificacao').val(null);
				}
			}
		});
	});
</script>
