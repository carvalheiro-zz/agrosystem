package br.com.srcsoftware.sistema.silo.entrada;

import java.math.BigDecimal;

import br.com.srcsoftware.abstracts.AuditoriaAbstractDTO;
import br.com.srcsoftware.modular.manager.pessoa.funcionario.FuncionarioDTO;
import br.com.srcsoftware.modular.manager.utilidades.DateUtil;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.safra.SafraDTO;
import br.com.srcsoftware.sistema.safra.cultura.variedade.VariedadeDTO;
import br.com.srcsoftware.sistema.safra.setor.SetorDTO;
import br.com.srcsoftware.sistema.silo.silo.SiloDTO;

public final class EntradaGraoDTO extends AuditoriaAbstractDTO implements Comparable< EntradaGraoDTO >{
	private String id;
	private String data;
	private String pesoBruto;
	private String pesoLiquido;
	private String umidade;
	private String percentualDescontoGeradoUmidade;
	private String percentualDescontoGeradoImpureza;
	private String nomeMotorista;
	private VariedadeDTO variedade;
	private SafraDTO safra = new SafraDTO();
	private SetorDTO setor = new SetorDTO();
	private FuncionarioDTO estoquista = new FuncionarioDTO();
	private SiloDTO localArmazenagem;

	public String getNomeCompleto() {
		String variedade = this.getVariedade().getNome();
		String setor = this.getSetor().getNomeCompleto();
		String safra = this.getSafra().getNomeCompleto();

		StringBuilder nomeCompleto = new StringBuilder();

		if ( !Utilidades.isNuloOuVazio( safra ) ) {
			nomeCompleto.append( safra );
		}
		if ( !Utilidades.isNuloOuVazio( setor ) ) {
			nomeCompleto.append( " - " );
			nomeCompleto.append( setor );
		}
		if ( !Utilidades.isNuloOuVazio( variedade ) ) {
			nomeCompleto.append( " - " );
			nomeCompleto.append( variedade );
		}

		return nomeCompleto.toString();
	}

	public void setPesoLiquido( String pesoLiquido ) {
		this.pesoLiquido = pesoLiquido;
	}

	public String getId() {
		return this.id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public String getData() {
		return this.data;
	}

	public void setData( String data ) {
		this.data = data;
	}

	public String getDataToString() {
		return DateUtil.formatLocalDate( this.getData() );
	}

	public String getEmSacas() {
		return Utilidades.parseBigDecimal( Utilidades.parseBigDecimal( this.getPesoLiquido() ).divide( new BigDecimal( "60" ), 2, BigDecimal.ROUND_FLOOR ) );
	}

	public String getPesoLiquido() {
		try {
			BigDecimal pesoBruto = Utilidades.parseBigDecimal( this.pesoBruto );
			BigDecimal percentualDescontoGeradoUmidade = Utilidades.parseBigDecimal( this.percentualDescontoGeradoUmidade );
			BigDecimal percentualDescontoGeradoImpureza = Utilidades.parseBigDecimal( this.percentualDescontoGeradoImpureza );

			if ( pesoBruto == null ) {
				pesoBruto = BigDecimal.ZERO;
			}
			if ( percentualDescontoGeradoUmidade == null ) {
				percentualDescontoGeradoUmidade = BigDecimal.ZERO;
			}
			if ( percentualDescontoGeradoImpureza == null ) {
				percentualDescontoGeradoImpureza = BigDecimal.ZERO;
			}

			BigDecimal resultadoPercentualDescontoGeradoUmidade = pesoBruto.multiply( percentualDescontoGeradoUmidade ).divide( new BigDecimal( "100" ), BigDecimal.ROUND_FLOOR ); //                                    Utilidades.calcularPercentual( pesoBruto, percentualDescontoGeradoUmidade );
			BigDecimal resultadoPercentualDescontoGeradoImpureza = pesoBruto.multiply( percentualDescontoGeradoImpureza ).divide( new BigDecimal( "100" ), BigDecimal.ROUND_FLOOR );//Utilidades.calcularPercentual( pesoBruto, percentualDescontoGeradoImpureza );

			BigDecimal resultado = pesoBruto.subtract( resultadoPercentualDescontoGeradoUmidade ).subtract( resultadoPercentualDescontoGeradoImpureza );

			return Utilidades.parseBigDecimal( resultado.setScale( 2, BigDecimal.ROUND_FLOOR ) );

		} catch ( Exception e ) {
			e.printStackTrace();
		}

		return null;
	}

	public String getPesoBruto() {
		return this.pesoBruto;
	}

	public void setPesoBruto( String pesoBruto ) {
		this.pesoBruto = pesoBruto;
	}

	public String getUmidade() {
		return this.umidade;
	}

	public void setUmidade( String umidade ) {
		this.umidade = umidade;
	}

	public String getPercentualDescontoGeradoUmidade() {
		return this.percentualDescontoGeradoUmidade;
	}

	public void setPercentualDescontoGeradoUmidade( String percentualDescontoGeradoUmidade ) {
		this.percentualDescontoGeradoUmidade = percentualDescontoGeradoUmidade;
	}

	public String getPercentualDescontoGeradoImpureza() {
		return this.percentualDescontoGeradoImpureza;
	}

	public void setPercentualDescontoGeradoImpureza( String percentualDescontoGeradoImpureza ) {
		this.percentualDescontoGeradoImpureza = percentualDescontoGeradoImpureza;
	}

	public SiloDTO getLocalArmazenagem() {
		if ( this.localArmazenagem == null ) {
			this.localArmazenagem = new SiloDTO();
		}
		return this.localArmazenagem;
	}

	public void setLocalArmazenagem( SiloDTO localArmazenagem ) {
		this.localArmazenagem = localArmazenagem;
	}

	public String getNomeMotorista() {
		return this.nomeMotorista;
	}

	public void setNomeMotorista( String nomeMotorista ) {
		this.nomeMotorista = nomeMotorista;
	}

	public VariedadeDTO getVariedade() {
		if ( this.variedade == null ) {
			this.variedade = new VariedadeDTO();
		}
		return this.variedade;
	}

	public void setVariedade( VariedadeDTO variedade ) {
		this.variedade = variedade;
	}

	public SafraDTO getSafra() {
		if ( this.safra == null ) {
			this.safra = new SafraDTO();
		}
		return this.safra;
	}

	public void setSafra( SafraDTO safra ) {
		this.safra = safra;
	}

	public SetorDTO getSetor() {
		if ( this.setor == null ) {
			this.setor = new SetorDTO();
		}
		return this.setor;
	}

	public void setSetor( SetorDTO setor ) {
		this.setor = setor;
	}

	public FuncionarioDTO getEstoquista() {
		if ( this.estoquista == null ) {
			this.estoquista = new FuncionarioDTO();
		}
		return this.estoquista;
	}

	public void setEstoquista( FuncionarioDTO estoquista ) {
		this.estoquista = estoquista;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = ( prime * result ) + ( ( this.variedade == null ) ? 0 : this.variedade.hashCode() );
		result = ( prime * result ) + ( ( this.data == null ) ? 0 : this.data.hashCode() );
		result = ( prime * result ) + ( ( this.estoquista == null ) ? 0 : this.estoquista.hashCode() );
		result = ( prime * result ) + ( ( this.id == null ) ? 0 : this.id.hashCode() );
		result = ( prime * result ) + ( ( this.localArmazenagem == null ) ? 0 : this.localArmazenagem.hashCode() );
		result = ( prime * result ) + ( ( this.nomeMotorista == null ) ? 0 : this.nomeMotorista.hashCode() );
		result = ( prime * result ) + ( ( this.percentualDescontoGeradoImpureza == null ) ? 0 : this.percentualDescontoGeradoImpureza.hashCode() );
		result = ( prime * result ) + ( ( this.percentualDescontoGeradoUmidade == null ) ? 0 : this.percentualDescontoGeradoUmidade.hashCode() );
		result = ( prime * result ) + ( ( this.pesoBruto == null ) ? 0 : this.pesoBruto.hashCode() );
		result = ( prime * result ) + ( ( this.safra == null ) ? 0 : this.safra.hashCode() );
		result = ( prime * result ) + ( ( this.setor == null ) ? 0 : this.setor.hashCode() );
		result = ( prime * result ) + ( ( this.umidade == null ) ? 0 : this.umidade.hashCode() );
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
		EntradaGraoDTO other = (EntradaGraoDTO) obj;
		if ( this.variedade == null ) {
			if ( other.variedade != null ) {
				return false;
			}
		} else if ( !this.variedade.equals( other.variedade ) ) {
			return false;
		}
		if ( this.data == null ) {
			if ( other.data != null ) {
				return false;
			}
		} else if ( !this.data.equals( other.data ) ) {
			return false;
		}
		if ( this.estoquista == null ) {
			if ( other.estoquista != null ) {
				return false;
			}
		} else if ( !this.estoquista.equals( other.estoquista ) ) {
			return false;
		}
		if ( this.id == null ) {
			if ( other.id != null ) {
				return false;
			}
		} else if ( !this.id.equals( other.id ) ) {
			return false;
		}
		if ( this.localArmazenagem == null ) {
			if ( other.localArmazenagem != null ) {
				return false;
			}
		} else if ( !this.localArmazenagem.equals( other.localArmazenagem ) ) {
			return false;
		}
		if ( this.nomeMotorista == null ) {
			if ( other.nomeMotorista != null ) {
				return false;
			}
		} else if ( !this.nomeMotorista.equals( other.nomeMotorista ) ) {
			return false;
		}
		if ( this.percentualDescontoGeradoImpureza == null ) {
			if ( other.percentualDescontoGeradoImpureza != null ) {
				return false;
			}
		} else if ( !this.percentualDescontoGeradoImpureza.equals( other.percentualDescontoGeradoImpureza ) ) {
			return false;
		}
		if ( this.percentualDescontoGeradoUmidade == null ) {
			if ( other.percentualDescontoGeradoUmidade != null ) {
				return false;
			}
		} else if ( !this.percentualDescontoGeradoUmidade.equals( other.percentualDescontoGeradoUmidade ) ) {
			return false;
		}
		if ( this.pesoBruto == null ) {
			if ( other.pesoBruto != null ) {
				return false;
			}
		} else if ( !this.pesoBruto.equals( other.pesoBruto ) ) {
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
		if ( this.umidade == null ) {
			if ( other.umidade != null ) {
				return false;
			}
		} else if ( !this.umidade.equals( other.umidade ) ) {
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
		builder.append( "EntradaGraoDTO [id=" );
		builder.append( id );
		builder.append( ", data=" );
		builder.append( data );
		builder.append( ", pesoBruto=" );
		builder.append( pesoBruto );
		builder.append( ", pesoLiquido=" );
		builder.append( pesoLiquido );
		builder.append( ", umidade=" );
		builder.append( umidade );
		builder.append( ", percentualDescontoGeradoUmidade=" );
		builder.append( percentualDescontoGeradoUmidade );
		builder.append( ", percentualDescontoGeradoImpureza=" );
		builder.append( percentualDescontoGeradoImpureza );
		builder.append( ", nomeMotorista=" );
		builder.append( nomeMotorista );
		builder.append( ", variedade=" );
		builder.append( variedade );
		builder.append( ", safra=" );
		builder.append( safra );
		builder.append( ", setor=" );
		builder.append( setor );
		builder.append( ", estoquista=" );
		builder.append( estoquista );
		builder.append( ", localArmazenagem=" );
		builder.append( localArmazenagem );
		builder.append( ", toString()=" );
		builder.append( super.toString() );
		builder.append( "]" );
		return builder.toString();
	}

	@Override
	public int compareTo( EntradaGraoDTO o ) {
		return this.getData().compareTo( o.getData() );
	}

}
