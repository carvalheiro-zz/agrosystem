package br.com.srcsoftware.sistema.notafiscalcupom;

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
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.cupom.CupomDAOImpl;
import br.com.srcsoftware.sistema.cupom.CupomDAOInterface;
import br.com.srcsoftware.sistema.cupom.CupomPO;
import br.com.srcsoftware.sistema.pessoa.fornecedor.juridico.FornecedorFacade;
import br.com.srcsoftware.sistema.pessoa.fornecedor.juridico.FornecedorJuridicoDTO;
import br.com.srcsoftware.sistema.pessoa.fornecedor.juridico.FornecedorJuridicoPO;

public final class NotaFiscalCupomServiceImpl implements NotaFiscalCupomServiceInterface{

	private transient Logger logger = Logger.getLogger( this.getClass().getName() );

	private NotaFiscalCupomDAOInterface daoInterface;
	private CupomDAOInterface daoCupomInterface;

	private ModelFactory< NotaFiscalCupomPO, NotaFiscalCupomDTO > modelFactory = new ModelFactory<>();

	public NotaFiscalCupomServiceImpl(){
		this.daoInterface = new NotaFiscalCupomDAOImpl();
		this.daoCupomInterface = new CupomDAOImpl();
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, NotaFiscalCupomDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			NotaFiscalCupomPO po = this.modelFactory.getPO( NotaFiscalCupomPO.class, dto );
			po.setarDadosAuditoria( usuarioSessaoPOJO.getUsuario().getLogin(), LocalDateTime.now() );

			this.validarCuposLancar( po );

			this.gerenciarFornecedor( hibernate, po );

			ArrayList< CupomPO > cuponsRemover = new ArrayList<>();
			for ( CupomPO cupomCorrente : po.getCupons() ) {
				if ( !cupomCorrente.getNotaEmitida() ) {
					cuponsRemover.add( cupomCorrente );
				}
				hibernate.alterar( cupomCorrente );
			}
			po.getCupons().removeAll( cuponsRemover );

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

	private void validarCuposLancar( NotaFiscalCupomPO po ) throws ApplicationException {
		CupomPO cupomFiltrar = new CupomPO();
		cupomFiltrar.setFornecedor( new FornecedorJuridicoPO() );
		cupomFiltrar.getFornecedor().setId( po.getFornecedor().getId() );
		cupomFiltrar.setNotaEmitida( false );
		List< CupomPO > cuponsSemNotaEmissao = this.daoCupomInterface.filtrar( Paginacao.NAO, HibernateConnection.SEM_ORDENACAO, HibernateConnection.SEM_BETWEEN, cupomFiltrar );

		ArrayList< Long > idCuponsEncontrados = new ArrayList<>();
		for ( CupomPO cupomCorrente : cuponsSemNotaEmissao ) {
			idCuponsEncontrados.add( cupomCorrente.getId() );
		}
		ArrayList< Long > idCuponsLancar = new ArrayList<>();
		for ( CupomPO cupomCorrente : po.getCupons() ) {
			idCuponsLancar.add( cupomCorrente.getId() );
		}

		for ( Long idCupomCorrente : idCuponsLancar ) {
			if ( !idCuponsEncontrados.contains( idCupomCorrente ) ) {
				throw new ApplicationException( "Um ou mais cupons relacionados já foram emitidos! Por favor, clique em limpar e tente novamente." );
			}
		}

	}

	@Override
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, NotaFiscalCupomDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			NotaFiscalCupomPO po = this.modelFactory.getPO( NotaFiscalCupomPO.class, dto );
			po.setarDadosAuditoria( usuarioSessaoPOJO.getUsuario().getLogin(), LocalDateTime.now() );

			//			this.validarCuposLancar( po );

			this.gerenciarFornecedor( hibernate, po );

			ArrayList< CupomPO > cuponsRemover = new ArrayList<>();
			for ( CupomPO cupomCorrente : po.getCupons() ) {
				if ( !cupomCorrente.getNotaEmitida() ) {
					cuponsRemover.add( cupomCorrente );
				}
				hibernate.alterar( cupomCorrente );
			}
			po.getCupons().removeAll( cuponsRemover );

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
	public void excluir( NotaFiscalCupomDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			NotaFiscalCupomPO po = this.modelFactory.getPO( NotaFiscalCupomPO.class, dto );

			for ( CupomPO cupomCorrente : po.getCupons() ) {
				cupomCorrente.setNotaEmitida( false );
				hibernate.alterar( cupomCorrente );
			}

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

	@SuppressWarnings( "unchecked" )
	private void gerenciarFornecedor( HibernateConnection hibernateConnection, NotaFiscalCupomPO notaFiscalCupom ) throws ApplicationException, ModelFactoryException {
		if ( Utilidades.isNuloOuVazio( notaFiscalCupom.getFornecedor().getNome() ) ) {

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
				notaFiscalCupom.setFornecedor( (FornecedorJuridicoPO) hibernateConnection.inserir( fornecedorInserir ) );
			} else {
				notaFiscalCupom.setFornecedor( fornecedoresEncontrado.get( 0 ) );
			}
		}
	}

	@Override
	public ArrayList< NotaFiscalCupomDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, NotaFiscalCupomDTO dto, String dataInicialParam, String dataFinalParam ) throws ApplicationException {

		try {
			NotaFiscalCupomPO poFilter = this.modelFactory.getPO( NotaFiscalCupomPO.class, dto );

			HashMap< String, ArrayList< Object > > camposBetween = new HashMap<>();

			if ( !Utilidades.isNuloOuVazio( dataInicialParam ) && !Utilidades.isNuloOuVazio( dataFinalParam ) ) {
				camposBetween.put( "data", new ArrayList< Object >( Arrays.asList( LocalDate.parse( dataInicialParam, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ), LocalDate.parse( dataFinalParam, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) ) ) ) );
			}

			List< NotaFiscalCupomPO > encontrados = this.daoInterface.filtrar( paginacao, camposOrders, camposBetween, poFilter );

			ArrayList< NotaFiscalCupomDTO > dtosRetorno = new ArrayList<>();

			for ( NotaFiscalCupomPO poCorrente : encontrados ) {
				dtosRetorno.add( this.modelFactory.getDTO( NotaFiscalCupomDTO.class, poCorrente ) );
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
	public NotaFiscalCupomDTO filtrarPorId( String id ) throws ApplicationException {

		try {

			if ( Utilidades.isNuloOuVazio( id ) ) {
				throw new ApplicationException( "Id não informado!" );
			}

			NotaFiscalCupomPO encontrado = this.daoInterface.filtrarPorId( Long.valueOf( id ) );

			return this.modelFactory.getDTO( NotaFiscalCupomDTO.class, encontrado );
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
}
