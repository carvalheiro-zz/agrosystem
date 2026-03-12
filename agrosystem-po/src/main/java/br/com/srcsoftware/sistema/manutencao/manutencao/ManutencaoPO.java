package br.com.srcsoftware.sistema.manutencao.manutencao;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.srcsoftware.abstracts.AuditoriaAbstractPO;
import br.com.srcsoftware.sistema.manutencao.imovel.ImovelPO;
import br.com.srcsoftware.sistema.manutencao.implemento.ImplementoPO;
import br.com.srcsoftware.sistema.manutencao.servico.ServicoPO;
import br.com.srcsoftware.sistema.manutencao.veiculo.VeiculoPO;
import br.com.srcsoftware.sistema.pessoa.fornecedor.juridico.FornecedorJuridicoPO;
import br.com.srcsoftware.sistema.pessoa.prestadorservico.PrestadorServicoPO;
import br.com.srcsoftware.sistema.safra.SafraPO;
import br.com.srcsoftware.sistema.safra.cultura.CulturaPO;
import br.com.srcsoftware.sistema.safra.setor.SetorPO;

@Entity
@Table( name = "sistema_manutencoes", uniqueConstraints = @UniqueConstraint( columnNames = { "notaFiscal", "idFornecedor", "idVeiculo", "idImovel", "idImplemento", "idServico" }, name = "UK_sistema_manutencoes" ) )
public class ManutencaoPO extends AuditoriaAbstractPO{

	@Column( length = 10, nullable = false )
	private LocalDate data;

	@Column( length = 15, nullable = true )
	private String notaFiscal;

	@Column( length = 15, nullable = false )
	private String finalidade;

	@Column( precision = 12, scale = 2, nullable = false )
	private BigDecimal custo;

	@Column( length = 60, nullable = false )
	private String reciboOuNotaFiscal;

	@Column( length = 20, nullable = false )
	private String tipo;

	@Column( length = 50, nullable = true )
	private String kmHorimetro;

	@ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, optional = true )
	@JoinColumn( name = "idVeiculo", nullable = true, foreignKey = @ForeignKey( name = "fk_veiculo_manutencoes" ) )
	private VeiculoPO veiculo = new VeiculoPO();

	@ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, optional = true )
	@JoinColumn( name = "idImplemento", nullable = true, foreignKey = @ForeignKey( name = "fk_implemento_manutencoes" ) )
	private ImplementoPO implemento = new ImplementoPO();

	@ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, optional = true )
	@JoinColumn( name = "idImovel", nullable = true, foreignKey = @ForeignKey( name = "fk_imovel_manutencoes" ) )
	private ImovelPO imovel = new ImovelPO();

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idFornecedor", nullable = false, foreignKey = @ForeignKey( name = "fk_fornecedor_manutencoes" ) )
	private FornecedorJuridicoPO fornecedor = new FornecedorJuridicoPO();

	@ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, optional = false )
	@JoinColumn( name = "idServico", nullable = false, foreignKey = @ForeignKey( name = "fk_servico_manutencoes" ) )
	private ServicoPO servico = new ServicoPO();

	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idSafra", nullable = false, foreignKey = @ForeignKey( name = "fk_safra_manutencoes" ) )
	private SafraPO safra = new SafraPO();

	@ManyToOne( fetch = FetchType.EAGER, optional = true )
	@JoinColumn( name = "idSetor", nullable = true, foreignKey = @ForeignKey( name = "fk_setor_manutencoes" ) )
	private SetorPO setor = new SetorPO();

	@ManyToOne( fetch = FetchType.EAGER, optional = true )
	@JoinColumn( name = "idCultura", nullable = true, foreignKey = @ForeignKey( name = "fk_cultura_manutencoes" ) )
	private CulturaPO cultura;

	@ManyToOne( fetch = FetchType.EAGER, optional = true )
	@JoinColumn( name = "idPrestador", nullable = true, foreignKey = @ForeignKey( name = "fk_prestador_itens" ) )
	private PrestadorServicoPO prestador;

	@Lob
	@Column( length = 1000, nullable = true )
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

	public PrestadorServicoPO getPrestador() {
		return this.prestador;
	}

	public void setPrestador( PrestadorServicoPO prestador ) {
		this.prestador = prestador;
	}

	public String getReciboOuNotaFiscal() {
		return this.reciboOuNotaFiscal;
	}

	public void setReciboOuNotaFiscal( String reciboOuNotaFiscal ) {
		this.reciboOuNotaFiscal = reciboOuNotaFiscal;
	}

	public CulturaPO getCultura() {
		return this.cultura;
	}

	public void setCultura( CulturaPO cultura ) {
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

	public VeiculoPO getVeiculo() {
		return this.veiculo;
	}

	public void setVeiculo( VeiculoPO veiculo ) {
		this.veiculo = veiculo;
	}

	public ImplementoPO getImplemento() {
		return this.implemento;
	}

	public void setImplemento( ImplementoPO implemento ) {
		this.implemento = implemento;
	}

	public ImovelPO getImovel() {
		return this.imovel;
	}

	public void setImovel( ImovelPO imovel ) {
		this.imovel = imovel;
	}

	public FornecedorJuridicoPO getFornecedor() {
		return this.fornecedor;
	}

	public void setFornecedor( FornecedorJuridicoPO fornecedor ) {
		this.fornecedor = fornecedor;
	}

	public ServicoPO getServico() {
		return this.servico;
	}

	public void setServico( ServicoPO servico ) {
		this.servico = servico;
	}

	public SafraPO getSafra() {
		return this.safra;
	}

	public void setSafra( SafraPO safra ) {
		this.safra = safra;
	}

	public SetorPO getSetor() {
		return this.setor;
	}

	public void setSetor( SetorPO setor ) {
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
		ManutencaoPO other = (ManutencaoPO) obj;
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
		builder.append( ", getId()=" );
		builder.append( this.getId() );
		builder.append( "]" );
		return builder.toString();
	}

}
