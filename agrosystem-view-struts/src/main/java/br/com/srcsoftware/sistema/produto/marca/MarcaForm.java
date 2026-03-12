package br.com.srcsoftware.sistema.produto.marca;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import br.com.srcsoftware.modular.manager.abstracts.AbstractCRUDForm;

public final class MarcaForm extends AbstractCRUDForm{

	private static final long serialVersionUID = 1342719712529624093L;

	private MarcaDTO marca;

	private ArrayList< MarcaDTO > marcas;

	public MarcaDTO getMarca() {
		if ( this.marca == null ) {
			this.marca = new MarcaDTO();
		}
		return this.marca;
	}

	public void setMarca( MarcaDTO marca ) {
		this.marca = marca;
	}

	public ArrayList< MarcaDTO > getMarcas() {
		if ( this.marcas == null ) {
			this.marcas = new ArrayList<>();
		}
		return this.marcas;
	}

	public void setMarcas( ArrayList< MarcaDTO > marcas ) {
		this.marcas = marcas;
	}

	@Override
	public void limparCamposCadastro( HttpServletRequest request ) {
		this.setMarca( null );
	}

	@Override
	public void limparCamposConsulta( HttpServletRequest request ) {

	}

	@Override
	public void limparLista() {
		this.getMarcas().clear();
	}

}
