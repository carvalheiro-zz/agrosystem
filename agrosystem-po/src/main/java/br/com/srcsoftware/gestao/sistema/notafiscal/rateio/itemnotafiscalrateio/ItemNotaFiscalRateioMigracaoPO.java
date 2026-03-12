package br.com.srcsoftware.gestao.sistema.notafiscal.rateio.itemnotafiscalrateio;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.srcsoftware.abstracts.AbstractPO;
import br.com.srcsoftware.gestao.sistema.aplicacao.safra.SafraMigracaoPO;
import br.com.srcsoftware.gestao.sistema.aplicacao.setor.SetorMigracaoPO;
import br.com.srcsoftware.gestao.sistema.cultura.CulturaMigracaoPO;
import br.com.srcsoftware.gestao.sistema.notafiscal.rateio.centrocusto.CentroCustoReceitaMigracaoPO;
import br.com.srcsoftware.modular.manager.utilidades.Constantes;

@Entity
@Table( name = "itens_notas_fiscais_rateios", schema = Constantes.SCHEMA_MIGRACAO )
public class ItemNotaFiscalRateioMigracaoPO extends AbstractPO{

	@Lob
	@Column( nullable = true, length = 1000 )
	private String descricao;

	@Column( precision = 16, scale = 2, nullable = false )
	private BigDecimal valor;

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
	@JoinColumn( name = "idCentroCustoReceita", nullable = false )
	private CentroCustoReceitaMigracaoPO centroCustoReceita;

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao( String descricao ) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor( BigDecimal valor ) {
		this.valor = valor;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo( String tipo ) {
		this.tipo = tipo;
	}

	public CulturaMigracaoPO getCultura() {
		return this.cultura;
	}

	public void setCultura( CulturaMigracaoPO cultura ) {
		this.cultura = cultura;
	}

	public SafraMigracaoPO getSafra() {
		return this.safra;
	}

	public void setSafra( SafraMigracaoPO safra ) {
		this.safra = safra;
	}

	public SetorMigracaoPO getSetor() {
		return this.setor;
	}

	public void setSetor( SetorMigracaoPO setor ) {
		this.setor = setor;
	}

	public CentroCustoReceitaMigracaoPO getCentroCustoReceita() {
		return this.centroCustoReceita;
	}

	public void setCentroCustoReceita( CentroCustoReceitaMigracaoPO centroCustoReceita ) {
		this.centroCustoReceita = centroCustoReceita;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 0;
		result = ( prime * result ) + ( ( this.centroCustoReceita == null ) ? 0 : this.centroCustoReceita.hashCode() );
		result = ( prime * result ) + ( ( this.cultura == null ) ? 0 : this.cultura.hashCode() );
		result = ( prime * result ) + ( ( this.descricao == null ) ? 0 : this.descricao.hashCode() );
		result = ( prime * result ) + ( ( this.safra == null ) ? 0 : this.safra.hashCode() );
		result = ( prime * result ) + ( ( this.setor == null ) ? 0 : this.setor.hashCode() );
		result = ( prime * result ) + ( ( this.tipo == null ) ? 0 : this.tipo.hashCode() );
		result = ( prime * result ) + ( ( this.valor == null ) ? 0 : this.valor.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}

		if ( !( obj instanceof ItemNotaFiscalRateioMigracaoPO ) ) {
			return false;
		}
		ItemNotaFiscalRateioMigracaoPO other = (ItemNotaFiscalRateioMigracaoPO) obj;
		if ( this.centroCustoReceita == null ) {
			if ( other.centroCustoReceita != null ) {
				return false;
			}
		} else if ( !this.centroCustoReceita.equals( other.centroCustoReceita ) ) {
			return false;
		}
		if ( this.cultura == null ) {
			if ( other.cultura != null ) {
				return false;
			}
		} else if ( !this.cultura.equals( other.cultura ) ) {
			return false;
		}
		if ( this.descricao == null ) {
			if ( other.descricao != null ) {
				return false;
			}
		} else if ( !this.descricao.equals( other.descricao ) ) {
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
		if ( this.valor == null ) {
			if ( other.valor != null ) {
				return false;
			}
		} else if ( !this.valor.equals( other.valor ) ) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "ItemNotaFiscalRateio [\n\tdescricao=" );
		builder.append( this.descricao );
		builder.append( ", \nvalor=" );
		builder.append( this.valor );
		builder.append( ", \ntipo=" );
		builder.append( this.tipo );
		builder.append( ", \ncultura=" );
		builder.append( this.cultura );
		builder.append( ", \nsafra=" );
		builder.append( this.safra );
		builder.append( ", \nsetor=" );
		builder.append( this.setor );
		builder.append( ", \ncentroCustoReceita=" );
		builder.append( this.centroCustoReceita );
		builder.append( "]\n" );
		return builder.toString();
	}

}
