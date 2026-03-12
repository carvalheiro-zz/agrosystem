package br.com.srcsoftware.sistema.pessoa.prestadorservico;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;

public interface PrestadorServicoDAOInterface{

	PrestadorServicoPO inserir( HibernateConnection hibernate, PrestadorServicoPO po ) throws ApplicationException;

	void alterar( HibernateConnection hibernate, PrestadorServicoPO po ) throws ApplicationException;

	void excluir( HibernateConnection hibernate, PrestadorServicoPO po ) throws ApplicationException;

	PrestadorServicoPO filtrarPorId( Long id ) throws ApplicationException;

	List< PrestadorServicoPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, PrestadorServicoPO poFilter ) throws ApplicationException;

}