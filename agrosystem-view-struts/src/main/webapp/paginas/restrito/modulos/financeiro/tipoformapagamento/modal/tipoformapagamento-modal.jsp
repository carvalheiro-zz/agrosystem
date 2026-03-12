<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>


<!-- CAMPOS DE CADASTRO -->
<div class="row">
	<jsp:include page="../../../../adm/alert-modal/alert-modal.jsp"></jsp:include>

	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<html:form styleId="form_tipoFormaPagamento" action="restrito/sistema/tipoFormaPagamento" method="post">
			<html:hidden property="method" value="empty" />
			<div class="row">
				<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-5">
					<label>Nome</label>
					<html:text styleClass="form-control input-sm" styleId="nomeModal" property="tipoFormaPagamento.nome" name="tipoFormaPagamentoForm" />
				</div>
			</div>

		</html:form>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {

		/* Foco inicial */
		$("#nomeModal").focus();		

		/* Setando NOTNULL nos campos*/
		$("#nomeModal").addClass("obrigatorio");
		
		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#nomeModal").attr("maxlength", 50);		

		/* Setando os placeholder dos campos*/
		$("#especificacaoModal").attr("placeholder", "Ex: Cartão");

		// Desliga o auto-complete da pagina
		$("#form_tipoFormaPagamento").prop("autocomplete", "off");

		$('#form_tipoFormaPagamento').on('submit', function(e) {
			return false;
		});		
	});
</script>
