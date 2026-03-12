package br.com.srcsoftware.sistema.pedido.pedido;

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
import br.com.srcsoftware.sistema.pessoa.fornecedor.juridico.FornecedorFacade;
import br.com.srcsoftware.sistema.pessoa.fornecedor.juridico.FornecedorJuridicoDTO;
import br.com.srcsoftware.sistema.produto.produto.ProdutoDTO;
import br.com.srcsoftware.sistema.produto.produto.ProdutoFacade;

public final class PedidoAction extends AbstractCRUDAction< PedidoForm >{

	@Override
	public ActionForward abrirListagem( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {

			PedidoForm meuForm = this.getCastingForm( form, request );

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
			PedidoForm meuForm = this.getCastingForm( form, request );

			meuForm.limparTudo( request );

			meuForm.getPaginacao().inicializar();

			meuForm.getPedido().setData( DateUtil.parseLocalDate( LocalDate.now() ) );
			meuForm.getPedido().setStatus( PedidoDTO.STATUS_ABERTO );

			return mapping.findForward( "pedidoCampos" );
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
			PedidoForm meuForm = this.getCastingForm( form, request );

			PedidoDTO dtoPersistir = meuForm.getPedido();

			PedidoFacade.getInstance().inserir( meuForm.getUsuarioSessaoPOJO( request ), dtoPersistir );

			this.addMessages( request, Messages.createMessages( "sucesso" ) );
			request.getSession().setAttribute( "sucessoAjax", "Operação realizada com sucesso!" );

		} catch ( ApplicationException | Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
			request.getSession().setAttribute( "erroAjax", e.getMessage() );
			request.getSession().removeAttribute( "sucessoAjax" );
		}

		return abrirCadastro( mapping, form, request, response );
	}

	@Override
	public ActionForward alterar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			PedidoForm meuForm = this.getCastingForm( form, request );

			PedidoDTO dtoPersistir = meuForm.getPedido();

			PedidoFacade.getInstance().alterar( meuForm.getUsuarioSessaoPOJO( request ), dtoPersistir );

			this.addMessages( request, Messages.createMessages( "sucesso" ) );

		} catch ( ApplicationException | Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
			request.getSession().setAttribute( "erroAjax", e.getMessage() );
			request.getSession().removeAttribute( "sucessoAjax" );
		}
		return abrirCadastro( mapping, form, request, response );
	}

	@Override
	public ActionForward excluir( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			PedidoForm meuForm = this.getCastingForm( form, request );

			PedidoFacade.getInstance().excluir( PedidoFacade.getInstance().filtrarPorId( meuForm.getIdRegistroExcluir() ) );

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
			PedidoForm meuForm = this.getCastingForm( form, request );
			meuForm.getPedido().getItens().add( meuForm.getItemPesquisar() );

			meuForm.getPaginacao().inicializar();

			HashMap< String, String > camposOrdenacao = new HashMap<>();
			camposOrdenacao.put( "data", "DESC" );

			List< PedidoDTO > encontrados = PedidoFacade.getInstance().filtrar( meuForm.getPaginacao(), camposOrdenacao, meuForm.getPedido(), meuForm.getDataInicial(), meuForm.getDataFinal() );

			meuForm.limparLista();

			meuForm.getPedidos().addAll( encontrados );
		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
		} catch ( Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			e.printStackTrace();
		}

		return mapping.findForward( "pedidoLista" );
	}

	@Override
	public ActionForward selecionar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			PedidoForm meuForm = this.getCastingForm( form, request );

			PedidoDTO encontrado = PedidoFacade.getInstance().filtrarPorId( meuForm.getPedido().getId() );

			meuForm.limparTudo( request );

			meuForm.setPedido( encontrado );

			/*for ( ItemPedidoDTO itemCorrente : encontrado.getItens() ) {
				itemCorrente.setQuantidadeRestante( Utilidades.parseBigDecimal( EstoqueFacade.getInstance().calcularValoresRestantesEntregarPedido( Long.valueOf( itemCorrente.getProduto().getId() ), Long.valueOf( encontrado.getId() ) ) ) );
			}
			
			if ( BigDecimal.ZERO.compareTo( EstoqueFacade.getInstance().calcularValoresRestantesEntregarPedido( Long.valueOf( encontrado.getId() ) ) ) != 0 ) {
				meuForm.setPermiteEmitirNota( true );
			} else {
				meuForm.setPermiteEmitirNota( false );
			}*/

		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
			return mapping.findForward( "pedidoLista" );
		} catch ( Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			e.printStackTrace();
			return mapping.findForward( "pedidoLista" );
		}

		return mapping.findForward( "pedidoCampos" );
	}

	@Override
	public ActionForward limpar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		return abrirCadastro( mapping, form, request, response );
	}

	@Override
	public ActionForward limparFiltro( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		return abrirListagem( mapping, form, request, response );
	}

	/**
	 * M�todo respons�vel pelo funcionamento da Paginação
	 */
	public ActionForward paginar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			PedidoForm meuForm = this.getCastingForm( form, request );
			meuForm.getPedido().getItens().add( meuForm.getItemPesquisar() );

			HashMap< String, String > camposOrdenacao = new HashMap<>();
			camposOrdenacao.put( "data", "DESC" );

			List< PedidoDTO > encontrados = PedidoFacade.getInstance().filtrar( meuForm.getPaginacao(), camposOrdenacao, meuForm.getPedido(), meuForm.getDataInicial(), meuForm.getDataFinal() );

			meuForm.limparLista();

			meuForm.getPedidos().addAll( encontrados );
		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
		} catch ( Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			e.printStackTrace();
		}

		return mapping.findForward( "pedidoLista" );
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
	public ActionForward selecionarProdutoAutoComplete( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			PedidoForm meuForm = this.getCastingForm( form, request );

			ProdutoDTO filtrar = new ProdutoDTO();
			filtrar.setNome( meuForm.getItemAdicionar().getProduto().getNome() );

			HashMap< String, String > camposOrdenacao = new HashMap<>();
			camposOrdenacao.put( "nome", "ASC" );

			List< ProdutoDTO > encontrados = ProdutoFacade.getInstance().filtrar( Paginacao.NAO, camposOrdenacao, filtrar );

			JSONObject json = new JSONObject();
			JSONObject map = null;
			JSONArray arr = new JSONArray();

			for ( ProdutoDTO dtoCorrente : encontrados ) {
				map = new JSONObject();
				map.put( "value", dtoCorrente.getNome().concat( " - " ).concat( dtoCorrente.getMarca().getNome() ) );
				map.put( "unidadeMedida", dtoCorrente.getUnidadeMedida().getSigla() );

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
	public ActionForward selecionarFornecedorAutoComplete( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			PedidoForm meuForm = this.getCastingForm( form, request );

			FornecedorJuridicoDTO filtrar = new FornecedorJuridicoDTO();
			filtrar.setNome( meuForm.getPedido().getFornecedor().getNome() );

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

	public ActionForward adicionarItem( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			PedidoForm meuForm = this.getCastingForm( form, request );

			PedidoFacade.getInstance().adicionarItem( meuForm.getItemAdicionar(), meuForm.getPedido() );

			meuForm.setItemAdicionar( null );

			request.getSession().removeAttribute( "erroAjax" );

		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
			request.getSession().removeAttribute( "erroAjax" );
		} catch ( Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			e.printStackTrace();
			request.getSession().removeAttribute( "erroAjax" );
		}

		return mapping.findForward( "itensTabela" );
	}

	public ActionForward removerItem( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			PedidoForm meuForm = this.getCastingForm( form, request );

			PedidoFacade.getInstance().removerItem( meuForm.getPedido(), meuForm.getIdItemRemover() );

			meuForm.setItemAdicionar( null );

		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
			request.getSession().removeAttribute( "erroAjax" );
		} catch ( Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );
			logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			e.printStackTrace();
			request.getSession().removeAttribute( "erroAjax" );
		}

		return mapping.findForward( "itensTabela" );
	}

	public ActionForward atualizarValorPedido( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		return mapping.findForward( "itensTabela" );
	}

	public ActionForward atualizarQuantidade( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		return mapping.findForward( "itensTabela" );
	}

	public ActionForward atualizarPrecosCusto( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		return mapping.findForward( "itensTabela" );
	}
}