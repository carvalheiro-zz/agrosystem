package br.com.srcsoftware.sistema.silo.naturezaoperacao;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public interface NaturezaOperacaoServiceInterface {
	
	List< NaturezaOperacaoDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, NaturezaOperacaoDTO dto ) throws ApplicationException;
	
	void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, NaturezaOperacaoDTO dto ) throws ApplicationException;
	
	void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, NaturezaOperacaoDTO dto ) throws ApplicationException;
	
	void excluir( NaturezaOperacaoDTO dto ) throws ApplicationException;
	
	NaturezaOperacaoDTO filtrarPorId( String id ) throws ApplicationException;
	
}
