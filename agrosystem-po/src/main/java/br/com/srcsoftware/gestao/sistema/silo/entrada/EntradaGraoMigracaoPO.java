package br.com.srcsoftware.gestao.sistema.silo.entrada;

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
import br.com.srcsoftware.modular.manager.utilidades.Constantes;

@Entity
@Table( name = "sistema_entradasgrao", schema = Constantes.SCHEMA_MIGRACAO )
public final class EntradaGraoMigracaoPO extends AuditoriaAbstractMigracaoPO implements Comparable< EntradaGraoMigracaoPO >{

	@Column( length = 10, nullable = false )
	private LocalDate data;

	@Column( precision = 12, scale = 2, nullable = false )
	private BigDecimal pesoBruto;

	@Column( precision = 12, scale = 2, nullable = false )
	private BigDecimal umidade;

	@Column( precision = 12, scale = 2, nullable = false )
	private BigDecimal percentualDescontoGeradoUmidade;

	@Column( precision = 12, scale = 2, nullable = false )
	private BigDecimal percentualDescontoGeradoImpureza;

	@Column( length = 255, nullable = true )
	private String localArmazenagem;

	@Column( length = 255, nullable = false )
	private String nomeMotorista;

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idCultura", nullable = false )
	private CulturaMigracaoPO cultura;

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idSafra", nullable = false )
	private SafraMigracaoPO safra = new SafraMigracaoPO();

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idSetor", nullable = false )
	private SetorMigracaoPO setor = new SetorMigracaoPO();

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idEstoquista", nullable = false )
	private FuncionarioMigracaoPO estoquista = new FuncionarioMigracaoPO();

	public LocalDate getData() {
		return data;
	}

	public void setData( LocalDate data ) {
		this.data = data;
	}

	public BigDecimal getPesoBruto() {
		return pesoBruto;
	}

	public void setPesoBruto( BigDecimal pesoBruto ) {
		this.pesoBruto = pesoBruto;
	}

	public BigDecimal getUmidade() {
		return umidade;
	}

	public void setUmidade( BigDecimal umidade ) {
		this.umidade = umidade;
	}

	public BigDecimal getPercentualDescontoGeradoUmidade() {
		return percentualDescontoGeradoUmidade;
	}

	public void setPercentualDescontoGeradoUmidade( BigDecimal percentualDescontoGeradoUmidade ) {
		this.percentualDescontoGeradoUmidade = percentualDescontoGeradoUmidade;
	}

	public BigDecimal getPercentualDescontoGeradoImpureza() {
		return percentualDescontoGeradoImpureza;
	}

	public void setPercentualDescontoGeradoImpureza( BigDecimal percentualDescontoGeradoImpureza ) {
		this.percentualDescontoGeradoImpureza = percentualDescontoGeradoImpureza;
	}

	public String getLocalArmazenagem() {
		return localArmazenagem;
	}

	public void setLocalArmazenagem( String localArmazenagem ) {
		this.localArmazenagem = localArmazenagem;
	}

	public String getNomeMotorista() {
		return nomeMotorista;
	}

	public void setNomeMotorista( String nomeMotorista ) {
		this.nomeMotorista = nomeMotorista;
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

	public SetorMigracaoPO getSetor() {
		return setor;
	}

	public void setSetor( SetorMigracaoPO setor ) {
		this.setor = setor;
	}

	public FuncionarioMigracaoPO getEstoquista() {
		return estoquista;
	}

	public void setEstoquista( FuncionarioMigracaoPO estoquista ) {
		this.estoquista = estoquista;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 0;
		result = prime * result + ( ( cultura == null ) ? 0 : cultura.hashCode() );
		result = prime * result + ( ( data == null ) ? 0 : data.hashCode() );
		result = prime * result + ( ( estoquista == null ) ? 0 : estoquista.hashCode() );
		result = prime * result + ( ( localArmazenagem == null ) ? 0 : localArmazenagem.hashCode() );
		result = prime * result + ( ( nomeMotorista == null ) ? 0 : nomeMotorista.hashCode() );
		result = prime * result + ( ( percentualDescontoGeradoImpureza == null ) ? 0 : percentualDescontoGeradoImpureza.hashCode() );
		result = prime * result + ( ( percentualDescontoGeradoUmidade == null ) ? 0 : percentualDescontoGeradoUmidade.hashCode() );
		result = prime * result + ( ( pesoBruto == null ) ? 0 : pesoBruto.hashCode() );
		result = prime * result + ( ( safra == null ) ? 0 : safra.hashCode() );
		result = prime * result + ( ( setor == null ) ? 0 : setor.hashCode() );
		result = prime * result + ( ( umidade == null ) ? 0 : umidade.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}

		if ( !( obj instanceof EntradaGraoMigracaoPO ) ) {
			return false;
		}
		EntradaGraoMigracaoPO other = (EntradaGraoMigracaoPO) obj;
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
		if ( estoquista == null ) {
			if ( other.estoquista != null ) {
				return false;
			}
		} else if ( !estoquista.equals( other.estoquista ) ) {
			return false;
		}
		if ( localArmazenagem == null ) {
			if ( other.localArmazenagem != null ) {
				return false;
			}
		} else if ( !localArmazenagem.equals( other.localArmazenagem ) ) {
			return false;
		}
		if ( nomeMotorista == null ) {
			if ( other.nomeMotorista != null ) {
				return false;
			}
		} else if ( !nomeMotorista.equals( other.nomeMotorista ) ) {
			return false;
		}
		if ( percentualDescontoGeradoImpureza == null ) {
			if ( other.percentualDescontoGeradoImpureza != null ) {
				return false;
			}
		} else if ( !percentualDescontoGeradoImpureza.equals( other.percentualDescontoGeradoImpureza ) ) {
			return false;
		}
		if ( percentualDescontoGeradoUmidade == null ) {
			if ( other.percentualDescontoGeradoUmidade != null ) {
				return false;
			}
		} else if ( !percentualDescontoGeradoUmidade.equals( other.percentualDescontoGeradoUmidade ) ) {
			return false;
		}
		if ( pesoBruto == null ) {
			if ( other.pesoBruto != null ) {
				return false;
			}
		} else if ( !pesoBruto.equals( other.pesoBruto ) ) {
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
		if ( umidade == null ) {
			if ( other.umidade != null ) {
				return false;
			}
		} else if ( !umidade.equals( other.umidade ) ) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "EntradaGraoPO [\n\tdata=" );
		builder.append( data );
		builder.append( ", \npesoBruto=" );
		builder.append( pesoBruto );
		builder.append( ", \numidade=" );
		builder.append( umidade );
		builder.append( ", \npercentualDescontoGeradoUmidade=" );
		builder.append( percentualDescontoGeradoUmidade );
		builder.append( ", \npercentualDescontoGeradoImpureza=" );
		builder.append( percentualDescontoGeradoImpureza );
		builder.append( ", \nlocalArmazenagem=" );
		builder.append( localArmazenagem );
		builder.append( ", \nnomeMotorista=" );
		builder.append( nomeMotorista );
		builder.append( ", \ncultura=" );
		builder.append( cultura );
		builder.append( ", \nsafra=" );
		builder.append( safra );
		builder.append( ", \nsetor=" );
		builder.append( setor );
		builder.append( ", \nestoquista=" );
		builder.append( estoquista );
		builder.append( ", \ngetNomeUsuarioCriacao()=" );
		builder.append( getNomeUsuarioCriacao() );
		builder.append( ", \ngetNomeUsuarioAlteracao()=" );
		builder.append( getNomeUsuarioAlteracao() );
		builder.append( ", \ngetDataOcorrenciaCriacao()=" );
		builder.append( getDataOcorrenciaCriacao() );
		builder.append( ", \ngetDataOcorrenciaAlteracao()=" );
		builder.append( getDataOcorrenciaAlteracao() );
		builder.append( ", \ngetIdTemp()=" );
		builder.append( getIdTemp() );
		builder.append( "]\n" );
		return builder.toString();
	}

	@Override
	public int compareTo( EntradaGraoMigracaoPO o ) {
		return this.getData().compareTo( o.getData() );
	}

}
