package br.com.srcsoftware.sistema.cupom;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;
import br.com.srcsoftware.sistema.cupom.item.ItemCupomDTO;

public final class CupomFacade implements CupomServiceInterface{

	private final CupomServiceImpl service;

	private CupomFacade(){
		this.service = new CupomServiceImpl();
	}

	public static CupomFacade getInstance() {
		return new CupomFacade();
	}

	@Override
	public List< CupomDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, CupomDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException {
		return service.filtrar( paginacao, camposOrders, dto, dataInicialParam, dataFinalParam );
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, CupomDTO dto ) throws ApplicationException {
		service.inserir( usuarioSessaoPOJO, dto );
	}

	@Override
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, CupomDTO dto ) throws ApplicationException {
		service.alterar( usuarioSessaoPOJO, dto );
	}

	@Override
	public void excluir( CupomDTO dto ) throws ApplicationException {
		service.excluir( dto );
	}

	@Override
	public CupomDTO filtrarPorId( String id ) throws ApplicationException {
		return service.filtrarPorId( id );
	}

	@Override
	public void adicionarItem( ItemCupomDTO item, CupomDTO cupom ) throws ApplicationException {
		service.adicionarItem( item, cupom );
	}

	@Override
	public void removerItem( CupomDTO cupom, String idItem ) throws ApplicationException {
		service.removerItem( cupom, idItem );
	}

}
