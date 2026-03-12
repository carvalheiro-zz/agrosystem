package br.com.srcsoftware.sistema.estoque.estoque;

import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.sistema.estoque.estoque.pojo.EstoquePOJO;

public class EstoqueFacade implements EstoqueServiceInterface{

	private final EstoqueServiceImpl service;

	private EstoqueFacade(){
		this.service = new EstoqueServiceImpl();
	}

	public static EstoqueFacade getInstance() {
		return new EstoqueFacade();
	}

	@Override
	public List< EstoquePOJO > filtrar( Paginacao paginacao, String idProduto, String nomeProduto, String localizacaoProduto, String idMarca, String idTipo, String negativos ) throws ApplicationException {
		return service.filtrar( paginacao, idProduto, nomeProduto, localizacaoProduto, idMarca, idTipo, negativos );
	}

	@Override
	public EstoquePOJO filtrarSaldoPorProduto( String idProduto ) throws ApplicationException {
		return service.filtrarSaldoPorProduto( idProduto );
	}

}
