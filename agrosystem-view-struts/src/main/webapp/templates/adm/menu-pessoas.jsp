<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>

<logic:equal name="loginForm" value="true" property="exibirGrupoMenu(${usuarioSessaoPOJO.usuario.id}.funcionario.cliente.fornecedor)">
	<li>
		<a href="#" class="cor-menu-sistema">
			<i class="fa fa-circle-o fa-fw" style="font-size: 14px;"></i>
			Cadastros Pessoas
			<span class="fa arrow" style="font-size: 20px;"></span>
		</a>
		<ul class="nav nav-second-level">			
			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.funcionario.acessar)">
				<li>
					<a href="${contextPath}/restrito/admin/funcionario.src?method=abrirListagem">
						<i class="fa fa-circle-thin fa-fw" style="font-size: 12px;"></i>
						Funcionario
					</a>
				</li>
			</logic:equal>
			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.cliente.acessar)">
				<li>
					<a href="${contextPath}/restrito/admin/cliente.src?method=abrirListagem">
						<i class="fa fa-circle-thin fa-fw" style="font-size: 12px;"></i>
						Cliente
					</a>
				</li>
			</logic:equal>
			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.fornecedor.acessar)">
				<li>
					<a href="${contextPath}/restrito/admin/fornecedor.src?method=abrirListagem">
						<i class="fa fa-circle-thin fa-fw" style="font-size: 12px;"></i>
						Fornecedor
					</a>
				</li>
			</logic:equal>			
		</ul>
		<!-- /.nav-second-level -->
	</li>
</logic:equal>