package br.com.srcsoftware.sistema.notafiscal.rateio.centrocusto;

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

public class CentroCustoDAOImpl implements CentroCustoDAOInterface{

	@Override
	public CentroCustoPO inserir( HibernateConnection hibernate, CentroCustoPO po ) throws ApplicationException {
		try {
			return (CentroCustoPO) hibernate.inserir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void alterar( HibernateConnection hibernate, CentroCustoPO po ) throws ApplicationException {
		try {
			hibernate.alterar( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void excluir( HibernateConnection hibernate, CentroCustoPO po ) throws ApplicationException {
		try {
			hibernate.excluir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public CentroCustoPO filtrarPorId( Long id ) throws ApplicationException {
		try {
			return (CentroCustoPO) new HibernateConnection().filtrarPorId( CentroCustoPO.class, id );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@SuppressWarnings( "unchecked" )
	@Override
	public List< CentroCustoPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, CentroCustoPO poFilter ) throws ApplicationException {

		HibernateConnection hibernate = new HibernateConnection();
		try {
			hibernate.iniciarTransacao();

			//  CRIANDO A CRITERIA COM BASE NA CRITERIABUILDER
			CriteriaBuilder builder = hibernate.getSessaoCorrente().getCriteriaBuilder();

			//  CRIANDO A CRITERIA DE CONSULTA COM BASE NA CRITERIABUILDER
			CriteriaQuery< CentroCustoPO > criteria = builder.createQuery( CentroCustoPO.class );

			// DEFININDO O OBJETO BASE DA SELECT
			Root< CentroCustoPO > root = criteria.from( CentroCustoPO.class );

			// Definindo os parametros do WHERE
			Predicate[ ] parametros = this.montarParametros( builder, root, poFilter );

			// Executando A Consulta
			List< CentroCustoPO > encontrados = hibernate.filtrar( paginacao, camposOrders, builder, root, criteria, parametros, CentroCustoPO.class );

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

	private Predicate[ ] montarParametros( CriteriaBuilder builder, Root< CentroCustoPO > root, CentroCustoPO poFilter ) {
		ArrayList< Predicate > lista = new ArrayList<>();

		if ( poFilter == null ) {
			return null;
		}

		if ( !Utilidades.isNuloOuVazio( poFilter.getId() ) ) {
			lista.add( builder.equal( root.get( "id" ), poFilter.getId() ) );
		} else {

			if ( !Utilidades.isNuloOuVazio( poFilter.getCodigo() ) ) {
				lista.add( builder.like( root.get( "codigo" ), poFilter.getCodigo() + "%" ) );
			}
			if ( !Utilidades.isNuloOuVazio( poFilter.getDescricao() ) ) {
				lista.add( builder.like( root.get( "descricao" ), poFilter.getDescricao() + "%" ) );
			}
			if ( !Utilidades.isNuloOuVazio( poFilter.getTipo() ) ) {
				lista.add( builder.equal( root.get( "tipo" ), poFilter.getTipo() ) );
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
	public List< CentroCustoPO > filtrarPorTipoNotaFiscalRateioAgrupado( String codigo, String tipoNotaFiscalRateio ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {

			hibernate.iniciarTransacao();

			final StringBuffer HQL = new StringBuffer();
			HQL.append( " SELECT centroCusto FROM " );
			HQL.append( " CentroCustoPO centroCusto " );
			HQL.append( " WHERE 1=1" );
			HashMap< String, Object > parametros = new HashMap<>();

			if ( !Utilidades.isNuloOuVazio( codigo ) ) {
				HQL.append( " and centroCusto.codigo like :codigoParam " );
				parametros.put( "codigoParam", codigo + "%" );
			}

			if ( !Utilidades.isNuloOuVazio( tipoNotaFiscalRateio ) ) {
				if ( tipoNotaFiscalRateio.equalsIgnoreCase( "Investimento/Despesa" ) ) {
					HQL.append( " and (centroCusto.tipo = :tipoInvestimentoParam or centroCusto.tipo = :tipoDespesaParam)" );
					parametros.put( "tipoInvestimentoParam", "Investimento" );
					parametros.put( "tipoDespesaParam", "Despesa" );
				}

				if ( tipoNotaFiscalRateio.equalsIgnoreCase( "Receita" ) ) {
					HQL.append( " and centroCusto.tipo = :tipoReceitaParam " );
					parametros.put( "tipoReceitaParam", "Receita" );
				}
			}

			Query query = hibernate.getSessaoCorrente().createQuery( HQL.toString() );

			for ( String key : parametros.keySet() ) {
				query.setParameter( key, parametros.get( key ) );
			}

			ArrayList< CentroCustoPO > encontrados = (ArrayList< CentroCustoPO >) query.getResultList();

			hibernate.commitTransacao();

			return encontrados;

		} catch ( ApplicationException e ) {
			hibernate.rollbackTransacao();
			throw e;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();
			throw new ApplicationException( "Erro inesperado[999]" + System.lineSeparator() + e.getCause().getMessage().trim(), e );
		}
	}
}
