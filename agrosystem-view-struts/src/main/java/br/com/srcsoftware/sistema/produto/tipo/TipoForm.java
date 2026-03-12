package br.com.srcsoftware.sistema.produto.tipo;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import br.com.srcsoftware.modular.manager.abstracts.AbstractCRUDForm;

public final class TipoForm extends AbstractCRUDForm{

	private static final long serialVersionUID = 1342719712529624093L;

	private TipoDTO tipo;

	private ArrayList< TipoDTO > tipos;

	public TipoDTO getTipo() {
		if ( tipo == null ) {
			tipo = new TipoDTO();
		}
		return tipo;
	}

	public void setTipo( TipoDTO tipo ) {
		this.tipo = tipo;
	}

	public ArrayList< TipoDTO > getTipos() {
		if ( tipos == null ) {
			tipos = new ArrayList<>();
		}
		return tipos;
	}

	public void setTipos( ArrayList< TipoDTO > tipos ) {
		this.tipos = tipos;
	}

	@Override
	public void limparCamposCadastro( HttpServletRequest request ) {
		setTipo( null );
	}

	@Override
	public void limparCamposConsulta( HttpServletRequest request ) {

	}

	@Override
	public void limparLista() {
		this.getTipos().clear();
	}

}
