package br.com.srcsoftware.sistema.notafiscalcupom;

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
import br.com.srcsoftware.sistema.aplicacao.aplicacao.AplicacaoPO;

public class NotaFiscalCupomDAOImpl implements NotaFiscalCupomDAOInterface{

	@Override
	public NotaFiscalCupomPO inserir( HibernateConnection hibernate, NotaFiscalCupomPO po ) throws ApplicationException {
		try {
			return (NotaFiscalCupomPO) hibernate.inserir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void alterar( HibernateConnection hibernate, NotaFiscalCupomPO po ) throws ApplicationException {
		try {
			hibernate.alterar( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void excluir( HibernateConnection hibernate, NotaFiscalCupomPO po ) throws ApplicationException {
		try {
			hibernate.excluir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public NotaFiscalCupomPO filtrarPorId( Long id ) throws ApplicationException {
		try {
			return (NotaFiscalCupomPO) new HibernateConnection().filtrarPorId( NotaFiscalCupomPO.class, id );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@SuppressWarnings( "unchecked" )
	@Override
	public List< NotaFiscalCupomPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, HashMap< String, ArrayList< Object > > camposBetween, NotaFiscalCupomPO poFilter ) throws ApplicationException {

		HibernateConnection hibernate = new HibernateConnection();
		try {
			hibernate.iniciarTransacao();

			//  CRIANDO A CRITERIA COM BASE NA CRITERIABUILDER
			CriteriaBuilder builder = hibernate.getSessaoCorrente().getCriteriaBuilder();

			//  CRIANDO A CRITERIA DE CONSULTA COM BASE NA CRITERIABUILDER
			CriteriaQuery< NotaFiscalCupomPO > criteria = builder.createQuery( NotaFiscalCupomPO.class );

			// DEFININDO O OBJETO BASE DA SELECT
			Root< NotaFiscalCupomPO > root = criteria.from( NotaFiscalCupomPO.class );

			// Definindo os parametros do WHERE
			Predicate[ ] parametros = this.montarParametros( builder, root, poFilter, camposBetween );

			// Executando A Consulta
			List< NotaFiscalCupomPO > encontrados = hibernate.filtrar( paginacao, camposOrders, builder, root, criteria, parametros, NotaFiscalCupomPO.class );

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

	private Predicate[ ] montarParametros( CriteriaBuilder builder, Root< NotaFiscalCupomPO > root, NotaFiscalCupomPO poFilter, HashMap< String, ArrayList< Object > > camposBetween ) {
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

			// MONTANDO JOIN FORNECEDOR
			if ( !Utilidades.isNuloOuVazio( poFilter.getFornecedor() ) ) {

				Join< AplicacaoPO, FornecedorPO > fornecedorJoin = null;

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
		}
		if ( lista.isEmpty() ) {
			return null;
		} else {
			return lista.toArray( new Predicate[ 1 ] );
		}
	}

}
