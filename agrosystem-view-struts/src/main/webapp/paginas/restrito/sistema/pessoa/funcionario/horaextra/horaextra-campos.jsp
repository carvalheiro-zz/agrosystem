<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!-- CAMPOS DE CADASTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -30px; margin-bottom: -15px;">
		<h1 class="page-header cabecalho_pagina">
			<i class="fa fa-graduation-cap" style="font-size: 26px;"></i>
			Lançar hora extra
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Cadastro das horas extras
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<div class="row">
	<!-- Define o tamanho geral da horaExtra -->
	<div class="col-md-offset-1 col-lg-offset-2 col-xs-12 col-sm-12 col-md-8 col-lg-8">
		<html:form styleId="form_horaExtra" action="restrito/sistema/horaExtra" method="post">
			<html:hidden property="method" value="empty" />

			<div class="panel sombra">

				<!-- INICIO FORMULARIO -->
				<div class="panel-body">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

							<div class="row">

								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label>Data</label>
									<html:text styleClass="form-control input-sm data focoInicial" styleId="data" property="horaExtra.data" name="horaExtraForm" />
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label>Feriado?</label>
									<html:select styleClass="form-control input-sm" styleId="feriado" property="horaExtra.feriado" name="horaExtraForm">
										<html:option value="false">Não</html:option>
										<html:option value="true">Sim</html:option>
									</html:select>
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
									<label>Colaborador</label>
									<html:text styleClass="form-control input-sm " styleId="colaborador" property="horaExtra.colaborador.pessoaFisica.razaoSocial" name="horaExtraForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="colaboradorSelecionado" property="horaExtra.colaborador.id" name="horaExtraForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label>Horas</label>
									<html:text styleClass="form-control input-sm decimal" styleId="quantidadeHoras" property="horaExtra.quantidadeHoras" name="horaExtraForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<label>Tipo</label>
									<html:select styleClass="form-control input-sm" styleId="tipo" property="horaExtra.tipo" name="horaExtraForm">
										<html:option value="">Selecione...</html:option>
										<html:option value="Lançamento">Lançamento</html:option>
										<html:option value="Pagamento">Pagamento</html:option>
									</html:select>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="panel-footer">
					<div class="row">
						<!-- BOTOES -->
						<logic:notPresent property="horaExtra.id" name="horaExtraForm">
							<logic:equal name="horaExtraForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.horaExtra.inserir)">
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4" style="margin-bottom: 0px;">
									<button type="submit" id="inserir" class="btn btn-success btn-sm cor-sistema btn-block">
										<i class="fa fa-save"></i>
										Inserir
									</button>
								</div>
							</logic:equal>
						</logic:notPresent>
						<logic:present property="horaExtra.id" name="horaExtraForm">
							<logic:equal name="horaExtraForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.horaExtra.alterar)">
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4" style="margin-bottom: 0px;">
									<button type="submit" id="alterar" class="btn btn-success btn-sm cor-sistema btn-block">
										<i class="fa fa-edit"></i>
										Alterar
									</button>
								</div>
							</logic:equal>
						</logic:present>

						<div class="form-group ol-xs-12 col-sm-12 col-md-4 col-lg-4" style="margin-bottom: 0px;">
							<button type="button" id="limpar" class="btn btn-success btn-sm cor-sistema btn-block">
								<i class="glyphicon glyphicon-erase"></i>
								Limpar
							</button>
						</div>

						<logic:equal name="horaExtraForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.horaExtra.filtrar)">
							<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4" style="margin-bottom: 0px;">
								<button type="button" id="listagem" class="btn btn-success btn-sm cor-sistema btn-block">
									<i class="fa fa-search"></i>
									Listagem
								</button>
							</div>
						</logic:equal>

					</div>

					<!-- TERMINO FORMULARIO -->
					<!-- /.panel-body -->
				</div>
			</div>
			<!-- /.panel -->
		</html:form>
	</div>
	<!-- /.col-lg-12 -->
</div>


<script type="text/javascript">
	$(document).ready(function() {
		/* Foco inicial */
		$(".focoInicial").focus();

		/* Setando NOTNULL nos campos*/
		$("#colaborador").addClass("obrigatorio");
		$("#data").addClass("obrigatorio");
		$("#quantidadeHoras").addClass("obrigatorio");
		$("#tipo").addClass("obrigatorio");

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#colaborador").prop("maxlength", 50);
		$("#quantidadeHoras").prop("maxlength", 6);

		/* Setando os placeholder dos campos*/
		$("#colaborador").prop("placeholder", "Nome do colaborador");

		/* EVENTOS */

		// Desliga o auto-complete da pagina
		$("#form_horaExtra").prop("autocomplete", "off");

		$('#form_horaExtra').on('submit', function(e) {
			abrirModalProcessando();
		});

		$('#inserir').click(function() {
			executar('form_horaExtra', 'inserir');
		});

		$('#alterar').click(function() {
			executar('form_horaExtra', 'alterar');
		});

		$('#limpar').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_horaExtra', 'limpar');
		});

		$('#listagem').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_horaExtra', 'abrirListagem');
		});

		$('#colaborador').keyup(function(e) {
			if ($('#colaborador').val() == '') {
				$('#colaboradorSelecionado').val(null);
			}
		});
		$('#colaborador').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'horaExtra.colaborador.pessoaFisica.razaoSocial',
			serviceUrl : '${contextPath}/restrito/sistema/horaExtra.src?method=selecionarColaboradorAutoComplete',
			onSelect : function(suggestion) {
				$('#colaboradorSelecionado').val(suggestion.data);
			},
			onSearchComplete : function(query, suggestions) {
				$('#colaboradorSelecionado').val(null);
			}
		});
	});
</script>