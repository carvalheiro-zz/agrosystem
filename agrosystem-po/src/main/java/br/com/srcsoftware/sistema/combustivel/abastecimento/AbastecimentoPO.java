package br.com.srcsoftware.sistema.combustivel.abastecimento;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.srcsoftware.abstracts.AuditoriaAbstractPO;
import br.com.srcsoftware.modular.manager.pessoa.funcionario.FuncionarioPO;
import br.com.srcsoftware.sistema.manutencao.veiculo.VeiculoPO;
import br.com.srcsoftware.sistema.produto.produto.ProdutoPO;

@Entity
@Table( name = "sistema_abastecimentos" )
public final class AbastecimentoPO extends AuditoriaAbstractPO implements Comparable< AbastecimentoPO >{

	@Column( length = 10, nullable = false )
	private LocalDate data;

	@Column( precision = 12, scale = 2, nullable = false )
	private BigDecimal quantidade;

	@Column( precision = 12, scale = 2, nullable = false )
	private BigDecimal custoMedio;

	@Column( precision = 12, scale = 2, nullable = false )
	private BigDecimal custoTotal;

	@Column( length = 50, nullable = true )
	private String kmHorimetro;

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idProduto", nullable = false, foreignKey = @ForeignKey( name = "fk_produto_abastecimentos" ) )
	private ProdutoPO produto;

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idAlmoxarife", nullable = false, foreignKey = @ForeignKey( name = "fk_almoxarife_abastecimentos" ) )
	private FuncionarioPO almoxarife;

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idRequerente", nullable = false, foreignKey = @ForeignKey( name = "fk_requerente_abastecimentos" ) )
	private FuncionarioPO requerente;

	@ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, optional = true )
	@JoinColumn( name = "idVeiculo", nullable = true, foreignKey = @ForeignKey( name = "fk_veiculo_abastecimentos" ) )
	private VeiculoPO veiculo;

	public AbastecimentoPO(){
		this.produto = new ProdutoPO();
		this.almoxarife = new FuncionarioPO();
		this.requerente = new FuncionarioPO();
		this.veiculo = new VeiculoPO();
	}

	public String getKmHorimetro() {
		return kmHorimetro;
	}

	public void setKmHorimetro( String kmHorimetro ) {
		this.kmHorimetro = kmHorimetro;
	}

	public VeiculoPO getVeiculo() {
		return this.veiculo;
	}

	public void setVeiculo( VeiculoPO veiculo ) {
		this.veiculo = veiculo;
	}

	public BigDecimal getCustoMedio() {
		return this.custoMedio;
	}

	public void setCustoMedio( BigDecimal custoMedio ) {
		this.custoMedio = custoMedio;
	}

	public LocalDate getData() {
		return this.data;
	}

	public void setData( LocalDate data ) {
		this.data = data;
	}

	public BigDecimal getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade( BigDecimal quantidade ) {
		this.quantidade = quantidade;
	}

	public BigDecimal getCustoTotal() {
		return this.custoTotal;
	}

	public void setCustoTotal( BigDecimal custoTotal ) {
		this.custoTotal = custoTotal;
	}

	public ProdutoPO getProduto() {
		return this.produto;
	}

	public void setProduto( ProdutoPO produto ) {
		this.produto = produto;
	}

	public FuncionarioPO getAlmoxarife() {
		return this.almoxarife;
	}

	public void setAlmoxarife( FuncionarioPO almoxarife ) {
		this.almoxarife = almoxarife;
	}

	public FuncionarioPO getRequerente() {
		return this.requerente;
	}

	public void setRequerente( FuncionarioPO requerente ) {
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
		AbastecimentoPO other = (AbastecimentoPO) obj;
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
		builder.append( "ItemPO [data=" );
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
	public int compareTo( AbastecimentoPO o ) {
		return this.getData().compareTo( o.getData() );
	}

}
