package br.com.srcsoftware.sistema.manutencao.veiculo;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public class VeiculoFacade implements VeiculoServiceInterface{

	private final VeiculoServiceImpl service;

	private VeiculoFacade(){
		this.service = new VeiculoServiceImpl();
	}

	public static VeiculoFacade getInstance() {
		return new VeiculoFacade();
	}

	@Override
	public List< VeiculoDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, VeiculoDTO dto ) throws ApplicationException {
		return this.service.filtrar( paginacao, camposOrders, dto );
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, VeiculoDTO dto ) throws ApplicationException {
		this.service.inserir( usuarioSessaoPOJO, dto );
	}

	@Override
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, VeiculoDTO dto ) throws ApplicationException {
		this.service.alterar( usuarioSessaoPOJO, dto );
	}

	@Override
	public void excluir( VeiculoDTO dto ) throws ApplicationException {
		this.service.excluir( dto );
	}

	@Override
	public VeiculoDTO filtrarPorId( String id ) throws ApplicationException {
		return this.service.filtrarPorId( id );
	}
}
