package br.com.srcsoftware.sistema.notafiscal.direta;

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
import br.com.srcsoftware.sistema.notafiscal.direta.item.ItemNotaFiscalVendaDiretaPO;
import br.com.srcsoftware.sistema.pessoa.fornecedor.juridico.FornecedorJuridicoPO;

@Entity
@Table( name = "sistema_notasfiscalvendadireta", uniqueConstraints = @UniqueConstraint( columnNames = { "numero", "idFornecedor" }, name = "UK_sistema_notasfiscalvendadireta" ) )
public class NotaFiscalVendaDiretaPO extends AuditoriaAbstractPO implements Comparable< NotaFiscalVendaDiretaPO >{

	@Column( nullable = false, length = 10 )
	private LocalDate data;

	@Column( nullable = true, length = 20 )
	private String numero;

	@Column( name = "numero_recibo", nullable = true, length = 20 )
	private String numeroRecibo;

	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true )
	@JoinColumn( name = "idNotaFiscalVendaDireta", nullable = false, foreignKey = @ForeignKey( name = "fk_itemNotaFiscalVendaDireta_notasfiscalvendadireta" ) )
	private Set< ItemNotaFiscalVendaDiretaPO > itens = new HashSet<>();

	@ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, optional = true )
	@JoinColumn( name = "idFornecedor", nullable = true, foreignKey = @ForeignKey( name = "fk_fornecedor_notasfiscalvendadireta" ) )
	private FornecedorJuridicoPO fornecedor;

	@Lob
	@Column( nullable = true, length = 1000 )
	private String observacao;

	@Column( nullable = false, length = 20 )
	private String status;

	public String getNumeroRecibo() {
		return this.numeroRecibo;
	}

	public void setNumeroRecibo( String numeroRecibo ) {
		this.numeroRecibo = numeroRecibo;
	}

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

	public Set< ItemNotaFiscalVendaDiretaPO > getItens() {
		return this.itens;
	}

	public void setItens( Set< ItemNotaFiscalVendaDiretaPO > itens ) {
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
		NotaFiscalVendaDiretaPO other = (NotaFiscalVendaDiretaPO) obj;
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
		return "NotaFiscalVendaDiretaPO [data=" + this.data + ", numero=" + this.numero + ", numeroRecibo=" + this.numeroRecibo /*+ ", despesa=" + this.despesa */ + ", itens=" + this.itens + ", fornecedor=" + this.fornecedor + ", observacao=" + this.observacao + ", status=" + this.status + ", getNomeUsuarioCriacao()=" + this.getNomeUsuarioCriacao() + ", getNomeUsuarioAlteracao()=" + this.getNomeUsuarioAlteracao() + ", getDataOcorrenciaCriacao()=" + this.getDataOcorrenciaCriacao() + ", getDataOcorrenciaAlteracao()=" + this.getDataOcorrenciaAlteracao() + ", getId()=" + this.getId() + "]";
	}

	@Override
	public int compareTo( NotaFiscalVendaDiretaPO o ) {
		return this.getData().compareTo( o.getData() );
	}
}
