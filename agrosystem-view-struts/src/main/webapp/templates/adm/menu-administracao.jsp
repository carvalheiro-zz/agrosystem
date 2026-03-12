<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>

<logic:equal name="loginForm" value="true" property="exibirGrupoMenu(${usuarioSessaoPOJO.usuario.id}.empresa.tipoUsuario.usuario.parametrizacao.ativacao)">
	<li>
		<a href="#" class="cor-menu-sistema">
			<i class="fa fa-gears fa-fw" style="font-size: 14px;"></i>
			Administração
			<span class="fa arrow" style="font-size: 20px;"></span>
		</a>
		<ul class="nav nav-second-level">
			<logic:equal value="true" name="usuarioSessaoPOJO" property="isADM" scope="session">
				<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.empresa.acessar)">
					<li>
						<a href="${contextPath}/restrito/admin/empresa.src?method=abrirListagem">
							<i class="fa fa-circle-thin fa-fw" style="font-size: 12px;"></i>
							Empresa
						</a>
					</li>
				</logic:equal>
			</logic:equal>
			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.tipoUsuario.acessar)">
				<li>
					<a href="${contextPath}/restrito/admin/tipoUsuario.src?method=abrirListagem">
						<i class="fa fa-circle-thin fa-fw" style="font-size: 12px;"></i>
						Tipo de Usuário
					</a>
				</li>
			</logic:equal>

			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.usuario.acessar)">
				<li>
					<a href="${contextPath}/restrito/admin/usuario.src?method=abrirListagem">
						<i class="fa fa-circle-thin fa-fw" style="font-size: 12px;"></i>
						Usuário
					</a>
				</li>
			</logic:equal>

			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.parametrizacao.acessar)">
				<li>
					<a href="${contextPath}/restrito/admin/parametrizacao.src?method=abrirCadastro">
						<i class="fa fa-circle-thin fa-fw" style="font-size: 12px;"></i>
						Parametrização
					</a>
				</li>
			</logic:equal>

			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.ativacao.acessar)">
				<li>
					<a href="${contextPath}/restrito/admin/ativacao.src?method=abrirCadastro">
						<i class="fa fa-circle-thin fa-fw" style="font-size: 12px;"></i>
						Ativação
					</a>
				</li>
			</logic:equal>

			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.logViewer.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/logViewer.src?method=abrirTela">
						<i class="fa fa-circle-thin fa-fw" style="font-size: 12px;"></i>
						Log Viewer
					</a>
				</li>
			</logic:equal>

			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.painelTestesMail.acessar)">
				<li>
					<a href="${contextPath}/restrito/admin/painelTestesMail.src?method=abrirCadastro">
						<i class="fa fa-circle-thin fa-fw" style="font-size: 12px;"></i>
						Painel testes email
					</a>
				</li>
			</logic:equal>
		</ul>
	</li>
</logic:equal>