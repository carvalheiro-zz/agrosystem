package br.com.srcsoftware.gestao.sistema.notafiscal.rateio;

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

import br.com.srcsoftware.abstracts.AbstractPO;
import br.com.srcsoftware.gestao.manager.pessoa.cliente.juridico.ClienteJuridicoMigracaoPO;
import br.com.srcsoftware.gestao.sistema.notafiscal.rateio.itemnotafiscalrateio.ItemNotaFiscalRateioMigracaoPO;
import br.com.srcsoftware.gestao.sistema.pedido.fornecedor.FornecedorMigracaoPO;
import br.com.srcsoftware.modular.manager.utilidades.Constantes;

@Entity
@Table( name = "notas_fiscais_rateios",
        schema = Constantes.SCHEMA_MIGRACAO,
        uniqueConstraints = @UniqueConstraint( columnNames = { "numero", "numeroRecibo", "idFornecedor", "idFornecedor"/*, "id_contaPagar", "id_contaReceber"*/ }, name = "UK_notas_fiscais_rateios" ) )
public class NotaFiscalRateioMigracaoPO extends AbstractPO implements Comparable< NotaFiscalRateioMigracaoPO >{

	@Column( nullable = false, length = 20 )
	private String tipo;// Despesa/Investimento - Receita

	@Column( nullable = true, length = 20 )
	private String numero;

	@Column( nullable = true, length = 20 )
	private String numeroRecibo;

	/*@OneToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, optional = true )
	@JoinColumn( name = "id_contaReceber", nullable = true )
	private ContaReceberPO contaReceber;
	 
	@OneToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, optional = true )
	@JoinColumn( name = "id_contaPagar", nullable = true )
	private ContaPagarPO contaPagar;*/

	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true )
	@JoinColumn( name = "id_nota_fiscal_rateio", nullable = false )
	private Set< ItemNotaFiscalRateioMigracaoPO > itens = new HashSet<>();

	@ManyToOne( fetch = FetchType.EAGER, optional = true )
	@JoinColumn( name = "idFornecedor", nullable = true, foreignKey = @ForeignKey( name = "fk_fornecedor_nota_fiscal_rateio" ) )
	private FornecedorMigracaoPO fornecedor;

	@ManyToOne( fetch = FetchType.EAGER, optional = true )
	@JoinColumn( name = "idCliente", nullable = true, foreignKey = @ForeignKey( name = "fk_cliente_nota_fiscal_rateio" ) )
	private ClienteJuridicoMigracaoPO cliente;

	public ClienteJuridicoMigracaoPO getCliente() {
		return cliente;
	}

	public void setCliente( ClienteJuridicoMigracaoPO cliente ) {
		this.cliente = cliente;
	}

	public FornecedorMigracaoPO getFornecedor() {
		return this.fornecedor;
	}

	public void setFornecedor( FornecedorMigracaoPO fornecedor ) {
		this.fornecedor = fornecedor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo( String tipo ) {
		this.tipo = tipo;
	}

	/*public ContaPagarPO getContaPagar() {
		return contaPagar;
	}
	
	public void setContaPagar( ContaPagarPO contaPagar ) {
		this.contaPagar = contaPagar;
	}*/

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

	public Set< ItemNotaFiscalRateioMigracaoPO > getItens() {
		return this.itens;
	}

	public void setItens( Set< ItemNotaFiscalRateioMigracaoPO > itens ) {
		this.itens = itens;
	}

	/*public ContaReceberPO getContaReceber() {
		return contaReceber;
	}
	
	public void setContaReceber( ContaReceberPO contaReceber ) {
		this.contaReceber = contaReceber;
	}*/

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 0;
		result = ( prime * result ) + ( ( this.numero == null ) ? 0 : this.numero.hashCode() );
		result = ( prime * result ) + ( ( this.numeroRecibo == null ) ? 0 : this.numeroRecibo.hashCode() );
		//result = ( prime * result ) + ( ( this.contaPagar == null ) ? 0 : this.contaPagar.hashCode() );
		//result = ( prime * result ) + ( ( this.contaPagar == null ) ? 0 : this.contaPagar.hashCode() );
		result = ( prime * result ) + ( ( this.tipo == null ) ? 0 : this.tipo.hashCode() );
		result = prime * result + ( ( fornecedor == null ) ? 0 : fornecedor.hashCode() );
		result = prime * result + ( ( cliente == null ) ? 0 : cliente.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}

		if ( !( obj instanceof NotaFiscalRateioMigracaoPO ) ) {
			return false;
		}
		NotaFiscalRateioMigracaoPO other = (NotaFiscalRateioMigracaoPO) obj;
		if ( this.numero == null ) {
			if ( other.numero != null ) {
				return false;
			}
		} else if ( !this.numero.equals( other.numero ) ) {
			return false;
		}
		if ( this.numeroRecibo == null ) {
			if ( other.numeroRecibo != null ) {
				return false;
			}
		} else if ( !this.numeroRecibo.equals( other.numeroRecibo ) ) {
			return false;
		}
		/*if ( this.contaPagar == null ) {
			if ( other.contaPagar != null ) {
				return false;
			}
		} else if ( !this.contaPagar.equals( other.contaPagar ) ) {
			return false;
		}*/
		if ( this.tipo == null ) {
			if ( other.tipo != null ) {
				return false;
			}
		} else if ( !this.tipo.equals( other.tipo ) ) {
			return false;
		}
		if ( fornecedor == null ) {
			if ( other.fornecedor != null ) {
				return false;
			}
		} else if ( !fornecedor.equals( other.fornecedor ) ) {
			return false;
		}
		if ( cliente == null ) {
			if ( other.cliente != null ) {
				return false;
			}
		} else if ( !cliente.equals( other.cliente ) ) {
			return false;
		}
		/*if ( this.contaReceber == null ) {
			if ( other.contaReceber != null ) {
				return false;
			}
		} else if ( !this.contaReceber.equals( other.contaReceber ) ) {
			return false;
		}*/
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
		//builder.append( contaPagar );
		builder.append( ", \ncontaReceber=" );
		//builder.append( contaReceber );
		builder.append( ",\nfornecedor=" );
		builder.append( fornecedor );
		builder.append( ",\ncliente=" );
		builder.append( cliente );
		builder.append( ", \nitens=" );
		builder.append( itens );
		builder.append( "]\n" );
		return builder.toString();
	}

	@Override
	public int compareTo( NotaFiscalRateioMigracaoPO o ) {
		/*if ( this.getContaPagar() != null ) {
			return this.getContaPagar().getData().compareTo( o.getContaPagar().getData() );
		}
		if ( this.getContaReceber() != null ) {
			return this.getContaReceber().getData().compareTo( o.getContaReceber().getData() );
		}*/
		return getId().compareTo( o.getId() );
	}
}
