package br.com.srcsoftware.sistema.notafiscal.rateio.centrocusto;

import br.com.srcsoftware.abstracts.AuditoriaAbstractDTO;

public class CentroCustoDTO extends AuditoriaAbstractDTO{

	private static final long serialVersionUID = 6789042253005131815L;

	private String id;
	private String codigo;
	private String descricao;
	private String tipo;

	public String getId() {
		return id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo( String codigo ) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao( String descricao ) {
		this.descricao = descricao;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo( String tipo ) {
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( codigo == null ) ? 0 : codigo.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		CentroCustoDTO other = (CentroCustoDTO) obj;
		if ( codigo == null ) {
			if ( other.codigo != null )
				return false;
		} else if ( !codigo.equals( other.codigo ) )
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "CentroCustoReceitaDTO [\n\tcodigo=" );
		builder.append( this.codigo );
		builder.append( ", \ndescricao=" );
		builder.append( this.descricao );
		builder.append( ", \ntipo=" );
		builder.append( this.tipo );
		builder.append( ", \ngetNomeUsuarioCriacao()=" );
		builder.append( this.getNomeUsuarioCriacao() );
		builder.append( ", \ngetNomeUsuarioAlteracao()=" );
		builder.append( this.getNomeUsuarioAlteracao() );
		builder.append( ", \ngetDataOcorrenciaCriacao()=" );
		builder.append( this.getDataOcorrenciaCriacao() );
		builder.append( ", \ngetDataOcorrenciaAlteracao()=" );
		builder.append( this.getDataOcorrenciaAlteracao() );
		builder.append( ", \ngetId()=" );
		builder.append( this.getId() );
		builder.append( "]\n" );
		return builder.toString();
	}

}
