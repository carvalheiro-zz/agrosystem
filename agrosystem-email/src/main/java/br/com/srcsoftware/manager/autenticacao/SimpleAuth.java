package br.com.srcsoftware.manager.autenticacao;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Clase reponsável por criar uma br.com.srcsoftware.autenticacao para ser enviada e verificada pelo servidor smtp.
 * 
 * @author Gabriel Damiani Carvalheiro <b>gabriel.carvalheiro@gmail.com</b>
 * @since 28/04/2011
 * @version 1.2
 */
public class SimpleAuth extends Authenticator{
	public String username = null;
	public String password = null;

	public SimpleAuth( String user, String pwd ){
		username = user;
		password = pwd;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication( username, password );
	}
}
