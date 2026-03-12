package br.com.srcsoftware.sistema.silo.entrada;

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
import br.com.srcsoftware.sistema.safra.SafraPO;
import br.com.srcsoftware.sistema.safra.cultura.CulturaPO;
import br.com.srcsoftware.sistema.safra.cultura.variedade.VariedadePO;
import br.com.srcsoftware.sistema.safra.setor.SetorPO;

public final class EntradaGraoDAOImpl implements EntradaGraoDAOInterface{

	@Override
	public EntradaGraoPO inserir( HibernateConnection hibernate, EntradaGraoPO po ) throws ApplicationException {
		try {
			return (EntradaGraoPO) hibernate.inserir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void alterar( HibernateConnection hibernate, EntradaGraoPO po ) throws ApplicationException {
		try {
			hibernate.alterar( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void excluir( HibernateConnection hibernate, EntradaGraoPO po ) throws ApplicationException {
		try {
			hibernate.excluir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public EntradaGraoPO filtrarPorId( Long id ) throws ApplicationException {
		try {
			return (EntradaGraoPO) new HibernateConnection().filtrarPorId( EntradaGraoPO.class, id );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@SuppressWarnings( "unchecked" )
	@Override
	public List< EntradaGraoPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, HashMap< String, ArrayList< Object > > camposBetween, EntradaGraoPO poFilter ) throws ApplicationException {

		HibernateConnection hibernate = new HibernateConnection();
		try {
			hibernate.iniciarTransacao();

			//  CRIANDO A CRITERIA COM BASE NA CRITERIABUILDER
			CriteriaBuilder builder = hibernate.getSessaoCorrente().getCriteriaBuilder();

			//  CRIANDO A CRITERIA DE CONSULTA COM BASE NA CRITERIABUILDER
			CriteriaQuery< EntradaGraoPO > criteria = builder.createQuery( EntradaGraoPO.class );

			// DEFININDO O OBJETO BASE DA SELECT
			Root< EntradaGraoPO > root = criteria.from( EntradaGraoPO.class );

			// Definindo os parametros do WHERE
			Predicate[ ] parametros = this.montarParametros( builder, root, poFilter, camposBetween );

			// Executando A Consulta
			List< EntradaGraoPO > encontrados = hibernate.filtrar( paginacao, camposOrders, builder, root, criteria, parametros, EntradaGraoPO.class );

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

	private Predicate[ ] montarParametros( CriteriaBuilder builder, Root< EntradaGraoPO > root, EntradaGraoPO poFilter, HashMap< String, ArrayList< Object > > camposBetween ) {
		ArrayList< Predicate > lista = new ArrayList<>();

		if ( poFilter == null ) {
			return null;
		}

		lista.addAll( HibernateConnection.montarBetween( root, builder, camposBetween ) );

		if ( !Utilidades.isNuloOuVazio( poFilter.getId() ) ) {
			lista.add( builder.equal( root.get( "id" ), poFilter.getId() ) );
		} else {

			if ( !Utilidades.isNuloOuVazio( poFilter.getData() ) ) {
				lista.add( builder.equal( root.get( "data" ), poFilter.getData() ) );
			}

			// MONTANDO JOIN ESTOQUISTA
			if ( !Utilidades.isNuloOuVazio( poFilter.getEstoquista() ) ) {

				Join< EntradaGraoPO, FuncionarioPO > estoquistaJoin = null;

				if ( !Utilidades.isNuloOuVazio( poFilter.getEstoquista().getId() ) ) {

					if ( estoquistaJoin == null ) {
						estoquistaJoin = root.join( "estoquista", JoinType.INNER );
						estoquistaJoin.alias( "estoquista" );
					}

					lista.add( builder.equal( estoquistaJoin.get( "id" ), poFilter.getEstoquista().getId() ) );
				} else {
					if ( ( poFilter.getEstoquista().getPessoaFisica() != null ) ) {
						Join< FuncionarioPO, PessoaFisicaPO > pessoaFisicaJoin = null;

						if ( !Utilidades.isNuloOuVazio( poFilter.getEstoquista().getPessoaFisica().getRazaoSocial() ) ) {

							if ( estoquistaJoin == null ) {
								estoquistaJoin = root.join( "estoquista", JoinType.INNER );
								estoquistaJoin.alias( "estoquista" );
							}
							if ( pessoaFisicaJoin == null ) {
								pessoaFisicaJoin = estoquistaJoin.join( "pessoaFisica", JoinType.INNER );
								pessoaFisicaJoin.alias( "pessoaFisica" );
							}
							lista.add( builder.like( pessoaFisicaJoin.get( "razaoSocial" ), poFilter.getEstoquista().getPessoaFisica().getRazaoSocial() + "%" ) );
						}
					}
				}
			}

			// MONTANDO JOIN SETOR
			if ( !Utilidades.isNuloOuVazio( poFilter.getSetor() ) ) {

				Join< EntradaGraoPO, SetorPO > setorJoin = null;

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

				Join< EntradaGraoPO, SafraPO > safraJoin = null;

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
			if ( !Utilidades.isNuloOuVazio( poFilter.getVariedade() ) ) {

				Join< EntradaGraoPO, VariedadePO > variedadeJoin = null;

				if ( !Utilidades.isNuloOuVazio( poFilter.getVariedade().getId() ) ) {
					if ( variedadeJoin == null ) {
						variedadeJoin = root.join( "variedade", JoinType.INNER );
						variedadeJoin.alias( "variedade" );
					}
					lista.add( builder.equal( variedadeJoin.get( "id" ), poFilter.getVariedade().getId() ) );

				} else {
					if ( !Utilidades.isNuloOuVazio( poFilter.getVariedade().getNome() ) ) {

						if ( variedadeJoin == null ) {
							variedadeJoin = root.join( "variedade", JoinType.INNER );
							variedadeJoin.alias( "variedade" );
						}
						lista.add( builder.like( variedadeJoin.get( "nome" ), poFilter.getVariedade().getNome() + "%" ) );
					}

					if ( !Utilidades.isNuloOuVazio( poFilter.getVariedade().getCultura() ) ) {
						if ( !Utilidades.isNuloOuVazio( poFilter.getVariedade().getCultura().getId() ) ) {
							Join< VariedadePO, CulturaPO > culturaJoin = null;

							if ( variedadeJoin == null ) {
								variedadeJoin = root.join( "variedade", JoinType.INNER );
								variedadeJoin.alias( "variedade" );
							}
							if ( culturaJoin == null ) {
								culturaJoin = variedadeJoin.join( "cultura", JoinType.INNER );
								culturaJoin.alias( "cultura" );
							}
							lista.add( builder.equal( culturaJoin.get( "id" ), poFilter.getVariedade().getCultura().getId() ) );

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