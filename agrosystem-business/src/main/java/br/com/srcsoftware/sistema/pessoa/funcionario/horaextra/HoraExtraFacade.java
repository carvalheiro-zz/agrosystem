package br.com.srcsoftware.sistema.pessoa.funcionario.horaextra;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public class HoraExtraFacade implements HoraExtraServiceInterface{

	private final HoraExtraServiceImpl service;

	private HoraExtraFacade(){
		this.service = new HoraExtraServiceImpl();
	}

	public static HoraExtraFacade getInstance() {
		return new HoraExtraFacade();
	}

	@Override
	public List< HoraExtraDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, HoraExtraDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException {
		return this.service.filtrar( paginacao, camposOrders, dto, dataInicialParam, dataFinalParam );
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, HoraExtraDTO dto ) throws ApplicationException {
		this.service.inserir( usuarioSessaoPOJO, dto );
	}

	@Override
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, HoraExtraDTO dto ) throws ApplicationException {
		this.service.alterar( usuarioSessaoPOJO, dto );
	}

	@Override
	public void excluir( HoraExtraDTO dto ) throws ApplicationException {
		this.service.excluir( dto );
	}

	@Override
	public HoraExtraDTO filtrarPorId( String id ) throws ApplicationException {
		return this.service.filtrarPorId( id );
	}

	@Override
	public List< SaldoHoraExtraPOJO > filtrarSaldo( String idColaborador, String dataInicial, String dataFinal ) throws ApplicationException {
		return service.filtrarSaldo( idColaborador, dataInicial, dataFinal );
	}
}
