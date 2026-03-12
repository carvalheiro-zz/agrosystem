package br.com.srcsoftware.sistema.pessoa.prestadorservico;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import br.com.srcsoftware.modular.manager.abstracts.AbstractCRUDForm;

public final class PrestadorServicoForm extends AbstractCRUDForm{

	private static final long serialVersionUID = 1342719712529624093L;

	private PrestadorServicoDTO prestadorServico;

	private ArrayList< PrestadorServicoDTO > prestadores;

	public PrestadorServicoDTO getPrestadorServico() {
		if ( this.prestadorServico == null ) {
			this.prestadorServico = new PrestadorServicoDTO();
		}
		return this.prestadorServico;
	}

	public void setPrestadorServico( PrestadorServicoDTO prestadorServico ) {
		this.prestadorServico = prestadorServico;
	}

	public ArrayList< PrestadorServicoDTO > getPrestadores() {
		if ( this.prestadores == null ) {
			this.prestadores = new ArrayList<>();
		}
		return this.prestadores;
	}

	public void setPrestadores( ArrayList< PrestadorServicoDTO > prestadores ) {
		this.prestadores = prestadores;
	}

	@Override
	public void limparCamposCadastro( HttpServletRequest request ) {
		this.setPrestadorServico( null );
	}

	@Override
	public void limparCamposConsulta( HttpServletRequest request ) {

	}

	@Override
	public void limparLista() {
		this.getPrestadores().clear();
	}

}
