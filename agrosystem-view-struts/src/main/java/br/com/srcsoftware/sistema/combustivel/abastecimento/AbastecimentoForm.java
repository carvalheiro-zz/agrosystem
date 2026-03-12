package br.com.srcsoftware.sistema.combustivel.abastecimento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.com.srcsoftware.modular.manager.abstracts.AbstractCRUDForm;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.utilidades.DateUtil;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;

public final class AbastecimentoForm extends AbstractCRUDForm{

	private static final long serialVersionUID = 1342719712529624093L;

	private AbastecimentoDTO abastecimento;

	private ArrayList< AbastecimentoDTO > abastecimentos;

	private String dataInicial;
	private String dataFinal;

	private String estoque;

	private String totalQuantidade;
	private String totalValor;

	public void calcularTotais( List< AbastecimentoDTO > encontradosParaTotais ) {

		BigDecimal totalQuantidade = BigDecimal.ZERO;
		BigDecimal totalValor = BigDecimal.ZERO;

		for ( AbastecimentoDTO aplicacaoCorrente : encontradosParaTotais ) {

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

	public AbastecimentoDTO getAbastecimento() {
		if ( abastecimento == null ) {
			abastecimento = new AbastecimentoDTO();
		}
		return abastecimento;
	}

	public void setAbastecimento( AbastecimentoDTO abastecimento ) {
		this.abastecimento = abastecimento;
	}

	public ArrayList< AbastecimentoDTO > getAbastecimentos() {
		if ( abastecimentos == null ) {
			abastecimentos = new ArrayList<>();
		}
		return abastecimentos;
	}

	public void setAbastecimentos( ArrayList< AbastecimentoDTO > abastecimentos ) {
		this.abastecimentos = abastecimentos;
	}

	@Override
	public void limparCamposCadastro( HttpServletRequest request ) {
		setAbastecimento( null );
		setEstoque( null );
	}

	@Override
	public void limparCamposConsulta( HttpServletRequest request ) {
		try {
			// Garante que os campos de Data venha preenchidos com a data de atual
			LocalDate dataInicialParam = DateUtil.getPrimeiroDiaMesFromData(LocalDate.now());
			LocalDate dataFinalParam = DateUtil.getUltimoDiaMesFromData(LocalDate.now());
			setDataInicial(DateUtil.parseLocalDate(dataInicialParam));
			setDataFinal(DateUtil.parseLocalDate(dataFinalParam));
			//this.setDataFinal(null);
			//this.setDataInicial(null);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void limparLista() {
		this.getAbastecimentos().clear();
	}

}
