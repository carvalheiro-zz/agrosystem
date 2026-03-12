package br.com.srcsoftware.sistema.silo.ajuste;

import java.math.BigDecimal;

import br.com.srcsoftware.abstracts.AuditoriaAbstractDTOComEmpresa;
import br.com.srcsoftware.modular.manager.pessoa.funcionario.FuncionarioDTO;
import br.com.srcsoftware.modular.manager.utilidades.DateUtil;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.safra.SafraDTO;
import br.com.srcsoftware.sistema.safra.cultura.CulturaDTO;
import br.com.srcsoftware.sistema.silo.silo.SiloDTO;

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
public class AjusteSiloDTO extends AuditoriaAbstractDTOComEmpresa implements Comparable< AjusteSiloDTO >{

	public static final String TIPO_SOBRA_CLASSIFICACAO = "Sobra de Classificação";//Sobra de Classificação(+)
	public static final String TIPO_QUEBRA_CLASSIFICACAO = "Quebra de Classificação";//Quebra de Classificação(-)

	private String id;
	private String tipo;
	private FuncionarioDTO lancador;
	private String data;
	private SafraDTO safra;
	private CulturaDTO cultura;
	private SiloDTO localArmazenagem;
	private String quantidade;
	private String motivo;

	public String getEmSacas() {
		if ( Utilidades.isNuloOuVazio( this.getQuantidade() ) ) {
			return null;
		}
		return Utilidades.parseBigDecimal( Utilidades.parseBigDecimal( this.getQuantidade() ).divide( new BigDecimal( "60" ), 2, BigDecimal.ROUND_FLOOR ) );
	}

	public String getId() {
		return id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public SiloDTO getLocalArmazenagem() {
		if ( localArmazenagem == null ) {
			localArmazenagem = new SiloDTO();
		}
		return localArmazenagem;
	}

	public void setLocalArmazenagem( SiloDTO localArmazenagem ) {
		this.localArmazenagem = localArmazenagem;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo( String tipo ) {
		this.tipo = tipo;
	}

	public FuncionarioDTO getLancador() {
		if ( lancador == null ) {
			lancador = new FuncionarioDTO();
		}
		return lancador;
	}

	public void setLancador( FuncionarioDTO lancador ) {
		this.lancador = lancador;
	}

	public String getData() {
		return data;
	}

	public void setData( String data ) {
		this.data = data;
	}

	public String getDataToString() {
		return DateUtil.formatLocalDate( this.getData() );
	}

	public SafraDTO getSafra() {
		if ( safra == null ) {
			safra = new SafraDTO();
		}
		return safra;
	}

	public void setSafra( SafraDTO safra ) {
		this.safra = safra;
	}

	public CulturaDTO getCultura() {
		if ( cultura == null ) {
			cultura = new CulturaDTO();
		}
		return cultura;
	}

	public void setCultura( CulturaDTO cultura ) {
		this.cultura = cultura;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade( String quantidade ) {
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
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
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
		if ( !( obj instanceof AjusteSiloDTO ) ) {
			return false;
		}
		AjusteSiloDTO other = (AjusteSiloDTO) obj;
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
		if ( id == null ) {
			if ( other.id != null ) {
				return false;
			}
		} else if ( !id.equals( other.id ) ) {
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
		builder.append( "AjusteSiloDTO [id=" );
		builder.append( id );
		builder.append( ", tipo=" );
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
	public int compareTo( AjusteSiloDTO o ) {
		return getId().compareTo( o.getId() );
	}
}
