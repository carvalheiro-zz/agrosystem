package br.com.srcsoftware.gestao.manager.pessoa.pessoa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.srcsoftware.abstracts.AbstractPO;
import br.com.srcsoftware.modular.manager.utilidades.Constantes;

/**
 * Classe que representa o modelo de objeto de persistencia do tipo Endereco.
 * 
 * @author Leandro Ulisses dos Passos <leandroulisses@outlook.com>
 * @since 13/02/2014 at 17:03:30
 * @version 1.0
 */
@Entity
@Table( name = "enderecos", schema = Constantes.SCHEMA_MIGRACAO )
public final class EnderecoMigracaoPO extends AbstractPO{

	@Column( name = "logradouro", nullable = false, length = 100 )
	private String logradouro;

	@Column( name = "numero", nullable = false, length = 5 )
	private String numero;

	@Column( name = "bairro", nullable = false, length = 100 )
	private String bairro;

	@Column( name = "cep", nullable = false, length = 12 )
	private String cep;

	@Column( name = "cidade", nullable = false, length = 50 )
	private String cidade;

	@Column( name = "estado", nullable = false, length = 2 )
	private String estado;

	@Column( name = "complemento", nullable = true, length = 50 )
	private String complemento;

	/**
	 * M�todo construtor(Vazio) respos�vel por inicializar os atributos e/ou
	 * executar qualquer ação no momento da criação da classe.
	 * 
	 * 
	 * @author Leandro Ulisses dos Passos <leandroulisses@outlook.com>
	 * @since 13/02/2014 at 17:07:03
	 * @version 1.0
	 */
	public EnderecoMigracaoPO(){}

	/**
	 * M�todo respons�vel por retornar o valor do atributo logradouro.
	 * 
	 * @return String logradouro - logradouro a ser retornado(a).
	 */
	public String getLogradouro() {
		return logradouro;
	}

	/**
	 * M�todo respons�vel por atribuir o valor ao atributo logradouro.
	 * 
	 * @param String
	 *        logradouro - logradouro a ser atribuido(a).
	 */
	public void setLogradouro( String logradouro ) {
		this.logradouro = logradouro;
	}

	/**
	 * M�todo respons�vel por retornar o valor do atributo numeroResidencia.
	 * 
	 * @return Short numeroResidencia - numeroResidencia a ser retornado(a).
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * M�todo respons�vel por atribuir o valor ao atributo numeroResidencia.
	 * 
	 * @param Short
	 *        numeroResidencia - numeroResidencia a ser atribuido(a).
	 */
	public void setNumero( String numero ) {
		this.numero = numero;
	}

	/**
	 * M�todo respons�vel por retornar o valor do atributo bairro.
	 * 
	 * @return String bairro - bairro a ser retornado(a).
	 */
	public String getBairro() {
		return bairro;
	}

	/**
	 * M�todo respons�vel por atribuir o valor ao atributo bairro.
	 * 
	 * @param String
	 *        bairro - bairro a ser atribuido(a).
	 */
	public void setBairro( String bairro ) {
		this.bairro = bairro;
	}

	/**
	 * M�todo respons�vel por retornar o valor do atributo cep.
	 * 
	 * @return String cep - cep a ser retornado(a).
	 */
	public String getCep() {
		return cep;
	}

	/**
	 * M�todo respons�vel por atribuir o valor ao atributo cep.
	 * 
	 * @param String
	 *        cep - cep a ser atribuido(a).
	 */
	public void setCep( String cep ) {
		this.cep = cep;
	}

	/**
	 * M�todo respons�vel por retornar o valor do atributo cidade.
	 * 
	 * @return String cidade - cidade a ser retornado(a).
	 */
	public String getCidade() {
		return cidade;
	}

	/**
	 * M�todo respons�vel por atribuir o valor ao atributo cidade.
	 * 
	 * @param String
	 *        cidade - cidade a ser atribuido(a).
	 */
	public void setCidade( String cidade ) {
		this.cidade = cidade;
	}

	/**
	 * M�todo respons�vel por retornar o valor do atributo estado.
	 * 
	 * @return String estado - estado a ser retornado(a).
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * M�todo respons�vel por atribuir o valor ao atributo estado.
	 * 
	 * @param String
	 *        estado - estado a ser atribuido(a).
	 */
	public void setEstado( String estado ) {
		this.estado = estado;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento( String complemento ) {
		this.complemento = complemento;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 0;
		result = prime * result + ( ( bairro == null ) ? 0 : bairro.hashCode() );
		result = prime * result + ( ( cep == null ) ? 0 : cep.hashCode() );
		result = prime * result + ( ( cidade == null ) ? 0 : cidade.hashCode() );
		result = prime * result + ( ( complemento == null ) ? 0 : complemento.hashCode() );
		result = prime * result + ( ( estado == null ) ? 0 : estado.hashCode() );
		result = prime * result + ( ( logradouro == null ) ? 0 : logradouro.hashCode() );
		result = prime * result + ( ( numero == null ) ? 0 : numero.hashCode() );
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}

		if ( !( obj instanceof EnderecoMigracaoPO ) ) {
			return false;
		}
		EnderecoMigracaoPO other = (EnderecoMigracaoPO) obj;
		if ( bairro == null ) {
			if ( other.bairro != null ) {
				return false;
			}
		} else if ( !bairro.equals( other.bairro ) ) {
			return false;
		}
		if ( cep == null ) {
			if ( other.cep != null ) {
				return false;
			}
		} else if ( !cep.equals( other.cep ) ) {
			return false;
		}
		if ( cidade == null ) {
			if ( other.cidade != null ) {
				return false;
			}
		} else if ( !cidade.equals( other.cidade ) ) {
			return false;
		}
		if ( complemento == null ) {
			if ( other.complemento != null ) {
				return false;
			}
		} else if ( !complemento.equals( other.complemento ) ) {
			return false;
		}
		if ( estado == null ) {
			if ( other.estado != null ) {
				return false;
			}
		} else if ( !estado.equals( other.estado ) ) {
			return false;
		}
		if ( logradouro == null ) {
			if ( other.logradouro != null ) {
				return false;
			}
		} else if ( !logradouro.equals( other.logradouro ) ) {
			return false;
		}
		if ( numero == null ) {
			if ( other.numero != null ) {
				return false;
			}
		} else if ( !numero.equals( other.numero ) ) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "EnderecoPO [getLogradouro()=" + getLogradouro() + ", getNumero()=" + getNumero() + ", getBairro()=" + getBairro() + ", getCep()=" + getCep() + ", getCidade()=" + getCidade() + ", getEstado()=" + getEstado() + ", getComplemento()=" + getComplemento() + ", hashCode()=" + hashCode() + "]";
	}

}
