package br.com.srcsoftware.sistema.cupom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;

public interface CupomDAOInterface{

	CupomPO inserir( HibernateConnection hibernate, CupomPO po ) throws ApplicationException;

	void alterar( HibernateConnection hibernate, CupomPO po ) throws ApplicationException;

	void excluir( HibernateConnection hibernate, CupomPO po ) throws ApplicationException;

	CupomPO filtrarPorId( Long id ) throws ApplicationException;

	List< CupomPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, HashMap< String, ArrayList< Object > > camposBetween, CupomPO poFilter ) throws ApplicationException;

}
