package br.com.srcsoftware.sistema.cupom;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import br.com.srcsoftware.modular.manager.abstracts.AbstractCRUDForm;
import br.com.srcsoftware.sistema.cupom.item.ItemCupomDTO;

public final class CupomForm extends AbstractCRUDForm{

	private static final long serialVersionUID = 4850606525383582889L;

	private CupomDTO cupom;

	private ArrayList< CupomDTO > cupons;

	private String dataInicial;
	private String dataFinal;

	private ItemCupomDTO itemPesquisar;

	/** Quando Há ToMany */
	private ItemCupomDTO itemAdicionar;
	private String idItemRemover;

	public ItemCupomDTO getItemPesquisar() {
		if ( this.itemPesquisar == null ) {
			this.itemPesquisar = new ItemCupomDTO();
		}
		return this.itemPesquisar;
	}

	public void setItemPesquisar( ItemCupomDTO itemPesquisar ) {
		this.itemPesquisar = itemPesquisar;
	}

	public ItemCupomDTO getItemAdicionar() {
		if ( this.itemAdicionar == null ) {
			this.itemAdicionar = new ItemCupomDTO();
		}
		return this.itemAdicionar;
	}

	public void setItemAdicionar( ItemCupomDTO itemAdicionar ) {
		this.itemAdicionar = itemAdicionar;
	}

	public String getIdItemRemover() {
		return this.idItemRemover;
	}

	public void setIdItemRemover( String idItemRemover ) {
		this.idItemRemover = idItemRemover;
	}

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

	public ArrayList< CupomDTO > getCupons() {
		if ( this.cupons == null ) {
			this.cupons = new ArrayList<>();
		}
		return this.cupons;
	}

	public void setCupons( ArrayList< CupomDTO > cupons ) {
		this.cupons = cupons;
	}

	public CupomDTO getCupom() {
		if ( this.cupom == null ) {
			this.cupom = new CupomDTO();
		}
		return this.cupom;
	}

	public void setCupom( CupomDTO cupom ) {
		this.cupom = cupom;
	}

	@Override
	public void limparCamposCadastro( HttpServletRequest request ) {
		setCupom( null );
		limparCamposItem();
	}

	@Override
	public void limparCamposConsulta( HttpServletRequest request ) {
		this.setDataFinal( null );
		this.setDataInicial( null );
		setItemPesquisar( null );
	}

	@Override
	public void limparLista() {
		this.getCupons().clear();
	}

	public void limparCamposItem() {
		this.setItemAdicionar( null );
		this.setIdItemRemover( null );
	}
}
