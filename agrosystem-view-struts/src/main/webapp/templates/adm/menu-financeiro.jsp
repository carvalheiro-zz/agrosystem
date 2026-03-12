<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>

<logic:equal name="loginForm" value="true" property="exibirGrupoMenu(${usuarioSessaoPOJO.usuario.id}.caixa.formaPagamento.despesa.receita.tituloPagar.tituloReceber)">
	<li>
		<a href="#" class="cor-menu-sistema">
			<i class="fa fa-money fa-fw" style="font-size: 14px;"></i>
			Financeiro
			<span class="fa arrow" style="font-size: 20px;"></span>
		</a>
		<ul class="nav nav-second-level" style="background-color: rgb(234, 234, 234);">

			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.controleCaixa.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/controleCaixa.src?method=abrirCadastro">
						<i class="fa fa-circle-thin fa-fw" style="font-size: 12px;"></i>
						Abrir/Fechar Caixa
					</a>
				</li>
				<li>
					<a href="${contextPath}/restrito/sistema/controleCaixa.src?method=abrirListagem">
						<i class="fa fa-circle-thin fa-fw" style="font-size: 12px;"></i>
						Gerenciar Caixa
					</a>
				</li>
			</logic:equal>
			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.tipoFormaPagamento.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/tipoFormaPagamento.src?method=abrirListagem">
						<i class="fa fa-circle-thin fa-fw" style="font-size: 12px;"></i>
						Tipo Forma de Pagamento
					</a>
				</li>
			</logic:equal>
			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.formaPagamento.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/formaPagamento.src?method=abrirListagem">
						<i class="fa fa-circle-thin fa-fw" style="font-size: 12px;"></i>
						Forma de Pagamento
					</a>
				</li>
			</logic:equal>
			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.receita.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/receita.src?method=abrirListagem">
						<i class="fa fa-money fa-fw" style="font-size: 12px; color: blue;"></i>
						Lançar receitas
					</a>
				</li>
			</logic:equal>


			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.tituloReceber.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/tituloReceber.src?method=abrirListagem">
						<i class="fa fa-money fa-fw" style="font-size: 12px; color: blue;"></i>
						Contas à receber
					</a>
				</li>
			</logic:equal>
			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.despesa.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/despesa.src?method=abrirListagem">
						<i class="fa fa-money fa-fw" style="font-size: 12px; color: red"></i>
						Lançar despesas
					</a>
				</li>
			</logic:equal>
			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.tituloPagar.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/tituloPagar.src?method=abrirListagem">
						<i class="fa fa-money fa-fw" style="font-size: 12px; color: red"></i>
						Contas à pagar
					</a>
				</li>
			</logic:equal>
		</ul>
	</li>
</logic:equal>