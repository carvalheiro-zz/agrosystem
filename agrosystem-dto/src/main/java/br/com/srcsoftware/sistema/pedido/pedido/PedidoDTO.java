package br.com.srcsoftware.sistema.pedido.pedido;

import java.math.BigDecimal;
import java.util.ArrayList;

import br.com.srcsoftware.abstracts.AuditoriaAbstractDTO;
import br.com.srcsoftware.modular.manager.utilidades.DateUtil;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.pedido.itempedido.ItemPedidoDTO;
import br.com.srcsoftware.sistema.pessoa.fornecedor.juridico.FornecedorJuridicoDTO;

public class PedidoDTO extends AuditoriaAbstractDTO implements Comparable< PedidoDTO >{

	private static final long serialVersionUID = 7621468743118748846L;

	public static final String STATUS_FINALIZADO = "FINALIZADO";
	public static final String STATUS_ABERTO = "ABERTO";
	public static final String STATUS_ANDAMENTO = "ANDAMENTO";

	private String id;
	private String data;
	private String numero;
	private ArrayList< ItemPedidoDTO > itens;
	private FornecedorJuridicoDTO fornecedor;
	private String status;
	private String observacao;

	/** Usado para exibir na tabela */
	private String quantidadeRestante;
	private boolean permiteEmitirNota;

	public boolean isPermiteEmitirNota() {
		return this.permiteEmitirNota;
	}

	public void setPermiteEmitirNota( boolean permiteEmitirNota ) {
		this.permiteEmitirNota = permiteEmitirNota;
	}

	public String getId() {
		return id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public String getDataToString() {
		return DateUtil.formatLocalDate( getData() );
	}

	public String getQuantidadeItens() {
		return String.valueOf( this.getItens().size() );
	}

	public String getQuantidadeTotalItens() {
		BigDecimal soma = BigDecimal.ZERO;
		for ( ItemPedidoDTO itemCorrente : this.getItens() ) {
			BigDecimal quantidade = BigDecimal.ZERO;
			quantidade = Utilidades.parseBigDecimal( itemCorrente.getQuantidade() );

			soma = soma.add( quantidade );
		}
		return Utilidades.parseBigDecimal( soma );
	}

	public String getValorCustoTotal() {
		BigDecimal soma = BigDecimal.ZERO;
		for ( ItemPedidoDTO itemCorrente : this.getItens() ) {
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

	/*public String getValorTotal() {
		BigDecimal soma = BigDecimal.ZERO;
		for ( ItemPedidoDTO itemCorrente : this.getItens() ) {
			BigDecimal precoCusto = BigDecimal.ZERO;
			precoCusto = Utilidades.parseBigDecimal( itemCorrente.getPrecoCusto() );
	
			BigDecimal quantidadePedido = BigDecimal.ZERO;
			quantidadePedido = Utilidades.parseBigDecimal( itemCorrente.getQuantidade() );
	
			soma = soma.add( precoCusto.multiply( quantidadePedido ) );
		}
		return Utilidades.parseBigDecimal( soma );
	}*/

	public String getObservacao() {
		return this.observacao;
	}

	public String getQuantidadeRestante() {
		return this.quantidadeRestante;
	}

	public void setQuantidadeRestante( String quantidadeRestante ) {
		this.quantidadeRestante = quantidadeRestante;
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

	public ArrayList< ItemPedidoDTO > getItens() {
		if ( this.itens == null ) {
			this.itens = new ArrayList<>();
		}
		return this.itens;
	}

	public void setItens( ArrayList< ItemPedidoDTO > itens ) {
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
		PedidoDTO other = (PedidoDTO) obj;
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
		return "PedidoDTO [data=" + this.data + ", numero=" + this.numero + ", itens=" + this.itens + ", fornecedor=" + this.fornecedor + ", getNomeUsuarioCriacao()=" + this.getNomeUsuarioCriacao() + ", getNomeUsuarioAlteracao()=" + this.getNomeUsuarioAlteracao() + ", getDataOcorrenciaCriacao()=" + this.getDataOcorrenciaCriacao() + ", getDataOcorrenciaAlteracao()=" + this.getDataOcorrenciaAlteracao() + ", getId()=" + this.getId() + "]";
	}

	@Override
	public int compareTo( PedidoDTO o ) {
		return this.getData().compareTo( o.getData() );
	}
}
