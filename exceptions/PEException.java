package myTwitter.exceptions;

public class PEException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PEException() {
		super("Erro: Perfil já existente!");
	}

}
