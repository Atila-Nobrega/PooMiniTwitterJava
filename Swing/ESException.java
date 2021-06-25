package myTwitter.Swing;

public class ESException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ESException() {
		super("Erro: não foi possivel salvar o arquivo!");
	}
}
