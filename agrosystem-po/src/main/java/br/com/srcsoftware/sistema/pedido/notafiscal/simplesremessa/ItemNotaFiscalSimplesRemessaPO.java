package br.com.srcsoftware.sistema.pedido.notafiscal.simplesremessa;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.srcsoftware.abstracts.AbstractPO;
import br.com.srcsoftware.sistema.pedido.notafiscal.venda.ItemNotaFiscalVendaPO;

@Entity
@Table( name = "sistema_itenssimplesremessa" )
public class ItemNotaFiscalSimplesRemessaPO extends AbstractPO{

	@Column( precision = 26, scale = 13, nullable = false )
	private BigDecimal precoCusto;

	@Column( precision = 26, scale = 13, nullable = false )
	private BigDecimal quantidade;

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idItemNotaFiscalVenda", nullable = false, foreignKey = @ForeignKey( name = "fk_itemNotaFiscalVendaFutura_itenssimplesremessa" ) )
	private ItemNotaFiscalVendaPO itemNotaFiscalVendaFutura;

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

	public ItemNotaFiscalVendaPO getItemNotaFiscalVendaFutura() {
		return this.itemNotaFiscalVendaFutura;
	}

	public void setItemNotaFiscalVendaFutura( ItemNotaFiscalVendaPO itemNotaFiscalVendaFutura ) {
		this.itemNotaFiscalVendaFutura = itemNotaFiscalVendaFutura;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( itemNotaFiscalVendaFutura == null ) ? 0 : itemNotaFiscalVendaFutura.hashCode() );
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
		ItemNotaFiscalSimplesRemessaPO other = (ItemNotaFiscalSimplesRemessaPO) obj;

		if ( itemNotaFiscalVendaFutura == null ) {
			if ( other.itemNotaFiscalVendaFutura != null )
				return false;
		} else if ( !itemNotaFiscalVendaFutura.equals( other.itemNotaFiscalVendaFutura ) )
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
		builder.append( "ItemNotaFiscalSimplesRemessaPO :\n\tprecoCusto=" );
		builder.append( precoCusto );
		builder.append( "\n\tquantidade=" );
		builder.append( quantidade );
		builder.append( "\n\titemNotaFiscalVendaFutura=" );
		builder.append( itemNotaFiscalVendaFutura );
		return builder.toString();
	}

}
