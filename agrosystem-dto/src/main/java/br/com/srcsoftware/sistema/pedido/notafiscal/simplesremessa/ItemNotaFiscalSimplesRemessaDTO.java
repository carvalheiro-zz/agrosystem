package br.com.srcsoftware.sistema.pedido.notafiscal.simplesremessa;

import java.math.BigDecimal;

import br.com.srcsoftware.abstracts.AbstractDTO;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.pedido.notafiscal.venda.ItemNotaFiscalVendaDTO;

public class ItemNotaFiscalSimplesRemessaDTO extends AbstractDTO implements Comparable< ItemNotaFiscalSimplesRemessaDTO >{

	private static final long serialVersionUID = -8979357609222170395L;

	private String id;
	private String precoCusto;
	private String quantidade;
	private ItemNotaFiscalVendaDTO itemNotaFiscalVendaFutura;

	public String getTotalCusto() {

		BigDecimal quantidade = Utilidades.parseBigDecimal( this.getQuantidade() );
		BigDecimal precoCusto = Utilidades.parseBigDecimal( this.getPrecoCusto() );

		return Utilidades.parseBigDecimal( quantidade.multiply( precoCusto ) );
	}

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

	public ItemNotaFiscalVendaDTO getItemNotaFiscalVendaFutura() {
		if ( this.itemNotaFiscalVendaFutura == null ) {
			this.itemNotaFiscalVendaFutura = new ItemNotaFiscalVendaDTO();
		}
		return this.itemNotaFiscalVendaFutura;
	}

	public void setItemNotaFiscalVendaFutura( ItemNotaFiscalVendaDTO itemNotaFiscalVendaFutura ) {
		this.itemNotaFiscalVendaFutura = itemNotaFiscalVendaFutura;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
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
		ItemNotaFiscalSimplesRemessaDTO other = (ItemNotaFiscalSimplesRemessaDTO) obj;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
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
		return "ItemNotaEntregaDTO [precoCusto=" + this.precoCusto + ", quantidade=" + this.quantidade + ", itemNotaFiscalVendaFutura=" + this.itemNotaFiscalVendaFutura + ", getId()=" + this.getId() + "]";
	}

	@Override
	public int compareTo( ItemNotaFiscalSimplesRemessaDTO o ) {
		return this.getItemNotaFiscalVendaFutura().getItemPedido().getProduto().getNome().compareToIgnoreCase( o.getItemNotaFiscalVendaFutura().getItemPedido().getProduto().getNome() );
	}
}
