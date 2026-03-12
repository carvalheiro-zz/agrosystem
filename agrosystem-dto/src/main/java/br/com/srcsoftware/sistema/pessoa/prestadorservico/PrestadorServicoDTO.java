package br.com.srcsoftware.sistema.pessoa.prestadorservico;

import br.com.srcsoftware.abstracts.AbstractDTO;

public final class PrestadorServicoDTO extends AbstractDTO implements Comparable< PrestadorServicoDTO >{

	private String id;
	private String nome;
	private String telefone;
	private String empresa;

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

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone( String telefone ) {
		this.telefone = telefone;
	}

	public String getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa( String empresa ) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {
		return "PrestadorServicoDTO [nome=" + this.nome + ", telefone=" + this.telefone + ", empresa=" + this.empresa + ", getIdTemp()=" + this.getIdTemp() + ", getId()=" + this.getId() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = ( prime * result ) + ( ( this.empresa == null ) ? 0 : this.empresa.hashCode() );
		result = ( prime * result ) + ( ( this.nome == null ) ? 0 : this.nome.hashCode() );
		result = ( prime * result ) + ( ( this.telefone == null ) ? 0 : this.telefone.hashCode() );
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
		if ( !( obj instanceof PrestadorServicoDTO ) ) {
			return false;
		}
		PrestadorServicoDTO other = (PrestadorServicoDTO) obj;
		if ( this.empresa == null ) {
			if ( other.empresa != null ) {
				return false;
			}
		} else if ( !this.empresa.equals( other.empresa ) ) {
			return false;
		}
		if ( this.nome == null ) {
			if ( other.nome != null ) {
				return false;
			}
		} else if ( !this.nome.equals( other.nome ) ) {
			return false;
		}
		if ( this.telefone == null ) {
			if ( other.telefone != null ) {
				return false;
			}
		} else if ( !this.telefone.equals( other.telefone ) ) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo( PrestadorServicoDTO o ) {
		return this.getNome().compareToIgnoreCase( o.getNome() );
	}

}