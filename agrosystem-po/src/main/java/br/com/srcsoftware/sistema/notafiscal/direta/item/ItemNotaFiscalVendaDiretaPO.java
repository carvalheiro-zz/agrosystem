package br.com.srcsoftware.sistema.notafiscal.direta.item;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.srcsoftware.abstracts.AbstractPO;
import br.com.srcsoftware.sistema.produto.produto.ProdutoPO;

@Entity
@Table( name = "sistema_itensnotafiscalvendadireta" )
public class ItemNotaFiscalVendaDiretaPO extends AbstractPO{

	@Column( precision = 26, scale = 13, nullable = false )
	private BigDecimal precoCusto;

	@Column( precision = 26, scale = 13, nullable = false )
	private BigDecimal quantidade;

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idProduto", nullable = false, foreignKey = @ForeignKey( name = "fk_produto_itensnotafiscalvendadireta" ) )
	private ProdutoPO produto;

	public BigDecimal getPrecoCusto() {
		return this.precoCusto;
	}

	public void setPrecoCusto( BigDecimal precoCusto ) {
		this.precoCusto = precoCusto;
	}

	public BigDecimal getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade( BigDecimal quantidade ) {
		this.quantidade = quantidade;
	}

	public ProdutoPO getProduto() {
		return this.produto;
	}

	public void setProduto( ProdutoPO produto ) {
		this.produto = produto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		ItemNotaFiscalVendaDiretaPO other = (ItemNotaFiscalVendaDiretaPO) obj;
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
		return "ItemPedidoPO [precoCusto=" + this.precoCusto + ", quantidade=" + this.quantidade + ", produto=" + this.produto + ", getId()=" + this.getId() + "]";
	}

}
