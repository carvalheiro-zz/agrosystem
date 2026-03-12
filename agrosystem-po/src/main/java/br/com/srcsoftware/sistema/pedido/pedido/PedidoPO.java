package br.com.srcsoftware.sistema.pedido.pedido;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.srcsoftware.abstracts.AuditoriaAbstractPO;
import br.com.srcsoftware.sistema.pedido.itempedido.ItemPedidoPO;
import br.com.srcsoftware.sistema.pessoa.fornecedor.juridico.FornecedorJuridicoPO;

@Entity
@Table( name = "sistema_pedidos", uniqueConstraints = @UniqueConstraint( columnNames = { "numero", "idFornecedor" }, name = "UK_sistema_pedidos" ) )
public class PedidoPO extends AuditoriaAbstractPO implements Comparable< PedidoPO >{

	@Column( nullable = false, length = 10 )
	private LocalDate data;

	@Column( nullable = true, length = 20 )
	private String numero;

	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true )
	@JoinColumn( name = "idPedido", nullable = false, foreignKey = @ForeignKey( name = "fk_itemPedido_pedidos" ) )
	private Set< ItemPedidoPO > itens = new HashSet<>();

	@ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, optional = true )
	@JoinColumn( name = "idFornecedor", nullable = true, foreignKey = @ForeignKey( name = "fk_fornecedor_pedidos" ) )
	private FornecedorJuridicoPO fornecedor;

	@Lob
	@Column( nullable = true, length = 1000 )
	private String observacao;

	@Column( nullable = false, length = 20 )
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

	public Set< ItemPedidoPO > getItens() {
		return this.itens;
	}

	public void setItens( Set< ItemPedidoPO > itens ) {
		this.itens = itens;
	}

	public FornecedorJuridicoPO getFornecedor() {
		return this.fornecedor;
	}

	public void setFornecedor( FornecedorJuridicoPO fornecedor ) {
		this.fornecedor = fornecedor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( fornecedor == null ) ? 0 : fornecedor.hashCode() );
		result = prime * result + ( ( numero == null ) ? 0 : numero.hashCode() );
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
		PedidoPO other = (PedidoPO) obj;
		if ( fornecedor == null ) {
			if ( other.fornecedor != null )
				return false;
		} else if ( !fornecedor.equals( other.fornecedor ) )
			return false;
		if ( numero == null ) {
			if ( other.numero != null )
				return false;
		} else if ( !numero.equals( other.numero ) )
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PedidoPO [data=" + this.data + ", numero=" + this.numero + ", itens=" + this.itens + ", fornecedor=" + this.fornecedor + ", getNomeUsuarioCriacao()=" + this.getNomeUsuarioCriacao() + ", getNomeUsuarioAlteracao()=" + this.getNomeUsuarioAlteracao() + ", getDataOcorrenciaCriacao()=" + this.getDataOcorrenciaCriacao() + ", getDataOcorrenciaAlteracao()=" + this.getDataOcorrenciaAlteracao() + ", getId()=" + this.getId() + "]";
	}

	@Override
	public int compareTo( PedidoPO o ) {
		return this.getData().compareTo( o.getData() );
	}
}
