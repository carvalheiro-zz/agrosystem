package br.com.srcsoftware.sistema.safra.cultura.variedade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public class VariedadeFacade implements VariedadeServiceInterface{

	private final VariedadeServiceImpl service;

	private VariedadeFacade(){
		this.service = new VariedadeServiceImpl();
	}

	public static VariedadeFacade getInstance() {
		return new VariedadeFacade();
	}

	@Override
	public List< VariedadeDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, VariedadeDTO dto ) throws ApplicationException {
		return this.service.filtrar( paginacao, camposOrders, dto );
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, VariedadeDTO dto ) throws ApplicationException {
		this.service.inserir( usuarioSessaoPOJO, dto );
	}

	@Override
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, VariedadeDTO dto ) throws ApplicationException {
		this.service.alterar( usuarioSessaoPOJO, dto );
	}

	@Override
	public void excluir( VariedadeDTO dto ) throws ApplicationException {
		this.service.excluir( dto );
	}

	@Override
	public VariedadeDTO filtrarPorId( String id ) throws ApplicationException {
		return this.service.filtrarPorId( id );
	}

	@Override
	public ArrayList< VariedadeDTO > filtrarPorSafraSetor( String idSafra, String idSetor, String nome ) throws ApplicationException {
		return service.filtrarPorSafraSetor( idSafra, idSetor, nome );
	}
}
