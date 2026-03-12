package br.com.srcsoftware.sistema.notafiscal.rateio.despesa;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.enuns.ManutencaoEnum;
import br.com.srcsoftware.exceptions.Moeda;
import br.com.srcsoftware.factory.ModelFactory;
import br.com.srcsoftware.modular.financeiro.contapagar.ContaPagarDTO;
import br.com.srcsoftware.modular.financeiro.contapagar.ContaPagarServiceImpl;
import br.com.srcsoftware.modular.financeiro.enums.SituacaoTituloEnum;
import br.com.srcsoftware.modular.financeiro.enums.TipoTituloEnum;
import br.com.srcsoftware.modular.financeiro.formapagamento.FormaPagamentoDTO;
import br.com.srcsoftware.modular.financeiro.formapagamento.FormaPagamentoFacade;
import br.com.srcsoftware.modular.financeiro.titulo.titulopagar.TituloPagarDTO;
import br.com.srcsoftware.modular.financeiro.titulo.titulopagar.TituloPagarFacade;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.exceptions.ModelFactoryException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;
import br.com.srcsoftware.modular.manager.utilidades.DateUtil;
import br.com.srcsoftware.modular.manager.utilidades.MensagensResourcesApplication;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.notafiscal.rateio.NotaFiscalRateioDAOImpl;
import br.com.srcsoftware.sistema.notafiscal.rateio.NotaFiscalRateioDAOInterface;
import br.com.srcsoftware.sistema.notafiscal.rateio.NotaFiscalRateioDTO;
import br.com.srcsoftware.sistema.notafiscal.rateio.NotaFiscalRateioPO;
import br.com.srcsoftware.sistema.notafiscal.rateio.centrocusto.CentroCustoPO;
import br.com.srcsoftware.sistema.notafiscal.rateio.itemnotafiscalrateio.ItemNotaFiscalRateioDTO;
import br.com.srcsoftware.sistema.notafiscal.rateio.itemnotafiscalrateio.ItemNotaFiscalRateioPO;
import br.com.srcsoftware.sistema.pessoa.fornecedor.juridico.FornecedorJuridicoPO;
import br.com.srcsoftware.sistema.safra.SafraDTO;
import br.com.srcsoftware.sistema.safra.SafraFacade;
import br.com.srcsoftware.sistema.safra.cultura.CulturaDTO;
import br.com.srcsoftware.sistema.safra.cultura.CulturaFacade;
import br.com.srcsoftware.sistema.safra.setor.SetorDTO;
import br.com.srcsoftware.sistema.safra.setor.SetorFacade;

public final class NotaFiscalRateioDespesaServiceImpl extends ContaPagarServiceImpl implements NotaFiscalRateioDespesaServiceInterface{

	private transient Logger logger = Logger.getLogger( this.getClass().getName() );

	private NotaFiscalRateioDAOInterface daoInterface;

	private ModelFactory< NotaFiscalRateioPO, NotaFiscalRateioDTO > modelFactory = new ModelFactory<>();

	public NotaFiscalRateioDespesaServiceImpl(){
		this.daoInterface = new NotaFiscalRateioDAOImpl();
	}

	private String gerenciarQuantidadeParcelas( String idFormaPagamento ) throws ApplicationException {
		if ( Utilidades.isNuloOuVazio( idFormaPagamento ) ) {
			return "0";
		}
		FormaPagamentoDTO formaPagamento = FormaPagamentoFacade.getInstance().filtrarPorId( idFormaPagamento );

		return ( formaPagamento.getParcelas() );
	}

	private void gerenciarAssociacoes( UsuarioSessaoPOJO usuarioSessaoPOJO,
	        NotaFiscalRateioDTO notaFiscalRateio,
	        String usuarioCriacao,
	        String dataCriacao ) throws ApplicationException, IllegalArgumentException, IllegalAccessException, ModelFactoryException {
		if ( Utilidades.isNuloOuVazio( notaFiscalRateio.getItens() ) ) {
			throw new ApplicationException( "Nenhum item foi adicionado!" );
		}
		super.gerenciarAssociacoes( usuarioSessaoPOJO, notaFiscalRateio.getContaPagar() );
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, NotaFiscalRateioDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			String usuarioAlteracao = usuarioSessaoPOJO.getUsuario().getLogin();
			LocalDateTime dataAlteracao = LocalDateTime.now();

			dto.getContaPagar().setNumero( "".concat( dto.getNumero() + "" ).concat( dto.getNumeroRecibo() + "" ) );

			dto.getContaPagar().setQuantidadeParcelas( gerenciarQuantidadeParcelas( dto.getContaPagar().getFormaPagamento().getId() ) );

			dto.setContaPagar( gerarParcelas( usuarioSessaoPOJO, dto.getContaPagar(), dto.getDescricaoOrigemTitulos(), dto.getFornecedor().getNome() ) );

			NotaFiscalRateioPO po = this.modelFactory.getPO( NotaFiscalRateioPO.class, dto );
			po.getContaPagar().setarDadosAuditoria( usuarioAlteracao, dataAlteracao );
			po.getContaPagar().setCancelado( false );

			po.setCliente( null );
			po.setContaReceber( null );

			if ( !isValidoValorTotalParcelas( po.getContaPagar() ) ) {
				throw new ApplicationException( "A soma dos valores das parcelas não batem com o valor da NF!" );
			}

			this.gerenciarFornecedor( hibernate, po, usuarioAlteracao, dataAlteracao );

			this.daoInterface.inserir( hibernate, po );

			for ( ItemNotaFiscalRateioPO itemCorrente : po.getItens() ) {
				this.gerenciarCentroCustoDespesas( itemCorrente );
			}

			hibernate.commitTransacao();
		} catch ( ApplicationException e ) {
			hibernate.rollbackTransacao();

			this.logger.error( e.getMessage(), e );

			throw e;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();

			this.logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );

			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, NotaFiscalRateioDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			String usuarioAlteracao = usuarioSessaoPOJO.getUsuario().getLogin();
			LocalDateTime dataAlteracao = LocalDateTime.now();

			dto.getContaPagar().setNumero( "".concat( dto.getNumero() + "" ).concat( dto.getNumeroRecibo() + "" ) );

			dto.getContaPagar().setQuantidadeParcelas( gerenciarQuantidadeParcelas( dto.getContaPagar().getFormaPagamento().getId() ) );

			this.gerenciarParcelas( usuarioSessaoPOJO, dto );

			this.gerenciarAssociacoes( usuarioSessaoPOJO, dto, usuarioAlteracao, DateUtil.parseLocalDateTime( dataAlteracao ) );

			NotaFiscalRateioPO po = this.modelFactory.getPO( NotaFiscalRateioPO.class, dto );
			po.getContaPagar().setarDadosAuditoria( usuarioAlteracao, dataAlteracao );

			po.setCliente( null );
			po.setContaReceber( null );

			if ( !isValidoValorTotalParcelas( po.getContaPagar() ) ) {
				throw new ApplicationException( "A soma dos valores das parcelas não batem com o valor da NF!" );
			}

			this.gerenciarFornecedor( hibernate, po, usuarioAlteracao, dataAlteracao );

			this.daoInterface.alterar( hibernate, po );

			for ( ItemNotaFiscalRateioPO itemCorrente : po.getItens() ) {
				this.gerenciarCentroCustoDespesas( itemCorrente );
			}

			hibernate.commitTransacao();
		} catch ( ApplicationException e ) {
			hibernate.rollbackTransacao();

			this.logger.error( e.getMessage(), e );

			throw e;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();

			this.logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );

			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}

	}

	private void gerenciarParcelas( UsuarioSessaoPOJO usuarioSessaoPOJO, NotaFiscalRateioDTO dto ) throws ApplicationException {

		ContaPagarDTO contaPagarOld = this.filtrarPorId( dto.getId() ).getContaPagar();

		dto.setContaPagar( super.gerenciarParcelas( usuarioSessaoPOJO, dto.getContaPagar(), contaPagarOld, dto.getDescricaoOrigemTitulos(), dto.getFornecedor().getNome() ) );
	}

	@Override
	public void excluir( NotaFiscalRateioDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			NotaFiscalRateioPO po = this.modelFactory.getPO( NotaFiscalRateioPO.class, dto );

			this.daoInterface.excluir( hibernate, po );

			hibernate.commitTransacao();
		} catch ( ApplicationException e ) {
			hibernate.rollbackTransacao();

			this.logger.error( e.getMessage(), e );
			throw e;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();

			this.logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public ArrayList< NotaFiscalRateioDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, NotaFiscalRateioDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException {

		try {
			NotaFiscalRateioPO poFilter = this.modelFactory.getPO( NotaFiscalRateioPO.class, dto );

			HashMap< String, ArrayList< Object > > camposBetween = new HashMap<>();

			if ( !Utilidades.isNuloOuVazio( dataInicialParam ) && !Utilidades.isNuloOuVazio( dataFinalParam ) ) {
				camposBetween.put( "contaPagar.data", new ArrayList< Object >( Arrays.asList( LocalDate.parse( dataInicialParam, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ), LocalDate.parse( dataFinalParam, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ) ) ) );
			}

			List< NotaFiscalRateioPO > encontrados = this.daoInterface.filtrar( paginacao, camposOrders, camposBetween, poFilter );

			ArrayList< NotaFiscalRateioDTO > dtosRetorno = new ArrayList<>();

			for ( NotaFiscalRateioPO poCorrente : encontrados ) {

				NotaFiscalRateioDTO notaFiscalRateio = this.modelFactory.getDTO( NotaFiscalRateioDTO.class, poCorrente );

				this.montarNotaFiscalRateioDTOComDadosTitulo( notaFiscalRateio );

				dtosRetorno.add( notaFiscalRateio );
			}

			return dtosRetorno;
		} catch ( ApplicationException e ) {

			this.logger.error( e.getMessage(), e );
			throw e;
		} catch ( ModelFactoryException e ) {
			this.logger.error( "Erro no ModelFactory" + System.lineSeparator() + e.getMessage().trim(), e );
			throw new ApplicationException( "Erro no ModelFactory" + System.lineSeparator() + e.getMessage().trim(), e );
		} catch ( Exception e ) {

			this.logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public NotaFiscalRateioDTO filtrarPorId( String id ) throws ApplicationException {

		try {

			if ( Utilidades.isNuloOuVazio( id ) ) {
				throw new ApplicationException( "Id não informado!" );
			}

			NotaFiscalRateioPO encontrado = this.daoInterface.filtrarPorId( Long.valueOf( id ) );

			NotaFiscalRateioDTO notaFiscalRateio = this.modelFactory.getDTO( NotaFiscalRateioDTO.class, encontrado );

			this.montarNotaFiscalRateioDTOComDadosTitulo( notaFiscalRateio );

			return notaFiscalRateio;
		} catch ( ApplicationException e ) {

			this.logger.error( e.getMessage(), e );
			throw e;
		} catch ( ModelFactoryException e ) {
			this.logger.error( "Erro no ModelFactory" + System.lineSeparator() + e.getMessage().trim(), e );
			throw new ApplicationException( "Erro no ModelFactory" + System.lineSeparator() + e.getMessage().trim(), e );
		} catch ( Exception e ) {

			this.logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	private void montarNotaFiscalRateioDTOComDadosTitulo( NotaFiscalRateioDTO notaFiscalRateioDTO ) throws ApplicationException {
		try {
			/** Informando na Despesa a Data de Vencimeno da primeira Parcela */
			TituloPagarDTO primeiraParcela = TituloPagarFacade.getInstance().filtrarPorNumeroTipoContaPagar( "1", TipoTituloEnum.PARCELA, notaFiscalRateioDTO.getContaPagar().getId() );
			if ( primeiraParcela == null ) {
				primeiraParcela = TituloPagarFacade.getInstance().filtrarPorNumeroTipoContaPagar( "1", TipoTituloEnum.A_VISTA, notaFiscalRateioDTO.getContaPagar().getId() );
			}
			if ( primeiraParcela == null ) {
				primeiraParcela = TituloPagarFacade.getInstance().filtrarPorNumeroTipoContaPagar( "1", TipoTituloEnum.FIXA, notaFiscalRateioDTO.getContaPagar().getId() );
			}
			if ( !Utilidades.isNuloOuVazio( primeiraParcela ) ) {
				notaFiscalRateioDTO.getContaPagar().setVencimentoPrimeiraParcela( primeiraParcela.getDataVencimento() );
			}

			/** Informando na Despesa o Valor e a Forma de pagamento da Entrada */
			TituloPagarDTO entrada = TituloPagarFacade.getInstance().filtrarPorNumeroTipoContaPagar( null, TipoTituloEnum.ENTRADA, notaFiscalRateioDTO.getContaPagar().getId() );
			if ( !Utilidades.isNuloOuVazio( entrada ) ) {
				notaFiscalRateioDTO.getContaPagar().setFormaPagamentoEntrada( entrada.getFormaPagamento() );
				notaFiscalRateioDTO.getContaPagar().setValorEntrada( entrada.getValorFinal() );
			}

			/** Informando na Despesa a Situação dela. Quitada caso todas as parcelas estejam Quitadas */
			List< TituloPagarDTO > abertos = TituloPagarFacade.getInstance().filtrarPorSituacaoContaPagar( SituacaoTituloEnum.ABERTA, notaFiscalRateioDTO.getContaPagar().getId() );
			if ( !Utilidades.isNuloOuVazio( abertos ) ) {
				notaFiscalRateioDTO.getContaPagar().setSituacao( SituacaoTituloEnum.ABERTA.getValor() );
			} else {
				notaFiscalRateioDTO.getContaPagar().setSituacao( SituacaoTituloEnum.QUITADA.getValor() );
			}

			/** Informando na NotaFiscalRateio a Data de Vencimeno da primeira Parcela */
			/*TituloPagarDTO primeiraParcela = TituloPagarFacade.getInstance().filtrarPorNumeroTipoContaPagar( "1", TipoTituloEnum.PARCELA, notaFiscalRateioDTO.getContaPagar().getId() );
			if ( primeiraParcela == null ) {
				primeiraParcela = TituloPagarFacade.getInstance().filtrarPorNumeroTipoContaPagar( "1", TipoTituloEnum.A_VISTA, notaFiscalRateioDTO.getContaPagar().getId() );
			}
			if ( !Utilidades.isNuloOuVazio( primeiraParcela ) ) {
				notaFiscalRateioDTO.getContaPagar().setVencimentoPrimeiraParcela( primeiraParcela.getDataVencimento() );
			}*/

			/** Informando na NotaFiscalRateio o Valor e a Forma de pagamento da Entrada */
			/*TituloPagarDTO entrada = TituloPagarFacade.getInstance().filtrarPorNumeroTipoContaPagar( null, TipoTituloEnum.ENTRADA, notaFiscalRateioDTO.getContaPagar().getId() );
			if ( !Utilidades.isNuloOuVazio( entrada ) ) {
				notaFiscalRateioDTO.getContaPagar().setFormaPagamentoEntrada( entrada.getFormaPagamento() );
				notaFiscalRateioDTO.getContaPagar().setValorEntrada( entrada.getValorFinal() );
			}*/

			/** Informando na NotaFiscalRateio a Situação dela. Quitada caso todas as parcelas estejam Quitadas */
			/*List< TituloPagarDTO > abertos = TituloPagarFacade.getInstance().filtrarPorSituacaoContaPagar( SituacaoTituloEnum.ABERTA, notaFiscalRateioDTO.getContaPagar().getId() );
			if ( !Utilidades.isNuloOuVazio( abertos ) ) {
				notaFiscalRateioDTO.getContaPagar().setSituacao( SituacaoTituloEnum.ABERTA.getValor() );
			} else {
				notaFiscalRateioDTO.getContaPagar().setSituacao( SituacaoTituloEnum.QUITADA.getValor() );
			}*/

		} catch ( Exception e ) {
			this.logger.error( MensagensResourcesApplication.getString( "mensagem", new String[ ] { e.getMessage() } ) );
			throw new ApplicationException( "Erro ao ocorrido: " + e.getMessage(), e );
		} catch ( ApplicationException e ) {
			this.logger.error( MensagensResourcesApplication.getString( "mensagem", new String[ ] { e.getMessage() } ) );
			throw e;
		}
	}

	private void gerenciarCentroCustoDespesas( ItemNotaFiscalRateioPO itemCorrente ) throws ApplicationException {

		CentroCustoPO centroCustoDespesaEncontrado = (CentroCustoPO) new HibernateConnection().filtrarPorId( CentroCustoPO.class, itemCorrente.getCentroCusto().getId() );
		if ( !Utilidades.isNuloOuVazio( centroCustoDespesaEncontrado ) ) {
			itemCorrente.setCentroCusto( centroCustoDespesaEncontrado );
		} else {
			itemCorrente.setCentroCusto( null );
		}

		if ( itemCorrente.getTipo().equals( ManutencaoEnum.TIPO_CULTURA.getValor() ) ) {
			itemCorrente.setSetor( null );
		} else if ( itemCorrente.getTipo().equals( ManutencaoEnum.TIPO_TUDO.getValor() ) ) {
			itemCorrente.setSetor( null );
			itemCorrente.setCultura( null );
		} else {
			if ( Utilidades.isNuloOuVazio( itemCorrente.getSafra() ) || Utilidades.isNuloOuVazio( itemCorrente.getSafra().getId() ) ) {
				throw new ApplicationException( "Safra não informada!" );
			}
			if ( Utilidades.isNuloOuVazio( itemCorrente.getSetor() ) || Utilidades.isNuloOuVazio( itemCorrente.getSetor().getId() ) ) {
				throw new ApplicationException( "Setor não informado!" );
			}
			itemCorrente.setCultura( null );
		}
	}

	@Override
	public void adicionarItem( ItemNotaFiscalRateioDTO item, NotaFiscalRateioDTO nfr ) throws ApplicationException {

		try {
			item.getIdTemp();
			if ( Utilidades.isNuloOuVazio( item.getCentroCusto().getId() ) ) {
				throw new ApplicationException( "Centro de Custo de Despesa/Despesa informado [ " + item.getCentroCusto().getDescricao() + " ] não Cadastrado!" );
			}

			if ( Utilidades.isNuloOuVazio( item.getCentroCusto().getId() ) || Utilidades.isNuloOuVazio( item.getDescricao() ) || Utilidades.isNuloOuVazio( item.getValor() ) || Utilidades.isNuloOuVazio( item.getTipo() ) ) {
				throw new ApplicationException( "Preencha todos os campos do \"Centro de Custo de Despesas /Despesas\"" );
			}
			if ( !Utilidades.isNuloOuVazio( item.getTipo() ) ) {
				if ( item.getTipo().equalsIgnoreCase( "safra/setor" ) ) {
					if ( Utilidades.isNuloOuVazio( item.getSafra().getId() ) || Utilidades.isNuloOuVazio( item.getSetor().getId() ) ) {
						throw new ApplicationException( "Preencha todos os campos do \"Centro de Custo\"" );
					}
				}
				if ( item.getTipo().equalsIgnoreCase( "tudo" ) ) {
					if ( Utilidades.isNuloOuVazio( item.getSafra().getId() ) ) {
						throw new ApplicationException( "Preencha todos os campos do \"Centro de Custo de Despesas\"" );
					}
				}
				if ( item.getTipo().equalsIgnoreCase( "cultura" ) ) {
					if ( Utilidades.isNuloOuVazio( item.getSafra().getId() ) || Utilidades.isNuloOuVazio( item.getCultura().getNome() ) ) {
						throw new ApplicationException( "Preencha todos os campos do \"Centro de Custo de Despesas\"" );
					}
				}
			}

			if ( !Utilidades.isNuloOuVazio( item.getCentroCusto().getId() ) ) {
				SafraDTO safra = null;
				if ( !Utilidades.isNuloOuVazio( item.getSafra().getId() ) ) {
					safra = new SafraDTO();
					safra.setId( item.getSafra().getId() );

					safra = SafraFacade.getInstance().filtrarPorId( item.getSafra().getId() );

				}
				item.setSafra( safra );

				SetorDTO setor = null;
				if ( !Utilidades.isNuloOuVazio( item.getSetor().getId() ) ) {
					setor = new SetorDTO();
					setor.setId( item.getSetor().getId() );

					setor = SetorFacade.getInstance().filtrarPorId( item.getSetor().getId() );
				}
				item.setSetor( setor );

				CulturaDTO cultura = null;
				if ( !Utilidades.isNuloOuVazio( item.getCultura().getId() ) ) {
					cultura = new CulturaDTO();
					cultura.setId( item.getCultura().getId() );

					cultura = CulturaFacade.getInstance().filtrarPorId( item.getCultura().getId() );
				}
				item.setCultura( cultura );

				nfr.getItens().add( item );
			}

			BigDecimal valorNFR = BigDecimal.ZERO;
			for ( ItemNotaFiscalRateioDTO itemCorrente : nfr.getItens() ) {
				BigDecimal valorItem = Utilidades.parseBigDecimal( itemCorrente.getValor() );

				valorNFR = valorNFR.add( valorItem );

			}
			nfr.getContaPagar().setValor( Moeda.mascaraDinheiro( valorNFR, Moeda.DINHEIRO_REAL ) );

		} catch ( ApplicationException e ) {
			this.logger.error( MensagensResourcesApplication.getString( "mensagem", new String[ ] { e.getMessage() } ) );
			throw new ApplicationException( "Erro ao ocorrido: " + e.getMessage(), e );
		}
	}

	@Override
	public void removerItem( NotaFiscalRateioDTO nfr, String idItem ) throws ApplicationException {
		try {

			for ( ItemNotaFiscalRateioDTO itemNotaFiscalRateioCorrente : nfr.getItens() ) {
				if ( idItem.equals( itemNotaFiscalRateioCorrente.getIdTemp() ) ) {
					nfr.getItens().remove( itemNotaFiscalRateioCorrente );
					break;
				}
			}

			BigDecimal valorNFR = BigDecimal.ZERO;
			for ( ItemNotaFiscalRateioDTO itemCorrente : nfr.getItens() ) {
				BigDecimal valorItem = Utilidades.parseBigDecimal( itemCorrente.getValor() );

				valorNFR = valorNFR.add( valorItem );
			}

			nfr.getContaPagar().setValor( Moeda.mascaraDinheiro( valorNFR, Moeda.DINHEIRO_REAL ) );

		} catch ( Exception e ) {
			this.logger.error( MensagensResourcesApplication.getString( "mensagem", new String[ ] { e.getMessage() } ) );
			throw new ApplicationException( "Erro ao ocorrido: " + e.getMessage(), e );
		}
	}

	private void gerenciarFornecedor( HibernateConnection hibernateConnection, NotaFiscalRateioPO po, String usuario, LocalDateTime dataOcorrencia ) throws ApplicationException {
		if ( !Utilidades.isNuloOuVazio( po.getFornecedor().getId() ) ) {
			po.setFornecedor( (FornecedorJuridicoPO) new HibernateConnection().filtrarPorId( FornecedorJuridicoPO.class, po.getFornecedor().getId() ) );
		} else {
			po.setFornecedor( null );
		}
	}
}
