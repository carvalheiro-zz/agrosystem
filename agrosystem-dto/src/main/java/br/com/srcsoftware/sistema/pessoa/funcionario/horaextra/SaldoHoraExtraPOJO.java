package br.com.srcsoftware.sistema.pessoa.funcionario.horaextra;

import java.math.BigDecimal;

import br.com.srcsoftware.modular.manager.pessoa.funcionario.FuncionarioDTO;

public class SaldoHoraExtraPOJO implements Comparable< SaldoHoraExtraPOJO >{

	private String idColaborador;
	private FuncionarioDTO colaborador;
	private BigDecimal lancamento;
	// TODO private BigDecimal pagamento;// N�o sera usado por enquanto
	// TODO private BigDecimal saldo;// N�o sera usado por enquanto
	
	private BigDecimal quantidade50Porcento;
	private BigDecimal quantidade100Porcento;
	private BigDecimal quantidadeDomingoFeriado;

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

	public BigDecimal getLancamento() {
		return lancamento;
	}

	public void setLancamento( BigDecimal lancamento ) {
		this.lancamento = lancamento;
	}

	/*public BigDecimal getPagamento() {
		return pagamento;
	}

	public void setPagamento( BigDecimal pagamento ) {
		this.pagamento = pagamento;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo( BigDecimal saldo ) {
		this.saldo = saldo;
	}*/

	

	public BigDecimal getQuantidade50Porcento() {
		return quantidade50Porcento;
	}

	public void setQuantidade50Porcento(BigDecimal quantidade50Porcento) {
		this.quantidade50Porcento = quantidade50Porcento;
	}

	public BigDecimal getQuantidade100Porcento() {
		return quantidade100Porcento;
	}

	public void setQuantidade100Porcento(BigDecimal quantidade100Porcento) {
		this.quantidade100Porcento = quantidade100Porcento;
	}

	public BigDecimal getQuantidadeDomingoFeriado() {
		return quantidadeDomingoFeriado;
	}

	public void setQuantidadeDomingoFeriado(BigDecimal quantidadeDomingoFeriado) {
		this.quantidadeDomingoFeriado = quantidadeDomingoFeriado;
	}

	@Override
	public int compareTo( SaldoHoraExtraPOJO o ) {
		return this.getColaborador().getPessoaFisica().getRazaoSocial().compareToIgnoreCase( o.getColaborador().getPessoaFisica().getRazaoSocial() );
	}
}
