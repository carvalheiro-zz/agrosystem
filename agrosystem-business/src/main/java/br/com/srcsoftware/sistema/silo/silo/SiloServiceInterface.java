package br.com.srcsoftware.sistema.silo.silo;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public interface SiloServiceInterface{

	List< SiloDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, SiloDTO dto ) throws ApplicationException;

	void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, SiloDTO dto ) throws ApplicationException;

	void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, SiloDTO dto ) throws ApplicationException;

	void excluir( SiloDTO dto ) throws ApplicationException;

	SiloDTO filtrarPorId( String id ) throws ApplicationException;

	InformacoesSiloPOJO filtrarInformacoesSilo( String idSilo, String idCultura ) throws ApplicationException;

}
