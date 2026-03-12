package br.com.srcsoftware.sistema.notafiscal.direta;

import java.math.BigDecimal;
import java.util.ArrayList;

import br.com.srcsoftware.abstracts.AuditoriaAbstractDTO;
import br.com.srcsoftware.modular.manager.utilidades.DateUtil;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.notafiscal.direta.item.ItemNotaFiscalVendaDiretaDTO;
import br.com.srcsoftware.sistema.pessoa.fornecedor.juridico.FornecedorJuridicoDTO;

public class NotaFiscalVendaDiretaDTO extends AuditoriaAbstractDTO implements Comparable< NotaFiscalVendaDiretaDTO >{

	private static final long serialVersionUID = 7621468743118748846L;

	public static final String STATUS_FINALIZADO = "FINALIZADO";
	public static final String STATUS_ABERTO = "ABERTO";

	private String id;
	private String data;
	private String numero;
	private String numeroRecibo;
	private ArrayList< ItemNotaFiscalVendaDiretaDTO > itens;
	private FornecedorJuridicoDTO fornecedor;
	private String status;
	private String observacao;

	public String getDataToString() {
		return DateUtil.formatLocalDate( getData() );
	}

	public String getValorCustoTotal() {
		BigDecimal soma = BigDecimal.ZERO;
		for ( ItemNotaFiscalVendaDiretaDTO itemCorrente : this.getItens() ) {
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
		for ( ItemNotaFiscalVendaDiretaDTO itemCorrente : this.getItens() ) {
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

	public String getObservacao() {
		return this.observacao;
	}

	public String getNumeroRecibo() {
		return this.numeroRecibo;
	}

	public void setNumeroRecibo( String numeroRecibo ) {
		this.numeroRecibo = numeroRecibo;
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

	public ArrayList< ItemNotaFiscalVendaDiretaDTO > getItens() {
		if ( this.itens == null ) {
			this.itens = new ArrayList<>();
		}
		return this.itens;
	}

	public void setItens( ArrayList< ItemNotaFiscalVendaDiretaDTO > itens ) {
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
		NotaFiscalVendaDiretaDTO other = (NotaFiscalVendaDiretaDTO) obj;
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
		StringBuilder builder = new StringBuilder();
		builder.append( "NotaFiscalVendaDiretaDTO [\n\tdata=" );
		builder.append( data );
		builder.append( ",\n\tnumero=" );
		builder.append( numero );
		builder.append( ",\n\tnumeroRecibo=" );
		builder.append( numeroRecibo );
		builder.append( ",\n\titens=" );
		builder.append( itens );
		builder.append( ",\n\tfornecedor=" );
		builder.append( fornecedor );
		builder.append( ",\n\tstatus=" );
		builder.append( status );
		builder.append( ",\n\tobservacao=" );
		builder.append( observacao );
		builder.append( ",\n\tgetNomeUsuarioCriacao()=" );
		builder.append( getNomeUsuarioCriacao() );
		builder.append( ",\n\tgetNomeUsuarioAlteracao()=" );
		builder.append( getNomeUsuarioAlteracao() );
		builder.append( ",\n\tgetDataOcorrenciaCriacao()=" );
		builder.append( getDataOcorrenciaCriacao() );
		builder.append( ",\n\tgetDataOcorrenciaAlteracao()=" );
		builder.append( getDataOcorrenciaAlteracao() );
		builder.append( ",\n\tgetId()=" );
		builder.append( getId() );
		builder.append( "]\n" );
		return builder.toString();
	}

	@Override
	public int compareTo( NotaFiscalVendaDiretaDTO o ) {
		return this.getData().compareTo( o.getData() );
	}
}
