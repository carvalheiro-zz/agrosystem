package br.com.srcsoftware.gestao.sistema.produto.produto;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.srcsoftware.gestao.AuditoriaAbstractMigracaoPO;
import br.com.srcsoftware.gestao.sistema.produto.marca.MarcaMigracaoPO;
import br.com.srcsoftware.gestao.sistema.produto.tipo.TipoMigracaoPO;
import br.com.srcsoftware.gestao.sistema.produto.unidademedida.UnidadeMedidaMigracaoPO;
import br.com.srcsoftware.modular.manager.utilidades.Constantes;

@Entity
@Table( name = "produtos", schema = Constantes.SCHEMA_MIGRACAO, uniqueConstraints = @UniqueConstraint( columnNames = { "nome", "id_marca", "id_unidade_medida" } ) )
public final class ProdutoMigracaoPO extends AuditoriaAbstractMigracaoPO implements Comparable< ProdutoMigracaoPO >{

	@Column( name = "nome", nullable = false, length = 100 )
	private String nome;

	@Column( name = "quantidade_minima_estoque", nullable = false, precision = 20, scale = 2 )
	private BigDecimal quantidadeMinimaEstoque;

	@Column( name = "localizacao", nullable = false, length = 20 )
	private String localizacao;

	@ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, optional = false )
	@JoinColumn( name = "id_marca", nullable = false )
	private MarcaMigracaoPO marca;

	@ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, optional = false )
	@JoinColumn( name = "id_unidade_medida", nullable = false )
	private UnidadeMedidaMigracaoPO unidadeMedida;

	@ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, optional = false )
	@JoinColumn( name = "id_tipo", nullable = false )
	private TipoMigracaoPO tipo;

	public TipoMigracaoPO getTipo() {
		return this.tipo;
	}

	public void setTipo( TipoMigracaoPO tipo ) {
		this.tipo = tipo;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome( String nome ) {
		this.nome = nome;
	}

	public MarcaMigracaoPO getMarca() {
		return this.marca;
	}

	public void setMarca( MarcaMigracaoPO marca ) {
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

	public UnidadeMedidaMigracaoPO getUnidadeMedida() {
		return this.unidadeMedida;
	}

	public void setUnidadeMedida( UnidadeMedidaMigracaoPO unidadeMedida ) {
		this.unidadeMedida = unidadeMedida;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 0;
		result = ( prime * result ) + ( ( this.marca == null ) ? 0 : this.marca.hashCode() );
		result = ( prime * result ) + ( ( this.nome == null ) ? 0 : this.nome.hashCode() );
		result = ( prime * result ) + ( ( this.tipo == null ) ? 0 : this.tipo.hashCode() );
		result = ( prime * result ) + ( ( this.unidadeMedida == null ) ? 0 : this.unidadeMedida.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}

		if ( !( obj instanceof ProdutoMigracaoPO ) ) {
			return false;
		}
		ProdutoMigracaoPO other = (ProdutoMigracaoPO) obj;
		if ( this.marca == null ) {
			if ( other.marca != null ) {
				return false;
			}
		} else if ( !this.marca.equals( other.marca ) ) {
			return false;
		}
		if ( this.nome == null ) {
			if ( other.nome != null ) {
				return false;
			}
		} else if ( !this.nome.equals( other.nome ) ) {
			return false;
		}
		if ( this.tipo == null ) {
			if ( other.tipo != null ) {
				return false;
			}
		} else if ( !this.tipo.equals( other.tipo ) ) {
			return false;
		}
		if ( this.unidadeMedida == null ) {
			if ( other.unidadeMedida != null ) {
				return false;
			}
		} else if ( !this.unidadeMedida.equals( other.unidadeMedida ) ) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ProdutoPO [nome=" + this.nome + ", quantidadeMinimaEstoque=" + this.quantidadeMinimaEstoque + ", localizacao=" + this.localizacao + ", marca=" + this.marca + ", unidadeMedida=" + this.unidadeMedida + ", tipo=" + this.tipo + ", getNomeUsuarioCriacao()=" + this.getNomeUsuarioCriacao() + ", getNomeUsuarioAlteracao()=" + this.getNomeUsuarioAlteracao() + ", getDataOcorrenciaCriacao()=" + this.getDataOcorrenciaCriacao() + ", getDataOcorrenciaAlteracao()=" + this.getDataOcorrenciaAlteracao() + "]";
	}

	@Override
	public int compareTo( ProdutoMigracaoPO o ) {
		return this.getNome().compareToIgnoreCase( o.getNome() );
	}

}
