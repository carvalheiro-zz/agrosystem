package br.com.srcsoftware.sistema.gerenciamento.demonstrativolivroponto.ferias;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.com.srcsoftware.modular.manager.abstracts.AbstractUsuarioForm;
import br.com.srcsoftware.modular.manager.utilidades.DateUtil;
import br.com.srcsoftware.sistema.pessoa.funcionario.ferias.SaldoFeriasPOJO;

public final class DemonstrativoLivroPontoFeriasForm extends AbstractUsuarioForm{

	private static final long serialVersionUID = 1342719712529624093L;

	private String idColaborador;
	private String nomeColaborador;
	private String dataInicial;
	private String dataFinal;

	private String textoIntervaloPesquisa;
	private String saldo;

	private List< SaldoFeriasPOJO > saldosFerias = new ArrayList<>();

	public String getSaldo() {
		return saldo;
	}

	public void setSaldo( String saldo ) {
		this.saldo = saldo;
	}

	public String getTextoIntervaloPesquisa() {
		return textoIntervaloPesquisa;
	}

	public void setTextoIntervaloPesquisa( String textoIntervaloPesquisa ) {
		this.textoIntervaloPesquisa = textoIntervaloPesquisa;
	}

	public String getNomeColaborador() {
		return nomeColaborador;
	}

	public void setNomeColaborador( String nomeColaborador ) {
		this.nomeColaborador = nomeColaborador;
	}

	public String getIdColaborador() {
		return idColaborador;
	}

	public void setIdColaborador( String idColaborador ) {
		this.idColaborador = idColaborador;
	}

	public List< SaldoFeriasPOJO > getSaldosFerias() {
		if ( this.saldosFerias == null ) {
			this.saldosFerias = new ArrayList<>();
		}
		return saldosFerias;
	}

	public void setSaldosFerias( List< SaldoFeriasPOJO > saldosFerias ) {
		this.saldosFerias = saldosFerias;
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

	public void limparTudo() {
		setIdColaborador( null );
		setNomeColaborador( null );
		setDataInicial( DateUtil.parseLocalDate( LocalDate.now().withDayOfMonth( 1 ) ) );
		setDataFinal( DateUtil.parseLocalDate( LocalDate.now().with( TemporalAdjusters.lastDayOfMonth() ) ) );
		setTextoIntervaloPesquisa( null );
		getSaldosFerias().clear();
	}

	public void limparMensagens( HttpServletRequest request ) {
		request.getSession().removeAttribute( "erroAjax" );
		request.getSession().removeAttribute( "sucessoAjax" );
	}
}
