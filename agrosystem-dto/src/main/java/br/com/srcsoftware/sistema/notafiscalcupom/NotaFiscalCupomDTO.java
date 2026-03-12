package br.com.srcsoftware.sistema.notafiscalcupom;

import java.math.BigDecimal;
import java.util.ArrayList;

import br.com.srcsoftware.abstracts.AuditoriaAbstractDTO;
import br.com.srcsoftware.modular.manager.utilidades.DateUtil;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.cupom.CupomDTO;
import br.com.srcsoftware.sistema.pessoa.fornecedor.juridico.FornecedorJuridicoDTO;

public class NotaFiscalCupomDTO extends AuditoriaAbstractDTO implements Comparable< NotaFiscalCupomDTO >{

	private static final long serialVersionUID = -1671769068277662017L;

	private String id;
	private String data;
	private String numero;
	private ArrayList< CupomDTO > cupons;
	private FornecedorJuridicoDTO fornecedor;

	public String getDataToString() {
		return DateUtil.formatLocalDate( getData() );
	}

	public String getValorCustoTotal() {
		BigDecimal soma = BigDecimal.ZERO;
		for ( CupomDTO itemCorrente : this.getCupons() ) {

			if ( !Boolean.valueOf( itemCorrente.getNotaEmitida() ) ) {
				continue;
			}

			BigDecimal custoItem = Utilidades.parseBigDecimal( itemCorrente.getValorCustoTotal() );

			if ( Utilidades.isNuloOuVazio( custoItem ) ) {
				continue;
			}

			soma = soma.add( custoItem );
		}
		return Utilidades.parseBigDecimal( soma );
	}

	public String getId() {
		return id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public String getQuantidadeCupons() {
		return String.valueOf( this.getCupons().size() );
	}

	public ArrayList< CupomDTO > getCupons() {
		if ( this.cupons == null ) {
			this.cupons = new ArrayList< >();
		}
		return this.cupons;
	}

	public void setCupons( ArrayList< CupomDTO > cupons ) {
		this.cupons = cupons;
	}

	public String getData() {
		return this.data;
	}

	public void setData( String data ) {
		this.data = data;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero( String numero ) {
		this.numero = numero;
	}

	public FornecedorJuridicoDTO getFornecedor() {
		if ( this.fornecedor == null ) {
			this.fornecedor = new FornecedorJuridicoDTO();
		}
		return this.fornecedor;
	}

	public void setFornecedor( FornecedorJuridicoDTO fornecedor ) {
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
		NotaFiscalCupomDTO other = (NotaFiscalCupomDTO) obj;
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
		return "NotaFiscalCupomDTO [data=" + this.data + ", numero=" + this.numero + ", cupons=" + this.cupons + ", fornecedor=" + this.fornecedor + ", getNomeUsuarioCriacao()=" + this.getNomeUsuarioCriacao() + ", getNomeUsuarioAlteracao()=" + this.getNomeUsuarioAlteracao() + ", getDataOcorrenciaCriacao()=" + this.getDataOcorrenciaCriacao() + ", getDataOcorrenciaAlteracao()=" + this.getDataOcorrenciaAlteracao() + ", getId()=" + this.getId() + "]";
	}

	@Override
	public int compareTo( NotaFiscalCupomDTO o ) {
		return this.getData().compareTo( o.getData() );
	}
}
