package br.com.srcsoftware.sistema.pedido.notafiscal.venda;

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
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.pedido.itempedido.ItemPedidoPO;
import br.com.srcsoftware.sistema.pedido.pedido.PedidoPO;
import br.com.srcsoftware.sistema.pessoa.fornecedor.juridico.FornecedorJuridicoPO;
import br.com.srcsoftware.sistema.produto.produto.ProdutoPO;

public class NotaFiscalVendaDAOImpl implements NotaFiscalVendaDAOInterface{

	@Override
	public NotaFiscalVendaPO inserir( HibernateConnection hibernate, NotaFiscalVendaPO po ) throws ApplicationException {
		try {
			return (NotaFiscalVendaPO) hibernate.inserir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void alterar( HibernateConnection hibernate, NotaFiscalVendaPO po ) throws ApplicationException {
		try {
			hibernate.alterar( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void excluir( HibernateConnection hibernate, NotaFiscalVendaPO po ) throws ApplicationException {
		try {
			hibernate.excluir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public NotaFiscalVendaPO filtrarPorId( Long id ) throws ApplicationException {
		try {
			return (NotaFiscalVendaPO) new HibernateConnection().filtrarPorId( NotaFiscalVendaPO.class, id );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@SuppressWarnings( "unchecked" )
	@Override
	public List< NotaFiscalVendaPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, HashMap< String, ArrayList< Object > > camposBetween, NotaFiscalVendaPO poFilter ) throws ApplicationException {

		HibernateConnection hibernate = new HibernateConnection();
		try {
			hibernate.iniciarTransacao();

			//  CRIANDO A CRITERIA COM BASE NA CRITERIABUILDER
			CriteriaBuilder builder = hibernate.getSessaoCorrente().getCriteriaBuilder();

			//  CRIANDO A CRITERIA DE CONSULTA COM BASE NA CRITERIABUILDER
			CriteriaQuery< NotaFiscalVendaPO > criteria = builder.createQuery( NotaFiscalVendaPO.class );

			// DEFININDO O OBJETO BASE DA SELECT
			Root< NotaFiscalVendaPO > root = criteria.from( NotaFiscalVendaPO.class );

			// Definindo os parametros do WHERE
			Predicate[ ] parametros = this.montarParametros( builder, root, poFilter, camposBetween );

			// Executando A Consulta
			List< NotaFiscalVendaPO > encontrados = hibernate.filtrar( paginacao, camposOrders, builder, root, criteria, parametros, NotaFiscalVendaPO.class );

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

	private Predicate[ ] montarParametros( CriteriaBuilder builder, Root< NotaFiscalVendaPO > root, NotaFiscalVendaPO poFilter, HashMap< String, ArrayList< Object > > camposBetween ) {
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

			if ( !Utilidades.isNuloOuVazio( poFilter.getNomeUsuarioCriacao() ) ) {
				lista.add( builder.like( root.get( "nomeUsuarioCriacao" ), poFilter.getNomeUsuarioCriacao() + "%" ) );
			}

			if ( !Utilidades.isNuloOuVazio( poFilter.getTipo() ) ) {
				lista.add( builder.equal( root.get( "tipo" ), poFilter.getTipo() ) );
			}

			// MONTANDO JOIN PEDIDO
			if ( !Utilidades.isNuloOuVazio( poFilter.getPedido() ) ) {

				Join< NotaFiscalVendaPO, PedidoPO > pedidoJoin = null;

				if ( !Utilidades.isNuloOuVazio( poFilter.getPedido().getId() ) ) {
					if ( pedidoJoin == null ) {
						pedidoJoin = root.join( "pedido", JoinType.INNER );
						pedidoJoin.alias( "pedido" );
					}
					lista.add( builder.equal( pedidoJoin.get( "id" ), poFilter.getPedido().getId() ) );

				} else {
					if ( !Utilidades.isNuloOuVazio( poFilter.getPedido().getNumero() ) ) {

						if ( pedidoJoin == null ) {
							pedidoJoin = root.join( "pedido", JoinType.INNER );
							pedidoJoin.alias( "pedido" );
						}
						lista.add( builder.equal( pedidoJoin.get( "numero" ), poFilter.getPedido().getNumero() ) );
					}

					if ( !Utilidades.isNuloOuVazio( poFilter.getPedido().getStatus() ) ) {

						if ( pedidoJoin == null ) {
							pedidoJoin = root.join( "pedido", JoinType.INNER );
							pedidoJoin.alias( "pedido" );
						}
						lista.add( builder.equal( pedidoJoin.get( "status" ), poFilter.getPedido().getStatus() ) );
					}

					// MONTANDO JOIN FORNECEDOR
					if ( !Utilidades.isNuloOuVazio( poFilter.getPedido().getFornecedor() ) ) {

						Join< PedidoPO, FornecedorJuridicoPO > fornecedorJoin = null;

						if ( !Utilidades.isNuloOuVazio( poFilter.getPedido().getFornecedor().getId() ) ) {

							if ( pedidoJoin == null ) {
								pedidoJoin = root.join( "pedido", JoinType.INNER );
								pedidoJoin.alias( "pedido" );
							}

							if ( fornecedorJoin == null ) {
								fornecedorJoin = pedidoJoin.join( "fornecedor", JoinType.INNER );
								fornecedorJoin.alias( "fornecedor" );
							}
							lista.add( builder.equal( fornecedorJoin.get( "id" ), poFilter.getPedido().getFornecedor().getId() ) );

						} else {
							if ( !Utilidades.isNuloOuVazio( poFilter.getPedido().getFornecedor().getNome() ) ) {

								if ( pedidoJoin == null ) {
									pedidoJoin = root.join( "pedido", JoinType.INNER );
									pedidoJoin.alias( "pedido" );
								}

								if ( fornecedorJoin == null ) {
									fornecedorJoin = pedidoJoin.join( "fornecedor", JoinType.INNER );
									fornecedorJoin.alias( "fornecedor" );
								}
								lista.add( builder.like( fornecedorJoin.get( "nome" ), poFilter.getPedido().getFornecedor().getNome() + "%" ) );
							}
						}
					}
				}
			}

			// MONTANDO JOIN ITENS
			if ( !Utilidades.isNuloOuVazio( poFilter.getItens() ) ) {
				Join< NotaFiscalVendaPO, ItemNotaFiscalVendaPO > itemJoinList = null;

				ItemNotaFiscalVendaPO itemFiltrar = poFilter.getItens().iterator().next();

				if ( !Utilidades.isNuloOuVazio( itemFiltrar.getId() ) ) {
					if ( itemJoinList == null ) {
						itemJoinList = root.joinSet( "itens", JoinType.LEFT );
						itemJoinList.alias( "item" );
					}
					lista.add( builder.equal( itemJoinList.get( "id" ), itemFiltrar.getId() ) );
				} else {

					// MONTANDO JOIN PRODUTO
					if ( !Utilidades.isNuloOuVazio( itemFiltrar.getItemPedido() ) ) {

						Join< ItemNotaFiscalVendaPO, ItemPedidoPO > itemPedidoJoin = null;

						if ( !Utilidades.isNuloOuVazio( itemFiltrar.getItemPedido().getId() ) ) {

							if ( itemJoinList == null ) {
								itemJoinList = root.joinSet( "itens", JoinType.LEFT );
								itemJoinList.alias( "item" );
							}
							if ( itemPedidoJoin == null ) {
								itemPedidoJoin = itemJoinList.join( "itemPedido", JoinType.INNER );
								itemPedidoJoin.alias( "itemPedido" );
							}

							lista.add( builder.equal( itemPedidoJoin.get( "id" ), itemFiltrar.getItemPedido().getId() ) );
						} else {
							if ( !Utilidades.isNuloOuVazio( itemFiltrar.getItemPedido().getProduto() ) ) {
								Join< ItemPedidoPO, ProdutoPO > produtoJoin = null;

								if ( !Utilidades.isNuloOuVazio( itemFiltrar.getItemPedido().getProduto().getId() ) ) {
									if ( itemJoinList == null ) {
										itemJoinList = root.joinSet( "itens", JoinType.LEFT );
										itemJoinList.alias( "item" );
									}
									if ( itemPedidoJoin == null ) {
										itemPedidoJoin = itemJoinList.join( "itemPedido", JoinType.INNER );
										itemPedidoJoin.alias( "itemPedido" );
									}
									if ( produtoJoin == null ) {
										produtoJoin = itemPedidoJoin.join( "produto", JoinType.INNER );
										produtoJoin.alias( "produto" );
									}

									lista.add( builder.equal( produtoJoin.get( "id" ), itemFiltrar.getItemPedido().getProduto().getId() ) );
								} else {
									if ( !Utilidades.isNuloOuVazio( itemFiltrar.getItemPedido().getProduto().getNomeCompleto() ) ) {
										if ( itemJoinList == null ) {
											itemJoinList = root.joinSet( "itens", JoinType.LEFT );
											itemJoinList.alias( "item" );
										}
										if ( itemPedidoJoin == null ) {
											itemPedidoJoin = itemJoinList.join( "itemPedido", JoinType.INNER );
											itemPedidoJoin.alias( "itemPedido" );
										}
										if ( produtoJoin == null ) {
											produtoJoin = itemPedidoJoin.join( "produto", JoinType.INNER );
											produtoJoin.alias( "produto" );
										}

										lista.add( builder.like( produtoJoin.get( "nomeCompleto" ), "%".concat( itemFiltrar.getItemPedido().getProduto().getNomeCompleto().concat( "%" ) ) ) );
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
	public List< NotaFiscalVendaPO > filtrarAbertosAndamentos( String numeroNF, String tipoNF, HashMap< String, String > camposOrders ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();
		try {
			hibernate.iniciarTransacao();

			final StringBuffer HQL = new StringBuffer();
			HQL.append( " SELECT notaFiscalVenda FROM " );
			HQL.append( " NotaFiscalVendaPO notaFiscalVenda " );
			HQL.append( " WHERE 1=1" );
			HQL.append( " and notaFiscalVenda.status in (:statusParam) " );
			HQL.append( " and notaFiscalVenda.tipo = :tipoParam " );

			HashMap< String, Object > parametros = new HashMap<>();

			if ( !Utilidades.isNuloOuVazio( numeroNF ) ) {
				HQL.append( " and notaFiscalVenda.numero like :numeroParam " );
				parametros.put( "numeroParam", numeroNF + "%" );
			}

			parametros.put( "statusParam", new ArrayList<>( Arrays.asList( "ABERTO", "ANDAMENTO" ) ) );
			parametros.put( "tipoParam", tipoNF );

			Query query = hibernate.getSessaoCorrente().createQuery( HQL.toString() );

			for ( String key : parametros.keySet() ) {
				query.setParameter( key, parametros.get( key ) );
			}

			List< NotaFiscalVendaPO > encontrados = query.getResultList();

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
}
