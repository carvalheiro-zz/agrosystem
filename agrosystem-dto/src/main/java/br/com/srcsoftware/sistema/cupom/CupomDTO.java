package br.com.srcsoftware.sistema.cupom;

import java.math.BigDecimal;
import java.util.ArrayList;

import br.com.srcsoftware.abstracts.AuditoriaAbstractDTO;
import br.com.srcsoftware.modular.manager.utilidades.DateUtil;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.cupom.item.ItemCupomDTO;
import br.com.srcsoftware.sistema.pessoa.fornecedor.juridico.FornecedorJuridicoDTO;

public class CupomDTO extends AuditoriaAbstractDTO implements Comparable< CupomDTO >{

	private static final long serialVersionUID = -1817489579203351393L;

	private String id;
	private String data;
	private String numero;
	private ArrayList< ItemCupomDTO > itens;
	private FornecedorJuridicoDTO fornecedor;
	//Para gerenciar os Cupons que ja foram emitido a Nota
	private String notaEmitida;

	public String getDataToString() {
		return DateUtil.formatLocalDate( getData() );
	}

	public String getCorInformativa() {
		Boolean notaEmitidaBoolean = Boolean.valueOf( notaEmitida );

		if ( notaEmitidaBoolean ) {
			return "info";
		}
		return "";

	}

	public String getValorCustoTotal() {
		BigDecimal soma = BigDecimal.ZERO;
		for ( ItemCupomDTO itemCorrente : this.getItens() ) {
			BigDecimal custo = BigDecimal.ZERO;
			BigDecimal quantidade = Utilidades.parseBigDecimal( itemCorrente.getQuantidade() );

			BigDecimal custoItem = Utilidades.parseBigDecimal( itemCorrente.getPrecoCusto() );

			if ( Utilidades.isNuloOuVazio( custoItem ) ) {
				continue;
			}

			custo = custoItem.multiply( quantidade ).setScale( 2, BigDecimal.ROUND_FLOOR );

			soma = soma.add( custo );
		}
		return Utilidades.parseBigDecimal( soma );
	}

	public String getQuantidadeItens() {
		return String.valueOf( this.getItens().size() );
	}

	public String getQuantidadeTotalItens() {
		BigDecimal soma = BigDecimal.ZERO;
		for ( ItemCupomDTO itemCorrente : this.getItens() ) {
			BigDecimal quantidade = BigDecimal.ZERO;
			quantidade = Utilidades.parseBigDecimal( itemCorrente.getQuantidade() );

			soma = soma.add( quantidade );
		}
		return Utilidades.parseBigDecimal( soma );
	}

	public String getId() {
		return id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public String getNotaEmitida() {
		return this.notaEmitida;
	}

	public void setNotaEmitida( String notaEmitida ) {
		this.notaEmitida = notaEmitida;
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

	public ArrayList< ItemCupomDTO > getItens() {
		if ( this.itens == null ) {
			this.itens = new ArrayList<>();
		}
		return this.itens;
	}

	public void setItens( ArrayList< ItemCupomDTO > itens ) {
		this.itens = itens;
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
		CupomDTO other = (CupomDTO) obj;
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
		return "CupomDTO [data=" + this.data + ", numero=" + this.numero + ", itens=" + this.itens + ", fornecedor=" + this.fornecedor + ", getNomeUsuarioCriacao()=" + this.getNomeUsuarioCriacao() + ", getNomeUsuarioAlteracao()=" + this.getNomeUsuarioAlteracao() + ", getDataOcorrenciaCriacao()=" + this.getDataOcorrenciaCriacao() + ", getDataOcorrenciaAlteracao()=" + this.getDataOcorrenciaAlteracao() + ", getId()=" + this.getId() + "]";
	}

	@Override
	public int compareTo( CupomDTO o ) {
		return this.getData().compareTo( o.getData() );
	}
}
