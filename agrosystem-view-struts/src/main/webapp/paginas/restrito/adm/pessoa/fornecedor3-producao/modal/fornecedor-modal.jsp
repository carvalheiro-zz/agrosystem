<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>


<!-- CAMPOS DE CADASTRO -->
<div class="row">
	<jsp:include page="../../../../adm/alert-modal/alert-modal.jsp"></jsp:include>

	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<html:form styleId="form_fornecedor" action="restrito/admin/fornecedor" method="post">
			<html:hidden property="method" value="empty" />

			<div class="row">
				<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12" style="display: none;">
					<label>Empresa</label>
					<logic:equal value="true" name="usuarioSessaoPOJO" property="isADM" scope="session">
						<html:select styleClass="form-control input-sm" styleId="empresa" property="fornecedor.empresa.id" name="fornecedorForm">
							<html:optionsCollection name="fornecedorForm" property="comboEmpresas" label="label" value="value" />
						</html:select>
					</logic:equal>
					<logic:equal value="false" name="usuarioSessaoPOJO" property="isADM" scope="session">
						<html:text styleClass="form-control input-sm bloqueado" styleId="empresaView" property="fornecedor.empresa.nomeFantasia" name="fornecedorForm" />
						<html:hidden styleId="empresa" property="fornecedor.empresa.id" name="fornecedorForm" />
					</logic:equal>
				</div>

				<div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-3">
					<label>Natureza do Cliente</label>
					<html:select styleClass="form-control input-sm" styleId="natureza" property="fornecedor.natureza" name="fornecedorForm">
						<html:option value="">Selecione</html:option>
						<html:option value="PF">Pessoa Fisica</html:option>
						<html:option value="PJ">Pessoa Juridica</html:option>
					</html:select>
				</div>
				<div class="form-group col-xs-12 col-sm-6 col-md-8 col-lg-9" id="info">
					<div class="alert alert-info" style="margin-bottom: 0px; font-size: 16px;">
						<strong>
							<i class="fa fa-hand-o-left" style="font-size: 24px"></i>
							&nbsp;
						</strong>
						Informe se o fornecedor é Fisico ou Juridico.
					</div>
				</div>
			</div>

			<div class="row" id="fisico">
				<jsp:include page="../includes/pessoafisica-campos.jsp"></jsp:include>
			</div>
			<div class="row" id="juridico">
				<jsp:include page="../includes/pessoajuridica-campos.jsp"></jsp:include>
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
