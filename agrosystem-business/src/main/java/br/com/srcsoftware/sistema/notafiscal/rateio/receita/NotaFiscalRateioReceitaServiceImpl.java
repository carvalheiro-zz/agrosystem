package br.com.srcsoftware.sistema.notafiscal.rateio.receita;

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
import br.com.srcsoftware.modular.financeiro.contareceber.ContaReceberDTO;
import br.com.srcsoftware.modular.financeiro.contareceber.ContaReceberServiceImpl;
import br.com.srcsoftware.modular.financeiro.enums.SituacaoTituloEnum;
import br.com.srcsoftware.modular.financeiro.enums.TipoTituloEnum;
import br.com.srcsoftware.modular.financeiro.formapagamento.FormaPagamentoDTO;
import br.com.srcsoftware.modular.financeiro.formapagamento.FormaPagamentoFacade;
import br.com.srcsoftware.modular.financeiro.titulo.tituloreceber.TituloReceberDTO;
import br.com.srcsoftware.modular.financeiro.titulo.tituloreceber.TituloReceberFacade;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.exceptions.ModelFactoryException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.pessoa.cliente.ClientePO;
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
import br.com.srcsoftware.sistema.safra.SafraDTO;
import br.com.srcsoftware.sistema.safra.SafraFacade;
import br.com.srcsoftware.sistema.safra.cultura.CulturaDTO;
import br.com.srcsoftware.sistema.safra.cultura.CulturaFacade;
import br.com.srcsoftware.sistema.safra.setor.SetorDTO;
import br.com.srcsoftware.sistema.safra.setor.SetorFacade;

public final class NotaFiscalRateioReceitaServiceImpl extends ContaReceberServiceImpl implements NotaFiscalRateioReceitaServiceInterface{

	private transient Logger logger = Logger.getLogger( this.getClass().getName() );

	private NotaFiscalRateioDAOInterface daoInterface;

	private ModelFactory< NotaFiscalRateioPO, NotaFiscalRateioDTO > modelFactory = new ModelFactory<>();

	public NotaFiscalRateioReceitaServiceImpl(){
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
		super.gerenciarAssociacoes( usuarioSessaoPOJO, notaFiscalRateio.getContaReceber() );
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, NotaFiscalRateioDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			String usuarioAlteracao = usuarioSessaoPOJO.getUsuario().getLogin();
			LocalDateTime dataAlteracao = LocalDateTime.now();

			dto.getContaReceber().setNumero( "".concat( dto.getNumero() + "" ).concat( dto.getNumeroRecibo() + "" ) );

			dto.getContaReceber().setQuantidadeParcelas( gerenciarQuantidadeParcelas( dto.getContaReceber().getFormaPagamento().getId() ) );

			dto.setContaReceber( gerarParcelas( usuarioSessaoPOJO, dto.getContaReceber(), dto.getDescricaoOrigemTitulos(), dto.getCliente().getNome() ) );

			NotaFiscalRateioPO po = this.modelFactory.getPO( NotaFiscalRateioPO.class, dto );
			po.getContaReceber().setarDadosAuditoria( usuarioAlteracao, dataAlteracao );
			po.getContaReceber().setCancelado( false );

			//po.getContaReceber().setValorFinal( po.getContaReceber().getValor() );

			po.setFornecedor( null );
			po.setContaPagar( null );

			if ( !isValidoValorTotalParcelas( po.getContaReceber() ) ) {
				throw new ApplicationException( "A soma dos valores das parcelas não batem com o valor da NF!" );
			}

			this.gerenciarCliente( hibernate, po, usuarioAlteracao, dataAlteracao );

			this.daoInterface.inserir( hibernate, po );

			for ( ItemNotaFiscalRateioPO itemCorrente : po.getItens() ) {
				this.gerenciarCentroCustoReceitas( itemCorrente );
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

			dto.getContaReceber().setNumero( "".concat( dto.getNumero() + "" ).concat( dto.getNumeroRecibo() + "" ) );

			dto.getContaReceber().setQuantidadeParcelas( gerenciarQuantidadeParcelas( dto.getContaReceber().getFormaPagamento().getId() ) );

			this.gerenciarParcelas( usuarioSessaoPOJO, dto );

			this.gerenciarAssociacoes( usuarioSessaoPOJO, dto, usuarioAlteracao, DateUtil.parseLocalDateTime( dataAlteracao ) );

			NotaFiscalRateioPO po = this.modelFactory.getPO( NotaFiscalRateioPO.class, dto );
			po.getContaReceber().setarDadosAuditoria( usuarioAlteracao, dataAlteracao );
			//po.getContaReceber().setValorFinal( po.getContaReceber().getValor() );

			po.setFornecedor( null );
			po.setContaPagar( null );

			if ( !isValidoValorTotalParcelas( po.getContaReceber() ) ) {
				throw new ApplicationException( "A soma dos valores das parcelas não batem com o valor da NF!" );
			}

			this.gerenciarCliente( hibernate, po, usuarioAlteracao, dataAlteracao );

			this.daoInterface.alterar( hibernate, po );

			for ( ItemNotaFiscalRateioPO itemCorrente : po.getItens() ) {
				this.gerenciarCentroCustoReceitas( itemCorrente );
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

		ContaReceberDTO contaReceberOld = this.filtrarPorId( dto.getId() ).getContaReceber();

		dto.setContaReceber( super.gerenciarParcelas( usuarioSessaoPOJO, dto.getContaReceber(), contaReceberOld, dto.getDescricaoOrigemTitulos(), dto.getCliente().getNome() ) );
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
				camposBetween.put( "contaReceber.data", new ArrayList< Object >( Arrays.asList( LocalDate.parse( dataInicialParam, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ), LocalDate.parse( dataFinalParam, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ) ) ) );
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

			/** Informando na Receita a Data de Vencimeno da primeira Parcela */
			TituloReceberDTO primeiraParcela = TituloReceberFacade.getInstance().filtrarPorNumeroTipoContaReceber( "1", TipoTituloEnum.PARCELA, notaFiscalRateioDTO.getContaReceber().getId() );
			if ( primeiraParcela == null ) {
				primeiraParcela = TituloReceberFacade.getInstance().filtrarPorNumeroTipoContaReceber( "1", TipoTituloEnum.A_VISTA, notaFiscalRateioDTO.getContaReceber().getId() );
			}
			if ( primeiraParcela == null ) {
				primeiraParcela = TituloReceberFacade.getInstance().filtrarPorNumeroTipoContaReceber( "1", TipoTituloEnum.FIXA, notaFiscalRateioDTO.getContaReceber().getId() );
			}
			if ( !Utilidades.isNuloOuVazio( primeiraParcela ) ) {
				notaFiscalRateioDTO.getContaReceber().setVencimentoPrimeiraParcela( primeiraParcela.getDataVencimento() );
			}

			/** Informando na Receita o Valor e a Forma de pagamento da Entrada */
			TituloReceberDTO entrada = TituloReceberFacade.getInstance().filtrarPorNumeroTipoContaReceber( null, TipoTituloEnum.ENTRADA, notaFiscalRateioDTO.getContaReceber().getId() );
			if ( !Utilidades.isNuloOuVazio( entrada ) ) {
				notaFiscalRateioDTO.getContaReceber().setFormaPagamentoEntrada( entrada.getFormaPagamento() );
				notaFiscalRateioDTO.getContaReceber().setValorEntrada( entrada.getValorFinal() );
			}

			/** Informando na Receita a Situação dela. Quitada caso todas as parcelas estejam Quitadas */
			List< TituloReceberDTO > abertos = TituloReceberFacade.getInstance().filtrarPorSituacaoContaReceber( SituacaoTituloEnum.ABERTA, notaFiscalRateioDTO.getContaReceber().getId() );
			if ( !Utilidades.isNuloOuVazio( abertos ) ) {
				notaFiscalRateioDTO.getContaReceber().setSituacao( SituacaoTituloEnum.ABERTA.getValor() );
			} else {
				notaFiscalRateioDTO.getContaReceber().setSituacao( SituacaoTituloEnum.QUITADA.getValor() );
			}

			/** Informando na NotaFiscalRateio a Data de Vencimeno da primeira Parcela */
			/*TituloReceberDTO primeiraParcela = TituloReceberFacade.getInstance().filtrarPorNumeroTipoContaReceber( "1", TipoTituloEnum.PARCELA, notaFiscalRateioDTO.getContaReceber().getId() );
			if ( primeiraParcela == null ) {
				primeiraParcela = TituloReceberFacade.getInstance().filtrarPorNumeroTipoContaReceber( "1", TipoTituloEnum.A_VISTA, notaFiscalRateioDTO.getContaReceber().getId() );
			}
			if ( !Utilidades.isNuloOuVazio( primeiraParcela ) ) {
				notaFiscalRateioDTO.getContaReceber().setVencimentoPrimeiraParcela( primeiraParcela.getDataVencimento() );
			}*/

			/** Informando na NotaFiscalRateio o Valor e a Forma de pagamento da Entrada */
			/*TituloReceberDTO entrada = TituloReceberFacade.getInstance().filtrarPorNumeroTipoContaReceber( null, TipoTituloEnum.ENTRADA, notaFiscalRateioDTO.getContaReceber().getId() );
			if ( !Utilidades.isNuloOuVazio( entrada ) ) {
				notaFiscalRateioDTO.getContaReceber().setFormaPagamentoEntrada( entrada.getFormaPagamento() );
				notaFiscalRateioDTO.getContaReceber().setValorEntrada( entrada.getValorFinal() );
			}*/

			/** Informando na NotaFiscalRateio a Situação dela. Quitada caso todas as parcelas estejam Quitadas */
			/*List< TituloReceberDTO > abertos = TituloReceberFacade.getInstance().filtrarPorSituacaoContaReceber( SituacaoTituloEnum.ABERTA, notaFiscalRateioDTO.getContaReceber().getId() );
			if ( !Utilidades.isNuloOuVazio( abertos ) ) {
				notaFiscalRateioDTO.getContaReceber().setSituacao( SituacaoTituloEnum.ABERTA.getValor() );
			} else {
				notaFiscalRateioDTO.getContaReceber().setSituacao( SituacaoTituloEnum.QUITADA.getValor() );
			}*/

		} catch ( Exception e ) {
			this.logger.error( MensagensResourcesApplication.getString( "mensagem", new String[ ] { e.getMessage() } ) );
			throw new ApplicationException( "Erro ao ocorrido: " + e.getMessage(), e );
		} catch ( ApplicationException e ) {
			this.logger.error( MensagensResourcesApplication.getString( "mensagem", new String[ ] { e.getMessage() } ) );
			throw e;
		}
	}

	private void gerenciarCentroCustoReceitas( ItemNotaFiscalRateioPO itemCorrente ) throws ApplicationException {

		CentroCustoPO centroCustoReceitaEncontrado = (CentroCustoPO) new HibernateConnection().filtrarPorId( CentroCustoPO.class, itemCorrente.getCentroCusto().getId() );
		if ( !Utilidades.isNuloOuVazio( centroCustoReceitaEncontrado ) ) {
			itemCorrente.setCentroCusto( centroCustoReceitaEncontrado );
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
				throw new ApplicationException( "Centro de Custo de Receita/Receita informado [ " + item.getCentroCusto().getDescricao() + " ] não Cadastrado!" );
			}

			if ( Utilidades.isNuloOuVazio( item.getCentroCusto().getId() ) || Utilidades.isNuloOuVazio( item.getDescricao() ) || Utilidades.isNuloOuVazio( item.getValor() ) || Utilidades.isNuloOuVazio( item.getTipo() ) ) {
				throw new ApplicationException( "Preencha todos os campos do \"Centro de Custo de Receitas /Receitas\"" );
			}
			if ( !Utilidades.isNuloOuVazio( item.getTipo() ) ) {
				if ( item.getTipo().equalsIgnoreCase( "safra/setor" ) ) {
					if ( Utilidades.isNuloOuVazio( item.getSafra().getId() ) || Utilidades.isNuloOuVazio( item.getSetor().getId() ) ) {
						throw new ApplicationException( "Preencha todos os campos do \"Centro de Custo\"" );
					}
				}
				if ( item.getTipo().equalsIgnoreCase( "tudo" ) ) {
					if ( Utilidades.isNuloOuVazio( item.getSafra().getId() ) ) {
						throw new ApplicationException( "Preencha todos os campos do \"Centro de Custo de Receitas\"" );
					}
				}
				if ( item.getTipo().equalsIgnoreCase( "cultura" ) ) {
					if ( Utilidades.isNuloOuVazio( item.getSafra().getId() ) || Utilidades.isNuloOuVazio( item.getCultura().getNome() ) ) {
						throw new ApplicationException( "Preencha todos os campos do \"Centro de Custo de Receitas\"" );
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
			nfr.getContaReceber().setValor( Moeda.mascaraDinheiro( valorNFR, Moeda.DINHEIRO_REAL ) );
			nfr.getContaReceber().setValorFinal( Moeda.mascaraDinheiro( valorNFR, Moeda.DINHEIRO_REAL ) );

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

			nfr.getContaReceber().setValor( Moeda.mascaraDinheiro( valorNFR, Moeda.DINHEIRO_REAL ) );
			nfr.getContaReceber().setValorFinal( Moeda.mascaraDinheiro( valorNFR, Moeda.DINHEIRO_REAL ) );

		} catch ( Exception e ) {
			this.logger.error( MensagensResourcesApplication.getString( "mensagem", new String[ ] { e.getMessage() } ) );
			throw new ApplicationException( "Erro ao ocorrido: " + e.getMessage(), e );
		}
	}

	private void gerenciarCliente( HibernateConnection hibernateConnection, NotaFiscalRateioPO po, String usuario, LocalDateTime dataOcorrencia ) throws ApplicationException {
		if ( !Utilidades.isNuloOuVazio( po.getCliente().getId() ) ) {
			po.setCliente( (ClientePO) new HibernateConnection().filtrarPorId( ClientePO.class, po.getCliente().getId() ) );
		} else {
			po.setCliente( null );
		}
	}

}
