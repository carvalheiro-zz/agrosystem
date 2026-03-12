package br.com.srcsoftware.sistema.pessoa.fornecedor.juridico;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public class FornecedorFacade implements FornecedorServiceInterface{

	private final FornecedorServiceImpl service;

	private FornecedorFacade(){
		this.service = new FornecedorServiceImpl();
	}

	public static FornecedorFacade getInstance() {
		return new FornecedorFacade();
	}

	@Override
	public List< FornecedorJuridicoDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, FornecedorJuridicoDTO dto ) throws ApplicationException {
		return this.service.filtrar( paginacao, camposOrders, dto );
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, FornecedorJuridicoDTO dto ) throws ApplicationException {
		this.service.inserir( usuarioSessaoPOJO, dto );
	}

	@Override
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, FornecedorJuridicoDTO dto ) throws ApplicationException {
		this.service.alterar( usuarioSessaoPOJO, dto );
	}

	@Override
	public void excluir( FornecedorJuridicoDTO dto ) throws ApplicationException {
		this.service.excluir( dto );
	}

	@Override
	public FornecedorJuridicoDTO filtrarPorId( String id ) throws ApplicationException {
		return this.service.filtrarPorId( id );
	}
}
