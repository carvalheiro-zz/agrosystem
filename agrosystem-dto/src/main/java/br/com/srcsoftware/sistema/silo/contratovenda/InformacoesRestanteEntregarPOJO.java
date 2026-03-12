package br.com.srcsoftware.sistema.silo.contratovenda;

import java.math.BigDecimal;

import br.com.srcsoftware.modular.manager.utilidades.Utilidades;

public class InformacoesRestanteEntregarPOJO implements Comparable< InformacoesRestanteEntregarPOJO >{

	private String cultura;
	private String quantidadeProduzida;
	private String quantidadeVendida;
	private String quantidadeEntregue;
	private String quantidadeRestante;
	private String saldo;

	private String quantidadeEntregar;

	public String getProduzidaEmSacas() {
		return Utilidades.parseBigDecimal( Utilidades.parseBigDecimal( this.getQuantidadeProduzida() ).divide( new BigDecimal( "60" ), 3, BigDecimal.ROUND_FLOOR ) );
	}

	public String getVendidaEmSacas() {
		return Utilidades.parseBigDecimal( Utilidades.parseBigDecimal( this.getQuantidadeVendida() ).divide( new BigDecimal( "60" ), 3, BigDecimal.ROUND_FLOOR ) );
	}

	public String getEntregueEmSacas() {
		return Utilidades.parseBigDecimal( Utilidades.parseBigDecimal( this.getQuantidadeEntregue() ).divide( new BigDecimal( "60" ), 3, BigDecimal.ROUND_FLOOR ) );
	}

	public String getRestanteEmSacas() {
		return Utilidades.parseBigDecimal( Utilidades.parseBigDecimal( this.getQuantidadeRestante() ).divide( new BigDecimal( "60" ), 3, BigDecimal.ROUND_FLOOR ) );
	}

	public String getSaldoEmSacas() {
		return Utilidades.parseBigDecimal( Utilidades.parseBigDecimal( this.getSaldo() ).divide( new BigDecimal( "60" ), 3, BigDecimal.ROUND_FLOOR ) );
	}

	public String getEmSacas() {
		return Utilidades.parseBigDecimal( Utilidades.parseBigDecimal( this.getQuantidadeEntregar() ).divide( new BigDecimal( "60" ), 3, BigDecimal.ROUND_FLOOR ) );
	}

	public String getSaldo() {
		return saldo;
	}

	public void setSaldo( String saldo ) {
		this.saldo = saldo;
	}

	public String getQuantidadeProduzida() {
		return quantidadeProduzida;
	}

	public void setQuantidadeProduzida( String quantidadeProduzida ) {
		this.quantidadeProduzida = quantidadeProduzida;
	}

	public String getQuantidadeVendida() {
		return quantidadeVendida;
	}

	public void setQuantidadeVendida( String quantidadeVendida ) {
		this.quantidadeVendida = quantidadeVendida;
	}

	public String getQuantidadeEntregue() {
		return quantidadeEntregue;
	}

	public void setQuantidadeEntregue( String quantidadeEntregue ) {
		this.quantidadeEntregue = quantidadeEntregue;
	}

	public String getQuantidadeRestante() {
		return quantidadeRestante;
	}

	public void setQuantidadeRestante( String quantidadeRestante ) {
		this.quantidadeRestante = quantidadeRestante;
	}

	public String getCultura() {
		return cultura;
	}

	public void setCultura( String cultura ) {
		this.cultura = cultura;
	}

	public String getQuantidadeEntregar() {
		return quantidadeEntregar;
	}

	public void setQuantidadeEntregar( String quantidadeEntregar ) {
		this.quantidadeEntregar = quantidadeEntregar;
	}

	/**
	 * Polimorfico
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "InformacoesRestanteEntregarPOJO [cultura=" );
		builder.append( cultura );
		builder.append( ", quantidadeProduzida=" );
		builder.append( quantidadeProduzida );
		builder.append( ", quantidadeVendida=" );
		builder.append( quantidadeVendida );
		builder.append( ", quantidadeEntregue=" );
		builder.append( quantidadeEntregue );
		builder.append( ", quantidadeRestante=" );
		builder.append( quantidadeRestante );
		builder.append( ", saldo=" );
		builder.append( saldo );
		builder.append( "]" );
		return builder.toString();
	}

	@Override
	public int compareTo( InformacoesRestanteEntregarPOJO o ) {
		return this.getCultura().compareToIgnoreCase( o.getCultura() );
	}
}
