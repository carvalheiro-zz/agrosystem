package br.com.srcsoftware.sistema.silo.entrada;

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
import br.com.srcsoftware.modular.manager.abstracts.AbstractCRUDAction;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.pessoa.funcionario.FuncionarioDTO;
import br.com.srcsoftware.modular.manager.pessoa.funcionario.FuncionarioFacade;
import br.com.srcsoftware.modular.manager.utilidades.MensagensResourcesApplication;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.modular.manager.view.utilidades.Messages;
import br.com.srcsoftware.sistema.safra.SafraDTO;
import br.com.srcsoftware.sistema.safra.SafraFacade;
import br.com.srcsoftware.sistema.safra.cultura.CulturaDTO;
import br.com.srcsoftware.sistema.safra.cultura.CulturaFacade;
import br.com.srcsoftware.sistema.safra.cultura.variedade.VariedadeDTO;
import br.com.srcsoftware.sistema.safra.cultura.variedade.VariedadeFacade;
import br.com.srcsoftware.sistema.safra.setor.SetorDTO;
import br.com.srcsoftware.sistema.safra.setor.SetorFacade;
import br.com.srcsoftware.sistema.silo.silo.InformacoesSiloPOJO;
import br.com.srcsoftware.sistema.silo.silo.SiloDTO;
import br.com.srcsoftware.sistema.silo.silo.SiloFacade;

public final class EntradaGraoAction extends AbstractCRUDAction< EntradaGraoForm >{

	@Override
	public ActionForward abrirListagem( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {

			EntradaGraoForm meuForm = this.getCastingForm( form, request );

			meuForm.limparTudo( request );

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
			EntradaGraoForm meuForm = this.getCastingForm( form, request );

			meuForm.limparTudo( request );

			meuForm.getPaginacao().inicializar();

			calcularSaldoSilosSafras( meuForm );

			return mapping.findForward( "entradaGraoCampos" );
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
			EntradaGraoForm meuForm = this.getCastingForm( form, request );

			EntradaGraoDTO dtoPersistir = meuForm.getEntradaGrao();

			EntradaGraoFacade.getInstance().inserir( meuForm.getUsuarioSessaoPOJO( request ), dtoPersistir );

			meuForm.limparTudo( request );

			calcularSaldoSilosSafras( meuForm );

			this.addMessages( request, Messages.createMessages( "sucesso" ) );

		} catch ( ApplicationException | Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
		}

		return mapping.findForward( "entradaGraoCampos" );
	}

	@Override
	public ActionForward alterar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			EntradaGraoForm meuForm = this.getCastingForm( form, request );

			EntradaGraoDTO dtoPersistir = meuForm.getEntradaGrao();

			EntradaGraoFacade.getInstance().alterar( meuForm.getUsuarioSessaoPOJO( request ), dtoPersistir );

			meuForm.limparTudo( request );

			calcularSaldoSilosSafras( meuForm );

			this.addMessages( request, Messages.createMessages( "sucesso" ) );

		} catch ( ApplicationException | Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
		}
		return mapping.findForward( "entradaGraoCampos" );
	}

	@Override
	public ActionForward excluir( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			EntradaGraoForm meuForm = this.getCastingForm( form, request );

			EntradaGraoFacade.getInstance().excluir( EntradaGraoFacade.getInstance().filtrarPorId( meuForm.getIdRegistroExcluir() ) );

			// meuForm.limparTudo( request );

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
			EntradaGraoForm meuForm = this.getCastingForm( form, request );

			meuForm.getPaginacao().inicializar();

			HashMap< String, String > camposOrdenacao = new HashMap<>();
			camposOrdenacao.put( "data", "DESC" );

			List< EntradaGraoDTO > encontrados = EntradaGraoFacade.getInstance().filtrar( meuForm.getPaginacao(), camposOrdenacao, meuForm.getEntradaGrao(), meuForm.getDataInicial(), meuForm.getDataFinal() );

			List< EntradaGraoDTO > encontradosParaTotais = EntradaGraoFacade.getInstance().filtrar( Paginacao.NAO, camposOrdenacao, meuForm.getEntradaGrao(), meuForm.getDataInicial(), meuForm.getDataFinal() );
			meuForm.calcularTotais( encontradosParaTotais );

			meuForm.limparLista();

			meuForm.getEntradas().addAll( encontrados );

		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
		} catch ( Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			e.printStackTrace();
		}

		return mapping.findForward( "entradaGraoLista" );
	}

	@Override
	public ActionForward selecionar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			EntradaGraoForm meuForm = this.getCastingForm( form, request );

			EntradaGraoDTO encontrado = EntradaGraoFacade.getInstance().filtrarPorId( meuForm.getEntradaGrao().getId() );

			meuForm.limparTudo( request );

			calcularSaldoSilosSafras( meuForm );

			meuForm.setEntradaGrao( encontrado );

		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
			return mapping.findForward( "entradaGraoLista" );
		} catch ( Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			e.printStackTrace();
			return mapping.findForward( "entradaGraoLista" );
		}

		return mapping.findForward( "entradaGraoCampos" );
	}

	@Override
	public ActionForward limpar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			EntradaGraoForm meuForm = this.getCastingForm( form, request );

			meuForm.limparTudo( request );

			calcularSaldoSilosSafras( meuForm );

		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
		} catch ( Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			e.printStackTrace();
		}

		return mapping.findForward( "entradaGraoCampos" );
	}

	@Override
	public ActionForward limparFiltro( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			EntradaGraoForm meuForm = this.getCastingForm( form, request );

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
			EntradaGraoForm meuForm = this.getCastingForm( form, request );

			HashMap< String, String > camposOrdenacao = new HashMap<>();
			camposOrdenacao.put( "data", "DESC" );

			List< EntradaGraoDTO > encontrados = EntradaGraoFacade.getInstance().filtrar( meuForm.getPaginacao(), camposOrdenacao, meuForm.getEntradaGrao(), meuForm.getDataInicial(), meuForm.getDataFinal() );

			meuForm.limparLista();

			meuForm.getEntradas().addAll( encontrados );

		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
		} catch ( Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			e.printStackTrace();
		}

		return mapping.findForward( "entradaGraoLista" );
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
	public ActionForward selecionarEstoquistaAutoComplete( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			EntradaGraoForm meuForm = this.getCastingForm( form, request );

			FuncionarioDTO filtrar = new FuncionarioDTO();
			filtrar.getPessoaFisica().setRazaoSocial( meuForm.getEntradaGrao().getEstoquista().getPessoaFisica().getRazaoSocial() );

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
			EntradaGraoForm meuForm = this.getCastingForm( form, request );

			SafraDTO pesquisar = new SafraDTO();
			pesquisar.setNome( meuForm.getEntradaGrao().getSafra().getNome() );

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
	public ActionForward selecionarSetorAutoComplete( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			EntradaGraoForm meuForm = this.getCastingForm( form, request );

			List< SetorDTO > encontrados = SetorFacade.getInstance().filtrarPorSafra( meuForm.getEntradaGrao().getSafra().getId(), meuForm.getEntradaGrao().getSetor().getNomeCompleto() );

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
		} catch ( Throwable e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { "Erro inesperado![" + e.getMessage() + "]" } ) );
			logger.error( MensagensResourcesApplication.getString( "log.erro.inesperado", new String[ ] { this.getClass().getSimpleName() + " [inserir]", e.toString() } ) );
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings( "unchecked" )
	public ActionForward selecionarVariedadeAutoComplete( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			EntradaGraoForm meuForm = this.getCastingForm( form, request );

			List< VariedadeDTO > encontrados = VariedadeFacade.getInstance().filtrarPorSafraSetor( meuForm.getEntradaGrao().getSafra().getId(), meuForm.getEntradaGrao().getSetor().getId(), meuForm.getEntradaGrao().getVariedade().getNome() );
			// List< VariedadeDTO > encontrados = VariedadeFacade.getInstance().filtrarPorSafraSetor( null, null,
			// meuForm.getEntradaGrao().getVariedade().getNome() );

			JSONObject json = new JSONObject();
			JSONObject map = null;
			JSONArray arr = new JSONArray();

			for ( VariedadeDTO dtoCorrente : encontrados ) {

				map = new JSONObject();
				map.put( "value", dtoCorrente.getNome() );
				map.put( "data", dtoCorrente.getId() );

				map.put( "nomeCompleto", dtoCorrente.getNomeCompleto() );

				map.put( "nomeCultura", dtoCorrente.getCultura().getNome() );
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
			EntradaGraoForm meuForm = this.getCastingForm( form, request );

			JSONObject json = new JSONObject();

			json = new JSONObject();
			json.put( "pesoLiquido", meuForm.getEntradaGrao().getPesoLiquido() );
			json.put( "emSacas", meuForm.getEntradaGrao().getEmSacas() );

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

	public void calcularSaldoSilosSafras( EntradaGraoForm meuForm ) throws ApplicationException {

		meuForm.getSilos().clear();
		List< SiloDTO > silos = SiloFacade.getInstance().filtrar( Paginacao.NAO, HibernateConnection.SEM_ORDENACAO, null );

		meuForm.getSafras().clear();
		List< SafraDTO > safras = SafraFacade.getInstance().filtrar( Paginacao.NAO, HibernateConnection.SEM_ORDENACAO, null );

		List< CulturaDTO > culturas = CulturaFacade.getInstance().filtrar( Paginacao.NAO, HibernateConnection.SEM_ORDENACAO, null );

		for ( CulturaDTO culturaCorrente : culturas ) {

			for ( SiloDTO siloCorrente : silos ) {

				// VariedadeDTO variedadeSelecionada = VariedadeFacade.getInstance().filtrarPorId(
				// meuForm.getEntradaGrao().getVariedade().getId() );
				InformacoesSiloPOJO encontrado = SiloFacade.getInstance().filtrarInformacoesSilo( siloCorrente.getId(), culturaCorrente.getId() );

				if ( encontrado != null ) {
					meuForm.getSilos().add( encontrado );
				}

			}
			Collections.sort( meuForm.getSilos() );

			for ( SafraDTO safraCorrente : safras ) {

				// VariedadeDTO variedadeSelecionada = VariedadeFacade.getInstance().filtrarPorId(
				// meuForm.getEntradaGrao().getVariedade().getId() );

				InformacoesProducaoSafraPOJO encontrado = SafraFacade.getInstance().filtrarSaldoProducaoSafra( safraCorrente.getId(), culturaCorrente.getId() );
				if ( encontrado != null ) {
					if ( Utilidades.parseBigDecimal( encontrado.getProducao() ).compareTo( BigDecimal.ZERO ) == 0 ) {
						continue;
					}
					meuForm.getSafras().add( encontrado );
				}

			}
		}

	}

	public ActionForward gerarPDF( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			EntradaGraoForm meuForm = this.getCastingForm( form, request );

			HashMap< String, String > camposOrdenacao = new HashMap<>();
			camposOrdenacao.put( "data", "DESC" );

			EntradaGraoFacade.getInstance().gerarPDF( response, camposOrdenacao, meuForm.getEntradaGrao(), meuForm.getDataInicial(), meuForm.getDataFinal() );

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
	/*
	 * public ActionForward selecionarVariedade( ActionMapping mapping, ActionForm form, HttpServletRequest request,
	 * HttpServletResponse response ) {
	 * try {
	 * EntradaGraoForm meuForm = this.getCastingForm( form, request );
	 * if ( Utilidades.isNuloOuVazio( meuForm.getEntradaGrao().getLocalArmazenagem().getId() ) ) {
	 * return mapping.findForward( "entradaGraoCampos" );
	 * }
	 * if ( Utilidades.isNuloOuVazio( meuForm.getEntradaGrao().getVariedade().getId() ) ) {
	 * return mapping.findForward( "entradaGraoCampos" );
	 * }
	 * meuForm.getSilos().clear();
	 * List< SiloDTO > silos = SiloFacade.getInstance().filtrar( Paginacao.NAO, HibernateConnection.SEM_ORDENACAO, null );
	 * meuForm.getSafras().clear();
	 * List< SafraDTO > safras = SafraFacade.getInstance().filtrar( Paginacao.NAO, HibernateConnection.SEM_ORDENACAO, null
	 * );
	 * List< CulturaDTO > culturas = CulturaFacade.getInstance().filtrar( Paginacao.NAO, HibernateConnection.SEM_ORDENACAO,
	 * null );
	 * for ( CulturaDTO culturaCorrente : culturas ) {
	 * for ( SiloDTO siloCorrente : silos ) {
	 * //VariedadeDTO variedadeSelecionada = VariedadeFacade.getInstance().filtrarPorId(
	 * meuForm.getEntradaGrao().getVariedade().getId() );
	 * InformacoesSiloPOJO encontrado = SiloFacade.getInstance().filtrarInformacoesSilo( siloCorrente.getId(),
	 * culturaCorrente.getId() );
	 * if ( encontrado != null ) {
	 * meuForm.getSilos().add( encontrado );
	 * }
	 * }
	 * for ( SafraDTO safraCorrente : safras ) {
	 * //VariedadeDTO variedadeSelecionada = VariedadeFacade.getInstance().filtrarPorId(
	 * meuForm.getEntradaGrao().getVariedade().getId() );
	 * InformacoesProducaoSafraPOJO encontrado = SafraFacade.getInstance().filtrarSaldoProducaoSafra( safraCorrente.getId(),
	 * culturaCorrente.getId() );
	 * if ( encontrado != null ) {
	 * if ( Utilidades.parseBigDecimal( encontrado.getProducao() ).compareTo( BigDecimal.ZERO ) == 0 ) {
	 * continue;
	 * }
	 * meuForm.getSafras().add( encontrado );
	 * }
	 * }
	 * }
	 * } catch ( ApplicationException e ) {
	 * this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
	 * logger.error( e.getMessage(), e );
	 * e.printStackTrace();
	 * return mapping.findForward( "entradaGraoCampos" );
	 * } catch ( Exception e ) {
	 * this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
	 * logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
	 * e.printStackTrace();
	 * return mapping.findForward( "entradaGraoCampos" );
	 * }
	 * return mapping.findForward( "entradaGraoCampos" );
	 * }
	 */
}
