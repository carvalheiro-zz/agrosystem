package br.com.srcsoftware.sistema.safra;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;
import br.com.srcsoftware.sistema.safra.cultura.variedade.VariedadeDTO;
import br.com.srcsoftware.sistema.safra.setorsafra.SetorSafraDTO;
import br.com.srcsoftware.sistema.silo.entrada.InformacoesProducaoSafraPOJO;

public class SafraFacade implements SafraServiceInterface{

	private final SafraServiceImpl service;

	private SafraFacade(){
		this.service = new SafraServiceImpl();
	}

	public static SafraFacade getInstance() {
		return new SafraFacade();
	}

	@Override
	public List< SafraDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, SafraDTO dto ) throws ApplicationException {
		return this.service.filtrar( paginacao, camposOrders, dto );
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, SafraDTO dto ) throws ApplicationException {
		this.service.inserir( usuarioSessaoPOJO, dto );
	}

	@Override
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, SafraDTO dto ) throws ApplicationException {
		this.service.alterar( usuarioSessaoPOJO, dto );
	}

	@Override
	public void excluir( SafraDTO dto ) throws ApplicationException {
		this.service.excluir( dto );
	}

	@Override
	public SafraDTO filtrarPorId( String id ) throws ApplicationException {
		return this.service.filtrarPorId( id );
	}

	@Override
	public void adicionarSetor( ArrayList< SetorSafraDTO > setoresSafras, String idSetor, String nomeSetor, String area, VariedadeDTO variedade ) {
		service.adicionarSetor( setoresSafras, idSetor, nomeSetor, area, variedade );
	}

	@Override
	public void removerSetor( ArrayList< SetorSafraDTO > setoresSafras, String idSetor ) {
		service.removerSetor( setoresSafras, idSetor );
	}

	@Override
	public LocalDate[ ] getDataInicioFimSafra( String idSafra ) throws ApplicationException {
		return service.getDataInicioFimSafra( idSafra );
	}

	@Override
	public InformacoesProducaoSafraPOJO filtrarSaldoProducaoSafra( String idSafra, String idCultura ) throws ApplicationException {
		return service.filtrarSaldoProducaoSafra( idSafra, idCultura );
	}
}
