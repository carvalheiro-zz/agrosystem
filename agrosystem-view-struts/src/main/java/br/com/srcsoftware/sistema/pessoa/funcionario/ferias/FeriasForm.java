package br.com.srcsoftware.sistema.pessoa.funcionario.ferias;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import br.com.srcsoftware.modular.manager.abstracts.AbstractCRUDForm;

public final class FeriasForm extends AbstractCRUDForm{

	private static final long serialVersionUID = 1342719712529624093L;

	private FeriasDTO ferias;

	private ArrayList< FeriasDTO > lista;

	private String dataInicial;
	private String dataFinal;

	public String getDataInicial() {
		return this.dataInicial;
	}

	public void setDataInicial( String dataInicial ) {
		this.dataInicial = dataInicial;
	}

	public String getDataFinal() {
		return this.dataFinal;
	}

	public void setDataFinal( String dataFinal ) {
		this.dataFinal = dataFinal;
	}

	public FeriasDTO getFerias() {
		if ( ferias == null ) {
			ferias = new FeriasDTO();
		}
		return ferias;
	}

	public void setFerias( FeriasDTO ferias ) {
		this.ferias = ferias;
	}

	public ArrayList< FeriasDTO > getLista() {
		if ( lista == null ) {
			lista = new ArrayList<>();
		}
		return lista;
	}

	public void setLista( ArrayList< FeriasDTO > lista ) {
		this.lista = lista;
	}

	@Override
	public void limparCamposCadastro( HttpServletRequest request ) {
		setFerias( null );
		//getFerias().setTipo( FeriasDTO.TIPO_LANCAMENTO );
	}

	@Override
	public void limparCamposConsulta( HttpServletRequest request ) {
		setFerias( null );
		setDataInicial( null );
		setDataFinal( null );
	}

	@Override
	public void limparLista() {
		this.getLista().clear();
	}

}
