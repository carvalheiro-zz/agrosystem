package br.com.srcsoftware.gestao.sistema.pedido.notafiscal.venda;

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
import br.com.srcsoftware.gestao.sistema.pedido.pedido.PedidoMigracaoPO;
import br.com.srcsoftware.modular.manager.utilidades.Constantes;

@Entity
@Table( name = "notas_fiscais_vendas", schema = Constantes.SCHEMA_MIGRACAO, uniqueConstraints = @UniqueConstraint( columnNames = { "numero", "id_pedido" } ) )
public class NotaFiscalVendaMigracaoPO extends AuditoriaAbstractMigracaoPO implements Comparable< NotaFiscalVendaMigracaoPO >{

	@Column( name = "tipo", nullable = false, length = 20 )
	private String tipo;

	@Column( name = "data", nullable = false, length = 10 )
	private LocalDate data;

	@Column( name = "numero", nullable = true, length = 20 )
	private String numero;

	/*@OneToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true )
	@JoinColumn( name = "id_despesa", nullable = false )
	private DespesaPO despesa;*/

	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true )
	@JoinColumn( name = "id_nota_entrega", nullable = false )
	private Set< ItemNotaFiscalVendaMigracaoPO > itens = new HashSet<>();

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "id_pedido", nullable = false )
	private PedidoMigracaoPO pedido;

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

	public Set< ItemNotaFiscalVendaMigracaoPO > getItens() {
		return this.itens;
	}

	public void setItens( Set< ItemNotaFiscalVendaMigracaoPO > itens ) {
		this.itens = itens;
	}

	public PedidoMigracaoPO getPedido() {
		return this.pedido;
	}

	public void setPedido( PedidoMigracaoPO pedido ) {
		this.pedido = pedido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 0;
		result = ( prime * result ) + ( ( this.data == null ) ? 0 : this.data.hashCode() );
		//result = ( prime * result ) + ( ( this.despesa == null ) ? 0 : this.despesa.hashCode() );
		result = ( prime * result ) + ( ( this.numero == null ) ? 0 : this.numero.hashCode() );
		result = ( prime * result ) + ( ( this.tipo == null ) ? 0 : this.tipo.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}

		if ( !( obj instanceof NotaFiscalVendaMigracaoPO ) ) {
			return false;
		}
		NotaFiscalVendaMigracaoPO other = (NotaFiscalVendaMigracaoPO) obj;
		if ( this.data == null ) {
			if ( other.data != null ) {
				return false;
			}
		} else if ( !this.data.equals( other.data ) ) {
			return false;
		}
		/*if ( this.despesa == null ) {
			if ( other.despesa != null ) {
				return false;
			}
		} else if ( !this.despesa.equals( other.despesa ) ) {
			return false;
		}*/
		if ( this.numero == null ) {
			if ( other.numero != null ) {
				return false;
			}
		} else if ( !this.numero.equals( other.numero ) ) {
			return false;
		}
		if ( this.tipo == null ) {
			if ( other.tipo != null ) {
				return false;
			}
		} else if ( !this.tipo.equals( other.tipo ) ) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "NotaEntregaPO [data=" + this.data + ", numero=" + this.numero + ", tipo=" + this.tipo + /*", despesa=" + this.despesa +*/ ", itens=" + this.itens + ", pedido=" + this.pedido + ", getNomeUsuarioCriacao()=" + this.getNomeUsuarioCriacao() + ", getNomeUsuarioAlteracao()=" + this.getNomeUsuarioAlteracao() + ", getDataOcorrenciaCriacao()=" + this.getDataOcorrenciaCriacao() + ", getDataOcorrenciaAlteracao()=" + this.getDataOcorrenciaAlteracao() + "]";
	}

	@Override
	public int compareTo( NotaFiscalVendaMigracaoPO o ) {
		return this.getData().compareTo( o.getData() );
	}
}
