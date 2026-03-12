package br.com.srcsoftware.sistema.manutencao.imovel;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import br.com.srcsoftware.modular.manager.abstracts.AbstractCRUDForm;

public final class ImovelForm extends AbstractCRUDForm{

	private static final long serialVersionUID = 1342719712529624093L;

	private ImovelDTO imovel;

	private ArrayList< ImovelDTO > imoveis;

	public ImovelDTO getImovel() {
		if ( imovel == null ) {
			imovel = new ImovelDTO();
		}
		return imovel;
	}

	public void setImovel( ImovelDTO imovel ) {
		this.imovel = imovel;
	}

	public ArrayList< ImovelDTO > getImoveis() {
		if ( imoveis == null ) {
			imoveis = new ArrayList<>();
		}
		return imoveis;
	}

	public void setImoveis( ArrayList< ImovelDTO > imoveis ) {
		this.imoveis = imoveis;
	}

	@Override
	public void limparCamposCadastro( HttpServletRequest request ) {
		setImovel( null );
	}

	@Override
	public void limparCamposConsulta( HttpServletRequest request ) {
		limparCamposCadastro( null );
	}

	@Override
	public void limparLista() {
		this.getImoveis().clear();
	}

}
