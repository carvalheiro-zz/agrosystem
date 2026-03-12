package br.com.srcsoftware.sistema.silo.ajuste;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import br.com.srcsoftware.modular.manager.abstracts.AbstractCRUDAction;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.pessoa.funcionario.FuncionarioDTO;
import br.com.srcsoftware.modular.manager.pessoa.funcionario.FuncionarioFacade;
import br.com.srcsoftware.modular.manager.utilidades.MensagensResourcesApplication;
import br.com.srcsoftware.modular.manager.view.utilidades.Messages;
import br.com.srcsoftware.sistema.safra.SafraDTO;
import br.com.srcsoftware.sistema.safra.SafraFacade;
import br.com.srcsoftware.sistema.safra.cultura.CulturaDTO;
import br.com.srcsoftware.sistema.safra.cultura.CulturaFacade;

public final class AjusteSiloAction extends AbstractCRUDAction< AjusteSiloForm >{

	@Override
	public ActionForward abrirListagem( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {

			AjusteSiloForm meuForm = this.getCastingForm( form, request );

			//String tipo = meuForm.getAjusteSilo().getTipo();

			meuForm.limparTudo( request );

			//meuForm.getAjusteSilo().setTipo( tipo );

			meuForm.getPaginacao().inicializar();

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

	@Override
	public ActionForward abrirCadastro( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			AjusteSiloForm meuForm = this.getCastingForm( form, request );

			//String tipo = meuForm.getAjusteSilo().getTipo();

			meuForm.limparTudo( request );

			//meuForm.getAjusteSilo().setTipo( tipo );

			meuForm.getPaginacao().inicializar();

			return mapping.findForward( "ajusteSiloCampos" );
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

	@Override
	public ActionForward inserir( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			AjusteSiloForm meuForm = this.getCastingForm( form, request );

			AjusteSiloDTO dtoPersistir = meuForm.getAjusteSilo();

			AjusteSiloFacade.getInstance().inserir( meuForm.getUsuarioSessaoPOJO( request ), dtoPersistir );

			meuForm.limparTudo( request );

			this.addMessages( request, Messages.createMessages( "sucesso" ) );

		} catch ( ApplicationException | Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
		}

		return mapping.findForward( "ajusteSiloCampos" );
	}

	@Override
	public ActionForward alterar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			AjusteSiloForm meuForm = this.getCastingForm( form, request );

			AjusteSiloDTO dtoPersistir = meuForm.getAjusteSilo();

			AjusteSiloFacade.getInstance().alterar( meuForm.getUsuarioSessaoPOJO( request ), dtoPersistir );

			meuForm.limparTudo( request );

			this.addMessages( request, Messages.createMessages( "sucesso" ) );

		} catch ( ApplicationException | Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
		}
		return mapping.findForward( "ajusteSiloCampos" );
	}

	@Override
	public ActionForward excluir( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			AjusteSiloForm meuForm = this.getCastingForm( form, request );

			AjusteSiloFacade.getInstance().excluir( AjusteSiloFacade.getInstance().filtrarPorId( meuForm.getIdRegistroExcluir() ) );

			this.addMessages( request, Messages.createMessages( "sucesso" ) );

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

	@Override
	public ActionForward filtrar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			AjusteSiloForm meuForm = this.getCastingForm( form, request );

			meuForm.getPaginacao().inicializar();

			HashMap< String, String > camposOrdenacao = new HashMap<>();
			camposOrdenacao.put( "data", "DESC" );

			List< AjusteSiloDTO > encontrados = AjusteSiloFacade.getInstance().filtrar( meuForm.getPaginacao(), camposOrdenacao, meuForm.getAjusteSilo(), meuForm.getDataInicial(), meuForm.getDataFinal() );

			meuForm.limparLista();

			meuForm.getAjustes().addAll( encontrados );

		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
		} catch ( Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			e.printStackTrace();
		}

		return mapping.findForward( "ajusteSiloLista" );
	}

	@Override
	public ActionForward selecionar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			AjusteSiloForm meuForm = this.getCastingForm( form, request );

			AjusteSiloDTO encontrado = AjusteSiloFacade.getInstance().filtrarPorId( meuForm.getAjusteSilo().getId() );

			meuForm.limparTudo( request );

			meuForm.setAjusteSilo( encontrado );

		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
			return mapping.findForward( "ajusteSiloLista" );
		} catch ( Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			e.printStackTrace();
			return mapping.findForward( "ajusteSiloLista" );
		}

		return mapping.findForward( "ajusteSiloCampos" );
	}

	@Override
	public ActionForward limpar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			AjusteSiloForm meuForm = this.getCastingForm( form, request );

			meuForm.limparTudo( request );

		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
		} catch ( Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			e.printStackTrace();
		}

		return mapping.findForward( "ajusteSiloCampos" );
	}

	@Override
	public ActionForward limparFiltro( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			AjusteSiloForm meuForm = this.getCastingForm( form, request );

			meuForm.limparTudo( request );

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

	/**
	 * M�todo respons�vel pelo funcionamento da Paginação
	 */
	public ActionForward paginar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			AjusteSiloForm meuForm = this.getCastingForm( form, request );

			HashMap< String, String > camposOrdenacao = new HashMap<>();
			camposOrdenacao.put( "data", "DESC" );

			List< AjusteSiloDTO > encontrados = AjusteSiloFacade.getInstance().filtrar( meuForm.getPaginacao(), camposOrdenacao, meuForm.getAjusteSilo(), meuForm.getDataInicial(), meuForm.getDataFinal() );

			meuForm.limparLista();

			meuForm.getAjustes().addAll( encontrados );

		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
		} catch ( Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			e.printStackTrace();
		}

		return mapping.findForward( "ajusteSiloLista" );
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

	@SuppressWarnings( "unchecked" )
	public ActionForward selecionarLancadorAutoComplete( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			AjusteSiloForm meuForm = this.getCastingForm( form, request );

			FuncionarioDTO filtrar = new FuncionarioDTO();
			filtrar.getPessoaFisica().setRazaoSocial( meuForm.getAjusteSilo().getLancador().getPessoaFisica().getRazaoSocial() );

			HashMap< String, String > camposOrdenacao = new HashMap<>();
			camposOrdenacao.put( "pessoaFisica.razaoSocial", "ASC" );

			List< FuncionarioDTO > encontrados = FuncionarioFacade.getInstance().filtrar( Paginacao.NAO, camposOrdenacao, filtrar );

			JSONObject json = new JSONObject();
			JSONObject map = null;
			JSONArray arr = new JSONArray();

			for ( FuncionarioDTO dtoCorrente : encontrados ) {

				map = new JSONObject();
				map.put( "value", dtoCorrente.getPessoaFisica().getRazaoSocial() );
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
	public ActionForward selecionarSafraAutoComplete( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			AjusteSiloForm meuForm = this.getCastingForm( form, request );

			SafraDTO pesquisar = new SafraDTO();
			pesquisar.setNome( meuForm.getAjusteSilo().getSafra().getNome() );

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
		} catch ( Throwable e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { "Erro inesperado![" + e.getMessage() + "]" } ) );
			logger.error( MensagensResourcesApplication.getString( "log.erro.inesperado", new String[ ] { this.getClass().getSimpleName() + " [inserir]", e.toString() } ) );
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings( "unchecked" )
	public ActionForward selecionarCulturaAutoComplete( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			AjusteSiloForm meuForm = this.getCastingForm( form, request );

			List< CulturaDTO > encontrados = CulturaFacade.getInstance().filtrarPorSafraSetor( meuForm.getAjusteSilo().getSafra().getId(), null, meuForm.getAjusteSilo().getCultura().getNome(), null );

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
		} catch ( Throwable e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { "Erro inesperado![" + e.getMessage() + "]" } ) );
			logger.error( MensagensResourcesApplication.getString( "log.erro.inesperado", new String[ ] { this.getClass().getSimpleName() + " [inserir]", e.toString() } ) );
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward calcularLiquidoSacas( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			AjusteSiloForm meuForm = this.getCastingForm( form, request );

			JSONObject json = new JSONObject();

			json = new JSONObject();
			json.put( "quantidade", meuForm.getAjusteSilo().getQuantidade() );
			json.put( "emSacas", meuForm.getAjusteSilo().getEmSacas() );

			response.setContentType( "application/json" );
			response.setHeader( "Cache-Control", "nocache" );
			response.getWriter().print( json.toString() );

		} catch ( Throwable e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { "Erro inesperado![" + e.getMessage() + "]" } ) );
			logger.error( MensagensResourcesApplication.getString( "log.erro.inesperado", new String[ ] { this.getClass().getSimpleName() + " [inserir]", e.toString() } ) );
			e.printStackTrace();
		}
		return null;
	}

}
