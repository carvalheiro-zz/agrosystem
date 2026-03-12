package br.com.srcsoftware.sistema.produto.produto;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public class ProdutoFacade implements ProdutoServiceInterface{

	private final ProdutoServiceImpl service;

	private ProdutoFacade(){
		this.service = new ProdutoServiceImpl();
	}

	public static ProdutoFacade getInstance() {
		return new ProdutoFacade();
	}

	@Override
	public List< ProdutoDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, ProdutoDTO dto ) throws ApplicationException {
		return this.service.filtrar( paginacao, camposOrders, dto );
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, ProdutoDTO dto ) throws ApplicationException {
		this.service.inserir( usuarioSessaoPOJO, dto );
	}

	@Override
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, ProdutoDTO dto ) throws ApplicationException {
		this.service.alterar( usuarioSessaoPOJO, dto );
	}

	@Override
	public void excluir( ProdutoDTO dto ) throws ApplicationException {
		this.service.excluir( dto );
	}

	@Override
	public ProdutoDTO filtrarPorId( String id ) throws ApplicationException {
		return this.service.filtrarPorId( id );
	}
}
