package br.com.srcsoftware.gestao.manager.pessoa.pessoa.pessoajuridica;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.srcsoftware.gestao.manager.pessoa.pessoa.PessoaMigracaoPO;
import br.com.srcsoftware.modular.manager.utilidades.Constantes;

@Entity
@Table( name = "pessoas_juridicas", schema = Constantes.SCHEMA_MIGRACAO, uniqueConstraints = @UniqueConstraint( columnNames = { "cnpj" } ) )
public final class PessoaJuridicaMigracaoPO extends PessoaMigracaoPO{

	@Column( name = "cnpj", nullable = false, length = 18 )
	private String cnpj;

	@Column( name = "nome_fantasia", nullable = true, length = 50 )
	private String nomeFantasia;

	@Column( nullable = true, length = 25 )
	private String ie;

	@Column( nullable = true, length = 255 )
	private String responsavel;

	public String getIe() {
		return ie;
	}

	public void setIe( String ie ) {
		this.ie = ie;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel( String responsavel ) {
		this.responsavel = responsavel;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj( String cnpj ) {
		this.cnpj = cnpj;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia( String nomeFantasia ) {
		this.nomeFantasia = nomeFantasia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ( ( cnpj == null ) ? 0 : cnpj.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( !super.equals( obj ) )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		PessoaJuridicaMigracaoPO other = (PessoaJuridicaMigracaoPO) obj;
		if ( cnpj == null ) {
			if ( other.cnpj != null )
				return false;
		} else if ( !cnpj.equals( other.cnpj ) )
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "PessoaJuridicaPO [\n\tcnpj=" );
		builder.append( cnpj );
		builder.append( ", \nnomeFantasia=" );
		builder.append( nomeFantasia );
		builder.append( ", \nie=" );
		builder.append( ie );
		builder.append( ", \nresponsavel=" );
		builder.append( responsavel );
		builder.append( ", \ntoString()=" );
		builder.append( super.toString() );
		builder.append( "]\n" );
		return builder.toString();
	}

}
