package br.com.srcsoftware.sistema.notafiscalcupom;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.srcsoftware.abstracts.AuditoriaAbstractPO;
import br.com.srcsoftware.sistema.cupom.CupomPO;
import br.com.srcsoftware.sistema.pessoa.fornecedor.juridico.FornecedorJuridicoPO;

@Entity
@Table( name = "sistema_notasFiscalCupom", uniqueConstraints = @UniqueConstraint( columnNames = { "numero", "idFornecedor" }, name = "UK_sistema_notasFiscalCupom" ) )
public class NotaFiscalCupomPO extends AuditoriaAbstractPO implements Comparable< NotaFiscalCupomPO >{

	@Column( nullable = false, length = 10 )
	private LocalDate data;

	@Column( nullable = true, length = 20 )
	private String numero;

	@ManyToMany( fetch = FetchType.EAGER )
	@JoinTable( name = "sistema_cuponsnotafiscal",
	        joinColumns = { @JoinColumn( name = "idCupom", foreignKey = @ForeignKey( name = "fk_cupom_cuponsnotafiscal" ) ) },
	        inverseJoinColumns = { @JoinColumn( name = "idNotaFiscalCupom", foreignKey = @ForeignKey( name = "fk_notaFiscalCupom_cuponsnotafiscal" ) ) } )
	private Set< CupomPO > cupons = new HashSet<>();

	@ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, optional = true )
	@JoinColumn( name = "idFornecedor", nullable = false, foreignKey = @ForeignKey( name = "fk_fornecedor_notasFiscalCupom" ) )
	private FornecedorJuridicoPO fornecedor;

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

	public FornecedorJuridicoPO getFornecedor() {
		return this.fornecedor;
	}

	public void setFornecedor( FornecedorJuridicoPO fornecedor ) {
		this.fornecedor = fornecedor;
	}

	public Set< CupomPO > getCupons() {
		return this.cupons;
	}

	public void setCupons( Set< CupomPO > cupons ) {
		this.cupons = cupons;
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
		NotaFiscalCupomPO other = (NotaFiscalCupomPO) obj;
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
		return "NotaFiscalCupomPO [data=" + this.data + ", numero=" + this.numero + ", cupons=" + this.cupons + ", fornecedor=" + this.fornecedor + ", getNomeUsuarioCriacao()=" + this.getNomeUsuarioCriacao() + ", getNomeUsuarioAlteracao()=" + this.getNomeUsuarioAlteracao() + ", getDataOcorrenciaCriacao()=" + this.getDataOcorrenciaCriacao() + ", getDataOcorrenciaAlteracao()=" + this.getDataOcorrenciaAlteracao() + ", getId()=" + this.getId() + "]";
	}

	@Override
	public int compareTo( NotaFiscalCupomPO o ) {
		return this.getData().compareTo( o.getData() );
	}
}
