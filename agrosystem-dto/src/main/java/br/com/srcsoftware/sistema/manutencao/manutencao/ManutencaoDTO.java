package br.com.srcsoftware.sistema.manutencao.manutencao;

import br.com.srcsoftware.abstracts.AuditoriaAbstractDTO;
import br.com.srcsoftware.modular.manager.utilidades.DateUtil;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.manutencao.imovel.ImovelDTO;
import br.com.srcsoftware.sistema.manutencao.implemento.ImplementoDTO;
import br.com.srcsoftware.sistema.manutencao.servico.ServicoDTO;
import br.com.srcsoftware.sistema.manutencao.veiculo.VeiculoDTO;
import br.com.srcsoftware.sistema.pessoa.fornecedor.juridico.FornecedorJuridicoDTO;
import br.com.srcsoftware.sistema.pessoa.prestadorservico.PrestadorServicoDTO;
import br.com.srcsoftware.sistema.safra.SafraDTO;
import br.com.srcsoftware.sistema.safra.cultura.CulturaDTO;
import br.com.srcsoftware.sistema.safra.setor.SetorDTO;

public class ManutencaoDTO extends AuditoriaAbstractDTO{

	/*public static final String FINALIDADE_MANUTENCAO = "Manutenção";
	public static final String FINALIDADE_CONCERTO = "Concerto";
	
	public static final String TIPO_SAFRASETOR = "Safra/Setor";
	public static final String TIPO_TUDO = "Tudo";
	public static final String TIPO_CULTURA = "Cultura";
	
	public static final String RECIBO = "Recibo";
	public static final String NOTA_FISCAL = "Nota Fiscal";*/

	private String id;
	private String data;
	private String notaFiscal;
	private String finalidade;
	private String custo;
	private VeiculoDTO veiculo;
	private ImplementoDTO implemento;
	private ImovelDTO imovel;
	private FornecedorJuridicoDTO fornecedor;
	private ServicoDTO servico;
	private String kmHorimetro;

	private String tipo;
	private CulturaDTO cultura;
	private SafraDTO safra;
	private SetorDTO setor;

	private String reciboOuNotaFiscal;

	private PrestadorServicoDTO prestador;

	private String observacao;

	public String getKmHorimetro() {
		return kmHorimetro;
	}

	public void setKmHorimetro( String kmHorimetro ) {
		this.kmHorimetro = kmHorimetro;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao( String observacao ) {
		this.observacao = observacao;
	}

	public PrestadorServicoDTO getPrestador() {
		if ( this.prestador == null ) {
			this.prestador = new PrestadorServicoDTO();
		}
		return this.prestador;
	}

	public void setPrestador( PrestadorServicoDTO prestador ) {
		this.prestador = prestador;
	}

	public String getNomeCompleto() {
		String cultura = this.getCultura().getNome();
		String setor = this.getSetor().getNomeCompleto();
		String safra = this.getSafra().getNomeCompleto();

		StringBuilder nomeCompleto = new StringBuilder();

		if ( !Utilidades.isNuloOuVazio( safra ) ) {
			nomeCompleto.append( safra );
		}
		if ( !Utilidades.isNuloOuVazio( setor ) ) {
			nomeCompleto.append( " - " );
			nomeCompleto.append( setor );
		}
		if ( !Utilidades.isNuloOuVazio( cultura ) ) {
			nomeCompleto.append( " - " );
			nomeCompleto.append( cultura );
		}

		return nomeCompleto.toString();
	}

	public String getId() {
		return this.id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public String getReciboOuNotaFiscal() {
		return this.reciboOuNotaFiscal;
	}

	public void setReciboOuNotaFiscal( String reciboOuNotaFiscal ) {
		this.reciboOuNotaFiscal = reciboOuNotaFiscal;
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

	public CulturaDTO getCultura() {
		if ( this.cultura == null ) {
			this.cultura = new CulturaDTO();
		}
		return this.cultura;
	}

	public void setCultura( CulturaDTO cultura ) {
		this.cultura = cultura;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo( String tipo ) {
		this.tipo = tipo;
	}

	public String getNotaFiscal() {
		return this.notaFiscal;
	}

	public void setNotaFiscal( String notaFiscal ) {
		this.notaFiscal = notaFiscal;
	}

	public String getFinalidade() {
		return this.finalidade;
	}

	public void setFinalidade( String finalidade ) {
		this.finalidade = finalidade;
	}

	public String getCusto() {
		return this.custo;
	}

	public void setCusto( String custo ) {
		this.custo = custo;
	}

	public VeiculoDTO getVeiculo() {
		if ( this.veiculo == null ) {
			this.veiculo = new VeiculoDTO();
		}
		return this.veiculo;
	}

	public void setVeiculo( VeiculoDTO veiculo ) {
		this.veiculo = veiculo;
	}

	public ImplementoDTO getImplemento() {
		if ( this.implemento == null ) {
			this.implemento = new ImplementoDTO();
		}
		return this.implemento;
	}

	public void setImplemento( ImplementoDTO implemento ) {
		this.implemento = implemento;
	}

	public ImovelDTO getImovel() {
		if ( this.imovel == null ) {
			this.imovel = new ImovelDTO();
		}
		return this.imovel;
	}

	public void setImovel( ImovelDTO imovel ) {
		this.imovel = imovel;
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

	public ServicoDTO getServico() {
		if ( this.servico == null ) {
			this.servico = new ServicoDTO();
		}
		return this.servico;
	}

	public void setServico( ServicoDTO servico ) {
		this.servico = servico;
	}

	public SafraDTO getSafra() {
		if ( this.safra == null ) {
			this.safra = new SafraDTO();
		}
		return this.safra;
	}

	public void setSafra( SafraDTO safra ) {
		this.safra = safra;
	}

	public SetorDTO getSetor() {
		if ( this.setor == null ) {
			this.setor = new SetorDTO();
		}
		return this.setor;
	}

	public void setSetor( SetorDTO setor ) {
		this.setor = setor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = ( prime * result ) + ( ( this.cultura == null ) ? 0 : this.cultura.hashCode() );
		result = ( prime * result ) + ( ( this.data == null ) ? 0 : this.data.hashCode() );
		result = ( prime * result ) + ( ( this.notaFiscal == null ) ? 0 : this.notaFiscal.hashCode() );
		result = ( prime * result ) + ( ( this.safra == null ) ? 0 : this.safra.hashCode() );
		result = ( prime * result ) + ( ( this.setor == null ) ? 0 : this.setor.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}
		if ( obj == null ) {
			return false;
		}
		if ( this.getClass() != obj.getClass() ) {
			return false;
		}
		ManutencaoDTO other = (ManutencaoDTO) obj;
		if ( this.cultura == null ) {
			if ( other.cultura != null ) {
				return false;
			}
		} else if ( !this.cultura.equals( other.cultura ) ) {
			return false;
		}
		if ( this.data == null ) {
			if ( other.data != null ) {
				return false;
			}
		} else if ( !this.data.equals( other.data ) ) {
			return false;
		}
		if ( this.notaFiscal == null ) {
			if ( other.notaFiscal != null ) {
				return false;
			}
		} else if ( !this.notaFiscal.equals( other.notaFiscal ) ) {
			return false;
		}
		if ( this.safra == null ) {
			if ( other.safra != null ) {
				return false;
			}
		} else if ( !this.safra.equals( other.safra ) ) {
			return false;
		}
		if ( this.setor == null ) {
			if ( other.setor != null ) {
				return false;
			}
		} else if ( !this.setor.equals( other.setor ) ) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "ManutencaoDTO [data=" );
		builder.append( this.data );
		builder.append( ", notaFiscal=" );
		builder.append( this.notaFiscal );
		builder.append( ", finalidade=" );
		builder.append( this.finalidade );
		builder.append( ", custo=" );
		builder.append( this.custo );
		builder.append( ", veiculo=" );
		builder.append( this.veiculo );
		builder.append( ", implemento=" );
		builder.append( this.implemento );
		builder.append( ", imovel=" );
		builder.append( this.imovel );
		builder.append( ", fornecedor=" );
		builder.append( this.fornecedor );
		builder.append( ", servico=" );
		builder.append( this.servico );
		builder.append( ", safra=" );
		builder.append( this.safra );
		builder.append( ", setor=" );
		builder.append( this.setor );
		builder.append( ", getNomeUsuarioCriacao()=" );
		builder.append( this.getNomeUsuarioCriacao() );
		builder.append( ", getNomeUsuarioAlteracao()=" );
		builder.append( this.getNomeUsuarioAlteracao() );
		builder.append( ", getDataOcorrenciaCriacao()=" );
		builder.append( this.getDataOcorrenciaCriacao() );
		builder.append( ", getDataOcorrenciaAlteracao()=" );
		builder.append( this.getDataOcorrenciaAlteracao() );
		builder.append( ", getId()=" );
		builder.append( this.getId() );
		builder.append( "]" );
		return builder.toString();
	}

}
