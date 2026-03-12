package br.com.srcsoftware.sistema.notafiscal.cupom;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import br.com.srcsoftware.modular.manager.abstracts.AbstractCRUDForm;
import br.com.srcsoftware.sistema.notafiscalcupom.NotaFiscalCupomDTO;

public final class NotaFiscalCupomForm extends AbstractCRUDForm{

	private static final long serialVersionUID = 4850606525383582889L;

	private NotaFiscalCupomDTO notaFiscalCupom;

	private ArrayList< NotaFiscalCupomDTO > notas;

	private String dataInicial;
	private String dataFinal;

	private String valorCustoTotalAux;

	public String getValorCustoTotalAux() {
		return valorCustoTotalAux;
	}

	public void setValorCustoTotalAux( String valorCustoTotalAux ) {
		this.valorCustoTotalAux = valorCustoTotalAux;
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

	public ArrayList< NotaFiscalCupomDTO > getNotas() {
		if ( this.notas == null ) {
			this.notas = new ArrayList<>();
		}
		return this.notas;
	}

	public void setNotas( ArrayList< NotaFiscalCupomDTO > notas ) {
		this.notas = notas;
	}

	public NotaFiscalCupomDTO getNotaFiscalCupom() {
		if ( this.notaFiscalCupom == null ) {
			this.notaFiscalCupom = new NotaFiscalCupomDTO();
		}
		return this.notaFiscalCupom;
	}

	public void setNotaFiscalCupom( NotaFiscalCupomDTO notaFiscalCupom ) {
		this.notaFiscalCupom = notaFiscalCupom;
	}

	@Override
	public void limparCamposCadastro( HttpServletRequest request ) {
		setNotaFiscalCupom( null );
	}

	@Override
	public void limparCamposConsulta( HttpServletRequest request ) {
		this.setDataFinal( null );
		this.setDataInicial( null );
	}

	@Override
	public void limparLista() {
		this.getNotas().clear();
	}

}
