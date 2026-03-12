<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
	<label>Nome</label>
	<html:text styleClass="form-control input-sm nome" styleId="nomeJuridico" property="fornecedor.nomeJuridico" name="fornecedorForm" />
</div>
<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
	<label>CNPJ</label>
	<html:text styleClass="form-control input-sm cnpj numeroDocumentoIdentificacao" styleId="numeroDocumentoIdentificacaoJuridico" property="fornecedor.numeroDocumentoIdentificacaoJuridico" name="fornecedorForm" />
</div>
<div class="form-group col-xs-12 col-sm-6 col-md-3 col-lg-3">
	<label>I.E</label>
	<html:text styleClass="form-control input-sm text-center" styleId="inscricaoEstadual" property="fornecedor.pessoaJuridica.inscricaoEstadual" name="fornecedorForm" />
</div>
<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
	<label>E-mail</label>
	<html:text styleClass="form-control input-sm email" styleId="emailJuridico" property="fornecedor.emailJuridico" name="fornecedorForm" />
</div>
<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
	<label>Fixo</label>
	<html:text styleClass="form-control input-sm fixo text-center telefoneFixo" styleId="telefoneFixoJuridico" property="fornecedor.telefoneFixoJuridico" name="fornecedorForm" />
</div>
<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
	<label>Celular</label>
	<html:text styleClass="form-control input-sm cel text-center telefoneCelular" styleId="telefoneCelularJuridico" property="fornecedor.telefoneCelularJuridico" name="fornecedorForm" />
</div>
<div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-4">
	<label>Representante</label>
	<html:text styleClass="form-control input-sm" styleId="representante" property="fornecedor.pessoaJuridica.representante" name="fornecedorForm" />
</div>
<div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-4">
	<label>Contato representante</label>
	<html:text styleClass="form-control input-sm" styleId="telefoneRepresentante" property="fornecedor.pessoaJuridica.telefoneRepresentante" name="fornecedorForm" />
</div>
<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
	<label>Status</label>
	<input type="checkbox" id="ativoCheckJuridico" checked data-toggle="toggle" data-on="Ativo" data-off="Inativo" data-onstyle="primary" data-offstyle="danger" data-width="100%" data-size="small">
	<html:hidden styleId="ativoJuridico" property="fornecedor.ativoJuridico" name="fornecedorForm" />
</div>
<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
	<label>Observação</label>
	<h6 class="pull-right" id="count_observacaoJuridico" style="margin-top: 0px; margin-bottom: 0px;"></h6>
	<html:textarea styleClass="form-control input-sm" styleId="observacaoJuridico" property="fornecedor.observacaoJuridico" name="fornecedorForm" rows="3" />
</div>

<%-- <div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<label>E-mail CC</label>
									<html:text styleClass="form-control input-sm " styleId="emailsCC" property="fornecedorAvulso.emailsCC" name="fornecedorAvulsoForm" />
									<span id="helpEmailsCC" class="help-block">Ex: email1@gmail.com; email2@gmail.com; email3@gmail.com;</span>
								</div> --%>

<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
	<div class="panel panel-success" style="border-color: #909090;">
		<div class="panel-heading cor-sistema" style="font-size: 20px; padding: 0px 15px; color: white;">Endereço</div>
		<div class="panel-body">
			<div class="row">

				<div class="form-group col-xs-12 col-sm-9 col-md-4 col-lg-4">
					<label>Logradouro</label>
					<html:text styleClass="form-control input-sm logradouro" styleId="logradouroJuridico" property="fornecedor.pessoaJuridica.endereco.logradouro" name="fornecedorForm" />
				</div>

				<div class="form-group col-xs-12 col-sm-3 col-md-2 col-lg-2">
					<label>Numero</label>
					<html:text styleClass="form-control input-sm text-center numero" styleId="numeroJuridico" property="fornecedor.pessoaJuridica.endereco.numero" name="fornecedorForm" />
				</div>

				<div class="form-group col-xs-12 col-sm-8 col-md-3 col-lg-3">
					<label>Bairro</label>
					<html:text styleClass="form-control input-sm bairro" styleId="bairroJuridico" property="fornecedor.pessoaJuridica.endereco.bairro" name="fornecedorForm" />
				</div>

				<div class="form-group col-xs-12 col-sm-8 col-md-3 col-lg-3">
					<label>Cidade</label>
					<html:text styleClass="form-control input-sm cidade" styleId="cidadeJuridico" property="fornecedor.pessoaJuridica.endereco.cidade" name="fornecedorForm" />
				</div>

				<div class="form-group col-xs-12 col-sm-4 col-md-2 col-lg-2">
					<label>UF</label>
					<html:select styleClass="form-control input-sm estado" styleId="estadoJuridico" property="fornecedor.pessoaJuridica.endereco.estado" name="fornecedorForm">
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

				<div class="form-group col-xs-12 col-sm-4 col-md-2 col-lg-2">
					<label>CEP</label>
					<html:text styleClass="form-control cep input-sm text-center cep" styleId="cepJuridico" property="fornecedor.pessoaJuridica.endereco.cep" name="fornecedorForm" />
				</div>

				<div class="form-group col-xs-12 col-sm-8 col-md-5 col-lg-5">
					<label>Complemento</label>
					<html:text styleClass="form-control input-sm complemento" styleId="complementoJuridico" property="fornecedor.pessoaJuridica.endereco.complemento" name="fornecedorForm" />
				</div>
			</div>

		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#inscricaoEstadual").attr("maxlength", 20);
		$("#representante").attr("maxlength", 70);
		$("#telefoneRepresentante").attr("maxlength", 20);
		$("#limiteCreditoJuridico").attr("maxlength", 10);

		/* Setando os placeholder dos campos*/
		$("#numeroDocumentoIdentificacaoJuridico").attr("placeholder", "CNPJ");
		$("#inscricaoEstadual").attr("placeholder", "I.M");
		$("#representante").attr("placeholder", "Nome representante");
		$("#telefoneRepresentante").attr("placeholder", "Contato representante");

		$('#ativoCheckJuridico').change(function() {
			$('#ativoJuridico').val($(this).prop('checked'));
		});		

		function preencherCkecksJuridico() {
			if ($('#ativoJuridico').val() == 'false') {
				$('#ativoCheckJuridico').bootstrapToggle('off')
			} else {
				$('#ativoCheckJuridico').bootstrapToggle('on')
			}
		}
		preencherCkecksJuridico();

		var count_observacaoJuridico_max = 1000;
		$('#count_observacaoJuridico').html(count_observacaoJuridico_max + ' restantes');
		$('#observacaoJuridico').keyup(function() {
			gerenciarContadorCaracteresObservacaoJuridico();
		});
		function gerenciarContadorCaracteresObservacaoJuridico() {
			var text_length = $('#observacaoJuridico').val().length;
			var text_remaining = count_observacaoJuridico_max - text_length;
			$('#count_observacaoJuridico').html(text_remaining + ' restantes');
		}
		
		gerenciarContadorCaracteresObservacaoJuridico();
	});
</script>