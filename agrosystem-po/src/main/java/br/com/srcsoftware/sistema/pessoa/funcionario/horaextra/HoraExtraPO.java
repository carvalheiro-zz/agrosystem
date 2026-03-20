package br.com.srcsoftware.sistema.pessoa.funcionario.horaextra;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;

import br.com.srcsoftware.abstracts.AuditoriaAbstractPOComEmpresa;
import br.com.srcsoftware.modular.manager.pessoa.funcionario.FuncionarioPO;

@Entity
@Table( name = "sistema_horasextras", uniqueConstraints = @UniqueConstraint( columnNames = { "idColaborador", "data", "tipo" }, name = "UK_sistema_horasextras" ) )
public class HoraExtraPO extends AuditoriaAbstractPOComEmpresa implements Comparable< HoraExtraPO >{

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idColaborador", nullable = false )
	private FuncionarioPO colaborador = new FuncionarioPO();

	@Column( nullable = false )
	private LocalDate data;

	@Column( nullable = false, precision = 5, scale = 2 )
	private BigDecimal quantidadeHoras;

	@Column( nullable = false, length = 10 )
	private String tipo; // Lan�amento / Pagamento
	
	@Column( nullable = false, precision = 5, scale = 2 )
	private BigDecimal quantidade50Porcento;
	@Column( nullable = false, precision = 5, scale = 2 )
	private BigDecimal quantidade100Porcento;
	@Column( nullable = false, precision = 5, scale = 2 )
	private BigDecimal quantidadeDomingoFeriado;
	
	@Type( type = "true_false" )
	@Column( nullable = true )
	private Boolean feriado;
	
	public Boolean getFeriado() {
		return feriado;
	}

	public void setFeriado(Boolean feriado) {
		this.feriado = feriado;
	}

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

	

	public BigDecimal getQuantidade50Porcento() {
		return quantidade50Porcento;
	}

	public void setQuantidade50Porcento(BigDecimal quantidade50Porcento) {
		this.quantidade50Porcento = quantidade50Porcento;
	}

	public BigDecimal getQuantidade100Porcento() {
		return quantidade100Porcento;
	}

	public void setQuantidade100Porcento(BigDecimal quantidade100Porcento) {
		this.quantidade100Porcento = quantidade100Porcento;
	}

	public BigDecimal getQuantidadeDomingoFeriado() {
		return quantidadeDomingoFeriado;
	}

	public void setQuantidadeDomingoFeriado(BigDecimal quantidadeDomingoFeriado) {
		this.quantidadeDomingoFeriado = quantidadeDomingoFeriado;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HoraExtraPO [getTipo()=");
		builder.append(getTipo());
		builder.append(", getColaborador()=");
		builder.append(getColaborador());
		builder.append(", getData()=");
		builder.append(getData());
		builder.append(", getQuantidadeHoras()=");
		builder.append(getQuantidadeHoras());
		builder.append(", getQuantidade50Porcento()=");
		builder.append(getQuantidade50Porcento());
		builder.append(", getQuantidade100Porcento()=");
		builder.append(getQuantidade100Porcento());
		builder.append(", getQuantidadeDomingoFeriado()=");
		builder.append(getQuantidadeDomingoFeriado());
		builder.append("]");
		return builder.toString();
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
		result = prime * result + ( ( quantidadeHoras == null ) ? 0 : quantidadeHoras.hashCode() );
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
		if ( !( obj instanceof HoraExtraPO ) ) {
			return false;
		}
		HoraExtraPO other = (HoraExtraPO) obj;
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
		if ( quantidadeHoras == null ) {
			if ( other.quantidadeHoras != null ) {
				return false;
			}
		} else if ( !quantidadeHoras.equals( other.quantidadeHoras ) ) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo( HoraExtraPO o ) {
		return this.getData().compareTo( o.getData() );
	}

}
