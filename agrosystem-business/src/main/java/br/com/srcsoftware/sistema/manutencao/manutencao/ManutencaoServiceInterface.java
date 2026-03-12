package br.com.srcsoftware.sistema.manutencao.manutencao;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public interface ManutencaoServiceInterface{

	List< ManutencaoDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, ManutencaoDTO dto, String dataInicialParam, String dataFinalParam, String tipoManutencaoPesquisa ) throws ApplicationException;

	void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, ManutencaoDTO dto ) throws ApplicationException;

	void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, ManutencaoDTO dto ) throws ApplicationException;

	void excluir( ManutencaoDTO dto ) throws ApplicationException;

	ManutencaoDTO filtrarPorId( String id ) throws ApplicationException;

}
