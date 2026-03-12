package br.com.srcsoftware.sistema.combustivel.abastecimento;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public interface AbastecimentoServiceInterface{

	List< AbastecimentoDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, AbastecimentoDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException;

	void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, AbastecimentoDTO dto ) throws ApplicationException;

	void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, AbastecimentoDTO dto ) throws ApplicationException;

	void excluir( AbastecimentoDTO dto ) throws ApplicationException;

	AbastecimentoDTO filtrarPorId( String id ) throws ApplicationException;
}
