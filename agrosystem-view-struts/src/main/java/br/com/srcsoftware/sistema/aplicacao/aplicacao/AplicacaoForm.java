package br.com.srcsoftware.sistema.aplicacao.aplicacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.com.srcsoftware.modular.manager.abstracts.AbstractCRUDForm;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.utilidades.DateUtil;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;

public final class AplicacaoForm extends AbstractCRUDForm {

	private static final long serialVersionUID = 1342719712529624093L;

	private AplicacaoDTO aplicacao;

	private ArrayList<AplicacaoDTO> aplicacoes;

	private String dataInicial;
	private String dataFinal;

	private String estoque;

	private String totalQuantidade;
	private String totalValor;

	public void calcularTotais(List<AplicacaoDTO> encontradosParaTotais) {

		BigDecimal totalQuantidade = BigDecimal.ZERO;
		BigDecimal totalValor = BigDecimal.ZERO;

		for (AplicacaoDTO aplicacaoCorrente : encontradosParaTotais) {

			BigDecimal valor = Utilidades.parseBigDecimal(aplicacaoCorrente.getCustoTotal());
			totalValor = totalValor.add(valor);

			BigDecimal quantidade = Utilidades.parseBigDecimal(aplicacaoCorrente.getQuantidade());
			totalQuantidade = totalQuantidade.add(quantidade);
		}

		setTotalValor(Utilidades.parseBigDecimal(totalValor));
		setTotalQuantidade(Utilidades.parseBigDecimal(totalQuantidade));

	}

	public void setTotalQuantidade(String totalQuantidade) {
		this.totalQuantidade = totalQuantidade;
	}

	public void setTotalValor(String totalValor) {
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

	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

	public String getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getEstoque() {
		return estoque;
	}

	public void setEstoque(String estoque) {
		this.estoque = estoque;
	}

	public AplicacaoDTO getAplicacao() {
		if (aplicacao == null) {
			aplicacao = new AplicacaoDTO();
		}
		return aplicacao;
	}

	public void setAplicacao(AplicacaoDTO aplicacao) {
		this.aplicacao = aplicacao;
	}

	public ArrayList<AplicacaoDTO> getAplicacoes() {
		if (aplicacoes == null) {
			aplicacoes = new ArrayList<>();
		}
		return aplicacoes;
	}

	public void setAplicacoes(ArrayList<AplicacaoDTO> aplicacoes) {
		this.aplicacoes = aplicacoes;
	}

	@Override
	public void limparCamposCadastro(HttpServletRequest request) {
		setAplicacao(null);
		setEstoque(null);
	}

	@Override
	public void limparCamposConsulta(HttpServletRequest request) {
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
		this.getAplicacoes().clear();
	}

}
