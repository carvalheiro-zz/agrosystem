package br.com.srcsoftware.sistema.notafiscal.simplesremessa;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import br.com.srcsoftware.modular.manager.abstracts.AbstractCRUDForm;
import br.com.srcsoftware.sistema.pedido.notafiscal.simplesremessa.ItemNotaFiscalSimplesRemessaDTO;
import br.com.srcsoftware.sistema.pedido.notafiscal.simplesremessa.NotaFiscalSimplesRemessaDTO;
import br.com.srcsoftware.sistema.pedido.notafiscal.venda.ItemNotaFiscalVendaDTO;

public final class NotaFiscalSimplesRemessaForm extends AbstractCRUDForm{

	private static final long serialVersionUID = 4850606525383582889L;

	private NotaFiscalSimplesRemessaDTO notaFiscalSimplesRemessa;

	private ArrayList< NotaFiscalSimplesRemessaDTO > notas;
	private String nomeFuncionarioReceptor;

	private String dataInicial;
	private String dataFinal;

	/** Quando Há ToMany */
	private String idItemNotaFiscalVendaFuturaRetornar;
	private String idItemNotaFiscalVendaFuturaReceber;
	private String quantidadeItensRetornar;
	private String quantidadeItensAdicionar;

	private ArrayList< ItemNotaFiscalVendaDTO > itensNotasFiscaisVendasFuturasRestantes;

	private ItemNotaFiscalSimplesRemessaDTO itemPesquisar;

	public ItemNotaFiscalSimplesRemessaDTO getItemPesquisar() {
		if ( itemPesquisar == null ) {
			itemPesquisar = new ItemNotaFiscalSimplesRemessaDTO();
		}
		return itemPesquisar;
	}

	public void setItemPesquisar( ItemNotaFiscalSimplesRemessaDTO itemPesquisar ) {
		this.itemPesquisar = itemPesquisar;
	}

	public NotaFiscalSimplesRemessaDTO getNotaFiscalSimplesRemessa() {
		if ( notaFiscalSimplesRemessa == null ) {
			notaFiscalSimplesRemessa = new NotaFiscalSimplesRemessaDTO();
		}
		return notaFiscalSimplesRemessa;
	}

	public void setNotaFiscalSimplesRemessa( NotaFiscalSimplesRemessaDTO notaFiscalSimplesRemessa ) {
		this.notaFiscalSimplesRemessa = notaFiscalSimplesRemessa;
	}

	public ArrayList< NotaFiscalSimplesRemessaDTO > getNotas() {
		if ( notas == null ) {
			notas = new ArrayList<>();
		}
		return notas;
	}

	public void setNotas( ArrayList< NotaFiscalSimplesRemessaDTO > notas ) {
		this.notas = notas;
	}

	public String getNomeFuncionarioReceptor() {
		return nomeFuncionarioReceptor;
	}

	public void setNomeFuncionarioReceptor( String nomeFuncionarioReceptor ) {
		this.nomeFuncionarioReceptor = nomeFuncionarioReceptor;
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

	public String getIdItemNotaFiscalVendaFuturaRetornar() {
		return idItemNotaFiscalVendaFuturaRetornar;
	}

	public void setIdItemNotaFiscalVendaFuturaRetornar( String idItemNotaFiscalVendaFuturaRetornar ) {
		this.idItemNotaFiscalVendaFuturaRetornar = idItemNotaFiscalVendaFuturaRetornar;
	}

	public String getIdItemNotaFiscalVendaFuturaReceber() {
		return idItemNotaFiscalVendaFuturaReceber;
	}

	public void setIdItemNotaFiscalVendaFuturaReceber( String idItemNotaFiscalVendaFuturaReceber ) {
		this.idItemNotaFiscalVendaFuturaReceber = idItemNotaFiscalVendaFuturaReceber;
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

	public ArrayList< ItemNotaFiscalVendaDTO > getItensNotasFiscaisVendasFuturasRestantes() {
		if ( itensNotasFiscaisVendasFuturasRestantes == null ) {
			itensNotasFiscaisVendasFuturasRestantes = new ArrayList<>();
		}
		return itensNotasFiscaisVendasFuturasRestantes;
	}

	public void setItensNotasFiscaisVendasFuturasRestantes( ArrayList< ItemNotaFiscalVendaDTO > itensNotasFiscaisVendasFuturasRestantes ) {
		this.itensNotasFiscaisVendasFuturasRestantes = itensNotasFiscaisVendasFuturasRestantes;
	}

	@Override
	public void limparCamposCadastro( HttpServletRequest request ) {
		setNotaFiscalSimplesRemessa( null );
		this.setItensNotasFiscaisVendasFuturasRestantes( null );
		this.limparCamposItem();
	}

	@Override
	public void limparCamposConsulta( HttpServletRequest request ) {
		this.setDataFinal( null );
		this.setDataInicial( null );
		setNomeFuncionarioReceptor( null );
		setItemPesquisar( null );
	}

	@Override
	public void limparLista() {
		this.getNotas().clear();
	}

	public void limparCamposItem() {
		this.setIdItemNotaFiscalVendaFuturaReceber( null );
		this.setIdItemNotaFiscalVendaFuturaRetornar( null );
		setQuantidadeItensAdicionar( null );
		setQuantidadeItensRetornar( null );
	}
}
