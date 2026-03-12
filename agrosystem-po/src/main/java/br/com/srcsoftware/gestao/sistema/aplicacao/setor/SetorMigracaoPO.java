package br.com.srcsoftware.gestao.sistema.aplicacao.setor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.srcsoftware.gestao.AuditoriaAbstractMigracaoPO;
import br.com.srcsoftware.modular.manager.utilidades.Constantes;

@Entity
@Table( name = "setores", schema = Constantes.SCHEMA_MIGRACAO, uniqueConstraints = @UniqueConstraint( columnNames = { "nome", "subSetor" } ) )
public final class SetorMigracaoPO extends AuditoriaAbstractMigracaoPO implements Comparable< SetorMigracaoPO >{

	@Column( name = "nome", length = 100, nullable = false )
	private String nome;

	@Column( name = "subSetor", length = 100, nullable = true )
	private String subSetor;

	public String getNome() {
		return this.nome;
	}

	public void setNome( String nome ) {
		this.nome = nome;
	}

	public String getSubSetor() {
		return this.subSetor;
	}

	public void setSubSetor( String subSetor ) {
		this.subSetor = subSetor;
	}

	@Override
	public String toString() {
		return "SetorPO [nome=" + this.nome + ", subSetor=" + this.subSetor + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 0;
		result = ( prime * result ) + ( ( this.nome == null ) ? 0 : this.nome.hashCode() );
		result = ( prime * result ) + ( ( this.subSetor == null ) ? 0 : this.subSetor.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}

		if ( this.getClass() != obj.getClass() ) {
			return false;
		}
		SetorMigracaoPO other = (SetorMigracaoPO) obj;
		if ( this.nome == null ) {
			if ( other.nome != null ) {
				return false;
			}
		} else if ( !this.nome.equals( other.nome ) ) {
			return false;
		}
		if ( this.subSetor == null ) {
			if ( other.subSetor != null ) {
				return false;
			}
		} else if ( !this.subSetor.equals( other.subSetor ) ) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo( SetorMigracaoPO o ) {
		return this.getNome().compareToIgnoreCase( o.getNome() );
	}

}
