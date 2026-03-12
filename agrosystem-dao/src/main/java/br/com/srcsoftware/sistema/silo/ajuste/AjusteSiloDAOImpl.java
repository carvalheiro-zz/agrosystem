package br.com.srcsoftware.sistema.silo.ajuste;

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
import br.com.srcsoftware.sistema.silo.entrada.EntradaGraoPO;
import br.com.srcsoftware.sistema.silo.silo.SiloPO;

public final class AjusteSiloDAOImpl implements AjusteSiloDAOInterface{

	@Override
	public AjusteSiloPO inserir( HibernateConnection hibernate, AjusteSiloPO po ) throws ApplicationException {
		try {
			return (AjusteSiloPO) hibernate.inserir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void alterar( HibernateConnection hibernate, AjusteSiloPO po ) throws ApplicationException {
		try {
			hibernate.alterar( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void excluir( HibernateConnection hibernate, AjusteSiloPO po ) throws ApplicationException {
		try {
			hibernate.excluir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public AjusteSiloPO filtrarPorId( Long id ) throws ApplicationException {
		try {
			return (AjusteSiloPO) new HibernateConnection().filtrarPorId( AjusteSiloPO.class, id );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@SuppressWarnings( "unchecked" )
	@Override
	public List< AjusteSiloPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, HashMap< String, ArrayList< Object > > camposBetween, AjusteSiloPO poFilter ) throws ApplicationException {

		HibernateConnection hibernate = new HibernateConnection();
		try {
			hibernate.iniciarTransacao();

			//  CRIANDO A CRITERIA COM BASE NA CRITERIABUILDER
			CriteriaBuilder builder = hibernate.getSessaoCorrente().getCriteriaBuilder();

			//  CRIANDO A CRITERIA DE CONSULTA COM BASE NA CRITERIABUILDER
			CriteriaQuery< AjusteSiloPO > criteria = builder.createQuery( AjusteSiloPO.class );

			// DEFININDO O OBJETO BASE DA SELECT
			Root< AjusteSiloPO > root = criteria.from( AjusteSiloPO.class );

			// Definindo os parametros do WHERE
			Predicate[ ] parametros = this.montarParametros( builder, root, poFilter, camposBetween );

			// Executando A Consulta
			List< AjusteSiloPO > encontrados = hibernate.filtrar( paginacao, camposOrders, builder, root, criteria, parametros, AjusteSiloPO.class );

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

	private Predicate[ ] montarParametros( CriteriaBuilder builder, Root< AjusteSiloPO > root, AjusteSiloPO poFilter, HashMap< String, ArrayList< Object > > camposBetween ) {
		ArrayList< Predicate > lista = new ArrayList<>();

		if ( poFilter == null ) {
			return null;
		}

		lista.addAll( HibernateConnection.montarBetween( root, builder, camposBetween ) );

		if ( !Utilidades.isNuloOuVazio( poFilter.getId() ) ) {
			lista.add( builder.equal( root.get( "id" ), poFilter.getId() ) );
		} else {

			if ( !Utilidades.isNuloOuVazio( poFilter.getTipo() ) ) {
				lista.add( builder.like( root.get( "tipo" ), poFilter.getTipo() ) );
			}

			// MONTANDO JOIN SAFRA
			if ( !Utilidades.isNuloOuVazio( poFilter.getSafra() ) ) {

				Join< AjusteSiloPO, SafraPO > safraJoin = null;

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

				Join< AjusteSiloPO, CulturaPO > culturaJoin = null;

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

			// MONTANDO JOIN FUNCIONARIO
			if ( !Utilidades.isNuloOuVazio( poFilter.getLancador() ) ) {

				Join< EntradaGraoPO, FuncionarioPO > lancadorJoin = null;

				if ( !Utilidades.isNuloOuVazio( poFilter.getLancador().getId() ) ) {

					if ( lancadorJoin == null ) {
						lancadorJoin = root.join( "lancador", JoinType.INNER );
						lancadorJoin.alias( "lancador" );
					}

					lista.add( builder.equal( lancadorJoin.get( "id" ), poFilter.getLancador().getId() ) );
				} else {
					if ( ( poFilter.getLancador().getPessoaFisica() != null ) ) {
						Join< FuncionarioPO, PessoaFisicaPO > pessoaFisicaJoin = null;

						if ( !Utilidades.isNuloOuVazio( poFilter.getLancador().getPessoaFisica().getRazaoSocial() ) ) {

							if ( lancadorJoin == null ) {
								lancadorJoin = root.join( "lancador", JoinType.INNER );
								lancadorJoin.alias( "lancador" );
							}
							if ( pessoaFisicaJoin == null ) {
								pessoaFisicaJoin = lancadorJoin.join( "pessoaFisica", JoinType.INNER );
								pessoaFisicaJoin.alias( "pessoaFisica" );
							}
							lista.add( builder.like( pessoaFisicaJoin.get( "razaoSocial" ), poFilter.getLancador().getPessoaFisica().getRazaoSocial() + "%" ) );
						}
					}
				}
			}

			if ( !Utilidades.isNuloOuVazio( poFilter.getLocalArmazenagem() ) ) {

				Join< AjusteSiloPO, SiloPO > localArmazenagemJoin = null;

				if ( !Utilidades.isNuloOuVazio( poFilter.getLocalArmazenagem().getId() ) ) {
					if ( localArmazenagemJoin == null ) {
						localArmazenagemJoin = root.join( "localArmazenagem", JoinType.INNER );
						localArmazenagemJoin.alias( "localArmazenagem" );
					}
					lista.add( builder.equal( localArmazenagemJoin.get( "id" ), poFilter.getLocalArmazenagem().getId() ) );
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