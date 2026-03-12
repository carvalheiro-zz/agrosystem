package br.com.srcsoftware.sistema.manutencao.imovel;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public interface ImovelServiceInterface{

	List< ImovelDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, ImovelDTO dto ) throws ApplicationException;

	void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, ImovelDTO dto ) throws ApplicationException;

	void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, ImovelDTO dto ) throws ApplicationException;

	void excluir( ImovelDTO dto ) throws ApplicationException;

	ImovelDTO filtrarPorId( String id ) throws ApplicationException;

}
