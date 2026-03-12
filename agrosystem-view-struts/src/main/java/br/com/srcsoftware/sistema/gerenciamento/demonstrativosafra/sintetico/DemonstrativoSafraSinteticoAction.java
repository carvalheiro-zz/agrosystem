package br.com.srcsoftware.sistema.gerenciamento.demonstrativosafra.sintetico;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import br.com.srcsoftware.modular.manager.abstracts.AbstractUsuarioAction;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.utilidades.MensagensResourcesApplication;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.modular.manager.view.utilidades.Messages;
import br.com.srcsoftware.sistema.demonstrativosafra.DemonstrativoSafraFacade;
import br.com.srcsoftware.sistema.demonstrativosafra.custodireto.insumo.CustoDiretoInsumoPOJO;
import br.com.srcsoftware.sistema.demonstrativosafra.custodireto.insumo.sintetico.CustoDiretoInsumoSinteticoPOJO;
import br.com.srcsoftware.sistema.safra.SafraDTO;
import br.com.srcsoftware.sistema.safra.SafraFacade;
import br.com.srcsoftware.sistema.safra.cultura.CulturaDTO;
import br.com.srcsoftware.sistema.safra.cultura.CulturaFacade;
import br.com.srcsoftware.sistema.safra.setor.SetorDTO;
import br.com.srcsoftware.sistema.safra.setor.SetorFacade;
import br.com.srcsoftware.sistema.safra.setorsafra.SetorSafraDTO;

public final class DemonstrativoSafraSinteticoAction extends AbstractUsuarioAction< DemonstrativoSafraSinteticoForm >{

	public ActionForward abrirModalDadosExcel( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		return mapping.findForward( "excelTabelaModalSintetica" );
	}

	public ActionForward abrirPainelDemonstrativoSintetico( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			DemonstrativoSafraSinteticoForm meuForm = this.getCastingForm( form, request );

			meuForm.limparMensagens( request );

			meuForm.getSinteticos().clear();
			meuForm.setSafra( null );
			meuForm.setCultura( null );

			meuForm.limparTudo();

		} catch ( ApplicationException | Exception e ) {
			logger.error( e.getMessage(), e );
			e.printStackTrace();
			request.getSession().setAttribute( "erroAjax", e.getMessage() );
			request.getSession().removeAttribute( "sucessoAjax" );
		}

		return mapping.findForward( "custoDiretoSinteticoAjax" );
	}

	@SuppressWarnings( "unchecked" )
	public ActionForward selecionarSafraAutoComplete( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			DemonstrativoSafraSinteticoForm meuForm = this.getCastingForm( form, request );

			meuForm.limparMensagens( request );

			SafraDTO pesquisar = new SafraDTO();
			pesquisar.setNome( meuForm.getSafra().getNome() );

			HashMap< String, String > camposOrdenacao = new HashMap<>();
			camposOrdenacao.put( "nome", "ASC" );

			List< SafraDTO > encontrados = SafraFacade.getInstance().filtrar( Paginacao.NAO, camposOrdenacao, pesquisar );

			JSONObject json = new JSONObject();
			JSONObject map = null;
			JSONArray arr = new JSONArray();

			for ( SafraDTO dtoCorrente : encontrados ) {

				map = new JSONObject();
				map.put( "value", dtoCorrente.getNome() );
				map.put( "data", dtoCorrente.getId() );
				arr.add( map );
			}
			json.put( "suggestions", arr );

			response.setContentType( "application/json" );
			response.setHeader( "Cache-Control", "nocache" );
			response.getWriter().print( json.toString() );
		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( MensagensResourcesApplication.getString( "falha", new String[ ] { this.getClass().getSimpleName() + "-" + e.getMessage(), e.toString() } ) );
			e.printStackTrace();

			request.getSession().setAttribute( "erroAjax", e.getMessage() );
			request.getSession().removeAttribute( "sucessoAjax" );
		} catch ( Throwable e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { "Erro inesperado![" + e.getMessage() + "]" } ) );
			logger.error( MensagensResourcesApplication.getString( "log.erro.inesperado", new String[ ] { this.getClass().getSimpleName() + " [inserir]", e.toString() } ) );
			e.printStackTrace();

			request.getSession().setAttribute( "erroAjax", e.getMessage() );
			request.getSession().removeAttribute( "sucessoAjax" );
		}
		return null;
	}

	@SuppressWarnings( "unchecked" )
	public ActionForward selecionarSetorAutoComplete( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			DemonstrativoSafraSinteticoForm meuForm = this.getCastingForm( form, request );

			meuForm.limparMensagens( request );

			List< SetorDTO > encontrados = SetorFacade.getInstance().filtrarPorSafra( meuForm.getSafra().getId(), meuForm.getSetor().getNomeCompleto() );

			JSONObject json = new JSONObject();
			JSONObject map = null;
			JSONArray arr = new JSONArray();

			for ( SetorDTO dtoCorrente : encontrados ) {

				map = new JSONObject();
				map.put( "value", dtoCorrente.getNomeCompleto() );
				map.put( "data", dtoCorrente.getId() );
				arr.add( map );
			}
			json.put( "suggestions", arr );

			response.setContentType( "application/json" );
			response.setHeader( "Cache-Control", "nocache" );
			response.getWriter().print( json.toString() );
		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( MensagensResourcesApplication.getString( "falha", new String[ ] { this.getClass().getSimpleName() + "-" + e.getMessage(), e.toString() } ) );
			e.printStackTrace();

			request.getSession().setAttribute( "erroAjax", e.getMessage() );
			request.getSession().removeAttribute( "sucessoAjax" );
		} catch ( Throwable e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { "Erro inesperado![" + e.getMessage() + "]" } ) );
			logger.error( MensagensResourcesApplication.getString( "log.erro.inesperado", new String[ ] { this.getClass().getSimpleName() + " [inserir]", e.toString() } ) );
			e.printStackTrace();

			request.getSession().setAttribute( "erroAjax", e.getMessage() );
			request.getSession().removeAttribute( "sucessoAjax" );
		}
		return null;
	}

	@SuppressWarnings( "unchecked" )
	public ActionForward selecionarCulturaAutoComplete( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			DemonstrativoSafraSinteticoForm meuForm = this.getCastingForm( form, request );

			meuForm.limparMensagens( request );

			List< CulturaDTO > encontrados = CulturaFacade.getInstance().filtrarPorSafraSetor( meuForm.getSafra().getId(), meuForm.getSetor().getId(), meuForm.getCultura().getNome(), null );

			JSONObject json = new JSONObject();
			JSONObject map = null;
			JSONArray arr = new JSONArray();

			for ( CulturaDTO dtoCorrente : encontrados ) {

				map = new JSONObject();
				map.put( "value", dtoCorrente.getNome() );
				map.put( "data", dtoCorrente.getId() );
				arr.add( map );
			}
			json.put( "suggestions", arr );

			response.setContentType( "application/json" );
			response.setHeader( "Cache-Control", "nocache" );
			response.getWriter().print( json.toString() );
		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( MensagensResourcesApplication.getString( "falha", new String[ ] { this.getClass().getSimpleName() + "-" + e.getMessage(), e.toString() } ) );
			e.printStackTrace();

			request.getSession().setAttribute( "erroAjax", e.getMessage() );
			request.getSession().removeAttribute( "sucessoAjax" );
		} catch ( Throwable e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { "Erro inesperado![" + e.getMessage() + "]" } ) );
			logger.error( MensagensResourcesApplication.getString( "log.erro.inesperado", new String[ ] { this.getClass().getSimpleName() + " [inserir]", e.toString() } ) );
			e.printStackTrace();

			request.getSession().setAttribute( "erroAjax", e.getMessage() );
			request.getSession().removeAttribute( "sucessoAjax" );
		}
		return null;
	}

	public ActionForward filtrarDemonstrativoSafraCustosDiretosSintetico( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			DemonstrativoSafraSinteticoForm meuForm = this.getCastingForm( form, request );

			meuForm.limparMensagens( request );

			if ( Utilidades.isNuloOuVazio( meuForm.getSafra().getId() ) ) {
				throw new ApplicationException( "Nenhum parâmetro de pesquisa foi informado!" );
			}

			meuForm.getSinteticos().clear();

			SafraDTO safra = SafraFacade.getInstance().filtrarPorId( meuForm.getSafra().getId() );

			String areaConsultada = "0";
			if ( meuForm.getTipo().equals( "Tudo" ) ) {
				areaConsultada = safra.getAreaTotal();
				meuForm.setInformativoResultado( "A safra consultada corresponde a ".concat( areaConsultada ).concat( " alqueires." ) );
			} else if ( meuForm.getTipo().equals( "Safra/Setor" ) ) {
				areaConsultada = safra.getAreaSetor( meuForm.getSetor().getId() );
				meuForm.setInformativoResultado( "Área consultada corresponde a ".concat( areaConsultada ).concat( " alqueires de ".concat( safra.getCulturaSetor( meuForm.getSetor().getId() ) ) ) );
			} else if ( meuForm.getTipo().equals( "Cultura" ) ) {
				areaConsultada = safra.getAreaTotalCultura( meuForm.getCultura().getId() );
				meuForm.setInformativoResultado( "Área consultada corresponde a ".concat( areaConsultada ).concat( " alqueires de ".concat( safra.getCultura( meuForm.getCultura().getId() ) ) ) );
			} else {
				meuForm.setInformativoResultado( null );
				areaConsultada = "0";
			}

			List< SetorSafraDTO > setoresSafra = SetorFacade.getInstance().filtrarPorSafraSetorCultura( meuForm.getSafra().getId(), meuForm.getSetor().getId(), meuForm.getCultura().getId() );

			boolean jaFoi = false;
			for ( SetorSafraDTO setorSafraCorrente : setoresSafra ) {

				List< CustoDiretoInsumoPOJO > demonstrativos = null;
				if ( meuForm.getTipo().equals( "Tudo" ) ) {
					demonstrativos = DemonstrativoSafraFacade.getInstance().filtrarDemonstrativoSafraCustosDiretos( meuForm.getSafra().getId(), setorSafraCorrente.getSetor().getId(), null, "Insumo" );
				} else if ( meuForm.getTipo().equals( "Safra/Setor" ) ) {
					demonstrativos = DemonstrativoSafraFacade.getInstance().filtrarDemonstrativoSafraCustosDiretos( meuForm.getSafra().getId(), setorSafraCorrente.getSetor().getId(), null, "Insumo" );
				} else if ( meuForm.getTipo().equals( "Cultura" ) ) {
					demonstrativos = DemonstrativoSafraFacade.getInstance().filtrarDemonstrativoSafraCustosDiretos( meuForm.getSafra().getId(), setorSafraCorrente.getSetor().getId(), meuForm.getCultura().getId(), "Insumo" );
				}

				String areaSetor = safra.getAreaSetor( setorSafraCorrente.getSetor().getId() );
				for ( CustoDiretoInsumoPOJO corrente : demonstrativos ) {
					BigDecimal custoPorAlqueire = corrente.getCustoTotal().divide( Utilidades.parseBigDecimal( areaSetor ), Utilidades.SCALE_CALCULOS, BigDecimal.ROUND_HALF_EVEN );
					corrente.setCustoPorAlqueire( custoPorAlqueire );
				}

				for ( CustoDiretoInsumoSinteticoPOJO sinteticoCorrente : meuForm.getSinteticos() ) {
					if ( sinteticoCorrente.getSetor().equals( setorSafraCorrente.getSetor().getNomeCompleto() ) ) {
						sinteticoCorrente.setVariedade( sinteticoCorrente.getVariedade().concat( " / " ).concat( setorSafraCorrente.getVariedade().getNomeCompleto() ) );
						jaFoi = true;
					}
				}

				if ( !jaFoi ) {
					CustoDiretoInsumoSinteticoPOJO sintetico = meuForm.calcularTotaisSintetico( demonstrativos );
					sintetico.setInformativoResultado( "Área consultada corresponde a ".concat( areaSetor ).concat( " alqueires de ".concat( safra.getCulturaSetor( setorSafraCorrente.getSetor().getId() ) ) ) );
					sintetico.setSetor( setorSafraCorrente.getSetor().getNomeCompleto() );
					sintetico.setVariedade( setorSafraCorrente.getVariedade().getNomeCompleto() );
					sintetico.setAreaTotal( areaSetor );

					meuForm.getSinteticos().add( sintetico );
				}
			}

			meuForm.calcularTotais( meuForm.getSinteticos() );

			Collections.sort( meuForm.getSinteticos() );
		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( MensagensResourcesApplication.getString( "falha", new String[ ] { this.getClass().getSimpleName() + "-" + e.getMessage(), e.toString() } ) );
			e.printStackTrace();

			request.getSession().setAttribute( "erroAjax", e.getMessage() );
			request.getSession().removeAttribute( "sucessoAjax" );

		}

		return mapping.findForward( "custoDiretoSinteticoAjax" );
	}

	public ActionForward gerarPdfSintetico( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			DemonstrativoSafraSinteticoForm meuForm = this.getCastingForm( form, request );

			DemonstrativoSafraFacade.getInstance().gerarPdfSintetico( response, meuForm.getUsuarioSessaoPOJO( request ).getEmpresa(), meuForm.getSinteticos(), meuForm.getInformativoResultado(), meuForm.getTotalArea(), meuForm.getTotalPorAlqueire(), meuForm.getTotalPorArea() );

		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
		} catch ( Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			e.printStackTrace();
		}

		return null;
	}

}
