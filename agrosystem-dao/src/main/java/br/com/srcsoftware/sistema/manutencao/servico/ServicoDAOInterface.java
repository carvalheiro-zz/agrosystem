package br.com.srcsoftware.sistema.manutencao.servico;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;

public interface ServicoDAOInterface{

	ServicoPO inserir( HibernateConnection hibernate, ServicoPO po ) throws ApplicationException;

	void alterar( HibernateConnection hibernate, ServicoPO po ) throws ApplicationException;

	void excluir( HibernateConnection hibernate, ServicoPO po ) throws ApplicationException;

	ServicoPO filtrarPorId( Long id ) throws ApplicationException;

	List< ServicoPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, ServicoPO poFilter ) throws ApplicationException;

}
