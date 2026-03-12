package br.com.srcsoftware.sistema.safra.cultura.variedade;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import br.com.srcsoftware.modular.manager.abstracts.AbstractCRUDForm;

public final class VariedadeForm extends AbstractCRUDForm{

	private static final long serialVersionUID = 1342719712529624093L;

	private VariedadeDTO variedade;

	private ArrayList< VariedadeDTO > variedades;

	public VariedadeDTO getVariedade() {
		if ( this.variedade == null ) {
			this.variedade = new VariedadeDTO();
		}
		return this.variedade;
	}

	public void setVariedade( VariedadeDTO variedade ) {
		this.variedade = variedade;
	}

	public ArrayList< VariedadeDTO > getVariedades() {
		if ( this.variedades == null ) {
			this.variedades = new ArrayList<>();
		}
		return this.variedades;
	}

	public void setVariedades( ArrayList< VariedadeDTO > variedades ) {
		this.variedades = variedades;
	}

	@Override
	public void limparCamposCadastro( HttpServletRequest request ) {
		this.setVariedade( null );
	}

	@Override
	public void limparCamposConsulta( HttpServletRequest request ) {

	}

	@Override
	public void limparLista() {
		this.getVariedades().clear();
	}

}
