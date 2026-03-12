package br.com.srcsoftware.sistema.produto.unidademedida;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;

public interface UnidadeMedidaDAOInterface{

	UnidadeMedidaPO inserir( HibernateConnection hibernate, UnidadeMedidaPO po ) throws ApplicationException;

	void alterar( HibernateConnection hibernate, UnidadeMedidaPO po ) throws ApplicationException;

	void excluir( HibernateConnection hibernate, UnidadeMedidaPO po ) throws ApplicationException;

	UnidadeMedidaPO filtrarPorId( Long id ) throws ApplicationException;

	List< UnidadeMedidaPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, UnidadeMedidaPO poFilter ) throws ApplicationException;

}