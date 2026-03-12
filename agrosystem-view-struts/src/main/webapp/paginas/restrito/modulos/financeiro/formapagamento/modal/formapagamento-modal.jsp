<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>


<!-- CAMPOS DE CADASTRO -->
<div class="row">
	<jsp:include page="../../../../adm/alert-modal/alert-modal.jsp"></jsp:include>

	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<html:form styleId="form_formaPagamento" action="restrito/sistema/formaPagamento" method="post">
			<html:hidden property="method" value="empty" />
			<div class="row">

				<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<html:text styleClass="form-control input-lg bloqueado text-center" styleId="nomeCompletoModal" property="formaPagamento.nomeCompleto" name="formaPagamentoForm" />
				</div>
			</div>
			<div class="row">
				<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
					<label>Tipo</label>
					<html:select styleClass="form-control input-sm bloqueado" styleId="tipoModal" property="formaPagamento.tipo" name="formaPagamentoForm">
						<html:option value="Receita">Receita</html:option>
						<html:option value="Despesa">Despesa</html:option>
					</html:select>
				</div>
			</div>	
			<div class="row">
				<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-4">
					<label>Nome</label>
					<html:select styleClass="form-control input-sm" styleId="tipoFormaPagamentoModal" property="formaPagamento.tipoFormaPagamento.id" name="formaPagamentoForm">
						<html:option value="">Selecione...</html:option>
						<html:optionsCollection name="formaPagamentoForm" property="comboTipoFormaPagamento(${usuarioSessaoPOJO.isADM})" label="label" value="value" />
					</html:select>
					<html:hidden styleId="nomeModal" name="formaPagamentoForm" property="formaPagamento.tipoFormaPagamento.nome" />
				</div>

				<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-5">
					<label>Descritivo</label>
					<html:text styleClass="form-control input-sm montarNomeCompleto" styleId="especificacaoModal" property="formaPagamento.especificacao" name="formaPagamentoForm" />
				</div>

				<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
					<label>À Vista?</label>
					<input type="checkbox" id="aVistaCheckModal" checked data-toggle="toggle" data-on="Sim" data-off="Não" data-onstyle="primary" data-offstyle="danger" data-width="100%" data-size="small">
				</div>

				<div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-3">
					<label>Parcelas</label>
					<html:text styleClass="form-control input-sm inteiro montarNomeCompleto" styleId="parcelasModal" property="formaPagamento.parcelas" name="formaPagamentoForm" />
				</div>

				<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4">
					<label>Gerar</label>
					<input type="checkbox" id="gerarQuitadaCheckModal" checked data-toggle="toggle" data-on="Compensado" data-off="Aberto" data-onstyle="primary" data-offstyle="info" data-width="100%" data-size="small">
					<html:hidden styleId="gerarQuitadaModal" property="formaPagamento.gerarQuitada" name="formaPagamentoForm" />
				</div>

				<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-2">
					<label>% Taxa</label>
					<html:text styleClass="form-control input-sm decimal" styleId="percentualTaxaModal" property="formaPagamento.percentualTaxa" name="formaPagamentoForm" />
				</div>
				<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-2">
					<label>R$ Taxa</label>
					<html:text styleClass="form-control input-sm decimal" styleId="valorTaxaModal" property="formaPagamento.valorTaxa" name="formaPagamentoForm" />
				</div>
				<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-3">
					<label>Dias compensação</label>
					<html:text styleClass="form-control input-sm inteiro" styleId="diasCompensacaoModal" property="formaPagamento.diasCompensacao" name="formaPagamentoForm" />
				</div>

			</div>

		</html:form>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {

		/* Foco inicial */
		$("#tipoFormaPagamentoModal").focus();

		$("#parcelasModal").prop("type", "number");
		$("#parcelasModal").prop("min", 1);

		/* Setando NOTNULL nos campos*/
		$("#tipoFormaPagamentoModal").addClass("obrigatorio");
		$("#parcelasModal").addClass("obrigatorio");

		/* Setando os tamanhos maximos dos campos baseando-se no PO*/
		$("#tipoFormaPagamentoModal").attr("maxlength", 50);
		$("#especificacaoModal").attr("maxlength", 50);
		$("#parcelasModal").attr("maxlength", 2);

		/* Setando os placeholder dos campos*/
		$("#especificacaoModal").attr("placeholder", "Ex: Stone crédito.");

		// Desliga o auto-complete da pagina
		$("#form_formaPagamento").prop("autocomplete", "off");

		$('#form_formaPagamento').on('submit', function(e) {
			return false;
		});

		$('#gerarQuitadaCheckModal').change(function() {
			$('#gerarQuitadaModal').val($(this).prop('checked'));
		});
		
		$('#aVistaCheckModal').change(function() {
			if ( $(this).prop('checked') ) {
				$('#parcelasModal').val('0');

				$('#parcelasModal').addClass('bloqueado');
			} else {
				//$('#parcelasModal').val(null);
				$('#parcelasModal').removeClass('bloqueado');
			}	
			montarNomeCompleto();
			recarregarObrigatorios();
		});

		function preencherCkecks() {
			if ($('#gerarQuitadaModal').val() == 'false') {
				$('#gerarQuitadaCheckModal').bootstrapToggle('off')
			} else {
				$('#gerarQuitadaCheckModal').bootstrapToggle('on')
			}
			
			if ($('#parcelasModal').val() == '' || $('#parcelasModal').val() == null || $('#parcelasModal').val() == '0') {
				$('#parcelasModal').val('0');
				$('#aVistaCheckModal').bootstrapToggle('on')
			} else {
				$('#aVistaCheckModal').bootstrapToggle('off')
			}
		}
		preencherCkecks();

		$('#tipoFormaPagamentoModal').on('change', function() {
			
			var theForm = $('form[name=formaPagamentoForm]');
			var params = theForm.serialize();
			var actionURL = theForm.attr('action') + '?method=selecionarTipoFormaPagamento';

			$.ajax({
				type : 'POST',
				url : actionURL,
				data : params,
				success : function(data, textStatus, XMLHttpRequest) {
					$('#nomeModal').val(data.nome);
					
					montarNomeCompleto();
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(textStatus);
				}
			});			
			
			//gerenciarCamposCartao();
			
		});

		/* function gerenciarCamposCartao() {
			if ($('#tipoFormaPagamentoModal').val() == 'Cartão') {
				$(".cartao").css('display', 'block');
			} else {
				$(".cartao").css('display', 'none');
			}
		} */

		//gerenciarCamposCartao();
		
		$('.montarNomeCompleto').keyup(function() {
			montarNomeCompleto();
		});
		function montarNomeCompleto() {
			var nomeModal = $('#nomeModal').val();
			var especificacaoModal = $('#especificacaoModal').val();
			var parcelasModal = getParcelasToString($('#parcelasModal').val());

			$('#nomeCompletoModal').val(nomeModal + ' ' + especificacaoModal + ' ' + parcelasModal);
		}
		
		function getParcelasToString(parcelasModal) {
			if (parcelasModal == '') {
				return '';
			}
			
			if (parcelasModal == '0') {
				return '( A Vista )';
			} else {
				return '( Parcelado ' + parcelasModal + 'x )';
			}
		}
		montarNomeCompleto();

		/* $('#nomeModal').autocomplete({
			minChars : 2,
			noCache : true,
			paramName : 'formaPagamento.nomeCompletoModal',
			serviceUrl : '${contextPath}/restrito/sistema/formaPagamento.src?method=selecionarFormaPagamentoAutoComplete',
			onSelect : function(suggestion) {
				
			},
			onSearchComplete : function(query, suggestions) {
				if (suggestions == null || suggestions == '') {

				}
			}
		}); */
	});
</script>
