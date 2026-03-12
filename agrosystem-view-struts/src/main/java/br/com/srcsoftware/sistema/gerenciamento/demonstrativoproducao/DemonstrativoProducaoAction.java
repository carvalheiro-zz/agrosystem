package br.com.srcsoftware.sistema.gerenciamento.demonstrativoproducao;

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

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.abstracts.AbstractUsuarioAction;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.utilidades.MensagensResourcesApplication;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.modular.manager.view.utilidades.Messages;
import br.com.srcsoftware.sistema.demonstrativoproducao.DemonstrativoProducaoFacade;
import br.com.srcsoftware.sistema.demonstrativovenda.DemonstrativoVendaFacade;
import br.com.srcsoftware.sistema.safra.SafraDTO;
import br.com.srcsoftware.sistema.safra.SafraFacade;
import br.com.srcsoftware.sistema.safra.cultura.CulturaDTO;
import br.com.srcsoftware.sistema.safra.cultura.CulturaFacade;
import br.com.srcsoftware.sistema.safra.cultura.variedade.VariedadeDTO;
import br.com.srcsoftware.sistema.safra.cultura.variedade.VariedadeFacade;
import br.com.srcsoftware.sistema.safra.setor.SetorDTO;
import br.com.srcsoftware.sistema.safra.setor.SetorFacade;
import br.com.srcsoftware.sistema.silo.contratovenda.InformacoesRestanteEntregarPOJO;
import br.com.srcsoftware.sistema.silo.entrada.InformacoesProducaoSafraPOJO;
import br.com.srcsoftware.sistema.silo.silo.InformacoesSiloPOJO;
import br.com.srcsoftware.sistema.silo.silo.SiloDTO;
import br.com.srcsoftware.sistema.silo.silo.SiloFacade;

public final class DemonstrativoProducaoAction extends AbstractUsuarioAction< DemonstrativoProducaoForm >{

	public ActionForward abrirPainelDemonstrativoProducao( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			DemonstrativoProducaoForm meuForm = this.getCastingForm( form, request );

			meuForm.limparMensagens( request );

			meuForm.limparTudo();

			calcularSaldoSilosSafras( meuForm );

		} catch ( ApplicationException | Exception e ) {
			logger.error( e.getMessage(), e );
			e.printStackTrace();
			request.getSession().setAttribute( "erroAjax", e.getMessage() );
			request.getSession().removeAttribute( "sucessoAjax" );
		}

		return mapping.findForward( "demonstrativoProducaoAjax" );
	}

	public ActionForward filtrarDemonstrativoProducao( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			DemonstrativoProducaoForm meuForm = this.getCastingForm( form, request );

			List< InformacoesProducaoSafraPOJO > encontrados = DemonstrativoProducaoFacade.getInstance().filtrarSaldoProducao( meuForm.getSafra().getId(), meuForm.getSetor().getId(), meuForm.getCultura().getId(), meuForm.getVariedade().getId() );

			meuForm.setDemonstrativos( encontrados );

		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( MensagensResourcesApplication.getString( "falha", new String[ ] { this.getClass().getSimpleName() + "-" + e.getMessage(), e.toString() } ) );
			e.printStackTrace();

			request.getSession().setAttribute( "erroAjax", e.getMessage() );
			request.getSession().removeAttribute( "sucessoAjax" );

		}

		return mapping.findForward( "demonstrativoProducaoAjax" );
	}

	@SuppressWarnings( "unchecked" )
	public ActionForward selecionarSafraAutoComplete( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			DemonstrativoProducaoForm meuForm = this.getCastingForm( form, request );

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
			DemonstrativoProducaoForm meuForm = this.getCastingForm( form, request );

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
			DemonstrativoProducaoForm meuForm = this.getCastingForm( form, request );

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

	@SuppressWarnings( "unchecked" )
	public ActionForward selecionarVariedadeAutoComplete( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			DemonstrativoProducaoForm meuForm = this.getCastingForm( form, request );

			meuForm.limparMensagens( request );

			List< VariedadeDTO > encontrados = VariedadeFacade.getInstance().filtrarPorSafraSetor( meuForm.getSafra().getId(), meuForm.getSetor().getId(), meuForm.getVariedade().getNome() );

			JSONObject json = new JSONObject();
			JSONObject map = null;
			JSONArray arr = new JSONArray();

			for ( VariedadeDTO dtoCorrente : encontrados ) {

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

	public void calcularSaldoSilosSafras( DemonstrativoProducaoForm meuForm ) throws ApplicationException {

		meuForm.getSilos().clear();
		List< SiloDTO > silos = SiloFacade.getInstance().filtrar( Paginacao.NAO, HibernateConnection.SEM_ORDENACAO, null );

		meuForm.getSafras().clear();
		List< SafraDTO > safras = SafraFacade.getInstance().filtrar( Paginacao.NAO, HibernateConnection.SEM_ORDENACAO, null );

		meuForm.getRestantes().clear();

		List< CulturaDTO > culturas = CulturaFacade.getInstance().filtrar( Paginacao.NAO, HibernateConnection.SEM_ORDENACAO, null );

		for ( CulturaDTO culturaCorrente : culturas ) {

			for ( SiloDTO siloCorrente : silos ) {

				//VariedadeDTO variedadeSelecionada = VariedadeFacade.getInstance().filtrarPorId( meuForm.getEntradaGrao().getVariedade().getId() );				
				InformacoesSiloPOJO encontrado = SiloFacade.getInstance().filtrarInformacoesSilo( siloCorrente.getId(), culturaCorrente.getId() );

				if ( encontrado != null ) {
					if ( Utilidades.parseBigDecimal( encontrado.getSaldoSilo() ).compareTo( BigDecimal.ZERO ) == 0 ) {
						continue;
					}
					meuForm.getSilos().add( encontrado );
				}

			}
			Collections.sort( meuForm.getSilos() );

			for ( SafraDTO safraCorrente : safras ) {

				//VariedadeDTO variedadeSelecionada = VariedadeFacade.getInstance().filtrarPorId( meuForm.getEntradaGrao().getVariedade().getId() );

				InformacoesProducaoSafraPOJO encontrado = SafraFacade.getInstance().filtrarSaldoProducaoSafra( safraCorrente.getId(), culturaCorrente.getId() );
				if ( encontrado != null ) {
					if ( Utilidades.parseBigDecimal( encontrado.getProducao() ).compareTo( BigDecimal.ZERO ) == 0 ) {
						continue;
					}
					meuForm.getSafras().add( encontrado );
				}
			}

			Collections.sort( meuForm.getSafras() );
			Collections.reverse( meuForm.getSafras() );

			/*ContratoVendaDTO contratoVenda = new ContratoVendaDTO();
			contratoVenda.getCultura().setId( culturaCorrente.getId() );
			List< ContratoVendaDTO > contratos = ContratoVendaFacade.getInstance().filtrar( Paginacao.NAO, HibernateConnection.SEM_ORDENACAO, contratoVenda, null, null );
			BigDecimal somaCultura = BigDecimal.ZERO;
			for ( ContratoVendaDTO contratoCorrente : contratos ) {
				BigDecimal restante = Utilidades.parseBigDecimal( contratoCorrente.getQuantidadeRestante() );
				somaCultura = somaCultura.add( restante );
			}
			if ( somaCultura.compareTo( BigDecimal.ZERO ) != 0 ) {
				InformacoesRestanteEntregarPOJO restante = new InformacoesRestanteEntregarPOJO();
				restante.setCultura( culturaCorrente.getNome() );
				restante.setQuantidadeEntregar( Utilidades.parseBigDecimal( somaCultura ) );
			
				meuForm.getRestantes().add( restante );
			}*/

			List< InformacoesRestanteEntregarPOJO > encontrados = DemonstrativoVendaFacade.getInstance().filtrarDemonstrativoVenda( culturaCorrente.getId() );
			for ( InformacoesRestanteEntregarPOJO restanteCorrente : encontrados ) {
				System.out.println( restanteCorrente );

				meuForm.getRestantes().add( restanteCorrente );
			}

		}

	}

	public ActionForward gerarPDF( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			DemonstrativoProducaoForm meuForm = this.getCastingForm( form, request );

			DemonstrativoProducaoFacade.getInstance().gerarPDF( response, meuForm.getUsuarioSessaoPOJO( request ).getEmpresa().getId(), meuForm.getDemonstrativos(), meuForm.getSafra().getNome(), meuForm.getSetor().getNome(), meuForm.getCultura().getNome(), meuForm.getVariedade().getNome() );

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
