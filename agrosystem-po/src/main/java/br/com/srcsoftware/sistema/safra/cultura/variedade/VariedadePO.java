package br.com.srcsoftware.sistema.safra.cultura.variedade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.srcsoftware.abstracts.AbstractPO;
import br.com.srcsoftware.sistema.safra.cultura.CulturaPO;

@Entity
@Table( name = "sistema_variedades", uniqueConstraints = @UniqueConstraint( columnNames = { "nome", "idCultura" }, name = "UK_sistema_variedades" ) )
public final class VariedadePO extends AbstractPO implements Comparable< VariedadePO >{

	@Lob
	@Column( length = 500, nullable = true )
	private String nomeCompleto;

	@Column( nullable = false, length = 100 )
	private String nome;

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idCultura", nullable = false, foreignKey = @ForeignKey( name = "fk_variedade_cultura" ) )
	private CulturaPO cultura = new CulturaPO();

	public String getNomeCompleto() {
		return this.nomeCompleto;
	}

	public void setNomeCompleto( String nomeCompleto ) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome( String nome ) {
		this.nome = nome;
	}

	public CulturaPO getCultura() {
		return this.cultura;
	}

	public void setCultura( CulturaPO cultura ) {
		this.cultura = cultura;
	}

	@Override
	public int compareTo( VariedadePO o ) {
		return this.getNomeCompleto().compareToIgnoreCase( o.getNomeCompleto() );
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = ( prime * result ) + ( ( this.nomeCompleto == null ) ? 0 : this.nomeCompleto.hashCode() );
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
		if ( !( obj instanceof VariedadePO ) ) {
			return false;
		}
		VariedadePO other = (VariedadePO) obj;
		if ( this.nomeCompleto == null ) {
			if ( other.nomeCompleto != null ) {
				return false;
			}
		} else if ( !this.nomeCompleto.equals( other.nomeCompleto ) ) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "VariedadePO [nomeCompleto=" );
		builder.append( this.nomeCompleto );
		builder.append( "]" );
		return builder.toString();
	}

}
