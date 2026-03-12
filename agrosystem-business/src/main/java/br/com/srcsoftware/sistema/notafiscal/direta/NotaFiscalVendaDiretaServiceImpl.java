package br.com.srcsoftware.sistema.notafiscal.direta;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.factory.ModelFactory;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.exceptions.ModelFactoryException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.usuario.usuario.UsuarioSessaoPOJO;
import br.com.srcsoftware.modular.manager.utilidades.MensagensResourcesApplication;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.notafiscal.direta.item.ItemNotaFiscalVendaDiretaDTO;
import br.com.srcsoftware.sistema.notafiscal.direta.item.ItemNotaFiscalVendaDiretaPO;
import br.com.srcsoftware.sistema.pessoa.fornecedor.juridico.FornecedorFacade;
import br.com.srcsoftware.sistema.pessoa.fornecedor.juridico.FornecedorJuridicoDTO;
import br.com.srcsoftware.sistema.pessoa.fornecedor.juridico.FornecedorJuridicoPO;
import br.com.srcsoftware.sistema.produto.produto.ProdutoDTO;
import br.com.srcsoftware.sistema.produto.produto.ProdutoFacade;

public final class NotaFiscalVendaDiretaServiceImpl implements NotaFiscalVendaDiretaServiceInterface{

	private transient Logger logger = Logger.getLogger( this.getClass().getName() );

	private NotaFiscalVendaDiretaDAOInterface daoInterface;

	private ModelFactory< NotaFiscalVendaDiretaPO, NotaFiscalVendaDiretaDTO > modelFactory = new ModelFactory<>();

	public NotaFiscalVendaDiretaServiceImpl(){
		this.daoInterface = new NotaFiscalVendaDiretaDAOImpl();
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, NotaFiscalVendaDiretaDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			String usuarioAlteracao = usuarioSessaoPOJO.getUsuario().getLogin();
			LocalDateTime dataAlteracao = LocalDateTime.now();

			if ( Utilidades.isNuloOuVazio( dto.getItens() ) ) {
				throw new ApplicationException( "Por favor informe os itens do NotaFiscalVendaDireta!" );
			}

			hibernate.iniciarTransacao();

			NotaFiscalVendaDiretaPO po = this.modelFactory.getPO( NotaFiscalVendaDiretaPO.class, dto );
			po.setarDadosAuditoria( usuarioAlteracao, dataAlteracao );

			this.gerenciarFornecedor( hibernate, po );

			dto.setId( this.daoInterface.inserir( hibernate, po ).getId().toString() );

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
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, NotaFiscalVendaDiretaDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			String usuarioAlteracao = usuarioSessaoPOJO.getUsuario().getLogin();
			LocalDateTime dataAlteracao = LocalDateTime.now();

			if ( Utilidades.isNuloOuVazio( dto.getItens() ) ) {
				throw new ApplicationException( "Por favor informe os itens do NotaFiscalVendaDireta!" );
			}

			hibernate.iniciarTransacao();

			NotaFiscalVendaDiretaPO po = this.modelFactory.getPO( NotaFiscalVendaDiretaPO.class, dto );
			po.setarDadosAuditoria( usuarioAlteracao, dataAlteracao );

			this.daoInterface.alterar( hibernate, po );

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
	public void excluir( NotaFiscalVendaDiretaDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {

			/** Pegando o PO passado na Requisicao */
			NotaFiscalVendaDiretaPO po = this.modelFactory.getPO( NotaFiscalVendaDiretaPO.class, dto );

			hibernate.iniciarTransacao();

			hibernate.excluir( po );
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

	private ArrayList< ItemNotaFiscalVendaDiretaPO > getItensEntregaRemovidos( NotaFiscalVendaDiretaPO compraProduto ) throws ApplicationException {
		NotaFiscalVendaDiretaPO notaEncontrada = this.daoInterface.filtrarPorId( compraProduto.getId() );
		ArrayList< ItemNotaFiscalVendaDiretaPO > itensRemovidos = new ArrayList<>();

		for ( ItemNotaFiscalVendaDiretaPO itemEntregaCorrente : notaEncontrada.getItens() ) {
			boolean achei = false;
			for ( ItemNotaFiscalVendaDiretaPO itemCorrente : compraProduto.getItens() ) {
				if ( itemEntregaCorrente.getId().equals( itemCorrente.getId() ) ) {
					achei = true;
					break;
				}
			}

			if ( !achei ) {
				ItemNotaFiscalVendaDiretaPO itemRemovido = new ItemNotaFiscalVendaDiretaPO();
				itemRemovido.setId( itemEntregaCorrente.getId() );
				itemRemovido.setProduto( itemEntregaCorrente.getProduto() );
				itemRemovido.setPrecoCusto( itemEntregaCorrente.getPrecoCusto() );
				itemRemovido.setQuantidade( itemEntregaCorrente.getQuantidade() );

				itensRemovidos.add( itemRemovido );
			}
		}

		return itensRemovidos;
	}

	private void gerenciarFornecedor( HibernateConnection hibernateConnection, NotaFiscalVendaDiretaPO po ) throws ApplicationException, ModelFactoryException {
		if ( Utilidades.isNuloOuVazio( po.getFornecedor().getNome() ) ) {

			ModelFactory< FornecedorJuridicoPO, FornecedorJuridicoDTO > modelFactoryFornecedor = new ModelFactory<>();

			FornecedorJuridicoDTO fornecedorFilter = new FornecedorJuridicoDTO();
			fornecedorFilter.setNome( "Fornecedor padrão" );

			List< FornecedorJuridicoDTO > encontrados = FornecedorFacade.getInstance().filtrar( Paginacao.NAO, HibernateConnection.SEM_ORDENACAO, fornecedorFilter );

			List< FornecedorJuridicoPO > fornecedoresEncontrado = new ArrayList<>();

			for ( FornecedorJuridicoDTO poCorrente : encontrados ) {
				FornecedorJuridicoPO encontrado = modelFactoryFornecedor.getPO( FornecedorJuridicoPO.class, poCorrente );

				fornecedoresEncontrado.add( encontrado );
			}

			if ( Utilidades.isNuloOuVazio( fornecedoresEncontrado ) ) {
				FornecedorJuridicoPO fornecedorInserir = new FornecedorJuridicoPO();
				fornecedorInserir.setNome( "Fornecedor padrão" );
				/*fornecedorInserir.setCelular( "(00)0000-00000" );
				fornecedorInserir.setFixo( "(00)0000-00000" );*/
				fornecedorInserir.setTelefone( "(00)0000-00000" );
				fornecedorInserir.setEndereco( "Endereço padrão" );
				po.setFornecedor( (FornecedorJuridicoPO) hibernateConnection.inserir( fornecedorInserir ) );
			} else {
				po.setFornecedor( fornecedoresEncontrado.get( 0 ) );
			}
		}
	}

	@Override
	public ArrayList< NotaFiscalVendaDiretaDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, NotaFiscalVendaDiretaDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException {

		try {
			NotaFiscalVendaDiretaPO poFilter = this.modelFactory.getPO( NotaFiscalVendaDiretaPO.class, dto );

			HashMap< String, ArrayList< Object > > camposBetween = new HashMap<>();

			if ( !Utilidades.isNuloOuVazio( dataInicialParam ) && !Utilidades.isNuloOuVazio( dataFinalParam ) ) {
				camposBetween.put( "data", new ArrayList< Object >( Arrays.asList( LocalDate.parse( dataInicialParam, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ), LocalDate.parse( dataFinalParam, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ) ) ) );
			}

			List< NotaFiscalVendaDiretaPO > encontrados = this.daoInterface.filtrar( paginacao, camposOrders, camposBetween, poFilter );

			ArrayList< NotaFiscalVendaDiretaDTO > dtosRetorno = new ArrayList<>();

			for ( NotaFiscalVendaDiretaPO poCorrente : encontrados ) {
				dtosRetorno.add( this.modelFactory.getDTO( NotaFiscalVendaDiretaDTO.class, poCorrente ) );
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
	public NotaFiscalVendaDiretaDTO filtrarPorId( String id ) throws ApplicationException {

		try {

			if ( Utilidades.isNuloOuVazio( id ) ) {
				throw new ApplicationException( "Id não informado!" );
			}

			NotaFiscalVendaDiretaPO encontrado = this.daoInterface.filtrarPorId( Long.valueOf( id ) );

			return this.modelFactory.getDTO( NotaFiscalVendaDiretaDTO.class, encontrado );
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
	public void adicionarItem( ItemNotaFiscalVendaDiretaDTO item, NotaFiscalVendaDiretaDTO nf ) throws ApplicationException {

		try {

			if ( Utilidades.isNuloOuVazio( item.getQuantidade() ) ) {
				item.setQuantidade( "1" );
			}

			ProdutoDTO produtoAdicionar = ProdutoFacade.getInstance().filtrarPorId( item.getProduto().getId() );
			item.setProduto( produtoAdicionar );

			nf.getItens().add( item );
		} catch ( ApplicationException e ) {
			this.logger.error( MensagensResourcesApplication.getString( "mensagem", new String[ ] { e.getMessage() } ) );
			throw new ApplicationException( "Erro ao ocorrido: " + e.getMessage(), e );
		}
	}

	@Override
	public void removerItem( NotaFiscalVendaDiretaDTO nf, String idItem ) throws ApplicationException {
		try {

			for ( ItemNotaFiscalVendaDiretaDTO itemNotaFiscalVendaDiretaCorrente : nf.getItens() ) {
				if ( idItem.equals( itemNotaFiscalVendaDiretaCorrente.getIdTemp() ) ) {
					nf.getItens().remove( itemNotaFiscalVendaDiretaCorrente );
					break;
				}
			}

		} catch ( Exception e ) {
			this.logger.error( MensagensResourcesApplication.getString( "mensagem", new String[ ] { e.getMessage() } ) );
			throw new ApplicationException( "Erro ao ocorrido: " + e.getMessage(), e );
		}
	}
}
