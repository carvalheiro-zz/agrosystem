package br.com.srcsoftware.sistema.silo.naturezaoperacao;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;

public interface NaturezaOperacaoDAOInterface {
	
	NaturezaOperacaoPO inserir( HibernateConnection hibernate, NaturezaOperacaoPO po ) throws ApplicationException;
	
	void alterar( HibernateConnection hibernate, NaturezaOperacaoPO po ) throws ApplicationException;
	
	void excluir( HibernateConnection hibernate, NaturezaOperacaoPO po ) throws ApplicationException;
	
	NaturezaOperacaoPO filtrarPorId( Long id ) throws ApplicationException;
	
	List< NaturezaOperacaoPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, NaturezaOperacaoPO poFilter ) throws ApplicationException;
	
}