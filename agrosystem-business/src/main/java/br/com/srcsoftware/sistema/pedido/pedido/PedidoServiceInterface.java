package br.com.srcsoftware.sistema.pedido.pedido;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;
import br.com.srcsoftware.sistema.pedido.itempedido.ItemPedidoDTO;

public interface PedidoServiceInterface{

	List< PedidoDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, PedidoDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException;

	void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, PedidoDTO dto ) throws ApplicationException;

	void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, PedidoDTO dto ) throws ApplicationException;

	void excluir( PedidoDTO dto ) throws ApplicationException;

	PedidoDTO filtrarPorId( String id ) throws ApplicationException;

	List< PedidoDTO > filtrarAbertosAndamentos( String numeroPedido, HashMap< String, String > camposOrders ) throws ApplicationException;

	void adicionarItem( ItemPedidoDTO item, PedidoDTO pedido ) throws ApplicationException;

	void removerItem( PedidoDTO pedido, String idItem ) throws ApplicationException;

	String calcularValoresRestantesEntregarEstoque( String idProduto ) throws ApplicationException;

	/**
	 * Método responsável por informar a QUANTIDADE REAL restantes que ainda não foram entregues.
	 * <br />
	 * <b>OBS:</b><br />
	 * Considera apenas como sendo ENTREGUES - Nota Fiscal de Venda / Nota Fiscal de Simples Remessa
	 * <br />
	 * <b>Restante = quantidades pedidas - (Nota Fiscal Venda + Nota Fiscal Simples Remessa)<b />
	 *
	 * @param idProduto - id do PRODUTO no qual se deseja saber a quantidade que ainda falta entregar.
	 * @param idPedido - id do PEDIDO no qual se deseja saber a quantidade de itens que ainda faltam entregar.
	 * @return BigDecimal - quantidade de item a serem entregues com base na seguinte combinação: Produto, Pedido e Produto/Pedido
	 * @throws ApplicationException
	 *
	 * @author Gabriel Damiani Carvalheiro <gabriel.carvalheiro@gmail.com.br>
	 * @since 23 de mai de 2017 17:50:53
	 * @version 1.0
	 */
	String calcularValoresRestantesEntregarPedido( String idProduto, String idPedido ) throws ApplicationException;

	/**
	 * Método responsável por informar a QUANTIDADE REAL restantes que ainda não foram entregues.
	 * 
	 * <b>OBS:</b><br />
	 * Considera apenas como sendo ENTREGUES - Nota Fiscal de Venda / Nota Fiscal Futura
	 * <br />
	 * <b>Restante = quantidades pedidas - (Nota Fiscal Venda + Nota Fiscal Simples Remessa)<b />
	 *
	 * @param idPedido - id do PEDIDO no qual se deseja saber a quantidade de itens que ainda faltam entregar.
	 * @return BigDecimal - quantidade de item a serem entregues do Pedido
	 * @throws ApplicationException
	 *
	 * @author Gabriel Damiani Carvalheiro <gabriel.carvalheiro@gmail.com.br>
	 * @since 29 de mai de 2017 17:06:02
	 * @version 1.0
	 */
	String calcularValoresRestantesEntregarPedido( String idPedido ) throws ApplicationException;

	String calcularValoresRestantesEntregarNFVendaNFFutura( String idProduto, String idPedido ) throws ApplicationException;

	String calcularValoresRestantesEntregarNFSimplesRemessa( String idProduto, String idNotaFiscalVenda ) throws ApplicationException;

	String calcularValoresRestantesEntregarPedido( String idPedido, boolean considerarFutura ) throws ApplicationException;
}
