package br.com.srcsoftware.enuns;

public enum NotaFiscalRateioEnum {
	TIPO_RECEITA("Receita"), TIPO_INVESTIMENTO_DESPESA("Investimento/Despesa");

	private String valor;

	NotaFiscalRateioEnum( String valor ){
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
}
