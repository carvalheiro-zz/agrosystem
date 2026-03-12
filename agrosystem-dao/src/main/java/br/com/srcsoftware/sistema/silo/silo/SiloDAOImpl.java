package br.com.srcsoftware.sistema.silo.silo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.transform.ResultTransformer;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.utilidades.Constantes;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;

public class SiloDAOImpl implements SiloDAOInterface{

	@Override
	public SiloPO inserir( HibernateConnection hibernate, SiloPO po ) throws ApplicationException {
		try {
			return (SiloPO) hibernate.inserir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void alterar( HibernateConnection hibernate, SiloPO po ) throws ApplicationException {
		try {
			hibernate.alterar( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void excluir( HibernateConnection hibernate, SiloPO po ) throws ApplicationException {
		try {
			hibernate.excluir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public SiloPO filtrarPorId( Long id ) throws ApplicationException {
		try {
			return (SiloPO) new HibernateConnection().filtrarPorId( SiloPO.class, id );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@SuppressWarnings( "unchecked" )
	@Override
	public List< SiloPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, SiloPO poFilter ) throws ApplicationException {

		HibernateConnection hibernate = new HibernateConnection();
		try {
			hibernate.iniciarTransacao();

			//  CRIANDO A CRITERIA COM BASE NA CRITERIABUILDER
			CriteriaBuilder builder = hibernate.getSessaoCorrente().getCriteriaBuilder();

			//  CRIANDO A CRITERIA DE CONSULTA COM BASE NA CRITERIABUILDER
			CriteriaQuery< SiloPO > criteria = builder.createQuery( SiloPO.class );

			// DEFININDO O OBJETO BASE DA SELECT
			Root< SiloPO > root = criteria.from( SiloPO.class );

			// Definindo os parametros do WHERE
			Predicate[ ] parametros = this.montarParametros( builder, root, poFilter );

			// Executando A Consulta
			List< SiloPO > encontrados = hibernate.filtrar( paginacao, camposOrders, builder, root, criteria, parametros, SiloPO.class );

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

	private Predicate[ ] montarParametros( CriteriaBuilder builder, Root< SiloPO > root, SiloPO poFilter ) {
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

	@SuppressWarnings( { "serial", "deprecation", "unchecked" } )
	@Override
	public InformacoesSiloPOJO filtrarInformacoesSilo( Long idSilo, Long idCultura ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			StringBuffer SQL = new StringBuffer();
			SQL.append( " SELECT saldo.silo, saldo.cultura, saldo.capacidade, sum(saldo.quantidade) as quantidade FROM " );
			SQL.append( " (SELECT  " );
			SQL.append( " 	s.nome as silo, c.nome as cultura, s.capacidade ,sum(eg.pesoLiquido) as quantidade " );
			SQL.append( " FROM  " );
			SQL.append( Constantes.SCHEMA + ".sistema_entradasgrao eg, " );
			SQL.append( Constantes.SCHEMA + ".sistema_silos s, " );
			SQL.append( Constantes.SCHEMA + ".sistema_variedades v, " );
			SQL.append( Constantes.SCHEMA + ".sistema_culturas c " );
			SQL.append( " where 1=1 " );
			SQL.append( " 	and v.idCultura = :idCulturaParam " );
			SQL.append( "     and s.id = :idSiloParam " );
			SQL.append( " 	and v.idCultura = c.id " );
			SQL.append( " 	and eg.idVariedade = v.id " );
			SQL.append( " 	and eg.idSilo = s.id " );
			SQL.append( " group by s.id " );

			SQL.append( " UNION ALL " );

			SQL.append( " SELECT " );
			SQL.append( " 	s.nome as silo, c.nome as cultura, s.capacidade ,sum(COALESCE(sg.pesoLiquido01,0)*-1) as quantidade " );
			SQL.append( " FROM " );
			SQL.append( Constantes.SCHEMA + ".sistema_saidasgrao sg, " );
			SQL.append( Constantes.SCHEMA + ".sistema_silos s, " );
			SQL.append( Constantes.SCHEMA + ".sistema_culturas c " );
			SQL.append( " where 1=1 " );
			SQL.append( " 	and c.id = :idCulturaParam " );
			SQL.append( "     and s.id = :idSiloParam " );
			SQL.append( " 	and sg.idCultura = c.id " );
			SQL.append( " 	and sg.idSilo01 = s.id " );
			SQL.append( " group by s.id " );

			SQL.append( " UNION ALL " );

			SQL.append( " SELECT  " );
			SQL.append( " 	s.nome as silo, c.nome as cultura, s.capacidade ,sum(COALESCE(sg.pesoLiquido02,0)*-1) as quantidade " );
			SQL.append( " FROM  " );
			SQL.append( Constantes.SCHEMA + ".sistema_saidasgrao sg, " );
			SQL.append( Constantes.SCHEMA + ".sistema_silos s, " );
			SQL.append( Constantes.SCHEMA + ".sistema_culturas c " );
			SQL.append( " where 1=1 " );
			SQL.append( " 	and c.id = :idCulturaParam " );
			SQL.append( "   and s.id = :idSiloParam " );
			SQL.append( " 	and sg.idCultura = c.id " );
			SQL.append( " 	and sg.idSilo02 = s.id " );
			SQL.append( " group by s.id " );

			SQL.append( " UNION ALL " );

			SQL.append( " SELECT    " );
			SQL.append( "    s.nome as silo, c.nome as cultura, s.capacidade ,COALESCE(sum(COALESCE(aj.quantidade,0)),0) as quantidade  " );
			SQL.append( " FROM  " );
			SQL.append( "    esafra.sistema_ajustes aj, " );
			SQL.append( "    esafra.sistema_culturas c,  " );
			SQL.append( "   esafra.sistema_silos s " );
			SQL.append( " WHERE 1=1 " );
			SQL.append( "    AND aj.idCultura = :idCulturaParam " );
			SQL.append( "    AND aj.idSilo = :idSiloParam " );
			SQL.append( "    AND aj.tipo = 'Sobra de Classificação' " );
			SQL.append( "    and aj.idCultura = c.id  " );
			SQL.append( "    and aj.idSilo = s.id " );
			SQL.append( "    group by s.id " );

			SQL.append( " UNION ALL " );

			SQL.append( " SELECT  " );
			SQL.append( "    s.nome as silo, c.nome as cultura, s.capacidade ,sum(COALESCE(aj.quantidade,0)*-1) as quantidade  " );
			SQL.append( " FROM  " );
			SQL.append( "    esafra.sistema_ajustes aj, " );
			SQL.append( "    esafra.sistema_culturas c,  " );
			SQL.append( "    esafra.sistema_silos s  " );
			SQL.append( " WHERE 1=1 " );
			SQL.append( "    AND aj.idCultura = :idCulturaParam " );
			SQL.append( "    AND aj.idSilo = :idSiloParam " );
			SQL.append( "    AND aj.tipo = 'Quebra de Classificação' " );
			SQL.append( "    and aj.idCultura = c.id  " );
			SQL.append( "   and aj.idSilo = s.id " );
			SQL.append( "   group by s.id  " );

			SQL.append( " ) as saldo" );
			//SQL.append( " ) as saldo group by saldo.silo" );

			Query query = hibernate.getSessaoCorrente().createNativeQuery( SQL.toString() ).setResultTransformer( new ResultTransformer(){
				@Override
				public Object transformTuple( Object[ ] tuple, String[ ] aliases ) {
					InformacoesSiloPOJO pojo = new InformacoesSiloPOJO();

					if ( Utilidades.isNuloOuVazio( tuple[ 0 ] ) ) {
						return null;
					}

					pojo.setSilo( (String) tuple[ 0 ] );
					pojo.setCultura( (String) tuple[ 1 ] );
					pojo.setCapacidadeSilo( ( (BigDecimal) tuple[ 2 ] ).toString() );
					pojo.setSaldoSilo( ( Utilidades.parseBigDecimal( ( (BigDecimal) tuple[ 3 ] ) ) ) );

					return pojo;
				}

				@SuppressWarnings( "rawtypes" )
				@Override
				public List transformList( List collection ) {
					return collection;
				}
			} );

			query.setParameter( "idSiloParam", idSilo );
			query.setParameter( "idCulturaParam", idCultura );

			InformacoesSiloPOJO encontrado = (InformacoesSiloPOJO) query.getSingleResult();

			hibernate.commitTransacao();

			return encontrado;

		} catch ( ApplicationException e ) {
			hibernate.rollbackTransacao();
			throw e;
		} catch ( NoResultException e ) {
			hibernate.rollbackTransacao();
			return null;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();
			throw new ApplicationException( "Erro inesperado[999]" + System.lineSeparator() + e.getMessage().trim(), e );
		}

	}

}
