package br.com.srcsoftware.sistema.silo.entrada;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.LabelValueBean;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.abstracts.AbstractCRUDForm;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.silo.silo.InformacoesSiloPOJO;
import br.com.srcsoftware.sistema.silo.silo.SiloDTO;
import br.com.srcsoftware.sistema.silo.silo.SiloFacade;

public final class EntradaGraoForm extends AbstractCRUDForm{

	private static final long serialVersionUID = 1342719712529624093L;

	private EntradaGraoDTO entradaGrao;

	private ArrayList< EntradaGraoDTO > entradas;

	private ArrayList< InformacoesSiloPOJO > silos;
	private ArrayList< InformacoesProducaoSafraPOJO > safras;

	private String dataInicial;
	private String dataFinal;

	private String totalPesoBruto;
	private String totalPesoLiquido;
	private String totalSacas;

	public void calcularTotais( List< EntradaGraoDTO > encontradosParaTotais ) {

		BigDecimal totalPesoBruto = BigDecimal.ZERO;
		BigDecimal totalPesoLiquido = BigDecimal.ZERO;
		BigDecimal totalSacas = BigDecimal.ZERO;

		for ( EntradaGraoDTO corrente : encontradosParaTotais ) {

			BigDecimal pesoBruto = Utilidades.parseBigDecimal( corrente.getPesoBruto() );
			totalPesoBruto = totalPesoBruto.add( pesoBruto );

			BigDecimal pesoLiquido = Utilidades.parseBigDecimal( corrente.getPesoLiquido() );
			totalPesoLiquido = totalPesoLiquido.add( pesoLiquido );

			BigDecimal sacas = Utilidades.parseBigDecimal( corrente.getEmSacas() );
			totalSacas = totalSacas.add( sacas );
		}

		setTotalPesoBruto( Utilidades.parseBigDecimal( totalPesoBruto ) );
		setTotalPesoLiquido( Utilidades.parseBigDecimal( totalPesoLiquido ) );
		setTotalSacas( Utilidades.parseBigDecimal( totalSacas ) );
	}

	public String getTotalPesoBruto() {
		return totalPesoBruto;
	}

	public void setTotalPesoBruto( String totalPesoBruto ) {
		this.totalPesoBruto = totalPesoBruto;
	}

	public String getTotalPesoLiquido() {
		return totalPesoLiquido;
	}

	public void setTotalPesoLiquido( String totalPesoLiquido ) {
		this.totalPesoLiquido = totalPesoLiquido;
	}

	public String getTotalSacas() {
		return totalSacas;
	}

	public void setTotalSacas( String totalSacas ) {
		this.totalSacas = totalSacas;
	}

	public ArrayList< InformacoesProducaoSafraPOJO > getSafras() {
		if ( this.safras == null ) {
			this.safras = new ArrayList<>();
		}
		return safras;
	}

	public void setSafras( ArrayList< InformacoesProducaoSafraPOJO > safras ) {
		this.safras = safras;
	}

	public ArrayList< InformacoesSiloPOJO > getSilos() {
		if ( this.silos == null ) {
			this.silos = new ArrayList<>();
		}
		return silos;
	}

	public void setSilos( ArrayList< InformacoesSiloPOJO > silos ) {
		this.silos = silos;
	}

	public String getDataInicial() {
		return this.dataInicial;
	}

	public void setDataInicial( String dataInicial ) {
		this.dataInicial = dataInicial;
	}

	public String getDataFinal() {
		return this.dataFinal;
	}

	public void setDataFinal( String dataFinal ) {
		this.dataFinal = dataFinal;
	}

	public ArrayList< EntradaGraoDTO > getEntradas() {
		if ( this.entradas == null ) {
			this.entradas = new ArrayList<>();
		}
		return this.entradas;
	}

	public void setEntradas( ArrayList< EntradaGraoDTO > entradas ) {
		this.entradas = entradas;
	}

	public EntradaGraoDTO getEntradaGrao() {
		if ( this.entradaGrao == null ) {
			this.entradaGrao = new EntradaGraoDTO();
		}
		return this.entradaGrao;
	}

	public void setEntradaGrao( EntradaGraoDTO entradaGrao ) {
		this.entradaGrao = entradaGrao;
	}

	public ArrayList< LabelValueBean > getComboSilos() throws Throwable {
		ArrayList< LabelValueBean > lista = new ArrayList<>();

		try {

			List< SiloDTO > encontrados = SiloFacade.getInstance().filtrar( Paginacao.NAO, HibernateConnection.SEM_ORDENACAO, null );

			Collections.sort( encontrados, new Comparator< SiloDTO >(){

				@Override
				public int compare( SiloDTO o1, SiloDTO o2 ) {
					return o1.getNome().compareTo( o2.getNome() );
				}

			} );

			Iterator< SiloDTO > setIter = encontrados.iterator();

			LabelValueBean lvb = new LabelValueBean();

			while ( setIter.hasNext() ) {
				SiloDTO bean = setIter.next();

				lvb = new LabelValueBean();

				lvb.setLabel( bean.getNome() );
				lvb.setValue( bean.getId().toString() );

				lista.add( lvb );

			}

		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public void limparCamposCadastro( HttpServletRequest request ) {
		setEntradaGrao( null );

		getSilos().clear();
		getSafras().clear();
	}

	@Override
	public void limparCamposConsulta( HttpServletRequest request ) {
		setDataInicial( null );
		setDataFinal( null );
		limparCamposCadastro( request );
	}

	@Override
	public void limparLista() {
		this.getEntradas().clear();
	}

}
