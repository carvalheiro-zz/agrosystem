package br.com.srcsoftware.sistema.produto.tipo;

import br.com.srcsoftware.abstracts.AbstractDTO;

public class TipoDTO extends AbstractDTO implements Comparable< TipoDTO >{

	private String id;
	private String nome;
	private String classificacao; // Insumo(aplica na lavroura) / Item(usado em imoveis, veiculos e implementos)

	public TipoDTO(){}

	public TipoDTO( String id ){
		setId( id );
	}

	public String getId() {
		return id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao( String classificacao ) {
		this.classificacao = classificacao;
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
		TipoDTO other = (TipoDTO) obj;
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
		builder.append( ",\n\tclassificacao=" );
		builder.append( classificacao );
		builder.append( "]\n" );
		return builder.toString();
	}

	@Override
	public int compareTo( TipoDTO o ) {
		return this.getNome().compareToIgnoreCase( o.getNome() );
	}

}
