package br.com.srcsoftware.sistema.produto.marca;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.srcsoftware.abstracts.AbstractPO;

@Entity
@Table( name = "sistema_marcas", uniqueConstraints = @UniqueConstraint( columnNames = { "nome" }, name = "UK_sistema_marcas" ) )
public class MarcaPO extends AbstractPO implements Comparable< MarcaPO >{

	@Column( nullable = false, length = 50 )
	private String nome;

	/**
	 * Método responsável por retornar o valor do atributo nome.
	 * 
	 * @return String nome - nome a ser retornado(a).
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Método responsável por atribuir o valor ao atributo nome.
	 * 
	 * @param String nome - nome a ser atribuido(a).
	 */
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
		if ( this == obj ) {
			return true;
		}
		if ( obj == null ) {
			return false;
		}
		if ( !( obj instanceof MarcaPO ) ) {
			return false;
		}
		MarcaPO other = (MarcaPO) obj;
		if ( nome == null ) {
			if ( other.nome != null ) {
				return false;
			}
		} else if ( !nome.equals( other.nome ) ) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "MarcaPO [\n\tnome=" );
		builder.append( nome );
		builder.append( ",\n\tgetIdTemp()=" );
		builder.append( getIdTemp() );
		builder.append( "]\n" );
		return builder.toString();
	}

	@Override
	public int compareTo( MarcaPO o ) {
		return this.getNome().compareToIgnoreCase( o.getNome() );
	}

}
