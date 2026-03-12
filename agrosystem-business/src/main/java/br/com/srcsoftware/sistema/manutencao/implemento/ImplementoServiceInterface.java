package br.com.srcsoftware.sistema.manutencao.implemento;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public interface ImplementoServiceInterface{

	List< ImplementoDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, ImplementoDTO dto ) throws ApplicationException;

	void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, ImplementoDTO dto ) throws ApplicationException;

	void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, ImplementoDTO dto ) throws ApplicationException;

	void excluir( ImplementoDTO dto ) throws ApplicationException;

	ImplementoDTO filtrarPorId( String id ) throws ApplicationException;

}
