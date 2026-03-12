package br.com.srcsoftware.sistema.silo.naturezaoperacao;

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

public class NaturezaOperacaoDAOImpl implements NaturezaOperacaoDAOInterface {
	
	@Override
	public NaturezaOperacaoPO inserir( HibernateConnection hibernate, NaturezaOperacaoPO po ) throws ApplicationException {
		try {
			return (NaturezaOperacaoPO) hibernate.inserir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}
	
	@Override
	public void alterar( HibernateConnection hibernate, NaturezaOperacaoPO po ) throws ApplicationException {
		try {
			hibernate.alterar( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}
	
	@Override
	public void excluir( HibernateConnection hibernate, NaturezaOperacaoPO po ) throws ApplicationException {
		try {
			hibernate.excluir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}
	
	@Override
	public NaturezaOperacaoPO filtrarPorId( Long id ) throws ApplicationException {
		try {
			return (NaturezaOperacaoPO) new HibernateConnection().filtrarPorId( NaturezaOperacaoPO.class, id );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List< NaturezaOperacaoPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, NaturezaOperacaoPO poFilter ) throws ApplicationException {
		
		HibernateConnection hibernate = new HibernateConnection();
		try {
			hibernate.iniciarTransacao();
			
			// CRIANDO A CRITERIA COM BASE NA CRITERIABUILDER
			CriteriaBuilder builder = hibernate.getSessaoCorrente().getCriteriaBuilder();
			
			// CRIANDO A CRITERIA DE CONSULTA COM BASE NA CRITERIABUILDER
			CriteriaQuery< NaturezaOperacaoPO > criteria = builder.createQuery( NaturezaOperacaoPO.class );
			
			// DEFININDO O OBJETO BASE DA SELECT
			Root< NaturezaOperacaoPO > root = criteria.from( NaturezaOperacaoPO.class );
			
			// Definindo os parametros do WHERE
			Predicate[] parametros = this.montarParametros( builder, root, poFilter );
			
			// Executando A Consulta
			List< NaturezaOperacaoPO > encontrados = hibernate.filtrar( paginacao, camposOrders, builder, root, criteria, parametros, NaturezaOperacaoPO.class );
			
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
	
	private Predicate[] montarParametros( CriteriaBuilder builder, Root< NaturezaOperacaoPO > root, NaturezaOperacaoPO poFilter ) {
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
		}
		
		if ( lista.isEmpty() ) {
			return null;
		} else {
			return lista.toArray( new Predicate[1] );
		}
	}
	
}
