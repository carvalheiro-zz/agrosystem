package br.com.srcsoftware.sistema.safra.cultura;

import br.com.srcsoftware.abstracts.AbstractDTO;

public final class CulturaDTO extends AbstractDTO implements Comparable< CulturaDTO >{

	private String id;
	private String nome;

	public CulturaDTO(){}

	public CulturaDTO( String id ){
		this.setId( id );
	}

	public String getId() {
		return this.id;
	}

	public void setId( String id ) {
		this.id = id;
	}

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
		CulturaDTO other = (CulturaDTO) obj;
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
		builder.append( "CulturaDTO [id=" );
		builder.append( this.id );
		builder.append( ", nome=" );
		builder.append( this.nome );
		builder.append( ", getIdTemp()=" );
		builder.append( this.getIdTemp() );
		builder.append( "]" );
		return builder.toString();
	}

	@Override
	public int compareTo( CulturaDTO o ) {
		return this.getNome().compareToIgnoreCase( o.getNome() );
	}

}
