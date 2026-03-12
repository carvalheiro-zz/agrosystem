<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!-- CAMPOS DE CADASTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -30px; margin-bottom: -15px;">
		<h1 class="page-header cabecalho_pagina">
			<i class="fa fa-graduation-cap" style="font-size: 26px;"></i>
			Cadastrar Setores
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Cadastro dos Setores
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<div class="row">
	<!-- Define o tamanho geral da setor -->
	<div class="col-md-offset-1 col-lg-offset-2 col-xs-12 col-sm-12 col-md-10 col-lg-8">
		<html:form styleId="form_setor" action="restrito/sistema/setor" method="post">
			<html:hidden property="method" value="empty" />

			<div class="panel sombra">

				<!-- INICIO FORMULARIO -->
				<div class="panel-body">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

							<div class="row">

								<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<html:text styleClass="form-control input-lg bloqueado text-center" styleId="nomeCompleto" property="setor.nomeCompleto" name="setorForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-8 col-md-8 col-lg-8">
									<label>Nome</label>
									<html:text styleClass="form-control input-sm focoInicial montarNomeCompleto" styleId="nome" property="setor.nome" name="setorForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-4 col-md-4 col-lg-4">
									<label>SubSetor</label>
									<html:text styleClass="form-control input-sm montarNomeCompleto" styleId="subSetor" property="setor.subSetor" name="setorForm" />
								</div>

							</div>

						</div>
					</div>
				</div>
				<div class="panel-footer">
					<div class="row">
						<!-- BOTOES -->
						<logic:notPresent property="setor.id" name="setorForm">
							<logic:equal name="setorForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.setor.inserir)">
								<div class="form-group col-xs-12 col-sm-3 col-md-3 col-lg-3" style="margin-bottom: 0px;">
									<button type="submit" id="inserir" class="btn btn-success btn-sm cor-sistema btn-block">
										<i class="fa fa-save"></i>
										Inserir
									</button>
								</div>
							</logic:equal>
						</logic:notPresent>
						<logic:present property="setor.id" name="setorForm">
							<logic:equal name="setorForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.setor.alterar)">
								<div class="form-group col-xs-12 col-sm-3 col-md-3 col-lg-3" style="margin-bottom: 0px;">
									<button type="submit" id="alterar" class="btn btn-success btn-sm cor-sistema btn-block">
										<i class="fa fa-edit"></i>
										Alterar
									</button>
								</div>
							</logic:equal>
						</logic:present>

						<div class="form-group ol-xs-12 col-sm-3 col-md-3 col-lg-3" style="margin-bottom: 0px;">
							<button type="button" id="limpar" class="btn btn-success btn-sm cor-sistema btn-block">
								<i class="glyphicon glyphicon-erase"></i>
								Limpar
							</button>
						</div>

						<logic:equal name="setorForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.setor.filtrar)">
							<div class="form-group col-xs-12 col-sm-3 col-md-3 col-lg-3" style="margin-bottom: 0px;">
								<button type="button" id="listagem" class="btn btn-success btn-sm cor-sistema btn-block">
									<i class="fa fa-search"></i>
									Listagem
								</button>
							</div>
						</logic:equal>

					</div>
				</div>
				<!-- TERMINO FORMULARIO -->
				<!-- /.panel-body -->

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
		$("#nome").addClass("obrigatorio");
		//$("#subSetor").addClass("obrigatorio");

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#nome").prop("maxlength", 50);
		$("#subSetor").prop("maxlength", 50);

		/* Setando os placeholder dos campos*/
		$("#nome").prop("placeholder", "Nome");
		$("#subSetor").prop("placeholder", "SubSetor");

		/* EVENTOS */

		// Desliga o auto-complete da pagina
		$("#form_setor").prop("autocomplete", "off");

		$('#form_setor').on('submit', function(e) {
			abrirModalProcessando();
		});

		$('#inserir').click(function() {
			executar('form_setor', 'inserir');
		});

		$('#alterar').click(function() {
			executar('form_setor', 'alterar');
		});

		$('#limpar').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_setor', 'limpar');
		});

		$('#listagem').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_setor', 'abrirListagem');
		});
		
		$('.montarNomeCompleto').keyup(function() {
			montarNomeCompleto();
		});
		function montarNomeCompleto() {
			
			var nome = $('#nome').val();
			var subSetor = $('#subSetor').val();
			
			if(subSetor != null && subSetor != ''){
				$('#nomeCompleto').val( nome + ' - ' + subSetor );
				return;
			}
			
			$('#nomeCompleto').val( nome );
		}
	});
</script>