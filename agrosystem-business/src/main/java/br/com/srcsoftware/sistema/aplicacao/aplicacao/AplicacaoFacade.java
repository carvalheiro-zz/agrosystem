package br.com.srcsoftware.sistema.aplicacao.aplicacao;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public class AplicacaoFacade implements AplicacaoServiceInterface{

	private final AplicacaoServiceImpl service;

	private AplicacaoFacade(){
		this.service = new AplicacaoServiceImpl();
	}

	public static AplicacaoFacade getInstance() {
		return new AplicacaoFacade();
	}

	@Override
	public ArrayList< AplicacaoDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, AplicacaoDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException {
		return this.service.filtrar( paginacao, camposOrders, dto, dataInicialParam, dataFinalParam );
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, AplicacaoDTO dto ) throws ApplicationException {
		this.service.inserir( usuarioSessaoPOJO, dto );
	}

	@Override
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, AplicacaoDTO dto ) throws ApplicationException {
		this.service.alterar( usuarioSessaoPOJO, dto );
	}

	@Override
	public void excluir( AplicacaoDTO dto ) throws ApplicationException {
		this.service.excluir( dto );
	}

	@Override
	public AplicacaoDTO filtrarPorId( String id ) throws ApplicationException {
		return this.service.filtrarPorId( id );
	}

	@Override
	public ArrayList< AplicacaoDTO > filtrarParaTotais( AplicacaoDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException {
		return service.filtrarParaTotais( dto, dataInicialParam, dataFinalParam );
	}

	@Override
	public void gerarPDF( HttpServletResponse response, HashMap< String, String > camposOrders, AplicacaoDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException {
		service.gerarPDF( response, camposOrders, dto, dataInicialParam, dataFinalParam );
	}
}
