package br.com.srcsoftware.sistema.cupom;

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

import org.hibernate.annotations.Type;

import br.com.srcsoftware.abstracts.AuditoriaAbstractPO;
import br.com.srcsoftware.sistema.cupom.item.ItemCupomPO;
import br.com.srcsoftware.sistema.pessoa.fornecedor.juridico.FornecedorJuridicoPO;

@Entity
@Table( name = "sistema_cupons", uniqueConstraints = @UniqueConstraint( columnNames = { "numero", "idFornecedor" }, name = "UK_sistema_cupons" ) )
public class CupomPO extends AuditoriaAbstractPO implements Comparable< CupomPO >{

	@Column( nullable = false, length = 10 )
	private LocalDate data;

	@Column( nullable = false, length = 20 )
	private String numero;

	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true )
	@JoinColumn( name = "idCupom", nullable = false, foreignKey = @ForeignKey( name = "fk_itemCupom_cupons" ) )
	private Set< ItemCupomPO > itens = new HashSet<>();

	@ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, optional = true )
	@JoinColumn( name = "idFornecedor", nullable = false, foreignKey = @ForeignKey( name = "fk_fornecedor_cupons" ) )
	private FornecedorJuridicoPO fornecedor;

	@Type( type = "true_false" )
	@Column( nullable = false )
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

	public Set< ItemCupomPO > getItens() {
		return this.itens;
	}

	public void setItens( Set< ItemCupomPO > itens ) {
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
		CupomPO other = (CupomPO) obj;
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
		return "CupomPO [data=" + this.data + ", numero=" + this.numero + ", itens=" + this.itens + ", fornecedor=" + this.fornecedor + ", getNomeUsuarioCriacao()=" + this.getNomeUsuarioCriacao() + ", getNomeUsuarioAlteracao()=" + this.getNomeUsuarioAlteracao() + ", getDataOcorrenciaCriacao()=" + this.getDataOcorrenciaCriacao() + ", getDataOcorrenciaAlteracao()=" + this.getDataOcorrenciaAlteracao() + ", getId()=" + this.getId() + "]";
	}

	@Override
	public int compareTo( CupomPO o ) {
		return this.getData().compareTo( o.getData() );
	}
}
