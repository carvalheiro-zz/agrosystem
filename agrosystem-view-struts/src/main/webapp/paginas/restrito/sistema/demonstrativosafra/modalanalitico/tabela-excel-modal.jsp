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
						<th>Insumo</th>
						<th class="text-center" style="width: 180px; min-width: 180px;">Custo por Alqueire</th>
						<th class="text-center" style="width: 180px; min-width: 180px;">Custo da área</th>
						<th class="text-center" style="width: 180px; min-width: 180px;">% Custo</th>
					</tr>
				</thead>
				<tbody>
					<!-- TABELA -->
					<logic:iterate indexId="i" id="demonstrativoCorrente" name="demonstrativoSafraForm" property="demonstrativos" type="br.com.srcsoftware.sistema.demonstrativosafra.custodireto.insumo.CustoDiretoInsumoPOJO">

						<tr>

							<td style="vertical-align: middle;">${demonstrativoCorrente.nome}</td>

							<td style="vertical-align: middle;" class="text-right">${demonstrativoCorrente.custoPorAlqueireToString}</td>
							<td style="vertical-align: middle;" class="text-right">${demonstrativoCorrente.custoTotalToString}</td>
							<td style="vertical-align: middle;" class="text-right">${demonstrativoCorrente.percentualCustoToString}</td>

						</tr>

					</logic:iterate>
				</tbody>
			</table>
		</div>
	</div>
</div>