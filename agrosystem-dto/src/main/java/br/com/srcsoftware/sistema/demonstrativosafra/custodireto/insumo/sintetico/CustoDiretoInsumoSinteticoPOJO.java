package br.com.srcsoftware.sistema.demonstrativosafra.custodireto.insumo.sintetico;

import java.math.BigDecimal;

import br.com.srcsoftware.modular.manager.utilidades.Utilidades;

public final class CustoDiretoInsumoSinteticoPOJO implements Comparable< CustoDiretoInsumoSinteticoPOJO >{

	private String setor;
	private String variedade;
	private String areaTotal;
	private BigDecimal totalPorAlqueire;
	private BigDecimal totalPorArea;
	private String informativoResultado;

	public String getVariedade() {
		return variedade;
	}

	public void setVariedade( String variedade ) {
		this.variedade = variedade;
	}

	public String getInformativoResultado() {
		return informativoResultado;
	}

	public void setInformativoResultado( String informativoResultado ) {
		this.informativoResultado = informativoResultado;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor( String setor ) {
		this.setor = setor;
	}

	public String getAreaTotal() {
		return areaTotal;
	}

	public void setAreaTotal( String areaTotal ) {
		this.areaTotal = areaTotal;
	}

	public BigDecimal getTotalPorAlqueire() {
		if ( totalPorAlqueire == null ) {
			return totalPorAlqueire;
		}
		return totalPorAlqueire.setScale( Utilidades.SCALE_CALCULOS, BigDecimal.ROUND_FLOOR );
	}

	public void setTotalPorAlqueire( BigDecimal totalPorAlqueire ) {
		this.totalPorAlqueire = totalPorAlqueire;
	}

	public String getTotalPorAlqueireToString() {
		return Utilidades.parseBigDecimal( getTotalPorAlqueire() );
	}

	public String getTotalPorAlqueireViewToString() {
		return Utilidades.parseBigDecimal( getTotalPorAlqueire().setScale( Utilidades.SCALE_EXIBICAO, BigDecimal.ROUND_HALF_EVEN ) );
	}

	public BigDecimal getTotalPorArea() {
		if ( totalPorArea == null ) {
			return totalPorArea;
		}
		return totalPorArea.setScale( Utilidades.SCALE_CALCULOS, BigDecimal.ROUND_FLOOR );
	}

	public void setTotalPorArea( BigDecimal totalPorArea ) {
		this.totalPorArea = totalPorArea;
	}

	public String getTotalPorAreaToString() {
		return Utilidades.parseBigDecimal( getTotalPorArea() );
	}

	public String getTotalPorAreaViewToString() {
		return Utilidades.parseBigDecimal( getTotalPorArea().setScale( Utilidades.SCALE_EXIBICAO, BigDecimal.ROUND_HALF_EVEN ) );
	}

	/**
	 * Polimorfico
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "CustoDiretoInsumoSinteticoPOJO [setor=" );
		builder.append( setor );
		builder.append( ", areaTotal=" );
		builder.append( areaTotal );
		builder.append( ", totalPorAlqueire=" );
		builder.append( totalPorAlqueire );
		builder.append( ", totalPorArea=" );
		builder.append( totalPorArea );
		builder.append( "]" );
		return builder.toString();
	}

	@Override
	public int compareTo( CustoDiretoInsumoSinteticoPOJO o ) {
		return getSetor().compareToIgnoreCase( o.getSetor() );
	}

}
