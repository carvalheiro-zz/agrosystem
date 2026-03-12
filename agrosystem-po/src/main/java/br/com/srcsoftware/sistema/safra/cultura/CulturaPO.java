package br.com.srcsoftware.sistema.safra.cultura;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.srcsoftware.abstracts.AbstractPO;

@Entity
@Table( name = "sistema_culturas", uniqueConstraints = @UniqueConstraint( columnNames = { "nome" }, name = "UK_sistema_culturas" ) )
public final class CulturaPO extends AbstractPO implements Comparable< CulturaPO >{

	@Column( nullable = false, length = 100 )
	private String nome;

	public String getNome() {
		return this.nome;
	}

	public void setNome( String nome ) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = ( prime * result ) + ( ( this.nome == null ) ? 0 : this.nome.hashCode() );

		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}
		if ( obj == null ) {
			return false;
		}
		if ( this.getClass() != obj.getClass() ) {
			return false;
		}
		CulturaPO other = (CulturaPO) obj;
		if ( this.nome == null ) {
			if ( other.nome != null ) {
				return false;
			}
		} else if ( !this.nome.equals( other.nome ) ) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "CulturaPO [nome=" );
		builder.append( this.nome );
		builder.append( ", getId()=" );
		builder.append( this.getId() );
		builder.append( "]" );
		return builder.toString();
	}

	@Override
	public int compareTo( CulturaPO o ) {
		return this.getNome().compareToIgnoreCase( o.getNome() );
	}

}
