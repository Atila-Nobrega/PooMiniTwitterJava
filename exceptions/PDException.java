package myTwitter.exceptions;

public class PDException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PDException() {
		super("Erro: O perfil está inativo!");
	}

}
