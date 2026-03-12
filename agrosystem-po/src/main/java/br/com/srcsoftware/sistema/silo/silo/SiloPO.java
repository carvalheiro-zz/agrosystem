package br.com.srcsoftware.sistema.silo.silo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.srcsoftware.abstracts.AbstractPO;

@Entity
@Table( name = "sistema_silos", uniqueConstraints = @UniqueConstraint( columnNames = { "nome" }, name = "UK_sistema_silos" ) )
public class SiloPO extends AbstractPO implements Comparable< SiloPO >{

	@Column( nullable = false, length = 100 )
	private String nome;

	@Column( nullable = true, precision = 8, scale = 2 )
	private BigDecimal capacidade;

	public String getNome() {
		return this.nome;
	}

	public BigDecimal getCapacidade() {
		return capacidade;
	}

	public void setCapacidade( BigDecimal capacidade ) {
		this.capacidade = capacidade;
	}

	public void setNome( String nome ) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( nome == null ) ? 0 : nome.hashCode() );
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
		SiloPO other = (SiloPO) obj;
		if ( nome == null ) {
			if ( other.nome != null )
				return false;
		} else if ( !nome.equals( other.nome ) )
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "TipoPO \n\tnome=" );
		builder.append( nome );
		builder.append( ",\n\tcapacidade=" );
		builder.append( capacidade );
		builder.append( "]\n" );
		return builder.toString();
	}

	@Override
	public int compareTo( SiloPO o ) {
		return this.getNome().compareToIgnoreCase( o.getNome() );
	}

}
