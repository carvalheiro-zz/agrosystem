package br.com.srcsoftware.sistema.safra.cultura.variedade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.safra.cultura.CulturaPO;

public class VariedadeDAOImpl implements VariedadeDAOInterface{

	@Override
	public VariedadePO inserir( HibernateConnection hibernate, VariedadePO po ) throws ApplicationException {
		try {
			return (VariedadePO) hibernate.inserir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void alterar( HibernateConnection hibernate, VariedadePO po ) throws ApplicationException {
		try {
			hibernate.alterar( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void excluir( HibernateConnection hibernate, VariedadePO po ) throws ApplicationException {
		try {
			hibernate.excluir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public VariedadePO filtrarPorId( Long id ) throws ApplicationException {
		try {
			return (VariedadePO) new HibernateConnection().filtrarPorId( VariedadePO.class, id );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@SuppressWarnings( "unchecked" )
	@Override
	public List< VariedadePO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, VariedadePO poFilter ) throws ApplicationException {

		HibernateConnection hibernate = new HibernateConnection();
		try {
			hibernate.iniciarTransacao();

			//  CRIANDO A CRITERIA COM BASE NA CRITERIABUILDER
			CriteriaBuilder builder = hibernate.getSessaoCorrente().getCriteriaBuilder();

			//  CRIANDO A CRITERIA DE CONSULTA COM BASE NA CRITERIABUILDER
			CriteriaQuery< VariedadePO > criteria = builder.createQuery( VariedadePO.class );

			// DEFININDO O OBJETO BASE DA SELECT
			Root< VariedadePO > root = criteria.from( VariedadePO.class );

			// Definindo os parametros do WHERE
			Predicate[ ] parametros = this.montarParametros( builder, root, poFilter );

			// Executando A Consulta
			List< VariedadePO > encontrados = hibernate.filtrar( paginacao, camposOrders, builder, root, criteria, parametros, VariedadePO.class );

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

	private Predicate[ ] montarParametros( CriteriaBuilder builder, Root< VariedadePO > root, VariedadePO poFilter ) {
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

			if ( !Utilidades.isNuloOuVazio( poFilter.getNomeCompleto() ) ) {
				lista.add( builder.like( root.get( "nomeCompleto" ), "%" + poFilter.getNomeCompleto() + "%" ) );
			}

			// MONTANDO JOIN CULTURA
			if ( !Utilidades.isNuloOuVazio( poFilter.getCultura() ) ) {

				Join< VariedadePO, CulturaPO > culturaJoin = null;

				if ( !Utilidades.isNuloOuVazio( poFilter.getCultura().getId() ) ) {

					if ( culturaJoin == null ) {
						culturaJoin = root.join( "cultura", JoinType.INNER );
						culturaJoin.alias( "cultura" );
					}

					lista.add( builder.equal( culturaJoin.get( "id" ), poFilter.getCultura().getId() ) );
				} else {
					if ( !Utilidades.isNuloOuVazio( poFilter.getCultura().getNome() ) ) {

						if ( culturaJoin == null ) {
							culturaJoin = root.join( "cultura", JoinType.INNER );
							culturaJoin.alias( "cultura" );
						}
						lista.add( builder.equal( culturaJoin.get( "nome" ), poFilter.getCultura().getNome() ) );
					}
				}
			}
		}
		if ( lista.isEmpty() ) {
			return null;
		} else {
			return lista.toArray( new Predicate[ 1 ] );
		}
	}

	public List filtrarPorSafraSetor( Long idSafra, Long idSetor, String nome ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();
		try {
			hibernate.iniciarTransacao();

			final StringBuffer HQL = new StringBuffer();
			HQL.append( " SELECT distinct setorSafra.variedade FROM " );
			HQL.append( " SafraPO safra inner join safra.setoresSafras setorSafra " );
			HQL.append( " WHERE 1=1" );
			if ( !Utilidades.isNuloOuVazio( idSafra ) ) {
				HQL.append( " and safra.id = :idSafraParam " );
			}
			if ( !Utilidades.isNuloOuVazio( idSetor ) ) {
				HQL.append( " and setorSafra.setor.id = :idSetorParam " );
			}

			if ( !Utilidades.isNuloOuVazio( nome ) ) {
				HQL.append( " and setorSafra.variedade.nome like :nomeVariedadeParam " );
			}

			HQL.append( " order by setorSafra.variedade.nome asc" );
			Query query = hibernate.getSessaoCorrente().createQuery( HQL.toString() );

			if ( !Utilidades.isNuloOuVazio( idSafra ) ) {
				query.setParameter( "idSafraParam", idSafra );
			}
			if ( !Utilidades.isNuloOuVazio( idSetor ) ) {
				query.setParameter( "idSetorParam", idSetor );
			}

			if ( !Utilidades.isNuloOuVazio( nome ) ) {
				query.setParameter( "nomeVariedadeParam", nome.concat( "%" ) );
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
