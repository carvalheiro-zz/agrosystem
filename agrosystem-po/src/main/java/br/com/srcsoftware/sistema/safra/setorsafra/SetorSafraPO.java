package br.com.srcsoftware.sistema.safra.setorsafra;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.srcsoftware.abstracts.AbstractPO;
import br.com.srcsoftware.sistema.safra.cultura.variedade.VariedadePO;
import br.com.srcsoftware.sistema.safra.setor.SetorPO;

@Entity
@Table( name = "sistema_setoressafra" )
public final class SetorSafraPO extends AbstractPO implements Comparable< SetorSafraPO >{

	@Column( precision = 12, scale = 3, nullable = false )
	private BigDecimal area;

	/*@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idCultura", nullable = false, foreignKey = @ForeignKey( name = "fk_cultura_setoressafra" ) )
	private CulturaPO cultura = new CulturaPO();*/
	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idVariedade", nullable = false, foreignKey = @ForeignKey( name = "fk_variedade_setoressafra" ) )
	private VariedadePO variedade = new VariedadePO();

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idSetor", nullable = false, foreignKey = @ForeignKey( name = "fk_setor_setoressafra" ) )
	private SetorPO setor = new SetorPO();

	/*public CulturaPO getCultura() {
		return cultura;
	}
	
	public void setCultura( CulturaPO cultura ) {
		this.cultura = cultura;
	}*/

	public BigDecimal getArea() {
		return this.area;
	}

	public VariedadePO getVariedade() {
		return variedade;
	}

	public void setVariedade( VariedadePO variedade ) {
		this.variedade = variedade;
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
		result = prime * result + ( ( area == null ) ? 0 : area.hashCode() );
		result = prime * result + ( ( variedade == null ) ? 0 : variedade.hashCode() );
		result = prime * result + ( ( setor == null ) ? 0 : setor.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		SetorSafraPO other = (SetorSafraPO) obj;
		if ( area == null ) {
			if ( other.area != null )
				return false;
		} else if ( !area.equals( other.area ) )
			return false;
		if ( variedade == null ) {
			if ( other.variedade != null )
				return false;
		} else if ( !variedade.equals( other.variedade ) )
			return false;
		if ( setor == null ) {
			if ( other.setor != null )
				return false;
		} else if ( !setor.equals( other.setor ) )
			return false;
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
		builder.append( ", \nvariedade=" );
		builder.append( variedade );
		builder.append( ", \nsetor=" );
		builder.append( setor );
		builder.append( ", \ngetId()=" );
		builder.append( getId() );
		builder.append( "]\n" );
		return builder.toString();
	}

	@Override
	public int compareTo( SetorSafraPO o ) {
		return this.getVariedade().getNome().compareToIgnoreCase( o.getVariedade().getNome() );
	}

}
