package br.com.srcsoftware.gestao.sistema.notafiscalcupom;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.srcsoftware.gestao.AuditoriaAbstractMigracaoPO;
import br.com.srcsoftware.gestao.sistema.cupom.CupomMigracaoPO;
import br.com.srcsoftware.gestao.sistema.pedido.fornecedor.FornecedorMigracaoPO;
import br.com.srcsoftware.modular.manager.utilidades.Constantes;

@Entity
@Table( name = "notasFiscaisCupons", schema = Constantes.SCHEMA_MIGRACAO, uniqueConstraints = @UniqueConstraint( columnNames = { "numero", "id_fornecedor" } ) )
public class NotaFiscalCupomMigracaoPO extends AuditoriaAbstractMigracaoPO implements Comparable< NotaFiscalCupomMigracaoPO >{

	@Column( name = "data", nullable = false, length = 10 )
	private LocalDate data;

	@Column( name = "numero", nullable = true, length = 20 )
	private String numero;

	@ManyToMany( fetch = FetchType.EAGER )
	@JoinTable( name = "cuponsnotafiscal", joinColumns = { @JoinColumn( name = "idCupom" ) }, inverseJoinColumns = { @JoinColumn( name = "idNotaFiscalCupom" ) } )
	private Set< CupomMigracaoPO > cupons = new HashSet<>();

	@ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, optional = true )
	@JoinColumn( name = "id_fornecedor", nullable = false )
	private FornecedorMigracaoPO fornecedor;

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

	public FornecedorMigracaoPO getFornecedor() {
		return this.fornecedor;
	}

	public void setFornecedor( FornecedorMigracaoPO fornecedor ) {
		this.fornecedor = fornecedor;
	}

	public Set< CupomMigracaoPO > getCupons() {
		return this.cupons;
	}

	public void setCupons( Set< CupomMigracaoPO > cupons ) {
		this.cupons = cupons;
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

		if ( !( obj instanceof NotaFiscalCupomMigracaoPO ) ) {
			return false;
		}
		NotaFiscalCupomMigracaoPO other = (NotaFiscalCupomMigracaoPO) obj;
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
		return "NotaFiscalCupomPO [data=" + this.data + ", numero=" + this.numero + ", cupons=" + this.cupons + ", fornecedor=" + this.fornecedor + ", getNomeUsuarioCriacao()=" + this.getNomeUsuarioCriacao() + ", getNomeUsuarioAlteracao()=" + this.getNomeUsuarioAlteracao() + ", getDataOcorrenciaCriacao()=" + this.getDataOcorrenciaCriacao() + ", getDataOcorrenciaAlteracao()=" + this.getDataOcorrenciaAlteracao() + "]";
	}

	@Override
	public int compareTo( NotaFiscalCupomMigracaoPO o ) {
		return this.getData().compareTo( o.getData() );
	}
}
