package br.com.srcsoftware.sistema.pedido.pedido;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
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
import br.com.srcsoftware.sistema.pessoa.fornecedor.juridico.FornecedorJuridicoPO;
import br.com.srcsoftware.sistema.produto.marca.MarcaPO;
import br.com.srcsoftware.sistema.produto.produto.ProdutoPO;
import br.com.srcsoftware.sistema.produto.tipo.TipoPO;
import br.com.srcsoftware.sistema.produto.unidademedida.UnidadeMedidaPO;

public class PedidoDAOImpl implements PedidoDAOInterface{

	@Override
	public PedidoPO inserir( HibernateConnection hibernate, PedidoPO po ) throws ApplicationException {
		try {
			return (PedidoPO) hibernate.inserir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void alterar( HibernateConnection hibernate, PedidoPO po ) throws ApplicationException {
		try {
			hibernate.alterar( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void excluir( HibernateConnection hibernate, PedidoPO po ) throws ApplicationException {
		try {
			hibernate.excluir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public PedidoPO filtrarPorId( Long id ) throws ApplicationException {
		try {
			return (PedidoPO) new HibernateConnection().filtrarPorId( PedidoPO.class, id );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@SuppressWarnings( "unchecked" )
	@Override
	public List< PedidoPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, HashMap< String, ArrayList< Object > > camposBetween, PedidoPO poFilter ) throws ApplicationException {

		HibernateConnection hibernate = new HibernateConnection();
		try {
			hibernate.iniciarTransacao();

			//  CRIANDO A CRITERIA COM BASE NA CRITERIABUILDER
			CriteriaBuilder builder = hibernate.getSessaoCorrente().getCriteriaBuilder();

			//  CRIANDO A CRITERIA DE CONSULTA COM BASE NA CRITERIABUILDER
			CriteriaQuery< PedidoPO > criteria = builder.createQuery( PedidoPO.class );

			// DEFININDO O OBJETO BASE DA SELECT
			Root< PedidoPO > root = criteria.from( PedidoPO.class );

			// Definindo os parametros do WHERE
			Predicate[ ] parametros = this.montarParametros( builder, root, poFilter, camposBetween );

			// Executando A Consulta
			List< PedidoPO > encontrados = hibernate.filtrar( paginacao, camposOrders, builder, root, criteria, parametros, PedidoPO.class );

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

	private Predicate[ ] montarParametros( CriteriaBuilder builder, Root< PedidoPO > root, PedidoPO poFilter, HashMap< String, ArrayList< Object > > camposBetween ) {
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

			if ( !Utilidades.isNuloOuVazio( poFilter.getStatus() ) ) {
				lista.add( builder.equal( root.get( "status" ), poFilter.getStatus() ) );
			}

			// MONTANDO JOIN FORNECEDOR
			if ( !Utilidades.isNuloOuVazio( poFilter.getFornecedor() ) ) {

				Join< PedidoPO, FornecedorJuridicoPO > fornecedorJoin = null;

				if ( !Utilidades.isNuloOuVazio( poFilter.getFornecedor().getId() ) ) {
					if ( fornecedorJoin == null ) {
						fornecedorJoin = root.join( "fornecedor", JoinType.INNER );
						fornecedorJoin.alias( "fornecedor" );
					}
					lista.add( builder.equal( fornecedorJoin.get( "id" ), poFilter.getFornecedor().getId() ) );

				} else {
					if ( !Utilidades.isNuloOuVazio( poFilter.getFornecedor().getNome() ) ) {

						if ( fornecedorJoin == null ) {
							fornecedorJoin = root.join( "fornecedor", JoinType.INNER );
							fornecedorJoin.alias( "fornecedor" );
						}
						lista.add( builder.like( fornecedorJoin.get( "nome" ), poFilter.getFornecedor().getNome() + "%" ) );
					}
				}
			}

			// MONTANDO JOIN ITENS
			if ( !Utilidades.isNuloOuVazio( poFilter.getItens() ) ) {
				Join< PedidoPO, ItemPedidoPO > itemJoinList = null;

				ItemPedidoPO itemFiltrar = poFilter.getItens().iterator().next();

				if ( !Utilidades.isNuloOuVazio( itemFiltrar.getId() ) ) {
					if ( itemJoinList == null ) {
						itemJoinList = root.joinSet( "itens", JoinType.LEFT );
						itemJoinList.alias( "item" );
					}
					lista.add( builder.equal( itemJoinList.get( "id" ), itemFiltrar.getId() ) );
				} else {

					// MONTANDO JOIN PRODUTO
					if ( !Utilidades.isNuloOuVazio( itemFiltrar.getProduto() ) ) {

						Join< ItemPedidoPO, ProdutoPO > produtoJoin = null;

						if ( !Utilidades.isNuloOuVazio( itemFiltrar.getProduto().getId() ) ) {

							if ( itemJoinList == null ) {
								itemJoinList = root.joinSet( "itens", JoinType.LEFT );
								itemJoinList.alias( "item" );
							}
							if ( produtoJoin == null ) {
								produtoJoin = itemJoinList.join( "produto", JoinType.INNER );
								produtoJoin.alias( "produto" );
							}

							lista.add( builder.equal( produtoJoin.get( "id" ), itemFiltrar.getProduto().getId() ) );
						} else {

							if ( !Utilidades.isNuloOuVazio( itemFiltrar.getProduto().getNome() ) ) {

								if ( itemJoinList == null ) {
									itemJoinList = root.joinSet( "itens", JoinType.LEFT );
									itemJoinList.alias( "item" );
								}
								if ( produtoJoin == null ) {
									produtoJoin = itemJoinList.join( "produto", JoinType.INNER );
									produtoJoin.alias( "produto" );
								}

								lista.add( builder.like( produtoJoin.get( "nome" ), itemFiltrar.getProduto().getNome() + "%" ) );
							}

							if ( !Utilidades.isNuloOuVazio( itemFiltrar.getProduto().getNomeCompleto() ) ) {

								if ( itemJoinList == null ) {
									itemJoinList = root.joinSet( "itens", JoinType.LEFT );
									itemJoinList.alias( "item" );
								}
								if ( produtoJoin == null ) {
									produtoJoin = itemJoinList.join( "produto", JoinType.INNER );
									produtoJoin.alias( "produto" );
								}

								lista.add( builder.like( produtoJoin.get( "nomeCompleto" ), "%".concat( itemFiltrar.getProduto().getNomeCompleto() ) + "%" ) );
							}

							if ( !Utilidades.isNuloOuVazio( itemFiltrar.getProduto().getLocalizacao() ) ) {
								if ( itemJoinList == null ) {
									itemJoinList = root.joinSet( "itens", JoinType.LEFT );
									itemJoinList.alias( "item" );
								}
								if ( produtoJoin == null ) {
									produtoJoin = itemJoinList.join( "produto", JoinType.INNER );
									produtoJoin.alias( "produto" );
								}

								lista.add( builder.equal( produtoJoin.get( "localizacao" ), itemFiltrar.getProduto().getLocalizacao() ) );
							}

							// MONTANDO JOIN TIPO
							if ( !Utilidades.isNuloOuVazio( itemFiltrar.getProduto().getTipo() ) ) {

								Join< ProdutoPO, TipoPO > tipoJoin = null;

								if ( !Utilidades.isNuloOuVazio( itemFiltrar.getProduto().getTipo().getId() ) ) {
									if ( itemJoinList == null ) {
										itemJoinList = root.joinSet( "itens", JoinType.LEFT );
										itemJoinList.alias( "item" );
									}
									if ( produtoJoin == null ) {
										produtoJoin = itemJoinList.join( "produto", JoinType.INNER );
										produtoJoin.alias( "produto" );
									}

									if ( tipoJoin == null ) {
										tipoJoin = produtoJoin.join( "tipo", JoinType.INNER );
										tipoJoin.alias( "tipo" );
									}

									lista.add( builder.equal( tipoJoin.get( "id" ), itemFiltrar.getProduto().getTipo().getId() ) );
								} else {
									if ( !Utilidades.isNuloOuVazio( itemFiltrar.getProduto().getTipo().getNome() ) ) {

										if ( itemJoinList == null ) {
											itemJoinList = root.joinSet( "itens", JoinType.LEFT );
											itemJoinList.alias( "item" );
										}
										if ( produtoJoin == null ) {
											produtoJoin = itemJoinList.join( "produto", JoinType.INNER );
											produtoJoin.alias( "produto" );
										}

										if ( tipoJoin == null ) {
											tipoJoin = produtoJoin.join( "tipo", JoinType.INNER );
											tipoJoin.alias( "tipo" );
										}
										lista.add( builder.equal( tipoJoin.get( "nome" ), itemFiltrar.getProduto().getTipo().getNome() ) );
									}
								}
							}

							// MONTANDO JOIN MARCA
							if ( !Utilidades.isNuloOuVazio( itemFiltrar.getProduto().getMarca() ) ) {

								Join< ProdutoPO, MarcaPO > marcaJoin = null;

								if ( !Utilidades.isNuloOuVazio( itemFiltrar.getProduto().getMarca().getId() ) ) {
									if ( itemJoinList == null ) {
										itemJoinList = root.joinSet( "itens", JoinType.LEFT );
										itemJoinList.alias( "item" );
									}
									if ( produtoJoin == null ) {
										produtoJoin = itemJoinList.join( "produto", JoinType.INNER );
										produtoJoin.alias( "produto" );
									}

									if ( marcaJoin == null ) {
										marcaJoin = produtoJoin.join( "marca", JoinType.INNER );
										marcaJoin.alias( "marca" );
									}

									lista.add( builder.equal( marcaJoin.get( "id" ), itemFiltrar.getProduto().getMarca().getId() ) );
								} else {
									if ( !Utilidades.isNuloOuVazio( itemFiltrar.getProduto().getMarca().getNome() ) ) {

										if ( itemJoinList == null ) {
											itemJoinList = root.joinSet( "itens", JoinType.LEFT );
											itemJoinList.alias( "item" );
										}
										if ( produtoJoin == null ) {
											produtoJoin = itemJoinList.join( "produto", JoinType.INNER );
											produtoJoin.alias( "produto" );
										}

										if ( marcaJoin == null ) {
											marcaJoin = produtoJoin.join( "marca", JoinType.INNER );
											marcaJoin.alias( "marca" );
										}
										lista.add( builder.equal( marcaJoin.get( "nome" ), itemFiltrar.getProduto().getMarca().getNome() ) );
									}
								}
							}

							// MONTANDO JOIN TIPO
							if ( !Utilidades.isNuloOuVazio( itemFiltrar.getProduto().getUnidadeMedida() ) ) {

								Join< ProdutoPO, UnidadeMedidaPO > unidadeMedidaJoin = null;

								if ( !Utilidades.isNuloOuVazio( itemFiltrar.getProduto().getUnidadeMedida().getId() ) ) {

									if ( itemJoinList == null ) {
										itemJoinList = root.joinSet( "itens", JoinType.LEFT );
										itemJoinList.alias( "item" );
									}
									if ( produtoJoin == null ) {
										produtoJoin = itemJoinList.join( "produto", JoinType.INNER );
										produtoJoin.alias( "produto" );
									}

									if ( unidadeMedidaJoin == null ) {
										unidadeMedidaJoin = produtoJoin.join( "unidadeMedida", JoinType.INNER );
										unidadeMedidaJoin.alias( "unidadeMedida" );
									}

									lista.add( builder.equal( unidadeMedidaJoin.get( "id" ), itemFiltrar.getProduto().getUnidadeMedida().getId() ) );
								} else {
									if ( !Utilidades.isNuloOuVazio( itemFiltrar.getProduto().getUnidadeMedida().getNome() ) ) {

										if ( itemJoinList == null ) {
											itemJoinList = root.joinSet( "itens", JoinType.LEFT );
											itemJoinList.alias( "item" );
										}
										if ( produtoJoin == null ) {
											produtoJoin = itemJoinList.join( "produto", JoinType.INNER );
											produtoJoin.alias( "produto" );
										}

										if ( unidadeMedidaJoin == null ) {
											unidadeMedidaJoin = produtoJoin.join( "unidadeMedida", JoinType.INNER );
											unidadeMedidaJoin.alias( "unidadeMedida" );
										}
										lista.add( builder.equal( unidadeMedidaJoin.get( "nome" ), itemFiltrar.getProduto().getUnidadeMedida().getNome() ) );
									}

									if ( !Utilidades.isNuloOuVazio( itemFiltrar.getProduto().getUnidadeMedida().getSigla() ) ) {

										if ( itemJoinList == null ) {
											itemJoinList = root.joinSet( "itens", JoinType.LEFT );
											itemJoinList.alias( "item" );
										}
										if ( produtoJoin == null ) {
											produtoJoin = itemJoinList.join( "produto", JoinType.INNER );
											produtoJoin.alias( "produto" );
										}

										if ( unidadeMedidaJoin == null ) {
											unidadeMedidaJoin = produtoJoin.join( "unidadeMedida", JoinType.INNER );
											unidadeMedidaJoin.alias( "unidadeMedida" );
										}
										lista.add( builder.equal( unidadeMedidaJoin.get( "sigla" ), itemFiltrar.getProduto().getUnidadeMedida().getSigla() ) );
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

	@SuppressWarnings( "unchecked" )
	@Override
	public List< PedidoPO > filtrarAbertosAndamentos( String numeroPedido, HashMap< String, String > camposOrders ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();
		try {
			hibernate.iniciarTransacao();

			final StringBuffer HQL = new StringBuffer();
			HQL.append( " SELECT pedido FROM " );
			HQL.append( " PedidoPO pedido " );
			HQL.append( " WHERE 1=1" );

			HQL.append( " and pedido.status in (:statusParam) " );

			HashMap< String, Object > parametros = new HashMap<>();

			if ( !Utilidades.isNuloOuVazio( numeroPedido ) ) {
				HQL.append( " and pedido.numero like :numeroParam " );
				parametros.put( "numeroParam", numeroPedido + "%" );
			}

			parametros.put( "statusParam", new ArrayList<>( Arrays.asList( "ABERTO", "ANDAMENTO" ) ) );

			Query query = hibernate.getSessaoCorrente().createQuery( HQL.toString() );

			for ( String key : parametros.keySet() ) {
				query.setParameter( key, parametros.get( key ) );
			}

			List< PedidoPO > encontrados = query.getResultList();

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

	@Override
	public BigDecimal calcularValoresRestantesEntregar( Long idProduto, Long idPedido ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();
		try {
			hibernate.iniciarTransacao();

			final StringBuffer SQL = new StringBuffer();
			SQL.append( " 	select	 " );
			SQL.append( " 	  coalesce(totais.qtdPedido,0) - (coalesce(totais.qtdEntregue,0)+coalesce(totais.qtdEntregue2,0))	 " );
			SQL.append( " 	from	 " );
			SQL.append( " 	(select 	 " );

			// ITENS PEDIDOS
			SQL.append( " 	(SELECT sum(quantidade) quantidadePedido FROM " + Constantes.SCHEMA + ".sistema_itenspedido ip	 " );
			SQL.append( " 	where 1=1 " );
			if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
				SQL.append( " and	ip.idProduto = :idProdutoParam	 " );
			}
			if ( !Utilidades.isNuloOuVazio( idPedido ) ) {
				SQL.append( " 	and ip.idPedido = :idPedidoParam " );
			}
			if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
				SQL.append( " 	group by ip.idProduto) qtdPedido,	 " );
			} else if ( !Utilidades.isNuloOuVazio( idPedido ) ) {
				SQL.append( " 	group by ip.idPedido) qtdPedido,	 " );
			}

			// NOTAS FISCAIS VENDAS
			SQL.append( " 	(SELECT sum(infv.quantidade) quantidadeEntregue FROM	 " );
			SQL.append( " 	 " + Constantes.SCHEMA + ".sistema_notasfiscalvenda nfv,	 " );
			SQL.append( " 	 " + Constantes.SCHEMA + ".sistema_itensnotafiscalvenda infv,	 " );
			SQL.append( " 	 " + Constantes.SCHEMA + ".sistema_itenspedido ip	 " );
			SQL.append( " 	where 1=1	 " );
			if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
				SQL.append( " and	ip.idProduto = :idProdutoParam	 " );
			}
			if ( !Utilidades.isNuloOuVazio( idPedido ) ) {
				SQL.append( " 	and ip.idPedido = :idPedidoParam " );
			}
			SQL.append( " 	and (nfv.tipo = 'Venda')  " );

			SQL.append( " 	and infv.idNotaEntrega = nfv.id	 " );
			SQL.append( " 	and infv.idItemPedido = ip.id	 " );
			if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
				SQL.append( " 	group by ip.idProduto) qtdEntregue,	 " );
			} else if ( !Utilidades.isNuloOuVazio( idPedido ) ) {
				SQL.append( " 	group by ip.idPedido) qtdEntregue,	 " );
			}

			// NOTAS FISCAIS VENDAS DE SIMPLES REMESSA
			SQL.append( " 	(SELECT sum(isr.quantidade) quantidadeEntregue FROM	 " );
			SQL.append( " 	 " + Constantes.SCHEMA + ".sistema_notassimplesremessa nsr,	 " );
			SQL.append( " 	 " + Constantes.SCHEMA + ".sistema_itenssimplesremessa isr,	 " );
			SQL.append( " 	 " + Constantes.SCHEMA + ".sistema_itensnotafiscalvenda infv,	 " );
			SQL.append( " 	 " + Constantes.SCHEMA + ".sistema_itenspedido ip	 " );
			SQL.append( " 	where 1=1	 " );
			if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
				SQL.append( " and	ip.idProduto = :idProdutoParam	 " );
			}
			if ( !Utilidades.isNuloOuVazio( idPedido ) ) {
				SQL.append( " 	and ip.idPedido = :idPedidoParam " );
			}
			SQL.append( " 	and isr.idNotaSimplesRemessa = nsr.id	 " );
			SQL.append( " 	and isr.idItemNotaFiscalVenda = infv.id	 " );
			SQL.append( " 	and infv.idItemPedido = ip.id	 " );
			if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
				SQL.append( " 	group by ip.idProduto) qtdEntregue2	 " );
			} else if ( !Utilidades.isNuloOuVazio( idPedido ) ) {
				SQL.append( " 	group by ip.idPedido) qtdEntregue2	 " );
			}

			SQL.append( " 	from dual) totais	 " );

			HashMap< String, Object > parametros = new HashMap<>();

			if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
				parametros.put( "idProdutoParam", idProduto );
			}
			if ( !Utilidades.isNuloOuVazio( idPedido ) ) {
				parametros.put( "idPedidoParam", idPedido );
			}

			Query query = hibernate.getSessaoCorrente().createNativeQuery( SQL.toString() );

			if ( !Utilidades.isNuloOuVazio( parametros ) ) {
				for ( String key : parametros.keySet() ) {
					query.setParameter( key, parametros.get( key ) );
				}
			}

			BigDecimal resultado = (BigDecimal) query.getSingleResult();

			hibernate.commitTransacao();

			return resultado;
		} catch ( ApplicationException e ) {
			hibernate.rollbackTransacao();
			throw e;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();
			throw new ApplicationException( "Erro inesperado[999]" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public BigDecimal calcularValoresRestantesEntregar( Long idProduto, Long idPedido, Long idNotaFiscalVenda, boolean considerarFutura ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();
		try {
			hibernate.iniciarTransacao();

			final StringBuffer SQL = new StringBuffer();
			SQL.append( " 	select	 " );
			SQL.append( " 	  coalesce(totais.qtdPedido,0) - (coalesce(totais.qtdEntregue,0)+coalesce(totais.qtdEntregue2,0))	 " );
			SQL.append( " 	from	 " );
			SQL.append( " 	(select 	 " );

			// ITENS PEDIDOS
			SQL.append( " 	(SELECT sum(quantidade) quantidadePedido FROM " + Constantes.SCHEMA + ".sistema_itensPedido ip	 " );
			SQL.append( " 	where 1=1 " );
			if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
				SQL.append( " and	ip.idProduto = :idProdutoParam	 " );
			}
			if ( !Utilidades.isNuloOuVazio( idPedido ) ) {
				SQL.append( " 	and ip.idPedido = :idPedidoParam " );
			}
			if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
				SQL.append( " 	group by ip.idProduto) qtdPedido,	 " );
			} else if ( !Utilidades.isNuloOuVazio( idPedido ) ) {
				SQL.append( " 	group by ip.idPedido) qtdPedido,	 " );
			}

			// NOTAS FISCAIS VENDAS
			SQL.append( " 	(SELECT sum(infv.quantidade) quantidadeEntregue FROM	 " );
			SQL.append( " 	 " + Constantes.SCHEMA + ".sistema_notasFiscalVenda nfv,	 " );
			SQL.append( " 	 " + Constantes.SCHEMA + ".sistema_itensNotaFiscalVenda infv,	 " );
			SQL.append( " 	 " + Constantes.SCHEMA + ".sistema_itensPedido ip	 " );
			SQL.append( " 	where 1=1	 " );
			if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
				SQL.append( " and	ip.idProduto = :idProdutoParam	 " );
			}
			if ( !Utilidades.isNuloOuVazio( idPedido ) ) {
				SQL.append( " 	and ip.idPedido = :idPedidoParam " );
			}
			if ( !Utilidades.isNuloOuVazio( idNotaFiscalVenda ) ) {
				SQL.append( " 	and nfv.id = :idNotaFiscalVendaParam " );
			}
			SQL.append( " 	and (nfv.tipo = 'Venda')  " );

			SQL.append( " 	and infv.idNotaEntrega = nfv.id	 " );
			SQL.append( " 	and infv.idItemPedido = ip.id	 " );
			if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
				SQL.append( " 	group by ip.idProduto) qtdEntregue,	 " );
			} else if ( !Utilidades.isNuloOuVazio( idPedido ) ) {
				SQL.append( " 	group by ip.idPedido) qtdEntregue,	 " );
			}

			if ( considerarFutura ) {
				// NOTAS FISCAIS FUTURAS
				SQL.append( " 	(SELECT sum(infv.quantidade) quantidadeEntregue FROM	 " );
				SQL.append( " 	 " + Constantes.SCHEMA + ".sistema_notasFiscalVenda nfv,	 " );
				SQL.append( " 	 " + Constantes.SCHEMA + ".sistema_itensNotaFiscalVenda infv,	 " );
				SQL.append( " 	 " + Constantes.SCHEMA + ".sistema_itensPedido ip	 " );
				SQL.append( " 	where 1=1	 " );
				if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
					SQL.append( " and	ip.idProduto = :idProdutoParam	 " );
				}
				if ( !Utilidades.isNuloOuVazio( idPedido ) ) {
					SQL.append( " 	and ip.idPedido = :idPedidoParam " );
				}
				if ( !Utilidades.isNuloOuVazio( idNotaFiscalVenda ) ) {
					SQL.append( " 	and nfv.id = :idNotaFiscalVendaParam " );
				}
				SQL.append( " 	and (nfv.tipo = 'Futura')  " );

				SQL.append( " 	and infv.idNotaEntrega = nfv.id	 " );
				SQL.append( " 	and infv.idItemPedido = ip.id	 " );
				if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
					SQL.append( " 	group by ip.idProduto) qtdEntregue2	 " );
				} else if ( !Utilidades.isNuloOuVazio( idPedido ) ) {
					SQL.append( " 	group by ip.idPedido) qtdEntregue2	 " );
				}

			} else {
				SQL.append( " 0 qtdEntregue2 " );
			}

			SQL.append( " 	from dual) totais	 " );

			HashMap< String, Object > parametros = new HashMap<>();

			if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
				parametros.put( "idProdutoParam", idProduto );
			}
			if ( !Utilidades.isNuloOuVazio( idPedido ) ) {
				parametros.put( "idPedidoParam", idPedido );
			}
			if ( !Utilidades.isNuloOuVazio( idNotaFiscalVenda ) ) {
				parametros.put( "idNotaFiscalVendaParam", idNotaFiscalVenda );
			}

			Query query = hibernate.getSessaoCorrente().createNativeQuery( SQL.toString() );

			if ( !Utilidades.isNuloOuVazio( parametros ) ) {
				for ( String key : parametros.keySet() ) {
					query.setParameter( key, parametros.get( key ) );
				}
			}

			BigDecimal resultado = (BigDecimal) query.getSingleResult();

			hibernate.commitTransacao();

			return resultado;
		} catch ( ApplicationException e ) {
			hibernate.rollbackTransacao();
			throw e;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();
			throw new ApplicationException( "Erro inesperado[999]" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public BigDecimal calcularValoresRestantesEntregarSimplesRemessa( Long idProduto, Long idNotaFiscalVendaFutura ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();
		try {
			hibernate.iniciarTransacao();

			final StringBuffer SQL = new StringBuffer();
			SQL.append( " 	select	 " );
			SQL.append( " 	  (coalesce(totais.qtdEntregue,0)) - coalesce(totais.qtdSimpleRemessa,0)	 " );
			SQL.append( " 	from	 " );
			SQL.append( " 	(select 	 " );

			SQL.append( " 	(SELECT sum(infv.quantidade) quantidadeEntregue FROM	 " );
			SQL.append( " 	 " + Constantes.SCHEMA + ".sistema_notasFiscalVenda nfv,	 " );
			SQL.append( " 	 " + Constantes.SCHEMA + ".sistema_itensNotaFiscalVenda infv,	 " );
			SQL.append( " 	 " + Constantes.SCHEMA + ".sistema_itensPedido ip	 " );
			SQL.append( " 	where 1=1	 " );
			if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
				SQL.append( " and	ip.idProduto = :idProdutoParam	 " );
			}
			if ( !Utilidades.isNuloOuVazio( idNotaFiscalVendaFutura ) ) {
				SQL.append( " 	and nfv.id = :idNotaFiscalVendaFuturaParam " );
			}
			SQL.append( " 	and (nfv.tipo = 'Futura')  " );

			SQL.append( " 	and infv.idNotaEntrega = nfv.id	 " );
			SQL.append( " 	and infv.idItemPedido = ip.id	 " );
			if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
				SQL.append( " 	group by ip.idProduto) qtdEntregue,	 " );
			} else if ( !Utilidades.isNuloOuVazio( idNotaFiscalVendaFutura ) ) {
				SQL.append( " 	group by infv.idNotaEntrega) qtdEntregue,	 " );
			}

			SQL.append( " 	(SELECT sum(isr.quantidade) quantidadeEntregue FROM	 " );
			SQL.append( " 	 " + Constantes.SCHEMA + ".sistema_notasSimplesRemessa nsr,	 " );
			SQL.append( " 	 " + Constantes.SCHEMA + ".sistema_itensSimplesRemessa isr,	 " );
			SQL.append( " 	 " + Constantes.SCHEMA + ".sistema_itensNotaFiscalVenda infv,	 " );
			SQL.append( " 	 " + Constantes.SCHEMA + ".sistema_itensPedido ip	 " );
			SQL.append( " 	where 1=1	 " );
			if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
				SQL.append( " and	ip.idProduto = :idProdutoParam	 " );
			}
			if ( !Utilidades.isNuloOuVazio( idNotaFiscalVendaFutura ) ) {
				SQL.append( " 	and infv.idNotaEntrega = :idNotaFiscalVendaFuturaParam " );
			}
			SQL.append( " 	and isr.idNotaSimplesRemessa = nsr.id	 " );
			SQL.append( " 	and isr.idItemNotaFiscalVenda = infv.id	 " );
			SQL.append( " 	and infv.idItemPedido = ip.id	 " );
			if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
				SQL.append( " 	group by ip.idProduto) qtdSimpleRemessa	 " );
			} else if ( !Utilidades.isNuloOuVazio( idNotaFiscalVendaFutura ) ) {
				SQL.append( " 	group by infv.idNotaEntrega) qtdSimpleRemessa	 " );
			}

			SQL.append( " 	from dual) totais	 " );

			HashMap< String, Object > parametros = new HashMap<>();

			if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
				parametros.put( "idProdutoParam", idProduto );
			}
			if ( !Utilidades.isNuloOuVazio( idNotaFiscalVendaFutura ) ) {
				parametros.put( "idNotaFiscalVendaFuturaParam", idNotaFiscalVendaFutura );
			}

			Query query = hibernate.getSessaoCorrente().createNativeQuery( SQL.toString() );

			if ( !Utilidades.isNuloOuVazio( parametros ) ) {
				for ( String key : parametros.keySet() ) {
					query.setParameter( key, parametros.get( key ) );
				}
			}

			BigDecimal resultado = (BigDecimal) query.getSingleResult();

			hibernate.commitTransacao();

			return resultado;
		} catch ( ApplicationException e ) {
			hibernate.rollbackTransacao();
			throw e;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();
			throw new ApplicationException( "Erro inesperado[999]" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}
}
