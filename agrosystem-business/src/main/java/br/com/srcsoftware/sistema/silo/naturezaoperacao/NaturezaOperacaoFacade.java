package br.com.srcsoftware.sistema.silo.naturezaoperacao;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public class NaturezaOperacaoFacade implements NaturezaOperacaoServiceInterface {
	
	private final NaturezaOperacaoServiceImpl service;
	
	private NaturezaOperacaoFacade() {
		this.service = new NaturezaOperacaoServiceImpl();
	}
	
	public static NaturezaOperacaoFacade getInstance() {
		return new NaturezaOperacaoFacade();
	}
	
	@Override
	public List< NaturezaOperacaoDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, NaturezaOperacaoDTO dto ) throws ApplicationException {
		return this.service.filtrar( paginacao, camposOrders, dto );
	}
	
	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, NaturezaOperacaoDTO dto ) throws ApplicationException {
		this.service.inserir( usuarioSessaoPOJO, dto );
	}
	
	@Override
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, NaturezaOperacaoDTO dto ) throws ApplicationException {
		this.service.alterar( usuarioSessaoPOJO, dto );
	}
	
	@Override
	public void excluir( NaturezaOperacaoDTO dto ) throws ApplicationException {
		this.service.excluir( dto );
	}
	
	@Override
	public NaturezaOperacaoDTO filtrarPorId( String id ) throws ApplicationException {
		return this.service.filtrarPorId( id );
	}
}
