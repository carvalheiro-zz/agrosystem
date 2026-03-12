package br.com.srcsoftware.sistema.aplicacao.item;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.srcsoftware.abstracts.AuditoriaAbstractPO;
import br.com.srcsoftware.modular.manager.pessoa.funcionario.FuncionarioPO;
import br.com.srcsoftware.sistema.manutencao.imovel.ImovelPO;
import br.com.srcsoftware.sistema.manutencao.implemento.ImplementoPO;
import br.com.srcsoftware.sistema.manutencao.veiculo.VeiculoPO;
import br.com.srcsoftware.sistema.pessoa.prestadorservico.PrestadorServicoPO;
import br.com.srcsoftware.sistema.produto.produto.ProdutoPO;
import br.com.srcsoftware.sistema.safra.SafraPO;
import br.com.srcsoftware.sistema.safra.cultura.CulturaPO;
import br.com.srcsoftware.sistema.safra.setor.SetorPO;

@Entity
@Table( name = "sistema_itens" )
public final class ItemPO extends AuditoriaAbstractPO implements Comparable< ItemPO >{

	@Column( length = 10, nullable = false )
	private LocalDate data;

	@Column( precision = 12, scale = 2, nullable = false )
	private BigDecimal quantidade;

	@Column( precision = 12, scale = 2, nullable = false )
	private BigDecimal custoMedio;

	@Column( precision = 12, scale = 2, nullable = false )
	private BigDecimal custoTotal;

	@Column( length = 50, nullable = true )
	private String kmHorimetro;

	@Column( length = 20, nullable = false )
	private String tipo;

	@ManyToOne( fetch = FetchType.EAGER, optional = true )
	@JoinColumn( name = "idCultura", nullable = true, foreignKey = @ForeignKey( name = "fk_cultura_itens" ) )
	private CulturaPO cultura;

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idSafra", nullable = false, foreignKey = @ForeignKey( name = "fk_safra_itens" ) )
	private SafraPO safra;

	@ManyToOne( fetch = FetchType.EAGER, optional = true )
	@JoinColumn( name = "idSetor", nullable = true, foreignKey = @ForeignKey( name = "fk_setor_itens" ) )
	private SetorPO setor;

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idProduto", nullable = false, foreignKey = @ForeignKey( name = "fk_produto_itens" ) )
	private ProdutoPO produto;

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idAlmoxarife", nullable = false, foreignKey = @ForeignKey( name = "fk_almoxarife_itens" ) )
	private FuncionarioPO almoxarife;

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idRequerente", nullable = false, foreignKey = @ForeignKey( name = "fk_requerente_itens" ) )
	private FuncionarioPO requerente;

	@ManyToOne( fetch = FetchType.EAGER, optional = true )
	@JoinColumn( name = "idPrestador", nullable = true, foreignKey = @ForeignKey( name = "fk_prestador_itens" ) )
	private PrestadorServicoPO prestador;

	@ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, optional = true )
	@JoinColumn( name = "idVeiculo", nullable = true, foreignKey = @ForeignKey( name = "fk_veiculo_itens" ) )
	private VeiculoPO veiculo;

	@ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, optional = true )
	@JoinColumn( name = "idImplemento", nullable = true, foreignKey = @ForeignKey( name = "fk_implemento_itens" ) )
	private ImplementoPO implemento;

	@ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, optional = true )
	@JoinColumn( name = "idImovel", nullable = true, foreignKey = @ForeignKey( name = "fk_imovel_itens" ) )
	private ImovelPO imovel;

	@Lob
	@Column( length = 1000, nullable = true )
	private String observacao;

	public String getKmHorimetro() {
		return kmHorimetro;
	}

	public void setKmHorimetro( String kmHorimetro ) {
		this.kmHorimetro = kmHorimetro;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao( String observacao ) {
		this.observacao = observacao;
	}

	public ItemPO(){
		this.safra = new SafraPO();
		this.setor = new SetorPO();
		this.produto = new ProdutoPO();
		this.almoxarife = new FuncionarioPO();
		this.requerente = new FuncionarioPO();
		this.veiculo = new VeiculoPO();
		this.implemento = new ImplementoPO();
		this.imovel = new ImovelPO();
	}

	public PrestadorServicoPO getPrestador() {
		return this.prestador;
	}

	public void setPrestador( PrestadorServicoPO prestador ) {
		this.prestador = prestador;
	}

	public VeiculoPO getVeiculo() {
		return this.veiculo;
	}

	public void setVeiculo( VeiculoPO veiculo ) {
		this.veiculo = veiculo;
	}

	public ImplementoPO getImplemento() {
		return this.implemento;
	}

	public void setImplemento( ImplementoPO implemento ) {
		this.implemento = implemento;
	}

	public ImovelPO getImovel() {
		return this.imovel;
	}

	public void setImovel( ImovelPO imovel ) {
		this.imovel = imovel;
	}

	public CulturaPO getCultura() {
		return this.cultura;
	}

	public void setCultura( CulturaPO cultura ) {
		this.cultura = cultura;
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

	public FuncionarioPO getRequerente() {
		return this.requerente;
	}

	public void setRequerente( FuncionarioPO requerente ) {
		this.requerente = requerente;
	}

	public SetorPO getSetor() {
		return this.setor;
	}

	public void setSetor( SetorPO setor ) {
		this.setor = setor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = ( prime * result ) + ( ( this.almoxarife == null ) ? 0 : this.almoxarife.hashCode() );
		result = ( prime * result ) + ( ( this.cultura == null ) ? 0 : this.cultura.hashCode() );
		result = ( prime * result ) + ( ( this.custoMedio == null ) ? 0 : this.custoMedio.hashCode() );
		result = ( prime * result ) + ( ( this.custoTotal == null ) ? 0 : this.custoTotal.hashCode() );
		result = ( prime * result ) + ( ( this.data == null ) ? 0 : this.data.hashCode() );
		result = ( prime * result ) + ( ( this.imovel == null ) ? 0 : this.imovel.hashCode() );
		result = ( prime * result ) + ( ( this.implemento == null ) ? 0 : this.implemento.hashCode() );
		result = ( prime * result ) + ( ( this.produto == null ) ? 0 : this.produto.hashCode() );
		result = ( prime * result ) + ( ( this.quantidade == null ) ? 0 : this.quantidade.hashCode() );
		result = ( prime * result ) + ( ( this.requerente == null ) ? 0 : this.requerente.hashCode() );
		result = ( prime * result ) + ( ( this.safra == null ) ? 0 : this.safra.hashCode() );
		result = ( prime * result ) + ( ( this.setor == null ) ? 0 : this.setor.hashCode() );
		result = ( prime * result ) + ( ( this.tipo == null ) ? 0 : this.tipo.hashCode() );
		result = ( prime * result ) + ( ( this.veiculo == null ) ? 0 : this.veiculo.hashCode() );
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
		ItemPO other = (ItemPO) obj;
		if ( this.almoxarife == null ) {
			if ( other.almoxarife != null ) {
				return false;
			}
		} else if ( !this.almoxarife.equals( other.almoxarife ) ) {
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

		if ( this.imovel == null ) {
			if ( other.imovel != null ) {
				return false;
			}
		} else if ( !this.imovel.equals( other.imovel ) ) {
			return false;
		}
		if ( this.implemento == null ) {
			if ( other.implemento != null ) {
				return false;
			}
		} else if ( !this.implemento.equals( other.implemento ) ) {
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
		builder.append( ", tipo=" );
		builder.append( this.tipo );
		builder.append( ", cultura=" );
		builder.append( this.cultura );
		builder.append( ", safra=" );
		builder.append( this.safra );
		builder.append( ", setor=" );
		builder.append( this.setor );
		builder.append( ", produto=" );
		builder.append( this.produto );
		builder.append( ", almoxarife=" );
		builder.append( this.almoxarife );
		builder.append( ", requerente=" );
		builder.append( this.requerente );
		builder.append( ", veiculo=" );
		builder.append( this.veiculo );
		builder.append( ", implemento=" );
		builder.append( this.implemento );
		builder.append( ", imovel=" );
		builder.append( this.imovel );
		builder.append( ", getNomeUsuarioCriacao()=" );
		builder.append( this.getNomeUsuarioCriacao() );
		builder.append( ", getNomeUsuarioAlteracao()=" );
		builder.append( this.getNomeUsuarioAlteracao() );
		builder.append( ", getDataOcorrenciaCriacao()=" );
		builder.append( this.getDataOcorrenciaCriacao() );
		builder.append( ", getDataOcorrenciaAlteracao()=" );
		builder.append( this.getDataOcorrenciaAlteracao() );
		builder.append( ", getId()=" );
		builder.append( this.getId() );
		builder.append( "]" );
		return builder.toString();
	}

	@Override
	public int compareTo( ItemPO o ) {
		return this.getData().compareTo( o.getData() );
	}

}
