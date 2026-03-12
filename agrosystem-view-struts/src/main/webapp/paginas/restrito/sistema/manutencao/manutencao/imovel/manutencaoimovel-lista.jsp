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
			Pesquisar Manutenção em Imóveis
			<small class="sub_cabecalho_pagina">
				<i class="fa fa-angle-double-right"></i>
				Listagem das Manutenção em Imóveis
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
						<html:form styleId="form_manutencaoImovel_consulta" action="restrito/sistema/manutencaoImovel" method="post">
							<html:hidden property="method" value="empty" />

							<div class="row">
								<div class="form-group col-xs-12 col-sm-4 col-md-3 col-lg-2">
									<label class="text-white">Data Inicial</label>
									<html:text styleClass="form-control input-sm data text-center" styleId="dataInicial" property="dataInicial" name="manutencaoImovelForm" />
								</div>
								<div class="form-group col-xs-12 col-sm-4 col-md-3 col-lg-2">
									<label class="text-white">Data Final</label>
									<html:text styleClass="form-control input-sm data text-center" styleId="dataFinal" property="dataFinal" name="manutencaoImovelForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-2">
									<label class="text-white">Recibo/NF</label>
									<html:select styleClass="form-control input-sm" styleId="reciboNF" property="manutencaoImovel.reciboOuNotaFiscal" name="manutencaoImovelForm">
										<html:option value="">Selecione...</html:option>
										<html:option value="Recibo">Recibo</html:option>
										<html:option value="Nota Fiscal">Nota Fiscal</html:option>
									</html:select>
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-2" id="divNotaFiscal">
									<label class="text-white">Nota Fiscal</label>
									<html:text styleClass="form-control input-sm text-center" styleId="notaFiscal" property="manutencaoImovel.notaFiscal" name="manutencaoImovelForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-2">
									<label class="text-white">Finalidade</label>
									<html:select styleClass="form-control input-sm" styleId="finalidade" property="manutencaoImovel.finalidade" name="manutencaoImovelForm">
										<html:option value="">Selecione...</html:option>
										<html:option value="Manutenção">Manutenção</html:option>
										<html:option value="Conserto">Conserto</html:option>
									</html:select>
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-2">
									<label class="text-white">Prestador de Serviço</label>
									<html:text styleClass="form-control input-sm" styleId="prestador" property="manutencaoImovel.prestador.nome" name="manutencaoImovelForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="prestadorSelecionado" property="manutencaoImovel.prestador.id" name="manutencaoImovelForm" />
								</div>


								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<label class="text-white">Fornecedor</label>
									<html:text styleClass="form-control input-sm" styleId="fornecedor" property="manutencaoImovel.fornecedor.nome" name="manutencaoImovelForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<label class="text-white">Imovel</label>
									<html:text styleClass="form-control input-sm" styleId="imovel" property="manutencaoImovel.imovel.nome" name="manutencaoImovelForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
								</div>
								<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
									<label class="text-white">Serviço</label>
									<html:text styleClass="form-control input-sm" styleId="servico" property="manutencaoImovel.servico.nome" name="manutencaoImovelForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
								</div>



								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label class="text-white">Atribuição de Custo</label>
									<html:select styleClass="form-control input-sm" styleId="tipo" property="manutencaoImovel.tipo" name="manutencaoImovelForm">
										<html:option value="">Selecione...</html:option>
										<html:option value="Safra/Setor">Safra/Setor</html:option>
										<html:option value="Tudo">Tudo</html:option>
										<html:option value="Cultura">Cultura</html:option>
									</html:select>
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label class="text-white">Safra</label>
									<html:text styleClass="form-control input-sm" styleId="safra" property="manutencaoImovel.safra.nome" name="manutencaoImovelForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="safraSelecionada" property="manutencaoImovel.safra.id" name="manutencaoImovelForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
									<label class="text-white">Setor</label>
									<html:text styleClass="form-control input-sm" styleId="setor" property="manutencaoImovel.setor.nomeCompleto" name="manutencaoImovelForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="setorSelecionado" property="manutencaoImovel.setor.id" name="manutencaoImovelForm" />
								</div>

								<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<label class="text-white">Cultura</label>
									<html:text styleClass="form-control input-sm" styleId="cultura" property="manutencaoImovel.cultura.nome" name="manutencaoImovelForm" />
									<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
									<html:hidden styleId="culturaSelecionada" property="manutencaoImovel.cultura.id" name="manutencaoImovelForm" />
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
			<logic:empty name="manutencaoImovelForm" property="manutencoes">
				<div class="row" style="margin-top: 15px;">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="alert alert-warning">Nenhum registro encontrado!</div>
					</div>
				</div>
			</logic:empty>
			<logic:notEmpty name="manutencaoImovelForm" property="manutencoes">
				<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.manutencao.filtrar)">
					<div class="panel-body">
						<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
							<span class="label cor-sistema" style="font-size: 15px;">Total Custo: R$ ${manutencaoImovelForm.totalValor}</span>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
							<div class="table-responsive">
								<table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
									<thead>
										<!-- CABEÇALHO DA TABELA -->
										<tr class="cor-sistema" style="color: white;">
											<th>Serviço</th>
											<th>Imóvel</th>
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
										<logic:iterate id="manutencaoImovelCorrente" name="manutencaoImovelForm" property="manutencoes" type="br.com.srcsoftware.sistema.manutencao.manutencao.ManutencaoDTO">

											<tr>
												<td onclick="selecionar('${contextPath}/restrito/sistema/manutencaoImovel.src?method=selecionar&manutencaoImovel.id=${manutencaoImovelCorrente.id}')">${manutencaoImovelCorrente.servico.nome}</td>
												<td onclick="selecionar('${contextPath}/restrito/sistema/manutencaoImovel.src?method=selecionar&manutencaoImovel.id=${manutencaoImovelCorrente.id}')">${manutencaoImovelCorrente.imovel.nome}</td>
												<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/manutencaoImovel.src?method=selecionar&manutencaoImovel.id=${manutencaoImovelCorrente.id}')">${manutencaoImovelCorrente.dataToString}</td>

												<logic:equal value="Recibo" name="manutencaoImovelCorrente" property="reciboOuNotaFiscal">
													<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/manutencaoImovel.src?method=selecionar&manutencaoImovel.id=${manutencaoImovelCorrente.id}')">${manutencaoImovelCorrente.reciboOuNotaFiscal}</td>
												</logic:equal>
												<logic:equal value="Nota Fiscal" name="manutencaoImovelCorrente" property="reciboOuNotaFiscal">
													<td class="text-center" onclick="selecionar('${contextPath}/restrito/sistema/manutencaoImovel.src?method=selecionar&manutencaoImovel.id=${manutencaoImovelCorrente.id}')">${manutencaoImovelCorrente.notaFiscal}</td>
												</logic:equal>

												<td onclick="selecionar('${contextPath}/restrito/sistema/manutencaoImovel.src?method=selecionar&manutencaoImovel.id=${manutencaoImovelCorrente.id}')">${manutencaoImovelCorrente.finalidade}</td>
												<td onclick="selecionar('${contextPath}/restrito/sistema/manutencaoImovel.src?method=selecionar&manutencaoImovel.id=${manutencaoImovelCorrente.id}')">${manutencaoImovelCorrente.custo}</td>

												<td style="background-color: #e6e6e6;" onclick="selecionar('${contextPath}/restrito/sistema/manutencaoImovel.src?method=selecionar&manutencaoImovel.id=${manutencaoImovelCorrente.id}')">
													<strong>${manutencaoImovelCorrente.tipo}:</strong>
													${manutencaoImovelCorrente.nomeCompleto}
												</td>

												<td onclick="selecionar('${contextPath}/restrito/sistema/manutencaoImovel.src?method=selecionar&manutencaoImovel.id=${manutencaoImovelCorrente.id}')">${manutencaoImovelCorrente.fornecedor.nome}</td>


												<td class="text-center" style="min-width: 80px; vertical-align: middle;">
													<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.manutencao.excluir)">
														<button type="button" id="excluir" class="btn btn-danger btn-xs fa fa-trash" style="font-size: 14px"
															onclick="excluir('${manutencaoImovelCorrente.data}-${manutencaoImovelCorrente.notaFiscal}', 'sistema/manutencaoImovel', ${manutencaoImovelCorrente.id} );"></button>
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
			<logic:notEmpty name="manutencaoImovelForm" property="manutencoes">
				<div class="panel-footer">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">

							<nav aria-label="Page navigation">
								<ul class="pagination" style="font-size: 12px;">
									<li>
										<a href="javascript://" style="cursor: default;">Encontrados: ${manutencaoImovelForm.paginacao.totalRegistros} </a>
									</li>
									<logic:equal name="manutencaoImovelForm" property="paginacao.voltarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="manutencaoImovelForm" property="paginacao.voltarPaginacaoInativo" value="false">
										<li class="page-item">
											<a class="page-link" href="${contextPath}/restrito/sistema/manutencaoImovel.src?method=voltar&forwardDestino=manutencaoImovelLista" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
												<span class="sr-only">Anterior</span>
											</a>
										</li>
									</logic:equal>

									<c:forEach var="i" begin="${manutencaoImovelForm.paginacao.paginaInicial}" end="${manutencaoImovelForm.paginacao.paginaFinal}" step="1" varStatus="row">
										<c:if test="${manutencaoImovelForm.paginacao.paginaCorrente == i}">
											<li class="page-item active">
										</c:if>

										<c:if test="${manutencaoImovelForm.paginacao.paginaCorrente != i}">
											<li class="page-item ">
										</c:if>

										<a class="page-link" href="${contextPath}/restrito/sistema/manutencaoImovel.src?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>
										</li>
									</c:forEach>

									<logic:equal name="manutencaoImovelForm" property="paginacao.avancarPaginacaoInativo" value="true">
										<li class="page-item disabled">
											<a class="page-link" href="javascript://" aria-label="Next">
												<span aria-hidden="true">&raquo;</span>
												<span class="sr-only">Próximo</span>
											</a>
										</li>
									</logic:equal>
									<logic:equal name="manutencaoImovelForm" property="paginacao.avancarPaginacaoInativo" value="false">
										<li class="page-item ">
											<a class="page-link" href="${contextPath}/restrito/sistema/manutencaoImovel.src?method=avancar&forwardDestino=manutencaoImovelLista" aria-label="Next">
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
		$( "#imovel" ).attr("placeholder","Imovel a ser pesquisado");
		$( "#fornecedor" ).attr("placeholder","Fornecedor a ser pesquisado");
		$( "#servico" ).attr("placeholder","Serviço a ser pesquisado");
		$( "#safra" ).attr("placeholder","Safra pesquisar");
		$( "#setor" ).attr("placeholder","Setor pesquisar");
		$( "#cultura" ).attr("placeholder","Cultura pesquisar");
		
		/* EVENTOS */
		// Desliga o auto-complete da pagina
		$("#form_manutencaoImovel_consulta").prop("autocomplete", "off");
		
		$('#form_manutencaoImovel_consulta').on('submit', function(e){
	        abrirModalProcessando();
	    });		
		
		$('#pesquisar').on('click', function(e){
			executar('form_manutencaoImovel_consulta','filtrar');
		 });
	   		
		$('#limparPesquisa').on('click', function(e){
			abrirModalProcessando();
			executarComSubmit('form_manutencaoImovel_consulta','limparFiltro');
	    });
		
		$('#novo').on('click', function(e){
			abrirModalProcessando();
			executarComSubmit('form_manutencaoImovel_consulta','abrirCadastro');
	    });
				
		$('#fornecedor').keyup(function() {
			if ($('#fornecedor').val() == null || $('#marca').val() == '') {
				$('#fornecedorSelecionado').val(null);
			}
		});
		$('#fornecedor').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'manutencaoImovel.fornecedor.nome',
			/* params : {
				'saidaGrao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/manutencaoImovel.src?method=selecionarFornecedorAutoComplete',
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
		
		$('#imovel').keyup(function() {
			if ($('#imovel').val() == null || $('#marca').val() == '') {
				$('#imovelSelecionado').val(null);
			}
		});
		$('#imovel').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'manutencaoImovel.imovel.nome',
			/* params : {
				'saidaGrao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/manutencaoImovel.src?method=selecionarImovelAutoComplete',
			onSelect : function(suggestion) {
				$('#imovelSelecionado').val(suggestion.data);
			},
			/* formatResult : function(suggestion, currentValue) {
				var valorExibir = suggestion.value + " - " + suggestion.variedade;
				return valorExibir;
			}, */
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {
					$('#imovelSelecionado').val(null);
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
			paramName : 'manutencaoImovel.servico.nome',
			/* params : {
				'saidaGrao.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/manutencaoImovel.src?method=selecionarServicoAutoComplete',
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
			paramName : 'manutencaoImovel.safra.nome',
			serviceUrl : '${contextPath}/restrito/sistema/manutencaoImovel.src?method=selecionarSafraAutoComplete',
			onSelect : function(suggestion) {
				$('#safraSelecionada').val(suggestion.data);

				if ($('#tipo').val() == '') {
					$("#safra").focus();
				} else if ($('#tipo').val() == 'Safra/Setor') {
					$("#setor").focus();
				} else if ($('#tipo').val() == 'Tudo') {
					$("#imovel").focus();
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
			paramName : 'manutencaoImovel.setor.nomeCompleto',
			params : {
				'manutencaoImovel.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			},
			serviceUrl : '${contextPath}/restrito/sistema/manutencaoImovel.src?method=selecionarSetorAutoComplete',
			onSelect : function(suggestion) {
				$('#setorSelecionado').val(suggestion.data);

				if ($('#tipo').val() == '') {
					$("#safra").focus();
				} else if ($('#tipo').val() == 'Safra/Setor') {
					$("#imovel").focus();
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
			paramName : 'manutencaoImovel.cultura.nome',
			params : {
				'manutencaoImovel.setor.id' : function() {
					return $('#setorSelecionado').val();
				},
				'manutencaoImovel.safra.id' : function() {
					return $('#safraSelecionada').val();
				}
			},
			/* params : {
				'saidaGrao.manutencaoImovel.id' : function() {
					return $('#manutencaoImovelSelecionada').val();
				}
			}, */
			serviceUrl : '${contextPath}/restrito/sistema/manutencaoImovel.src?method=selecionarCulturaAutoComplete',
			onSelect : function(suggestion) {
				$('#culturaSelecionada').val(suggestion.data);

				$("#imovel").focus();
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