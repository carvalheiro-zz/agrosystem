package br.com.srcsoftware.sistema.manutencao.manutencao;

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
import br.com.srcsoftware.sistema.aplicacao.item.ItemPO;
import br.com.srcsoftware.sistema.manutencao.imovel.ImovelPO;
import br.com.srcsoftware.sistema.manutencao.implemento.ImplementoPO;
import br.com.srcsoftware.sistema.manutencao.servico.ServicoPO;
import br.com.srcsoftware.sistema.manutencao.veiculo.VeiculoPO;
import br.com.srcsoftware.sistema.pessoa.fornecedor.juridico.FornecedorJuridicoPO;
import br.com.srcsoftware.sistema.pessoa.prestadorservico.PrestadorServicoPO;
import br.com.srcsoftware.sistema.safra.SafraPO;
import br.com.srcsoftware.sistema.safra.cultura.CulturaPO;
import br.com.srcsoftware.sistema.safra.setor.SetorPO;

public class ManutencaoDAOImpl implements ManutencaoDAOInterface{

	@Override
	public ManutencaoPO inserir( HibernateConnection hibernate, ManutencaoPO po ) throws ApplicationException {
		try {
			return (ManutencaoPO) hibernate.inserir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void alterar( HibernateConnection hibernate, ManutencaoPO po ) throws ApplicationException {
		try {
			hibernate.alterar( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public void excluir( HibernateConnection hibernate, ManutencaoPO po ) throws ApplicationException {
		try {
			hibernate.excluir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@Override
	public ManutencaoPO filtrarPorId( Long id ) throws ApplicationException {
		try {
			return (ManutencaoPO) new HibernateConnection().filtrarPorId( ManutencaoPO.class, id );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	@SuppressWarnings( "unchecked" )
	@Override
	public List< ManutencaoPO > filtrar( Paginacao paginacao, HashMap< String, String > camposOrders, HashMap< String, ArrayList< Object > > camposBetween, ManutencaoPO poFilter, String tipoManutencao ) throws ApplicationException {

		HibernateConnection hibernate = new HibernateConnection();
		try {
			hibernate.iniciarTransacao();

			//  CRIANDO A CRITERIA COM BASE NA CRITERIABUILDER
			CriteriaBuilder builder = hibernate.getSessaoCorrente().getCriteriaBuilder();

			//  CRIANDO A CRITERIA DE CONSULTA COM BASE NA CRITERIABUILDER
			CriteriaQuery< ManutencaoPO > criteria = builder.createQuery( ManutencaoPO.class );

			// DEFININDO O OBJETO BASE DA SELECT
			Root< ManutencaoPO > root = criteria.from( ManutencaoPO.class );

			// Definindo os parametros do WHERE
			Predicate[ ] parametros = this.montarParametros( builder, root, poFilter, camposBetween, tipoManutencao );

			// Executando A Consulta
			List< ManutencaoPO > encontrados = hibernate.filtrar( paginacao, camposOrders, builder, root, criteria, parametros, ManutencaoPO.class );

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

	private Predicate[ ] montarParametros( CriteriaBuilder builder, Root< ManutencaoPO > root, ManutencaoPO poFilter, HashMap< String, ArrayList< Object > > camposBetween, String tipoManutencao ) {
		ArrayList< Predicate > lista = new ArrayList<>();

		if ( poFilter == null ) {
			return null;
		}

		lista.addAll( HibernateConnection.montarBetween( root, builder, camposBetween ) );

		lista.add( builder.isNotNull( root.get( tipoManutencao ) ) );

		if ( !Utilidades.isNuloOuVazio( poFilter.getId() ) ) {
			lista.add( builder.equal( root.get( "id" ), poFilter.getId() ) );
		} else {

			if ( !Utilidades.isNuloOuVazio( poFilter.getTipo() ) ) {
				lista.add( builder.equal( root.get( "tipo" ), poFilter.getTipo() ) );
			}

			if ( !Utilidades.isNuloOuVazio( poFilter.getData() ) ) {
				lista.add( builder.equal( root.get( "data" ), poFilter.getData() ) );
			}
			if ( !Utilidades.isNuloOuVazio( poFilter.getFinalidade() ) ) {
				lista.add( builder.equal( root.get( "finalidade" ), poFilter.getFinalidade() ) );
			}
			if ( !Utilidades.isNuloOuVazio( poFilter.getNotaFiscal() ) ) {
				lista.add( builder.equal( root.get( "notaFiscal" ), poFilter.getNotaFiscal() ) );
			}
			if ( !Utilidades.isNuloOuVazio( poFilter.getReciboOuNotaFiscal() ) ) {
				lista.add( builder.equal( root.get( "reciboOuNotaFiscal" ), poFilter.getReciboOuNotaFiscal() ) );
			}

			// MONTANDO JOIN SETOR
			if ( !Utilidades.isNuloOuVazio( poFilter.getSetor() ) ) {

				Join< ManutencaoPO, SetorPO > setorJoin = null;

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

				Join< ManutencaoPO, SafraPO > safraJoin = null;

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

				Join< ManutencaoPO, CulturaPO > culturaJoin = null;

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

			// MONTANDO JOIN VEICULO
			if ( !Utilidades.isNuloOuVazio( poFilter.getVeiculo() ) ) {

				Join< ManutencaoPO, VeiculoPO > veiculoJoin = null;

				if ( !Utilidades.isNuloOuVazio( poFilter.getVeiculo().getId() ) ) {
					if ( veiculoJoin == null ) {
						veiculoJoin = root.join( "veiculo", JoinType.INNER );
						veiculoJoin.alias( "veiculo" );
					}
					lista.add( builder.equal( veiculoJoin.get( "id" ), poFilter.getVeiculo().getId() ) );

				} else {
					if ( !Utilidades.isNuloOuVazio( poFilter.getVeiculo().getNome() ) ) {

						if ( veiculoJoin == null ) {
							veiculoJoin = root.join( "veiculo", JoinType.INNER );
							veiculoJoin.alias( "veiculo" );
						}
						lista.add( builder.like( veiculoJoin.get( "nome" ), poFilter.getVeiculo().getNome() + "%" ) );
					}

					if ( !Utilidades.isNuloOuVazio( poFilter.getVeiculo().getNomeCompleto() ) ) {
						if ( veiculoJoin == null ) {
							veiculoJoin = root.join( "veiculo", JoinType.INNER );
							veiculoJoin.alias( "veiculo" );
						}
						lista.add( builder.like( veiculoJoin.get( "nomeCompleto" ), "%".concat( poFilter.getVeiculo().getNomeCompleto() ).concat( "%" ) ) );
					}

					if ( !Utilidades.isNuloOuVazio( poFilter.getVeiculo().getTipo() ) ) {

						if ( veiculoJoin == null ) {
							veiculoJoin = root.join( "veiculo", JoinType.INNER );
							veiculoJoin.alias( "veiculo" );
						}
						lista.add( builder.like( veiculoJoin.get( "tipo" ), poFilter.getVeiculo().getTipo() + "%" ) );
					}
				}
			}

			// MONTANDO JOIN IMPLEMENTO
			if ( !Utilidades.isNuloOuVazio( poFilter.getImplemento() ) ) {

				Join< ManutencaoPO, ImplementoPO > implementoJoin = null;

				if ( !Utilidades.isNuloOuVazio( poFilter.getImplemento().getId() ) ) {
					if ( implementoJoin == null ) {
						implementoJoin = root.join( "implemento", JoinType.INNER );
						implementoJoin.alias( "implemento" );
					}
					lista.add( builder.equal( implementoJoin.get( "id" ), poFilter.getImplemento().getId() ) );

				} else {
					if ( !Utilidades.isNuloOuVazio( poFilter.getImplemento().getNome() ) ) {

						if ( implementoJoin == null ) {
							implementoJoin = root.join( "implemento", JoinType.INNER );
							implementoJoin.alias( "implemento" );
						}
						lista.add( builder.like( implementoJoin.get( "nome" ), poFilter.getImplemento().getNome() + "%" ) );
					}

					if ( !Utilidades.isNuloOuVazio( poFilter.getImplemento().getNomeCompleto() ) ) {

						if ( implementoJoin == null ) {
							implementoJoin = root.join( "implemento", JoinType.INNER );
							implementoJoin.alias( "implemento" );
						}
						lista.add( builder.like( implementoJoin.get( "nomeCompleto" ), "%" + poFilter.getImplemento().getNomeCompleto() + "%" ) );
					}

					if ( !Utilidades.isNuloOuVazio( poFilter.getImplemento().getModelo() ) ) {

						if ( implementoJoin == null ) {
							implementoJoin = root.join( "implemento", JoinType.INNER );
							implementoJoin.alias( "implemento" );
						}
						lista.add( builder.like( implementoJoin.get( "modelo" ), poFilter.getImplemento().getModelo() + "%" ) );
					}
				}
			}

			// MONTANDO JOIN IMOVEL
			if ( !Utilidades.isNuloOuVazio( poFilter.getImovel() ) ) {

				Join< ManutencaoPO, ImovelPO > imovelJoin = null;

				if ( !Utilidades.isNuloOuVazio( poFilter.getImovel().getId() ) ) {
					if ( imovelJoin == null ) {
						imovelJoin = root.join( "imovel", JoinType.INNER );
						imovelJoin.alias( "imovel" );
					}
					lista.add( builder.equal( imovelJoin.get( "id" ), poFilter.getImovel().getId() ) );

				} else {
					if ( !Utilidades.isNuloOuVazio( poFilter.getImovel().getNome() ) ) {

						if ( imovelJoin == null ) {
							imovelJoin = root.join( "imovel", JoinType.INNER );
							imovelJoin.alias( "imovel" );
						}
						lista.add( builder.like( imovelJoin.get( "nome" ), poFilter.getImovel().getNome() + "%" ) );
					}
				}
			}

			// MONTANDO JOIN SERVIÇO
			if ( !Utilidades.isNuloOuVazio( poFilter.getServico() ) ) {

				Join< ManutencaoPO, ServicoPO > servicoJoin = null;

				if ( !Utilidades.isNuloOuVazio( poFilter.getServico().getId() ) ) {
					if ( servicoJoin == null ) {
						servicoJoin = root.join( "servico", JoinType.INNER );
						servicoJoin.alias( "servico" );
					}
					lista.add( builder.equal( servicoJoin.get( "id" ), poFilter.getServico().getId() ) );

				} else {
					if ( !Utilidades.isNuloOuVazio( poFilter.getServico().getNome() ) ) {

						if ( servicoJoin == null ) {
							servicoJoin = root.join( "servico", JoinType.INNER );
							servicoJoin.alias( "servico" );
						}
						lista.add( builder.like( servicoJoin.get( "nome" ), poFilter.getServico().getNome() + "%" ) );
					}
				}
			}

			// MONTANDO JOIN FORNECEDOR
			if ( !Utilidades.isNuloOuVazio( poFilter.getFornecedor() ) ) {

				Join< ManutencaoPO, FornecedorJuridicoPO > fornecedorJoin = null;

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

			// MONTANDO JOIN PRESTADOR
			if ( !Utilidades.isNuloOuVazio( poFilter.getPrestador() ) ) {

				Join< ItemPO, PrestadorServicoPO > prestadorJoin = null;

				if ( !Utilidades.isNuloOuVazio( poFilter.getPrestador().getId() ) ) {

					if ( prestadorJoin == null ) {
						prestadorJoin = root.join( "prestador", JoinType.INNER );
						prestadorJoin.alias( "prestador" );
					}

					lista.add( builder.equal( prestadorJoin.get( "id" ), poFilter.getPrestador().getId() ) );
				} else {

					if ( !Utilidades.isNuloOuVazio( poFilter.getPrestador().getNome() ) ) {

						if ( prestadorJoin == null ) {
							prestadorJoin = root.join( "prestador", JoinType.INNER );
							prestadorJoin.alias( "prestador" );
						}

						lista.add( builder.like( prestadorJoin.get( "nome" ), poFilter.getPrestador().getNome() + "%" ) );
					}

					if ( !Utilidades.isNuloOuVazio( poFilter.getPrestador().getTelefone() ) ) {

						if ( prestadorJoin == null ) {
							prestadorJoin = root.join( "prestador", JoinType.INNER );
							prestadorJoin.alias( "prestador" );
						}

						lista.add( builder.like( prestadorJoin.get( "telefone" ), poFilter.getPrestador().getTelefone() + "%" ) );
					}

					if ( !Utilidades.isNuloOuVazio( poFilter.getPrestador().getEmpresa() ) ) {

						if ( prestadorJoin == null ) {
							prestadorJoin = root.join( "prestador", JoinType.INNER );
							prestadorJoin.alias( "prestador" );
						}

						lista.add( builder.like( prestadorJoin.get( "empresa" ), poFilter.getPrestador().getEmpresa() + "%" ) );
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
