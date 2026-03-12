package br.com.srcsoftware.sistema.manutencao.servico;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public interface ServicoServiceInterface{

	List< ServicoDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, ServicoDTO dto ) throws ApplicationException;

	void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, ServicoDTO dto ) throws ApplicationException;

	void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, ServicoDTO dto ) throws ApplicationException;

	void excluir( ServicoDTO dto ) throws ApplicationException;

	ServicoDTO filtrarPorId( String id ) throws ApplicationException;

}
