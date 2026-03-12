package br.com.srcsoftware.sistema.silo.contratovenda;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public class ContratoVendaFacade implements ContratoVendaServiceInterface{

	private final ContratoVendaServiceImpl service;

	private ContratoVendaFacade(){
		this.service = new ContratoVendaServiceImpl();
	}

	public static ContratoVendaFacade getInstance() {
		return new ContratoVendaFacade();
	}

	@Override
	public List< ContratoVendaDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, ContratoVendaDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException {
		return service.filtrar( paginacao, camposOrders, dto, dataInicialParam, dataFinalParam );
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, ContratoVendaDTO dto ) throws ApplicationException {
		service.inserir( usuarioSessaoPOJO, dto );
	}

	@Override
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, ContratoVendaDTO dto ) throws ApplicationException {
		service.alterar( usuarioSessaoPOJO, dto );
	}

	@Override
	public void excluir( ContratoVendaDTO dto ) throws ApplicationException {
		service.excluir( dto );
	}

	@Override
	public ContratoVendaDTO filtrarPorId( String id ) throws ApplicationException {
		return service.filtrarPorId( id );
	}

	@Override
	public ContratoVendaDTO filtrarPorNumero( String numero ) throws ApplicationException {
		return service.filtrarPorNumero( numero );
	}

}
