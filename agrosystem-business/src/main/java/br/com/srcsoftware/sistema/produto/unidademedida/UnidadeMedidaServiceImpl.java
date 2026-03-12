package br.com.srcsoftware.sistema.produto.unidademedida;

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

public class UnidadeMedidaServiceImpl implements UnidadeMedidaServiceInterface{

	private transient Logger logger = Logger.getLogger( this.getClass().getName() );

	private UnidadeMedidaDAOInterface daoInterface;

	private ModelFactory< UnidadeMedidaPO, UnidadeMedidaDTO > modelFactory = new ModelFactory<>();

	public UnidadeMedidaServiceImpl(){
		this.daoInterface = new UnidadeMedidaDAOImpl();
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, UnidadeMedidaDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			UnidadeMedidaPO po = this.modelFactory.getPO( UnidadeMedidaPO.class, dto );

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
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, UnidadeMedidaDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			UnidadeMedidaPO po = this.modelFactory.getPO( UnidadeMedidaPO.class, dto );

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
	public void excluir( UnidadeMedidaDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			UnidadeMedidaPO po = this.modelFactory.getPO( UnidadeMedidaPO.class, dto );

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
	public ArrayList< UnidadeMedidaDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, UnidadeMedidaDTO dto ) throws ApplicationException {

		try {
			UnidadeMedidaPO poFilter = this.modelFactory.getPO( UnidadeMedidaPO.class, dto );

			List< UnidadeMedidaPO > encontrados = this.daoInterface.filtrar( paginacao, camposOrders, poFilter );

			ArrayList< UnidadeMedidaDTO > dtosRetorno = new ArrayList<>();

			for ( UnidadeMedidaPO poCorrente : encontrados ) {
				dtosRetorno.add( this.modelFactory.getDTO( UnidadeMedidaDTO.class, poCorrente ) );
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
	public UnidadeMedidaDTO filtrarPorId( String id ) throws ApplicationException {

		try {

			if ( Utilidades.isNuloOuVazio( id ) ) {
				throw new ApplicationException( "Id não informado!" );
			}

			UnidadeMedidaPO encontrado = this.daoInterface.filtrarPorId( Long.valueOf( id ) );

			return this.modelFactory.getDTO( UnidadeMedidaDTO.class, encontrado );
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