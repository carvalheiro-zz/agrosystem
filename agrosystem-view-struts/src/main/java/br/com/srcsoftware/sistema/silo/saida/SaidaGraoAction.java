package br.com.srcsoftware.sistema.silo.saida;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
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
import br.com.srcsoftware.modular.manager.pessoa.cliente.ClienteDTO;
import br.com.srcsoftware.modular.manager.pessoa.cliente.ClienteFacade;
import br.com.srcsoftware.modular.manager.pessoa.funcionario.FuncionarioDTO;
import br.com.srcsoftware.modular.manager.pessoa.funcionario.FuncionarioFacade;
import br.com.srcsoftware.modular.manager.utilidades.MensagensResourcesApplication;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.modular.manager.view.utilidades.Messages;
import br.com.srcsoftware.sistema.safra.SafraDTO;
import br.com.srcsoftware.sistema.safra.SafraFacade;
import br.com.srcsoftware.sistema.silo.contratovenda.ContratoVendaDTO;
import br.com.srcsoftware.sistema.silo.contratovenda.ContratoVendaFacade;
import br.com.srcsoftware.sistema.silo.entrada.InformacoesProducaoSafraPOJO;
import br.com.srcsoftware.sistema.silo.silo.InformacoesSiloPOJO;
import br.com.srcsoftware.sistema.silo.silo.SiloDTO;
import br.com.srcsoftware.sistema.silo.silo.SiloFacade;

public final class SaidaGraoAction extends AbstractCRUDAction< SaidaGraoForm >{

	@Override
	public ActionForward abrirListagem( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {

			SaidaGraoForm meuForm = this.getCastingForm( form, request );

			meuForm.limparTudo( request );
			//meuForm.limparLista();

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
			SaidaGraoForm meuForm = this.getCastingForm( form, request );

			meuForm.limparTudo( request );

			meuForm.getPaginacao().inicializar();

			return mapping.findForward( "saidaGraoCampos" );
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
			SaidaGraoForm meuForm = this.getCastingForm( form, request );

			SaidaGraoDTO dtoPersistir = meuForm.getSaidaGrao();

			SaidaGraoFacade.getInstance().inserir( meuForm.getUsuarioSessaoPOJO( request ), dtoPersistir );

			meuForm.limparTudo( request );

			this.addMessages( request, Messages.createMessages( "sucesso" ) );

		} catch ( ApplicationException | Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
		}

		return mapping.findForward( "saidaGraoCampos" );
	}

	@Override
	public ActionForward alterar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			SaidaGraoForm meuForm = this.getCastingForm( form, request );

			SaidaGraoDTO dtoPersistir = meuForm.getSaidaGrao();

			SaidaGraoFacade.getInstance().alterar( meuForm.getUsuarioSessaoPOJO( request ), dtoPersistir );

			meuForm.limparTudo( request );

			this.addMessages( request, Messages.createMessages( "sucesso" ) );

		} catch ( ApplicationException | Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
		}
		return mapping.findForward( "saidaGraoCampos" );
	}

	@Override
	public ActionForward excluir( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			SaidaGraoForm meuForm = this.getCastingForm( form, request );

			SaidaGraoFacade.getInstance().excluir( SaidaGraoFacade.getInstance().filtrarPorId( meuForm.getIdRegistroExcluir() ) );

			//meuForm.limparTudo( request );

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
			SaidaGraoForm meuForm = this.getCastingForm( form, request );

			meuForm.getPaginacao().inicializar();

			HashMap< String, String > camposOrdenacao = new HashMap<>();
			camposOrdenacao.put( "data", "ASC" );

			List< SaidaGraoDTO > encontrados = SaidaGraoFacade.getInstance().filtrar( meuForm.getPaginacao(), camposOrdenacao, meuForm.getSaidaGrao(), meuForm.getDataInicial(), meuForm.getDataFinal() );

			List< SaidaGraoDTO > encontradosParaTotais = SaidaGraoFacade.getInstance().filtrar( Paginacao.NAO, camposOrdenacao, meuForm.getSaidaGrao(), meuForm.getDataInicial(), meuForm.getDataFinal() );
			meuForm.calcularTotais( encontradosParaTotais );

			meuForm.limparLista();

			meuForm.getSaidas().addAll( encontrados );

		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
		} catch ( Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			e.printStackTrace();
		}

		return mapping.findForward( "saidaGraoLista" );
	}

	@Override
	public ActionForward selecionar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			SaidaGraoForm meuForm = this.getCastingForm( form, request );

			SaidaGraoDTO encontrado = SaidaGraoFacade.getInstance().filtrarPorId( meuForm.getSaidaGrao().getId() );

			meuForm.limparTudo( request );

			meuForm.setSaidaGrao( encontrado );

			meuForm.getSilos().clear();
			List< SiloDTO > silos = SiloFacade.getInstance().filtrar( Paginacao.NAO, HibernateConnection.SEM_ORDENACAO, null );
			for ( SiloDTO siloCorrente : silos ) {
				InformacoesSiloPOJO pojo = SiloFacade.getInstance().filtrarInformacoesSilo( siloCorrente.getId(), meuForm.getSaidaGrao().getContratoVenda().getCultura().getId() );

				if ( pojo != null ) {
					meuForm.getSilos().add( pojo );
				}

			}

			meuForm.getSafras().clear();
			List< SafraDTO > safras = SafraFacade.getInstance().filtrar( Paginacao.NAO, HibernateConnection.SEM_ORDENACAO, null );
			for ( SafraDTO safraCorrente : safras ) {
				InformacoesProducaoSafraPOJO pojo = SafraFacade.getInstance().filtrarSaldoProducaoSafra( safraCorrente.getId(), meuForm.getSaidaGrao().getContratoVenda().getCultura().getId() );
				if ( pojo != null ) {
					if ( Utilidades.parseBigDecimal( pojo.getProducao() ).compareTo( BigDecimal.ZERO ) == 0 ) {
						continue;
					}
					meuForm.getSafras().add( pojo );
				}
			}

		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
			return mapping.findForward( "saidaGraoLista" );
		} catch ( Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			e.printStackTrace();
			return mapping.findForward( "saidaGraoLista" );
		}

		return mapping.findForward( "saidaGraoCampos" );
	}

	@Override
	public ActionForward limpar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			SaidaGraoForm meuForm = this.getCastingForm( form, request );

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

		return mapping.findForward( "saidaGraoCampos" );
	}

	@Override
	public ActionForward limparFiltro( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			SaidaGraoForm meuForm = this.getCastingForm( form, request );

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
			SaidaGraoForm meuForm = this.getCastingForm( form, request );

			HashMap< String, String > camposOrdenacao = new HashMap<>();
			camposOrdenacao.put( "data", "ASC" );

			List< SaidaGraoDTO > encontrados = SaidaGraoFacade.getInstance().filtrar( meuForm.getPaginacao(), camposOrdenacao, meuForm.getSaidaGrao(), meuForm.getDataInicial(), meuForm.getDataFinal() );

			meuForm.limparLista();

			meuForm.getSaidas().addAll( encontrados );

		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
		} catch ( Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			e.printStackTrace();
		}

		return mapping.findForward( "saidaGraoLista" );
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
			SaidaGraoForm meuForm = this.getCastingForm( form, request );

			FuncionarioDTO filtrar = new FuncionarioDTO();
			filtrar.getPessoaFisica().setRazaoSocial( meuForm.getSaidaGrao().getEstoquista().getPessoaFisica().getRazaoSocial() );

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
	public ActionForward selecionarClienteAutoComplete( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			SaidaGraoForm meuForm = this.getCastingForm( form, request );

			ClienteDTO filtrar = new ClienteDTO();
			filtrar.getPessoaJuridica().setRazaoSocial( meuForm.getSaidaGrao().getCliente().getNome() );

			HashMap< String, String > camposOrdenacao = new HashMap<>();
			camposOrdenacao.put( "nome", "ASC" );

			List< ClienteDTO > encontrados = ClienteFacade.getInstance().filtrar( Paginacao.NAO, camposOrdenacao, filtrar );

			JSONObject json = new JSONObject();
			JSONObject map = null;
			JSONArray arr = new JSONArray();

			for ( ClienteDTO dtoCorrente : encontrados ) {

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
	public ActionForward selecionarSafraAutoComplete( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			SaidaGraoForm meuForm = this.getCastingForm( form, request );

			SafraDTO pesquisar = new SafraDTO();
			pesquisar.setNome( meuForm.getSaidaGrao().getSafra01().getNome() );

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
	public ActionForward selecionarSafra02AutoComplete( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			SaidaGraoForm meuForm = this.getCastingForm( form, request );

			SafraDTO pesquisar = new SafraDTO();
			pesquisar.setNome( meuForm.getSaidaGrao().getSafra02().getNome() );

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

	/*@SuppressWarnings( "unchecked" )
	public ActionForward selecionarCulturaAutoComplete( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
	
		try {
			SaidaGraoForm meuForm = this.getCastingForm( form, request );
	
			List< CulturaDTO > encontrados = CulturaFacade.getInstance().filtrarPorSafraSetor( meuForm.getSaidaGrao().getSafra().getId(), null, meuForm.getSaidaGrao().getCultura().getNome(), null );
	
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
	}*/

	public ActionForward calcularValorLiquido( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			SaidaGraoForm meuForm = this.getCastingForm( form, request );

			String percentualDescontoString = meuForm.getSaidaGrao().getPercentualDesconto();
			String valorBrutoString = meuForm.getSaidaGrao().getValorBruto();

			BigDecimal percentualDesconto = null;
			BigDecimal valorBruto = null;

			if ( !Utilidades.isNuloOuVazio( percentualDescontoString ) ) {
				percentualDesconto = Utilidades.parseBigDecimal( percentualDescontoString );
			}
			if ( !Utilidades.isNuloOuVazio( valorBrutoString ) ) {
				valorBruto = Utilidades.parseBigDecimal( valorBrutoString );
			}

			if ( ( percentualDesconto != null ) && ( valorBruto != null ) ) {
				BigDecimal percentual = percentualDesconto.divide( new BigDecimal( "100" ) );
				meuForm.getSaidaGrao().setValorLiquido( Utilidades.parseBigDecimal( valorBruto.multiply( BigDecimal.ONE.subtract( percentual ) ).setScale( 2, BigDecimal.ROUND_FLOOR ) ) );
			} else {
				meuForm.getSaidaGrao().setValorLiquido( valorBrutoString );
			}

			JSONObject json = new JSONObject();

			json = new JSONObject();
			json.put( "valorLiquido", meuForm.getSaidaGrao().getValorLiquido() );

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

	public ActionForward calcularQuantidades( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			SaidaGraoForm meuForm = this.getCastingForm( form, request );

			gerenciarBaixasSafras( meuForm );

			BigDecimal quantidadeSolicitada = Utilidades.parseBigDecimal( meuForm.getSaidaGrao().getPesoLiquido() );
			BigDecimal quantidadeRestanteContrato = Utilidades.parseBigDecimal( meuForm.getSaidaGrao().getContratoVenda().getQuantidadeRestante() );

			JSONObject json = new JSONObject();

			json = new JSONObject();
			json.put( "emSacas", meuForm.getSaidaGrao().getEmSacas() );
			json.put( "valorBruto", meuForm.getSaidaGrao().getValorBrutoCalculado() );

			if ( quantidadeSolicitada.compareTo( quantidadeRestanteContrato ) > 0 ) {
				json.put( "mensagemQuantidadeInvalida", "Quantidade informada [" + Utilidades.parseBigDecimal( quantidadeSolicitada ) + " kg] � maior que a quantidades restante do Contrato [" + Utilidades.parseBigDecimal( quantidadeRestanteContrato ) + "kg] ! " );
				json.put( "idSafra01", null );
				json.put( "nomeSafra01", null );
				json.put( "pesoLiquidoSafra01", null );

				json.put( "idSafra02", null );
				json.put( "nomeSafra02", null );
				json.put( "pesoLiquidoSafra02", null );
			} else {
				json.put( "mensagemQuantidadeInvalida", null );
				json.put( "idSafra01", meuForm.getSaidaGrao().getSafra01().getId() );
				json.put( "nomeSafra01", meuForm.getSaidaGrao().getSafra01().getNome() );
				json.put( "pesoLiquidoSafra01", meuForm.getSaidaGrao().getPesoLiquidoSafra01() );

				json.put( "idSafra02", meuForm.getSaidaGrao().getSafra02().getId() );
				json.put( "nomeSafra02", meuForm.getSaidaGrao().getSafra02().getNome() );
				json.put( "pesoLiquidoSafra02", meuForm.getSaidaGrao().getPesoLiquidoSafra02() );
			}

			/*json.put( "idLocalArmazenagem01", null );
			json.put( "pesoLiquido01", null );
			json.put( "idLocalArmazenagem02", null );
			json.put( "pesoLiquido02", null );
			
			
			
			json.put( "idSafra02", 2 );
			json.put( "nomeSafra02", 2 );
			json.put( "pesoLiquidoSafra02", 2 );*/

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

	private void gerenciarBaixasSafras( SaidaGraoForm meuForm ) throws ApplicationException {
		/* Verificando se a Safra possui a quantidade solicitada */
		List< SafraDTO > safrasEncontradas = SafraFacade.getInstance().filtrar( Paginacao.NAO, HibernateConnection.SEM_ORDENACAO, null );
		Collections.sort( safrasEncontradas, new Comparator< SafraDTO >(){
			@Override
			public int compare( SafraDTO o1, SafraDTO o2 ) {
				Long id1 = Long.valueOf( o1.getId() );
				Long id2 = Long.valueOf( o2.getId() );
				return id1.compareTo( id2 );
			}

		} );

		meuForm.getSaidaGrao().setSafra01( null );
		meuForm.getSaidaGrao().setPesoLiquidoSafra01( null );
		meuForm.getSaidaGrao().setSafra02( null );
		meuForm.getSaidaGrao().setPesoLiquidoSafra02( null );
		meuForm.getSaidaGrao().setLocalArmazenagem01( null );
		meuForm.getSaidaGrao().setPesoLiquido01( null );
		meuForm.getSaidaGrao().setLocalArmazenagem02( null );
		meuForm.getSaidaGrao().setPesoLiquido02( null );

		for ( SafraDTO safraCorrente : safrasEncontradas ) {
			InformacoesProducaoSafraPOJO encontrado = SafraFacade.getInstance().filtrarSaldoProducaoSafra( safraCorrente.getId(), meuForm.getSaidaGrao().getContratoVenda().getCultura().getId() );
			if ( encontrado != null ) {

				BigDecimal quantidadeSolicitada = Utilidades.parseBigDecimal( meuForm.getSaidaGrao().getPesoLiquido() );
				BigDecimal saldoSafra = Utilidades.parseBigDecimal( encontrado.getProducao() );

				if ( saldoSafra.compareTo( BigDecimal.ZERO ) == 0 ) {
					continue;
				}

				if ( Utilidades.isNuloOuVazio( meuForm.getSaidaGrao().getSafra01().getId() ) ) {
					meuForm.getSaidaGrao().setSafra01( safraCorrente );
					if ( saldoSafra.compareTo( quantidadeSolicitada ) >= 0 ) {
						meuForm.getSaidaGrao().setPesoLiquidoSafra01( Utilidades.parseBigDecimal( quantidadeSolicitada ) );
					} else {
						meuForm.getSaidaGrao().setPesoLiquidoSafra01( Utilidades.parseBigDecimal( saldoSafra ) );
						continue;
					}
				} else {
					quantidadeSolicitada = quantidadeSolicitada.subtract( Utilidades.parseBigDecimal( meuForm.getSaidaGrao().getPesoLiquidoSafra01() ) );
					if ( quantidadeSolicitada.compareTo( BigDecimal.ZERO ) == 0 ) {
						break;
					}
					if ( saldoSafra.compareTo( quantidadeSolicitada ) >= 0 ) {
						meuForm.getSaidaGrao().setSafra02( safraCorrente );
						meuForm.getSaidaGrao().setPesoLiquidoSafra02( Utilidades.parseBigDecimal( quantidadeSolicitada ) );
					}
				}

			}
		}
	}

	public ActionForward gerenciarBaixasSilo1( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			SaidaGraoForm meuForm = this.getCastingForm( form, request );

			SiloDTO silo = SiloFacade.getInstance().filtrarPorId( meuForm.getSaidaGrao().getLocalArmazenagem01().getId() );
			if ( silo != null ) {
				InformacoesSiloPOJO encontrado = SiloFacade.getInstance().filtrarInformacoesSilo( silo.getId(), meuForm.getSaidaGrao().getContratoVenda().getCultura().getId() );

				if ( encontrado != null ) {
					BigDecimal quantidadeSolicitada = Utilidades.parseBigDecimal( meuForm.getSaidaGrao().getPesoLiquido() );
					BigDecimal saldoSilo = Utilidades.parseBigDecimal( encontrado.getSaldoSilo() );
					meuForm.getSaidaGrao().setLocalArmazenagem01( silo );
					if ( saldoSilo.compareTo( quantidadeSolicitada ) >= 0 ) {
						meuForm.getSaidaGrao().setPesoLiquido01( meuForm.getSaidaGrao().getPesoLiquido() );
					} else {
						meuForm.getSaidaGrao().setPesoLiquido01( Utilidades.parseBigDecimal( saldoSilo ) );
						meuForm.getSaidaGrao().setPesoLiquido02( Utilidades.parseBigDecimal( quantidadeSolicitada.subtract( saldoSilo ) ) );
					}
				} else {
					meuForm.getSaidaGrao().setPesoLiquido01( null );
				}
			}

			JSONObject json = new JSONObject();

			json = new JSONObject();
			json.put( "idLocalArmazenagem01", meuForm.getSaidaGrao().getLocalArmazenagem01().getId() );
			json.put( "pesoLiquido01", meuForm.getSaidaGrao().getPesoLiquido01() );

			json.put( "idLocalArmazenagem02", null );
			json.put( "pesoLiquido02", meuForm.getSaidaGrao().getPesoLiquido02() );

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

	public ActionForward gerenciarBaixasSilo2( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			SaidaGraoForm meuForm = this.getCastingForm( form, request );

			String mensagemQuantidadeInvalida = null;
			SiloDTO silo = SiloFacade.getInstance().filtrarPorId( meuForm.getSaidaGrao().getLocalArmazenagem02().getId() );
			if ( silo != null ) {
				InformacoesSiloPOJO encontrado = SiloFacade.getInstance().filtrarInformacoesSilo( silo.getId(), meuForm.getSaidaGrao().getContratoVenda().getCultura().getId() );

				if ( encontrado != null ) {
					BigDecimal quantidadeSolicitada = Utilidades.parseBigDecimal( meuForm.getSaidaGrao().getPesoLiquido02() );
					BigDecimal saldoSilo = Utilidades.parseBigDecimal( encontrado.getSaldoSilo() );
					if ( saldoSilo.compareTo( quantidadeSolicitada ) >= 0 ) {
						meuForm.getSaidaGrao().setLocalArmazenagem02( silo );
					} else {
						mensagemQuantidadeInvalida = "Silo [" + silo.getNome() + " " + saldoSilo + "kg] n�o possui quantidade suficiente [" + quantidadeSolicitada + "kg]";
					}
				}
			}

			JSONObject json = new JSONObject();

			json = new JSONObject();
			json.put( "mensagemQuantidadeInvalida", mensagemQuantidadeInvalida );
			json.put( "idLocalArmazenagem02", meuForm.getSaidaGrao().getLocalArmazenagem02().getId() );

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

	public ActionForward calcularSacas( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			SaidaGraoForm meuForm = this.getCastingForm( form, request );

			JSONObject json = new JSONObject();

			json = new JSONObject();
			json.put( "emSacas", meuForm.getSaidaGrao().getEmSacas() );
			json.put( "valorBruto", meuForm.getSaidaGrao().getValorBrutoCalculado() );

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

	public ActionForward selecionarContratoVenda( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			SaidaGraoForm meuForm = this.getCastingForm( form, request );

			if ( Utilidades.isNuloOuVazio( meuForm.getSaidaGrao().getContratoVenda().getNumero() ) ) {
				return mapping.findForward( "saidaGraoCampos" );
			}

			ContratoVendaDTO contratoVenda = ContratoVendaFacade.getInstance().filtrarPorNumero( meuForm.getSaidaGrao().getContratoVenda().getNumero() );

			if ( contratoVenda == null ) {
				throw new ApplicationException( "Contrato n� " + meuForm.getSaidaGrao().getContratoVenda().getNumero() + " n�o cadastrado!" );
			}

			meuForm.getSaidaGrao().setContratoVenda( contratoVenda );
			meuForm.getSaidaGrao().setCultura( contratoVenda.getCultura() );
			meuForm.getSaidaGrao().setCliente( contratoVenda.getCliente() );

			meuForm.getSilos().clear();
			List< SiloDTO > silos = SiloFacade.getInstance().filtrar( Paginacao.NAO, HibernateConnection.SEM_ORDENACAO, null );
			for ( SiloDTO siloCorrente : silos ) {
				InformacoesSiloPOJO encontrado = SiloFacade.getInstance().filtrarInformacoesSilo( siloCorrente.getId(), contratoVenda.getCultura().getId() );

				if ( encontrado != null ) {
					meuForm.getSilos().add( encontrado );
				}

			}
			Collections.sort( meuForm.getSilos() );

			meuForm.getSafras().clear();
			List< SafraDTO > safras = SafraFacade.getInstance().filtrar( Paginacao.NAO, HibernateConnection.SEM_ORDENACAO, null );
			for ( SafraDTO safraCorrente : safras ) {
				InformacoesProducaoSafraPOJO encontrado = SafraFacade.getInstance().filtrarSaldoProducaoSafra( safraCorrente.getId(), contratoVenda.getCultura().getId() );
				if ( encontrado != null ) {
					if ( Utilidades.parseBigDecimal( encontrado.getProducao() ).compareTo( BigDecimal.ZERO ) == 0 ) {
						continue;
					}
					meuForm.getSafras().add( encontrado );
				}
			}

		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
		} catch ( Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			e.printStackTrace();
		}
		return mapping.findForward( "saidaGraoCampos" );
	}

	public ActionForward gerarPDF( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			SaidaGraoForm meuForm = this.getCastingForm( form, request );

			HashMap< String, String > camposOrdenacao = new HashMap<>();
			camposOrdenacao.put( "data", "DESC" );

			SaidaGraoFacade.getInstance().gerarPDF( response, camposOrdenacao, meuForm.getSaidaGrao(), meuForm.getDataInicial(), meuForm.getDataFinal() );

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
