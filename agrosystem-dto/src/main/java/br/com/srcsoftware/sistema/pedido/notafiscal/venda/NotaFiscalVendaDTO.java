package br.com.srcsoftware.sistema.pedido.notafiscal.venda;

import java.math.BigDecimal;
import java.util.ArrayList;

import br.com.srcsoftware.abstracts.AuditoriaAbstractDTO;
import br.com.srcsoftware.modular.manager.utilidades.DateUtil;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.pedido.pedido.PedidoDTO;

public class NotaFiscalVendaDTO extends AuditoriaAbstractDTO implements Comparable< NotaFiscalVendaDTO >{

	private static final long serialVersionUID = 1758882520598529700L;

	public static final String TIPO_VENDA = "Venda";
	public static final String TIPO_FUTURA = "Futura";
	public static final String TIPO_SIMPLES_REMESSA = "Simples Remessa";

	private String id;
	private String tipo;
	private String data;
	private String numero;
	private ArrayList< ItemNotaFiscalVendaDTO > itens;
	private PedidoDTO pedido;
	private String status;
	private String observacao;

	/** Usado para exibir na tabela */
	private String quantidadeRestante;

	public String getId() {
		return id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public String getDataToString() {
		return DateUtil.formatLocalDate( getData() );
	}

	public String getValorCustoTotal() {
		BigDecimal soma = BigDecimal.ZERO;
		for ( ItemNotaFiscalVendaDTO itemCorrente : this.getItens() ) {
			BigDecimal custo = BigDecimal.ZERO;
			BigDecimal quantidade = Utilidades.parseBigDecimal( itemCorrente.getQuantidade() );

			BigDecimal custoItem = Utilidades.parseBigDecimal( itemCorrente.getPrecoCusto() );

			if ( Utilidades.isNuloOuVazio( custoItem ) ) {
				continue;
			}

			custo = custoItem.multiply( quantidade ).setScale( 2, BigDecimal.ROUND_FLOOR );

			soma = soma.add( custo );
		}
		return Utilidades.parseBigDecimal( soma );
	}

	public String getQuantidadeItens() {
		return String.valueOf( this.getItens().size() );
	}

	public String getQuantidadeTotalItens() {
		BigDecimal soma = BigDecimal.ZERO;
		for ( ItemNotaFiscalVendaDTO itemCorrente : this.getItens() ) {
			BigDecimal quantidade = BigDecimal.ZERO;
			quantidade = Utilidades.parseBigDecimal( itemCorrente.getQuantidade() );

			soma = soma.add( quantidade );
		}
		return Utilidades.parseBigDecimal( soma );
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

	public String getStatus() {
		return this.status;
	}

	public void setStatus( String status ) {
		this.status = status;
	}

	public String getData() {
		return this.data;
	}

	public void setData( String data ) {
		this.data = data;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo( String tipo ) {
		this.tipo = tipo;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero( String numero ) {
		this.numero = numero;
	}

	public ArrayList< ItemNotaFiscalVendaDTO > getItens() {
		if ( this.itens == null ) {
			this.itens = new ArrayList<>();
		}
		return this.itens;
	}

	public void setItens( ArrayList< ItemNotaFiscalVendaDTO > itens ) {
		this.itens = itens;
	}

	public PedidoDTO getPedido() {
		if ( this.pedido == null ) {
			this.pedido = new PedidoDTO();
		}
		return this.pedido;
	}

	public void setPedido( PedidoDTO pedido ) {
		this.pedido = pedido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( numero == null ) ? 0 : numero.hashCode() );
		result = prime * result + ( ( pedido == null ) ? 0 : pedido.hashCode() );
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
		NotaFiscalVendaDTO other = (NotaFiscalVendaDTO) obj;
		if ( numero == null ) {
			if ( other.numero != null )
				return false;
		} else if ( !numero.equals( other.numero ) )
			return false;
		if ( pedido == null ) {
			if ( other.pedido != null )
				return false;
		} else if ( !pedido.equals( other.pedido ) )
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "NotaFiscalVendaDTO [\n\ttipo=" );
		builder.append( tipo );
		builder.append( ",\n\tdata=" );
		builder.append( data );
		builder.append( ",\n\tnumero=" );
		builder.append( numero );
		builder.append( ",\n\titens=" );
		builder.append( itens );
		builder.append( ",\n\tpedido=" );
		builder.append( pedido );
		builder.append( ",\n\tstatus=" );
		builder.append( status );
		builder.append( ",\n\tobservacao=" );
		builder.append( observacao );
		builder.append( ",\n\tquantidadeRestante=" );
		builder.append( quantidadeRestante );
		builder.append( ",\n\tgetNomeUsuarioCriacao()=" );
		builder.append( getNomeUsuarioCriacao() );
		builder.append( ",\n\tgetNomeUsuarioAlteracao()=" );
		builder.append( getNomeUsuarioAlteracao() );
		builder.append( ",\n\tgetDataOcorrenciaCriacao()=" );
		builder.append( getDataOcorrenciaCriacao() );
		builder.append( ",\n\tgetDataOcorrenciaAlteracao()=" );
		builder.append( getDataOcorrenciaAlteracao() );
		builder.append( ",\n\tgetId()=" );
		builder.append( getId() );
		builder.append( "]\n" );
		return builder.toString();
	}

	@Override
	public int compareTo( NotaFiscalVendaDTO o ) {
		return this.getData().compareTo( o.getData() );
	}
}
