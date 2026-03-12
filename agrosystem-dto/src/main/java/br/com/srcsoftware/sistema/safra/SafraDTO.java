package br.com.srcsoftware.sistema.safra;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;

import br.com.srcsoftware.abstracts.AuditoriaAbstractDTO;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.safra.setorsafra.SetorSafraDTO;

public class SafraDTO extends AuditoriaAbstractDTO implements Comparable< SafraDTO >{

	private String id;
	private String nome;
	private ArrayList< SetorSafraDTO > setoresSafras;
	private String dataInicioFim;

	public String getNomeCompleto() {
		if ( Utilidades.isNuloOuVazio( this.nome ) || Utilidades.isNuloOuVazio( this.setoresSafras ) ) {
			return null;
		}

		StringBuilder nomeCompleto = new StringBuilder();
		nomeCompleto.append( this.getNome() );
		/*if ( !Utilidades.isNuloOuVazio( this.getId() ) ) {
			nomeCompleto.append( this.getNome() );
			nomeCompleto.append( " - " );
			nomeCompleto.append( this.getQuantidadeSetores() + " setores" );
			nomeCompleto.append( " - Área: " );
			nomeCompleto.append( this.getAreaTotal() );
		}*/
		return nomeCompleto.toString();
	}

	public String getQuantidadeSetores() {
		HashSet< String > setores = new HashSet<>();
		for ( SetorSafraDTO ssCorrente : this.getSetoresSafras() ) {
			setores.add( ssCorrente.getSetor().getId() );
		}
		return String.valueOf( setores.size() );
	}

	public String getAreaTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for ( SetorSafraDTO setorSafraCorrente : this.getSetoresSafras() ) {
			BigDecimal area = Utilidades.parseBigDecimal( setorSafraCorrente.getArea() );

			if ( !Utilidades.isNuloOuVazio( area ) ) {
				total = total.add( area );
			}
		}
		return Utilidades.parseBigDecimal( total );
	}

	public String getVariedades() {
		HashSet< String > variedades = new HashSet<>();
		for ( SetorSafraDTO setorSafraCorrente : this.getSetoresSafras() ) {
			variedades.add( setorSafraCorrente.getVariedade().getNome() );
		}
		return variedades.toString().replace( ",", " / " ).replace( "[", "" ).replace( "]", "" ).trim();
	}

	public String getAreaSetor( String idSetor ) {
		/*for ( SetorSafraDTO setorSafraCorrente : this.getSetoresSafras() ) {
		
			if ( setorSafraCorrente.getSetor().getId().equals( idSetor ) ) {
				return setorSafraCorrente.getArea();
			}
		}
		return "0";*/

		BigDecimal total = BigDecimal.ZERO;
		for ( SetorSafraDTO setorSafraCorrente : this.getSetoresSafras() ) {
			if ( setorSafraCorrente.getSetor().getId().equals( idSetor ) ) {
				BigDecimal area = Utilidades.parseBigDecimal( setorSafraCorrente.getArea() );

				if ( !Utilidades.isNuloOuVazio( area ) ) {
					total = total.add( area );
				}
			}
		}
		return Utilidades.parseBigDecimal( total );
	}

	public String getVariedadeSetor( String idSetor ) {
		for ( SetorSafraDTO setorSafraCorrente : this.getSetoresSafras() ) {

			if ( setorSafraCorrente.getSetor().getId().equals( idSetor ) ) {
				//return setorSafraCorrente.getCultura().getNomeCompleto();
				return setorSafraCorrente.getVariedade().getNome();
			}
		}
		return "N/A";
	}

	public String getCulturaSetor( String idSetor ) {
		for ( SetorSafraDTO setorSafraCorrente : this.getSetoresSafras() ) {

			if ( setorSafraCorrente.getSetor().getId().equals( idSetor ) ) {
				//return setorSafraCorrente.getCultura().getNomeCompleto();
				return setorSafraCorrente.getVariedade().getCultura().getNome();
			}
		}
		return "N/A";
	}

	public String getAreaTotalVariedade( String idVariedade ) {
		BigDecimal total = BigDecimal.ZERO;
		for ( SetorSafraDTO setorSafraCorrente : this.getSetoresSafras() ) {

			if ( setorSafraCorrente.getVariedade().getId().equals( idVariedade ) ) {
				BigDecimal area = Utilidades.parseBigDecimal( setorSafraCorrente.getArea() );

				if ( !Utilidades.isNuloOuVazio( area ) ) {
					total = total.add( area );
				}
			}
		}
		return Utilidades.parseBigDecimal( total );
	}

	public String getAreaTotalCultura( String idCultura ) {
		BigDecimal total = BigDecimal.ZERO;
		for ( SetorSafraDTO setorSafraCorrente : this.getSetoresSafras() ) {

			if ( setorSafraCorrente.getVariedade().getCultura().getId().equals( idCultura ) ) {
				BigDecimal area = Utilidades.parseBigDecimal( setorSafraCorrente.getArea() );

				if ( !Utilidades.isNuloOuVazio( area ) ) {
					total = total.add( area );
				}
			}
		}
		return Utilidades.parseBigDecimal( total );
	}

	public String getVariedade( String idVariedade ) {
		for ( SetorSafraDTO setorSafraCorrente : this.getSetoresSafras() ) {

			if ( setorSafraCorrente.getVariedade().getId().equals( idVariedade ) ) {
				//return setorSafraCorrente.getCultura().getNomeCompleto();
				return setorSafraCorrente.getVariedade().getNome();
			}
		}
		return "N/A";
	}

	public String getCultura( String idCultura ) {
		for ( SetorSafraDTO setorSafraCorrente : this.getSetoresSafras() ) {

			if ( setorSafraCorrente.getVariedade().getCultura().getId().equals( idCultura ) ) {
				//return setorSafraCorrente.getCultura().getNomeCompleto();
				return setorSafraCorrente.getVariedade().getCultura().getNome();
			}
		}
		return "N/A";
	}

	public String getId() {
		return this.id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public String getDataInicioFim() {
		return this.dataInicioFim;
	}

	public void setDataInicioFim( String dataInicioFim ) {
		this.dataInicioFim = dataInicioFim;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome( String nome ) {
		this.nome = nome;
	}

	public ArrayList< SetorSafraDTO > getSetoresSafras() {
		if ( this.setoresSafras == null ) {
			this.setoresSafras = new ArrayList<>();
		}
		return this.setoresSafras;
	}

	public void setSetoresSafras( ArrayList< SetorSafraDTO > setoresSafras ) {
		this.setoresSafras = setoresSafras;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = ( prime * result ) + ( ( this.nome == null ) ? 0 : this.nome.hashCode() );
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
		SafraDTO other = (SafraDTO) obj;
		if ( this.nome == null ) {
			if ( other.nome != null ) {
				return false;
			}
		} else if ( !this.nome.equals( other.nome ) ) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "SafraDTO [nome=" );
		builder.append( this.nome );
		builder.append( ", setoresSafras=" );
		builder.append( this.setoresSafras );
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
	public int compareTo( SafraDTO o ) {
		return this.getNome().compareToIgnoreCase( o.getNome() );
	}
}
