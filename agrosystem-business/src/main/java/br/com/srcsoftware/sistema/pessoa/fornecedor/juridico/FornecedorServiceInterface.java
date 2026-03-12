package br.com.srcsoftware.sistema.pessoa.fornecedor.juridico;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public interface FornecedorServiceInterface{

	List< FornecedorJuridicoDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, FornecedorJuridicoDTO dto ) throws ApplicationException;

	void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, FornecedorJuridicoDTO dto ) throws ApplicationException;

	void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, FornecedorJuridicoDTO dto ) throws ApplicationException;

	void excluir( FornecedorJuridicoDTO dto ) throws ApplicationException;

	FornecedorJuridicoDTO filtrarPorId( String id ) throws ApplicationException;

}
