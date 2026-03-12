package br.com.srcsoftware.sistema.pedido.notafiscal.simplesremessa;

import java.math.BigDecimal;
import java.util.ArrayList;

import br.com.srcsoftware.abstracts.AuditoriaAbstractDTO;
import br.com.srcsoftware.modular.manager.utilidades.DateUtil;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.pedido.notafiscal.venda.NotaFiscalVendaDTO;

public class NotaFiscalSimplesRemessaDTO extends AuditoriaAbstractDTO implements Comparable< NotaFiscalSimplesRemessaDTO >{

	private static final long serialVersionUID = 3699885088238140499L;

	private String id;
	private String data;
	private String numero;
	private ArrayList< ItemNotaFiscalSimplesRemessaDTO > itens = new ArrayList< ItemNotaFiscalSimplesRemessaDTO >();
	private NotaFiscalVendaDTO notaFiscalVendaFutura;

	public String getDataToString() {
		return DateUtil.formatLocalDate( getData() );
	}

	public String getValorCustoTotal() {
		BigDecimal soma = BigDecimal.ZERO;
		for ( ItemNotaFiscalSimplesRemessaDTO itemCorrente : this.getItens() ) {
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

	public String getId() {
		return id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public String getQuantidadeItens() {
		return String.valueOf( this.getItens().size() );
	}

	public String getQuantidadeTotalItens() {
		BigDecimal soma = BigDecimal.ZERO;
		for ( ItemNotaFiscalSimplesRemessaDTO itemCorrente : this.getItens() ) {
			BigDecimal quantidade = BigDecimal.ZERO;
			quantidade = Utilidades.parseBigDecimal( itemCorrente.getQuantidade() );

			soma = soma.add( quantidade );
		}
		return Utilidades.parseBigDecimal( soma );
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

	public ArrayList< ItemNotaFiscalSimplesRemessaDTO > getItens() {
		if ( this.itens == null ) {
			this.itens = new ArrayList< ItemNotaFiscalSimplesRemessaDTO >();
		}
		return this.itens;
	}

	public void setItens( ArrayList< ItemNotaFiscalSimplesRemessaDTO > itens ) {
		this.itens = itens;
	}

	public NotaFiscalVendaDTO getNotaFiscalVendaFutura() {
		if ( this.notaFiscalVendaFutura == null ) {
			this.notaFiscalVendaFutura = new NotaFiscalVendaDTO();
		}
		return this.notaFiscalVendaFutura;
	}

	public void setNotaFiscalVendaFutura( NotaFiscalVendaDTO notaFiscalVendaFutura ) {
		this.notaFiscalVendaFutura = notaFiscalVendaFutura;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( notaFiscalVendaFutura == null ) ? 0 : notaFiscalVendaFutura.hashCode() );
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
		NotaFiscalSimplesRemessaDTO other = (NotaFiscalSimplesRemessaDTO) obj;
		if ( notaFiscalVendaFutura == null ) {
			if ( other.notaFiscalVendaFutura != null )
				return false;
		} else if ( !notaFiscalVendaFutura.equals( other.notaFiscalVendaFutura ) )
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
		return "NotaFiscalSimplesRemessaDTO [data=" + this.data + ", numero=" + this.numero + ", itens=" + this.itens + ", notaFiscalVendaFutura=" + this.notaFiscalVendaFutura + ", getNomeUsuarioCriacao()=" + this.getNomeUsuarioCriacao() + ", getNomeUsuarioAlteracao()=" + this.getNomeUsuarioAlteracao() + ", getDataOcorrenciaCriacao()=" + this.getDataOcorrenciaCriacao() + ", getDataOcorrenciaAlteracao()=" + this.getDataOcorrenciaAlteracao() + ", getId()=" + this.getId() + "]";
	}

	@Override
	public int compareTo( NotaFiscalSimplesRemessaDTO o ) {
		return this.getData().compareTo( o.getData() );
	}
}
