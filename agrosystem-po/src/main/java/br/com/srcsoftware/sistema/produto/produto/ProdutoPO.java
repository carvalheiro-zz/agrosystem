package br.com.srcsoftware.sistema.produto.produto;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.srcsoftware.abstracts.AuditoriaAbstractPO;
import br.com.srcsoftware.sistema.manutencao.veiculo.VeiculoPO;
import br.com.srcsoftware.sistema.produto.marca.MarcaPO;
import br.com.srcsoftware.sistema.produto.tipo.TipoPO;
import br.com.srcsoftware.sistema.produto.unidademedida.UnidadeMedidaPO;

@Entity
@Table( name = "sistema_produtos", uniqueConstraints = @UniqueConstraint( columnNames = { "nomeCompleto" }, name = "UK_sistema_produtos" ) )
public final class ProdutoPO extends AuditoriaAbstractPO implements Comparable< ProdutoPO >{

	@Lob
	@Column( length = 500, nullable = true )
	private String nomeCompleto;

	@Column( nullable = false, length = 100 )
	private String nome;

	@Column( nullable = false, precision = 20, scale = 2 )
	private BigDecimal quantidadeMinimaEstoque;

	@Column( nullable = false, length = 20 )
	private String localizacao;

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idMarca", nullable = false, foreignKey = @ForeignKey( name = "fk_marca_produtos" ) )
	private MarcaPO marca;

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idUnidadeMedida", nullable = false, foreignKey = @ForeignKey( name = "fk_unidadeMedida_produtos" ) )
	private UnidadeMedidaPO unidadeMedida;

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idTipo", nullable = false, foreignKey = @ForeignKey( name = "fk_tipo_produtos" ) )
	private TipoPO tipo;

	/*private void montarNomeCompleto2() {
		String nome = Utilidades.isNuloOuVazio( this.getNome() ) ? "" : this.getNome();
		String marca = Utilidades.isNuloOuVazio( this.getMarca().getNome() ) ? "" : this.getMarca().getNome();
		String nomeTipo = Utilidades.isNuloOuVazio( this.getTipo().getNome() ) ? "" : this.getTipo().getNome();
		String siglaUnidadeMedida = Utilidades.isNuloOuVazio( this.getUnidadeMedida().getSigla() ) ? "" : "(".concat( this.getUnidadeMedida().getSigla() ).concat( ")" );
		String classificacao = Utilidades.isNuloOuVazio( this.getTipo().getClassificacao() ) ? "" : "[".concat( this.getTipo().getClassificacao() ).concat( "]" );
	
		String valor = String.join( " ", classificacao, nomeTipo, nome, marca, siglaUnidadeMedida ).replaceAll( "null", "" ).trim();
		if ( Utilidades.isNuloOuVazio( valor ) ) {
			return;
		}
		this.setNomeCompleto( valor );
	}*/

	public String getNomeCompleto() {
		//this.montarNomeCompleto2();
		return this.nomeCompleto;
	}

	public void setNomeCompleto( String nomeCompleto ) {
		this.nomeCompleto = nomeCompleto;
	}

	public TipoPO getTipo() {
		return this.tipo;
	}

	public void setTipo( TipoPO tipo ) {
		this.tipo = tipo;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome( String nome ) {
		this.nome = nome;
	}

	public MarcaPO getMarca() {
		return this.marca;
	}

	public void setMarca( MarcaPO marca ) {
		this.marca = marca;
	}

	public BigDecimal getQuantidadeMinimaEstoque() {
		return this.quantidadeMinimaEstoque;
	}

	public void setQuantidadeMinimaEstoque( BigDecimal quantidadeMinimaEstoque ) {
		this.quantidadeMinimaEstoque = quantidadeMinimaEstoque;
	}

	public String getLocalizacao() {
		return this.localizacao;
	}

	public void setLocalizacao( String localizacao ) {
		this.localizacao = localizacao;
	}

	public UnidadeMedidaPO getUnidadeMedida() {
		return this.unidadeMedida;
	}

	public void setUnidadeMedida( UnidadeMedidaPO unidadeMedida ) {
		this.unidadeMedida = unidadeMedida;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = ( prime * result ) + ( ( this.nomeCompleto == null ) ? 0 : this.nomeCompleto.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}
		if ( obj == null ) {
			return false;
		}
		if ( !( obj instanceof VeiculoPO ) ) {
			return false;
		}
		ProdutoPO other = (ProdutoPO) obj;
		if ( this.nomeCompleto == null ) {
			if ( other.nomeCompleto != null ) {
				return false;
			}
		} else if ( !this.nomeCompleto.equals( other.nomeCompleto ) ) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ProdutoPO [nomeCompleto=" + this.nomeCompleto + "]";
	}

	@Override
	public int compareTo( ProdutoPO o ) {
		return this.getNomeCompleto().compareToIgnoreCase( o.getNomeCompleto() );
	}

}
