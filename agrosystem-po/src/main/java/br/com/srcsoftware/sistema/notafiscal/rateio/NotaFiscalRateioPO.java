package br.com.srcsoftware.sistema.notafiscal.rateio;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.srcsoftware.abstracts.AbstractPO;
import br.com.srcsoftware.modular.financeiro.contapagar.ContaPagarPO;
import br.com.srcsoftware.modular.financeiro.contareceber.ContaReceberPO;
import br.com.srcsoftware.modular.manager.pessoa.cliente.ClientePO;
import br.com.srcsoftware.sistema.notafiscal.rateio.itemnotafiscalrateio.ItemNotaFiscalRateioPO;
import br.com.srcsoftware.sistema.pessoa.fornecedor.juridico.FornecedorJuridicoPO;

@Entity
@Table( name = "sistema_notasfiscalrateio", uniqueConstraints = @UniqueConstraint( columnNames = { "numero", "numeroRecibo", "idFornecedor", "idFornecedor", "idContaPagar", "idContaReceber" }, name = "UK_sistema_notasfiscalrateio" ) )
public class NotaFiscalRateioPO extends AbstractPO implements Comparable< NotaFiscalRateioPO >{

	@Column( nullable = false, length = 20 )
	private String tipo;// Despesa/Investimento - Receita

	@Column( nullable = true, length = 20 )
	private String numero;

	@Column( nullable = true, length = 20 )
	private String numeroRecibo;

	@OneToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, optional = true )
	@JoinColumn( name = "idContaReceber", nullable = true, foreignKey = @ForeignKey( name = "fk_contaReceber_notafiscalrateio" ) )
	private ContaReceberPO contaReceber;

	@OneToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, optional = true )
	@JoinColumn( name = "idContaPagar", nullable = true, foreignKey = @ForeignKey( name = "fk_contaPagar_notafiscalrateio" ) )
	private ContaPagarPO contaPagar;

	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true )
	@JoinColumn( name = "idNotaFiscalRateio", nullable = false )
	private Set< ItemNotaFiscalRateioPO > itens = new HashSet<>();

	@ManyToOne( fetch = FetchType.EAGER, optional = true )
	@JoinColumn( name = "idFornecedor", nullable = true, foreignKey = @ForeignKey( name = "fk_fornecedor_notafiscalrateio" ) )
	private FornecedorJuridicoPO fornecedor;

	@ManyToOne( fetch = FetchType.EAGER, optional = true )
	@JoinColumn( name = "idCliente", nullable = true, foreignKey = @ForeignKey( name = "fk_cliente_notafiscalrateio" ) )
	private ClientePO cliente;

	public ClientePO getCliente() {
		return cliente;
	}

	public void setCliente( ClientePO cliente ) {
		this.cliente = cliente;
	}

	public FornecedorJuridicoPO getFornecedor() {
		return this.fornecedor;
	}

	public void setFornecedor( FornecedorJuridicoPO fornecedor ) {
		this.fornecedor = fornecedor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo( String tipo ) {
		this.tipo = tipo;
	}

	public ContaPagarPO getContaPagar() {
		return contaPagar;
	}

	public void setContaPagar( ContaPagarPO contaPagar ) {
		this.contaPagar = contaPagar;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero( String numero ) {
		this.numero = numero;
	}

	public String getNumeroRecibo() {
		return this.numeroRecibo;
	}

	public void setNumeroRecibo( String numeroRecibo ) {
		this.numeroRecibo = numeroRecibo;
	}

	public Set< ItemNotaFiscalRateioPO > getItens() {
		return this.itens;
	}

	public void setItens( Set< ItemNotaFiscalRateioPO > itens ) {
		this.itens = itens;
	}

	public ContaReceberPO getContaReceber() {
		return contaReceber;
	}

	public void setContaReceber( ContaReceberPO contaReceber ) {
		this.contaReceber = contaReceber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( contaPagar == null ) ? 0 : contaPagar.hashCode() );
		result = prime * result + ( ( contaReceber == null ) ? 0 : contaReceber.hashCode() );
		result = prime * result + ( ( fornecedor == null ) ? 0 : fornecedor.hashCode() );
		result = prime * result + ( ( numero == null ) ? 0 : numero.hashCode() );
		result = prime * result + ( ( numeroRecibo == null ) ? 0 : numeroRecibo.hashCode() );
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
		NotaFiscalRateioPO other = (NotaFiscalRateioPO) obj;
		if ( contaPagar == null ) {
			if ( other.contaPagar != null )
				return false;
		} else if ( !contaPagar.equals( other.contaPagar ) )
			return false;
		if ( contaReceber == null ) {
			if ( other.contaReceber != null )
				return false;
		} else if ( !contaReceber.equals( other.contaReceber ) )
			return false;
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
		if ( numeroRecibo == null ) {
			if ( other.numeroRecibo != null )
				return false;
		} else if ( !numeroRecibo.equals( other.numeroRecibo ) )
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "NotaFiscalRateioPO [\n\tnumero=" );
		builder.append( numero );
		builder.append( ", \nnumeroRecibo=" );
		builder.append( numeroRecibo );
		builder.append( ", \ntipo=" );
		builder.append( tipo );
		builder.append( ", \ncontaPagar=" );
		builder.append( contaPagar );
		builder.append( ", \ncontaReceber=" );
		builder.append( contaReceber );
		builder.append( ",\nfornecedor=" );
		builder.append( fornecedor );
		builder.append( ",\ncliente=" );
		builder.append( cliente );
		builder.append( ", \nitens=" );
		builder.append( itens );
		builder.append( ", \ngetId()=" );
		builder.append( getId() );
		builder.append( "]\n" );
		return builder.toString();
	}

	@Override
	public int compareTo( NotaFiscalRateioPO o ) {
		if ( this.getContaPagar() != null ) {
			return this.getContaPagar().getData().compareTo( o.getContaPagar().getData() );
		}
		if ( this.getContaReceber() != null ) {
			return this.getContaReceber().getData().compareTo( o.getContaReceber().getData() );
		}
		return getId().compareTo( o.getId() );
	}
}
