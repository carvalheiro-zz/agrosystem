package br.com.srcsoftware.sistema.estoque.estoque;

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
import br.com.srcsoftware.modular.manager.view.utilidades.Messages;
import br.com.srcsoftware.sistema.estoque.estoque.pojo.EstoquePOJO;
import br.com.srcsoftware.sistema.produto.marca.MarcaDTO;
import br.com.srcsoftware.sistema.produto.marca.MarcaFacade;
import br.com.srcsoftware.sistema.produto.produto.ProdutoDTO;
import br.com.srcsoftware.sistema.produto.produto.ProdutoFacade;
import br.com.srcsoftware.sistema.produto.tipo.TipoDTO;
import br.com.srcsoftware.sistema.produto.tipo.TipoFacade;

public class EstoqueAction extends AbstractUsuarioAction< EstoqueForm >{

	public ActionForward abrirListagem( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {

			EstoqueForm meuForm = this.getCastingForm( form, request );

			meuForm.limparLista();
			meuForm.limparCamposConsulta();

		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
		} catch ( Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			e.printStackTrace();
		}

		return this.filtrar( mapping, form, request, response );
	}

	public ActionForward filtrar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			EstoqueForm meuForm = this.getCastingForm( form, request );

			meuForm.getPaginacao().inicializar();

			List< EstoquePOJO > encontrados = EstoqueFacade.getInstance().filtrar( meuForm.getPaginacao(), meuForm.getIdProduto(), meuForm.getNomeProduto(), meuForm.getLocalizacaoProduto(), meuForm.getIdMarca(), meuForm.getIdTipo(), meuForm.getNegativos() );

			meuForm.limparLista();

			meuForm.getEstoques().addAll( encontrados );

		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
		} catch ( Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			e.printStackTrace();
		}

		return mapping.findForward( "estoqueLista" );
	}

	public ActionForward limparFiltro( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			EstoqueForm meuForm = this.getCastingForm( form, request );

			meuForm.limparLista();
			meuForm.limparCamposConsulta();

		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
		} catch ( Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			e.printStackTrace();
		}

		return this.filtrar( mapping, form, request, response );
	}

	@SuppressWarnings( "unchecked" )
	public ActionForward selecionarProdutoAutoComplete( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			EstoqueForm meuForm = this.getCastingForm( form, request );

			ProdutoDTO produtoFiltrar = new ProdutoDTO();
			produtoFiltrar.setNome( "%".concat( meuForm.getNomeProduto() ) );

			HashMap< String, String > camposOrdenacao = new HashMap<>();
			camposOrdenacao.put( "nome", "ASC" );

			List< ProdutoDTO > encontrados = ProdutoFacade.getInstance().filtrar( Paginacao.NAO, camposOrdenacao, produtoFiltrar );

			JSONObject json = new JSONObject();
			JSONObject map = null;
			JSONArray arr = new JSONArray();

			for ( ProdutoDTO dtoCorrente : encontrados ) {

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
		} catch ( Throwable e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { "Erro inesperado![" + e.getMessage() + "]" } ) );
			logger.error( MensagensResourcesApplication.getString( "log.erro.inesperado", new String[ ] { this.getClass().getSimpleName() + " [inserir]", e.toString() } ) );
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings( "unchecked" )
	public ActionForward selecionarMarcaAutoComplete( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			EstoqueForm meuForm = this.getCastingForm( form, request );

			MarcaDTO produtoFiltrar = new MarcaDTO();
			produtoFiltrar.setNome( "%".concat( meuForm.getNomeMarca() ) );

			HashMap< String, String > camposOrdenacao = new HashMap<>();
			camposOrdenacao.put( "nome", "ASC" );

			List< MarcaDTO > encontrados = MarcaFacade.getInstance().filtrar( Paginacao.NAO, camposOrdenacao, produtoFiltrar );

			JSONObject json = new JSONObject();
			JSONObject map = null;
			JSONArray arr = new JSONArray();

			for ( MarcaDTO dtoCorrente : encontrados ) {

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
		} catch ( Throwable e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { "Erro inesperado![" + e.getMessage() + "]" } ) );
			logger.error( MensagensResourcesApplication.getString( "log.erro.inesperado", new String[ ] { this.getClass().getSimpleName() + " [inserir]", e.toString() } ) );
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings( "unchecked" )
	public ActionForward selecionarTipoAutoComplete( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			EstoqueForm meuForm = this.getCastingForm( form, request );

			TipoDTO produtoFiltrar = new TipoDTO();
			produtoFiltrar.setNome( "%".concat( meuForm.getNomeTipo() ) );

			HashMap< String, String > camposOrdenacao = new HashMap<>();
			camposOrdenacao.put( "nome", "ASC" );

			List< TipoDTO > encontrados = TipoFacade.getInstance().filtrar( Paginacao.NAO, camposOrdenacao, produtoFiltrar );

			JSONObject json = new JSONObject();
			JSONObject map = null;
			JSONArray arr = new JSONArray();

			for ( TipoDTO dtoCorrente : encontrados ) {

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
		} catch ( Throwable e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { "Erro inesperado![" + e.getMessage() + "]" } ) );
			logger.error( MensagensResourcesApplication.getString( "log.erro.inesperado", new String[ ] { this.getClass().getSimpleName() + " [inserir]", e.toString() } ) );
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * M�todo respons�vel pelo funcionamento da Paginação
	 */
	public ActionForward paginar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			EstoqueForm meuForm = this.getCastingForm( form, request );

			List< EstoquePOJO > encontrados = EstoqueFacade.getInstance().filtrar( meuForm.getPaginacao(), meuForm.getIdProduto(), meuForm.getNomeProduto(), meuForm.getLocalizacaoProduto(), meuForm.getIdMarca(), meuForm.getIdTipo(), meuForm.getNegativos() );

			meuForm.limparLista();

			meuForm.getEstoques().addAll( encontrados );

		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
		} catch ( Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			e.printStackTrace();
		}

		return mapping.findForward( "estoqueLista" );
	}

	/**
	 * M�todo respons�vel pelo funcionamento da Paginação
	 */
	@Override
	public ActionForward avancar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		super.avancar( mapping, form, request, response );
		return this.paginar( mapping, form, request, response );
	}

	/**
	 * M�todo respons�vel pelo funcionamento da Paginação
	 */
	@Override
	public ActionForward voltar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		super.voltar( mapping, form, request, response );
		return this.paginar( mapping, form, request, response );
	}
}
