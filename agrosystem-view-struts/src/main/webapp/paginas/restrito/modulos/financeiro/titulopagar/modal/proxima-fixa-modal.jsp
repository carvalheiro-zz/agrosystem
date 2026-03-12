<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<div class="row">
	<div class="form-group col-xs-12 col-sm-7 col-md-7 col-lg-7">
		<label>Deseja gerar esta Despesa para o MÊS sugerido?</label>
	</div>
	<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<label>Vencimento Próxima</label>
		<input type="text" class="form-control input-sm dataMesAno" id="mesAnoProximaParcela" />
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		/* Foco inicial */
		$("#mesAnoProximaParcela").focus();
		
	/* 	$(".dataMesAno").mask("00/0000", {
			placeholder : "__/____",
			clearIfNotMatch : true
		});
 */
		
	});
</script>