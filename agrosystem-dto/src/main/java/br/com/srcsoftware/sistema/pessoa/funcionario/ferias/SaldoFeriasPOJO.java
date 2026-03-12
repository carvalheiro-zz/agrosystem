package br.com.srcsoftware.sistema.pessoa.funcionario.ferias;

import java.math.BigDecimal;

import br.com.srcsoftware.modular.manager.pessoa.funcionario.FuncionarioDTO;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;

public class SaldoFeriasPOJO implements Comparable< SaldoFeriasPOJO >{

	private String idColaborador;
	private FuncionarioDTO colaborador;
	private BigDecimal adquiridas;
	private BigDecimal cumpridas;
	private BigDecimal vendidas;

	public String getIdColaborador() {
		return idColaborador;
	}

	public void setIdColaborador( String idColaborador ) {
		this.idColaborador = idColaborador;
	}

	public FuncionarioDTO getColaborador() {
		if ( colaborador == null ) {
			colaborador = new FuncionarioDTO();
		}
		return colaborador;
	}

	public void setColaborador( FuncionarioDTO colaborador ) {
		this.colaborador = colaborador;
	}

	public BigDecimal getAdquiridas() {
		return adquiridas;
	}

	public void setAdquiridas( BigDecimal adquiridas ) {
		this.adquiridas = adquiridas;
	}

	public BigDecimal getVendidas() {
		return vendidas;
	}

	public void setVendidas( BigDecimal vendidas ) {
		this.vendidas = vendidas;
	}

	public BigDecimal getCumpridas() {
		return cumpridas;
	}

	public void setCumpridas( BigDecimal cumpridas ) {
		this.cumpridas = cumpridas;
	}

	public BigDecimal getSaldo() {
		return getAdquiridas().subtract( getCumpridas() ).subtract( getVendidas() );
	}

	public BigDecimal getSaldoEmDias() {
		return getSaldo() == null ? BigDecimal.ZERO : getSaldo().divide( new BigDecimal( "8" ), Utilidades.SCALE_CALCULOS, BigDecimal.ROUND_FLOOR ).setScale( 2, BigDecimal.ROUND_FLOOR );
	}

	@Override
	public int compareTo( SaldoFeriasPOJO o ) {
		return this.getColaborador().getPessoaFisica().getRazaoSocial().compareToIgnoreCase( o.getColaborador().getPessoaFisica().getRazaoSocial() );
	}
}
