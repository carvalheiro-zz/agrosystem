package br.com.srcsoftware.sistema.pessoa.funcionario.ferias;

import br.com.srcsoftware.abstracts.AuditoriaAbstractDTOComEmpresa;
import br.com.srcsoftware.modular.manager.pessoa.funcionario.FuncionarioDTO;

public class FeriasDTO extends AuditoriaAbstractDTOComEmpresa implements Comparable< FeriasDTO >{

	// Adquirida / Cumprida / Vendida
	public static final String TIPO_ADQUIRIDA = "Adquirida";
	public static final String TIPO_CUMPRIDA = "Cumprida";
	public static final String TIPO_VENDIDA = "Vendida";

	private String id;
	private FuncionarioDTO colaborador;
	private String data;
	private String dataCumprimentoTermino;
	private String quantidadeHoras;
	private String tipo; // Adquirida / Cumprida / Vendida

	public String getId() {
		return id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo( String tipo ) {
		this.tipo = tipo;
	}

	public FuncionarioDTO getColaborador() {
		if ( colaborador == null ) {
			colaborador = new FuncionarioDTO();
		}
		return colaborador;
	}

	public void setColaborador( FuncionarioDTO colaborador ) {
		this.colaborador = colaborador;
	}

	public String getData() {
		return data;
	}

	public void setData( String data ) {
		this.data = data;
	}

	public String getQuantidadeHoras() {
		return quantidadeHoras;
	}

	public void setQuantidadeHoras( String quantidadeHoras ) {
		this.quantidadeHoras = quantidadeHoras;
	}

	public String getDataCumprimentoTermino() {
		return dataCumprimentoTermino;
	}

	public void setDataCumprimentoTermino( String dataCumprimentoTermino ) {
		this.dataCumprimentoTermino = dataCumprimentoTermino;
	}

	/**
	 * Polimorfico
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( colaborador == null ) ? 0 : colaborador.hashCode() );
		result = prime * result + ( ( data == null ) ? 0 : data.hashCode() );
		result = prime * result + ( ( dataCumprimentoTermino == null ) ? 0 : dataCumprimentoTermino.hashCode() );
		result = prime * result + ( ( quantidadeHoras == null ) ? 0 : quantidadeHoras.hashCode() );
		result = prime * result + ( ( tipo == null ) ? 0 : tipo.hashCode() );
		return result;
	}

	/**
	 * Polimorfico
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}
		if ( obj == null ) {
			return false;
		}
		if ( !( obj instanceof FeriasDTO ) ) {
			return false;
		}
		FeriasDTO other = (FeriasDTO) obj;
		if ( colaborador == null ) {
			if ( other.colaborador != null ) {
				return false;
			}
		} else if ( !colaborador.equals( other.colaborador ) ) {
			return false;
		}
		if ( data == null ) {
			if ( other.data != null ) {
				return false;
			}
		} else if ( !data.equals( other.data ) ) {
			return false;
		}
		if ( dataCumprimentoTermino == null ) {
			if ( other.dataCumprimentoTermino != null ) {
				return false;
			}
		} else if ( !dataCumprimentoTermino.equals( other.dataCumprimentoTermino ) ) {
			return false;
		}
		if ( quantidadeHoras == null ) {
			if ( other.quantidadeHoras != null ) {
				return false;
			}
		} else if ( !quantidadeHoras.equals( other.quantidadeHoras ) ) {
			return false;
		}
		if ( tipo == null ) {
			if ( other.tipo != null ) {
				return false;
			}
		} else if ( !tipo.equals( other.tipo ) ) {
			return false;
		}
		return true;
	}

	/**
	 * Polimorfico
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "FeriasDTO [colaborador=" );
		builder.append( colaborador );
		builder.append( ", data=" );
		builder.append( data );
		builder.append( ", dataCumprimentoTermino=" );
		builder.append( dataCumprimentoTermino );
		builder.append( ", quantidadeHoras=" );
		builder.append( quantidadeHoras );
		builder.append( ", tipo=" );
		builder.append( tipo );
		builder.append( ", toString()=" );
		builder.append( super.toString() );
		builder.append( "]" );
		return builder.toString();
	}

	@Override
	public int compareTo( FeriasDTO o ) {
		return this.getData().compareTo( o.getData() );
	}

}
