package br.com.srcsoftware.sistema.silo.entrada;

import java.math.BigDecimal;

import br.com.srcsoftware.modular.manager.utilidades.Utilidades;

public class InformacoesProducaoSafraPOJO implements Comparable< InformacoesProducaoSafraPOJO >{

	private String idSafra;
	private String nomeSafra;
	private String nomeSetor;
	private String cultura;
	private String variedade;
	private String producao;
	private String retirado;

	public String getEmSacas() {
		return Utilidades.parseBigDecimal( Utilidades.parseBigDecimal( this.getProducao() ).divide( new BigDecimal( "60" ), 3, BigDecimal.ROUND_FLOOR ) );
	}

	public String getNomeSetorView() {
		return nomeSetor.replace( "|", "<br \\>" );
	}

	public String getNomeSetor() {
		return nomeSetor;
	}

	public void setNomeSetor( String nomeSetor ) {
		this.nomeSetor = nomeSetor;
	}

	public String getVariedadeView() {
		return variedade.replace( "|", "<br \\>" );
	}

	public String getVariedade() {
		return variedade;
	}

	public void setVariedade( String variedade ) {
		this.variedade = variedade;
	}

	public String getIdSafra() {
		return idSafra;
	}

	public void setIdSafra( String idSafra ) {
		this.idSafra = idSafra;
	}

	public String getNomeSafraView() {
		return nomeSafra.replace( "|", "<br \\>" );
	}

	public String getNomeSafra() {
		return nomeSafra;
	}

	public void setNomeSafra( String nomeSafra ) {
		this.nomeSafra = nomeSafra;
	}

	public String getCulturaView() {
		return cultura.replace( "|", "<br \\>" );
	}

	public String getCultura() {
		return cultura;
	}

	public void setCultura( String cultura ) {
		this.cultura = cultura;
	}

	public String getProducao() {
		return producao;
	}

	public void setProducao( String producao ) {
		this.producao = producao;
	}

	public String getRetirado() {
		return retirado;
	}

	public void setRetirado( String retirado ) {
		this.retirado = retirado;
	}

	@Override
	public int compareTo( InformacoesProducaoSafraPOJO o ) {
		return this.getNomeSafra().compareToIgnoreCase( o.getNomeSafra() );
	}
}
