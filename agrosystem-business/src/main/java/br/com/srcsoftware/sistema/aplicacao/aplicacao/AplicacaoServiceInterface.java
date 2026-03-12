package br.com.srcsoftware.sistema.aplicacao.aplicacao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public interface AplicacaoServiceInterface{

	List< AplicacaoDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, AplicacaoDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException;

	void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, AplicacaoDTO dto ) throws ApplicationException;

	void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, AplicacaoDTO dto ) throws ApplicationException;

	void excluir( AplicacaoDTO dto ) throws ApplicationException;

	AplicacaoDTO filtrarPorId( String id ) throws ApplicationException;

	ArrayList< AplicacaoDTO > filtrarParaTotais( AplicacaoDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException;

	void gerarPDF( HttpServletResponse response, HashMap< String, String > camposOrders, AplicacaoDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException;
}
