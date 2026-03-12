package br.com.srcsoftware.gestao.manager.pessoa.cliente.juridico;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.srcsoftware.gestao.AuditoriaAbstractMigracaoPO;
import br.com.srcsoftware.gestao.manager.pessoa.pessoa.pessoajuridica.PessoaJuridicaMigracaoPO;
import br.com.srcsoftware.modular.manager.utilidades.Constantes;

@Entity
@Table( name = "clientesjuridico", schema = Constantes.SCHEMA_MIGRACAO, uniqueConstraints = @UniqueConstraint( columnNames = { "idPessoaJuridica" } ) )
public final class ClienteJuridicoMigracaoPO extends AuditoriaAbstractMigracaoPO{

	@OneToOne( cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.EAGER )
	@JoinColumn( name = "idPessoaJuridica", nullable = false )
	private PessoaJuridicaMigracaoPO pessoaJuridica;

	public PessoaJuridicaMigracaoPO getPessoaJuridica() {
		return this.pessoaJuridica;
	}

	public void setPessoaJuridica( PessoaJuridicaMigracaoPO pessoaJuridica ) {
		this.pessoaJuridica = pessoaJuridica;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 0;
		result = ( prime * result ) + ( ( this.pessoaJuridica == null ) ? 0 : this.pessoaJuridica.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}

		if ( !( obj instanceof ClienteJuridicoMigracaoPO ) ) {
			return false;
		}
		ClienteJuridicoMigracaoPO other = (ClienteJuridicoMigracaoPO) obj;
		if ( this.pessoaJuridica == null ) {
			if ( other.pessoaJuridica != null ) {
				return false;
			}
		} else if ( !this.pessoaJuridica.equals( other.pessoaJuridica ) ) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "ClienteJuridicoPO [\n\tpessoaJuridica=" );
		builder.append( pessoaJuridica );
		builder.append( ", \ngetNomeUsuarioCriacao()=" );
		builder.append( getNomeUsuarioCriacao() );
		builder.append( ", \ngetNomeUsuarioAlteracao()=" );
		builder.append( getNomeUsuarioAlteracao() );
		builder.append( ", \ngetDataOcorrenciaCriacao()=" );
		builder.append( getDataOcorrenciaCriacao() );
		builder.append( ", \ngetDataOcorrenciaAlteracao()=" );
		builder.append( getDataOcorrenciaAlteracao() );
		builder.append( "]\n" );
		return builder.toString();
	}

}
