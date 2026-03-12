package br.com.srcsoftware.sistema.estoque.estoque;

import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.sistema.estoque.estoque.pojo.EstoquePOJO;

public interface EstoqueServiceInterface{

	List< EstoquePOJO > filtrar( Paginacao paginacao, String idProduto, String nomeProduto, String localizacaoProduto, String idMarca, String idTipo, String negativos ) throws ApplicationException;

	EstoquePOJO filtrarSaldoPorProduto( String idProduto ) throws ApplicationException;

}
