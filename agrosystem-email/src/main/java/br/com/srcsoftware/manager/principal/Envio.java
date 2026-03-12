package br.com.srcsoftware.manager.principal;

import java.util.ArrayList;

import javax.mail.MessagingException;

import br.com.srcsoftware.manager.controller.SendMail;

/**
 * Classe respons�vel por gerenciar o envio de emails.
 * 
 * @author Gabriel Damiani Carvalheiro <b>gabriel.carvalheiro@gmail.com</b>
 * @since 28/04/2011
 * @version 1.2
 */
public class Envio{

	private String mailSMTPServer;
	private String mailSMTPServerPort;
	private String smtpMailUser;
	private String smtpMailPassword;

	/**
	 * M�todo construtor que quando chamado receber� os dados de configuração do SMTP.
	 * 
	 * @param String smtpMailUser - Email do usuario do smtp.
	 * @param String smtpMailPassword - Password do usuario do smtp.
	 * @param String mailSMTPServer - Email do servidor do smtp.
	 * @param String mailSMTPServerPort - Password do servidor do smtp.
	 */
	public Envio( String smtpMailUser, String smtpMailPassword, String mailSMTPServer, String mailSMTPServerPort ){
		this.mailSMTPServer = mailSMTPServer;
		this.mailSMTPServerPort = mailSMTPServerPort;
		this.smtpMailUser = smtpMailUser;
		this.smtpMailPassword = smtpMailPassword;
	}

	/**
	 * M�todo respons�vel pela recepção dos dados de construção do email.
	 * 
	 * @param String emailRemetente - Endere�o de email do remetente.
	 * @param ArrayList<String> emailsDestinatarios - Endere�os de email dos destinatarios.
	 * @param String assunto - Assunto do email a ser enviado.
	 * @param String mensagem - Mensagem do email a ser enviado.
	 * @param String caminhoAnexo - Caminho onde se localiza o arquivo a ser anexado ao email. Ex: C:\\FILES\\NEGOCIO\\dados.txt
	 * @param String nomeAnexo - Nome do arquivo a ser anexado ao email. Ex: dados.txt
	 * @return true caso o email tenha sido enviado com sucesso e false caso n�o tenha sido enviado com sucesso.
	 * @throws MessagingException
	 */
	public boolean emailComAnexo( String emailRemetente, ArrayList< String > emailsDestinatarios, String assunto, String mensagem, String caminhoAnexo, String nomeAnexo ) throws MessagingException {
		SendMail sm = new SendMail( smtpMailUser, smtpMailPassword, mailSMTPServer, mailSMTPServerPort );
		sm.sendMail( emailRemetente, emailsDestinatarios, assunto, mensagem, caminhoAnexo, nomeAnexo );
		return true;
	}
}
