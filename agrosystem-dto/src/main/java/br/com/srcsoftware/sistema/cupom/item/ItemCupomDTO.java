package br.com.srcsoftware.sistema.cupom.item;

import br.com.srcsoftware.abstracts.AbstractDTO;
import br.com.srcsoftware.sistema.produto.produto.ProdutoDTO;

public class ItemCupomDTO extends AbstractDTO implements Comparable< ItemCupomDTO >{

	private static final long serialVersionUID = 8664847879102943016L;

	private String id;
	private String precoCusto;
	private String quantidade;
	private ProdutoDTO produto;

	public String getId() {
		return id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public String getPrecoCusto() {
		return this.precoCusto;
	}

	public void setPrecoCusto( String precoCusto ) {
		this.precoCusto = precoCusto;
	}

	public String getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade( String quantidade ) {
		this.quantidade = quantidade;
	}

	public ProdutoDTO getProduto() {
		if ( this.produto == null ) {
			this.produto = new ProdutoDTO();
		}
		return this.produto;
	}

	public void setProduto( ProdutoDTO produto ) {
		this.produto = produto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		result = prime * result + ( ( precoCusto == null ) ? 0 : precoCusto.hashCode() );
		result = prime * result + ( ( produto == null ) ? 0 : produto.hashCode() );
		result = prime * result + ( ( quantidade == null ) ? 0 : quantidade.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		ItemCupomDTO other = (ItemCupomDTO) obj;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
		if ( precoCusto == null ) {
			if ( other.precoCusto != null )
				return false;
		} else if ( !precoCusto.equals( other.precoCusto ) )
			return false;
		if ( produto == null ) {
			if ( other.produto != null )
				return false;
		} else if ( !produto.equals( other.produto ) )
			return false;
		if ( quantidade == null ) {
			if ( other.quantidade != null )
				return false;
		} else if ( !quantidade.equals( other.quantidade ) )
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ItemCupomDTO [precoCusto=" + this.precoCusto + ", quantidade=" + this.quantidade + ", produto=" + this.produto + ", getId()=" + this.getId() + "]";
	}

	@Override
	public int compareTo( ItemCupomDTO o ) {
		return this.getProduto().getNome().compareToIgnoreCase( o.getProduto().getNome() );
	}

}
