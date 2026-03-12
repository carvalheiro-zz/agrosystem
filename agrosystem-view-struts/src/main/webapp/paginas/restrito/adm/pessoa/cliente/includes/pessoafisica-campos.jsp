<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
	<label>CPF</label>
	<html:text styleClass="form-control input-sm cpf numeroDocumentoIdentificacao" styleId="numeroDocumentoIdentificacaoFisico" property="cliente.numeroDocumentoIdentificacaoFisico" name="clienteForm" />
</div>

<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
	<label>Nome</label>
	<html:text styleClass="form-control input-sm nome " styleId="nomeFisico" property="cliente.nomeFisico" name="clienteForm" />
</div>

<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
	<label>E-mail</label>
	<html:text styleClass="form-control input-sm email" styleId="emailFisico" property="cliente.pessoaFisica.email" name="clienteForm" />
</div>

<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
	<label>Status</label>
	<input type="checkbox" id="ativoCheckFisico" checked data-toggle="toggle" data-on="Ativo" data-off="Inativo" data-onstyle="primary" data-offstyle="danger" data-width="100%" data-size="small">
	<html:hidden styleId="ativoFisico" property="cliente.ativo" name="clienteForm" />
</div>

<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
	<label>Celular</label>
	<html:text styleClass="form-control input-sm cel text-center telefoneCelular" styleId="telefoneCelularFisico" property="cliente.pessoaFisica.telefone1" name="clienteForm" />
</div>

<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-2">
	<label>Fixo</label>
	<html:text styleClass="form-control input-sm fixo text-center telefoneFixo" styleId="telefoneFixoFisico" property="cliente.pessoaFisica.telefone3" name="clienteForm" />
</div>

<div class="form-group col-xs-12 col-sm-6 col-md-3 col-lg-2">
	<label>RG</label>
	<html:text styleClass="form-control input-sm text-center" styleId="rg" property="cliente.pessoaFisica.rg" name="clienteForm" />
</div>

<div class="form-group col-xs-12 col-sm-6 col-md-3 col-lg-2">
	<label>Nascimento</label>
	<html:text styleClass="form-control input-sm data text-center" styleId="dataNascimento" property="cliente.pessoaFisica.dataNascimento" name="clienteForm" />
</div>

<div class="form-group col-xs-12 col-sm-6 col-md-3 col-lg-2">
	<label>Gênero</label>
	<html:select styleClass="form-control input-sm" styleId="sexo" property="cliente.pessoaFisica.sexo" name="clienteForm">
		<html:option value="">Selecione</html:option>
		<html:option value="Feminino">Feminino</html:option>
		<html:option value="Masculino">Masculino</html:option>
	</html:select>
</div>

<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
	<div class="panel panel-success" style="border-color: #909090;">
		<div class="panel-heading cor-sistema" style="font-size: 20px; padding: 0px 15px; color: white;">Endereço</div>
		<div class="panel-body">
			<div class="row">

				<div class="form-group col-xs-12 col-sm-9 col-md-4 col-lg-4">
					<label>Logradouro</label>
					<html:text styleClass="form-control input-sm logradouro" styleId="logradouroFisico" property="cliente.pessoaFisica.endereco.logradouro" name="clienteForm" />
				</div>

				<div class="form-group col-xs-12 col-sm-3 col-md-2 col-lg-2">
					<label>Numero</label>
					<html:text styleClass="form-control input-sm text-center numero" styleId="numeroFisico" property="cliente.pessoaFisica.endereco.numero" name="clienteForm" />
				</div>

				<div class="form-group col-xs-12 col-sm-8 col-md-3 col-lg-3">
					<label>Bairro</label>
					<html:text styleClass="form-control input-sm bairro" styleId="bairroFisico" property="cliente.pessoaFisica.endereco.bairro" name="clienteForm" />
				</div>
				
				<div class="form-group col-xs-12 col-sm-8 col-md-3 col-lg-3">
					<label>Cidade</label>
					<html:text styleClass="form-control input-sm cidade" styleId="cidadeFisico" property="cliente.pessoaFisica.endereco.cidade" name="clienteForm" />
				</div>

				<div class="form-group col-xs-12 col-sm-4 col-md-2 col-lg-2">
					<label>UF</label>
					<html:select styleClass="form-control input-sm estado" styleId="estadoFisico" property="cliente.pessoaFisica.endereco.estado" name="clienteForm">
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
					<html:text styleClass="form-control cep input-sm text-center cep" styleId="cepFisico" property="cliente.pessoaFisica.endereco.cep" name="clienteForm" />
				</div>

				<div class="form-group col-xs-12 col-sm-8 col-md-5 col-lg-5">
					<label>Complemento</label>
					<html:text styleClass="form-control input-sm complemento" styleId="complementoFisico" property="cliente.pessoaFisica.endereco.complemento"
						name="clienteForm" />
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
		
		/* $('#numeroDocumentoIdentificacaoFisico').autocomplete({
			minChars : 2,
			noCache:true,
			paramName : 'cliente.pessoaFisica.cpf',
			params : {
				'cliente.tipo"' : function() {
					return $('#tipo').val();
				}
			},			
			formatResult : function(suggestion, currentValue) {
				return formatResultAutoCompleteHightLigth(suggestion.nomeExibir, currentValue);
			},
			serviceUrl : '${contextPath}/restrito/sistema/cliente.src?method=selecionarClienteFisicoAutoComplete',
			onSelect : function(suggestion) {				
				
				executarComSubmit('form_cliente', 'prePreencherDadosClienteFisico');
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					
				}
			}
		}); */
		
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
	});
</script>