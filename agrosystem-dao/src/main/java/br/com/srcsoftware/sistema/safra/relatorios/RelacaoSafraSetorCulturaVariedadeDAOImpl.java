package br.com.srcsoftware.sistema.safra.relatorios;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.transform.ResultTransformer;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.utilidades.Utilidades;

public class RelacaoSafraSetorCulturaVariedadeDAOImpl implements RelacaoSafraSetorCulturaVariedadeDAOInterface{

	@SuppressWarnings( { "serial", "deprecation", "unchecked" } )
	@Override
	public List< RelacaoSafraSetorCulturaVariedadePOJO > filtrarRelacao( StringBuffer SQL, HashMap< String, Object > parametros ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			Query query = hibernate.getSessaoCorrente().createNativeQuery( SQL.toString() ).setResultTransformer( new ResultTransformer(){
				@Override
				public Object transformTuple( Object[ ] tuple, String[ ] aliases ) {
					RelacaoSafraSetorCulturaVariedadePOJO pojo = new RelacaoSafraSetorCulturaVariedadePOJO();

					pojo.setSafra( (String) tuple[ 0 ] );
					pojo.setSetor( (String) tuple[ 1 ] );
					pojo.setVariedade( (String) tuple[ 2 ] );
					pojo.setCultura( (String) tuple[ 3 ] );
					pojo.setAreaAlqueire( ( (BigDecimal) tuple[ 4 ] ).setScale( Utilidades.SCALE_CALCULOS, BigDecimal.ROUND_HALF_EVEN ) );

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

			ArrayList< RelacaoSafraSetorCulturaVariedadePOJO > encontrados = (ArrayList< RelacaoSafraSetorCulturaVariedadePOJO >) query.getResultList();

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
