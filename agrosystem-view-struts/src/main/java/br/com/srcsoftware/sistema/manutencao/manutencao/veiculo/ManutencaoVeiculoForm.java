package br.com.srcsoftware.sistema.manutencao.manutencao.veiculo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.com.srcsoftware.modular.manager.abstracts.AbstractCRUDForm;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.manutencao.manutencao.ManutencaoDTO;

public final class ManutencaoVeiculoForm extends AbstractCRUDForm{

	private static final long serialVersionUID = 1342719712529624093L;

	private ManutencaoDTO manutencaoVeiculo;

	private ArrayList< ManutencaoDTO > manutencoes;

	private String dataInicial;
	private String dataFinal;

	private String totalValor;

	public void calcularTotais( List< ManutencaoDTO > encontradosParaTotais ) {

		BigDecimal totalValor = BigDecimal.ZERO;

		for ( ManutencaoDTO aplicacaoCorrente : encontradosParaTotais ) {

			BigDecimal valor = Utilidades.parseBigDecimal( aplicacaoCorrente.getCusto() );
			totalValor = totalValor.add( valor );
		}

		setTotalValor( Utilidades.parseBigDecimal( totalValor ) );

	}

	public void setTotalValor( String totalValor ) {
		this.totalValor = totalValor;
	}

	public String getTotalValor() {
		return totalValor;
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

	public ArrayList< ManutencaoDTO > getManutencoes() {
		if ( this.manutencoes == null ) {
			this.manutencoes = new ArrayList<>();
		}
		return this.manutencoes;
	}

	public void setManutencoes( ArrayList< ManutencaoDTO > manutencoes ) {
		this.manutencoes = manutencoes;
	}

	public ManutencaoDTO getManutencaoVeiculo() {
		if ( this.manutencaoVeiculo == null ) {
			this.manutencaoVeiculo = new ManutencaoDTO();
		}
		return this.manutencaoVeiculo;
	}

	public void setManutencaoVeiculo( ManutencaoDTO manutencaoVeiculo ) {
		this.manutencaoVeiculo = manutencaoVeiculo;
	}

	@Override
	public void limparCamposCadastro( HttpServletRequest request ) {
		setManutencaoVeiculo( null );
	}

	@Override
	public void limparCamposConsulta( HttpServletRequest request ) {
		setDataInicial( null );
		setDataFinal( null );
		limparCamposCadastro( request );
	}

	@Override
	public void limparLista() {
		this.getManutencoes().clear();
	}

}
