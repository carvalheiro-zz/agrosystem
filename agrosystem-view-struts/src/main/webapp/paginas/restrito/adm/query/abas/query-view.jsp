<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<div class="panel sombra">

	<!-- INICIO CAMPOS DE PESQUISA -->
	<div class="panel-heading cor-sistema">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="row">
					<logic:iterate indexId="i" id="campoCorrente" name="queryForm" property="query.dadosCampos" type="br.com.srcsoftware.modular.manager.query.CampoPOJO">
						<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<label class="text-white">${campoCorrente.campo}</label>
							<html:text styleClass="form-control input-sm ${campoCorrente.tipo} " styleId="${i}" property="query.dadosCampos[${i}].valor" name="queryForm" />
						</div>
					</logic:iterate>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
						<button type="button" class="btn btn-success btn-sm cor-sistema">
							<i class="fa fa-search"></i>
							Pesquisar
						</button>

						<button type="button" class="btn btn-success btn-sm cor-sistema">
							<i class="glyphicon glyphicon-erase"></i>
							Limpar
						</button>
						<button type="button" class="btn btn-success btn-sm cor-sistema">
							<i class="glyphicon glyphicon-erase"></i>
							Exportar para Excel
						</button>
					</div>
					<logic:notEmpty name="queryForm" property="query.dados">
						<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4 text-right">
							<span class="label label-success" style="font-size: 15px;">Encontrados: ${queryForm.query.dados.size()}</span>
						</div>
					</logic:notEmpty>
				</div>
			</div>
		</div>
	</div>
	<!-- TERMINO CAMPOS DE PESQUISA -->

	<div class="panel-body">
		<div class="table-responsive" style="overflow-y: scroll; max-height: 350px;">
			<table class="table table-bordered table-striped table-hover" style="font-size: 12px;">
				<thead>
					<!-- CABEÇALHO DA TABELA -->
					<tr class="cor-sistema" style="color: white;">
						<logic:iterate indexId="i" id="colunaCorrente" name="queryForm" property="query.cabecalhoDados" type="java.lang.String">
							<th>${colunaCorrente}</th>
						</logic:iterate>
					</tr>
				</thead>
				<tbody>
					<!-- TABELA -->
					<logic:iterate indexId="x" id="linhaCorrente" name="queryForm" property="query.dados" type="br.com.srcsoftware.modular.manager.query.DadosRetornoPOJO">
						<tr>
							<logic:iterate indexId="i" id="colunaCorrente" name="queryForm" property="query.cabecalhoDados" type="java.lang.String">
								<td>${linhaCorrente.tuple[i]}</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
				</tbody>
			</table>
		</div>
	</div>
</div>