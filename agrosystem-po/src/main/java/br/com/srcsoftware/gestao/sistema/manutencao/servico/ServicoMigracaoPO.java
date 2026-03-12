package br.com.srcsoftware.gestao.sistema.manutencao.servico;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.srcsoftware.gestao.AuditoriaAbstractMigracaoPO;
import br.com.srcsoftware.modular.manager.utilidades.Constantes;

@Entity
@Table( name = "servicos", schema = Constantes.SCHEMA_MIGRACAO, uniqueConstraints = @UniqueConstraint( columnNames = { "nome" } ) )
public class ServicoMigracaoPO extends AuditoriaAbstractMigracaoPO{

	@Column( name = "nome", length = 50, nullable = false )
	private String nome;

	@Column( name = "observacao", length = 255, nullable = true )
	private String observacao;

	public String getNome() {
		return this.nome;
	}

	public void setNome( String nome ) {
		this.nome = nome;
	}

	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao( String observacao ) {
		this.observacao = observacao;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "VeiculoPO [nome=" );
		builder.append( this.nome );
		builder.append( ", observacao=" );
		builder.append( this.observacao );
		builder.append( ", getNomeUsuarioCriacao()=" );
		builder.append( this.getNomeUsuarioCriacao() );
		builder.append( ", getNomeUsuarioAlteracao()=" );
		builder.append( this.getNomeUsuarioAlteracao() );
		builder.append( ", getDataOcorrenciaCriacao()=" );
		builder.append( this.getDataOcorrenciaCriacao() );
		builder.append( ", getDataOcorrenciaAlteracao()=" );
		builder.append( this.getDataOcorrenciaAlteracao() );
		builder.append( "]" );
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 0;
		result = ( prime * result ) + ( ( this.nome == null ) ? 0 : this.nome.hashCode() );
		result = ( prime * result ) + ( ( this.observacao == null ) ? 0 : this.observacao.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}

		if ( !( obj instanceof ServicoMigracaoPO ) ) {
			return false;
		}
		ServicoMigracaoPO other = (ServicoMigracaoPO) obj;
		if ( this.nome == null ) {
			if ( other.nome != null ) {
				return false;
			}
		} else if ( !this.nome.equals( other.nome ) ) {
			return false;
		}
		if ( this.observacao == null ) {
			if ( other.observacao != null ) {
				return false;
			}
		} else if ( !this.observacao.equals( other.observacao ) ) {
			return false;
		}
		return true;
	}

}
