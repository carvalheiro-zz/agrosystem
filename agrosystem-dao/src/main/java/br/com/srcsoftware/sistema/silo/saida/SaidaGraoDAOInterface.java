package br.com.srcsoftware.sistema.silo.saida;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;

public interface SaidaGraoDAOInterface{
	SaidaGraoPO inserir( HibernateConnection hibernate, SaidaGraoPO po ) throws ApplicationException;

	void alterar( HibernateConnection hibernate, SaidaGraoPO po ) throws ApplicationException;

	void excluir( HibernateConnection hibernate, SaidaGraoPO po ) throws ApplicationException;

	SaidaGraoPO filtrarPorId( Long id ) throws ApplicationException;

	List< SaidaGraoPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, HashMap< String, ArrayList< Object > > camposBetween, SaidaGraoPO poFilter ) throws ApplicationException;

}
