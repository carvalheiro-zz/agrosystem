package br.com.srcsoftware.sistema.silo.contratovenda;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import br.com.srcsoftware.modular.manager.abstracts.AbstractCRUDForm;
import br.com.srcsoftware.sistema.silo.entrada.InformacoesProducaoSafraPOJO;
import br.com.srcsoftware.sistema.silo.silo.InformacoesSiloPOJO;

public final class ContratoVendaForm extends AbstractCRUDForm{

	private static final long serialVersionUID = 1342719712529624093L;

	private ContratoVendaDTO contratoVenda;

	private ArrayList< ContratoVendaDTO > contratos;

	private ArrayList< InformacoesSiloPOJO > silos;
	private ArrayList< InformacoesProducaoSafraPOJO > safras;

	private String dataInicial;
	private String dataFinal;

	public ArrayList< InformacoesProducaoSafraPOJO > getSafras() {
		if ( this.safras == null ) {
			this.safras = new ArrayList<>();
		}
		return safras;
	}

	public void setSafras( ArrayList< InformacoesProducaoSafraPOJO > safras ) {
		this.safras = safras;
	}

	public ArrayList< InformacoesSiloPOJO > getSilos() {
		if ( this.silos == null ) {
			this.silos = new ArrayList<>();
		}
		return silos;
	}

	public void setSilos( ArrayList< InformacoesSiloPOJO > silos ) {
		this.silos = silos;
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

	public ArrayList< ContratoVendaDTO > getContratos() {
		if ( this.contratos == null ) {
			this.contratos = new ArrayList<>();
		}
		return this.contratos;
	}

	public void setContratos( ArrayList< ContratoVendaDTO > contratos ) {
		this.contratos = contratos;
	}

	public ContratoVendaDTO getContratoVenda() {
		if ( this.contratoVenda == null ) {
			this.contratoVenda = new ContratoVendaDTO();
		}
		return this.contratoVenda;
	}

	public void setContratoVenda( ContratoVendaDTO contratoVenda ) {
		this.contratoVenda = contratoVenda;
	}

	@Override
	public void limparCamposCadastro( HttpServletRequest request ) {
		setContratoVenda( null );
	}

	@Override
	public void limparCamposConsulta( HttpServletRequest request ) {
		setDataInicial( null );
		setDataFinal( null );
		limparCamposCadastro( request );
	}

	@Override
	public void limparLista() {
		this.getContratos().clear();
	}

}
