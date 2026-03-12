package br.com.srcsoftware.sistema.silo.silo;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public class SiloFacade implements SiloServiceInterface{

	private final SiloServiceImpl service;

	private SiloFacade(){
		this.service = new SiloServiceImpl();
	}

	public static SiloFacade getInstance() {
		return new SiloFacade();
	}

	@Override
	public List< SiloDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, SiloDTO dto ) throws ApplicationException {
		return this.service.filtrar( paginacao, camposOrders, dto );
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, SiloDTO dto ) throws ApplicationException {
		this.service.inserir( usuarioSessaoPOJO, dto );
	}

	@Override
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, SiloDTO dto ) throws ApplicationException {
		this.service.alterar( usuarioSessaoPOJO, dto );
	}

	@Override
	public void excluir( SiloDTO dto ) throws ApplicationException {
		this.service.excluir( dto );
	}

	@Override
	public SiloDTO filtrarPorId( String id ) throws ApplicationException {
		return this.service.filtrarPorId( id );
	}

	@Override
	public InformacoesSiloPOJO filtrarInformacoesSilo( String idSilo, String idCultura ) throws ApplicationException {
		return service.filtrarInformacoesSilo( idSilo, idCultura );
	}
}
