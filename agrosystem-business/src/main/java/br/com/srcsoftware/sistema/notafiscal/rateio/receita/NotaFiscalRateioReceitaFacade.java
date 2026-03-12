package br.com.srcsoftware.sistema.notafiscal.rateio.receita;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.financeiro.contareceber.ContaReceberFacade;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;
import br.com.srcsoftware.sistema.notafiscal.rateio.NotaFiscalRateioDTO;
import br.com.srcsoftware.sistema.notafiscal.rateio.itemnotafiscalrateio.ItemNotaFiscalRateioDTO;

public final class NotaFiscalRateioReceitaFacade extends ContaReceberFacade implements NotaFiscalRateioReceitaServiceInterface{

	private final NotaFiscalRateioReceitaServiceImpl service;

	private NotaFiscalRateioReceitaFacade(){
		this.service = new NotaFiscalRateioReceitaServiceImpl();
	}

	public static NotaFiscalRateioReceitaFacade getInstance() {
		return new NotaFiscalRateioReceitaFacade();
	}

	@Override
	public List filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, NotaFiscalRateioDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException {
		return service.filtrar( paginacao, camposOrders, dto, dataInicialParam, dataFinalParam );
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, NotaFiscalRateioDTO dto ) throws ApplicationException {
		service.inserir( usuarioSessaoPOJO, dto );
	}

	@Override
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, NotaFiscalRateioDTO dto ) throws ApplicationException {
		service.alterar( usuarioSessaoPOJO, dto );
	}

	@Override
	public void excluir( NotaFiscalRateioDTO dto ) throws ApplicationException {
		service.excluir( dto );
	}

	@Override
	public NotaFiscalRateioDTO filtrarPorId( String id ) throws ApplicationException {
		return service.filtrarPorId( id );
	}

	@Override
	public void adicionarItem( ItemNotaFiscalRateioDTO item, NotaFiscalRateioDTO nfr ) throws ApplicationException {
		service.adicionarItem( item, nfr );
	}

	@Override
	public void removerItem( NotaFiscalRateioDTO nfr, String idItem ) throws ApplicationException {
		service.removerItem( nfr, idItem );
	}
}
