package br.com.srcsoftware.gestao.manager.pessoa.cliente;

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
@Table( name = "clientes", schema = Constantes.SCHEMA_MIGRACAO, uniqueConstraints = @UniqueConstraint( columnNames = { "id_pessoa_fisica" } ) )
public final class ClienteMigracaoPO extends AuditoriaAbstractMigracaoPO{

	@OneToOne( cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.EAGER )
	@JoinColumn( name = "id_pessoa_fisica", nullable = false )
	private PessoaFisicaMigracaoPO pessoaFisica;

	@Column( name = "observacao", length = 255, nullable = true )
	private String observacao;

	public PessoaFisicaMigracaoPO getPessoaFisica() {
		return this.pessoaFisica;
	}

	public void setPessoaFisica( PessoaFisicaMigracaoPO pessoaFisica ) {
		this.pessoaFisica = pessoaFisica;
	}

	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao( String observacao ) {
		this.observacao = observacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 0;
		result = ( prime * result ) + ( ( this.observacao == null ) ? 0 : this.observacao.hashCode() );
		result = ( prime * result ) + ( ( this.pessoaFisica == null ) ? 0 : this.pessoaFisica.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}

		if ( !( obj instanceof ClienteMigracaoPO ) ) {
			return false;
		}
		ClienteMigracaoPO other = (ClienteMigracaoPO) obj;
		if ( this.observacao == null ) {
			if ( other.observacao != null ) {
				return false;
			}
		} else if ( !this.observacao.equals( other.observacao ) ) {
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
		return "ClientePO [pessoaFisica=" + this.pessoaFisica + ", observacao=" + this.observacao + ", getNomeUsuarioCriacao()=" + this.getNomeUsuarioCriacao() + ", getNomeUsuarioAlteracao()=" + this.getNomeUsuarioAlteracao() + ", getDataOcorrenciaCriacao()=" + this.getDataOcorrenciaCriacao() + ", getDataOcorrenciaAlteracao()=" + this.getDataOcorrenciaAlteracao() + "]";
	}

}
