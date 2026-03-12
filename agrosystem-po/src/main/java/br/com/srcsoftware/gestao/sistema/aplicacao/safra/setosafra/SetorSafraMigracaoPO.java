package br.com.srcsoftware.gestao.sistema.aplicacao.safra.setosafra;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.srcsoftware.abstracts.AbstractPO;
import br.com.srcsoftware.gestao.sistema.aplicacao.setor.SetorMigracaoPO;
import br.com.srcsoftware.gestao.sistema.cultura.CulturaMigracaoPO;
import br.com.srcsoftware.modular.manager.utilidades.Constantes;

@Entity
@Table( name = "setoressafras", schema = Constantes.SCHEMA_MIGRACAO )
public final class SetorSafraMigracaoPO extends AbstractPO implements Comparable< SetorSafraMigracaoPO >{

	@Column( name = "area", precision = 12, scale = 2, nullable = false )
	private BigDecimal area;

	/*@Column( name = "culturaOld", length = 100, nullable = false )
	private String culturaOld;
	
	@Column( name = "variedadeOld", length = 100, nullable = false )
	private String variedadeOld;*/

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idCultura", nullable = false )
	private CulturaMigracaoPO cultura = new CulturaMigracaoPO();

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idSetor", nullable = false )
	private SetorMigracaoPO setor = new SetorMigracaoPO();

	public CulturaMigracaoPO getCultura() {
		return cultura;
	}

	public void setCultura( CulturaMigracaoPO cultura ) {
		this.cultura = cultura;
	}

	public BigDecimal getArea() {
		return this.area;
	}

	public void setArea( BigDecimal area ) {
		this.area = area;
	}

	/*public String getCulturaOld() {
		return this.culturaOld;
	}
	
	public void setCulturaOld( String culturaOld ) {
		this.culturaOld = culturaOld;
	}
	
	public String getVariedadeOld() {
		return this.variedadeOld;
	}
	
	public void setVariedadeOld( String variedadeOld ) {
		this.variedadeOld = variedadeOld;
	}*/

	public SetorMigracaoPO getSetor() {
		return this.setor;
	}

	public void setSetor( SetorMigracaoPO setor ) {
		this.setor = setor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 0;
		result = prime * result + ( ( area == null ) ? 0 : area.hashCode() );
		result = prime * result + ( ( cultura == null ) ? 0 : cultura.hashCode() );
		//result = prime * result + ( ( culturaOld == null ) ? 0 : culturaOld.hashCode() );
		result = prime * result + ( ( setor == null ) ? 0 : setor.hashCode() );
		//result = prime * result + ( ( variedadeOld == null ) ? 0 : variedadeOld.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}

		if ( !( obj instanceof SetorSafraMigracaoPO ) ) {
			return false;
		}
		SetorSafraMigracaoPO other = (SetorSafraMigracaoPO) obj;
		if ( area == null ) {
			if ( other.area != null ) {
				return false;
			}
		} else if ( !area.equals( other.area ) ) {
			return false;
		}
		if ( cultura == null ) {
			if ( other.cultura != null ) {
				return false;
			}
		} else if ( !cultura.equals( other.cultura ) ) {
			return false;
		}
		/*if ( culturaOld == null ) {
			if ( other.culturaOld != null ) {
				return false;
			}
		} else if ( !culturaOld.equals( other.culturaOld ) ) {
			return false;
		}*/
		if ( setor == null ) {
			if ( other.setor != null ) {
				return false;
			}
		} else if ( !setor.equals( other.setor ) ) {
			return false;
		}
		/*if ( variedadeOld == null ) {
			if ( other.variedadeOld != null ) {
				return false;
			}
		} else if ( !variedadeOld.equals( other.variedadeOld ) ) {
			return false;
		}*/
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "SetorSafraPO [\n\tarea=" );
		builder.append( area );
		/*builder.append( ", \nculturaOld=" );
		builder.append( culturaOld );
		builder.append( ", \nvariedadeOld=" );
		builder.append( variedadeOld );*/
		builder.append( ", \ncultura=" );
		builder.append( cultura );
		builder.append( ", \nsetor=" );
		builder.append( setor );
		builder.append( "]\n" );
		return builder.toString();
	}

	@Override
	public int compareTo( SetorSafraMigracaoPO o ) {
		return this.getCultura().getNome().compareToIgnoreCase( o.getCultura().getNome() );
	}

}
