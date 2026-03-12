package br.com.srcsoftware.sistema.silo.contratovenda;

import java.math.BigDecimal;

import br.com.srcsoftware.abstracts.AuditoriaAbstractDTO;
import br.com.srcsoftware.modular.manager.pessoa.cliente.ClienteDTO;
import br.com.srcsoftware.modular.manager.pessoa.funcionario.FuncionarioDTO;
import br.com.srcsoftware.modular.manager.utilidades.DateUtil;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.safra.cultura.CulturaDTO;

public final class ContratoVendaDTO extends AuditoriaAbstractDTO implements Comparable< ContratoVendaDTO >{
	private String id;
	private String data;
	private String numero;
	private ClienteDTO cliente = new ClienteDTO();
	private FuncionarioDTO vendedor = new FuncionarioDTO();
	private CulturaDTO cultura;
	private String valorUnitario;
	private String quantidade;
	private String valorTotal;
	private String observacao;
	private String funrural;

	// Usado na tela
	private String quantidadeRestante;
	private String quantidadeEntregue;

	public String getValorTotalLiquido() {
		if ( Utilidades.isNuloOuVazio( this.getFunrural() ) ) {
			return this.getValorTotal();
		}

		BigDecimal valorTotal = Utilidades.parseBigDecimal( this.getValorTotal() ).setScale( 2 );
		BigDecimal funrural = Utilidades.parseBigDecimal( this.getFunrural() ).setScale( 2 );
		BigDecimal percentualFunrural = funrural.divide( new BigDecimal( "100" ), Utilidades.SCALE_CALCULOS, BigDecimal.ROUND_FLOOR );
		BigDecimal totalLiquido = valorTotal.multiply( BigDecimal.ONE.subtract( percentualFunrural ) );

		return Utilidades.parseBigDecimal( totalLiquido );
	}

	public String getFunrural() {
		return funrural;
	}

	public void setFunrural( String funrural ) {
		this.funrural = funrural;
	}

	public void setValorTotal( String valorTotal ) {
		this.valorTotal = valorTotal;
	}

	public String getValorTotal() {
		return valorTotal;
	}

	public String getQuantidadeRestante() {
		return quantidadeRestante;
	}

	public void setQuantidadeRestante( String quantidadeRestante ) {
		this.quantidadeRestante = quantidadeRestante;
	}

	public String getQuantidadeEntregue() {
		return quantidadeEntregue;
	}

	public void setQuantidadeEntregue( String quantidadeEntregue ) {
		this.quantidadeEntregue = quantidadeEntregue;
	}

	public String getId() {
		return id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public String getEmSacas() {
		if ( Utilidades.isNuloOuVazio( this.getQuantidade() ) ) {
			return null;
		}
		return Utilidades.parseBigDecimal( Utilidades.parseBigDecimal( this.getQuantidade() ).divide( new BigDecimal( "60" ), 2, BigDecimal.ROUND_CEILING ) );
	}

	public String getValorUnitario() {
		if ( Utilidades.isNuloOuVazio( this.getQuantidade() ) ) {
			return null;
		}
		if ( Utilidades.isNuloOuVazio( this.getValorTotal() ) ) {
			return null;
		}
		return Utilidades.parseBigDecimal( Utilidades.parseBigDecimal( getValorTotal() ).divide( Utilidades.parseBigDecimal( this.getQuantidade() ), Utilidades.SCALE_CALCULOS, BigDecimal.ROUND_CEILING ) );
	}

	public String getDataToString() {
		return DateUtil.formatLocalDate( this.getData() );
	}

	public String getData() {
		return data;
	}

	public void setData( String data ) {
		this.data = data;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero( String numero ) {
		this.numero = numero;
	}

	public ClienteDTO getCliente() {
		if ( cliente == null ) {
			cliente = new ClienteDTO();
		}
		return cliente;
	}

	public void setCliente( ClienteDTO cliente ) {
		this.cliente = cliente;
	}

	public FuncionarioDTO getVendedor() {
		if ( vendedor == null ) {
			vendedor = new FuncionarioDTO();
		}
		return vendedor;
	}

	public void setVendedor( FuncionarioDTO vendedor ) {
		this.vendedor = vendedor;
	}

	public CulturaDTO getCultura() {
		if ( cultura == null ) {
			cultura = new CulturaDTO();
		}
		return cultura;
	}

	public void setCultura( CulturaDTO cultura ) {
		this.cultura = cultura;
	}

	/*public String getValorUnitario() {
		return valorUnitario;
	}*/

	public void setValorUnitario( String valorUnitario ) {
		this.valorUnitario = valorUnitario;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade( String quantidade ) {
		this.quantidade = quantidade;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao( String observacao ) {
		this.observacao = observacao;
	}

	/**
	 * Polimorfico
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( numero == null ) ? 0 : numero.hashCode() );
		return result;
	}

	/**
	 * Polimorfico
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}
		if ( obj == null ) {
			return false;
		}
		if ( !( obj instanceof ContratoVendaDTO ) ) {
			return false;
		}
		ContratoVendaDTO other = (ContratoVendaDTO) obj;
		if ( numero == null ) {
			if ( other.numero != null ) {
				return false;
			}
		} else if ( !numero.equals( other.numero ) ) {
			return false;
		}
		return true;
	}

	/**
	 * Polimorfico
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "ContratoVendaDTO [data=" );
		builder.append( data );
		builder.append( ", numero=" );
		builder.append( numero );
		builder.append( ", cliente=" );
		builder.append( cliente );
		builder.append( ", vendedor=" );
		builder.append( vendedor );
		builder.append( ", cultura=" );
		builder.append( cultura );
		builder.append( ", valorUnitario=" );
		builder.append( valorUnitario );
		builder.append( ", quantidade=" );
		builder.append( quantidade );
		builder.append( ", observacao=" );
		builder.append( observacao );
		builder.append( ", toString()=" );
		builder.append( super.toString() );
		builder.append( "]" );
		return builder.toString();
	}

	@Override
	public int compareTo( ContratoVendaDTO o ) {
		return this.getData().compareTo( o.getData() );
	}
}
