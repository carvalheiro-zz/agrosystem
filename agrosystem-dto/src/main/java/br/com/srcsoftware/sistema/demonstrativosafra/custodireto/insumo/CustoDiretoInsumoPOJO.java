package br.com.srcsoftware.sistema.demonstrativosafra.custodireto.insumo;

import java.math.BigDecimal;

import br.com.srcsoftware.modular.manager.utilidades.Utilidades;

public final class CustoDiretoInsumoPOJO{

	private String nome;
	private BigDecimal custoTotal;
	private BigDecimal percentualCusto;
	private BigDecimal custoPorAlqueire;

	public String getNome() {
		return nome;
	}

	public void setNome( String nome ) {
		this.nome = nome;
	}

	public BigDecimal getCustoPorAlqueire() {
		if ( custoPorAlqueire == null ) {
			return custoPorAlqueire;
		}
		return custoPorAlqueire.setScale( Utilidades.SCALE_CALCULOS, BigDecimal.ROUND_FLOOR );
	}

	public void setCustoPorAlqueire( BigDecimal custoPorAlqueire ) {
		this.custoPorAlqueire = custoPorAlqueire;
	}

	public String getCustoPorAlqueireToString() {
		return Utilidades.parseBigDecimal( getCustoPorAlqueire() );
	}

	public String getCustoPorAlqueireViewToString() {
		return Utilidades.parseBigDecimal( getCustoPorAlqueire().setScale( Utilidades.SCALE_EXIBICAO, BigDecimal.ROUND_HALF_EVEN ) );
	}

	public BigDecimal getPercentualCusto() {
		if ( percentualCusto == null ) {
			return percentualCusto;
		}
		return percentualCusto.setScale( Utilidades.SCALE_CALCULOS, BigDecimal.ROUND_FLOOR );
	}

	public void setPercentualCusto( BigDecimal percentualCusto ) {
		this.percentualCusto = percentualCusto;
	}

	public String getPercentualCustoToString() {
		return Utilidades.parseBigDecimal( getPercentualCusto() );
	}

	public String getPercentualCustoViewToString() {
		return Utilidades.parseBigDecimal( getPercentualCusto().setScale( Utilidades.SCALE_EXIBICAO, BigDecimal.ROUND_HALF_EVEN ) );
	}

	public BigDecimal getCustoTotal() {
		if ( custoTotal == null ) {
			return custoTotal;
		}
		return custoTotal.setScale( Utilidades.SCALE_CALCULOS, BigDecimal.ROUND_FLOOR );
	}

	public void setCustoTotal( BigDecimal custoTotal ) {
		this.custoTotal = custoTotal;
	}

	public String getCustoTotalToString() {
		return Utilidades.parseBigDecimal( getCustoTotal() );
	}

	public String getCustoTotalViewToString() {
		return Utilidades.parseBigDecimal( getCustoTotal().setScale( Utilidades.SCALE_EXIBICAO, BigDecimal.ROUND_HALF_EVEN ) );
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "CustoDiretoInsumoPOJO [\n\tnome=" );
		builder.append( nome );
		builder.append( ",\n\tcustoTotal=" );
		builder.append( custoTotal );
		builder.append( "]\n" );
		return builder.toString();
	}

}
