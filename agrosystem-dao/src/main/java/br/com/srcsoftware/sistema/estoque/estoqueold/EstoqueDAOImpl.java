package br.com.srcsoftware.sistema.estoque.estoqueold;

public class EstoqueDAOImpl implements EstoqueDAOInterface{

	/**
	 * M�todo respons�vel por informar a QUANTIDADE REAL restantes que ainda n�o foram entregues.
	 * <br />
	 * <b>OBS:</b><br />
	 * Considera apenas como sendo ENTREGUES - Nota Fiscal de Venda / Nota Fiscal de Simples Remessa
	 *
	 * @param idProduto - id do PRODUTO no qual se deseja saber a quantidade que ainda falta entregar.
	 * @param idPedido - id do PEDIDO no qual se deseja saber a quantidade de itens que ainda faltam entregar.
	 * @return BigDecimal - quantidade de item a serem entregues com base na seguinte combinação: Produto, Pedido e Produto/Pedido
	 * @throws ApplicationException
	 *
	 * @author Gabriel Damiani Carvalheiro <gabriel.carvalheiro@gmail.com.br>
	 * @since 23 de mai de 2017 17:50:53
	 * @version 1.0
	 */
	/*@Override
	public BigDecimal calcularValoresRestantesEntregar( Long idProduto, Long idPedido ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();
	
		try {
	
			hibernate.iniciarTransacao();
	
			final StringBuffer SQL = new StringBuffer();
			SQL.append( " 	select	 " );
			SQL.append( " 	  coalesce(totais.qtdPedido,0) - (coalesce(totais.qtdEntregue,0)+coalesce(totais.qtdEntregue2,0))	 " );
			SQL.append( " 	from	 " );
			SQL.append( " 	(select 	 " );
	
			// ITENS PEDIDOS
			SQL.append( " 	(SELECT sum(quantidade) quantidadePedido FROM " + Constantes.SCHEMA + ".itens_pedidos ip	 " );
			SQL.append( " 	where 1=1 " );
			if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
				SQL.append( " and	ip.idProduto = :idProdutoParam	 " );
			}
			if ( !Utilidades.isNuloOuVazio( idPedido ) ) {
				SQL.append( " 	and ip.idPedido = :idPedidoParam " );
			}
			if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
				SQL.append( " 	group by ip.idProduto) qtdPedido,	 " );
			} else if ( !Utilidades.isNuloOuVazio( idPedido ) ) {
				SQL.append( " 	group by ip.idPedido) qtdPedido,	 " );
			}
	
			// NOTAS FISCAIS VENDAS
			SQL.append( " 	(SELECT sum(infv.quantidade) quantidadeEntregue FROM	 " );
			SQL.append( " 	 " + Constantes.SCHEMA + ".notas_fiscais_vendas nfv,	 " );
			SQL.append( " 	 " + Constantes.SCHEMA + ".itens_notas_fiscais_vendas infv,	 " );
			SQL.append( " 	 " + Constantes.SCHEMA + ".itens_pedidos ip	 " );
			SQL.append( " 	where 1=1	 " );
			if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
				SQL.append( " and	ip.idProduto = :idProdutoParam	 " );
			}
			if ( !Utilidades.isNuloOuVazio( idPedido ) ) {
				SQL.append( " 	and ip.idPedido = :idPedidoParam " );
			}
			SQL.append( " 	and (nfv.tipo = 'Venda')  " );
	
			SQL.append( " 	and infv.idNotaEntrega = nfv.id	 " );
			SQL.append( " 	and infv.idItemPedido = ip.id	 " );
			if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
				SQL.append( " 	group by ip.idProduto) qtdEntregue,	 " );
			} else if ( !Utilidades.isNuloOuVazio( idPedido ) ) {
				SQL.append( " 	group by ip.idPedido) qtdEntregue,	 " );
			}
	
			// NOTAS FISCAIS VENDAS DE SIMPLES REMESSA
			SQL.append( " 	(SELECT sum(isr.quantidade) quantidadeEntregue FROM	 " );
			SQL.append( " 	 " + Constantes.SCHEMA + ".notas_simples_remessa nsr,	 " );
			SQL.append( " 	 " + Constantes.SCHEMA + ".itens_simples_remessas isr,	 " );
			SQL.append( " 	 " + Constantes.SCHEMA + ".itens_notas_fiscais_vendas infv,	 " );
			SQL.append( " 	 " + Constantes.SCHEMA + ".itens_pedidos ip	 " );
			SQL.append( " 	where 1=1	 " );
			if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
				SQL.append( " and	ip.idProduto = :idProdutoParam	 " );
			}
			if ( !Utilidades.isNuloOuVazio( idPedido ) ) {
				SQL.append( " 	and ip.idPedido = :idPedidoParam " );
			}
			SQL.append( " 	and isr.idNotaSimplesRemessa = nsr.id	 " );
			SQL.append( " 	and isr.idItemNotaFiscalVenda = infv.id	 " );
			SQL.append( " 	and infv.idItemPedido = ip.id	 " );
			if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
				SQL.append( " 	group by ip.idProduto) qtdEntregue2	 " );
			} else if ( !Utilidades.isNuloOuVazio( idPedido ) ) {
				SQL.append( " 	group by ip.idPedido) qtdEntregue2	 " );
			}
	
			SQL.append( " 	from dual) totais	 " );
	
			HashMap< String, Object > parametros = new HashMap<>();
			if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
				parametros.put( "idProdutoParam", idProduto );
			}
			if ( !Utilidades.isNuloOuVazio( idPedido ) ) {
				parametros.put( "idPedidoParam", idPedido );
			}
	
			Query query = hibernate.getSessaoCorrente().createNativeQuery( SQL.toString() );
	
			for ( String key : parametros.keySet() ) {
				query.setParameter( key, parametros.get( key ) );
			}
	
			BigDecimal resultado = (BigDecimal) query.getSingleResult();
	
			hibernate.commitTransacao();
	
			return resultado;
	
		} catch ( ApplicationException e ) {
			hibernate.rollbackTransacao();
			throw e;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();
			throw new ApplicationException( "Erro inesperado[999]" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}*/

	/**
	 * #CALCULO#
	 * select
	 * coalesce(totais.qtdPedido,0) - (coalesce(totais.qtdEntregue,0)+coalesce(totais.qtdEntregue2,0))
	 * from
	 * 
	 * (select
	 * 
	 * (SELECT sum(quantidade) quantidadePedido FROM itens_pedidos ip
	 * where 1=1 ip.idProduto = 7
	 * and ip.idPedido = 20
	 * group by ip.idProduto) qtdPedido,
	 * 
	 * 
	 * (SELECT sum( infv.quantidade) quantidadeEntregue FROM
	 * notas_fiscais_vendas nfv,
	 * itens_notas_fiscais_vendas infv,
	 * itens_pedidos ip
	 * where 1=1
	 * ip.idProduto = 7
	 * and ip.idPedido = 20
	 * and nfv.tipo = 'Venda'
	 * and infv.idNotaEntrega = nfv.id
	 * and infv.idItemPedido = ip.id
	 * group by ip.idProduto) qtdEntregue,
	 * 
	 * (SELECT sum(isr.quantidade) quantidadeEntregue FROM
	 * notas_simples_remessa nsr,
	 * itens_simples_remessas isr,
	 * itens_notas_fiscais_vendas infv,
	 * itens_pedidos ip
	 * where 1=1
	 * ip.idProduto = 7
	 * and ip.idPedido = 20
	 * and isr.idNotaSimplesRemessa = nsr.id
	 * and isr.idItemNotaFiscalVenda = infv.id
	 * and infv.idItemPedido = ip.id
	 * group by ip.idProduto) qtdEntregue2
	 * 
	 * from dual) totais
	 * Polimorfico
	 * 
	 * @see br.com.srcsoftware.sistema.estoque.estoque.EstoqueDAOInterface#calcularValoresRestantesEntregar(java.lang.Long)
	 */
	/*@Override
	public BigDecimal calcularValoresRestantesEntregar( Long idProduto, Long idPedido, Long idNotaFiscalVenda, boolean considerarFutura ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();
	
		try {
	
			hibernate.iniciarTransacao();
	
			final StringBuffer SQL = new StringBuffer();
			SQL.append( " 	select	 " );
			//SQL.append( " 	  coalesce(totais.qtdPedido,0) - (coalesce(totais.qtdEntregue,0))	 " );
			SQL.append( " 	  coalesce(totais.qtdPedido,0) - (coalesce(totais.qtdEntregue,0)+coalesce(totais.qtdEntregue2,0))	 " );
			SQL.append( " 	from	 " );
			SQL.append( " 	(select 	 " );
	
			// ITENS PEDIDOS
			SQL.append( " 	(SELECT sum(quantidade) quantidadePedido FROM " + Constantes.SCHEMA + ".itens_pedidos ip	 " );
			SQL.append( " 	where 1=1 " );
			if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
				SQL.append( " and	ip.idProduto = :idProdutoParam	 " );
			}
			if ( !Utilidades.isNuloOuVazio( idPedido ) ) {
				SQL.append( " 	and ip.idPedido = :idPedidoParam " );
			}
			if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
				SQL.append( " 	group by ip.idProduto) qtdPedido,	 " );
			} else if ( !Utilidades.isNuloOuVazio( idPedido ) ) {
				SQL.append( " 	group by ip.idPedido) qtdPedido,	 " );
			}
	
			// NOTAS FISCAIS VENDAS
			SQL.append( " 	(SELECT sum(infv.quantidade) quantidadeEntregue FROM	 " );
			SQL.append( " 	 " + Constantes.SCHEMA + ".notas_fiscais_vendas nfv,	 " );
			SQL.append( " 	 " + Constantes.SCHEMA + ".itens_notas_fiscais_vendas infv,	 " );
			SQL.append( " 	 " + Constantes.SCHEMA + ".itens_pedidos ip	 " );
			SQL.append( " 	where 1=1	 " );
			if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
				SQL.append( " and	ip.idProduto = :idProdutoParam	 " );
			}
			if ( !Utilidades.isNuloOuVazio( idPedido ) ) {
				SQL.append( " 	and ip.idPedido = :idPedidoParam " );
			}
			if ( !Utilidades.isNuloOuVazio( idNotaFiscalVenda ) ) {
				SQL.append( " 	and nfv.id = :idNotaFiscalVendaParam " );
			}
			SQL.append( " 	and (nfv.tipo = 'Venda')  " );
	
			SQL.append( " 	and infv.idNotaEntrega = nfv.id	 " );
			SQL.append( " 	and infv.idItemPedido = ip.id	 " );
			if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
				//if ( considerarFutura ) {
				SQL.append( " 	group by ip.idProduto) qtdEntregue,	 " );
				//} else {
				//	SQL.append( " 	group by ip.idProduto) qtdEntregue	 " );
				//}
			} else if ( !Utilidades.isNuloOuVazio( idPedido ) ) {
				//if ( considerarFutura ) {
				SQL.append( " 	group by ip.idPedido) qtdEntregue,	 " );
				//} else {
				//	SQL.append( " 	group by ip.idPedido) qtdEntregue	 " );
				//}
			}
	
			if ( considerarFutura ) {
				// NOTAS FISCAIS FUTURAS
				SQL.append( " 	(SELECT sum(infv.quantidade) quantidadeEntregue FROM	 " );
				SQL.append( " 	 " + Constantes.SCHEMA + ".notas_fiscais_vendas nfv,	 " );
				SQL.append( " 	 " + Constantes.SCHEMA + ".itens_notas_fiscais_vendas infv,	 " );
				SQL.append( " 	 " + Constantes.SCHEMA + ".itens_pedidos ip	 " );
				SQL.append( " 	where 1=1	 " );
				if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
					SQL.append( " and	ip.idProduto = :idProdutoParam	 " );
				}
				if ( !Utilidades.isNuloOuVazio( idPedido ) ) {
					SQL.append( " 	and ip.idPedido = :idPedidoParam " );
				}
				if ( !Utilidades.isNuloOuVazio( idNotaFiscalVenda ) ) {
					SQL.append( " 	and nfv.id = :idNotaFiscalVendaParam " );
				}
				SQL.append( " 	and (nfv.tipo = 'Futura')  " );
	
				SQL.append( " 	and infv.idNotaEntrega = nfv.id	 " );
				SQL.append( " 	and infv.idItemPedido = ip.id	 " );
				if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
					SQL.append( " 	group by ip.idProduto) qtdEntregue2	 " );
					//SQL.append( " 	group by ip.idProduto) qtdEntregue,	 " );
				} else if ( !Utilidades.isNuloOuVazio( idPedido ) ) {
					SQL.append( " 	group by ip.idPedido) qtdEntregue2	 " );
					//SQL.append( " 	group by ip.idPedido) qtdEntregue,	 " );
				}
	
			} else {
				SQL.append( " 0 qtdEntregue2 " );
			}
			
			SQL.append( " 	from dual) totais	 " );
	
			HashMap< String, Object > parametros = new HashMap<>();
			if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
				parametros.put( "idProdutoParam", idProduto );
			}
			if ( !Utilidades.isNuloOuVazio( idPedido ) ) {
				parametros.put( "idPedidoParam", idPedido );
			}
			if ( !Utilidades.isNuloOuVazio( idNotaFiscalVenda ) ) {
				parametros.put( "idNotaFiscalVendaParam", idNotaFiscalVenda );
			}
	
			Query query = hibernate.getSessaoCorrente().createNativeQuery( SQL.toString() );
	
			for ( String key : parametros.keySet() ) {
				query.setParameter( key, parametros.get( key ) );
			}
	
			BigDecimal resultado = (BigDecimal) query.getSingleResult();
	
			hibernate.commitTransacao();
	
			return resultado;
	
		} catch ( ApplicationException e ) {
			hibernate.rollbackTransacao();
			throw e;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();
			throw new ApplicationException( "Erro inesperado[999]" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}*/

	/**
	 * #RESTANTES SIMPLES REMESSA#
	 * #CALCULO#
	 * select
	 * (coalesce(totais.qtdEntregue,0)) - coalesce(totais.qtdSimpleRemessa,0)
	 * from
	 * 
	 * (select
	 * 
	 * (SELECT sum( infv.quantidade) quantidadeEntregue FROM
	 * notas_fiscais_vendas nfv,
	 * itens_notas_fiscais_vendas infv,
	 * itens_pedidos ip
	 * where 1=1
	 * and ip.idProduto = 8
	 * #and ip.idPedido = 20
	 * and nfv.id = 34
	 * and (nfv.tipo = 'Futura')
	 * and infv.idNotaEntrega = nfv.id
	 * and infv.idItemPedido = ip.id
	 * #group by ip.idPedido) qtdEntregue,
	 * group by ip.idProduto) qtdEntregue,
	 * 
	 * (SELECT sum(isr.quantidade) quantidadeEntregue FROM
	 * notas_simples_remessa nsr,
	 * itens_simples_remessas isr,
	 * itens_notas_fiscais_vendas infv,
	 * itens_pedidos ip
	 * where 1=1
	 * and ip.idProduto = 8
	 * #and ip.idPedido = 20
	 * and infv.idNotaEntrega = 34
	 * and isr.idNotaSimplesRemessa = nsr.id
	 * and isr.idItemNotaFiscalVenda = infv.id
	 * and infv.idItemPedido = ip.id
	 * #group by ip.idPedido) qtdEntregue2
	 * group by ip.idProduto) qtdSimpleRemessa
	 * 
	 * from dual) totais
	 * 
	 *
	 * @param idProduto
	 * @param idNotaFiscalVendaFutura
	 * @param considerarFutura
	 * @return
	 * @throws ApplicationException
	 *
	 * @author Gabriel Damiani Carvalheiro <gabriel.carvalheiro@gmail.com.br>
	 * @since 16 de mai de 2017 11:12:34
	 * @version 1.0
	 */
	/*@Override
	public BigDecimal calcularValoresRestantesEntregarSimplesRemessa( Long idProduto, Long idNotaFiscalVendaFutura ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();
	
		try {
	
			hibernate.iniciarTransacao();
	
			final StringBuffer SQL = new StringBuffer();
			SQL.append( " 	select	 " );
			SQL.append( " 	  (coalesce(totais.qtdEntregue,0)) - coalesce(totais.qtdSimpleRemessa,0)	 " );
			SQL.append( " 	from	 " );
			SQL.append( " 	(select 	 " );
	
			SQL.append( " 	(SELECT sum(infv.quantidade) quantidadeEntregue FROM	 " );
			SQL.append( " 	 " + Constantes.SCHEMA + ".notas_fiscais_vendas nfv,	 " );
			SQL.append( " 	 " + Constantes.SCHEMA + ".itens_notas_fiscais_vendas infv,	 " );
			SQL.append( " 	 " + Constantes.SCHEMA + ".itens_pedidos ip	 " );
			SQL.append( " 	where 1=1	 " );
			if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
				SQL.append( " and	ip.idProduto = :idProdutoParam	 " );
			}
			if ( !Utilidades.isNuloOuVazio( idNotaFiscalVendaFutura ) ) {
				SQL.append( " 	and nfv.id = :idNotaFiscalVendaFuturaParam " );
			}
			SQL.append( " 	and (nfv.tipo = 'Futura')  " );
	
			SQL.append( " 	and infv.idNotaEntrega = nfv.id	 " );
			SQL.append( " 	and infv.idItemPedido = ip.id	 " );
			if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
				SQL.append( " 	group by ip.idProduto) qtdEntregue,	 " );
			} else if ( !Utilidades.isNuloOuVazio( idNotaFiscalVendaFutura ) ) {
				SQL.append( " 	group by infv.idNotaEntrega) qtdEntregue,	 " );
			}
	
			SQL.append( " 	(SELECT sum(isr.quantidade) quantidadeEntregue FROM	 " );
			SQL.append( " 	 " + Constantes.SCHEMA + ".notas_simples_remessa nsr,	 " );
			SQL.append( " 	 " + Constantes.SCHEMA + ".itens_simples_remessas isr,	 " );
			SQL.append( " 	 " + Constantes.SCHEMA + ".itens_notas_fiscais_vendas infv,	 " );
			SQL.append( " 	 " + Constantes.SCHEMA + ".itens_pedidos ip	 " );
			SQL.append( " 	where 1=1	 " );
			if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
				SQL.append( " and	ip.idProduto = :idProdutoParam	 " );
			}
			if ( !Utilidades.isNuloOuVazio( idNotaFiscalVendaFutura ) ) {
				SQL.append( " 	and infv.idNotaEntrega = :idNotaFiscalVendaFuturaParam " );
			}
			SQL.append( " 	and isr.idNotaSimplesRemessa = nsr.id	 " );
			SQL.append( " 	and isr.idItemNotaFiscalVenda = infv.id	 " );
			SQL.append( " 	and infv.idItemPedido = ip.id	 " );
			if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
				SQL.append( " 	group by ip.idProduto) qtdSimpleRemessa	 " );
			} else if ( !Utilidades.isNuloOuVazio( idNotaFiscalVendaFutura ) ) {
				SQL.append( " 	group by infv.idNotaEntrega) qtdSimpleRemessa	 " );
			}
	
			SQL.append( " 	from dual) totais	 " );
	
			HashMap< String, Object > parametros = new HashMap<>();
			if ( !Utilidades.isNuloOuVazio( idProduto ) ) {
				parametros.put( "idProdutoParam", idProduto );
			}
			if ( !Utilidades.isNuloOuVazio( idNotaFiscalVendaFutura ) ) {
				parametros.put( "idNotaFiscalVendaFuturaParam", idNotaFiscalVendaFutura );
			}
	
			Query query = hibernate.getSessaoCorrente().createNativeQuery( SQL.toString() );
	
			for ( String key : parametros.keySet() ) {
				query.setParameter( key, parametros.get( key ) );
			}
	
			BigDecimal resultado = (BigDecimal) query.getSingleResult();
	
			hibernate.commitTransacao();
	
			return resultado;
	
		} catch ( ApplicationException e ) {
			hibernate.rollbackTransacao();
			throw e;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();
			throw new ApplicationException( "Erro inesperado[999]" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}*/

}
