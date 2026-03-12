package br.com.srcsoftware.sistema.silo.saida;

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
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.pessoa.funcionario.FuncionarioFacade;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.silo.entrada.InformacoesProducaoSafraPOJO;
import br.com.srcsoftware.sistema.silo.naturezaoperacao.NaturezaOperacaoDTO;
import br.com.srcsoftware.sistema.silo.naturezaoperacao.NaturezaOperacaoFacade;
import br.com.srcsoftware.sistema.silo.silo.InformacoesSiloPOJO;
import br.com.srcsoftware.sistema.silo.silo.SiloDTO;
import br.com.srcsoftware.sistema.silo.silo.SiloFacade;

public final class SaidaGraoForm extends AbstractCRUDForm{

	private static final long serialVersionUID = 1342719712529624093L;

	//private ContratoVendaDTO contratoVenda;
	//private String quantidadeRestante;

	private SaidaGraoDTO saidaGrao;

	private ArrayList< SaidaGraoDTO > saidas;

	private ArrayList< InformacoesSiloPOJO > silos;
	private ArrayList< InformacoesProducaoSafraPOJO > safras;

	private String dataInicial;
	private String dataFinal;

	private String totalPesoLiquido;
	private String totalSacas;
	private String totalValorLiquido;

	public void calcularTotais( List< SaidaGraoDTO > encontradosParaTotais ) {

		BigDecimal totalPesoLiquido = BigDecimal.ZERO;
		BigDecimal totalSacas = BigDecimal.ZERO;
		BigDecimal totalValorLiquido = BigDecimal.ZERO;

		for ( SaidaGraoDTO corrente : encontradosParaTotais ) {

			BigDecimal pesoLiquido = Utilidades.parseBigDecimal( corrente.getPesoLiquido() );
			totalPesoLiquido = totalPesoLiquido.add( pesoLiquido );

			BigDecimal sacas = Utilidades.parseBigDecimal( corrente.getEmSacas() );
			totalSacas = totalSacas.add( sacas );

			BigDecimal valorLiquido = Utilidades.parseBigDecimal( corrente.getValorLiquido() );
			totalValorLiquido = totalValorLiquido.add( valorLiquido );

		}

		setTotalPesoLiquido( Utilidades.parseBigDecimal( totalPesoLiquido ) );
		setTotalSacas( Utilidades.parseBigDecimal( totalSacas ) );
		setTotalValorLiquido( Utilidades.parseBigDecimal( totalValorLiquido ) );
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

	public String getTotalValorLiquido() {
		return totalValorLiquido;
	}

	public void setTotalValorLiquido( String totalValorLiquido ) {
		this.totalValorLiquido = totalValorLiquido;
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

	/*public String getQuantidadeRestante() {
		return quantidadeRestante;
	}
	
	public void setQuantidadeRestante( String quantidadeRestante ) {
		this.quantidadeRestante = quantidadeRestante;
	}*/

	/*public ContratoVendaDTO getContratoVenda() {
		if ( this.contratoVenda == null ) {
			this.contratoVenda = new ContratoVendaDTO();
		}
		return this.contratoVenda;
	}
	
	public void setContratoVenda( ContratoVendaDTO contratoVenda ) {
		this.contratoVenda = contratoVenda;
	}*/

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

	public ArrayList< SaidaGraoDTO > getSaidas() {
		if ( this.saidas == null ) {
			this.saidas = new ArrayList<>();
		}
		return this.saidas;
	}

	public void setSaidas( ArrayList< SaidaGraoDTO > saidas ) {
		this.saidas = saidas;
	}

	public SaidaGraoDTO getSaidaGrao() {
		if ( this.saidaGrao == null ) {
			this.saidaGrao = new SaidaGraoDTO();
		}
		return this.saidaGrao;
	}

	public void setSaidaGrao( SaidaGraoDTO saidaGrao ) {
		this.saidaGrao = saidaGrao;
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

	public ArrayList< LabelValueBean > getComboNaturezas() throws Throwable {
		ArrayList< LabelValueBean > lista = new ArrayList<>();

		try {

			List< NaturezaOperacaoDTO > encontrados = NaturezaOperacaoFacade.getInstance().filtrar( Paginacao.NAO, HibernateConnection.SEM_ORDENACAO, null );

			Collections.sort( encontrados, new Comparator< NaturezaOperacaoDTO >(){

				@Override
				public int compare( NaturezaOperacaoDTO o1, NaturezaOperacaoDTO o2 ) {
					return o1.getNome().compareTo( o2.getNome() );
				}

			} );

			Iterator< NaturezaOperacaoDTO > setIter = encontrados.iterator();

			LabelValueBean lvb = new LabelValueBean();

			while ( setIter.hasNext() ) {
				NaturezaOperacaoDTO bean = setIter.next();

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
		setSaidaGrao( null );
		//setContratoVenda( null );
		//setQuantidadeRestante( null );

		try {
			getSaidaGrao().setEstoquista( FuncionarioFacade.getInstance().filtrarPorId( getUsuarioSessaoPOJO( request ).getIdPessoa() ) );
		} catch ( ApplicationException e ) {
			e.printStackTrace();
		}

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
		this.getSaidas().clear();
	}

}
