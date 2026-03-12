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
				<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-4">
					<label>CNPJ</label>
					<html:text styleClass="form-control input-sm cnpj text-center" style="font-size: 20px;" styleId="cnpjModal" property="fornecedor.pessoaJuridica.cnpj" name="fornecedorForm" />
					<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
					<html:hidden styleId="fornecedorSelecionadoModal" property="fornecedor.pessoaJuridica.id" name="fornecedorForm" />
				</div>
				<div class="form-group col-xs-12 col-sm-9 col-md-6 col-lg-4">
					<label>Email</label>
					<html:text styleClass="form-control input-sm" styleId="emailModal" property="fornecedor.pessoaJuridica.email" name="fornecedorForm" />
				</div>

				<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
					<label>Razão social</label>
					<html:text styleClass="form-control input-sm" styleId="razaoSocialModal" property="fornecedor.pessoaJuridica.razaoSocial" name="fornecedorForm" />
				</div>

				<div class="form-group col-xs-12 col-sm-12 col-md-8 col-lg-6">
					<label>Nome Fantasia</label>
					<html:text styleClass="form-control input-sm" styleId="nomeFantasiaModal" property="fornecedor.pessoaJuridica.nomeFantasia" name="fornecedorForm" />
				</div>

				<div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-3">
					<label>Contato 1</label>
					<html:text styleClass="form-control input-sm cel text-center" styleId="telefoneCelularModal" property="fornecedor.pessoaJuridica.telefone1" name="fornecedorForm" />
				</div>
			</div>
			<div class="row">
				<div class="form-group col-xs-12 col-sm-9 col-md-5 col-lg-5">
					<label>Logradouro</label>
					<html:text styleClass="form-control input-sm" styleId="logradouroModal" property="fornecedor.pessoaJuridica.endereco.logradouro" name="fornecedorForm" />
				</div>

				<div class="form-group col-xs-12 col-sm-3 col-md-2 col-lg-2">
					<label>Numero</label>
					<html:text styleClass="form-control input-sm text-center" styleId="numeroModal" property="fornecedor.pessoaJuridica.endereco.numero" name="fornecedorForm" />
				</div>

				<div class="form-group col-xs-12 col-sm-8 col-md-5 col-lg-3">
					<label>Bairro</label>
					<html:text styleClass="form-control input-sm" styleId="bairroModal" property="fornecedor.pessoaJuridica.endereco.bairro" name="fornecedorForm" />
				</div>

				<div class="form-group col-xs-12 col-sm-4 col-md-3 col-lg-2">
					<label>CEP</label>
					<html:text styleClass="form-control cep input-sm text-center" styleId="cepModal" property="fornecedor.pessoaJuridica.endereco.cep" name="fornecedorForm" />
				</div>

				<div class="form-group col-xs-12 col-sm-8 col-md-5 col-lg-5">
					<label>Cidade</label>
					<html:text styleClass="form-control input-sm" styleId="cidadeModal" property="fornecedor.pessoaJuridica.endereco.cidade" name="fornecedorForm" />
				</div>

				<div class="form-group col-xs-12 col-sm-4 col-md-4 col-lg-2">
					<label>UF</label>
					<html:select styleClass="form-control input-sm" styleId="estadoModal" property="fornecedor.pessoaJuridica.endereco.estado" name="fornecedorForm">
						<html:option value="">Selecione</html:option>
						<html:option value="AC">AC</html:option>
						<html:option value="AL">AL</html:option>
						<html:option value="AP">AP</html:option>
						<html:option value="AM">AM</html:option>
						<html:option value="BA">BA</html:option>
						<html:option value="CE">CE</html:option>
						<html:option value="DF">DF</html:option>
						<html:option value="ES">ES</html:option>
						<html:option value="GO">GO</html:option>
						<html:option value="MA">MA</html:option>
						<html:option value="MT">MT</html:option>
						<html:option value="MS">MS</html:option>
						<html:option value="MG">MG</html:option>
						<html:option value="PR">PR</html:option>
						<html:option value="PB">PB</html:option>
						<html:option value="PA">PA</html:option>
						<html:option value="PE">PE</html:option>
						<html:option value="PI">PI</html:option>
						<html:option value="RJ">RJ</html:option>
						<html:option value="RN">RN</html:option>
						<html:option value="RS">RS</html:option>
						<html:option value="RO">RO</html:option>
						<html:option value="RR">RR</html:option>
						<html:option value="SC">SC</html:option>
						<html:option value="SE">SE</html:option>
						<html:option value="SP">SP</html:option>
						<html:option value="TO">TO</html:option>
					</html:select>
				</div>

				<div class="form-group col-xs-12 col-sm-8 col-md-5 col-lg-5">
					<label>Complemento</label>
					<html:text styleClass="form-control input-sm" styleId="complementoModal" property="fornecedor.pessoaJuridica.endereco.complemento" name="fornecedorForm" />
				</div>
			</div>

		</html:form>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		/* Foco inicial */
		$("#cnpjModal").focus();

		/* Setando NOTNULL nos campos*/
		/* Pessoa Física */
		$("#cnpjModal").addClass("obrigatorio");
		$("#cnpjModal").addClass("obrigatorio");

		/* Pessoa */
		$("#razaoSocialModal").addClass("obrigatorio");
		$("#razaoSocialModal").addClass("obrigatorioModal");
		$("#emailModal").addClass("obrigatorio");
		$("#emailModal").addClass("obrigatorioModal");
		$("#telefoneCelularModal").addClass("obrigatorio");
		$("#telefoneCelularModal").addClass("obrigatorioModal");

		/* Logradouro */
		$("#logradouroModal").addClass("obrigatorio");
		$("#logradouroModal").addClass("obrigatorioModal");
		$("#numeroModal").addClass("obrigatorio");
		$("#numeroModal").addClass("obrigatorioModal");
		$("#bairroModal").addClass("obrigatorio");
		$("#bairroModal").addClass("obrigatorioModal");
		$("#cepModal").addClass("obrigatorio");
		$("#cepModal").addClass("obrigatorioModal");
		$("#cidadeModal").addClass("obrigatorio");
		$("#cidadeModal").addClass("obrigatorioModal");
		$("#estadoModal").addClass("obrigatorio");
		$("#estadoModal").addClass("obrigatorioModal");
		
		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		/* Pessoa Física */
		$("#cnpjModal").attr("maxlength", 18);

		/* Pessoa */
		$("#razaoSocialModal").attr("maxlength", 70);
		$("#telefoneCelularModal").attr("maxlength", 20);
		$("#email").attr("maxlength", 50);

		/* Logradouro */
		$("#logradouroModal").attr("maxlength", 100);
		$("#numeroModal").attr("maxlength", 5);
		$("#bairroModal").attr("maxlength", 100);
		$("#cepModal").attr("maxlength", 12);
		$("#cidadeModal").attr("maxlength", 50);
		$("#estadoModal").attr("maxlength", 2);
		$("#complementoModal").attr("maxlength", 50);

		/* Setando os placeholder dos campos*/
		/* Pessoa Física */
		$("#cnpjModal").attr("placeholder", "CNPJ");

		/* Pessoa */
		$("#razaoSocialModal").attr("placeholder", "Nome");
		$("#telefoneCelularModal").attr("placeholder", "Telefone 1");
		$("#emailModal").attr("placeholder", "E-mail");

		/* Endereço */
		$("#logradouroModal").attr("placeholder", "Rua / Avenida");
		$("#numeroModal").attr("placeholder", "Nº");
		$("#bairroModal").attr("placeholder", "Bairro");
		$("#cepModal").attr("placeholder", "CEP");
		$("#cidadeModal").attr("placeholder", "Cidade");
		$("#estadoModal").attr("placeholder", "Estado");
		$("#complementoModal").attr("placeholder", "Complemento");

		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_fornecedor").prop("autocomplete", "off");

		$('#form_fornecedor').on('submit', function(e) {
			return false;
		});

		$('#cnpjModal').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'fornecedor.pessoaJuridica.cnpj',
			serviceUrl : '${contextPath}/restrito/admin/fornecedor.src?method=selecionarPessoaJuridicaAutoComplete',
			onSelect : function(suggestion) {
				
			}
		});
	});
</script>
