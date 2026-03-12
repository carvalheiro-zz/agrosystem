package br.com.srcsoftware.sistema.notafiscal.rateio.centrocusto;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public final class CentroCustoFacade implements CentroCustoServiceInterface{

	private final CentroCustoServiceImpl service;

	private CentroCustoFacade(){
		this.service = new CentroCustoServiceImpl();
	}

	public static CentroCustoFacade getInstance() {
		return new CentroCustoFacade();
	}

	@Override
	public List< CentroCustoDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, CentroCustoDTO dto ) throws ApplicationException {
		return this.service.filtrar( paginacao, camposOrders, dto );
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, CentroCustoDTO dto ) throws ApplicationException {
		this.service.inserir( usuarioSessaoPOJO, dto );
	}

	@Override
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, CentroCustoDTO dto ) throws ApplicationException {
		this.service.alterar( usuarioSessaoPOJO, dto );
	}

	@Override
	public void excluir( CentroCustoDTO dto ) throws ApplicationException {
		this.service.excluir( dto );
	}

	@Override
	public CentroCustoDTO filtrarPorId( String id ) throws ApplicationException {
		return this.service.filtrarPorId( id );
	}

	@Override
	public List< CentroCustoDTO > filtrarPorTipoNotaFiscalRateioAgrupado( String codigo, String tipoNotaFiscalRateio ) throws ApplicationException {
		return service.filtrarPorTipoNotaFiscalRateioAgrupado( codigo, tipoNotaFiscalRateio );
	}

}
