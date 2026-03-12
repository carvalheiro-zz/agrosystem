package br.com.srcsoftware.sistema.safra.setor;

import br.com.srcsoftware.abstracts.AbstractDTO;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;

public final class SetorDTO extends AbstractDTO{

	private String id;
	private String nomeCompleto;
	private String nome;
	private String subSetor;

	public void montarNomeCompleto() {
		String nome = Utilidades.isNuloOuVazio( this.getNome() ) ? "" : this.getNome();
		String subSetor = Utilidades.isNuloOuVazio( this.getSubSetor() ) ? "" : this.getSubSetor();

		StringBuilder nomeCompleto = new StringBuilder();

		nomeCompleto.append( nome );

		if ( !Utilidades.isNuloOuVazio( subSetor ) ) {
			nomeCompleto.append( " - " );
			nomeCompleto.append( this.getSubSetor() );
		}

		if ( Utilidades.isNuloOuVazio( nomeCompleto.toString() ) ) {
			return;
		}
		this.setNomeCompleto( nomeCompleto.toString() );
	}

	public String getNomeCompleto() {
		return this.nomeCompleto;
	}

	public void setNomeCompleto( String nomeCompleto ) {
		this.nomeCompleto = nomeCompleto;
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

	public String getSubSetor() {
		return this.subSetor;
	}

	public void setSubSetor( String subSetor ) {
		this.subSetor = subSetor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( nome == null ) ? 0 : nome.hashCode() );
		result = prime * result + ( ( subSetor == null ) ? 0 : subSetor.hashCode() );
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
		SetorDTO other = (SetorDTO) obj;
		if ( nome == null ) {
			if ( other.nome != null )
				return false;
		} else if ( !nome.equals( other.nome ) )
			return false;
		if ( subSetor == null ) {
			if ( other.subSetor != null )
				return false;
		} else if ( !subSetor.equals( other.subSetor ) )
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "SetorDTO [\n\tid=" );
		builder.append( id );
		builder.append( ",\n\tnomeCompleto=" );
		builder.append( nomeCompleto );
		builder.append( ",\n\tnome=" );
		builder.append( nome );
		builder.append( ",\n\tsubSetor=" );
		builder.append( subSetor );
		builder.append( ",\n\tgetIdTemp()=" );
		builder.append( getIdTemp() );
		builder.append( "]\n" );
		return builder.toString();
	}

}
