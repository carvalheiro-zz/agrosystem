package br.com.srcsoftware.sistema.pedido.notafiscal.simplesremessa;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.srcsoftware.abstracts.AuditoriaAbstractPO;
import br.com.srcsoftware.sistema.pedido.notafiscal.venda.NotaFiscalVendaPO;

@Entity
@Table( name = "sistema_notassimplesremessa", uniqueConstraints = @UniqueConstraint( columnNames = { "numero", "idNotaFiscalVendaFutura" }, name = "UK_sistema_notassimplesremessa" ) )
public class NotaFiscalSimplesRemessaPO extends AuditoriaAbstractPO implements Comparable< NotaFiscalSimplesRemessaPO >{

	@Column( nullable = false, length = 10 )
	private LocalDate data;

	@Column( nullable = true, length = 20 )
	private String numero;

	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true )
	@JoinColumn( name = "idNotaSimplesRemessa", nullable = false, foreignKey = @ForeignKey( name = "fk_itemNotaFiscalSimplesRemessa_notaFiscalSimplesRemessa" ) )
	private Set< ItemNotaFiscalSimplesRemessaPO > itens = new HashSet<>();

	@ManyToOne( fetch = FetchType.EAGER, optional = true )
	@JoinColumn( name = "idNotaFiscalVendaFutura", nullable = false, foreignKey = @ForeignKey( name = "fk_notaFiscalVendaFutura_notaFiscalSimplesRemessa" ) )
	private NotaFiscalVendaPO notaFiscalVendaFutura;

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

	public Set< ItemNotaFiscalSimplesRemessaPO > getItens() {
		return this.itens;
	}

	public void setItens( Set< ItemNotaFiscalSimplesRemessaPO > itens ) {
		this.itens = itens;
	}

	public NotaFiscalVendaPO getNotaFiscalVendaFutura() {
		return this.notaFiscalVendaFutura;
	}

	public void setNotaFiscalVendaFutura( NotaFiscalVendaPO notaFiscalVendaFutura ) {
		this.notaFiscalVendaFutura = notaFiscalVendaFutura;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( notaFiscalVendaFutura == null ) ? 0 : notaFiscalVendaFutura.hashCode() );
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
		NotaFiscalSimplesRemessaPO other = (NotaFiscalSimplesRemessaPO) obj;
		if ( notaFiscalVendaFutura == null ) {
			if ( other.notaFiscalVendaFutura != null )
				return false;
		} else if ( !notaFiscalVendaFutura.equals( other.notaFiscalVendaFutura ) )
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
		return "NotaFiscalSimplesRemessaPO [data=" + this.data + ", numero=" + this.numero + ", itens=" + this.itens + ", notaFiscalVendaFutura=" + this.notaFiscalVendaFutura + ", getNomeUsuarioCriacao()=" + this.getNomeUsuarioCriacao() + ", getNomeUsuarioAlteracao()=" + this.getNomeUsuarioAlteracao() + ", getDataOcorrenciaCriacao()=" + this.getDataOcorrenciaCriacao() + ", getDataOcorrenciaAlteracao()=" + this.getDataOcorrenciaAlteracao() + ", getId()=" + this.getId() + "]";
	}

	@Override
	public int compareTo( NotaFiscalSimplesRemessaPO o ) {
		return this.getData().compareTo( o.getData() );
	}
}
