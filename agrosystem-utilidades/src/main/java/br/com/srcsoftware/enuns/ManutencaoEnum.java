package br.com.srcsoftware.enuns;

public enum ManutencaoEnum {
	FINALIDADE_MANUTENCAO("Manutenção"), FINALIDADE_CONCERTO("Concerto"), TIPO_SAFRASETOR("Safra/Setor"), TIPO_TUDO("Tudo"), TIPO_CULTURA("Cultura"), RECIBO("Recibo"), NOTA_FISCAL("Nota Fiscal"), TIPO_IMOVEL("imovel"), TIPO_VEICULO(
	        "veiculo"), TIPO_IMPLEMENTO("implemento");

	private String valor;

	ManutencaoEnum( String valor ){
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
}
