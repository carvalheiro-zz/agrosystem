package br.com.srcsoftware.sistema.estoque.estoque;

import java.util.ArrayList;

import br.com.srcsoftware.modular.manager.abstracts.AbstractUsuarioForm;
import br.com.srcsoftware.sistema.estoque.estoque.pojo.EstoquePOJO;

public class EstoqueForm extends AbstractUsuarioForm{

	private static final long serialVersionUID = 1342719712529624093L;

	private String idProduto;
	private String nomeProduto;

	private String localizacaoProduto;

	private String idMarca;
	private String nomeMarca;

	private String idTipo;
	private String nomeTipo;

	private String negativos;

	private ArrayList< EstoquePOJO > estoques;

	public String getNegativos() {
		return negativos;
	}

	public void setNegativos( String negativos ) {
		this.negativos = negativos;
	}

	public String getNomeMarca() {
		return nomeMarca;
	}

	public void setNomeMarca( String nomeMarca ) {
		this.nomeMarca = nomeMarca;
	}

	public String getNomeTipo() {
		return nomeTipo;
	}

	public void setNomeTipo( String nomeTipo ) {
		this.nomeTipo = nomeTipo;
	}

	public String getLocalizacaoProduto() {
		return localizacaoProduto;
	}

	public void setLocalizacaoProduto( String localizacaoProduto ) {
		this.localizacaoProduto = localizacaoProduto;
	}

	public String getIdMarca() {
		return idMarca;
	}

	public void setIdMarca( String idMarca ) {
		this.idMarca = idMarca;
	}

	public String getIdTipo() {
		return idTipo;
	}

	public void setIdTipo( String idTipo ) {
		this.idTipo = idTipo;
	}

	public String getIdProduto() {
		return idProduto;
	}

	public void setIdProduto( String idProduto ) {
		this.idProduto = idProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto( String nomeProduto ) {
		this.nomeProduto = nomeProduto;
	}

	public ArrayList< EstoquePOJO > getEstoques() {
		if ( estoques == null ) {
			estoques = new ArrayList<>();
		}
		return estoques;
	}

	public void setEstoques( ArrayList< EstoquePOJO > estoques ) {
		this.estoques = estoques;
	}

	public void limparCamposConsulta() {
		setIdProduto( null );
		setNomeProduto( null );
		setLocalizacaoProduto( null );
		setIdMarca( null );
		setNomeMarca( null );
		setIdTipo( null );
		setNomeTipo( null );
		setNegativos( null );
	}

	public void limparLista() {
		this.getEstoques().clear();
	}
}
