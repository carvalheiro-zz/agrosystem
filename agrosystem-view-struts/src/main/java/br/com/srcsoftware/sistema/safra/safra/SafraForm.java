package br.com.srcsoftware.sistema.safra.safra;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import br.com.srcsoftware.modular.manager.abstracts.AbstractCRUDForm;
import br.com.srcsoftware.sistema.safra.SafraDTO;
import br.com.srcsoftware.sistema.safra.setorsafra.SetorSafraDTO;

public final class SafraForm extends AbstractCRUDForm{

	private static final long serialVersionUID = 1342719712529624093L;

	private SafraDTO safra;

	private ArrayList< SafraDTO > safras;

	// Item da Safra
	private SetorSafraDTO setorSafra;

	public SetorSafraDTO getSetorSafra() {
		if ( this.setorSafra == null ) {
			this.setorSafra = new SetorSafraDTO();
		}
		return setorSafra;
	}

	public void setSetorSafra( SetorSafraDTO setorSafra ) {
		this.setorSafra = setorSafra;
	}

	public ArrayList< SafraDTO > getSafras() {
		if ( this.safras == null ) {
			this.safras = new ArrayList<>();
		}
		return this.safras;
	}

	public void setSafras( ArrayList< SafraDTO > safras ) {
		this.safras = safras;
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

	@Override
	public void limparCamposCadastro( HttpServletRequest request ) {
		setSafra( null );
		setSetorSafra( null );
	}

	@Override
	public void limparCamposConsulta( HttpServletRequest request ) {
		setSetorSafra( null );
	}

	@Override
	public void limparLista() {
		this.getSafras().clear();
	}

}
