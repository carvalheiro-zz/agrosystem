package br.com.srcsoftware.sistema.safra.setorsafra;

import br.com.srcsoftware.abstracts.AbstractDTO;
import br.com.srcsoftware.sistema.safra.cultura.variedade.VariedadeDTO;
import br.com.srcsoftware.sistema.safra.setor.SetorDTO;

public class SetorSafraDTO extends AbstractDTO implements Comparable< SetorSafraDTO >{

	private String id;
	private String area;
	//private CulturaDTO cultura = new CulturaDTO();
	private VariedadeDTO variedade = new VariedadeDTO();
	private SetorDTO setor;

	public VariedadeDTO getVariedade() {
		if ( this.variedade == null ) {
			this.variedade = new VariedadeDTO();
		}
		return variedade;
	}

	public String getId() {
		return id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public void setVariedade( VariedadeDTO variedade ) {
		this.variedade = variedade;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea( String area ) {
		this.area = area;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( area == null ) ? 0 : area.hashCode() );
		result = prime * result + ( ( variedade == null ) ? 0 : variedade.hashCode() );
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
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
		SetorSafraDTO other = (SetorSafraDTO) obj;
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
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
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
		builder.append( "SetorSafraDTO [\n\tarea=" );
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
	public int compareTo( SetorSafraDTO o ) {
		return Long.valueOf( this.getSetor().getNome().hashCode() ).compareTo( Long.valueOf( o.getSetor().getNome().hashCode() ) );

		//return this.getVariedade().getCultura().getNome().compareToIgnoreCase( o.getVariedade().getCultura().getNome() );
	}
}
