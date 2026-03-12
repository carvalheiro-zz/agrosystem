package br.com.srcsoftware.sistema.manutencao.implemento;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;

public interface ImplementoDAOInterface{

	ImplementoPO inserir( HibernateConnection hibernate, ImplementoPO po ) throws ApplicationException;

	void alterar( HibernateConnection hibernate, ImplementoPO po ) throws ApplicationException;

	void excluir( HibernateConnection hibernate, ImplementoPO po ) throws ApplicationException;

	ImplementoPO filtrarPorId( Long id ) throws ApplicationException;

	List< ImplementoPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, ImplementoPO poFilter ) throws ApplicationException;

}
