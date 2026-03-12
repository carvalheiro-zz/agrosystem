package br.com.srcsoftware.sistema.pedido.notafiscal.venda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;

public interface NotaFiscalVendaDAOInterface{
	NotaFiscalVendaPO inserir( HibernateConnection hibernate, NotaFiscalVendaPO po ) throws ApplicationException;

	void alterar( HibernateConnection hibernate, NotaFiscalVendaPO po ) throws ApplicationException;

	void excluir( HibernateConnection hibernate, NotaFiscalVendaPO po ) throws ApplicationException;

	NotaFiscalVendaPO filtrarPorId( Long id ) throws ApplicationException;

	List< NotaFiscalVendaPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, HashMap< String, ArrayList< Object > > camposBetween, NotaFiscalVendaPO poFilter ) throws ApplicationException;

	List< NotaFiscalVendaPO > filtrarAbertosAndamentos( String numeroNF, String tipoNF, HashMap< String, String > camposOrders ) throws ApplicationException;
}
