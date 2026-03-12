package br.com.srcsoftware.sistema.estoque.estoque;

import java.util.List;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.sistema.estoque.estoque.pojo.EstoquePOJO;

public interface EstoqueDAOInterface{

	boolean CONSIDERAR_FUTURA_SIM = true;
	boolean CONSIDERAR_FUTURA_NAO = false;

	List< EstoquePOJO > filtrar( Paginacao paginacao, Long idProduto, String nomeProduto, String localizacaoProduto, Long idMarca, Long idTipo, String negativos ) throws ApplicationException;
}
