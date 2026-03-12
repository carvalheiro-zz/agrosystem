package br.com.srcsoftware.sistema.notafiscalcupom;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public final class NotaFiscalCupomFacade implements NotaFiscalCupomServiceInterface{

	private final NotaFiscalCupomServiceImpl service;

	private NotaFiscalCupomFacade(){
		this.service = new NotaFiscalCupomServiceImpl();
	}

	public static NotaFiscalCupomFacade getInstance() {
		return new NotaFiscalCupomFacade();
	}

	@Override
	public List< NotaFiscalCupomDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, NotaFiscalCupomDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException {
		return service.filtrar( paginacao, camposOrders, dto, dataInicialParam, dataFinalParam );
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, NotaFiscalCupomDTO dto ) throws ApplicationException {
		service.inserir( usuarioSessaoPOJO, dto );
	}

	@Override
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, NotaFiscalCupomDTO dto ) throws ApplicationException {
		service.alterar( usuarioSessaoPOJO, dto );
	}

	@Override
	public void excluir( NotaFiscalCupomDTO dto ) throws ApplicationException {
		service.excluir( dto );
	}

	@Override
	public NotaFiscalCupomDTO filtrarPorId( String id ) throws ApplicationException {
		return service.filtrarPorId( id );
	}

}
