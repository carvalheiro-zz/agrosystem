package br.com.srcsoftware.sistema.safra.cultura;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public interface CulturaServiceInterface{

	List< CulturaDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, CulturaDTO dto ) throws ApplicationException;

	void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, CulturaDTO dto ) throws ApplicationException;

	void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, CulturaDTO dto ) throws ApplicationException;

	void excluir( CulturaDTO dto ) throws ApplicationException;

	CulturaDTO filtrarPorId( String id ) throws ApplicationException;

	ArrayList< CulturaDTO > filtrarPorSafraSetor( String idSafra, String idSetor, String nome, String variedade ) throws ApplicationException;
}
