package br.com.srcsoftware.sistema.pessoa.funcionario.ferias;

import java.math.BigDecimal;
import java.time.LocalDate;
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

import org.hibernate.transform.ResultTransformer;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.pessoa.funcionario.FuncionarioPO;
import br.com.srcsoftware.modular.manager.utilidades.Constantes;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;

public class FeriasDAOImpl implements FeriasDAOInterface{

	@Override
	public FeriasPO inserir( HibernateConnection hibernate, FeriasPO po ) throws ApplicationException {
		try {
			return (FeriasPO) hibernate.inserir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void alterar( HibernateConnection hibernate, FeriasPO po ) throws ApplicationException {
		try {
			hibernate.alterar( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void excluir( HibernateConnection hibernate, FeriasPO po ) throws ApplicationException {
		try {
			hibernate.excluir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public FeriasPO filtrarPorId( Long id ) throws ApplicationException {
		try {
			return (FeriasPO) new HibernateConnection().filtrarPorId( FeriasPO.class, id );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@SuppressWarnings( "unchecked" )
	@Override
	public List< FeriasPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, HashMap< String, ArrayList< Object > > camposBetween, FeriasPO poFilter ) throws ApplicationException {

		HibernateConnection hibernate = new HibernateConnection();
		try {
			hibernate.iniciarTransacao();

			//  CRIANDO A CRITERIA COM BASE NA CRITERIABUILDER
			CriteriaBuilder builder = hibernate.getSessaoCorrente().getCriteriaBuilder();

			//  CRIANDO A CRITERIA DE CONSULTA COM BASE NA CRITERIABUILDER
			CriteriaQuery< FeriasPO > criteria = builder.createQuery( FeriasPO.class );

			// DEFININDO O OBJETO BASE DA SELECT
			Root< FeriasPO > root = criteria.from( FeriasPO.class );

			// Definindo os parametros do WHERE
			Predicate[ ] parametros = this.montarParametros( builder, root, poFilter, camposBetween );

			// Executando A Consulta
			List< FeriasPO > encontrados = hibernate.filtrar( paginacao, camposOrders, builder, root, criteria, parametros, FeriasPO.class );

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

	private Predicate[ ] montarParametros( CriteriaBuilder builder, Root< FeriasPO > root, FeriasPO poFilter, HashMap< String, ArrayList< Object > > camposBetween ) {
		ArrayList< Predicate > lista = new ArrayList<>();

		/** PERMITE O BEETWEEN DE 0 NIVEL DE JOIN ex: (data) */
		lista.addAll( HibernateConnection.montarBetween( root, builder, camposBetween ) );

		if ( poFilter == null ) {
			if ( lista.isEmpty() ) {
				return null;
			} else {
				return lista.toArray( new Predicate[ 1 ] );
			}
		}

		if ( !Utilidades.isNuloOuVazio( poFilter.getId() ) ) {
			lista.add( builder.equal( root.get( "id" ), poFilter.getId() ) );
		} else {

			if ( !Utilidades.isNuloOuVazio( poFilter.getTipo() ) ) {
				lista.add( builder.equal( root.get( "tipo" ), poFilter.getTipo() ) );
			}

			// MONTANDO JOIN
			if ( poFilter.getColaborador() != null && poFilter.getColaborador().getId() != null ) {
				Join< FeriasPO, FuncionarioPO > colaboradorJoin = root.join( "colaborador", JoinType.INNER );
				colaboradorJoin.alias( "colaborador" );

				if ( poFilter.getColaborador().getId() != null ) {
					lista.add( builder.equal( colaboradorJoin.get( "id" ), poFilter.getColaborador().getId() ) );
				}
			}
		}

		if ( lista.isEmpty() ) {
			return null;
		} else {
			return lista.toArray( new Predicate[ 1 ] );
		}
	}

	@Override
	@SuppressWarnings( { "serial", "deprecation", "unchecked" } )
	public List< SaldoFeriasPOJO > filtrarSaldo( Long idColaborador, LocalDate dataInicial, LocalDate dataFinal ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			StringBuffer SQL = new StringBuffer();
			SQL.append( " SELECT " );
			SQL.append( "     adquiridas.idColaborador as colaborador, " );
			SQL.append( "     adquiridas.adquiridas as adquiridas, " );
			SQL.append( "     IFNULL(cumpridas.cumpridas, 0) as cumpridas, " );
			SQL.append( "     IFNULL(vendidas.vendidas, 0) as vendidas " );
			SQL.append( "  FROM " );
			SQL.append( "     (SELECT " );
			SQL.append( "         adquiridas.idColaborador, SUM(adquiridas.quantidadeHoras) as adquiridas, adquiridas.tipo " );
			SQL.append( "     FROM " );
			SQL.append( Constantes.SCHEMA + ".sistema_ferias adquiridas " );
			SQL.append( "     WHERE 1=1 " );
			if ( idColaborador != null ) {
				SQL.append( "     AND adquiridas.idColaborador = :idColaboradorParam " );
			}
			SQL.append( "         AND adquiridas.tipo = 'Adquirida' " );
			if ( dataInicial != null && dataFinal != null ) {
				SQL.append( "     AND adquiridas.data between :dataInicialParam and :dataFinalParam " );
			}
			SQL.append( "     group by adquiridas.idColaborador) as adquiridas " );
			SQL.append( " left Join " );
			SQL.append( "     (SELECT " );
			SQL.append( "         cumpridas.idColaborador, SUM(cumpridas.quantidadeHoras) as cumpridas, cumpridas.tipo " );
			SQL.append( "     FROM " );
			SQL.append( Constantes.SCHEMA + ".sistema_ferias cumpridas " );
			SQL.append( "     WHERE 1=1 " );
			if ( idColaborador != null ) {
				SQL.append( "     AND cumpridas.idColaborador = :idColaboradorParam " );
			}
			SQL.append( "         AND cumpridas.tipo = 'Cumprida' " );
			if ( dataInicial != null && dataFinal != null ) {
				SQL.append( "     AND cumpridas.data between :dataInicialParam and :dataFinalParam " );
			}
			SQL.append( "     group by cumpridas.idColaborador) as cumpridas on cumpridas.idColaborador = adquiridas.idColaborador " );
			SQL.append( " left Join " );
			SQL.append( "     (SELECT " );
			SQL.append( "         vendidas.idColaborador, SUM(vendidas.quantidadeHoras) as vendidas, vendidas.tipo " );
			SQL.append( "     FROM " );
			SQL.append( Constantes.SCHEMA + ".sistema_ferias vendidas " );
			SQL.append( "     WHERE 1=1 " );
			if ( idColaborador != null ) {
				SQL.append( "         AND vendidas.idColaborador = :idColaboradorParam " );
			}
			SQL.append( "         AND vendidas.tipo = 'Vendida' " );
			if ( dataInicial != null && dataFinal != null ) {
				SQL.append( "     AND vendidas.data between :dataInicialParam and :dataFinalParam " );
			}
			SQL.append( "     group by vendidas.idColaborador) as vendidas on vendidas.idColaborador = adquiridas.idColaborador " );

			Query query = hibernate.getSessaoCorrente().createNativeQuery( SQL.toString().substring( 0, SQL.toString().length() - 1 ) ).setResultTransformer( new ResultTransformer(){
				@Override
				public Object transformTuple( Object[ ] tuple, String[ ] aliases ) {
					SaldoFeriasPOJO pojo = new SaldoFeriasPOJO();

					if ( tuple[ 0 ] == null ) {
						return null;
					}

					pojo.setIdColaborador( tuple[ 0 ] == null ? null : tuple[ 0 ].toString() );
					pojo.setAdquiridas( tuple[ 1 ] == null ? null : ( (BigDecimal) tuple[ 1 ] ) );
					pojo.setCumpridas( tuple[ 2 ] == null ? null : ( (BigDecimal) tuple[ 2 ] ) );
					pojo.setVendidas( tuple[ 3 ] == null ? null : ( (BigDecimal) tuple[ 3 ] ) );

					return pojo;
				}

				@SuppressWarnings( "rawtypes" )
				@Override
				public List transformList( List collection ) {
					return collection;
				}
			} );

			if ( idColaborador != null ) {
				query.setParameter( "idColaboradorParam", idColaborador );
			}
			if ( dataInicial != null ) {
				query.setParameter( "dataInicialParam", dataInicial );
			}
			if ( dataFinal != null ) {
				query.setParameter( "dataFinalParam", dataFinal );
			}

			List< SaldoFeriasPOJO > encontrados = query.getResultList();

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
			throw new ApplicationException( "Erro inesperado[999]" + System.lineSeparator() + e.getMessage().trim(), e );
		}

	}
}
