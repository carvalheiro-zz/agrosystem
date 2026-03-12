package br.com.srcsoftware.sistema.estoque.estoque.pojo;

import java.math.BigDecimal;

import br.com.srcsoftware.modular.manager.utilidades.Utilidades;

public final class EstoquePOJO{

	private String idPoduto;
	private String nomeCompletoProduto;
	private String localizacao;
	private String quantidadeMinima;
	private String saldo;
	private String custoTotal;
	private String custoMedio;

	private String restantes;

	public EstoquePOJO( String idPoduto, String nomeCompletoProduto, String localizacao, String quantidadeMinima, String saldo, String custoTotal, String custoMedio ){
		setIdPoduto( idPoduto );
		setNomeCompletoProduto( nomeCompletoProduto );
		setLocalizacao( localizacao );
		setQuantidadeMinima( quantidadeMinima );
		setSaldo( saldo );
		setCustoTotal( custoTotal );
		setCustoMedio( custoMedio );
	}

	public String getCorAlerta() {
		BigDecimal quantidade = BigDecimal.ZERO;
		BigDecimal quantidadeMinima = BigDecimal.ZERO;

		quantidade = Utilidades.parseBigDecimal( getSaldo() );
		quantidadeMinima = Utilidades.parseBigDecimal( getQuantidadeMinima() );

		if ( quantidade.compareTo( BigDecimal.ZERO ) > 0 && quantidade.compareTo( quantidadeMinima ) <= 0 ) {
			return "warning";
		} else if ( quantidade.compareTo( BigDecimal.ZERO ) <= 0 ) {
			return "danger";
		} else {
			return "";
		}
	}

	public String getRestantes() {
		return restantes;
	}

	public void setRestantes( String restantes ) {
		this.restantes = restantes;
	}

	public String getIdPoduto() {
		return idPoduto;
	}

	public void setIdPoduto( String idPoduto ) {
		this.idPoduto = idPoduto;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao( String localizacao ) {
		this.localizacao = localizacao;
	}

	public String getCustoTotal() {
		return custoTotal;
	}

	public void setCustoTotal( String custoTotal ) {
		this.custoTotal = custoTotal;
	}

	public String getQuantidadeMinima() {
		return quantidadeMinima;
	}

	public void setQuantidadeMinima( String quantidadeMinima ) {
		this.quantidadeMinima = quantidadeMinima;
	}

	public String getCustoMedio() {
		return custoMedio;
	}

	public void setCustoMedio( String custoMedio ) {
		this.custoMedio = custoMedio;
	}

	public String getNomeCompletoProduto() {
		return nomeCompletoProduto;
	}

	public void setNomeCompletoProduto( String nomeCompletoProduto ) {
		this.nomeCompletoProduto = nomeCompletoProduto;
	}

	public String getSaldo() {
		return saldo;
	}

	public void setSaldo( String saldo ) {
		this.saldo = saldo;
	}

	public String getUnidadeMedida() {
		if ( Utilidades.isNuloOuVazio( getNomeCompletoProduto() ) ) {
			return "";
		}

		return getNomeCompletoProduto().substring( getNomeCompletoProduto().lastIndexOf( "(" ), getNomeCompletoProduto().length() );
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "nomeCompletoProduto=" );
		builder.append( nomeCompletoProduto );
		builder.append( "\tsaldo=" );
		builder.append( saldo );
		builder.append( "\n" );
		return builder.toString();
	}

}
