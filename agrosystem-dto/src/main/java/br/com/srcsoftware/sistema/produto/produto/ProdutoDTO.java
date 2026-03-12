package br.com.srcsoftware.sistema.produto.produto;

import br.com.srcsoftware.abstracts.AuditoriaAbstractDTO;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.produto.marca.MarcaDTO;
import br.com.srcsoftware.sistema.produto.tipo.TipoDTO;
import br.com.srcsoftware.sistema.produto.unidademedida.UnidadeMedidaDTO;

public final class ProdutoDTO extends AuditoriaAbstractDTO implements Comparable< ProdutoDTO >{

	private String id;
	private String nomeCompleto;
	private String nome;
	private String quantidadeMinimaEstoque;
	private String localizacao;
	private MarcaDTO marca;
	private UnidadeMedidaDTO unidadeMedida;
	private TipoDTO tipo;

	public void montarNomeCompleto() {
		String nome = Utilidades.isNuloOuVazio( this.getNome() ) ? "" : this.getNome();
		String marca = Utilidades.isNuloOuVazio( this.getMarca().getNome() ) ? "" : this.getMarca().getNome();
		String nomeTipo = Utilidades.isNuloOuVazio( this.getTipo().getNome() ) ? "" : this.getTipo().getNome();
		String siglaUnidadeMedida = Utilidades.isNuloOuVazio( this.getUnidadeMedida().getSigla() ) ? "" : "(".concat( this.getUnidadeMedida().getSigla() ).concat( ")" );
		String classificacao = Utilidades.isNuloOuVazio( this.getTipo().getClassificacao() ) ? "" : "[".concat( this.getTipo().getClassificacao() ).concat( "]" );

		String valor = String.join( " ", classificacao, nomeTipo, nome, marca, siglaUnidadeMedida ).replaceAll( "null", "" ).trim();
		if ( Utilidades.isNuloOuVazio( valor ) ) {
			return;
		}
		this.setNomeCompleto( valor );
	}

	public String getNomeCompleto() {
		//this.montarNomeCompleto();
		return this.nomeCompleto;
	}

	public void setNomeCompleto( String nomeCompleto ) {
		this.nomeCompleto = nomeCompleto;
	}

	public void ajustarObjetosParaRelacionamentos() {
		this.setMarca( new MarcaDTO( Utilidades.isNuloOuVazio( this.getMarca().getId() ) ? null : this.getMarca().getId() ) );
		this.setUnidadeMedida( new UnidadeMedidaDTO( Utilidades.isNuloOuVazio( this.getUnidadeMedida().getId() ) ? null : this.getUnidadeMedida().getId() ) );
		this.setTipo( new TipoDTO( Utilidades.isNuloOuVazio( this.getTipo().getId() ) ? null : this.getTipo().getId() ) );
	}

	public TipoDTO getTipo() {
		if ( this.tipo == null ) {
			this.tipo = new TipoDTO();
		}
		return this.tipo;
	}

	public String getId() {
		return this.id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public void setTipo( TipoDTO tipo ) {
		this.tipo = tipo;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome( String nome ) {
		this.nome = nome;
	}

	public MarcaDTO getMarca() {
		if ( this.marca == null ) {
			this.marca = new MarcaDTO();
		}
		return this.marca;
	}

	public void setMarca( MarcaDTO marca ) {
		this.marca = marca;
	}

	public String getQuantidadeMinimaEstoque() {
		return this.quantidadeMinimaEstoque;
	}

	public void setQuantidadeMinimaEstoque( String quantidadeMinimaEstoque ) {
		this.quantidadeMinimaEstoque = quantidadeMinimaEstoque;
	}

	public String getLocalizacao() {
		return this.localizacao;
	}

	public void setLocalizacao( String localizacao ) {
		this.localizacao = localizacao;
	}

	public UnidadeMedidaDTO getUnidadeMedida() {
		if ( this.unidadeMedida == null ) {
			this.unidadeMedida = new UnidadeMedidaDTO();
		}
		return this.unidadeMedida;
	}

	public void setUnidadeMedida( UnidadeMedidaDTO unidadeMedida ) {
		this.unidadeMedida = unidadeMedida;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = ( prime * result ) + ( ( this.nomeCompleto == null ) ? 0 : this.nomeCompleto.hashCode() );
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
		ProdutoDTO other = (ProdutoDTO) obj;

		if ( this.nomeCompleto == null ) {
			if ( other.nomeCompleto != null ) {
				return false;
			}
		} else if ( !this.nomeCompleto.equals( other.nomeCompleto ) ) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "ProdutoPO [\n\tid=" );
		builder.append( this.id );
		builder.append( ",\n\tnomeCompleto=" );
		builder.append( this.nomeCompleto );
		builder.append( ",\n\tnome=" );
		builder.append( this.nome );
		builder.append( ",\n\tquantidadeMinimaEstoque=" );
		builder.append( this.quantidadeMinimaEstoque );
		builder.append( ",\n\tlocalizacao=" );
		builder.append( this.localizacao );
		builder.append( ",\n\tmarca=" );
		builder.append( this.marca );
		builder.append( ",\n\tunidadeMedida=" );
		builder.append( this.unidadeMedida );
		builder.append( ",\n\ttipo=" );
		builder.append( this.tipo );

		builder.append( "]\n" );
		return builder.toString();
	}

	@Override
	public int compareTo( ProdutoDTO o ) {
		return this.getNomeCompleto().compareToIgnoreCase( o.getNomeCompleto() );
	}

}
