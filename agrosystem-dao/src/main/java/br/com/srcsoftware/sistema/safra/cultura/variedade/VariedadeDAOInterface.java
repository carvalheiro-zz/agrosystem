package br.com.srcsoftware.sistema.safra.cultura.variedade;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;

public interface VariedadeDAOInterface{

	VariedadePO inserir( HibernateConnection hibernate, VariedadePO po ) throws ApplicationException;

	void alterar( HibernateConnection hibernate, VariedadePO po ) throws ApplicationException;

	void excluir( HibernateConnection hibernate, VariedadePO po ) throws ApplicationException;

	VariedadePO filtrarPorId( Long id ) throws ApplicationException;

	List< VariedadePO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, VariedadePO poFilter ) throws ApplicationException;

	List filtrarPorSafraSetor( Long idSafra, Long idSetor, String nome ) throws ApplicationException;

}
