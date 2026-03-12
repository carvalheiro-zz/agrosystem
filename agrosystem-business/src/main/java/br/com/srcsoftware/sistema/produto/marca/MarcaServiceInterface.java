package br.com.srcsoftware.sistema.produto.marca;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public interface MarcaServiceInterface{

	List< MarcaDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, MarcaDTO dto ) throws ApplicationException;

	void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, MarcaDTO dto ) throws ApplicationException;

	void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, MarcaDTO dto ) throws ApplicationException;

	void excluir( MarcaDTO dto ) throws ApplicationException;

	MarcaDTO filtrarPorId( String id ) throws ApplicationException;

}
