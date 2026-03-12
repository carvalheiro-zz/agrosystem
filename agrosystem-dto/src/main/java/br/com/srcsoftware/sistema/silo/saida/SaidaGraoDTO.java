package br.com.srcsoftware.sistema.silo.saida;

import java.math.BigDecimal;

import br.com.srcsoftware.abstracts.AuditoriaAbstractDTO;
import br.com.srcsoftware.modular.manager.pessoa.cliente.ClienteDTO;
import br.com.srcsoftware.modular.manager.pessoa.funcionario.FuncionarioDTO;
import br.com.srcsoftware.modular.manager.utilidades.DateUtil;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.safra.SafraDTO;
import br.com.srcsoftware.sistema.safra.cultura.CulturaDTO;
import br.com.srcsoftware.sistema.silo.contratovenda.ContratoVendaDTO;
import br.com.srcsoftware.sistema.silo.naturezaoperacao.NaturezaOperacaoDTO;
import br.com.srcsoftware.sistema.silo.silo.SiloDTO;

public final class SaidaGraoDTO extends AuditoriaAbstractDTO implements Comparable< SaidaGraoDTO >{

	private String id;
	private String data;
	private String pesoLiquido;
	private String valorBruto;
	private String valorLiquido;
	private String percentualDesconto;
	private String observacaoDesconto;
	private CulturaDTO cultura;
	private ClienteDTO cliente = new ClienteDTO();
	private String ticket;
	private String numeroNotaFiscal;
	private String placa;
	private String motorista;
	private NaturezaOperacaoDTO naturezaOperacao;
	private FuncionarioDTO estoquista = new FuncionarioDTO();
	private ContratoVendaDTO contratoVenda = new ContratoVendaDTO();

	private String pesoLiquido01;
	private SiloDTO localArmazenagem01;

	private String pesoLiquido02;
	private SiloDTO localArmazenagem02;

	private SafraDTO safra01 = new SafraDTO();
	private String pesoLiquidoSafra01;

	private SafraDTO safra02 = new SafraDTO();
	private String pesoLiquidoSafra02;

	public String getNomeCompleto() {
		String cultura = this.getCultura().getNome();
		String safra = this.getSafra01().getNomeCompleto();

		StringBuilder nomeCompleto = new StringBuilder();

		if ( !Utilidades.isNuloOuVazio( safra ) ) {
			nomeCompleto.append( safra );
		}
		if ( !Utilidades.isNuloOuVazio( cultura ) ) {
			nomeCompleto.append( " - " );
			nomeCompleto.append( cultura );
		}

		return nomeCompleto.toString();
	}

	public String getPesoLiquido01() {
		return pesoLiquido01;
	}

	public void setPesoLiquido01( String pesoLiquido01 ) {
		this.pesoLiquido01 = pesoLiquido01;
	}

	public SafraDTO getSafra02() {
		if ( this.safra02 == null ) {
			this.safra02 = new SafraDTO();
		}
		return safra02;
	}

	public void setSafra02( SafraDTO safra02 ) {
		this.safra02 = safra02;
	}

	public String getPesoLiquidoSafra02() {
		return pesoLiquidoSafra02;
	}

	public void setPesoLiquidoSafra02( String pesoLiquidoSafra02 ) {
		this.pesoLiquidoSafra02 = pesoLiquidoSafra02;
	}

	public String getPesoLiquidoSafra01() {
		return pesoLiquidoSafra01;
	}

	public void setPesoLiquidoSafra01( String pesoLiquidoSafra01 ) {
		this.pesoLiquidoSafra01 = pesoLiquidoSafra01;
	}

	public String getPesoLiquido02() {
		return pesoLiquido02;
	}

	public void setPesoLiquido02( String pesoLiquido02 ) {
		this.pesoLiquido02 = pesoLiquido02;
	}

	public String getId() {
		return this.id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public ContratoVendaDTO getContratoVenda() {
		if ( this.contratoVenda == null ) {
			this.contratoVenda = new ContratoVendaDTO();
		}
		return contratoVenda;
	}

	public void setContratoVenda( ContratoVendaDTO contratoVenda ) {
		this.contratoVenda = contratoVenda;
	}

	public FuncionarioDTO getEstoquista() {
		if ( this.estoquista == null ) {
			this.estoquista = new FuncionarioDTO();
		}
		return this.estoquista;
	}

	public void setEstoquista( FuncionarioDTO estoquista ) {
		this.estoquista = estoquista;
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

	public NaturezaOperacaoDTO getNaturezaOperacao() {
		if ( this.naturezaOperacao == null ) {
			this.naturezaOperacao = new NaturezaOperacaoDTO();
		}
		return naturezaOperacao;
	}

	public void setNaturezaOperacao( NaturezaOperacaoDTO naturezaOperacao ) {
		this.naturezaOperacao = naturezaOperacao;
	}

	public String getDataToString() {
		return DateUtil.formatLocalDate( this.getData() );
	}

	public String getData() {
		return this.data;
	}

	public void setData( String data ) {
		this.data = data;
	}

	public String getEmSacas() {
		if ( Utilidades.isNuloOuVazio( this.getPesoLiquido() ) ) {
			return null;
		}
		return Utilidades.parseBigDecimal( Utilidades.parseBigDecimal( this.getPesoLiquido() ).divide( new BigDecimal( "60" ), 2, BigDecimal.ROUND_FLOOR ) );
	}

	public SiloDTO getLocalArmazenagem01() {
		if ( this.localArmazenagem01 == null ) {
			this.localArmazenagem01 = new SiloDTO();
		}
		return this.localArmazenagem01;
	}

	public void setLocalArmazenagem01( SiloDTO localArmazenagem01 ) {
		this.localArmazenagem01 = localArmazenagem01;
	}

	public SiloDTO getLocalArmazenagem02() {
		if ( this.localArmazenagem02 == null ) {
			this.localArmazenagem02 = new SiloDTO();
		}
		return this.localArmazenagem02;
	}

	public void setLocalArmazenagem02( SiloDTO localArmazenagem02 ) {
		this.localArmazenagem02 = localArmazenagem02;
	}

	public String getPesoLiquido() {
		return this.pesoLiquido;
	}

	public void setPesoLiquido( String pesoLiquido ) {
		this.pesoLiquido = pesoLiquido;
	}

	public String getValorBruto() {
		return this.valorBruto;
	}

	public void setValorBruto( String valorBruto ) {
		this.valorBruto = valorBruto;
	}

	public String getValorBrutoCalculado() {
		if ( Utilidades.isNuloOuVazio( this.getPesoLiquido() ) ) {
			return null;
		}
		if ( Utilidades.isNuloOuVazio( this.getContratoVenda().getValorUnitario() ) ) {
			return null;
		}
		return Utilidades.parseBigDecimal( Utilidades.parseBigDecimal( this.getPesoLiquido() ).multiply( Utilidades.parseBigDecimal( this.getContratoVenda().getValorUnitario() ) ) );
	}

	public String getValorLiquido() {
		return this.valorLiquido;
	}

	public void setValorLiquido( String valorLiquido ) {
		this.valorLiquido = valorLiquido;
	}

	public String getPercentualDesconto() {
		return this.percentualDesconto;
	}

	public void setPercentualDesconto( String percentualDesconto ) {
		this.percentualDesconto = percentualDesconto;
	}

	public String getObservacaoDesconto() {
		return this.observacaoDesconto;
	}

	public void setObservacaoDesconto( String observacaoDesconto ) {
		this.observacaoDesconto = observacaoDesconto;
	}

	public CulturaDTO getCultura() {
		if ( this.cultura == null ) {
			this.cultura = new CulturaDTO();
		}
		return this.cultura;
	}

	public void setCultura( CulturaDTO cultura ) {
		this.cultura = cultura;
	}

	public SafraDTO getSafra01() {
		if ( this.safra01 == null ) {
			this.safra01 = new SafraDTO();
		}
		return this.safra01;
	}

	public void setSafra01( SafraDTO safra01 ) {
		this.safra01 = safra01;
	}

	public ClienteDTO getCliente() {
		if ( this.cliente == null ) {
			this.cliente = new ClienteDTO();
		}
		return this.cliente;
	}

	public void setCliente( ClienteDTO cliente ) {
		this.cliente = cliente;
	}

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

	/**
	 * Polimorfico
	 * 
	 * @see java.lang.Object#hashCode()
	 */
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
		if ( !( obj instanceof SaidaGraoDTO ) ) {
			return false;
		}
		SaidaGraoDTO other = (SaidaGraoDTO) obj;
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
		builder.append( "SaidaGraoDTO [id=" );
		builder.append( id );
		builder.append( ", data=" );
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
		builder.append( ", estoquista=" );
		builder.append( estoquista );
		builder.append( ", contratoVenda=" );
		builder.append( contratoVenda );
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
	public int compareTo( SaidaGraoDTO o ) {
		return this.getData().compareTo( o.getData() );
	}
}
