package br.com.srcsoftware.sistema.silo.ajuste;

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
import br.com.srcsoftware.sistema.silo.silo.SiloDTO;
import br.com.srcsoftware.sistema.silo.silo.SiloFacade;

public final class AjusteSiloForm extends AbstractCRUDForm{

	private static final long serialVersionUID = 1342719712529624093L;

	private AjusteSiloDTO ajusteSilo;

	private ArrayList< AjusteSiloDTO > ajustes;

	// 1 para Sobra de Classificação / 2 para Quebra de Classificação
	private String codigoTempTipo;
	private String dataInicial;
	private String dataFinal;

	public String getCodigoTempTipo() {
		return codigoTempTipo;
	}

	public void setCodigoTempTipo( String codigoTempTipo ) {
		this.codigoTempTipo = codigoTempTipo;
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

	public ArrayList< AjusteSiloDTO > getAjustes() {
		if ( this.ajustes == null ) {
			this.ajustes = new ArrayList<>();
		}
		return this.ajustes;
	}

	public void setAjustes( ArrayList< AjusteSiloDTO > ajustes ) {
		this.ajustes = ajustes;
	}

	public AjusteSiloDTO getAjusteSilo() {
		if ( this.ajusteSilo == null ) {
			this.ajusteSilo = new AjusteSiloDTO();
		}
		return this.ajusteSilo;
	}

	public void setAjusteSilo( AjusteSiloDTO ajusteSilo ) {
		this.ajusteSilo = ajusteSilo;
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
		setAjusteSilo( null );

		if ( "1".equals( getCodigoTempTipo() ) ) {
			getAjusteSilo().setTipo( "Sobra de Classificação" );
		} else if ( "2".equals( getCodigoTempTipo() ) ) {
			getAjusteSilo().setTipo( "Quebra de Classificação" );
		}
	}

	@Override
	public void limparCamposConsulta( HttpServletRequest request ) {
		setDataInicial( null );
		setDataFinal( null );
		limparCamposCadastro( request );

		if ( "1".equals( getCodigoTempTipo() ) ) {
			getAjusteSilo().setTipo( "Sobra de Classificação" );
		} else if ( "2".equals( getCodigoTempTipo() ) ) {
			getAjusteSilo().setTipo( "Quebra de Classificação" );
		}
	}

	@Override
	public void limparLista() {
		this.getAjustes().clear();
	}

}
