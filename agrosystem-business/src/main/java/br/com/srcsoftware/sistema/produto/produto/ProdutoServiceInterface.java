package br.com.srcsoftware.sistema.produto.produto;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public interface ProdutoServiceInterface{

	List< ProdutoDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, ProdutoDTO dto ) throws ApplicationException;

	void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, ProdutoDTO dto ) throws ApplicationException;

	void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, ProdutoDTO dto ) throws ApplicationException;

	void excluir( ProdutoDTO dto ) throws ApplicationException;

	ProdutoDTO filtrarPorId( String id ) throws ApplicationException;

}
