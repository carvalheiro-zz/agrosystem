package br.com.srcsoftware.sistema.aplicacao.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;

public interface ItemDAOInterface{

	ItemPO inserir( HibernateConnection hibernate, ItemPO po ) throws ApplicationException;

	void alterar( HibernateConnection hibernate, ItemPO po ) throws ApplicationException;

	void excluir( HibernateConnection hibernate, ItemPO po ) throws ApplicationException;

	ItemPO filtrarPorId( Long id ) throws ApplicationException;

	List< ItemPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, HashMap< String, ArrayList< Object > > camposBetween, ItemPO poFilter, String tipoManutencao ) throws ApplicationException;

}
