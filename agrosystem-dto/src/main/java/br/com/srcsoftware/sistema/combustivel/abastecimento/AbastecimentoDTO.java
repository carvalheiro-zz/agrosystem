package br.com.srcsoftware.sistema.combustivel.abastecimento;

import java.time.LocalDate;

import br.com.srcsoftware.abstracts.AuditoriaAbstractDTO;
import br.com.srcsoftware.modular.manager.pessoa.funcionario.FuncionarioDTO;
import br.com.srcsoftware.modular.manager.utilidades.DateUtil;
import br.com.srcsoftware.sistema.manutencao.veiculo.VeiculoDTO;
import br.com.srcsoftware.sistema.produto.produto.ProdutoDTO;

public final class AbastecimentoDTO extends AuditoriaAbstractDTO implements Comparable< AbastecimentoDTO >{

	private String id;
	private String data;
	private String quantidade;
	private String custoMedio;
	private String custoTotal;
	private String kmHorimetro;
	private ProdutoDTO produto;
	private FuncionarioDTO almoxarife;
	private FuncionarioDTO requerente;
	private VeiculoDTO veiculo;

	public AbastecimentoDTO(){
		this.produto = new ProdutoDTO();
		this.almoxarife = new FuncionarioDTO();
		this.requerente = new FuncionarioDTO();
		this.veiculo = new VeiculoDTO();
	}

	public String getKmHorimetro() {
		return kmHorimetro;
	}

	public void setKmHorimetro( String kmHorimetro ) {
		this.kmHorimetro = kmHorimetro;
	}

	public String getDataToString() {
		return DateUtil.formatLocalDate( getData() );
	}

	public String getId() {
		return id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public VeiculoDTO getVeiculo() {
		return this.veiculo;
	}

	public void setVeiculo( VeiculoDTO veiculo ) {
		this.veiculo = veiculo;
	}

	public String getCustoMedio() {
		return this.custoMedio;
	}

	public void setCustoMedio( String custoMedio ) {
		this.custoMedio = custoMedio;
	}

	public String getData() {
		return this.data;
	}

	public void setData( String data ) {
		this.data = data;
	}

	public String getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade( String quantidade ) {
		this.quantidade = quantidade;
	}

	public String getCustoTotal() {
		return this.custoTotal;
	}

	public void setCustoTotal( String custoTotal ) {
		this.custoTotal = custoTotal;
	}

	public ProdutoDTO getProduto() {
		return this.produto;
	}

	public void setProduto( ProdutoDTO produto ) {
		this.produto = produto;
	}

	public FuncionarioDTO getAlmoxarife() {
		return this.almoxarife;
	}

	public void setAlmoxarife( FuncionarioDTO almoxarife ) {
		this.almoxarife = almoxarife;
	}

	public FuncionarioDTO getRequerente() {
		return this.requerente;
	}

	public void setRequerente( FuncionarioDTO requerente ) {
		this.requerente = requerente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( almoxarife == null ) ? 0 : almoxarife.hashCode() );
		result = prime * result + ( ( custoMedio == null ) ? 0 : custoMedio.hashCode() );
		result = prime * result + ( ( custoTotal == null ) ? 0 : custoTotal.hashCode() );
		result = prime * result + ( ( data == null ) ? 0 : data.hashCode() );
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		result = prime * result + ( ( produto == null ) ? 0 : produto.hashCode() );
		result = prime * result + ( ( quantidade == null ) ? 0 : quantidade.hashCode() );
		result = prime * result + ( ( requerente == null ) ? 0 : requerente.hashCode() );
		result = prime * result + ( ( veiculo == null ) ? 0 : veiculo.hashCode() );
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
		AbastecimentoDTO other = (AbastecimentoDTO) obj;
		if ( almoxarife == null ) {
			if ( other.almoxarife != null )
				return false;
		} else if ( !almoxarife.equals( other.almoxarife ) )
			return false;
		if ( custoMedio == null ) {
			if ( other.custoMedio != null )
				return false;
		} else if ( !custoMedio.equals( other.custoMedio ) )
			return false;
		if ( custoTotal == null ) {
			if ( other.custoTotal != null )
				return false;
		} else if ( !custoTotal.equals( other.custoTotal ) )
			return false;
		if ( data == null ) {
			if ( other.data != null )
				return false;
		} else if ( !data.equals( other.data ) )
			return false;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
		if ( produto == null ) {
			if ( other.produto != null )
				return false;
		} else if ( !produto.equals( other.produto ) )
			return false;
		if ( quantidade == null ) {
			if ( other.quantidade != null )
				return false;
		} else if ( !quantidade.equals( other.quantidade ) )
			return false;
		if ( requerente == null ) {
			if ( other.requerente != null )
				return false;
		} else if ( !requerente.equals( other.requerente ) )
			return false;
		if ( veiculo == null ) {
			if ( other.veiculo != null )
				return false;
		} else if ( !veiculo.equals( other.veiculo ) )
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "ItemDTO [data=" );
		builder.append( this.data );
		builder.append( ", quantidade=" );
		builder.append( this.quantidade );
		builder.append( ", custoMedio=" );
		builder.append( this.custoMedio );
		builder.append( ", custoTotal=" );
		builder.append( this.custoTotal );
		builder.append( ", produto=" );
		builder.append( this.produto );
		builder.append( ", almoxarife=" );
		builder.append( this.almoxarife );
		builder.append( ", requerente=" );
		builder.append( this.requerente );
		builder.append( ", veiculo=" );
		builder.append( this.veiculo );
		builder.append( ", getNomeUsuarioCriacao()=" );
		builder.append( this.getNomeUsuarioCriacao() );
		builder.append( ", getNomeUsuarioAlteracao()=" );
		builder.append( this.getNomeUsuarioAlteracao() );
		builder.append( ", getDataOcorrenciaCriacao()=" );
		builder.append( this.getDataOcorrenciaCriacao() );
		builder.append( ", getDataOcorrenciaAlteracao()=" );
		builder.append( this.getDataOcorrenciaAlteracao() );
		builder.append( ", getId()=" );
		builder.append( this.getId() );
		builder.append( "]" );
		return builder.toString();
	}

	@Override
	public int compareTo( AbastecimentoDTO o ) {

		LocalDate dataThis = DateUtil.parseLocalDate( getData() );
		LocalDate dataComparar = DateUtil.parseLocalDate( o.getData() );

		return dataThis.compareTo( dataComparar );

	}

}
