<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>


<!-- CAMPOS DE CADASTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -30px; margin-bottom: -15px;">
		<h1 class="page-header cabecalho_pagina">
			<i class="fa fa-graduation-cap" style="font-size: 26px;"></i>
			Cadastrar Clientes
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Cadastro dos Clientes
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<logic:present property="cliente.id" name="clienteForm">
	<div class="row">
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px;">
			<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-user-plus"></i>
				&nbsp;${clienteForm.cliente.nomeUsuarioCriacao }
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
			<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-calendar-plus-o"></i>
				&nbsp;${clienteForm.cliente.dataOcorrenciaCriacao}
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
			<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-refresh"></i>
				&nbsp;${clienteForm.cliente.nomeUsuarioAlteracao}
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-left: 0px;">
			<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-calendar-o"></i>
				&nbsp;${clienteForm.cliente.dataOcorrenciaAlteracao}
			</div>
		</div>
	</div>
</logic:present>

<div class="row">
	<!-- Define o tamanho geral da cliente -->
	<div class="col-md-offset-0 col-lg-offset-0 col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel sombra">

			<!-- INICIO FORMULARIO -->
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_cliente" action="restrito/admin/cliente" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12" style="display: none;">
									<label>Empresa</label>
									<logic:equal value="true" name="usuarioSessaoPOJO" property="isADM" scope="session">
										<html:select styleClass="form-control input-sm" styleId="empresa" property="cliente.empresa.id" name="clienteForm">
											<html:optionsCollection name="clienteForm" property="comboEmpresas" label="label" value="value" />
										</html:select>
									</logic:equal>
									<logic:equal value="false" name="usuarioSessaoPOJO" property="isADM" scope="session">
										<html:text styleClass="form-control input-sm bloqueado" styleId="empresaView" property="cliente.empresa.nomeFantasia" name="clienteForm" />
										<html:hidden styleId="empresa" property="cliente.empresa.id" name="clienteForm" />
									</logic:equal>
								</div>

								<div class="form-group col-xs-12 col-sm-6 col-md-3 col-lg-2">
									<label>Natureza do Cliente</label>
									<html:select styleClass="form-control input-sm" styleId="natureza" property="cliente.natureza" name="clienteForm">
										<html:option value="">Selecione</html:option>
										<html:option value="PF">Pessoa Fisica</html:option>
										<html:option value="PJ">Pessoa Juridica</html:option>
									</html:select>
								</div>
								<div class="form-group col-xs-12 col-sm-6 col-md-9 col-lg-10" id="info">
									<div class="alert alert-info" style="margin-bottom: 0px;font-size: 16px;">
										<strong>
											<i class="fa fa-hand-o-left" style="font-size: 24px"></i>
											&nbsp;
										</strong>
										Informe se o cliente é Fisico ou Juridico.
									</div>
								</div>
							</div>

							<div class="row" id="fisico">
								<jsp:include page="includes/pessoafisica-campos.jsp"></jsp:include>
							</div>
							<div class="row" id="juridico">
								<jsp:include page="includes/pessoajuridica-campos.jsp"></jsp:include>
							</div>

							<div class="row">
								<!-- BOTOES --> 
								<logic:notPresent property="cliente.id" name="clienteForm">
									<logic:equal name="clienteForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.cliente.inserir)">
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-2">
											<button type="submit" id="inserir" class="btn btn-success btn-sm btn-block cor-sistema">
												<i class="fa fa-save"></i>
												Inserir
											</button>
										</div>
									</logic:equal>
								</logic:notPresent>
								<logic:present property="cliente.id" name="clienteForm">
									<logic:equal name="clienteForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.cliente.alterar)">
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-2">
											<button type="submit" id="alterar" class="btn btn-success btn-sm btn-block cor-sistema">
												<i class="fa fa-edit"></i>
												Alterar
											</button>
										</div>
									</logic:equal>
								</logic:present>


								<div class="col-xs-12 col-sm-12 col-md-4 col-lg-2">
									<button type="button" id="limpar" class="btn btn-success btn-sm btn-block cor-sistema">
										<i class="glyphicon glyphicon-erase"></i>
										Limpar
									</button>
								</div>

								<logic:equal name="clienteForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.cliente.filtrar)">
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-2">
										<button type="button" id="filtrar" class="btn btn-success btn-sm btn-block cor-sistema">
											<i class="fa fa-search"></i>
											Listagem
										</button>
									</div>
								</logic:equal>

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
		$("#form_cliente").attr("autocomplete", "off");

		$('#form_cliente').on('submit', function(e) {
			abrirModalProcessando();
		});

		$('#inserir').click(function() {
			executar('form_cliente', 'inserir');
		});

		$('#alterar').click(function() {
			executar('form_cliente', 'alterar');
		});

		$('#limpar').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_cliente', 'limpar');
		});

		$('#filtrar').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_cliente', 'abrirListagem');
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
			paramName : 'cliente.numeroDocumentoIdentificacao',
			params : {
				'cliente.natureza' : function() {
					return $('#natureza').val();
				}
			},
			serviceUrl : '${contextPath}/restrito/admin/cliente.src?method=selecionarPessoaAutoComplete',
			onSelect : function(suggestion) {
				$('.numeroDocumentoIdentificacao').val(suggestion.data);

				executarComSubmit('form_cliente', 'buscarPessoaByNumeroDocumentoIdentificacao');
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