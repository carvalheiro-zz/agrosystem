package br.com.srcsoftware.gestao.manager.pessoa.pessoa;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import br.com.srcsoftware.gestao.AuditoriaAbstractMigracaoPO;

@MappedSuperclass
public abstract class PessoaMigracaoPO extends AuditoriaAbstractMigracaoPO{

	@Column( name = "razao_social", length = 70, nullable = false )
	private String razaoSocial;

	@Column( name = "telefone_1", length = 20, nullable = false )
	private String telefone1;

	@Column( name = "telefone_2", length = 20, nullable = true )
	private String telefone2;

	@Column( name = "telefone_3", length = 20, nullable = true )
	private String telefone3;

	@Column( name = "email", length = 50, nullable = true )
	private String email;

	@Column( name = "imagem", length = 200, nullable = true )
	private String imagem;

	@OneToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = true )
	@JoinColumn( name = "id_endereco", nullable = true )
	private EnderecoMigracaoPO endereco;

	public PessoaMigracaoPO(){}

	/**
	 * Método responsável por retornar o valor do atributo imagem.
	 * 
	 * @return String imagem - imagem a ser retornado(a).
	 */
	public String getImagem() {
		return this.imagem;
	}

	/**
	 * Método responsável por atribuir o valor ao atributo imagem.
	 * 
	 * @param String imagem - imagem a ser atribuido(a).
	 */
	public void setImagem( String imagem ) {
		this.imagem = imagem;
	}

	/**
	 * Método responsável por retornar o valor do atributo email.
	 * 
	 * @return String email - email a ser retornado(a).
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Método responsável por atribuir o valor ao atributo email.
	 * 
	 * @param String email - email a ser atribuido(a).
	 */
	public void setEmail( String email ) {
		this.email = email;
	}

	/**
	 * Método responsável por retornar o valor do atributo razaoSocial.
	 * 
	 * @return String razaoSocial - razaoSocial a ser retornado(a).
	 */
	public String getRazaoSocial() {
		return this.razaoSocial;
	}

	/**
	 * Método responsável por atribuir o valor ao atributo razaoSocial.
	 * 
	 * @param String razaoSocial - razaoSocial a ser atribuido(a).
	 */
	public void setRazaoSocial( String razaoSocial ) {
		this.razaoSocial = razaoSocial;
	}

	/**
	 * Método responsável por retornar o valor do atributo telefone1.
	 * 
	 * @return String telefone1 - telefone1 a ser retornado(a).
	 */
	public String getTelefone1() {
		return this.telefone1;
	}

	/**
	 * Método responsável por atribuir o valor ao atributo telefone1.
	 * 
	 * @param String telefone1 - telefone1 a ser atribuido(a).
	 */
	public void setTelefone1( String telefone1 ) {
		this.telefone1 = telefone1;
	}

	/**
	 * Método responsável por retornar o valor do atributo telefone2.
	 * 
	 * @return String telefone2 - telefone2 a ser retornado(a).
	 */
	public String getTelefone2() {
		return this.telefone2;
	}

	/**
	 * Método responsável por atribuir o valor ao atributo telefone2.
	 * 
	 * @param String telefone2 - telefone2 a ser atribuido(a).
	 */
	public void setTelefone2( String telefone2 ) {
		this.telefone2 = telefone2;
	}

	/**
	 * Método responsável por retornar o valor do atributo telefone3.
	 * 
	 * @return String telefone3 - telefone3 a ser retornado(a).
	 */
	public String getTelefone3() {
		return this.telefone3;
	}

	/**
	 * Método responsável por atribuir o valor ao atributo telefone3.
	 * 
	 * @param String telefone3 - telefone3 a ser atribuido(a).
	 */
	public void setTelefone3( String telefone3 ) {
		this.telefone3 = telefone3;
	}

	/**
	 * Método responsável por retornar o valor do atributo endereco.
	 * 
	 * @return EnderecoPO endereco - endereco a ser retornado(a).
	 */
	public EnderecoMigracaoPO getEndereco() {
		return this.endereco;
	}

	/**
	 * Método responsável por atribuir o valor ao atributo endereco.
	 * 
	 * @param ProjetoPO endereco - endereco a ser atribuido(a).
	 */
	public void setEndereco( EnderecoMigracaoPO endereco ) {
		this.endereco = endereco;
	}

	/**
	 * Polimorfico
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 0;
		result = ( prime * result ) + ( ( this.email == null ) ? 0 : this.email.hashCode() );
		result = ( prime * result ) + ( ( this.endereco == null ) ? 0 : this.endereco.hashCode() );
		result = ( prime * result ) + ( ( this.razaoSocial == null ) ? 0 : this.razaoSocial.hashCode() );
		result = ( prime * result ) + ( ( this.telefone1 == null ) ? 0 : this.telefone1.hashCode() );
		result = ( prime * result ) + ( ( this.telefone2 == null ) ? 0 : this.telefone2.hashCode() );
		result = ( prime * result ) + ( ( this.telefone3 == null ) ? 0 : this.telefone3.hashCode() );
		return result;
	}

	/**
	 * Polimorfico
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}

		if ( !( obj instanceof PessoaMigracaoPO ) ) {
			return false;
		}
		PessoaMigracaoPO other = (PessoaMigracaoPO) obj;
		if ( this.endereco == null ) {
			if ( other.endereco != null ) {
				return false;
			}
		} else if ( !this.endereco.equals( other.endereco ) ) {
			return false;
		}
		if ( this.email == null ) {
			if ( other.email != null ) {
				return false;
			}
		} else if ( !this.email.equals( other.email ) ) {
			return false;
		}
		if ( this.razaoSocial == null ) {
			if ( other.razaoSocial != null ) {
				return false;
			}
		} else if ( !this.razaoSocial.equals( other.razaoSocial ) ) {
			return false;
		}
		if ( this.telefone1 == null ) {
			if ( other.telefone1 != null ) {
				return false;
			}
		} else if ( !this.telefone1.equals( other.telefone1 ) ) {
			return false;
		}
		if ( this.telefone2 == null ) {
			if ( other.telefone2 != null ) {
				return false;
			}
		} else if ( !this.telefone2.equals( other.telefone2 ) ) {
			return false;
		}
		if ( this.telefone3 == null ) {
			if ( other.telefone3 != null ) {
				return false;
			}
		} else if ( !this.telefone3.equals( other.telefone3 ) ) {
			return false;
		}
		return true;
	}

	/**
	 * Polimorfico
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PessoaPO [getRazaoSocial()=" + this.getRazaoSocial() + ", getTelefone1()=" + this.getTelefone1() + ", getTelefone2()=" + this.getTelefone2() + ", getTelefone3()=" + this.getTelefone3() + ", getEndereco()=" + this.getEndereco() + ", hashCode()=" + this.hashCode() + ", getNomeUsuarioCriacao()=" + this.getNomeUsuarioCriacao() + ", getNomeUsuarioAlteracao()=" + this.getNomeUsuarioAlteracao() + ", getDataOcorrenciaCriacao()=" + this.getDataOcorrenciaCriacao() + ", getDataOcorrenciaAlteracao()=" + this.getDataOcorrenciaAlteracao() + "]";
	}

}
