package br.com.srcsoftware.sistema.notafiscal.direta.item;

import br.com.srcsoftware.abstracts.AbstractDTO;
import br.com.srcsoftware.sistema.produto.produto.ProdutoDTO;

public class ItemNotaFiscalVendaDiretaDTO extends AbstractDTO implements Comparable< ItemNotaFiscalVendaDiretaDTO >{

	private static final long serialVersionUID = 762023215158929636L;

	private String id;
	private String precoCusto;
	private String quantidade;
	private ProdutoDTO produto;

	/** Usado para exibir na tabela */
	private String quantidadeRestante;
	private String corInformativaEntrega;

	public String getId() {
		return id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public String getCorInformativaEntrega() {
		return this.corInformativaEntrega;
	}

	public void setCorInformativaEntrega( String corInformativaEntrega ) {
		this.corInformativaEntrega = corInformativaEntrega;
	}

	public String getPrecoCusto() {
		return this.precoCusto;
	}

	public void setPrecoCusto( String precoCusto ) {
		this.precoCusto = precoCusto;
	}

	public String getQuantidadeRestante() {
		return this.quantidadeRestante;
	}

	public void setQuantidadeRestante( String quantidadeRestante ) {
		this.quantidadeRestante = quantidadeRestante;
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
		result = prime * result + ( ( corInformativaEntrega == null ) ? 0 : corInformativaEntrega.hashCode() );
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		result = prime * result + ( ( precoCusto == null ) ? 0 : precoCusto.hashCode() );
		result = prime * result + ( ( produto == null ) ? 0 : produto.hashCode() );
		result = prime * result + ( ( quantidade == null ) ? 0 : quantidade.hashCode() );
		result = prime * result + ( ( quantidadeRestante == null ) ? 0 : quantidadeRestante.hashCode() );
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
		ItemNotaFiscalVendaDiretaDTO other = (ItemNotaFiscalVendaDiretaDTO) obj;
		if ( corInformativaEntrega == null ) {
			if ( other.corInformativaEntrega != null )
				return false;
		} else if ( !corInformativaEntrega.equals( other.corInformativaEntrega ) )
			return false;
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
		if ( quantidadeRestante == null ) {
			if ( other.quantidadeRestante != null )
				return false;
		} else if ( !quantidadeRestante.equals( other.quantidadeRestante ) )
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ItemNotaFiscalVendaDiretaDTO [precoCusto=" + this.precoCusto + ", quantidade=" + this.quantidade + ", produto=" + this.produto + ", quantidadeRestante=" + this.quantidadeRestante + ", corInformativaEntrega=" + this.corInformativaEntrega + ", getId()=" + this.getId() + "]";
	}

	@Override
	public int compareTo( ItemNotaFiscalVendaDiretaDTO o ) {
		return this.getProduto().getNome().compareToIgnoreCase( o.getProduto().getNome() );
	}

}
