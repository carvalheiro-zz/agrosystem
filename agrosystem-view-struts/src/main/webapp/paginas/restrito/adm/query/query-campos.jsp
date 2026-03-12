<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!-- CAMPOS DE CADASTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -30px; margin-bottom: -15px;">
		<h1 class="page-header cabecalho_pagina">
			<i class="fa fa-graduation-cap" style="font-size: 26px;"></i>
			Cadastrar Queries
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Cadastro das Queries
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<logic:present property="query.id" name="queryForm">
	<div class="row">
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px;">
			<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-user-plus"></i>
				&nbsp;${queryForm.query.nomeUsuarioCriacao }
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
			<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-calendar-plus-o"></i>
				&nbsp;${queryForm.query.dataOcorrenciaCriacao}
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
			<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-refresh"></i>
				&nbsp;${queryForm.query.nomeUsuarioAlteracao}
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-left: 0px;">
			<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-calendar-o"></i>
				&nbsp;${queryForm.query.dataOcorrenciaAlteracao}
			</div>
		</div>
	</div>
</logic:present>

<div class="row">
	<!-- Define o tamanho geral da query -->
	<div class="col-md-offset-0 col-lg-offset-0 col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<html:form styleId="form_query" action="restrito/sistema/query" method="post">
			<html:hidden property="method" value="empty" />
			<div class="panel sombra">

				<!-- INICIO FORMULARIO -->
				<div class="panel-body">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<a data-toggle="collapse" style="opacity: 0.7;" id="modalInfo" class="close">
								<i class="fa fa-exclamation-circle"></i>
							</a>
							<ul class="nav nav-tabs" id="tabsReceita">
								<li class="active">
									<a data-toggle="tab" href="#abaCadastro">Cadastro</a>
								</li>
								<li>
									<a data-toggle="tab" href="#abaUsuario">Usuários com acesso</a>
								</li>
								<li>
									<a data-toggle="tab" href="#abaHTML">Campos e Resposta da Query</a>
								</li>
								<li>
									<a data-toggle="tab" href="#abaResultadoQuery">Query View</a>
								</li>

							</ul>
							<div class="tab-content">
								<div id="abaCadastro" class="tab-pane fade in active" style="margin-top: 20px;">

									<div class="row">
										<%-- <div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
											<label>Nome arquivo Tela</label>
											<html:text styleClass="form-control input-sm" styleId="arquivoJSP" property="query.arquivoJSP" name="queryForm" />
										</div> --%>

										<logic:notPresent property="query.id" name="queryForm">
											<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
												<label>Nome</label>
												<html:text styleClass="form-control input-sm" styleId="nome" property="query.nome" name="queryForm" />
											</div>
										</logic:notPresent>
										<logic:present property="query.id" name="queryForm">
											<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
												<label>Nome</label>
												<html:text styleClass="form-control input-sm bloqueado" styleId="nome" property="query.nome" name="queryForm" />
											</div>
										</logic:present>

										<div class="form-group col-xs-12 col-sm-12 col-md-9 col-lg-9">
											<label>Titulo da Tela</label>
											<html:text styleClass="form-control input-sm" styleId="tituloTela" property="query.tituloTela" name="queryForm" />
										</div>
										<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<label>Finalidade</label>
											<h6 class="pull-right" id="count_finalidade"></h6>
											<html:textarea styleClass="form-control input-sm" styleId="finalidade" property="query.finalidade" name="queryForm" rows="2" />
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<label>Query</label>
											<h6 class="pull-right" id="count_query"></h6>
											<html:textarea styleClass="form-control input-sm" styleId="query" property="query.query" name="queryForm" rows="30" />
										</div>
									</div>									
								</div>
								<div id="abaUsuario" class="tab-pane fade" style="margin-top: 20px;">
									<div class="row">
										<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-6" id="id-usuarios">
											<jsp:include page="ajax/painel-usuarios-ajax.jsp"></jsp:include>
										</div>
										
									</div>
								</div>
								<div id="abaHTML" class="tab-pane fade" style="margin-top: 20px;">
									<div class="row">

										<%-- <div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<label>HTML gerado</label>
											<h6 class="pull-right" id="count_htmlTela"></h6>
											<html:textarea styleClass="form-control input-sm bloqueado" styleId="htmlTela" property="query.htmlTela" name="queryForm" rows="20" />
										</div> --%>

										<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<label>Campos</label>
											<h6 class="pull-right" id="count_campos"></h6>
											<html:textarea styleClass="form-control input-sm bloqueado" styleId="campos" property="query.campos" name="queryForm" rows="2" />
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<label>Resposta</label>
											<h6 class="pull-right" id="count_campos"></h6>
											<html:textarea styleClass="form-control input-sm bloqueado" styleId="campos" property="query.colunas" name="queryForm" rows="2" />
										</div>

									</div>
								</div>

								<div id="abaResultadoQuery" class="tab-pane fade" style="margin-top: 20px;">
									<div class="row">
										<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12" id="id_resultadoQuery_ajax">
											<%-- <jsp:include page="ajax/painel-parcelas-ajax.jsp"></jsp:include> --%>
											<%-- <jsp:include page="telasgeradas/${queryForm.query.arquivoJSP}"></jsp:include> --%>
											<jsp:include page="abas/principal-campos.jsp"></jsp:include>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="panel-footer">
					<div class="row">
						<!-- BOTOES -->
						<logic:notPresent property="query.id" name="queryForm">
							<logic:equal name="queryForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.query.inserir)">
								<div class="col-xs-12 col-sm-3 col-md-4 col-lg-2" style="margin-bottom: 0px;">
									<button type="submit" id="inserir" class="btn btn-success btn-sm cor-sistema btn-block">
										<i class="fa fa-save"></i>
										Inserir
									</button>
								</div>
							</logic:equal>
						</logic:notPresent>
						<logic:present property="query.id" name="queryForm">
							<logic:equal name="queryForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.query.alterar)">
								<div class="col-xs-12 col-sm-3 col-md-4 col-lg-2" style="margin-bottom: 0px;">
									<button type="submit" id="alterar" class="btn btn-success btn-sm cor-sistema btn-block">
										<i class="fa fa-edit"></i>
										Alterar
									</button>
								</div>
							</logic:equal>
						</logic:present>

						<div class="ol-xs-12 col-sm-3 col-md-4 col-lg-2" style="margin-bottom: 0px;">
							<button type="button" id="limpar" class="btn btn-success btn-sm cor-sistema btn-block">
								<i class="glyphicon glyphicon-erase"></i>
								Limpar
							</button>
						</div>

						<logic:equal name="queryForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.query.filtrar)">
							<div class="col-xs-12 col-sm-3 col-md-4 col-lg-2" style="margin-bottom: 0px;">
								<button type="button" id="filtrar" class="btn btn-success btn-sm cor-sistema btn-block">
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
		</html:form>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>


<script type="text/javascript">
	$(document)
			.ready(
					function() {
						/* Foco inicial */
						$("#nome").focus();

						/* Setando NOTNULL nos campos*/
						$("#nome").addClass("obrigatorio");
						$("#tituloTela").addClass("obrigatorio");
						$("#finalidade").addClass("obrigatorio");
						$("#query").addClass("obrigatorio");

						/* Setando os tamanhos maximos dos campos baseando-se no PO*/
						$("#nome").attr("maxlength", 100);
						$("#tituloTela").attr("maxlength", 50);
						$("#finalidade").attr("maxlength", 5000);
						$("#query").attr("maxlength", 10000);

						/* Setando os placeholder dos campos*/
						$("#nome").attr("placeholder", "Nome da Query");
						$("#tituloTela").attr("placeholder", "Titulo da Tela");
						$("#finalidade").attr("placeholder", "Qual a finalidade dessa Query");
						$("#query").attr("placeholder", "Queries");

						/* EVENTOS */
						var count_query_max = 10000;
						$('#count_query').html(count_query_max + ' restantes');
						$('#query').keyup(function() {
							gerenciarContadorCaracteresQuery();
						});
						function gerenciarContadorCaracteresQuery() {
							var text_length = $('#query').val().length;
							var text_remaining = count_query_max - text_length;
							$('#count_query').html(text_remaining + ' restantes');
						}

						gerenciarContadorCaracteresQuery();

						var count_finalidade_max = 5000;
						$('#count_finalidade').html(count_finalidade_max + ' restantes');
						$('#finalidade').keyup(function() {
							gerenciarContadorCaracteresFinalidade();
						});
						function gerenciarContadorCaracteresFinalidade() {
							var text_length = $('#finalidade').val().length;
							var text_remaining = count_finalidade_max - text_length;
							$('#count_finalidade').html(text_remaining + ' restantes');
						}

						gerenciarContadorCaracteresFinalidade();

						var count_campos_max = 5000;
						$('#count_campos').html(count_campos_max + ' restantes');
						$('#campos').keyup(function() {
							gerenciarContadorCaracteresCampos();
						});
						function gerenciarContadorCaracteresCampos() {
							var text_length = $('#campos').val().length;
							var text_remaining = count_campos_max - text_length;
							$('#count_campos').html(text_remaining + ' restantes');
						}

						gerenciarContadorCaracteresCampos();
						
						// Desliga o auto-complete da pagina
						$("#form_query").attr("autocomplete", "off");

						$('#form_query').on('submit', function(e) {
							abrirModalProcessando();
						});

						$('#inserir').click(function() {
							executar('form_query', 'inserir');
						});

						$('#alterar').click(function() {
							executar('form_query', 'alterar');
						});

						$('#limpar').click(function() {
							abrirModalProcessando();
							executarComSubmit('form_query', 'limpar');
						});

						$('#filtrar').click(function() {
							abrirModalProcessando();
							executarComSubmit('form_query', 'abrirListagem');
						});

						$('#modalInfo')
								.click(
										function() {
											BootstrapDialog
													.show({
														size : BootstrapDialog.SIZE_WIDE,
														title : 'Layout de criação dos parametros da Query',
														message : '<div class="row">'
																+ '<div class="col-xs-12">'
																+ '<p style="margin-bottom: 0px; margin-left: 10px;">	1 - Os parametros devem iniciar com : (dois pontos), seguir com o nome do Campo separado por _ (underline), mais o tipo do dado a ser informado.</p>'
																+ '<p style="margin-bottom: 0px; margin-left: 10px;">	<b>Exemplo:</b> :Data_solicitação_data</p>'
																+ '<p style="margin-bottom: 0px; margin-left: 10px;">	<b>PS:</b> Para os campos IN usar (in) como tipo. <b>Exemplo:</b>:Codigo_solicitação_in</p>'
																+ '<br />'
																+ '<p style="margin-bottom: 0px; margin-left: 10px;">	<b>Tipos e Exemplos:</b></p>'
																+ '<p style="margin-bottom: 0px; margin-left: 10px;">	Texto :Nome_texto</p>'
																+ '<p style="margin-bottom: 0px; margin-left: 10px;">	Texto com Like :Nome_textoLike</p>'
																+ '<p style="margin-bottom: 0px; margin-left: 10px;">	Inteiros :Numero_inteiro</p>'
																+ '<p style="margin-bottom: 0px; margin-left: 10px;">	Decimais :Altura_decimal</p>'
																+ '<p style="margin-bottom: 0px; margin-left: 10px;">	Datas :Data_Cadastro_data</p>'
																+ '<p style="margin-bottom: 0px; margin-left: 10px;">	Horas :Hora_Cadastro_hora</p>'
																+ '<p style="margin-bottom: 0px; margin-left: 10px;">	Datas e horas (dia/mes/ano hora:min:sec):Data_Cadastro_dataHora</p>'
																+ '<p style="margin-bottom: 0px; margin-left: 10px;">	Vários números (IN) :Codigos_Contratos_in</p>'
																+ '<p style="margin-bottom: 0px; margin-left: 10px;">	Vários textos (IN) :Nomes_tin</p>'
																+ '<br />'
																+ '<p style="margin-bottom: 0px; margin-left: 10px;">	<b>## Exemplo de Query ##</b></p>'
																+ '<p style="margin-bottom: 0px; margin-left: 10px; font-size:14px;">	SELECT <br />* <br />FROM <br />clientes c <br />where <br />c.nome = :Nome_texto <br />AND c.idade = :Idade_inteiro <br />AND c.codigo in ( :Codigos_entre_in )<br />AND c.nome in ( :Nomes_entre_tin )<br />AND c.dataCadastro = :Data_Cadastro_data<br />AND c.horaCadastro = :Hora_Cadastro_hora<br />AND c.dataHoraAtualizacao = :Data_Hora_Atualizacao_dataHora <br />AND c.dataNascimento <br />between :Data_Nascimento_inicial_data AND :Data_Nascimento_final_data</p>'
																+ '</div>' + '</div>',
														closable : true,
														type : BootstrapDialog.TYPE_SUCCESS,
														buttons : [ {
															label : 'Obrigado',
															action : function(dialogRef) {

																dialogRef.close();
															}
														} ]
													});
										});

					});
</script>