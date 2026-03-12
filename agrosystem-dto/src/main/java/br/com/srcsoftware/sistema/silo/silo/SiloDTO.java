package br.com.srcsoftware.sistema.silo.silo;

import br.com.srcsoftware.abstracts.AbstractDTO;

public class SiloDTO extends AbstractDTO implements Comparable< SiloDTO >{

	private String id;
	private String nome;
	private String capacidade;

	public SiloDTO(){}

	public SiloDTO( String id ){
		setId( id );
	}

	public String getId() {
		return id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public String getCapacidade() {
		return capacidade;
	}

	public void setCapacidade( String capacidade ) {
		this.capacidade = capacidade;
	}

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

	/**
	 * Polimorfico
	 * 
	 * @see java.lang.Object#hashCode()
	 */

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
		SiloDTO other = (SiloDTO) obj;
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
		builder.append( "TipoDTO [\n\tid=" );
		builder.append( id );
		builder.append( ",\n\tnome=" );
		builder.append( nome );
		builder.append( ",\n\tcapacidade=" );
		builder.append( capacidade );
		builder.append( "]\n" );
		return builder.toString();
	}

	@Override
	public int compareTo( SiloDTO o ) {
		return this.getNome().compareToIgnoreCase( o.getNome() );
	}

}
