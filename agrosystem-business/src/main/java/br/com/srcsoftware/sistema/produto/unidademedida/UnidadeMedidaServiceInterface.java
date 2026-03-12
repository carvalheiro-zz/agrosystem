package br.com.srcsoftware.sistema.produto.unidademedida;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public interface UnidadeMedidaServiceInterface{

	List< UnidadeMedidaDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, UnidadeMedidaDTO dto ) throws ApplicationException;

	void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, UnidadeMedidaDTO dto ) throws ApplicationException;

	void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, UnidadeMedidaDTO dto ) throws ApplicationException;

	void excluir( UnidadeMedidaDTO dto ) throws ApplicationException;

	UnidadeMedidaDTO filtrarPorId( String id ) throws ApplicationException;

}
