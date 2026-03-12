package br.com.srcsoftware.gestao.sistema.manutencao.implemento;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.srcsoftware.gestao.AuditoriaAbstractMigracaoPO;
import br.com.srcsoftware.modular.manager.utilidades.Constantes;

@Entity
@Table( name = "implementos", schema = Constantes.SCHEMA_MIGRACAO )
public class ImplementoMigracaoPO extends AuditoriaAbstractMigracaoPO{

	@Column( name = "codigo", length = 20, nullable = false, unique = true )
	private String codigo;

	@Column( name = "nome", length = 100, nullable = false )
	private String nome;

	@Column( name = "modelo", length = 50, nullable = false )
	private String modelo;

	@Column( length = 4, nullable = true )
	private String anoFabricacao;

	@Column( length = 20, nullable = true )
	private String numeroChassis;

	public String getAnoFabricacao() {
		return this.anoFabricacao;
	}

	public void setAnoFabricacao( String anoFabricacao ) {
		this.anoFabricacao = anoFabricacao;
	}

	public String getNumeroChassis() {
		return this.numeroChassis;
	}

	public void setNumeroChassis( String numeroChassis ) {
		this.numeroChassis = numeroChassis;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo( String codigo ) {
		this.codigo = codigo;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome( String nome ) {
		this.nome = nome;
	}

	public String getModelo() {
		return this.modelo;
	}

	public void setModelo( String modelo ) {
		this.modelo = modelo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 0;
		result = prime * result + ( ( codigo == null ) ? 0 : codigo.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}

		if ( !( obj instanceof ImplementoMigracaoPO ) ) {
			return false;
		}
		ImplementoMigracaoPO other = (ImplementoMigracaoPO) obj;
		if ( codigo == null ) {
			if ( other.codigo != null ) {
				return false;
			}
		} else if ( !codigo.equals( other.codigo ) ) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "ImplementoPO [\n\tcodigo=" );
		builder.append( codigo );
		builder.append( ",\n\tnome=" );
		builder.append( nome );
		builder.append( ",\n\tmodelo=" );
		builder.append( modelo );
		builder.append( ",\n\tanoFabricacao=" );
		builder.append( anoFabricacao );
		builder.append( ",\n\tnumeroChassis=" );
		builder.append( numeroChassis );
		builder.append( ",\n\tgetNomeUsuarioCriacao()=" );
		builder.append( getNomeUsuarioCriacao() );
		builder.append( ",\n\tgetNomeUsuarioAlteracao()=" );
		builder.append( getNomeUsuarioAlteracao() );
		builder.append( ",\n\tgetDataOcorrenciaCriacao()=" );
		builder.append( getDataOcorrenciaCriacao() );
		builder.append( ",\n\tgetDataOcorrenciaAlteracao()=" );
		builder.append( getDataOcorrenciaAlteracao() );
		builder.append( "]\n" );
		return builder.toString();
	}

}
