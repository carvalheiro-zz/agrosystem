<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<style>
.kv-avatar .file-preview-frame, .kv-avatar .file-preview-frame:hover {
	margin: 0;
	padding: 0;
	border: none;
	box-shadow: none;
	text-align: center;
}

.kv-avatar .file-input {
	display: table-cell;
	max-width: 220px;
}
</style>

<!-- UPLOAD DA IMAGEM -->
<link href="${contextPath}/assets/bootstrap/bootstrap-fileinput-master/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />
<script src="${contextPath}/assets/bootstrap/bootstrap-fileinput-master/js/plugins/canvas-to-blob.min.js" type="text/javascript"></script>
<script src="${contextPath}/assets/bootstrap/bootstrap-fileinput-master/js/fileinput.min.js" type="text/javascript"></script>
<script src="${contextPath}/assets/bootstrap/bootstrap-fileinput-master/js/fileinput_locale_pt-BR.js"></script>

<script src="${contextPath}/assets/bootstrap/bower_components/bootstrap-filestyle-1.2.1/src/bootstrap-filestyle.min.js"></script>

<!-- CAMPOS DE CADASTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -30px; margin-bottom: -15px;">
		<h1 class="page-header cabecalho_pagina">
			<i class="fa fa-users" style="font-size: 26px;"></i>
			Colaborador
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Cadastro
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<logic:present property="funcionario.id" name="funcionarioForm">
	<div class="row">
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px;">
			<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-user-plus"></i>
				&nbsp;${funcionarioForm.funcionario.nomeUsuarioCriacao }
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
			<div class="alert alert-info" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-calendar-plus-o"></i>
				&nbsp;${funcionarioForm.funcionario.dataOcorrenciaCriacao}
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-right: 0px; padding-left: 0px;">
			<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-refresh"></i>
				&nbsp;${funcionarioForm.funcionario.nomeUsuarioAlteracao}
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3" style="padding-left: 0px;">
			<div class="alert alert-warning" role="alert" style="padding-bottom: 5px; padding-top: 5px;">
				<i class="fa fa-calendar-o"></i>
				&nbsp;${funcionarioForm.funcionario.dataOcorrenciaAlteracao}
			</div>
		</div>
	</div>
</logic:present>

<div class="row">
	<!-- Define o tamanho geral da funcionario -->
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel sombra">

			<!-- INICIO FORMULARIO -->
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_funcionario" action="restrito/admin/funcionario" method="post" enctype="multipart/form-data">
							<html:hidden property="method" value="empty" />
							<html:hidden styleId="imagem" property="funcionario.pessoaFisica.imagem" name="funcionarioForm" />

							<ul class="nav nav-tabs">
								<li class="active">
									<a data-toggle="tab" href="#dados">Dados</a>
								</li>
								<!-- <li>
									<a data-toggle="tab" href="#endereco">Endereço</a>
								</li> -->
								<li>
									<a data-toggle="tab" href="#foto">Foto</a>
								</li>
								<li>
									<a data-toggle="tab" href="#anexo">Documentos</a>
								</li>
							</ul>
							<div class="tab-content">
								<div id="dados" class="tab-pane fade in active" style="margin-top: 20px;">
									<div class="row">
										<div class="form-group text-center col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<div id="kv-avatar-errors" class="center-block" style="width: 800px; display: none"></div>
										</div>
									</div>

									<div class="row">
										<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<label>Empresa</label>
											<logic:equal value="true" name="usuarioSessaoPOJO" property="isADM" scope="session">
												<html:select styleClass="form-control input-sm" styleId="empresa" property="funcionario.empresa.id" name="funcionarioForm">
													<html:optionsCollection name="funcionarioForm" property="comboEmpresas" label="label" value="value" />
												</html:select>
											</logic:equal>
											<logic:equal value="false" name="usuarioSessaoPOJO" property="isADM" scope="session">
												<html:text styleClass="form-control input-sm bloqueado" styleId="empresaView" property="funcionario.empresa.nomeFantasia" name="funcionarioForm" />
												<html:hidden styleId="empresa" property="funcionario.empresa.id" name="funcionarioForm" />
											</logic:equal>
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<label>Nome</label>
											<html:text styleClass="form-control input-sm" styleId="razaoSocial" property="funcionario.pessoaFisica.razaoSocial" name="funcionarioForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
											<label>CPF</label>
											<html:text styleClass="form-control input-sm cpf text-center" style="font-size: 24px;" styleId="cpf" property="funcionario.pessoaFisica.cpf" name="funcionarioForm" />
											<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
											<html:hidden styleId="funcionarioSelecionado" property="funcionario.pessoaFisica.id" name="funcionarioForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-6 col-md-3 col-lg-3">
											<label>RG</label>
											<html:text styleClass="form-control input-sm text-center" styleId="rg" property="funcionario.pessoaFisica.rg" name="funcionarioForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-9 col-md-6 col-lg-4">
											<label>Email</label>
											<html:text styleClass="form-control input-sm" styleId="email" property="funcionario.pessoaFisica.email" name="funcionarioForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-6 col-md-2 col-lg-2">
											<label>Fixo</label>
											<html:text styleClass="form-control input-sm fixo text-center" styleId="telefone3" property="funcionario.pessoaFisica.telefone3" name="funcionarioForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-6 col-md-2 col-lg-2">
											<label>Celular</label>
											<html:text styleClass="form-control input-sm cel text-center" styleId="telefone1" property="funcionario.pessoaFisica.telefone1" name="funcionarioForm" />
										</div>

										<%-- <div class="form-group col-xs-12 col-sm-6 col-md-2 col-lg-2">
											<label>Celular 2</label>
											<html:text styleClass="form-control input-sm cel text-center" styleId="telefone2" property="funcionario.pessoaFisica.telefone2" name="funcionarioForm" />
										</div> --%>

										<div class="form-group col-xs-12 col-sm-6 col-md-2 col-lg-2">
											<label>Nascimento</label>
											<html:text styleClass="form-control input-sm data text-center" styleId="dataNascimento" property="funcionario.pessoaFisica.dataNascimento" name="funcionarioForm" />
										</div>

										<%-- <div class="form-group col-xs-12 col-sm-6 col-md-2 col-lg-2">
											<label>Sexo</label>
											<html:select styleClass="form-control input-sm" styleId="sexo" property="funcionario.pessoaFisica.sexo" name="funcionarioForm">
												<html:option value="">Selecione</html:option>
												<html:option value="Feminino">Feminino</html:option>
												<html:option value="Masculino">Masculino</html:option>
											</html:select>
										</div> --%>

										<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<label>Observação</label>
											<h6 class="pull-right" id="count_observacao"></h6>
											<html:textarea styleClass="form-control input-sm" styleId="observacao" property="funcionario.pessoaFisica.observacao" name="funcionarioForm" rows="2" />
										</div>

										<%-- <div class="form-group col-xs-3 col-sm-3 col-md-3 col-lg-2">
											<label>Status</label>
											<input type="checkbox" id="ativoCheck" checked data-toggle="toggle" data-on="<i class='fa fa-check-square-o' style='font-size: 20px;'></i>" data-off="<i class='fa fa-square-o' style='font-size: 20px;'></i>" data-onstyle="primary"
												data-offstyle="danger" data-width="100%" data-size="mini">
											<html:hidden styleId="ativo" property="funcionario.ativo" name="funcionarioForm" />
										</div> --%>
									</div>
									<div class="row">
										<%-- <div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-3">
											<label>Código</label>
											<html:text styleClass="form-control input-sm text-center" styleId="codigo" property="funcionario.codigo" name="funcionarioForm" />
										</div> --%>
										<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
											<label>Matrícula</label>
											<html:text styleClass="form-control input-sm text-center" styleId="matricula" property="funcionario.matricula" name="funcionarioForm" />
										</div>
										<div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-3">
											<label>Admissão</label>
											<html:text styleClass="form-control input-sm data text-center" styleId="admissao" property="funcionario.dataAdmissao" name="funcionarioForm" />
										</div>
										<div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-2">
											<label>Demissão</label>
											<html:text styleClass="form-control input-sm data text-center" styleId="demissao" property="funcionario.dataDemissao" name="funcionarioForm" />
										</div>
										<div class="form-group col-xs-12 col-sm-6 col-md-6 col-lg-2">
											<label>Salário</label>
											<html:text styleClass="form-control input-sm text-center dinheiro" styleId="salario" property="funcionario.salario" name="funcionarioForm" />
										</div>
										<%-- <div class="form-group col-xs-12 col-sm-6 col-md-6 col-lg-2">
											<label>Comissão</label>
											<html:text styleClass="form-control input-sm text-center decimal" styleId="comissao" property="funcionario.comissao" name="funcionarioForm" />
										</div> --%>

										<div class="form-group col-xs-12 col-sm-6 col-md-3 col-lg-3">
											<label>% Divisão de Lucro</label>
											<html:text styleClass="form-control input-sm text-center decimal" styleId="percentualDivisaoLucro" property="funcionario.percentualDivisaoLucro" name="funcionarioForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
											<label>Status</label>
											<input type="checkbox" id="ativoCheck" checked data-toggle="toggle" data-on="Ativo" data-off="Inativo" data-onstyle="primary" data-offstyle="danger" data-width="100%" data-size="small">
											<html:hidden styleId="ativo" property="funcionario.ativo" name="funcionarioForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
											<label>Assinatura eletronica</label>
											<html:text styleClass="form-control input-md text-center autoCompleteOff senha" styleId="assinaturaEletronicaGerencia" property="funcionario.assinaturaEletronicaGerencia"
												name="funcionarioForm" />
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12" id="usuario_ajax">
											<jsp:include page="ajax/funcionario-usuario-ajax.jsp"></jsp:include>
										</div>
									</div>

									<div class="row">
										<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<div class="panel panel-success" style="border-color: #909090;">
												<div class="panel-heading cor-sistema" style="font-size: 20px; padding: 0px 15px; color: white;">Endereço</div>
												<div class="panel-body">
													<div class="row">

														<div class="form-group col-xs-12 col-sm-9 col-md-4 col-lg-4">
															<label>Logradouro</label>
															<html:text styleClass="form-control input-sm " styleId="logradouro" property="funcionario.pessoaFisica.endereco.logradouro" name="funcionarioForm" />
														</div>

														<div class="form-group col-xs-12 col-sm-3 col-md-2 col-lg-2">
															<label>Numero</label>
															<html:text styleClass="form-control input-sm text-center numero" styleId="numero" property="funcionario.pessoaFisica.endereco.numero" name="funcionarioForm" />
														</div>

														<div class="form-group col-xs-12 col-sm-8 col-md-3 col-lg-3">
															<label>Bairro</label>
															<html:text styleClass="form-control input-sm bairro" styleId="bairro" property="funcionario.pessoaFisica.endereco.bairro" name="funcionarioForm" />
														</div>

														<div class="form-group col-xs-12 col-sm-8 col-md-3 col-lg-3">
															<label>Cidade</label>
															<html:text styleClass="form-control input-sm cidade" styleId="cidade" property="funcionario.pessoaFisica.endereco.cidade" name="funcionarioForm" />
														</div>

														<div class="form-group col-xs-12 col-sm-4 col-md-2 col-lg-2">
															<label>UF</label>
															<html:select styleClass="form-control input-sm estado" styleId="estado" property="funcionario.pessoaFisica.endereco.estado" name="funcionarioForm">
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
															<html:text styleClass="form-control cep input-sm text-center cep" styleId="cep" property="funcionario.pessoaFisica.endereco.cep" name="funcionarioForm" />
														</div>

														<div class="form-group col-xs-12 col-sm-8 col-md-5 col-lg-5">
															<label>Complemento</label>
															<html:text styleClass="form-control input-sm complemento" styleId="complemento" property="funcionario.pessoaFisica.endereco.complemento" name="funcionarioForm" />
														</div>
													</div>

												</div>
											</div>
										</div>
									</div>














								</div>

								<div id="foto" class="tab-pane fade" style="margin-top: 20px;">
									<div class="row">
										<div class="form-group text-center col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<div id="kv-avatar-errors" class="center-block" style="width: 800px; display: none"></div>
										</div>
										<div class="form-group col-lg-offset-4 col-md-offset-4 col-xs-12 col-sm-12 col-md-8 col-lg-8">
											<div class="kv-avatar center-block">
												<input id="foto1" name="file" type="file" class="file-loading">
											</div>
										</div>
									</div>
								</div>
								<div id="anexo" class="tab-pane fade" style="margin-top: 20px;">
									<div class="row">
										<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<div class="alert alert-success" role="alert" style="height: 172px">
												<div class="row">
													<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
														<label>Descrição</label>
														<html:text styleClass="form-control input-sm documentoAnexo1" styleId="descricaoDocumento1" property="funcionario.pessoaFisica.documento1.descricao" name="funcionarioForm" />
														<html:hidden styleClass="documentoAnexo1" styleId="idDocumento1" property="funcionario.pessoaFisica.documento1.id" name="funcionarioForm" />
													</div>


													<!-- SE TIVER ANEXO SALVO -->
													<div class="form-group col-xs-12 col-sm-12 col-md-8 col-lg-9 exibirAnexado1">
														<html:text styleClass="form-control input-sm documentoAnexo1 bloqueado" styleId="anexoDocumento1" property="funcionario.pessoaFisica.documento1.anexo" name="funcionarioForm" />
													</div>
													<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-3 text-right exibirAnexado1">
														<a style="padding: 0px 6px; font-size: 18px;"
															href="${contextPath}/temp/${funcionarioForm.funcionario.pessoaFisica.documento1.prefixoAnexo}/${funcionarioForm.funcionario.pessoaFisica.documento1.anexo}" target="_blank"
															class="btn btn-success btn-xs" title="Visualizar">
															<i class="fa fa-eye"></i>
														</a>
														<button style="padding: 0px 6px; font-size: 18px;" type="button" id="removerAnexo11" class="btn btn-danger btn-xs removerAnexo1" title="Remover">
															<i class="fa fa-trash"></i>
														</button>
													</div>

													<!-- ANEXOS UPLOAD -->
													<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12 exibirAnexar1">
														<html:file styleClass="form-control input-sm anexos" styleId="anexo1File" property="anexo1File" name="funcionarioForm" />
														<button type="button" id="removerAnexo12" class="btn btn-success btn-sm cor-sistema removerAnexo1">
															<i class="fa fa-close"></i>
															Limpar Arquivo Anexo
														</button>
													</div>
												</div>
											</div>
										</div>


										<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<div class="alert alert-success" role="alert" style="height: 172px">
												<div class="row">
													<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
														<label>Descrição</label>
														<html:text styleClass="form-control input-sm documentoAnexo2" styleId="descricaoDocumento2" property="funcionario.pessoaFisica.documento2.descricao" name="funcionarioForm" />
														<html:hidden styleClass="documentoAnexo2" styleId="idDocumento2" property="funcionario.pessoaFisica.documento2.id" name="funcionarioForm" />
													</div>


													<!-- SE TIVER ANEXO SALVO -->
													<div class="form-group col-xs-12 col-sm-12 col-md-8 col-lg-9 exibirAnexado2">
														<html:text styleClass="form-control input-sm documentoAnexo2 bloqueado" styleId="anexoDocumento2" property="funcionario.pessoaFisica.documento2.anexo" name="funcionarioForm" />
													</div>
													<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-3 text-right exibirAnexado2">
														<a style="padding: 0px 6px; font-size: 18px;"
															href="${contextPath}/temp/${funcionarioForm.funcionario.pessoaFisica.documento2.prefixoAnexo}/${funcionarioForm.funcionario.pessoaFisica.documento2.anexo}" target="_blank"
															class="btn btn-success btn-xs" title="Visualizar">
															<i class="fa fa-eye"></i>
														</a>
														<button style="padding: 0px 6px; font-size: 18px;" type="button" id="removerAnexo21" class="btn btn-danger btn-xs removerAnexo2" title="Remover">
															<i class="fa fa-trash"></i>
														</button>
													</div>

													<!-- ANEXOS UPLOAD -->
													<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12 exibirAnexar2">
														<html:file styleClass="form-control input-sm anexos" styleId="anexo2File" property="anexo2File" name="funcionarioForm" />
														<button type="button" id="removerAnexo22" class="btn btn-success btn-sm cor-sistema removerAnexo2">
															<i class="fa fa-close"></i>
															Limpar Arquivo Anexo
														</button>
													</div>
												</div>
											</div>
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<div class="alert alert-success" role="alert" style="height: 172px">
												<div class="row">
													<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
														<label>Descrição</label>
														<html:text styleClass="form-control input-sm documentoAnexo3" styleId="descricaoDocumento3" property="funcionario.pessoaFisica.documento3.descricao" name="funcionarioForm" />
														<html:hidden styleClass="documentoAnexo3" styleId="idDocumento3" property="funcionario.pessoaFisica.documento3.id" name="funcionarioForm" />
													</div>


													<!-- SE TIVER ANEXO SALVO -->
													<div class="form-group col-xs-12 col-sm-12 col-md-8 col-lg-9 exibirAnexado3">
														<html:text styleClass="form-control input-sm documentoAnexo3 bloqueado" styleId="anexoDocumento3" property="funcionario.pessoaFisica.documento3.anexo" name="funcionarioForm" />
													</div>
													<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-3 text-right exibirAnexado3">
														<a style="padding: 0px 6px; font-size: 18px;"
															href="${contextPath}/temp/${funcionarioForm.funcionario.pessoaFisica.documento3.prefixoAnexo}/${funcionarioForm.funcionario.pessoaFisica.documento3.anexo}" target="_blank"
															class="btn btn-success btn-xs" title="Visualizar">
															<i class="fa fa-eye"></i>
														</a>
														<button style="padding: 0px 6px; font-size: 18px;" type="button" id="removerAnexo31" class="btn btn-danger btn-xs removerAnexo3" title="Remover">
															<i class="fa fa-trash"></i>
														</button>
													</div>

													<!-- ANEXOS UPLOAD -->
													<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12 exibirAnexar3">
														<html:file styleClass="form-control input-sm anexos" styleId="anexo3File" property="anexo3File" name="funcionarioForm" />
														<button type="button" id="removerAnexo32" class="btn btn-success btn-sm cor-sistema removerAnexo3">
															<i class="fa fa-close"></i>
															Limpar Arquivo Anexo
														</button>
													</div>
												</div>
											</div>
										</div>

										<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<div class="alert alert-success" role="alert" style="height: 172px">
												<div class="row">
													<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
														<label>Descrição</label>
														<html:text styleClass="form-control input-sm documentoAnexo4" styleId="descricaoDocumento4" property="funcionario.pessoaFisica.documento4.descricao" name="funcionarioForm" />
														<html:hidden styleClass="documentoAnexo4" styleId="idDocumento4" property="funcionario.pessoaFisica.documento4.id" name="funcionarioForm" />
													</div>


													<!-- SE TIVER ANEXO SALVO -->
													<div class="form-group col-xs-12 col-sm-12 col-md-8 col-lg-9 exibirAnexado4">
														<html:text styleClass="form-control input-sm documentoAnexo4 bloqueado" styleId="anexoDocumento4" property="funcionario.pessoaFisica.documento4.anexo" name="funcionarioForm" />
													</div>
													<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-3 text-right exibirAnexado4">
														<a style="padding: 0px 6px; font-size: 18px;"
															href="${contextPath}/temp/${funcionarioForm.funcionario.pessoaFisica.documento4.prefixoAnexo}/${funcionarioForm.funcionario.pessoaFisica.documento4.anexo}" target="_blank"
															class="btn btn-success btn-xs" title="Visualizar">
															<i class="fa fa-eye"></i>
														</a>
														<button style="padding: 0px 6px; font-size: 18px;" type="button" id="removerAnexo41" class="btn btn-danger btn-xs removerAnexo4" title="Remover">
															<i class="fa fa-trash"></i>
														</button>
													</div>

													<!-- ANEXOS UPLOAD -->
													<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12 exibirAnexar4">
														<html:file styleClass="form-control input-sm anexos" styleId="anexo4File" property="anexo4File" name="funcionarioForm" />
														<button type="button" id="removerAnexo42" class="btn btn-success btn-sm cor-sistema removerAnexo4">
															<i class="fa fa-close"></i>
															Limpar Arquivo Anexo
														</button>
													</div>
												</div>
											</div>
										</div>







									</div>
								</div>
							</div>

							<div class="row">
								<!-- BOTOES -->
								<logic:notPresent property="funcionario.id" name="funcionarioForm">
									<logic:equal name="funcionarioForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.funcionario.inserir)">
										<div class="col-xs-12 col-sm-12 col-md-4 col-lg-2">
											<button type="submit" id="inserir" class="btn btn-success btn-sm btn-block cor-sistema">
												<i class="fa fa-save"></i>
												Inserir
											</button>
										</div>
									</logic:equal>
								</logic:notPresent>
								<logic:present property="funcionario.id" name="funcionarioForm">
									<logic:equal name="funcionarioForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.funcionario.alterar)">
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

								<logic:equal name="funcionarioForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.funcionario.filtrar)">
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
	$(document)
			.ready(
					function() {
						/* Foco inicial */
						$("#cpf").focus();
						
						/* Setando NOTNULL nos campos*/
						/* Pessoa Física */
						$("#cpf").addClass("obrigatorio");
						
						$("#empresa").addClass("obrigatorio");
						$("#empresaView").addClass("obrigatorio");
						
						/* Pessoa */
						$("#razaoSocial").addClass("obrigatorio");
						$("#email").addClass("obrigatorio");
						//$("#sexo").addClass("obrigatorio");
						
						/* Logradouro */
						$("#logradouro").addClass("obrigatorio");
						$("#numero").addClass("obrigatorio");
						$("#bairro").addClass("obrigatorio");
						$("#cep").addClass("obrigatorio");
						$("#cidade").addClass("obrigatorio");
						$("#estado").addClass("obrigatorio");
						
						/* Funcionário */
						$("#codigo").addClass("obrigatorio");
						$("#admissao").addClass("obrigatorio");
						$("#matricula").addClass("obrigatorio");
						
						/* Setando os tamanhos maximos dos campos baseando-se no PO*/
						/* Pessoa Física */
						$("#cpf").attr("maxlength", 14);
						$("#rg").attr("maxlength", 15);
						$("#comissao").attr("maxlength", 5);
						$("#percentualDivisaoLucro").attr("maxlength", 5);
						$("#salario").attr("maxlength", 10);
						$("#matricula").attr("maxlength", 10);
						$("#codigo").attr("maxlength", 10);
						$("#assinaturaEletronicaGerencia").attr("maxlength", 8);
						
						/* Pessoa */
						$("#razaoSocial").attr("maxlength", 100);
						$("#email").attr("maxlength", 50);
						
						/* Logradouro */
						$("#logradouro").attr("maxlength", 100);
						$("#numero").attr("maxlength", 5);
						$("#bairro").attr("maxlength", 100);
						$("#cep").attr("maxlength", 12);
						$("#cidade").attr("maxlength", 50);
						$("#estado").attr("maxlength", 2);
						$("#complemento").attr("maxlength", 50);
						
						/* Setando os placeholder dos campos*/
						/* Pessoa Física */
						$("#cpf").attr("placeholder", "CPF");
						$("#rg").attr("placeholder", "RG");
						$("#dataNascimento").attr("placeholder", "Data de Nascimento");
						
						/* Pessoa */
						$("#razaoSocial").attr("placeholder", "Nome");
						$("#email").attr("placeholder", "E-mail");
						
						/* Endereço */
						$("#logradouro").attr("placeholder", "Rua / Avenida");
						$("#numero").attr("placeholder", "Nº");
						$("#bairro").attr("placeholder", "Bairro");
						$("#cep").attr("placeholder", "CEP");
						$("#cidade").attr("placeholder", "Cidade");
						$("#estado").attr("placeholder", "Estado");
						$("#complemento").attr("placeholder", "Complemento");
						
						/* Funcionário */
						$("#matricula").attr("placeholder", "Matrícula");
						$("#codigo").attr("placeholder", "Código");
						
						/* EVENTOS */
						// Desliga o auto-complete da pagina
						$("#form_funcionario").attr("autocomplete", "off");
						
						$('#form_funcionario').on('submit', function(e) {
							abrirModalProcessando();
						});
						
						$('#inserir').click(function() {
							/* Usado para telas com ABAS */
							inserirComAba('form_funcionario');
						});
						
						$('#alterar').click(function() {
							/* Usado para telas com ABAS */
							alterarComAba('form_funcionario');
						});
						
						$('#limpar').click(function() {
							abrirModalProcessando();
							executarComSubmit('form_funcionario', 'limpar');
						});
						
						$('#filtrar').click(function() {
							abrirModalProcessando();
							executarComSubmit('form_funcionario', 'abrirListagem');
						});
						
						$('#gerenciarPermissoes').click(function() {
							window.location = '${contextPath}/restrito/admin/usuario.src?method=selecionar&usuarioFilter.id=${funcionarioForm.funcionario.usuario.id}';
						});
						
						var count_observacao_max = 1000;
						$('#count_observacao').html(count_observacao_max + ' restantes');
						$('#observacao').keyup(function() {
							gerenciarContadorCaracteresObservacao();
						});
						function gerenciarContadorCaracteresObservacao() {
							var text_length = $('#observacao').val().length;
							var text_remaining = count_observacao_max - text_length;
							$('#count_observacao').html(text_remaining + ' restantes');
						}
						
						$('#ativoCheck').change(function() {
							$('#ativo').val($(this).prop('checked'));
						});
						
						function preencherChecks() {
							if ($('#ativo').val() == 'false') {
								$('#ativoCheck').bootstrapToggle('off')
							} else {
								$('#ativoCheck').bootstrapToggle('on')
							}
						}
						preencherChecks();
						
						var pathTemp1 = 'remover_${funcionarioForm.funcionario.pessoaFisica.imagem}';
						pathTemp1 = pathTemp1.replace(".", "_").replace("/", "_");
						$("#foto1")
								.fileinput(
										{
											overwriteInitial : true,
											maxFileSize : 5000,
											showClose : false,
											showCaption : false,
											browseLabel : 'Selecione',
											removeLabel : '',
											browseIcon : '<i class="glyphicon glyphicon-folder-open"></i>',
											removeIcon : '<i class="glyphicon glyphicon-remove"></i>',
											removeTitle : 'Cancela ou desfaz as alterações',
											removeClass : pathTemp1 + ' btn btn-default',
											elErrorContainer : '#kv-avatar-errors',
											msgErrorClass : 'alert alert-block alert-danger',
											initialPreview : '<img src="${contextPath}/temp/${funcionarioForm.funcionario.pessoaFisica.prefixoImagem}/${funcionarioForm.funcionario.pessoaFisica.imagem}" alt="Imagem" style="width:100%">',
											defaultPreviewContent : '<img src="${contextPath}/assets/images/propria/empty.png" alt="Imagem" style="width:100%">',
											layoutTemplates : {
												main2 : '{preview} {remove} {browse}'
											},
											allowedFileExtensions : [ "jpg", "png", "gif" ]
										}).on('filecleared', function(event, data) {
									$('#imagem').val(null);
								});
						
						$(".anexos").filestyle({
							input : true,
							buttonName : "btn-primary",
							size : "sm col-xs-12 col-sm-12 col-md-12 col-lg-12",
							badge : false,
							buttonText : "Anexo",
							placeholder : "Selecione o arquivo"
						});
						
						$('.removerAnexo1').click(function() {
							$("#anexo1File").filestyle('clear');
							
							$('.documentoAnexo1').val(null);
							gerenciarExibicaoAnexos();
						});
						$('.removerAnexo2').click(function() {
							$("#anexo2File").filestyle('clear');
							
							$('.documentoAnexo2').val(null);
							gerenciarExibicaoAnexos();
						});
						$('.removerAnexo3').click(function() {
							$("#anexo3File").filestyle('clear');
							
							$('.documentoAnexo3').val(null);
							gerenciarExibicaoAnexos();
						});
						$('.removerAnexo4').click(function() {
							$("#anexo4File").filestyle('clear');
							
							$('.documentoAnexo4').val(null);
							gerenciarExibicaoAnexos();
						});
						
						/* $('#btnAdicionar').click(function() {
							var theForm = $('form[name=funcionarioForm]');
							var params = theForm.serialize();
							var actionURL = theForm.attr('action') + '?method=adicionarDocumento';
							
							$.ajax({
								type : 'POST',
								url : actionURL,
								data : params,
								success : function(data, textStatus, XMLHttpRequest) {
									
									$('#id_documentos_ajax').html(data);
									$('.documentoAdicionar').val(null);
									$("#nome").focus();
									
									$("#anexo1File").filestyle('clear');
									$("#anexo2File").filestyle('clear');
									$("#anexo3File").filestyle('clear');
									$("#anexo4File").filestyle('clear');
								},
								error : function(XMLHttpRequest, textStatus, errorThrown) {
									alert(textStatus);
								}
							});
							//executarComSubmit('form_funcionario', 'adicionarDocumento');
						}); */
						
						$('a[data-toggle="tab"]').on('hide.bs.tab', function(e) {
							var aba = $(this).attr('href');

							if (aba == '#anexo') {

								var campoVazio = false;

								if ($('#anexo1File').val() != null && $('#anexo1File').val() != '') {
									if ($('#descricaoDocumento1').val() == null || $('#descricaoDocumento1').val() == '') {

										$('#descricaoDocumento1').addClass("obrigatorio");

										campoVazio = true;

										$("#descricaoDocumento1").focus();
									}
								}

								if ($('#anexo2File').val() != null && $('#anexo2File').val() != '') {
									if ($('#descricaoDocumento2').val() == null || $('#descricaoDocumento2').val() == '') {

										$('#descricaoDocumento2').addClass("obrigatorio");

										campoVazio = true;

										$("#descricaoDocumento2").focus();
									}
								}

								if ($('#anexo3File').val() != null && $('#anexo3File').val() != '') {
									if ($('#descricaoDocumento3').val() == null || $('#descricaoDocumento3').val() == '') {

										$('#descricaoDocumento3').addClass("obrigatorio");

										campoVazio = true;

										$("#descricaoDocumento3").focus();
									}
								}

								if ($('#anexo4File').val() != null && $('#anexo4File').val() != '') {
									if ($('#descricaoDocumento4').val() == null || $('#descricaoDocumento4').val() == '') {

										$('#descricaoDocumento4').addClass("obrigatorio");

										campoVazio = true;

										$("#descricaoDocumento4").focus();
									}
								}

								recarregarObrigatorios();

								if (campoVazio) {
									return false;
								}

							}

						});
						
						$('#cpf').autocomplete({
							minChars : 2,
							paramName : 'funcionario.pessoaFisica.cpf',
							serviceUrl : '${contextPath}/restrito/admin/funcionario.src?method=selecionarPessoaFisicaAutoComplete',
							onSelect : function(suggestion) {
								//alert('You selected: ' + suggestion.value + ', ' + suggestion.data);
								$('#funcionarioSelecionado').val(suggestion.data);
								
								executarComSubmit('form_funcionario', 'buscarPessoaFisicaByCPF');
							}
						});
						
						$('#empresa').change(function() {
							var actionURL = '${contextPath}/restrito/admin/funcionario.src?method=selecionarEmpresa&funcionario.empresa.id=' + $('#empresa').val();
							$.ajax({
								type : "POST",
								url : actionURL,
								success : function(data, textStatus, XMLHttpRequest) {
								},
								error : function(XMLHttpRequest, textStatus, errorThrown) {
									alert(textStatus);
								}
							});
							
							//executarComSubmit('form_funcionario', 'selecionarEmpresa');
						});
						
						/* $('#email').blur(function() {
							if( '${funcionarioForm.funcionario.usuario.id}' == '' || '${funcionarioForm.funcionario.usuario.id}' == null ){
								var actionURL = '${contextPath}/restrito/admin/funcionario.src?method=buscarUsuarioByEmail&funcionario.pessoaFisica.email=' + $('#email').val() + '&funcionario.empresa.id=' + $('#empresa').val();
								$.ajax({
									type : "POST",
									url : actionURL,
									success : function(data, textStatus, XMLHttpRequest) {
										// we have the response
										$('#usuario_ajax').html(data);
									},
									error : function(XMLHttpRequest, textStatus, errorThrown) {
										alert(textStatus);
									}
								});
							}
						}); */

						gerenciarExibicaoAnexos();
						
						$("#dataNascimento").focusout(function() {
							validarData('dataNascimento');
						});
						$("#admissao").focusout(function() {
							validarData('admissao');
						});
						$("#demissao").focusout(function() {
							validarData('demissao');
						});
					});
	
	function gerenciarExibicaoAnexos() {
		if ($('#idDocumento1').val() == null || $('#idDocumento1').val() == '') {
			$('.exibirAnexar1').css('display', 'block');
			$('.exibirAnexado1').css('display', 'none');
		} else {
			$('.exibirAnexar1').css('display', 'none');
			$('.exibirAnexado1').css('display', 'block');
		}
		
		if ($('#idDocumento2').val() == null || $('#idDocumento2').val() == '') {
			$('.exibirAnexar2').css('display', 'block');
			$('.exibirAnexado2').css('display', 'none');
		} else {
			$('.exibirAnexar2').css('display', 'none');
			$('.exibirAnexado2').css('display', 'block');
		}
		
		if ($('#idDocumento3').val() == null || $('#idDocumento3').val() == '') {
			$('.exibirAnexar3').css('display', 'block');
			$('.exibirAnexado3').css('display', 'none');
		} else {
			$('.exibirAnexar3').css('display', 'none');
			$('.exibirAnexado3').css('display', 'block');
		}
		
		if ($('#idDocumento4').val() == null || $('#idDocumento4').val() == '') {
			$('.exibirAnexar4').css('display', 'block');
			$('.exibirAnexado4').css('display', 'none');
		} else {
			$('.exibirAnexar4').css('display', 'none');
			$('.exibirAnexado4').css('display', 'block');
		}
		
	}

	String.prototype.formatMoney = function() {
		var v = this;
		
		if (v.indexOf('.') === -1) {
			v = v.replace(/([\d]+)/, "$1,00");
		}
		
		v = v.replace(/([\d]+)\.([\d]{1})$/, "$1,$20");
		v = v.replace(/([\d]+)\.([\d]{2})$/, "$1,$2");
		v = v.replace(/([\d]+)([\d]{3}),([\d]{2})$/, "$1.$2,$3");
		
		return v;
	};
</script>