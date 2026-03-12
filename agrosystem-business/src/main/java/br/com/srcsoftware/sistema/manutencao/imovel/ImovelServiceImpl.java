package br.com.srcsoftware.sistema.manutencao.imovel;

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

public class ImovelServiceImpl implements ImovelServiceInterface{

	private transient Logger logger = Logger.getLogger( this.getClass().getName() );

	private ImovelDAOInterface daoInterface;

	private ModelFactory< ImovelPO, ImovelDTO > modelFactory = new ModelFactory<>();

	public ImovelServiceImpl(){
		this.daoInterface = new ImovelDAOImpl();
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, ImovelDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			ImovelPO po = this.modelFactory.getPO( ImovelPO.class, dto );
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
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, ImovelDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			ImovelPO po = this.modelFactory.getPO( ImovelPO.class, dto );
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
	public void excluir( ImovelDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			ImovelPO po = this.modelFactory.getPO( ImovelPO.class, dto );

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
	public ArrayList< ImovelDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, ImovelDTO dto ) throws ApplicationException {

		try {
			ImovelPO poFilter = this.modelFactory.getPO( ImovelPO.class, dto );

			List< ImovelPO > encontrados = this.daoInterface.filtrar( paginacao, camposOrders, poFilter );

			ArrayList< ImovelDTO > dtosRetorno = new ArrayList<>();

			for ( ImovelPO poCorrente : encontrados ) {
				dtosRetorno.add( this.modelFactory.getDTO( ImovelDTO.class, poCorrente ) );
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
	public ImovelDTO filtrarPorId( String id ) throws ApplicationException {

		try {

			if ( Utilidades.isNuloOuVazio( id ) ) {
				throw new ApplicationException( "Id não informado!" );
			}

			ImovelPO encontrado = this.daoInterface.filtrarPorId( Long.valueOf( id ) );

			return this.modelFactory.getDTO( ImovelDTO.class, encontrado );
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