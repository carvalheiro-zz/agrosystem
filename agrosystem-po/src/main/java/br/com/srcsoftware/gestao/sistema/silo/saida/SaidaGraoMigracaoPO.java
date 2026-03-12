package br.com.srcsoftware.gestao.sistema.silo.saida;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.srcsoftware.gestao.AuditoriaAbstractMigracaoPO;
import br.com.srcsoftware.gestao.manager.pessoa.cliente.juridico.ClienteJuridicoMigracaoPO;
import br.com.srcsoftware.gestao.sistema.aplicacao.safra.SafraMigracaoPO;
import br.com.srcsoftware.gestao.sistema.cultura.CulturaMigracaoPO;
import br.com.srcsoftware.modular.manager.utilidades.Constantes;

@Entity
@Table( name = "sistema_saidasgrao", schema = Constantes.SCHEMA_MIGRACAO )
public final class SaidaGraoMigracaoPO extends AuditoriaAbstractMigracaoPO implements Comparable< SaidaGraoMigracaoPO >{

	@Column( length = 10, nullable = false )
	private LocalDate data;

	@Column( length = 255, nullable = true )
	private String localArmazenagem;

	@Column( precision = 12, scale = 2, nullable = false )
	private BigDecimal pesoLiquido;

	@Column( precision = 12, scale = 2, nullable = false )
	private BigDecimal valorBruto;

	@Column( precision = 12, scale = 2, nullable = false )
	private BigDecimal valorLiquido;

	@Column( precision = 12, scale = 2, nullable = false )
	private BigDecimal percentualDesconto;

	@Lob
	@Column( length = 1000, nullable = true )
	private String observacaoDesconto;

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idCultura", nullable = false )
	private CulturaMigracaoPO cultura;

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idSafra", nullable = false )
	private SafraMigracaoPO safra = new SafraMigracaoPO();

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idCliente", nullable = false )
	private ClienteJuridicoMigracaoPO cliente = new ClienteJuridicoMigracaoPO();

	public LocalDate getData() {
		return data;
	}

	public void setData( LocalDate data ) {
		this.data = data;
	}

	public String getLocalArmazenagem() {
		return localArmazenagem;
	}

	public void setLocalArmazenagem( String localArmazenagem ) {
		this.localArmazenagem = localArmazenagem;
	}

	public BigDecimal getPesoLiquido() {
		return pesoLiquido;
	}

	public void setPesoLiquido( BigDecimal pesoLiquido ) {
		this.pesoLiquido = pesoLiquido;
	}

	public BigDecimal getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto( BigDecimal valorBruto ) {
		this.valorBruto = valorBruto;
	}

	public BigDecimal getValorLiquido() {
		return valorLiquido;
	}

	public void setValorLiquido( BigDecimal valorLiquido ) {
		this.valorLiquido = valorLiquido;
	}

	public BigDecimal getPercentualDesconto() {
		return percentualDesconto;
	}

	public void setPercentualDesconto( BigDecimal percentualDesconto ) {
		this.percentualDesconto = percentualDesconto;
	}

	public String getObservacaoDesconto() {
		return observacaoDesconto;
	}

	public void setObservacaoDesconto( String observacaoDesconto ) {
		this.observacaoDesconto = observacaoDesconto;
	}

	public CulturaMigracaoPO getCultura() {
		return cultura;
	}

	public void setCultura( CulturaMigracaoPO cultura ) {
		this.cultura = cultura;
	}

	public SafraMigracaoPO getSafra() {
		return safra;
	}

	public void setSafra( SafraMigracaoPO safra ) {
		this.safra = safra;
	}

	public ClienteJuridicoMigracaoPO getCliente() {
		return cliente;
	}

	public void setCliente( ClienteJuridicoMigracaoPO cliente ) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 0;
		result = prime * result + ( ( cliente == null ) ? 0 : cliente.hashCode() );
		result = prime * result + ( ( cultura == null ) ? 0 : cultura.hashCode() );
		result = prime * result + ( ( data == null ) ? 0 : data.hashCode() );
		result = prime * result + ( ( localArmazenagem == null ) ? 0 : localArmazenagem.hashCode() );
		result = prime * result + ( ( observacaoDesconto == null ) ? 0 : observacaoDesconto.hashCode() );
		result = prime * result + ( ( percentualDesconto == null ) ? 0 : percentualDesconto.hashCode() );
		result = prime * result + ( ( pesoLiquido == null ) ? 0 : pesoLiquido.hashCode() );
		result = prime * result + ( ( safra == null ) ? 0 : safra.hashCode() );
		result = prime * result + ( ( valorBruto == null ) ? 0 : valorBruto.hashCode() );
		result = prime * result + ( ( valorLiquido == null ) ? 0 : valorLiquido.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}

		if ( !( obj instanceof SaidaGraoMigracaoPO ) ) {
			return false;
		}
		SaidaGraoMigracaoPO other = (SaidaGraoMigracaoPO) obj;
		if ( cliente == null ) {
			if ( other.cliente != null ) {
				return false;
			}
		} else if ( !cliente.equals( other.cliente ) ) {
			return false;
		}
		if ( cultura == null ) {
			if ( other.cultura != null ) {
				return false;
			}
		} else if ( !cultura.equals( other.cultura ) ) {
			return false;
		}
		if ( data == null ) {
			if ( other.data != null ) {
				return false;
			}
		} else if ( !data.equals( other.data ) ) {
			return false;
		}
		if ( localArmazenagem == null ) {
			if ( other.localArmazenagem != null ) {
				return false;
			}
		} else if ( !localArmazenagem.equals( other.localArmazenagem ) ) {
			return false;
		}
		if ( observacaoDesconto == null ) {
			if ( other.observacaoDesconto != null ) {
				return false;
			}
		} else if ( !observacaoDesconto.equals( other.observacaoDesconto ) ) {
			return false;
		}
		if ( percentualDesconto == null ) {
			if ( other.percentualDesconto != null ) {
				return false;
			}
		} else if ( !percentualDesconto.equals( other.percentualDesconto ) ) {
			return false;
		}
		if ( pesoLiquido == null ) {
			if ( other.pesoLiquido != null ) {
				return false;
			}
		} else if ( !pesoLiquido.equals( other.pesoLiquido ) ) {
			return false;
		}
		if ( safra == null ) {
			if ( other.safra != null ) {
				return false;
			}
		} else if ( !safra.equals( other.safra ) ) {
			return false;
		}
		if ( valorBruto == null ) {
			if ( other.valorBruto != null ) {
				return false;
			}
		} else if ( !valorBruto.equals( other.valorBruto ) ) {
			return false;
		}
		if ( valorLiquido == null ) {
			if ( other.valorLiquido != null ) {
				return false;
			}
		} else if ( !valorLiquido.equals( other.valorLiquido ) ) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "SaidaGraoPO [\n\tdata=" );
		builder.append( data );
		builder.append( ", \nlocalArmazenagem=" );
		builder.append( localArmazenagem );
		builder.append( ", \npesoLiquido=" );
		builder.append( pesoLiquido );
		builder.append( ", \nvalorBruto=" );
		builder.append( valorBruto );
		builder.append( ", \nvalorLiquido=" );
		builder.append( valorLiquido );
		builder.append( ", \npercentualDesconto=" );
		builder.append( percentualDesconto );
		builder.append( ", \nobservacaoDesconto=" );
		builder.append( observacaoDesconto );
		builder.append( ", \ncultura=" );
		builder.append( cultura );
		builder.append( ", \nsafra=" );
		builder.append( safra );
		builder.append( ", \ncliente=" );
		builder.append( cliente );
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
	public int compareTo( SaidaGraoMigracaoPO o ) {
		return this.getData().compareTo( o.getData() );
	}
}
