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
						<th style="width: 230px; min-width: 230px;">Colaborador</th>
						<th class="text-center" style="width: 120px; min-width: 120px;">Lançamento</th>
						<th class="text-center" style="width: 120px; min-width: 120px;">50%</th>
						<th class="text-center" style="width: 120px; min-width: 120px;">100%</th>
						<th class="text-center" style="width: 120px; min-width: 120px;">Dom./Fer.</th>						
					</tr>
				</thead>
				<tbody>
					<!-- TABELA -->
					<logic:iterate indexId="i" id="saldoCorrente" name="demonstrativoLivroPontoForm" property="saldosHoraExtra" type="br.com.srcsoftware.sistema.pessoa.funcionario.horaextra.SaldoHoraExtraPOJO">
															
						<tr>
							<td style="vertical-align: middle;">${saldoCorrente.colaborador.pessoaFisica.razaoSocial}</td>
							<td style="vertical-align: middle;" class="text-right">${saldoCorrente.lancamento}</td>
							<td style="vertical-align: middle;" class="text-right">${saldoCorrente.quantidade50Porcento}</td>
							<td style="vertical-align: middle;" class="text-right">${saldoCorrente.quantidade100Porcento}</td>
							<td style="vertical-align: middle;" class="text-right">${saldoCorrente.quantidadeDomingoFeriado}&nbsp;dias</td>
						</tr>

					</logic:iterate>
				</tbody>
			</table>
		</div>
	</div>
</div>