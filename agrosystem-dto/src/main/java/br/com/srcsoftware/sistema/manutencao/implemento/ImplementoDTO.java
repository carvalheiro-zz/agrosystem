package br.com.srcsoftware.sistema.manutencao.implemento;

import br.com.srcsoftware.abstracts.AuditoriaAbstractDTO;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;

public class ImplementoDTO extends AuditoriaAbstractDTO{

	private String id;
	private String codigo;
	private String nome;
	private String modelo;
	private String anoFabricacao;
	private String numeroChassis;
	private String nomeCompleto;

	public void montarNomeCompleto() {
		String codigo = Utilidades.isNuloOuVazio( this.getCodigo() ) ? "" : this.getCodigo();
		String nome = Utilidades.isNuloOuVazio( this.getNome() ) ? "" : this.getNome();
		String modelo = Utilidades.isNuloOuVazio( this.getModelo() ) ? "" : "(" + this.getModelo() + ")";
		String anoFabricacao = Utilidades.isNuloOuVazio( this.getAnoFabricacao() ) ? "" : this.getAnoFabricacao();
		String numeroChassis = Utilidades.isNuloOuVazio( this.getNumeroChassis() ) ? "" : this.getNumeroChassis();

		String valor = String.join( " ", codigo, nome, modelo, anoFabricacao, numeroChassis ).replaceAll( "null", "" ).trim();
		if ( Utilidades.isNuloOuVazio( valor ) ) {
			return;
		}
		this.setNomeCompleto( valor );
	}

	public String getNomeCompleto() {
		return this.nomeCompleto;
	}

	public void setNomeCompleto( String nomeCompleto ) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getId() {
		return this.id;
	}

	public void setId( String id ) {
		this.id = id;
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
		int result = 1;
		result = ( prime * result ) + ( ( this.anoFabricacao == null ) ? 0 : this.anoFabricacao.hashCode() );
		result = ( prime * result ) + ( ( this.codigo == null ) ? 0 : this.codigo.hashCode() );
		result = ( prime * result ) + ( ( this.id == null ) ? 0 : this.id.hashCode() );
		result = ( prime * result ) + ( ( this.modelo == null ) ? 0 : this.modelo.hashCode() );
		result = ( prime * result ) + ( ( this.nome == null ) ? 0 : this.nome.hashCode() );
		result = ( prime * result ) + ( ( this.numeroChassis == null ) ? 0 : this.numeroChassis.hashCode() );
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
		ImplementoDTO other = (ImplementoDTO) obj;
		if ( this.anoFabricacao == null ) {
			if ( other.anoFabricacao != null ) {
				return false;
			}
		} else if ( !this.anoFabricacao.equals( other.anoFabricacao ) ) {
			return false;
		}
		if ( this.codigo == null ) {
			if ( other.codigo != null ) {
				return false;
			}
		} else if ( !this.codigo.equals( other.codigo ) ) {
			return false;
		}
		if ( this.id == null ) {
			if ( other.id != null ) {
				return false;
			}
		} else if ( !this.id.equals( other.id ) ) {
			return false;
		}
		if ( this.modelo == null ) {
			if ( other.modelo != null ) {
				return false;
			}
		} else if ( !this.modelo.equals( other.modelo ) ) {
			return false;
		}
		if ( this.nome == null ) {
			if ( other.nome != null ) {
				return false;
			}
		} else if ( !this.nome.equals( other.nome ) ) {
			return false;
		}
		if ( this.numeroChassis == null ) {
			if ( other.numeroChassis != null ) {
				return false;
			}
		} else if ( !this.numeroChassis.equals( other.numeroChassis ) ) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "ImplementoDTO [\n\tcodigo=" );
		builder.append( this.codigo );
		builder.append( ",\n\tnome=" );
		builder.append( this.nome );
		builder.append( ",\n\tmodelo=" );
		builder.append( this.modelo );
		builder.append( ",\n\tanoFabricacao=" );
		builder.append( this.anoFabricacao );
		builder.append( ",\n\tnumeroChassis=" );
		builder.append( this.numeroChassis );
		builder.append( ",\n\tgetNomeUsuarioCriacao()=" );
		builder.append( this.getNomeUsuarioCriacao() );
		builder.append( ",\n\tgetNomeUsuarioAlteracao()=" );
		builder.append( this.getNomeUsuarioAlteracao() );
		builder.append( ",\n\tgetDataOcorrenciaCriacao()=" );
		builder.append( this.getDataOcorrenciaCriacao() );
		builder.append( ",\n\tgetDataOcorrenciaAlteracao()=" );
		builder.append( this.getDataOcorrenciaAlteracao() );
		builder.append( ",\n\tgetId()=" );
		builder.append( this.getId() );
		builder.append( "]\n" );
		return builder.toString();
	}

}
