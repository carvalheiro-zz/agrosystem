package br.com.srcsoftware.sistema.manutencao.implemento;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public class ImplementoFacade implements ImplementoServiceInterface{

	private final ImplementoServiceImpl service;

	private ImplementoFacade(){
		this.service = new ImplementoServiceImpl();
	}

	public static ImplementoFacade getInstance() {
		return new ImplementoFacade();
	}

	@Override
	public List< ImplementoDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, ImplementoDTO dto ) throws ApplicationException {
		return this.service.filtrar( paginacao, camposOrders, dto );
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, ImplementoDTO dto ) throws ApplicationException {
		this.service.inserir( usuarioSessaoPOJO, dto );
	}

	@Override
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, ImplementoDTO dto ) throws ApplicationException {
		this.service.alterar( usuarioSessaoPOJO, dto );
	}

	@Override
	public void excluir( ImplementoDTO dto ) throws ApplicationException {
		this.service.excluir( dto );
	}

	@Override
	public ImplementoDTO filtrarPorId( String id ) throws ApplicationException {
		return this.service.filtrarPorId( id );
	}
}
