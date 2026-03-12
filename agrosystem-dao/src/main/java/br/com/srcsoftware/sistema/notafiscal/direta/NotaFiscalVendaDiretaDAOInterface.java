package br.com.srcsoftware.sistema.notafiscal.direta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;

public interface NotaFiscalVendaDiretaDAOInterface{

	NotaFiscalVendaDiretaPO inserir( HibernateConnection hibernate, NotaFiscalVendaDiretaPO po ) throws ApplicationException;

	void alterar( HibernateConnection hibernate, NotaFiscalVendaDiretaPO po ) throws ApplicationException;

	void excluir( HibernateConnection hibernate, NotaFiscalVendaDiretaPO po ) throws ApplicationException;

	NotaFiscalVendaDiretaPO filtrarPorId( Long id ) throws ApplicationException;

	List< NotaFiscalVendaDiretaPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, HashMap< String, ArrayList< Object > > camposBetween, NotaFiscalVendaDiretaPO poFilter ) throws ApplicationException;
}
