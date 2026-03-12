package br.com.srcsoftware.sistema.silo.saida;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public class SaidaGraoFacade implements SaidaGraoServiceInterface{

	private final SaidaGraoServiceImpl service;

	private SaidaGraoFacade(){
		this.service = new SaidaGraoServiceImpl();
	}

	public static SaidaGraoFacade getInstance() {
		return new SaidaGraoFacade();
	}

	@Override
	public List< SaidaGraoDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, SaidaGraoDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException {
		return service.filtrar( paginacao, camposOrders, dto, dataInicialParam, dataFinalParam );
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, SaidaGraoDTO dto ) throws ApplicationException {
		service.inserir( usuarioSessaoPOJO, dto );
	}

	@Override
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, SaidaGraoDTO dto ) throws ApplicationException {
		service.alterar( usuarioSessaoPOJO, dto );
	}

	@Override
	public void excluir( SaidaGraoDTO dto ) throws ApplicationException {
		service.excluir( dto );
	}

	@Override
	public SaidaGraoDTO filtrarPorId( String id ) throws ApplicationException {
		return service.filtrarPorId( id );
	}

	@Override
	public void gerarPDF( HttpServletResponse response, HashMap< String, String > camposOrders, SaidaGraoDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException {
		service.gerarPDF( response, camposOrders, dto, dataInicialParam, dataFinalParam );
	}

}
