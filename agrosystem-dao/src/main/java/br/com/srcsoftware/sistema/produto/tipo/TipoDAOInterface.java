package br.com.srcsoftware.sistema.produto.tipo;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;

public interface TipoDAOInterface{

	TipoPO inserir( HibernateConnection hibernate, TipoPO po ) throws ApplicationException;

	void alterar( HibernateConnection hibernate, TipoPO po ) throws ApplicationException;

	void excluir( HibernateConnection hibernate, TipoPO po ) throws ApplicationException;

	TipoPO filtrarPorId( Long id ) throws ApplicationException;

	List< TipoPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, TipoPO poFilter ) throws ApplicationException;

}