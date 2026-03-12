package br.com.srcsoftware.sistema.notafiscal.rateio.centrocusto;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import br.com.srcsoftware.modular.manager.abstracts.AbstractCRUDForm;

public final class CentroCustoForm extends AbstractCRUDForm{

	private static final long serialVersionUID = 1342719712529624093L;

	private CentroCustoDTO centroCusto;

	private ArrayList< CentroCustoDTO > centros;

	public CentroCustoDTO getCentroCusto() {
		if ( centroCusto == null ) {
			centroCusto = new CentroCustoDTO();
		}
		return centroCusto;
	}

	public void setCentroCusto( CentroCustoDTO centroCusto ) {
		this.centroCusto = centroCusto;
	}

	public ArrayList< CentroCustoDTO > getCentros() {
		if ( centros == null ) {
			centros = new ArrayList<>();
		}
		return centros;
	}

	public void setCentros( ArrayList< CentroCustoDTO > centros ) {
		this.centros = centros;
	}

	@Override
	public void limparCamposCadastro( HttpServletRequest request ) {
		setCentroCusto( null );
	}

	@Override
	public void limparCamposConsulta( HttpServletRequest request ) {

	}

	@Override
	public void limparLista() {
		this.getCentros().clear();
	}

}
