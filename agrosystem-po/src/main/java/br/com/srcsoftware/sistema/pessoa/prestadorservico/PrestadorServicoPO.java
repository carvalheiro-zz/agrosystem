package br.com.srcsoftware.sistema.pessoa.prestadorservico;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.srcsoftware.abstracts.AbstractPO;

@Entity
@Table( name = "sistema_prestadores", uniqueConstraints = @UniqueConstraint( columnNames = { "nome", "telefone", "empresa" }, name = "UK_sistema_prestadores" ) )
public final class PrestadorServicoPO extends AbstractPO implements Comparable< PrestadorServicoPO >{

	@Column( nullable = false, length = 100 )
	private String nome;

	@Column( nullable = true, length = 30 )
	private String telefone;

	@Column( nullable = true, length = 100 )
	private String empresa;

	public String getNome() {
		return this.nome;
	}

	public void setNome( String nome ) {
		this.nome = nome;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone( String telefone ) {
		this.telefone = telefone;
	}

	public String getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa( String empresa ) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {
		return "PrestadorServicoPO [nome=" + this.nome + ", telefone=" + this.telefone + ", empresa=" + this.empresa + ", getIdTemp()=" + this.getIdTemp() + ", getId()=" + this.getId() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = ( prime * result ) + ( ( this.empresa == null ) ? 0 : this.empresa.hashCode() );
		result = ( prime * result ) + ( ( this.nome == null ) ? 0 : this.nome.hashCode() );
		result = ( prime * result ) + ( ( this.telefone == null ) ? 0 : this.telefone.hashCode() );
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
		if ( !( obj instanceof PrestadorServicoPO ) ) {
			return false;
		}
		PrestadorServicoPO other = (PrestadorServicoPO) obj;
		if ( this.empresa == null ) {
			if ( other.empresa != null ) {
				return false;
			}
		} else if ( !this.empresa.equals( other.empresa ) ) {
			return false;
		}
		if ( this.nome == null ) {
			if ( other.nome != null ) {
				return false;
			}
		} else if ( !this.nome.equals( other.nome ) ) {
			return false;
		}
		if ( this.telefone == null ) {
			if ( other.telefone != null ) {
				return false;
			}
		} else if ( !this.telefone.equals( other.telefone ) ) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo( PrestadorServicoPO o ) {
		return this.getNome().compareToIgnoreCase( o.getNome() );
	}

}