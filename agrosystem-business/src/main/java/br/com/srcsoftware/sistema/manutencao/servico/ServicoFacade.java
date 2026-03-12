package br.com.srcsoftware.sistema.manutencao.servico;

import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;

public class ServicoFacade implements ServicoServiceInterface{

	private final ServicoServiceImpl service;

	private ServicoFacade(){
		this.service = new ServicoServiceImpl();
	}

	public static ServicoFacade getInstance() {
		return new ServicoFacade();
	}

	@Override
	public List< ServicoDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, ServicoDTO dto ) throws ApplicationException {
		return this.service.filtrar( paginacao, camposOrders, dto );
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, ServicoDTO dto ) throws ApplicationException {
		this.service.inserir( usuarioSessaoPOJO, dto );
	}

	@Override
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, ServicoDTO dto ) throws ApplicationException {
		this.service.alterar( usuarioSessaoPOJO, dto );
	}

	@Override
	public void excluir( ServicoDTO dto ) throws ApplicationException {
		this.service.excluir( dto );
	}

	@Override
	public ServicoDTO filtrarPorId( String id ) throws ApplicationException {
		return this.service.filtrarPorId( id );
	}
}
