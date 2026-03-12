package br.com.srcsoftware.sistema.estoque.estoque;

import java.math.BigDecimal;

import br.com.srcsoftware.abstracts.AbstractDTO;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.produto.produto.ProdutoDTO;

public final class EstoqueDTO extends AbstractDTO implements Comparable< EstoqueDTO >{

	private static final long serialVersionUID = 4231347359937638847L;

	public static final String TIPO_MOVIMENTO_ENTRADA = "entrada";
	public static final String TIPO_MOVIMENTO_SAIDA = "saida";
	public static final String TIPO_MOVIMENTO_EXCLUSAO_ENTRADA = "entradaExcluida";
	public static final String TIPO_MOVIMENTO_EXCLUSAO_SAIDA = "saidaExcluida";
	public static final String TIPO_MOVIMENTO_ALTERACAO_ENTRADA = "alteracaoEntrada";
	public static final String TIPO_MOVIMENTO_ALTERACAO_SAIDA = "alteracaoSaida";

	private String id;
	private String quantidade;
	private String custoTotal;
	private ProdutoDTO produto;

	private String restantes;

	public String getCorAlertaEstoqueMinimo() {
		BigDecimal quantidade = BigDecimal.ZERO;
		BigDecimal quantidadeMinima = BigDecimal.ZERO;

		quantidade = Utilidades.parseBigDecimal( this.getQuantidade() );
		quantidadeMinima = Utilidades.parseBigDecimal( this.getProduto().getQuantidadeMinimaEstoque() );

		if ( quantidade.compareTo( quantidadeMinima ) <= 0 ) {
			return "danger";
		} else {
			return "";
		}
	}

	public String getCustoMedio() {
		BigDecimal quantidade = BigDecimal.ZERO;
		BigDecimal custoTotal = BigDecimal.ZERO;

		quantidade = Utilidades.parseBigDecimal( this.getQuantidade() );
		custoTotal = Utilidades.parseBigDecimal( this.getCustoTotal() );

		/** Se a quantidade no estoque for 0 */
		if ( quantidade.compareTo( BigDecimal.ZERO ) != 0 ) {
			return Utilidades.parseBigDecimal( custoTotal.divide( quantidade, 2, BigDecimal.ROUND_HALF_EVEN ) );
		}

		return "0.0";
	}

	public String getId() {
		return id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public String getRestantes() {
		return this.restantes;
	}

	public void setRestantes( String restantes ) {
		this.restantes = restantes;
	}

	public String getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade( String quantidade ) {
		this.quantidade = quantidade;
	}

	public String getCustoTotal() {
		return this.custoTotal;
	}

	public void setCustoTotal( String custoTotal ) {
		this.custoTotal = custoTotal;
	}

	public ProdutoDTO getProduto() {
		if ( this.produto == null ) {
			this.produto = new ProdutoDTO();
		}
		return this.produto;
	}

	public void setProduto( ProdutoDTO produto ) {
		this.produto = produto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( produto == null ) ? 0 : produto.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		EstoqueDTO other = (EstoqueDTO) obj;
		if ( produto == null ) {
			if ( other.produto != null )
				return false;
		} else if ( !produto.equals( other.produto ) )
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EstoqueDTO [quantidade=" + this.quantidade + ", custoTotal=" + this.custoTotal + ", produto=" + this.produto + ", getId()=" + this.getId() + "]";
	}

	@Override
	public int compareTo( EstoqueDTO o ) {
		return this.getProduto().getNome().compareToIgnoreCase( o.getProduto().getNome() );
	}
}