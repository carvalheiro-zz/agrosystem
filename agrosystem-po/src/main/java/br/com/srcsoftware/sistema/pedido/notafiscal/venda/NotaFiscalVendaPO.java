package br.com.srcsoftware.sistema.pedido.notafiscal.venda;

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
import br.com.srcsoftware.sistema.pedido.pedido.PedidoPO;

@Entity
@Table( name = "sistema_notasfiscalvenda", uniqueConstraints = @UniqueConstraint( columnNames = { "numero", "idPedido" }, name = "UK_sistema_notasfiscalvenda" ) )
public class NotaFiscalVendaPO extends AuditoriaAbstractPO implements Comparable< NotaFiscalVendaPO >{

	@Column( nullable = false, length = 20 )
	private String tipo;

	@Column( nullable = false, length = 10 )
	private LocalDate data;

	@Column( nullable = true, length = 20 )
	private String numero;

	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true )
	@JoinColumn( name = "idNotaEntrega", nullable = false, foreignKey = @ForeignKey( name = "fk_itemNotaFiscalVenda_notasfiscalvenda" ) )
	private Set< ItemNotaFiscalVendaPO > itens = new HashSet<>();

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idPedido", nullable = false, foreignKey = @ForeignKey( name = "fk_pedido_notasfiscalvenda" ) )
	private PedidoPO pedido;

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

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo( String tipo ) {
		this.tipo = tipo;
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

	/*public DespesaPO getDespesa() {
		return this.despesa;
	}
	
	public void setDespesa( DespesaPO despesa ) {
		this.despesa = despesa;
	}*/

	public Set< ItemNotaFiscalVendaPO > getItens() {
		return this.itens;
	}

	public void setItens( Set< ItemNotaFiscalVendaPO > itens ) {
		this.itens = itens;
	}

	public PedidoPO getPedido() {
		return this.pedido;
	}

	public void setPedido( PedidoPO pedido ) {
		this.pedido = pedido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( numero == null ) ? 0 : numero.hashCode() );
		result = prime * result + ( ( pedido == null ) ? 0 : pedido.hashCode() );
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
		NotaFiscalVendaPO other = (NotaFiscalVendaPO) obj;
		if ( numero == null ) {
			if ( other.numero != null )
				return false;
		} else if ( !numero.equals( other.numero ) )
			return false;
		if ( pedido == null ) {
			if ( other.pedido != null )
				return false;
		} else if ( !pedido.equals( other.pedido ) )
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NotaEntregaPO [data=" + this.data + ", numero=" + this.numero + ", tipo=" + this.tipo + /*", despesa=" + this.despesa +*/ ", itens=" + this.itens + ", pedido=" + this.pedido + ", getNomeUsuarioCriacao()=" + this.getNomeUsuarioCriacao() + ", getNomeUsuarioAlteracao()=" + this.getNomeUsuarioAlteracao() + ", getDataOcorrenciaCriacao()=" + this.getDataOcorrenciaCriacao() + ", getDataOcorrenciaAlteracao()=" + this.getDataOcorrenciaAlteracao() + ", getId()=" + this.getId() + "]";
	}

	@Override
	public int compareTo( NotaFiscalVendaPO o ) {
		return this.getData().compareTo( o.getData() );
	}
}
