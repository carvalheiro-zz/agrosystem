package br.com.srcsoftware.gestao.manager.pessoa.pessoafisica;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.srcsoftware.gestao.manager.pessoa.pessoa.PessoaMigracaoPO;
import br.com.srcsoftware.modular.manager.utilidades.Constantes;

@Entity
@Table( name = "pessoas_fisicas", schema = Constantes.SCHEMA_MIGRACAO, uniqueConstraints = @UniqueConstraint( columnNames = { "cpf" } ) )
public final class PessoaFisicaMigracaoPO extends PessoaMigracaoPO{

	@Column( name = "cpf", nullable = false, length = 14 )
	private String cpf;

	@Column( name = "rg", length = 15, nullable = true )
	private String rg;

	@Column( name = "data_nascimento", length = 10, nullable = true )
	private LocalDate dataNascimento;

	@Column( name = "sexo", length = 9, nullable = true )
	private String sexo;

	/**
	 * Método responsável por retornar o valor do atributo cpf.
	 * 
	 * @return String cpf - cpf a ser retornado(a).
	 */
	public String getCpf() {
		return this.cpf;
	}

	/**
	 * Método responsável por atribuir o valor ao atributo cpf.
	 * 
	 * @param String cpf - cpf a ser atribuido(a).
	 */
	public void setCpf( String cpf ) {
		this.cpf = cpf;
	}

	/**
	 * Método responsável por retornar o valor do atributo rg.
	 * 
	 * @return String rg - rg a ser retornado(a).
	 */
	public String getRg() {
		return this.rg;
	}

	/**
	 * Método responsável por atribuir o valor ao atributo rg.
	 * 
	 * @param String rg - rg a ser atribuido(a).
	 */
	public void setRg( String rg ) {
		this.rg = rg;
	}

	/**
	 * Método responsável por retornar o valor do atributo dataNascimento.
	 * 
	 * @return LocalDate dataNascimento - dataNascimento a ser retornado(a).
	 */
	public LocalDate getDataNascimento() {
		return this.dataNascimento;
	}

	/**
	 * Método responsável por atribuir o valor ao atributo dataNascimento.
	 * 
	 * @param LocalDate dataNascimento - dataNascimento a ser atribuido(a).
	 */
	public void setDataNascimento( LocalDate dataNascimento ) {
		this.dataNascimento = dataNascimento;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo( String sexo ) {
		this.sexo = sexo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = ( prime * result ) + ( ( this.cpf == null ) ? 0 : this.cpf.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}
		if ( !super.equals( obj ) ) {
			return false;
		}
		if ( this.getClass() != obj.getClass() ) {
			return false;
		}
		PessoaFisicaMigracaoPO other = (PessoaFisicaMigracaoPO) obj;
		if ( this.cpf == null ) {
			if ( other.cpf != null ) {
				return false;
			}
		} else if ( !this.cpf.equals( other.cpf ) ) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "PessoaFisicaPO [getCpf()=" + this.getCpf() + ", getRg()=" + this.getRg() + ", getDataNascimento()=" + this.getDataNascimento() + ", getSexo()=" + this.getSexo() + ", hashCode()=" + this.hashCode() + "]";
	}

}
