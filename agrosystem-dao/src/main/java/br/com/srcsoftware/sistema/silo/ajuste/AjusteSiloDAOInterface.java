package br.com.srcsoftware.sistema.silo.ajuste;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;

public interface AjusteSiloDAOInterface{
	AjusteSiloPO inserir( HibernateConnection hibernate, AjusteSiloPO po ) throws ApplicationException;

	void alterar( HibernateConnection hibernate, AjusteSiloPO po ) throws ApplicationException;

	void excluir( HibernateConnection hibernate, AjusteSiloPO po ) throws ApplicationException;

	AjusteSiloPO filtrarPorId( Long id ) throws ApplicationException;

	List< AjusteSiloPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, HashMap< String, ArrayList< Object > > camposBetween, AjusteSiloPO poFilter ) throws ApplicationException;

}
