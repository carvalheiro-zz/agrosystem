package br.com.srcsoftware.sistema.pedido.pedido;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;
import br.com.srcsoftware.sistema.pedido.itempedido.ItemPedidoDTO;

public final class PedidoFacade implements PedidoServiceInterface{

	private final PedidoServiceImpl service;

	private PedidoFacade(){
		this.service = new PedidoServiceImpl();
	}

	public static PedidoFacade getInstance() {
		return new PedidoFacade();
	}

	@Override
	public List< PedidoDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, PedidoDTO dto, String dataInicialParam, String dataFinalParam ) throws br.com.srcsoftware.modular.manager.exceptions.ApplicationException {
		return service.filtrar( paginacao, camposOrders, dto, dataInicialParam, dataFinalParam );
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, PedidoDTO dto ) throws br.com.srcsoftware.modular.manager.exceptions.ApplicationException {
		service.inserir( usuarioSessaoPOJO, dto );
	}

	@Override
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, PedidoDTO dto ) throws br.com.srcsoftware.modular.manager.exceptions.ApplicationException {
		service.alterar( usuarioSessaoPOJO, dto );
	}

	@Override
	public void excluir( PedidoDTO dto ) throws br.com.srcsoftware.modular.manager.exceptions.ApplicationException {
		service.excluir( dto );
	}

	@Override
	public PedidoDTO filtrarPorId( String id ) throws br.com.srcsoftware.modular.manager.exceptions.ApplicationException {
		return service.filtrarPorId( id );
	}

	@Override
	public List< PedidoDTO > filtrarAbertosAndamentos( String numeroPedido, HashMap< String, String > camposOrders ) throws br.com.srcsoftware.modular.manager.exceptions.ApplicationException {
		return service.filtrarAbertosAndamentos( numeroPedido, camposOrders );
	}

	@Override
	public void adicionarItem( ItemPedidoDTO item, PedidoDTO pedido ) throws ApplicationException {
		service.adicionarItem( item, pedido );
	}

	@Override
	public void removerItem( PedidoDTO pedido, String idItem ) throws ApplicationException {
		service.removerItem( pedido, idItem );
	}

	@Override
	public String calcularValoresRestantesEntregarPedido( String idProduto, String idPedido ) throws ApplicationException {
		return service.calcularValoresRestantesEntregarPedido( idProduto, idPedido );
	}

	@Override
	public String calcularValoresRestantesEntregarPedido( String idPedido ) throws ApplicationException {
		return service.calcularValoresRestantesEntregarPedido( idPedido );
	}

	@Override
	public String calcularValoresRestantesEntregarNFVendaNFFutura( String idProduto, String idPedido ) throws ApplicationException {
		return service.calcularValoresRestantesEntregarNFVendaNFFutura( idProduto, idPedido );
	}

	@Override
	public String calcularValoresRestantesEntregarNFSimplesRemessa( String idProduto, String idNotaFiscalVenda ) throws ApplicationException {
		return service.calcularValoresRestantesEntregarNFSimplesRemessa( idProduto, idNotaFiscalVenda );
	}

	@Override
	public String calcularValoresRestantesEntregarPedido( String idPedido, boolean considerarFutura ) throws ApplicationException {
		return service.calcularValoresRestantesEntregarPedido( idPedido, considerarFutura );
	}

	@Override
	public String calcularValoresRestantesEntregarEstoque( String idProduto ) throws ApplicationException {
		return service.calcularValoresRestantesEntregarEstoque( idProduto );
	}

}
