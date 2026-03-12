package createhibernateconfig;

import java.io.File;
import java.io.IOException;

public class GeradorMappingsHibernateConfigXML{

	public static void main( String[ ] args ) throws IOException {
		String arquivoWave = "C:/SRC/UML_MANAGER/projeto/workspace/atlantis/atlantis-po/src/main/java";

		File pastaRaiz = new File( arquivoWave );

		percorrerPastas( pastaRaiz );

		System.out.println( arquivoWave );
	}

	private static void percorrerPastas( File pastaRaiz ) throws IOException {
		for ( File fileCorrente : pastaRaiz.listFiles() ) {
			if ( fileCorrente.isDirectory() ) {
				percorrerPastas( fileCorrente );
			}
			if ( fileCorrente.isFile() ) {
				System.out.println( "<mapping class=\"" + fileCorrente.getAbsolutePath().replace( "\\", "/" ).replace( "C:/SRC/UML_MANAGER/projeto/workspace/atlantis/atlantis-po/src/main/java/", "" ).replace( ".java", "" ).replace( "/", "." ) + "\" />" );
			}
		}
	}

}
