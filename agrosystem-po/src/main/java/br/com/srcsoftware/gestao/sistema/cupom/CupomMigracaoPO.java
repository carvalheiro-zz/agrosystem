package br.com.srcsoftware.gestao.sistema.cupom;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.srcsoftware.gestao.AuditoriaAbstractMigracaoPO;
import br.com.srcsoftware.gestao.sistema.cupom.item.ItemCupomMigracaoPO;
import br.com.srcsoftware.gestao.sistema.pedido.fornecedor.FornecedorMigracaoPO;
import br.com.srcsoftware.modular.manager.utilidades.Constantes;

@Entity
@Table( name = "cupons", schema = Constantes.SCHEMA_MIGRACAO, uniqueConstraints = @UniqueConstraint( columnNames = { "numero", "id_fornecedor" } ) )
public class CupomMigracaoPO extends AuditoriaAbstractMigracaoPO implements Comparable< CupomMigracaoPO >{

	@Column( name = "data", nullable = false, length = 10 )
	private LocalDate data;

	@Column( name = "numero", nullable = false, length = 20 )
	private String numero;

	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true )
	@JoinColumn( name = "id_cupom", nullable = false )
	private Set< ItemCupomMigracaoPO > itens = new HashSet<>();

	@ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, optional = true )
	@JoinColumn( name = "id_fornecedor", nullable = false )
	private FornecedorMigracaoPO fornecedor;

	@Column( name = "notaEmitida", nullable = false )
	private Boolean notaEmitida;

	public Boolean getNotaEmitida() {
		return this.notaEmitida;
	}

	public void setNotaEmitida( Boolean notaEmitida ) {
		this.notaEmitida = notaEmitida;
	}

	public LocalDate getData() {
		return this.data;
	}

	public void setData( LocalDate data ) {
		this.data = data;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero( String numero ) {
		this.numero = numero;
	}

	public Set< ItemCupomMigracaoPO > getItens() {
		return this.itens;
	}

	public void setItens( Set< ItemCupomMigracaoPO > itens ) {
		this.itens = itens;
	}

	public FornecedorMigracaoPO getFornecedor() {
		return this.fornecedor;
	}

	public void setFornecedor( FornecedorMigracaoPO fornecedor ) {
		this.fornecedor = fornecedor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 0;
		result = ( prime * result ) + ( ( this.data == null ) ? 0 : this.data.hashCode() );
		result = ( prime * result ) + ( ( this.numero == null ) ? 0 : this.numero.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}

		if ( !( obj instanceof CupomMigracaoPO ) ) {
			return false;
		}
		CupomMigracaoPO other = (CupomMigracaoPO) obj;
		if ( this.data == null ) {
			if ( other.data != null ) {
				return false;
			}
		} else if ( !this.data.equals( other.data ) ) {
			return false;
		}
		if ( this.numero == null ) {
			if ( other.numero != null ) {
				return false;
			}
		} else if ( !this.numero.equals( other.numero ) ) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "CupomPO [data=" + this.data + ", numero=" + this.numero + ", itens=" + this.itens + ", fornecedor=" + this.fornecedor + ", getNomeUsuarioCriacao()=" + this.getNomeUsuarioCriacao() + ", getNomeUsuarioAlteracao()=" + this.getNomeUsuarioAlteracao() + ", getDataOcorrenciaCriacao()=" + this.getDataOcorrenciaCriacao() + ", getDataOcorrenciaAlteracao()=" + this.getDataOcorrenciaAlteracao() + "]";
	}

	@Override
	public int compareTo( CupomMigracaoPO o ) {
		return this.getData().compareTo( o.getData() );
	}
}
