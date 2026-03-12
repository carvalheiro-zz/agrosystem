package br.com.srcsoftware.sistema.aplicacao.item;

import java.time.LocalDate;

import br.com.srcsoftware.abstracts.AuditoriaAbstractDTO;
import br.com.srcsoftware.modular.manager.pessoa.funcionario.FuncionarioDTO;
import br.com.srcsoftware.modular.manager.utilidades.DateUtil;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.manutencao.imovel.ImovelDTO;
import br.com.srcsoftware.sistema.manutencao.implemento.ImplementoDTO;
import br.com.srcsoftware.sistema.manutencao.veiculo.VeiculoDTO;
import br.com.srcsoftware.sistema.pessoa.prestadorservico.PrestadorServicoDTO;
import br.com.srcsoftware.sistema.produto.produto.ProdutoDTO;
import br.com.srcsoftware.sistema.safra.SafraDTO;
import br.com.srcsoftware.sistema.safra.cultura.CulturaDTO;
import br.com.srcsoftware.sistema.safra.setor.SetorDTO;

public final class ItemDTO extends AuditoriaAbstractDTO implements Comparable< ItemDTO >{

	private static final long serialVersionUID = -1603619934429518556L;

	private String id;
	private String data;
	private String quantidade;
	private String custoMedio;
	private String custoTotal;
	private String kmHorimetro;

	private String tipo;
	private CulturaDTO cultura;
	private SafraDTO safra;
	private SetorDTO setor;

	private ProdutoDTO produto;
	private FuncionarioDTO almoxarife;
	private FuncionarioDTO requerente;

	private VeiculoDTO veiculo;
	private ImplementoDTO implemento;
	private ImovelDTO imovel;

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

	public String getDataToString() {
		return DateUtil.formatLocalDate( this.getData() );
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

	public String getData() {
		return this.data;
	}

	public void setData( String data ) {
		this.data = data;
	}

	public String getCustoMedio() {
		return this.custoMedio;
	}

	public void setCustoMedio( String custoMedio ) {
		this.custoMedio = custoMedio;
	}

	public String getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade( String quantidade ) {
		this.quantidade = quantidade;
	}

	public String getCustoTotal() {
		return this.custoTotal;
	}

	public void setCustoTotal( String custoTotal ) {
		this.custoTotal = custoTotal;
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

	public ProdutoDTO getProduto() {
		if ( this.produto == null ) {
			this.produto = new ProdutoDTO();
		}
		return this.produto;
	}

	public void setProduto( ProdutoDTO produto ) {
		this.produto = produto;
	}

	public FuncionarioDTO getAlmoxarife() {
		if ( this.almoxarife == null ) {
			this.almoxarife = new FuncionarioDTO();
		}
		return this.almoxarife;
	}

	public void setAlmoxarife( FuncionarioDTO almoxarife ) {
		this.almoxarife = almoxarife;
	}

	public FuncionarioDTO getRequerente() {
		if ( this.requerente == null ) {
			this.requerente = new FuncionarioDTO();
		}
		return this.requerente;
	}

	public void setRequerente( FuncionarioDTO requerente ) {
		this.requerente = requerente;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = ( prime * result ) + ( ( this.almoxarife == null ) ? 0 : this.almoxarife.hashCode() );
		result = ( prime * result ) + ( ( this.cultura == null ) ? 0 : this.cultura.hashCode() );
		result = ( prime * result ) + ( ( this.custoMedio == null ) ? 0 : this.custoMedio.hashCode() );
		result = ( prime * result ) + ( ( this.custoTotal == null ) ? 0 : this.custoTotal.hashCode() );
		result = ( prime * result ) + ( ( this.data == null ) ? 0 : this.data.hashCode() );
		result = ( prime * result ) + ( ( this.id == null ) ? 0 : this.id.hashCode() );
		result = ( prime * result ) + ( ( this.imovel == null ) ? 0 : this.imovel.hashCode() );
		result = ( prime * result ) + ( ( this.implemento == null ) ? 0 : this.implemento.hashCode() );
		result = ( prime * result ) + ( ( this.produto == null ) ? 0 : this.produto.hashCode() );
		result = ( prime * result ) + ( ( this.quantidade == null ) ? 0 : this.quantidade.hashCode() );
		result = ( prime * result ) + ( ( this.requerente == null ) ? 0 : this.requerente.hashCode() );
		result = ( prime * result ) + ( ( this.safra == null ) ? 0 : this.safra.hashCode() );
		result = ( prime * result ) + ( ( this.setor == null ) ? 0 : this.setor.hashCode() );
		result = ( prime * result ) + ( ( this.tipo == null ) ? 0 : this.tipo.hashCode() );
		result = ( prime * result ) + ( ( this.veiculo == null ) ? 0 : this.veiculo.hashCode() );
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
		ItemDTO other = (ItemDTO) obj;
		if ( this.almoxarife == null ) {
			if ( other.almoxarife != null ) {
				return false;
			}
		} else if ( !this.almoxarife.equals( other.almoxarife ) ) {
			return false;
		}
		if ( this.cultura == null ) {
			if ( other.cultura != null ) {
				return false;
			}
		} else if ( !this.cultura.equals( other.cultura ) ) {
			return false;
		}
		if ( this.custoMedio == null ) {
			if ( other.custoMedio != null ) {
				return false;
			}
		} else if ( !this.custoMedio.equals( other.custoMedio ) ) {
			return false;
		}
		if ( this.custoTotal == null ) {
			if ( other.custoTotal != null ) {
				return false;
			}
		} else if ( !this.custoTotal.equals( other.custoTotal ) ) {
			return false;
		}
		if ( this.data == null ) {
			if ( other.data != null ) {
				return false;
			}
		} else if ( !this.data.equals( other.data ) ) {
			return false;
		}
		if ( this.id == null ) {
			if ( other.id != null ) {
				return false;
			}
		} else if ( !this.id.equals( other.id ) ) {
			return false;
		}
		if ( this.imovel == null ) {
			if ( other.imovel != null ) {
				return false;
			}
		} else if ( !this.imovel.equals( other.imovel ) ) {
			return false;
		}
		if ( this.implemento == null ) {
			if ( other.implemento != null ) {
				return false;
			}
		} else if ( !this.implemento.equals( other.implemento ) ) {
			return false;
		}
		if ( this.produto == null ) {
			if ( other.produto != null ) {
				return false;
			}
		} else if ( !this.produto.equals( other.produto ) ) {
			return false;
		}
		if ( this.quantidade == null ) {
			if ( other.quantidade != null ) {
				return false;
			}
		} else if ( !this.quantidade.equals( other.quantidade ) ) {
			return false;
		}
		if ( this.requerente == null ) {
			if ( other.requerente != null ) {
				return false;
			}
		} else if ( !this.requerente.equals( other.requerente ) ) {
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
		if ( this.tipo == null ) {
			if ( other.tipo != null ) {
				return false;
			}
		} else if ( !this.tipo.equals( other.tipo ) ) {
			return false;
		}
		if ( this.veiculo == null ) {
			if ( other.veiculo != null ) {
				return false;
			}
		} else if ( !this.veiculo.equals( other.veiculo ) ) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "AplicacaoDTO [data=" );
		builder.append( this.data );
		builder.append( ", quantidade=" );
		builder.append( this.quantidade );
		builder.append( ", custoMedio=" );
		builder.append( this.custoMedio );
		builder.append( ", custoTotal=" );
		builder.append( this.custoTotal );
		builder.append( ", safra=" );
		builder.append( this.safra );
		builder.append( ", produto=" );
		builder.append( this.produto );
		builder.append( ", almoxarife=" );
		builder.append( this.almoxarife );
		builder.append( ", requerente=" );
		builder.append( this.requerente );
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

	@Override
	public int compareTo( ItemDTO o ) {

		LocalDate data1 = DateUtil.parseLocalDate( this.getData() );
		LocalDate data2 = DateUtil.parseLocalDate( o.getData() );

		return data1.compareTo( data2 );

	}
}
