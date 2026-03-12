package br.com.srcsoftware.sistema.notafiscal.rateio.centrocusto;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

public final class CentroCustoServiceImpl implements CentroCustoServiceInterface{

	private transient Logger logger = Logger.getLogger( this.getClass().getName() );

	private CentroCustoDAOInterface daoInterface;

	private ModelFactory< CentroCustoPO, CentroCustoDTO > modelFactory = new ModelFactory<>();

	public CentroCustoServiceImpl(){
		this.daoInterface = new CentroCustoDAOImpl();
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, CentroCustoDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			CentroCustoPO po = this.modelFactory.getPO( CentroCustoPO.class, dto );
			po.setarDadosAuditoria( usuarioSessaoPOJO.getUsuario().getLogin(), LocalDateTime.now() );

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
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, CentroCustoDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			CentroCustoPO po = this.modelFactory.getPO( CentroCustoPO.class, dto );
			po.setarDadosAuditoria( usuarioSessaoPOJO.getUsuario().getLogin(), LocalDateTime.now() );

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
	public void excluir( CentroCustoDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			CentroCustoPO po = this.modelFactory.getPO( CentroCustoPO.class, dto );

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
	public ArrayList< CentroCustoDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, CentroCustoDTO dto ) throws ApplicationException {

		try {
			CentroCustoPO poFilter = this.modelFactory.getPO( CentroCustoPO.class, dto );

			List< CentroCustoPO > encontrados = this.daoInterface.filtrar( paginacao, camposOrders, poFilter );

			ArrayList< CentroCustoDTO > dtosRetorno = new ArrayList<>();

			for ( CentroCustoPO poCorrente : encontrados ) {
				dtosRetorno.add( this.modelFactory.getDTO( CentroCustoDTO.class, poCorrente ) );
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
	public CentroCustoDTO filtrarPorId( String id ) throws ApplicationException {

		try {

			if ( Utilidades.isNuloOuVazio( id ) ) {
				throw new ApplicationException( "Id não informado!" );
			}

			CentroCustoPO encontrado = this.daoInterface.filtrarPorId( Long.valueOf( id ) );

			return this.modelFactory.getDTO( CentroCustoDTO.class, encontrado );
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
	public ArrayList< CentroCustoDTO > filtrarPorTipoNotaFiscalRateioAgrupado( String codigo, String tipoNotaFiscalRateio ) throws ApplicationException {

		try {
			List< CentroCustoPO > encontrados = this.daoInterface.filtrarPorTipoNotaFiscalRateioAgrupado( codigo, tipoNotaFiscalRateio );

			ArrayList< CentroCustoDTO > dtosRetorno = new ArrayList<>();

			for ( CentroCustoPO poCorrente : encontrados ) {
				dtosRetorno.add( this.modelFactory.getDTO( CentroCustoDTO.class, poCorrente ) );
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
}
