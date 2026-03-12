package br.com.srcsoftware.sistema.pessoa.funcionario.horaextra;

import br.com.srcsoftware.abstracts.AuditoriaAbstractDTOComEmpresa;
import br.com.srcsoftware.modular.manager.pessoa.funcionario.FuncionarioDTO;
import br.com.srcsoftware.modular.manager.utilidades.DateUtil;

public class HoraExtraDTO extends AuditoriaAbstractDTOComEmpresa implements Comparable< HoraExtraDTO >{

	public static final String TIPO_LANCAMENTO = "Lançamento";
	public static final String TIPO_PAGAMENTO = "Pagamento";

	private String id;
	private FuncionarioDTO colaborador = new FuncionarioDTO();
	private String data;
	private String quantidadeHoras;
	private String tipo; // Lancamento / Pagamento
	
	private String quantidade50Porcento;
	private String quantidade100Porcento;
	private String quantidadeDomingoFeriado;
	private String feriado;

	public String getDiaSemanaToString() {
		return DateUtil.getDiaSemana(DateUtil.parseLocalDate(data));
	}
	
	public String getFeriado() {
		return feriado;
	}

	public void setFeriado(String feriado) {
		this.feriado = feriado;
	}
	
	public String getFeriadoToString() {
		if(feriado == null) {
			return "---";
		}
		return feriado.equalsIgnoreCase("false") ? "N�o" : "Sim";
	}

	public String getId() {
		return id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo( String tipo ) {
		this.tipo = tipo;
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

	public String getData() {
		return data;
	}

	public void setData( String data ) {
		this.data = data;
	}

	public String getQuantidadeHoras() {
		return quantidadeHoras;
	}

	public void setQuantidadeHoras( String quantidadeHoras ) {
		this.quantidadeHoras = quantidadeHoras;
	}

	public String getQuantidade50Porcento() {
		return quantidade50Porcento;
	}

	public void setQuantidade50Porcento(String quantidade50Porcento) {
		this.quantidade50Porcento = quantidade50Porcento;
	}

	public String getQuantidade100Porcento() {
		return quantidade100Porcento;
	}

	public void setQuantidade100Porcento(String quantidade100Porcento) {
		this.quantidade100Porcento = quantidade100Porcento;
	}

	public String getQuantidadeDomingoFeriadoToString() {
		return quantidadeDomingoFeriado.replace(",00", "");
	}
	public String getQuantidadeDomingoFeriado() {
		return quantidadeDomingoFeriado;
	}

	public void setQuantidadeDomingoFeriado(String quantidadeDomingoFeriado) {
		this.quantidadeDomingoFeriado = quantidadeDomingoFeriado;
	}

	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HoraExtraDTO [getId()=");
		builder.append(getId());
		builder.append(", getTipo()=");
		builder.append(getTipo());
		builder.append(", getColaborador()=");
		builder.append(getColaborador());
		builder.append(", getData()=");
		builder.append(getData());
		builder.append(", getQuantidadeHoras()=");
		builder.append(getQuantidadeHoras());
		builder.append(", getQuantidade50Porcento()=");
		builder.append(getQuantidade50Porcento());
		builder.append(", getQuantidade100Porcento()=");
		builder.append(getQuantidade100Porcento());
		builder.append(", getQuantidadeDomingoFeriado()=");
		builder.append(getQuantidadeDomingoFeriado());
		builder.append("]");
		return builder.toString();
	}

	/**
	 * Polimorfico
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( colaborador == null ) ? 0 : colaborador.hashCode() );
		result = prime * result + ( ( data == null ) ? 0 : data.hashCode() );
		result = prime * result + ( ( quantidadeHoras == null ) ? 0 : quantidadeHoras.hashCode() );
		return result;
	}

	/**
	 * Polimorfico
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}
		if ( obj == null ) {
			return false;
		}
		if ( !( obj instanceof HoraExtraDTO ) ) {
			return false;
		}
		HoraExtraDTO other = (HoraExtraDTO) obj;
		if ( colaborador == null ) {
			if ( other.colaborador != null ) {
				return false;
			}
		} else if ( !colaborador.equals( other.colaborador ) ) {
			return false;
		}
		if ( data == null ) {
			if ( other.data != null ) {
				return false;
			}
		} else if ( !data.equals( other.data ) ) {
			return false;
		}
		if ( quantidadeHoras == null ) {
			if ( other.quantidadeHoras != null ) {
				return false;
			}
		} else if ( !quantidadeHoras.equals( other.quantidadeHoras ) ) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo( HoraExtraDTO o ) {
		return this.getData().compareTo( o.getData() );
	}

}
