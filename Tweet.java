package myTwitter;

import java.io.Serializable;

public class Tweet implements Serializable {
	private String usuario;
	private String mensagem;
	
	public Tweet(String usuario, String mensagem) {
		setUsuario(usuario);
		setMensagem(mensagem);
	}
	
	private void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	private void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public String getMensagem() {
		return mensagem;
	}
}
