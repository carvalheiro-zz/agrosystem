package br.com.srcsoftware.sistema.combustivel.abastecimento;

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

import br.com.srcsoftware.modular.manager.abstracts.AbstractCRUDAction;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.pessoa.funcionario.FuncionarioDTO;
import br.com.srcsoftware.modular.manager.pessoa.funcionario.FuncionarioFacade;
import br.com.srcsoftware.modular.manager.utilidades.MensagensResourcesApplication;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.modular.manager.view.utilidades.Messages;
import br.com.srcsoftware.sistema.estoque.estoque.EstoqueFacade;
import br.com.srcsoftware.sistema.estoque.estoque.pojo.EstoquePOJO;
import br.com.srcsoftware.sistema.manutencao.veiculo.VeiculoDTO;
import br.com.srcsoftware.sistema.manutencao.veiculo.VeiculoFacade;
import br.com.srcsoftware.sistema.produto.produto.ProdutoDTO;
import br.com.srcsoftware.sistema.produto.produto.ProdutoFacade;

public final class AbastecimentoAction extends AbstractCRUDAction< AbastecimentoForm > {
	
	@Override
	public ActionForward abrirListagem( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			
			AbastecimentoForm meuForm = this.getCastingForm( form, request );
			
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
			AbastecimentoForm meuForm = this.getCastingForm( form, request );
			
			meuForm.limparTudo( request );
			
			meuForm.getPaginacao().inicializar();
			
			return mapping.findForward( "abastecimentoCampos" );
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
			AbastecimentoForm meuForm = this.getCastingForm( form, request );
			
			AbastecimentoDTO dtoPersistir = meuForm.getAbastecimento();
			
			AbastecimentoFacade.getInstance().inserir( meuForm.getUsuarioSessaoPOJO( request ), dtoPersistir );
			
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
		
		return mapping.findForward( "abastecimentoCampos" );
	}
	
	@Override
	public ActionForward alterar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			AbastecimentoForm meuForm = this.getCastingForm( form, request );
			
			AbastecimentoDTO dtoPersistir = meuForm.getAbastecimento();
			
			AbastecimentoFacade.getInstance().alterar( meuForm.getUsuarioSessaoPOJO( request ), dtoPersistir );
			
			meuForm.limparTudo( request );
			
			this.addMessages( request, Messages.createMessages( "sucesso" ) );
			
		} catch ( ApplicationException | Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
			request.getSession().setAttribute( "erroAjax", e.getMessage() );
			request.getSession().removeAttribute( "sucessoAjax" );
		}
		return mapping.findForward( "abastecimentoCampos" );
	}
	
	@Override
	public ActionForward excluir( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			AbastecimentoForm meuForm = this.getCastingForm( form, request );
			
			AbastecimentoFacade.getInstance().excluir( AbastecimentoFacade.getInstance().filtrarPorId( meuForm.getIdRegistroExcluir() ) );
			
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
		return filtrar( mapping, form, request, response );
	}
	
	@Override
	public ActionForward filtrar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			AbastecimentoForm meuForm = this.getCastingForm( form, request );
			
			meuForm.getPaginacao().inicializar();
			
			HashMap< String, String > camposOrdenacao = new HashMap<>();
			camposOrdenacao.put( "data", "DESC" );
			
			List< AbastecimentoDTO > encontrados = AbastecimentoFacade.getInstance().filtrar( meuForm.getPaginacao(), camposOrdenacao, meuForm.getAbastecimento(), meuForm.getDataInicial(),
				meuForm.getDataFinal() );
			
			List< AbastecimentoDTO > encontradosParaTotais = AbastecimentoFacade.getInstance().filtrar( Paginacao.NAO, camposOrdenacao, meuForm.getAbastecimento(), meuForm.getDataInicial(),
				meuForm.getDataFinal() );
			meuForm.calcularTotais( encontradosParaTotais );
			
			meuForm.limparLista();
			
			meuForm.getAbastecimentos().addAll( encontrados );
			
		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
		} catch ( Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ e.getMessage() } ) );
			logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			e.printStackTrace();
		}
		
		return mapping.findForward( "abastecimentoLista" );
	}
	
	@Override
	public ActionForward selecionar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			AbastecimentoForm meuForm = this.getCastingForm( form, request );
			
			AbastecimentoDTO encontrado = AbastecimentoFacade.getInstance().filtrarPorId( meuForm.getAbastecimento().getId() );
			
			meuForm.limparTudo( request );
			
			meuForm.setAbastecimento( encontrado );
			
			EstoquePOJO estoque = EstoqueFacade.getInstance().filtrarSaldoPorProduto( meuForm.getAbastecimento().getProduto().getId() );
			meuForm.setEstoque( estoque.getSaldo() );
			
		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
			return mapping.findForward( "abastecimentoLista" );
		} catch ( Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ e.getMessage() } ) );
			logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			e.printStackTrace();
			return mapping.findForward( "abastecimentoLista" );
		}
		
		return mapping.findForward( "abastecimentoCampos" );
	}
	
	@Override
	public ActionForward limpar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			AbastecimentoForm meuForm = this.getCastingForm( form, request );
			
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
		
		return mapping.findForward( "abastecimentoCampos" );
	}
	
	@Override
	public ActionForward limparFiltro( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			AbastecimentoForm meuForm = this.getCastingForm( form, request );
			
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
			AbastecimentoForm meuForm = this.getCastingForm( form, request );
			
			HashMap< String, String > camposOrdenacao = new HashMap<>();
			camposOrdenacao.put( "data", "DESC" );
			
			List< AbastecimentoDTO > encontrados = AbastecimentoFacade.getInstance().filtrar( meuForm.getPaginacao(), camposOrdenacao, meuForm.getAbastecimento(), meuForm.getDataInicial(),
				meuForm.getDataFinal() );
			
			meuForm.limparLista();
			
			meuForm.getAbastecimentos().addAll( encontrados );
			
		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ e.getMessage() } ) );
			logger.error( e.getMessage(), e );
			e.printStackTrace();
		} catch ( Exception e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[]{ e.getMessage() } ) );
			logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			e.printStackTrace();
		}
		
		return mapping.findForward( "abastecimentoLista" );
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
	public ActionForward selecionarProdutoAutoComplete( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		
		try {
			AbastecimentoForm meuForm = this.getCastingForm( form, request );
			
			ProdutoDTO filtrar = new ProdutoDTO();
			filtrar.setNome( meuForm.getAbastecimento().getProduto().getNome() );
			filtrar.getTipo().setNome( "Combustivel" );
			
			HashMap< String, String > camposOrdenacao = new HashMap<>();
			camposOrdenacao.put( "nome", "ASC" );
			
			List< ProdutoDTO > encontrados = ProdutoFacade.getInstance().filtrar( Paginacao.NAO, camposOrdenacao, filtrar );
			
			JSONObject json = new JSONObject();
			JSONObject map = null;
			JSONArray arr = new JSONArray();
			
			for ( ProdutoDTO dtoCorrente : encontrados ) {
				
				EstoquePOJO estoque = EstoqueFacade.getInstance().filtrarSaldoPorProduto( dtoCorrente.getId() );
				
				BigDecimal saldo = Utilidades.parseBigDecimal( estoque.getSaldo() );
				
				if ( saldo.compareTo( BigDecimal.ZERO ) > 0 ) {
					map = new JSONObject();
					map.put( "value", dtoCorrente.getNomeCompleto() );
					map.put( "localizacao", dtoCorrente.getLocalizacao() );
					map.put( "custoMedio", ( dtoCorrente == null ? "0.00" : estoque.getCustoMedio() ) );
					map.put( "estoque", ( dtoCorrente == null ? "0.00" : estoque.getSaldo() ) );
					map.put( "data", dtoCorrente.getId() );
					arr.add( map );
				}
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
	
	public ActionForward calcularSaldoPorProduto( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			AbastecimentoForm meuForm = this.getCastingForm( form, request );
			
			JSONObject json = new JSONObject();
			
			EstoquePOJO estoque = EstoqueFacade.getInstance().filtrarSaldoPorProduto( meuForm.getAbastecimento().getProduto().getId() );
			
			json = new JSONObject();
			json.put( "custoMedio", estoque.getCustoMedio() );
			json.put( "estoque", estoque.getSaldo() );
			
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
	
	public ActionForward calcularDisponibilidadeQuantidadeEstoque( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			AbastecimentoForm meuForm = this.getCastingForm( form, request );
			
			String quantidade = meuForm.getAbastecimento().getQuantidade();
			String inEstoque = meuForm.getEstoque();
			
			BigDecimal quantidade_ = BigDecimal.ZERO;
			BigDecimal inEstoque_ = BigDecimal.ZERO;
			
			if ( !Utilidades.isNuloOuVazio( quantidade ) ) {
				quantidade_ = Utilidades.parseBigDecimal( quantidade );
			}
			if ( !Utilidades.isNuloOuVazio( inEstoque ) ) {
				inEstoque_ = Utilidades.parseBigDecimal( inEstoque );
			}
			
			JSONObject json = new JSONObject();
			
			json = new JSONObject();
			json.put( "estoqueDisponivel", ( quantidade_.compareTo( inEstoque_ ) <= 0 ? "true" : "false" ) );
			
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
	
	@SuppressWarnings("unchecked")
	public ActionForward selecionarVeiculoAutoComplete( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		
		try {
			AbastecimentoForm meuForm = this.getCastingForm( form, request );
			
			VeiculoDTO veiculoFiltrar = new VeiculoDTO();
			veiculoFiltrar.setNomeCompleto( meuForm.getAbastecimento().getVeiculo().getNomeCompleto() );
			
			HashMap< String, String > camposOrdenacao = new HashMap<>();
			camposOrdenacao.put( "nome", "ASC" );
			
			List< VeiculoDTO > encontrados = VeiculoFacade.getInstance().filtrar( Paginacao.NAO, camposOrdenacao, veiculoFiltrar );
			
			JSONObject json = new JSONObject();
			JSONObject map = null;
			JSONArray arr = new JSONArray();
			
			for ( VeiculoDTO dtoCorrente : encontrados ) {
				
				map = new JSONObject();
				map.put( "value", dtoCorrente.getNomeCompleto() );
				map.put( "data", dtoCorrente.getId() );
				map.put( "nome", dtoCorrente.getNome() );
				map.put( "tipo", dtoCorrente.getTipo() );
				map.put( "codigo", dtoCorrente.getCodigo() );
				map.put( "modelo", dtoCorrente.getModelo() );
				map.put( "anoFabricacao", dtoCorrente.getAnoFabricacao() );
				map.put( "numeroChassis", dtoCorrente.getNumeroChassis() );
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
	public ActionForward selecionarAlmoxarifeAutoComplete( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		
		try {
			AbastecimentoForm meuForm = this.getCastingForm( form, request );
			
			FuncionarioDTO tipoFiltrar = new FuncionarioDTO();
			tipoFiltrar.getPessoaFisica().setRazaoSocial( meuForm.getAbastecimento().getAlmoxarife().getPessoaFisica().getRazaoSocial() );
			
			HashMap< String, String > camposOrdenacao = new HashMap<>();
			camposOrdenacao.put( "pessoaFisica.razaoSocial", "ASC" );
			
			List< FuncionarioDTO > encontrados = FuncionarioFacade.getInstance().filtrar( Paginacao.NAO, camposOrdenacao, tipoFiltrar );
			
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
	public ActionForward selecionarRequerenteAutoComplete( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		
		try {
			AbastecimentoForm meuForm = this.getCastingForm( form, request );
			
			FuncionarioDTO tipoFiltrar = new FuncionarioDTO();
			tipoFiltrar.getPessoaFisica().setRazaoSocial( meuForm.getAbastecimento().getRequerente().getPessoaFisica().getRazaoSocial() );
			
			HashMap< String, String > camposOrdenacao = new HashMap<>();
			camposOrdenacao.put( "pessoaFisica.razaoSocial", "ASC" );
			
			List< FuncionarioDTO > encontrados = FuncionarioFacade.getInstance().filtrar( Paginacao.NAO, camposOrdenacao, tipoFiltrar );
			
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
	
}