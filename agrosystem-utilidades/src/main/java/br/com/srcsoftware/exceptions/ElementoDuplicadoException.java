package br.com.srcsoftware.exceptions;

public class ElementoDuplicadoException extends Exception{

	private static final long serialVersionUID = -8838018126081424844L;

	public ElementoDuplicadoException( String message ){
		super( message );
	}

	public ElementoDuplicadoException( String message, Throwable cause ){
		super( message, cause );
	}

}
