package br.com.srcsoftware.sistema.combustivel.abastecimento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;

public interface AbastecimentoDAOInterface{

	AbastecimentoPO inserir( HibernateConnection hibernate, AbastecimentoPO po ) throws ApplicationException;

	void alterar( HibernateConnection hibernate, AbastecimentoPO po ) throws ApplicationException;

	void excluir( HibernateConnection hibernate, AbastecimentoPO po ) throws ApplicationException;

	AbastecimentoPO filtrarPorId( Long id ) throws ApplicationException;

	List< AbastecimentoPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, HashMap< String, ArrayList< Object > > camposBetween, AbastecimentoPO poFilter ) throws ApplicationException;

}
