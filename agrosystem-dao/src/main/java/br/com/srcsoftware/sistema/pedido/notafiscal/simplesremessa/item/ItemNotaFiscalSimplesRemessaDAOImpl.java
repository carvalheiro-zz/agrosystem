package br.com.srcsoftware.sistema.pedido.notafiscal.simplesremessa.item;

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
import br.com.srcsoftware.sistema.pedido.itempedido.ItemPedidoPO;
import br.com.srcsoftware.sistema.pedido.notafiscal.simplesremessa.ItemNotaFiscalSimplesRemessaPO;
import br.com.srcsoftware.sistema.pedido.notafiscal.venda.ItemNotaFiscalVendaPO;
import br.com.srcsoftware.sistema.pedido.pedido.PedidoPO;
import br.com.srcsoftware.sistema.produto.marca.MarcaPO;
import br.com.srcsoftware.sistema.produto.produto.ProdutoPO;
import br.com.srcsoftware.sistema.produto.tipo.TipoPO;
import br.com.srcsoftware.sistema.produto.unidademedida.UnidadeMedidaPO;

public final class ItemNotaFiscalSimplesRemessaDAOImpl implements ItemNotaFiscalSimplesRemessaDAOInterface{

	@Override
	@SuppressWarnings( "unchecked" )
	public ArrayList< ItemNotaFiscalSimplesRemessaPO > filtrarPorDataNotaFiscalSimplesRemessaBetween( LocalDate dataInicial, LocalDate dataFinal ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {

			hibernate.iniciarTransacao();

			final StringBuffer HQL = new StringBuffer();
			HQL.append( " SELECT itemNotaFiscalSimplesRemessa.* FROM " );
			HQL.append( Constantes.SCHEMA + ".itens_simples_remessas itemNotaFiscalSimplesRemessa, " );
			HQL.append( Constantes.SCHEMA + ".notas_simples_remessa notaFiscalSimplesRemessa, " );
			HQL.append( " WHERE itemNotaFiscalSimplesRemessa.idNotaSimplesRemessa = notaFiscalSimplesRemessa.id " );
			HQL.append( " and notaFiscalSimplesRemessa.data between :dataInicialParam and :dataFinalParam" );

			HashMap< String, Object > parametros = new HashMap<>();
			parametros.put( "dataInicialParam", dataInicial );
			parametros.put( "dataFinalParam", dataFinal );

			Query query = hibernate.getSessaoCorrente().createNativeQuery( HQL.toString() );

			for ( String key : parametros.keySet() ) {
				query.setParameter( key, parametros.get( key ) );
			}

			ArrayList< ItemNotaFiscalSimplesRemessaPO > encontrados = (ArrayList< ItemNotaFiscalSimplesRemessaPO >) query.getResultList();

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
	public List< ItemNotaFiscalSimplesRemessaPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, ItemNotaFiscalSimplesRemessaPO poFilter ) throws ApplicationException {

		HibernateConnection hibernate = new HibernateConnection();
		try {
			hibernate.iniciarTransacao();

			//  CRIANDO A CRITERIA COM BASE NA CRITERIABUILDER
			CriteriaBuilder builder = hibernate.getSessaoCorrente().getCriteriaBuilder();

			//  CRIANDO A CRITERIA DE CONSULTA COM BASE NA CRITERIABUILDER
			CriteriaQuery< ItemNotaFiscalSimplesRemessaPO > criteria = builder.createQuery( ItemNotaFiscalSimplesRemessaPO.class );

			// DEFININDO O OBJETO BASE DA SELECT
			Root< ItemNotaFiscalSimplesRemessaPO > root = criteria.from( ItemNotaFiscalSimplesRemessaPO.class );

			// Definindo os parametros do WHERE
			Predicate[ ] parametros = this.montarParametros( builder, root, poFilter );

			// Executando A Consulta
			List< ItemNotaFiscalSimplesRemessaPO > encontrados = hibernate.filtrar( paginacao, camposOrders, builder, root, criteria, parametros, ItemNotaFiscalSimplesRemessaPO.class );

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

	private Predicate[ ] montarParametros( CriteriaBuilder builder, Root< ItemNotaFiscalSimplesRemessaPO > root, ItemNotaFiscalSimplesRemessaPO poFilter ) {
		ArrayList< Predicate > lista = new ArrayList<>();

		if ( poFilter == null ) {
			return null;
		}

		if ( !Utilidades.isNuloOuVazio( poFilter.getId() ) ) {
			lista.add( builder.equal( root.get( "id" ), poFilter.getId() ) );
		} else {

			if ( !Utilidades.isNuloOuVazio( poFilter.getItemNotaFiscalVendaFutura() ) ) {
				Join< ItemNotaFiscalSimplesRemessaPO, ItemNotaFiscalVendaPO > itemNotaFiscalVendaFuturaJoin = null;

				if ( !Utilidades.isNuloOuVazio( poFilter.getItemNotaFiscalVendaFutura().getItemPedido() ) ) {
					Join< ItemNotaFiscalVendaPO, PedidoPO > itemPedidoJoin = null;

					if ( !Utilidades.isNuloOuVazio( poFilter.getItemNotaFiscalVendaFutura().getItemPedido().getId() ) ) {

						if ( itemNotaFiscalVendaFuturaJoin == null ) {
							itemNotaFiscalVendaFuturaJoin = root.join( "itemNotaFiscalVendaFutura", JoinType.INNER );
							itemNotaFiscalVendaFuturaJoin.alias( "itemNotaFiscalVendaFutura" );
						}

						if ( itemPedidoJoin == null ) {
							itemPedidoJoin = itemNotaFiscalVendaFuturaJoin.join( "itemPedido", JoinType.INNER );
							itemPedidoJoin.alias( "itemPedido" );
						}

						lista.add( builder.equal( itemPedidoJoin.get( "id" ), poFilter.getItemNotaFiscalVendaFutura().getItemPedido().getId() ) );
					} else {
						// MONTANDO JOIN PRODUTO
						if ( !Utilidades.isNuloOuVazio( poFilter.getItemNotaFiscalVendaFutura().getItemPedido().getProduto() ) ) {

							Join< ItemPedidoPO, ProdutoPO > produtoJoin = null;

							if ( !Utilidades.isNuloOuVazio( poFilter.getItemNotaFiscalVendaFutura().getItemPedido().getProduto().getId() ) ) {
								if ( itemNotaFiscalVendaFuturaJoin == null ) {
									itemNotaFiscalVendaFuturaJoin = root.join( "itemNotaFiscalVendaFutura", JoinType.INNER );
									itemNotaFiscalVendaFuturaJoin.alias( "itemNotaFiscalVendaFutura" );
								}

								if ( itemPedidoJoin == null ) {
									itemPedidoJoin = itemNotaFiscalVendaFuturaJoin.join( "itemPedido", JoinType.INNER );
									itemPedidoJoin.alias( "itemPedido" );
								}

								if ( produtoJoin == null ) {
									produtoJoin = itemPedidoJoin.join( "produto", JoinType.INNER );
									produtoJoin.alias( "produto" );
								}

								lista.add( builder.equal( produtoJoin.get( "id" ), poFilter.getItemNotaFiscalVendaFutura().getItemPedido().getProduto().getId() ) );
							} else {

								if ( !Utilidades.isNuloOuVazio( poFilter.getItemNotaFiscalVendaFutura().getItemPedido().getProduto().getNome() ) ) {
									if ( itemNotaFiscalVendaFuturaJoin == null ) {
										itemNotaFiscalVendaFuturaJoin = root.join( "itemNotaFiscalVendaFutura", JoinType.INNER );
										itemNotaFiscalVendaFuturaJoin.alias( "itemNotaFiscalVendaFutura" );
									}

									if ( itemPedidoJoin == null ) {
										itemPedidoJoin = itemNotaFiscalVendaFuturaJoin.join( "itemPedido", JoinType.INNER );
										itemPedidoJoin.alias( "itemPedido" );
									}

									if ( produtoJoin == null ) {
										produtoJoin = itemPedidoJoin.join( "produto", JoinType.INNER );
										produtoJoin.alias( "produto" );
									}

									lista.add( builder.like( produtoJoin.get( "nome" ), poFilter.getItemNotaFiscalVendaFutura().getItemPedido().getProduto().getNome() + "%" ) );
								}

								if ( !Utilidades.isNuloOuVazio( poFilter.getItemNotaFiscalVendaFutura().getItemPedido().getProduto().getLocalizacao() ) ) {
									if ( itemNotaFiscalVendaFuturaJoin == null ) {
										itemNotaFiscalVendaFuturaJoin = root.join( "itemNotaFiscalVendaFutura", JoinType.INNER );
										itemNotaFiscalVendaFuturaJoin.alias( "itemNotaFiscalVendaFutura" );
									}

									if ( itemPedidoJoin == null ) {
										itemPedidoJoin = itemNotaFiscalVendaFuturaJoin.join( "itemPedido", JoinType.INNER );
										itemPedidoJoin.alias( "itemPedido" );
									}

									if ( produtoJoin == null ) {
										produtoJoin = itemPedidoJoin.join( "produto", JoinType.INNER );
										produtoJoin.alias( "produto" );
									}

									lista.add( builder.equal( produtoJoin.get( "localizacao" ), poFilter.getItemNotaFiscalVendaFutura().getItemPedido().getProduto().getLocalizacao() ) );
								}

								// MONTANDO JOIN TIPO
								if ( !Utilidades.isNuloOuVazio( poFilter.getItemNotaFiscalVendaFutura().getItemPedido().getProduto().getTipo() ) ) {

									Join< ProdutoPO, TipoPO > tipoJoin = null;

									if ( !Utilidades.isNuloOuVazio( poFilter.getItemNotaFiscalVendaFutura().getItemPedido().getProduto().getTipo().getId() ) ) {
										if ( itemNotaFiscalVendaFuturaJoin == null ) {
											itemNotaFiscalVendaFuturaJoin = root.join( "itemNotaFiscalVendaFutura", JoinType.INNER );
											itemNotaFiscalVendaFuturaJoin.alias( "itemNotaFiscalVendaFutura" );
										}

										if ( itemPedidoJoin == null ) {
											itemPedidoJoin = itemNotaFiscalVendaFuturaJoin.join( "itemPedido", JoinType.INNER );
											itemPedidoJoin.alias( "itemPedido" );
										}

										if ( produtoJoin == null ) {
											produtoJoin = itemPedidoJoin.join( "produto", JoinType.INNER );
											produtoJoin.alias( "produto" );
										}

										if ( tipoJoin == null ) {
											tipoJoin = produtoJoin.join( "tipo", JoinType.INNER );
											tipoJoin.alias( "tipo" );
										}

										lista.add( builder.equal( tipoJoin.get( "id" ), poFilter.getItemNotaFiscalVendaFutura().getItemPedido().getProduto().getTipo().getId() ) );
									} else {
										if ( !Utilidades.isNuloOuVazio( poFilter.getItemNotaFiscalVendaFutura().getItemPedido().getProduto().getTipo().getNome() ) ) {

											if ( itemNotaFiscalVendaFuturaJoin == null ) {
												itemNotaFiscalVendaFuturaJoin = root.join( "itemNotaFiscalVendaFutura", JoinType.INNER );
												itemNotaFiscalVendaFuturaJoin.alias( "itemNotaFiscalVendaFutura" );
											}

											if ( itemPedidoJoin == null ) {
												itemPedidoJoin = itemNotaFiscalVendaFuturaJoin.join( "itemPedido", JoinType.INNER );
												itemPedidoJoin.alias( "itemPedido" );
											}

											if ( produtoJoin == null ) {
												produtoJoin = itemPedidoJoin.join( "produto", JoinType.INNER );
												produtoJoin.alias( "produto" );
											}

											if ( tipoJoin == null ) {
												tipoJoin = produtoJoin.join( "tipo", JoinType.INNER );
												tipoJoin.alias( "tipo" );
											}
											lista.add( builder.equal( tipoJoin.get( "nome" ), poFilter.getItemNotaFiscalVendaFutura().getItemPedido().getProduto().getTipo().getNome() ) );
										}
									}
								}

								// MONTANDO JOIN MARCA
								if ( !Utilidades.isNuloOuVazio( poFilter.getItemNotaFiscalVendaFutura().getItemPedido().getProduto().getMarca() ) ) {

									Join< ProdutoPO, MarcaPO > marcaJoin = null;

									if ( !Utilidades.isNuloOuVazio( poFilter.getItemNotaFiscalVendaFutura().getItemPedido().getProduto().getMarca().getId() ) ) {
										if ( itemNotaFiscalVendaFuturaJoin == null ) {
											itemNotaFiscalVendaFuturaJoin = root.join( "itemNotaFiscalVendaFutura", JoinType.INNER );
											itemNotaFiscalVendaFuturaJoin.alias( "itemNotaFiscalVendaFutura" );
										}

										if ( itemPedidoJoin == null ) {
											itemPedidoJoin = itemNotaFiscalVendaFuturaJoin.join( "itemPedido", JoinType.INNER );
											itemPedidoJoin.alias( "itemPedido" );
										}

										if ( produtoJoin == null ) {
											produtoJoin = itemPedidoJoin.join( "produto", JoinType.INNER );
											produtoJoin.alias( "produto" );
										}

										if ( marcaJoin == null ) {
											marcaJoin = produtoJoin.join( "marca", JoinType.INNER );
											marcaJoin.alias( "marca" );
										}

										lista.add( builder.equal( marcaJoin.get( "id" ), poFilter.getItemNotaFiscalVendaFutura().getItemPedido().getProduto().getMarca().getId() ) );
									} else {
										if ( !Utilidades.isNuloOuVazio( poFilter.getItemNotaFiscalVendaFutura().getItemPedido().getProduto().getMarca().getNome() ) ) {

											if ( itemNotaFiscalVendaFuturaJoin == null ) {
												itemNotaFiscalVendaFuturaJoin = root.join( "itemNotaFiscalVendaFutura", JoinType.INNER );
												itemNotaFiscalVendaFuturaJoin.alias( "itemNotaFiscalVendaFutura" );
											}

											if ( itemPedidoJoin == null ) {
												itemPedidoJoin = itemNotaFiscalVendaFuturaJoin.join( "itemPedido", JoinType.INNER );
												itemPedidoJoin.alias( "itemPedido" );
											}

											if ( produtoJoin == null ) {
												produtoJoin = itemPedidoJoin.join( "produto", JoinType.INNER );
												produtoJoin.alias( "produto" );
											}

											if ( marcaJoin == null ) {
												marcaJoin = produtoJoin.join( "marca", JoinType.INNER );
												marcaJoin.alias( "marca" );
											}
											lista.add( builder.equal( marcaJoin.get( "nome" ), poFilter.getItemNotaFiscalVendaFutura().getItemPedido().getProduto().getMarca().getNome() ) );
										}
									}
								}

								// MONTANDO JOIN TIPO
								if ( !Utilidades.isNuloOuVazio( poFilter.getItemNotaFiscalVendaFutura().getItemPedido().getProduto().getUnidadeMedida() ) ) {

									Join< ProdutoPO, UnidadeMedidaPO > unidadeMedidaJoin = null;

									if ( !Utilidades.isNuloOuVazio( poFilter.getItemNotaFiscalVendaFutura().getItemPedido().getProduto().getUnidadeMedida().getId() ) ) {

										if ( itemNotaFiscalVendaFuturaJoin == null ) {
											itemNotaFiscalVendaFuturaJoin = root.join( "itemNotaFiscalVendaFutura", JoinType.INNER );
											itemNotaFiscalVendaFuturaJoin.alias( "itemNotaFiscalVendaFutura" );
										}

										if ( itemPedidoJoin == null ) {
											itemPedidoJoin = itemNotaFiscalVendaFuturaJoin.join( "itemPedido", JoinType.INNER );
											itemPedidoJoin.alias( "itemPedido" );
										}

										if ( produtoJoin == null ) {
											produtoJoin = itemPedidoJoin.join( "produto", JoinType.INNER );
											produtoJoin.alias( "produto" );
										}

										if ( unidadeMedidaJoin == null ) {
											unidadeMedidaJoin = produtoJoin.join( "unidadeMedida", JoinType.INNER );
											unidadeMedidaJoin.alias( "unidadeMedida" );
										}

										lista.add( builder.equal( unidadeMedidaJoin.get( "id" ), poFilter.getItemNotaFiscalVendaFutura().getItemPedido().getProduto().getUnidadeMedida().getId() ) );
									} else {
										if ( !Utilidades.isNuloOuVazio( poFilter.getItemNotaFiscalVendaFutura().getItemPedido().getProduto().getUnidadeMedida().getNome() ) ) {

											if ( itemNotaFiscalVendaFuturaJoin == null ) {
												itemNotaFiscalVendaFuturaJoin = root.join( "itemNotaFiscalVendaFutura", JoinType.INNER );
												itemNotaFiscalVendaFuturaJoin.alias( "itemNotaFiscalVendaFutura" );
											}

											if ( itemPedidoJoin == null ) {
												itemPedidoJoin = itemNotaFiscalVendaFuturaJoin.join( "itemPedido", JoinType.INNER );
												itemPedidoJoin.alias( "itemPedido" );
											}

											if ( produtoJoin == null ) {
												produtoJoin = itemPedidoJoin.join( "produto", JoinType.INNER );
												produtoJoin.alias( "produto" );
											}

											if ( unidadeMedidaJoin == null ) {
												unidadeMedidaJoin = produtoJoin.join( "unidadeMedida", JoinType.INNER );
												unidadeMedidaJoin.alias( "unidadeMedida" );
											}
											lista.add( builder.equal( unidadeMedidaJoin.get( "nome" ), poFilter.getItemNotaFiscalVendaFutura().getItemPedido().getProduto().getUnidadeMedida().getNome() ) );
										}

										if ( !Utilidades.isNuloOuVazio( poFilter.getItemNotaFiscalVendaFutura().getItemPedido().getProduto().getUnidadeMedida().getSigla() ) ) {

											if ( itemNotaFiscalVendaFuturaJoin == null ) {
												itemNotaFiscalVendaFuturaJoin = root.join( "itemNotaFiscalVendaFutura", JoinType.INNER );
												itemNotaFiscalVendaFuturaJoin.alias( "itemNotaFiscalVendaFutura" );
											}

											if ( itemPedidoJoin == null ) {
												itemPedidoJoin = itemNotaFiscalVendaFuturaJoin.join( "itemPedido", JoinType.INNER );
												itemPedidoJoin.alias( "itemPedido" );
											}

											if ( produtoJoin == null ) {
												produtoJoin = itemPedidoJoin.join( "produto", JoinType.INNER );
												produtoJoin.alias( "produto" );
											}

											if ( unidadeMedidaJoin == null ) {
												unidadeMedidaJoin = produtoJoin.join( "unidadeMedida", JoinType.INNER );
												unidadeMedidaJoin.alias( "unidadeMedida" );
											}
											lista.add( builder.equal( unidadeMedidaJoin.get( "sigla" ), poFilter.getItemNotaFiscalVendaFutura().getItemPedido().getProduto().getUnidadeMedida().getSigla() ) );
										}
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
}