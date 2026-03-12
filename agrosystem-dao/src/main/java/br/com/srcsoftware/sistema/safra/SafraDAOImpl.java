package br.com.srcsoftware.sistema.safra;

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
import br.com.srcsoftware.modular.manager.utilidades.Constantes;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.safra.cultura.CulturaPO;
import br.com.srcsoftware.sistema.safra.cultura.variedade.VariedadePO;
import br.com.srcsoftware.sistema.safra.setor.SetorPO;
import br.com.srcsoftware.sistema.safra.setorsafra.SetorSafraPO;
import br.com.srcsoftware.sistema.silo.entrada.InformacoesProducaoSafraPOJO;

public class SafraDAOImpl implements SafraDAOInterface{

	@Override
	public SafraPO inserir( HibernateConnection hibernate, SafraPO po ) throws ApplicationException {
		try {
			return (SafraPO) hibernate.inserir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void alterar( HibernateConnection hibernate, SafraPO po ) throws ApplicationException {
		try {
			hibernate.alterar( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void excluir( HibernateConnection hibernate, SafraPO po ) throws ApplicationException {
		try {
			hibernate.excluir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public SafraPO filtrarPorId( Long id ) throws ApplicationException {
		try {
			return (SafraPO) new HibernateConnection().filtrarPorId( SafraPO.class, id );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@SuppressWarnings( "unchecked" )
	@Override
	public List< SafraPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, SafraPO poFilter ) throws ApplicationException {

		HibernateConnection hibernate = new HibernateConnection();
		try {
			hibernate.iniciarTransacao();

			//  CRIANDO A CRITERIA COM BASE NA CRITERIABUILDER
			CriteriaBuilder builder = hibernate.getSessaoCorrente().getCriteriaBuilder();

			//  CRIANDO A CRITERIA DE CONSULTA COM BASE NA CRITERIABUILDER
			CriteriaQuery< SafraPO > criteria = builder.createQuery( SafraPO.class );

			// DEFININDO O OBJETO BASE DA SELECT
			Root< SafraPO > root = criteria.from( SafraPO.class );

			// Definindo os parametros do WHERE
			Predicate[ ] parametros = this.montarParametros( builder, root, poFilter );

			// Executando A Consulta
			List< SafraPO > encontrados = hibernate.filtrar( paginacao, camposOrders, builder, root, criteria, parametros, SafraPO.class );

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

	private Predicate[ ] montarParametros( CriteriaBuilder builder, Root< SafraPO > root, SafraPO poFilter ) {
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

			// MONTANDO JOIN SERVIÇO
			if ( !Utilidades.isNuloOuVazio( poFilter.getSetoresSafras() ) ) {
				Join< SafraPO, SetorSafraPO > itemJoinList = null;

				SetorSafraPO setorSafraFiltrar = poFilter.getSetoresSafras().iterator().next();

				if ( !Utilidades.isNuloOuVazio( setorSafraFiltrar.getId() ) ) {
					if ( itemJoinList == null ) {
						itemJoinList = root.joinSet( "setoresSafras", JoinType.LEFT );
						itemJoinList.alias( "setorSafra" );
					}
					lista.add( builder.equal( itemJoinList.get( "id" ), setorSafraFiltrar.getId() ) );
				} else {

					if ( !Utilidades.isNuloOuVazio( setorSafraFiltrar.getSetor() ) ) {

						Join< SetorSafraPO, SetorPO > setorJoin = null;

						if ( !Utilidades.isNuloOuVazio( setorSafraFiltrar.getSetor().getId() ) ) {
							if ( itemJoinList == null ) {
								itemJoinList = root.joinSet( "setoresSafras", JoinType.LEFT );
								itemJoinList.alias( "setorSafra" );
							}
							if ( setorJoin == null ) {
								setorJoin = itemJoinList.join( "setor", JoinType.INNER );
								setorJoin.alias( "setor" );
							}

							lista.add( builder.equal( setorJoin.get( "id" ), setorSafraFiltrar.getSetor().getId() ) );
						} else {
							if ( !Utilidades.isNuloOuVazio( setorSafraFiltrar.getSetor().getNome() ) ) {
								if ( itemJoinList == null ) {
									itemJoinList = root.joinSet( "setoresSafras", JoinType.LEFT );
									itemJoinList.alias( "setorSafra" );
								}
								if ( setorJoin == null ) {
									setorJoin = itemJoinList.join( "setor", JoinType.INNER );
									setorJoin.alias( "setor" );
								}
								lista.add( builder.like( setorJoin.get( "nome" ), setorSafraFiltrar.getSetor().getNome() + "%" ) );
							}
							if ( !Utilidades.isNuloOuVazio( setorSafraFiltrar.getSetor().getSubSetor() ) ) {
								if ( itemJoinList == null ) {
									itemJoinList = root.joinSet( "setoresSafras", JoinType.LEFT );
									itemJoinList.alias( "setorSafra" );
								}
								if ( setorJoin == null ) {
									setorJoin = itemJoinList.join( "setor", JoinType.INNER );
									setorJoin.alias( "setor" );
								}
								lista.add( builder.like( setorJoin.get( "subSetor" ), setorSafraFiltrar.getSetor().getSubSetor() + "%" ) );
							}
						}
					}

					if ( !Utilidades.isNuloOuVazio( setorSafraFiltrar.getVariedade() ) ) {

						Join< SetorSafraPO, VariedadePO > variedadeJoin = null;

						if ( !Utilidades.isNuloOuVazio( setorSafraFiltrar.getVariedade().getId() ) ) {
							if ( itemJoinList == null ) {
								itemJoinList = root.joinSet( "setoresSafras", JoinType.LEFT );
								itemJoinList.alias( "setorSafra" );
							}
							if ( variedadeJoin == null ) {
								variedadeJoin = itemJoinList.join( "variedade", JoinType.INNER );
								variedadeJoin.alias( "variedade" );
							}

							lista.add( builder.equal( variedadeJoin.get( "id" ), setorSafraFiltrar.getVariedade().getId() ) );
						} else {
							if ( !Utilidades.isNuloOuVazio( setorSafraFiltrar.getVariedade().getNome() ) ) {
								if ( itemJoinList == null ) {
									itemJoinList = root.joinSet( "setoresSafras", JoinType.LEFT );
									itemJoinList.alias( "setorSafra" );
								}
								if ( variedadeJoin == null ) {
									variedadeJoin = itemJoinList.join( "variedade", JoinType.INNER );
									variedadeJoin.alias( "variedade" );
								}
								lista.add( builder.like( variedadeJoin.get( "nome" ), setorSafraFiltrar.getVariedade().getNome() + "%" ) );
							} else if ( !Utilidades.isNuloOuVazio( setorSafraFiltrar.getVariedade().getCultura() ) ) {

								Join< VariedadePO, CulturaPO > culturaJoin = null;

								if ( !Utilidades.isNuloOuVazio( setorSafraFiltrar.getVariedade().getCultura().getId() ) ) {
									if ( itemJoinList == null ) {
										itemJoinList = root.joinSet( "setoresSafras", JoinType.LEFT );
										itemJoinList.alias( "setorSafra" );
									}
									if ( variedadeJoin == null ) {
										variedadeJoin = itemJoinList.join( "variedade", JoinType.INNER );
										variedadeJoin.alias( "variedade" );
									}
									if ( culturaJoin == null ) {
										culturaJoin = variedadeJoin.join( "cultura", JoinType.INNER );
										culturaJoin.alias( "cultura" );
									}

									lista.add( builder.equal( culturaJoin.get( "id" ), setorSafraFiltrar.getVariedade().getCultura().getId() ) );
								} else {
									if ( !Utilidades.isNuloOuVazio( setorSafraFiltrar.getVariedade().getCultura().getNome() ) ) {
										if ( itemJoinList == null ) {
											itemJoinList = root.joinSet( "setoresSafras", JoinType.LEFT );
											itemJoinList.alias( "setorSafra" );
										}
										if ( variedadeJoin == null ) {
											variedadeJoin = itemJoinList.join( "variedade", JoinType.INNER );
											variedadeJoin.alias( "variedade" );
										}
										if ( culturaJoin == null ) {
											culturaJoin = variedadeJoin.join( "cultura", JoinType.INNER );
											culturaJoin.alias( "cultura" );
										}
										lista.add( builder.like( culturaJoin.get( "nome" ), setorSafraFiltrar.getVariedade().getCultura().getNome() + "%" ) );
									}
								}

							}

						}
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

	@Override
	public LocalDate[ ] getDataInicioFimSafra( Long idSafra ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();
		try {

			hibernate.iniciarTransacao();

			final StringBuffer HQL = new StringBuffer();
			HQL.append( " SELECT min(aplicacao.data),max(aplicacao.data) FROM " );
			HQL.append( " AplicacaoPO aplicacao " );
			HQL.append( " WHERE 1=1" );
			HQL.append( " AND aplicacao.safra.id = :idSafraParam" );

			HashMap< String, Object > parametros = new HashMap<>();
			parametros.put( "idSafraParam", idSafra );

			Query query = hibernate.getSessaoCorrente().createQuery( HQL.toString() );

			for ( String key : parametros.keySet() ) {
				query.setParameter( key, parametros.get( key ) );
			}

			Object[ ] encontrados = (Object[ ]) query.getSingleResult();

			LocalDate[ ] datas = new LocalDate[ 2 ];
			datas[ 0 ] = (LocalDate) encontrados[ 0 ];
			datas[ 1 ] = (LocalDate) encontrados[ 1 ];

			hibernate.commitTransacao();

			return datas;

		} catch ( ApplicationException e ) {
			hibernate.rollbackTransacao();
			throw e;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();
			throw new ApplicationException( "Erro inesperado[999]" + System.lineSeparator() + e.getCause().getMessage().trim(), e );
		}
	}

	@SuppressWarnings( { "serial", "deprecation", "unchecked" } )
	@Override
	public InformacoesProducaoSafraPOJO filtrarSaldoProducaoSafra( StringBuffer SQL, HashMap< String, Object > parametros ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			//StringBuffer SQL = new StringBuffer();
			//SQL.append( " SELECT saldo.idSafra, saldo.safra, saldo.cultura, sum(saldo.quantidade) as quantidade FROM (" );
			/*SQL.append( " 	SELECT  " );
			SQL.append( " 			s.id as idSafra, s.nome as safra, c.nome as cultura ,sum(eg.pesoLiquido) as quantidade " );
			SQL.append( " 	FROM  " );
			SQL.append( Constantes.SCHEMA + ".sistema_entradasgrao eg, " );
			SQL.append( Constantes.SCHEMA + ".sistema_variedades v, " );
			SQL.append( Constantes.SCHEMA + ".sistema_culturas c, " );
			SQL.append( Constantes.SCHEMA + ".sistema_safras s " );
			SQL.append( " 	where 1=1 " );
			SQL.append( " 		and v.idCultura = :idCulturaParam " );
			SQL.append( " 		and s.id = :idSafraParam " );
			SQL.append( " 		and v.idCultura = c.id " );
			SQL.append( " 		and eg.idVariedade = v.id " );
			SQL.append( " 		and eg.idSafra = s.id " );
			SQL.append( " 	group by eg.idSafra " );*/

			/*SQL.append( " 	UNION ALL " );
			
			SQL.append( " 	SELECT " );
			SQL.append( " 		s.id as idSafra, s.nome as safra, c.nome as cultura ,sum(COALESCE(sg.pesoLiquidoSafra01,0)*-1) as quantidade " );
			SQL.append( " 	FROM  " );
			SQL.append( Constantes.SCHEMA + ".sistema_saidasgrao sg, " );
			SQL.append( Constantes.SCHEMA + ".sistema_culturas c, " );
			SQL.append( Constantes.SCHEMA + ".sistema_safras s " );
			SQL.append( " 	where 1=1 " );
			SQL.append( " 		and c.id = :idCulturaParam " );
			SQL.append( " 		and s.id = :idSafraParam " );
			SQL.append( " 		and sg.idCultura = c.id " );
			SQL.append( " 		and sg.idSafra01 = s.id " );
			SQL.append( " 	group by sg.idSafra01 " );
			
			SQL.append( " 	UNION ALL " );
			
			SQL.append( " 	SELECT " );
			SQL.append( " 		s.id as idSafra, s.nome as safra, c.nome as cultura ,sum(COALESCE(sg.pesoLiquidoSafra02,0)*-1) as quantidade " );
			SQL.append( " 	FROM  " );
			SQL.append( Constantes.SCHEMA + ".sistema_saidasgrao sg, " );
			SQL.append( Constantes.SCHEMA + ".sistema_culturas c, " );
			SQL.append( Constantes.SCHEMA + ".sistema_safras s " );
			SQL.append( " 	where 1=1 " );
			SQL.append( " 		and c.id = :idCulturaParam " );
			SQL.append( " 		and s.id = :idSafraParam " );
			SQL.append( " 		and sg.idCultura = c.id " );
			SQL.append( " 		and sg.idSafra02 = s.id " );
			SQL.append( " 	group by sg.idSafra02 " );*/
			//SQL.append( " ) as saldo" );
			//SQL.append( " ) as saldo  group by saldo.idSafra" );

			Query query = hibernate.getSessaoCorrente().createNativeQuery( SQL.toString() ).setResultTransformer( new ResultTransformer(){
				@Override
				public Object transformTuple( Object[ ] tuple, String[ ] aliases ) {
					InformacoesProducaoSafraPOJO pojo = new InformacoesProducaoSafraPOJO();

					if ( tuple[ 0 ] == null ) {
						return null;
					}

					pojo.setIdSafra( tuple[ 0 ] == null ? null : tuple[ 0 ].toString() );
					pojo.setNomeSafra( tuple[ 1 ] == null ? null : tuple[ 1 ].toString() );
					pojo.setCultura( tuple[ 2 ] == null ? null : tuple[ 2 ].toString() );
					pojo.setProducao( tuple[ 3 ] == null ? null : Utilidades.parseBigDecimal( ( (BigDecimal) tuple[ 3 ] ) ) );

					return pojo;
				}

				@SuppressWarnings( "rawtypes" )
				@Override
				public List transformList( List collection ) {
					return collection;
				}
			} );

			//query.setParameter( "idSafraParam", idSafra );
			//query.setParameter( "idCulturaParam", idCultura );
			for ( String key : parametros.keySet() ) {
				query.setParameter( key, parametros.get( key ) );
			}

			InformacoesProducaoSafraPOJO encontrado = (InformacoesProducaoSafraPOJO) query.getSingleResult();

			hibernate.commitTransacao();

			return encontrado;

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

	@Override
	@SuppressWarnings( { "serial", "deprecation", "unchecked" } )
	public List< InformacoesProducaoSafraPOJO > filtrarSaldoProducao( Long idSafra, Long idSetor, Long idCultura, Long idVariedade ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			StringBuffer SQL = new StringBuffer();
			SQL.append( " SELECT " );
			SQL.append( " 	safra.id as idSafra, " );
			SQL.append( " 	GROUP_CONCAT(DISTINCT safra.nome SEPARATOR ' | ') as safra, " );
			SQL.append( " 	GROUP_CONCAT(DISTINCT setor.nomeCompleto SEPARATOR ' | ') as setor, " );
			SQL.append( " 	GROUP_CONCAT(DISTINCT cultura.nome SEPARATOR ' | ') as cultura , " );
			SQL.append( " 	GROUP_CONCAT(DISTINCT variedade.nome SEPARATOR ' | ') as variedade, " );
			SQL.append( " 	sum(entradaGrao.pesoLiquido) as quantidade " );
			SQL.append( " FROM " );
			SQL.append( Constantes.SCHEMA + ".sistema_entradasgrao entradaGrao, " );
			SQL.append( Constantes.SCHEMA + ".sistema_variedades variedade, " );
			SQL.append( Constantes.SCHEMA + ".sistema_culturas cultura, " );
			SQL.append( Constantes.SCHEMA + ".sistema_safras safra, " );
			SQL.append( Constantes.SCHEMA + ".sistema_setores setor " );
			SQL.append( " where 1=1 " );

			if ( idSafra != null ) {
				SQL.append( "   and safra.id = :idSafraParam " );
			}
			if ( idSetor != null ) {
				SQL.append( "   and setor.id = :idSetorParam " );
			}
			if ( idCultura != null ) {
				SQL.append( "   and cultura.id = :idCulturaParam " );
			}
			if ( idVariedade != null ) {
				SQL.append( "   and variedade.id = :idVariedadeParam " );
			}
			SQL.append( " 	and variedade.idCultura = cultura.id " );
			SQL.append( " 	and entradaGrao.idVariedade = variedade.id " );
			SQL.append( " 	and entradaGrao.idSafra = safra.id " );
			SQL.append( "   and entradaGrao.idSetor = setor.id " );
			SQL.append( " group by " );
			/*if ( idSafra != null ) {
				SQL.append( " 	safra.id," );
			}
			if ( idSetor != null ) {
				SQL.append( " 	setor.id," );
			}
			if ( idCultura != null ) {
				SQL.append( " 	cultura.id," );
			}
			if ( idVariedade != null ) {
				SQL.append( " 	variedade.id," );
			}
			if ( idSafra == null && idSetor == null && idCultura == null && idVariedade == null ) {
				SQL.append( " 	safra.id, setor.id, cultura.id, variedade.id " );
			}*/
			SQL.append( " 	safra.id, setor.id, cultura.id, variedade.id " );

			Query query = hibernate.getSessaoCorrente().createNativeQuery( SQL.toString().substring( 0, SQL.toString().length() - 1 ) ).setResultTransformer( new ResultTransformer(){
				@Override
				public Object transformTuple( Object[ ] tuple, String[ ] aliases ) {
					InformacoesProducaoSafraPOJO pojo = new InformacoesProducaoSafraPOJO();

					if ( tuple[ 0 ] == null ) {
						return null;
					}

					pojo.setIdSafra( tuple[ 0 ] == null ? null : tuple[ 0 ].toString() );
					pojo.setNomeSafra( tuple[ 1 ] == null ? null : tuple[ 1 ].toString() );
					pojo.setNomeSetor( tuple[ 2 ] == null ? null : tuple[ 2 ].toString() );
					pojo.setCultura( tuple[ 3 ] == null ? null : tuple[ 3 ].toString() );
					pojo.setVariedade( tuple[ 4 ] == null ? null : tuple[ 4 ].toString() );
					pojo.setProducao( tuple[ 5 ] == null ? null : Utilidades.parseBigDecimal( ( (BigDecimal) tuple[ 5 ] ) ) );

					return pojo;
				}

				@SuppressWarnings( "rawtypes" )
				@Override
				public List transformList( List collection ) {
					return collection;
				}
			} );

			if ( idSafra != null ) {
				query.setParameter( "idSafraParam", idSafra );
			}
			if ( idSetor != null ) {
				query.setParameter( "idSetorParam", idSetor );
			}
			if ( idCultura != null ) {
				query.setParameter( "idCulturaParam", idCultura );
			}
			if ( idVariedade != null ) {
				query.setParameter( "idVariedadeParam", idVariedade );
			}

			List< InformacoesProducaoSafraPOJO > encontrados = query.getResultList();

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
