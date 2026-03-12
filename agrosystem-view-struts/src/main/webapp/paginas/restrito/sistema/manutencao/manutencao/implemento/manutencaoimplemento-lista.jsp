<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- CAMPOS DE FILTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: -30px; margin-bottom: -15px;">
		<h1 class="page-header cabecalho_pagina">
			<i class="fa fa-graduation-cap" style="font-size: 26px;"></i>
			Pesquisar Manutenção em Implementos
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Listagem das Manutenção em Implementos
			</small>
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<jsp:include page="../../../../../errors/alerts-sucesso_erro.jsp"></jsp:include>

<div class="row">

	<!-- Define o tamanho geral da tela -->
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel" style="box-shadow: 0 10px 30px rgba(0, 0, 0, 0.8); border-color: #909090;">

			<!-- INICIO CAMPOS DE PESQUISA -->
			<div class="panel-heading cor-sistema">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<html:form styleId="form_manutencaoImplemento_consulta" action="restrito/sistema/manutencaoImplemento" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<div class="form-group col-xs-12 col-sm-4 col-md-3 col-lg-2">
									<label class="text-white">Data Inicial</label>
									<html:text styleClass="form-control input-sm data text-center" styleId="dataInicial" property="dataInicial" name="manutencaoImplementoForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-4 col-md-3 col-lg-2">
									<label class="text-white">Data Final</label>
									<html:text styleClass="form-control input-sm data text-center" styleId="dataFinal" property="dataFinal" name="manutencaoImplementoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-2">
									<label class="text-white">Recibo/NF</label>
									<html:select styleClass="form-control input-sm" styleId="reciboNF" property="manutencaoImplemento.reciboOuNotaFiscal" name="manutencaoImplementoForm">
										<html:option value="">Selecione...</html:option>
										<html:option value="Recibo">Recibo</html:option>
										<html:option value="Nota Fiscal">Nota Fiscal</html:option>
									</html:select>
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-2" id="divNotaFiscal">
									<label class="text-white">Nota Fiscal</label>
									<html:text styleClass="form-control input-sm text-center" styleId="notaFiscal" property="manutencaoImplemento.notaFiscal" name="manutencaoImplementoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-2">
									<label class="text-white">Finalidade</label>
									<html:select styleClass="form-control input-sm" styleId="finalidade" property="manutencaoImplemento.finalidade" name="manutencaoImplementoForm">
										<html:option value="">Selecione...</html:option>
										<html:option value="Manutenção">Manutenção</html:option>
										<html:option value="Conserto">Conserto</html:option>
									</html:select>
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-2">
									<label class="text-white">Prestador de Serviço</label>
									<html:text styleClass="form-control input-sm" styleId="prestador" property="manutencaoImplemento.prestador.nome" name="manutencaoImplementoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="prestadorSelecionado" property="manutencaoImplemento.prestador.id" name="manutencaoImplementoForm" />
								</div>


								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<label class="text-white">Fornecedor</label>
									<html:text styleClass="form-control input-sm" styleId="fornecedor" property="manutencaoImplemento.fornecedor.nome" name="manutencaoImplementoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<label class="text-white">Implemento</label>
									<html:text styleClass="form-control input-sm" styleId="implemento" property="manutencaoImplemento.implemento.nomeCompleto" name="manutencaoImplementoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<label class="text-white">Serviço</label>
									<html:text styleClass="form-control input-sm" styleId="servico" property="manutencaoImplemento.servico.nome" name="manutencaoImplementoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label class="text-white">Atribuição de Custo</label>
									<html:select styleClass="form-control input-sm" styleId="tipo" property="manutencaoImplemento.tipo" name="manutencaoImplementoForm">
										<html:option value="">Selecione...</html:option>
										<html:option value="Safra/Setor">Safra/Setor</html:option>
										<html:option value="Tudo">Tudo</html:option>
										<html:option value="Cultura">Cultura</html:option>
									</html:select>
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label class="text-white">Safra</label>
									<html:text styleClass="form-control input-sm" styleId="safra" property="manutencaoImplemento.safra.nome" name="manutencaoImplementoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="safraSelecionada" property="manutencaoImplemento.safra.id" name="manutencaoImplementoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label class="text-white">Setor</label>
									<html:text styleClass="form-control input-sm" styleId="setor" property="manutencaoImplemento.setor.nomeCompleto" name="manutencaoImplementoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="setorSelecionado" property="manutencaoImplemento.setor.id" name="manutencaoImplementoForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label class="text-white">Cultura</label>
									<html:text styleClass="form-control input-sm" styleId="cultura" property="manutencaoImplemento.cultura.nome" name="manutencaoImplementoForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="culturaSelecionada" property="manutencaoImplemento.cultura.id" name="manutencaoImplementoForm" />
								</div>
							</div>


							<div class="row">

								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.manutencao.filtrar)">
									<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4" style="margin-bottom: 0px;">
										<button type="submit" id="pesquisar" class="btn btn-success btn-sm cor-sistema btn-block">
											<i class="fa fa-search"></i>
											Pesquisar
										</button>
									</div>
								</logic:equal>
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4" style="margin-bottom: 0px;">
									<button type="button" id="limparPesquisa" class="btn btn-success btn-sm cor-sistema btn-block">
										<i class="glyphicon glyphicon-erase"></i>
										Limpar
									</button>
								</div>
								<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.manutencao.inserir)">
									<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4" style="margin-bottom: 0px;">
										<button type="button" id="novo" class="btn btn-success btn-sm cor-sistema btn-block">
											<i class="fa fa-file-o"></i>
											Novo
										</button>
									</div>
								</logic:equal>

							</div>
						</html:form>
					</div>
				</div>
			</div>
			<!-- TERMINO CAMPOS DE PESQUISA -->

			<!-- INICIO TABELA -->
			<logic:empty name="manutencaoImplementoForm" property="manutencoes">
				<div class="row" style="margin-top: 15px;">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="alert alert-warning">Nenhum registro encontrado!</div>
					</div>
				</div>
			</logic:empty>
			<logic:notEmpty name="manutencaoImplementoForm" property="manutencoes">
				<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.manutencao.filtrar)">
					<div class="panel-body">
						<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
							<span class="label cor-sistema" style="font-size: 15px;">Total Custo: R$ ${manutencaoImplementoForm.totalValor}</span>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
							<div class="table-responsive">
								<table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
									<thead>
										<!-- CABEÇALHO DA TABELA -->
										<tr class="cor-sistema" style="color: white;">
											<th>Serviço</th>
											<th>Implemento</th>
											<th class="text-center">Data</th>
											<th class="text-center">NF</th>
											<th>Finalidade</th>
											<th>Custo</th>
											<th>Atribuição de Custo</th>
											<th>Fornecedor</th>
											<th class="text-center" style="width: 80px;">Opções</th>
										</tr>
									</thead>
									<tbody>
										<!-- TABELA -->
										<logic:iterate id="manutencaoImplementoCorrente" name="manutencaoImplementoForm" property="manutencoes" type="br.com.srcsoftware.sistema.manutencao.manutencao.ManutencaoDTO">

											<tr>
												<td onclick="selecionar('${contextPath}/restrito/sistema/manutencaoImplemento.src?method=selecionar&manutencaoImplemento.id=${manutencaoImplementoCorrente.id}')">${manutencaoImplementoCorrente.servico.nome}</td>
												<td onclick="selecionar('${contextPath}/restrito/sistema/manutencaoImplemento.src?method=selecionar&manutencaoImplemento.id=${manutencaoImplementoCorrente.id}')">${manutencaoImplementoCorrente.implemento.nomeCompleto}</td>
												<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/manutencaoImplemento.src?method=selecionar&manutencaoImplemento.id=${manutencaoImplementoCorrente.id}')">${manutencaoImplementoCorrente.dataToString}</td>

												<logic:equal value="Recibo" name="manutencaoImplementoCorrente" property="reciboOuNotaFiscal">
													<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/manutencaoImplemento.src?method=selecionar&manutencaoImplemento.id=${manutencaoImplementoCorrente.id}')">${manutencaoImplementoCorrente.reciboOuNotaFiscal}</td>
												</logic:equal>
												<logic:equal value="Nota Fiscal" name="manutencaoImplementoCorrente" property="reciboOuNotaFiscal">
													<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/manutencaoImplemento.src?method=selecionar&manutencaoImplemento.id=${manutencaoImplementoCorrente.id}')">${manutencaoImplementoCorrente.notaFiscal}</td>
												</logic:equal>

												<td onclick="selecionar('${contextPath}/restrito/sistema/manutencaoImplemento.src?method=selecionar&manutencaoImplemento.id=${manutencaoImplementoCorrente.id}')">${manutencaoImplementoCorrente.finalidade}</td>
												<td onclick="selecionar('${contextPath}/restrito/sistema/manutencaoImplemento.src?method=selecionar&manutencaoImplemento.id=${manutencaoImplementoCorrente.id}')">${manutencaoImplementoCorrente.custo}</td>

												<td style="background-color: #e6e6e6;" onclick="selecionar('${contextPath}/restrito/sistema/manutencaoImplemento.src?method=selecionar&manutencaoImplemento.id=${manutencaoImplementoCorrente.id}')">
													<strong>${manutencaoImplementoCorrente.tipo}:</strong>
													${manutencaoImplementoCorrente.nomeCompleto}
												</td>

												<td onclick="selecionar('${contextPath}/restrito/sistema/manutencaoImplemento.src?method=selecionar&manutencaoImplemento.id=${manutencaoImplementoCorrente.id}')">${manutencaoImplementoCorrente.fornecedor.nome}</td>


												<td class="text-center" style="min-width: 80px; vertical-align: middle;">
													<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.manutencao.excluir)">
														<button type="button" id="excluir" class="btn btn-danger btn-xs fa fa-trash" style="font-size: 14px"
															onclick="excluir('${manutencaoImplementoCorrente.data}-${manutencaoImplementoCorrente.notaFiscal}', 'sistema/manutencaoImplemento', ${manutencaoImplementoCorrente.id} );"></button>
													</logic:equal>
												</td>

											</tr>
										</logic:iterate>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</logic:equal>
			</logic:notEmpty>
			<!-- TERMINO TABELA -->
			<!-- /.panel-body -->

			<!-- INICIO PAGINAÇÃO -->
			<logic:notEmpty name="manutencaoImplementoForm" property="manutencoes">
				<div class="panel-footer">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">

							<nav aria-label="Page navigation">
								<ul class="pagination" style="font-size: 12px;">
									<li>
										<a href="javascript://" style="cursor: default;">Encontrados: ${manutencaoImplementoForm.paginacao.totalRegistros} </a>
									</li>
									<logic:equal name="manutencaoImplementoForm" property="paginacao.voltarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="manutencaoImplementoForm" property="paginacao.voltarPaginacaoInativo" value="false">
										<li class="page-item">
											<a class="page-link" href="${contextPath}/restrito/sistema/manutencaoImplemento.src?method=voltar&forwardDestino=manutencaoImplementoLista" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>

									<c:forEach var="i" begin="${manutencaoImplementoForm.paginacao.paginaInicial}" end="${manutencaoImplementoForm.paginacao.paginaFinal}" step="1" varStatus="row">
										<c:if test="${manutencaoImplementoForm.paginacao.paginaCorrente == i}">
											<li class="page-item active">
										</c:if>

										<c:if test="${manutencaoImplementoForm.paginacao.paginaCorrente != i}">
											<li class="page-item ">
										</c:if>

										<a class="page-link" href="${contextPath}/restrito/sistema/manutencaoImplemento.src?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>
										</li>
									</c:forEach>

									<logic:equal name="manutencaoImplementoForm" property="paginacao.avancarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Next">
												<span aria-hidden="true">&raquo;</span>
												<span class="sr-only">Próximo</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="manutencaoImplementoForm" property="paginacao.avancarPaginacaoInativo" value="false">
										<li class="page-item ">
											<a class="page-link" href="${contextPath}/restrito/sistema/manutencaoImplemento.src?method=avancar&forwardDestino=manutencaoImplementoLista" aria-label="Next">
												<span aria-hidden="true">&raquo;</span>
												<span class="sr-only">Próximo</span>
											</a>
										</li>
									</logic:equal>
								</ul>
							</nav>
						</div>
					</div>
				</div>
			</logic:notEmpty>
			<!-- TERMINO PAGINAÇÃO -->

		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>


<script type="text/javascript">
	$(document).ready(function(){
		/* Foco inicial */
		$( "#data" ).focus();

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#notaFiscal").attr("maxlength", 15);	
		$("#safra").attr("maxlength", 100);
		$("#setor").attr("maxlength", 20);
		$("#cultura").attr("maxlength", 50);
		$("#prestador").attr("maxlength", 50);
		$( "#prestador" ).attr("placeholder","Prestador de Serviço");

		/* Setando os placeholder dos campos*/
		$( "#notaFiscal" ).attr("placeholder","NF");		
		$( "#implemento" ).attr("placeholder","Implemento a ser pesquisado");
		$( "#fornecedor" ).attr("placeholder","Fornecedor a ser pesquisado");
		$( "#servico" ).attr("placeholder","Serviço a ser pesquisado");
		$( "#safra" ).attr("placeholder","Safra pesquisar");
		$( "#setor" ).attr("placeholder","Setor pesquisar");
		$( "#cultura" ).attr("placeholder","Cultura pesquisar");
		
		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_manutencaoImplemento_consulta").prop("autocomplete", "off");
		
		$('#form_manutencaoImplemento_consulta').on('submit', function(e){
	        abrirModalProcessando();
	    });		
		
		$('#pesquisar').on('click', function(e){
			executar('form_manutencaoImplemento_consulta','filtrar');
		 });
	   		
		$('#limparPesquisa').on('click', function(e){
			abrirModalProcessando();
			executarComSubmit('form_manutencaoImplemento_consulta','limparFiltro');
	    });
		
		$('#novo').on('click', function(e){
			abrirModalProcessando();
			executarComSubmit('form_manutencaoImplemento_consulta','abrirCadastro');
	    });
				
		$('#fornecedor').keyup(function() {
			if ($('#fornecedor').val() == null || $('#marca').val() == '') {
				$('#fornecedorSelecionado').val(null);
			}
		});
		$('#fornecedor').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'manutencaoImplemento.fornecedor.nome',
			/* params : {
				'saidaGrao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/manutencaoImplemento.src?method=selecionarFornecedorAutoComplete',
			onSelect : function(suggestion) {
				$('#fornecedorSelecionado').val(suggestion.data);
			},
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			}, */
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#fornecedorSelecionado').val(null);
				}
			}
		});
		
		$('#implemento').keyup(function() {
			if ($('#implemento').val() == null || $('#implemento').val() == '') {
				$('#implementoSelecionado').val(null);
			}
		});
		$('#implemento').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'manutencaoImplemento.implemento.nomeCompleto',
			/* params : {
				'saidaGrao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/manutencaoImplemento.src?method=selecionarImplementoAutoComplete',
			onSelect : function(suggestion) {
				$('#implementoSelecionado').val(suggestion.data);
			},
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			}, */
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#implementoSelecionado').val(null);
				}
			}
		});
		
		$('#servico').keyup(function() {
			if ($('#servico').val() == null || $('#marca').val() == '') {
				$('#servicoSelecionado').val(null);
			}
		});
		$('#servico').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'manutencaoImplemento.servico.nome',
			/* params : {
				'saidaGrao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/manutencaoImplemento.src?method=selecionarServicoAutoComplete',
			onSelect : function(suggestion) {
				$('#servicoSelecionado').val(suggestion.data);
			},
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			}, */
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#servicoSelecionado').val(null);
				}
			}
		});
		
		$('#prestador').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'prestadorServico.nome',			
			serviceUrl : '${contextPath}/restrito/sistema/prestadorServico.src?method=selecionarPrestadorServicoAutoComplete',
			onSelect : function(suggestion) {
				
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					
				}
			}
		});
		
		$('#safra').keyup(function() {
			if ($('#safra').val() == null || $('#safra').val().trim() == '') {
				$('#safraSelecionada').val(null);

				$('#setor').val(null);
				$('#setorSelecionado').val(null);

				$('#cultura').val(null);
				$('#culturaSelecionada').val(null);
			}
		});

		$('#safra').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'manutencaoImplemento.safra.nome',
			serviceUrl : '${contextPath}/restrito/sistema/manutencaoImplemento.src?method=selecionarSafraAutoComplete',
			onSelect : function(suggestion) {
				$('#safraSelecionada').val(suggestion.data);

				if ($('#tipo').val() == '') {
					$("#safra").focus();
				} else if ($('#tipo').val() == 'Safra/Setor') {
					$("#setor").focus();
				} else if ($('#tipo').val() == 'Tudo') {
					$("#implemento").focus();
				} else if ($('#tipo').val() == 'Cultura') {
					$("#cultura").focus();
				}
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#safraSelecionada').val(null);

					$('#setor').val(null);
					$('#setorSelecionado').val(null);

					$('#cultura').val(null);
					$('#culturaSelecionada').val(null);
				}
			}
		});

		$('#setor').keyup(function() {
			if ($('#setor').val() == null || $('#setor').val().trim() == '') {
				$('#setorSelecionado').val(null);

				$('#cultura').val(null);
				$('#culturaSelecionada').val(null);
			}
		});

		$('#setor').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'manutencaoImplemento.setor.nomeCompleto',
			params : {
				'manutencaoImplemento.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			},
			serviceUrl : '${contextPath}/restrito/sistema/manutencaoImplemento.src?method=selecionarSetorAutoComplete',
			onSelect : function(suggestion) {
				$('#setorSelecionado').val(suggestion.data);

				if ($('#tipo').val() == '') {
					$("#safra").focus();
				} else if ($('#tipo').val() == 'Safra/Setor') {
					$("#implemento").focus();
				} else if ($('#tipo').val() == 'Tudo') {
					$("#safra").focus();
				} else if ($('#tipo').val() == 'Cultura') {
					$("#cultura").focus();
				}
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#setorSelecionado').val(null);

					$('#cultura').val(null);
					$('#culturaSelecionada').val(null);
				}
			}
		});

		$('#cultura').keyup(function() {
			if ($('#cultura').val() == null || $('#cultura').val().trim() == '') {
				$('#culturaSelecionada').val(null);
			}
		});

		$('#cultura').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'manutencaoImplemento.cultura.nome',
			params : {
				'manutencaoImplemento.setor.id' : function() {
					return $('#setorSelecionado').val();
				},
				'manutencaoImplemento.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			},
			/* params : {
				'saidaGrao.manutencaoImplemento.id' : function() {
					return $('#manutencaoImplementoSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/manutencaoImplemento.src?method=selecionarCulturaAutoComplete',
			onSelect : function(suggestion) {
				$('#culturaSelecionada').val(suggestion.data);

				$("#implemento").focus();
			},
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			}, */
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#culturaSelecionada').val(null);
				}
			}
		});

		$('#tipo').change(function() {
			$('#safra').val(null);
			$('#safraSelecionada').val(null);
			$('#setor').val(null);
			$('#setorSelecionado').val(null);
			$('#cultura').val(null);
			$('#culturaSelecionada').val(null);
			gerenciarCamposSafraSetorCultura();
		});
		
		gerenciarCamposSafraSetorCultura();
	});
	
	function gerenciarCamposSafraSetorCultura() {
		if ($('#tipo').val() == '') {
			//$("#safra").removeClass("obrigatorio");
			//$("#cultura").removeClass("obrigatorio");
			//$("#setor").removeClass("obrigatorio");

			$("#safra").addClass("bloqueado");
			$("#cultura").addClass("bloqueado");
			$("#setor").addClass("bloqueado");

			$('#safra').val(null);
			$('#safraSelecionada').val(null);
			$('#setor').val(null);
			$('#setorSelecionado').val(null);
			$('#cultura').val(null);
			$('#variedade').val(null);
			$('#culturaSelecionada').val(null);

		} else if ($('#tipo').val() == 'Safra/Setor') {
			//$("#safra").addClass("obrigatorio");
			//$("#setor").addClass("obrigatorio");

			$("#safra").removeClass("bloqueado");
			$("#setor").removeClass("bloqueado");

			//$("#cultura").removeClass("obrigatorio");
			$("#cultura").addClass("bloqueado");

			$("#safra").focus();
		} else if ($('#tipo').val() == 'Tudo') {
			//$("#safra").addClass("obrigatorio");

			$("#safra").removeClass("bloqueado");

			//$("#setor").removeClass("obrigatorio");
			$("#setor").addClass("bloqueado");

			//$("#cultura").removeClass("obrigatorio");
			$("#cultura").addClass("bloqueado");

			$("#safra").focus();
		} else if ($('#tipo').val() == 'Cultura') {
			//$("#safra").addClass("obrigatorio");
			//$("#cultura").addClass("obrigatorio");

			$("#safra").removeClass("bloqueado");
			$("#cultura").removeClass("bloqueado");

			//$("#setor").removeClass("obrigatorio");
			$("#setor").addClass("bloqueado");

			$("#safra").focus();
		}

		recarregarObrigatorios();
	}
	
	function selecionar(urlAction) {
		abrirModalProcessando();
		window.location = urlAction;
	}
</script>