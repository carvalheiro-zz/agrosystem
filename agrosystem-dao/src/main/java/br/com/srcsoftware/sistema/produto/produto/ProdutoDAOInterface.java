package br.com.srcsoftware.sistema.produto.produto;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;

public interface ProdutoDAOInterface{

	ProdutoPO inserir( HibernateConnection hibernate, ProdutoPO po ) throws ApplicationException;

	void alterar( HibernateConnection hibernate, ProdutoPO po ) throws ApplicationException;

	void excluir( HibernateConnection hibernate, ProdutoPO po ) throws ApplicationException;

	ProdutoPO filtrarPorId( Long id ) throws ApplicationException;

	List< ProdutoPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, ProdutoPO poFilter ) throws ApplicationException;

}
