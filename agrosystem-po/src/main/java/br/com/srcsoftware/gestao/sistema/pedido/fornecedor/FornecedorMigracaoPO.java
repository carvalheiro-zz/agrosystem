package br.com.srcsoftware.gestao.sistema.pedido.fornecedor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.srcsoftware.abstracts.AbstractPO;
import br.com.srcsoftware.modular.manager.utilidades.Constantes;

@Entity
@Table( name = "fornecedores", schema = Constantes.SCHEMA_MIGRACAO, uniqueConstraints = @UniqueConstraint( columnNames = { "nome", "telefone", "endereco" } ) )
public final class FornecedorMigracaoPO extends AbstractPO implements Comparable< FornecedorMigracaoPO >{

	@Column( name = "nome", nullable = false, length = 100 )
	private String nome;

	@Column( name = "telefone", nullable = true, length = 30 )
	private String telefone;

	@Column( name = "endereco", nullable = true, length = 100 )
	private String endereco;

	@Column( name = "observacao", nullable = true, length = 255 )
	private String observacao;

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
	 * Método responsável por retornar o valor do atributo telefone.
	 * 
	 * @return String telefone - telefone a ser retornado(a).
	 */
	public String getTelefone() {
		return this.telefone;
	}

	/**
	 * Método responsável por atribuir o valor ao atributo telefone.
	 * 
	 * @param String telefone - telefone a ser atribuido(a).
	 */
	public void setTelefone( String telefone ) {
		this.telefone = telefone;
	}

	/**
	 * Método responsável por retornar o valor do atributo endereco.
	 * 
	 * @return String endereco - endereco a ser retornado(a).
	 */
	public String getEndereco() {
		return this.endereco;
	}

	/**
	 * Método responsável por atribuir o valor ao atributo endereco.
	 * 
	 * @param String endereco - endereco a ser atribuido(a).
	 */
	public void setEndereco( String endereco ) {
		this.endereco = endereco;
	}

	/**
	 * Método responsável por retornar o valor do atributo observacao.
	 * 
	 * @return String observacao - observacao a ser retornado(a).
	 */
	public String getObservacao() {
		return this.observacao;
	}

	/**
	 * Método responsável por atribuir o valor ao atributo observacao.
	 * 
	 * @param String observacao - observacao a ser atribuido(a).
	 */
	public void setObservacao( String observacao ) {
		this.observacao = observacao;
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
		result = ( prime * result ) + ( ( this.endereco == null ) ? 0 : this.endereco.hashCode() );
		result = ( prime * result ) + ( ( this.nome == null ) ? 0 : this.nome.hashCode() );
		result = ( prime * result ) + ( ( this.telefone == null ) ? 0 : this.telefone.hashCode() );
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

		if ( !( obj instanceof FornecedorMigracaoPO ) ) {
			return false;
		}
		FornecedorMigracaoPO other = (FornecedorMigracaoPO) obj;
		if ( this.endereco == null ) {
			if ( other.endereco != null ) {
				return false;
			}
		} else if ( !this.endereco.equals( other.endereco ) ) {
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
	public String toString() {
		return "FornecedorPO [nome=" + this.nome + ", telefone=" + this.telefone + ", endereco=" + this.endereco + ", observacao=" + this.observacao + "]";
	}

	@Override
	public int compareTo( FornecedorMigracaoPO o ) {
		return this.getNome().compareToIgnoreCase( o.getNome() );
	}

}