package br.com.srcsoftware.sistema.gerenciamento.demonstrativosafra;

import java.math.BigDecimal;
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
import br.com.srcsoftware.sistema.safra.SafraDTO;
import br.com.srcsoftware.sistema.safra.SafraFacade;
import br.com.srcsoftware.sistema.safra.cultura.CulturaDTO;
import br.com.srcsoftware.sistema.safra.cultura.CulturaFacade;
import br.com.srcsoftware.sistema.safra.setor.SetorDTO;
import br.com.srcsoftware.sistema.safra.setor.SetorFacade;

public final class DemonstrativoSafraAction extends AbstractUsuarioAction< DemonstrativoSafraForm >{

	public ActionForward abrirModalDadosExcel( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		return mapping.findForward( "excelTabelaModal" );
	}

	public ActionForward abrirPainelDemonstrativo( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			DemonstrativoSafraForm meuForm = this.getCastingForm( form, request );

			meuForm.limparMensagens( request );

			meuForm.setCultura( null );
			meuForm.getDemonstrativos().clear();
			meuForm.setSafra( null );
			meuForm.setSetor( null );
			meuForm.setTipo( null );

			meuForm.limparTudo();

			//meuForm.getSafra().setId( "1" );
			//meuForm.getSetor().setId( "1" );

			//return filtrarDemonstrativoSafraCustosDiretos( mapping, meuForm, request, response );
		} catch ( ApplicationException | Exception e ) {
			logger.error( e.getMessage(), e );
			e.printStackTrace();
			request.getSession().setAttribute( "erroAjax", e.getMessage() );
			request.getSession().removeAttribute( "sucessoAjax" );
		}

		return mapping.findForward( "custoDiretoAjax" );
	}

	public ActionForward filtrarDemonstrativoSafraCustosDiretos( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			DemonstrativoSafraForm meuForm = this.getCastingForm( form, request );

			meuForm.limparMensagens( request );

			if ( Utilidades.isNuloOuVazio( meuForm.getSafra().getId() ) && Utilidades.isNuloOuVazio( meuForm.getSetor().getId() ) && Utilidades.isNuloOuVazio( meuForm.getCultura().getId() ) ) {
				throw new ApplicationException( "Nenhum parâmetro de pesquisa foi informado!" );
			}

			List< CustoDiretoInsumoPOJO > demonstrativos = DemonstrativoSafraFacade.getInstance().filtrarDemonstrativoSafraCustosDiretos( meuForm.getSafra().getId(), meuForm.getSetor().getId(), meuForm.getCultura().getId(), "Insumo" );

			meuForm.getDemonstrativos().clear();
			meuForm.setDemonstrativos( demonstrativos );

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

			//meuForm.setInformativoResultado( meuForm.getInformativoResultado().concat( "<p>" ).concat( "Total contultado: R$ " ).concat( meuForm.getCustoTotalEncontrado() ).concat( "</p>" ) );

			for ( CustoDiretoInsumoPOJO corrente : meuForm.getDemonstrativos() ) {
				BigDecimal custoPorAlqueire = corrente.getCustoTotal().divide( Utilidades.parseBigDecimal( areaConsultada ), Utilidades.SCALE_CALCULOS, BigDecimal.ROUND_HALF_EVEN );
				corrente.setCustoPorAlqueire( custoPorAlqueire );
			}

			meuForm.calcularTotais( demonstrativos );

		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( MensagensResourcesApplication.getString( "falha", new String[ ] { this.getClass().getSimpleName() + "-" + e.getMessage(), e.toString() } ) );
			e.printStackTrace();

			request.getSession().setAttribute( "erroAjax", e.getMessage() );
			request.getSession().removeAttribute( "sucessoAjax" );

		}

		return mapping.findForward( "custoDiretoAjax" );
	}

	@SuppressWarnings( "unchecked" )
	public ActionForward selecionarSafraAutoComplete( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			DemonstrativoSafraForm meuForm = this.getCastingForm( form, request );

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
			DemonstrativoSafraForm meuForm = this.getCastingForm( form, request );

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
			DemonstrativoSafraForm meuForm = this.getCastingForm( form, request );

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

}
