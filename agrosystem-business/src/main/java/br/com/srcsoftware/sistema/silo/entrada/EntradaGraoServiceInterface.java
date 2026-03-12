package br.com.srcsoftware.sistema.silo.entrada;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public interface EntradaGraoServiceInterface{

	List< EntradaGraoDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, EntradaGraoDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException;

	void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, EntradaGraoDTO dto ) throws ApplicationException;

	void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, EntradaGraoDTO dto ) throws ApplicationException;

	void excluir( EntradaGraoDTO dto ) throws ApplicationException;

	EntradaGraoDTO filtrarPorId( String id ) throws ApplicationException;

	void gerarPDF( HttpServletResponse response, HashMap< String, String > camposOrders, EntradaGraoDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException;

}
