package br.com.srcsoftware.gestao.sistema.notafiscal.rateio.centrocusto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.srcsoftware.gestao.AuditoriaAbstractMigracaoPO;
import br.com.srcsoftware.modular.manager.utilidades.Constantes;

/*notas_fiscais_rateios*/
@Entity
@Table( name = "centros_custos", schema = Constantes.SCHEMA_MIGRACAO, uniqueConstraints = @UniqueConstraint( columnNames = { "codigo" }, name = "UK_centroscustos" ) )
public class CentroCustoReceitaMigracaoPO extends AuditoriaAbstractMigracaoPO{

	@Column( nullable = true, length = 50 )
	private String codigo;

	@Column( nullable = true, length = 255 )
	private String descricao;

	@Column( nullable = true, length = 50 )
	private String tipo;

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo( String codigo ) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao( String descricao ) {
		this.descricao = descricao;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo( String tipo ) {
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 0;
		result = ( prime * result ) + ( ( this.codigo == null ) ? 0 : this.codigo.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}

		if ( !( obj instanceof CentroCustoReceitaMigracaoPO ) ) {
			return false;
		}
		CentroCustoReceitaMigracaoPO other = (CentroCustoReceitaMigracaoPO) obj;
		if ( this.codigo == null ) {
			if ( other.codigo != null ) {
				return false;
			}
		} else if ( !this.codigo.equals( other.codigo ) ) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "CentroCustoReceitaPO [\n\tcodigo=" );
		builder.append( this.codigo );
		builder.append( ", \ndescricao=" );
		builder.append( this.descricao );
		builder.append( ", \ntipo=" );
		builder.append( this.tipo );
		builder.append( ", \ngetNomeUsuarioCriacao()=" );
		builder.append( this.getNomeUsuarioCriacao() );
		builder.append( ", \ngetNomeUsuarioAlteracao()=" );
		builder.append( this.getNomeUsuarioAlteracao() );
		builder.append( ", \ngetDataOcorrenciaCriacao()=" );
		builder.append( this.getDataOcorrenciaCriacao() );
		builder.append( ", \ngetDataOcorrenciaAlteracao()=" );
		builder.append( this.getDataOcorrenciaAlteracao() );
		builder.append( "]\n" );
		return builder.toString();
	}

}
