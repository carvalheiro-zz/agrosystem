package br.com.srcsoftware.sistema.produto.marca;

import br.com.srcsoftware.abstracts.AbstractDTO;
import br.com.srcsoftware.sistema.produto.unidademedida.UnidadeMedidaDTO;

public class MarcaDTO extends AbstractDTO implements Comparable< UnidadeMedidaDTO >{

	private String id;
	private String nome;

	public MarcaDTO(){}

	public MarcaDTO( String id ){
		setId( id );
	}

	public String getNome() {
		return this.nome;
	}

	public String getId() {
		return id;
	}

	public void setNome( String nome ) {
		this.nome = nome;
	}

	public void setId( String id ) {
		this.id = id;
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
		MarcaDTO other = (MarcaDTO) obj;
		if ( nome == null ) {
			if ( other.nome != null )
				return false;
		} else if ( !nome.equals( other.nome ) )
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MarcaDTO [nome=" + this.nome + ", getId()=" + this.getId() + "]";
	}

	@Override
	public int compareTo( UnidadeMedidaDTO o ) {
		return this.getNome().compareToIgnoreCase( o.getNome() );
	}

}
