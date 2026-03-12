package br.com.srcsoftware.sistema.pessoa.funcionario.ferias;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.srcsoftware.abstracts.AuditoriaAbstractPOComEmpresa;
import br.com.srcsoftware.modular.manager.pessoa.funcionario.FuncionarioPO;

@Entity
@Table( name = "sistema_ferias",
        uniqueConstraints = { @UniqueConstraint( columnNames = { "idColaborador", "data", "tipo" }, name = "UK_sistema_ferias1" ), @UniqueConstraint( columnNames = { "idColaborador", "data", "dataCumprimentoTermino", "tipo" },
                name = "UK_sistema_ferias2" ) } )
public class FeriasPO extends AuditoriaAbstractPOComEmpresa implements Comparable< FeriasPO >{

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idColaborador", nullable = false )
	private FuncionarioPO colaborador = new FuncionarioPO();

	@Column( nullable = false )
	private LocalDate data;

	@Column( nullable = true )
	private LocalDate dataCumprimentoTermino;

	@Column( nullable = false, precision = 5, scale = 2 )
	private BigDecimal quantidadeHoras;

	@Column( nullable = false, length = 10 )
	private String tipo; // Adquirida / Cumprida / Vendida

	public String getTipo() {
		return tipo;
	}

	public void setTipo( String tipo ) {
		this.tipo = tipo;
	}

	public FuncionarioPO getColaborador() {
		return colaborador;
	}

	public void setColaborador( FuncionarioPO colaborador ) {
		this.colaborador = colaborador;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData( LocalDate data ) {
		this.data = data;
	}

	public BigDecimal getQuantidadeHoras() {
		return quantidadeHoras;
	}

	public void setQuantidadeHoras( BigDecimal quantidadeHoras ) {
		this.quantidadeHoras = quantidadeHoras;
	}

	public LocalDate getDataCumprimentoTermino() {
		return dataCumprimentoTermino;
	}

	public void setDataCumprimentoTermino( LocalDate dataCumprimentoTermino ) {
		this.dataCumprimentoTermino = dataCumprimentoTermino;
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
		result = prime * result + ( ( colaborador == null ) ? 0 : colaborador.hashCode() );
		result = prime * result + ( ( data == null ) ? 0 : data.hashCode() );
		result = prime * result + ( ( dataCumprimentoTermino == null ) ? 0 : dataCumprimentoTermino.hashCode() );
		result = prime * result + ( ( quantidadeHoras == null ) ? 0 : quantidadeHoras.hashCode() );
		result = prime * result + ( ( tipo == null ) ? 0 : tipo.hashCode() );
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
		if ( !( obj instanceof FeriasPO ) ) {
			return false;
		}
		FeriasPO other = (FeriasPO) obj;
		if ( colaborador == null ) {
			if ( other.colaborador != null ) {
				return false;
			}
		} else if ( !colaborador.equals( other.colaborador ) ) {
			return false;
		}
		if ( data == null ) {
			if ( other.data != null ) {
				return false;
			}
		} else if ( !data.equals( other.data ) ) {
			return false;
		}
		if ( dataCumprimentoTermino == null ) {
			if ( other.dataCumprimentoTermino != null ) {
				return false;
			}
		} else if ( !dataCumprimentoTermino.equals( other.dataCumprimentoTermino ) ) {
			return false;
		}
		if ( quantidadeHoras == null ) {
			if ( other.quantidadeHoras != null ) {
				return false;
			}
		} else if ( !quantidadeHoras.equals( other.quantidadeHoras ) ) {
			return false;
		}
		if ( tipo == null ) {
			if ( other.tipo != null ) {
				return false;
			}
		} else if ( !tipo.equals( other.tipo ) ) {
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
		builder.append( "FeriasPO [colaborador=" );
		builder.append( colaborador );
		builder.append( ", data=" );
		builder.append( data );
		builder.append( ", dataCumprimentoTermino=" );
		builder.append( dataCumprimentoTermino );
		builder.append( ", quantidadeHoras=" );
		builder.append( quantidadeHoras );
		builder.append( ", tipo=" );
		builder.append( tipo );
		builder.append( ", toString()=" );
		builder.append( super.toString() );
		builder.append( "]" );
		return builder.toString();
	}

	@Override
	public int compareTo( FeriasPO o ) {
		return this.getData().compareTo( o.getData() );
	}

}
