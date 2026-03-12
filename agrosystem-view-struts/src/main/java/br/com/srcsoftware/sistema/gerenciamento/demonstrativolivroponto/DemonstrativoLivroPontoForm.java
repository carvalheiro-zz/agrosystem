package br.com.srcsoftware.sistema.gerenciamento.demonstrativolivroponto;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.com.srcsoftware.modular.manager.abstracts.AbstractUsuarioForm;
import br.com.srcsoftware.sistema.pessoa.funcionario.horaextra.SaldoHoraExtraPOJO;

public final class DemonstrativoLivroPontoForm extends AbstractUsuarioForm{

	private static final long serialVersionUID = 1342719712529624093L;

	private String idColaborador;
	private String nomeColaborador;
	private String dataInicial;
	private String dataFinal;

	private String textoIntervaloPesquisa;
	private String valorTotalPagar;

	private List< SaldoHoraExtraPOJO > saldosHoraExtra = new ArrayList<>();

	public String getValorTotalPagar() {
		return valorTotalPagar;
	}

	public void setValorTotalPagar( String valorTotalPagar ) {
		this.valorTotalPagar = valorTotalPagar;
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

	public List< SaldoHoraExtraPOJO > getSaldosHoraExtra() {
		if ( this.saldosHoraExtra == null ) {
			this.saldosHoraExtra = new ArrayList<>();
		}
		return saldosHoraExtra;
	}

	public void setSaldosHoraExtra( List< SaldoHoraExtraPOJO > saldosHoraExtra ) {
		this.saldosHoraExtra = saldosHoraExtra;
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
		setDataInicial( null );
		setDataFinal( null );
		setTextoIntervaloPesquisa( null );
		getSaldosHoraExtra().clear();
	}

	public void limparMensagens( HttpServletRequest request ) {
		request.getSession().removeAttribute( "erroAjax" );
		request.getSession().removeAttribute( "sucessoAjax" );
	}
}
