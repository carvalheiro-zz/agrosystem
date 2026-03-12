package br.com.srcsoftware.sistema.notafiscal.rateio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.srcsoftware.abstracts.AbstractDTO;
import br.com.srcsoftware.modular.financeiro.contapagar.ContaPagarDTO;
import br.com.srcsoftware.modular.financeiro.contareceber.ContaReceberDTO;
import br.com.srcsoftware.modular.financeiro.formapagamento.FormaPagamentoDTO;
import br.com.srcsoftware.modular.manager.pessoa.cliente.ClienteDTO;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.notafiscal.rateio.itemnotafiscalrateio.ItemNotaFiscalRateioDTO;
import br.com.srcsoftware.sistema.pessoa.fornecedor.juridico.FornecedorJuridicoDTO;

public class NotaFiscalRateioDTO extends AbstractDTO implements Comparable< NotaFiscalRateioDTO >{

	private String id;
	private String tipo;// Despesa/Investimento - 
	private String numero;
	private String numeroRecibo;
	private ContaReceberDTO contaReceber;
	private ContaPagarDTO contaPagar;
	private ArrayList< ItemNotaFiscalRateioDTO > itens;
	private FornecedorJuridicoDTO fornecedor;
	private ClienteDTO cliente;

	public String getId() {
		return id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public NotaFiscalRateioDTO(){
		this.setItens( new ArrayList< ItemNotaFiscalRateioDTO >() );
		this.setContaPagar( new ContaPagarDTO() );
	}

	public String getCentrosCustosToString() {
		StringBuilder centrosCustos = new StringBuilder();

		int i = 1;
		for ( ItemNotaFiscalRateioDTO itemCorrente : getItens() ) {
			centrosCustos.append( itemCorrente.getCentroCusto().getCodigo() );

			if ( i < getItens().size() ) {
				centrosCustos.append( " / " );
			}
			i++;
		}

		return centrosCustos.toString();
	}

	public String getDescricaoOrigemTitulos() {
		StringBuilder descricao = new StringBuilder();

		if ( Utilidades.isNuloOuVazio( getFornecedor().getId() ) ) {
			descricao.append( getCliente().getNome() );
			descricao.append( " - " );
			descricao.append( "NF Rateio Receita" );
		}

		if ( Utilidades.isNuloOuVazio( getCliente().getId() ) ) {
			descricao.append( getFornecedor().getNome() );
			descricao.append( " - " );
			descricao.append( "NF Rateio Despesa/Investimento" );
		}

		descricao.append( " Itens" );
		descricao.append( " (" );
		descricao.append( getItens().size() );
		descricao.append( ")" );
		descricao.append( " " );
		descricao.append( " (" );
		descricao.append( getItens().get( 0 ).getCentroCusto().getCodigo() );
		descricao.append( "-" );
		descricao.append( getItens().get( 0 ).getDescricao() );
		if ( getItens().size() > 1 ) {
			descricao.append( ", ... " );
		}
		descricao.append( ")" );

		return descricao.toString();
	}

	public String getValorTotal() {
		BigDecimal soma = BigDecimal.ZERO;
		for ( ItemNotaFiscalRateioDTO itemCorrente : this.getItens() ) {

			BigDecimal valorItem = Utilidades.parseBigDecimal( itemCorrente.getValor() );

			if ( Utilidades.isNuloOuVazio( valorItem ) ) {
				continue;
			}

			soma = soma.add( valorItem );
		}
		return Utilidades.parseBigDecimal( soma );
	}

	public FornecedorJuridicoDTO getFornecedor() {
		if ( this.fornecedor == null ) {
			this.fornecedor = new FornecedorJuridicoDTO();
		}
		return fornecedor;
	}

	public void setFornecedor( FornecedorJuridicoDTO fornecedor ) {
		this.fornecedor = fornecedor;
	}

	public ClienteDTO getCliente() {
		if ( this.cliente == null ) {
			this.cliente = new ClienteDTO();
		}
		return cliente;
	}

	public void setCliente( ClienteDTO cliente ) {
		this.cliente = cliente;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo( String tipo ) {
		this.tipo = tipo;
	}

	public String getQuantidadeItens() {
		return String.valueOf( getItens().size() );
	}

	public ContaPagarDTO getContaPagar() {
		if ( contaPagar == null ) {
			contaPagar = new ContaPagarDTO();
		}
		return contaPagar;
	}

	public void setContaPagar( ContaPagarDTO contaPagar ) {
		this.contaPagar = contaPagar;
	}

	public ContaReceberDTO getContaReceber() {
		if ( contaReceber == null ) {
			contaReceber = new ContaReceberDTO();
		}
		return contaReceber;
	}

	public void setContaReceber( ContaReceberDTO contaReceber ) {
		this.contaReceber = contaReceber;
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

	public ArrayList< ItemNotaFiscalRateioDTO > getItens() {
		return this.itens;
	}

	public void setItens( ArrayList< ItemNotaFiscalRateioDTO > itens ) {
		this.itens = itens;
	}

	/** ########## ADAPTADORES FINANCEIRO ########### */
	public String getPercentualJuros() {
		if ( "".equals( getTipo() ) ) {
			return getContaReceber().getPercentualJuros();
		}
		return null;
	}

	public void setPercentualJuros( String percentualJuros ) {
		if ( "".equals( getTipo() ) ) {
			this.getContaReceber().setPercentualJuros( percentualJuros );
		}
	}

	public String getValorDesconto() {
		if ( "".equals( getTipo() ) ) {
			return getContaReceber().getValorDesconto();
		}
		return null;
	}

	public void setValorDesconto( String valorDesconto ) {
		if ( "".equals( getTipo() ) ) {
			this.getContaReceber().setValorDesconto( valorDesconto );
		}
	}

	public String getVencimentoPrimeiraParcelaToString() {
		if ( "Investimento/Despesa".equals( getTipo() ) ) {
			return getContaPagar().getVencimentoPrimeiraParcelaToString();
		} else if ( "".equals( getTipo() ) ) {
			return getContaReceber().getVencimentoPrimeiraParcelaToString();
		}
		return null;
	}

	public String getVencimentoPrimeiraParcela() {
		if ( "Investimento/Despesa".equals( getTipo() ) ) {
			return getContaPagar().getVencimentoPrimeiraParcela();
		} else if ( "".equals( getTipo() ) ) {
			return getContaReceber().getVencimentoPrimeiraParcela();
		}
		return null;
	}

	public void setVencimentoPrimeiraParcela( String vencimentoPrimeiraParcela ) {
		if ( "Investimento/Despesa".equals( getTipo() ) ) {
			this.getContaPagar().setVencimentoPrimeiraParcela( vencimentoPrimeiraParcela );
		} else if ( "".equals( getTipo() ) ) {
			this.getContaReceber().setVencimentoPrimeiraParcela( vencimentoPrimeiraParcela );
		}
	}

	public String getTipoFinanceiro() {
		if ( "Investimento/Despesa".equals( getTipo() ) ) {
			return getContaPagar().getTipo();
		} else if ( "".equals( getTipo() ) ) {
			return getContaReceber().getTipo();
		}
		return null;
	}

	public void setTipoFinanceiro( String tipo ) {
		if ( "Investimento/Despesa".equals( getTipo() ) ) {
			this.getContaPagar().setTipo( tipo );
		} else if ( "".equals( getTipo() ) ) {
			this.getContaReceber().setTipo( tipo );
		}
	}

	public String getSituacao() {
		if ( "Investimento/Despesa".equals( getTipo() ) ) {
			return getContaPagar().getSituacao();
		} else if ( "".equals( getTipo() ) ) {
			return getContaReceber().getSituacao();
		}
		return null;
	}

	public void setSituacao( String situacao ) {
		if ( "Investimento/Despesa".equals( getTipo() ) ) {
			this.getContaPagar().setSituacao( situacao );
		} else if ( "".equals( getTipo() ) ) {
			this.getContaReceber().setSituacao( situacao );
		}
	}

	public String getDataToString() {
		if ( "Investimento/Despesa".equals( getTipo() ) ) {
			return getContaPagar().getDataToString();
		} else if ( "".equals( getTipo() ) ) {
			return getContaReceber().getDataToString();
		}
		return null;

	}

	public String getData() {
		if ( "Investimento/Despesa".equals( getTipo() ) ) {
			return getContaPagar().getData();
		} else if ( "".equals( getTipo() ) ) {
			return getContaReceber().getData();
		}
		return null;
	}

	public void setData( String data ) {
		if ( "Investimento/Despesa".equals( getTipo() ) ) {
			this.getContaPagar().setData( data );
		} else if ( "".equals( getTipo() ) ) {
			this.getContaReceber().setData( data );
		}
	}

	public String getValor() {
		if ( "Investimento/Despesa".equals( getTipo() ) ) {
			return getContaPagar().getValor();
		} else if ( "".equals( getTipo() ) ) {
			return getContaReceber().getValor();
		}
		return null;
	}

	public void setValor( String valor ) {
		if ( "Investimento/Despesa".equals( getTipo() ) ) {
			this.getContaPagar().setValor( valor );
		} else if ( "".equals( getTipo() ) ) {
			this.getContaReceber().setValor( valor );
		}
	}

	public String getValorEntrada() {
		if ( "Investimento/Despesa".equals( getTipo() ) ) {
			return getContaPagar().getValorEntrada();
		} else if ( "".equals( getTipo() ) ) {
			return getContaReceber().getValorEntrada();
		}
		return null;
	}

	public void setValorEntrada( String valorEntrada ) {
		if ( "Investimento/Despesa".equals( getTipo() ) ) {
			this.getContaPagar().setValorEntrada( valorEntrada );
		} else if ( "".equals( getTipo() ) ) {
			this.getContaReceber().setValorEntrada( valorEntrada );
		}
	}

	public String getQuantidadeParcelas() {
		if ( "Investimento/Despesa".equals( getTipo() ) ) {
			return getContaPagar().getQuantidadeParcelas();
		} else if ( "".equals( getTipo() ) ) {
			return getContaReceber().getQuantidadeParcelas();
		}
		return null;
	}

	public void setQuantidadeParcelas( String quantidadeParcelas ) {
		if ( "Investimento/Despesa".equals( getTipo() ) ) {
			this.getContaPagar().setQuantidadeParcelas( quantidadeParcelas );
		} else if ( "".equals( getTipo() ) ) {
			this.getContaReceber().setQuantidadeParcelas( quantidadeParcelas );
		}
	}

	public FormaPagamentoDTO getFormaPagamento() {
		if ( "Investimento/Despesa".equals( getTipo() ) ) {
			return getContaPagar().getFormaPagamento();
		} else if ( "".equals( getTipo() ) ) {
			return getContaReceber().getFormaPagamento();
		}
		return null;
	}

	public void setFormaPagamento( FormaPagamentoDTO formaPagamento ) {
		if ( "Investimento/Despesa".equals( getTipo() ) ) {
			this.getContaPagar().setFormaPagamento( formaPagamento );
		} else if ( "".equals( getTipo() ) ) {
			this.getContaReceber().setFormaPagamento( formaPagamento );
		}
	}

	public FormaPagamentoDTO getFormaPagamentoEntrada() {
		if ( "Investimento/Despesa".equals( getTipo() ) ) {
			return getContaPagar().getFormaPagamentoEntrada();
		} else if ( "".equals( getTipo() ) ) {
			return getContaReceber().getFormaPagamentoEntrada();
		}
		return null;

	}

	public void setFormaPagamentoEntrada( FormaPagamentoDTO formaPagamentoEntrada ) {
		if ( "Investimento/Despesa".equals( getTipo() ) ) {
			this.getContaPagar().setFormaPagamentoEntrada( formaPagamentoEntrada );
		} else if ( "".equals( getTipo() ) ) {
			this.getContaReceber().setFormaPagamentoEntrada( formaPagamentoEntrada );
		}
	}

	public List getParcelas() {
		if ( "Investimento/Despesa".equals( getTipo() ) ) {
			return getContaPagar().getParcelas();
		} else if ( "".equals( getTipo() ) ) {
			return getContaReceber().getParcelas();
		}
		return null;

	}

	public void setParcelas( ArrayList parcelas ) {
		if ( "Investimento/Despesa".equals( getTipo() ) ) {
			this.getContaPagar().setParcelas( parcelas );
		} else if ( "".equals( getTipo() ) ) {
			this.getContaReceber().setParcelas( parcelas );
		}
	}

	/** ################################################## */

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
		NotaFiscalRateioDTO other = (NotaFiscalRateioDTO) obj;
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
		builder.append( "NotaFiscalRateioDTO [\n\tnumero=" );
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
	public int compareTo( NotaFiscalRateioDTO o ) {
		if ( this.getContaPagar() != null ) {
			return this.getContaPagar().getData().compareTo( o.getContaPagar().getData() );
		}
		if ( this.getContaReceber() != null ) {
			return this.getContaReceber().getData().compareTo( o.getContaReceber().getData() );
		}
		return getId().compareTo( o.getId() );
	}
}
