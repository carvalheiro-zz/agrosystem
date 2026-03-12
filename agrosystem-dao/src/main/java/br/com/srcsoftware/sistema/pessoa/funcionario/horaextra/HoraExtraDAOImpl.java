package br.com.srcsoftware.sistema.pessoa.funcionario.horaextra;

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

public class HoraExtraDAOImpl implements HoraExtraDAOInterface{

	@Override
	public HoraExtraPO inserir( HibernateConnection hibernate, HoraExtraPO po ) throws ApplicationException {
		try {
			return (HoraExtraPO) hibernate.inserir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void alterar( HibernateConnection hibernate, HoraExtraPO po ) throws ApplicationException {
		try {
			hibernate.alterar( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void excluir( HibernateConnection hibernate, HoraExtraPO po ) throws ApplicationException {
		try {
			hibernate.excluir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public HoraExtraPO filtrarPorId( Long id ) throws ApplicationException {
		try {
			return (HoraExtraPO) new HibernateConnection().filtrarPorId( HoraExtraPO.class, id );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@SuppressWarnings( "unchecked" )
	@Override
	public List< HoraExtraPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, HashMap< String, ArrayList< Object > > camposBetween, HoraExtraPO poFilter ) throws ApplicationException {

		HibernateConnection hibernate = new HibernateConnection();
		try {
			hibernate.iniciarTransacao();

			//  CRIANDO A CRITERIA COM BASE NA CRITERIABUILDER
			CriteriaBuilder builder = hibernate.getSessaoCorrente().getCriteriaBuilder();

			//  CRIANDO A CRITERIA DE CONSULTA COM BASE NA CRITERIABUILDER
			CriteriaQuery< HoraExtraPO > criteria = builder.createQuery( HoraExtraPO.class );

			// DEFININDO O OBJETO BASE DA SELECT
			Root< HoraExtraPO > root = criteria.from( HoraExtraPO.class );

			// Definindo os parametros do WHERE
			Predicate[ ] parametros = this.montarParametros( builder, root, poFilter, camposBetween );

			// Executando A Consulta
			List< HoraExtraPO > encontrados = hibernate.filtrar( paginacao, camposOrders, builder, root, criteria, parametros, HoraExtraPO.class );

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

	private Predicate[ ] montarParametros( CriteriaBuilder builder, Root< HoraExtraPO > root, HoraExtraPO poFilter, HashMap< String, ArrayList< Object > > camposBetween ) {
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
				Join< HoraExtraPO, FuncionarioPO > colaboradorJoin = root.join( "colaborador", JoinType.INNER );
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
	public List< SaldoHoraExtraPOJO > filtrarSaldo( Long idColaborador, LocalDate dataInicial, LocalDate dataFinal ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			StringBuffer SQL = new StringBuffer();
			
			
			SQL.append( " SELECT " );
			SQL.append( " lancamentos.idColaborador as colaborador,  " );
			SQL.append( " SUM(lancamentos.quantidadeHoras) as lancamento,  " );
			SQL.append( " SUM(lancamentos.quantidade50Porcento) as quantidade50Porcento,  " );
			SQL.append( " SUM(lancamentos.quantidade100Porcento) as quantidade100Porcento,  " );
			SQL.append( " SUM(lancamentos.quantidadeDomingoFeriado) as quantidadeDomingoFeriado, " );
			SQL.append( " lancamentos.tipo  " );
			SQL.append( "     FROM " );
			SQL.append( Constantes.SCHEMA + ".sistema_horasextras lancamentos " );
			SQL.append( "     WHERE 1=1 " );
			if ( idColaborador != null ) {
				SQL.append( "         AND lancamentos.idColaborador = :idColaboradorParam " );
			}
			SQL.append( "         AND lancamentos.tipo = 'Lançamento' " );
			if ( dataInicial != null && dataFinal != null ) {
				SQL.append( "         AND lancamentos.data between :dataInicialParam and :dataFinalParam " );
			}
			SQL.append( "     group by lancamentos.idColaborador " );
			
			
			
			/*SQL.append( " SELECT " );
			SQL.append( "     lancamentos.idColaborador as colaborador, " );
			SQL.append( "     lancamentos.lancamento as lancamento, " );
			SQL.append( "     IFNULL(pagamentos.pagamentos, 0) as pagamento, " );
			SQL.append( "     (lancamentos.lancamento-IFNULL(pagamentos.pagamentos, 0)) as saldo " );
			SQL.append( "  FROM " );
			SQL.append( "     (SELECT " );
			SQL.append( "         lancamentos.idColaborador, SUM(lancamentos.quantidadeHoras) as lancamento, lancamentos.tipo " );
			SQL.append( "     FROM " );
			SQL.append( Constantes.SCHEMA + ".sistema_horasextras lancamentos " );
			SQL.append( "     WHERE 1=1 " );
			if ( idColaborador != null ) {
				SQL.append( "         AND lancamentos.idColaborador = :idColaboradorParam " );
			}
			SQL.append( "         AND lancamentos.tipo = 'Lançamento' " );
			if ( dataInicial != null && dataFinal != null ) {
				SQL.append( "         AND lancamentos.data between :dataInicialParam and :dataFinalParam " );
			}
			SQL.append( "     group by lancamentos.idColaborador) as lancamentos " );
			SQL.append( " left Join " );
			SQL.append( "     (SELECT " );
			SQL.append( "         pagamentos.idColaborador, SUM(pagamentos.quantidadeHoras) as pagamentos, pagamentos.tipo " );
			SQL.append( "     FROM " );
			SQL.append( Constantes.SCHEMA + ".sistema_horasextras pagamentos " );
			SQL.append( "     WHERE 1=1" );
			if ( idColaborador != null ) {
				SQL.append( "         AND pagamentos.idColaborador = :idColaboradorParam " );
			}
			SQL.append( "         AND pagamentos.tipo = 'Pagamento' " );
			if ( dataInicial != null && dataFinal != null ) {
				SQL.append( "         AND pagamentos.data between :dataInicialParam and :dataFinalParam " );
			}
			SQL.append( "     group by pagamentos.idColaborador) as pagamentos on pagamentos.idColaborador = lancamentos.idColaborador " );
			*/
			
			Query query = hibernate.getSessaoCorrente().createNativeQuery( SQL.toString().substring( 0, SQL.toString().length() - 1 ) ).setResultTransformer( new ResultTransformer(){
				@Override
				public Object transformTuple( Object[ ] tuple, String[ ] aliases ) {
					SaldoHoraExtraPOJO pojo = new SaldoHoraExtraPOJO();

					if ( tuple[ 0 ] == null ) {
						return null;
					}

					pojo.setIdColaborador( tuple[ 0 ] == null ? null : tuple[ 0 ].toString() );
					pojo.setLancamento( tuple[ 1 ] == null ? null : ( (BigDecimal) tuple[ 1 ] ) );
					pojo.setQuantidade50Porcento( tuple[ 2 ] == null ? null : ( (BigDecimal) tuple[ 2 ] ) );
					pojo.setQuantidade100Porcento( tuple[ 3 ] == null ? null : ( (BigDecimal) tuple[ 3 ] ) );
					pojo.setQuantidadeDomingoFeriado( tuple[ 4 ] == null ? null : ( (BigDecimal) tuple[ 4 ] ) );
										
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

			List< SaldoHoraExtraPOJO > encontrados = query.getResultList();

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

	/*SELECT  
	lancamentos.idColaborador as colaborador, 
	lancamentos.lancamento as lancamento, 
	IFNULL(pagamentos.pagamentos, 0) as pagamento,
	(lancamentos.lancamento-IFNULL(pagamentos.pagamentos, 0)) as saldo 
	FROM 
	(SELECT 
	    lancamentos.idColaborador, SUM(lancamentos.quantidadeHoras) as lancamento, lancamentos.tipo
	FROM 
	    esafra.sistema_horasextras lancamentos
	WHERE
	    lancamentos.tipo = 'Lançamento'
	    AND lancamentos.data between '2020-12-02' and '2020-12-04'
	group by lancamentos.idColaborador) as lancamentos
	left Join 
	(SELECT 
	    pagamentos.idColaborador, SUM(pagamentos.quantidadeHoras) as pagamentos, pagamentos.tipo
	FROM 
	    esafra.sistema_horasextras pagamentos
	WHERE
	    pagamentos.tipo = 'Pagamento'
	     AND pagamentos.data between '2020-12-02' and '2020-12-04'
	group by pagamentos.idColaborador) as pagamentos on pagamentos.idColaborador = lancamentos.idColaborador*/

}
