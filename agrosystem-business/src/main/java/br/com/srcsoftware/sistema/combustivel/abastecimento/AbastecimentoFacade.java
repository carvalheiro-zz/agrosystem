package br.com.srcsoftware.sistema.combustivel.abastecimento;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public class AbastecimentoFacade implements AbastecimentoServiceInterface{

	private final AbastecimentoServiceImpl service;

	private AbastecimentoFacade(){
		this.service = new AbastecimentoServiceImpl();
	}

	public static AbastecimentoFacade getInstance() {
		return new AbastecimentoFacade();
	}

	@Override
	public List< AbastecimentoDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, AbastecimentoDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException {
		return service.filtrar( paginacao, camposOrders, dto, dataInicialParam, dataFinalParam );
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, AbastecimentoDTO dto ) throws ApplicationException {
		service.inserir( usuarioSessaoPOJO, dto );
	}

	@Override
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, AbastecimentoDTO dto ) throws ApplicationException {
		service.alterar( usuarioSessaoPOJO, dto );
	}

	@Override
	public void excluir( AbastecimentoDTO dto ) throws ApplicationException {
		service.excluir( dto );
	}

	@Override
	public AbastecimentoDTO filtrarPorId( String id ) throws ApplicationException {
		return service.filtrarPorId( id );
	}

}
