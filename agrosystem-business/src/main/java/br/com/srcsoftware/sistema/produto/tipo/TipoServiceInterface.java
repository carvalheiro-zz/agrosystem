package br.com.srcsoftware.sistema.produto.tipo;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public interface TipoServiceInterface{

	List< TipoDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, TipoDTO dto ) throws ApplicationException;

	void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, TipoDTO dto ) throws ApplicationException;

	void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, TipoDTO dto ) throws ApplicationException;

	void excluir( TipoDTO dto ) throws ApplicationException;

	TipoDTO filtrarPorId( String id ) throws ApplicationException;

}
