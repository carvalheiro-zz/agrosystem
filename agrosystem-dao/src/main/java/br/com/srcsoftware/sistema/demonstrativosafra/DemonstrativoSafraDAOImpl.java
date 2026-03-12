package br.com.srcsoftware.sistema.demonstrativosafra;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.transform.ResultTransformer;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;
import br.com.srcsoftware.sistema.demonstrativosafra.custodireto.insumo.CustoDiretoInsumoPOJO;

public class DemonstrativoSafraDAOImpl implements DemonstrativoSafraDAOInterface{

	@SuppressWarnings( { "serial", "deprecation", "unchecked" } )
	@Override
	public List< CustoDiretoInsumoPOJO > filtrarDemonstrativoSafraCustosDiretos( StringBuffer SQL, HashMap< String, Object > parametros ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			//Query query = hibernate.getSessaoCorrente().createNativeQuery( SQL.toString() );
			Query query = hibernate.getSessaoCorrente().createNativeQuery( SQL.toString() ).setResultTransformer( new ResultTransformer(){
				@Override
				public Object transformTuple( Object[ ] tuple, String[ ] aliases ) {
					CustoDiretoInsumoPOJO pojo = new CustoDiretoInsumoPOJO();

					pojo.setNome( (String) tuple[ 0 ] );
					pojo.setCustoTotal( ( (BigDecimal) tuple[ 1 ] ).setScale( Utilidades.SCALE_CALCULOS, BigDecimal.ROUND_HALF_EVEN ) );

					return pojo;
				}

				@SuppressWarnings( "rawtypes" )
				@Override
				public List transformList( List collection ) {
					return collection;
				}
			} );

			for ( String key : parametros.keySet() ) {
				query.setParameter( key, parametros.get( key ) );
			}

			ArrayList< CustoDiretoInsumoPOJO > encontrados = (ArrayList< CustoDiretoInsumoPOJO >) query.getResultList();

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
