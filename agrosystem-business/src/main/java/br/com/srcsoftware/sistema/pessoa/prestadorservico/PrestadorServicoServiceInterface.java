package br.com.srcsoftware.sistema.pessoa.prestadorservico;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public interface PrestadorServicoServiceInterface{

	List< PrestadorServicoDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, PrestadorServicoDTO dto ) throws ApplicationException;

	void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, PrestadorServicoDTO dto ) throws ApplicationException;

	void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, PrestadorServicoDTO dto ) throws ApplicationException;

	void excluir( PrestadorServicoDTO dto ) throws ApplicationException;

	PrestadorServicoDTO filtrarPorId( String id ) throws ApplicationException;

}
