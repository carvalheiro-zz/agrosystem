package br.com.srcsoftware.sistema.item.imovel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.com.srcsoftware.modular.manager.abstracts.AbstractCRUDForm;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.aplicacao.item.ItemDTO;

public final class ItemImovelForm extends AbstractCRUDForm{

	private static final long serialVersionUID = 1342719712529624093L;

	private ItemDTO itemImovel;

	private ArrayList< ItemDTO > itens;

	private String dataInicial;
	private String dataFinal;

	private String estoque;

	private String totalQuantidade;
	private String totalValor;

	public void calcularTotais( List< ItemDTO > encontradosParaTotais ) {

		BigDecimal totalQuantidade = BigDecimal.ZERO;
		BigDecimal totalValor = BigDecimal.ZERO;

		for ( ItemDTO aplicacaoCorrente : encontradosParaTotais ) {

			BigDecimal valor = Utilidades.parseBigDecimal( aplicacaoCorrente.getCustoTotal() );
			totalValor = totalValor.add( valor );

			BigDecimal quantidade = Utilidades.parseBigDecimal( aplicacaoCorrente.getQuantidade() );
			totalQuantidade = totalQuantidade.add( quantidade );
		}

		setTotalValor( Utilidades.parseBigDecimal( totalValor ) );
		setTotalQuantidade( Utilidades.parseBigDecimal( totalQuantidade ) );

	}

	public void setTotalQuantidade( String totalQuantidade ) {
		this.totalQuantidade = totalQuantidade;
	}

	public void setTotalValor( String totalValor ) {
		this.totalValor = totalValor;
	}

	public String getTotalQuantidade() {
		return totalQuantidade;
	}

	public String getTotalValor() {
		return totalValor;
	}

	public String getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial( String dataInicial ) {
		this.dataInicial = dataInicial;
	}

	public String getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal( String dataFinal ) {
		this.dataFinal = dataFinal;
	}

	public String getEstoque() {
		return estoque;
	}

	public void setEstoque( String estoque ) {
		this.estoque = estoque;
	}

	public ItemDTO getItemImovel() {
		if ( itemImovel == null ) {
			itemImovel = new ItemDTO();
		}
		return itemImovel;
	}

	public void setItemImovel( ItemDTO itemImovel ) {
		this.itemImovel = itemImovel;
	}

	public ArrayList< ItemDTO > getItens() {
		if ( itens == null ) {
			itens = new ArrayList<>();
		}
		return itens;
	}

	public void setItens( ArrayList< ItemDTO > itens ) {
		this.itens = itens;
	}

	@Override
	public void limparCamposCadastro( HttpServletRequest request ) {
		setItemImovel( null );
		setEstoque( null );
	}

	@Override
	public void limparCamposConsulta( HttpServletRequest request ) {
		this.setDataFinal( null );
		this.setDataInicial( null );
	}

	@Override
	public void limparLista() {
		this.getItens().clear();
	}
}
