package migracao;

import br.com.srcsoftware.connection.HibernateConnection;
import br.com.srcsoftware.migracao.Principal;
import br.com.srcsoftware.migracao.conexao.HibernateConnectionMigracao;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;

public class TestaMigracao{

	public static void main( String[ ] args ) {
		try {
			new Principal().executar();

			HibernateConnection.shutdown();
			HibernateConnectionMigracao.shutdown();
		} catch ( Exception e ) {
			e.printStackTrace();
		} catch ( ApplicationException e ) {
			e.printStackTrace();
		}

	}

}
