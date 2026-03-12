package br.com.srcsoftware.sistema.pedido.notafiscal.venda;

import java.math.BigDecimal;

import br.com.srcsoftware.abstracts.AbstractDTO;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.pedido.itempedido.ItemPedidoDTO;

public class ItemNotaFiscalVendaDTO extends AbstractDTO implements Comparable< ItemNotaFiscalVendaDTO >{

	private static final long serialVersionUID = -9138635200428367508L;

	private String id;
	private String precoCusto;
	private String quantidade;
	private ItemPedidoDTO itemPedido;
	private String observacao;

	/** Usado para exibir na tabela */
	private String quantidadeRestante;

	public String getCorInformativaEntrega() {
		BigDecimal quantidadeRestante = Utilidades.parseBigDecimal( this.getQuantidadeRestante() );

		if ( Utilidades.isNuloOuVazio( quantidadeRestante ) ) {
			return ( "" );
		} else if ( BigDecimal.ZERO.compareTo( quantidadeRestante ) == 0 ) {
			return ( "rgba(0, 18, 230, 0.41)" );
		} else if ( BigDecimal.ZERO.compareTo( quantidadeRestante ) < 0 ) {
			return ( "rgba(230, 0, 0, 0.41)" );
		}

		return "";
		//return this.corInformativaEntrega;
	}

	public String getId() {
		return id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public String getQuantidadeRestante() {
		return this.quantidadeRestante;
	}

	public void setQuantidadeRestante( String quantidadeRestante ) {
		this.quantidadeRestante = quantidadeRestante;
	}

	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao( String observacao ) {
		this.observacao = observacao;
	}

	public String getTotalCusto() {

		BigDecimal quantidade = Utilidades.parseBigDecimal( this.getQuantidade() );
		BigDecimal precoCusto = Utilidades.parseBigDecimal( this.getPrecoCusto() );

		return Utilidades.parseBigDecimal( quantidade.multiply( precoCusto ) );
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

	public ItemPedidoDTO getItemPedido() {
		if ( this.itemPedido == null ) {
			this.itemPedido = new ItemPedidoDTO();
		}
		return this.itemPedido;
	}

	public void setItemPedido( ItemPedidoDTO itemPedido ) {
		this.itemPedido = itemPedido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		result = prime * result + ( ( itemPedido == null ) ? 0 : itemPedido.hashCode() );
		result = prime * result + ( ( observacao == null ) ? 0 : observacao.hashCode() );
		result = prime * result + ( ( precoCusto == null ) ? 0 : precoCusto.hashCode() );
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
		ItemNotaFiscalVendaDTO other = (ItemNotaFiscalVendaDTO) obj;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
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
		if ( quantidadeRestante == null ) {
			if ( other.quantidadeRestante != null )
				return false;
		} else if ( !quantidadeRestante.equals( other.quantidadeRestante ) )
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ItemNotaEntregaDTO [precoCusto=" + this.precoCusto + ", quantidade=" + this.quantidade + ", itemPedido=" + this.itemPedido + ", getId()=" + this.getId() + "]";
	}

	@Override
	public int compareTo( ItemNotaFiscalVendaDTO o ) {
		return this.getItemPedido().getProduto().getNome().compareToIgnoreCase( o.getItemPedido().getProduto().getNome() );
	}
}
