package br.com.srcsoftware.sistema.silo.ajuste;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.srcsoftware.abstracts.AuditoriaAbstractPOComEmpresa;
import br.com.srcsoftware.modular.manager.pessoa.funcionario.FuncionarioPO;
import br.com.srcsoftware.sistema.safra.SafraPO;
import br.com.srcsoftware.sistema.safra.cultura.CulturaPO;
import br.com.srcsoftware.sistema.silo.silo.SiloPO;

/**
 * Criar uma tela para lan�amento manual no Silo para Entrada e Saida de gr�os.
 * Campos:
 * Tipo(Sobra de Classificação(+), Quebra de Classificação(-)),
 * Lan�ador,
 * Data,
 * Safra,
 * Cultura,
 * Quantidade (Kg),
 * Quantidade(sacas)
 * e Motivo.
 *
 * @author Gabriel Damiani Carvalheiro <gabriel.carvalheiro@gmail.com.br>
 * @since 22 de mar de 2021 11:36:22
 * @version 1.0
 */
@Entity
@Table( name = "sistema_ajustes" )
public class AjusteSiloPO extends AuditoriaAbstractPOComEmpresa implements Comparable< AjusteSiloPO >{

	@Column( nullable = false, length = 30 )
	private String tipo;//Sobra de Classificação(+), Quebra de Classificação(-)

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idLancador", nullable = false, foreignKey = @ForeignKey( name = "fk_lancador_ajuste" ) )
	private FuncionarioPO lancador;

	@Column( length = 10, nullable = false )
	private LocalDate data;

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idSafra", nullable = false, foreignKey = @ForeignKey( name = "fk_safra_ajuste" ) )
	private SafraPO safra;

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idCultura", nullable = false, foreignKey = @ForeignKey( name = "fk_cultura_ajuste" ) )
	private CulturaPO cultura;

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idSilo", nullable = false, foreignKey = @ForeignKey( name = "fk_silo_ajuste" ) )
	private SiloPO localArmazenagem;

	@Column( nullable = false, precision = 8, scale = 2 )
	private BigDecimal quantidade;

	@Column( nullable = true, length = 1000 )
	private String motivo;

	public SiloPO getLocalArmazenagem() {
		return localArmazenagem;
	}

	public void setLocalArmazenagem( SiloPO localArmazenagem ) {
		this.localArmazenagem = localArmazenagem;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo( String tipo ) {
		this.tipo = tipo;
	}

	public FuncionarioPO getLancador() {
		if ( lancador == null ) {
			lancador = new FuncionarioPO();
		}
		return lancador;
	}

	public void setLancador( FuncionarioPO lancador ) {
		this.lancador = lancador;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData( LocalDate data ) {
		this.data = data;
	}

	public SafraPO getSafra() {
		if ( safra == null ) {
			safra = new SafraPO();
		}
		return safra;
	}

	public void setSafra( SafraPO safra ) {
		this.safra = safra;
	}

	public CulturaPO getCultura() {
		if ( cultura == null ) {
			cultura = new CulturaPO();
		}
		return cultura;
	}

	public void setCultura( CulturaPO cultura ) {
		this.cultura = cultura;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade( BigDecimal quantidade ) {
		this.quantidade = quantidade;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo( String motivo ) {
		this.motivo = motivo;
	}

	/**
	 * Polimorfico
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( cultura == null ) ? 0 : cultura.hashCode() );
		result = prime * result + ( ( data == null ) ? 0 : data.hashCode() );
		result = prime * result + ( ( lancador == null ) ? 0 : lancador.hashCode() );
		result = prime * result + ( ( motivo == null ) ? 0 : motivo.hashCode() );
		result = prime * result + ( ( quantidade == null ) ? 0 : quantidade.hashCode() );
		result = prime * result + ( ( safra == null ) ? 0 : safra.hashCode() );
		result = prime * result + ( ( tipo == null ) ? 0 : tipo.hashCode() );
		return result;
	}

	/**
	 * Polimorfico
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}
		if ( obj == null ) {
			return false;
		}
		if ( !( obj instanceof AjusteSiloPO ) ) {
			return false;
		}
		AjusteSiloPO other = (AjusteSiloPO) obj;
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
		if ( lancador == null ) {
			if ( other.lancador != null ) {
				return false;
			}
		} else if ( !lancador.equals( other.lancador ) ) {
			return false;
		}
		if ( motivo == null ) {
			if ( other.motivo != null ) {
				return false;
			}
		} else if ( !motivo.equals( other.motivo ) ) {
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
		if ( tipo == null ) {
			if ( other.tipo != null ) {
				return false;
			}
		} else if ( !tipo.equals( other.tipo ) ) {
			return false;
		}
		return true;
	}

	/**
	 * Polimorfico
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "AjusteSiloPO [tipo=" );
		builder.append( tipo );
		builder.append( ", lancador=" );
		builder.append( lancador );
		builder.append( ", data=" );
		builder.append( data );
		builder.append( ", safra=" );
		builder.append( safra );
		builder.append( ", cultura=" );
		builder.append( cultura );
		builder.append( ", quantidade=" );
		builder.append( quantidade );
		builder.append( ", motivo=" );
		builder.append( motivo );
		builder.append( ", toString()=" );
		builder.append( super.toString() );
		builder.append( "]" );
		return builder.toString();
	}

	@Override
	public int compareTo( AjusteSiloPO o ) {
		return getData().compareTo( o.getData() );
	}
}
