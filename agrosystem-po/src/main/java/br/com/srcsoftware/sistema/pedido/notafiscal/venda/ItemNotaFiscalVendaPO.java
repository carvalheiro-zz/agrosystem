package br.com.srcsoftware.sistema.pedido.notafiscal.venda;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.srcsoftware.abstracts.AbstractPO;
import br.com.srcsoftware.sistema.pedido.itempedido.ItemPedidoPO;

@Entity
@Table( name = "sistema_itensnotafiscalvenda" )
public class ItemNotaFiscalVendaPO extends AbstractPO{

	@Column( precision = 26, scale = 13, nullable = false )
	private BigDecimal precoCusto;

	@Column( precision = 26, scale = 13, nullable = false )
	private BigDecimal quantidade;

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idItemPedido", nullable = false, foreignKey = @ForeignKey( name = "fk_itemPedido_itensnotafiscalvenda" ) )
	private ItemPedidoPO itemPedido;

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

	public ItemPedidoPO getItemPedido() {
		return this.itemPedido;
	}

	public void setItemPedido( ItemPedidoPO itemPedido ) {
		this.itemPedido = itemPedido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( itemPedido == null ) ? 0 : itemPedido.hashCode() );
		result = prime * result + ( ( observacao == null ) ? 0 : observacao.hashCode() );
		result = prime * result + ( ( precoCusto == null ) ? 0 : precoCusto.hashCode() );
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
		ItemNotaFiscalVendaPO other = (ItemNotaFiscalVendaPO) obj;

		if ( itemPedido == null ) {
			if ( other.itemPedido != null )
				return false;
		} else if ( !itemPedido.equals( other.itemPedido ) )
			return false;
		if ( observacao == null ) {
			if ( other.observacao != null )
				return false;
		} else if ( !observacao.equals( other.observacao ) )
			return false;
		if ( precoCusto == null ) {
			if ( other.precoCusto != null )
				return false;
		} else if ( !precoCusto.equals( other.precoCusto ) )
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
		StringBuilder builder = new StringBuilder();
		builder.append( "ItemNotaFiscalVendaPO :\n\tprecoCusto=" );
		builder.append( precoCusto );
		builder.append( "\n\tquantidade=" );
		builder.append( quantidade );
		builder.append( "\n\titemPedido=" );
		builder.append( itemPedido );
		builder.append( "\n\tobservacao=" );
		builder.append( observacao );
		return builder.toString();
	}

}
