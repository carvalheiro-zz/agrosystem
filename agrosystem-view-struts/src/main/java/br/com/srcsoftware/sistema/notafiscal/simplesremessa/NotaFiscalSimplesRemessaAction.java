package br.com.srcsoftware.sistema.notafiscal.simplesremessa;

import java.math.BigDecimal;
import java.util.ArrayList;
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

import br.com.srcsoftware.modular.manager.abstracts.AbstractCRUDAction;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.pessoa.funcionario.FuncionarioDTO;
import br.com.srcsoftware.modular.manager.pessoa.funcionario.FuncionarioFacade;
import br.com.srcsoftware.modular.manager.utilidades.MensagensResourcesApplication;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.modular.manager.view.utilidades.Messages;
import br.com.srcsoftware.sistema.pedido.notafiscal.simplesremessa.NotaFiscalSimplesRemessaDTO;
import br.com.srcsoftware.sistema.pedido.notafiscal.simplesremessa.NotaFiscalSimplesRemessaFacade;
import br.com.srcsoftware.sistema.pedido.notafiscal.venda.ItemNotaFiscalVendaDTO;
import br.com.srcsoftware.sistema.pedido.notafiscal.venda.NotaFiscalVendaDTO;
import br.com.srcsoftware.sistema.pedido.notafiscal.venda.NotaFiscalVendaFacade;
import br.com.srcsoftware.sistema.pessoa.fornecedor.juridico.FornecedorFacade;
import br.com.srcsoftware.sistema.pessoa.fornecedor.juridico.FornecedorJuridicoDTO;

public final class NotaFiscalSimplesRemessaAction extends AbstractCRUDAction< NotaFiscalSimplesRemessaForm >{

	@Override
	public ActionForward abrirListagem( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {

			NotaFiscalSimplesRemessaForm meuForm = this.getCastingForm( form, request );

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
			NotaFiscalSimplesRemessaForm meuForm = this.getCastingForm( form, request );

			meuForm.limparTudo( request );

			meuForm.getPaginacao().inicializar();

			return mapping.findForward( "notaFiscalSimplesRemessaCampos" );
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
			NotaFiscalSimplesRemessaForm meuForm = this.getCastingForm( form, request );

			NotaFiscalSimplesRemessaDTO dtoPersistir = meuForm.getNotaFiscalSimplesRemessa();

			NotaFiscalSimplesRemessaFacade.getInstance().inserir( meuForm.getUsuarioSessaoPOJO( request ), dtoPersistir );

			meuForm.limparTudo( request );

			this.addMessages( request, Messages.createMessages( "sucesso" ) );
			request.getSession().setAttribute( "sucessoAjax", "Operação realizada com sucesso!" );

		} catch ( ApplicationException | Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
			request.getSession().setAttribute( "erroAjax", e.getMessage() );
			request.getSession().removeAttribute( "sucessoAjax" );
		}

		return mapping.findForward( "notaFiscalSimplesRemessaCampos" );
	}

	@Override
	public ActionForward alterar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			NotaFiscalSimplesRemessaForm meuForm = this.getCastingForm( form, request );

			NotaFiscalSimplesRemessaDTO dtoPersistir = meuForm.getNotaFiscalSimplesRemessa();

			NotaFiscalSimplesRemessaFacade.getInstance().alterar( meuForm.getUsuarioSessaoPOJO( request ), dtoPersistir );

			meuForm.limparTudo( request );

			this.addMessages( request, Messages.createMessages( "sucesso" ) );

		} catch ( ApplicationException | Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
			request.getSession().setAttribute( "erroAjax", e.getMessage() );
			request.getSession().removeAttribute( "sucessoAjax" );
		}
		return mapping.findForward( "notaFiscalSimplesRemessaCampos" );
	}

	@Override
	public ActionForward excluir( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			NotaFiscalSimplesRemessaForm meuForm = this.getCastingForm( form, request );

			NotaFiscalSimplesRemessaFacade.getInstance().excluir( NotaFiscalSimplesRemessaFacade.getInstance().filtrarPorId( meuForm.getIdRegistroExcluir() ) );

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
		return filtrar( mapping, form, request, response );
	}

	@Override
	public ActionForward filtrar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			NotaFiscalSimplesRemessaForm meuForm = this.getCastingForm( form, request );
			meuForm.getNotaFiscalSimplesRemessa().getItens().add( meuForm.getItemPesquisar() );

			meuForm.getPaginacao().inicializar();

			HashMap< String, String > camposOrdenacao = new HashMap<>();
			camposOrdenacao.put( "data", "DESC" );

			List< NotaFiscalSimplesRemessaDTO > encontrados = NotaFiscalSimplesRemessaFacade.getInstance().filtrar( meuForm.getPaginacao(), camposOrdenacao, meuForm.getNotaFiscalSimplesRemessa(), meuForm.getDataInicial(), meuForm.getDataFinal() );

			meuForm.limparLista();

			meuForm.getNotas().addAll( encontrados );

		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
		} catch ( Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			e.printStackTrace();
		}

		return mapping.findForward( "notaFiscalSimplesRemessaLista" );
	}

	@Override
	public ActionForward selecionar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			NotaFiscalSimplesRemessaForm meuForm = this.getCastingForm( form, request );

			NotaFiscalSimplesRemessaDTO encontrado = NotaFiscalSimplesRemessaFacade.getInstance().filtrarPorId( meuForm.getNotaFiscalSimplesRemessa().getId() );

			meuForm.limparTudo( request );

			meuForm.setNotaFiscalSimplesRemessa( encontrado );

			meuForm.getItensNotasFiscaisVendasFuturasRestantes().addAll( this.montarTabelaItensNotasFiscaisVendasFuturasRestantes( meuForm.getNotaFiscalSimplesRemessa().getNotaFiscalVendaFutura().getId(), meuForm.getNotaFiscalSimplesRemessa().getNotaFiscalVendaFutura().getItens() ) );

		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
			return mapping.findForward( "notaFiscalSimplesRemessaLista" );
		} catch ( Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			e.printStackTrace();
			return mapping.findForward( "notaFiscalSimplesRemessaLista" );
		}

		return mapping.findForward( "notaFiscalSimplesRemessaCampos" );
	}

	@Override
	public ActionForward limpar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			NotaFiscalSimplesRemessaForm meuForm = this.getCastingForm( form, request );

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

		return mapping.findForward( "notaFiscalSimplesRemessaCampos" );
	}

	@Override
	public ActionForward limparFiltro( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			NotaFiscalSimplesRemessaForm meuForm = this.getCastingForm( form, request );

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
			NotaFiscalSimplesRemessaForm meuForm = this.getCastingForm( form, request );
			meuForm.getNotaFiscalSimplesRemessa().getItens().add( meuForm.getItemPesquisar() );

			HashMap< String, String > camposOrdenacao = new HashMap<>();
			camposOrdenacao.put( "data", "DESC" );

			List< NotaFiscalSimplesRemessaDTO > encontrados = NotaFiscalSimplesRemessaFacade.getInstance().filtrar( meuForm.getPaginacao(), camposOrdenacao, meuForm.getNotaFiscalSimplesRemessa(), meuForm.getDataInicial(), meuForm.getDataFinal() );

			meuForm.limparLista();

			meuForm.getNotas().addAll( encontrados );

		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
		} catch ( Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			e.printStackTrace();
		}

		return mapping.findForward( "notaFiscalSimplesRemessaLista" );
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
	public ActionForward selecionarFornecedorAutoComplete( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			NotaFiscalSimplesRemessaForm meuForm = this.getCastingForm( form, request );

			FornecedorJuridicoDTO filtrar = new FornecedorJuridicoDTO();
			filtrar.setNome( meuForm.getNotaFiscalSimplesRemessa().getNotaFiscalVendaFutura().getPedido().getFornecedor().getNome() );

			HashMap< String, String > camposOrdenacao = new HashMap<>();
			camposOrdenacao.put( "nome", "ASC" );

			List< FornecedorJuridicoDTO > encontrados = FornecedorFacade.getInstance().filtrar( Paginacao.NAO, camposOrdenacao, filtrar );

			JSONObject json = new JSONObject();
			JSONObject map = null;
			JSONArray arr = new JSONArray();

			for ( FornecedorJuridicoDTO dtoCorrente : encontrados ) {

				map = new JSONObject();
				map.put( "value", dtoCorrente.getNome() );
				map.put( "data", dtoCorrente.getId() );
				map.put( "telefone", dtoCorrente.getTelefone() );
				map.put( "observacao", dtoCorrente.getObservacao() );
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
	public ActionForward selecionarReceptorAutoComplete( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			NotaFiscalSimplesRemessaForm meuForm = this.getCastingForm( form, request );

			FuncionarioDTO tipoFiltrar = new FuncionarioDTO();
			tipoFiltrar.getPessoaFisica().setRazaoSocial( meuForm.getNomeFuncionarioReceptor() );

			HashMap< String, String > camposOrdenacao = new HashMap<>();
			camposOrdenacao.put( "pessoaFisica.razaoSocial", "ASC" );

			List< FuncionarioDTO > encontrados = FuncionarioFacade.getInstance().filtrar( Paginacao.NAO, camposOrdenacao, tipoFiltrar );

			JSONObject json = new JSONObject();
			JSONObject map = null;
			JSONArray arr = new JSONArray();

			for ( FuncionarioDTO dtoCorrente : encontrados ) {

				map = new JSONObject();
				map.put( "value", dtoCorrente.getPessoaFisica().getRazaoSocial() );
				map.put( "data", dtoCorrente.getUsuario().getLogin() );
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
	public ActionForward selecionarNotaFiscalVendaFuturaAutoComplete( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			NotaFiscalSimplesRemessaForm meuForm = this.getCastingForm( form, request );

			HashMap< String, String > camposOrdenacao = new HashMap<>();
			camposOrdenacao.put( "numero", "ASC" );
			List< NotaFiscalVendaDTO > encontrados = NotaFiscalVendaFacade.getInstance().filtrarAbertosAndamentos( meuForm.getNotaFiscalSimplesRemessa().getNotaFiscalVendaFutura().getNumero(), NotaFiscalVendaDTO.TIPO_FUTURA, camposOrdenacao );

			JSONObject json = new JSONObject();
			JSONObject map = null;
			JSONArray arr = new JSONArray();

			for ( NotaFiscalVendaDTO dtoCorrente : encontrados ) {

				map = new JSONObject();
				map.put( "value", dtoCorrente.getNumero() );
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

	public ActionForward selecionarNotaFiscalVendaFuturaById( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			NotaFiscalSimplesRemessaForm meuForm = this.getCastingForm( form, request );

			NotaFiscalVendaDTO encontrado = NotaFiscalVendaFacade.getInstance().filtrarPorId( meuForm.getNotaFiscalSimplesRemessa().getNotaFiscalVendaFutura().getId() );

			meuForm.limparLista();
			meuForm.getItensNotasFiscaisVendasFuturasRestantes().clear();
			meuForm.getNotaFiscalSimplesRemessa().getItens().clear();

			meuForm.getNotaFiscalSimplesRemessa().setNotaFiscalVendaFutura( encontrado );

			meuForm.getItensNotasFiscaisVendasFuturasRestantes().addAll( this.montarTabelaItensNotasFiscaisVendasFuturasRestantes( meuForm.getNotaFiscalSimplesRemessa().getNotaFiscalVendaFutura().getId(), meuForm.getNotaFiscalSimplesRemessa().getNotaFiscalVendaFutura().getItens() ) );

			Collections.sort( meuForm.getNotaFiscalSimplesRemessa().getItens() );
			Collections.sort( meuForm.getItensNotasFiscaisVendasFuturasRestantes() );

		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
			return mapping.findForward( "notaFiscalSimplesRemessaLista" );
		} catch ( Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			e.printStackTrace();
			return mapping.findForward( "notaFiscalSimplesRemessaLista" );
		}

		return mapping.findForward( "notaFiscalSimplesRemessaCampos" );
	}

	private ArrayList< ItemNotaFiscalVendaDTO > montarTabelaItensNotasFiscaisVendasFuturasRestantes( String idNotaFiscalVendaFutura, ArrayList< ItemNotaFiscalVendaDTO > itensNotaFiscalVenda ) throws ApplicationException {

		ArrayList< ItemNotaFiscalVendaDTO > itensRemover = new ArrayList<>();

		for ( ItemNotaFiscalVendaDTO itemNotaFiscalVendaCorrente : itensNotaFiscalVenda ) {
			String quantidadeFaltaEntregar = NotaFiscalSimplesRemessaFacade.getInstance().getTotalFaltaEntregar( itemNotaFiscalVendaCorrente.getItemPedido().getProduto().getId(), idNotaFiscalVendaFutura );

			itemNotaFiscalVendaCorrente.setQuantidadeRestante( quantidadeFaltaEntregar );
			itemNotaFiscalVendaCorrente.setQuantidade( quantidadeFaltaEntregar );

			if ( Utilidades.parseBigDecimal( quantidadeFaltaEntregar ).compareTo( BigDecimal.ZERO ) <= 0 ) {
				itensRemover.add( itemNotaFiscalVendaCorrente );
			}
		}
		itensNotaFiscalVenda.removeAll( itensRemover );

		return itensNotaFiscalVenda;
	}

	public ActionForward adicionarItemRecebido( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			NotaFiscalSimplesRemessaForm meuForm = this.getCastingForm( form, request );

			NotaFiscalSimplesRemessaFacade.getInstance().adicionarItemRecebido( meuForm.getNotaFiscalSimplesRemessa(), meuForm.getItensNotasFiscaisVendasFuturasRestantes(), meuForm.getIdItemNotaFiscalVendaFuturaReceber(), meuForm.getQuantidadeItensAdicionar() );

			meuForm.setIdItemNotaFiscalVendaFuturaReceber( null );

			Collections.sort( meuForm.getNotaFiscalSimplesRemessa().getItens() );
			Collections.sort( meuForm.getItensNotasFiscaisVendasFuturasRestantes() );

			meuForm.limparCamposItem();
		} catch ( Throwable e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { "Erro inesperado![" + e.getMessage() + "]" } ) );
			logger.error( MensagensResourcesApplication.getString( "log.erro.inesperado", new String[ ] { this.getClass().getSimpleName() + " [inserir]", e.toString() } ) );
			e.printStackTrace();
		}
		return mapping.findForward( "notaFiscalSimplesRemessaCampos" );
	}

	public ActionForward removerItemRecebido( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			NotaFiscalSimplesRemessaForm meuForm = this.getCastingForm( form, request );

			NotaFiscalSimplesRemessaFacade.getInstance().removerItemRecebido( meuForm.getNotaFiscalSimplesRemessa(), meuForm.getItensNotasFiscaisVendasFuturasRestantes(), meuForm.getIdItemNotaFiscalVendaFuturaRetornar(), meuForm.getQuantidadeItensRetornar() );

			meuForm.setIdItemNotaFiscalVendaFuturaRetornar( null );

			Collections.sort( meuForm.getNotaFiscalSimplesRemessa().getItens() );
			Collections.sort( meuForm.getItensNotasFiscaisVendasFuturasRestantes() );

			meuForm.limparCamposItem();
		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( MensagensResourcesApplication.getString( "falha", new String[ ] { this.getClass().getSimpleName() + "-" + e.getMessage(), e.toString() } ) );
			e.printStackTrace();
		} catch ( Throwable e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { "Erro inesperado![" + e.getMessage() + "]" } ) );
			logger.error( MensagensResourcesApplication.getString( "log.erro.inesperado", new String[ ] { this.getClass().getSimpleName() + " [inserir]", e.toString() } ) );
			e.printStackTrace();
		}

		return mapping.findForward( "notaFiscalSimplesRemessaCampos" );
	}

	public ActionForward atualizarPrecosCusto( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		return mapping.findForward( "itensEntreteguesNotaFiscalSimplesRemessaTabela" );
	}

	public ActionForward limparDadosNotaFiscalVendaFutura( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			NotaFiscalSimplesRemessaForm meuForm = this.getCastingForm( form, request );

			meuForm.setItensNotasFiscaisVendasFuturasRestantes( null );
			meuForm.getNotaFiscalSimplesRemessa().getItens().clear();
			meuForm.getNotaFiscalSimplesRemessa().setNotaFiscalVendaFutura( null );

			meuForm.limparCamposItem();

		} catch ( Throwable e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { "Erro inesperado [ " + e.getMessage() + " ]" } ) );
			logger.error( MensagensResourcesApplication.getString( "log.erro.inesperado", new String[ ] { this.getClass().getSimpleName() + " [inserir]", e.toString() } ) );
			e.printStackTrace();
		}

		return mapping.findForward( "notaFiscalSimplesRemessaCampos" );
	}
}