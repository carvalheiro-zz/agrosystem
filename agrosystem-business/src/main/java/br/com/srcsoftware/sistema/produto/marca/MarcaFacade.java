package br.com.srcsoftware.sistema.produto.marca;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public class MarcaFacade implements MarcaServiceInterface{

	private final MarcaServiceImpl service;

	private MarcaFacade(){
		this.service = new MarcaServiceImpl();
	}

	public static MarcaFacade getInstance() {
		return new MarcaFacade();
	}

	@Override
	public List< MarcaDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, MarcaDTO dto ) throws ApplicationException {
		return this.service.filtrar( paginacao, camposOrders, dto );
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, MarcaDTO dto ) throws ApplicationException {
		this.service.inserir( usuarioSessaoPOJO, dto );
	}

	@Override
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, MarcaDTO dto ) throws ApplicationException {
		this.service.alterar( usuarioSessaoPOJO, dto );
	}

	@Override
	public void excluir( MarcaDTO dto ) throws ApplicationException {
		this.service.excluir( dto );
	}

	@Override
	public MarcaDTO filtrarPorId( String id ) throws ApplicationException {
		return this.service.filtrarPorId( id );
	}
}
