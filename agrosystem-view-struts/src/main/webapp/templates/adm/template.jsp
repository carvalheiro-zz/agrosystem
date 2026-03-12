<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>


<html:html lang="pt-br">

<head>
<meta charset="pt-br">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="Gabriel Damiani Carvalheiro">
<meta http-equiv="Content-Language" content="pt-br">

<%-- <link rel="shortcut icon" href="${contextPath}/assets/images/propria/favicon.ico" type="image/x-icon" /> --%>
<link rel="apple-touch-icon" sizes="57x57" href="${contextPath}/assets/images/propria/favicons/${usuarioSessaoPOJO.empresa.codigo}/apple-icon-57x57.png">
<link rel="apple-touch-icon" sizes="60x60" href="${contextPath}/assets/images/propria/favicons/${usuarioSessaoPOJO.empresa.codigo}/apple-icon-60x60.png">
<link rel="apple-touch-icon" sizes="72x72" href="${contextPath}/assets/images/propria/favicons/${usuarioSessaoPOJO.empresa.codigo}/apple-icon-72x72.png">
<link rel="apple-touch-icon" sizes="76x76" href="${contextPath}/assets/images/propria/favicons/${usuarioSessaoPOJO.empresa.codigo}/apple-icon-76x76.png">
<link rel="apple-touch-icon" sizes="114x114" href="${contextPath}/assets/images/propria/favicons/${usuarioSessaoPOJO.empresa.codigo}/apple-icon-114x114.png">
<link rel="apple-touch-icon" sizes="120x120" href="${contextPath}/assets/images/propria/favicons/${usuarioSessaoPOJO.empresa.codigo}/apple-icon-120x120.png">
<link rel="apple-touch-icon" sizes="144x144" href="${contextPath}/assets/images/propria/favicons/${usuarioSessaoPOJO.empresa.codigo}/apple-icon-144x144.png">
<link rel="apple-touch-icon" sizes="152x152" href="${contextPath}/assets/images/propria/favicons/${usuarioSessaoPOJO.empresa.codigo}/apple-icon-152x152.png">
<link rel="apple-touch-icon" sizes="180x180" href="${contextPath}/assets/images/propria/favicons/${usuarioSessaoPOJO.empresa.codigo}/apple-icon-180x180.png">
<link rel="icon" type="image/png" sizes="192x192" href="${contextPath}/assets/images/propria/favicons/${usuarioSessaoPOJO.empresa.codigo}/android-icon-192x192.png">
<link rel="icon" type="image/png" sizes="32x32" href="${contextPath}/assets/images/propria/favicons/${usuarioSessaoPOJO.empresa.codigo}/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="96x96" href="${contextPath}/assets/images/propria/favicons/${usuarioSessaoPOJO.empresa.codigo}/favicon-96x96.png">
<link rel="icon" type="image/png" sizes="16x16" href="${contextPath}/assets/images/propria/favicons/${usuarioSessaoPOJO.empresa.codigo}/favicon-16x16.png">
<link rel="manifest" href="${contextPath}/assets/images/propria/favicons/${usuarioSessaoPOJO.empresa.codigo}/manifest.json">
<meta name="msapplication-TileColor" content="#ffffff">
<meta name="msapplication-TileImage" content="${contextPath}/assets/images/propria/favicons/${usuarioSessaoPOJO.empresa.codigo}/ms-icon-144x144.png">
<meta name="theme-color" content="#ffffff">

<%
	request.getSession().setAttribute("contextPath", request.getContextPath());
%>

<!-- IMPORTAÇÃO DOS CSS e DOS JSs -->
<jsp:include page="../../paginas/imports/imports.jsp"></jsp:include>

<title>${usuarioSessaoPOJO.empresa.nomeSistema}</title>

</head>

<body>
	<%-- style="background: url('${contextPath}/assets/images/propria/background-login-verde.png') no-repeat center center fixed; -webkit-background-size: cover;
    -moz-background-size: cover;
    -o-background-size: cover;
    background-size: cover;" --%>

	<div id="wrapper" style="background: rgba(255, 255, 255, 0.6);">
		<!-- Navigation -->

		<!-- DESENVOLVIMENTO -->
		<!-- <nav class="navbar navbar-default navbar-static-top" style="background: red; color: white; margin-bottom: 0px;" role="navigation" style="margin-bottom: 0"> -->

		<!-- PRODUÇÃO -->
		<nav class="navbar navbar-default navbar-static-top cor-sistema" role="navigation" style="color: white; margin-bottom: 0;">

			<div class="navbar-header" style="width: 100%">

				<!-- HEADER (MENU QUANDO PEQUENO + NOME DO SISTEMA)  navbar-static-top-->
				<tiles:insert name="header" />
			</div>

			<!-- MENU PRINCIPAL LATERAL -->
			<div class="navbar-default sidebar cor-menu-sistema" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<!-- MENU PRINCIPAL LATERAL style="margin-top: 0px;"-->
					<tiles:insert name="menu" />
				</div>
				<!-- /.sidebar-collapse -->
			</div>
			<!-- /MENU PRINCIPAL LATERAL -->
		</nav>


		<!-- CONTEUDO PRINCIPAL ONDE SE LOCALIZAM AS TELAS -->
		<div id="page-wrapper"
			style="background: url('${contextPath}/assets/images/propria/background-login.png') no-repeat center center fixed; -webkit-background-size: cover;
   				 -moz-background-size: cover;
   				 -o-background-size: cover;
    			background-size: cover;">

			<!-- OS JSP DAS TELAS DEVEM SER CRIADOS DENTRO DE 'DIVs row' -->
			<tiles:insert name="body" />

		</div>
		<!-- /CONTEUDO PRINCIPAL ONDE SE LOCALIZAM AS TELAS -->

	</div>
	<!-- /#wrapper -->

	<!-- INICIO - MODAL DE PROCESSANDO -->
	<div class="modal fade bs-example-modal-sm" id="myPleaseWait" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
		<div class="modal-dialog modal-sm" style="width: 380px;">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">
						<i class="fa fa-clock-o"></i>
						Processando...
					</h4>
					<!-- <label id="contador"></label> -->
				</div>
				<div class="modal-body" style="height: 60px;">
					<div>
						<div>
							<img src="${contextPath}/assets/images/propria/ajax-loader.gif" id="loading-indicator" style="display: none; width: 345px;" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- TERMINO - MODAL DE PROCESSANDO -->

	<script type="text/javascript">
		$(document).ready(function() {
			/* INICIO - MODAL DE PROCESSANDO */
			//abrirModalProcessando();
			/* TERMINO - MODAL DE PROCESSANDO */

			recarregarObrigatorios();
			recarregarAceitarTab();
			carregarMascaras();

		});

		/*function validarData(data) {
			reg = /[^\d\/\.]/gi; // Mascara = dd/mm/aaaa | dd.mm.aaaa
			var valida = data.replace(reg, ''); // aplica mascara e valida só numeros
			if (valida && valida.length == 10) { // é válida, então ;)
				var ano = data.substr(6), mes = data.substr(3, 2), dia = data.substr(0, 2), M30 = [ '04', '06', '09', '11' ], v_mes = /(0[1-9])|(1[0-2])/.test(mes), v_ano = /(19[1-9]\d)|(20\d\d)|2100/
						.test(ano), rexpr = new RegExp(mes), fev29 = ano % 4 ? 28 : 29;

				if (v_mes && v_ano) {
					if (mes == '02')
						return (dia >= 1 && dia <= fev29);
					else if (rexpr.test(M30))
						return /((0[1-9])|([1-2]\d)|30)/.test(dia);
					else
						return /((0[1-9])|([1-2]\d)|3[0-1])/.test(dia);
				}
			}
			return false // se inválida :(
		}*/

		function formatResultAutoCompleteHightLigth(value, currentValue) {
			var utils = {
				escapeRegExChars : function(value) {
					return value.replace(/[|\\{}()[\]^$+*?.]/g, "\\$&");
				}
			};

			var pattern, words;

			if (!currentValue) {
				return value;
			}

			words = utils.escapeRegExChars(currentValue);
			words = words.split(' ').join('|');

			pattern = '(' + words + ')';

			return (value).replace(new RegExp(pattern, 'gi'), '<strong>$1<\/strong>').replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, '&quot;').replace(
					/&lt;(\/?strong)&gt;/g, '<$1>');
		}

		function carregarMascaras() {
			$(".placa").mask("SSS-0000", {
				placeholder : "AVA-6438",
				clearIfNotMatch : true
			});

			$(".data").mask("00/00/0000", {
				placeholder : "__/__/____",
				clearIfNotMatch : true
			});
			$(".dataHora").mask("00/00/0000 00:00:00.000", {
				placeholder : "__/__/____ __:__:__.___",
				clearIfNotMatch : true
			});

			$(".dinheiro").mask('000.000.000.000.000,00', {
				placeholder : "R$ 0,00",
				reverse : true
			});

			$('.inteiro').mask('##0', {
				placeholder : "0",
				reverse : true
			});
			$('.decimal').mask('000.000.000.000.000,00', {
				placeholder : "0,00",
				reverse : true
			});				
			
			$(".cpf").mask("000.000.000-00", {
				placeholder : "000.000.000-00",
				clearIfNotMatch : true
			});
			$(".cnpj").mask("00.000.000/0000-00", {
				placeholder : "00.000.000/0000-00",
				clearIfNotMatch : true
			});
			$(".cep").mask("00.000-000", {
				placeholder : "00.000-000",
				clearIfNotMatch : true
			});

			$(".cel").mask("(00) 0000-00009", {
				placeholder : "(00) 0000-0000x",
				clearIfNotMatch : true
			});

			$(".fixo").mask("(00) 0000-0000", {
				placeholder : "(00) 0000-0000",
				clearIfNotMatch : true
			});

			$(".horaComSegundos").mask("00:00:00", {
				placeholder : "00:00:00",
				clearIfNotMatch : false
			});

			$(".hora").mask("00:00", {
				placeholder : "00:00",
				clearIfNotMatch : true
			});

			$(".dataDiaMes").mask("00/00", {
				placeholder : "__/__",
				clearIfNotMatch : true
			});

			$(".dataMesAno").mask("00/0000", {
				placeholder : "__/____",
				clearIfNotMatch : true
			});

			$(".cpfPesquisa").mask("000.000.000-00", {
				placeholder : "000.000.000-00",
				clearIfNotMatch : false
			});
			$(".cnpjPesquisa").mask("00.000.000/0000-00", {
				placeholder : "00.000.000/0000-00",
				clearIfNotMatch : false
			});

			$(".celPesquisa").mask("(00) 0000-00009", {
				placeholder : "(00) 0000-0000x",
				clearIfNotMatch : false
			});

			$(".fixoPesquisa").mask("(00) 0000-0000", {
				placeholder : "(00) 0000-0000",
				clearIfNotMatch : false
			});
		}

		function recarregarObrigatorios() {

			$("input").each(function(index, element) {
				/* Limpando os Obrigatorios para redefini-los */
				$(element).removeAttr("required");

				/* Limpando os Bloqueados para redefini-los */
				$(element).removeAttr("readonly");
			});
			$("select").each(function(index, element) {
				/* Limpando os Obrigatorios para redefini-los */
				$(element).removeAttr("required");

				/* Limpando os Bloqueados para redefini-los */
				$(element).removeAttr("readonly");
			});
			$("textarea").each(function(index, element) {
				/* Limpando os Obrigatorios para redefini-los */
				$(element).removeAttr("required");

				/* Limpando os Bloqueados para redefini-los */
				$(element).removeAttr("readonly");
			});

			/* Definindo os Campos Obrigatórios */
			$(".obrigatorio").each(function(index, element) {
				$(element).attr("required", "required");
			});

			$(".bloqueado").each(function(index, element) {
				/* Criando uma CLASS para apenas leitura e simulando o disable */
				$(element).attr("readonly", "readonly");
			});

			$(".bloqueado-font-normal").each(function(index, element) {
				/* Criando uma CLASS para apenas leitura e simulando o disable */
				$(element).attr("readonly", "readonly");
			});

			/* Definindo os Campos Obrigatórios */
			$(".autoCompleteOff").each(function(index, element) {
				$(element).css("background-color", "#fff");
				$(element).attr("readonly", "readonly");
				$(element).on('focusin', function(e) {
					$(element).removeAttr("readonly");
				});
				//alert('autoCompleteOff');
			});

			$(".cel").each(function(index, element) {
				//$(element).attr('pattern',"\([0-9]{2}\)[\s][0-9]{4}-[0-9]{4,5}");
			});
		}

		function recarregarAceitarTab() {
			$(".aceitaTab").unbind("keydown");
			$(".aceitaTab").each(function(index) {
				$(this).on('keydown', function(ev) {
					var keyCode = ev.keyCode || ev.which;

					if (keyCode == 9) {
						ev.preventDefault();
						var start = this.selectionStart;
						var end = this.selectionEnd;
						var val = this.value;
						var selected = val.substring(start, end);
						var re, count;

						re = /^/gm;
						count = selected.match(re).length;
						this.value = val.substring(0, start) + selected.replace(re, '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;') + val.substring(end);

						if (start === end) {
							this.selectionStart = end + count;
						} else {
							this.selectionStart = start;
						}

						this.selectionEnd = end + count;
					}
				});
			});
		}

		function abrirModalProcessando() {
			/* Abre sempre que alguma tela for aberta */
			$('#loading-indicator').show();
			$('#myPleaseWait').modal('show');
		}

		function fecharModalProcessando() {
			/* Fecha sempre que a tela for completamente Carregada */
			$('#loading-indicator').hide();
			$('#myPleaseWait').modal('hide');
		}

		function excluir(registro, action, idSelecionado) {

			registro = tratadorDeAspas(registro);

			BootstrapDialog.show({
				size : BootstrapDialog.SIZE_LARGE,
				title : '!!! ATENÇÃO !!!',
				message : 'Deseja realmente excluir este registro? <br /><b>[' + registro + ']</b>',
				type : BootstrapDialog.TYPE_WARNING, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
				closable : true, // <-- Default value is false
				draggable : true, // <-- Default value is false
				buttons : [ {
					hotkey : 13,
					label : 'Cancelar',
					action : function(dialogRef) {
						dialogRef.close();
					}
				}, {
					icon : 'fa fa-trash',
					label : 'Excluir',
					cssClass : 'btn-warning',
					action : function(dialogRef) {
						window.location = "${contextPath}/restrito/" + action + ".src?method=excluir&idRegistroExcluir=" + idSelecionado;
					}
				} ]
			});

		}

		/* Usado para telas com ABAS */
		function inserirComAba(formulario) {
			var camposNaoPreenchidos = '';

			/* Validando os campos Obrigatorios */
			$(".obrigatorio").each(function(index, element) {
				if ($(element).val() == null || $(element).val() == '') {
					camposNaoPreenchidos = camposNaoPreenchidos + ' / ' + $(element).attr("id")
				}
			});

			if (camposNaoPreenchidos == null || camposNaoPreenchidos == '') {
				executar(formulario, 'inserir');
			} else {
				modalCamposObrigatorios(camposNaoPreenchidos);
			}

		}

		/* Usado para telas com ABAS */
		function alterarComAba(formulario) {
			var camposNaoPreenchidos = '';

			/* Validando os campos Obrigatorios */
			$(".obrigatorio").each(function(index, element) {
				if ($(element).val() == null || $(element).val() == '') {

					camposNaoPreenchidos = camposNaoPreenchidos + ' / ' + $(element).attr("id")
				}
			});

			if (camposNaoPreenchidos == null || camposNaoPreenchidos == '') {
				executar(formulario, 'alterar');
			} else {
				modalCamposObrigatorios(camposNaoPreenchidos);
			}

		}

		function executarComModalObrigatorios(formulario, acao, clazz) {
			var camposNaoPreenchidos = '';

			/* Validando os campos Obrigatorios */
			$("." + clazz).each(function(index, element) {
				if ($(element).val() == null || $(element).val() == '') {

					camposNaoPreenchidos = camposNaoPreenchidos + ' / ' + $(element).attr("id")
				}
			});

			if (camposNaoPreenchidos == null || camposNaoPreenchidos == '') {
				abrirModalProcessando();
				executarComSubmit(formulario, acao);
			} else {
				modalCamposObrigatorios(camposNaoPreenchidos);
			}

		}

		function getCamposObrigatoriosVazios() {
			var camposNaoPreenchidos = '';

			/* Validando os campos Obrigatorios */
			$(".obrigatorio").each(function(index, element) {
				if ($(element).val() == null || $(element).val() == '') {

					camposNaoPreenchidos = camposNaoPreenchidos + ' / ' + $(element).attr("id")
				}
			});

			return camposNaoPreenchidos;
		}

		function modalCamposObrigatorios(campos) {
			var mensagem = "";
			mensagem = '<div class="row">' + '<div class="form-group col-lg-12">' + '<strong>' + 'Por favor preencha todos os campos obrigatórios!' + '</strong>' + '</div>'
					+ '<div class="form-group col-lg-12">' + '<i class="fa fa-ban" color: red;></i>&nbsp;' + campos + '</div>' + '</div>';

			BootstrapDialog.show({
				size : BootstrapDialog.SIZE_NORMAL,
				title : 'Atenção',
				message : mensagem,
				type : BootstrapDialog.TYPE_WARNING, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
				closable : true, // <-- Default value is false
				draggable : true, // <-- Default value is false
				buttons : [ {
					hotkey : 13,
					label : 'Fechar',
					action : function(dialogRef) {
						dialogRef.close();
					}
				} ]
			});

		}

		function formatarDinheiro(v) {
			if (v.indexOf('.') === -1) {
				v = v.replace(/([\d]+)/, "$1,00");
			}

			v = v.replace(/([\d]+)\.([\d]{1})$/, "$1,$20");
			v = v.replace(/([\d]+)\.([\d]{2})$/, "$1,$2");
			v = v.replace(/([\d]+)([\d]{3}),([\d]{2})$/, "$1.$2,$3");

			return v;
		}

		function validarData(idCampo) {
			var dataSplit = $('#' + idCampo).val().split('/');
			var dia = dataSplit[0];
			var mes = dataSplit[1];
			var ano = dataSplit[2];

			if (dia == null || dia == '') {
				dia = null;
			}
			if (mes == null || mes == '') {
				mes = null;
			}
			if (ano == null || ano == '') {
				ano = null;
			}

			if (mes != null && mes > 12) {
				$('#' + idCampo).css('background-color', '#ffa7a7');
				$('#' + idCampo).val(null);
				$('#' + idCampo).focus();
				return;
			} else {
				$('#' + idCampo).css('background-color', '#fff');
			}

			if (dia != null && dia > 31) {
				$('#' + idCampo).css('background-color', '#ffa7a7');
				$('#' + idCampo).val(null);
				$('#' + idCampo).focus();
				return;
			} else {
				$('#' + idCampo).css('background-color', '#fff');
			}

			if (ano != null && ano.length == 4 && ano < 1900) {
				$('#' + idCampo).css('background-color', '#ffa7a7');
				$('#' + idCampo).val(null);
				$('#' + idCampo).focus();
				return;
			} else {
				$('#' + idCampo).css('background-color', '#fff');
			}

			if (mes != null && mes == 02) {
				if (dia != null && dia > 29) {
					$('#' + idCampo).css('background-color', '#ffa7a7');
					$('#' + idCampo).val(null);
					$('#' + idCampo).focus();
					return;
				} else if (dia != null && dia == 29) {
					if (ano != null && ano >= 1900 && ano % 4 != 0) {
						$('#' + idCampo).css('background-color', '#ffa7a7');
						$('#' + idCampo).val(null);
						$('#' + idCampo).focus();
						return;
					}
				}
				$('#' + idCampo).css('background-color', '#fff');
			}

			if (mes != null && (mes == 01 || mes == 03 || mes == 05 || mes == 07 || mes == 08 || mes == 10 || mes == 12)) {
				if (dia != null && dia > 31) {
					$('#' + idCampo).css('background-color', '#ffa7a7');
					$('#' + idCampo).val(null);
					$('#' + idCampo).focus();
					return;
				}
				$('#' + idCampo).css('background-color', '#fff');
			}

			if (mes != null && (mes == 04 || mes == 06 || mes == 09 || mes == 11)) {
				if (dia != null && dia > 30) {
					$('#' + idCampo).css('background-color', '#ffa7a7');
					$('#' + idCampo).val(null);
					$('#' + idCampo).focus();
					return;
				}
				$('#vencimentoPrimeiraParcela').css('background-color', '#fff');
			}
		}
		
		function validarHora(idCampo) {
			var horaSplit = $('#' + idCampo).val().split(':');
			var hora;
			var minuto;
			var segundo;
			
			if( horaSplit.length == 3){
				hora = horaSplit[0];
				minuto = horaSplit[1];
				segundo = horaSplit[2];
			}else{
				hora = horaSplit[0];
				minuto = horaSplit[1];
			}

			if (hora == null || hora == '') {
				hora = null;
			}
			if (minuto == null || minuto == '') {
				minuto = null;
			}
			if (segundo == null || segundo == '') {
				segundo = null;
			}

			if (hora != null && hora > 23) {
				$('#' + idCampo).css('background-color', '#ffa7a7');
				$('#' + idCampo).val(null);
				$('#' + idCampo).focus();
				return;
			} else {
				$('#' + idCampo).css('background-color', '#fff');
			}

			if (minuto != null && minuto > 60) {
				$('#' + idCampo).css('background-color', '#ffa7a7');
				$('#' + idCampo).val(null);
				$('#' + idCampo).focus();
				return;
			} else {
				$('#' + idCampo).css('background-color', '#fff');
			}

			if (segundo != null && segundo.length > 60) {
				$('#' + idCampo).css('background-color', '#ffa7a7');
				$('#' + idCampo).val(null);
				$('#' + idCampo).focus();
				return;
			} else {
				$('#' + idCampo).css('background-color', '#fff');
			}			
		}
	</script>

	<script type="text/javascript">
		// Este evendo é acionado após o carregamento total da página
		jQuery(window).load(function() {
			//Após a leitura da pagina
			//setTimeout(function(){
			/* INICIO - MODAL DE PROCESSANDO */
			fecharModalProcessando();
			/* TERMINO - MODAL DE PROCESSANDO */
			//}, 500);
		});
	</script>


	<script type="text/javascript">
		/*  Função para Bloquear botão voltar */

		(function(window) {
			'use strict';

			var noback = {

				//globals 
				version : '0.0.1',
				history_api : typeof history.pushState !== 'undefined',

				init : function() {
					window.location.hash = '#no-back';
					noback.configure();
				},

				hasChanged : function() {
					if (window.location.hash == '#no-back') {
						window.location.hash = '#BLOQUEIO';
						//mostra mensagem que não pode usar o btn volta do browser
						if ($("#msgAviso").css('display') == 'none') {
							$("#msgAviso").slideToggle("slow");
						}
					}
				},

				checkCompat : function() {
					if (window.addEventListener) {
						window.addEventListener("hashchange", noback.hasChanged, false);
					} else if (window.attachEvent) {
						window.attachEvent("onhashchange", noback.hasChanged);
					} else {
						window.onhashchange = noback.hasChanged;
					}
				},

				configure : function() {
					if (window.location.hash == '#no-back') {
						if (this.history_api) {
							history.pushState(null, '', '#BLOQUEIO');
						} else {
							window.location.hash = '#BLOQUEIO';
							//mostra mensagem que não pode usar o btn volta do browser
							if ($("#msgAviso").css('display') == 'none') {
								$("#msgAviso").slideToggle("slow");
							}
						}
					}
					noback.checkCompat();
					noback.hasChanged();
				}

			};

			// AMD support 
			if (typeof define === 'function' && define.amd) {
				define(function() {
					return noback;
				});
			}
			// For CommonJS and CommonJS-like 
			else if (typeof module === 'object' && module.exports) {
				module.exports = noback;
			} else {
				window.noback = noback;
			}
			noback.init();
		}(window));

		function tratadorDeAspas(texto) {
			return texto.replace('\"', '&quot;').replace('\'', '&quot;');
		}
	</script>
</body>
</html:html>