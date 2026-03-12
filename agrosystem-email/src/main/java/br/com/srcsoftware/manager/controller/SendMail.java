package br.com.srcsoftware.manager.controller;

import java.util.ArrayList;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import br.com.srcsoftware.manager.autenticacao.SimpleAuth;

/**
 * Classe respons�vel pela construção, configuração e envio de email com ou sem anexos.
 * 
 * @author Gabriel Damiani Carvalheiro <b>gabriel.carvalheiro@gmail.com</b>
 * @since 28/04/2011
 * @version 1.2
 */
public class SendMail{

	private String mailSMTPServer;
	private String mailSMTPServerPort;
	private String smtpMailUser;
	private String smtpMailPassword;

	/**
	 * M�todo construtor que quando chamado j� ser� atribuido o servidor SMTP do GMAIL
	 * e a porta usada por ele.
	 */
	public SendMail(){ //Para o GMAIL
		mailSMTPServer = "smtp.gmail.com";
		mailSMTPServerPort = "465";
	}

	/**
	 * M�todo construtor que quando chamado receber� os dados de configuração do SMTP.
	 * 
	 * @param String smtpMailUser - Email do usuario do smtp.
	 * @param String smtpMailPassword - Password do usuario do smtp.
	 * @param String mailSMTPServer - Email do servidor do smtp.
	 * @param String mailSMTPServerPort - Password do servidor do smtp.
	 */
	public SendMail( String smtpMailUser, String smtpMailPassword, String mailSMTPServer, String mailSMTPServerPort ){ //Para outro Servidor
		this.mailSMTPServer = mailSMTPServer;
		this.mailSMTPServerPort = mailSMTPServerPort;
		this.smtpMailUser = smtpMailUser;
		this.smtpMailPassword = smtpMailPassword;
	}

	public void sendMail( String de, ArrayList< String > destinatarios, String assunto, String mensagem, String attach, String nomeArquivo ) throws MessagingException {

		//Security.addProvider( new com.sun.net.ssl.internal.ssl.Provider() );

		Properties props = new Properties();

		/*props.put( "mail.transport.protocol", "smtp" );
		props.put( "mail.smtp.host", mailSMTPServer ); //server SMTP do GMAIL
		props.put( "mail.smtp.port", mailSMTPServerPort ); //porta
		props.put( "mail.smtp.auth", "true" ); //ativa br.com.srcsoftware.autenticacao
		props.put( "mail.smtp.user", de ); //usuario ou seja, a conta que esta enviando o email (tem que ser do GMAIL)
		props.put( "mail.smtp.password",smtpMailPassword);*/

		props.put( "mail.transport.protocol", "smtp" ); //define protocolo de envio como SMTP
		props.put( "mail.smtp.starttls.enable", "false" );
		props.put( "mail.smtp.host", mailSMTPServer ); //server SMTP do GMAIL
		props.put( "mail.smtp.auth", "true" ); //ativa br.com.srcsoftware.autenticacao
		props.put( "mail.smtp.user", de ); //usuario ou seja, a conta que esta enviando o email (tem que ser do GMAIL)
		props.put( "mail.debug", "true" );
		props.put( "mail.smtp.port", mailSMTPServerPort ); //porta
		props.put( "mail.smtp.socketFactory.port", mailSMTPServerPort ); //mesma porta para o socket
		//props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put( "mail.smtp.socketFactory.fallback", "false" );

		/*props.setProperty("proxySet","true");
		props.setProperty("socksProxyHost","192.168.20.200");
		props.setProperty("socksProxyPort","3128");*/

		//Cria um autenticador que sera usado a seguir
		SimpleAuth auth = null;
		auth = new SimpleAuth( smtpMailUser, smtpMailPassword );

		// Session - objeto que ira realizar a conex�o com o servidor
		/*Como h� necessidade de autenticação � criada uma br.com.srcsoftware.autenticacao que
		 * � responsavel por solicitar e retornar o usu�rio e senha para
		 * autenticação */
		Session s = Session.getDefaultInstance( props, auth );
		s.setDebug( true ); //Habilita o LOG das açães executadas durante o envio do email

		Message message = new MimeMessage( s );
		// Estipula quem esta enviando  
		InternetAddress from = new InternetAddress( de );
		message.setFrom( from );

		//Estipula para quem ser� enviado 
		int arrayLength = destinatarios.size();
		InternetAddress[ ] enderecosDestinatarios = new InternetAddress[ arrayLength ];
		for ( int i = 0; i < arrayLength; i++ ) {
			enderecosDestinatarios[ i ] = new InternetAddress( destinatarios.get( i ) );
		}
		message.setRecipients( Message.RecipientType.TO, enderecosDestinatarios );
		message.setSubject( assunto );

		MimeMultipart mpRoot = new MimeMultipart( "mixed" );
		MimeMultipart mpContent = new MimeMultipart( "alternative" );
		MimeBodyPart contentPartRoot = new MimeBodyPart();
		contentPartRoot.setContent( mpContent );
		mpRoot.addBodyPart( contentPartRoot );

		//enviando texto  
		//XXX
		MimeBodyPart mbp1 = new MimeBodyPart();
		mbp1.setText( ".Jar" );
		mpContent.addBodyPart( mbp1 );

		//enviando html  
		MimeBodyPart mbp2 = new MimeBodyPart();
		mbp2.setContent( "<P> " + mensagem + " </P>", "text/html" );
		mpContent.addBodyPart( mbp2 );

		// verificando se possui anexos
		if ( attach != null ) {
			//enviando anexo  
			MimeBodyPart mbp3 = new MimeBodyPart();
			DataSource fds = new FileDataSource( attach );
			mbp3.setDisposition( Part.ATTACHMENT );
			mbp3.setDataHandler( new DataHandler( fds ) );
			mbp3.setFileName( nomeArquivo );

			mpRoot.addBodyPart( mbp3 );
		}

		message.setContent( mpRoot );
		message.saveChanges();

		Transport.send( message );
	}
}