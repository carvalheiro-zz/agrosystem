package br.com.srcsoftware.sistema.produto.produto;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import br.com.srcsoftware.modular.manager.abstracts.AbstractCRUDForm;

public final class ProdutoForm extends AbstractCRUDForm{

	private static final long serialVersionUID = 1342719712529624093L;

	private ProdutoDTO produto;

	private ArrayList< ProdutoDTO > produtos;

	public ProdutoDTO getProduto() {
		if ( produto == null ) {
			produto = new ProdutoDTO();
		}
		return produto;
	}

	public void setProduto( ProdutoDTO produto ) {
		this.produto = produto;
	}

	public ArrayList< ProdutoDTO > getProdutos() {
		if ( produtos == null ) {
			produtos = new ArrayList<>();
		}
		return produtos;
	}

	public void setProdutos( ArrayList< ProdutoDTO > produtos ) {
		this.produtos = produtos;
	}

	@Override
	public void limparCamposCadastro( HttpServletRequest request ) {
		setProduto( null );
	}

	@Override
	public void limparCamposConsulta( HttpServletRequest request ) {

	}

	@Override
	public void limparLista() {
		this.getProdutos().clear();
	}

}
