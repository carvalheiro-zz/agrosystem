package br.com.srcsoftware.sistema.produto.marca;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;

public interface MarcaDAOInterface{

	MarcaPO inserir( HibernateConnection hibernate, MarcaPO po ) throws ApplicationException;

	void alterar( HibernateConnection hibernate, MarcaPO po ) throws ApplicationException;

	void excluir( HibernateConnection hibernate, MarcaPO po ) throws ApplicationException;

	MarcaPO filtrarPorId( Long id ) throws ApplicationException;

	List< MarcaPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, MarcaPO poFilter ) throws ApplicationException;

}