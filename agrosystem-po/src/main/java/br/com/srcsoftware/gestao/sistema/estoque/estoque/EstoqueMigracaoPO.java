package br.com.srcsoftware.gestao.sistema.estoque.estoque;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.srcsoftware.abstracts.AbstractPO;
import br.com.srcsoftware.gestao.sistema.produto.produto.ProdutoMigracaoPO;
import br.com.srcsoftware.modular.manager.utilidades.Constantes;

@Entity
@Table( name = "estoques", schema = Constantes.SCHEMA_MIGRACAO, uniqueConstraints = @UniqueConstraint( columnNames = { "id_produto" } ) )
public final class EstoqueMigracaoPO extends AbstractPO implements Comparable< EstoqueMigracaoPO >{

	@Column( name = "quantidade", precision = 20, scale = 2, nullable = false )
	private BigDecimal quantidade;

	@Column( name = "custo_total", precision = 17, scale = 2, nullable = false )
	private BigDecimal custoTotal;

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "id_produto", nullable = false )
	private ProdutoMigracaoPO produto;

	public BigDecimal getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade( BigDecimal quantidade ) {
		this.quantidade = quantidade;
	}

	public BigDecimal getCustoTotal() {
		return this.custoTotal;
	}

	public void setCustoTotal( BigDecimal custoTotal ) {
		this.custoTotal = custoTotal;
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
		result = ( prime * result ) + ( ( this.produto == null ) ? 0 : this.produto.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}

		if ( !( obj instanceof EstoqueMigracaoPO ) ) {
			return false;
		}
		EstoqueMigracaoPO other = (EstoqueMigracaoPO) obj;
		if ( this.produto == null ) {
			if ( other.produto != null ) {
				return false;
			}
		} else if ( !this.produto.equals( other.produto ) ) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "EstoquePO [quantidade=" + this.quantidade + ", custoTotal=" + this.custoTotal + ", produto=" + this.produto + "]";
	}

	@Override
	public int compareTo( EstoqueMigracaoPO o ) {
		return this.getProduto().getNome().compareToIgnoreCase( o.getProduto().getNome() );
	}
}