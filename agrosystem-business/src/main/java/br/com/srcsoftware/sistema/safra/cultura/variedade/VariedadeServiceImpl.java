package br.com.srcsoftware.sistema.safra.cultura.variedade;

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

public class VariedadeServiceImpl implements VariedadeServiceInterface{

	private transient Logger logger = Logger.getLogger( this.getClass().getName() );

	private VariedadeDAOInterface daoInterface;

	private ModelFactory< VariedadePO, VariedadeDTO > modelFactory = new ModelFactory<>();

	public VariedadeServiceImpl(){
		this.daoInterface = new VariedadeDAOImpl();
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, VariedadeDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			dto.montarNomeCompleto();
			dto.ajustarObjetosParaRelacionamentos();

			VariedadePO po = this.modelFactory.getPO( VariedadePO.class, dto );

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
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, VariedadeDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			dto.montarNomeCompleto();
			dto.ajustarObjetosParaRelacionamentos();

			VariedadePO po = this.modelFactory.getPO( VariedadePO.class, dto );

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
	public void excluir( VariedadeDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			VariedadePO po = this.modelFactory.getPO( VariedadePO.class, dto );

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
	public ArrayList< VariedadeDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, VariedadeDTO dto ) throws ApplicationException {

		try {
			VariedadePO poFilter = this.modelFactory.getPO( VariedadePO.class, dto );

			List< VariedadePO > encontrados = this.daoInterface.filtrar( paginacao, camposOrders, poFilter );

			ArrayList< VariedadeDTO > dtosRetorno = new ArrayList<>();

			for ( VariedadePO poCorrente : encontrados ) {
				dtosRetorno.add( this.modelFactory.getDTO( VariedadeDTO.class, poCorrente ) );
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
	public VariedadeDTO filtrarPorId( String id ) throws ApplicationException {

		try {

			if ( Utilidades.isNuloOuVazio( id ) ) {
				throw new ApplicationException( "Id não informado!" );
			}

			VariedadePO encontrado = this.daoInterface.filtrarPorId( Long.valueOf( id ) );

			return this.modelFactory.getDTO( VariedadeDTO.class, encontrado );
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

	public ArrayList< VariedadeDTO > filtrarPorSafraSetor( String idSafra, String idSetor, String nome ) throws ApplicationException {
		try {

			Long safraId = null;
			if ( !Utilidades.isNuloOuVazio( idSafra ) ) {
				safraId = Long.valueOf( idSafra );
			}

			Long setorId = null;
			if ( !Utilidades.isNuloOuVazio( idSetor ) ) {
				setorId = Long.valueOf( idSetor );
			}

			List< VariedadePO > encontrados = this.daoInterface.filtrarPorSafraSetor( safraId, setorId, nome );

			ArrayList< VariedadeDTO > dtosRetorno = new ArrayList<>();

			for ( VariedadePO poCorrente : encontrados ) {
				dtosRetorno.add( this.modelFactory.getDTO( VariedadeDTO.class, poCorrente ) );
			}

			return dtosRetorno;
		} catch ( ApplicationException e ) {
			this.logger.error( e.getMessage(), e );
			throw e;
		} catch ( Exception e ) {

			this.logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

}
