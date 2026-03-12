package br.com.srcsoftware.sistema.silo.saida;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.srcsoftware.abstracts.AuditoriaAbstractPO;
import br.com.srcsoftware.modular.manager.pessoa.cliente.ClientePO;
import br.com.srcsoftware.modular.manager.pessoa.funcionario.FuncionarioPO;
import br.com.srcsoftware.sistema.safra.SafraPO;
import br.com.srcsoftware.sistema.safra.cultura.CulturaPO;
import br.com.srcsoftware.sistema.silo.contratovenda.ContratoVendaPO;
import br.com.srcsoftware.sistema.silo.naturezaoperacao.NaturezaOperacaoPO;
import br.com.srcsoftware.sistema.silo.silo.SiloPO;

@Entity
@Table( name = "sistema_saidasgrao", uniqueConstraints = @UniqueConstraint( columnNames = { "idContratoVenda", "numeroNotaFiscal", "ticket" }, name = "UK_sistema_saidasgrao" ) )
public final class SaidaGraoPO extends AuditoriaAbstractPO implements Comparable< SaidaGraoPO >{
	@Column( length = 10, nullable = false )
	private LocalDate data;

	@Column( precision = 12, scale = 2, nullable = false )
	private BigDecimal pesoLiquido;

	@Column( precision = 12, scale = 2, nullable = false )
	private BigDecimal valorBruto;

	@Column( precision = 12, scale = 2, nullable = false )
	private BigDecimal valorLiquido;

	@Column( precision = 12, scale = 2, nullable = true )
	private BigDecimal percentualDesconto;

	@Lob
	@Column( length = 1000, nullable = true )
	private String observacaoDesconto;

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idCultura", nullable = false, foreignKey = @ForeignKey( name = "fk_cultura_saidasgrao" ) )
	private CulturaPO cultura;

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idCliente", nullable = false, foreignKey = @ForeignKey( name = "fk_cliente_saidasgrao" ) )
	private ClientePO cliente = new ClientePO();

	@Column( length = 50, nullable = false )
	private String ticket;

	@Column( length = 50, nullable = false )
	private String numeroNotaFiscal;

	@Column( length = 10, nullable = true )
	private String placa;

	@Column( length = 100, nullable = true )
	private String motorista;

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idNaturezaOperacao", nullable = false, foreignKey = @ForeignKey( name = "fk_naturezaOperacao_saidasgrao" ) )
	private NaturezaOperacaoPO naturezaOperacao;

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idContratoVenda", nullable = false, foreignKey = @ForeignKey( name = "fk_contratoVenda_saidasgrao" ) )
	private ContratoVendaPO contratoVenda;

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idEstoquista", nullable = false, foreignKey = @ForeignKey( name = "fk_estoquista_saidasgrao" ) )
	private FuncionarioPO estoquista = new FuncionarioPO();

	@Column( precision = 12, scale = 2, nullable = false )
	private BigDecimal pesoLiquido01;
	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idSilo01", nullable = false, foreignKey = @ForeignKey( name = "fk_silo01_saidasgrao" ) )
	private SiloPO localArmazenagem01;

	@Column( precision = 12, scale = 2, nullable = true )
	private BigDecimal pesoLiquido02;
	@ManyToOne( fetch = FetchType.EAGER, optional = true )
	@JoinColumn( name = "idSilo02", nullable = true, foreignKey = @ForeignKey( name = "fk_silo02_saidasgrao" ) )
	private SiloPO localArmazenagem02;

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idSafra01", nullable = false, foreignKey = @ForeignKey( name = "fk_safra01_saidasgrao" ) )
	private SafraPO safra01 = new SafraPO();
	@Column( precision = 12, scale = 2, nullable = false )
	private BigDecimal pesoLiquidoSafra01;

	@ManyToOne( fetch = FetchType.EAGER, optional = true )
	@JoinColumn( name = "idSafra02", nullable = true, foreignKey = @ForeignKey( name = "fk_safra02_saidasgrao" ) )
	private SafraPO safra02 = new SafraPO();

	@Column( precision = 12, scale = 2, nullable = true )
	private BigDecimal pesoLiquidoSafra02;

	public String getPlaca() {
		return placa;
	}

	public void setPlaca( String placa ) {
		this.placa = placa;
	}

	public String getMotorista() {
		return motorista;
	}

	public void setMotorista( String motorista ) {
		this.motorista = motorista;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData( LocalDate data ) {
		this.data = data;
	}

	public BigDecimal getPesoLiquido() {
		return pesoLiquido;
	}

	public void setPesoLiquido( BigDecimal pesoLiquido ) {
		this.pesoLiquido = pesoLiquido;
	}

	public BigDecimal getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto( BigDecimal valorBruto ) {
		this.valorBruto = valorBruto;
	}

	public BigDecimal getValorLiquido() {
		return valorLiquido;
	}

	public void setValorLiquido( BigDecimal valorLiquido ) {
		this.valorLiquido = valorLiquido;
	}

	public BigDecimal getPercentualDesconto() {
		return percentualDesconto;
	}

	public void setPercentualDesconto( BigDecimal percentualDesconto ) {
		this.percentualDesconto = percentualDesconto;
	}

	public String getObservacaoDesconto() {
		return observacaoDesconto;
	}

	public void setObservacaoDesconto( String observacaoDesconto ) {
		this.observacaoDesconto = observacaoDesconto;
	}

	public CulturaPO getCultura() {
		return cultura;
	}

	public void setCultura( CulturaPO cultura ) {
		this.cultura = cultura;
	}

	public ClientePO getCliente() {
		return cliente;
	}

	public void setCliente( ClientePO cliente ) {
		this.cliente = cliente;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket( String ticket ) {
		this.ticket = ticket;
	}

	public String getNumeroNotaFiscal() {
		return numeroNotaFiscal;
	}

	public void setNumeroNotaFiscal( String numeroNotaFiscal ) {
		this.numeroNotaFiscal = numeroNotaFiscal;
	}

	public NaturezaOperacaoPO getNaturezaOperacao() {
		return naturezaOperacao;
	}

	public void setNaturezaOperacao( NaturezaOperacaoPO naturezaOperacao ) {
		this.naturezaOperacao = naturezaOperacao;
	}

	public ContratoVendaPO getContratoVenda() {
		return contratoVenda;
	}

	public void setContratoVenda( ContratoVendaPO contratoVenda ) {
		this.contratoVenda = contratoVenda;
	}

	public FuncionarioPO getEstoquista() {
		return estoquista;
	}

	public void setEstoquista( FuncionarioPO estoquista ) {
		this.estoquista = estoquista;
	}

	public BigDecimal getPesoLiquido01() {
		return pesoLiquido01;
	}

	public void setPesoLiquido01( BigDecimal pesoLiquido01 ) {
		this.pesoLiquido01 = pesoLiquido01;
	}

	public SiloPO getLocalArmazenagem01() {
		return localArmazenagem01;
	}

	public void setLocalArmazenagem01( SiloPO localArmazenagem01 ) {
		this.localArmazenagem01 = localArmazenagem01;
	}

	public BigDecimal getPesoLiquido02() {
		return pesoLiquido02;
	}

	public void setPesoLiquido02( BigDecimal pesoLiquido02 ) {
		this.pesoLiquido02 = pesoLiquido02;
	}

	public SiloPO getLocalArmazenagem02() {
		return localArmazenagem02;
	}

	public void setLocalArmazenagem02( SiloPO localArmazenagem02 ) {
		this.localArmazenagem02 = localArmazenagem02;
	}

	public SafraPO getSafra01() {
		return safra01;
	}

	public void setSafra01( SafraPO safra01 ) {
		this.safra01 = safra01;
	}

	public BigDecimal getPesoLiquidoSafra01() {
		return pesoLiquidoSafra01;
	}

	public void setPesoLiquidoSafra01( BigDecimal pesoLiquidoSafra01 ) {
		this.pesoLiquidoSafra01 = pesoLiquidoSafra01;
	}

	public SafraPO getSafra02() {
		return safra02;
	}

	public void setSafra02( SafraPO safra02 ) {
		this.safra02 = safra02;
	}

	public BigDecimal getPesoLiquidoSafra02() {
		return pesoLiquidoSafra02;
	}

	public void setPesoLiquidoSafra02( BigDecimal pesoLiquidoSafra02 ) {
		this.pesoLiquidoSafra02 = pesoLiquidoSafra02;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( contratoVenda == null ) ? 0 : contratoVenda.hashCode() );
		result = prime * result + ( ( numeroNotaFiscal == null ) ? 0 : numeroNotaFiscal.hashCode() );
		result = prime * result + ( ( ticket == null ) ? 0 : ticket.hashCode() );
		return result;
	}

	/**
	 * Polimorfico
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}
		if ( obj == null ) {
			return false;
		}
		if ( !( obj instanceof SaidaGraoPO ) ) {
			return false;
		}
		SaidaGraoPO other = (SaidaGraoPO) obj;
		if ( contratoVenda == null ) {
			if ( other.contratoVenda != null ) {
				return false;
			}
		} else if ( !contratoVenda.equals( other.contratoVenda ) ) {
			return false;
		}
		if ( numeroNotaFiscal == null ) {
			if ( other.numeroNotaFiscal != null ) {
				return false;
			}
		} else if ( !numeroNotaFiscal.equals( other.numeroNotaFiscal ) ) {
			return false;
		}
		if ( ticket == null ) {
			if ( other.ticket != null ) {
				return false;
			}
		} else if ( !ticket.equals( other.ticket ) ) {
			return false;
		}
		return true;
	}

	/**
	 * Polimorfico
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "SaidaGraoPO [data=" );
		builder.append( data );
		builder.append( ", pesoLiquido=" );
		builder.append( pesoLiquido );
		builder.append( ", valorBruto=" );
		builder.append( valorBruto );
		builder.append( ", valorLiquido=" );
		builder.append( valorLiquido );
		builder.append( ", percentualDesconto=" );
		builder.append( percentualDesconto );
		builder.append( ", observacaoDesconto=" );
		builder.append( observacaoDesconto );
		builder.append( ", cultura=" );
		builder.append( cultura );
		builder.append( ", cliente=" );
		builder.append( cliente );
		builder.append( ", ticket=" );
		builder.append( ticket );
		builder.append( ", numeroNotaFiscal=" );
		builder.append( numeroNotaFiscal );
		builder.append( ", placa=" );
		builder.append( placa );
		builder.append( ", motorista=" );
		builder.append( motorista );
		builder.append( ", naturezaOperacao=" );
		builder.append( naturezaOperacao );
		builder.append( ", contratoVenda=" );
		builder.append( contratoVenda );
		builder.append( ", estoquista=" );
		builder.append( estoquista );
		builder.append( ", pesoLiquido01=" );
		builder.append( pesoLiquido01 );
		builder.append( ", localArmazenagem01=" );
		builder.append( localArmazenagem01 );
		builder.append( ", pesoLiquido02=" );
		builder.append( pesoLiquido02 );
		builder.append( ", localArmazenagem02=" );
		builder.append( localArmazenagem02 );
		builder.append( ", safra01=" );
		builder.append( safra01 );
		builder.append( ", pesoLiquidoSafra01=" );
		builder.append( pesoLiquidoSafra01 );
		builder.append( ", safra02=" );
		builder.append( safra02 );
		builder.append( ", pesoLiquidoSafra02=" );
		builder.append( pesoLiquidoSafra02 );
		builder.append( ", toString()=" );
		builder.append( super.toString() );
		builder.append( "]" );
		return builder.toString();
	}

	@Override
	public int compareTo( SaidaGraoPO o ) {
		return this.getData().compareTo( o.getData() );
	}

}
