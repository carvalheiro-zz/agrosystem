package br.com.srcsoftware.sistema.pedido.notafiscal.simplesremessa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;

public interface NotaFiscalSimplesRemessaDAOInterface{

	NotaFiscalSimplesRemessaPO inserir( HibernateConnection hibernate, NotaFiscalSimplesRemessaPO po ) throws ApplicationException;

	void alterar( HibernateConnection hibernate, NotaFiscalSimplesRemessaPO po ) throws ApplicationException;

	void excluir( HibernateConnection hibernate, NotaFiscalSimplesRemessaPO po ) throws ApplicationException;

	NotaFiscalSimplesRemessaPO filtrarPorId( Long id ) throws ApplicationException;

	List< NotaFiscalSimplesRemessaPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, HashMap< String, ArrayList< Object > > camposBetween, NotaFiscalSimplesRemessaPO poFilter ) throws ApplicationException;
}
