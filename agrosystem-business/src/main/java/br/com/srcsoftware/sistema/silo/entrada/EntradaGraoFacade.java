package br.com.srcsoftware.sistema.silo.entrada;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public class EntradaGraoFacade implements EntradaGraoServiceInterface{

	private final EntradaGraoServiceImpl service;

	private EntradaGraoFacade(){
		this.service = new EntradaGraoServiceImpl();
	}

	public static EntradaGraoFacade getInstance() {
		return new EntradaGraoFacade();
	}

	@Override
	public List< EntradaGraoDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, EntradaGraoDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException {
		return service.filtrar( paginacao, camposOrders, dto, dataInicialParam, dataFinalParam );
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, EntradaGraoDTO dto ) throws ApplicationException {
		service.inserir( usuarioSessaoPOJO, dto );
	}

	@Override
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, EntradaGraoDTO dto ) throws ApplicationException {
		service.alterar( usuarioSessaoPOJO, dto );
	}

	@Override
	public void excluir( EntradaGraoDTO dto ) throws ApplicationException {
		service.excluir( dto );
	}

	@Override
	public EntradaGraoDTO filtrarPorId( String id ) throws ApplicationException {
		return service.filtrarPorId( id );
	}

	@Override
	public void gerarPDF( HttpServletResponse response, HashMap< String, String > camposOrders, EntradaGraoDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException {
		service.gerarPDF( response, camposOrders, dto, dataInicialParam, dataFinalParam );
	}
}
