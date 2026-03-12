package br.com.srcsoftware.sistema.silo.saida;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public interface SaidaGraoServiceInterface{

	List< SaidaGraoDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, SaidaGraoDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException;

	void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, SaidaGraoDTO dto ) throws ApplicationException;

	void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, SaidaGraoDTO dto ) throws ApplicationException;

	void excluir( SaidaGraoDTO dto ) throws ApplicationException;

	SaidaGraoDTO filtrarPorId( String id ) throws ApplicationException;

	void gerarPDF( HttpServletResponse response, HashMap< String, String > camposOrders, SaidaGraoDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException;

}
