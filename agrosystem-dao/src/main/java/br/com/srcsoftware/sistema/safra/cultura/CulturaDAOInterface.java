package br.com.srcsoftware.sistema.safra.cultura;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.sistema.safra.cultura.CulturaPO;

public interface CulturaDAOInterface{
	CulturaPO inserir( HibernateConnection hibernate, CulturaPO po ) throws ApplicationException;

	void alterar( HibernateConnection hibernate, CulturaPO po ) throws ApplicationException;

	void excluir( HibernateConnection hibernate, CulturaPO po ) throws ApplicationException;

	CulturaPO filtrarPorId( Long id ) throws ApplicationException;

	List< CulturaPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, CulturaPO poFilter ) throws ApplicationException;

	List< CulturaPO > filtrarPorSafraSetor( Long idSafra, Long idSetor, String nome, String variedade ) throws ApplicationException;
}