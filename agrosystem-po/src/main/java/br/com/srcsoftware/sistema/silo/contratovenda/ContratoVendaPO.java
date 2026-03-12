package br.com.srcsoftware.sistema.silo.contratovenda;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.srcsoftware.abstracts.AuditoriaAbstractPO;
import br.com.srcsoftware.modular.manager.pessoa.cliente.ClientePO;
import br.com.srcsoftware.modular.manager.pessoa.funcionario.FuncionarioPO;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.safra.cultura.CulturaPO;

@Entity
@Table( name = "sistema_contratovendas" )
public final class ContratoVendaPO extends AuditoriaAbstractPO implements Comparable< ContratoVendaPO >{

	@Column( length = 10, nullable = false )
	private LocalDate data;

	@Column( length = 50, nullable = false )
	private String numero;

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idCliente", nullable = false, foreignKey = @ForeignKey( name = "fk_cliente_contratovendas" ) )
	private ClientePO cliente = new ClientePO();

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idVendedor", nullable = false )
	private FuncionarioPO vendedor = new FuncionarioPO();

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idCultura", nullable = false, foreignKey = @ForeignKey( name = "fk_cultura_contratovendas" ) )
	private CulturaPO cultura;

	@Column( precision = 16, scale = Utilidades.SCALE_CALCULOS, nullable = false )
	private BigDecimal valorUnitario;

	@Column( precision = 16, scale = 2, nullable = false )
	private BigDecimal quantidade;

	@Column( precision = 16, scale = 2, nullable = false )
	private BigDecimal valorTotal;

	@Lob
	@Column( length = 1000, nullable = true )
	private String observacao;

	@Column( precision = 4, scale = 2, nullable = true )
	private BigDecimal funrural;

	public BigDecimal getFunRural() {
		return funrural;
	}

	public void setFunRural( BigDecimal funrural ) {
		this.funrural = funrural;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal( BigDecimal valorTotal ) {
		this.valorTotal = valorTotal;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData( LocalDate data ) {
		this.data = data;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero( String numero ) {
		this.numero = numero;
	}

	public ClientePO getCliente() {
		return cliente;
	}

	public void setCliente( ClientePO cliente ) {
		this.cliente = cliente;
	}

	public FuncionarioPO getVendedor() {
		return vendedor;
	}

	public void setVendedor( FuncionarioPO vendedor ) {
		this.vendedor = vendedor;
	}

	public CulturaPO getCultura() {
		return cultura;
	}

	public void setCultura( CulturaPO cultura ) {
		this.cultura = cultura;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario( BigDecimal valorUnitario ) {
		this.valorUnitario = valorUnitario;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade( BigDecimal quantidade ) {
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
		if ( !( obj instanceof ContratoVendaPO ) ) {
			return false;
		}
		ContratoVendaPO other = (ContratoVendaPO) obj;
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
		builder.append( "ContratoVendaPO [data=" );
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
	public int compareTo( ContratoVendaPO o ) {
		return this.getData().compareTo( o.getData() );
	}
}
