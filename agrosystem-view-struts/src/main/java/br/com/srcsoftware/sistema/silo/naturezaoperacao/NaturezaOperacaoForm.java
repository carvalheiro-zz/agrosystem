package br.com.srcsoftware.sistema.silo.naturezaoperacao;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import br.com.srcsoftware.modular.manager.abstracts.AbstractCRUDForm;

public final class NaturezaOperacaoForm extends AbstractCRUDForm {

	private static final long serialVersionUID = 1342719712529624093L;

	private NaturezaOperacaoDTO naturezaOperacao;

	private ArrayList< NaturezaOperacaoDTO > operacoes;

	public NaturezaOperacaoDTO getNaturezaOperacao() {
		if ( naturezaOperacao == null ) {
			naturezaOperacao = new NaturezaOperacaoDTO();
		}
		return naturezaOperacao;
	}

	public void setNaturezaOperacao( NaturezaOperacaoDTO naturezaOperacao ) {
		this.naturezaOperacao = naturezaOperacao;
	}

	public ArrayList< NaturezaOperacaoDTO > getOperacoes() {
		if ( operacoes == null ) {
			operacoes = new ArrayList<>();
		}
		return operacoes;
	}

	public void setOperacoes( ArrayList< NaturezaOperacaoDTO > operacoes ) {
		this.operacoes = operacoes;
	}

	@Override
	public void limparCamposCadastro( HttpServletRequest request ) {
		setNaturezaOperacao( null );
	}

	@Override
	public void limparCamposConsulta( HttpServletRequest request ) {

	}

	@Override
	public void limparLista() {
		this.getOperacoes().clear();
	}

}
