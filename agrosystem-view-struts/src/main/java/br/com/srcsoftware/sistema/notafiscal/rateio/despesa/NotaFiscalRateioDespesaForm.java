package br.com.srcsoftware.sistema.notafiscal.rateio.despesa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.LabelValueBean;

import br.com.srcsoftware.enuns.NotaFiscalRateioEnum;
import br.com.srcsoftware.modular.financeiro.enums.SituacaoFinanceiroEnum;
import br.com.srcsoftware.modular.financeiro.financeiro.FinanceiroFacade;
import br.com.srcsoftware.modular.financeiro.formapagamento.FormaPagamentoDTO;
import br.com.srcsoftware.modular.financeiro.titulo.titulopagar.TituloPagarDTO;
import br.com.srcsoftware.modular.manager.abstracts.AbstractCRUDForm;
import br.com.srcsoftware.modular.manager.utilidades.DateUtil;
import br.com.srcsoftware.sistema.notafiscal.rateio.NotaFiscalRateioDTO;
import br.com.srcsoftware.sistema.notafiscal.rateio.centrocusto.CentroCustoDTO;
import br.com.srcsoftware.sistema.notafiscal.rateio.centrocusto.CentroCustoFacade;
import br.com.srcsoftware.sistema.notafiscal.rateio.itemnotafiscalrateio.ItemNotaFiscalRateioDTO;

public class NotaFiscalRateioDespesaForm extends AbstractCRUDForm {

	private static final long serialVersionUID = 4314982858466992002L;

	private NotaFiscalRateioDTO notaFiscalRateio;

	private ArrayList< NotaFiscalRateioDTO > notas;

	private String dataInicial;
	private String dataFinal;

	/** Quando Há ToMany */
	private ItemNotaFiscalRateioDTO itemAdicionar;
	private String idItemRemover;

	private TituloPagarDTO parcelaSelecionada;

	public TituloPagarDTO getParcelaSelecionada() {
		if ( this.parcelaSelecionada == null ) {
			this.parcelaSelecionada = new TituloPagarDTO();
		}
		return this.parcelaSelecionada;
	}

	public void setParcelaSelecionada( TituloPagarDTO parcelaSelecionada ) {
		this.parcelaSelecionada = parcelaSelecionada;
	}

	public ItemNotaFiscalRateioDTO getItemAdicionar() {
		if ( this.itemAdicionar == null ) {
			this.itemAdicionar = new ItemNotaFiscalRateioDTO();
		}
		return this.itemAdicionar;
	}

	public void setItemAdicionar( ItemNotaFiscalRateioDTO itemAdicionar ) {
		this.itemAdicionar = itemAdicionar;
	}

	public String getIdItemRemover() {
		return this.idItemRemover;
	}

	public void setIdItemRemover( String idItemRemover ) {
		this.idItemRemover = idItemRemover;
	}

	public ArrayList< NotaFiscalRateioDTO > getNotas() {
		if ( this.notas == null ) {
			this.notas = new ArrayList<>();
		}
		return this.notas;
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

	public void setNotas( ArrayList< NotaFiscalRateioDTO > notas ) {
		this.notas = notas;
	}

	public NotaFiscalRateioDTO getNotaFiscalRateio() {
		if ( this.notaFiscalRateio == null ) {
			this.notaFiscalRateio = new NotaFiscalRateioDTO();
		}
		return this.notaFiscalRateio;
	}

	public void setNotaFiscalRateio( NotaFiscalRateioDTO notaFiscalRateio ) {
		this.notaFiscalRateio = notaFiscalRateio;
	}

	public ArrayList< LabelValueBean > getComboCentroCusto() throws Throwable {
		ArrayList< LabelValueBean > lista = new ArrayList<>();

		try {

			List< CentroCustoDTO > encontrados = CentroCustoFacade.getInstance().filtrarPorTipoNotaFiscalRateioAgrupado( null, getNotaFiscalRateio().getTipo() );

			Collections.sort( encontrados, new Comparator< CentroCustoDTO >() {

				@Override
				public int compare( CentroCustoDTO o1, CentroCustoDTO o2 ) {
					return o1.getCodigo().compareTo( o2.getCodigo() );
				}

			} );

			Iterator< CentroCustoDTO > setIter = encontrados.iterator();

			LabelValueBean lvb = new LabelValueBean();

			while ( setIter.hasNext() ) {
				CentroCustoDTO bean = setIter.next();

				lvb = new LabelValueBean();

				lvb.setLabel( bean.getCodigo() );
				lvb.setValue( bean.getId().toString() );

				lista.add( lvb );

			}

		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return lista;
	}

	public ArrayList< LabelValueBean > getComboFormaPagamento( String isADM ) throws Throwable {
		ArrayList< LabelValueBean > lista = new ArrayList<>();

		try {
			List< FormaPagamentoDTO > encontrados = FinanceiroFacade.getInstance().getComboFormaPagamento( String.valueOf( isADM ), null, getNotaFiscalRateio().getContaPagar().getTipo(),
				FormaPagamentoDTO.TIPO_DESPESA );

			Collections.sort( encontrados, new Comparator< FormaPagamentoDTO >() {

				@Override
				public int compare( FormaPagamentoDTO o1, FormaPagamentoDTO o2 ) {
					return o1.getNomeCompleto().compareTo( o2.getNomeCompleto() );
				}

			} );

			Iterator< FormaPagamentoDTO > setIter = encontrados.iterator();

			LabelValueBean lvb = new LabelValueBean();

			while ( setIter.hasNext() ) {
				FormaPagamentoDTO bean = setIter.next();

				lvb = new LabelValueBean();

				if ( Boolean.valueOf( isADM ) ) {
					lvb.setLabel( bean.getNomeCompleto() + " - " + bean.getEmpresa().getNomeFantasia() );
				} else {
					lvb.setLabel( bean.getNomeCompleto() );
				}

				lvb.setValue( bean.getId().toString() );

				lista.add( lvb );

			}

		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return lista;
	}

	public ArrayList< LabelValueBean > getComboFormaPagamentoAVista( String isADM ) throws Throwable {
		ArrayList< LabelValueBean > lista = new ArrayList<>();

		try {
			List< FormaPagamentoDTO > encontrados = FinanceiroFacade.getInstance().getComboFormaPagamentoAVista( String.valueOf( isADM ), null, FormaPagamentoDTO.TIPO_DESPESA );

			Collections.sort( encontrados, new Comparator< FormaPagamentoDTO >() {

				@Override
				public int compare( FormaPagamentoDTO o1, FormaPagamentoDTO o2 ) {
					return o1.getNomeCompleto().compareTo( o2.getNomeCompleto() );
				}

			} );

			Iterator< FormaPagamentoDTO > setIter = encontrados.iterator();

			LabelValueBean lvb = new LabelValueBean();

			while ( setIter.hasNext() ) {
				FormaPagamentoDTO bean = setIter.next();

				lvb = new LabelValueBean();

				if ( Boolean.valueOf( isADM ) ) {
					lvb.setLabel( bean.getNomeCompleto() + " - " + bean.getEmpresa().getNomeFantasia() );
				} else {
					lvb.setLabel( bean.getNomeCompleto() );
				}

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
		this.notaFiscalRateio = null;
		this.getNotaFiscalRateio().getContaPagar().setData( DateUtil.parseLocalDate( LocalDate.now() ) );
		this.getNotaFiscalRateio().getContaPagar().setSituacao( SituacaoFinanceiroEnum.ABERTA.getValor() );
		getNotaFiscalRateio().setTipo( NotaFiscalRateioEnum.TIPO_INVESTIMENTO_DESPESA.getValor() );
		request.getSession().removeAttribute( "erroAjax" );
		limparCamposItem();
	}

	@Override
	public void limparCamposConsulta( HttpServletRequest request ) {
		this.setDataInicial( null );
		this.setDataFinal( null );
		request.getSession().removeAttribute( "erroAjax" );
	}

	@Override
	public void limparLista() {
		this.getNotas().clear();
	}

	public void limparCamposItem() {
		this.setItemAdicionar( null );
		this.setIdItemRemover( null );
	}

}
