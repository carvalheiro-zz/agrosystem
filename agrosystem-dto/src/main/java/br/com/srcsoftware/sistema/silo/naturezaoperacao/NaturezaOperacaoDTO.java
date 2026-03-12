package br.com.srcsoftware.sistema.silo.naturezaoperacao;

import br.com.srcsoftware.abstracts.AbstractDTO;

public class NaturezaOperacaoDTO extends AbstractDTO implements Comparable< NaturezaOperacaoDTO > {

	private String id;
	private String nome;

	public NaturezaOperacaoDTO() {
	}

	public NaturezaOperacaoDTO( String id ) {
		setId( id );
	}

	public String getId() {
		return id;
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
		NaturezaOperacaoDTO other = (NaturezaOperacaoDTO) obj;
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
		builder.append( "NaturezaOperacaoDTO \n\tnome=" );
		builder.append( nome );
		builder.append( "]\n" );
		return builder.toString();
	}

	@Override
	public int compareTo( NaturezaOperacaoDTO o ) {
		return this.getNome().compareToIgnoreCase( o.getNome() );
	}

}
