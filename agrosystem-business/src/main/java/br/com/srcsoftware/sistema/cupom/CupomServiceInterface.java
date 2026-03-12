package br.com.srcsoftware.sistema.cupom;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;
import br.com.srcsoftware.sistema.cupom.item.ItemCupomDTO;

public interface CupomServiceInterface{

	List< CupomDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, CupomDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException;

	void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, CupomDTO dto ) throws ApplicationException;

	void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, CupomDTO dto ) throws ApplicationException;

	void excluir( CupomDTO dto ) throws ApplicationException;

	CupomDTO filtrarPorId( String id ) throws ApplicationException;

	void adicionarItem( ItemCupomDTO item, CupomDTO cupom ) throws ApplicationException;

	void removerItem( CupomDTO cupom, String idItem ) throws ApplicationException;

}
