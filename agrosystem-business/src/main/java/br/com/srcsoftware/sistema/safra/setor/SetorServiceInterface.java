package br.com.srcsoftware.sistema.safra.setor;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;
import br.com.srcsoftware.sistema.safra.setorsafra.SetorSafraDTO;

public interface SetorServiceInterface{

	List< SetorDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, SetorDTO dto ) throws ApplicationException;

	void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, SetorDTO dto ) throws ApplicationException;

	void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, SetorDTO dto ) throws ApplicationException;

	void excluir( SetorDTO dto ) throws ApplicationException;

	SetorDTO filtrarPorId( String id ) throws ApplicationException;

	List< SetorDTO > filtrarPorSafra( String idSafra, String nomeSetor ) throws ApplicationException;

	List< SetorSafraDTO > filtrarPorSafraSetorCultura( String idSafra, String idSetor, String idCultura ) throws ApplicationException;
}
