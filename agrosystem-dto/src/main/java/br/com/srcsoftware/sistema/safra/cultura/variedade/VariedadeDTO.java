package br.com.srcsoftware.sistema.safra.cultura.variedade;

import br.com.srcsoftware.abstracts.AbstractDTO;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.safra.cultura.CulturaDTO;

public final class VariedadeDTO extends AbstractDTO implements Comparable< VariedadeDTO >{

	private String id;
	private String nomeCompleto;
	private String nome;
	private CulturaDTO cultura;

	public void montarNomeCompleto() {
		String nome = Utilidades.isNuloOuVazio( this.getNome() ) ? "" : this.getNome();
		String cultura = Utilidades.isNuloOuVazio( this.getCultura().getNome() ) ? "" : this.getCultura().getNome();

		String valor = String.join( " ", nome, "(", cultura, ")" ).replaceAll( "null", "" ).trim();
		if ( Utilidades.isNuloOuVazio( valor ) ) {
			return;
		}
		this.setNomeCompleto( valor );
	}

	public void ajustarObjetosParaRelacionamentos() {
		this.setCultura( new CulturaDTO( Utilidades.isNuloOuVazio( this.getCultura().getId() ) ? null : this.getCultura().getId() ) );
	}

	public String getNomeCompleto() {
		return this.nomeCompleto;
	}

	public void setNomeCompleto( String nomeCompleto ) {
		this.nomeCompleto = nomeCompleto;
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

	public CulturaDTO getCultura() {
		if ( this.cultura == null ) {
			this.cultura = new CulturaDTO();
		}
		return this.cultura;
	}

	public void setCultura( CulturaDTO cultura ) {
		this.cultura = cultura;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = ( prime * result ) + ( ( this.nomeCompleto == null ) ? 0 : this.nomeCompleto.hashCode() );
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
		if ( !( obj instanceof VariedadeDTO ) ) {
			return false;
		}
		VariedadeDTO other = (VariedadeDTO) obj;
		if ( this.nomeCompleto == null ) {
			if ( other.nomeCompleto != null ) {
				return false;
			}
		} else if ( !this.nomeCompleto.equals( other.nomeCompleto ) ) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "VariedadeDTO [nomeCompleto=" );
		builder.append( this.nomeCompleto );
		builder.append( "]" );
		return builder.toString();
	}

	@Override
	public int compareTo( VariedadeDTO o ) {
		return this.getNomeCompleto().compareToIgnoreCase( o.getNomeCompleto() );
	}

}
