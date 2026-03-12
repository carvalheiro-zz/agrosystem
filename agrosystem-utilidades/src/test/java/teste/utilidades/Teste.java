package teste.utilidades;

import org.junit.Test;

import br.com.srcsoftware.modular.manager.exceptions.ApplicationException;
import br.com.srcsoftware.modular.manager.utilidades.UploadManager;

public class Teste{
	@Test
	public void executar() {

		try {
			//UploadManager.upload( "D:/temp/casa1.png", "D:/testeUpload/casa1.png" );

			UploadManager.copiarTodoConteudo( "D:/temp", "D:/testeUpload" );
		} /*catch ( IOException e ) {
		  e.printStackTrace();
		  } */catch ( ApplicationException e ) {
			e.printStackTrace();
		}
	}

}
