package br.com.srcsoftware.sistema.pessoa.prestadorservico;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public class PrestadorServicoFacade implements PrestadorServicoServiceInterface{

	private final PrestadorServicoServiceImpl service;

	private PrestadorServicoFacade(){
		this.service = new PrestadorServicoServiceImpl();
	}

	public static PrestadorServicoFacade getInstance() {
		return new PrestadorServicoFacade();
	}

	@Override
	public List< PrestadorServicoDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, PrestadorServicoDTO dto ) throws ApplicationException {
		return this.service.filtrar( paginacao, camposOrders, dto );
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, PrestadorServicoDTO dto ) throws ApplicationException {
		this.service.inserir( usuarioSessaoPOJO, dto );
	}

	@Override
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, PrestadorServicoDTO dto ) throws ApplicationException {
		this.service.alterar( usuarioSessaoPOJO, dto );
	}

	@Override
	public void excluir( PrestadorServicoDTO dto ) throws ApplicationException {
		this.service.excluir( dto );
	}

	@Override
	public PrestadorServicoDTO filtrarPorId( String id ) throws ApplicationException {
		return this.service.filtrarPorId( id );
	}
}
