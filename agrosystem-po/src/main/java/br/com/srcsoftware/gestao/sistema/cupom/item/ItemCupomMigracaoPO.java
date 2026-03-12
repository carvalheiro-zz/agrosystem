package br.com.srcsoftware.gestao.sistema.cupom.item;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.srcsoftware.abstracts.AbstractPO;
import br.com.srcsoftware.gestao.sistema.produto.produto.ProdutoMigracaoPO;
import br.com.srcsoftware.modular.manager.utilidades.Constantes;

@Entity
@Table( name = "itens_cupons", schema = Constantes.SCHEMA_MIGRACAO )
public class ItemCupomMigracaoPO extends AbstractPO{

	@Column( name = "preco_custo", precision = 26, scale = 13, nullable = false )
	private BigDecimal precoCusto;

	@Column( name = "quantidade", precision = 26, scale = 13, nullable = false )
	private BigDecimal quantidade;

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "id_produto", nullable = false )
	private ProdutoMigracaoPO produto;

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

	public ProdutoMigracaoPO getProduto() {
		return this.produto;
	}

	public void setProduto( ProdutoMigracaoPO produto ) {
		this.produto = produto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 0;
		result = ( prime * result ) + ( ( this.precoCusto == null ) ? 0 : this.precoCusto.hashCode() );
		result = ( prime * result ) + ( ( this.produto == null ) ? 0 : this.produto.hashCode() );
		result = ( prime * result ) + ( ( this.quantidade == null ) ? 0 : this.quantidade.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}

		if ( !( obj instanceof ItemCupomMigracaoPO ) ) {
			return false;
		}
		ItemCupomMigracaoPO other = (ItemCupomMigracaoPO) obj;
		if ( this.precoCusto == null ) {
			if ( other.precoCusto != null ) {
				return false;
			}
		} else if ( !this.precoCusto.equals( other.precoCusto ) ) {
			return false;
		}
		if ( this.produto == null ) {
			if ( other.produto != null ) {
				return false;
			}
		} else if ( !this.produto.equals( other.produto ) ) {
			return false;
		}
		if ( this.quantidade == null ) {
			if ( other.quantidade != null ) {
				return false;
			}
		} else if ( !this.quantidade.equals( other.quantidade ) ) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ItemCupomPO [precoCusto=" + this.precoCusto + ", quantidade=" + this.quantidade + ", produto=" + this.produto + "]";
	}

}
