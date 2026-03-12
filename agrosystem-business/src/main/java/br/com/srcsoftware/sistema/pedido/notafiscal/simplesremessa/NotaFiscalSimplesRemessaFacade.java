package br.com.srcsoftware.sistema.pedido.notafiscal.simplesremessa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;
import br.com.srcsoftware.sistema.pedido.notafiscal.venda.ItemNotaFiscalVendaDTO;

public final class NotaFiscalSimplesRemessaFacade implements NotaFiscalSimplesRemessaServiceInterface{

	private final NotaFiscalSimplesRemessaServiceImpl service;

	private NotaFiscalSimplesRemessaFacade(){
		this.service = new NotaFiscalSimplesRemessaServiceImpl();
	}

	public static NotaFiscalSimplesRemessaFacade getInstance() {
		return new NotaFiscalSimplesRemessaFacade();
	}

	@Override
	public List< NotaFiscalSimplesRemessaDTO > filtrar( Paginacao paginacao,
	        HashMap< String, String > camposOrders,
	        NotaFiscalSimplesRemessaDTO dto,
	        String dataInicialParam,
	        String dataFinalParam ) throws br.com.srcsoftware.modular.manager.exceptions.ApplicationException {
		return service.filtrar( paginacao, camposOrders, dto, dataInicialParam, dataFinalParam );
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, NotaFiscalSimplesRemessaDTO dto ) throws br.com.srcsoftware.modular.manager.exceptions.ApplicationException {
		service.inserir( usuarioSessaoPOJO, dto );
	}

	@Override
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, NotaFiscalSimplesRemessaDTO dto ) throws br.com.srcsoftware.modular.manager.exceptions.ApplicationException {
		service.alterar( usuarioSessaoPOJO, dto );
	}

	@Override
	public void excluir( NotaFiscalSimplesRemessaDTO dto ) throws br.com.srcsoftware.modular.manager.exceptions.ApplicationException {
		service.excluir( dto );
	}

	@Override
	public NotaFiscalSimplesRemessaDTO filtrarPorId( String id ) throws br.com.srcsoftware.modular.manager.exceptions.ApplicationException {
		return service.filtrarPorId( id );
	}

	@Override
	public String getTotalFaltaEntregar( String idProduto, String idNotaFutura ) throws br.com.srcsoftware.modular.manager.exceptions.ApplicationException {
		return service.getTotalFaltaEntregar( idProduto, idNotaFutura );
	}

	@Override
	public void adicionarItemRecebido( NotaFiscalSimplesRemessaDTO notaFiscalSimplesRemessa, ArrayList< ItemNotaFiscalVendaDTO > itensNotaFiscalRestantes, String idItemNotaFiscalReceber, String quantidadeItensAdicionar ) throws ApplicationException {
		service.adicionarItemRecebido( notaFiscalSimplesRemessa, itensNotaFiscalRestantes, idItemNotaFiscalReceber, quantidadeItensAdicionar );
	}

	@Override
	public void removerItemRecebido( NotaFiscalSimplesRemessaDTO notaFiscalSimplesRemessa, ArrayList< ItemNotaFiscalVendaDTO > itensNotaFiscalRestantes, String idItemNotaFiscalRetornar, String quantidadeItensRetornar ) throws ApplicationException {
		service.removerItemRecebido( notaFiscalSimplesRemessa, itensNotaFiscalRestantes, idItemNotaFiscalRetornar, quantidadeItensRetornar );
	}

}
