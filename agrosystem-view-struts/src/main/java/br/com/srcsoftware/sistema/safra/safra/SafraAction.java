package br.com.srcsoftware.sistema.safra.safra;

import java.text.ParseException;
import java.time.LocalDate;
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
import br.com.srcsoftware.modular.manager.utilidades.DateUtil;
import br.com.srcsoftware.modular.manager.utilidades.MensagensResourcesApplication;
import br.com.srcsoftware.modular.manager.view.utilidades.Messages;
import br.com.srcsoftware.sistema.safra.SafraDTO;
import br.com.srcsoftware.sistema.safra.SafraFacade;
import br.com.srcsoftware.sistema.safra.cultura.CulturaDTO;
import br.com.srcsoftware.sistema.safra.cultura.CulturaFacade;
import br.com.srcsoftware.sistema.safra.cultura.variedade.VariedadeDTO;
import br.com.srcsoftware.sistema.safra.cultura.variedade.VariedadeFacade;
import br.com.srcsoftware.sistema.safra.setor.SetorDTO;
import br.com.srcsoftware.sistema.safra.setor.SetorFacade;
import br.com.srcsoftware.sistema.safra.setorsafra.SetorSafraDTO;

public final class SafraAction extends AbstractCRUDAction< SafraForm > {
	
	@Override
	public ActionForward abrirListagem( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			
			SafraForm meuForm = this.getCastingForm( form, request );
			
			meuForm.limparTudo( request );
			
			meuForm.getPaginacao().inicializar();
			
		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
		} catch ( Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ e.getMessage() } ) );
			logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			e.printStackTrace();
		}
		
		return this.filtrar( mapping, form, request, response );
	}
	
	@Override
	public ActionForward abrirCadastro( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			SafraForm meuForm = this.getCastingForm( form, request );
			
			meuForm.limparTudo( request );
			
			meuForm.getPaginacao().inicializar();
			
			return mapping.findForward( "safraCampos" );
		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
		} catch ( Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ e.getMessage() } ) );
			logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			e.printStackTrace();
		}
		
		return this.filtrar( mapping, form, request, response );
	}
	
	@Override
	public ActionForward inserir( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			SafraForm meuForm = this.getCastingForm( form, request );
			
			SafraDTO dtoPersistir = meuForm.getSafra();
			
			SafraFacade.getInstance().inserir( meuForm.getUsuarioSessaoPOJO( request ), dtoPersistir );
			
			meuForm.limparTudo( request );
			
			this.addMessages( request, Messages.createMessages( "sucesso" ) );
			request.getSession().setAttribute( "sucessoAjax", "Operação realizada com sucesso!" );
			
		} catch ( ApplicationException | Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
			request.getSession().setAttribute( "erroAjax", e.getMessage() );
			request.getSession().removeAttribute( "sucessoAjax" );
		}
		
		return mapping.findForward( "safraCampos" );
	}
	
	@Override
	public ActionForward alterar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			SafraForm meuForm = this.getCastingForm( form, request );
			
			SafraDTO dtoPersistir = meuForm.getSafra();
			
			SafraFacade.getInstance().alterar( meuForm.getUsuarioSessaoPOJO( request ), dtoPersistir );
			
			meuForm.limparTudo( request );
			
			this.addMessages( request, Messages.createMessages( "sucesso" ) );
			
		} catch ( ApplicationException | Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
			request.getSession().setAttribute( "erroAjax", e.getMessage() );
			request.getSession().removeAttribute( "sucessoAjax" );
		}
		return mapping.findForward( "safraCampos" );
	}
	
	@Override
	public ActionForward excluir( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			SafraForm meuForm = this.getCastingForm( form, request );
			
			SafraFacade.getInstance().excluir( SafraFacade.getInstance().filtrarPorId( meuForm.getIdRegistroExcluir() ) );
			
			// meuForm.limparTudo( request );
			
			this.addMessages( request, Messages.createMessages( "sucesso" ) );
			
		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
		} catch ( Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ e.getMessage() } ) );
			logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			e.printStackTrace();
		}
		return this.filtrar( mapping, form, request, response );
	}
	
	@Override
	public ActionForward filtrar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			SafraForm meuForm = this.getCastingForm( form, request );
			
			meuForm.getSafra().getSetoresSafras().clear();
			
			SetorSafraDTO setorSafra = new SetorSafraDTO();
			setorSafra.getSetor().setId( meuForm.getSetorSafra().getSetor().getId() );
			setorSafra.getVariedade().setId( meuForm.getSetorSafra().getVariedade().getId() );
			
			meuForm.getSafra().getSetoresSafras().add( meuForm.getSetorSafra() );
			
			meuForm.getPaginacao().inicializar();
			
			HashMap< String, String > camposOrdenacao = new HashMap<>();
			camposOrdenacao.put( "safra.nome", "ASC" );
			
			List< SafraDTO > encontrados = SafraFacade.getInstance().filtrar( meuForm.getPaginacao(), camposOrdenacao, meuForm.getSafra() );
			
			meuForm.limparLista();
			
			for ( SafraDTO safraCorrente : encontrados ) {
				String datas = this.getDataInicioFimSafraToString( safraCorrente.getId() );
				safraCorrente.setDataInicioFim( datas );
			}
			
			meuForm.getSafras().addAll( encontrados );
			
		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
		} catch ( Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ e.getMessage() } ) );
			logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			e.printStackTrace();
		}
		
		return mapping.findForward( "safraLista" );
	}
	
	@Override
	public ActionForward selecionar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			SafraForm meuForm = this.getCastingForm( form, request );
			
			SafraDTO encontrado = SafraFacade.getInstance().filtrarPorId( meuForm.getSafra().getId() );
			
			meuForm.limparTudo( request );
			
			String datas = this.getDataInicioFimSafraToString( encontrado.getId() );
			encontrado.setDataInicioFim( datas );
			
			meuForm.setSafra( encontrado );
			
		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
			return mapping.findForward( "safraLista" );
		} catch ( Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ e.getMessage() } ) );
			logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			e.printStackTrace();
			return mapping.findForward( "safraLista" );
		}
		
		return mapping.findForward( "safraCampos" );
	}
	
	private String getDataInicioFimSafraToString( String idSafra ) throws ApplicationException, ParseException {
		LocalDate[] datas = SafraFacade.getInstance().getDataInicioFimSafra( idSafra );
		
		String dataInicio = DateUtil.parseLocalDate( datas[0] );
		String dataTermino = DateUtil.parseLocalDate( datas[1] );
		
		return "In�cio: " + ( dataInicio == null ? "__/__/____" : dataInicio ) + " T�rmino: " + ( dataTermino == null ? "__/__/____" : dataTermino );
	}
	
	@Override
	public ActionForward limpar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			SafraForm meuForm = this.getCastingForm( form, request );
			
			meuForm.limparTudo( request );
			
		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
		} catch ( Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ e.getMessage() } ) );
			logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			e.printStackTrace();
		}
		
		return mapping.findForward( "safraCampos" );
	}
	
	@Override
	public ActionForward limparFiltro( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			SafraForm meuForm = this.getCastingForm( form, request );
			
			meuForm.limparTudo( request );
			
		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
		} catch ( Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ e.getMessage() } ) );
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
			SafraForm meuForm = this.getCastingForm( form, request );
			
			meuForm.getSafra().getSetoresSafras().clear();
			
			meuForm.getSafra().getSetoresSafras().add( meuForm.getSetorSafra() );
			
			HashMap< String, String > camposOrdenacao = new HashMap<>();
			camposOrdenacao.put( "safra.nome", "ASC" );
			
			List< SafraDTO > encontrados = SafraFacade.getInstance().filtrar( meuForm.getPaginacao(), camposOrdenacao, meuForm.getSafra() );
			
			meuForm.limparLista();
			
			for ( SafraDTO safraCorrente : encontrados ) {
				String datas = this.getDataInicioFimSafraToString( safraCorrente.getId() );
				safraCorrente.setDataInicioFim( datas );
			}
			
			meuForm.getSafras().addAll( encontrados );
			
		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
		} catch ( Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ e.getMessage() } ) );
			logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			e.printStackTrace();
		}
		
		return mapping.findForward( "safraLista" );
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
	
	@SuppressWarnings("unchecked")
	public ActionForward selecionarVariedadeAutoComplete( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		
		try {
			SafraForm meuForm = this.getCastingForm( form, request );
			
			VariedadeDTO pesquisar = new VariedadeDTO();
			pesquisar.setNomeCompleto( meuForm.getSetorSafra().getVariedade().getNomeCompleto() );
			
			HashMap< String, String > camposOrdenacao = new HashMap<>();
			camposOrdenacao.put( "nome", "ASC" );
			
			List< VariedadeDTO > encontrados = VariedadeFacade.getInstance().filtrar( Paginacao.NAO, camposOrdenacao, pesquisar );
			
			JSONObject json = new JSONObject();
			JSONObject map = null;
			JSONArray arr = new JSONArray();
			
			for ( VariedadeDTO dtoCorrente : encontrados ) {
				
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
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ e.getMessage() } ) );
			logger.error( MensagensResourcesApplication.getString( "falha", new String[]{ this.getClass().getSimpleName() + "-" + e.getMessage(), e.toString() } ) );
			e.printStackTrace();
		} catch ( Throwable e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ "Erro inesperado![" + e.getMessage() + "]" } ) );
			logger.error( MensagensResourcesApplication.getString( "log.erro.inesperado", new String[]{ this.getClass().getSimpleName() + " [inserir]", e.toString() } ) );
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward selecionarCulturaAutoComplete( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		
		try {
			SafraForm meuForm = this.getCastingForm( form, request );
			
			CulturaDTO pesquisar = new CulturaDTO();
			pesquisar.setNome( meuForm.getSetorSafra().getVariedade().getCultura().getNome() );
			
			HashMap< String, String > camposOrdenacao = new HashMap<>();
			camposOrdenacao.put( "nome", "ASC" );
			
			List< CulturaDTO > encontrados = CulturaFacade.getInstance().filtrar( Paginacao.NAO, camposOrdenacao, pesquisar );
			
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
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ e.getMessage() } ) );
			logger.error( MensagensResourcesApplication.getString( "falha", new String[]{ this.getClass().getSimpleName() + "-" + e.getMessage(), e.toString() } ) );
			e.printStackTrace();
		} catch ( Throwable e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ "Erro inesperado![" + e.getMessage() + "]" } ) );
			logger.error( MensagensResourcesApplication.getString( "log.erro.inesperado", new String[]{ this.getClass().getSimpleName() + " [inserir]", e.toString() } ) );
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward selecionarSetorAutoComplete( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		
		try {
			SafraForm meuForm = this.getCastingForm( form, request );
			
			SetorDTO pesquisar = new SetorDTO();
			pesquisar.setNomeCompleto( meuForm.getSetorSafra().getSetor().getNomeCompleto() );
			
			HashMap< String, String > camposOrdenacao = new HashMap<>();
			camposOrdenacao.put( "nomeCompleto", "ASC" );
			
			List< SetorDTO > encontrados = SetorFacade.getInstance().filtrar( Paginacao.NAO, camposOrdenacao, pesquisar );
			
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
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ e.getMessage() } ) );
			logger.error( MensagensResourcesApplication.getString( "falha", new String[]{ this.getClass().getSimpleName() + "-" + e.getMessage(), e.toString() } ) );
			e.printStackTrace();
		} catch ( Throwable e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ "Erro inesperado![" + e.getMessage() + "]" } ) );
			logger.error( MensagensResourcesApplication.getString( "log.erro.inesperado", new String[]{ this.getClass().getSimpleName() + " [inserir]", e.toString() } ) );
			e.printStackTrace();
		}
		return null;
	}
	
	// ############### adicionar e remover Setores #######################
	public ActionForward adicionarSetores( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			
			SafraForm meuForm = this.getCastingForm( form, request );
			
			SafraFacade.getInstance().adicionarSetor( meuForm.getSafra().getSetoresSafras(), meuForm.getSetorSafra().getSetor().getId(), meuForm.getSetorSafra().getSetor().getNome(),
				meuForm.getSetorSafra().getArea(), meuForm.getSetorSafra().getVariedade() );
			
			meuForm.setSetorSafra( null );
			
		} catch ( Throwable e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ "Erro inesperado![" + e.getMessage() + "]" } ) );
			logger.error( MensagensResourcesApplication.getString( "log.erro.inesperado", new String[]{ this.getClass().getSimpleName() + " [inserir]", e.toString() } ) );
			e.printStackTrace();
		}
		
		return mapping.findForward( "safraCampos" );
	}
	
	public ActionForward removerSetores( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			
			SafraForm meuForm = this.getCastingForm( form, request );
			
			SafraFacade.getInstance().removerSetor( meuForm.getSafra().getSetoresSafras(), meuForm.getSetorSafra().getIdTemp() );
			
			meuForm.setSetorSafra( null );
			
		} catch ( Throwable e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ "Erro inesperado![" + e.getMessage() + "]" } ) );
			logger.error( MensagensResourcesApplication.getString( "log.erro.inesperado", new String[]{ this.getClass().getSimpleName() + " [inserir]", e.toString() } ) );
			e.printStackTrace();
		}
		
		return mapping.findForward( "safraCampos" );
	}
}