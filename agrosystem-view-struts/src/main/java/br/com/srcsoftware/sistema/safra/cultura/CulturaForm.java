package br.com.srcsoftware.sistema.safra.cultura;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import br.com.srcsoftware.modular.manager.abstracts.AbstractCRUDForm;

public final class CulturaForm extends AbstractCRUDForm{

	private static final long serialVersionUID = 1342719712529624093L;

	private CulturaDTO cultura;

	private ArrayList< CulturaDTO > culturas;

	public CulturaDTO getCultura() {
		if ( cultura == null ) {
			cultura = new CulturaDTO();
		}
		return cultura;
	}

	public void setCultura( CulturaDTO cultura ) {
		this.cultura = cultura;
	}

	public ArrayList< CulturaDTO > getCulturas() {
		if ( culturas == null ) {
			culturas = new ArrayList<>();
		}
		return culturas;
	}

	public void setCulturas( ArrayList< CulturaDTO > culturas ) {
		this.culturas = culturas;
	}

	@Override
	public void limparCamposCadastro( HttpServletRequest request ) {
		setCultura( null );
	}

	@Override
	public void limparCamposConsulta( HttpServletRequest request ) {

	}

	@Override
	public void limparLista() {
		this.getCulturas().clear();
	}

}
