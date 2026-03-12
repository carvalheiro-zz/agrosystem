package br.com.srcsoftware.gestao.sistema.manutencao.manutencao;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.srcsoftware.gestao.AuditoriaAbstractMigracaoPO;
import br.com.srcsoftware.gestao.sistema.aplicacao.safra.SafraMigracaoPO;
import br.com.srcsoftware.gestao.sistema.aplicacao.setor.SetorMigracaoPO;
import br.com.srcsoftware.gestao.sistema.cultura.CulturaMigracaoPO;
import br.com.srcsoftware.gestao.sistema.manutencao.imovel.ImovelMigracaoPO;
import br.com.srcsoftware.gestao.sistema.manutencao.implemento.ImplementoMigracaoPO;
import br.com.srcsoftware.gestao.sistema.manutencao.servico.ServicoMigracaoPO;
import br.com.srcsoftware.gestao.sistema.manutencao.veiculo.VeiculoMigracaoPO;
import br.com.srcsoftware.gestao.sistema.pedido.fornecedor.FornecedorMigracaoPO;
import br.com.srcsoftware.modular.manager.utilidades.Constantes;

@Entity
@Table( name = "manutencoes", schema = Constantes.SCHEMA_MIGRACAO, uniqueConstraints = @UniqueConstraint( columnNames = { "data", "notaFiscal", "idCultura", "idSafra", "idSetor" } ) )
public class ManutencaoMigracaoPO extends AuditoriaAbstractMigracaoPO{

	@Column( name = "data", length = 10, nullable = false )
	private LocalDate data;

	@Column( name = "notaFiscal", length = 15, nullable = true )
	private String notaFiscal;

	@Column( name = "finalidade", length = 15, nullable = false )
	private String finalidade;

	@Column( name = "custo", precision = 12, scale = 2, nullable = false )
	private BigDecimal custo;

	@Column( name = "reciboOuNotaFiscal", length = 60, nullable = false )
	private String reciboOuNotaFiscal;

	@Column( name = "tipo", length = 20, nullable = false )
	private String tipo;

	@ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, optional = true )
	@JoinColumn( name = "idVeiculo", nullable = true )
	private VeiculoMigracaoPO veiculo = new VeiculoMigracaoPO();

	@ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, optional = true )
	@JoinColumn( name = "idImplemento", nullable = true )
	private ImplementoMigracaoPO implemento = new ImplementoMigracaoPO();

	@ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, optional = true )
	@JoinColumn( name = "idImovel", nullable = true )
	private ImovelMigracaoPO imovel = new ImovelMigracaoPO();

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idFornecedor", nullable = false )
	private FornecedorMigracaoPO fornecedor = new FornecedorMigracaoPO();

	@ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, optional = false )
	@JoinColumn( name = "idServico", nullable = false )
	private ServicoMigracaoPO servico = new ServicoMigracaoPO();

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idSafra", nullable = false )
	private SafraMigracaoPO safra = new SafraMigracaoPO();

	@ManyToOne( fetch = FetchType.EAGER, optional = true )
	@JoinColumn( name = "idSetor", nullable = true )
	private SetorMigracaoPO setor = new SetorMigracaoPO();

	@ManyToOne( fetch = FetchType.EAGER, optional = true )
	@JoinColumn( name = "idCultura", nullable = true )
	private CulturaMigracaoPO cultura;

	public String getReciboOuNotaFiscal() {
		return this.reciboOuNotaFiscal;
	}

	public void setReciboOuNotaFiscal( String reciboOuNotaFiscal ) {
		this.reciboOuNotaFiscal = reciboOuNotaFiscal;
	}

	public CulturaMigracaoPO getCultura() {
		return this.cultura;
	}

	public void setCultura( CulturaMigracaoPO cultura ) {
		this.cultura = cultura;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo( String tipo ) {
		this.tipo = tipo;
	}

	public LocalDate getData() {
		return this.data;
	}

	public void setData( LocalDate data ) {
		this.data = data;
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

	public BigDecimal getCusto() {
		return this.custo;
	}

	public void setCusto( BigDecimal custo ) {
		this.custo = custo;
	}

	public VeiculoMigracaoPO getVeiculo() {
		return this.veiculo;
	}

	public void setVeiculo( VeiculoMigracaoPO veiculo ) {
		this.veiculo = veiculo;
	}

	public ImplementoMigracaoPO getImplemento() {
		return this.implemento;
	}

	public void setImplemento( ImplementoMigracaoPO implemento ) {
		this.implemento = implemento;
	}

	public ImovelMigracaoPO getImovel() {
		return this.imovel;
	}

	public void setImovel( ImovelMigracaoPO imovel ) {
		this.imovel = imovel;
	}

	public FornecedorMigracaoPO getFornecedor() {
		return this.fornecedor;
	}

	public void setFornecedor( FornecedorMigracaoPO fornecedor ) {
		this.fornecedor = fornecedor;
	}

	public ServicoMigracaoPO getServico() {
		return this.servico;
	}

	public void setServico( ServicoMigracaoPO servico ) {
		this.servico = servico;
	}

	public SafraMigracaoPO getSafra() {
		return this.safra;
	}

	public void setSafra( SafraMigracaoPO safra ) {
		this.safra = safra;
	}

	public SetorMigracaoPO getSetor() {
		return this.setor;
	}

	public void setSetor( SetorMigracaoPO setor ) {
		this.setor = setor;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "ManutencaoPO [data=" );
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
		builder.append( "]" );
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 0;
		result = ( prime * result ) + ( ( this.data == null ) ? 0 : this.data.hashCode() );
		result = ( prime * result ) + ( ( this.notaFiscal == null ) ? 0 : this.notaFiscal.hashCode() );
		result = ( prime * result ) + ( ( this.safra == null ) ? 0 : this.safra.hashCode() );
		result = ( prime * result ) + ( ( this.setor == null ) ? 0 : this.setor.hashCode() );
		result = ( prime * result ) + ( ( this.veiculo == null ) ? 0 : this.veiculo.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}

		if ( !( obj instanceof ManutencaoMigracaoPO ) ) {
			return false;
		}
		ManutencaoMigracaoPO other = (ManutencaoMigracaoPO) obj;
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
		if ( this.veiculo == null ) {
			if ( other.veiculo != null ) {
				return false;
			}
		} else if ( !this.veiculo.equals( other.veiculo ) ) {
			return false;
		}
		return true;
	}

}
