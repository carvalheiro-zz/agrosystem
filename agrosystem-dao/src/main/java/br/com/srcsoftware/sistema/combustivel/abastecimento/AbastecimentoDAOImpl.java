package br.com.srcsoftware.sistema.combustivel.abastecimento;

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
import br.com.srcsoftware.modular.manager.pessoa.funcionario.FuncionarioPO;
import br.com.srcsoftware.modular.manager.pessoa.pessoafisica.PessoaFisicaPO;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.manutencao.veiculo.VeiculoPO;
import br.com.srcsoftware.sistema.produto.marca.MarcaPO;
import br.com.srcsoftware.sistema.produto.produto.ProdutoPO;
import br.com.srcsoftware.sistema.produto.tipo.TipoPO;
import br.com.srcsoftware.sistema.produto.unidademedida.UnidadeMedidaPO;

public class AbastecimentoDAOImpl implements AbastecimentoDAOInterface{

	@Override
	public AbastecimentoPO inserir( HibernateConnection hibernate, AbastecimentoPO po ) throws ApplicationException {
		try {
			return (AbastecimentoPO) hibernate.inserir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void alterar( HibernateConnection hibernate, AbastecimentoPO po ) throws ApplicationException {
		try {
			hibernate.alterar( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void excluir( HibernateConnection hibernate, AbastecimentoPO po ) throws ApplicationException {
		try {
			hibernate.excluir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public AbastecimentoPO filtrarPorId( Long id ) throws ApplicationException {
		try {
			return (AbastecimentoPO) new HibernateConnection().filtrarPorId( AbastecimentoPO.class, id );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@SuppressWarnings( "unchecked" )
	@Override
	public List< AbastecimentoPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, HashMap< String, ArrayList< Object > > camposBetween, AbastecimentoPO poFilter ) throws ApplicationException {

		HibernateConnection hibernate = new HibernateConnection();
		try {
			hibernate.iniciarTransacao();

			//  CRIANDO A CRITERIA COM BASE NA CRITERIABUILDER
			CriteriaBuilder builder = hibernate.getSessaoCorrente().getCriteriaBuilder();

			//  CRIANDO A CRITERIA DE CONSULTA COM BASE NA CRITERIABUILDER
			CriteriaQuery< AbastecimentoPO > criteria = builder.createQuery( AbastecimentoPO.class );

			// DEFININDO O OBJETO BASE DA SELECT
			Root< AbastecimentoPO > root = criteria.from( AbastecimentoPO.class );

			// Definindo os parametros do WHERE
			Predicate[ ] parametros = this.montarParametros( builder, root, poFilter, camposBetween );

			// Executando A Consulta
			List< AbastecimentoPO > encontrados = hibernate.filtrar( paginacao, camposOrders, builder, root, criteria, parametros, AbastecimentoPO.class );

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

	private Predicate[ ] montarParametros( CriteriaBuilder builder, Root< AbastecimentoPO > root, AbastecimentoPO poFilter, HashMap< String, ArrayList< Object > > camposBetween ) {
		ArrayList< Predicate > lista = new ArrayList<>();

		if ( poFilter == null ) {
			return null;
		}

		lista.addAll( HibernateConnection.montarBetween( root, builder, camposBetween ) );

		if ( !Utilidades.isNuloOuVazio( poFilter.getId() ) ) {
			lista.add( builder.equal( root.get( "id" ), poFilter.getId() ) );
		} else {

			// MONTANDO JOIN ALMOXARIFE
			if ( !Utilidades.isNuloOuVazio( poFilter.getAlmoxarife() ) ) {

				Join< AbastecimentoPO, FuncionarioPO > almoxarifeJoin = null;

				if ( !Utilidades.isNuloOuVazio( poFilter.getAlmoxarife().getId() ) ) {

					if ( almoxarifeJoin == null ) {
						almoxarifeJoin = root.join( "almoxarife", JoinType.INNER );
						almoxarifeJoin.alias( "almoxarife" );
					}

					lista.add( builder.equal( almoxarifeJoin.get( "id" ), poFilter.getAlmoxarife().getId() ) );
				} else {
					if ( ( poFilter.getAlmoxarife().getPessoaFisica() != null ) ) {
						Join< FuncionarioPO, PessoaFisicaPO > pessoaFisicaJoin = null;

						if ( !Utilidades.isNuloOuVazio( poFilter.getAlmoxarife().getPessoaFisica().getRazaoSocial() ) ) {

							if ( almoxarifeJoin == null ) {
								almoxarifeJoin = root.join( "almoxarife", JoinType.INNER );
								almoxarifeJoin.alias( "almoxarife" );
							}
							if ( pessoaFisicaJoin == null ) {
								pessoaFisicaJoin = almoxarifeJoin.join( "pessoaFisica", JoinType.INNER );
								pessoaFisicaJoin.alias( "pessoaFisica" );
							}
							lista.add( builder.like( pessoaFisicaJoin.get( "razaoSocial" ), poFilter.getAlmoxarife().getPessoaFisica().getRazaoSocial() + "%" ) );
						}
					}
				}
			}

			// MONTANDO JOIN REQUERENTE
			if ( !Utilidades.isNuloOuVazio( poFilter.getRequerente() ) ) {

				Join< AbastecimentoPO, FuncionarioPO > requerenteJoin = null;

				if ( !Utilidades.isNuloOuVazio( poFilter.getRequerente().getId() ) ) {

					if ( requerenteJoin == null ) {
						requerenteJoin = root.join( "requerente", JoinType.INNER );
						requerenteJoin.alias( "requerente" );
					}

					lista.add( builder.equal( requerenteJoin.get( "id" ), poFilter.getRequerente().getId() ) );
				} else {
					if ( ( poFilter.getRequerente().getPessoaFisica() != null ) ) {
						Join< FuncionarioPO, PessoaFisicaPO > pessoaFisicaJoin = null;

						if ( !Utilidades.isNuloOuVazio( poFilter.getRequerente().getPessoaFisica().getRazaoSocial() ) ) {

							if ( requerenteJoin == null ) {
								requerenteJoin = root.join( "requerente", JoinType.INNER );
								requerenteJoin.alias( "requerente" );
							}
							if ( pessoaFisicaJoin == null ) {
								pessoaFisicaJoin = requerenteJoin.join( "pessoaFisica", JoinType.INNER );
								pessoaFisicaJoin.alias( "pessoaFisica" );
							}
							lista.add( builder.like( pessoaFisicaJoin.get( "razaoSocial" ), poFilter.getRequerente().getPessoaFisica().getRazaoSocial() + "%" ) );
						}
					}
				}
			}

			// MONTANDO JOIN PRODUTO
			if ( !Utilidades.isNuloOuVazio( poFilter.getProduto() ) ) {

				Join< AbastecimentoPO, ProdutoPO > produtoJoin = null;

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

					// MONTANDO JOIN TIPO
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

			// MONTANDO JOIN VEICULO
			if ( !Utilidades.isNuloOuVazio( poFilter.getVeiculo() ) ) {

				Join< AbastecimentoPO, VeiculoPO > veiculoJoin = null;

				if ( !Utilidades.isNuloOuVazio( poFilter.getVeiculo().getId() ) ) {
					if ( veiculoJoin == null ) {
						veiculoJoin = root.join( "veiculo", JoinType.INNER );
						veiculoJoin.alias( "veiculo" );
					}
					lista.add( builder.equal( veiculoJoin.get( "id" ), poFilter.getVeiculo().getId() ) );

				} else {
					if ( !Utilidades.isNuloOuVazio( poFilter.getVeiculo().getCodigo() ) ) {

						if ( veiculoJoin == null ) {
							veiculoJoin = root.join( "veiculo", JoinType.INNER );
							veiculoJoin.alias( "veiculo" );
						}
						lista.add( builder.equal( veiculoJoin.get( "codigo" ), poFilter.getVeiculo().getCodigo() ) );
					}

					if ( !Utilidades.isNuloOuVazio( poFilter.getVeiculo().getNome() ) ) {

						if ( veiculoJoin == null ) {
							veiculoJoin = root.join( "veiculo", JoinType.INNER );
							veiculoJoin.alias( "veiculo" );
						}
						lista.add( builder.like( veiculoJoin.get( "nome" ), poFilter.getVeiculo().getNome() + "%" ) );
					}
					if ( !Utilidades.isNuloOuVazio( poFilter.getVeiculo().getNomeCompleto() ) ) {

						if ( veiculoJoin == null ) {
							veiculoJoin = root.join( "veiculo", JoinType.INNER );
							veiculoJoin.alias( "veiculo" );
						}
						lista.add( builder.like( veiculoJoin.get( "nomeCompleto" ), "%" + poFilter.getVeiculo().getNomeCompleto() + "%" ) );
					}

					if ( !Utilidades.isNuloOuVazio( poFilter.getVeiculo().getTipo() ) ) {

						if ( veiculoJoin == null ) {
							veiculoJoin = root.join( "veiculo", JoinType.INNER );
							veiculoJoin.alias( "veiculo" );
						}
						lista.add( builder.like( veiculoJoin.get( "tipo" ), poFilter.getVeiculo().getTipo() + "%" ) );
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
