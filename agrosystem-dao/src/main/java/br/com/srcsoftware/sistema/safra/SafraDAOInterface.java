package br.com.srcsoftware.sistema.safra;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.sistema.silo.entrada.InformacoesProducaoSafraPOJO;

public interface SafraDAOInterface{

	SafraPO inserir( HibernateConnection hibernate, SafraPO po ) throws ApplicationException;

	void alterar( HibernateConnection hibernate, SafraPO po ) throws ApplicationException;

	void excluir( HibernateConnection hibernate, SafraPO po ) throws ApplicationException;

	SafraPO filtrarPorId( Long id ) throws ApplicationException;

	List< SafraPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, SafraPO poFilter ) throws ApplicationException;

	LocalDate[ ] getDataInicioFimSafra( Long idSafra ) throws ApplicationException;

	InformacoesProducaoSafraPOJO filtrarSaldoProducaoSafra( StringBuffer SQL, HashMap< String, Object > parametros ) throws ApplicationException;

	List< InformacoesProducaoSafraPOJO > filtrarSaldoProducao( Long idSafra, Long idSetor, Long idCultura, Long idVariedade ) throws ApplicationException;
}
