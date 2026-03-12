package br.com.srcsoftware.sistema.manutencao.veiculo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.srcsoftware.abstracts.AuditoriaAbstractPO;

@Entity
@Table( name = "sistema_veiculos", uniqueConstraints = @UniqueConstraint( columnNames = { "nomeCompleto" }, name = "UK_sistema_veiculos" ) )
public class VeiculoPO extends AuditoriaAbstractPO{

	@Column( length = 10, nullable = false, unique = true )
	private String codigo;

	@Column( length = 50, nullable = false )
	private String nome;

	@Column( length = 20, nullable = false )
	private String tipo;

	@Column( length = 20, nullable = false )
	private String modelo;

	@Column( length = 4, nullable = true )
	private String anoFabricacao;

	@Column( length = 20, nullable = true )
	private String numeroChassis;

	@Lob
	@Column( length = 500, nullable = true )
	private String nomeCompleto;

	public String getNomeCompleto() {
		return this.nomeCompleto;
	}

	public void setNomeCompleto( String nomeCompleto ) {
		this.nomeCompleto = nomeCompleto;
	}

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
		int result = 1;
		result = ( prime * result ) + ( ( this.nomeCompleto == null ) ? 0 : this.nomeCompleto.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}
		if ( obj == null ) {
			return false;
		}
		if ( !( obj instanceof VeiculoPO ) ) {
			return false;
		}
		VeiculoPO other = (VeiculoPO) obj;
		if ( this.nomeCompleto == null ) {
			if ( other.nomeCompleto != null ) {
				return false;
			}
		} else if ( !this.nomeCompleto.equals( other.nomeCompleto ) ) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "VeiculoPO [nomeCompleto=" + this.nomeCompleto + "]";
	}

}
