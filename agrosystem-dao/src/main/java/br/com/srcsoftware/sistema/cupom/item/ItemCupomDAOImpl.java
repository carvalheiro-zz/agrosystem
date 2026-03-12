package br.com.srcsoftware.sistema.cupom.item;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import br.com.srcsoftware.modular.manager.utilidades.Constantes;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.produto.marca.MarcaPO;
import br.com.srcsoftware.sistema.produto.produto.ProdutoPO;
import br.com.srcsoftware.sistema.produto.tipo.TipoPO;
import br.com.srcsoftware.sistema.produto.unidademedida.UnidadeMedidaPO;

public final class ItemCupomDAOImpl implements ItemCupomDAOInterface{

	@Override
	@SuppressWarnings( "unchecked" )
	public ArrayList< ItemCupomPO > filtrarPorDataCupomBetween( LocalDate dataInicial, LocalDate dataFinal ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {

			hibernate.iniciarTransacao();

			final StringBuffer HQL = new StringBuffer();
			HQL.append( " SELECT itemCupom.* FROM " );
			HQL.append( Constantes.SCHEMA + ".itens_cupons itemCupom, " );
			HQL.append( Constantes.SCHEMA + ".cupons cupom, " );
			HQL.append( " WHERE itemCupom.idCupom = cupom.id " );
			HQL.append( " and itemCupom.data between :dataInicialParam and :dataFinalParam" );

			HashMap< String, Object > parametros = new HashMap< String, Object >();
			parametros.put( "dataInicialParam", dataInicial );
			parametros.put( "dataFinalParam", dataFinal );

			Query query = hibernate.getSessaoCorrente().createNativeQuery( HQL.toString() );

			for ( String key : parametros.keySet() ) {
				query.setParameter( key, parametros.get( key ) );
			}

			ArrayList< ItemCupomPO > encontrados = (ArrayList< ItemCupomPO >) query.getResultList();

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

	@SuppressWarnings( "unchecked" )
	@Override
	public List< ItemCupomPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, HashMap< String, ArrayList< Object > > camposBetween, ItemCupomPO poFilter ) throws ApplicationException {

		HibernateConnection hibernate = new HibernateConnection();
		try {
			hibernate.iniciarTransacao();

			//  CRIANDO A CRITERIA COM BASE NA CRITERIABUILDER
			CriteriaBuilder builder = hibernate.getSessaoCorrente().getCriteriaBuilder();

			//  CRIANDO A CRITERIA DE CONSULTA COM BASE NA CRITERIABUILDER
			CriteriaQuery< ItemCupomPO > criteria = builder.createQuery( ItemCupomPO.class );

			// DEFININDO O OBJETO BASE DA SELECT
			Root< ItemCupomPO > root = criteria.from( ItemCupomPO.class );

			// Definindo os parametros do WHERE
			Predicate[ ] parametros = this.montarParametros( builder, root, poFilter, camposBetween );

			// Executando A Consulta
			List< ItemCupomPO > encontrados = hibernate.filtrar( paginacao, camposOrders, builder, root, criteria, parametros, ItemCupomPO.class );

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

	private Predicate[ ] montarParametros( CriteriaBuilder builder, Root< ItemCupomPO > root, ItemCupomPO poFilter, HashMap< String, ArrayList< Object > > camposBetween ) {
		ArrayList< Predicate > lista = new ArrayList<>();

		if ( poFilter == null ) {
			return null;
		}

		lista.addAll( HibernateConnection.montarBetween( root, builder, camposBetween ) );

		if ( !Utilidades.isNuloOuVazio( poFilter.getId() ) ) {
			lista.add( builder.equal( root.get( "id" ), poFilter.getId() ) );
		} else {

			// MONTANDO JOIN PRODUTO
			if ( !Utilidades.isNuloOuVazio( poFilter.getProduto() ) ) {

				Join< ItemCupomPO, ProdutoPO > produtoJoin = null;

				if ( !Utilidades.isNuloOuVazio( poFilter.getProduto().getId() ) ) {

					if ( produtoJoin == null ) {
						produtoJoin = root.join( "produto", JoinType.INNER );
						produtoJoin.alias( "produto" );
					}

					lista.add( builder.equal( produtoJoin.get( "id" ), poFilter.getProduto().getId() ) );
				} else {

					if ( !Utilidades.isNuloOuVazio( poFilter.getProduto().getNome() ) ) {

						if ( produtoJoin == null ) {
							produtoJoin = root.join( "produto", JoinType.INNER );
							produtoJoin.alias( "produto" );
						}

						lista.add( builder.like( produtoJoin.get( "nome" ), poFilter.getProduto().getNome() + "%" ) );
					}

					if ( !Utilidades.isNuloOuVazio( poFilter.getProduto().getLocalizacao() ) ) {

						if ( produtoJoin == null ) {
							produtoJoin = root.join( "produto", JoinType.INNER );
							produtoJoin.alias( "produto" );
						}

						lista.add( builder.equal( produtoJoin.get( "localizacao" ), poFilter.getProduto().getLocalizacao() ) );
					}

					// MONTANDO JOIN TIPO
					if ( !Utilidades.isNuloOuVazio( poFilter.getProduto().getTipo() ) ) {

						Join< ProdutoPO, TipoPO > tipoJoin = null;

						if ( !Utilidades.isNuloOuVazio( poFilter.getProduto().getTipo().getId() ) ) {

							if ( produtoJoin == null ) {
								produtoJoin = root.join( "produto", JoinType.INNER );
								produtoJoin.alias( "produto" );
							}

							if ( tipoJoin == null ) {
								tipoJoin = produtoJoin.join( "tipo", JoinType.INNER );
								tipoJoin.alias( "tipo" );
							}

							lista.add( builder.equal( tipoJoin.get( "id" ), poFilter.getProduto().getTipo().getId() ) );
						} else {
							if ( !Utilidades.isNuloOuVazio( poFilter.getProduto().getTipo().getNome() ) ) {

								if ( produtoJoin == null ) {
									produtoJoin = root.join( "produto", JoinType.INNER );
									produtoJoin.alias( "produto" );
								}

								if ( tipoJoin == null ) {
									tipoJoin = produtoJoin.join( "tipo", JoinType.INNER );
									tipoJoin.alias( "tipo" );
								}
								lista.add( builder.equal( tipoJoin.get( "nome" ), poFilter.getProduto().getTipo().getNome() ) );
							}
						}
					}

					// MONTANDO JOIN MARCA
					if ( !Utilidades.isNuloOuVazio( poFilter.getProduto().getMarca() ) ) {

						Join< ProdutoPO, MarcaPO > marcaJoin = null;

						if ( !Utilidades.isNuloOuVazio( poFilter.getProduto().getMarca().getId() ) ) {

							if ( produtoJoin == null ) {
								produtoJoin = root.join( "produto", JoinType.INNER );
								produtoJoin.alias( "produto" );
							}

							if ( marcaJoin == null ) {
								marcaJoin = produtoJoin.join( "marca", JoinType.INNER );
								marcaJoin.alias( "marca" );
							}

							lista.add( builder.equal( marcaJoin.get( "id" ), poFilter.getProduto().getMarca().getId() ) );
						} else {
							if ( !Utilidades.isNuloOuVazio( poFilter.getProduto().getMarca().getNome() ) ) {

								if ( produtoJoin == null ) {
									produtoJoin = root.join( "produto", JoinType.INNER );
									produtoJoin.alias( "produto" );
								}

								if ( marcaJoin == null ) {
									marcaJoin = produtoJoin.join( "marca", JoinType.INNER );
									marcaJoin.alias( "marca" );
								}
								lista.add( builder.equal( marcaJoin.get( "nome" ), poFilter.getProduto().getMarca().getNome() ) );
							}
						}
					}

					// MONTANDO JOIN UNIDADE MEDIDA
					if ( !Utilidades.isNuloOuVazio( poFilter.getProduto().getUnidadeMedida() ) ) {

						Join< ProdutoPO, UnidadeMedidaPO > unidadeMedidaJoin = null;

						if ( !Utilidades.isNuloOuVazio( poFilter.getProduto().getUnidadeMedida().getId() ) ) {

							if ( produtoJoin == null ) {
								produtoJoin = root.join( "produto", JoinType.INNER );
								produtoJoin.alias( "produto" );
							}

							if ( unidadeMedidaJoin == null ) {
								unidadeMedidaJoin = produtoJoin.join( "unidadeMedida", JoinType.INNER );
								unidadeMedidaJoin.alias( "unidadeMedida" );
							}

							lista.add( builder.equal( unidadeMedidaJoin.get( "id" ), poFilter.getProduto().getUnidadeMedida().getId() ) );
						} else {
							if ( !Utilidades.isNuloOuVazio( poFilter.getProduto().getUnidadeMedida().getNome() ) ) {

								if ( produtoJoin == null ) {
									produtoJoin = root.join( "produto", JoinType.INNER );
									produtoJoin.alias( "produto" );
								}

								if ( unidadeMedidaJoin == null ) {
									unidadeMedidaJoin = produtoJoin.join( "unidadeMedida", JoinType.INNER );
									unidadeMedidaJoin.alias( "unidadeMedida" );
								}
								lista.add( builder.equal( unidadeMedidaJoin.get( "nome" ), poFilter.getProduto().getUnidadeMedida().getNome() ) );
							}

							if ( !Utilidades.isNuloOuVazio( poFilter.getProduto().getUnidadeMedida().getSigla() ) ) {

								if ( produtoJoin == null ) {
									produtoJoin = root.join( "produto", JoinType.INNER );
									produtoJoin.alias( "produto" );
								}

								if ( unidadeMedidaJoin == null ) {
									unidadeMedidaJoin = produtoJoin.join( "unidadeMedida", JoinType.INNER );
									unidadeMedidaJoin.alias( "unidadeMedida" );
								}
								lista.add( builder.equal( unidadeMedidaJoin.get( "sigla" ), poFilter.getProduto().getUnidadeMedida().getSigla() ) );
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
}