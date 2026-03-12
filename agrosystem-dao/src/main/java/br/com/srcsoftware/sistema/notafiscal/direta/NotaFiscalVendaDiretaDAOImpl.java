package br.com.srcsoftware.sistema.notafiscal.direta;

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
import br.com.srcsoftware.modular.manager.pessoa.fornecedor.FornecedorPO;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.notafiscal.direta.item.ItemNotaFiscalVendaDiretaPO;
import br.com.srcsoftware.sistema.produto.marca.MarcaPO;
import br.com.srcsoftware.sistema.produto.produto.ProdutoPO;
import br.com.srcsoftware.sistema.produto.tipo.TipoPO;
import br.com.srcsoftware.sistema.produto.unidademedida.UnidadeMedidaPO;

public class NotaFiscalVendaDiretaDAOImpl implements NotaFiscalVendaDiretaDAOInterface{

	@Override
	public NotaFiscalVendaDiretaPO inserir( HibernateConnection hibernate, NotaFiscalVendaDiretaPO po ) throws ApplicationException {
		try {
			return (NotaFiscalVendaDiretaPO) hibernate.inserir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void alterar( HibernateConnection hibernate, NotaFiscalVendaDiretaPO po ) throws ApplicationException {
		try {
			hibernate.alterar( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void excluir( HibernateConnection hibernate, NotaFiscalVendaDiretaPO po ) throws ApplicationException {
		try {
			hibernate.excluir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public NotaFiscalVendaDiretaPO filtrarPorId( Long id ) throws ApplicationException {
		try {
			return (NotaFiscalVendaDiretaPO) new HibernateConnection().filtrarPorId( NotaFiscalVendaDiretaPO.class, id );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@SuppressWarnings( "unchecked" )
	@Override
	public List< NotaFiscalVendaDiretaPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, HashMap< String, ArrayList< Object > > camposBetween, NotaFiscalVendaDiretaPO poFilter ) throws ApplicationException {

		HibernateConnection hibernate = new HibernateConnection();
		try {
			hibernate.iniciarTransacao();

			//  CRIANDO A CRITERIA COM BASE NA CRITERIABUILDER
			CriteriaBuilder builder = hibernate.getSessaoCorrente().getCriteriaBuilder();

			//  CRIANDO A CRITERIA DE CONSULTA COM BASE NA CRITERIABUILDER
			CriteriaQuery< NotaFiscalVendaDiretaPO > criteria = builder.createQuery( NotaFiscalVendaDiretaPO.class );

			// DEFININDO O OBJETO BASE DA SELECT
			Root< NotaFiscalVendaDiretaPO > root = criteria.from( NotaFiscalVendaDiretaPO.class );

			// Definindo os parametros do WHERE
			Predicate[ ] parametros = this.montarParametros( builder, root, poFilter, camposBetween );

			// Executando A Consulta
			List< NotaFiscalVendaDiretaPO > encontrados = hibernate.filtrar( paginacao, camposOrders, builder, root, criteria, parametros, NotaFiscalVendaDiretaPO.class );

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

	private Predicate[ ] montarParametros( CriteriaBuilder builder, Root< NotaFiscalVendaDiretaPO > root, NotaFiscalVendaDiretaPO poFilter, HashMap< String, ArrayList< Object > > camposBetween ) {
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

			if ( !Utilidades.isNuloOuVazio( poFilter.getNumeroRecibo() ) ) {
				lista.add( builder.like( root.get( "numeroRecibo" ), poFilter.getNumeroRecibo() + "%" ) );
			}

			if ( !Utilidades.isNuloOuVazio( poFilter.getStatus() ) ) {
				lista.add( builder.equal( root.get( "status" ), poFilter.getStatus() ) );
			}

			// MONTANDO JOIN FORNECEDOR
			if ( !Utilidades.isNuloOuVazio( poFilter.getFornecedor() ) ) {

				Join< NotaFiscalVendaDiretaPO, FornecedorPO > fornecedorJoin = null;

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
				Join< NotaFiscalVendaDiretaPO, ItemNotaFiscalVendaDiretaPO > itemJoinList = null;

				ItemNotaFiscalVendaDiretaPO itemFiltrar = poFilter.getItens().iterator().next();

				if ( !Utilidades.isNuloOuVazio( itemFiltrar.getId() ) ) {
					if ( itemJoinList == null ) {
						itemJoinList = root.joinSet( "itens", JoinType.LEFT );
						itemJoinList.alias( "item" );
					}
					lista.add( builder.equal( itemJoinList.get( "id" ), itemFiltrar.getId() ) );
				} else {

					// MONTANDO JOIN PRODUTO
					if ( !Utilidades.isNuloOuVazio( itemFiltrar.getProduto() ) ) {

						Join< ItemNotaFiscalVendaDiretaPO, ProdutoPO > produtoJoin = null;

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

}
