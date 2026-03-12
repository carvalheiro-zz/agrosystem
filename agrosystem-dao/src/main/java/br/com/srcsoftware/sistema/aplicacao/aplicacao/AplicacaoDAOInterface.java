package br.com.srcsoftware.sistema.aplicacao.aplicacao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;

public interface AplicacaoDAOInterface{

	AplicacaoPO inserir( HibernateConnection hibernate, AplicacaoPO po ) throws ApplicationException;

	void alterar( HibernateConnection hibernate, AplicacaoPO po ) throws ApplicationException;

	void excluir( HibernateConnection hibernate, AplicacaoPO po ) throws ApplicationException;

	AplicacaoPO filtrarPorId( Long id ) throws ApplicationException;

	List< AplicacaoPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, HashMap< String, ArrayList< Object > > camposBetween, AplicacaoPO poFilter ) throws ApplicationException;

	List< AplicacaoPO > filtrarParaTotais( HashMap< String, ArrayList< Object > > camposBetween, AplicacaoPO poFilter ) throws ApplicationException;

}
