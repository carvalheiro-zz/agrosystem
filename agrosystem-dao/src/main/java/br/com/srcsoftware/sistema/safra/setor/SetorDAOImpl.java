package br.com.srcsoftware.sistema.safra.setor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.safra.setorsafra.SetorSafraPO;

public class SetorDAOImpl implements SetorDAOInterface{

	@Override
	public SetorPO inserir( HibernateConnection hibernate, SetorPO po ) throws ApplicationException {
		try {
			return (SetorPO) hibernate.inserir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void alterar( HibernateConnection hibernate, SetorPO po ) throws ApplicationException {
		try {
			hibernate.alterar( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void excluir( HibernateConnection hibernate, SetorPO po ) throws ApplicationException {
		try {
			hibernate.excluir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public SetorPO filtrarPorId( Long id ) throws ApplicationException {
		try {
			return (SetorPO) new HibernateConnection().filtrarPorId( SetorPO.class, id );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@SuppressWarnings( "unchecked" )
	@Override
	public List< SetorPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, SetorPO poFilter ) throws ApplicationException {

		HibernateConnection hibernate = new HibernateConnection();
		try {
			hibernate.iniciarTransacao();

			//  CRIANDO A CRITERIA COM BASE NA CRITERIABUILDER
			CriteriaBuilder builder = hibernate.getSessaoCorrente().getCriteriaBuilder();

			//  CRIANDO A CRITERIA DE CONSULTA COM BASE NA CRITERIABUILDER
			CriteriaQuery< SetorPO > criteria = builder.createQuery( SetorPO.class );

			// DEFININDO O OBJETO BASE DA SELECT
			Root< SetorPO > root = criteria.from( SetorPO.class );

			// Definindo os parametros do WHERE
			Predicate[ ] parametros = this.montarParametros( builder, root, poFilter );

			// Executando A Consulta
			List< SetorPO > encontrados = hibernate.filtrar( paginacao, camposOrders, builder, root, criteria, parametros, SetorPO.class );

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

	private Predicate[ ] montarParametros( CriteriaBuilder builder, Root< SetorPO > root, SetorPO poFilter ) {
		ArrayList< Predicate > lista = new ArrayList<>();

		if ( poFilter == null ) {
			return null;
		}
		if ( !Utilidades.isNuloOuVazio( poFilter.getId() ) ) {
			lista.add( builder.equal( root.get( "id" ), poFilter.getId() ) );
		} else {
			if ( !Utilidades.isNuloOuVazio( poFilter.getNomeCompleto() ) ) {
				lista.add( builder.like( root.get( "nomeCompleto" ), poFilter.getNomeCompleto() + "%" ) );
			}

			if ( !Utilidades.isNuloOuVazio( poFilter.getNome() ) ) {
				lista.add( builder.like( root.get( "nome" ), poFilter.getNome() + "%" ) );
			}

			if ( !Utilidades.isNuloOuVazio( poFilter.getSubSetor() ) ) {
				lista.add( builder.equal( root.get( "subSetor" ), poFilter.getSubSetor() ) );
			}
		}
		if ( lista.isEmpty() ) {
			return null;
		} else {
			return lista.toArray( new Predicate[ 1 ] );
		}
	}

	@SuppressWarnings( "unchecked" )
	@Override
	public List< SetorPO > filtrarPorSafra( Long idSafra, String nomeSetor ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {

			hibernate.iniciarTransacao();

			final StringBuffer HQL = new StringBuffer();
			HQL.append( " SELECT distinct setorSafra.setor FROM " );
			HQL.append( " SafraPO safra inner join safra.setoresSafras setorSafra " );
			HQL.append( " WHERE 1=1" );

			HashMap< String, Object > parametros = new HashMap<>();

			if ( !Utilidades.isNuloOuVazio( idSafra ) ) {
				HQL.append( " and safra.id = :idSafraParam " );
				parametros.put( "idSafraParam", idSafra );
			}

			if ( !Utilidades.isNuloOuVazio( nomeSetor ) ) {
				HQL.append( " and setorSafra.setor.nome like :nomeSetorParam " );
				parametros.put( "nomeSetorParam", nomeSetor.concat( "%" ) );
			}

			HashMap< String, String > camposOrdenacao = new HashMap<>();
			camposOrdenacao.put( "setorSafra.setor.nome", "ASC" );

			Query query = hibernate.getSessaoCorrente().createQuery( HQL.toString() );

			for ( String key : parametros.keySet() ) {
				query.setParameter( key, parametros.get( key ) );
			}

			List< SetorPO > encontrados = query.getResultList();

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

	@SuppressWarnings( "unchecked" )
	@Override
	public List< SetorSafraPO > filtrarPorSafraSetorCultura( Long idSafra, Long idSetor, Long idCultura ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {

			hibernate.iniciarTransacao();
			/*SELECT 
			ss.* 
			FROM
			sistema_setoressafra ss,
			sistema_setores s,
			sistema_variedades v
			WHERE 1=1
			and v.idCultura = 7
			and ss.idSafra = 2
			and s.id = 4
			and ss.idSetor = s.id
			and ss.idVariedade = v.id	*/

			final StringBuffer HQL = new StringBuffer();
			/*HQL.append( " SELECT setorSafra.setor FROM " );
			HQL.append( " SafraPO safra inner join safra.setoresSafras setorSafra " );
			HQL.append( " SetorPO setor inner join setorSafra.setor setor " );
			HQL.append( " VariedadePO variedade inner join setorSafra.variedade variedade " );
			HQL.append( " CulturaPO cultura inner join variedade.cultura cultura " );*/
			HQL.append( " SELECT setorSafra FROM " );
			HQL.append( " SafraPO safra inner join safra.setoresSafras setorSafra " );
			HQL.append( " inner join setorSafra.variedade.cultura cultura " );
			HQL.append( " WHERE 1=1" );

			HashMap< String, Object > parametros = new HashMap<>();

			if ( !Utilidades.isNuloOuVazio( idSafra ) ) {
				HQL.append( " and safra.id = :idSafraParam " );
				parametros.put( "idSafraParam", idSafra );
			}

			if ( !Utilidades.isNuloOuVazio( idSetor ) ) {
				HQL.append( " and setorSafra.setor.id = :idSetorParam " );
				parametros.put( "idSetorParam", idSetor );
			}

			if ( !Utilidades.isNuloOuVazio( idCultura ) ) {
				HQL.append( " and cultura.id = :idCulturaParam " );
				parametros.put( "idCulturaParam", idCultura );
			}

			HashMap< String, String > camposOrdenacao = new HashMap<>();
			camposOrdenacao.put( "setorSafra.setor.nome", "ASC" );

			Query query = hibernate.getSessaoCorrente().createQuery( HQL.toString() );

			for ( String key : parametros.keySet() ) {
				query.setParameter( key, parametros.get( key ) );
			}

			List< SetorSafraPO > encontrados = query.getResultList();

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
}
