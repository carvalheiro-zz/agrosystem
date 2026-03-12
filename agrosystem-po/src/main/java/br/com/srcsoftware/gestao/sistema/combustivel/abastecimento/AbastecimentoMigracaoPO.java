package br.com.srcsoftware.gestao.sistema.combustivel.abastecimento;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.srcsoftware.gestao.AuditoriaAbstractMigracaoPO;
import br.com.srcsoftware.gestao.manager.pessoa.funcionario.FuncionarioMigracaoPO;
import br.com.srcsoftware.gestao.sistema.manutencao.veiculo.VeiculoMigracaoPO;
import br.com.srcsoftware.gestao.sistema.produto.produto.ProdutoMigracaoPO;
import br.com.srcsoftware.modular.manager.utilidades.Constantes;

@Entity
@Table( name = "abastecimentos", schema = Constantes.SCHEMA_MIGRACAO )
public final class AbastecimentoMigracaoPO extends AuditoriaAbstractMigracaoPO implements Comparable< AbastecimentoMigracaoPO >{

	@Column( name = "data", length = 10, nullable = false )
	private LocalDate data;

	@Column( name = "quantidade", precision = 12, scale = 2, nullable = false )
	private BigDecimal quantidade;

	@Column( name = "custoMedio", precision = 12, scale = 2, nullable = false )
	private BigDecimal custoMedio;

	@Column( name = "custoTotal", precision = 12, scale = 2, nullable = false )
	private BigDecimal custoTotal;

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idProduto", nullable = false )
	private ProdutoMigracaoPO produto;

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idAlmoxarife", nullable = false )
	private FuncionarioMigracaoPO almoxarife;

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idRequerente", nullable = false )
	private FuncionarioMigracaoPO requerente;

	@ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, optional = true )
	@JoinColumn( name = "idVeiculo", nullable = true )
	private VeiculoMigracaoPO veiculo;

	public AbastecimentoMigracaoPO(){
		this.produto = new ProdutoMigracaoPO();
		this.almoxarife = new FuncionarioMigracaoPO();
		this.requerente = new FuncionarioMigracaoPO();
		this.veiculo = new VeiculoMigracaoPO();
	}

	public VeiculoMigracaoPO getVeiculo() {
		return this.veiculo;
	}

	public void setVeiculo( VeiculoMigracaoPO veiculo ) {
		this.veiculo = veiculo;
	}

	public BigDecimal getCustoMedio() {
		return this.custoMedio;
	}

	public void setCustoMedio( BigDecimal custoMedio ) {
		this.custoMedio = custoMedio;
	}

	public LocalDate getData() {
		return this.data;
	}

	public void setData( LocalDate data ) {
		this.data = data;
	}

	public BigDecimal getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade( BigDecimal quantidade ) {
		this.quantidade = quantidade;
	}

	public BigDecimal getCustoTotal() {
		return this.custoTotal;
	}

	public void setCustoTotal( BigDecimal custoTotal ) {
		this.custoTotal = custoTotal;
	}

	public ProdutoMigracaoPO getProduto() {
		return this.produto;
	}

	public void setProduto( ProdutoMigracaoPO produto ) {
		this.produto = produto;
	}

	public FuncionarioMigracaoPO getAlmoxarife() {
		return this.almoxarife;
	}

	public void setAlmoxarife( FuncionarioMigracaoPO almoxarife ) {
		this.almoxarife = almoxarife;
	}

	public FuncionarioMigracaoPO getRequerente() {
		return this.requerente;
	}

	public void setRequerente( FuncionarioMigracaoPO requerente ) {
		this.requerente = requerente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 0;
		result = ( prime * result ) + ( ( this.almoxarife == null ) ? 0 : this.almoxarife.hashCode() );
		result = ( prime * result ) + ( ( this.custoMedio == null ) ? 0 : this.custoMedio.hashCode() );
		result = ( prime * result ) + ( ( this.custoTotal == null ) ? 0 : this.custoTotal.hashCode() );
		result = ( prime * result ) + ( ( this.data == null ) ? 0 : this.data.hashCode() );
		result = ( prime * result ) + ( ( this.produto == null ) ? 0 : this.produto.hashCode() );
		result = ( prime * result ) + ( ( this.quantidade == null ) ? 0 : this.quantidade.hashCode() );
		result = ( prime * result ) + ( ( this.requerente == null ) ? 0 : this.requerente.hashCode() );
		result = ( prime * result ) + ( ( this.veiculo == null ) ? 0 : this.veiculo.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}

		if ( !( obj instanceof AbastecimentoMigracaoPO ) ) {
			return false;
		}
		AbastecimentoMigracaoPO other = (AbastecimentoMigracaoPO) obj;
		if ( this.almoxarife == null ) {
			if ( other.almoxarife != null ) {
				return false;
			}
		} else if ( !this.almoxarife.equals( other.almoxarife ) ) {
			return false;
		}
		if ( this.custoMedio == null ) {
			if ( other.custoMedio != null ) {
				return false;
			}
		} else if ( !this.custoMedio.equals( other.custoMedio ) ) {
			return false;
		}
		if ( this.custoTotal == null ) {
			if ( other.custoTotal != null ) {
				return false;
			}
		} else if ( !this.custoTotal.equals( other.custoTotal ) ) {
			return false;
		}
		if ( this.data == null ) {
			if ( other.data != null ) {
				return false;
			}
		} else if ( !this.data.equals( other.data ) ) {
			return false;
		}
		if ( this.produto == null ) {
			if ( other.produto != null ) {
				return false;
			}
		} else if ( !this.produto.equals( other.produto ) ) {
			return false;
		}
		if ( this.quantidade == null ) {
			if ( other.quantidade != null ) {
				return false;
			}
		} else if ( !this.quantidade.equals( other.quantidade ) ) {
			return false;
		}
		if ( this.requerente == null ) {
			if ( other.requerente != null ) {
				return false;
			}
		} else if ( !this.requerente.equals( other.requerente ) ) {
			return false;
		}
		if ( this.veiculo == null ) {
			if ( other.veiculo != null ) {
				return false;
			}
		} else if ( !this.veiculo.equals( other.veiculo ) ) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "ItemPO [data=" );
		builder.append( this.data );
		builder.append( ", quantidade=" );
		builder.append( this.quantidade );
		builder.append( ", custoMedio=" );
		builder.append( this.custoMedio );
		builder.append( ", custoTotal=" );
		builder.append( this.custoTotal );
		builder.append( ", produto=" );
		builder.append( this.produto );
		builder.append( ", almoxarife=" );
		builder.append( this.almoxarife );
		builder.append( ", requerente=" );
		builder.append( this.requerente );
		builder.append( ", veiculo=" );
		builder.append( this.veiculo );
		builder.append( ", getNomeUsuarioCriacao()=" );
		builder.append( this.getNomeUsuarioCriacao() );
		builder.append( ", getNomeUsuarioAlteracao()=" );
		builder.append( this.getNomeUsuarioAlteracao() );
		builder.append( ", getDataOcorrenciaCriacao()=" );
		builder.append( this.getDataOcorrenciaCriacao() );
		builder.append( ", getDataOcorrenciaAlteracao()=" );
		builder.append( this.getDataOcorrenciaAlteracao() );
		builder.append( "]" );
		return builder.toString();
	}

	@Override
	public int compareTo( AbastecimentoMigracaoPO o ) {
		return this.getData().compareTo( o.getData() );
	}

}
