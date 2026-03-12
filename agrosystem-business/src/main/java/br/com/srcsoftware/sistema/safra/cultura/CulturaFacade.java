package br.com.srcsoftware.sistema.safra.cultura;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public class CulturaFacade implements CulturaServiceInterface{

	private final CulturaServiceImpl service;

	private CulturaFacade(){
		this.service = new CulturaServiceImpl();
	}

	public static CulturaFacade getInstance() {
		return new CulturaFacade();
	}

	@Override
	public List< CulturaDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, CulturaDTO dto ) throws ApplicationException {
		return this.service.filtrar( paginacao, camposOrders, dto );
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, CulturaDTO dto ) throws ApplicationException {
		this.service.inserir( usuarioSessaoPOJO, dto );
	}

	@Override
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, CulturaDTO dto ) throws ApplicationException {
		this.service.alterar( usuarioSessaoPOJO, dto );
	}

	@Override
	public void excluir( CulturaDTO dto ) throws ApplicationException {
		this.service.excluir( dto );
	}

	@Override
	public CulturaDTO filtrarPorId( String id ) throws ApplicationException {
		return this.service.filtrarPorId( id );
	}

	@Override
	public ArrayList< CulturaDTO > filtrarPorSafraSetor( String idSafra, String idSetor, String nome, String variedade ) throws ApplicationException {
		return service.filtrarPorSafraSetor( idSafra, idSetor, nome, variedade );
	}

}
