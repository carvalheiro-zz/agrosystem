package br.com.srcsoftware.sistema.produto.produto;

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
import br.com.srcsoftware.sistema.produto.marca.MarcaPO;
import br.com.srcsoftware.sistema.produto.tipo.TipoPO;
import br.com.srcsoftware.sistema.produto.unidademedida.UnidadeMedidaPO;

public class ProdutoDAOImpl implements ProdutoDAOInterface{

	@Override
	public ProdutoPO inserir( HibernateConnection hibernate, ProdutoPO po ) throws ApplicationException {
		try {
			return (ProdutoPO) hibernate.inserir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void alterar( HibernateConnection hibernate, ProdutoPO po ) throws ApplicationException {
		try {
			hibernate.alterar( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void excluir( HibernateConnection hibernate, ProdutoPO po ) throws ApplicationException {
		try {
			hibernate.excluir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public ProdutoPO filtrarPorId( Long id ) throws ApplicationException {
		try {
			return (ProdutoPO) new HibernateConnection().filtrarPorId( ProdutoPO.class, id );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@SuppressWarnings( "unchecked" )
	@Override
	public List< ProdutoPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, ProdutoPO poFilter ) throws ApplicationException {

		HibernateConnection hibernate = new HibernateConnection();
		try {
			hibernate.iniciarTransacao();

			//  CRIANDO A CRITERIA COM BASE NA CRITERIABUILDER
			CriteriaBuilder builder = hibernate.getSessaoCorrente().getCriteriaBuilder();

			//  CRIANDO A CRITERIA DE CONSULTA COM BASE NA CRITERIABUILDER
			CriteriaQuery< ProdutoPO > criteria = builder.createQuery( ProdutoPO.class );

			// DEFININDO O OBJETO BASE DA SELECT
			Root< ProdutoPO > root = criteria.from( ProdutoPO.class );

			// Definindo os parametros do WHERE
			Predicate[ ] parametros = this.montarParametros( builder, root, poFilter );

			// Executando A Consulta
			List< ProdutoPO > encontrados = hibernate.filtrar( paginacao, camposOrders, builder, root, criteria, parametros, ProdutoPO.class );

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

	private Predicate[ ] montarParametros( CriteriaBuilder builder, Root< ProdutoPO > root, ProdutoPO poFilter ) {
		ArrayList< Predicate > lista = new ArrayList<>();

		if ( poFilter == null ) {
			return null;
		}
		if ( !Utilidades.isNuloOuVazio( poFilter.getId() ) ) {
			lista.add( builder.equal( root.get( "id" ), poFilter.getId() ) );
		} else {
			if ( !Utilidades.isNuloOuVazio( poFilter.getNome() ) ) {
				lista.add( builder.like( root.get( "nome" ), poFilter.getNome() + "%" ) );
			}

			if ( !Utilidades.isNuloOuVazio( poFilter.getLocalizacao() ) ) {
				lista.add( builder.equal( root.get( "localizacao" ), poFilter.getLocalizacao() ) );
			}

			if ( !Utilidades.isNuloOuVazio( poFilter.getNomeCompleto() ) ) {
				lista.add( builder.like( root.get( "nomeCompleto" ), "%".concat( poFilter.getNomeCompleto() ).concat( "%" ) ) );
			}

			// MONTANDO JOIN TIPO
			if ( !Utilidades.isNuloOuVazio( poFilter.getTipo() ) ) {

				Join< ProdutoPO, TipoPO > tipoJoin = null;

				if ( !Utilidades.isNuloOuVazio( poFilter.getTipo().getId() ) ) {

					if ( tipoJoin == null ) {
						tipoJoin = root.join( "tipo", JoinType.INNER );
						tipoJoin.alias( "tipo" );
					}

					lista.add( builder.equal( tipoJoin.get( "id" ), poFilter.getTipo().getId() ) );
				} else {
					if ( !Utilidades.isNuloOuVazio( poFilter.getTipo().getNome() ) ) {

						if ( tipoJoin == null ) {
							tipoJoin = root.join( "tipo", JoinType.INNER );
							tipoJoin.alias( "tipo" );
						}
						lista.add( builder.equal( tipoJoin.get( "nome" ), poFilter.getTipo().getNome() ) );
					}

					if ( !Utilidades.isNuloOuVazio( poFilter.getTipo().getClassificacao() ) ) {

						if ( tipoJoin == null ) {
							tipoJoin = root.join( "tipo", JoinType.INNER );
							tipoJoin.alias( "tipo" );
						}
						lista.add( builder.equal( tipoJoin.get( "classificacao" ), poFilter.getTipo().getClassificacao() ) );
					}

				}
			}

			// MONTANDO JOIN MARCA
			if ( !Utilidades.isNuloOuVazio( poFilter.getMarca() ) ) {

				Join< ProdutoPO, MarcaPO > marcaJoin = null;

				if ( !Utilidades.isNuloOuVazio( poFilter.getMarca().getId() ) ) {

					if ( marcaJoin == null ) {
						marcaJoin = root.join( "marca", JoinType.INNER );
						marcaJoin.alias( "marca" );
					}

					lista.add( builder.equal( marcaJoin.get( "id" ), poFilter.getMarca().getId() ) );
				} else {
					if ( !Utilidades.isNuloOuVazio( poFilter.getMarca().getNome() ) ) {

						if ( marcaJoin == null ) {
							marcaJoin = root.join( "marca", JoinType.INNER );
							marcaJoin.alias( "marca" );
						}
						lista.add( builder.equal( marcaJoin.get( "nome" ), poFilter.getMarca().getNome() ) );
					}
				}
			}

			// MONTANDO JOIN TIPO
			if ( !Utilidades.isNuloOuVazio( poFilter.getUnidadeMedida() ) ) {

				Join< ProdutoPO, UnidadeMedidaPO > unidadeMedidaJoin = null;

				if ( !Utilidades.isNuloOuVazio( poFilter.getUnidadeMedida().getId() ) ) {

					if ( unidadeMedidaJoin == null ) {
						unidadeMedidaJoin = root.join( "unidadeMedida", JoinType.INNER );
						unidadeMedidaJoin.alias( "unidadeMedida" );
					}

					lista.add( builder.equal( unidadeMedidaJoin.get( "id" ), poFilter.getUnidadeMedida().getId() ) );
				} else {
					if ( !Utilidades.isNuloOuVazio( poFilter.getUnidadeMedida().getNome() ) ) {

						if ( unidadeMedidaJoin == null ) {
							unidadeMedidaJoin = root.join( "unidadeMedida", JoinType.INNER );
							unidadeMedidaJoin.alias( "unidadeMedida" );
						}
						lista.add( builder.equal( unidadeMedidaJoin.get( "nome" ), poFilter.getUnidadeMedida().getNome() ) );
					}

					if ( !Utilidades.isNuloOuVazio( poFilter.getUnidadeMedida().getSigla() ) ) {

						if ( unidadeMedidaJoin == null ) {
							unidadeMedidaJoin = root.join( "unidadeMedida", JoinType.INNER );
							unidadeMedidaJoin.alias( "unidadeMedida" );
						}
						lista.add( builder.equal( unidadeMedidaJoin.get( "sigla" ), poFilter.getUnidadeMedida().getSigla() ) );
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
