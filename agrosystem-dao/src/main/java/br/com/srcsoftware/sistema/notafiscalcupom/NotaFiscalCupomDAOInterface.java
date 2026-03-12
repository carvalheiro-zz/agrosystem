package br.com.srcsoftware.sistema.notafiscalcupom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;

public interface NotaFiscalCupomDAOInterface{

	NotaFiscalCupomPO inserir( HibernateConnection hibernate, NotaFiscalCupomPO po ) throws ApplicationException;

	void alterar( HibernateConnection hibernate, NotaFiscalCupomPO po ) throws ApplicationException;

	void excluir( HibernateConnection hibernate, NotaFiscalCupomPO po ) throws ApplicationException;

	NotaFiscalCupomPO filtrarPorId( Long id ) throws ApplicationException;

	List< NotaFiscalCupomPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, HashMap< String, ArrayList< Object > > camposBetween, NotaFiscalCupomPO poFilter ) throws ApplicationException;
}
