<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>

<logic:equal name="loginForm" value="true" property="exibirGrupoMenu(${usuarioSessaoPOJO.usuario.id}.query)">
	<li>
		<a href="#" class="cor-menu-sistema">
			<i class="fa fa-circle-o fa-fw" style="font-size: 14px;"></i>
			Gerar Tela por Query
			<span class="fa arrow" style="font-size: 20px;"></span>
		</a>
		<ul class="nav nav-second-level">

			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.query.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/query.src?method=abrirListagem">
						<i class="fa fa-circle-thin fa-fw" style="font-size: 12px;"></i>
						Query
					</a>
				</li>
			</logic:equal>

		</ul>
		<!-- /.nav-second-level -->
	</li>
</logic:equal>
<!-- TELAS DINAMICAS CRIADAS A PARTIR DA TELA DE CADASTRO DE QUERY -->
<logic:notEmpty name="desktopForm" property="queriesX(${usuarioSessaoPOJO.usuario.id})">
	<li>
		<a href="#" class="cor-menu-sistema">
			<i class="fa fa-circle-o fa-fw" style="font-size: 14px;"></i>
			Relatórios das Queries
			<span class="fa arrow" style="font-size: 20px;"></span>
		</a>
		<ul class="nav nav-second-level">
			<!-- TELAS DINAMICAS CRIADAS A PARTIR DA TELA DE CADASTRO DE QUERY -->
			<logic:notEmpty name="desktopForm" property="queriesX(${usuarioSessaoPOJO.usuario.id})">
				<logic:iterate id="queryCorrente" name="desktopForm" property="queriesX(${usuarioSessaoPOJO.usuario.id})" type="br.com.srcsoftware.modular.manager.query.QueryDTO">
					<%-- <logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.${queryCorrente.nome}.acessar)"> --%>
						<li>
							<a href="${contextPath}/restrito/sistema/queryView.src?method=abrirTela&idQuery=${queryCorrente.id}" style="font-size: 12px;">
								<i class="fa fa-file-excel-o fa-fw" style="font-size: 12px;"></i>
								${queryCorrente.tituloTela}
							</a>
						</li>
					<%-- </logic:equal> --%>
				</logic:iterate>
			</logic:notEmpty>
		</ul>
		<!-- /.nav-second-level -->
	</li>
</logic:notEmpty>