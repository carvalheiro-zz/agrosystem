package br.com.srcsoftware.sistema.notafiscal.rateio.itemnotafiscalrateio;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.srcsoftware.abstracts.AbstractPO;
import br.com.srcsoftware.sistema.notafiscal.rateio.centrocusto.CentroCustoPO;
import br.com.srcsoftware.sistema.safra.SafraPO;
import br.com.srcsoftware.sistema.safra.cultura.CulturaPO;
import br.com.srcsoftware.sistema.safra.setor.SetorPO;

@Entity
@Table( name = "sistema_itensnotafiscalrateio" )
public class ItemNotaFiscalRateioPO extends AbstractPO{

	@Lob
	@Column( nullable = true, length = 1000 )
	private String descricao;

	@Column( precision = 16, scale = 2, nullable = false )
	private BigDecimal valor;

	@Column( name = "tipo", length = 20, nullable = false )
	private String tipo;

	@ManyToOne( fetch = FetchType.EAGER, optional = true )
	@JoinColumn( name = "idCultura", nullable = true, foreignKey = @ForeignKey( name = "fk_cultura_itensnotafiscalrateio" ) )
	private CulturaPO cultura;

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idSafra", nullable = false, foreignKey = @ForeignKey( name = "fk_safra_itensnotafiscalrateio" ) )
	private SafraPO safra = new SafraPO();

	@ManyToOne( fetch = FetchType.EAGER, optional = true )
	@JoinColumn( name = "idSetor", nullable = true, foreignKey = @ForeignKey( name = "fk_setor_itensnotafiscalrateio" ) )
	private SetorPO setor = new SetorPO();

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idCentroCusto", nullable = false, foreignKey = @ForeignKey( name = "fk_centroCusto_itensnotafiscalrateio" ) )
	private CentroCustoPO centroCusto;

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao( String descricao ) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor( BigDecimal valor ) {
		this.valor = valor;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo( String tipo ) {
		this.tipo = tipo;
	}

	public CulturaPO getCultura() {
		return this.cultura;
	}

	public void setCultura( CulturaPO cultura ) {
		this.cultura = cultura;
	}

	public SafraPO getSafra() {
		return this.safra;
	}

	public void setSafra( SafraPO safra ) {
		this.safra = safra;
	}

	public SetorPO getSetor() {
		return this.setor;
	}

	public void setSetor( SetorPO setor ) {
		this.setor = setor;
	}

	public CentroCustoPO getCentroCusto() {
		return this.centroCusto;
	}

	public void setCentroCusto( CentroCustoPO centroCusto ) {
		this.centroCusto = centroCusto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( centroCusto == null ) ? 0 : centroCusto.hashCode() );
		result = prime * result + ( ( cultura == null ) ? 0 : cultura.hashCode() );
		result = prime * result + ( ( descricao == null ) ? 0 : descricao.hashCode() );
		result = prime * result + ( ( safra == null ) ? 0 : safra.hashCode() );
		result = prime * result + ( ( setor == null ) ? 0 : setor.hashCode() );
		result = prime * result + ( ( tipo == null ) ? 0 : tipo.hashCode() );
		result = prime * result + ( ( valor == null ) ? 0 : valor.hashCode() );
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
		ItemNotaFiscalRateioPO other = (ItemNotaFiscalRateioPO) obj;
		if ( centroCusto == null ) {
			if ( other.centroCusto != null )
				return false;
		} else if ( !centroCusto.equals( other.centroCusto ) )
			return false;
		if ( cultura == null ) {
			if ( other.cultura != null )
				return false;
		} else if ( !cultura.equals( other.cultura ) )
			return false;
		if ( descricao == null ) {
			if ( other.descricao != null )
				return false;
		} else if ( !descricao.equals( other.descricao ) )
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
		if ( tipo == null ) {
			if ( other.tipo != null )
				return false;
		} else if ( !tipo.equals( other.tipo ) )
			return false;
		if ( valor == null ) {
			if ( other.valor != null )
				return false;
		} else if ( !valor.equals( other.valor ) )
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "ItemNotaFiscalRateio [\n\tdescricao=" );
		builder.append( this.descricao );
		builder.append( ", \nvalor=" );
		builder.append( this.valor );
		builder.append( ", \ntipo=" );
		builder.append( this.tipo );
		builder.append( ", \ncultura=" );
		builder.append( this.cultura );
		builder.append( ", \nsafra=" );
		builder.append( this.safra );
		builder.append( ", \nsetor=" );
		builder.append( this.setor );
		builder.append( ", \ncentroCusto=" );
		builder.append( this.centroCusto );
		builder.append( "]\n" );
		return builder.toString();
	}

}
