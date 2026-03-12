<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!-- CAMPOS DE CADASTRO -->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="table-responsive">
			<table class="table table-bordered table-striped" style="font-size: 12px;">
				<thead>
					<!-- CABEÇALHO DA TABELA -->
					<tr class="bg-danger">
						<th class="text-center">Setor</th>
						<th class="text-center">Variedade</th>
						<th class="text-center">Área em Alqueire</th>
						<th class="text-center" style="width: 180px; min-width: 180px;">Custo por Alqueire</th>
						<th class="text-center" style="width: 180px; min-width: 180px;">Custo da área</th>
					</tr>
				</thead>
				<tbody>
					<!-- TABELA -->
					<logic:iterate indexId="i" id="sinteticoCorrente" name="demonstrativoSafraSinteticoForm" property="sinteticos" type="br.com.srcsoftware.sistema.demonstrativosafra.custodireto.insumo.sintetico.CustoDiretoInsumoSinteticoPOJO">
						<tr>
							<td style="vertical-align: middle;" class="text-center">${sinteticoCorrente.setor}</td>
							<td style="vertical-align: middle;" class="text-center">${sinteticoCorrente.variedade}</td>
							<td style="vertical-align: middle;" class="text-center">${sinteticoCorrente.areaTotal}</td>
							<td style="vertical-align: middle;" class="text-center">${sinteticoCorrente.totalPorAlqueireToString}</td>
							<td style="vertical-align: middle;" class="text-center">${sinteticoCorrente.totalPorAreaToString}</td>
						</tr>
					</logic:iterate>
				</tbody>
			</table>
		</div>
	</div>
</div>