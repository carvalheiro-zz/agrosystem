package br.com.srcsoftware.sistema.manutencao.imovel;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;

public interface ImovelDAOInterface{

	ImovelPO inserir( HibernateConnection hibernate, ImovelPO po ) throws ApplicationException;

	void alterar( HibernateConnection hibernate, ImovelPO po ) throws ApplicationException;

	void excluir( HibernateConnection hibernate, ImovelPO po ) throws ApplicationException;

	ImovelPO filtrarPorId( Long id ) throws ApplicationException;

	List< ImovelPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, ImovelPO poFilter ) throws ApplicationException;

}
