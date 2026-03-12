package br.com.srcsoftware.sistema.notafiscal.rateio;

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
import br.com.srcsoftware.modular.financeiro.contapagar.ContaPagarPO;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.pessoa.cliente.ClientePO;
import br.com.srcsoftware.modular.manager.pessoa.pessoafisica.PessoaFisicaPO;
import br.com.srcsoftware.modular.manager.pessoa.pessoajuridica.PessoaJuridicaPO;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.notafiscal.rateio.centrocusto.CentroCustoPO;
import br.com.srcsoftware.sistema.notafiscal.rateio.itemnotafiscalrateio.ItemNotaFiscalRateioPO;
import br.com.srcsoftware.sistema.pessoa.fornecedor.juridico.FornecedorJuridicoPO;

public class NotaFiscalRateioDAOImpl implements NotaFiscalRateioDAOInterface{

	@Override
	public NotaFiscalRateioPO inserir( HibernateConnection hibernate, NotaFiscalRateioPO po ) throws ApplicationException {
		try {
			return (NotaFiscalRateioPO) hibernate.inserir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void alterar( HibernateConnection hibernate, NotaFiscalRateioPO po ) throws ApplicationException {
		try {
			hibernate.alterar( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void excluir( HibernateConnection hibernate, NotaFiscalRateioPO po ) throws ApplicationException {
		try {
			hibernate.excluir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public NotaFiscalRateioPO filtrarPorId( Long id ) throws ApplicationException {
		try {
			return (NotaFiscalRateioPO) new HibernateConnection().filtrarPorId( NotaFiscalRateioPO.class, id );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@SuppressWarnings( "unchecked" )
	@Override
	public List< NotaFiscalRateioPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, HashMap< String, ArrayList< Object > > camposBetween, NotaFiscalRateioPO poFilter ) throws ApplicationException {

		HibernateConnection hibernate = new HibernateConnection();
		try {
			hibernate.iniciarTransacao();

			//  CRIANDO A CRITERIA COM BASE NA CRITERIABUILDER
			CriteriaBuilder builder = hibernate.getSessaoCorrente().getCriteriaBuilder();

			//  CRIANDO A CRITERIA DE CONSULTA COM BASE NA CRITERIABUILDER
			CriteriaQuery< NotaFiscalRateioPO > criteria = builder.createQuery( NotaFiscalRateioPO.class );

			// DEFININDO O OBJETO BASE DA SELECT
			Root< NotaFiscalRateioPO > root = criteria.from( NotaFiscalRateioPO.class );

			// Definindo os parametros do WHERE
			Predicate[ ] parametros = this.montarParametros( builder, root, poFilter, camposBetween );

			// Executando A Consulta
			List< NotaFiscalRateioPO > encontrados = hibernate.filtrar( paginacao, camposOrders, builder, root, criteria, parametros, NotaFiscalRateioPO.class );

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

	private Predicate[ ] montarParametros( CriteriaBuilder builder, Root< NotaFiscalRateioPO > root, NotaFiscalRateioPO poFilter, HashMap< String, ArrayList< Object > > camposBetween ) {
		ArrayList< Predicate > lista = new ArrayList<>();

		if ( poFilter == null ) {
			return null;
		}

		if ( !Utilidades.isNuloOuVazio( poFilter.getId() ) ) {
			lista.add( builder.equal( root.get( "id" ), poFilter.getId() ) );
		} else {
			if ( !Utilidades.isNuloOuVazio( poFilter.getNumero() ) ) {
				lista.add( builder.like( root.get( "numero" ), poFilter.getNumero() + "%" ) );
			}

			if ( !Utilidades.isNuloOuVazio( poFilter.getNumeroRecibo() ) ) {
				lista.add( builder.like( root.get( "numeroRecibo" ), poFilter.getNumeroRecibo() + "%" ) );
			}

			if ( !Utilidades.isNuloOuVazio( poFilter.getTipo() ) ) {
				lista.add( builder.equal( root.get( "tipo" ), poFilter.getTipo() ) );
			}

			// MONTANDO JOIN FORNECEDOR
			if ( !Utilidades.isNuloOuVazio( poFilter.getFornecedor() ) ) {

				Join< NotaFiscalRateioPO, FornecedorJuridicoPO > fornecedorJoin = null;

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

			// MONTANDO JOIN CLIENTE
			if ( !Utilidades.isNuloOuVazio( poFilter.getCliente() ) ) {

				Join< NotaFiscalRateioPO, ClientePO > clienteJoin = null;

				if ( !Utilidades.isNuloOuVazio( poFilter.getCliente().getId() ) ) {
					if ( clienteJoin == null ) {
						clienteJoin = root.join( "cliente", JoinType.INNER );
						clienteJoin.alias( "cliente" );
					}
					lista.add( builder.equal( clienteJoin.get( "id" ), poFilter.getCliente().getId() ) );

				} else {
					if ( !Utilidades.isNuloOuVazio( poFilter.getCliente().getNome() ) ) {
						if ( clienteJoin == null ) {
							clienteJoin = root.join( "cliente", JoinType.INNER );
							clienteJoin.alias( "cliente" );
						}
						lista.add( builder.equal( clienteJoin.get( "nome" ), poFilter.getCliente().getNome() ) );

					}

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

					if ( !Utilidades.isNuloOuVazio( poFilter.getCliente().getPessoaFisica() ) ) {

						Join< ClientePO, PessoaFisicaPO > pessoaFisicaJoin = null;

						if ( !Utilidades.isNuloOuVazio( poFilter.getCliente().getPessoaFisica().getId() ) ) {
							if ( clienteJoin == null ) {
								clienteJoin = root.join( "cliente", JoinType.INNER );
								clienteJoin.alias( "cliente" );
							}

							if ( pessoaFisicaJoin == null ) {
								pessoaFisicaJoin = clienteJoin.join( "pessoaFisica", JoinType.INNER );
								pessoaFisicaJoin.alias( "pessoaFisica" );
							}
							lista.add( builder.equal( clienteJoin.get( "id" ), poFilter.getCliente().getPessoaFisica().getId() ) );

						} else {
							if ( !Utilidades.isNuloOuVazio( poFilter.getCliente().getPessoaFisica().getRazaoSocial() ) ) {

								if ( clienteJoin == null ) {
									clienteJoin = root.join( "cliente", JoinType.INNER );
									clienteJoin.alias( "cliente" );
								}

								if ( pessoaFisicaJoin == null ) {
									pessoaFisicaJoin = clienteJoin.join( "pessoaFisica", JoinType.INNER );
									pessoaFisicaJoin.alias( "pessoaFisica" );
								}

								lista.add( builder.like( pessoaFisicaJoin.get( "razaoSocial" ), poFilter.getCliente().getPessoaFisica().getRazaoSocial() + "%" ) );
							}

							if ( !Utilidades.isNuloOuVazio( poFilter.getCliente().getPessoaFisica().getTelefone1() ) ) {

								if ( clienteJoin == null ) {
									clienteJoin = root.join( "cliente", JoinType.INNER );
									clienteJoin.alias( "cliente" );
								}

								if ( pessoaFisicaJoin == null ) {
									pessoaFisicaJoin = clienteJoin.join( "pessoaFisica", JoinType.INNER );
									pessoaFisicaJoin.alias( "pessoaFisica" );
								}
								lista.add( builder.equal( pessoaFisicaJoin.get( "telefone1" ), poFilter.getCliente().getPessoaFisica().getTelefone1() ) );
							}
						}
					}
				}
			}

			// MONTANDO JOIN ITENS
			if ( !Utilidades.isNuloOuVazio( poFilter.getItens() ) ) {
				Join< NotaFiscalRateioPO, ItemNotaFiscalRateioPO > itemJoinList = null;

				ItemNotaFiscalRateioPO itemFiltrar = poFilter.getItens().iterator().next();

				if ( !Utilidades.isNuloOuVazio( itemFiltrar.getId() ) ) {
					if ( itemJoinList == null ) {
						itemJoinList = root.joinSet( "itens", JoinType.LEFT );
						itemJoinList.alias( "item" );
					}
					lista.add( builder.equal( itemJoinList.get( "id" ), itemFiltrar.getId() ) );
				} else {

					if ( !Utilidades.isNuloOuVazio( itemFiltrar.getCentroCusto() ) ) {

						Join< ItemNotaFiscalRateioPO, CentroCustoPO > centroCustoJoin = null;

						if ( !Utilidades.isNuloOuVazio( itemFiltrar.getCentroCusto().getId() ) ) {
							if ( itemJoinList == null ) {
								itemJoinList = root.joinSet( "itens", JoinType.LEFT );
								itemJoinList.alias( "item" );
							}

							if ( centroCustoJoin == null ) {
								centroCustoJoin = itemJoinList.join( "centroCusto", JoinType.INNER );
								centroCustoJoin.alias( "centroCusto" );
							}

							lista.add( builder.equal( centroCustoJoin.get( "id" ), itemFiltrar.getCentroCusto().getId() ) );
						} else {
							if ( !Utilidades.isNuloOuVazio( itemFiltrar.getCentroCusto().getCodigo() ) ) {
								if ( itemJoinList == null ) {
									itemJoinList = root.joinSet( "itens", JoinType.LEFT );
									itemJoinList.alias( "item" );
								}

								if ( centroCustoJoin == null ) {
									centroCustoJoin = itemJoinList.join( "centroCusto", JoinType.INNER );
									centroCustoJoin.alias( "centroCusto" );
								}

								lista.add( builder.like( centroCustoJoin.get( "codigo" ), itemFiltrar.getCentroCusto().getCodigo().concat( "%" ) ) );
							}
						}

					}

				}
			}

			// MONTANDO JOIN CONTAS A PAGAR
			if ( !Utilidades.isNuloOuVazio( poFilter.getContaPagar() ) ) {

				Join< NotaFiscalRateioPO, ContaPagarPO > contaPagarJoin = null;

				if ( !Utilidades.isNuloOuVazio( camposBetween ) ) {

					if ( contaPagarJoin == null ) {
						contaPagarJoin = root.join( "contaPagar", JoinType.INNER );
						contaPagarJoin.alias( "contaPagar" );
					}
					for ( String chave : camposBetween.keySet() ) {
						String[ ] chaveSplit = chave.split( "[.]" );

						if ( chaveSplit.length > 1 ) {
							String chaveJoin = chaveSplit[ 0 ];
							String campoJoin = chaveSplit[ 1 ];

							/** PARA CADA JOIN QUE SE DESEJAR FAZER BETWEEN, DEVEMOS ADICIONAR UM IF DESSE COMPARANDO COM CADA VARIAVEL aliasXxxxx (ex: aliasContaPagar) */
							if ( chaveJoin.equalsIgnoreCase( "contaPagar" ) ) {
								/* PERMITE O BEETWEEN DE contaPagar NIVEL DE JOIN ex: (data) */
								Predicate predicadoBetween = HibernateConnection.montarBetween( contaPagarJoin, builder, campoJoin, camposBetween.get( chave ) );
								if ( predicadoBetween != null ) {
									lista.add( predicadoBetween );
								}
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

}
