package br.com.srcsoftware.sistema.notafiscal.direta;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import br.com.srcsoftware.modular.manager.abstracts.AbstractCRUDForm;
import br.com.srcsoftware.modular.manager.utilidades.DateUtil;
import br.com.srcsoftware.sistema.notafiscal.direta.item.ItemNotaFiscalVendaDiretaDTO;

public final class NotaFiscalVendaDiretaForm extends AbstractCRUDForm{

	private static final long serialVersionUID = 4850606525383582889L;

	private NotaFiscalVendaDiretaDTO notaFiscalVendaDireta;

	private ArrayList< NotaFiscalVendaDiretaDTO > notas;

	private String dataInicial;
	private String dataFinal;

	private ItemNotaFiscalVendaDiretaDTO itemPesquisar;

	/** Quando Há ToMany */
	private ItemNotaFiscalVendaDiretaDTO itemAdicionar;
	private String idItemRemover;

	public ItemNotaFiscalVendaDiretaDTO getItemPesquisar() {
		if ( this.itemPesquisar == null ) {
			this.itemPesquisar = new ItemNotaFiscalVendaDiretaDTO();
		}
		return this.itemPesquisar;
	}

	public void setItemPesquisar( ItemNotaFiscalVendaDiretaDTO itemPesquisar ) {
		this.itemPesquisar = itemPesquisar;
	}

	public ItemNotaFiscalVendaDiretaDTO getItemAdicionar() {
		if ( this.itemAdicionar == null ) {
			this.itemAdicionar = new ItemNotaFiscalVendaDiretaDTO();
		}
		return this.itemAdicionar;
	}

	public void setItemAdicionar( ItemNotaFiscalVendaDiretaDTO itemAdicionar ) {
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

	public ArrayList< NotaFiscalVendaDiretaDTO > getNotas() {
		if ( this.notas == null ) {
			this.notas = new ArrayList<>();
		}
		return this.notas;
	}

	public void setNotas( ArrayList< NotaFiscalVendaDiretaDTO > notas ) {
		this.notas = notas;
	}

	public NotaFiscalVendaDiretaDTO getNotaFiscalVendaDireta() {
		if ( this.notaFiscalVendaDireta == null ) {
			this.notaFiscalVendaDireta = new NotaFiscalVendaDiretaDTO();
		}
		return this.notaFiscalVendaDireta;
	}

	public void setNotaFiscalVendaDireta( NotaFiscalVendaDiretaDTO notaFiscalVendaDireta ) {
		this.notaFiscalVendaDireta = notaFiscalVendaDireta;
	}

	@Override
	public void limparCamposCadastro( HttpServletRequest request ) {
		setNotaFiscalVendaDireta( null );

		this.getNotaFiscalVendaDireta().setData( DateUtil.parseLocalDate( LocalDate.now() ) );

		this.getNotaFiscalVendaDireta().setStatus( NotaFiscalVendaDiretaDTO.STATUS_FINALIZADO );
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
		this.getNotas().clear();
	}

	public void limparCamposItem() {
		this.setItemAdicionar( null );
		this.setIdItemRemover( null );
	}
}
