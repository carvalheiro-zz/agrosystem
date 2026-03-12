package br.com.srcsoftware.sistema.silo.ajuste;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public class AjusteSiloFacade implements AjusteSiloServiceInterface{

	private final AjusteSiloServiceImpl service;

	private AjusteSiloFacade(){
		this.service = new AjusteSiloServiceImpl();
	}

	public static AjusteSiloFacade getInstance() {
		return new AjusteSiloFacade();
	}

	@Override
	public List< AjusteSiloDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, AjusteSiloDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException {
		return service.filtrar( paginacao, camposOrders, dto, dataInicialParam, dataFinalParam );
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, AjusteSiloDTO dto ) throws ApplicationException {
		service.inserir( usuarioSessaoPOJO, dto );
	}

	@Override
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, AjusteSiloDTO dto ) throws ApplicationException {
		service.alterar( usuarioSessaoPOJO, dto );
	}

	@Override
	public void excluir( AjusteSiloDTO dto ) throws ApplicationException {
		service.excluir( dto );
	}

	@Override
	public AjusteSiloDTO filtrarPorId( String id ) throws ApplicationException {
		return service.filtrarPorId( id );
	}

}
