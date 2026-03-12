package br.com.srcsoftware.sistema.produto.unidademedida;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import br.com.srcsoftware.modular.manager.abstracts.AbstractCRUDForm;

public final class UnidadeMedidaForm extends AbstractCRUDForm{

	private static final long serialVersionUID = 1342719712529624093L;

	private UnidadeMedidaDTO unidadeMedida;

	private ArrayList< UnidadeMedidaDTO > unidadeMedidas;

	public UnidadeMedidaDTO getUnidadeMedida() {
		if ( unidadeMedida == null ) {
			unidadeMedida = new UnidadeMedidaDTO();
		}
		return unidadeMedida;
	}

	public void setUnidadeMedida( UnidadeMedidaDTO unidadeMedida ) {
		this.unidadeMedida = unidadeMedida;
	}

	public ArrayList< UnidadeMedidaDTO > getUnidadeMedidas() {
		if ( unidadeMedidas == null ) {
			unidadeMedidas = new ArrayList<>();
		}
		return unidadeMedidas;
	}

	public void setUnidadeMedidas( ArrayList< UnidadeMedidaDTO > unidadeMedidas ) {
		this.unidadeMedidas = unidadeMedidas;
	}

	@Override
	public void limparCamposCadastro( HttpServletRequest request ) {
		setUnidadeMedida( null );
	}

	@Override
	public void limparCamposConsulta( HttpServletRequest request ) {

	}

	@Override
	public void limparLista() {
		this.getUnidadeMedidas().clear();
	}
}
