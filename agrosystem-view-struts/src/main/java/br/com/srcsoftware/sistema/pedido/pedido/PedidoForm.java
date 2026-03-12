package br.com.srcsoftware.sistema.pedido.pedido;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import br.com.srcsoftware.modular.manager.abstracts.AbstractCRUDForm;
import br.com.srcsoftware.sistema.pedido.itempedido.ItemPedidoDTO;

public final class PedidoForm extends AbstractCRUDForm{

	private static final long serialVersionUID = 4850606525383582889L;

	private PedidoDTO pedido;

	private ArrayList< PedidoDTO > pedidos;

	private String dataInicial;
	private String dataFinal;

	private ItemPedidoDTO itemPesquisar;

	/** Quando Há ToMany */
	private ItemPedidoDTO itemAdicionar;
	private String idItemRemover;

	private boolean permiteEmitirNota;

	public ItemPedidoDTO getItemPesquisar() {
		if ( this.itemPesquisar == null ) {
			this.itemPesquisar = new ItemPedidoDTO();
		}
		return itemPesquisar;
	}

	public void setItemPesquisar( ItemPedidoDTO itemPesquisar ) {
		this.itemPesquisar = itemPesquisar;
	}

	public boolean isPermiteEmitirNota() {
		return this.permiteEmitirNota;
	}

	public void setPermiteEmitirNota( boolean permiteEmitirNota ) {
		this.permiteEmitirNota = permiteEmitirNota;
	}

	public ItemPedidoDTO getItemAdicionar() {
		if ( this.itemAdicionar == null ) {
			this.itemAdicionar = new ItemPedidoDTO();
		}
		return this.itemAdicionar;
	}

	public void setItemAdicionar( ItemPedidoDTO itemAdicionar ) {
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

	public ArrayList< PedidoDTO > getPedidos() {
		if ( this.pedidos == null ) {
			this.pedidos = new ArrayList<>();
		}
		return this.pedidos;
	}

	public void setPedidos( ArrayList< PedidoDTO > pedidos ) {
		this.pedidos = pedidos;
	}

	public PedidoDTO getPedido() {
		if ( this.pedido == null ) {
			this.pedido = new PedidoDTO();
		}
		return this.pedido;
	}

	public void setPedido( PedidoDTO pedido ) {
		this.pedido = pedido;
	}

	@Override
	public void limparCamposCadastro( HttpServletRequest request ) {
		setPedido( null );
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
		this.getPedidos().clear();
	}

	public void limparCamposItem() {
		this.setItemAdicionar( null );
		this.setIdItemRemover( null );
	}
}
