package br.com.srcsoftware.sistema.silo.contratovenda;

import java.math.BigDecimal;
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
import br.com.srcsoftware.modular.manager.pessoa.cliente.ClientePO;
import br.com.srcsoftware.modular.manager.pessoa.pessoajuridica.PessoaJuridicaPO;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.safra.cultura.CulturaPO;

public final class ContratoVendaDAOImpl implements ContratoVendaDAOInterface{

	@Override
	public ContratoVendaPO inserir( HibernateConnection hibernate, ContratoVendaPO po ) throws ApplicationException {
		try {
			return (ContratoVendaPO) hibernate.inserir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void alterar( HibernateConnection hibernate, ContratoVendaPO po ) throws ApplicationException {
		try {
			hibernate.alterar( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void excluir( HibernateConnection hibernate, ContratoVendaPO po ) throws ApplicationException {
		try {
			hibernate.excluir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public ContratoVendaPO filtrarPorId( Long id ) throws ApplicationException {
		try {
			return (ContratoVendaPO) new HibernateConnection().filtrarPorId( ContratoVendaPO.class, id );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@SuppressWarnings( "unchecked" )
	@Override
	public List< ContratoVendaPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, HashMap< String, ArrayList< Object > > camposBetween, ContratoVendaPO poFilter ) throws ApplicationException {

		HibernateConnection hibernate = new HibernateConnection();
		try {
			hibernate.iniciarTransacao();

			//  CRIANDO A CRITERIA COM BASE NA CRITERIABUILDER
			CriteriaBuilder builder = hibernate.getSessaoCorrente().getCriteriaBuilder();

			//  CRIANDO A CRITERIA DE CONSULTA COM BASE NA CRITERIABUILDER
			CriteriaQuery< ContratoVendaPO > criteria = builder.createQuery( ContratoVendaPO.class );

			// DEFININDO O OBJETO BASE DA SELECT
			Root< ContratoVendaPO > root = criteria.from( ContratoVendaPO.class );

			// Definindo os parametros do WHERE
			Predicate[ ] parametros = this.montarParametros( builder, root, poFilter, camposBetween );

			// Executando A Consulta
			List< ContratoVendaPO > encontrados = hibernate.filtrar( paginacao, camposOrders, builder, root, criteria, parametros, ContratoVendaPO.class );

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

	private Predicate[ ] montarParametros( CriteriaBuilder builder, Root< ContratoVendaPO > root, ContratoVendaPO poFilter, HashMap< String, ArrayList< Object > > camposBetween ) {
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

			// MONTANDO JOIN CULTURA
			if ( !Utilidades.isNuloOuVazio( poFilter.getCultura() ) ) {

				Join< ContratoVendaPO, CulturaPO > culturaJoin = null;

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

			// MONTANDO JOIN CLIENTE
			if ( !Utilidades.isNuloOuVazio( poFilter.getCliente() ) ) {

				Join< ContratoVendaPO, ClientePO > clienteJoin = null;

				if ( !Utilidades.isNuloOuVazio( poFilter.getCliente().getId() ) ) {
					if ( clienteJoin == null ) {
						clienteJoin = root.join( "cliente", JoinType.INNER );
						clienteJoin.alias( "cliente" );
					}
					lista.add( builder.equal( clienteJoin.get( "id" ), poFilter.getCliente().getId() ) );

				} else {
					if ( !Utilidades.isNuloOuVazio( poFilter.getCliente().getPessoaJuridica() ) ) {

						Join< ClientePO, PessoaJuridicaPO > pessoaJuridicaJoin = null;

						if ( !Utilidades.isNuloOuVazio( poFilter.getCliente().getPessoaJuridica().getId() ) ) {
							if ( clienteJoin == null ) {
								clienteJoin = root.join( "cliente", JoinType.INNER );
								clienteJoin.alias( "cliente" );
							}

							if ( pessoaJuridicaJoin == null ) {
								pessoaJuridicaJoin = clienteJoin.join( "pessoaJuridica", JoinType.INNER );
								pessoaJuridicaJoin.alias( "pessoaJuridica" );
							}
							lista.add( builder.equal( clienteJoin.get( "id" ), poFilter.getCliente().getPessoaJuridica().getId() ) );

						} else {
							if ( !Utilidades.isNuloOuVazio( poFilter.getCliente().getPessoaJuridica().getRazaoSocial() ) ) {

								if ( clienteJoin == null ) {
									clienteJoin = root.join( "cliente", JoinType.INNER );
									clienteJoin.alias( "cliente" );
								}

								if ( pessoaJuridicaJoin == null ) {
									pessoaJuridicaJoin = clienteJoin.join( "pessoaJuridica", JoinType.INNER );
									pessoaJuridicaJoin.alias( "pessoaJuridica" );
								}

								lista.add( builder.like( pessoaJuridicaJoin.get( "razaoSocial" ), poFilter.getCliente().getPessoaJuridica().getRazaoSocial() + "%" ) );
							}

							if ( !Utilidades.isNuloOuVazio( poFilter.getCliente().getPessoaJuridica().getTelefone1() ) ) {

								if ( clienteJoin == null ) {
									clienteJoin = root.join( "cliente", JoinType.INNER );
									clienteJoin.alias( "cliente" );
								}

								if ( pessoaJuridicaJoin == null ) {
									pessoaJuridicaJoin = clienteJoin.join( "pessoaJuridica", JoinType.INNER );
									pessoaJuridicaJoin.alias( "pessoaJuridica" );
								}
								lista.add( builder.equal( pessoaJuridicaJoin.get( "telefone1" ), poFilter.getCliente().getPessoaJuridica().getTelefone1() ) );
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

	@Override
	public ContratoVendaPO filtrarPorNumero( String numero ) throws ApplicationException {
		try {
			return (ContratoVendaPO) new HibernateConnection().filtrarSinglePorCampo( ContratoVendaPO.class, numero, "numero", HibernateConnection.COMITA_TRANSACAO );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public BigDecimal filtrarQuantidadeRestante( Long idContratoVenda ) throws ApplicationException {

		HibernateConnection hibernate = new HibernateConnection();
		try {
			hibernate.iniciarTransacao();
			StringBuilder SQL = new StringBuilder();
			SQL.append( " SELECT  " );
			SQL.append( "  	cv.quantidade - sum(COALESCE(sg.pesoLiquido,0)) " );
			SQL.append( " FROM  " );
			SQL.append( " 	esafra.sistema_contratovendas cv " );
			SQL.append( " 	LEFT JOIN esafra.sistema_saidasgrao sg ON cv.id = sg.idContratoVenda " );
			SQL.append( " where 1=1 " );
			SQL.append( " 	and cv.id  = :idContratoVendaParam " );
			SQL.append( " group by cv.id " );

			Query query = hibernate.getSessaoCorrente().createNativeQuery( SQL.toString() );
			query.setParameter( "idContratoVendaParam", idContratoVenda );

			BigDecimal resultado = (BigDecimal) query.getSingleResult();

			hibernate.commitTransacao();

			return resultado;

		} catch ( ApplicationException e ) {
			hibernate.rollbackTransacao();
			throw e;
		} catch ( javax.persistence.NoResultException e ) {
			return BigDecimal.ZERO;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();

			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}
}