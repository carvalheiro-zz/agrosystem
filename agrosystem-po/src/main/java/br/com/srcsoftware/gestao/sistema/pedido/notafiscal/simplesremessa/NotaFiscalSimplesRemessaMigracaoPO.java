package br.com.srcsoftware.gestao.sistema.pedido.notafiscal.simplesremessa;

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
import br.com.srcsoftware.gestao.sistema.pedido.notafiscal.venda.NotaFiscalVendaMigracaoPO;
import br.com.srcsoftware.modular.manager.utilidades.Constantes;

@Entity
@Table( name = "notas_simples_remessa", schema = Constantes.SCHEMA_MIGRACAO, uniqueConstraints = @UniqueConstraint( columnNames = { "numero", "id_nota_fiscal_venda_futura" } ) )
public class NotaFiscalSimplesRemessaMigracaoPO extends AuditoriaAbstractMigracaoPO implements Comparable< NotaFiscalSimplesRemessaMigracaoPO >{

	@Column( name = "data", nullable = false, length = 10 )
	private LocalDate data;

	@Column( name = "numero", nullable = true, length = 20 )
	private String numero;

	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true )
	@JoinColumn( name = "id_nota_simple_remessa", nullable = false )
	private Set< ItemNotaFiscalSimplesRemessaMigracaoPO > itens = new HashSet<>();

	@ManyToOne( fetch = FetchType.EAGER, optional = true )
	@JoinColumn( name = "id_nota_fiscal_venda_futura", nullable = false )
	private NotaFiscalVendaMigracaoPO notaFiscalVendaFutura;

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

	public Set< ItemNotaFiscalSimplesRemessaMigracaoPO > getItens() {
		return this.itens;
	}

	public void setItens( Set< ItemNotaFiscalSimplesRemessaMigracaoPO > itens ) {
		this.itens = itens;
	}

	public NotaFiscalVendaMigracaoPO getNotaFiscalVendaFutura() {
		return this.notaFiscalVendaFutura;
	}

	public void setNotaFiscalVendaFutura( NotaFiscalVendaMigracaoPO notaFiscalVendaFutura ) {
		this.notaFiscalVendaFutura = notaFiscalVendaFutura;
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

		if ( !( obj instanceof NotaFiscalSimplesRemessaMigracaoPO ) ) {
			return false;
		}
		NotaFiscalSimplesRemessaMigracaoPO other = (NotaFiscalSimplesRemessaMigracaoPO) obj;
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
		return "NotaFiscalSimplesRemessaPO [data=" + this.data + ", numero=" + this.numero + ", itens=" + this.itens + ", notaFiscalVendaFutura=" + this.notaFiscalVendaFutura + ", getNomeUsuarioCriacao()=" + this.getNomeUsuarioCriacao() + ", getNomeUsuarioAlteracao()=" + this.getNomeUsuarioAlteracao() + ", getDataOcorrenciaCriacao()=" + this.getDataOcorrenciaCriacao() + ", getDataOcorrenciaAlteracao()=" + this.getDataOcorrenciaAlteracao() + "]";
	}

	@Override
	public int compareTo( NotaFiscalSimplesRemessaMigracaoPO o ) {
		return this.getData().compareTo( o.getData() );
	}
}
