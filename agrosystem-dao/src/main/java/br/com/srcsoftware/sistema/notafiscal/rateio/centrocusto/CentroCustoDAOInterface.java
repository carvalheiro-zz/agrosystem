package br.com.srcsoftware.sistema.notafiscal.rateio.centrocusto;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;

public interface CentroCustoDAOInterface{
	CentroCustoPO inserir( HibernateConnection hibernate, CentroCustoPO po ) throws ApplicationException;

	void alterar( HibernateConnection hibernate, CentroCustoPO po ) throws ApplicationException;

	void excluir( HibernateConnection hibernate, CentroCustoPO po ) throws ApplicationException;

	CentroCustoPO filtrarPorId( Long id ) throws ApplicationException;

	List< CentroCustoPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, CentroCustoPO poFilter ) throws ApplicationException;

	List< CentroCustoPO > filtrarPorTipoNotaFiscalRateioAgrupado( String codigo, String tipoNotaFiscalRateio ) throws ApplicationException;
}
