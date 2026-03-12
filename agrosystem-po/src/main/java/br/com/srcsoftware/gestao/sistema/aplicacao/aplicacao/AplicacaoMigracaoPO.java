package br.com.srcsoftware.gestao.sistema.aplicacao.aplicacao;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.srcsoftware.gestao.AuditoriaAbstractMigracaoPO;
import br.com.srcsoftware.gestao.manager.pessoa.funcionario.FuncionarioMigracaoPO;
import br.com.srcsoftware.gestao.sistema.aplicacao.safra.SafraMigracaoPO;
import br.com.srcsoftware.gestao.sistema.aplicacao.setor.SetorMigracaoPO;
import br.com.srcsoftware.gestao.sistema.cultura.CulturaMigracaoPO;
import br.com.srcsoftware.gestao.sistema.produto.produto.ProdutoMigracaoPO;
import br.com.srcsoftware.modular.manager.utilidades.Constantes;

@Entity
@Table( name = "aplicacoes", schema = Constantes.SCHEMA_MIGRACAO )
public final class AplicacaoMigracaoPO extends AuditoriaAbstractMigracaoPO implements Comparable< AplicacaoMigracaoPO >{

	@Column( name = "data", length = 10, nullable = false )
	private LocalDate data;

	@Column( name = "quantidade", precision = 12, scale = 2, nullable = false )
	private BigDecimal quantidade;

	@Column( name = "custoMedio", precision = 12, scale = 2, nullable = false )
	private BigDecimal custoMedio;

	@Column( name = "custoTotal", precision = 12, scale = 2, nullable = false )
	private BigDecimal custoTotal;

	@Column( name = "tipo", length = 20, nullable = false )
	private String tipo;

	@ManyToOne( fetch = FetchType.EAGER, optional = true )
	@JoinColumn( name = "idCultura", nullable = true )
	private CulturaMigracaoPO cultura;

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idSafra", nullable = false )
	private SafraMigracaoPO safra = new SafraMigracaoPO();

	@ManyToOne( fetch = FetchType.EAGER, optional = true )
	@JoinColumn( name = "idSetor", nullable = true )
	private SetorMigracaoPO setor = new SetorMigracaoPO();

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idProduto", nullable = false )
	private ProdutoMigracaoPO produto = new ProdutoMigracaoPO();

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idAlmoxarife", nullable = false )
	private FuncionarioMigracaoPO almoxarife = new FuncionarioMigracaoPO();

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idAplicador", nullable = false )
	private FuncionarioMigracaoPO aplicador = new FuncionarioMigracaoPO();

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo( String tipo ) {
		this.tipo = tipo;
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

	public SafraMigracaoPO getSafra() {
		return this.safra;
	}

	public void setSafra( SafraMigracaoPO safra ) {
		this.safra = safra;
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

	public FuncionarioMigracaoPO getAplicador() {
		return this.aplicador;
	}

	public void setAplicador( FuncionarioMigracaoPO aplicador ) {
		this.aplicador = aplicador;
	}

	public SetorMigracaoPO getSetor() {
		return this.setor;
	}

	public void setSetor( SetorMigracaoPO setor ) {
		this.setor = setor;
	}

	public CulturaMigracaoPO getCultura() {
		return cultura;
	}

	public void setCultura( CulturaMigracaoPO cultura ) {
		this.cultura = cultura;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 0;
		result = prime * result + ( ( almoxarife == null ) ? 0 : almoxarife.hashCode() );
		result = prime * result + ( ( aplicador == null ) ? 0 : aplicador.hashCode() );
		result = prime * result + ( ( cultura == null ) ? 0 : cultura.hashCode() );
		result = prime * result + ( ( custoMedio == null ) ? 0 : custoMedio.hashCode() );
		result = prime * result + ( ( custoTotal == null ) ? 0 : custoTotal.hashCode() );
		result = prime * result + ( ( data == null ) ? 0 : data.hashCode() );
		result = prime * result + ( ( produto == null ) ? 0 : produto.hashCode() );
		result = prime * result + ( ( quantidade == null ) ? 0 : quantidade.hashCode() );
		result = prime * result + ( ( safra == null ) ? 0 : safra.hashCode() );
		result = prime * result + ( ( setor == null ) ? 0 : setor.hashCode() );
		result = prime * result + ( ( tipo == null ) ? 0 : tipo.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}

		if ( !( obj instanceof AplicacaoMigracaoPO ) ) {
			return false;
		}
		AplicacaoMigracaoPO other = (AplicacaoMigracaoPO) obj;
		if ( almoxarife == null ) {
			if ( other.almoxarife != null ) {
				return false;
			}
		} else if ( !almoxarife.equals( other.almoxarife ) ) {
			return false;
		}
		if ( aplicador == null ) {
			if ( other.aplicador != null ) {
				return false;
			}
		} else if ( !aplicador.equals( other.aplicador ) ) {
			return false;
		}
		if ( cultura == null ) {
			if ( other.cultura != null ) {
				return false;
			}
		} else if ( !cultura.equals( other.cultura ) ) {
			return false;
		}
		if ( custoMedio == null ) {
			if ( other.custoMedio != null ) {
				return false;
			}
		} else if ( !custoMedio.equals( other.custoMedio ) ) {
			return false;
		}
		if ( custoTotal == null ) {
			if ( other.custoTotal != null ) {
				return false;
			}
		} else if ( !custoTotal.equals( other.custoTotal ) ) {
			return false;
		}
		if ( data == null ) {
			if ( other.data != null ) {
				return false;
			}
		} else if ( !data.equals( other.data ) ) {
			return false;
		}
		if ( produto == null ) {
			if ( other.produto != null ) {
				return false;
			}
		} else if ( !produto.equals( other.produto ) ) {
			return false;
		}
		if ( quantidade == null ) {
			if ( other.quantidade != null ) {
				return false;
			}
		} else if ( !quantidade.equals( other.quantidade ) ) {
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
		if ( tipo == null ) {
			if ( other.tipo != null ) {
				return false;
			}
		} else if ( !tipo.equals( other.tipo ) ) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "AplicacaoPO [\n\tdata=" );
		builder.append( data );
		builder.append( ", \nquantidade=" );
		builder.append( quantidade );
		builder.append( ", \ncustoMedio=" );
		builder.append( custoMedio );
		builder.append( ", \ncustoTotal=" );
		builder.append( custoTotal );
		builder.append( ", \ntipo=" );
		builder.append( tipo );
		builder.append( ", \ncultura=" );
		builder.append( cultura );
		builder.append( ", \nsafra=" );
		builder.append( safra );
		builder.append( ", \nsetor=" );
		builder.append( setor );
		builder.append( ", \nproduto=" );
		builder.append( produto );
		builder.append( ", \nalmoxarife=" );
		builder.append( almoxarife );
		builder.append( ", \naplicador=" );
		builder.append( aplicador );
		builder.append( ", \ngetNomeUsuarioCriacao()=" );
		builder.append( getNomeUsuarioCriacao() );
		builder.append( ", \ngetNomeUsuarioAlteracao()=" );
		builder.append( getNomeUsuarioAlteracao() );
		builder.append( ", \ngetDataOcorrenciaCriacao()=" );
		builder.append( getDataOcorrenciaCriacao() );
		builder.append( ", \ngetDataOcorrenciaAlteracao()=" );
		builder.append( getDataOcorrenciaAlteracao() );
		builder.append( "]\n" );
		return builder.toString();
	}

	@Override
	public int compareTo( AplicacaoMigracaoPO o ) {
		return this.getData().compareTo( o.getData() );
	}

}
