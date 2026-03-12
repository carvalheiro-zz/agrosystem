package br.com.srcsoftware.sistema.pedido.notafiscal.simplesremessa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import br.com.srcsoftware.sistema.pedido.itempedido.ItemPedidoPO;
import br.com.srcsoftware.sistema.pedido.notafiscal.venda.ItemNotaFiscalVendaPO;
import br.com.srcsoftware.sistema.pedido.notafiscal.venda.NotaFiscalVendaPO;
import br.com.srcsoftware.sistema.pedido.pedido.PedidoPO;
import br.com.srcsoftware.sistema.pessoa.fornecedor.juridico.FornecedorJuridicoPO;
import br.com.srcsoftware.sistema.produto.produto.ProdutoPO;

public class NotaFiscalSimplesRemessaDAOImpl implements NotaFiscalSimplesRemessaDAOInterface{

	@Override
	public NotaFiscalSimplesRemessaPO inserir( HibernateConnection hibernate, NotaFiscalSimplesRemessaPO po ) throws ApplicationException {
		try {
			return (NotaFiscalSimplesRemessaPO) hibernate.inserir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void alterar( HibernateConnection hibernate, NotaFiscalSimplesRemessaPO po ) throws ApplicationException {
		try {
			hibernate.alterar( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void excluir( HibernateConnection hibernate, NotaFiscalSimplesRemessaPO po ) throws ApplicationException {
		try {
			hibernate.excluir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public NotaFiscalSimplesRemessaPO filtrarPorId( Long id ) throws ApplicationException {
		try {
			return (NotaFiscalSimplesRemessaPO) new HibernateConnection().filtrarPorId( NotaFiscalSimplesRemessaPO.class, id );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@SuppressWarnings( "unchecked" )
	@Override
	public List< NotaFiscalSimplesRemessaPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, HashMap< String, ArrayList< Object > > camposBetween, NotaFiscalSimplesRemessaPO poFilter ) throws ApplicationException {

		HibernateConnection hibernate = new HibernateConnection();
		try {
			hibernate.iniciarTransacao();

			//  CRIANDO A CRITERIA COM BASE NA CRITERIABUILDER
			CriteriaBuilder builder = hibernate.getSessaoCorrente().getCriteriaBuilder();

			//  CRIANDO A CRITERIA DE CONSULTA COM BASE NA CRITERIABUILDER
			CriteriaQuery< NotaFiscalSimplesRemessaPO > criteria = builder.createQuery( NotaFiscalSimplesRemessaPO.class );

			// DEFININDO O OBJETO BASE DA SELECT
			Root< NotaFiscalSimplesRemessaPO > root = criteria.from( NotaFiscalSimplesRemessaPO.class );

			// Definindo os parametros do WHERE
			Predicate[ ] parametros = this.montarParametros( builder, root, poFilter, camposBetween );

			// Executando A Consulta
			List< NotaFiscalSimplesRemessaPO > encontrados = hibernate.filtrar( paginacao, camposOrders, builder, root, criteria, parametros, NotaFiscalSimplesRemessaPO.class );

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

	private Predicate[ ] montarParametros( CriteriaBuilder builder, Root< NotaFiscalSimplesRemessaPO > root, NotaFiscalSimplesRemessaPO poFilter, HashMap< String, ArrayList< Object > > camposBetween ) {
		ArrayList< Predicate > lista = new ArrayList<>();

		if ( poFilter == null ) {
			return null;
		}

		lista.addAll( HibernateConnection.montarBetween( root, builder, camposBetween ) );

		if ( !Utilidades.isNuloOuVazio( poFilter.getId() ) ) {
			lista.add( builder.equal( root.get( "id" ), poFilter.getId() ) );
		} else {
			if ( !Utilidades.isNuloOuVazio( poFilter.getNumero() ) ) {
				lista.add( builder.like( root.get( "numero" ), poFilter.getNumero() + "%" ) );
			}

			if ( !Utilidades.isNuloOuVazio( poFilter.getNomeUsuarioCriacao() ) ) {
				lista.add( builder.like( root.get( "nomeUsuarioCriacao" ), poFilter.getNomeUsuarioCriacao() + "%" ) );
			}

			if ( !Utilidades.isNuloOuVazio( poFilter.getNotaFiscalVendaFutura() ) ) {

				Join< NotaFiscalSimplesRemessaPO, NotaFiscalVendaPO > notaFiscalVendaFuturaJoin = null;

				if ( !Utilidades.isNuloOuVazio( poFilter.getNotaFiscalVendaFutura().getId() ) ) {
					if ( notaFiscalVendaFuturaJoin == null ) {
						notaFiscalVendaFuturaJoin = root.join( "notaFiscalVendaFutura", JoinType.INNER );
						notaFiscalVendaFuturaJoin.alias( "notaFiscalVendaFutura" );
					}
					lista.add( builder.equal( notaFiscalVendaFuturaJoin.get( "id" ), poFilter.getNotaFiscalVendaFutura().getId() ) );

				} else {

					if ( !Utilidades.isNuloOuVazio( poFilter.getNotaFiscalVendaFutura().getNumero() ) ) {

						if ( notaFiscalVendaFuturaJoin == null ) {
							notaFiscalVendaFuturaJoin = root.join( "notaFiscalVendaFutura", JoinType.INNER );
							notaFiscalVendaFuturaJoin.alias( "notaFiscalVendaFutura" );
						}

						lista.add( builder.like( notaFiscalVendaFuturaJoin.get( "numero" ), poFilter.getNotaFiscalVendaFutura().getNumero() + "%" ) );
					}

					if ( !Utilidades.isNuloOuVazio( poFilter.getNotaFiscalVendaFutura().getStatus() ) ) {
						if ( notaFiscalVendaFuturaJoin == null ) {
							notaFiscalVendaFuturaJoin = root.join( "notaFiscalVendaFutura", JoinType.INNER );
							notaFiscalVendaFuturaJoin.alias( "notaFiscalVendaFutura" );
						}

						lista.add( builder.equal( notaFiscalVendaFuturaJoin.get( "status" ), poFilter.getNotaFiscalVendaFutura().getStatus() ) );
					}

					if ( !Utilidades.isNuloOuVazio( poFilter.getNotaFiscalVendaFutura().getNomeUsuarioCriacao() ) ) {
						if ( notaFiscalVendaFuturaJoin == null ) {
							notaFiscalVendaFuturaJoin = root.join( "notaFiscalVendaFutura", JoinType.INNER );
							notaFiscalVendaFuturaJoin.alias( "notaFiscalVendaFutura" );
						}

						lista.add( builder.like( notaFiscalVendaFuturaJoin.get( "nomeUsuarioCriacao" ), poFilter.getNotaFiscalVendaFutura().getNomeUsuarioCriacao() + "%" ) );
					}

					if ( !Utilidades.isNuloOuVazio( poFilter.getNotaFiscalVendaFutura().getTipo() ) ) {
						if ( notaFiscalVendaFuturaJoin == null ) {
							notaFiscalVendaFuturaJoin = root.join( "notaFiscalVendaFutura", JoinType.INNER );
							notaFiscalVendaFuturaJoin.alias( "notaFiscalVendaFutura" );
						}

						lista.add( builder.equal( notaFiscalVendaFuturaJoin.get( "tipo" ), poFilter.getNotaFiscalVendaFutura().getTipo() ) );
					}

					// MONTANDO JOIN PEDIDO
					if ( !Utilidades.isNuloOuVazio( poFilter.getNotaFiscalVendaFutura().getPedido() ) ) {

						Join< NotaFiscalVendaPO, PedidoPO > pedidoJoin = null;

						if ( !Utilidades.isNuloOuVazio( poFilter.getNotaFiscalVendaFutura().getPedido().getId() ) ) {

							if ( notaFiscalVendaFuturaJoin == null ) {
								notaFiscalVendaFuturaJoin = root.join( "notaFiscalVendaFutura", JoinType.INNER );
								notaFiscalVendaFuturaJoin.alias( "notaFiscalVendaFutura" );
							}

							if ( pedidoJoin == null ) {
								pedidoJoin = notaFiscalVendaFuturaJoin.join( "pedido", JoinType.INNER );
								pedidoJoin.alias( "pedido" );
							}
							lista.add( builder.equal( pedidoJoin.get( "id" ), poFilter.getNotaFiscalVendaFutura().getPedido().getId() ) );

						} else {
							if ( !Utilidades.isNuloOuVazio( poFilter.getNotaFiscalVendaFutura().getPedido().getNumero() ) ) {

								if ( notaFiscalVendaFuturaJoin == null ) {
									notaFiscalVendaFuturaJoin = root.join( "notaFiscalVendaFutura", JoinType.INNER );
									notaFiscalVendaFuturaJoin.alias( "notaFiscalVendaFutura" );
								}

								if ( pedidoJoin == null ) {
									pedidoJoin = notaFiscalVendaFuturaJoin.join( "pedido", JoinType.INNER );
									pedidoJoin.alias( "pedido" );
								}
								lista.add( builder.equal( pedidoJoin.get( "numero" ), poFilter.getNotaFiscalVendaFutura().getPedido().getNumero() ) );
							}

							if ( !Utilidades.isNuloOuVazio( poFilter.getNotaFiscalVendaFutura().getPedido().getStatus() ) ) {

								if ( notaFiscalVendaFuturaJoin == null ) {
									notaFiscalVendaFuturaJoin = root.join( "notaFiscalVendaFutura", JoinType.INNER );
									notaFiscalVendaFuturaJoin.alias( "notaFiscalVendaFutura" );
								}

								if ( pedidoJoin == null ) {
									pedidoJoin = notaFiscalVendaFuturaJoin.join( "pedido", JoinType.INNER );
									pedidoJoin.alias( "pedido" );
								}
								lista.add( builder.equal( pedidoJoin.get( "status" ), poFilter.getNotaFiscalVendaFutura().getPedido().getStatus() ) );
							}

							// MONTANDO JOIN FORNECEDOR
							if ( !Utilidades.isNuloOuVazio( poFilter.getNotaFiscalVendaFutura().getPedido().getFornecedor() ) ) {

								Join< PedidoPO, FornecedorJuridicoPO > fornecedorJoin = null;

								if ( !Utilidades.isNuloOuVazio( poFilter.getNotaFiscalVendaFutura().getPedido().getFornecedor().getId() ) ) {

									if ( notaFiscalVendaFuturaJoin == null ) {
										notaFiscalVendaFuturaJoin = root.join( "notaFiscalVendaFutura", JoinType.INNER );
										notaFiscalVendaFuturaJoin.alias( "notaFiscalVendaFutura" );
									}

									if ( pedidoJoin == null ) {
										pedidoJoin = notaFiscalVendaFuturaJoin.join( "pedido", JoinType.INNER );
										pedidoJoin.alias( "pedido" );
									}

									if ( fornecedorJoin == null ) {
										fornecedorJoin = pedidoJoin.join( "fornecedor", JoinType.INNER );
										fornecedorJoin.alias( "fornecedor" );
									}
									lista.add( builder.equal( fornecedorJoin.get( "id" ), poFilter.getNotaFiscalVendaFutura().getPedido().getFornecedor().getId() ) );

								} else {
									if ( !Utilidades.isNuloOuVazio( poFilter.getNotaFiscalVendaFutura().getPedido().getFornecedor().getNome() ) ) {

										if ( notaFiscalVendaFuturaJoin == null ) {
											notaFiscalVendaFuturaJoin = root.join( "notaFiscalVendaFutura", JoinType.INNER );
											notaFiscalVendaFuturaJoin.alias( "notaFiscalVendaFutura" );
										}

										if ( pedidoJoin == null ) {
											pedidoJoin = notaFiscalVendaFuturaJoin.join( "pedido", JoinType.INNER );
											pedidoJoin.alias( "pedido" );
										}

										if ( fornecedorJoin == null ) {
											fornecedorJoin = pedidoJoin.join( "fornecedor", JoinType.INNER );
											fornecedorJoin.alias( "fornecedor" );
										}
										lista.add( builder.like( fornecedorJoin.get( "nome" ), poFilter.getNotaFiscalVendaFutura().getPedido().getFornecedor().getNome() + "%" ) );
									}

								}
							}
						}
					}

					// MONTANDO JOIN ITENS
					if ( !Utilidades.isNuloOuVazio( poFilter.getItens() ) ) {
						Join< NotaFiscalSimplesRemessaPO, ItemNotaFiscalSimplesRemessaPO > itemNotaFiscalSimplesRemessaJoinList = null;

						ItemNotaFiscalSimplesRemessaPO itemNotaFiscalSimplesRemessaFiltrar = poFilter.getItens().iterator().next();

						if ( !Utilidades.isNuloOuVazio( itemNotaFiscalSimplesRemessaFiltrar.getId() ) ) {
							if ( itemNotaFiscalSimplesRemessaJoinList == null ) {
								itemNotaFiscalSimplesRemessaJoinList = root.joinSet( "itens", JoinType.LEFT );
								itemNotaFiscalSimplesRemessaJoinList.alias( "itemNotaFiscalSimplesRemessa" );
							}
							lista.add( builder.equal( itemNotaFiscalSimplesRemessaJoinList.get( "id" ), itemNotaFiscalSimplesRemessaFiltrar.getId() ) );
						} else {

							if ( !Utilidades.isNuloOuVazio( itemNotaFiscalSimplesRemessaFiltrar.getItemNotaFiscalVendaFutura() ) ) {

								Join< ItemNotaFiscalSimplesRemessaPO, ItemNotaFiscalVendaPO > itemNotaFiscalVendaJoin = null;

								ItemNotaFiscalVendaPO itemNotaFiscalVendaFiltrar = itemNotaFiscalSimplesRemessaFiltrar.getItemNotaFiscalVendaFutura();

								if ( !Utilidades.isNuloOuVazio( itemNotaFiscalVendaFiltrar.getId() ) ) {
									if ( itemNotaFiscalSimplesRemessaJoinList == null ) {
										itemNotaFiscalSimplesRemessaJoinList = root.joinSet( "itens", JoinType.LEFT );
										itemNotaFiscalSimplesRemessaJoinList.alias( "itemNotaFiscalSimplesRemessa" );
									}
									if ( itemNotaFiscalVendaJoin == null ) {
										itemNotaFiscalVendaJoin = itemNotaFiscalSimplesRemessaJoinList.join( "itemNotaFiscalVendaFutura", JoinType.INNER );
										itemNotaFiscalVendaJoin.alias( "itemNotaFiscalVendaFutura" );
									}

									lista.add( builder.equal( itemNotaFiscalVendaJoin.get( "id" ), itemNotaFiscalVendaFiltrar.getId() ) );
								} else {
									if ( !Utilidades.isNuloOuVazio( itemNotaFiscalSimplesRemessaFiltrar.getItemNotaFiscalVendaFutura().getItemPedido() ) ) {

										Join< ItemNotaFiscalVendaPO, ItemPedidoPO > itemPedidoJoin = null;

										ItemPedidoPO itemPedidoFiltrar = itemNotaFiscalSimplesRemessaFiltrar.getItemNotaFiscalVendaFutura().getItemPedido();

										if ( !Utilidades.isNuloOuVazio( itemPedidoFiltrar.getId() ) ) {
											if ( itemNotaFiscalSimplesRemessaJoinList == null ) {
												itemNotaFiscalSimplesRemessaJoinList = root.joinSet( "itens", JoinType.LEFT );
												itemNotaFiscalSimplesRemessaJoinList.alias( "itemNotaFiscalSimplesRemessa" );
											}
											if ( itemNotaFiscalVendaJoin == null ) {
												itemNotaFiscalVendaJoin = itemNotaFiscalSimplesRemessaJoinList.join( "itemNotaFiscalVendaFutura", JoinType.INNER );
												itemNotaFiscalVendaJoin.alias( "itemNotaFiscalVendaFutura" );
											}
											if ( itemPedidoJoin == null ) {
												itemPedidoJoin = itemNotaFiscalVendaJoin.join( "itemPedido", JoinType.INNER );
												itemPedidoJoin.alias( "itemPedido" );
											}
											lista.add( builder.equal( itemPedidoJoin.get( "id" ), itemPedidoFiltrar.getId() ) );
										} else {

											// MONTANDO JOIN PRODUTO
											if ( !Utilidades.isNuloOuVazio( itemPedidoFiltrar.getProduto() ) ) {

												Join< ItemPedidoPO, ProdutoPO > produtoJoin = null;

												if ( !Utilidades.isNuloOuVazio( itemPedidoFiltrar.getProduto().getId() ) ) {
													if ( itemNotaFiscalSimplesRemessaJoinList == null ) {
														itemNotaFiscalSimplesRemessaJoinList = root.joinSet( "itens", JoinType.LEFT );
														itemNotaFiscalSimplesRemessaJoinList.alias( "itemNotaFiscalSimplesRemessa" );
													}
													if ( itemNotaFiscalVendaJoin == null ) {
														itemNotaFiscalVendaJoin = itemNotaFiscalSimplesRemessaJoinList.join( "itemNotaFiscalVendaFutura", JoinType.INNER );
														itemNotaFiscalVendaJoin.alias( "itemNotaFiscalVendaFutura" );
													}
													if ( itemPedidoJoin == null ) {
														itemPedidoJoin = itemNotaFiscalVendaJoin.join( "itemPedido", JoinType.INNER );
														itemPedidoJoin.alias( "itemPedido" );
													}
													if ( produtoJoin == null ) {
														produtoJoin = itemPedidoJoin.join( "produto", JoinType.INNER );
														produtoJoin.alias( "produto" );
													}

													lista.add( builder.equal( produtoJoin.get( "id" ), itemPedidoFiltrar.getProduto().getId() ) );
												} else {

													if ( !Utilidades.isNuloOuVazio( itemPedidoFiltrar.getProduto().getNome() ) ) {

														if ( itemNotaFiscalSimplesRemessaJoinList == null ) {
															itemNotaFiscalSimplesRemessaJoinList = root.joinSet( "itens", JoinType.LEFT );
															itemNotaFiscalSimplesRemessaJoinList.alias( "itemNotaFiscalSimplesRemessa" );
														}
														if ( itemNotaFiscalVendaJoin == null ) {
															itemNotaFiscalVendaJoin = itemNotaFiscalSimplesRemessaJoinList.join( "itemNotaFiscalVendaFutura", JoinType.INNER );
															itemNotaFiscalVendaJoin.alias( "itemNotaFiscalVendaFutura" );
														}
														if ( itemPedidoJoin == null ) {
															itemPedidoJoin = itemNotaFiscalVendaJoin.join( "itemPedido", JoinType.INNER );
															itemPedidoJoin.alias( "itemPedido" );
														}
														if ( produtoJoin == null ) {
															produtoJoin = itemPedidoJoin.join( "produto", JoinType.INNER );
															produtoJoin.alias( "produto" );
														}

														lista.add( builder.like( produtoJoin.get( "nome" ), itemPedidoFiltrar.getProduto().getNome() + "%" ) );
													}

													if ( !Utilidades.isNuloOuVazio( itemPedidoFiltrar.getProduto().getNomeCompleto() ) ) {

														if ( itemNotaFiscalSimplesRemessaJoinList == null ) {
															itemNotaFiscalSimplesRemessaJoinList = root.joinSet( "itens", JoinType.LEFT );
															itemNotaFiscalSimplesRemessaJoinList.alias( "itemNotaFiscalSimplesRemessa" );
														}
														if ( itemNotaFiscalVendaJoin == null ) {
															itemNotaFiscalVendaJoin = itemNotaFiscalSimplesRemessaJoinList.join( "itemNotaFiscalVendaFutura", JoinType.INNER );
															itemNotaFiscalVendaJoin.alias( "itemNotaFiscalVendaFutura" );
														}
														if ( itemPedidoJoin == null ) {
															itemPedidoJoin = itemNotaFiscalVendaJoin.join( "itemPedido", JoinType.INNER );
															itemPedidoJoin.alias( "itemPedido" );
														}
														if ( produtoJoin == null ) {
															produtoJoin = itemPedidoJoin.join( "produto", JoinType.INNER );
															produtoJoin.alias( "produto" );
														}

														lista.add( builder.like( produtoJoin.get( "nomeCompleto" ), "%".concat( itemPedidoFiltrar.getProduto().getNomeCompleto() ) + "%" ) );
													}
												}
											}
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
