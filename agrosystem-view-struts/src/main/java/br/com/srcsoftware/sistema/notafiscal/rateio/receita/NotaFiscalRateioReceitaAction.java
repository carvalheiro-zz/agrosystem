package br.com.srcsoftware.sistema.notafiscal.rateio.receita;

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

import br.com.srcsoftware.enuns.NotaFiscalRateioEnum;
import br.com.srcsoftware.modular.financeiro.contareceber.ContaReceberDTO;
import br.com.srcsoftware.modular.financeiro.financeiro.FinanceiroFacade;
import br.com.srcsoftware.modular.financeiro.formapagamento.FormaPagamentoDTO;
import br.com.srcsoftware.modular.manager.abstracts.AbstractCRUDAction;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.pessoa.cliente.ClienteDTO;
import br.com.srcsoftware.modular.manager.pessoa.cliente.ClienteFacade;
import br.com.srcsoftware.modular.manager.utilidades.MensagensResourcesApplication;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.modular.manager.view.utilidades.Messages;
import br.com.srcsoftware.sistema.notafiscal.rateio.NotaFiscalRateioDTO;
import br.com.srcsoftware.sistema.notafiscal.rateio.centrocusto.CentroCustoDTO;
import br.com.srcsoftware.sistema.notafiscal.rateio.centrocusto.CentroCustoFacade;
import br.com.srcsoftware.sistema.notafiscal.rateio.itemnotafiscalrateio.ItemNotaFiscalRateioDTO;
import br.com.srcsoftware.sistema.safra.SafraDTO;
import br.com.srcsoftware.sistema.safra.SafraFacade;
import br.com.srcsoftware.sistema.safra.cultura.CulturaDTO;
import br.com.srcsoftware.sistema.safra.cultura.CulturaFacade;
import br.com.srcsoftware.sistema.safra.setor.SetorDTO;
import br.com.srcsoftware.sistema.safra.setor.SetorFacade;

public class NotaFiscalRateioReceitaAction extends AbstractCRUDAction< NotaFiscalRateioReceitaForm > {

	@Override
	public ActionForward abrirListagem( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {

			NotaFiscalRateioReceitaForm meuForm = this.getCastingForm( form, request );

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
			NotaFiscalRateioReceitaForm meuForm = this.getCastingForm( form, request );

			meuForm.limparTudo( request );

			meuForm.getPaginacao().inicializar();

			return mapping.findForward( "notaFiscalRateioReceitaCampos" );
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
			NotaFiscalRateioReceitaForm meuForm = this.getCastingForm( form, request );

			NotaFiscalRateioDTO dtoPersistir = meuForm.getNotaFiscalRateio();

			NotaFiscalRateioReceitaFacade.getInstance().inserir( meuForm.getUsuarioSessaoPOJO( request ), dtoPersistir );

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

		return mapping.findForward( "notaFiscalRateioReceitaCampos" );
	}

	@Override
	public ActionForward alterar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			NotaFiscalRateioReceitaForm meuForm = this.getCastingForm( form, request );

			NotaFiscalRateioDTO dtoPersistir = meuForm.getNotaFiscalRateio();

			NotaFiscalRateioReceitaFacade.getInstance().alterar( meuForm.getUsuarioSessaoPOJO( request ), dtoPersistir );

			meuForm.limparTudo( request );

			this.addMessages( request, Messages.createMessages( "sucesso" ) );

		} catch ( ApplicationException | Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
			request.getSession().setAttribute( "erroAjax", e.getMessage() );
			request.getSession().removeAttribute( "sucessoAjax" );
		}
		return mapping.findForward( "notaFiscalRateioReceitaCampos" );
	}

	@Override
	public ActionForward excluir( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			NotaFiscalRateioReceitaForm meuForm = this.getCastingForm( form, request );

			NotaFiscalRateioReceitaFacade.getInstance().excluir( NotaFiscalRateioReceitaFacade.getInstance().filtrarPorId( meuForm.getIdRegistroExcluir() ) );

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

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward filtrar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			NotaFiscalRateioReceitaForm meuForm = this.getCastingForm( form, request );

			meuForm.getPaginacao().inicializar();

			meuForm.getNotaFiscalRateio().setTipo( NotaFiscalRateioEnum.TIPO_RECEITA.getValor() );
			meuForm.getNotaFiscalRateio().getItens().add( meuForm.getItemAdicionar() );

			HashMap< String, String > camposOrdenacao = new HashMap<>();
			camposOrdenacao.put( "contaReceber.data", "ASC" );

			List< NotaFiscalRateioDTO > encontrados = NotaFiscalRateioReceitaFacade.getInstance().filtrar( meuForm.getPaginacao(), camposOrdenacao, meuForm.getNotaFiscalRateio(),
				meuForm.getDataInicial(), meuForm.getDataFinal() );

			meuForm.limparLista();
			meuForm.getNotaFiscalRateio().getItens().clear();

			meuForm.getNotas().addAll( encontrados );

		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
		} catch ( Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ e.getMessage() } ) );
			logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			e.printStackTrace();
		}

		return mapping.findForward( "notaFiscalRateioReceitaLista" );
	}

	@Override
	public ActionForward selecionar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			NotaFiscalRateioReceitaForm meuForm = this.getCastingForm( form, request );

			NotaFiscalRateioDTO encontrado = NotaFiscalRateioReceitaFacade.getInstance().filtrarPorId( meuForm.getNotaFiscalRateio().getId() );

			meuForm.limparTudo( request );

			meuForm.setNotaFiscalRateio( encontrado );

		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
			return mapping.findForward( "notaFiscalRateioReceitaLista" );
		} catch ( Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ e.getMessage() } ) );
			logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			e.printStackTrace();
			return mapping.findForward( "notaFiscalRateioReceitaLista" );
		}

		return mapping.findForward( "notaFiscalRateioReceitaCampos" );
	}

	@Override
	public ActionForward limpar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			NotaFiscalRateioReceitaForm meuForm = this.getCastingForm( form, request );

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

		return mapping.findForward( "notaFiscalRateioReceitaCampos" );
	}

	@Override
	public ActionForward limparFiltro( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			NotaFiscalRateioReceitaForm meuForm = this.getCastingForm( form, request );

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
			NotaFiscalRateioReceitaForm meuForm = this.getCastingForm( form, request );

			meuForm.getNotaFiscalRateio().setTipo( NotaFiscalRateioEnum.TIPO_RECEITA.getValor() );
			meuForm.getNotaFiscalRateio().getItens().add( meuForm.getItemAdicionar() );

			HashMap< String, String > camposOrdenacao = new HashMap<>();
			camposOrdenacao.put( "contaReceber.data", "ASC" );

			List< NotaFiscalRateioDTO > encontrados = NotaFiscalRateioReceitaFacade.getInstance().filtrar( meuForm.getPaginacao(), camposOrdenacao, meuForm.getNotaFiscalRateio(),
				meuForm.getDataInicial(), meuForm.getDataFinal() );

			meuForm.limparLista();
			meuForm.getNotaFiscalRateio().getItens().clear();

			meuForm.getNotas().addAll( encontrados );

		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
		} catch ( Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ e.getMessage() } ) );
			logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			e.printStackTrace();
		}

		return mapping.findForward( "notaFiscalRateioReceitaLista" );
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
	public ActionForward selecionarCentroCustoAutoComplete( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			NotaFiscalRateioReceitaForm meuForm = this.getCastingForm( form, request );

			List< CentroCustoDTO > encontrados = CentroCustoFacade.getInstance().filtrarPorTipoNotaFiscalRateioAgrupado( meuForm.getItemAdicionar().getCentroCusto().getCodigo(),
				meuForm.getNotaFiscalRateio().getTipo() );

			JSONObject json = new JSONObject();
			JSONObject map = null;
			JSONArray arr = new JSONArray();

			principal: for ( CentroCustoDTO centroCustoReceita : encontrados ) {

				for ( ItemNotaFiscalRateioDTO itemCorrenteJaAdicionado : meuForm.getNotaFiscalRateio().getItens() ) {
					if ( itemCorrenteJaAdicionado.getCentroCusto().getId().equals( centroCustoReceita.getId() ) ) {
						continue principal;
					}
				}

				map = new JSONObject();
				map.put( "value", centroCustoReceita.getCodigo() );
				map.put( "data", centroCustoReceita.getId() );
				map.put( "descricao", centroCustoReceita.getDescricao() );
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
	public ActionForward selecionarClienteAutoComplete( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			NotaFiscalRateioReceitaForm meuForm = this.getCastingForm( form, request );

			ClienteDTO filtrar = new ClienteDTO();
			filtrar.setNome( meuForm.getNotaFiscalRateio().getCliente().getNome() );

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

	public ActionForward adicionarItem( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			NotaFiscalRateioReceitaForm meuForm = this.getCastingForm( form, request );

			NotaFiscalRateioReceitaFacade.getInstance().adicionarItem( meuForm.getItemAdicionar(), meuForm.getNotaFiscalRateio() );

			meuForm.setItemAdicionar( null );

			request.getSession().removeAttribute( "erroAjax" );

		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
			request.getSession().removeAttribute( "erroAjax" );
		} catch ( Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ e.getMessage() } ) );
			logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			e.printStackTrace();
			request.getSession().removeAttribute( "erroAjax" );
		}

		return mapping.findForward( "painelItensAjax" );
	}

	public ActionForward removerItem( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			NotaFiscalRateioReceitaForm meuForm = this.getCastingForm( form, request );

			NotaFiscalRateioReceitaFacade.getInstance().removerItem( meuForm.getNotaFiscalRateio(), meuForm.getIdItemRemover() );

			meuForm.setItemAdicionar( null );

		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
			request.getSession().removeAttribute( "erroAjax" );
		} catch ( Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ e.getMessage() } ) );
			logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			e.printStackTrace();
			request.getSession().removeAttribute( "erroAjax" );
		}

		return mapping.findForward( "painelItensAjax" );
	}

	public ActionForward quitarParcela( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			NotaFiscalRateioReceitaForm meuForm = this.getCastingForm( form, request );

			ContaReceberDTO contaReceber = NotaFiscalRateioReceitaFacade.getInstance().quitar( meuForm.getUsuarioSessaoPOJO( request ), meuForm.getNotaFiscalRateio().getContaReceber(),
				meuForm.getParcelaSelecionada() );

			meuForm.getNotaFiscalRateio().setContaReceber( contaReceber );

			Collections.sort( meuForm.getNotaFiscalRateio().getContaReceber().getParcelas() );

			request.getSession().removeAttribute( "erroAjax" );
		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();

			request.getSession().setAttribute( "erroAjax", e.getMessage() );
		}
		return mapping.findForward( "abaParcelaAjax" );
	}

	public ActionForward estornarParcela( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			NotaFiscalRateioReceitaForm meuForm = this.getCastingForm( form, request );

			ContaReceberDTO contaReceber = NotaFiscalRateioReceitaFacade.getInstance().estornar( meuForm.getUsuarioSessaoPOJO( request ), meuForm.getNotaFiscalRateio().getContaReceber(),
				meuForm.getParcelaSelecionada() );

			meuForm.getNotaFiscalRateio().setContaReceber( contaReceber );

			Collections.sort( meuForm.getNotaFiscalRateio().getContaReceber().getParcelas() );

			request.getSession().removeAttribute( "erroAjax" );
		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();

			request.getSession().setAttribute( "erroAjax", e.getMessage() );
		}
		return mapping.findForward( "abaParcelaAjax" );
	}

	@SuppressWarnings("unchecked")
	public ActionForward selecionarSafraAutoComplete( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			NotaFiscalRateioReceitaForm meuForm = this.getCastingForm( form, request );

			SafraDTO pesquisar = new SafraDTO();
			pesquisar.setNome( meuForm.getItemAdicionar().getSafra().getNome() );

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
			NotaFiscalRateioReceitaForm meuForm = this.getCastingForm( form, request );

			List< SetorDTO > encontrados = SetorFacade.getInstance().filtrarPorSafra( meuForm.getItemAdicionar().getSafra().getId(), meuForm.getItemAdicionar().getSetor().getNomeCompleto() );

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

	@SuppressWarnings("unchecked")
	public ActionForward selecionarCulturaAutoComplete( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			NotaFiscalRateioReceitaForm meuForm = this.getCastingForm( form, request );

			List< CulturaDTO > encontrados = CulturaFacade.getInstance().filtrarPorSafraSetor( meuForm.getItemAdicionar().getSafra().getId(), meuForm.getItemAdicionar().getSetor().getId(),
				meuForm.getItemAdicionar().getCultura().getNome(), null );

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

	public ActionForward carregarComboFormaPagamento( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			NotaFiscalRateioReceitaForm meuForm = this.getCastingForm( form, request );

			if ( Utilidades.isNuloOuVazio( meuForm.getNotaFiscalRateio().getContaReceber().getTipo() ) ) {
				response.setContentType( "application/json" );
				response.setHeader( "Cache-Control", "nocache" );
				response.getWriter().print( "null" );
				return null;
			}

			List< FormaPagamentoDTO > encontrados = FinanceiroFacade.getInstance().getComboFormaPagamento( String.valueOf( meuForm.getUsuarioSessaoPOJO( request ).getIsADM() ), null,
				meuForm.getNotaFiscalRateio().getContaReceber().getTipo(), FormaPagamentoDTO.TIPO_RECEITA );

			Collections.sort( encontrados, new Comparator< FormaPagamentoDTO >() {

				@Override
				public int compare( FormaPagamentoDTO o1, FormaPagamentoDTO o2 ) {
					return o1.getNomeCompleto().compareTo( o2.getNomeCompleto() );
				}

			} );

			JSONObject json = new JSONObject();
			JSONObject map = null;
			JSONArray arr = new JSONArray();

			for ( FormaPagamentoDTO dtoCorrente : encontrados ) {

				map = new JSONObject();
				map.put( "value", dtoCorrente.getNomeCompleto() );
				map.put( "data", dtoCorrente.getId() );
				arr.add( map );
			}
			json.put( "dados", arr );

			response.setContentType( "application/json" );
			response.setHeader( "Cache-Control", "nocache" );
			response.getWriter().print( json.toString() );

		} catch ( Throwable e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ "Erro inesperado![" + e.getMessage() + "]" } ) );
			logger.error( MensagensResourcesApplication.getString( "log.erro.inesperado", new String[]{ this.getClass().getSimpleName() + " [inserir]", e.toString() } ) );
			e.printStackTrace();
		}
		return null;
	}
}
