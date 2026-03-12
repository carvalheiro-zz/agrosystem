package br.com.srcsoftware.gestao.sistema.cultura;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.srcsoftware.abstracts.AbstractPO;
import br.com.srcsoftware.modular.manager.utilidades.Constantes;

@Entity
@Table( name = "sistema_culturas", schema = Constantes.SCHEMA_MIGRACAO, uniqueConstraints = @UniqueConstraint( columnNames = { "nome", "variedade" } ) )
public final class CulturaMigracaoPO extends AbstractPO implements Comparable< CulturaMigracaoPO >{

	@Column( nullable = false, length = 100 )
	private String nome;

	@Column( nullable = false, length = 255 )
	private String variedade;

	public String getNome() {
		return this.nome;
	}

	public void setNome( String nome ) {
		this.nome = nome;
	}

	public String getVariedade() {
		return this.variedade;
	}

	public void setVariedade( String variedade ) {
		this.variedade = variedade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 0;
		result = ( prime * result ) + ( ( this.nome == null ) ? 0 : this.nome.hashCode() );
		result = ( prime * result ) + ( ( this.variedade == null ) ? 0 : this.variedade.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}

		if ( !( obj instanceof CulturaMigracaoPO ) ) {
			return false;
		}
		CulturaMigracaoPO other = (CulturaMigracaoPO) obj;
		if ( this.nome == null ) {
			if ( other.nome != null ) {
				return false;
			}
		} else if ( !this.nome.equals( other.nome ) ) {
			return false;
		}
		if ( this.variedade == null ) {
			if ( other.variedade != null ) {
				return false;
			}
		} else if ( !this.variedade.equals( other.variedade ) ) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "CulturaPO [nome=" + this.nome + ", variedade=" + this.variedade + "]";
	}

	@Override
	public int compareTo( CulturaMigracaoPO o ) {
		return this.getNome().compareToIgnoreCase( o.getNome() );
	}

}
