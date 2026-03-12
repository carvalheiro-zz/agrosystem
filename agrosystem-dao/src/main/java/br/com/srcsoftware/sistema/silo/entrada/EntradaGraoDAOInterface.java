package br.com.srcsoftware.sistema.silo.entrada;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;

public interface EntradaGraoDAOInterface{
	EntradaGraoPO inserir( HibernateConnection hibernate, EntradaGraoPO po ) throws ApplicationException;

	void alterar( HibernateConnection hibernate, EntradaGraoPO po ) throws ApplicationException;

	void excluir( HibernateConnection hibernate, EntradaGraoPO po ) throws ApplicationException;

	EntradaGraoPO filtrarPorId( Long id ) throws ApplicationException;

	List< EntradaGraoPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, HashMap< String, ArrayList< Object > > camposBetween, EntradaGraoPO poFilter ) throws ApplicationException;

}
