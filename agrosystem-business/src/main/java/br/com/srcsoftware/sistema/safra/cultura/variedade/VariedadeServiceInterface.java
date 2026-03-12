package br.com.srcsoftware.sistema.safra.cultura.variedade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public interface VariedadeServiceInterface{

	List< VariedadeDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, VariedadeDTO dto ) throws ApplicationException;

	void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, VariedadeDTO dto ) throws ApplicationException;

	void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, VariedadeDTO dto ) throws ApplicationException;

	void excluir( VariedadeDTO dto ) throws ApplicationException;

	VariedadeDTO filtrarPorId( String id ) throws ApplicationException;

	ArrayList< VariedadeDTO > filtrarPorSafraSetor( String idSafra, String idSetor, String nome ) throws ApplicationException;

}
