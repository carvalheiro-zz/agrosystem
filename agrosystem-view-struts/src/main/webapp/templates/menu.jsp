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
		</ul>
	</li>
</logic:equal>

<%-- <logic:equal name="loginForm" value="true" property="exibirGrupoMenu(${usuarioSessaoPOJO.usuario.id}.relatorio)"> --%>
<li>
	<a href="#" class="cor-menu-sistema">
		<i class="fa fa-cube" style="font-size: 14px;"></i>
		Relatórios
		<span class="fa arrow" style="font-size: 20px;"></span>
	</a>
	<ul class="nav nav-second-level" style="background-color: rgb(234, 234, 234);">
		<li>
			<a href="${contextPath}/restrito/sistema/relacaoSafraSetorCulturaVariedade.src?method=abrirListagem">
				<i class="fa fa-file-pdf-o" style="font-size: 12px;"></i>
				Relatório Safra/Variedades
			</a>
		</li>
	</ul>
</li>
<%-- </logic:equal> --%>

<logic:equal name="loginForm" value="true" property="exibirGrupoMenu(${usuarioSessaoPOJO.usuario.id}.centroCustoReceita.clienteJuridico.formaPagamento.funcionario.imovel.implemento.servico.veiculo)">
	<li>
		<a href="#" class="cor-menu-sistema">
			<i class="fa fa-newspaper-o" style="font-size: 14px;"></i>
			Cadastros
			<span class="fa arrow" style="font-size: 20px;"></span>
		</a>
		<ul class="nav nav-second-level" style="background-color: rgb(234, 234, 234);">

			<%-- <logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.centroCustoReceita.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/centroCustoReceita.src?method=abrirListagem">
						<i class="fa fa-circle-thin" style="font-size: 12px;"></i>
						Centro de Custo
					</a>
				</li>
			</logic:equal> --%>
			<%-- <logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.tipoFormaPagamento.acessar)">
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
						<i class="fa fa-circle-thin" style="font-size: 12px;"></i>
						Forma de Pagamento
					</a>
				</li>
			</logic:equal> --%>
			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.funcionario.acessar)">
				<li>
					<a href="${contextPath}/restrito/admin/funcionario.src?method=abrirListagem">
						<i class="fa fa-circle-thin" style="font-size: 12px;"></i>
						Colaborador
					</a>
				</li>
			</logic:equal>
			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.imovel.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/imovel.src?method=abrirListagem">
						<i class="fa fa-circle-thin" style="font-size: 12px;"></i>
						Imóvel
					</a>
				</li>
			</logic:equal>
			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.implemento.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/implemento.src?method=abrirListagem">
						<i class="fa fa-circle-thin" style="font-size: 12px;"></i>
						Implemento
					</a>
				</li>
			</logic:equal>
			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.servico.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/servico.src?method=abrirListagem">
						<i class="fa fa-circle-thin" style="font-size: 12px;"></i>
						Serviço
					</a>
				</li>
			</logic:equal>
			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.veiculo.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/veiculo.src?method=abrirListagem">
						<i class="fa fa-circle-thin" style="font-size: 12px;"></i>
						Veículo
					</a>
				</li>
			</logic:equal>
			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.prestadorServico.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/prestadorServico.src?method=abrirListagem">
						<i class="fa fa-circle-thin" style="font-size: 12px;"></i>
						Prestador de Serviço
					</a>
				</li>
			</logic:equal>
		</ul>
	</li>
</logic:equal>

<logic:equal name="loginForm" value="true" property="exibirGrupoMenu(${usuarioSessaoPOJO.usuario.id}.horaExtra)">
	<li>
		<a href="#" class="cor-menu-sistema">
			<i class="fa fa-newspaper-o" style="font-size: 14px;"></i>
			Horas Extras / Férias
			<span class="fa arrow" style="font-size: 20px;"></span>
		</a>
		<ul class="nav nav-second-level" style="background-color: rgb(234, 234, 234);">
			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.horaExtra.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/horaExtra.src?method=abrirListagem">
						<i class="fa fa-circle-thin" style="font-size: 12px;"></i>
						Horas Extras
					</a>
				</li>
			</logic:equal>
			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.ferias.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/ferias.src?method=abrirListagem">
						<i class="fa fa-circle-thin" style="font-size: 12px;"></i>
						Férias
					</a>
				</li>
			</logic:equal>
		</ul>
	</li>
</logic:equal>

<logic:equal name="loginForm" value="true" property="exibirGrupoMenu(${usuarioSessaoPOJO.usuario.id}.marca.unidadeMedida.tipo.produto.fornecedorJuridico)">
	<li>
		<a href="#" class="cor-menu-sistema">
			<i class="fa fa-newspaper-o" style="font-size: 14px;"></i>
			Cadastros Insumos / Itens
			<span class="fa arrow" style="font-size: 20px;"></span>
		</a>
		<ul class="nav nav-second-level" style="background-color: rgb(234, 234, 234);">
			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.tipo.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/tipo.src?method=abrirListagem">
						<i class="fa fa-circle-thin" style="font-size: 12px;"></i>
						Categoria de Produto
					</a>
				</li>
			</logic:equal>
			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.fornecedorJuridico.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/fornecedorJuridico.src?method=abrirListagem">
						<i class="fa fa-circle-thin" style="font-size: 12px;"></i>
						Fornecedor
					</a>
				</li>
			</logic:equal>
			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.marca.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/marca.src?method=abrirListagem">
						<i class="fa fa-circle-thin" style="font-size: 12px;"></i>
						Marca
					</a>
				</li>
			</logic:equal>
			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.produto.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/produto.src?method=abrirListagem">
						<i class="fa fa-circle-thin" style="font-size: 12px;"></i>
						Produto
					</a>
				</li>
			</logic:equal>
			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.unidadeMedida.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/unidadeMedida.src?method=abrirListagem">
						<i class="fa fa-circle-thin" style="font-size: 12px;"></i>
						Unidade de Medida
					</a>
				</li>
			</logic:equal>
		</ul>
	</li>
</logic:equal>

<logic:equal name="loginForm" value="true" property="exibirGrupoMenu(${usuarioSessaoPOJO.usuario.id}.cultura.setor.safra)">
	<li>
		<a href="#" class="cor-menu-sistema">
			<i class="fa fa-newspaper-o" style="font-size: 14px;"></i>
			Cadastros Safra
			<span class="fa arrow" style="font-size: 20px;"></span>
		</a>
		<ul class="nav nav-second-level" style="background-color: rgb(234, 234, 234);">

			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.cultura.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/cultura.src?method=abrirListagem">
						<i class="fa fa-circle-thin" style="font-size: 12px;"></i>
						Cultura
					</a>
				</li>
			</logic:equal>
			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.variedade.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/variedade.src?method=abrirListagem">
						<i class="fa fa-circle-thin" style="font-size: 12px;"></i>
						Variedade
					</a>
				</li>
			</logic:equal>
			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.setor.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/setor.src?method=abrirListagem">
						<i class="fa fa-circle-thin" style="font-size: 12px;"></i>
						Setor
					</a>
				</li>
			</logic:equal>
			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.safra.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/safra.src?method=abrirListagem">
						<i class="fa fa-circle-thin" style="font-size: 12px;"></i>
						Safra
					</a>
				</li>
			</logic:equal>
		</ul>
	</li>
</logic:equal>

<logic:equal name="loginForm" value="true" property="exibirGrupoMenu(${usuarioSessaoPOJO.usuario.id}.pedido.notaFiscalVenda.notaFiscalSimplesRemessa.notaFiscalVendaDireta.notaFiscalCupom.cupom)">
	<li>
		<a href="#" class="cor-menu-sistema">
			<i class="fa fa-clipboard" style="font-size: 14px;"></i>
			Pedido / Notas
			<span class="fa arrow" style="font-size: 20px;"></span>
		</a>
		<ul class="nav nav-second-level" style="background-color: rgb(234, 234, 234);">
			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.cupom.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/cupom.src?method=abrirListagem">
						<i class="fa fa-pencil-square-o" style="font-size: 12px;"></i>
						Cupom
					</a>
				</li>
			</logic:equal>
			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.pedido.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/pedido.src?method=abrirListagem">
						<i class="fa fa-truck" style="font-size: 12px;"></i>
						Lançar Pedido
					</a>
				</li>
			</logic:equal>
			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.notaFiscalCupom.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/notaFiscalCupom.src?method=abrirListagem">
						<i class="fa fa-clipboard" style="font-size: 12px;"></i>
						Nota Fiscal Cupom
					</a>
				</li>
			</logic:equal>
			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.notaFiscalSimplesRemessa.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/notaFiscalSimplesRemessa.src?method=abrirListagem">
						<i class="fa fa-clipboard" style="font-size: 12px;"></i>
						Nota Fiscal Simples Remessa
					</a>
				</li>
			</logic:equal>
			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.notaFiscalVenda.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/notaFiscalVenda.src?method=abrirListagem&tipoNF=Venda&notaFiscalVendaFilter.tipo=Venda">
						<i class="fa fa-clipboard" style="font-size: 12px;"></i>
						Nota Fiscal Venda
					</a>
				</li>
			</logic:equal>
			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.notaFiscalVendaDireta.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/notaFiscalVendaDireta.src?method=abrirListagem">
						<i class="fa fa-clipboard" style="font-size: 12px;"></i>
						Nota Fiscal Venda Direta
					</a>
				</li>
			</logic:equal>
			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.notaFiscalVenda.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/notaFiscalVenda.src?method=abrirListagem&tipoNF=Futura&notaFiscalVendaFilter.tipo=Futura">
						<i class="fa fa-clipboard" style="font-size: 12px;"></i>
						Nota Fiscal Venda Futura
					</a>
				</li>
			</logic:equal>
			<%-- <logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.notaFiscalRateio.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/notaFiscalRateioDespesa.src?method=abrirListagem">
						<i class="fa fa-clipboard" style="font-size: 12px;"></i>
						Rateio Despesa
					</a>
				</li>
			</logic:equal>
			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.notaFiscalRateio.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/notaFiscalRateioReceita.src?method=abrirListagem">
						<i class="fa fa-clipboard" style="font-size: 12px;"></i>
						Rateio Receita
					</a>
				</li>
			</logic:equal> --%>
		</ul>
	</li>
</logic:equal>




<logic:equal name="loginForm" value="true" property="exibirGrupoMenu(${usuarioSessaoPOJO.usuario.id}.aplicacao)">
	<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.aplicacao.acessar)">
		<li>
			<a href="${contextPath}/restrito/sistema/aplicacao.src?method=abrirListagem">
				<i class="fa fa-bug" style="font-size: 14px;"></i>
				Aplicações Insumos
			</a>
		</li>
	</logic:equal>
</logic:equal>

<logic:equal name="loginForm" value="true" property="exibirGrupoMenu(${usuarioSessaoPOJO.usuario.id}.item)">
	<li>
		<a href="#" class="cor-menu-sistema">
			<i class="fa fa-cube" style="font-size: 14px;"></i>
			Aplicações Itens
			<span class="fa arrow" style="font-size: 20px;"></span>
		</a>
		<ul class="nav nav-second-level" style="background-color: rgb(234, 234, 234);">

			<li>
				<a href="${contextPath}/restrito/sistema/itemImovel.src?method=abrirListagem">
					<i class="fa fa-cube" style="font-size: 12px;"></i>
					Aplicação Imóvel
				</a>
			</li>
			<li>
				<a href="${contextPath}/restrito/sistema/itemImplemento.src?method=abrirListagem">
					<i class="fa fa-cube" style="font-size: 12px;"></i>
					Aplicação Implemento
				</a>
			</li>
			<li>
				<a href="${contextPath}/restrito/sistema/itemVeiculo.src?method=abrirListagem">
					<i class="fa fa-cube" style="font-size: 12px;"></i>
					Aplicação Veículos
				</a>
			</li>
		</ul>
	</li>
</logic:equal>

<logic:equal name="loginForm" value="true" property="exibirGrupoMenu(${usuarioSessaoPOJO.usuario.id}.manutencao)">
	<li>
		<a href="#" class="cor-menu-sistema">
			<i class="fa fa-wrench" style="font-size: 14px;"></i>
			Mão de Obra
			<span class="fa arrow" style="font-size: 20px;"></span>
		</a>
		<ul class="nav nav-second-level" style="background-color: rgb(234, 234, 234);">
			<li>
				<a href="${contextPath}/restrito/sistema/manutencaoImovel.src?method=abrirListagem">
					<i class="fa fa-wrench" style="font-size: 12px;"></i>
					Mão de Obra Imóvel
				</a>
			</li>
			<li>
				<a href="${contextPath}/restrito/sistema/manutencaoImplemento.src?method=abrirListagem">
					<i class="fa fa-wrench" style="font-size: 12px;"></i>
					Mão de Obra Implemento
				</a>
			</li>
			<li>
				<a href="${contextPath}/restrito/sistema/manutencaoVeiculo.src?method=abrirListagem">
					<i class="fa fa-wrench" style="font-size: 12px;"></i>
					Mão de Obra Veículo
				</a>
			</li>
		</ul>
	</li>
</logic:equal>


<logic:equal name="loginForm" value="true" property="exibirGrupoMenu(${usuarioSessaoPOJO.usuario.id}.abastecimento.consumo)">
	<li>
		<a href="${contextPath}/restrito/sistema/abastecimento.src?method=abrirListagem">
			<i class="fa fa-tint" style="font-size: 14px;"></i>
			Abastecimento
		</a>
	</li>
</logic:equal>

<logic:equal name="loginForm" value="true" property="exibirGrupoMenu(${usuarioSessaoPOJO.usuario.id}.silo.cliente.entradaGrao.saidaGrao)">
	<li>
		<a href="#" class="cor-menu-sistema">
			<i class="fa fa-database" style="font-size: 14px;"></i>
			Silo
			<span class="fa arrow" style="font-size: 20px;"></span>
		</a>
		<ul class="nav nav-second-level" style="background-color: rgb(234, 234, 234);">
			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.contratoVenda.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/contratoVenda.src?method=abrirListagem">
						<i class="fa fa-circle-thin fa-fw" style="font-size: 12px;"></i>
						Contrato de Venda
					</a>
				</li>
			</logic:equal>

			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.naturezaOperacao.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/naturezaOperacao.src?method=abrirListagem">
						<i class="fa fa-circle-thin fa-fw" style="font-size: 12px;"></i>
						Natureza de Operação
					</a>
				</li>
			</logic:equal>

			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.silo.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/silo.src?method=abrirListagem">
						<i class="fa fa-circle-thin fa-fw" style="font-size: 12px;"></i>
						Silo
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
			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.entradaGrao.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/entradaGrao.src?method=abrirListagem">
						<i class="fa fa-circle-thin fa-fw" style="font-size: 12px;"></i>
						Entrada Silo
					</a>
				</li>
			</logic:equal>

			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.saidaGrao.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/saidaGrao.src?method=abrirListagem">
						<i class="fa fa-circle-thin fa-fw" style="font-size: 12px;"></i>
						Saida Silo
					</a>
				</li>
			</logic:equal>

			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.ajusteSilo.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/ajusteSilo.src?method=abrirListagem&codigoTempTipo=1">
						<i class="fa fa-circle-thin fa-fw" style="font-size: 12px;"></i>
						Sobra de Classificação (+)
					</a>
				</li>
			</logic:equal>
			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.ajusteSilo.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/ajusteSilo.src?method=abrirListagem&codigoTempTipo=2">
						<i class="fa fa-circle-thin fa-fw" style="font-size: 12px;"></i>
						Quebra de Classificação (-)
					</a>
				</li>
			</logic:equal>
		</ul>
	</li>
</logic:equal>

<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.estoque.acessar)">
	<li>
		<a href="${contextPath}/restrito/sistema/estoque.src?method=abrirListagem">
			<i class="fa fa-cubes" style="font-size: 14px;"></i>
			Estoque
		</a>
	</li>
</logic:equal>

<%-- <jsp:include page="adm/menu-financeiro.jsp"></jsp:include> --%>

<%-- <logic:equal name="loginForm" value="true" property="exibirGrupoMenu(${usuarioSessaoPOJO.usuario.id}.tituloPagar.tituloReceber)">
	<li>
		<a href="#" class="cor-menu-sistema">
			<i class="fa fa-money fa-fw" style="font-size: 14px;"></i>
			Financeiro
			<span class="fa arrow" style="font-size: 20px;"></span>
		</a>
		<ul class="nav nav-second-level" style="background-color: rgb(234, 234, 234);">
			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.tituloPagar.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/tituloPagar.src?method=abrirListagem">
						<i class="fa fa-money fa-fw" style="font-size: 12px; color: red"></i>
						Manipular pagamentos
					</a>
				</li>
			</logic:equal>

			<logic:equal name="loginForm" value="true" property="acessoPermitido(${usuarioSessaoPOJO.usuario.id}.tituloReceber.acessar)">
				<li>
					<a href="${contextPath}/restrito/sistema/tituloReceber.src?method=abrirListagem">
						<i class="fa fa-money fa-fw" style="font-size: 12px; color: blue;"></i>
						Manipular a recebimentos
					</a>
				</li>
			</logic:equal>

		</ul>
	</li>
</logic:equal> --%>

<jsp:include page="adm/menu-geracao-relatorios.jsp"></jsp:include>

