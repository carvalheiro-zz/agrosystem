package br.com.srcsoftware.gestao.manager.pessoa.funcionario;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.srcsoftware.gestao.AuditoriaAbstractMigracaoPO;
import br.com.srcsoftware.gestao.manager.pessoa.pessoafisica.PessoaFisicaMigracaoPO;
import br.com.srcsoftware.modular.manager.utilidades.Constantes;

@Entity
@Table( name = "funcionarios", schema = Constantes.SCHEMA_MIGRACAO, uniqueConstraints = @UniqueConstraint( columnNames = { "id_pessoa_fisica", "data_admissao" } ) )
public final class FuncionarioMigracaoPO extends AuditoriaAbstractMigracaoPO{

	/*@Column( name = "codigo", nullable = false )
	private Long codigo;*/

	@Column( name = "data_admissao", length = 10, nullable = false )
	private LocalDate dataAdmissao;

	@Column( name = "data_demissao", length = 10, nullable = true )
	private LocalDate dataDemissao;

	@Column( name = "salario", precision = 8, scale = 2, nullable = true )
	private BigDecimal salario;

	@Column( name = "comissao", precision = 8, scale = 2, nullable = true )
	private BigDecimal comissao;

	@OneToOne( cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.EAGER )
	@JoinColumn( name = "id_pessoa_fisica", nullable = false )
	private PessoaFisicaMigracaoPO pessoaFisica;

	/*public Long getCodigo() {
		return this.codigo;
	}
	
	public void setCodigo( Long codigo ) {
		this.codigo = codigo;
	}*/

	public LocalDate getDataAdmissao() {
		return this.dataAdmissao;
	}

	public void setDataAdmissao( LocalDate dataAdmissao ) {
		this.dataAdmissao = dataAdmissao;
	}

	public LocalDate getDataDemissao() {
		return this.dataDemissao;
	}

	public void setDataDemissao( LocalDate dataDemissao ) {
		this.dataDemissao = dataDemissao;
	}

	public BigDecimal getSalario() {
		return this.salario;
	}

	public void setSalario( BigDecimal salario ) {
		this.salario = salario;
	}

	public BigDecimal getComissao() {
		return this.comissao;
	}

	public void setComissao( BigDecimal comissao ) {
		this.comissao = comissao;
	}

	public PessoaFisicaMigracaoPO getPessoaFisica() {
		return this.pessoaFisica;
	}

	public void setPessoaFisica( PessoaFisicaMigracaoPO pessoaFisica ) {
		this.pessoaFisica = pessoaFisica;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 0;
		result = ( prime * result ) + ( ( this.dataAdmissao == null ) ? 0 : this.dataAdmissao.hashCode() );
		result = ( prime * result ) + ( ( this.pessoaFisica == null ) ? 0 : this.pessoaFisica.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}

		if ( this.getClass() != obj.getClass() ) {
			return false;
		}
		FuncionarioMigracaoPO other = (FuncionarioMigracaoPO) obj;
		if ( this.dataAdmissao == null ) {
			if ( other.dataAdmissao != null ) {
				return false;
			}
		} else if ( !this.dataAdmissao.equals( other.dataAdmissao ) ) {
			return false;
		}
		if ( this.pessoaFisica == null ) {
			if ( other.pessoaFisica != null ) {
				return false;
			}
		} else if ( !this.pessoaFisica.equals( other.pessoaFisica ) ) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "FuncionarioPO [dataAdmissao=" );
		builder.append( dataAdmissao );
		builder.append( ", dataDemissao=" );
		builder.append( dataDemissao );
		builder.append( ", salario=" );
		builder.append( salario );
		builder.append( ", comissao=" );
		builder.append( comissao );
		builder.append( ", pessoaFisica=" );
		builder.append( pessoaFisica );
		builder.append( ", getNomeUsuarioCriacao()=" );
		builder.append( getNomeUsuarioCriacao() );
		builder.append( ", getNomeUsuarioAlteracao()=" );
		builder.append( getNomeUsuarioAlteracao() );
		builder.append( ", getDataOcorrenciaCriacao()=" );
		builder.append( getDataOcorrenciaCriacao() );
		builder.append( ", getDataOcorrenciaAlteracao()=" );
		builder.append( getDataOcorrenciaAlteracao() );
		builder.append( "]" );
		return builder.toString();
	}
}
