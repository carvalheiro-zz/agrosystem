package br.com.srcsoftware.sistema.manutencao.manutencao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;

public interface ManutencaoDAOInterface{

	ManutencaoPO inserir( HibernateConnection hibernate, ManutencaoPO po ) throws ApplicationException;

	void alterar( HibernateConnection hibernate, ManutencaoPO po ) throws ApplicationException;

	void excluir( HibernateConnection hibernate, ManutencaoPO po ) throws ApplicationException;

	ManutencaoPO filtrarPorId( Long id ) throws ApplicationException;

	List< ManutencaoPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, HashMap< String, ArrayList< Object > > camposBetween, ManutencaoPO poFilter, String tipoManutencao ) throws ApplicationException;

}
