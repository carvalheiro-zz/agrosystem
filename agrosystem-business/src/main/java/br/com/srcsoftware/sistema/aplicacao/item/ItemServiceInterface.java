package br.com.srcsoftware.sistema.aplicacao.item;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public interface ItemServiceInterface{

	List< ItemDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, ItemDTO dto, String dataInicialParam, String dataFinalParam, String tipoManutencaoPesquisa ) throws ApplicationException;

	void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, ItemDTO dto ) throws ApplicationException;

	void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, ItemDTO dto ) throws ApplicationException;

	void excluir( ItemDTO dto ) throws ApplicationException;

	ItemDTO filtrarPorId( String id ) throws ApplicationException;

}
