package br.com.srcsoftware.sistema.safra.relatorios;

import java.math.BigDecimal;

import br.com.srcsoftware.modular.manager.utilidades.Utilidades;

public final class RelacaoSafraSetorCulturaVariedadePOJO{

	private String safra;
	private String setor;
	private String variedade;
	private String cultura;
	private BigDecimal areaAlqueire;

	public String getSafra() {
		return safra;
	}

	public void setSafra( String safra ) {
		this.safra = safra;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor( String setor ) {
		this.setor = setor;
	}

	public String getVariedade() {
		return variedade;
	}

	public void setVariedade( String variedade ) {
		this.variedade = variedade;
	}

	public String getCultura() {
		return cultura;
	}

	public void setCultura( String cultura ) {
		this.cultura = cultura;
	}

	public BigDecimal getAreaAlqueire() {
		if ( areaAlqueire == null ) {
			return areaAlqueire;
		}
		return areaAlqueire.setScale( Utilidades.SCALE_CALCULOS, BigDecimal.ROUND_FLOOR );
	}

	public void setAreaAlqueire( BigDecimal areaAlqueire ) {
		this.areaAlqueire = areaAlqueire;
	}

	public String getAreaAlqueireToString() {
		return Utilidades.parseBigDecimal( getAreaAlqueire() );
	}

	public String getAreaAlqueireViewToString() {
		return Utilidades.parseBigDecimal( getAreaAlqueire().setScale( Utilidades.SCALE_EXIBICAO, BigDecimal.ROUND_HALF_EVEN ) );
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
		result = prime * result + ( ( areaAlqueire == null ) ? 0 : areaAlqueire.hashCode() );
		result = prime * result + ( ( cultura == null ) ? 0 : cultura.hashCode() );
		result = prime * result + ( ( safra == null ) ? 0 : safra.hashCode() );
		result = prime * result + ( ( setor == null ) ? 0 : setor.hashCode() );
		result = prime * result + ( ( variedade == null ) ? 0 : variedade.hashCode() );
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
		if ( !( obj instanceof RelacaoSafraSetorCulturaVariedadePOJO ) ) {
			return false;
		}
		RelacaoSafraSetorCulturaVariedadePOJO other = (RelacaoSafraSetorCulturaVariedadePOJO) obj;
		if ( areaAlqueire == null ) {
			if ( other.areaAlqueire != null ) {
				return false;
			}
		} else if ( !areaAlqueire.equals( other.areaAlqueire ) ) {
			return false;
		}
		if ( cultura == null ) {
			if ( other.cultura != null ) {
				return false;
			}
		} else if ( !cultura.equals( other.cultura ) ) {
			return false;
		}
		if ( safra == null ) {
			if ( other.safra != null ) {
				return false;
			}
		} else if ( !safra.equals( other.safra ) ) {
			return false;
		}
		if ( setor == null ) {
			if ( other.setor != null ) {
				return false;
			}
		} else if ( !setor.equals( other.setor ) ) {
			return false;
		}
		if ( variedade == null ) {
			if ( other.variedade != null ) {
				return false;
			}
		} else if ( !variedade.equals( other.variedade ) ) {
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
		builder.append( "RelacaoSafraSetorCulturaVariedadePOJO [safra=" );
		builder.append( safra );
		builder.append( ", setor=" );
		builder.append( setor );
		builder.append( ", variedade=" );
		builder.append( variedade );
		builder.append( ", cultura=" );
		builder.append( cultura );
		builder.append( ", areaAlqueire=" );
		builder.append( areaAlqueire );
		builder.append( "]" );
		return builder.toString();
	}

}
