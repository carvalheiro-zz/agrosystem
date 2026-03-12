package br.com.srcsoftware.sistema.manutencao.manutencao;

import java.util.ArrayList;
import java.util.HashMap;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public class ManutencaoFacade implements ManutencaoServiceInterface{

	private final ManutencaoServiceImpl service;

	private ManutencaoFacade(){
		this.service = new ManutencaoServiceImpl();
	}

	public static ManutencaoFacade getInstance() {
		return new ManutencaoFacade();
	}

	@Override
	public ArrayList< ManutencaoDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, ManutencaoDTO dto, String dataInicialParam, String dataFinalParam, String tipoManutencaoPesquisa ) throws ApplicationException {
		return this.service.filtrar( paginacao, camposOrders, dto, dataInicialParam, dataFinalParam, tipoManutencaoPesquisa );
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, ManutencaoDTO dto ) throws ApplicationException {
		this.service.inserir( usuarioSessaoPOJO, dto );
	}

	@Override
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, ManutencaoDTO dto ) throws ApplicationException {
		this.service.alterar( usuarioSessaoPOJO, dto );
	}

	@Override
	public void excluir( ManutencaoDTO dto ) throws ApplicationException {
		this.service.excluir( dto );
	}

	@Override
	public ManutencaoDTO filtrarPorId( String id ) throws ApplicationException {
		return this.service.filtrarPorId( id );
	}
}
