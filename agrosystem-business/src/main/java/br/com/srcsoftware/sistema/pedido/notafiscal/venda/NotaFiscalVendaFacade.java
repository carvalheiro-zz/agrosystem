package br.com.srcsoftware.sistema.pedido.notafiscal.venda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;
import br.com.srcsoftware.sistema.pedido.itempedido.ItemPedidoDTO;

public final class NotaFiscalVendaFacade implements NotaFiscalVendaServiceInterface{

	private final NotaFiscalVendaServiceImpl service;

	private NotaFiscalVendaFacade(){
		this.service = new NotaFiscalVendaServiceImpl();
	}

	public static NotaFiscalVendaFacade getInstance() {
		return new NotaFiscalVendaFacade();
	}

	@Override
	public List< NotaFiscalVendaDTO > filtrar( Paginacao paginacao,
	        HashMap< String, String > camposOrders,
	        NotaFiscalVendaDTO dto,
	        String dataInicialParam,
	        String dataFinalParam ) throws br.com.srcsoftware.modular.manager.exceptions.ApplicationException {
		return service.filtrar( paginacao, camposOrders, dto, dataInicialParam, dataFinalParam );
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, NotaFiscalVendaDTO dto ) throws br.com.srcsoftware.modular.manager.exceptions.ApplicationException {
		service.inserir( usuarioSessaoPOJO, dto );
	}

	@Override
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, NotaFiscalVendaDTO dto ) throws br.com.srcsoftware.modular.manager.exceptions.ApplicationException {
		service.alterar( usuarioSessaoPOJO, dto );
	}

	@Override
	public void excluir( NotaFiscalVendaDTO dto ) throws br.com.srcsoftware.modular.manager.exceptions.ApplicationException {
		service.excluir( dto );
	}

	@Override
	public NotaFiscalVendaDTO filtrarPorId( String id ) throws br.com.srcsoftware.modular.manager.exceptions.ApplicationException {
		return service.filtrarPorId( id );
	}

	@Override
	public String getTotalFaltaEntregar( String idProduto, String idPedido ) throws br.com.srcsoftware.modular.manager.exceptions.ApplicationException {
		return service.getTotalFaltaEntregar( idProduto, idPedido );
	}

	@Override
	public List< NotaFiscalVendaDTO > filtrarAbertosAndamentos( String numeroNF, String tipoNF, HashMap< String, String > camposOrders ) throws br.com.srcsoftware.modular.manager.exceptions.ApplicationException {
		return service.filtrarAbertosAndamentos( numeroNF, tipoNF, camposOrders );
	}

	@Override
	public void adicionarItemRecebido( NotaFiscalVendaDTO notaFiscalVenda, ArrayList< ItemPedidoDTO > itensPedidosRestantes, String idItemPedidoReceber, String quantidadeItensAdicionar ) throws ApplicationException {
		service.adicionarItemRecebido( notaFiscalVenda, itensPedidosRestantes, idItemPedidoReceber, quantidadeItensAdicionar );
	}

	@Override
	public void removerItemRecebido( String tipoNF, NotaFiscalVendaDTO notaFiscalVenda, ArrayList< ItemPedidoDTO > itensPedidosRestantes, String idItemPedidoReceber, String quantidadeItensRetornar ) throws ApplicationException {
		service.removerItemRecebido( tipoNF, notaFiscalVenda, itensPedidosRestantes, idItemPedidoReceber, quantidadeItensRetornar );
	}

}
