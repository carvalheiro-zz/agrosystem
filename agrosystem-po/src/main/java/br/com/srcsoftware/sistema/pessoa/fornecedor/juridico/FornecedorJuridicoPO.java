package br.com.srcsoftware.sistema.pessoa.fornecedor.juridico;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.srcsoftware.abstracts.AbstractPO;

@Entity
@Table( name = "sistema_fornecedores", uniqueConstraints = @UniqueConstraint( columnNames = { "nome", "telefone", "endereco" }, name = "UK_sistema_fornecedores" ) )
public final class FornecedorJuridicoPO extends AbstractPO implements Comparable< FornecedorJuridicoPO >{

	@Column( nullable = false, length = 100 )
	private String nome;

	/*@Column( nullable = true, length = 16 )
	private String celular;
	
	@Column( nullable = true, length = 16 )
	private String fixo;*/

	@Column( nullable = true, length = 16 )
	private String telefone;

	@Column( nullable = true, length = 100 )
	private String endereco;

	@Column( nullable = true, length = 255 )
	private String observacao;

	public String getNome() {
		return this.nome;
	}

	public void setNome( String nome ) {
		this.nome = nome;
	}

	/*public String getCelular() {
		return this.celular;
	}
	
	public void setCelular( String celular ) {
		this.celular = celular;
	}
	
	public String getFixo() {
		return this.fixo;
	}
	
	public void setFixo( String fixo ) {
		this.fixo = fixo;
	}*/

	public String getEndereco() {
		return this.endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone( String telefone ) {
		this.telefone = telefone;
	}

	public void setEndereco( String endereco ) {
		this.endereco = endereco;
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
		int result = 1;
		result = ( prime * result ) + ( ( this.endereco == null ) ? 0 : this.endereco.hashCode() );
		result = ( prime * result ) + ( ( this.nome == null ) ? 0 : this.nome.hashCode() );
		result = ( prime * result ) + ( ( this.telefone == null ) ? 0 : this.telefone.hashCode() );
		/*result = ( prime * result ) + ( ( this.celular == null ) ? 0 : this.celular.hashCode() );
		result = ( prime * result ) + ( ( this.fixo == null ) ? 0 : this.fixo.hashCode() );*/
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
		if ( this.getClass() != obj.getClass() ) {
			return false;
		}
		FornecedorJuridicoPO other = (FornecedorJuridicoPO) obj;
		if ( this.endereco == null ) {
			if ( other.endereco != null ) {
				return false;
			}
		} else if ( !this.endereco.equals( other.endereco ) ) {
			return false;
		}
		if ( this.nome == null ) {
			if ( other.nome != null ) {
				return false;
			}
		} else if ( !this.nome.equals( other.nome ) ) {
			return false;
		}
		if ( this.telefone == null ) {
			if ( other.telefone != null ) {
				return false;
			}
		} else if ( !this.telefone.equals( other.telefone ) ) {
			return false;
		}
		/*if ( this.celular == null ) {
			if ( other.celular != null ) {
				return false;
			}
		} else if ( !this.celular.equals( other.celular ) ) {
			return false;
		}
		if ( this.fixo == null ) {
			if ( other.fixo != null ) {
				return false;
			}
		} else if ( !this.fixo.equals( other.fixo ) ) {
			return false;
		}*/
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "FornecedorJuridicoPO [\n\tnome=" );
		builder.append( nome );
		builder.append( ",\n\ttelefone=" );
		builder.append( telefone );
		builder.append( ",\n\tendereco=" );
		builder.append( endereco );
		builder.append( ",\n\tobservacao=" );
		builder.append( observacao );
		builder.append( ",\n\tgetIdTemp()=" );
		builder.append( getIdTemp() );
		builder.append( ",\n\tgetId()=" );
		builder.append( getId() );
		builder.append( "]\n" );
		return builder.toString();
	}

	@Override
	public int compareTo( FornecedorJuridicoPO o ) {
		return this.getNome().compareToIgnoreCase( o.getNome() );
	}

}