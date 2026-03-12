package br.com.srcsoftware.sistema.produto.unidademedida;

import br.com.srcsoftware.abstracts.AbstractDTO;

public final class UnidadeMedidaDTO extends AbstractDTO implements Comparable< UnidadeMedidaDTO >{

	private String id;
	private String nome;
	private String sigla;

	public UnidadeMedidaDTO(){}

	public UnidadeMedidaDTO( String id ){
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
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		result = prime * result + ( ( nome == null ) ? 0 : nome.hashCode() );
		result = prime * result + ( ( sigla == null ) ? 0 : sigla.hashCode() );
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
		UnidadeMedidaDTO other = (UnidadeMedidaDTO) obj;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
		if ( nome == null ) {
			if ( other.nome != null )
				return false;
		} else if ( !nome.equals( other.nome ) )
			return false;
		if ( sigla == null ) {
			if ( other.sigla != null )
				return false;
		} else if ( !sigla.equals( other.sigla ) )
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UnidadeMedidaDTO [nome=" + this.nome + ", sigla=" + this.sigla + ", getId()=" + this.getId() + "]";
	}

	@Override
	public int compareTo( UnidadeMedidaDTO o ) {
		return this.getNome().compareToIgnoreCase( o.getNome() );
	}

}
