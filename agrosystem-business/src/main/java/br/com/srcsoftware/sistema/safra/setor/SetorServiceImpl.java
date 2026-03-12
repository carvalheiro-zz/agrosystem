package br.com.srcsoftware.sistema.safra.setor;

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
import br.com.srcsoftware.sistema.safra.setorsafra.SetorSafraDTO;
import br.com.srcsoftware.sistema.safra.setorsafra.SetorSafraPO;

public class SetorServiceImpl implements SetorServiceInterface{

	private transient Logger logger = Logger.getLogger( this.getClass().getName() );

	private SetorDAOInterface daoInterface;

	private ModelFactory< SetorPO, SetorDTO > modelFactory = new ModelFactory<>();

	public SetorServiceImpl(){
		this.daoInterface = new SetorDAOImpl();
	}

	@Override
	public void inserir( UsuarioSessaoPOJO usuarioSessaoPOJO, SetorDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			dto.montarNomeCompleto();

			SetorPO po = this.modelFactory.getPO( SetorPO.class, dto );

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
	public void alterar( UsuarioSessaoPOJO usuarioSessaoPOJO, SetorDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			dto.montarNomeCompleto();

			SetorPO po = this.modelFactory.getPO( SetorPO.class, dto );

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
	public void excluir( SetorDTO dto ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			SetorPO po = this.modelFactory.getPO( SetorPO.class, dto );

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
	public ArrayList< SetorDTO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, SetorDTO dto ) throws ApplicationException {

		try {
			SetorPO poFilter = this.modelFactory.getPO( SetorPO.class, dto );

			List< SetorPO > encontrados = this.daoInterface.filtrar( paginacao, camposOrders, poFilter );

			ArrayList< SetorDTO > dtosRetorno = new ArrayList<>();

			for ( SetorPO poCorrente : encontrados ) {
				dtosRetorno.add( this.modelFactory.getDTO( SetorDTO.class, poCorrente ) );
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
	public SetorDTO filtrarPorId( String id ) throws ApplicationException {

		try {

			if ( Utilidades.isNuloOuVazio( id ) ) {
				throw new ApplicationException( "Id não informado!" );
			}

			SetorPO encontrado = this.daoInterface.filtrarPorId( Long.valueOf( id ) );

			return this.modelFactory.getDTO( SetorDTO.class, encontrado );
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
	public List< SetorDTO > filtrarPorSafra( String idSafra, String nomeSetor ) throws ApplicationException {

		try {

			Long safraId = null;
			if ( !Utilidades.isNuloOuVazio( idSafra ) ) {
				safraId = Long.valueOf( idSafra );
			}

			/*if ( Utilidades.isNuloOuVazio( idSafra ) ) {
				throw new ApplicationException( "Safra nao informado!" );
			}*/

			List< SetorPO > encontrados = this.daoInterface.filtrarPorSafra( safraId, nomeSetor );

			List< SetorDTO > dtos = new ArrayList<>();
			for ( SetorPO setorCorrente : encontrados ) {
				dtos.add( modelFactory.getDTO( SetorDTO.class, setorCorrente ) );
			}

			return dtos;

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
	public List< SetorSafraDTO > filtrarPorSafraSetorCultura( String idSafra, String idSetor, String idCultura ) throws ApplicationException {

		try {

			Long safraId = null;
			if ( !Utilidades.isNuloOuVazio( idSafra ) ) {
				safraId = Long.valueOf( idSafra );
			}
			Long setorId = null;
			if ( !Utilidades.isNuloOuVazio( idSetor ) ) {
				setorId = Long.valueOf( idSetor );
			}
			Long culturaId = null;
			if ( !Utilidades.isNuloOuVazio( idCultura ) ) {
				culturaId = Long.valueOf( idCultura );
			}

			List< SetorSafraPO > encontrados = this.daoInterface.filtrarPorSafraSetorCultura( safraId, setorId, culturaId );
			ModelFactory< SetorSafraPO, SetorSafraDTO > modelFactorySetorSafra = new ModelFactory<>();
			List< SetorSafraDTO > dtos = new ArrayList<>();
			for ( SetorSafraPO setorCorrente : encontrados ) {
				dtos.add( modelFactorySetorSafra.getDTO( SetorSafraDTO.class, setorCorrente ) );
			}

			return dtos;

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