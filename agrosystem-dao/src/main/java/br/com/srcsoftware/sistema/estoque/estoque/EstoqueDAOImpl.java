package br.com.srcsoftware.sistema.estoque.estoque;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.transform.ResultTransformer;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.paginacao.Paginacao;
import br.com.srcsoftware.modular.manager.utilidades.Constantes;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.estoque.estoque.pojo.EstoquePOJO;

public class EstoqueDAOImpl implements EstoqueDAOInterface{

	/**
	 * SELECT
	 * estoque.idproduto,
	 * estoque.produto,
	 * estoque.localizacao,
	 * estoque.quantidadeMinimaEstoque,
	 * sum(COALESCE(estoque.saldo,0)) as saldo,
	 * sum(COALESCE(estoque.custoTotal,0)) as custoTotal,
	 * COALESCE(sum(COALESCE(estoque.custoTotal,0))/sum(COALESCE(estoque.saldo,0)),0) as custoMedio
	 * FROM
	 * (
	 * # MONTADA A PARTE DE ENTRADA
	 * SELECT
	 * estoqueEntrada.idproduto,
	 * estoqueEntrada.produto,
	 * estoqueEntrada.localizacao,
	 * estoqueEntrada.quantidadeMinimaEstoque,
	 * sum(COALESCE(estoqueEntrada.saldo,0)) as saldo,
	 * sum(COALESCE(estoqueEntrada.custoTotal,0)) as custoTotal,
	 * COALESCE(sum(COALESCE(estoqueEntrada.custoTotal,0))/sum(COALESCE(estoqueEntrada.saldo,0)),0) as custoMedio
	 * FROM
	 * (
	 * # ENTRADA VIA NOTA FISCAL VENDA DIRETA
	 * SELECT
	 * p.id as idproduto, p.nomeCompleto as produto, p.localizacao as localizacao, p.quantidadeMinimaEstoque, sum(COALESCE(infvd.quantidade,0)) as saldo,
	 * sum(COALESCE(infvd.precoCusto*infvd.quantidade,0)) as
	 * custoTotal
	 * FROM
	 * agrosystem.produtos as p
	 * LEFT JOIN agrosystem.itensnotafiscalvendadireta as infvd ON p.id = infvd.idProduto
	 * INNER JOIN agrosystem.notasfiscalvendadireta as nfvd ON infvd.idNotaFiscalVendaDireta = nfvd.id
	 * where 1=1
	 * #and p.id = :idProdutoParam
	 * group by p.id
	 * 
	 * UNION ALL
	 * 
	 * # ENTRADA VIA NOTA FISCAL VENDA ENTREGA PEDIDO
	 * SELECT
	 * p.id as idproduto, p.nomeCompleto as produto, p.localizacao as localizacao, p.quantidadeMinimaEstoque, sum(COALESCE(infv.quantidade,0)) as saldo,
	 * sum(COALESCE(infv.precoCusto*infv.quantidade,0)) as
	 * custoTotal
	 * FROM
	 * agrosystem.produtos as p
	 * LEFT JOIN agrosystem.itenspedido as ip ON p.id = ip.idProduto
	 * LEFT JOIN agrosystem.itensnotafiscalvenda as infv ON ip.id = infv.idItemPedido
	 * INNER JOIN agrosystem.notasfiscalvenda as nfv ON infv.idNotaEntrega = nfv.id
	 * where 1=1
	 * and nfv.tipo = 'Venda'
	 * #and p.id = :idProdutoParam
	 * group by p.id
	 * 
	 * UNION ALL
	 * 
	 * # ENTRADA VIA CUPOM
	 * SELECT
	 * p.id as idproduto, p.nomeCompleto as produto, p.localizacao as localizacao, p.quantidadeMinimaEstoque, sum(COALESCE(ic.quantidade,0)) as saldo, sum(COALESCE(ic.precoCusto*ic.quantidade,0)) as
	 * custoTotal
	 * FROM
	 * agrosystem.produtos as p
	 * LEFT JOIN agrosystem.itenscupom as ic ON p.id = ic.idProduto
	 * where 1=1
	 * #and p.id = :idProdutoParam
	 * group by p.id
	 * 
	 * UNION ALL
	 * 
	 * # ENTRADA VIA NOTA FISCAL VENDA ENTREGA SIMPLES REMESSA
	 * SELECT
	 * p.id as idproduto, p.nomeCompleto as produto, p.localizacao as localizacao, p.quantidadeMinimaEstoque, sum(COALESCE(infsr.quantidade,0)) as saldo,
	 * sum(COALESCE(infsr.precoCusto*infsr.quantidade,0)) as
	 * custoTotal
	 * FROM
	 * agrosystem.produtos as p
	 * LEFT JOIN agrosystem.itenspedido as ip ON p.id = ip.idProduto
	 * LEFT JOIN agrosystem.itensnotafiscalvenda as infv ON ip.id = infv.idItemPedido
	 * INNER JOIN agrosystem.itenssimplesremessa as infsr ON infv.id = infsr.idItemNotaFiscalVenda
	 * where 1=1
	 * #and p.id = :idProdutoParam
	 * group by p.id
	 * 
	 * ) as estoqueEntrada
	 * group by estoqueEntrada.idproduto
	 * 
	 * 
	 * UNION ALL ##################################################
	 * 
	 * 
	 * # MONTADA A PARTE DE SAIDA
	 * SELECT
	 * estoqueSaida.idproduto,
	 * estoqueSaida.produto,
	 * estoqueSaida.localizacao,
	 * estoqueSaida.quantidadeMinimaEstoque,
	 * sum(COALESCE(estoqueSaida.saldo,0)*-1) as saldo,
	 * sum(COALESCE(estoqueSaida.custoTotal,0)*-1) as custoTotal,
	 * COALESCE(sum(COALESCE(estoqueSaida.custoTotal,0))/sum(COALESCE(estoqueSaida.saldo,0))*-1,0) as custoMedio
	 * FROM
	 * (
	 * 
	 * # SAIDA VIA APLICACAO
	 * SELECT
	 * p.id as idproduto, p.nomeCompleto as produto, p.localizacao as localizacao, p.quantidadeMinimaEstoque, sum(COALESCE(ap.quantidade,0)) as saldo, sum(COALESCE(ap.custoTotal,0)) as custoTotal
	 * FROM
	 * agrosystem.produtos as p
	 * LEFT JOIN agrosystem.aplicacoes as ap ON p.id = ap.idProduto
	 * where 1=1
	 * #and p.id = :idProdutoParam
	 * group by p.id
	 * 
	 * UNION ALL
	 * 
	 * # SAIDA VIA ABASTECIMENTO
	 * SELECT
	 * p.id as idproduto, p.nomeCompleto as produto, p.localizacao as localizacao, p.quantidadeMinimaEstoque, sum(COALESCE(ab.quantidade,0)) as saldo, sum(COALESCE(ab.custoTotal,0)) as custoTotal
	 * FROM
	 * agrosystem.produtos as p
	 * LEFT JOIN agrosystem.abastecimentos as ab ON p.id = ab.idProduto
	 * where 1=1
	 * #and p.id = :idProdutoParam
	 * group by p.id
	 * 
	 * UNION ALL
	 * 
	 * # SAIDA VIA ITENS
	 * SELECT
	 * p.id as idproduto, p.nomeCompleto as produto, p.localizacao as localizacao, p.quantidadeMinimaEstoque, sum(COALESCE(it.quantidade,0)) as saldo, sum(COALESCE(it.custoTotal,0)) as custoTotal
	 * FROM
	 * agrosystem.produtos as p
	 * LEFT JOIN agrosystem.itens as it ON p.id = it.idProduto
	 * where 1=1
	 * #and p.id = :idProdutoParam
	 * group by p.id
	 * 
	 * ) as estoqueSaida
	 * group by estoqueSaida.idproduto
	 * 
	 * ) as estoque
	 * group by estoque.idproduto
	 * 
	 * Polimorfico
	 * 
	 * @see br.com.srcsoftware.sistema.estoque.EstoqueDAOInterface#filtrar(java.lang.Long)
	 */
	@Override
	public List< EstoquePOJO > filtrar( Paginacao paginacao, Long idProduto, String nomeProduto, String localizacaoProduto, Long idMarca, Long idTipo, String negativos ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			final StringBuffer SQL = new StringBuffer();

			SQL.append( " Select * from  (" );

			SQL.append( " SELECT " );
			SQL.append( " 	estoque.idproduto, " );
			SQL.append( " 	estoque.produto, " );
			SQL.append( " 	estoque.localizacao, " );
			SQL.append( " 	estoque.quantidadeMinimaEstoque, " );
			SQL.append( " 	sum(COALESCE(estoque.saldo,0)) as saldo, " );
			SQL.append( " 	sum(COALESCE(estoque.custoTotal,0)) as custoTotal, " );
			SQL.append( " 	COALESCE(sum(COALESCE(estoque.custoTotal,0))/sum(COALESCE(estoque.saldo,0)),0) as custoMedio " );
			SQL.append( " FROM " );
			SQL.append( " ( " );
			SQL.append( this.montarSelectEntradasEstoque( idProduto, nomeProduto, localizacaoProduto, idMarca, idTipo ).toString() );
			SQL.append( " UNION ALL " );
			SQL.append( this.montarSelectSaidasEstoque( idProduto, nomeProduto, localizacaoProduto, idMarca, idTipo ).toString() );
			SQL.append( " ) as estoque " );
			SQL.append( " group by estoque.produto " );

			SQL.append( ") resultado WHERE 1=1 " );

			if ( !Utilidades.isNuloOuVazio( negativos ) && negativos.equals( "true" ) ) {
				SQL.append( " AND saldo < 0 " );
			}

			HashMap< String, Object > parametros = new HashMap<>();

			if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
				parametros.put( "idProdutoParam", idProduto );
			}
			if ( !Utilidades.isNuloOuVazio( nomeProduto ) ) {
				parametros.put( "nomeProdutoParam", nomeProduto.concat( "%" ) );
			}
			if ( !Utilidades.isNuloOuVazio( localizacaoProduto ) ) {
				parametros.put( "localizacaoProdutoParam", localizacaoProduto );
			}
			if ( !Utilidades.isNuloOuVazio( idMarca ) ) {
				parametros.put( "idMarcaProdutoParam", idMarca );
			}
			if ( !Utilidades.isNuloOuVazio( idTipo ) ) {
				parametros.put( "idTipoProdutoParam", idTipo );
			}

			Query query = hibernate.getSessaoCorrente().createNativeQuery( SQL.toString() ).setResultTransformer( new ResultTransformer(){
				@Override
				public Object transformTuple( Object[ ] tuple, String[ ] aliases ) {
					String idPoduto = tuple[ 0 ].toString();
					String nomeCompletoProduto = tuple[ 1 ].toString();
					String localizacao = tuple[ 2 ].toString();
					BigDecimal quantidadeMinima = new BigDecimal( tuple[ 3 ].toString() ).setScale( 2, BigDecimal.ROUND_FLOOR );
					BigDecimal saldo = new BigDecimal( tuple[ 4 ].toString() ).setScale( 2, BigDecimal.ROUND_FLOOR );
					BigDecimal custoTotal = new BigDecimal( tuple[ 5 ].toString() ).setScale( 2, BigDecimal.ROUND_FLOOR );
					BigDecimal custoMedio = new BigDecimal( tuple[ 6 ].toString() ).setScale( 2, BigDecimal.ROUND_FLOOR );

					return new EstoquePOJO( idPoduto, nomeCompletoProduto, localizacao, Utilidades.parseBigDecimal( quantidadeMinima ), Utilidades.parseBigDecimal( saldo ), Utilidades.parseBigDecimal( custoTotal ), Utilidades.parseBigDecimal( custoMedio ) );
				}

				@SuppressWarnings( "rawtypes" )
				@Override
				public List transformList( List collection ) {
					return collection;
				}
			} );

			// Paginação
			if ( paginacao != null ) {
				paginacao.setTotalRegistros( filtrarMaximoRegistroPaginacao( idProduto, nomeProduto, localizacaoProduto, idMarca, idTipo, negativos ) );

				query.setMaxResults( Paginacao.QUANTIDADE_REGISTROS_EXIBIR );
				query.setFirstResult( paginacao.prepararInicial( paginacao.getTotalRegistros() ).intValue() );
			}

			if ( !Utilidades.isNuloOuVazio( parametros ) ) {
				for ( String key : parametros.keySet() ) {
					query.setParameter( key, parametros.get( key ) );
				}
			}

			List< EstoquePOJO > encontrados = query.getResultList();

			hibernate.commitTransacao();

			return encontrados;

		} catch ( NoResultException e ) {
			hibernate.rollbackTransacao();
			return null;
		} catch ( ApplicationException e ) {
			hibernate.rollbackTransacao();
			throw e;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

	private StringBuilder montarSelectEntradasEstoque( Long idProduto, String nomeProduto, String localizacaoProduto, Long idMarca, Long idTipo ) {
		final StringBuilder SQL = new StringBuilder();

		// MONTADA A PARTE DE ENTRADA
		SQL.append( " SELECT " );
		SQL.append( " 	estoqueEntrada.idproduto, " );
		SQL.append( " 	estoqueEntrada.produto, " );
		SQL.append( " 	estoqueEntrada.localizacao, " );
		SQL.append( " 	estoqueEntrada.quantidadeMinimaEstoque, " );
		SQL.append( " 	sum(COALESCE(estoqueEntrada.saldo,0)) as saldo, " );
		SQL.append( " 	sum(COALESCE(estoqueEntrada.custoTotal,0)) as custoTotal, " );
		SQL.append( " 	COALESCE(sum(COALESCE(estoqueEntrada.custoTotal,0))/sum(COALESCE(estoqueEntrada.saldo,0)),0) as custoMedio " );
		SQL.append( " FROM " );
		SQL.append( " ( " );

		SQL.append( this.montarSubSelectsEntradasEstoque( idProduto, nomeProduto, localizacaoProduto, idMarca, idTipo ).toString() );

		SQL.append( " ) as estoqueEntrada " );
		SQL.append( " group by estoqueEntrada.idproduto " );

		return SQL;
	}

	private StringBuilder montarSubSelectsEntradasEstoque( Long idProduto, String nomeProduto, String localizacaoProduto, Long idMarca, Long idTipo ) {
		final StringBuilder SQL = new StringBuilder();

		final StringBuilder RESTRICOES = new StringBuilder();
		if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
			RESTRICOES.append( " 	and p.id = :idProdutoParam " );
		}
		if ( !Utilidades.isNuloOuVazio( nomeProduto ) ) {
			RESTRICOES.append( " 	and p.nomeCompleto like :nomeProdutoParam " );
		}
		if ( !Utilidades.isNuloOuVazio( localizacaoProduto ) ) {
			RESTRICOES.append( " 	and p.localizacao = :localizacaoProdutoParam " );
		}
		if ( !Utilidades.isNuloOuVazio( idMarca ) ) {
			RESTRICOES.append( " 	and p.idMarca = :idMarcaProdutoParam " );
		}
		if ( !Utilidades.isNuloOuVazio( idTipo ) ) {
			RESTRICOES.append( " 	and p.idTipo = :idTipoProdutoParam " );
		}

		// ENTRADA VIA NOTA FISCAL VENDA DIRETA
		SQL.append( " SELECT " );
		SQL.append( " 		p.id as idproduto, p.nomeCompleto as produto, p.localizacao as localizacao, p.quantidadeMinimaEstoque, sum(COALESCE(infvd.quantidade,0)) as saldo, sum(COALESCE(infvd.precoCusto*infvd.quantidade,0)) as custoTotal " );
		SQL.append( " 	FROM " );
		SQL.append( Constantes.SCHEMA.concat( ".sistema_produtos as p " ) );
		SQL.append( "       LEFT JOIN " + Constantes.SCHEMA.concat( ".sistema_itensnotafiscalvendadireta as infvd ON p.id = infvd.idProduto " ) );
		SQL.append( " 		INNER JOIN " + Constantes.SCHEMA.concat( ".sistema_notasfiscalvendadireta as nfvd ON infvd.idNotaFiscalVendaDireta = nfvd.id " ) );
		SQL.append( "    where  1=1 " );
		SQL.append( RESTRICOES.toString() );
		SQL.append( " 		group by p.id " );

		SQL.append( " UNION ALL " );

		// ENTRADA VIA NOTA FISCAL VENDA ENTREGA PEDIDO
		SQL.append( " SELECT  " );
		SQL.append( " 		p.id as idproduto, p.nomeCompleto as produto, p.localizacao as localizacao, p.quantidadeMinimaEstoque, sum(COALESCE(infv.quantidade,0)) as saldo, sum(COALESCE(infv.precoCusto*infv.quantidade,0)) as custoTotal " );
		SQL.append( " 	FROM " );
		SQL.append( Constantes.SCHEMA.concat( ".sistema_produtos as p  " ) );
		SQL.append( "         LEFT JOIN  " + Constantes.SCHEMA.concat( ".sistema_itenspedido as ip ON p.id = ip.idProduto " ) );
		SQL.append( "         LEFT JOIN  " + Constantes.SCHEMA.concat( ".sistema_itensnotafiscalvenda as infv ON ip.id = infv.idItemPedido " ) );
		SQL.append( " 		INNER JOIN  " + Constantes.SCHEMA.concat( ".sistema_notasfiscalvenda as nfv ON infv.idNotaEntrega = nfv.id " ) );
		SQL.append( "    where  1=1 " );
		SQL.append( " 		and nfv.tipo = 'Venda' " );
		SQL.append( RESTRICOES.toString() );
		SQL.append( " 		group by p.id " );

		SQL.append( " UNION ALL " );

		// ENTRADA VIA CUPOM
		SQL.append( " SELECT  " );
		SQL.append( " 		p.id as idproduto, p.nomeCompleto as produto, p.localizacao as localizacao, p.quantidadeMinimaEstoque, sum(COALESCE(ic.quantidade,0)) as saldo, sum(COALESCE(ic.precoCusto*ic.quantidade,0)) as custoTotal " );
		SQL.append( " 	FROM " );
		SQL.append( Constantes.SCHEMA.concat( ".sistema_produtos as p " ) );
		SQL.append( " 		LEFT JOIN  " + Constantes.SCHEMA.concat( ".sistema_itenscupom as ic ON p.id = ic.idProduto " ) );
		SQL.append( "    where  1=1 " );
		SQL.append( RESTRICOES.toString() );
		SQL.append( " 		group by p.id " );

		SQL.append( " UNION ALL " );

		// ENTRADA VIA NOTA FISCAL VENDA ENTREGA SIMPLES REMESSA
		SQL.append( " SELECT " );
		SQL.append( " 		p.id as idproduto, p.nomeCompleto as produto, p.localizacao as localizacao, p.quantidadeMinimaEstoque, sum(COALESCE(infsr.quantidade,0)) as saldo, sum(COALESCE(infsr.precoCusto*infsr.quantidade,0)) as custoTotal " );
		SQL.append( " 	FROM " );
		SQL.append( Constantes.SCHEMA.concat( ".sistema_produtos as p " ) );
		SQL.append( "         LEFT JOIN  " + Constantes.SCHEMA.concat( ".sistema_itenspedido as ip ON p.id = ip.idProduto " ) );
		SQL.append( "         LEFT JOIN  " + Constantes.SCHEMA.concat( ".sistema_itensnotafiscalvenda as infv ON ip.id = infv.idItemPedido " ) );
		SQL.append( " 		INNER JOIN  " + Constantes.SCHEMA.concat( ".sistema_itenssimplesremessa as infsr ON infv.id = infsr.idItemNotaFiscalVenda " ) );
		SQL.append( "    where  1=1 " );
		SQL.append( RESTRICOES.toString() );
		SQL.append( " 		group by p.id " );

		return SQL;
	}

	private StringBuilder montarSelectSaidasEstoque( Long idProduto, String nomeProduto, String localizacaoProduto, Long idMarca, Long idTipo ) {
		final StringBuilder SQL = new StringBuilder();

		// MONTADA A PARTE DE SAIDA
		SQL.append( " SELECT " );
		SQL.append( " 	estoqueSaida.idproduto, " );
		SQL.append( " 	estoqueSaida.produto, " );
		SQL.append( " 	estoqueSaida.localizacao, " );
		SQL.append( " 	estoqueSaida.quantidadeMinimaEstoque, " );
		SQL.append( " 	sum(COALESCE(estoqueSaida.saldo,0)*-1) as saldo, " );
		SQL.append( " 	sum(COALESCE(estoqueSaida.custoTotal,0)*-1) as custoTotal, " );
		SQL.append( " 	COALESCE(sum(COALESCE(estoqueSaida.custoTotal,0))/sum(COALESCE(estoqueSaida.saldo,0))*-1,0) as custoMedio " );
		SQL.append( " FROM " );
		SQL.append( " ( " );

		SQL.append( this.montarSubSelectsSaidasEstoque( idProduto, nomeProduto, localizacaoProduto, idMarca, idTipo ).toString() );

		SQL.append( " ) as estoqueSaida " );
		SQL.append( " group by estoqueSaida.idproduto " );

		return SQL;
	}

	private StringBuilder montarSubSelectsSaidasEstoque( Long idProduto, String nomeProduto, String localizacaoProduto, Long idMarca, Long idTipo ) {
		final StringBuilder SQL = new StringBuilder();

		final StringBuilder RESTRICOES = new StringBuilder();
		if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
			RESTRICOES.append( " 	and p.id = :idProdutoParam " );
		}
		if ( !Utilidades.isNuloOuVazio( nomeProduto ) ) {
			RESTRICOES.append( " 	and p.nomeCompleto like :nomeProdutoParam " );
		}
		if ( !Utilidades.isNuloOuVazio( localizacaoProduto ) ) {
			RESTRICOES.append( " 	and p.localizacao = :localizacaoProdutoParam " );
		}
		if ( !Utilidades.isNuloOuVazio( idMarca ) ) {
			RESTRICOES.append( " 	and p.idMarca = :idMarcaProdutoParam " );
		}
		if ( !Utilidades.isNuloOuVazio( idTipo ) ) {
			RESTRICOES.append( " 	and p.idTipo = :idTipoProdutoParam " );
		}

		// SAIDA VIA APLICACAO
		SQL.append( " SELECT " );
		SQL.append( " 		p.id as idproduto, p.nomeCompleto as produto, p.localizacao as localizacao, p.quantidadeMinimaEstoque, sum(COALESCE(ap.quantidade,0)) as saldo, sum(COALESCE(ap.custoTotal,0)) as custoTotal " );
		SQL.append( " 	FROM " );
		SQL.append( Constantes.SCHEMA.concat( ".sistema_produtos as p " ) );
		SQL.append( "         LEFT JOIN  " + Constantes.SCHEMA.concat( ".sistema_aplicacoes as ap ON p.id = ap.idProduto " ) );
		SQL.append( "    where  1=1 " );
		SQL.append( RESTRICOES.toString() );
		SQL.append( " 		group by p.id " );

		SQL.append( " UNION ALL " );

		// SAIDA VIA ABASTECIMENTO
		SQL.append( " SELECT " );
		SQL.append( " 		p.id as idproduto, p.nomeCompleto as produto, p.localizacao as localizacao, p.quantidadeMinimaEstoque, sum(COALESCE(ab.quantidade,0)) as saldo, sum(COALESCE(ab.custoTotal,0)) as custoTotal " );
		SQL.append( " 	FROM " );
		SQL.append( Constantes.SCHEMA.concat( ".sistema_produtos as p " ) );
		SQL.append( "         LEFT JOIN  " + Constantes.SCHEMA.concat( ".sistema_abastecimentos as ab ON p.id = ab.idProduto " ) );
		SQL.append( "    where  1=1 " );
		SQL.append( RESTRICOES.toString() );
		SQL.append( " 		group by p.id " );

		SQL.append( " UNION ALL " );

		// SAIDA VIA ITENS
		SQL.append( " SELECT " );
		SQL.append( " 		p.id as idproduto, p.nomeCompleto as produto, p.localizacao as localizacao, p.quantidadeMinimaEstoque, sum(COALESCE(it.quantidade,0)) as saldo, sum(COALESCE(it.custoTotal,0)) as custoTotal " );
		SQL.append( " 	FROM " );
		SQL.append( Constantes.SCHEMA.concat( ".sistema_produtos as p " ) );
		SQL.append( "         LEFT JOIN  " + Constantes.SCHEMA.concat( ".sistema_itens as it ON p.id = it.idProduto " ) );
		SQL.append( "    where  1=1 " );
		SQL.append( RESTRICOES.toString() );
		SQL.append( " 		group by p.id " );

		return SQL;

	}

	public Long filtrarMaximoRegistroPaginacao( Long idProduto, String nomeProduto, String localizacaoProduto, Long idMarca, Long idTipo, String negativos ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			final StringBuffer SQL = new StringBuffer();

			SQL.append( " Select count(total.idproduto)  from  ( " );

			SQL.append( " select * " );
			SQL.append( " FROM " );
			SQL.append( " ( " );
			SQL.append( " SELECT " );
			SQL.append( " 	estoque.idproduto, " );
			SQL.append( " 	estoque.produto, " );
			SQL.append( " 	estoque.localizacao, " );
			SQL.append( " 	estoque.quantidadeMinimaEstoque, " );
			SQL.append( " 	sum(COALESCE(estoque.saldo,0)) as saldo, " );
			SQL.append( " 	sum(COALESCE(estoque.custoTotal,0)) as custoTotal, " );
			SQL.append( " 	COALESCE(sum(COALESCE(estoque.custoTotal,0))/sum(COALESCE(estoque.saldo,0)),0) as custoMedio " );
			SQL.append( " FROM " );
			SQL.append( " ( " );
			SQL.append( this.montarSelectEntradasEstoque( idProduto, nomeProduto, localizacaoProduto, idMarca, idTipo ).toString() );
			SQL.append( " UNION ALL " );
			SQL.append( this.montarSelectSaidasEstoque( idProduto, nomeProduto, localizacaoProduto, idMarca, idTipo ).toString() );
			SQL.append( " ) as estoque " );
			SQL.append( " group by estoque.produto " );
			SQL.append( " ) as dados " );
			SQL.append( " ) total " );
			SQL.append( " WHERE 1=1 " );

			if ( !Utilidades.isNuloOuVazio( negativos ) && negativos.equals( "true" ) ) {
				SQL.append( " AND total.saldo < 0 " );
			}

			HashMap< String, Object > parametros = new HashMap<>();

			if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
				parametros.put( "idProdutoParam", idProduto );
			}
			if ( !Utilidades.isNuloOuVazio( nomeProduto ) ) {
				parametros.put( "nomeProdutoParam", nomeProduto.concat( "%" ) );
			}
			if ( !Utilidades.isNuloOuVazio( localizacaoProduto ) ) {
				parametros.put( "localizacaoProdutoParam", localizacaoProduto );
			}
			if ( !Utilidades.isNuloOuVazio( idMarca ) ) {
				parametros.put( "idMarcaProdutoParam", idMarca );
			}
			if ( !Utilidades.isNuloOuVazio( idTipo ) ) {
				parametros.put( "idTipoProdutoParam", idTipo );
			}

			Query query = hibernate.getSessaoCorrente().createNativeQuery( SQL.toString() );

			if ( !Utilidades.isNuloOuVazio( parametros ) ) {
				for ( String key : parametros.keySet() ) {
					query.setParameter( key, parametros.get( key ) );
				}
			}

			BigInteger quantidadeRegistros = (BigInteger) query.getSingleResult();

			hibernate.commitTransacao();

			return quantidadeRegistros.longValue();

		} catch ( NoResultException e ) {
			hibernate.rollbackTransacao();
			return null;
		} catch ( ApplicationException e ) {
			hibernate.rollbackTransacao();
			throw e;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}
}
