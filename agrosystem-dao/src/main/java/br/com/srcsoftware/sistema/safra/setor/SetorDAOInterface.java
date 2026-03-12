package br.com.srcsoftware.sistema.safra.setor;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.sistema.safra.setorsafra.SetorSafraPO;

public interface SetorDAOInterface{

	SetorPO inserir( HibernateConnection hibernate, SetorPO po ) throws ApplicationException;

	void alterar( HibernateConnection hibernate, SetorPO po ) throws ApplicationException;

	void excluir( HibernateConnection hibernate, SetorPO po ) throws ApplicationException;

	SetorPO filtrarPorId( Long id ) throws ApplicationException;

	List< SetorPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, SetorPO poFilter ) throws ApplicationException;

	List< SetorPO > filtrarPorSafra( Long idSafra, String nomeSetor ) throws ApplicationException;

	List< SetorSafraPO > filtrarPorSafraSetorCultura( Long idSafra, Long idSetor, Long idCultura ) throws ApplicationException;

}
