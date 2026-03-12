package br.com.srcsoftware.sistema.silo.silo;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;

public interface SiloDAOInterface{

	SiloPO inserir( HibernateConnection hibernate, SiloPO po ) throws ApplicationException;

	void alterar( HibernateConnection hibernate, SiloPO po ) throws ApplicationException;

	void excluir( HibernateConnection hibernate, SiloPO po ) throws ApplicationException;

	SiloPO filtrarPorId( Long id ) throws ApplicationException;

	List< SiloPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, SiloPO poFilter ) throws ApplicationException;

	InformacoesSiloPOJO filtrarInformacoesSilo( Long idSilo, Long idCultura ) throws ApplicationException;

}