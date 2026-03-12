package br.com.srcsoftware.sistema.notafiscal.venda;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import br.com.srcsoftware.modular.manager.abstracts.AbstractCRUDForm;
import br.com.srcsoftware.sistema.pedido.itempedido.ItemPedidoDTO;
import br.com.srcsoftware.sistema.pedido.notafiscal.venda.ItemNotaFiscalVendaDTO;
import br.com.srcsoftware.sistema.pedido.notafiscal.venda.NotaFiscalVendaDTO;

public final class NotaFiscalVendaForm extends AbstractCRUDForm{

	private static final long serialVersionUID = 4850606525383582889L;

	private String tipoNF;

	private NotaFiscalVendaDTO notaFiscalVenda;

	private ArrayList< NotaFiscalVendaDTO > notas;
	private String nomeFuncionarioReceptor;

	private String dataInicial;
	private String dataFinal;

	/** Quando Há ToMany */
	private String idItemPedidoRetornar;
	private String idItemPedidoReceber;
	private String quantidadeItensRetornar;
	private String quantidadeItensAdicionar;

	private ArrayList< ItemPedidoDTO > itensPedidosRestantes;

	private ItemNotaFiscalVendaDTO itemPesquisar;

	public ItemNotaFiscalVendaDTO getItemPesquisar() {
		if ( itemPesquisar == null ) {
			itemPesquisar = new ItemNotaFiscalVendaDTO();
		}
		return itemPesquisar;
	}

	public void setItemPesquisar( ItemNotaFiscalVendaDTO itemPesquisar ) {
		this.itemPesquisar = itemPesquisar;
	}

	public String getNomeFuncionarioReceptor() {
		return nomeFuncionarioReceptor;
	}

	public void setNomeFuncionarioReceptor( String nomeFuncionarioReceptor ) {
		this.nomeFuncionarioReceptor = nomeFuncionarioReceptor;
	}

	public String getTipoNF() {
		return tipoNF;
	}

	public void setTipoNF( String tipoNF ) {
		this.tipoNF = tipoNF;
	}

	public String getIdItemPedidoRetornar() {
		return idItemPedidoRetornar;
	}

	public void setIdItemPedidoRetornar( String idItemPedidoRetornar ) {
		this.idItemPedidoRetornar = idItemPedidoRetornar;
	}

	public String getIdItemPedidoReceber() {
		return idItemPedidoReceber;
	}

	public void setIdItemPedidoReceber( String idItemPedidoReceber ) {
		this.idItemPedidoReceber = idItemPedidoReceber;
	}

	public String getQuantidadeItensRetornar() {
		return quantidadeItensRetornar;
	}

	public void setQuantidadeItensRetornar( String quantidadeItensRetornar ) {
		this.quantidadeItensRetornar = quantidadeItensRetornar;
	}

	public String getQuantidadeItensAdicionar() {
		return quantidadeItensAdicionar;
	}

	public void setQuantidadeItensAdicionar( String quantidadeItensAdicionar ) {
		this.quantidadeItensAdicionar = quantidadeItensAdicionar;
	}

	public ArrayList< ItemPedidoDTO > getItensPedidosRestantes() {
		if ( this.itensPedidosRestantes == null ) {
			this.itensPedidosRestantes = new ArrayList<>();
		}
		return itensPedidosRestantes;
	}

	public void setItensPedidosRestantes( ArrayList< ItemPedidoDTO > itensPedidosRestantes ) {
		this.itensPedidosRestantes = itensPedidosRestantes;
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

	public ArrayList< NotaFiscalVendaDTO > getNotas() {
		if ( this.notas == null ) {
			this.notas = new ArrayList<>();
		}
		return this.notas;
	}

	public void setNotas( ArrayList< NotaFiscalVendaDTO > notas ) {
		this.notas = notas;
	}

	public NotaFiscalVendaDTO getNotaFiscalVenda() {
		if ( this.notaFiscalVenda == null ) {
			this.notaFiscalVenda = new NotaFiscalVendaDTO();
		}
		return this.notaFiscalVenda;
	}

	public void setNotaFiscalVenda( NotaFiscalVendaDTO notaFiscalVenda ) {
		this.notaFiscalVenda = notaFiscalVenda;
	}

	@Override
	public void limparCamposCadastro( HttpServletRequest request ) {
		setNotaFiscalVenda( null );

		this.getNotaFiscalVenda().setTipo( NotaFiscalVendaDTO.TIPO_VENDA );
		this.setItensPedidosRestantes( null );

		this.limparCamposItem();

		this.getNotaFiscalVenda().setTipo( this.getTipoNF() );
	}

	@Override
	public void limparCamposConsulta( HttpServletRequest request ) {
		this.setDataFinal( null );
		this.setDataInicial( null );
		setNomeFuncionarioReceptor( null );

		this.getNotaFiscalVenda().setTipo( this.getTipoNF() );
		setItemPesquisar( null );
	}

	@Override
	public void limparLista() {
		this.getNotas().clear();
	}

	public void limparCamposItem() {
		this.setIdItemPedidoReceber( null );
		this.setIdItemPedidoRetornar( null );
	}
}
