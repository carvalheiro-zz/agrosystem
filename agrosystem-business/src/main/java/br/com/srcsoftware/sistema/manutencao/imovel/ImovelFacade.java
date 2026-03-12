package br.com.srcsoftware.sistema.manutencao.imovel;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public class ImovelFacade implements ImovelServiceInterface{

	private final ImovelServiceImpl service;

	private ImovelFacade(){
		this.service = new ImovelServiceImpl();
	}

	public static ImovelFacade getInstance() {
		return new ImovelFacade();
	}

	@Override
	public List< ImovelDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, ImovelDTO dto ) throws ApplicationException {
		return this.service.filtrar( paginacao, camposOrders, dto );
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, ImovelDTO dto ) throws ApplicationException {
		this.service.inserir( usuarioSessaoPOJO, dto );
	}

	@Override
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, ImovelDTO dto ) throws ApplicationException {
		this.service.alterar( usuarioSessaoPOJO, dto );
	}

	@Override
	public void excluir( ImovelDTO dto ) throws ApplicationException {
		this.service.excluir( dto );
	}

	@Override
	public ImovelDTO filtrarPorId( String id ) throws ApplicationException {
		return this.service.filtrarPorId( id );
	}
}
