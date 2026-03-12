package br.com.srcsoftware.sistema.safra.setor;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;
import br.com.srcsoftware.sistema.safra.setorsafra.SetorSafraDTO;

public class SetorFacade implements SetorServiceInterface{

	private final SetorServiceImpl service;

	private SetorFacade(){
		this.service = new SetorServiceImpl();
	}

	public static SetorFacade getInstance() {
		return new SetorFacade();
	}

	@Override
	public List< SetorDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, SetorDTO dto ) throws ApplicationException {
		return this.service.filtrar( paginacao, camposOrders, dto );
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, SetorDTO dto ) throws ApplicationException {
		this.service.inserir( usuarioSessaoPOJO, dto );
	}

	@Override
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, SetorDTO dto ) throws ApplicationException {
		this.service.alterar( usuarioSessaoPOJO, dto );
	}

	@Override
	public void excluir( SetorDTO dto ) throws ApplicationException {
		this.service.excluir( dto );
	}

	@Override
	public SetorDTO filtrarPorId( String id ) throws ApplicationException {
		return this.service.filtrarPorId( id );
	}

	@Override
	public List< SetorDTO > filtrarPorSafra( String idSafra, String nomeSetor ) throws ApplicationException {
		return service.filtrarPorSafra( idSafra, nomeSetor );
	}

	@Override
	public List< SetorSafraDTO > filtrarPorSafraSetorCultura( String idSafra, String idSetor, String idCultura ) throws ApplicationException {
		return service.filtrarPorSafraSetorCultura( idSafra, idSetor, idCultura );
	}
}
