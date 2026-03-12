package br.com.srcsoftware.sistema.produto.unidademedida;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.srcsoftware.abstracts.AbstractPO;

@Entity
@Table( name = "sistema_unidadesmedida" )
public final class UnidadeMedidaPO extends AbstractPO implements Comparable< UnidadeMedidaPO >{

	@Column( nullable = false, length = 20, unique = true )
	private String nome;

	@Column( nullable = false, length = 4, unique = true )
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
		int result = 1;
		result = prime * result + ( ( sigla == null ) ? 0 : sigla.hashCode() );
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
		if ( !( obj instanceof UnidadeMedidaPO ) ) {
			return false;
		}
		UnidadeMedidaPO other = (UnidadeMedidaPO) obj;
		if ( sigla == null ) {
			if ( other.sigla != null ) {
				return false;
			}
		} else if ( !sigla.equals( other.sigla ) ) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "UnidadeMedidaPO [\n\tnome=" );
		builder.append( nome );
		builder.append( ",\n\tsigla=" );
		builder.append( sigla );
		builder.append( ",\n\tgetIdTemp()=" );
		builder.append( getIdTemp() );
		builder.append( "]\n" );
		return builder.toString();
	}

	@Override
	public int compareTo( UnidadeMedidaPO o ) {
		return this.getNome().compareToIgnoreCase( o.getNome() );
	}

}
