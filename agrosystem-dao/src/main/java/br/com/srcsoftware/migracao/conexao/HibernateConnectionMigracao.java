package br.com.srcsoftware.migracao.conexao;

import java.util.List;
import java.util.Set;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolation;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import br.com.srcsoftware.abstracts.AbstractPO;
import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.utilidades.Constantes;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;

public class HibernateConnectionMigracao{

	private transient Logger logger = Logger.getLogger( this.getClass().getName() );

	protected static StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;

	protected Session sessaoCorrente;
	protected Transaction transacao;

	public SessionFactory getSessionFactory() throws ApplicationException {
		if ( sessionFactory == null ) {
			try {

				// Create registry builder
				StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

				// Apply settings
				registryBuilder.applySetting( Environment.DRIVER, "com.mysql.jdbc.Driver" );
				registryBuilder.applySetting( Environment.URL, "jdbc:mysql://" + Constantes.HOST + "/" + Constantes.SCHEMA_MIGRACAO );
				registryBuilder.applySetting( Environment.DIALECT, "org.hibernate.dialect.MySQL5InnoDBDialect" );

				// Create registry
				registry = registryBuilder.configure( "hibernate_migracao.cfg.xml" ).build();

				// Create MetadataSources
				MetadataSources sources = new MetadataSources( registry );

				// Create Metadata
				MetadataBuilder metadataBuilder = sources.getMetadataBuilder();
				// specify the schema name to use for tables, etc when none is explicitly specified
				metadataBuilder.applyImplicitSchemaName( Constantes.SCHEMA_MIGRACAO );

				// Create Metadata Builder
				Metadata metadata = metadataBuilder.build();

				// Create SessionFactory
				sessionFactory = metadata.getSessionFactoryBuilder().build();

			} catch ( org.hibernate.service.spi.ServiceException e ) {
				e.printStackTrace();
				if ( registry != null ) {
					StandardServiceRegistryBuilder.destroy( registry );
				}

				if ( e.getCause() != null && e.getCause() instanceof org.hibernate.exception.JDBCConnectionException ) {
					if ( e.getCause().getCause() != null ) {
						this.logger.error( e.getCause().getCause().getMessage(), e );
						throw new ApplicationException( e.getCause().getCause().getMessage(), e );
					}

					this.logger.error( e.getCause().getMessage(), e );
					throw new ApplicationException( e.getCause().getMessage(), e );
				}

				this.logger.error( e.getMessage(), e );
				throw new ApplicationException( e.getMessage(), e );

			} catch ( Exception e ) {
				e.printStackTrace();
				if ( registry != null ) {
					StandardServiceRegistryBuilder.destroy( registry );
				}

				this.logger.error( e.getMessage(), e );
				throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage(), e );
			}
		}
		return sessionFactory;
	}

	public static void shutdown() throws ApplicationException {
		try {
			if ( registry != null ) {
				StandardServiceRegistryBuilder.destroy( registry );
			}
			registry = null;
			sessionFactory = null;
		} catch ( Exception e ) {
			Logger.getLogger( HibernateConnection.class.getName() ).error( e.getMessage(), e );
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage(), e );
		}
	}

	private void setSessaoCorrente( Session sessaoCorrente ) {
		this.sessaoCorrente = sessaoCorrente;
	}

	public Session getSessaoCorrente() throws ApplicationException {
		if ( this.sessaoCorrente == null ) {
			throw new ApplicationException( "Sess�o n�o esta Aberta!" );
		}

		return this.sessaoCorrente;
	}

	private void abrirSessao() throws ApplicationException {
		try {
			this.setSessaoCorrente( this.getSessionFactory().openSession() );
		} catch ( ApplicationException e ) {
			this.logger.error( e.getMessage(), e );
			throw e;
		} catch ( Exception e ) {
			this.logger.error( e.getMessage(), e );
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage(), e );
		}
	}

	private void fecharSessao() throws ApplicationException {
		try {
			if ( this.sessaoCorrente == null ) {
				throw new ApplicationException( "Sess�o n�o esta Aberta!" );
			}

			this.getSessaoCorrente().close();
			this.setSessaoCorrente( null );
		} catch ( Exception e ) {
			this.logger.error( e.getMessage(), e );
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage(), e );
		}
	}

	public void iniciarTransacao() throws ApplicationException {
		try {
			this.abrirSessao();
			this.transacao = this.getSessaoCorrente().beginTransaction();
		} catch ( ApplicationException e ) {
			this.logger.error( e.getMessage(), e );
			throw e;
		} catch ( Exception e ) {
			this.logger.error( e.getMessage(), e );
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage(), e );
		}
	}

	public void commitTransacao() throws ApplicationException {
		try {
			if ( this.transacao == null ) {
				throw new ApplicationException( "Transação n�o Inicializada!" );
			}

			this.transacao.commit();
			this.fecharSessao();
			this.transacao = null;
		} catch ( org.hibernate.exception.ConstraintViolationException e ) {
			if ( ( e.getCause() != null ) && ( e.getCause() instanceof java.sql.BatchUpdateException ) ) {
				if ( e.getCause().getMessage().contains( "Cannot delete or update a parent row" ) ) {
					throw new ApplicationException( "Impossivel excluir este registro, ele est� relacionado com algum outro cadastro. [" + e.getCause().getMessage().trim() + "]", e );
				}
			}
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		} catch ( javax.persistence.PersistenceException e ) {
			if ( ( e.getCause() != null ) && ( e.getCause() instanceof java.sql.BatchUpdateException ) || e.getCause() instanceof org.hibernate.exception.ConstraintViolationException ) {
				if ( ( e.getCause().getCause() != null ) && ( e.getCause().getCause() instanceof java.sql.BatchUpdateException ) ) {
					if ( e.getCause().getCause().getMessage().contains( "Cannot delete or update a parent row" ) ) {
						throw new ApplicationException( "Impossivel excluir este registro, ele est� relacionado com algum outro cadastro. [" + e.getCause().getCause().getMessage().trim() + "]", e );
					} else if ( e.getCause().getCause().getMessage().contains( "Duplicate" ) ) {
						throw new ApplicationException( "Entrada duplicada encontrada. [" + e.getCause().getCause().getMessage().trim() + "]", e );
					}
					throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getCause().getCause().getMessage().trim(), e );
				}
				throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getCause().getMessage().trim(), e );
			}
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		} catch ( javax.validation.ConstraintViolationException e ) {
			Set< ConstraintViolation< ? > > constraintViolations = e.getConstraintViolations();

			StringBuilder textValidatorBuilder = new StringBuilder();
			constraintViolations.forEach( s -> textValidatorBuilder.append( s.getMessage().concat( " | " ) ) );

			String textValidator = textValidatorBuilder.toString().trim();

			if ( constraintViolations.size() != 0 ) {
				textValidator = textValidator.substring( 0, textValidator.length() - 1 );

				if ( !Utilidades.isNuloOuVazio( textValidator ) ) {
					throw new ApplicationException( textValidator );
				}
			}
			throw new ApplicationException( e.getMessage() );
		}

	}

	public void rollbackTransacao() throws ApplicationException {
		try {
			if ( this.transacao == null ) {
				return;
			}

			this.transacao.rollback();
			this.fecharSessao();
			this.transacao = null;
		} catch ( ApplicationException e ) {
			this.logger.error( e.getMessage(), e );
			throw e;
		} catch ( Exception e ) {
			this.logger.error( e.getMessage(), e );
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage(), e );
		}
	}

	/** M�todo de Consulta */
	@SuppressWarnings( { "rawtypes", "unchecked" } )
	public AbstractPO filtrarPorId( Class clazz, Long id ) throws ApplicationException {

		try {
			this.iniciarTransacao();

			//  CRIANDO A CRITERIA COM BASE NA CRITERIABUILDER
			CriteriaBuilder builder = this.getSessaoCorrente().getCriteriaBuilder();
			CriteriaQuery criteria = builder.createQuery( clazz );
			Root root = null;

			// Definindo o FROM
			root = criteria.from( clazz );

			// Deixando a Criteria preparada pra consulta
			criteria.select( root );

			// Definindo os parametros do WHERE
			Predicate idParam = builder.equal( root.get( "id" ), id );

			// Adicionando a Clausula WHERE e AND
			criteria.where( idParam );

			// Executando A CRITERIA
			AbstractPO encontrado = (AbstractPO) this.getSessaoCorrente().createQuery( criteria.distinct( true ) ).getSingleResult();

			this.commitTransacao();

			return encontrado;

		} catch ( NoResultException e ) {
			this.rollbackTransacao();
			return null;
		} catch ( Throwable e ) {
			this.rollbackTransacao();
			throw new ApplicationException( "Erro desconhecido! ", e );
		}
	}

	/**
	 * PS:PERMITE ORDENAçãO DE ATE 1 NIVEL DE JOIN ex: (pessoaFisica.razaoSocial)
	 *
	 * @param paginacao
	 * @param camposOrders
	 * @param builder
	 * @param root
	 * @param criteria
	 * @param parametros
	 * @param clazz
	 * @return
	 * @throws ApplicationException
	 *
	 * @author Gabriel Damiani Carvalheiro <gabriel.carvalheiro@gmail.com.br>
	 * @since 11 de dez de 2017 11:09:35
	 * @version 1.0
	 */
	@SuppressWarnings( { "unchecked", "rawtypes" } )
	public List filtrarTodos( Class clazz ) throws ApplicationException {
		try {
			iniciarTransacao();

			//  CRIANDO A CRITERIA COM BASE NA CRITERIABUILDER
			CriteriaBuilder builder = getSessaoCorrente().getCriteriaBuilder();

			//  CRIANDO A CRITERIA DE CONSULTA COM BASE NA CRITERIABUILDER
			CriteriaQuery criteria = builder.createQuery( clazz );

			// DEFININDO O OBJETO BASE DA SELECT
			Root root = criteria.from( clazz );

			criteria.select( root );

			criteria.where();

			// Executando A Consulta
			Query query = this.getSessaoCorrente().createQuery( criteria.distinct( true ) );

			// Executando A CRITERIA
			List encontrados = query.getResultList();

			commitTransacao();

			return encontrados;

		} catch ( ApplicationException e ) {
			rollbackTransacao();
			throw e;
		} catch ( Exception e ) {
			rollbackTransacao();
			throw new ApplicationException( "Erro inesperado[999]" + System.lineSeparator() + e.getMessage().trim(), e );
		}

	}
}
