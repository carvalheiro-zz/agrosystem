package br.com.srcsoftware.sistema.safra.cultura;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;

public class CulturaDAOImpl implements CulturaDAOInterface{
	@Override
	public CulturaPO inserir( HibernateConnection hibernate, CulturaPO po ) throws ApplicationException {
		try {
			return (CulturaPO) hibernate.inserir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void alterar( HibernateConnection hibernate, CulturaPO po ) throws ApplicationException {
		try {
			hibernate.alterar( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void excluir( HibernateConnection hibernate, CulturaPO po ) throws ApplicationException {
		try {
			hibernate.excluir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public CulturaPO filtrarPorId( Long id ) throws ApplicationException {
		try {
			return (CulturaPO) new HibernateConnection().filtrarPorId( CulturaPO.class, id );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@SuppressWarnings( "unchecked" )
	@Override
	public List< CulturaPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, CulturaPO poFilter ) throws ApplicationException {

		HibernateConnection hibernate = new HibernateConnection();
		try {
			hibernate.iniciarTransacao();

			//  CRIANDO A CRITERIA COM BASE NA CRITERIABUILDER
			CriteriaBuilder builder = hibernate.getSessaoCorrente().getCriteriaBuilder();

			//  CRIANDO A CRITERIA DE CONSULTA COM BASE NA CRITERIABUILDER
			CriteriaQuery< CulturaPO > criteria = builder.createQuery( CulturaPO.class );

			// DEFININDO O OBJETO BASE DA SELECT
			Root< CulturaPO > root = criteria.from( CulturaPO.class );

			// Definindo os parametros do WHERE
			Predicate[ ] parametros = this.montarParametros( builder, root, poFilter );

			// Executando A Consulta
			List< CulturaPO > encontrados = hibernate.filtrar( paginacao, camposOrders, builder, root, criteria, parametros, CulturaPO.class );

			hibernate.commitTransacao();

			return encontrados;

		} catch ( ApplicationException e ) {
			hibernate.rollbackTransacao();
			throw e;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();
			throw new ApplicationException( "Erro inesperado[999]" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	private Predicate[ ] montarParametros( CriteriaBuilder builder, Root< CulturaPO > root, CulturaPO poFilter ) {
		ArrayList< Predicate > lista = new ArrayList<>();

		if ( poFilter == null ) {
			return null;
		}

		if ( !Utilidades.isNuloOuVazio( poFilter.getId() ) ) {
			lista.add( builder.equal( root.get( "id" ), poFilter.getId() ) );
		} else {

			if ( !Utilidades.isNuloOuVazio( poFilter.getNome() ) ) {
				lista.add( builder.like( root.get( "nome" ), poFilter.getNome() + "%" ) );
			}
		}

		if ( lista.isEmpty() ) {
			return null;
		} else {
			return lista.toArray( new Predicate[ 1 ] );
		}
	}

	@Override
	public List filtrarPorSafraSetor( Long idSafra, Long idSetor, String nome, String variedade ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();
		try {
			hibernate.iniciarTransacao();

			final StringBuffer HQL = new StringBuffer();
			HQL.append( " SELECT distinct setorSafra.variedade.cultura FROM " );
			HQL.append( " SafraPO safra inner join safra.setoresSafras setorSafra " );
			HQL.append( " WHERE 1=1" );
			if ( !Utilidades.isNuloOuVazio( idSafra ) ) {
				HQL.append( " and safra.id = :idSafraParam " );
			}
			if ( !Utilidades.isNuloOuVazio( idSetor ) ) {
				HQL.append( " and setorSafra.setor.id = :idSetorParam " );
			}

			if ( !Utilidades.isNuloOuVazio( nome ) ) {
				HQL.append( " and setorSafra.variedade.cultura.nome like :nomeCulturaParam " );
			}

			if ( !Utilidades.isNuloOuVazio( variedade ) ) {
				HQL.append( " and setorSafra.variedade like :variedadeCulturaParam " );
			}

			HQL.append( " order by setorSafra.variedade.cultura.nome asc" );
			Query query = hibernate.getSessaoCorrente().createQuery( HQL.toString() );

			if ( !Utilidades.isNuloOuVazio( idSafra ) ) {
				query.setParameter( "idSafraParam", idSafra );
			}
			if ( !Utilidades.isNuloOuVazio( idSetor ) ) {
				query.setParameter( "idSetorParam", idSetor );
			}

			if ( !Utilidades.isNuloOuVazio( nome ) ) {
				query.setParameter( "nomeCulturaParam", nome.concat( "%" ) );
			}
			if ( !Utilidades.isNuloOuVazio( variedade ) ) {
				query.setParameter( "variedadeCulturaParam", variedade.concat( "%" ) );
			}

			List encontrados = query.getResultList();

			hibernate.commitTransacao();

			return encontrados;

		} catch ( NoResultException e ) {
			hibernate.rollbackTransacao();
			return null;
		} catch ( ApplicationException e ) {
			hibernate.rollbackTransacao();
			throw e;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();

			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}
}
