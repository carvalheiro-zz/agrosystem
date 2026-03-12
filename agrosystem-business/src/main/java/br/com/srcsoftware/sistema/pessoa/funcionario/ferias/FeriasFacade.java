package br.com.srcsoftware.sistema.pessoa.funcionario.ferias;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public class FeriasFacade implements FeriasServiceInterface{

	private final FeriasServiceImpl service;

	private FeriasFacade(){
		this.service = new FeriasServiceImpl();
	}

	public static FeriasFacade getInstance() {
		return new FeriasFacade();
	}

	@Override
	public List< FeriasDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, FeriasDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException {
		return this.service.filtrar( paginacao, camposOrders, dto, dataInicialParam, dataFinalParam );
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, FeriasDTO dto ) throws ApplicationException {
		this.service.inserir( usuarioSessaoPOJO, dto );
	}

	@Override
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, FeriasDTO dto ) throws ApplicationException {
		this.service.alterar( usuarioSessaoPOJO, dto );
	}

	@Override
	public void excluir( FeriasDTO dto ) throws ApplicationException {
		this.service.excluir( dto );
	}

	@Override
	public FeriasDTO filtrarPorId( String id ) throws ApplicationException {
		return this.service.filtrarPorId( id );
	}

	@Override
	public List< SaldoFeriasPOJO > filtrarSaldo( String idColaborador, String dataInicial, String dataFinal ) throws ApplicationException {
		return service.filtrarSaldo( idColaborador, dataInicial, dataFinal );
	}
}
