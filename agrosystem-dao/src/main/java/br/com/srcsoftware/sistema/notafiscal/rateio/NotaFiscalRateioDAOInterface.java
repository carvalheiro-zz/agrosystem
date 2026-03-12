package br.com.srcsoftware.sistema.notafiscal.rateio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;

public interface NotaFiscalRateioDAOInterface{
	NotaFiscalRateioPO inserir( HibernateConnection hibernate, NotaFiscalRateioPO po ) throws ApplicationException;

	void alterar( HibernateConnection hibernate, NotaFiscalRateioPO po ) throws ApplicationException;

	void excluir( HibernateConnection hibernate, NotaFiscalRateioPO po ) throws ApplicationException;

	NotaFiscalRateioPO filtrarPorId( Long id ) throws ApplicationException;

	List< NotaFiscalRateioPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, HashMap< String, ArrayList< Object > > camposBetween, NotaFiscalRateioPO poFilter ) throws ApplicationException;
}
