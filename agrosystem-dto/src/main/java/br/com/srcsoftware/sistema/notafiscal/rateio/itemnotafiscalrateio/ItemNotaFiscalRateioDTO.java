package br.com.srcsoftware.sistema.notafiscal.rateio.itemnotafiscalrateio;

import br.com.srcsoftware.abstracts.AbstractDTO;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.notafiscal.rateio.centrocusto.CentroCustoDTO;
import br.com.srcsoftware.sistema.safra.SafraDTO;
import br.com.srcsoftware.sistema.safra.cultura.CulturaDTO;
import br.com.srcsoftware.sistema.safra.setor.SetorDTO;

public class ItemNotaFiscalRateioDTO extends AbstractDTO{

	private String id;
	private String descricao;
	private String valor;
	private String tipo;
	private CulturaDTO cultura;
	private SafraDTO safra;
	private SetorDTO setor;
	private CentroCustoDTO centroCusto;

	public String getNomeCompleto() {
		String cultura = this.getCultura().getNome();
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
		if ( !Utilidades.isNuloOuVazio( cultura ) ) {
			nomeCompleto.append( " - " );
			nomeCompleto.append( cultura );
		}

		return nomeCompleto.toString();
	}

	public String getId() {
		return this.id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public ItemNotaFiscalRateioDTO(){
		this.setSafra( new SafraDTO() );
		this.setSetor( new SetorDTO() );
		this.setCentroCusto( new CentroCustoDTO() );
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao( String descricao ) {
		this.descricao = descricao;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor( String valor ) {
		this.valor = valor;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo( String tipo ) {
		this.tipo = tipo;
	}

	public CulturaDTO getCultura() {
		if ( this.cultura == null ) {
			this.cultura = new CulturaDTO();
		}
		return this.cultura;
	}

	public void setCultura( CulturaDTO cultura ) {
		this.cultura = cultura;
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

	public CentroCustoDTO getCentroCusto() {
		if ( this.centroCusto == null ) {
			this.centroCusto = new CentroCustoDTO();
		}
		return this.centroCusto;
	}

	public void setCentroCusto( CentroCustoDTO centroCusto ) {
		this.centroCusto = centroCusto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = ( prime * result ) + ( ( this.centroCusto == null ) ? 0 : this.centroCusto.hashCode() );
		result = ( prime * result ) + ( ( this.cultura == null ) ? 0 : this.cultura.hashCode() );
		result = ( prime * result ) + ( ( this.descricao == null ) ? 0 : this.descricao.hashCode() );
		result = ( prime * result ) + ( ( this.id == null ) ? 0 : this.id.hashCode() );
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
		if ( obj == null ) {
			return false;
		}
		if ( this.getClass() != obj.getClass() ) {
			return false;
		}
		ItemNotaFiscalRateioDTO other = (ItemNotaFiscalRateioDTO) obj;
		if ( this.centroCusto == null ) {
			if ( other.centroCusto != null ) {
				return false;
			}
		} else if ( !this.centroCusto.equals( other.centroCusto ) ) {
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
		if ( this.id == null ) {
			if ( other.id != null ) {
				return false;
			}
		} else if ( !this.id.equals( other.id ) ) {
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
		builder.append( ", \ncentroCusto=" );
		builder.append( this.centroCusto );
		builder.append( "]\n" );
		return builder.toString();
	}

}
