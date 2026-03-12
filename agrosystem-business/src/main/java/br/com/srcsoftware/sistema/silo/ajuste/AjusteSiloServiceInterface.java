package br.com.srcsoftware.sistema.silo.ajuste;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public interface AjusteSiloServiceInterface{

	List< AjusteSiloDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, AjusteSiloDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException;

	void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, AjusteSiloDTO dto ) throws ApplicationException;

	void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, AjusteSiloDTO dto ) throws ApplicationException;

	void excluir( AjusteSiloDTO dto ) throws ApplicationException;

	AjusteSiloDTO filtrarPorId( String id ) throws ApplicationException;
}
