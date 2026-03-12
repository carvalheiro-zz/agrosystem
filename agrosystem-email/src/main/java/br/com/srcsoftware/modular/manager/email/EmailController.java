package br.com.srcsoftware.modular.manager.email;

import java.util.ArrayList;

import javax.mail.MessagingException;

import br.com.srcsoftware.manager.principal.Envio;
import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.utilidades.Parametrizacao;

public abstract class EmailController{

	public static void enviarEmailReenvioSenha( String email, String login, String senha ) throws ApplicationException {

		try {
			ArrayList< String > destinatarios = new ArrayList< String >();

			destinatarios.add( email );

			StringBuffer conteudoEmail = new StringBuffer();
			conteudoEmail.append( "<html>" );
			conteudoEmail.append( "	<body>" );
			conteudoEmail.append( "		<br>" );
			conteudoEmail.append( "		<p><H2>ECOMM</H2>" );
			conteudoEmail.append( "		<br>" );
			conteudoEmail.append( "		<p><H3>Serviço automático de reenvio de senha</H3>" );
			conteudoEmail.append( "		<br>" );
			conteudoEmail.append( "		Usuário: " + login );
			conteudoEmail.append( "		<br>" );
			conteudoEmail.append( "		Senha: " + senha );
			conteudoEmail.append( "		<br>" );
			conteudoEmail.append( "	</body>" );
			conteudoEmail.append( "</html>" );

			Envio envio = new Envio( Parametrizacao.parametros.get( "emailServidor" ).toString(), Parametrizacao.parametros.get( "senhaServidor" ).toString(), Parametrizacao.parametros.get( "smtpServidor" ).toString(), Parametrizacao.parametros.get( "portaServidor" ).toString() );
			envio.emailComAnexo( Parametrizacao.parametros.get( "emailRemetente" ).toString(), destinatarios, "Reenvio de senha", conteudoEmail.toString(), null, null );
		} catch ( MessagingException e ) {
			throw new ApplicationException( "Falha no Reenvio de Senha! ", e );
		} catch ( Throwable e ) {
			throw new ApplicationException( "Erro inesperado [ " + e.getMessage() + " ]", e );
		}
	}

	public static void enviarEmailPreCadastro( String email, String login, String senha, String orgaoLicitante ) throws ApplicationException {

		try {
			ArrayList< String > destinatarios = new ArrayList< String >();

			destinatarios.add( email );

			StringBuffer conteudoEmail = new StringBuffer();
			conteudoEmail.append( "<html>" );
			conteudoEmail.append( "	<body>" );
			conteudoEmail.append( "		<br>" );
			conteudoEmail.append( "		<p><H2>ECOMM</H2>" );
			conteudoEmail.append( "		<br>" );
			conteudoEmail.append( "		<p><H3>Serviço automático de envio de senha</H3>" );
			conteudoEmail.append( "		<br>" );
			conteudoEmail.append( "		<p><H4>Obrigado por se cadastrar no sistema Ecomm da " + orgaoLicitante + " </H4>" );
			conteudoEmail.append( "		<br>" );
			conteudoEmail.append( "		Usuário: " + login );
			conteudoEmail.append( "		<br>" );
			conteudoEmail.append( "		Senha: " + senha );
			conteudoEmail.append( "		<br>" );
			conteudoEmail.append( "	</body>" );
			conteudoEmail.append( "</html>" );

			Envio envio = new Envio( Parametrizacao.parametros.get( "emailServidor" ).toString(), Parametrizacao.parametros.get( "senhaServidor" ).toString(), Parametrizacao.parametros.get( "smtpServidor" ).toString(), Parametrizacao.parametros.get( "portaServidor" ).toString() );
			envio.emailComAnexo( Parametrizacao.parametros.get( "emailRemetente" ).toString(), destinatarios, "Reenvio de senha", conteudoEmail.toString(), null, null );
		} catch ( MessagingException e ) {
			throw new ApplicationException( "Falha no envio de Senha! ", e );
		} catch ( Throwable e ) {
			throw new ApplicationException( "Erro inesperado [ " + e.getMessage() + " ]", e );
		}
	}
}
