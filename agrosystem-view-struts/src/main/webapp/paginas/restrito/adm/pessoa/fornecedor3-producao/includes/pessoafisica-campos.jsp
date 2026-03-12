<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
	<label>CPF</label>
	<html:text styleClass="form-control input-sm cpf numeroDocumentoIdentificacao" styleId="numeroDocumentoIdentificacaoFisico" property="fornecedor.numeroDocumentoIdentificacaoFisico" name="fornecedorForm" />
</div>

<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
	<label>Nome</label>
	<html:text styleClass="form-control input-sm nome " styleId="nomeFisico" property="fornecedor.nomeFisico" name="fornecedorForm" />
</div>

<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
	<label>E-mail</label>
	<html:text styleClass="form-control input-sm email" styleId="emailFisico" property="fornecedor.emailFisico" name="fornecedorForm" />
</div>

<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
	<label>Status</label>
	<input type="checkbox" id="ativoCheckFisico" checked data-toggle="toggle" data-on="Ativo" data-off="Inativo" data-onstyle="primary" data-offstyle="danger" data-width="100%" data-size="small">
	<html:hidden styleId="ativoFisico" property="fornecedor.ativoFisico" name="fornecedorForm" />
</div>

<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
	<label>Celular</label>
	<html:text styleClass="form-control input-sm cel text-center telefoneCelular" styleId="telefoneCelularFisico" property="fornecedor.telefoneCelularFisico" name="fornecedorForm" />
</div>

<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
	<label>Fixo</label>
	<html:text styleClass="form-control input-sm fixo text-center telefoneFixo" styleId="telefoneFixoFisico" property="fornecedor.telefoneFixoFisico" name="fornecedorForm" />
</div>

<div class="form-group col-xs-12 col-sm-6 col-md-3 col-lg-2">
	<label>RG</label>
	<html:text styleClass="form-control input-sm text-center" styleId="rg" property="fornecedor.pessoaFisica.rg" name="fornecedorForm" />
</div>

<div class="form-group col-xs-12 col-sm-6 col-md-3 col-lg-2">
	<label>Nascimento</label>
	<html:text styleClass="form-control input-sm data text-center" styleId="dataNascimento" property="fornecedor.pessoaFisica.dataNascimento" name="fornecedorForm" />
</div>

<div class="form-group col-xs-12 col-sm-6 col-md-3 col-lg-2">
	<label>Gênero</label>
	<html:select styleClass="form-control input-sm" styleId="sexo" property="fornecedor.pessoaFisica.sexo" name="fornecedorForm">
		<html:option value="">Selecione</html:option>
		<html:option value="Feminino">Feminino</html:option>
		<html:option value="Masculino">Masculino</html:option>
	</html:select>
</div>

<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
	<label>Observação</label>
	<h6 class="pull-right" id="count_observacaoFisico" style="margin-top: 0px; margin-bottom: 0px;"></h6>
	<html:textarea styleClass="form-control input-sm" styleId="observacaoFisico" property="fornecedor.observacaoFisico" name="fornecedorForm" rows="2" />
</div>

<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
	<div class="panel panel-success" style="border-color: #909090;">
		<div class="panel-heading cor-sistema" style="font-size: 20px; padding: 0px 15px; color: white;">Endereço</div>
		<div class="panel-body">
			<div class="row">

				<div class="form-group col-xs-12 col-sm-9 col-md-4 col-lg-4">
					<label>Logradouro</label>
					<html:text styleClass="form-control input-sm logradouro" styleId="logradouroFisico" property="fornecedor.pessoaFisica.endereco.logradouro" name="fornecedorForm" />
				</div>

				<div class="form-group col-xs-12 col-sm-3 col-md-2 col-lg-2">
					<label>Numero</label>
					<html:text styleClass="form-control input-sm text-center numero" styleId="numeroFisico" property="fornecedor.pessoaFisica.endereco.numero" name="fornecedorForm" />
				</div>

				<div class="form-group col-xs-12 col-sm-8 col-md-3 col-lg-3">
					<label>Bairro</label>
					<html:text styleClass="form-control input-sm bairro" styleId="bairroFisico" property="fornecedor.pessoaFisica.endereco.bairro" name="fornecedorForm" />
				</div>
				
				<div class="form-group col-xs-12 col-sm-8 col-md-3 col-lg-3">
					<label>Cidade</label>
					<html:text styleClass="form-control input-sm cidade" styleId="cidadeFisico" property="fornecedor.pessoaFisica.endereco.cidade" name="fornecedorForm" />
				</div>

				<div class="form-group col-xs-12 col-sm-4 col-md-2 col-lg-2">
					<label>UF</label>
					<html:select styleClass="form-control input-sm estado" styleId="estadoFisico" property="fornecedor.pessoaFisica.endereco.estado" name="fornecedorForm">
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
					<html:text styleClass="form-control cep input-sm text-center cep" styleId="cepFisico" property="fornecedor.pessoaFisica.endereco.cep" name="fornecedorForm" />
				</div>

				<div class="form-group col-xs-12 col-sm-8 col-md-5 col-lg-5">
					<label>Complemento</label>
					<html:text styleClass="form-control input-sm complemento" styleId="complementoFisico" property="fornecedor.pessoaFisica.endereco.complemento"
						name="fornecedorForm" />
				</div>
			</div>

		</div>
	</div>
</div>



<script type="text/javascript">
	$(document).ready(function() {

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#rg").attr("maxlength", 15);
		$("#dataNascimento").attr("maxlength", 10);
		$("#sexo").attr("maxlength", 9);

		/* Setando os placeholder dos campos*/
		$("#numeroDocumentoIdentificacaoFisico").attr("placeholder", "CPF");
		$("#rg").attr("placeholder", "RG");
		$("#dataNascimento").attr("placeholder", "Nascimento");
		$("#sexo").attr("placeholder", "Sexo");

		$('#ativoCheckFisico').change(function() {
			$('#ativoFisico').val($(this).prop('checked'));
		});

		function preencherCkecksFisico() {
			if ($('#ativoFisico').val() == 'false') {
				$('#ativoCheckFisico').bootstrapToggle('off')
			} else {
				$('#ativoCheckFisico').bootstrapToggle('on')
			}
		}
		preencherCkecksFisico();

		var count_observacaoFisico_max = 1000;
		$('#count_observacaoFisico').html(count_observacaoFisico_max + ' restantes');
		$('#observacaoFisico').keyup(function() {
			gerenciarContadorCaracteresObservacaoFisico();
		});
		function gerenciarContadorCaracteresObservacaoFisico() {
			var text_length = $('#observacaoFisico').val().length;
			var text_remaining = count_observacaoFisico_max - text_length;
			$('#count_observacaoFisico').html(text_remaining + ' restantes');
		}
		
		$("#dataNascimento").focusout(function() {
			validarData('dataNascimento');
		});
	});
</script>