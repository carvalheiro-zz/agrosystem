package br.com.srcsoftware.sistema.produto.unidademedida;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public class UnidadeMedidaFacade implements UnidadeMedidaServiceInterface{

	private final UnidadeMedidaServiceImpl service;

	private UnidadeMedidaFacade(){
		this.service = new UnidadeMedidaServiceImpl();
	}

	public static UnidadeMedidaFacade getInstance() {
		return new UnidadeMedidaFacade();
	}

	@Override
	public List< UnidadeMedidaDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, UnidadeMedidaDTO dto ) throws ApplicationException {
		return this.service.filtrar( paginacao, camposOrders, dto );
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, UnidadeMedidaDTO dto ) throws ApplicationException {
		this.service.inserir( usuarioSessaoPOJO, dto );
	}

	@Override
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, UnidadeMedidaDTO dto ) throws ApplicationException {
		this.service.alterar( usuarioSessaoPOJO, dto );
	}

	@Override
	public void excluir( UnidadeMedidaDTO dto ) throws ApplicationException {
		this.service.excluir( dto );
	}

	@Override
	public UnidadeMedidaDTO filtrarPorId( String id ) throws ApplicationException {
		return this.service.filtrarPorId( id );
	}
}
