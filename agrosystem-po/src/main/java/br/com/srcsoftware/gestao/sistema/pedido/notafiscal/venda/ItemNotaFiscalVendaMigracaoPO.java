package br.com.srcsoftware.gestao.sistema.pedido.notafiscal.venda;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.srcsoftware.abstracts.AbstractPO;
import br.com.srcsoftware.gestao.sistema.pedido.itempedido.ItemPedidoMigracaoPO;
import br.com.srcsoftware.modular.manager.utilidades.Constantes;

@Entity
@Table( name = "itens_notas_fiscais_vendas", schema = Constantes.SCHEMA_MIGRACAO )
public class ItemNotaFiscalVendaMigracaoPO extends AbstractPO{

	@Column( name = "preco_custo", precision = 26, scale = 13, nullable = false )
	private BigDecimal precoCusto;

	@Column( name = "quantidade", precision = 26, scale = 13, nullable = false )
	private BigDecimal quantidade;

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "id_item_pedido", nullable = false )
	private ItemPedidoMigracaoPO itemPedido;

	@Column( name = "observacao", nullable = true )
	private String observacao;

	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao( String observacao ) {
		this.observacao = observacao;
	}

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

	public ItemPedidoMigracaoPO getItemPedido() {
		return this.itemPedido;
	}

	public void setItemPedido( ItemPedidoMigracaoPO itemPedido ) {
		this.itemPedido = itemPedido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 0;
		result = ( prime * result ) + ( ( this.itemPedido == null ) ? 0 : this.itemPedido.hashCode() );
		result = ( prime * result ) + ( ( this.precoCusto == null ) ? 0 : this.precoCusto.hashCode() );
		result = ( prime * result ) + ( ( this.quantidade == null ) ? 0 : this.quantidade.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}

		if ( !( obj instanceof ItemNotaFiscalVendaMigracaoPO ) ) {
			return false;
		}
		ItemNotaFiscalVendaMigracaoPO other = (ItemNotaFiscalVendaMigracaoPO) obj;
		if ( this.itemPedido == null ) {
			if ( other.itemPedido != null ) {
				return false;
			}
		} else if ( !this.itemPedido.equals( other.itemPedido ) ) {
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
		return "ItemNotaFiscalVendaPO [precoCusto=" + this.precoCusto + ", quantidade=" + this.quantidade + ", itemPedido=" + this.itemPedido + "]";
	}

}
