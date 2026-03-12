package br.com.srcsoftware.sistema.manutencao.veiculo;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;

public interface VeiculoDAOInterface{

	VeiculoPO inserir( HibernateConnection hibernate, VeiculoPO po ) throws ApplicationException;

	void alterar( HibernateConnection hibernate, VeiculoPO po ) throws ApplicationException;

	void excluir( HibernateConnection hibernate, VeiculoPO po ) throws ApplicationException;

	VeiculoPO filtrarPorId( Long id ) throws ApplicationException;

	List< VeiculoPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, VeiculoPO poFilter ) throws ApplicationException;

}
