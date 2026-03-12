package br.com.srcsoftware.sistema.manutencao.servico;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import br.com.srcsoftware.modular.manager.abstracts.AbstractCRUDForm;

public final class ServicoForm extends AbstractCRUDForm{

	private static final long serialVersionUID = 1342719712529624093L;

	private ServicoDTO servico;

	private ArrayList< ServicoDTO > servicos;

	public ServicoDTO getServico() {
		if ( servico == null ) {
			servico = new ServicoDTO();
		}
		return servico;
	}

	public void setServico( ServicoDTO servico ) {
		this.servico = servico;
	}

	public ArrayList< ServicoDTO > getServicos() {
		if ( servicos == null ) {
			servicos = new ArrayList<>();
		}
		return servicos;
	}

	public void setServicos( ArrayList< ServicoDTO > servicos ) {
		this.servicos = servicos;
	}

	@Override
	public void limparCamposCadastro( HttpServletRequest request ) {
		setServico( null );
	}

	@Override
	public void limparCamposConsulta( HttpServletRequest request ) {
		limparCamposCadastro( null );
	}

	@Override
	public void limparLista() {
		this.getServicos().clear();
	}

}
