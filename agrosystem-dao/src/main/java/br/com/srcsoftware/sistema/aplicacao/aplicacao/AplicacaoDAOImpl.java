package br.com.srcsoftware.sistema.aplicacao.aplicacao;

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
import br.com.srcsoftware.modular.manager.pessoa.funcionario.FuncionarioPO;
import br.com.srcsoftware.modular.manager.pessoa.pessoafisica.PessoaFisicaPO;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.aplicacao.item.ItemPO;
import br.com.srcsoftware.sistema.pessoa.prestadorservico.PrestadorServicoPO;
import br.com.srcsoftware.sistema.produto.marca.MarcaPO;
import br.com.srcsoftware.sistema.produto.produto.ProdutoPO;
import br.com.srcsoftware.sistema.produto.tipo.TipoPO;
import br.com.srcsoftware.sistema.produto.unidademedida.UnidadeMedidaPO;
import br.com.srcsoftware.sistema.safra.SafraPO;
import br.com.srcsoftware.sistema.safra.cultura.CulturaPO;
import br.com.srcsoftware.sistema.safra.setor.SetorPO;

public class AplicacaoDAOImpl implements AplicacaoDAOInterface{

	@Override
	public AplicacaoPO inserir( HibernateConnection hibernate, AplicacaoPO po ) throws ApplicationException {
		try {
			return (AplicacaoPO) hibernate.inserir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void alterar( HibernateConnection hibernate, AplicacaoPO po ) throws ApplicationException {
		try {
			hibernate.alterar( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void excluir( HibernateConnection hibernate, AplicacaoPO po ) throws ApplicationException {
		try {
			hibernate.excluir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public AplicacaoPO filtrarPorId( Long id ) throws ApplicationException {
		try {
			return (AplicacaoPO) new HibernateConnection().filtrarPorId( AplicacaoPO.class, id );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@SuppressWarnings( "unchecked" )
	@Override
	public List< AplicacaoPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, HashMap< String, ArrayList< Object > > camposBetween, AplicacaoPO poFilter ) throws ApplicationException {

		HibernateConnection hibernate = new HibernateConnection();
		try {
			hibernate.iniciarTransacao();

			//  CRIANDO A CRITERIA COM BASE NA CRITERIABUILDER
			CriteriaBuilder builder = hibernate.getSessaoCorrente().getCriteriaBuilder();

			//  CRIANDO A CRITERIA DE CONSULTA COM BASE NA CRITERIABUILDER
			CriteriaQuery< AplicacaoPO > criteria = builder.createQuery( AplicacaoPO.class );

			// DEFININDO O OBJETO BASE DA SELECT
			Root< AplicacaoPO > root = criteria.from( AplicacaoPO.class );

			// Definindo os parametros do WHERE
			Predicate[ ] parametros = this.montarParametros( builder, root, poFilter, camposBetween );

			// Executando A Consulta
			List< AplicacaoPO > encontrados = hibernate.filtrar( paginacao, camposOrders, builder, root, criteria, parametros, AplicacaoPO.class );

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

	private Predicate[ ] montarParametros( CriteriaBuilder builder, Root< AplicacaoPO > root, AplicacaoPO poFilter, HashMap< String, ArrayList< Object > > camposBetween ) {
		ArrayList< Predicate > lista = new ArrayList<>();

		if ( poFilter == null ) {
			return null;
		}

		lista.addAll( HibernateConnection.montarBetween( root, builder, camposBetween ) );

		if ( !Utilidades.isNuloOuVazio( poFilter.getId() ) ) {
			lista.add( builder.equal( root.get( "id" ), poFilter.getId() ) );
		} else {

			if ( !Utilidades.isNuloOuVazio( poFilter.getTipo() ) ) {
				lista.add( builder.equal( root.get( "tipo" ), poFilter.getTipo() ) );
			}

			// MONTANDO JOIN ALMOXARIFE
			if ( !Utilidades.isNuloOuVazio( poFilter.getAlmoxarife() ) ) {

				Join< AplicacaoPO, FuncionarioPO > almoxarifeJoin = null;

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

			// MONTANDO JOIN PRESTADOR
			if ( !Utilidades.isNuloOuVazio( poFilter.getPrestador() ) ) {

				Join< ItemPO, PrestadorServicoPO > prestadorJoin = null;

				if ( !Utilidades.isNuloOuVazio( poFilter.getPrestador().getId() ) ) {

					if ( prestadorJoin == null ) {
						prestadorJoin = root.join( "prestador", JoinType.INNER );
						prestadorJoin.alias( "prestador" );
					}

					lista.add( builder.equal( prestadorJoin.get( "id" ), poFilter.getPrestador().getId() ) );
				} else {

					if ( !Utilidades.isNuloOuVazio( poFilter.getPrestador().getNome() ) ) {

						if ( prestadorJoin == null ) {
							prestadorJoin = root.join( "prestador", JoinType.INNER );
							prestadorJoin.alias( "prestador" );
						}

						lista.add( builder.like( prestadorJoin.get( "nome" ), poFilter.getPrestador().getNome() + "%" ) );
					}

					if ( !Utilidades.isNuloOuVazio( poFilter.getPrestador().getTelefone() ) ) {

						if ( prestadorJoin == null ) {
							prestadorJoin = root.join( "prestador", JoinType.INNER );
							prestadorJoin.alias( "prestador" );
						}

						lista.add( builder.like( prestadorJoin.get( "telefone" ), poFilter.getPrestador().getTelefone() + "%" ) );
					}

					if ( !Utilidades.isNuloOuVazio( poFilter.getPrestador().getEmpresa() ) ) {

						if ( prestadorJoin == null ) {
							prestadorJoin = root.join( "prestador", JoinType.INNER );
							prestadorJoin.alias( "prestador" );
						}

						lista.add( builder.like( prestadorJoin.get( "empresa" ), poFilter.getPrestador().getEmpresa() + "%" ) );
					}

				}
			}

			// MONTANDO JOIN APLICADOR
			if ( !Utilidades.isNuloOuVazio( poFilter.getAplicador() ) ) {

				Join< AplicacaoPO, FuncionarioPO > aplicadorJoin = null;

				if ( !Utilidades.isNuloOuVazio( poFilter.getAplicador().getId() ) ) {

					if ( aplicadorJoin == null ) {
						aplicadorJoin = root.join( "aplicador", JoinType.INNER );
						aplicadorJoin.alias( "aplicador" );
					}

					lista.add( builder.equal( aplicadorJoin.get( "id" ), poFilter.getAplicador().getId() ) );
				} else {
					if ( ( poFilter.getAplicador().getPessoaFisica() != null ) ) {
						Join< FuncionarioPO, PessoaFisicaPO > pessoaFisicaJoin = null;

						if ( !Utilidades.isNuloOuVazio( poFilter.getAplicador().getPessoaFisica().getRazaoSocial() ) ) {

							if ( aplicadorJoin == null ) {
								aplicadorJoin = root.join( "aplicador", JoinType.INNER );
								aplicadorJoin.alias( "aplicador" );
							}
							if ( pessoaFisicaJoin == null ) {
								pessoaFisicaJoin = aplicadorJoin.join( "pessoaFisica", JoinType.INNER );
								pessoaFisicaJoin.alias( "pessoaFisica" );
							}
							lista.add( builder.like( pessoaFisicaJoin.get( "razaoSocial" ), poFilter.getAplicador().getPessoaFisica().getRazaoSocial() + "%" ) );
						}
					}
				}
			}

			// MONTANDO JOIN PRODUTO
			if ( !Utilidades.isNuloOuVazio( poFilter.getProduto() ) ) {

				Join< AplicacaoPO, ProdutoPO > produtoJoin = null;

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

					if ( !Utilidades.isNuloOuVazio( poFilter.getProduto().getNomeCompleto() ) ) {

						if ( produtoJoin == null ) {
							produtoJoin = root.join( "produto", JoinType.INNER );
							produtoJoin.alias( "produto" );
						}

						lista.add( builder.like( produtoJoin.get( "nomeCompleto" ), "%" + poFilter.getProduto().getNomeCompleto() + "%" ) );
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

			// MONTANDO JOIN SETOR
			if ( !Utilidades.isNuloOuVazio( poFilter.getSetor() ) ) {

				Join< AplicacaoPO, SetorPO > setorJoin = null;

				if ( !Utilidades.isNuloOuVazio( poFilter.getSetor().getId() ) ) {
					if ( setorJoin == null ) {
						setorJoin = root.join( "setor", JoinType.INNER );
						setorJoin.alias( "setor" );
					}
					lista.add( builder.equal( setorJoin.get( "id" ), poFilter.getSetor().getId() ) );

				} else {
					if ( !Utilidades.isNuloOuVazio( poFilter.getSetor().getNome() ) ) {

						if ( setorJoin == null ) {
							setorJoin = root.join( "setor", JoinType.INNER );
							setorJoin.alias( "setor" );
						}
						lista.add( builder.like( setorJoin.get( "nome" ), poFilter.getSetor().getNome() + "%" ) );
					}

					if ( !Utilidades.isNuloOuVazio( poFilter.getSetor().getSubSetor() ) ) {
						if ( setorJoin == null ) {
							setorJoin = root.join( "setor", JoinType.INNER );
							setorJoin.alias( "setor" );
						}
						lista.add( builder.like( setorJoin.get( "subSetor" ), poFilter.getSetor().getSubSetor() + "%" ) );
					}
				}
			}

			// MONTANDO JOIN SAFRA
			if ( !Utilidades.isNuloOuVazio( poFilter.getSafra() ) ) {

				Join< AplicacaoPO, SafraPO > safraJoin = null;

				if ( !Utilidades.isNuloOuVazio( poFilter.getSafra().getId() ) ) {
					if ( safraJoin == null ) {
						safraJoin = root.join( "safra", JoinType.INNER );
						safraJoin.alias( "safra" );
					}
					lista.add( builder.equal( safraJoin.get( "id" ), poFilter.getSafra().getId() ) );

				} else {
					if ( !Utilidades.isNuloOuVazio( poFilter.getSafra().getNome() ) ) {

						if ( safraJoin == null ) {
							safraJoin = root.join( "safra", JoinType.INNER );
							safraJoin.alias( "safra" );
						}
						lista.add( builder.like( safraJoin.get( "nome" ), poFilter.getSafra().getNome() + "%" ) );
					}
				}
			}

			// MONTANDO JOIN CULTURA
			if ( !Utilidades.isNuloOuVazio( poFilter.getCultura() ) ) {

				Join< AplicacaoPO, CulturaPO > culturaJoin = null;

				if ( !Utilidades.isNuloOuVazio( poFilter.getCultura().getId() ) ) {
					if ( culturaJoin == null ) {
						culturaJoin = root.join( "cultura", JoinType.INNER );
						culturaJoin.alias( "cultura" );
					}
					lista.add( builder.equal( culturaJoin.get( "id" ), poFilter.getCultura().getId() ) );

				} else {
					if ( !Utilidades.isNuloOuVazio( poFilter.getCultura().getNome() ) ) {

						if ( culturaJoin == null ) {
							culturaJoin = root.join( "cultura", JoinType.INNER );
							culturaJoin.alias( "cultura" );
						}
						lista.add( builder.like( culturaJoin.get( "nome" ), poFilter.getCultura().getNome() + "%" ) );
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
	public List< AplicacaoPO > filtrarParaTotais( HashMap< String, ArrayList< Object > > camposBetween, AplicacaoPO poFilter ) throws ApplicationException {

		HibernateConnection hibernate = new HibernateConnection();
		try {
			hibernate.iniciarTransacao();

			//  CRIANDO A CRITERIA COM BASE NA CRITERIABUILDER
			CriteriaBuilder builder = hibernate.getSessaoCorrente().getCriteriaBuilder();

			//  CRIANDO A CRITERIA DE CONSULTA COM BASE NA CRITERIABUILDER
			CriteriaQuery< AplicacaoPO > criteria = builder.createQuery( AplicacaoPO.class );

			// DEFININDO O OBJETO BASE DA SELECT
			Root< AplicacaoPO > root = criteria.from( AplicacaoPO.class );

			// Definindo os parametros do WHERE
			Predicate[ ] parametros = this.montarParametros( builder, root, poFilter, camposBetween );

			// Executando A Consulta
			// Deixando a Criteria preparada pra consulta
			criteria.select( root );
			// Adicionando a Clausula WHERE e AND
			if ( parametros != null ) {
				criteria.where( parametros );
			} else {
				criteria.where();
			}
			// Query
			Query query = hibernate.getSessaoCorrente().createQuery( criteria.distinct( true ) );
			// Executando A CRITERIA
			List encontrados = query.getResultList();

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
