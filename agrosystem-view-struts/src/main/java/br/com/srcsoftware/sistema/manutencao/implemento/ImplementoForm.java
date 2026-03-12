package br.com.srcsoftware.sistema.manutencao.implemento;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import br.com.srcsoftware.modular.manager.abstracts.AbstractCRUDForm;

public final class ImplementoForm extends AbstractCRUDForm{

	private static final long serialVersionUID = 1342719712529624093L;

	private ImplementoDTO implemento;

	private ArrayList< ImplementoDTO > implementos;

	public ImplementoDTO getImplemento() {
		if ( implemento == null ) {
			implemento = new ImplementoDTO();
		}
		return implemento;
	}

	public void setImplemento( ImplementoDTO implemento ) {
		this.implemento = implemento;
	}

	public ArrayList< ImplementoDTO > getImplementos() {
		if ( implementos == null ) {
			implementos = new ArrayList<>();
		}
		return implementos;
	}

	public void setImplementos( ArrayList< ImplementoDTO > implementos ) {
		this.implementos = implementos;
	}

	@Override
	public void limparCamposCadastro( HttpServletRequest request ) {
		setImplemento( null );
	}

	@Override
	public void limparCamposConsulta( HttpServletRequest request ) {
		limparCamposCadastro( null );
	}

	@Override
	public void limparLista() {
		this.getImplementos().clear();
	}

}
