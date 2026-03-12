package br.com.srcsoftware.gestao.sistema.produto.unidademedida;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.srcsoftware.abstracts.AbstractPO;
import br.com.srcsoftware.modular.manager.utilidades.Constantes;

@Entity
@Table( name = "unidades_medidas", schema = Constantes.SCHEMA_MIGRACAO )
public final class UnidadeMedidaMigracaoPO extends AbstractPO implements Comparable< UnidadeMedidaMigracaoPO >{

	@Column( name = "nome", nullable = false, length = 100, unique = true )
	private String nome;

	@Column( name = "sigla", nullable = false, length = 20, unique = true )
	private String sigla;

	public String getNome() {
		return this.nome;
	}

	public void setNome( String nome ) {
		this.nome = nome;
	}

	public String getSigla() {
		return this.sigla;
	}

	public void setSigla( String sigla ) {
		this.sigla = sigla;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 0;
		result = ( prime * result ) + ( ( this.nome == null ) ? 0 : this.nome.hashCode() );
		result = ( prime * result ) + ( ( this.sigla == null ) ? 0 : this.sigla.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}

		if ( !( obj instanceof UnidadeMedidaMigracaoPO ) ) {
			return false;
		}
		UnidadeMedidaMigracaoPO other = (UnidadeMedidaMigracaoPO) obj;
		if ( this.nome == null ) {
			if ( other.nome != null ) {
				return false;
			}
		} else if ( !this.nome.equals( other.nome ) ) {
			return false;
		}
		if ( this.sigla == null ) {
			if ( other.sigla != null ) {
				return false;
			}
		} else if ( !this.sigla.equals( other.sigla ) ) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "UnidadeMedidaPO [nome=" + this.nome + ", sigla=" + this.sigla + "]";
	}

	@Override
	public int compareTo( UnidadeMedidaMigracaoPO o ) {
		return this.getNome().compareToIgnoreCase( o.getNome() );
	}

}
