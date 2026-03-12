package br.com.srcsoftware.sistema.safra.setor;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import br.com.srcsoftware.modular.manager.abstracts.AbstractCRUDForm;

public final class SetorForm extends AbstractCRUDForm{

	private static final long serialVersionUID = 4850606525383582889L;

	private SetorDTO setor;

	private ArrayList< SetorDTO > setores;

	public ArrayList< SetorDTO > getSetores() {
		if ( this.setores == null ) {
			this.setores = new ArrayList<>();
		}
		return this.setores;
	}

	public void setSetores( ArrayList< SetorDTO > setores ) {
		this.setores = setores;
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
	public void limparCamposCadastro( HttpServletRequest request ) {
		setSetor( null );
	}

	@Override
	public void limparCamposConsulta( HttpServletRequest request ) {

	}

	@Override
	public void limparLista() {
		this.getSetores().clear();
	}

}
