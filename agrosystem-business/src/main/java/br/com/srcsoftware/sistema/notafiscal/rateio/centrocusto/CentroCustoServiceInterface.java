package br.com.srcsoftware.sistema.notafiscal.rateio.centrocusto;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public interface CentroCustoServiceInterface{

	List< CentroCustoDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, CentroCustoDTO dto ) throws ApplicationException;

	void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, CentroCustoDTO dto ) throws ApplicationException;

	void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, CentroCustoDTO dto ) throws ApplicationException;

	void excluir( CentroCustoDTO dto ) throws ApplicationException;

	CentroCustoDTO filtrarPorId( String id ) throws ApplicationException;

	List< CentroCustoDTO > filtrarPorTipoNotaFiscalRateioAgrupado( String codigo, String tipoNotaFiscalRateio ) throws ApplicationException;
}
