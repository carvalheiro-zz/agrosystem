package br.com.srcsoftware.sistema.pessoa.fornecedor.juridico;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;

public interface FornecedorDAOInterface{

	FornecedorJuridicoPO inserir( HibernateConnection hibernate, FornecedorJuridicoPO po ) throws ApplicationException;

	void alterar( HibernateConnection hibernate, FornecedorJuridicoPO po ) throws ApplicationException;

	void excluir( HibernateConnection hibernate, FornecedorJuridicoPO po ) throws ApplicationException;

	FornecedorJuridicoPO filtrarPorId( Long id ) throws ApplicationException;

	List< FornecedorJuridicoPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, FornecedorJuridicoPO poFilter ) throws ApplicationException;

}