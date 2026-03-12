package br.com.srcsoftware.sistema.silo.saida;

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
import br.com.srcsoftware.modular.manager.pessoa.cliente.ClientePO;
import br.com.srcsoftware.modular.manager.pessoa.pessoajuridica.PessoaJuridicaPO;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.safra.SafraPO;
import br.com.srcsoftware.sistema.safra.cultura.CulturaPO;
import br.com.srcsoftware.sistema.silo.contratovenda.ContratoVendaPO;

public final class SaidaGraoDAOImpl implements SaidaGraoDAOInterface{

	@Override
	public SaidaGraoPO inserir( HibernateConnection hibernate, SaidaGraoPO po ) throws ApplicationException {
		try {
			return (SaidaGraoPO) hibernate.inserir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void alterar( HibernateConnection hibernate, SaidaGraoPO po ) throws ApplicationException {
		try {
			hibernate.alterar( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void excluir( HibernateConnection hibernate, SaidaGraoPO po ) throws ApplicationException {
		try {
			hibernate.excluir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public SaidaGraoPO filtrarPorId( Long id ) throws ApplicationException {
		try {
			return (SaidaGraoPO) new HibernateConnection().filtrarPorId( SaidaGraoPO.class, id );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@SuppressWarnings( "unchecked" )
	@Override
	public List< SaidaGraoPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, HashMap< String, ArrayList< Object > > camposBetween, SaidaGraoPO poFilter ) throws ApplicationException {

		HibernateConnection hibernate = new HibernateConnection();
		try {
			hibernate.iniciarTransacao();

			//  CRIANDO A CRITERIA COM BASE NA CRITERIABUILDER
			CriteriaBuilder builder = hibernate.getSessaoCorrente().getCriteriaBuilder();

			//  CRIANDO A CRITERIA DE CONSULTA COM BASE NA CRITERIABUILDER
			CriteriaQuery< SaidaGraoPO > criteria = builder.createQuery( SaidaGraoPO.class );

			// DEFININDO O OBJETO BASE DA SELECT
			Root< SaidaGraoPO > root = criteria.from( SaidaGraoPO.class );

			// Definindo os parametros do WHERE
			Predicate[ ] parametros = this.montarParametros( builder, root, poFilter, camposBetween );

			// Executando A Consulta
			List< SaidaGraoPO > encontrados = hibernate.filtrar( paginacao, camposOrders, builder, root, criteria, parametros, SaidaGraoPO.class );

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

	private Predicate[ ] montarParametros( CriteriaBuilder builder, Root< SaidaGraoPO > root, SaidaGraoPO poFilter, HashMap< String, ArrayList< Object > > camposBetween ) {
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

			if ( !Utilidades.isNuloOuVazio( poFilter.getPlaca() ) ) {
				lista.add( builder.like( root.get( "placa" ), poFilter.getPlaca() + "%" ) );
			}

			if ( !Utilidades.isNuloOuVazio( poFilter.getMotorista() ) ) {
				lista.add( builder.like( root.get( "motorista" ), poFilter.getMotorista() + "%" ) );
			}

			// MONTANDO JOIN SAFRA
			if ( !Utilidades.isNuloOuVazio( poFilter.getSafra01() ) ) {

				Join< SaidaGraoPO, SafraPO > safra01Join = null;

				if ( !Utilidades.isNuloOuVazio( poFilter.getSafra01().getId() ) ) {
					if ( safra01Join == null ) {
						safra01Join = root.join( "safra01", JoinType.INNER );
						safra01Join.alias( "safra01" );
					}
					lista.add( builder.equal( safra01Join.get( "id" ), poFilter.getSafra01().getId() ) );

				} else {
					if ( !Utilidades.isNuloOuVazio( poFilter.getSafra01().getNome() ) ) {

						if ( safra01Join == null ) {
							safra01Join = root.join( "safra01", JoinType.INNER );
							safra01Join.alias( "safra01" );
						}
						lista.add( builder.like( safra01Join.get( "nome" ), poFilter.getSafra01().getNome() + "%" ) );
					}
				}
			}

			// MONTANDO JOIN CULTURA
			if ( !Utilidades.isNuloOuVazio( poFilter.getCultura() ) ) {

				Join< SaidaGraoPO, CulturaPO > culturaJoin = null;

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

				Join< SaidaGraoPO, ClientePO > clienteJoin = null;

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
			
			// MONTANDO JOIN CULTURA
			if ( !Utilidades.isNuloOuVazio( poFilter.getContratoVenda() ) ) {

				Join< SaidaGraoPO, ContratoVendaPO > contratoJoin = null;

				if ( !Utilidades.isNuloOuVazio( poFilter.getContratoVenda().getId() ) ) {
					if ( contratoJoin == null ) {
						contratoJoin = root.join( "contratoVenda", JoinType.INNER );
						contratoJoin.alias( "contratoVenda" );
					}
					lista.add( builder.equal( contratoJoin.get( "id" ), poFilter.getContratoVenda().getId() ) );

				} else {
					if ( !Utilidades.isNuloOuVazio( poFilter.getContratoVenda().getNumero() ) ) {

						if ( contratoJoin == null ) {
							contratoJoin = root.join( "contratoVenda", JoinType.INNER );
							contratoJoin.alias( "contratoVenda" );
						}
						lista.add( builder.equal( contratoJoin.get( "numero" ), poFilter.getContratoVenda().getNumero() ) );
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