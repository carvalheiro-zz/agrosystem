package br.com.srcsoftware.sistema.notafiscal.direta;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;
import br.com.srcsoftware.sistema.notafiscal.direta.item.ItemNotaFiscalVendaDiretaDTO;

public final class NotaFiscalVendaDiretaFacade implements NotaFiscalVendaDiretaServiceInterface{

	private final NotaFiscalVendaDiretaServiceImpl service;

	private NotaFiscalVendaDiretaFacade(){
		this.service = new NotaFiscalVendaDiretaServiceImpl();
	}

	public static NotaFiscalVendaDiretaFacade getInstance() {
		return new NotaFiscalVendaDiretaFacade();
	}

	@Override
	public List< NotaFiscalVendaDiretaDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, NotaFiscalVendaDiretaDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException {
		return service.filtrar( paginacao, camposOrders, dto, dataInicialParam, dataFinalParam );
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, NotaFiscalVendaDiretaDTO dto ) throws ApplicationException {
		service.inserir( usuarioSessaoPOJO, dto );
	}

	@Override
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, NotaFiscalVendaDiretaDTO dto ) throws ApplicationException {
		service.alterar( usuarioSessaoPOJO, dto );
	}

	@Override
	public void excluir( NotaFiscalVendaDiretaDTO dto ) throws ApplicationException {
		service.excluir( dto );
	}

	@Override
	public NotaFiscalVendaDiretaDTO filtrarPorId( String id ) throws ApplicationException {
		return service.filtrarPorId( id );
	}

	@Override
	public void adicionarItem( ItemNotaFiscalVendaDiretaDTO item, NotaFiscalVendaDiretaDTO nf ) throws ApplicationException {
		service.adicionarItem( item, nf );
	}

	@Override
	public void removerItem( NotaFiscalVendaDiretaDTO nf, String idItem ) throws ApplicationException {
		service.removerItem( nf, idItem );
	}

}
