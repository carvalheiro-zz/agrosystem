package br.com.srcsoftware.gestao.sistema.pedido.pedido;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.srcsoftware.gestao.AuditoriaAbstractMigracaoPO;
import br.com.srcsoftware.gestao.sistema.pedido.fornecedor.FornecedorMigracaoPO;
import br.com.srcsoftware.gestao.sistema.pedido.itempedido.ItemPedidoMigracaoPO;
import br.com.srcsoftware.modular.manager.utilidades.Constantes;

@Entity
@Table( name = "pedidos", schema = Constantes.SCHEMA_MIGRACAO, uniqueConstraints = @UniqueConstraint( columnNames = { "numero", "id_fornecedor" } ) )
public class PedidoMigracaoPO extends AuditoriaAbstractMigracaoPO implements Comparable< PedidoMigracaoPO >{

	@Column( name = "data", nullable = false, length = 10 )
	private LocalDate data;

	@Column( name = "numero", nullable = true, length = 20 )
	private String numero;

	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true )
	@JoinColumn( name = "id_pedido", nullable = false )
	private Set< ItemPedidoMigracaoPO > itens = new HashSet<>();

	@ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, optional = true )
	@JoinColumn( name = "id_fornecedor", nullable = true )
	private FornecedorMigracaoPO fornecedor;

	@Lob
	@Column( name = "observacao", nullable = true, length = 1000 )
	private String observacao;

	@Column( name = "status", nullable = false, length = 20 )
	private String status;

	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao( String observacao ) {
		this.observacao = observacao;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus( String status ) {
		this.status = status;
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

	public Set< ItemPedidoMigracaoPO > getItens() {
		return this.itens;
	}

	public void setItens( Set< ItemPedidoMigracaoPO > itens ) {
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

		if ( !( obj instanceof PedidoMigracaoPO ) ) {
			return false;
		}
		PedidoMigracaoPO other = (PedidoMigracaoPO) obj;
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
		return "PedidoPO [data=" + this.data + ", numero=" + this.numero + ", itens=" + this.itens + ", fornecedor=" + this.fornecedor + ", getNomeUsuarioCriacao()=" + this.getNomeUsuarioCriacao() + ", getNomeUsuarioAlteracao()=" + this.getNomeUsuarioAlteracao() + ", getDataOcorrenciaCriacao()=" + this.getDataOcorrenciaCriacao() + ", getDataOcorrenciaAlteracao()=" + this.getDataOcorrenciaAlteracao() + "]";
	}

	@Override
	public int compareTo( PedidoMigracaoPO o ) {
		return this.getData().compareTo( o.getData() );
	}
}
