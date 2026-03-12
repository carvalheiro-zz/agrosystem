package br.com.srcsoftware.sistema.demonstrativovenda;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.transform.ResultTransformer;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.silo.contratovenda.InformacoesRestanteEntregarPOJO;

public class DemonstrativoVendaDAOImpl implements DemonstrativoVendaDAOInterface{

	@SuppressWarnings( { "serial", "deprecation", "unchecked" } )
	@Override
	public List< InformacoesRestanteEntregarPOJO > filtrarDemonstrativoVenda( StringBuffer SQL, HashMap< String, Object > parametros ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			Query query = hibernate.getSessaoCorrente().createNativeQuery( SQL.toString() ).setResultTransformer( new ResultTransformer(){
				@Override
				public Object transformTuple( Object[ ] tuple, String[ ] aliases ) {
					InformacoesRestanteEntregarPOJO pojo = new InformacoesRestanteEntregarPOJO();

					pojo.setCultura( (String) tuple[ 0 ] );

					BigDecimal quantidadeProduzida = ( (BigDecimal) tuple[ 1 ] ).setScale( Utilidades.SCALE_CALCULOS, BigDecimal.ROUND_HALF_EVEN );
					pojo.setQuantidadeProduzida( Utilidades.parseBigDecimal( quantidadeProduzida ) );

					BigDecimal quantidadeVendida = ( (BigDecimal) tuple[ 2 ] ).setScale( Utilidades.SCALE_CALCULOS, BigDecimal.ROUND_HALF_EVEN );
					pojo.setQuantidadeVendida( Utilidades.parseBigDecimal( quantidadeVendida ) );

					BigDecimal quantidadeEntregue = ( (BigDecimal) tuple[ 3 ] ).setScale( Utilidades.SCALE_CALCULOS, BigDecimal.ROUND_HALF_EVEN );
					pojo.setQuantidadeEntregue( Utilidades.parseBigDecimal( quantidadeEntregue ) );

					BigDecimal quantidadeRestante = ( (BigDecimal) tuple[ 4 ] ).setScale( Utilidades.SCALE_CALCULOS, BigDecimal.ROUND_HALF_EVEN );
					pojo.setQuantidadeRestante( Utilidades.parseBigDecimal( quantidadeRestante ) );

					BigDecimal saldo = ( (BigDecimal) tuple[ 5 ] ).setScale( Utilidades.SCALE_CALCULOS, BigDecimal.ROUND_HALF_EVEN );
					pojo.setSaldo( Utilidades.parseBigDecimal( saldo ) );

					return pojo;
				}
				/*producao.cultura as cultura, 0
				producao.quantidadeProduzida as quantidadeProduzida, 1
				vendida.quantidadeVendida as quantidadeVendida, 2
				entregue.quantidadeEntregue as quantidadeEntregue, 3
				(vendida.quantidadeVendida-entregue.quantidadeEntregue) as quantidadeRestante, 4
				(producao.quantidadeProduzida-vendida.quantidadeVendida) as saldo 5 */

				@SuppressWarnings( "rawtypes" )
				@Override
				public List transformList( List collection ) {
					return collection;
				}
			} );

			for ( String key : parametros.keySet() ) {
				query.setParameter( key, parametros.get( key ) );
			}

			ArrayList< InformacoesRestanteEntregarPOJO > encontrados = (ArrayList< InformacoesRestanteEntregarPOJO >) query.getResultList();

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

}
