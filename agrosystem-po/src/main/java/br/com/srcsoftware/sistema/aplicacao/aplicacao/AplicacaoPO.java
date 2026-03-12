package br.com.srcsoftware.sistema.aplicacao.aplicacao;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.srcsoftware.abstracts.AuditoriaAbstractPO;
import br.com.srcsoftware.modular.manager.pessoa.funcionario.FuncionarioPO;
import br.com.srcsoftware.sistema.pessoa.prestadorservico.PrestadorServicoPO;
import br.com.srcsoftware.sistema.produto.produto.ProdutoPO;
import br.com.srcsoftware.sistema.safra.SafraPO;
import br.com.srcsoftware.sistema.safra.cultura.CulturaPO;
import br.com.srcsoftware.sistema.safra.setor.SetorPO;

@Entity
@Table( name = "sistema_aplicacoes" )
public final class AplicacaoPO extends AuditoriaAbstractPO implements Comparable< AplicacaoPO >{

	@Column( length = 10, nullable = false )
	private LocalDate data;

	@Column( precision = 12, scale = 2, nullable = false )
	private BigDecimal quantidade;

	@Column( precision = 12, scale = 2, nullable = false )
	private BigDecimal custoMedio;

	@Column( precision = 12, scale = 2, nullable = false )
	private BigDecimal custoTotal;

	@Column( length = 20, nullable = false )
	private String tipo;

	@ManyToOne( fetch = FetchType.EAGER, optional = true )
	@JoinColumn( name = "idCultura", nullable = true, foreignKey = @ForeignKey( name = "fk_cultura_aplicacoes" ) )
	private CulturaPO cultura;

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idSafra", nullable = false, foreignKey = @ForeignKey( name = "fk_safra_aplicacoes" ) )
	private SafraPO safra = new SafraPO();

	@ManyToOne( fetch = FetchType.EAGER, optional = true )
	@JoinColumn( name = "idSetor", nullable = true, foreignKey = @ForeignKey( name = "fk_setor_aplicacoes" ) )
	private SetorPO setor = new SetorPO();

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idProduto", nullable = false, foreignKey = @ForeignKey( name = "fk_produto_aplicacoes" ) )
	private ProdutoPO produto = new ProdutoPO();

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idAlmoxarife", nullable = false, foreignKey = @ForeignKey( name = "fk_almoxarife_aplicacoes" ) )
	private FuncionarioPO almoxarife = new FuncionarioPO();

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idAplicador", nullable = false, foreignKey = @ForeignKey( name = "fk_aplicador_aplicacoes" ) )
	private FuncionarioPO aplicador = new FuncionarioPO();

	@ManyToOne( fetch = FetchType.EAGER, optional = true )
	@JoinColumn( name = "idPrestador", nullable = true, foreignKey = @ForeignKey( name = "fk_prestador_aplicacoes" ) )
	private PrestadorServicoPO prestador;

	public PrestadorServicoPO getPrestador() {
		return this.prestador;
	}

	public void setPrestador( PrestadorServicoPO prestador ) {
		this.prestador = prestador;
	}

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

	public SafraPO getSafra() {
		return this.safra;
	}

	public void setSafra( SafraPO safra ) {
		this.safra = safra;
	}

	public ProdutoPO getProduto() {
		return this.produto;
	}

	public void setProduto( ProdutoPO produto ) {
		this.produto = produto;
	}

	public FuncionarioPO getAlmoxarife() {
		return this.almoxarife;
	}

	public void setAlmoxarife( FuncionarioPO almoxarife ) {
		this.almoxarife = almoxarife;
	}

	public FuncionarioPO getAplicador() {
		return this.aplicador;
	}

	public void setAplicador( FuncionarioPO aplicador ) {
		this.aplicador = aplicador;
	}

	public SetorPO getSetor() {
		return this.setor;
	}

	public void setSetor( SetorPO setor ) {
		this.setor = setor;
	}

	public CulturaPO getCultura() {
		return this.cultura;
	}

	public void setCultura( CulturaPO cultura ) {
		this.cultura = cultura;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = ( prime * result ) + ( ( this.almoxarife == null ) ? 0 : this.almoxarife.hashCode() );
		result = ( prime * result ) + ( ( this.aplicador == null ) ? 0 : this.aplicador.hashCode() );
		result = ( prime * result ) + ( ( this.cultura == null ) ? 0 : this.cultura.hashCode() );
		result = ( prime * result ) + ( ( this.custoMedio == null ) ? 0 : this.custoMedio.hashCode() );
		result = ( prime * result ) + ( ( this.custoTotal == null ) ? 0 : this.custoTotal.hashCode() );
		result = ( prime * result ) + ( ( this.data == null ) ? 0 : this.data.hashCode() );
		result = ( prime * result ) + ( ( this.produto == null ) ? 0 : this.produto.hashCode() );
		result = ( prime * result ) + ( ( this.quantidade == null ) ? 0 : this.quantidade.hashCode() );
		result = ( prime * result ) + ( ( this.safra == null ) ? 0 : this.safra.hashCode() );
		result = ( prime * result ) + ( ( this.setor == null ) ? 0 : this.setor.hashCode() );
		result = ( prime * result ) + ( ( this.tipo == null ) ? 0 : this.tipo.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}
		if ( obj == null ) {
			return false;
		}
		if ( this.getClass() != obj.getClass() ) {
			return false;
		}
		AplicacaoPO other = (AplicacaoPO) obj;
		if ( this.almoxarife == null ) {
			if ( other.almoxarife != null ) {
				return false;
			}
		} else if ( !this.almoxarife.equals( other.almoxarife ) ) {
			return false;
		}
		if ( this.aplicador == null ) {
			if ( other.aplicador != null ) {
				return false;
			}
		} else if ( !this.aplicador.equals( other.aplicador ) ) {
			return false;
		}
		if ( this.cultura == null ) {
			if ( other.cultura != null ) {
				return false;
			}
		} else if ( !this.cultura.equals( other.cultura ) ) {
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
		if ( this.safra == null ) {
			if ( other.safra != null ) {
				return false;
			}
		} else if ( !this.safra.equals( other.safra ) ) {
			return false;
		}
		if ( this.setor == null ) {
			if ( other.setor != null ) {
				return false;
			}
		} else if ( !this.setor.equals( other.setor ) ) {
			return false;
		}
		if ( this.tipo == null ) {
			if ( other.tipo != null ) {
				return false;
			}
		} else if ( !this.tipo.equals( other.tipo ) ) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "AplicacaoPO [\n\tdata=" );
		builder.append( this.data );
		builder.append( ", \nquantidade=" );
		builder.append( this.quantidade );
		builder.append( ", \ncustoMedio=" );
		builder.append( this.custoMedio );
		builder.append( ", \ncustoTotal=" );
		builder.append( this.custoTotal );
		builder.append( ", \ntipo=" );
		builder.append( this.tipo );
		builder.append( ", \ncultura=" );
		builder.append( this.cultura );
		builder.append( ", \nsafra=" );
		builder.append( this.safra );
		builder.append( ", \nsetor=" );
		builder.append( this.setor );
		builder.append( ", \nproduto=" );
		builder.append( this.produto );
		builder.append( ", \nalmoxarife=" );
		builder.append( this.almoxarife );
		builder.append( ", \naplicador=" );
		builder.append( this.aplicador );
		builder.append( ", \ngetNomeUsuarioCriacao()=" );
		builder.append( this.getNomeUsuarioCriacao() );
		builder.append( ", \ngetNomeUsuarioAlteracao()=" );
		builder.append( this.getNomeUsuarioAlteracao() );
		builder.append( ", \ngetDataOcorrenciaCriacao()=" );
		builder.append( this.getDataOcorrenciaCriacao() );
		builder.append( ", \ngetDataOcorrenciaAlteracao()=" );
		builder.append( this.getDataOcorrenciaAlteracao() );
		builder.append( ", \ngetId()=" );
		builder.append( this.getId() );
		builder.append( "]\n" );
		return builder.toString();
	}

	@Override
	public int compareTo( AplicacaoPO o ) {
		return this.getData().compareTo( o.getData() );
	}

}
