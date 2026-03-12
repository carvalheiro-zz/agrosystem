package br.com.srcsoftware.sistema.pedido.notafiscal.venda.item;

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
import br.com.srcsoftware.sistema.pedido.notafiscal.venda.ItemNotaFiscalVendaPO;
import br.com.srcsoftware.sistema.produto.marca.MarcaPO;
import br.com.srcsoftware.sistema.produto.produto.ProdutoPO;
import br.com.srcsoftware.sistema.produto.tipo.TipoPO;
import br.com.srcsoftware.sistema.produto.unidademedida.UnidadeMedidaPO;

public final class ItemNotaFiscalVendaDAOImpl implements ItemNotaFiscalVendaDAOInterface{

	@Override
	@SuppressWarnings( "unchecked" )
	public ArrayList< ItemNotaFiscalVendaPO > filtrarPorDataNotaFiscalVendaBetween( LocalDate dataInicial, LocalDate dataFinal ) throws ApplicationException {

		HibernateConnection hibernate = new HibernateConnection();

		try {

			hibernate.iniciarTransacao();

			final StringBuffer HQL = new StringBuffer();
			HQL.append( " SELECT itemNotaFiscalVenda.* FROM " );
			HQL.append( Constantes.SCHEMA + ".itens_notas_fiscais_vendas itemNotaFiscalVenda, " );
			HQL.append( Constantes.SCHEMA + ".notas_fiscais_vendas notaFiscalVenda, " );
			HQL.append( " WHERE itemNotaFiscalVenda.idNotaEntrega = notaFiscalVenda.id " );
			HQL.append( " and notaFiscalVenda.data between :dataInicialParam and :dataFinalParam" );

			HashMap< String, Object > parametros = new HashMap<>();
			parametros.put( "dataInicialParam", dataInicial );
			parametros.put( "dataFinalParam", dataFinal );

			Query query = hibernate.getSessaoCorrente().createNativeQuery( HQL.toString() );

			for ( String key : parametros.keySet() ) {
				query.setParameter( key, parametros.get( key ) );
			}

			ArrayList< ItemNotaFiscalVendaPO > encontrados = (ArrayList< ItemNotaFiscalVendaPO >) query.getResultList();

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
	public List< ItemNotaFiscalVendaPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, ItemNotaFiscalVendaPO poFilter ) throws ApplicationException {

		HibernateConnection hibernate = new HibernateConnection();
		try {
			hibernate.iniciarTransacao();

			//  CRIANDO A CRITERIA COM BASE NA CRITERIABUILDER
			CriteriaBuilder builder = hibernate.getSessaoCorrente().getCriteriaBuilder();

			//  CRIANDO A CRITERIA DE CONSULTA COM BASE NA CRITERIABUILDER
			CriteriaQuery< ItemNotaFiscalVendaPO > criteria = builder.createQuery( ItemNotaFiscalVendaPO.class );

			// DEFININDO O OBJETO BASE DA SELECT
			Root< ItemNotaFiscalVendaPO > root = criteria.from( ItemNotaFiscalVendaPO.class );

			// Definindo os parametros do WHERE
			Predicate[ ] parametros = this.montarParametros( builder, root, poFilter );

			// Executando A Consulta
			List< ItemNotaFiscalVendaPO > encontrados = hibernate.filtrar( paginacao, camposOrders, builder, root, criteria, parametros, ItemNotaFiscalVendaPO.class );

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

	private Predicate[ ] montarParametros( CriteriaBuilder builder, Root< ItemNotaFiscalVendaPO > root, ItemNotaFiscalVendaPO poFilter ) {
		ArrayList< Predicate > lista = new ArrayList<>();

		if ( poFilter == null ) {
			return null;
		}

		if ( !Utilidades.isNuloOuVazio( poFilter.getId() ) ) {
			lista.add( builder.equal( root.get( "id" ), poFilter.getId() ) );
		} else {

			if ( !Utilidades.isNuloOuVazio( poFilter.getItemPedido() ) ) {
				Join< ItemNotaFiscalVendaPO, ItemPedidoPO > itemPedidoJoin = null;

				// MONTANDO JOIN PRODUTO
				if ( !Utilidades.isNuloOuVazio( poFilter.getItemPedido().getProduto() ) ) {

					Join< ItemNotaFiscalVendaPO, ProdutoPO > produtoJoin = null;

					if ( !Utilidades.isNuloOuVazio( poFilter.getItemPedido().getProduto().getId() ) ) {
						if ( itemPedidoJoin == null ) {
							itemPedidoJoin = root.join( "itemPedido", JoinType.INNER );
							itemPedidoJoin.alias( "itemPedido" );
						}

						if ( produtoJoin == null ) {
							produtoJoin = itemPedidoJoin.join( "produto", JoinType.INNER );
							produtoJoin.alias( "produto" );
						}

						lista.add( builder.equal( produtoJoin.get( "id" ), poFilter.getItemPedido().getProduto().getId() ) );
					} else {

						if ( !Utilidades.isNuloOuVazio( poFilter.getItemPedido().getProduto().getNome() ) ) {
							if ( itemPedidoJoin == null ) {
								itemPedidoJoin = root.join( "itemPedido", JoinType.INNER );
								itemPedidoJoin.alias( "itemPedido" );
							}

							if ( produtoJoin == null ) {
								produtoJoin = itemPedidoJoin.join( "produto", JoinType.INNER );
								produtoJoin.alias( "produto" );
							}

							lista.add( builder.like( produtoJoin.get( "nome" ), poFilter.getItemPedido().getProduto().getNome() + "%" ) );
						}

						if ( !Utilidades.isNuloOuVazio( poFilter.getItemPedido().getProduto().getLocalizacao() ) ) {
							if ( itemPedidoJoin == null ) {
								itemPedidoJoin = root.join( "itemPedido", JoinType.INNER );
								itemPedidoJoin.alias( "itemPedido" );
							}

							if ( produtoJoin == null ) {
								produtoJoin = itemPedidoJoin.join( "produto", JoinType.INNER );
								produtoJoin.alias( "produto" );
							}

							lista.add( builder.equal( produtoJoin.get( "localizacao" ), poFilter.getItemPedido().getProduto().getLocalizacao() ) );
						}

						// MONTANDO JOIN TIPO
						if ( !Utilidades.isNuloOuVazio( poFilter.getItemPedido().getProduto().getTipo() ) ) {

							Join< ProdutoPO, TipoPO > tipoJoin = null;

							if ( !Utilidades.isNuloOuVazio( poFilter.getItemPedido().getProduto().getTipo().getId() ) ) {
								if ( itemPedidoJoin == null ) {
									itemPedidoJoin = root.join( "itemPedido", JoinType.INNER );
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

								lista.add( builder.equal( tipoJoin.get( "id" ), poFilter.getItemPedido().getProduto().getTipo().getId() ) );
							} else {
								if ( !Utilidades.isNuloOuVazio( poFilter.getItemPedido().getProduto().getTipo().getNome() ) ) {

									if ( itemPedidoJoin == null ) {
										itemPedidoJoin = root.join( "itemPedido", JoinType.INNER );
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
									lista.add( builder.equal( tipoJoin.get( "nome" ), poFilter.getItemPedido().getProduto().getTipo().getNome() ) );
								}
							}
						}

						// MONTANDO JOIN MARCA
						if ( !Utilidades.isNuloOuVazio( poFilter.getItemPedido().getProduto().getMarca() ) ) {

							Join< ProdutoPO, MarcaPO > marcaJoin = null;

							if ( !Utilidades.isNuloOuVazio( poFilter.getItemPedido().getProduto().getMarca().getId() ) ) {
								if ( itemPedidoJoin == null ) {
									itemPedidoJoin = root.join( "itemPedido", JoinType.INNER );
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

								lista.add( builder.equal( marcaJoin.get( "id" ), poFilter.getItemPedido().getProduto().getMarca().getId() ) );
							} else {
								if ( !Utilidades.isNuloOuVazio( poFilter.getItemPedido().getProduto().getMarca().getNome() ) ) {

									if ( itemPedidoJoin == null ) {
										itemPedidoJoin = root.join( "itemPedido", JoinType.INNER );
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
									lista.add( builder.equal( marcaJoin.get( "nome" ), poFilter.getItemPedido().getProduto().getMarca().getNome() ) );
								}
							}
						}

						// MONTANDO JOIN TIPO
						if ( !Utilidades.isNuloOuVazio( poFilter.getItemPedido().getProduto().getUnidadeMedida() ) ) {

							Join< ProdutoPO, UnidadeMedidaPO > unidadeMedidaJoin = null;

							if ( !Utilidades.isNuloOuVazio( poFilter.getItemPedido().getProduto().getUnidadeMedida().getId() ) ) {

								if ( itemPedidoJoin == null ) {
									itemPedidoJoin = root.join( "itemPedido", JoinType.INNER );
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

								lista.add( builder.equal( unidadeMedidaJoin.get( "id" ), poFilter.getItemPedido().getProduto().getUnidadeMedida().getId() ) );
							} else {
								if ( !Utilidades.isNuloOuVazio( poFilter.getItemPedido().getProduto().getUnidadeMedida().getNome() ) ) {

									if ( itemPedidoJoin == null ) {
										itemPedidoJoin = root.join( "itemPedido", JoinType.INNER );
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
									lista.add( builder.equal( unidadeMedidaJoin.get( "nome" ), poFilter.getItemPedido().getProduto().getUnidadeMedida().getNome() ) );
								}

								if ( !Utilidades.isNuloOuVazio( poFilter.getItemPedido().getProduto().getUnidadeMedida().getSigla() ) ) {

									if ( itemPedidoJoin == null ) {
										itemPedidoJoin = root.join( "itemPedido", JoinType.INNER );
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
									lista.add( builder.equal( unidadeMedidaJoin.get( "sigla" ), poFilter.getItemPedido().getProduto().getUnidadeMedida().getSigla() ) );
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