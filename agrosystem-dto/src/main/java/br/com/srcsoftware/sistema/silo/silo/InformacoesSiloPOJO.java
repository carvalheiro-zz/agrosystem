package br.com.srcsoftware.sistema.silo.silo;

import java.math.BigDecimal;

import br.com.srcsoftware.modular.manager.utilidades.Utilidades;

public class InformacoesSiloPOJO implements Comparable< InformacoesSiloPOJO >{

	private String silo;
	private String cultura;
	private String capacidadeSilo;
	private String saldoSilo;

	public String getEmSacas() {
		return Utilidades.parseBigDecimal( Utilidades.parseBigDecimal( this.getSaldoSilo() ).divide( new BigDecimal( "60" ), 3, BigDecimal.ROUND_FLOOR ) );
	}

	public String getCultura() {
		return cultura;
	}

	public void setCultura( String cultura ) {
		this.cultura = cultura;
	}

	public String getSilo() {
		return silo;
	}

	public void setSilo( String silo ) {
		this.silo = silo;
	}

	public String getCapacidadeSilo() {
		return capacidadeSilo;
	}

	public void setCapacidadeSilo( String capacidadeSilo ) {
		this.capacidadeSilo = capacidadeSilo;
	}

	public String getSaldoSilo() {
		return saldoSilo;
	}

	public void setSaldoSilo( String saldoSilo ) {
		this.saldoSilo = saldoSilo;
	}

	@Override
	public int compareTo( InformacoesSiloPOJO o ) {
		return this.getSilo().compareToIgnoreCase( o.getSilo() );
	}
}
