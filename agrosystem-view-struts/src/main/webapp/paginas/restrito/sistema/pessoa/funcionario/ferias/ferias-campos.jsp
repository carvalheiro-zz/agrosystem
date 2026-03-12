<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!-- CAMPOS DE CADASTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -30px; margin-bottom: -15px;">
		<h1 class="page-header cabecalho_pagina">
			<i class="fa fa-graduation-cap" style="font-size: 26px;"></i>
			Lançar registros de férias
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Cadastro dos registros de férias
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<div class="row">
	<!-- Define o tamanho geral da ferias -->
	<div class="col-md-offset-1 col-lg-offset-2 col-xs-12 col-sm-12 col-md-6 col-lg-6">
		<html:form styleId="form_ferias" action="restrito/sistema/ferias" method="post">
			<html:hidden property="method" value="empty" />

			<div class="panel sombra">

				<!-- INICIO FORMULARIO -->
				<div class="panel-body">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

							<div class="row">
							
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<label>Tipo</label>
									<html:select styleClass="form-control input-sm focoInicial" styleId="tipo" property="ferias.tipo" name="feriasForm" >
										<html:option value="">Selecione...</html:option>
										<html:option value="Adquirida">Adquirida</html:option>
										<html:option value="Cumprida">Cumprida</html:option>
										<html:option value="Vendida">Vendida</html:option>
									</html:select>
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4" id="divData">
									<label id="labelData">Data</label>
									<html:text styleClass="form-control input-sm data" styleId="data" property="ferias.data" name="feriasForm" />
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4" id="divDataCumprimentoTermino">
									<label id="labelDataCumprimentoTermino">Data cumprimento final</label>
									<html:text styleClass="form-control input-sm data" styleId="dataCumprimentoTermino" property="ferias.dataCumprimentoTermino" name="feriasForm" />
								</div>
							</div>
							<div class="row">
								
								<div class="form-group col-xs-12 col-sm-12 col-md-8 col-lg-8" id="divColaborador">
									<label>Colaborador</label>
									<html:text styleClass="form-control input-sm" styleId="colaborador" property="ferias.colaborador.pessoaFisica.razaoSocial" name="feriasForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="colaboradorSelecionado" property="ferias.colaborador.id" name="feriasForm" />
								</div>
								
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4" id="divQuantidadeHoras">
									<label>Horas</label>
									<html:text styleClass="form-control input-sm decimal" styleId="quantidadeHoras" property="ferias.quantidadeHoras" name="feriasForm" />
								</div>
								
							</div>
						</div>
					</div>
				</div>
				<div class="panel-footer">
					<div class="row">
						<!-- BOTOES -->
						<logic:notPresent property="ferias.id" name="feriasForm">
							<logic:equal name="feriasForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.ferias.inserir)">
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4 divBotoes" style="margin-bottom: 0px;">
									<button type="submit" id="inserir" class="btn btn-success btn-sm cor-sistema btn-block">
										<i class="fa fa-save"></i>
										Inserir
									</button>
								</div>
							</logic:equal>
						</logic:notPresent>
						<logic:present property="ferias.id" name="feriasForm">
							<logic:equal name="feriasForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.ferias.alterar)">
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4 divBotoes" style="margin-bottom: 0px;">
									<button type="submit" id="alterar" class="btn btn-success btn-sm cor-sistema btn-block">
										<i class="fa fa-edit"></i>
										Alterar
									</button>
								</div>
							</logic:equal>
						</logic:present>

						<div class="form-group ol-xs-12 col-sm-12 col-md-4 col-lg-4 divBotoes" style="margin-bottom: 0px;">
							<button type="button" id="limpar" class="btn btn-success btn-sm cor-sistema btn-block">
								<i class="glyphicon glyphicon-erase"></i>
								Limpar
							</button>
						</div>

						<logic:equal name="feriasForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.ferias.filtrar)">
							<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4" style="margin-bottom: 0px;">
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
		$("#form_ferias").prop("autocomplete", "off");

		$('#form_ferias').on('submit', function(e) {
			abrirModalProcessando();
		});

		$('#inserir').click(function() {
			executar('form_ferias', 'inserir');
		});

		$('#alterar').click(function() {
			executar('form_ferias', 'alterar');
		});

		$('#limpar').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_ferias', 'limpar');
		});

		$('#listagem').click(function() {
			abrirModalProcessando();
			executarComSubmit('form_ferias', 'abrirListagem');
		});
		
		$('#colaborador').keyup(function(e) {
			if ($('#colaborador').val() == '') {
				$('#colaboradorSelecionado').val(null);
			}
		});
		$('#colaborador').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'ferias.colaborador.pessoaFisica.razaoSocial',
			serviceUrl : '${contextPath}/restrito/sistema/ferias.src?method=selecionarColaboradorAutoComplete',
			onSelect : function(suggestion) {
				$('#colaboradorSelecionado').val(suggestion.data);
			},
			onSearchComplete : function(query, suggestions) {
				$('#colaboradorSelecionado').val(null);
			}
		});
		
		$('#tipo').change(function() {		

			$('#divColaborador').css('display','none');
			$('#divData').css('display','none');
			$('#divDataCumprimentoTermino').css('display','none');
			$('#divQuantidadeHoras').css('display','none');
			$('.divBotoes').css('display','none');
						
			if( $(this).val() == 'Adquirida' ){
				
				$('#divColaborador').css('display','block');				
				$('#divQuantidadeHoras').css('display','block');
				$('.divBotoes').css('display','block');
				
				$('#divData').css('display','block');
				$('#divDataCumprimentoTermino').css('display','none');
				
				$('#labelData').text('Data vencimento');
				$('#labelDataCumprimentoTermino').text('Data cumprimento final');
				
				$('#dataCumprimentoTermino').removeClass('obrigatorio');
				
				$("#data").focus();
				
			}else if( $(this).val() == 'Cumprida' ){
				
				$('#divColaborador').css('display','block');				
				$('#divQuantidadeHoras').css('display','block');
				$('.divBotoes').css('display','block');
				
				$('#divData').css('display','block');
				$('#divDataCumprimentoTermino').css('display','block');
				
				$('#labelData').text('Data cumprimento inicial');
				$('#labelDataCumprimentoTermino').text('Data cumprimento final');
				
				$('#dataCumprimentoTermino').addClass('obrigatorio');
				
				$("#data").focus();
				
			} else if( $(this).val() == 'Vendida' ){
				
				$('#divColaborador').css('display','block');				
				$('#divQuantidadeHoras').css('display','block');
				$('.divBotoes').css('display','block');
				
				$('#divData').css('display','block');
				$('#divDataCumprimentoTermino').css('display','none');
				
				$('#labelData').text('Data venda');
				$('#labelDataCumprimentoTermino').text('Data cumprimento final');
				
				$('#dataCumprimentoTermino').removeClass('obrigatorio');
				
				$("#data").focus();
			}
			
			carregarMascaras();		
			
		});
		
		$('#tipo').change();
	});
</script>