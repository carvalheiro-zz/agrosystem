package br.com.srcsoftware.gestao.sistema.pedido.notafiscal.simplesremessa;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.srcsoftware.abstracts.AbstractPO;
import br.com.srcsoftware.gestao.sistema.pedido.notafiscal.venda.ItemNotaFiscalVendaMigracaoPO;
import br.com.srcsoftware.modular.manager.utilidades.Constantes;

@Entity
@Table( name = "itens_simples_remessas", schema = Constantes.SCHEMA_MIGRACAO )
public class ItemNotaFiscalSimplesRemessaMigracaoPO extends AbstractPO{

	@Column( name = "preco_custo", precision = 26, scale = 13, nullable = false )
	private BigDecimal precoCusto;

	@Column( name = "quantidade", precision = 26, scale = 13, nullable = false )
	private BigDecimal quantidade;

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "id_item_nota_fiscal_venda", nullable = false )
	private ItemNotaFiscalVendaMigracaoPO itemNotaFiscalVendaFutura;

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

	public ItemNotaFiscalVendaMigracaoPO getItemNotaFiscalVendaFutura() {
		return this.itemNotaFiscalVendaFutura;
	}

	public void setItemNotaFiscalVendaFutura( ItemNotaFiscalVendaMigracaoPO itemNotaFiscalVendaFutura ) {
		this.itemNotaFiscalVendaFutura = itemNotaFiscalVendaFutura;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 0;
		result = ( prime * result ) + ( ( this.itemNotaFiscalVendaFutura == null ) ? 0 : this.itemNotaFiscalVendaFutura.hashCode() );
		result = ( prime * result ) + ( ( this.precoCusto == null ) ? 0 : this.precoCusto.hashCode() );
		result = ( prime * result ) + ( ( this.quantidade == null ) ? 0 : this.quantidade.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}

		if ( !( obj instanceof ItemNotaFiscalSimplesRemessaMigracaoPO ) ) {
			return false;
		}
		ItemNotaFiscalSimplesRemessaMigracaoPO other = (ItemNotaFiscalSimplesRemessaMigracaoPO) obj;
		if ( this.itemNotaFiscalVendaFutura == null ) {
			if ( other.itemNotaFiscalVendaFutura != null ) {
				return false;
			}
		} else if ( !this.itemNotaFiscalVendaFutura.equals( other.itemNotaFiscalVendaFutura ) ) {
			return false;
		}
		if ( this.precoCusto == null ) {
			if ( other.precoCusto != null ) {
				return false;
			}
		} else if ( !this.precoCusto.equals( other.precoCusto ) ) {
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
		return "ItemNotaEntregaPO [precoCusto=" + this.precoCusto + ", quantidade=" + this.quantidade + ", itemNotaFiscalVendaFutura=" + this.itemNotaFiscalVendaFutura + "]";
	}

}
