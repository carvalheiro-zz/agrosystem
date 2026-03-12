package br.com.srcsoftware.sistema.silo.entrada;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.srcsoftware.abstracts.AuditoriaAbstractPO;
import br.com.srcsoftware.modular.manager.pessoa.funcionario.FuncionarioPO;
import br.com.srcsoftware.sistema.safra.SafraPO;
import br.com.srcsoftware.sistema.safra.cultura.variedade.VariedadePO;
import br.com.srcsoftware.sistema.safra.setor.SetorPO;
import br.com.srcsoftware.sistema.silo.silo.SiloPO;

@Entity
@Table( name = "sistema_entradasgrao" )
public final class EntradaGraoPO extends AuditoriaAbstractPO implements Comparable< EntradaGraoPO >{

	@Column( length = 10, nullable = false )
	private LocalDate data;

	@Column( precision = 12, scale = 2, nullable = false )
	private BigDecimal pesoBruto;

	@Column( precision = 12, scale = 2, nullable = false )
	private BigDecimal pesoLiquido;

	@Column( precision = 12, scale = 2, nullable = false )
	private BigDecimal umidade;

	@Column( precision = 12, scale = 2, nullable = false )
	private BigDecimal percentualDescontoGeradoUmidade;

	@Column( precision = 12, scale = 2, nullable = false )
	private BigDecimal percentualDescontoGeradoImpureza;

	@Column( length = 255, nullable = false )
	private String nomeMotorista;

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idVariedade", nullable = false )
	private VariedadePO variedade;

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idSafra", nullable = false )
	private SafraPO safra = new SafraPO();

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idSetor", nullable = false )
	private SetorPO setor = new SetorPO();

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idEstoquista", nullable = false, foreignKey = @ForeignKey( name = "fk_estoquista_saidasgrao" ) )
	private FuncionarioPO estoquista = new FuncionarioPO();

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idSilo", nullable = false, foreignKey = @ForeignKey( name = "fk_silo_saidasgrao" ) )
	private SiloPO localArmazenagem;

	public BigDecimal getPesoLiquido() {
		return pesoLiquido;
	}

	public void setPesoLiquido( BigDecimal pesoLiquido ) {
		this.pesoLiquido = pesoLiquido;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData( LocalDate data ) {
		this.data = data;
	}

	public BigDecimal getPesoBruto() {
		return pesoBruto;
	}

	public void setPesoBruto( BigDecimal pesoBruto ) {
		this.pesoBruto = pesoBruto;
	}

	public BigDecimal getUmidade() {
		return umidade;
	}

	public void setUmidade( BigDecimal umidade ) {
		this.umidade = umidade;
	}

	public BigDecimal getPercentualDescontoGeradoUmidade() {
		return percentualDescontoGeradoUmidade;
	}

	public void setPercentualDescontoGeradoUmidade( BigDecimal percentualDescontoGeradoUmidade ) {
		this.percentualDescontoGeradoUmidade = percentualDescontoGeradoUmidade;
	}

	public BigDecimal getPercentualDescontoGeradoImpureza() {
		return percentualDescontoGeradoImpureza;
	}

	public void setPercentualDescontoGeradoImpureza( BigDecimal percentualDescontoGeradoImpureza ) {
		this.percentualDescontoGeradoImpureza = percentualDescontoGeradoImpureza;
	}

	public SiloPO getLocalArmazenagem() {
		return localArmazenagem;
	}

	public void setLocalArmazenagem( SiloPO localArmazenagem ) {
		this.localArmazenagem = localArmazenagem;
	}

	public String getNomeMotorista() {
		return nomeMotorista;
	}

	public void setNomeMotorista( String nomeMotorista ) {
		this.nomeMotorista = nomeMotorista;
	}

	public VariedadePO getVariedade() {
		return variedade;
	}

	public void setVariedade( VariedadePO variedade ) {
		this.variedade = variedade;
	}

	public SafraPO getSafra() {
		return safra;
	}

	public void setSafra( SafraPO safra ) {
		this.safra = safra;
	}

	public SetorPO getSetor() {
		return setor;
	}

	public void setSetor( SetorPO setor ) {
		this.setor = setor;
	}

	public FuncionarioPO getEstoquista() {
		return estoquista;
	}

	public void setEstoquista( FuncionarioPO estoquista ) {
		this.estoquista = estoquista;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( variedade == null ) ? 0 : variedade.hashCode() );
		result = prime * result + ( ( data == null ) ? 0 : data.hashCode() );
		result = prime * result + ( ( estoquista == null ) ? 0 : estoquista.hashCode() );
		result = prime * result + ( ( localArmazenagem == null ) ? 0 : localArmazenagem.hashCode() );
		result = prime * result + ( ( nomeMotorista == null ) ? 0 : nomeMotorista.hashCode() );
		result = prime * result + ( ( percentualDescontoGeradoImpureza == null ) ? 0 : percentualDescontoGeradoImpureza.hashCode() );
		result = prime * result + ( ( percentualDescontoGeradoUmidade == null ) ? 0 : percentualDescontoGeradoUmidade.hashCode() );
		result = prime * result + ( ( pesoBruto == null ) ? 0 : pesoBruto.hashCode() );
		result = prime * result + ( ( safra == null ) ? 0 : safra.hashCode() );
		result = prime * result + ( ( setor == null ) ? 0 : setor.hashCode() );
		result = prime * result + ( ( umidade == null ) ? 0 : umidade.hashCode() );
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
		EntradaGraoPO other = (EntradaGraoPO) obj;
		if ( variedade == null ) {
			if ( other.variedade != null )
				return false;
		} else if ( !variedade.equals( other.variedade ) )
			return false;
		if ( data == null ) {
			if ( other.data != null )
				return false;
		} else if ( !data.equals( other.data ) )
			return false;
		if ( estoquista == null ) {
			if ( other.estoquista != null )
				return false;
		} else if ( !estoquista.equals( other.estoquista ) )
			return false;

		if ( localArmazenagem == null ) {
			if ( other.localArmazenagem != null )
				return false;
		} else if ( !localArmazenagem.equals( other.localArmazenagem ) )
			return false;
		if ( nomeMotorista == null ) {
			if ( other.nomeMotorista != null )
				return false;
		} else if ( !nomeMotorista.equals( other.nomeMotorista ) )
			return false;
		if ( percentualDescontoGeradoImpureza == null ) {
			if ( other.percentualDescontoGeradoImpureza != null )
				return false;
		} else if ( !percentualDescontoGeradoImpureza.equals( other.percentualDescontoGeradoImpureza ) )
			return false;
		if ( percentualDescontoGeradoUmidade == null ) {
			if ( other.percentualDescontoGeradoUmidade != null )
				return false;
		} else if ( !percentualDescontoGeradoUmidade.equals( other.percentualDescontoGeradoUmidade ) )
			return false;
		if ( pesoBruto == null ) {
			if ( other.pesoBruto != null )
				return false;
		} else if ( !pesoBruto.equals( other.pesoBruto ) )
			return false;
		if ( safra == null ) {
			if ( other.safra != null )
				return false;
		} else if ( !safra.equals( other.safra ) )
			return false;
		if ( setor == null ) {
			if ( other.setor != null )
				return false;
		} else if ( !setor.equals( other.setor ) )
			return false;
		if ( umidade == null ) {
			if ( other.umidade != null )
				return false;
		} else if ( !umidade.equals( other.umidade ) )
			return false;
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
		builder.append( "EntradaGraoPO [data=" );
		builder.append( data );
		builder.append( ", pesoBruto=" );
		builder.append( pesoBruto );
		builder.append( ", pesoLiquido=" );
		builder.append( pesoLiquido );
		builder.append( ", umidade=" );
		builder.append( umidade );
		builder.append( ", percentualDescontoGeradoUmidade=" );
		builder.append( percentualDescontoGeradoUmidade );
		builder.append( ", percentualDescontoGeradoImpureza=" );
		builder.append( percentualDescontoGeradoImpureza );
		builder.append( ", nomeMotorista=" );
		builder.append( nomeMotorista );
		builder.append( ", variedade=" );
		builder.append( variedade );
		builder.append( ", safra=" );
		builder.append( safra );
		builder.append( ", setor=" );
		builder.append( setor );
		builder.append( ", estoquista=" );
		builder.append( estoquista );
		builder.append( ", localArmazenagem=" );
		builder.append( localArmazenagem );
		builder.append( ", toString()=" );
		builder.append( super.toString() );
		builder.append( "]" );
		return builder.toString();
	}

	@Override
	public int compareTo( EntradaGraoPO o ) {
		return this.getData().compareTo( o.getData() );
	}

}
