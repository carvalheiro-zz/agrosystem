package br.com.srcsoftware.sistema.produto.tipo;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public class TipoFacade implements TipoServiceInterface{

	private final TipoServiceImpl service;

	private TipoFacade(){
		this.service = new TipoServiceImpl();
	}

	public static TipoFacade getInstance() {
		return new TipoFacade();
	}

	@Override
	public List< TipoDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, TipoDTO dto ) throws ApplicationException {
		return this.service.filtrar( paginacao, camposOrders, dto );
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, TipoDTO dto ) throws ApplicationException {
		this.service.inserir( usuarioSessaoPOJO, dto );
	}

	@Override
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, TipoDTO dto ) throws ApplicationException {
		this.service.alterar( usuarioSessaoPOJO, dto );
	}

	@Override
	public void excluir( TipoDTO dto ) throws ApplicationException {
		this.service.excluir( dto );
	}

	@Override
	public TipoDTO filtrarPorId( String id ) throws ApplicationException {
		return this.service.filtrarPorId( id );
	}
}
