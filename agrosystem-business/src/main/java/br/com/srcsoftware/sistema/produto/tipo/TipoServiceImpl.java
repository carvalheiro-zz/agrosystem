package br.com.srcsoftware.sistema.produto.tipo;

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

public class TipoServiceImpl implements TipoServiceInterface{

	private transient Logger logger = Logger.getLogger( this.getClass().getName() );

	private TipoDAOInterface daoInterface;

	private ModelFactory< TipoPO, TipoDTO > modelFactory = new ModelFactory<>();

	public TipoServiceImpl(){
		this.daoInterface = new TipoDAOImpl();
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, TipoDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			TipoPO po = this.modelFactory.getPO( TipoPO.class, dto );

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
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, TipoDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			TipoPO po = this.modelFactory.getPO( TipoPO.class, dto );

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
	public void excluir( TipoDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			TipoPO po = this.modelFactory.getPO( TipoPO.class, dto );

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
	public ArrayList< TipoDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, TipoDTO dto ) throws ApplicationException {

		try {
			TipoPO poFilter = this.modelFactory.getPO( TipoPO.class, dto );

			List< TipoPO > encontrados = this.daoInterface.filtrar( paginacao, camposOrders, poFilter );

			ArrayList< TipoDTO > dtosRetorno = new ArrayList<>();

			for ( TipoPO poCorrente : encontrados ) {
				dtosRetorno.add( this.modelFactory.getDTO( TipoDTO.class, poCorrente ) );
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
	public TipoDTO filtrarPorId( String id ) throws ApplicationException {

		try {

			if ( Utilidades.isNuloOuVazio( id ) ) {
				throw new ApplicationException( "Id não informado!" );
			}

			TipoPO encontrado = this.daoInterface.filtrarPorId( Long.valueOf( id ) );

			return this.modelFactory.getDTO( TipoDTO.class, encontrado );
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