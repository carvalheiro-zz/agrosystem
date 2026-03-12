package br.com.srcsoftware.sistema.safra.setor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.srcsoftware.abstracts.AbstractPO;

@Entity
@Table( name = "sistema_setores", uniqueConstraints = @UniqueConstraint( columnNames = { "nome", "subSetor" }, name = "UK_sistema_setores" ) )
public final class SetorPO extends AbstractPO implements Comparable< SetorPO >{

	@Lob
	@Column( length = 500, nullable = true )
	private String nomeCompleto;

	@Column( length = 50, nullable = false )
	private String nome;

	@Column( length = 50, nullable = true )
	private String subSetor;

	public String getNomeCompleto() {
		return this.nomeCompleto;
	}

	public void setNomeCompleto( String nomeCompleto ) {
		this.nomeCompleto = nomeCompleto;
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
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "SetorPO [\n\tnomeCompleto=" );
		builder.append( nomeCompleto );
		builder.append( ",\n\tnome=" );
		builder.append( nome );
		builder.append( ",\n\tsubSetor=" );
		builder.append( subSetor );
		builder.append( ",\n\tgetId()=" );
		builder.append( getId() );
		builder.append( "]\n" );
		return builder.toString();
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
		SetorPO other = (SetorPO) obj;
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
	public int compareTo( SetorPO o ) {
		return this.getNome().compareToIgnoreCase( o.getNome() );
	}

}
