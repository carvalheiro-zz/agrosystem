package br.com.srcsoftware.gestao.sistema.manutencao.veiculo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.srcsoftware.gestao.AuditoriaAbstractMigracaoPO;
import br.com.srcsoftware.modular.manager.utilidades.Constantes;

@Entity
@Table( name = "veiculos", schema = Constantes.SCHEMA_MIGRACAO )
public class VeiculoMigracaoPO extends AuditoriaAbstractMigracaoPO{

	@Column( name = "codigo", length = 10, nullable = false, unique = true )
	private String codigo;

	@Column( name = "nome", length = 50, nullable = false )
	private String nome;

	@Column( name = "tipo", length = 20, nullable = false )
	private String tipo;

	@Column( name = "modelo", length = 20, nullable = false )
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

	public String getModelo() {
		return this.modelo;
	}

	public void setModelo( String modelo ) {
		this.modelo = modelo;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome( String nome ) {
		this.nome = nome;
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

		if ( !( obj instanceof VeiculoMigracaoPO ) ) {
			return false;
		}
		VeiculoMigracaoPO other = (VeiculoMigracaoPO) obj;
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
		builder.append( "VeiculoPO [\n\tcodigo=" );
		builder.append( this.codigo );
		builder.append( ", \nnome=" );
		builder.append( this.nome );
		builder.append( ", \ntipo=" );
		builder.append( this.tipo );
		builder.append( ", \nmodelo=" );
		builder.append( this.modelo );
		builder.append( ", \nanoFabricacao=" );
		builder.append( this.anoFabricacao );
		builder.append( ", \nnumeroChassis=" );
		builder.append( this.numeroChassis );
		builder.append( "]\n" );
		return builder.toString();
	}

}
