package br.com.srcsoftware.sistema.aplicacao.item;

import java.util.ArrayList;
import java.util.HashMap;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public class ItemFacade implements ItemServiceInterface{

	private final ItemServiceImpl service;

	private ItemFacade(){
		this.service = new ItemServiceImpl();
	}

	public static ItemFacade getInstance() {
		return new ItemFacade();
	}

	@Override
	public ArrayList< ItemDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, ItemDTO dto, String dataInicialParam, String dataFinalParam, String tipoItemPesquisa ) throws ApplicationException {
		return this.service.filtrar( paginacao, camposOrders, dto, dataInicialParam, dataFinalParam, tipoItemPesquisa );
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, ItemDTO dto ) throws ApplicationException {
		this.service.inserir( usuarioSessaoPOJO, dto );
	}

	@Override
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, ItemDTO dto ) throws ApplicationException {
		this.service.alterar( usuarioSessaoPOJO, dto );
	}

	@Override
	public void excluir( ItemDTO dto ) throws ApplicationException {
		this.service.excluir( dto );
	}

	@Override
	public ItemDTO filtrarPorId( String id ) throws ApplicationException {
		return this.service.filtrarPorId( id );
	}
}
