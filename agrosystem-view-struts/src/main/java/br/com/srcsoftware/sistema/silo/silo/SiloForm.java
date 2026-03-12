package br.com.srcsoftware.sistema.silo.silo;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import br.com.srcsoftware.modular.manager.abstracts.AbstractCRUDForm;

public final class SiloForm extends AbstractCRUDForm{

	private static final long serialVersionUID = 1342719712529624093L;

	private SiloDTO silo;

	private ArrayList< SiloDTO > silos;

	public SiloDTO getSilo() {
		if ( silo == null ) {
			silo = new SiloDTO();
		}
		return silo;
	}

	public void setSilo( SiloDTO silo ) {
		this.silo = silo;
	}

	public ArrayList< SiloDTO > getSilos() {
		if ( silos == null ) {
			silos = new ArrayList<>();
		}
		return silos;
	}

	public void setSilos( ArrayList< SiloDTO > silos ) {
		this.silos = silos;
	}

	@Override
	public void limparCamposCadastro( HttpServletRequest request ) {
		setSilo( null );
	}

	@Override
	public void limparCamposConsulta( HttpServletRequest request ) {

	}

	@Override
	public void limparLista() {
		this.getSilos().clear();
	}

}
