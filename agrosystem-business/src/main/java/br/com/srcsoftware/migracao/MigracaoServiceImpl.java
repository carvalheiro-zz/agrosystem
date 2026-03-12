package br.com.srcsoftware.migracao;

import org.apache.log4j.Logger;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;

public class MigracaoServiceImpl implements MigracaoServiceInterface{

	private transient Logger logger = Logger.getLogger( this.getClass().getName() );

	@Override
	public void executar() throws ApplicationException {

		try {
			new Principal().executar();
		} catch ( Exception e ) {
			this.logger.error( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
			throw new ApplicationException( "Erro inesperado" + System.lineSeparator() + e.getMessage().trim(), e );
		}
	}

}