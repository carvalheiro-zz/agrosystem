package br.com.srcsoftware.sistema.manutencao.veiculo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;

public class VeiculoDAOImpl implements VeiculoDAOInterface{

	@Override
	public VeiculoPO inserir( HibernateConnection hibernate, VeiculoPO po ) throws ApplicationException {
		try {
			return (VeiculoPO) hibernate.inserir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void alterar( HibernateConnection hibernate, VeiculoPO po ) throws ApplicationException {
		try {
			hibernate.alterar( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void excluir( HibernateConnection hibernate, VeiculoPO po ) throws ApplicationException {
		try {
			hibernate.excluir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public VeiculoPO filtrarPorId( Long id ) throws ApplicationException {
		try {
			return (VeiculoPO) new HibernateConnection().filtrarPorId( VeiculoPO.class, id );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@SuppressWarnings( "unchecked" )
	@Override
	public List< VeiculoPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, VeiculoPO poFilter ) throws ApplicationException {

		HibernateConnection hibernate = new HibernateConnection();
		try {
			hibernate.iniciarTransacao();

			//  CRIANDO A CRITERIA COM BASE NA CRITERIABUILDER
			CriteriaBuilder builder = hibernate.getSessaoCorrente().getCriteriaBuilder();

			//  CRIANDO A CRITERIA DE CONSULTA COM BASE NA CRITERIABUILDER
			CriteriaQuery< VeiculoPO > criteria = builder.createQuery( VeiculoPO.class );

			// DEFININDO O OBJETO BASE DA SELECT
			Root< VeiculoPO > root = criteria.from( VeiculoPO.class );

			// Definindo os parametros do WHERE
			Predicate[ ] parametros = this.montarParametros( builder, root, poFilter );

			// Executando A Consulta
			List< VeiculoPO > encontrados = hibernate.filtrar( paginacao, camposOrders, builder, root, criteria, parametros, VeiculoPO.class );

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

	private Predicate[ ] montarParametros( CriteriaBuilder builder, Root< VeiculoPO > root, VeiculoPO poFilter ) {
		ArrayList< Predicate > lista = new ArrayList<>();

		if ( poFilter == null ) {
			return null;
		}

		if ( !Utilidades.isNuloOuVazio( poFilter.getId() ) ) {
			lista.add( builder.equal( root.get( "id" ), poFilter.getId() ) );
		} else {

			if ( !Utilidades.isNuloOuVazio( poFilter.getNome() ) ) {
				lista.add( builder.like( root.get( "nome" ), poFilter.getNome().concat( "%" ) ) );
			}

			if ( !Utilidades.isNuloOuVazio( poFilter.getNomeCompleto() ) ) {
				lista.add( builder.like( root.get( "nomeCompleto" ), "%".concat( poFilter.getNomeCompleto() ).concat( "%" ) ) );
			}

			if ( !Utilidades.isNuloOuVazio( poFilter.getAnoFabricacao() ) ) {
				lista.add( builder.equal( root.get( "anoFabricacao" ), poFilter.getAnoFabricacao() ) );
			}

			if ( !Utilidades.isNuloOuVazio( poFilter.getCodigo() ) ) {
				lista.add( builder.equal( root.get( "codigo" ), poFilter.getCodigo() ) );
			}

			if ( !Utilidades.isNuloOuVazio( poFilter.getModelo() ) ) {
				lista.add( builder.like( root.get( "modelo" ), poFilter.getModelo().concat( "%" ) ) );
			}

			if ( !Utilidades.isNuloOuVazio( poFilter.getNumeroChassis() ) ) {
				lista.add( builder.like( root.get( "numeroChassis" ), poFilter.getNumeroChassis().concat( "%" ) ) );
			}

			if ( !Utilidades.isNuloOuVazio( poFilter.getTipo() ) ) {
				lista.add( builder.equal( root.get( "tipo" ), poFilter.getTipo() ) );
			}

		}

		if ( lista.isEmpty() ) {
			return null;
		} else {
			return lista.toArray( new Predicate[ 1 ] );
		}
	}
}
