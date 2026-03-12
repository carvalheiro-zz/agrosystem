package br.com.srcsoftware.sistema.pessoa.fornecedor.juridico;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import br.com.srcsoftware.modular.manager.abstracts.AbstractCRUDForm;

public final class FornecedorForm extends AbstractCRUDForm{

	private static final long serialVersionUID = 1342719712529624093L;

	private FornecedorJuridicoDTO fornecedorJuridico;

	private ArrayList< FornecedorJuridicoDTO > fornecedores;

	public FornecedorJuridicoDTO getFornecedorJuridico() {
		if ( fornecedorJuridico == null ) {
			fornecedorJuridico = new FornecedorJuridicoDTO();
		}
		return fornecedorJuridico;
	}

	public void setFornecedorJuridico( FornecedorJuridicoDTO fornecedorJuridico ) {
		this.fornecedorJuridico = fornecedorJuridico;
	}

	public ArrayList< FornecedorJuridicoDTO > getFornecedores() {
		if ( fornecedores == null ) {
			fornecedores = new ArrayList<>();
		}
		return fornecedores;
	}

	public void setFornecedores( ArrayList< FornecedorJuridicoDTO > fornecedores ) {
		this.fornecedores = fornecedores;
	}

	@Override
	public void limparCamposCadastro( HttpServletRequest request ) {
		setFornecedorJuridico( null );
	}

	@Override
	public void limparCamposConsulta( HttpServletRequest request ) {

	}

	@Override
	public void limparLista() {
		this.getFornecedores().clear();
	}

}
