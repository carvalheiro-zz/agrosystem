package br.com.srcsoftware.sistema.pessoa.fornecedor.juridico;

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

public class FornecedorDAOImpl implements FornecedorDAOInterface{

	@Override
	public FornecedorJuridicoPO inserir( HibernateConnection hibernate, FornecedorJuridicoPO po ) throws ApplicationException {
		try {
			return (FornecedorJuridicoPO) hibernate.inserir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void alterar( HibernateConnection hibernate, FornecedorJuridicoPO po ) throws ApplicationException {
		try {
			hibernate.alterar( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void excluir( HibernateConnection hibernate, FornecedorJuridicoPO po ) throws ApplicationException {
		try {
			hibernate.excluir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public FornecedorJuridicoPO filtrarPorId( Long id ) throws ApplicationException {
		try {
			return (FornecedorJuridicoPO) new HibernateConnection().filtrarPorId( FornecedorJuridicoPO.class, id );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@SuppressWarnings( "unchecked" )
	@Override
	public List< FornecedorJuridicoPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, FornecedorJuridicoPO poFilter ) throws ApplicationException {

		HibernateConnection hibernate = new HibernateConnection();
		try {
			hibernate.iniciarTransacao();

			//  CRIANDO A CRITERIA COM BASE NA CRITERIABUILDER
			CriteriaBuilder builder = hibernate.getSessaoCorrente().getCriteriaBuilder();

			//  CRIANDO A CRITERIA DE CONSULTA COM BASE NA CRITERIABUILDER
			CriteriaQuery< FornecedorJuridicoPO > criteria = builder.createQuery( FornecedorJuridicoPO.class );

			// DEFININDO O OBJETO BASE DA SELECT
			Root< FornecedorJuridicoPO > root = criteria.from( FornecedorJuridicoPO.class );

			// Definindo os parametros do WHERE
			Predicate[ ] parametros = this.montarParametros( builder, root, poFilter );

			// Executando A Consulta
			List< FornecedorJuridicoPO > encontrados = hibernate.filtrar( paginacao, camposOrders, builder, root, criteria, parametros, FornecedorJuridicoPO.class );

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

	private Predicate[ ] montarParametros( CriteriaBuilder builder, Root< FornecedorJuridicoPO > root, FornecedorJuridicoPO poFilter ) {
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

			if ( !Utilidades.isNuloOuVazio( poFilter.getTelefone() ) ) {
				lista.add( builder.like( root.get( "telefone" ), "%".concat( poFilter.getTelefone() ).concat( "%" ) ) );
			}

			if ( !Utilidades.isNuloOuVazio( poFilter.getEndereco() ) ) {
				lista.add( builder.like( root.get( "endereco" ), "%".concat( poFilter.getEndereco() ).concat( "%" ) ) );
			}

			if ( !Utilidades.isNuloOuVazio( poFilter.getObservacao() ) ) {
				lista.add( builder.like( root.get( "observacao" ), "%".concat( poFilter.getObservacao() ).concat( "%" ) ) );
			}
		}

		if ( lista.isEmpty() ) {
			return null;
		} else {
			return lista.toArray( new Predicate[ 1 ] );
		}
	}

}
